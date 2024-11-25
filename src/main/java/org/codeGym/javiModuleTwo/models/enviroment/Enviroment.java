package org.codeGym.javiModuleTwo.models.enviroment;


import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.config.constants.AvailableMovements;
import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.Plant.Plant;
import org.codeGym.javiModuleTwo.models.carnivore.*;
import org.codeGym.javiModuleTwo.models.herbivore.*;
import org.codeGym.javiModuleTwo.services.Carnivore;
import org.codeGym.javiModuleTwo.services.Herbivore;
import org.codeGym.javiModuleTwo.services.Photosynthetic;

import java.sql.Array;
import java.util.*;
import java.util.concurrent.*;

public class Enviroment {
    private int enviromentRows = 5;
    private int enviromentColumns = 3;
    private final List<Animal>[][] animalContainer = new ArrayList[enviromentRows][enviromentColumns];
    private final List<Integer>[][] numberOfAnimalsByCell = new ArrayList[enviromentRows][enviromentColumns];
    private final List<Integer>[][] availableMovementsInEachCell = new ArrayList[enviromentRows][enviromentColumns];
    String[][] animalContainerRepresentation = new String[enviromentRows][enviromentColumns];
    private final Integer[][] possibilityOFBeEatenContaniner = new Integer[AvailableAnimals.values().length][AvailableAnimals.values().length];
    public Map<String, Integer> possibi = new HashMap<>();
    private List<Animal> livingCarnivores = new ArrayList<>();
    private List<Animal> livingHerbivores = new ArrayList<>();
    private List<Animal> livingAnimals = new ArrayList<>();
    private List<Animal> livingPlants = new ArrayList<>();
    private List<Animal> deadAnimals = new ArrayList<>();


    public Enviroment() {
        prepareEnviromentArrays();
        determineNumberOfAnimalsByCell();
        determineAnimalsByCode();
        identifyTypeOfAnimal();
        determinePossibleMovements();
        displayAnimalLocation();

    }

    public int getEnviromentRows() {
        return enviromentRows;
    }

    public void setEnviromentRows(int enviromentRows) {
        this.enviromentRows = enviromentRows;
    }

    public int getEnviromentColumns() {
        return enviromentColumns;
    }

    public void setEnviromentColumns(int enviromentColumns) {
        this.enviromentColumns = enviromentColumns;
    }

    public List<Animal> getDeadAnimals() {
        return deadAnimals;
    }

    public void setDeadAnimals(Animal animal) {
        this.deadAnimals.add(animal);
    }

    public List<Integer>[][] getAvailableMovementsInEachCell() {
        return this.availableMovementsInEachCell;
    }

    public List<Animal>[][] getAnimalContainer() {
        return animalContainer;
    }


    public void prepareEnviromentArrays() {
        System.out.println("1. Get environment ready.");
        for (int i = 0; i < enviromentRows; i++) {
            for (int j = 0; j < enviromentColumns; j++) {
                animalContainer[i][j] = new ArrayList<>();
                numberOfAnimalsByCell[i][j] = new ArrayList<>();
                availableMovementsInEachCell[i][j] = new ArrayList<>();
            }
        }
    }

    public void determineNumberOfAnimalsByCell() {
        System.out.println("2. Determine number of animals by cell.");
        int limit = 4;
        Random random = new Random();

        for (List<Integer>[] lists : numberOfAnimalsByCell) {
            for (int i = 0; i < lists.length; i++) {
                lists[i].add(random.nextInt(limit) + 1);
            }
        }
        for (int i = 0; i < enviromentRows; i++) {
            for (int j = 0; j < enviromentColumns; j++) {
                for (Integer numbers : numberOfAnimalsByCell[i][j]) {
                    System.out.printf("C-[%d][%d]: %d\t", i, j, numbers);
                }
            }
            System.out.println();
        }
    }

    public void determineAnimalsByCode() {
        System.out.println("3. Create and assign animals by cell.");
        Random random = new Random();
        int animalCode;

        for (int i = 0; i < enviromentRows; i++) {
            for (int j = 0; j < enviromentColumns; j++) {
                for (Integer number : numberOfAnimalsByCell[i][j]) {
                    for (int k = 0; k < number; k++) {
                        animalCode = random.nextInt(AvailableAnimals.values().length);
                        String animalName = AvailableAnimals.getAnimalNameByCode(animalCode);
                        switch (animalName) {
                            case "BEAR":
                                Animal bear = new Bear();
                                animalContainer[i][j].add(bear);
                                break;
                            case "BOA":
                                Animal boa = new Boa();
                                animalContainer[i][j].add(boa);
                                break;
                            case "EAGLE":
                                Animal eagle = new Eagle();
                                animalContainer[i][j].add(eagle);
                                break;
                            case "FOX":
                                Animal fox = new Fox();
                                animalContainer[i][j].add(fox);
                                break;
                            case "WOLF":
                                Animal wolf = new Wolf();
                                animalContainer[i][j].add(wolf);
                                break;
                            case "BOAR":
                                Animal boar = new Boar();
                                animalContainer[i][j].add(boar);
                                break;
                            case "BUFFALO":
                                Animal buffalo = new Buffalo();
                                animalContainer[i][j].add(buffalo);
                                break;
                            case "CATERPILLAR":
                                Animal caterpillar = new Caterpillar();
                                animalContainer[i][j].add(caterpillar);
                                break;
                            case "DEER":
                                Animal deer = new Deer();
                                animalContainer[i][j].add(deer);
                                break;
                            case "DUCK":
                                Animal duck = new Duck();
                                animalContainer[i][j].add(duck);
                                break;
                            case "GOAT":
                                Animal goat = new Goat();
                                animalContainer[i][j].add(goat);
                                break;
                            case "HORSE":
                                Animal horse = new Horse();
                                animalContainer[i][j].add(horse);
                                break;
                            case "MOUSE":
                                Animal mouse = new Mouse();
                                animalContainer[i][j].add(mouse);
                                break;
                            case "RABBIT":
                                Animal rabbit = new Rabbit();
                                animalContainer[i][j].add(rabbit);
                                break;
                            case "SHEEP":
                                Animal sheep = new Sheep();
                                animalContainer[i][j].add(sheep);
                                break;
                            case "PLANT":
                                Animal plant = new Plant();
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

    public void determinePossibleMovements() {
        System.out.println("4. Identify possible movements by cell.");

        for (int i = 0; i < enviromentRows; i++) {
            for (int j = 0; j < enviromentColumns; j++) {
                if (i != enviromentRows - 1) {
                    availableMovementsInEachCell[i][j].add(AvailableMovements.DOWN.getAnimalMoveCode());
                }
                if (i != 0) {
                    availableMovementsInEachCell[i][j].add(AvailableMovements.UP.getAnimalMoveCode());
                }
                if (j != enviromentColumns - 1) {
                    availableMovementsInEachCell[i][j].add(AvailableMovements.RIGHT.getAnimalMoveCode());
                }
                if (j != 0) {
                    availableMovementsInEachCell[i][j].add(AvailableMovements.LEFT.getAnimalMoveCode());
                }
            }
        }
    }

    public void displayAnimalLocation() {
        System.out.println("5. Display animals by cell.");
        String avatar = "";

        for (int i = 0; i < enviromentRows; i++) {
            for (int j = 0; j < enviromentColumns; j++) {
                StringBuffer avatars = new StringBuffer();
                for (Animal animal1 : animalContainer[i][j]) {
                    avatars.append(AvailableAnimals.getAvatarByAnimalName(animal1.getClass().getSimpleName())).append(" ");
                }
                animalContainerRepresentation[i][j] = avatars.toString().trim();
            }
        }
        for (int i = 0; i < enviromentRows; i++) {
            for (int j = 0; j < enviromentColumns; j++) {
                System.out.printf(" | C-[%d][%d]: %s ", i, j, animalContainerRepresentation[i][j]);
            }
            System.out.println();
        }
    }


    public void moveAnimal(Enviroment enviromentInformation) {
        System.out.println("Animals on movement");
        ExecutorService moveThreadPool = Executors.newFixedThreadPool(enviromentRows * enviromentColumns);
        Random random = new Random();

        for (int i = 0; i < enviromentRows; i++) {
            for (int j = 0; j < enviromentColumns; j++) {
                List<Animal> animalList = new ArrayList<>(animalContainer[i][j]);
                List<Integer> movementsList = getAvailableMovementsInEachCell()[i][j];

                for (Animal animal : animalList) {
                    final int row = i;
                    final int col = j;
                    final int radomMovement = movementsList.get(random.nextInt(movementsList.size()));

                    moveThreadPool.submit(() -> {
                        synchronized (enviromentInformation.getAnimalContainer()) {
                            animal.move(row, col, radomMovement, enviromentInformation);
                        }
                    });
                }
            }
        }
        moveThreadPool.shutdownNow();
        try {
            if (!moveThreadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                System.err.println("Some tasks did not complete in time.");
                moveThreadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println("Thread pool interrupted: " + e.getMessage());
        }
        System.out.println("Movement logic completed.");
    }

    public void eatAnotherAnimal(Enviroment enviromentInformation) {
        System.out.println("Let's eat something");
        ExecutorService eatThreadPool = Executors.newFixedThreadPool(enviromentRows * enviromentColumns);

        for (int i = 0; i < enviromentRows; i++) {
            System.out.println("Row: " + i);
            for (int j = 0; j < enviromentColumns; j++) {
                List<Animal> animalList = new ArrayList<>(animalContainer[i][j]);
                System.out.printf("Animals in cell [%d][%d] ready to eat: \n", i, j);
                for (Animal animal : animalList) {
                    System.out.printf("%s\t", AvailableAnimals.getAvatarByAnimalName(animal.getClass().getSimpleName()));
                }
                System.out.println();

                for (Animal animal : animalList) {
                    final int row = i;
                    final int col = j;

                    if (animalContainer[i][j].size() > 1) {
                        eatThreadPool.submit(() -> {
                            synchronized (enviromentInformation.getAnimalContainer()) {
                                animal.eat(animalList, enviromentInformation);
                            }
                        });
                    } else {
                        System.out.printf("The %s is alone, there is nothing to eat in cell [%d][%d]", AvailableAnimals.getAvatarByAnimalName(animalContainer[i][j].getClass().getSimpleName()), i, j);
                    }
                }
            }
        }
        eatThreadPool.shutdown();
        try {
            if (!eatThreadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                System.err.println("Eat tasks did not complete in time.");
                eatThreadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println("Thread pool interrupted: " + e.getMessage());
        }
        System.out.println("Eat logic completed.");
    }

    public void displayAnimalHistory() {
        System.out.println("Animal history");

        for (int i = 0; i < enviromentRows; i++) {
            System.out.println("Row: " + i);
            for (int j = 0; j < enviromentColumns; j++) {
                System.out.printf("Cell-[%d][%d]\n", i, j);
                for (Animal animal : animalContainer[i][j]) {
                    System.out.printf(animal.getAnimalMemory());

                }
            }
        }
    }


    public void identifyTypeOfAnimal() {
        System.out.println("Identify type of animals");

        for (int i = 0; i < enviromentRows; i++) {
            // System.out.println("Row: " + i);
            for (int j = 0; j < enviromentColumns; j++) {
                //System.out.printf("Cell-[%d][%d]", i, j);
                for (Animal animal : animalContainer[i][j]) {
                    if (animal.isAlive && animal instanceof Carnivore) {
                        livingCarnivores.add(animal);
                    } else if (animal.isAlive && animal instanceof Herbivore) {
                        livingHerbivores.add(animal);
                    } else if (animal.isAlive && animal instanceof Photosynthetic) {
                        livingPlants.add(animal);
                    } else {
                        deadAnimals.add(animal);
                    }
                }
            }
        }
    }


}


