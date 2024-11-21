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

import java.util.*;
import java.util.concurrent.*;

public class Enviroment {
    private int enviromentRows = 5;
    private int enviromentColumns = 3;
    private final List<Animal>[][] animalContainer = new ArrayList[enviromentRows][enviromentColumns];
    private final List<Integer>[][] numberOfAnimalsByCell = new ArrayList[enviromentRows][enviromentColumns];
    private final List<Integer>[][] availableMovementsInEachCell = new ArrayList[enviromentRows][enviromentColumns];
    private List<Animal> livingCarnivores = new ArrayList<>();
    private List<Animal> livingHerbivores = new ArrayList<>();
    private List<Animal> livingAnimals = new ArrayList<>();
    private List<Animal> livingPlants = new ArrayList<>();
    private List<Animal> deadAnimals = new ArrayList<>();


    public Enviroment() {
        prepareEnviromentArrays();
        determineNumberOfAnimalsByCell();
        determineAnimalsByCode();
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
                        //System.out.println(animalCode);
                        String animalName = AvailableAnimals.getAnimalNameByCode(animalCode);
                        //System.out.println(animalType);
                        switch (animalName) {
                            case "BEAR":
                                Animal bear = new Bear();
                                bear.setAlive(true);
                                animalContainer[i][j].add(bear);
                                break;
                            case "BOA":
                                Animal boa = new Boa();
                                boa.setAlive(true);
                                animalContainer[i][j].add(boa);
                                break;
                            case "EAGLE":
                                Animal eagle = new Eagle();
                                eagle.setAlive(true);
                                animalContainer[i][j].add(eagle);
                                break;
                            case "FOX":
                                Animal fox = new Fox();
                                fox.setAlive(true);
                                animalContainer[i][j].add(fox);
                                break;
                            case "WOLF":
                                Animal wolf = new Wolf();
                                wolf.setAlive(true);
                                animalContainer[i][j].add(wolf);
                                break;
                            case "BOAR":
                                Animal boar = new Boar();
                                boar.setAlive(true);
                                animalContainer[i][j].add(boar);
                                break;
                            case "BUFFALO":
                                Animal buffalo = new Buffalo();
                                buffalo.setAlive(true);
                                animalContainer[i][j].add(buffalo);
                                break;
                            case "CATERPILLAR":
                                Animal caterpillar = new Caterpillar();
                                caterpillar.setAlive(true);
                                animalContainer[i][j].add(caterpillar);
                                break;
                            case "DEER":
                                Animal deer = new Deer();
                                deer.setAlive(true);
                                animalContainer[i][j].add(deer);
                                break;
                            case "DUCK":
                                Animal duck = new Duck();
                                duck.setAlive(true);
                                animalContainer[i][j].add(duck);
                                break;
                            case "GOAT":
                                Animal goat = new Goat();
                                goat.setAlive(true);
                                animalContainer[i][j].add(goat);
                                break;
                            case "HORSE":
                                Animal horse = new Horse();
                                horse.setAlive(true);
                                animalContainer[i][j].add(horse);
                                break;
                            case "MOUSE":
                                Animal mouse = new Mouse();
                                mouse.setAlive(true);
                                animalContainer[i][j].add(mouse);
                                break;
                            case "RABBIT":
                                Animal rabbit = new Rabbit();
                                rabbit.setAlive(true);
                                animalContainer[i][j].add(rabbit);
                                break;
                            case "SHEEP":
                                Animal sheep = new Sheep();
                                sheep.setAlive(true);
                                animalContainer[i][j].add(sheep);
                                break;
                            case "PLANT":
                                Animal plant = new Plant();
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
        for (int i = 0; i < enviromentRows; i++) {
            System.out.println("Row: " + i);
            for (int j = 0; j < enviromentColumns; j++) {
                System.out.printf("Cell-[%d][%d]", i, j);
                for (Animal animal : animalContainer[i][j]) {
                    Class<?>[] interfaces = animal.getClass().getInterfaces();
                    for (Class<?> interf : interfaces) {
                        System.out.printf(" | %s-%s (Alive: %s type: %s) ", AvailableAnimals.getAvatarByAnimalName(animal.getClass().getSimpleName()), animal.getClass().getSimpleName(), animal.isAlive(), interf.getSimpleName());
                    }
                }
               for ( Animal animal : animalContainer[i][j]){
                   if (!animal.animalMemory.isEmpty()){
                       displayAnimalHistory();
                   }
               }
            }
            System.out.println();
        }
    }

    public void startAnimalTasks(Enviroment enviromentInformation) {
        System.out.println("Start animal tasks");
        ExecutorService enviromentThreadPool =  Executors.newFixedThreadPool(enviromentRows * enviromentColumns);
        Random random = new Random();

        for (int i = 0; i < enviromentRows; i++) {
            //System.out.println("Row: " + i);
            for (int j = 0; j < enviromentColumns; j++) {
                //System.out.printf("[%d][%d] \n", i, j);
                List<Animal> animalList = new ArrayList<>(animalContainer[i][j]);
                List<Integer> movementsList = getAvailableMovementsInEachCell()[i][j];


                for (Animal animal : animalList) {
                    final int row = i;
                    final int col = j;
                    final int radomMovement = movementsList.get(random.nextInt(movementsList.size()));

                    enviromentThreadPool.submit(() -> {
                        synchronized (enviromentInformation.getAnimalContainer()) {
                            animal.move(row, col, radomMovement, enviromentInformation);
                        }
                        //animal.move(enviromentInformation);
                    });
                }
            }
        }
        enviromentThreadPool.shutdownNow();
        try {
            if (!enviromentThreadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                System.err.println("Some tasks did not complete within the timeout.");
            }
        } catch (InterruptedException e) {
            System.err.println("Thread pool interrupted: " + e.getMessage());
        }

        displayAnimalLocation();
        //displayAnimalHistory();
        /*try {
            if (!enviromentThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS)) {
                System.out.println("Some tasks did not complete within the timeout.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public void displayAnimalHistory() {
        System.out.println("Animal history");

        for (int i = 0; i < enviromentRows; i++) {
            System.out.println("Row: " + i);
            for (int j = 0; j < enviromentColumns; j++) {
                System.out.printf("[%d][%d] \n", i, j);
                for (Animal animal : animalContainer[i][j]) {
                        System.out.printf("%s %s Alive: %s", AvailableAnimals.getAvatarByAnimalName(animal.getClass().getSimpleName()), animal.getAnimalMemory(), animal.isAlive());
                }
            }
        }

       /* for (int i = 0; i < enviromentRows; i++) {
            System.out.println("Row: " + i);
            for (int j = 0; j < enviromentColumns; j++) {
                System.out.printf("Cell-[%d][%d]", i, j);
                for (Animal animal : animalContainer[i][j]) {
                    Class<?>[] interfaces = animal.getClass().getInterfaces();
                    for (Class<?> interf : interfaces) {
                        //System.out.printf("%s %s %s %s", tempoPicture=!tempoPicture.equals("N") ? tempoPicture : "*", animal.getClass().getSimpleName(),animal.isAlive() , interf.getSimpleName() );
                        System.out.printf(" | %s-%s (Alive: %s type: %s)", AvailableAnimals.getAvatarByAnimalName(animal.getClass().getSimpleName()), animal.getClass().getSimpleName(), animal.isAlive(), interf.getSimpleName());
                        System.out.printf("\n%s", animal.getAnimalMemory());
                    }
                }
                System.out.println();
            }
            System.out.println();
        }*/
    }

    public void moveAnimal() {
        System.out.println("6. Moviendo animal");

        int radomMovement;
        int indexOfAnimalToMove;
        Random random = new Random();


        for (int i = 0; i < enviromentRows; i++) {
            System.out.println("Row: " + i);
            for (int j = 0; j < enviromentColumns; j++) {
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


    public void identifyCarnivoreHerbivore() {
        System.out.println("8. Hello identifyCarnivoreHerbivore");

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

    public void eatTime() {
        System.out.println("9. Hello eatTime");
        for (Animal animal : livingCarnivores) {
            if (animal instanceof Carnivore) {
                animal.eat(livingHerbivores);
            } else if (animal instanceof Herbivore) {
                animal.eat(livingPlants);
            }
        }
    }

}


