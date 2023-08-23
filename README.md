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
