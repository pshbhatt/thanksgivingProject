package com.learn.project.thanksgiving.Repository;

import com.learn.project.thanksgiving.Entity.Registry;
import com.learn.project.thanksgiving.Entity.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends CrudRepository<Room, Long> {

    /*@Query("from GameCharacter gc where gc.id=:id")
    public Room findById(@Param("id") Long id);*/
}
