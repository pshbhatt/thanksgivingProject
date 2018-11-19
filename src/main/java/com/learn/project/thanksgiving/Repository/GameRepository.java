package com.learn.project.thanksgiving.Repository;

import com.learn.project.thanksgiving.Entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Item, Long> {

}
