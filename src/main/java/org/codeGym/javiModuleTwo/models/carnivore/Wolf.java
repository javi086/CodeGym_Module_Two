package org.codeGym.javiModuleTwo.models.carnivore;

import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.herbivore.Rabbit;
import org.codeGym.javiModuleTwo.services.Carnivore;

import java.util.List;
import java.util.Random;

public class Wolf extends Animal implements Carnivore {


    public Wolf() {
        this.setAlive(true);
    }

    public void eat(List<Animal> preyList) {
        /*boolean wasTheHuntSuccessful = false;
        System.out.println("El lobo se prepara para cazar");
        wasTheHuntSuccessful = hunt(preyList);
        if (wasTheHuntSuccessful){
            //System.out.println("El lobo esta comiendo");
            animalMemory.put("Eat", "El lobo esta comiendo");
        }
        else{
            System.out.println("El lobo sigue con hambre");
            animalMemory.put("Eat", "El lobo sigue con hambre");
        }*/

    }
    @Override
    public boolean hunt(List<Animal> possiblePreyList) {
        Random random = new Random();

        for (Animal animal : possiblePreyList){
            if (animal instanceof Rabbit){
                animal.setPossibilityOfBeingEaten(60);
            }

        }
return  true;
        /*int preyIndex = random.nextInt(preyList.size());
        preyList.get(preyIndex).setAlive(false);
        return true;*/
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
