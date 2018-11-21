package com.learn.project.thanksgiving.Controller;

import com.learn.project.thanksgiving.Repository.CharacterRepository;
import com.learn.project.thanksgiving.Repository.GameRepository;
import com.learn.project.thanksgiving.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BattleController {

    @Autowired
    RoomRepository roomRepo;

    @Autowired
    CharacterRepository charRepo;

    @Autowired
    GameRepository gameRepo;

}
