# How to configure the admin tool to call the new service using eureka

The new price will be displayed on the front-end in the detail page of a room

## Update the Room.java class

Add a new transient field named price. Transient is used to not be saved in the DB

    private transient BigDecimal price;

    public BigDecimal getPrice() {
      return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


## Netflix Feign is used to communicate to the price-service REST Api

### Update the pom.xml

    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-feign</artifactId>
    </dependency>

### Update the Application.java

Add the @EnableFeignClients annotation on the class. It is used to create feign
client on any class that uses @FeignClient annotation

### Create a new PriceService interface

@FeignClient is an annotation used to create a service client

    import java.math.BigDecimal;

    import org.springframework.cloud.netflix.feign.FeignClient;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;

    @FeignClient("price-service")
    public interface PriceService {

      @RequestMapping(value = "/price/{id}",
              method = RequestMethod.GET)
      ResponseEntity<BigDecimal> get(@PathVariable("id") Long id);

    }

### Call this new service in the RoomResource class


    @Inject
    private PriceService priceService;

    /**
     * GET  /rooms/:id -> get the "id" room.
     */
    @RequestMapping(value = "/rooms/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Room> get(@PathVariable Long id) {
        log.debug("REST request to get Room : {}", id);
        return Optional.ofNullable(roomRepository.findOne(id))
                .map(room -> {
                    try {
                        final ResponseEntity<BigDecimal> responseEntity = priceService.get(id);
                        if (responseEntity.getStatusCode() == HttpStatus.OK) {
                            room.setPrice(responseEntity.getBody());
                        }
                    } catch (Exception e) {
                        log.error("Failed to retrieve the price from the service", e);
                    }
                    return room;
                })
                .map(room -> {
                    System.out.println(room);
                    return new ResponseEntity<>(
                            room,
                            HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


### Call this new resource to see if the price is included

Open the browser on ```http://yulc02n31gyfd58.sea.corp.expecn.com:8080/api/rooms/1```

Here is the response:

    {
      id: 1,
      name: "Deluxe Room, 2 Queen Beds",
      hotel: {
        id: 1,
        name: "Ritz-Carlton",
        description: "<h3>Luxury Montreal hotel in Downtown Montreal, near Notre Dame Basilica</h3><article data-type="read-more"><section><h4>Location</h4><p>Located in Downtown Montreal, this luxury hotel is within a 15-minute walk of Reid Wilson House, Bell Centre, and Christ Church Cathedral. Percival Molson Memorial Stadium and Mount Royal Park are also within 1 mi (2 km).</p><h4>Hotel Features</h4><p>This smoke-free hotel features a restaurant, an indoor pool, and a 24-hour fitness center. WiFi in public areas is free. Additionally, a bar/lounge, a rooftop terrace, and a 24-hour business center are onsite.</p><h4>Room Amenities</h4><p>All 129 rooms boast deep soaking tubs and offer free WiFi and 24-hour room service. Guests will also find LCD TVs, minibars, and premium bedding.</p></section></article><p><br></p>",
        address: "1228 Sherbrooke Street West",
        city: "Montreal",
        postalCode: "H3G 1H6",
        province: "Quebec",
        country: "Canada"
      },
      price: 123
    }


### Display the price on the room detail page

Add a new row in the table

    <tr>
        <td>
            <span>Price</span>
        </td>
        <td>
            <input type="text" class="input-sm form-control" value="{{room.price | currency}}" readonly>
        </td>
    </tr>
