package org.codeGym.javiModuleTwo.models;

import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.Plant.Plant;
import org.codeGym.javiModuleTwo.models.carnivore.*;
import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;
import org.codeGym.javiModuleTwo.models.herbivore.*;
import org.codeGym.javiModuleTwo.services.Carnivore;

import java.security.Key;
import java.util.*;

public abstract class Animal {

    public boolean isHungry;
    public boolean isAlive;
    public char gender;
    public int possibilityOfBeingEaten;
    public Map<String, String> animalMemory = new HashMap<>();

    protected Animal() {
        this.setAlive(true);
        this.setHungry(true);
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

    public boolean isHungry() {
        return isHungry;
    }

    public void setHungry(boolean hungry) {
        isHungry = hungry;
    }

    public void setPossibilityOfBeingEaten(int possibility) {
        this.possibilityOfBeingEaten = possibility;
    }

    public int getPossibilityOfBeingEaten() {
        return this.possibilityOfBeingEaten;
    }

    public void setAnimalMemory(String key, String value) {
        animalMemory.put(key, value);
    }

    public String getAnimalMemory() {
        String memory = "";
        for (String memoryRecord : animalMemory.values()) {
            memory = memoryRecord;
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
            setAnimalMemory("Movement", recordOfMovement);
        } else if (radomMovement == 1 && enviroment.getAnimalContainer()[row][col].size() > 1) {
            enviroment.getAnimalContainer()[row - 1][col].add(this);
            enviroment.getAnimalContainer()[row][col].remove(this);
            recordOfMovement = String.format(message, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col, row - 1, col);
            setAnimalMemory("Movement", recordOfMovement);
        } else if (radomMovement == 2 && enviroment.getAnimalContainer()[row][col].size() > 1) {
            enviroment.getAnimalContainer()[row][col + 1].add(this);
            enviroment.getAnimalContainer()[row][col].remove(this);
            recordOfMovement = String.format(message, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col, row, col + 1);
            setAnimalMemory("Movement", recordOfMovement);
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

    public void eat(List<Animal> animalList, Enviroment enviromentInformation) {
        Random random = new Random();
        Set<Integer> indexOfAnimalsChoosenRandomlyOrEaten = new HashSet<>();
        Map<Animal, Integer> possibilityOfBeEaten = new HashMap<>();


        /*
         * REVISA EL CASO DONDE LA LISTA ES DE UN SOLO INTEGRANTE
         * REVISA QUE SI VIENEN POSIBILIDADDES DE CERO NO DE REGRESE UN MAX PUES NO HAY NADA QUE COMER
         * REVISA ELSE PARA EL CASO DE LAS PLANTAS
         * DEBUEALO
         * */

        while (indexOfAnimalsChoosenRandomlyOrEaten.size() < animalList.size()) {
            possibilityOfBeEaten.clear();
            int randomIndexAnimalSelect = random.nextInt(animalList.size());
            Animal hunter = animalList.get(randomIndexAnimalSelect);
            if (indexOfAnimalsChoosenRandomlyOrEaten.add(randomIndexAnimalSelect) && hunter.isHungry()) {
                System.out.printf("Animal selected randomly: %s %n", hunter.getClass().getSimpleName());

                //Verify possibility of be eaten for each animal
                for (Animal animal : animalList) {
                    if (!animal.equals(hunter)) {
                        verifyPossibilityOfBeEaten(hunter, animal);
                        System.out.println("Animal as prey: " + animal.getClass().getSimpleName() + " Possibility of be eaten: " + animal.getPossibilityOfBeingEaten());
                        possibilityOfBeEaten.put(animal, animal.getPossibilityOfBeingEaten());
                    }
                }

                //Determine animal to be eaten based on max possibility obtained, except for plants
                if (!(hunter instanceof Plant)) {
                    Map.Entry<Animal, Integer> mostLikekyAnimalOfBeEaten = possibilityOfBeEaten.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);
                    if (mostLikekyAnimalOfBeEaten != null && mostLikekyAnimalOfBeEaten.getValue() > 0) {
                        System.out.printf("The %s has identified a prey, %s with max probability of: %d %n",
                                animalList.get(randomIndexAnimalSelect).getClass().getSimpleName(),
                                mostLikekyAnimalOfBeEaten.getKey().getClass().getSimpleName(),
                                mostLikekyAnimalOfBeEaten.getValue());
                        animalList.get(randomIndexAnimalSelect).setHungry(false);
                        enviromentInformation.setDeadAnimals(mostLikekyAnimalOfBeEaten.getKey());
                        animalList.get(randomIndexAnimalSelect).setAnimalMemory("Eat", mostLikekyAnimalOfBeEaten.getKey().getClass().getSimpleName());
                        animalList.remove(mostLikekyAnimalOfBeEaten.getKey());
                        //possibilityOfBeEaten.remove(mostLikekyAnimalOfBeEaten.getKey(), mostLikekyAnimalOfBeEaten.getValue());
                    }
                } else if (animalList.get(randomIndexAnimalSelect) instanceof Plant) {
                    ((Plant) animalList.get(randomIndexAnimalSelect)).eat();
                } else {
                    System.out.println("There is not more possible preys to eat here!");
                }
            }
        }
    }


    public void breed() {
    }

    ;

    public void die() {
    }

    public void verifyPossibilityOfBeEaten(Animal animalAsHunter, Animal animalAsPrey) {
        // The matrix directly represents the provided table
        int[][] possibilityMatrix = {
                // Wolf Boa Fox Bear Eagle Horse Deer Rabbit Mouse Goat Sheep Boar Buffalo Duck Caterpillar Plant
                /*Wolf*/        {0, 0, 0, 0, 0, 10, 15, 60, 80, 60, 70, 15, 10, 40, 0, 0}, // Wolf
                /*Boa*/         {0, 0, 15, 0, 0, 0, 0, 20, 40, 0, 0, 0, 0, 10, 0, 0}, // Boa
                /*Fox*/         {0, 0, 0, 0, 0, 0, 0, 70, 90, 0, 0, 0, 0, 60, 40, 0}, // Fox
                /*Bear*/        {0, 80, 0, 0, 0, 40, 80, 80, 90, 70, 70, 50, 20, 10, 0, 0}, // Bear
                /*Eagle*/       {0, 0, 10, 0, 0, 0, 0, 90, 90, 0, 0, 0, 0, 80, 0, 0}, // Eagle
                /*Horse*/       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100}, // Horse
                /*Deer*/        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100}, // Deer
                /*Rabbit*/      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100}, // Rabbit
                /*Mouse*/       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100}, // Mouse
                /*Goat*/        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100}, // Goat
                /*Sheep*/       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100}, // Sheep
                /*Boar*/        {0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 90, 100}, // Boar
                /*Buffalo*/     {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100}, // Buffalo
                /*Duck*/        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100}, // Duck
                /*Caterpillar*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100}, // Caterpillar
                /*Plant */      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //Plant
        };

        int possibility = possibilityMatrix[AvailableAnimals.getAnimalCodeByName(animalAsHunter.getClass().getSimpleName().toUpperCase())][AvailableAnimals.getAnimalCodeByName(animalAsPrey.getClass().getSimpleName().toUpperCase())];
        animalAsPrey.setPossibilityOfBeingEaten(possibility);
    }

}
