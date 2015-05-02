# Build the docker image
mvn clean package docker:build

# Run the docker image
docker run -d -p 8080:8080 -e "ADMIN_SERVICE_URL=192.168.59.103" -e "EUREKA_URL=http://192.168.59.103:8761/eureka/" -t mcetech/price-service
