package org.codeGym.javiModuleTwo.models.carnivore;


import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.services.Carnivore;

import java.util.List;

public class Bear extends Animal implements Carnivore {

    public Bear(){
        this.setTypeOfAnimal("Carnivore");
        this.setAnimalMemory("Animal:", AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()));
        this.setAnimalMemory("Type:", getTypeOfAnimal());
    }



    public String breedNewBaby(){
        return "The Bear breeds the new baby jealously.";
    }

}
