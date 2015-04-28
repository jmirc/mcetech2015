# Basic Workshop 

## Update the RegistryServiceApplication

1. Add the @EnableEurekaServer annotation to enable the eureka server
    

## Update the application.yml

    server:
      port: 8761

    eureka:
      instance:
        hostname: localhost
      client:
        registerWithEureka: false
        fetchRegistry: false

