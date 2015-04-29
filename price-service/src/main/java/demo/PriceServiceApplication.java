package demo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringCloudApplication
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
