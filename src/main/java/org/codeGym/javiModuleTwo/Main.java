package org.codeGym.javiModuleTwo;

import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.carnivore.Wolf;
import org.codeGym.javiModuleTwo.models.enviroment.Environment;
import org.codeGym.javiModuleTwo.models.herbivore.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
* Revisar el orden en el que se presenta la información
* Parece ser que si un animakl como no se esta guardando el movimiento
* */
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Java Module Two!");
        System.out.println("Let's define the environment's dimensions");
        boolean isValidDimensions = false;
        int rows=0;
        int columns=0;
        try( Scanner scanner = new Scanner(System.in)){
            while(!isValidDimensions){
            System.out.print("Please enter number of rows from 1-9: ");
            rows = scanner.nextInt();
            System.out.print("Please enter number of columns from 1-9: ");
            columns = scanner.nextInt();
             isValidDimensions = rows > 0 && columns > 0 && rows <= 9 && columns <= 9;
             if(isValidDimensions){
                 Environment environment = new Environment(rows, columns);

                 environment.moveAnimal(environment);
                 environment.eatAnotherAnimal(environment);
                 environment.breedAnimal(environment);
                 environment.displayAnimalLocation();
                 //System.out.printf("%n Execution completed successfully!!! %n%n");
                 environment.displayAnimalHistory();
             }
             else{
                 System.out.println("Invalid dimensions!");
             }
            }




        } catch (Exception e) {
            System.err.println("Invalid input. Please enter valid integers for rows and columns.");
        }





        //Temporal information just for testing the eat method.
        List<Animal> pruebaList = new ArrayList<>(3);
        Wolf wolf = new Wolf();
        Buffalo buffalo = new Buffalo();
        Wolf wolf2 = new Wolf();
        Mouse mouse = new Mouse();
        pruebaList.add(wolf);
        pruebaList.add(buffalo);
        pruebaList.add(wolf2);
        pruebaList.add(mouse);
        //enviroment.breedAnimal(enviroment);
        //enviroment.displayAnimalHistory();

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