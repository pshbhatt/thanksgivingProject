package com.learn.project.thanksgiving.Repository;

import com.learn.project.thanksgiving.Entity.GameCharacter;
import org.springframework.data.repository.CrudRepository;

public interface CharacterRepository extends CrudRepository<GameCharacter, Long> {
}
