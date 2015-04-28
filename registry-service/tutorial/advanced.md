# Advanced Workshop

---
spring:
  profiles: peer # not standalone
eureka:
  instance:
    hostname: localhost
  client:
    registerWithEureka: true
    fetchRegistry: true
    serviceUrl:
      defaultZone: http://localhost2:8762/eureka/

---
spring:
  profiles: other # not standalone
server:
  port: 8762
eureka:
  instance:
    hostname: localhost2
    nonSecurePort: ${server.port}
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/


---
spring:
  profiles: remote # connect to remote peer
server:
  port: 8762
eureka:
  instance:
    hostname: localhost
  client:
    registerWithEureka: true
    fetchRegistry: true
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/




