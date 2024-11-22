package org.codeGym.javiModuleTwo;

import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.Plant.Plant;
import org.codeGym.javiModuleTwo.models.carnivore.Wolf;
import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;
import org.codeGym.javiModuleTwo.models.herbivore.Rabbit;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Enviroment enviroment = new Enviroment();
        //enviroment.moveAnimal(enviroment);
        //enviroment.eatAnotherAnimal(enviroment);
        //enviroment.displayAnimalHistory();
        //enviroment.displayAnimalLocation();
        List<Animal> prueba = new ArrayList<>(3);
        prueba.add(new Wolf());
        prueba.add(new Rabbit());
        prueba.add(new Plant());
        prueba.get(0).setAlive(true);
        prueba.get(1).setAlive(true);
        prueba.get(2).setAlive(true);
        Animal animal = new Animal() {
            @Override
            public String getAnimalMemory() {
                return super.getAnimalMemory();
            }
        };
        animal.eat(prueba);


    }

}