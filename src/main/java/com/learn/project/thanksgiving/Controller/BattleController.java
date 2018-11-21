package com.learn.project.thanksgiving.Controller;

import com.learn.project.thanksgiving.Entity.GameCharacter;
import com.learn.project.thanksgiving.Entity.Registry;
import com.learn.project.thanksgiving.Repository.CharacterRepository;
import com.learn.project.thanksgiving.Repository.GameRepository;
import com.learn.project.thanksgiving.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.Optional;
import java.util.Random;

@RestController
public class BattleController {

    @Autowired
    RoomRepository roomRepo;

    @Autowired
    CharacterRepository charRepo;

    @Autowired
    GameRepository gameRepo;

    @PostMapping("/battle/{characterId1}/{characterId2}")
    public ResponseEntity<String> attack(@PathVariable Long characterId1, @PathVariable Long characterId2){
        String finalResult= "";
        Optional<GameCharacter> gameChar1 = charRepo.findById(characterId1);
        Optional<GameCharacter> gameChar2 = charRepo.findById(characterId2);
        Random rand = new Random();
        int character1Value = rand.nextInt(20) + 1;
        int character2Value = rand.nextInt(20) + 1;
        if(character1Value>character2Value){
            int characterAttackValue = rand.nextInt(8) + 1;
            gameChar2.get().setHitPoints(gameChar2.get().getHitPoints()-character1Value);
            charRepo.save(gameChar2.get());
            if(gameChar2.get().getHitPoints()-character1Value>0) {
                finalResult = gameChar1.get().getName() + " successfully strikes " + gameChar2.get().getName() +
                        " and does " + characterAttackValue + " damage.";
            } else {
                finalResult = gameChar1.get().getName() + " successfully strikes " + gameChar2.get().getName() +
                        " and does " + characterAttackValue + " damage. " + gameChar2.get().getName() + " is killed";
            }

        } else if(character1Value<character2Value){
            int characterAttackValue = rand.nextInt(8) + 1;
            gameChar1.get().setHitPoints(gameChar1.get().getHitPoints()-character1Value);
            GameCharacter charchar = charRepo.save(gameChar1.get());
            if(gameChar1.get().getHitPoints()-character1Value>0) {
                finalResult = gameChar2.get().getName() + " successfully strikes " + gameChar1.get().getName() +
                        " and does " + characterAttackValue + " damage.";
            } else {
                finalResult = gameChar2.get().getName() + " successfully strikes " + gameChar1.get().getName() +
                        " and does " + characterAttackValue + " damage. " + gameChar1.get().getName() + " is killed";
            }


        } else if(character1Value==character2Value){
            finalResult = gameChar1.get().getName() + " and "+ gameChar1.get().getName() +
                    " attack each other but both fail to land a blow ";

        }
        return new ResponseEntity<String>(finalResult, HttpStatus.OK);
    }


}
