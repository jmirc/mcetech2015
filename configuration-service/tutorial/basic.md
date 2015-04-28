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

# How to support encryption

Failed to encrypt

    » curl localhost:8888/encrypt -d passwordToEncrypt
    {"description":"No key was installed for encryption service","status":"NO_KEY"}

    
## Update the bootstrap.yml

    encrypt:
      key: thisIsMyKeyToEncrypt

## Encrypt a value
    
    » curl http://localhost:8888/encrypt -d passwordToEncrypt
    52b1497d4f5efd0883fe8b1a83fdb4183462c9ce4c03c2bbef087c943de3da2d38e324fe8cc4df0a02ea7efd7d6c3ac5
    
## Decrypt a value
    
    » curl http://localhost:8888/decrypt -d 52b1497d4f5efd0883fe8b1a83fdb4183462c9ce4c03c2bbef087c943de3da2d38e324fe8cc4df0a02ea7efd7d6c3ac5
    passwordToEncrypt
    
    
    
