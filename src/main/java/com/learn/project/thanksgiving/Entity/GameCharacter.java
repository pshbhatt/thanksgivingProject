package com.learn.project.thanksgiving.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonPropertyOrder({ "id", "name", "klass","intelligence","wisdom","charchar","strength", "dexterity", "con", "location", "inventory","hitPoints"})
public class GameCharacter {

    public GameCharacter(Long id, String name, String klass, int intelligence, int wisdom, int charchar, int strength, int dexterity, int con, int location, String[] inventory, int hitPoints) {
        this.id = id;
        this.name = name;
        this.klass = klass;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charchar = charchar;
        this.strength = strength;
        this.dexterity = dexterity;
        this.con = con;
        this.location = location;
        this.inventory = inventory;
        this.hitPoints = hitPoints;
    }

    public GameCharacter() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonProperty("class")
    private String klass;
    @JsonProperty("int")
    private int intelligence;
    @JsonProperty("wis")
    private int wisdom;
    @JsonProperty("cha")
    private int charchar;
    @JsonProperty("str")
    private int strength;
    @JsonProperty("dex")
    private int dexterity;
    private int con;
    private int location;
    private String[] inventory;
    private int hitPoints;

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

    public String getKlass() {
        return klass;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }
    @JsonProperty("cha")
    public int getCharacter() {
        return charchar;
    }
    @JsonProperty("cha")
    public void setCharacter(int charchar) {
        this.charchar = charchar;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String[] getInventory() {
        return inventory;
    }

    public void setInventory(String[] inventory) {
        this.inventory = inventory;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
