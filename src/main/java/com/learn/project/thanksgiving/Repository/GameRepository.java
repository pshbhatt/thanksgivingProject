package com.learn.project.thanksgiving.Repository;

import com.learn.project.thanksgiving.Entity.Item;
import com.learn.project.thanksgiving.Entity.Registry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends CrudRepository<Registry, Long> {

    @Query("from Registry r where r.className=:className")
    public Registry findByClassName(@Param("className") String className);


    @Query("Delete from Registry r where r.className=:className")
    public Registry deleteByClassName(@Param("className") String className);


}
