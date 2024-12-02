package org.codeGym.javiModuleTwo.models.carnivore;

import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;
import org.codeGym.javiModuleTwo.services.Carnivore;

import java.util.List;

public class Eagle extends Animal implements  Carnivore{

    public Eagle(){
        this.setTypeOfAnimal("Carnivore");
        this.setAnimalMemory("Animal:", AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()));
        this.setAnimalMemory("Type:", getTypeOfAnimal());
    }
    public void eat(List<Animal> animalList) {
        System.out.println("La aguila va a comer");
    }



    public void breed() {

    }



    @Override
    public boolean hunt(List<Animal> preyList) {
        return false;
    }
}
