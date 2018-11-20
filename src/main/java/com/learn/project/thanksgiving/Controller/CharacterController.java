package com.learn.project.thanksgiving.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.project.thanksgiving.Entity.GameCharacter;
import com.learn.project.thanksgiving.Entity.Item;
import com.learn.project.thanksgiving.Entity.Registry;
import com.learn.project.thanksgiving.Repository.CharacterRepository;
import com.learn.project.thanksgiving.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CharacterController {

    List<Integer> valuesList = new ArrayList<>();
    public CharacterController(){
        Random rand = new Random();

        for(int i=0;i<6;i++){
            int n = rand.nextInt(18) + 8;
            valuesList.add(n);
        }
        Collections.sort(valuesList);
    }

    @Autowired
    GameRepository gameRepo;

    @Autowired
    CharacterRepository charRepo;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/character/gen/{name}/{klass}")
    public ResponseEntity<GameCharacter> getCharacter(@PathVariable String name, @PathVariable String klass) throws JsonProcessingException {
        Registry registry = new Registry();
        registry.setClassName(klass);
        Item item = new Item();
        item.setId(1);
        item.setName(name);
        String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(item);
        registry.setItem(jsonInString);
        GameCharacter character = new GameCharacter();
        character.setName(name);
        character.setKlass(klass);
        character.setInventory(new String[0]);
        character.setCon(valuesList.get(1));
        character.setHitPoints(character.getCon()*2);
        character.setLocation(4);
        character.setWisdom(valuesList.get(2));
        HttpStatus status;
        if(klass.equals("Warrior")){
            character.setStrength(valuesList.get(valuesList.size()-1));
            character.setIntelligence(valuesList.get(0));
            character.setCharacter(valuesList.get(4));
            character.setDexterity(valuesList.get(3));
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(character);
            registry.setGameCharacter(json);
            status = HttpStatus.OK;

        } else if(klass.equals("Archer")){
            character.setDexterity(valuesList.get(valuesList.size()-1));
            character.setCharacter(valuesList.get(0));
            character.setIntelligence(valuesList.get(4));
            character.setStrength(valuesList.get(3));
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(character);
            registry.setGameCharacter(json);
            status = HttpStatus.OK;
        } else if(klass.equals("Wizard")){
            character.setIntelligence(valuesList.get(valuesList.size()-1));
            character.setStrength(valuesList.get(0));
            character.setDexterity(valuesList.get(4));
            character.setCharacter(valuesList.get(3));
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(character);
            registry.setGameCharacter(json);
            status = HttpStatus.OK;
        } else if(klass.equals("Rogue")){
            character.setCharacter(valuesList.get(valuesList.size()-1));
            character.setStrength(valuesList.get(0));
            character.setDexterity(valuesList.get(4));
            character.setIntelligence(valuesList.get(3));
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(character);
            registry.setGameCharacter(json);
            status = HttpStatus.OK;

        } else {
            character.setKlass("Class not found");
            character.setName("Error message: 404");
            status = HttpStatus.NOT_FOUND;
        }
        gameRepo.save(registry);
        return new ResponseEntity<GameCharacter>(character,status);

    }

}
