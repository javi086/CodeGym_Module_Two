package org.codeGym.javiModuleTwo.models;

import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.carnivore.Wolf;
import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;
import org.codeGym.javiModuleTwo.services.Carnivore;

import java.util.*;

public abstract class Animal {

    public float weight;
    public boolean isAlive;
    public char gender;
    public int possibilityOfBeingEaten;
    public Map<String, String> animalMemory = new HashMap<>();
    //public Enviroment enviroment = new Enviroment();


    public void setPossibilityOfBeingEaten(int possibility){
        this.possibilityOfBeingEaten = possibility;
    }

    public int getPossibilityOfBeingEaten(){
        return this.possibilityOfBeingEaten;
    }
    public void setAnimalMemory(String key, String value) {
        animalMemory.put(key, value);
    }

    public String getAnimalMemory() {
        String memory = "";
        for (String memoryRecord : animalMemory.values()){
           memory=memoryRecord;
        }
   return memory;
    }


    public void move(int row, int col, int radomMovement, Enviroment enviroment) {
        String message = "The %s %s moved from the initial position [%d][%d] to the final position [%d][%d]\n";
        String recordOfMovement;

        if (radomMovement == 0 && enviroment.getAnimalContainer()[row][col].size() > 1) {
            //System.out.printf("[%d][%d]-Inicial\n", i, j);
            enviroment.getAnimalContainer()[row][col - 1].add(this);
            enviroment.getAnimalContainer()[row][col].remove(this);
            //System.out.printf("%s: Initial-[%d][%d]-Final[%d][%d]\n", this.getClass().getSimpleName(), row, col, row, col - 1);
            recordOfMovement = String.format(message, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col, row, col - 1);
            setAnimalMemory("Movement",recordOfMovement);
        } else if (radomMovement == 1 && enviroment.getAnimalContainer()[row][col].size() > 1) {
            enviroment.getAnimalContainer()[row - 1][col].add(this);
            enviroment.getAnimalContainer()[row][col].remove(this);
            recordOfMovement = String.format(message, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col, row - 1, col);
            setAnimalMemory("Movement",recordOfMovement);
        } else if (radomMovement == 2 && enviroment.getAnimalContainer()[row][col].size() > 1) {
            enviroment.getAnimalContainer()[row][col + 1].add(this);
            enviroment.getAnimalContainer()[row][col].remove(this);
            recordOfMovement = String.format(message, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col, row, col + 1);
            setAnimalMemory("Movement",recordOfMovement);
        } else if (radomMovement == 3 && enviroment.getAnimalContainer()[row][col].size() > 1) {
            enviroment.getAnimalContainer()[row + 1][col].add(this);
            enviroment.getAnimalContainer()[row][col].remove(this);
            recordOfMovement = String.format(message, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col, row + 1, col);
            setAnimalMemory("Movement", recordOfMovement);
        } else {
             message = "The %s %s is sleepy and decided to take a nap. it didn't move at all,  original position [%d][%d]\n";
             recordOfMovement = String.format(message, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col);
             setAnimalMemory("Movement", recordOfMovement);
        }

    }
    public  void eat( List<Animal> animalList){
        Random random = new Random();
        Set<Integer> indexOfAnimalsAlreadySelected = new HashSet<>();
        //List<Animal> animalList = enviromentInformation.getAnimalContainer()[row][col];

                while (indexOfAnimalsAlreadySelected.size() < animalList.size()){
                    int randomIndexAnimalSelect = random.nextInt(animalList.size());
                    System.out.println("Indice random:"+randomIndexAnimalSelect);
                    if (indexOfAnimalsAlreadySelected.add(randomIndexAnimalSelect)) { // add() returns false if the index already exists

                        System.out.println(animalList.get(randomIndexAnimalSelect));

                        if(animalList.get(randomIndexAnimalSelect) instanceof Wolf && animalList.get(randomIndexAnimalSelect).isAlive){
                            //Wolf wolf = (Wolf) animalList.get(randomIndexAnimalSelect);
                           // wolf.hunt(animalList);
                            System.out.println("Hola lobo");
                            animalList.remove(1);
                        }else{
                            System.out.println("no es");
                        }

                    }
                }




        System.out.println("Hora de comer");
        //Determining who is going to eat first carnivore or herbivore

/*
        for (Animal animal : animalListInCell){
            if(animal instanceof Wolf){

                Wolf wolf = (Wolf) animal;

            }
        }
*/
    }


    public void breed() {
    }

    ;

    public void die() {
    }

    ;


    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
