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
import java.util.Map;
/*
* Revisar el orden en el que se presenta la información
* Parece ser que si un animakl como no se esta guardando el movimiento
* */
public class Main {
    public static void main(String[] args) {
        Enviroment enviroment = new Enviroment();
       //enviroment.moveAnimal(enviroment);
       enviroment.eatAnotherAnimal(enviroment);
      // enviroment.displayAnimalLocation();
        //System.out.printf("%n Execution completed successfully!!! %n%n");
       //enviroment.displayAnimalHistory();


        //Temporal information just for testing the eat method.
        List<Animal> pruebaList = new ArrayList<>(3);
        Wolf wolf = new Wolf();
        //wolf.setAlive(true);
        //wolf.setGender("M");
        Wolf wolf2 = new Wolf();
       // wolf2.setAlive(true);
        //wolf2.setGender("F");
        pruebaList.add(wolf);
        pruebaList.add(wolf2);
        pruebaList.add(new Mouse());
        //enviroment.eatAnotherAnimal(enviroment);
        enviroment.displayAnimalLocation();
        enviroment.displayAnimalHistory();

       /*List<Animal> myAnimalTest = enviroment.getAnimalContainer()[0][0];
       for (Animal animal1 : myAnimalTest){
           for (Map.Entry<String,String> entry : animal1.getAnimalMemory().entrySet()){
               System.out.printf("%s %s | ", entry.getKey(), entry.getValue());

           }
           System.out.println();
       }
        System.out.printf("============");
*/

    }

}