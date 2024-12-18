package org.codeGym.javiModuleTwo.models.carnivore;

import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.services.Carnivore;

import java.util.ArrayList;
import java.util.List;

public class Wolf extends Animal implements Carnivore {

    public Wolf(){
        this.setTypeOfAnimal("Carnivore");
        this.setAnimalMemory("Animal:", AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()));
        this.setAnimalMemory("AnimalType:", getTypeOfAnimal());
    }

    public String howl() {
        return "The Wolf is happy and howls because of the new baby";
    }

    @Override
    public String sniff() {
        return "I'm sniffing looking for a possible prey.";
    }

}
