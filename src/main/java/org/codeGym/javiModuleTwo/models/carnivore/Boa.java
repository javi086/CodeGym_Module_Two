package org.codeGym.javiModuleTwo.models.carnivore;


import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.services.Carnivore;

public class Boa extends Animal implements Carnivore {

    public Boa(){
        this.setTypeOfAnimal("Carnivore");
        this.setAnimalMemory("Animal:", AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()));
        this.setAnimalMemory("Type:", getTypeOfAnimal());
    }

    public void breed() {

    }

    public String hideAndLeave(){
        return "The Boa, hide the new baby and leave.";
    }

}
