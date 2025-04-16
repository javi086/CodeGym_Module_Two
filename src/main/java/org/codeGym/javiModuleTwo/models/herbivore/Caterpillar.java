package org.codeGym.javiModuleTwo.models.herbivore;

import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.interfaces.Herbivore;

public class Caterpillar extends Animal implements Herbivore {

    public Caterpillar(){
        this.setTypeOfAnimal("Herbivore");
        this.setAnimalMemory("Animal:", AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()));
        this.setAnimalMemory("AnimalType:", getTypeOfAnimal());
    }


    public String eatEmptyEggs(){
        return "The Caterpillar eats empty eggs";
    }

    @Override
    public String pasture() {
        return "I'm pasturing.";
    }

}
