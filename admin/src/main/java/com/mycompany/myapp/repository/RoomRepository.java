package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Room;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Room entity.
 */
public interface RoomRepository extends JpaRepository<Room,Long> {

}
