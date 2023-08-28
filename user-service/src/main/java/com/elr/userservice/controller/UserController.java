package com.elr.userservice.controller;

import com.elr.userservice.entity.User;
import com.elr.userservice.models.Bike;
import com.elr.userservice.models.Car;
import com.elr.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user) {
        User userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }
    @CircuitBreaker(name = "carCB", fallbackMethod = "fallbackGetCars")
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId){
        User user= userService.getUserById(userId);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        List<Car> cars=userService.getCars(userId);
        return  ResponseEntity.ok(cars);
    }

    @CircuitBreaker(name = "carCB", fallbackMethod = "fallbackSaveCars")
    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId,
                                       @RequestBody Car car) {
        if(userService.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        Car carNew = userService.saveCar(userId, car);
        return ResponseEntity.ok(car);
    }

    @CircuitBreaker(name = "bikeCB", fallbackMethod = "fallbackGetBikes")
    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId){
        User user= userService.getUserById(userId);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        List<Bike> bikes=userService.getBikes(userId);
        return  ResponseEntity.ok(bikes);
    }

    @CircuitBreaker(name = "bikeCB", fallbackMethod = "fallbackSaveBikes")
    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike) {
        if(userService.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        Bike bikeNew = userService.saveBike(userId, bike);
        return ResponseEntity.ok(bike);
    }

    @CircuitBreaker(name = "allCB", fallbackMethod = "fallbackGetAll")
    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId")
                                                                  int userId) {
        Map<String, Object> result = userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(result);
    }

    //metodos fallback

    public ResponseEntity<List<Car>> fallbackGetCars(@PathVariable("userId") int userId){
        return  new ResponseEntity("El usuario"+ userId+"tiene los coches en el taller",
                HttpStatus.OK);
    }


    public ResponseEntity<Car> fallbackSaveCars(@PathVariable("userId") int userId,
                                       @RequestBody Car car,RuntimeException exc) {
        return  new ResponseEntity("El usuario"+ userId+" no tiene dinero para carros",
                HttpStatus.OK);

    }

    public ResponseEntity<List<Bike>> fallbackGetBikes(@PathVariable("userId") int userId){
        return  new ResponseEntity("El usuario"+ userId+"tiene las Motos en el taller",
                HttpStatus.OK);
    }


    public ResponseEntity<Bike> fallbackSaveBikes(@PathVariable("userId") int userId,
                                                @RequestBody Bike bike,RuntimeException exc) {
        return  new ResponseEntity("El usuario"+ userId+" no tiene dinero para motos",
                HttpStatus.OK);

    }


    public ResponseEntity<Map<String, Object>> fallbackGetAll(@PathVariable("userId")
                                                              int userId,RuntimeException exc) {
        return  new ResponseEntity("El usuario"+ userId+" tiene los vehiculos en el taller",
                HttpStatus.OK);
    }


}