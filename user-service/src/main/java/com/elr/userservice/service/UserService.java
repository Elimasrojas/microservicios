package com.elr.userservice.service;

import com.elr.userservice.entity.User;
import com.elr.userservice.feignclients.BikeFeignClient;
import com.elr.userservice.feignclients.CarFeignClient;
import com.elr.userservice.models.Bike;
import com.elr.userservice.models.Car;
import com.elr.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    private final CarFeignClient carFeignClient;

    private final BikeFeignClient bikeFeignClient;

    public UserService(UserRepository userRepository, RestTemplate restTemplate,
                       CarFeignClient carFeignClient, BikeFeignClient bikeFeignClient) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.carFeignClient = carFeignClient;
        this.bikeFeignClient = bikeFeignClient;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    /*
    public  List<Car> getCars(int userId) {
        //List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/byuser/" + UserId, List.class);
        ResponseEntity<List<Car>> responseEntity = restTemplate.exchange(
                "http://localhost:8002/car/byuser/" + userId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Car>>() {}
        );

        List<Car> cars = responseEntity.getBody();
        return cars;
    }
    */
    public  List<Car> getCars(int userId) {

        List<Car> cars = carFeignClient.getCars(userId);
        return cars;
    }


    public  List<Bike> getBikes(int UserId) {
        List<Bike> bikes;
        bikes = restTemplate.getForObject("http://localhost:8003/bike/byuser/"+UserId,List.class);
        return bikes;
    }

    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }
    public Bike saveBike(int userId, Bike bike) {
        bike.setUserId(userId);
        Bike bikeNew = bikeFeignClient.save(bike);
        return bikeNew;
    }

    public Map<String, Object> getUserAndVehicles(int userId) {
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            result.put("Mensaje", "no existe el usuario");
            return result;
        }
        result.put("User", user);
        List<Car> cars = carFeignClient.getCars(userId);
        if(cars.isEmpty())
            result.put("Cars", "ese user no tiene coches");
        else
            result.put("Cars", cars);
        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if(bikes.isEmpty())
            result.put("Bikes", "ese user no tiene motos");
        else
            result.put("Bikes", bikes);
        return result;
    }

}
