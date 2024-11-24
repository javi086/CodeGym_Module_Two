package org.codeGym.javiModuleTwo.models.Plant;


import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;
import org.codeGym.javiModuleTwo.services.Photosynthetic;

import java.util.List;

public class Plant  extends Animal implements Photosynthetic {



    public void eat(List<Animal> animalList) {
    }

    public void eat(){
        System.out.println("La planta se esta alimentando de la tierra");
    }


    @Override
    public void breed() {

    }

    @Override
    public void die() {

    }

    @Override
    public void performPhotosynthesis() {

    }

    /*private boolean isAlive;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }*/
}
