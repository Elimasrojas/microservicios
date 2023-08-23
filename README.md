# microservicios

01 Configuracion Inicial.

02 RestTemplate

03 openfeign
   -novedad
	<properties>
		<java.version>20</java.version>
		<spring-cloud.version>2022.0.4</spring-cloud.version>
	</properties>
	
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-openfeign</artifactId>
	</dependency>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

04 ejemplo de configuracion con config service
	-Anotacion importante
	config-service 
	config-data

	dependencias
	--
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-config</artifactId>
	</dependency>
	<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-				           starter-bootstrap -->
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-bootstrap</artifactId>
	</dependency>

	<properties>
		<java.version>20</java.version>
		<spring-cloud.version>2022.0.4</spring-cloud.version>
	</properties>
	se crean los archivos de configuracion para acada uno de los servicios
	microservicios/bike-service/src/main/resources/bootstrap.yaml

	configuracion del archivo bootstrap.yaml principal del servidor
	server:
         port: 8081
        spring:
         cloud:
           config:
            server:
             git:
             default-label: 04_config_server
             uri: https://github.com/Elimasrojas/microservicios
            search-paths: config-data
        application:
          name: config-service


05 ejemplo de configuracion con eureka
	--orden de ejecucion
		1-conf-service
		2-eureka-service
		3-bike-service
		4-car-service
		5-user-service


06 ejemplo de configuracion con eureka multiples instancias
	--orden de ejecucion 
		1-conf-service
		2-eureka-service
		3-bike-service
		4-car-service
		5-user-service
	al abrir http://localhost:8761/
	vemos que se crean varias instancias como estas
	* bike-service:c673d34f09e5718ff4ef4cc1770a8ec8 
	* bike-service:f5eb595a4b93d98cdb87fc555802dc13

	aca tenemos un problema al estar configurando la puerta de enlace
	por eseo se recomienda el gateway

