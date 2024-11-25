package org.codeGym.javiModuleTwo.models.carnivore;

import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.herbivore.Rabbit;
import org.codeGym.javiModuleTwo.services.Carnivore;

import java.util.List;
import java.util.Random;

public class Wolf extends Animal implements Carnivore {



    @Override
    public boolean hunt(List<Animal> possiblePreyList) {
        Random random = new Random();

        for (Animal animal : possiblePreyList){
            if (animal instanceof Rabbit){
                animal.setPossibilityOfBeingEaten(60);
            }

        }
return  true;
    }


    @Override
    public void breed() {
        System.out.println("El lobo se esta reproduciendo");
    }

    @Override
    public void die() {
        System.out.println("El lobo se murio");
    }



}
