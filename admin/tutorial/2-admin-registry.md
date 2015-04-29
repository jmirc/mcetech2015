# Registry the admin in Eureka 

## Change the parent in the pom.xml file to support Spring Cloud

    <parent>
        <artifactId>spring-cloud-starter-parent</artifactId>
        <groupId>org.springframework.cloud</groupId>
        <version>1.0.1.RELEASE</version>
        <!-- lookup parent from repository -->
    </parent>

## Update the pom.xml to provide additional dependencies

    <!-- Additional dependencies for the workshop -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-eureka</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-hystrix</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-config</artifactId>
        <version>${spring-security.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-core</artifactId>
        <version>${spring-security.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-crypto</artifactId>
        <version>${spring-security.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-web</artifactId>
        <version>${spring-security.version}</version>
    </dependency>    

## Create a new bootstrap.yml at the same level as the application.yml

Add the following content

    # Name of the service
    spring:
      application:
        name: admin-service

    # Eureka configuration
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

## Update the pom.xml to add filtering on the bootstrap.xml file

Add a new ```<include>**/bootstrap.yml</include>``` section as follow

    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
                <includes>
                    <include>**/*.xml</include>
                    <include>**/bootstrap.yml</include>
                </includes>
            </resource>

## Update the Application.java

Add @EnableDiscoveryClient annotation to enable the Eureka client

## Start the admin and it should be registried on Eureka

ADMIN-SERVICE will be available in the list of available applications.

