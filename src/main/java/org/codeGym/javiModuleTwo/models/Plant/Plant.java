package org.codeGym.javiModuleTwo.models.Plant;


import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.services.Photosynthetic;

public class Plant  extends Animal implements Photosynthetic {

    public Plant(){
        this.setTypeOfAnimal("Photosynthetic");
        this.setAnimalMemory("Animal:", AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()));
        this.setAnimalMemory("AnimalType:", getTypeOfAnimal());
    }

    public void eat(){
        this.setAnimalMemory("Eat:", "The plant is feeding from the ground");
    }

    @Override
    public String performPhotosynthesis() {
        return "The plant is happy and implementing photosynthesis";
    }
}
