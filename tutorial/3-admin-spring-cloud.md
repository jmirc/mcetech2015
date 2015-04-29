# Move the configuration to the Spring Cloud Config

## Update the pom.xml to add the cloud config client dependency

    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-config-client</artifactId>
    </dependency>

## Update the bootstrap.yml

1. Remove the server.port from the application-dev.yml and move to bootstrap.yml
2. Reference the spring cloud config

bootstrap.yml should contain the following content

    server:
      port: 8080
    
    spring:
      application:
        name: admin-service
      cloud:
        config:
          discovery:
            enabled: true
            serviceId: configuration-service
    
    eureka:
      client:
        serviceUrl:
          defaultZone: http://127.0.0.1:8761/eureka/
      instance:
        leaseRenewalIntervalInSeconds: 5
        metadataMap:
          instanceId: ${vcap.application.instance_id:${spring.application.name}:${spring.application.instance_id:${server.port}}}
    
    # Info displayed by spring boot when calling /info
    info:
      name: Admin Service
      description: Hotel Management - Admin Service
      version: ${project.version}


## Create a new admin-service.yml under ~/livecoding-config/ folder

Copy the content of the application.yml file and remove the file 

## Create a new admin-service-dev.yml under ~/livecoding-config/ folder

Copy the content of the application-dev.yml file and remove the file
 


