package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Room;
import com.mycompany.myapp.repository.RoomRepository;
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
 * REST controller for managing Room.
 */
@RestController
@RequestMapping("/api")
public class RoomResource {

    private final Logger log = LoggerFactory.getLogger(RoomResource.class);

    @Inject
    private RoomRepository roomRepository;

    /**
     * POST  /rooms -> Create a new room.
     */
    @RequestMapping(value = "/rooms",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@Valid @RequestBody Room room) throws URISyntaxException {
        log.debug("REST request to save Room : {}", room);
        if (room.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new room cannot already have an ID").build();
        }
        roomRepository.save(room);
        return ResponseEntity.created(new URI("/api/rooms/" + room.getId())).build();
    }

    /**
     * PUT  /rooms -> Updates an existing room.
     */
    @RequestMapping(value = "/rooms",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@Valid @RequestBody Room room) throws URISyntaxException {
        log.debug("REST request to update Room : {}", room);
        if (room.getId() == null) {
            return create(room);
        }
        roomRepository.save(room);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /rooms -> get all the rooms.
     */
    @RequestMapping(value = "/rooms",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Room> getAll() {
        log.debug("REST request to get all Rooms");
        return roomRepository.findAll();
    }

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
            .map(room -> new ResponseEntity<>(
                room,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /rooms/:id -> delete the "id" room.
     */
    @RequestMapping(value = "/rooms/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Room : {}", id);
        roomRepository.delete(id);
    }
}
