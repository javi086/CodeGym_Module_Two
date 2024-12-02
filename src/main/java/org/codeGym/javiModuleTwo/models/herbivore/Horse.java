package org.codeGym.javiModuleTwo.models.herbivore;

import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;
import org.codeGym.javiModuleTwo.services.Herbivore;

import java.util.List;

public class Horse extends Animal implements Herbivore {

    public Horse(){
        this.setTypeOfAnimal("Herbivore");
        this.setAnimalMemory("Animal:", AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()));
        this.setAnimalMemory("Type:", getTypeOfAnimal());
    }

    public void eat(List<Animal> animalList) {

    }

    public void breed() {

    }



    @Override
    public void pasture() {

    }
}
