package org.codeGym.javiModuleTwo.models.carnivore;

import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.herbivore.Rabbit;
import org.codeGym.javiModuleTwo.services.Carnivore;

import java.util.List;
import java.util.Random;

public class Wolf extends Animal implements Carnivore {

    public Wolf(){
        this.setTypeOfAnimal("Carnivore");
        this.setAnimalMemory("Animal:", AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()));
        this.setAnimalMemory("Type:", getTypeOfAnimal());
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
    }

    public void breed() {
        System.out.println("El lobo se esta reproduciendo");
    }





}
