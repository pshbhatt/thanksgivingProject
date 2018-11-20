package com.learn.project.thanksgiving.Entity;

import javax.persistence.*;

@Entity
public class Registry {
    @Id
    private String className;
    String item;
    private String gameCharacter;

    public Registry(){

    }

    public Registry(String className, String item, String gameCharacter) {
        this.className = className;
        this.item = item;
        this.gameCharacter = gameCharacter;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getGameCharacter() {
        return gameCharacter;
    }

    public void setGameCharacter(String gameCharacter) {
        this.gameCharacter = gameCharacter;
    }
}
