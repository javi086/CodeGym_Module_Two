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
    private final int numberOfAnimalsPerCell;
    private List<Animal>[][] animalContainer;
    private List<Integer>[][] numberOfAnimalsByCell;
    private List<Integer>[][] availableMovementsInEachCell;
    String[][] animalContainerRepresentation;
    private List<Animal> livingCarnivores = new ArrayList<>();
    private List<Animal> livingHerbivores = new ArrayList<>();
    private List<Animal> livingAnimals = new ArrayList<>();
    private List<Animal> livingPlants = new ArrayList<>();
    private List<Animal> deadAnimals = new ArrayList<>();

    //Essential methods to set before starting the move, eat, reproduce actions.
    public Environment(int environmentRows, int environmentColumns, int AnimalsPerCell) {
        this.environmentRows = environmentRows;
        this.environmentColumns = environmentColumns;
        this.numberOfAnimalsPerCell = AnimalsPerCell;
    }

    public void prepareEnvironment() {
        System.out.printf("******List of tasks required for the environment preparation:*******%n"
                + "1. Prepare arrays required.%n"
                + "2. Determine randomly the number of animals by cell.%n"
                + "3. Create and assign animals into each cell.%n"
                + "4. Determine randomly the possible movements of each animal based on the cell location.%n"
                + "5. Display initial positions.%n");
        prepareEnvironmentArrays();
        determineNumberOfAnimalsByCell();
        createAnimalsByCode();
        determinePossibleMovements();
        displayAnimalLocation();
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
                lists[i].add(random.nextInt(numberOfAnimalsPerCell) + 1);
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


    public void moveAnimal(Environment environmentInformation) {
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
                        moveThreadPool.submit(() -> {
                            synchronized (animalContainer[row][col]) {
                                animal.move(row, col, radomMovement, environmentInformation);

                            }
                        });
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
        System.out.println("Move logic completed successfully.");
    }


    public void eatAnotherAnimal(Environment environmentInformation) {
        System.out.println("Eat logic initiated");
        ExecutorService eatThreadPool = Executors.newFixedThreadPool(environmentRows * environmentColumns);
        Plant plant = new Plant();


        for (int i = 0; i < environmentRows; i++) {
            for (int j = 0; j < environmentColumns; j++) {
                animalContainer[0][0].clear();
                animalContainer[0][0].add(plant);
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
                        if(animal instanceof Herbivore){
                            animal.setAnimalMemory("Eat:", "I'm alone and "+((Herbivore) animal).pasture());
                        } else if (animal instanceof Carnivore) {
                            animal.setAnimalMemory("Eat:", "I'm alone and " +((Carnivore) animal).sniff());
                        }else{
                            animal.setAnimalMemory("Eat:", "I'm alone and " +((Photosynthetic) animal).performPhotosynthesis());
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
        System.out.println("Eat logic completed successfully.");
    }


    public void breedAnimal(Environment environmentInformation) {
        System.out.println("Breed logic initiated.");
        ExecutorService breedThreadPool = Executors.newFixedThreadPool(environmentRows * environmentColumns);
        Fox fox = new Fox();
        Deer deer = new Deer();
        Plant plant = new Plant();
        Caterpillar caterpillar = new Caterpillar();
        Eagle eagle = new Eagle();
        eagle.setGender("M");
        Eagle eagle2 = new Eagle();
        eagle2.setGender("F");
        Rabbit rabbit = new Rabbit();

        for (int i = 0; i < environmentRows; i++) {
            for (int j = 0; j < environmentColumns; j++) {
                int row = i;
                int col = j;

                breedThreadPool.submit(() -> {
//                   animalContainer[2][1].clear();
//                    animalContainer[2][1].add(fox);
//                    animalContainer[2][1].add(deer);
//                    animalContainer[2][1].add(plant);
//                    animalContainer[2][1].add(caterpillar);
//                    animalContainer[2][1].add(eagle);
//                    animalContainer[2][1].add(eagle2);
//                   animalContainer[2][1].add(rabbit);
                    List<Animal> possibleAnimalPartnerList = new ArrayList<>(getAnimalContainer()[row][col]);
                    List<Animal> femaleAnimalList = new ArrayList<>();

                    if (possibleAnimalPartnerList.size() > 1) {
                        for (Animal animalLookingForPartner : possibleAnimalPartnerList) {
                            if (!animalLookingForPartner.isWithCouple()) {
                               //System.out.printf("Row: %d, Col: %d\n", row, col);
                               //System.out.printf("Looking for partners for animal %s and couple match %s sex: %s %n", animalLookingForPartner.getClass().getSimpleName(), animalLookingForPartner.isWithCouple(), animalLookingForPartner.getGender());
                               //System.out.printf("Animals to breed before loop: %s from the cell[%s][%s]%n", femaleAnimalList, row, col);
                                //femaleAnimalList = animalLookingForPartner.lookForPartner(animalLookingForPartner, possibleAnimalPartnerList);
                            }

                        }

                        synchronized (animalContainer[row][col]) {
                            for (Animal animal : femaleAnimalList) {
                               animal.breed(animal, row, col, environmentInformation);
                            }
                        }
                    } else {
                        possibleAnimalPartnerList.get(0).setAnimalMemory("Breed:", "I'm alone there is no one to breed here 😨");
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
        System.out.println("Breed logic completed successfully.");


    }

    public void displayAnimalHistory() {
        System.out.printf("%n**** RECORD OF ACTIONS FOR EACH ANIMAL ****%n");

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


}


