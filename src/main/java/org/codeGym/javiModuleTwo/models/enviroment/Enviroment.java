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
import java.util.stream.Collectors;

public class Enviroment {
    private int enviromentRows = 3;
    private int enviromentColumns = 4;
    private final List<Animal>[][] animalContainer = new ArrayList[enviromentRows][enviromentColumns];
    private final List<Integer>[][] numberOfAnimalsByCell = new ArrayList[enviromentRows][enviromentColumns];
    private final List<Integer>[][] availableMovementsInEachCell = new ArrayList[enviromentRows][enviromentColumns];
    String[][] animalContainerRepresentation = new String[enviromentRows][enviromentColumns];
    private List<Animal> livingCarnivores = new ArrayList<>();
    private List<Animal> livingHerbivores = new ArrayList<>();
    private List<Animal> livingAnimals = new ArrayList<>();
    private List<Animal> livingPlants = new ArrayList<>();
    private List<Animal> deadAnimals = new ArrayList<>();

    //Essential methods to set before starting the move, eat, reproduce actions.
    public Enviroment() {
        System.out.println("List of tasks required for the environment preparation.");

        System.out.println("1. Set environment dimensions");
        prepareEnviromentArrays();

        System.out.println("2. Determine randomly the number of animals by cell.");
        determineNumberOfAnimalsByCell();

        System.out.println("3. Create and assign animals into each cell.");
        createAnimalsByCode();

        System.out.println("4. Determine randomly the possible movements of each animal based on the cell location.");
        determinePossibleMovements();

        System.out.println("5. Display initial positions.");
        displayAnimalLocation();

        //System.out.println("Initial environment information");
        //displayEnvironmentInformation();

    }

    //EnviromentsRows get and set pending of being used until the Scanner is implemented
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
        for (int i = 0; i < enviromentRows; i++) {
            for (int j = 0; j < enviromentColumns; j++) {
                animalContainer[i][j] = new ArrayList<>();
                numberOfAnimalsByCell[i][j] = new ArrayList<>();
                availableMovementsInEachCell[i][j] = new ArrayList<>();
            }
        }
    }

    public void determineNumberOfAnimalsByCell() {
        int limit = 3;
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

    public void createAnimalsByCode() {
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

    public void displayEnvironmentInformation() {
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
                        synchronized (animalContainer[row][col]) {
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
            //System.out.println("Row: " + i);
            for (int j = 0; j < enviromentColumns; j++) {
                List<Animal> animalList = new ArrayList<>(animalContainer[i][j]);

                if (animalList.size() > 1) {
                    int row = i;
                    int col = j;
                    for (Animal animal : animalList) {
                        eatThreadPool.submit(() -> {
                            /** System.out.printf("Thread ID %d (%s) is executing eat logic for animal %s in cell [%d][%d]%n",
                             Thread.currentThread().getId(),
                             Thread.currentThread().getName(),
                             animal.getClass().getSimpleName(),
                             row, col);*/
                            synchronized (animalContainer[row][col]) {
                                animal.eat(animalList, enviromentInformation);
                            }
                        });
                    }
                } else {
                    for (Animal animal : animalList) {
                        animal.setAnimalMemory("Eat:", "The animal is alone there is nothing to eat");
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



            public void breedAnimal (Enviroment enviromentInformation){
                System.out.println("Ready to breed");
                ExecutorService breedThreadPool = Executors.newFixedThreadPool(enviromentRows * enviromentColumns);

                for (int i = 0; i < enviromentRows; i++) {
                    System.out.println("Row: " + i);
                    for (int j = 0; j < enviromentColumns; j++) {
                        List<Animal> animalList = new ArrayList<>(getAnimalContainer()[i][j]);
                        System.out.printf("Animals in cell [%d][%d] are looking for a partner to breed: \n", i, j);
                        for (Animal animal : animalList) {
                            System.out.printf("%s\t", AvailableAnimals.getAvatarByAnimalName(animal.getClass().getSimpleName()));
                        }
                        System.out.println();

                        for (Animal animal : animalList) {
                            final int row = i;
                            final int col = j;

                            if (animalContainer[i][j].size() > 1) {
                                breedThreadPool.submit(() -> {
                                    synchronized (getAnimalContainer()) {
                                        animal.breed(row, col, animalList, enviromentInformation);
                                    }
                                });
                            } else {
                                System.out.printf("The %s is alone, there is nothing to breed in cell [%d][%d]", AvailableAnimals.getAvatarByAnimalName(animalContainer[i][j].getClass().getSimpleName()), i, j);
                            }
                        }
                    }
                }
                breedThreadPool.shutdown();
                try {
                    if (!breedThreadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                        System.err.println("Breed tasks did not complete in time.");
                        breedThreadPool.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    System.err.println("Thread pool interrupted: " + e.getMessage());
                }
                System.out.println("Breed logic completed.");
            }

            public void displayAnimalHistory () {
                System.out.println("****RECORD OF ACTIONS FOR EACH ANIMAL****\n");
                //.out.println("This is a record of all the actions performed by the animals during the execution.");

                for (int i = 0; i < enviromentRows; i++) {
                    System.out.println("Row: " + i);
                    for (int j = 0; j < enviromentColumns; j++) {
                        System.out.printf("Summary [%d][%d] - %s\n", i, j, animalContainerRepresentation[i][j]);
                        for (Animal animalList : animalContainer[i][j]) {
                            //System.out.println(AvailableAnimals.getAvatarByAnimalName(animalList.getClass().getSimpleName()));
                            //Map<String, String> sortedMap = new TreeMap<>(animalList.getAnimalMemory());
                            System.out.print(animalList.getAnimalMemory().entrySet().stream()
                                    .map(memory -> memory.getKey() + " " + memory.getValue()).sorted()
                                    .collect(Collectors.joining(", ", "[", "]")));
                            System.out.println();

                        }
                    }
                }
            }


        }


