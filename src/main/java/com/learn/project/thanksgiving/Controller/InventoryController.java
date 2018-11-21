package com.learn.project.thanksgiving.Controller;

import com.learn.project.thanksgiving.Entity.GameCharacter;
import com.learn.project.thanksgiving.Entity.Item;
import com.learn.project.thanksgiving.Entity.Registry;
import com.learn.project.thanksgiving.Repository.CharacterRepository;
import com.learn.project.thanksgiving.Repository.GameRepository;
import com.learn.project.thanksgiving.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println(gameChar.get().getKlass() + "   " + gameChar.get().getName());
        List<String> inventoryList = new ArrayList<String>();
        Registry registry = gameRepo.findByClassName(itemId);
        System.out.println("Klass::" +registry.getClassName() +"      " +registry.getItem());
        String item = registry.getItem();
        System.out.println("Item::" + item);
        inventoryList.add(item);
        System.out.println("Added to list");
        gameChar.get().setInventory(inventoryList.toArray(new String[inventoryList.size()]));
        System.out.println("After adding to character");
        return this.charRepo.save(gameChar.get());
    }

}
