package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Hotel;
import com.mycompany.myapp.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Hotel.
 */
@RestController
@RequestMapping("/api")
public class HotelResource {

    private final Logger log = LoggerFactory.getLogger(HotelResource.class);

    @Inject
    private HotelRepository hotelRepository;

    /**
     * POST  /hotels -> Create a new hotel.
     */
    @RequestMapping(value = "/hotels",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@Valid @RequestBody Hotel hotel) throws URISyntaxException {
        log.debug("REST request to save Hotel : {}", hotel);
        if (hotel.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new hotel cannot already have an ID").build();
        }
        hotelRepository.save(hotel);
        return ResponseEntity.created(new URI("/api/hotels/" + hotel.getId())).build();
    }

    /**
     * PUT  /hotels -> Updates an existing hotel.
     */
    @RequestMapping(value = "/hotels",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@Valid @RequestBody Hotel hotel) throws URISyntaxException {
        log.debug("REST request to update Hotel : {}", hotel);
        if (hotel.getId() == null) {
            return create(hotel);
        }
        hotelRepository.save(hotel);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /hotels -> get all the hotels.
     */
    @RequestMapping(value = "/hotels",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Hotel> getAll() {
        log.debug("REST request to get all Hotels");
        return hotelRepository.findAll();
    }

    /**
     * GET  /hotels/:id -> get the "id" hotel.
     */
    @RequestMapping(value = "/hotels/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Hotel> get(@PathVariable Long id) {
        log.debug("REST request to get Hotel : {}", id);
        return Optional.ofNullable(hotelRepository.findOne(id))
            .map(hotel -> new ResponseEntity<>(
                hotel,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /hotels/:id -> delete the "id" hotel.
     */
    @RequestMapping(value = "/hotels/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Hotel : {}", id);
        hotelRepository.delete(id);
    }
}
