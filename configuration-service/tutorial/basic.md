# Basic Workshop 

## Update the ConfigurationServiceApplication

1. Replace the @SpringBootApplication annotation to @SpringCloudApplication
1. Add the @EnableConfigServer annotation to enable the config server

## Update the application.yml

    server:
      port: 8888
    
    eureka:
      client:
        serviceUrl:
          defaultZone: http://localhost:8761/eureka/
      instance:
        leaseRenewalIntervalInSeconds: 5


## Update the bootstrap.yml

    spring:
      application:
        name: configuration-service
      cloud:
        config:
          server:
            git:
              uri: file://${HOME}/livecoding-config
    
    info:
        name: Configuration Service
        description: Configuration Service Server
        version: ${project.version}

# Start this service and check the registry service to see if it has been registred

    http://localhost:8761

You should see CONFIGURATION-SERVICE in the list of registered application


# How to support encryption or decryption

Run the following command

    » curl localhost:8888/encrypt -d passwordToEncrypt

You should got the following message: 

    {"description":"No key was installed for encryption service","status":"NO_KEY"}

    
## Update the bootstrap.yml to add a key

    encrypt:
      key: thisIsMyKeyToEncrypt

Add it and restart the service

## Encrypt a value
    
    » curl http://localhost:8888/encrypt -d passwordToEncrypt
    
Here is the result
    
    52b1497d4f5efd0883fe8b1a83fdb4183462c9ce4c03c2bbef087c943de3da2d38e324fe8cc4df0a02ea7efd7d6c3ac5
    
## Decrypt a value
    
    » curl http://localhost:8888/decrypt -d 52b1497d4f5efd0883fe8b1a83fdb4183462c9ce4c03c2bbef087c943de3da2d38e324fe8cc4df0a02ea7efd7d6c3ac5
    
Here is the result
    
    passwordToEncrypt
    
    
    
