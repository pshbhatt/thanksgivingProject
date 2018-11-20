package com.learn.project.thanksgiving.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.project.thanksgiving.Entity.Item;
import com.learn.project.thanksgiving.Entity.Registry;
import com.learn.project.thanksgiving.Repository.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class GameController {

    @Autowired
    GameRepository repo;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/object/create/{className}")
    public Registry addGame(@PathVariable String className, @RequestBody Item item) throws JsonProcessingException {
        Registry registry = new Registry();
        registry.setClassName(className);
        String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(item);
        registry.setItem(jsonInString);
        return this.repo.save(registry);
    }

    @DeleteMapping("/object/delete/{className}/{id}")
    public ResponseEntity<Registry> deleteGame(@PathVariable String className, @PathVariable Long id) throws IOException {
        Registry registry = repo.findByClassName(className);
            Item item = objectMapper.readValue(registry.getItem(), Item.class);
            if(item.getId()==id){
                this.repo.delete(registry);
                return new ResponseEntity<Registry>(registry,HttpStatus.OK);
            }

        return new ResponseEntity<Registry>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/object/get/{className}/{id}")
    public ResponseEntity<Registry> getGame(@PathVariable String className, @PathVariable Long id) throws IOException {
        Registry registry = repo.findByClassName(className);
            Item item = objectMapper.readValue(registry.getItem(), Item.class);
            if(item.getId()==id){
                //this.repo.findById(id);
                return new ResponseEntity<Registry>(registry,HttpStatus.OK);
            }

        return new ResponseEntity<Registry>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/object/get/{className}")
    public Registry getAllGamesByClassName(@PathVariable String className){
        return this.repo.findByClassName(className);
    }

    @GetMapping("/object/get")
    public Iterable<Registry> getAllGames(){
        return this.repo.findAll();
    }

}
