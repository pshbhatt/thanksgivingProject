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

}
