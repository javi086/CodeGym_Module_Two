package org.codeGym.javiModuleTwo.models.enviroment;


import org.codeGym.javiModuleTwo.config.constants.AvailableLivingCreatureCodes;
import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.Plant.Plant;
import org.codeGym.javiModuleTwo.models.carnivore.*;
import org.codeGym.javiModuleTwo.models.herbivore.*;

import java.util.*;

public class Enviroment {
    private int enviromentWidth = 5;
    private int enviromentHeight = 5;
    private final List<Animal>[][] animalContainer = new ArrayList[enviromentWidth][enviromentHeight];
    private final List<Integer>[][] numberOfAnimalsByCell = new ArrayList[enviromentWidth][enviromentHeight];


    public Enviroment() {
    }

    ;

    public int getenviromentWidth() {
        return this.enviromentWidth;
    }

    public void setenviromentWidth(int width1) {
        this.enviromentWidth = width1;
    }

    ;

    public int getenviromentHeight() {
        return enviromentHeight;
    }

    public void setenviromentHeight(int enviromentHeight) {
        this.enviromentHeight = enviromentHeight;
    }

    public void setEnviroment() {
        System.out.println("1. Hola setEnviroment");
        System.out.println("Enviroment lenght: " + animalContainer.length);
        for (int i = 0; i < enviromentWidth; i++) {
            for (int j = 0; j < enviromentHeight; j++) {
                animalContainer[i][j] = new ArrayList<>();
                numberOfAnimalsByCell[i][j] = new ArrayList<>();
            }
        }
    }

    public void determineNumberOfAnimalsByCell() {
        System.out.println("2. Hola determineNumberOfCreaturesByCell");
        int limit = 4;
        Random random = new Random();

        for (List<Integer>[] lists : numberOfAnimalsByCell) {
            for (int i = 0; i < numberOfAnimalsByCell.length; i++) {
                lists[i].add(random.nextInt(limit) + 1);
            }
        }
        for (int i = 0; i < numberOfAnimalsByCell.length; i++) {
            for (int j = 0; j < numberOfAnimalsByCell.length; j++) {
                for (Integer numbers : numberOfAnimalsByCell[i][j]) {
                    System.out.printf("C-[%d][%d]: %d\t", i, j, numbers);
                }
            }
            System.out.println();
        }
    }

    public void determineAnimalsByCode() {
        System.out.println("3. Hola determineCreatureByCode");
        Random random = new Random();
        int animalCode;
        //String[] creatureNames = new String[numberOfCreatures];
        //String[][] creatureNames = new String[numberOfCreatures.length][numberOfCreatures.length];
        //List<String>[][] creatureNames = new ArrayList[enviromentWidth][enviromentHeight];

        for (int i = 0; i < numberOfAnimalsByCell.length; i++) {
            for (int j = 0; j < numberOfAnimalsByCell.length; j++) {
                for (Integer number : numberOfAnimalsByCell[i][j]) {
                    for (int k = 0; k < number; k++) {
                        animalCode = random.nextInt(AvailableLivingCreatureCodes.values().length);
                        //System.out.println(animalCode);
                        String animalType = AvailableLivingCreatureCodes.getCreatureByType(animalCode);
                        //System.out.println(animalType);
                        switch (animalType) {
                            case "BEAR":
                                Bear bear = new Bear();
                                bear.setAlive(true);
                                animalContainer[i][j].add(bear);
                                break;
                            case "BOA":
                                Boa boa = new Boa();
                                boa.setAlive(true);
                                animalContainer[i][j].add(boa);
                                break;
                            case "EAGLE":
                                Eagle eagle = new Eagle();
                                eagle.setAlive(true);
                                animalContainer[i][j].add(eagle);
                                break;
                            case "FOX":
                                Fox fox = new Fox();
                                fox.setAlive(true);
                                animalContainer[i][j].add(fox);
                                break;
                            case "WOLF":
                                Wolf wolf = new Wolf();
                                wolf.setAlive(true);
                                animalContainer[i][j].add(wolf);
                                break;
                            case "BOAR":
                                Boar boar = new Boar();
                                boar.setAlive(true);
                                animalContainer[i][j].add(boar);
                                break;
                            case "BUFFALO":
                                Buffalo buffalo = new Buffalo();
                                buffalo.setAlive(true);
                                animalContainer[i][j].add(buffalo);
                                break;
                            case "CATERPILLAR":
                                Caterpillar caterpillar = new Caterpillar();
                                caterpillar.setAlive(true);
                                animalContainer[i][j].add(caterpillar);
                                break;
                            case "DEER":
                                Deer deer = new Deer();
                                deer.setAlive(true);
                                animalContainer[i][j].add(deer);
                                break;
                            case "DUCK":
                                Duck duck = new Duck();
                                duck.setAlive(true);
                                animalContainer[i][j].add(duck);
                                break;
                            case "GOAT":
                                Goat goat = new Goat();
                                goat.setAlive(true);
                                animalContainer[i][j].add(goat);
                                break;
                            case "HORSE":
                                Horse horse = new Horse();
                                horse.setAlive(true);
                                animalContainer[i][j].add(horse);
                                break;
                            case "MOUSE":
                                Mouse mouse = new Mouse();
                                mouse.setAlive(true);
                                animalContainer[i][j].add(mouse);
                                break;
                            case "RABBIT":
                                Rabbit rabbit = new Rabbit();
                                rabbit.setAlive(true);
                                animalContainer[i][j].add(rabbit);
                                break;
                            case "SHEEP":
                                Sheep sheep = new Sheep();
                                sheep.setAlive(true);
                                animalContainer[i][j].add(sheep);
                                break;
                            case "PLANT":
                                Plant plant = new Plant();
                                plant.setAlive(true);
                                animalContainer[i][j].add(plant);
                                break;
                            default:
                                System.out.println("Sorry! Creature code not recognized");
                        }
                    }
                }
            }
        }
    }


    public void displayEnviroment() {
        for (int i = 0; i < animalContainer.length; i++) {
            System.out.println("Row: "+i );
            for (int j = 0; j < animalContainer.length; j++) {
                System.out.printf("Cell-[%d][%d]: ", i, j);
                for (Animal animal : animalContainer[i][j]) {
                    Class<?>[] interfaces = animal.getClass().getInterfaces();
                    for (Class<?> interf : interfaces){
                        System.out.print(animal.getClass().getSimpleName() + "-(Alive: " + animal.isAlive() + ", type: "+ interf.getSimpleName() + ") | ");
                    }


                }

                System.out.println();
                //System.out.print("|\t");
            }
            System.out.println();
        }
/*
        for (int i = 0; i < creatureContainer.length; i++) {
            // First print the creature count for each cell in the row
            for (int j = 0; j < creatureContainer[i].length; j++) {
                System.out.printf("C-[%d][%d] - # Creatures: %d\t", i, j, creatureContainer[i][j].size());
            }
            System.out.println();

            // Now print each creature's information in each cell in the same row
            boolean moreCreatures = true; // Flag to check if any cell has more creatures left to display
            int creatureIndex = 0;

            while (moreCreatures) {
                moreCreatures = false; // Assume there are no more creatures left initially

                for (int j = 0; j < creatureContainer[i].length; j++) {
                    if (creatureIndex < creatureContainer[i][j].size()) {
                        // Get the creature from the list at the current index if it exists
                        Object animal = creatureContainer[i][j].get(creatureIndex);
                        String creatureName = animal.getClass().getSimpleName();
                        System.out.printf("C-[%d][%d]%s\t", i, j, creatureName);
                        moreCreatures = true; // Set flag to true as there are more creatures to display
                    } else {
                        // Print empty space if no more creatures in this cell
                        System.out.print("\t\t\t");
                    }
                }
                System.out.println();
                creatureIndex++; // Move to the next creature in each cell’s list
            }
        }

*/

    }




/*
            for (int i = 0; i < numberOfCreatures.length; i++) {
                creatureCode = random.nextInt(limit);
                for (AvailableLivingCreatureCodes creature : AvailableLivingCreatureCodes.values()){
                    if(creature.getLivingCreatureCode() == creatureCode){
                       creatureNames[i] = creature.name();
                        System.out.println(creature.name()+" code: " +creature.getLivingCreatureCode());
                    }
                }
            }
        //return creatureNames;
    }

    public void setCreaturesInCell(List<Animal> animalList) {
        System.out.println("Hola setCreaturesInCell");
        for (int i = 0; i < creatureContainer.length; i++) {
            for (int j = 0; j < creatureContainer.length; j++) {
                for (Animal animalObject : animalList) {
                    creatureContainer[i][j].add(animalObject);
                }
            }
        }
        System.out.println("Animals created:");
        for (int i = 0; i < creatureContainer.length; i++) {
            System.out.println("Row-" + i);
            for (Animal animal : animalList) {
                for (int j = 0; j < creatureContainer.length; j++) {
                    System.out.print("Col-" + j + " " + animal.getClass().getSimpleName() + "\t");
                }
                System.out.println();
            }
            System.out.println();
        }

    }


    public List<Animal> provideCreature(List<String>[][] creatureNames) {
        System.out.println("4. Hola provideCreature");
        List<Animal> animalList = new ArrayList<>();

        System.out.println("Creaturas recibidas");
        for (int i = 0; i <creatureNames.length ; i++) {
            for (int j = 0; j <creatureNames.length ; j++) {
                System.out.println(creatureNames[i][j]);
            }
        }

        return animalList;
    }

 */
}


