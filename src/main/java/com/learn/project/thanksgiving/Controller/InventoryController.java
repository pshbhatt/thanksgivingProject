package com.learn.project.thanksgiving.Controller;

import com.learn.project.thanksgiving.Entity.GameCharacter;
import com.learn.project.thanksgiving.Entity.Item;
import com.learn.project.thanksgiving.Entity.Registry;
import com.learn.project.thanksgiving.Repository.CharacterRepository;
import com.learn.project.thanksgiving.Repository.GameRepository;
import com.learn.project.thanksgiving.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class InventoryController {

    @Autowired
    RoomRepository roomRepo;

    @Autowired
    CharacterRepository charRepo;

    @Autowired
    GameRepository gameRepo;

    @PostMapping("/inventory/pickup/{characterId}/{itemId}")
    public GameCharacter addInventory(@PathVariable Long characterId, @PathVariable String itemId){
        Optional<GameCharacter> gameChar = charRepo.findById(characterId);
        List<String> inventoryList = new ArrayList<String>();
        Registry registry = gameRepo.findByClassName(itemId);
        String item = registry.getItem();
        inventoryList.add(item);
        gameChar.get().setInventory(inventoryList.toArray(new String[inventoryList.size()]));
        return this.charRepo.save(gameChar.get());
    }

    @PostMapping("/inventory/drop/{characterId}/{itemId}")
    public GameCharacter deleteInventory(@PathVariable Long characterId, @PathVariable String itemId){
        Optional<GameCharacter> gameChar = charRepo.findById(characterId);
        Registry registry = gameRepo.findByClassName(itemId);
        gameChar.get().setInventory(new String[0]);
        return this.charRepo.save(gameChar.get());
    }


}
