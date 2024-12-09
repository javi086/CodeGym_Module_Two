package org.codeGym.javiModuleTwo.models.herbivore;

import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.services.Herbivore;

import java.util.List;

public class Buffalo extends Animal implements Herbivore {

    public Buffalo(){
        this.setTypeOfAnimal("Herbivore");
        this.setAnimalMemory("Animal:", AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()));
        this.setAnimalMemory("AnimalType:", getTypeOfAnimal());
    }

   public String lickTheBaby() {
        return "The Buffalo is licking the new baby.";
   }

    @Override
    public String pasture() {
        return "I'm pasturing.";
    }

}
