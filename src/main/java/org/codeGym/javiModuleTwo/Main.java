package org.codeGym.javiModuleTwo;

import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.Plant.Plant;
import org.codeGym.javiModuleTwo.models.carnivore.Bear;
import org.codeGym.javiModuleTwo.models.carnivore.Eagle;
import org.codeGym.javiModuleTwo.models.carnivore.Fox;
import org.codeGym.javiModuleTwo.models.carnivore.Wolf;
import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;
import org.codeGym.javiModuleTwo.models.herbivore.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Enviroment enviroment = new Enviroment();
        enviroment.moveAnimal(enviroment);
        enviroment.eatAnotherAnimal(enviroment);
        enviroment.displayAnimalLocation();
        enviroment.displayAnimalHistory();
/*
        List<Animal> prueba = new ArrayList<>(3);
        prueba.add(new Fox());
        prueba.add(new Plant());
        prueba.add(new Mouse());
        Animal animal = new Animal() {
            @Override
            public String getAnimalMemory() {
                return super.getAnimalMemory();
            }
        };
        animal.eat(prueba, enviroment);
*/
    }

}