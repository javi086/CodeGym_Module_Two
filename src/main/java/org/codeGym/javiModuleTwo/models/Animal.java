package org.codeGym.javiModuleTwo.models;

import java.util.List;

public abstract class Animal {

    public float weight;
    public boolean isAlive;
    public char gender;


    public abstract  void eat(List<Animal> animalList);
    public abstract void move();
    public abstract void breed();
    public abstract void die();



    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
