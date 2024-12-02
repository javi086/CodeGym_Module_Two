package org.codeGym.javiModuleTwo.models.Plant;


import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;
import org.codeGym.javiModuleTwo.services.Photosynthetic;

import java.util.List;

public class Plant  extends Animal implements Photosynthetic {

    public Plant(){
        this.setTypeOfAnimal("Photosynthetic");
        this.setAnimalMemory("Animal:", AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()));
        this.setAnimalMemory("Type:", getTypeOfAnimal());
    }

    public void eat(){
        this.setAnimalMemory("Eat:", "The plant is feeding from the ground");
    }

    public void breed() {
        System.out.println("The plant spreads its spores to reproduce");
    }


    @Override
    public void performPhotosynthesis() {
        System.out.println("The plant is happy and implementing photosynthesis");
    }
}
