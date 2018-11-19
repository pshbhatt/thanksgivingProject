package com.learn.project.thanksgiving.Controller;

import com.learn.project.thanksgiving.Entity.Item;
import com.learn.project.thanksgiving.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @Autowired
    GameRepository repo;

    @PostMapping("/object/create/Game")
    public Item addGame(@RequestBody Item item){
        return this.repo.save(item);
    }

    @DeleteMapping("/object/delete/Game/{id}")
    public boolean deleteGame(@PathVariable int id){
        if (!repo.existsById((long)id)) {
            throw new RuntimeException();
        }
        this.repo.deleteById((long)id);
        return true;

    }

}
