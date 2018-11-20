package com.learn.project.thanksgiving.Entity;

import javax.persistence.*;

@Entity
public class Registry {
    @Id
    private String className;
    String item;

    public Registry(){

    }

    public Registry(String className, String item) {
        this.className = className;
        this.item = item;
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
}
