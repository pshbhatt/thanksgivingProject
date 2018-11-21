package com.learn.project.thanksgiving.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {

    public Room(Long id, String name, String description, int[] exits) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exits = exits;
    }

    public Room(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int[] exits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getExits() {
        return exits;
    }

    public void setExits(int[] exits) {
        this.exits = exits;
    }
}
