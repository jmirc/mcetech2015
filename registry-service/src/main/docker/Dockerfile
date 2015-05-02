FROM java:8
MAINTAINER jerome.mirc@gmail.com
# because that is where a Spring Boot application creates working directories for Tomcat by default.
# The effect is to create a temporary file on your host under "/var/lib/docker" and link it to the container under "/tmp"
VOLUME /tmp
ADD registry-service.jar app.jar
RUN bash -c 'touch /app.jar'
# To reduce Tomcat startup time we added a system property pointing to "/dev/urandom" as a source of entropy.
# http://wiki.apache.org/tomcat/HowTo/FasterStartUp#Entropy_Source
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
