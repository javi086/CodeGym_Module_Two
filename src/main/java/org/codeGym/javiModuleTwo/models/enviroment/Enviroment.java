package org.codeGym.javiModuleTwo.models.enviroment;


import org.codeGym.javiModuleTwo.config.constants.AvailableLivingCreatureCodes;
import org.codeGym.javiModuleTwo.config.constants.AvailableMovements;
import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.Plant.Plant;
import org.codeGym.javiModuleTwo.models.carnivore.*;
import org.codeGym.javiModuleTwo.models.herbivore.*;

import java.util.*;

public class Enviroment {
    private int rows = 5;
    private int columns = 3;
    private final List<Animal>[][] animalContainer = new ArrayList[rows][columns];
    private final List<Integer>[][] numberOfAnimalsByCell = new ArrayList[rows][columns];
    private final List<Integer>[][] availableMovementsInEachCell = new ArrayList[rows][columns];


    public void setEnviromentConditions() {
        System.out.println("1. Hola setEnviroment");
        System.out.println("Enviroment lenght: " + animalContainer.length);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                animalContainer[i][j] = new ArrayList<>();
                numberOfAnimalsByCell[i][j] = new ArrayList<>();
                availableMovementsInEachCell[i][j] = new ArrayList<>();
            }
        }
    }

    public void determineNumberOfAnimalsByCell() {
        System.out.println("2. Hola determineNumberOfCreaturesByCell");
        int limit = 4;
        Random random = new Random();

        for (List<Integer>[] lists : numberOfAnimalsByCell) {
            for (int i = 0; i < lists.length; i++) {
                lists[i].add(random.nextInt(limit) + 1);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
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

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
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


    public void displayInitialEnvironment() {
        System.out.println("4.Hola displayInitialEnvironment");
        String tempoPicture="*";
        boolean checker = true;
        for (int i = 0; i < rows; i++) {
            System.out.println("Row: " + i);
            for (int j = 0; j < columns; j++) {
                System.out.printf("Cell-[%d][%d]: ", i, j);
                for (Animal animal : animalContainer[i][j]) {
                        if (checker && animal instanceof Boa) {
                            tempoPicture = AvailableLivingCreatureCodes.BOA.getAvatar();
                            checker = false;
                        }


                    Class<?>[] interfaces = animal.getClass().getInterfaces();
                    for (Class<?> interf : interfaces) {
                        System.out.print(tempoPicture + animal.getClass().getSimpleName() + " (Alive:" + animal.isAlive() + ", type:" + interf.getSimpleName() + ") | ");
                    }
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    public void determinePossibleMovements() {
        System.out.println("5. Hello determinePossibleMovements");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i != rows - 1) {
                    availableMovementsInEachCell[i][j].add(AvailableMovements.DOWN.getAnimalMoveCode());
                }
                if (i != 0) {
                    availableMovementsInEachCell[i][j].add(AvailableMovements.UP.getAnimalMoveCode());
                }
                if (j != columns - 1) {
                    availableMovementsInEachCell[i][j].add(AvailableMovements.RIGHT.getAnimalMoveCode());
                }
                if (j != 0) {
                    availableMovementsInEachCell[i][j].add(AvailableMovements.LEFT.getAnimalMoveCode());
                }
            }
        }
    }


    public void moveAnimal() {
        System.out.println("6. Hello moveAnimal");

        int radomMovement;
        int indexOfAnimalToMove;
        Random random = new Random();


        for (int i = 0; i < rows; i++) {
            System.out.println("Row: " + i);
            for (int j = 0; j < columns; j++) {
                //System.out.printf("Cell-[%d][%d]:\n ", i, j);
                List<Integer> movementsList = availableMovementsInEachCell[i][j];
                radomMovement = movementsList.get(random.nextInt(movementsList.size()));
                indexOfAnimalToMove = random.nextInt(animalContainer[i][j].size());
                System.out.println("Movimiento determinado: " + radomMovement);
                System.out.printf("Animal a mover %s con el indice %d: ", animalContainer[i][j].get(indexOfAnimalToMove).getClass().getSimpleName(), indexOfAnimalToMove);

                if (radomMovement == 0 && animalContainer[i][j].size() > 1) {
                    System.out.printf("[%d][%d]-Inicial\n", i, j);
                    animalContainer[i][j - 1].add(animalContainer[i][j].get(indexOfAnimalToMove));
                    animalContainer[i][j].remove(indexOfAnimalToMove);
                    System.out.printf("[%d][%d]-Final", i, j - 1);
                }

                if (radomMovement == 1 && animalContainer[i][j].size() > 1) {
                    System.out.printf("[%d][%d]-Inicial\n", i, j);
                    animalContainer[i - 1][j].add(animalContainer[i][j].get(indexOfAnimalToMove));
                    animalContainer[i][j].remove(indexOfAnimalToMove);
                    System.out.printf("[%d][%d]-Final", i - 1, j);
                }
                if (radomMovement == 2 && animalContainer[i][j].size() > 1) {
                    System.out.printf("[%d][%d]-Inicial\n", i, j);
                    animalContainer[i][j + 1].add(animalContainer[i][j].get(indexOfAnimalToMove));
                    animalContainer[i][j].remove(indexOfAnimalToMove);
                    System.out.printf("[%d][%d]-Final", i, j + 1);
                }
                if (radomMovement == 3 && animalContainer[i][j].size() > 1) {
                    System.out.printf("[%d][%d]-Inicial\n", i, j);
                    animalContainer[i + 1][j].add(animalContainer[i][j].get(indexOfAnimalToMove));
                    animalContainer[i][j].remove(indexOfAnimalToMove);
                    System.out.printf("[%d][%d]-Final", i + 1, j);
                }

                System.out.println();
            }
            System.out.println();
        }
    }


    public void displayNewPosition() {
        System.out.println("7. Hello displayNewPositions");
        for (int i = 0; i < rows; i++) {
            System.out.println("Row: " + i);
            for (int j = 0; j < columns; j++) {
                System.out.printf("Cell-[%d][%d]: ", i, j);
                for (Animal animal : animalContainer[i][j]) {
                    Class<?>[] interfaces = animal.getClass().getInterfaces();
                    for (Class<?> interf : interfaces) {
                        System.out.print(animal.getClass().getSimpleName() + "-(Alive:" + animal.isAlive() + ", type:" + interf.getSimpleName() + ") | ");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }


}


