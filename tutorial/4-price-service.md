# Create a new price service

## Generate the service

    Open browser on ```http://start.spring.io``` and select

    1. Java version: 1.8
    1. Dependencies: actuator

Or use this command line

    curl https://start.spring.io/starter.tgz -d dependencies=actuator,web \
      -d type=maven-project -d baseDir=price-service -d javaVersion=1.8 \
      -d name=priceService | tar -xzvf -

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
        <artifactId>spring-cloud-config-client</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-hystrix</artifactId>
    </dependency>

## Update the pom.xml to configure build section

    <build>
      <resources>
          <resource>
              <directory>src/main/resources</directory>
          </resource>
      </resources>
      ...
    </build>


## Application.properties

Rename the application.properties file to application.yml because yml is more fun ;)

Add the following content:

    server:
      port: 9090

    spring:
      application:
        name: price-service
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


    info:
      name: Price Service
      description: Hotel Management - Price Service
      version: ${project.version}


## Create a new price-service.yml file under ~/livecoding-config folder

    » vi ~/livecoding-config/price-service.yml

Add the following content:

    rooms:
      prices:
        1: 123

## Update the RegistryServiceApplication

Replace the @SpringBootApplication annotation to @SpringCloudApplication

## Read the prices from the property

### Create the PriceProperties class

    @ConfigurationProperties(prefix = "rooms")
    class PriceProperties {

        private Map<Long, BigDecimal> prices = new HashMap<>();

        public Map<Long, BigDecimal> getPrices() {
            return prices;
        }
    }

### Enable the properties

Add the ```@EnableConfigurationProperties(PriceProperties.class)``` annotation on the Application.java


## Create a REST api to retrieve the price using a room's id

    @RestController
    class PriceResource {

        @Autowired
        private PriceProperties priceProperties;

        /**
         * GET  /prices/:id -> get the price associated for "id" hotel.
         */
        @RequestMapping(value = "/price/{id}",
                method = RequestMethod.GET,
                produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<BigDecimal> get(@PathVariable Long id) {
            if (priceProperties.getPrices().containsKey(id)) {
                return ResponseEntity.ok(priceProperties.getPrices().get(id));
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


## Restart the server and try the new API

    Open the browser on ```http://localhost:9090/price/1```

    
