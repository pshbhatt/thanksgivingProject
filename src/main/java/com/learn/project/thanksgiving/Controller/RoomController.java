package com.learn.project.thanksgiving.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.project.thanksgiving.Entity.GameCharacter;
import com.learn.project.thanksgiving.Entity.Registry;
import com.learn.project.thanksgiving.Entity.Room;
import com.learn.project.thanksgiving.Repository.CharacterRepository;
import com.learn.project.thanksgiving.Repository.GameRepository;
import com.learn.project.thanksgiving.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {

    @Autowired
    RoomRepository roomRepo;

    @Autowired
    CharacterRepository charRepo;

    @Autowired
    GameRepository gameRepo;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/room/add")
    public Room addRoom(@RequestBody Room room){
        return this.roomRepo.save(room);
    }

    @PostMapping("/move/{charId}/to/{roomId}")
    public ResponseEntity<GameCharacter> moveToRoom(@PathVariable int charId, @PathVariable int roomId){
    System.out.println("CHARACTER ID::" + charId);
    System.out.println("ROOM ID::" + roomId);
        //Make sure that when a char is created in the JSON, the id increases
        Optional<GameCharacter> gameChar = charRepo.findById((long)charId);
        //CHeck the charId passed and see the location
        System.out.println("RESULTING LOCATION::::" + gameChar.get().getLocation());
        System.out.println("RESULTING CLASS::::" + gameChar.get().getKlass());
        //Match the location to the roomId, if it matches, then check where it is trying to move
        Optional<Room> room  = roomRepo.findById((long)gameChar.get().getLocation());
        System.out.println("RESULTING ROOM exists::::" + room.get().getExits().toString());
        List exitsList = Arrays.asList(room.get().getExits());
        if(exitsList.contains(roomId)){
            gameChar.get().setLocation(roomId);
            charRepo.save(gameChar.get());
            return new ResponseEntity<GameCharacter>(gameChar.get(), HttpStatus.OK);
        } else if(!exitsList.contains(roomId)){
            return new ResponseEntity<GameCharacter>(gameChar.get(), HttpStatus.FORBIDDEN);
        } else{
            return new ResponseEntity<GameCharacter>(gameChar.get(), HttpStatus.UNAUTHORIZED);
        }
        //if the move location is in exits array, move and update the location
    }
}
