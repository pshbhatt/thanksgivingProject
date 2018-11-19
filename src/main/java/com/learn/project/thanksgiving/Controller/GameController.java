package com.learn.project.thanksgiving.Controller;

import com.learn.project.thanksgiving.Entity.Item;
import com.learn.project.thanksgiving.Repository.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@RestController
public class GameController {

    @Autowired
    GameRepository repo;

    @PostMapping("/object/create/Game")
    public Item addGame(@RequestBody Item item){
        return this.repo.save(item);
    }

    @DeleteMapping("/object/delete/Game/{id}")
    public ResponseEntity<Item> deleteGame(@PathVariable Long id){
        if (!repo.existsById(id)) {
            return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
        }
        Item item = new Item();
        this.repo.deleteById(id);
        return new ResponseEntity<Item>(item,HttpStatus.OK);

    }

    @GetMapping("/object/get/Game/{id}")
    public ResponseEntity<Item> getGame(@PathVariable Long id){
       if(!repo.existsById(id)){
           return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
       }
        Iterable<Item> item =  this.repo.findAllById(Arrays.asList(id));
        return new ResponseEntity<Item>(item.iterator().next(),HttpStatus.OK);
    }

    @GetMapping("/object/get/Game")
    public Iterable<Item> getAllGames(){
        return this.repo.findAll();
    }

}
