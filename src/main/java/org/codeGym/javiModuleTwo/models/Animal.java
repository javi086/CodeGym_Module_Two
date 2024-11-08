package org.codeGym.javiModuleTwo.models;

public abstract class Animal {

    public float weight;
    public boolean isAlive;
    public char gender;

    public abstract void eat();
    public abstract void move();
    public abstract void breed();
    public abstract void die();


}
