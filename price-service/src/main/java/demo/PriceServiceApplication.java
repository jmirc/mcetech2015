package demo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringCloudApplication
@EnableHystrix
@EnableConfigurationProperties(PriceProperties.class)
public class PriceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriceServiceApplication.class, args);
    }
}

@ConfigurationProperties(prefix = "rooms")
class PriceProperties {

    private Map<Long, BigDecimal> prices = new HashMap<>();

    public Map<Long, BigDecimal> getPrices() {
        return prices;
    }
}

@RestController
class PriceResource {

    @Autowired
    private PriceProperties priceProperties;

    /**
     * GET  /prices/:id -> get the price associated for "id" hotel.
     */
    @HystrixCommand(threadPoolKey = "priceService", commandKey = "getPrice", fallbackMethod = "defaultPrice")
    @RequestMapping(value = "/price/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BigDecimal> get(@PathVariable Long id) {
        if (priceProperties.getPrices().containsKey(id)) {
            return ResponseEntity.ok(priceProperties.getPrices().get(id));
        }

        if (id == 3L) {
            throw new IllegalArgumentException("Retrive the default price");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    private ResponseEntity<BigDecimal> defaultPrice(Long id) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BigDecimal(-1));
    }
}
