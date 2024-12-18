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

public class Environment {
    private final int environmentRows;
    private final int environmentColumns;
    private final int numberOfAnimalsInEachCell;
    private List<Animal>[][] animalContainer;
    private List<Integer>[][] numberOfAnimalsByCell;
    private List<Integer>[][] availableMovementsInEachCell;
    String[][] animalContainerRepresentation;

    //Essential methods to set before starting the move, eat, reproduce actions.
    public Environment(int environmentRows, int environmentColumns, int AnimalsPerCell) {
        this.environmentRows = environmentRows;
        this.environmentColumns = environmentColumns;
        this.numberOfAnimalsInEachCell = AnimalsPerCell;
    }

    public void prepareEnvironment() {
        System.out.printf("%n******List of tasks required for the environment preparation:*******%n"
                + "1. Prepare arrays required.%n"
                + "2. Determine randomly the number of animals by cell.%n"
                + "3. Create and assign animals into each cell.%n"
                + "4. Determine randomly the possible movements of each animal based on the cell location.%n"
                + "5. Display initial positions.%n");
        prepareEnvironmentArrays();
        determineNumberOfAnimalsByCell();
        createAnimalsByCode();
        determinePossibleMovements();
        System.out.printf("%n*** Initial animal positions before moving. ****%n");
        displayAnimalLocation();
    }

    public List<Integer>[][] getAvailableMovementsInEachCell() {

        return this.availableMovementsInEachCell;
    }

    public List<Animal>[][] getAnimalContainer() {
        return animalContainer;
    }


    public void prepareEnvironmentArrays() {
        animalContainer = new ArrayList[environmentRows][environmentColumns];
        numberOfAnimalsByCell = new ArrayList[environmentRows][environmentColumns];
        availableMovementsInEachCell = new ArrayList[environmentRows][environmentColumns];
        animalContainerRepresentation = new String[environmentRows][environmentColumns];

        for (int i = 0; i < environmentRows; i++) {
            for (int j = 0; j < environmentColumns; j++) {
                animalContainer[i][j] = new ArrayList<>();
                numberOfAnimalsByCell[i][j] = new ArrayList<>();
                availableMovementsInEachCell[i][j] = new ArrayList<>();
            }
        }
    }

    public void determineNumberOfAnimalsByCell() {
        Random random = new Random();
        for (List<Integer>[] lists : numberOfAnimalsByCell) {
            for (int i = 0; i < lists.length; i++) {
                lists[i].add(random.nextInt(numberOfAnimalsInEachCell) + 1);
            }
        }
        for (int i = 0; i < environmentRows; i++) {
            for (int j = 0; j < environmentColumns; j++) {
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

        for (int i = 0; i < environmentRows; i++) {
            for (int j = 0; j < environmentColumns; j++) {
                for (Integer number : numberOfAnimalsByCell[i][j]) {
                    for (int k = 0; k < number; k++) {
                        animalCode = random.nextInt(AvailableAnimals.values().length);
                        //String animalName = AvailableAnimals.getAnimalNameByCode(animalCode);
                        switch (AvailableAnimals.getAnimalNameByCode(animalCode)) {
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

        for (int i = 0; i < environmentRows; i++) {
            for (int j = 0; j < environmentColumns; j++) {
                if (i != environmentRows - 1) {
                    availableMovementsInEachCell[i][j].add(AvailableMovements.DOWN.getAnimalMoveCode());
                }
                if (i != 0) {
                    availableMovementsInEachCell[i][j].add(AvailableMovements.UP.getAnimalMoveCode());
                }
                if (j != environmentColumns - 1) {
                    availableMovementsInEachCell[i][j].add(AvailableMovements.RIGHT.getAnimalMoveCode());
                }
                if (j != 0) {
                    availableMovementsInEachCell[i][j].add(AvailableMovements.LEFT.getAnimalMoveCode());
                }
            }
        }
    }

    public void displayAnimalLocation() {
        //System.out.printf("%n Current animal state:%n");
        for (int i = 0; i < environmentRows; i++) {
            for (int j = 0; j < environmentColumns; j++) {
                StringBuffer avatars = new StringBuffer();
                for (Animal animal1 : animalContainer[i][j]) {
                    avatars.append(AvailableAnimals.getAvatarByAnimalName(animal1.getClass().getSimpleName()))
                            .append("(")
                            .append(animal1.isAlive ? animal1.getGender() : "💀")
                            .append(")")
                            .append(" ");
                }
                animalContainerRepresentation[i][j] = avatars.toString().trim();
            }
        }
        for (int i = 0; i < environmentRows; i++) {
            for (int j = 0; j < environmentColumns; j++) {
                System.out.printf(" | C-[%d][%d]: %s ", i, j, animalContainerRepresentation[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    public String moveAnimal(Environment environmentInformation) {
        System.out.println("Movement logic initiated");
        ExecutorService moveThreadPool = Executors.newFixedThreadPool(environmentRows * environmentColumns);
        Random random = new Random();

        for (int i = 0; i < environmentRows; i++) {
            for (int j = 0; j < environmentColumns; j++) {
                List<Animal> animalList = new ArrayList<>(animalContainer[i][j]);
                List<Integer> movementsList = getAvailableMovementsInEachCell()[i][j];

                if (animalList.size() > 1) {
                    int row = i;
                    int col = j;
                    final int radomMovement = movementsList.get(random.nextInt(movementsList.size()));
                    for (Animal animal : animalList) {
                        if (animal.isAlive) {
                            moveThreadPool.submit(() -> {
                                synchronized (animalContainer[row][col]) {
                                    animal.move(row, col, radomMovement, environmentInformation);
                                }
                            });
                        }
                    }
                } else {
                    for (Animal animal : animalList) {
                        animal.setAnimalMemory("Movement:", "There is only one animal in the location, it won't move");
                    }
                }
            }
        }
        moveThreadPool.shutdown();
        try {
            if (!moveThreadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                System.err.println("Move tasks did not complete in time.");
                moveThreadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println("Thread pool interrupted: " + e.getMessage());
        }
        return "Move logic completed successfully.";
    }


    public String eatAnotherAnimal(Environment environmentInformation) {
        System.out.println("Eat logic initiated");
        ExecutorService eatThreadPool = Executors.newFixedThreadPool(environmentRows * environmentColumns);

        for (int i = 0; i < environmentRows; i++) {
            for (int j = 0; j < environmentColumns; j++) {
                List<Animal> animalList = new ArrayList<>(animalContainer[i][j]);
                if (animalList.size() > 1) {
                    int row = i;
                    int col = j;
                    for (Animal animal : animalList) {
                        eatThreadPool.submit(() -> {
                            synchronized (animalContainer[row][col]) {
                                animal.eat(animalList, environmentInformation, row, col);
                            }
                        });
                    }
                } else {
                    for (Animal animal : animalList) {
                        if (animal instanceof Herbivore) {
                            animal.setAnimalMemory("Eat:", "I'm alone and " + ((Herbivore) animal).pasture());
                        } else if (animal instanceof Carnivore) {
                            animal.setAnimalMemory("Eat:", "I'm alone and " + ((Carnivore) animal).sniff());
                        } else {
                            animal.setAnimalMemory("Eat:", "I'm alone and " + ((Photosynthetic) animal).performPhotosynthesis());
                        }

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
        return "Eat logic completed successfully.";
    }


    public String breedAnimal(Environment environmentInformation) {
        System.out.println("Breed logic initiated.");
        ExecutorService breedThreadPool = Executors.newFixedThreadPool(environmentRows * environmentColumns);

        for (int i = 0; i < environmentRows; i++) {
            for (int j = 0; j < environmentColumns; j++) {
                int row = i;
                int col = j;
                List<Animal> animalsAliveLookingForPartner = new ArrayList<>(getAnimalContainer()[row][col].stream().
                        filter(Animal::isAlive).collect(Collectors.toList()));

                breedThreadPool.submit(() -> {

                    if (animalsAliveLookingForPartner.size() > 1) {
                        List<Animal> femaleAnimalList = new ArrayList<>();
                        for (Animal animalLookingForPartner : animalsAliveLookingForPartner) {
                            if (!animalLookingForPartner.isWithCouple()) {
                                animalLookingForPartner.lookForPartner(animalLookingForPartner, animalsAliveLookingForPartner).ifPresent(femaleAnimalList::add);
                            }
                        }
                        synchronized (animalContainer[row][col]) {
                            if (!femaleAnimalList.isEmpty()) {
                                for (Animal animal : femaleAnimalList) {
                                    animal.breed(animal, row, col, environmentInformation);
                                }
                            }
                        }
                    } else {
                        animalsAliveLookingForPartner.get(0).setAnimalMemory("Breed:", "I'm alone there is no one to breed here 😨");
                    }
                });
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
        return "Breed logic completed successfully.";
    }

    public void displayAnimalMemories() {
        System.out.printf("%n**** ACTIONS PERFORMED BY EACH ANIMAL IN EACH CELL ****%n");

        for (int i = 0; i < environmentRows; i++) {
            System.out.println("Row: " + i);
            for (int j = 0; j < environmentColumns; j++) {
                System.out.printf("Summary [%d][%d] - %s\n", i, j, animalContainerRepresentation[i][j]);
                for (Animal animalList : animalContainer[i][j]) {
                    System.out.print(animalList.getAnimalMemory().entrySet().stream()
                            .map(memory -> memory.getKey() + " " + memory.getValue()).sorted()
                            .collect(Collectors.joining(", ", "[", "]")));
                    System.out.println();
                }
            }
        }
    }

    public void displayAnimalInformationInEnvironment() {
        int totalOfAnimalsCreated = 0;
        int totalOfAnimalsAlive = 0;
        int totalOFAnimalsDeath = 0;
        int totalOfCarnivores = 0;
        int totalOfHerbivores = 0;
        int totalOfPlants = 0;
        int totalOFNewBabies = 0;

        for (int i = 0; i < environmentRows; i++) {
            for (int j = 0; j < environmentColumns; j++) {
                totalOfAnimalsCreated += animalContainer[i][j].size();
                totalOfAnimalsAlive += (int) animalContainer[i][j].stream().filter(Animal::isAlive).count();
                totalOFAnimalsDeath += (int) animalContainer[i][j].stream().filter(animal -> !animal.isAlive()).count();
                totalOfCarnivores += (int) animalContainer[i][j].stream().filter(animal -> animal instanceof Carnivore).count();
                totalOfHerbivores += (int) animalContainer[i][j].stream().filter(animal -> animal instanceof Herbivore).count();
                totalOfPlants += (int) animalContainer[i][j].stream().filter(animal -> animal instanceof Photosynthetic).count();
                totalOFNewBabies += (int) animalContainer[i][j].stream().filter(animal -> animal.getAnimalMemory().containsValue("I'm the new baby")).count();
            }
        }
        System.out.println("*** GENERAL INFORMATION +++");
        System.out.printf("Total of animals created: %s %n", totalOfAnimalsCreated);
        System.out.printf("Total of animals still alive: %s %n", totalOfAnimalsAlive);
        System.out.printf("Total of animals  death: %s %n", totalOFAnimalsDeath);
        System.out.printf("Total of Carnivores created: %s %n", totalOfCarnivores);
        System.out.printf("Total of Herbivores created: %s %n", totalOfHerbivores);
        System.out.printf("Total of Plants created: %s %n", totalOfPlants);
        System.out.printf("Total of new babies: %s %n", totalOFNewBabies);


    }


}


