package org.codeGym.javiModuleTwo.models.carnivore;

import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.services.Carnivore;

import java.util.List;

public class Eagle extends Animal implements  Carnivore{

    public Eagle(){
        this.setTypeOfAnimal("Carnivore");
        this.setAnimalMemory("Animal:", AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()));
        this.setAnimalMemory("AnimalType:", getTypeOfAnimal());
    }

    public String trill(){ //Silbar
        return "The Eagle trills because of the new baby.";
    }

    @Override
    public String sniff() {
        return "I'm sniffing looking for a possible prey.";
    }
}
