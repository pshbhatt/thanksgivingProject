package com.learn.project.thanksgiving.Controller;

import com.learn.project.thanksgiving.Entity.Item;
import com.learn.project.thanksgiving.Repository.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
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
    public String deleteGame(@PathVariable Long id){
        if (!repo.existsById(id)) {
            return "404";
        }
        this.repo.deleteById(id);
        return "200";

    }

    @GetMapping("/object/get/Game/{id}")
    public Optional<Item> getGame(@PathVariable Long id){
        return this.repo.findById(id);
    }

}
