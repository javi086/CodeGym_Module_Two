package org.codeGym.javiModuleTwo.models;

import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.Plant.Plant;
import org.codeGym.javiModuleTwo.models.carnivore.*;
import org.codeGym.javiModuleTwo.models.enviroment.Environment;

import java.util.*;

public abstract class Animal {

    public boolean isHungry;
    public boolean isAlive;
    public boolean isWithCouple;
    public String gender;
    public String typeOfAnimal;
    public int possibilityOfBeingEaten;
    public Map<String, String> animalMemory = new HashMap<>();


    protected Animal() {
        this.setHungry(true);
        this.setAlive(true);
        this.setWithCouple(false);
        this.assignGenderRandomly();
        //this.setAnimalMemory("Sex:", getGender());
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isWithCouple() {
        return isWithCouple;
    }

    public void setWithCouple(boolean withCouple) {
        isWithCouple = withCouple;
    }

    public String getGender() {
        return gender;
    }

    private void assignGenderRandomly() {
        Random random = new Random();
        int randomlySelectedSexuality = random.nextInt(2);
        if (randomlySelectedSexuality == 0) {
            setGender("F");
        } else {
            setGender("M");
        }
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTypeOfAnimal() {
        return typeOfAnimal;
    }

    public void setTypeOfAnimal(String typeOfAnimal) {
        this.typeOfAnimal = typeOfAnimal;
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

    public Map<String, String> getAnimalMemory() {
        return animalMemory;
    }


    public void move(int row, int col, int radomMovement, Environment environmentInformation) {
        String memoryMessage = "The %s %s moved from [%d][%d] to [%d][%d]";
        String memoryMessageFormatted;

        if (radomMovement == 0 && environmentInformation.getAnimalContainer()[row][col].size() > 1) {
            //System.out.printf("[%d][%d]-Inicial\n", i, j);
            environmentInformation.getAnimalContainer()[row][col - 1].add(this);
            environmentInformation.getAnimalContainer()[row][col].remove(this);
            //System.out.printf("%s: Initial-[%d][%d]-Final[%d][%d]\n", this.getClass().getSimpleName(), row, col, row, col - 1);
            memoryMessageFormatted = String.format(memoryMessage, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col, row, col - 1);
            setAnimalMemory("Movement:", memoryMessageFormatted);
        } else if (radomMovement == 1 && environmentInformation.getAnimalContainer()[row][col].size() > 1) {
            environmentInformation.getAnimalContainer()[row - 1][col].add(this);
            environmentInformation.getAnimalContainer()[row][col].remove(this);
            memoryMessageFormatted = String.format(memoryMessage, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col, row - 1, col);
            setAnimalMemory("Movement:", memoryMessageFormatted);
        } else if (radomMovement == 2 && environmentInformation.getAnimalContainer()[row][col].size() > 1) {
            environmentInformation.getAnimalContainer()[row][col + 1].add(this);
            environmentInformation.getAnimalContainer()[row][col].remove(this);
            memoryMessageFormatted = String.format(memoryMessage, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col, row, col + 1);
            setAnimalMemory("Movement:", memoryMessageFormatted);
        } else if (radomMovement == 3 && environmentInformation.getAnimalContainer()[row][col].size() > 1) {
            environmentInformation.getAnimalContainer()[row + 1][col].add(this);
            environmentInformation.getAnimalContainer()[row][col].remove(this);
            memoryMessageFormatted = String.format(memoryMessage, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col, row + 1, col);
            setAnimalMemory("Movement:", memoryMessageFormatted);
        } else {
            memoryMessage = "The %s %s is sleepy and decided to take a nap. it didn't move at all,  stays in the position [%d][%d]";
            memoryMessageFormatted = String.format(memoryMessage, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col);
            setAnimalMemory("Movement:", memoryMessageFormatted);
        }

    }

    public void eat(List<Animal> animalListInCell, Environment environmentInformation) {
        Random random = new Random();
        String hunterMemoryMessage = "The %s (%s) ate a %s (%s)";
        String preyMemoryMessage = "The %s (%s) was eaten by %s (%s)";
        String memoryMessageFormatted;
        Set<Integer> animalsChoosenRandomlyOrEatenAsAPrey = new HashSet<>();
        Map<Animal, Integer> possibilityOfBeEaten = new HashMap<>();

        while (animalsChoosenRandomlyOrEatenAsAPrey.size() < animalListInCell.size()) {
            possibilityOfBeEaten.clear();
            int hunterRandomlySelected = random.nextInt(animalListInCell.size());
            Animal hunter = animalListInCell.get(hunterRandomlySelected);
            if (animalsChoosenRandomlyOrEatenAsAPrey.add(hunterRandomlySelected) && hunter.isHungry()) {
                //Verify possibility of be eaten for each animal
                for (Animal prey : animalListInCell) {
                    if (!prey.equals(hunter)) {
                        verifyPossibilityOfBeEaten(hunter, prey);
                        possibilityOfBeEaten.put(prey, prey.getPossibilityOfBeingEaten());
                    }
                }
                //Determine animal to be eaten based on max possibility obtained, except for plants
                if (!(hunter instanceof Plant)) {
                    Animal mostLikekyAnimalOfBeEaten = possibilityOfBeEaten.entrySet()
                            .stream()
                            .max(Map.Entry.comparingByValue())
                            .map(Map.Entry::getKey)
                            .orElse(null);
                    if (mostLikekyAnimalOfBeEaten != null && mostLikekyAnimalOfBeEaten.getPossibilityOfBeingEaten() > 0) {
                        //hunter information
                        hunter.setHungry(false);
                        memoryMessageFormatted = String.format(hunterMemoryMessage,
                                AvailableAnimals.getAvatarByAnimalName(hunter.getClass().getSimpleName()),
                                hunter.getClass().getSimpleName(),
                                AvailableAnimals.getAvatarByAnimalName(mostLikekyAnimalOfBeEaten.getClass().getSimpleName()),
                                mostLikekyAnimalOfBeEaten.getClass().getSimpleName());
                        hunter.setAnimalMemory("Eat:", memoryMessageFormatted);


                        //prey information
                        mostLikekyAnimalOfBeEaten.setAlive(false);
                        environmentInformation.setDeadAnimals(mostLikekyAnimalOfBeEaten);
                        memoryMessageFormatted = String.format(preyMemoryMessage,
                                AvailableAnimals.getAvatarByAnimalName(mostLikekyAnimalOfBeEaten.getClass().getSimpleName()),
                                mostLikekyAnimalOfBeEaten.getClass().getSimpleName(),
                                AvailableAnimals.getAvatarByAnimalName(hunter.getClass().getSimpleName()),
                                hunter.getClass().getSimpleName());
                        mostLikekyAnimalOfBeEaten.setAnimalMemory("Eat:", memoryMessageFormatted);
                        animalListInCell.remove(mostLikekyAnimalOfBeEaten);
                    } else {
                        animalListInCell.get(hunterRandomlySelected).setAnimalMemory("Eat:", "There are more animals but none of them is a possible prey");
                    }
                } else if (animalListInCell.get(hunterRandomlySelected) instanceof Plant) {
                    ((Plant) animalListInCell.get(hunterRandomlySelected)).eat();
                }
            }
        }
    }

    public List<Animal> lookingForPartner(Animal animalLookingForPartner, List<Animal> possibleAnimalPartnerList) {
        Set<Animal> animalHasFoundAMatch = new HashSet<>();
        List<Animal> animalsToBreed = new ArrayList<>();


        for (Animal possibleAnimalPartner : possibleAnimalPartnerList) {
            if (animalLookingForPartner.getClass().getSimpleName().equals(possibleAnimalPartner.getClass().getSimpleName()) &&
                    !animalLookingForPartner.getGender().equals(possibleAnimalPartner.getGender())) {
                animalLookingForPartner.setWithCouple(true);
                possibleAnimalPartner.setWithCouple(true);
                //animalHasFoundAMatch.add(animalLookingForPartner);
                //animalHasFoundAMatch.add(possibleAnimalPartner);
                if (animalLookingForPartner.getGender().equals("F")) {
                    System.out.printf("Female: %s ", animalLookingForPartner);
                    animalsToBreed.add(animalLookingForPartner);//Female
                    possibleAnimalPartner.setAnimalMemory("Breed:", "Yes, I found a partner to breed A " + animalLookingForPartner + animalLookingForPartner.getGender() + possibleAnimalPartner + possibleAnimalPartner.getGender());
                } else {
                    System.out.printf("Male: %s ", animalLookingForPartner);
                    animalsToBreed.add(possibleAnimalPartner);//Female
                    animalLookingForPartner.setAnimalMemory("Breed:", "Yes, I found a partner to breed B " + animalLookingForPartner + animalLookingForPartner.getGender() + possibleAnimalPartner + possibleAnimalPartner.getGender());
                }
            } else {
                animalLookingForPartner.setAnimalMemory("Breed:", "No, I couldn't find a partner to breed 😭 C " + animalLookingForPartner + animalLookingForPartner.getGender() + possibleAnimalPartner);
            }
        }

        return animalsToBreed;
    }

    public void breed(Animal animal, int row, int col, Environment environmentInformation) {
        System.out.println("Logic of reproduction");
        List<Animal> newBabyAnimalList = new ArrayList<>();

        try {
            Animal babyAnimal = animal.getClass().getDeclaredConstructor().newInstance();
            babyAnimal.setAnimalMemory("Breed:", "I'm the new baby");
            newBabyAnimalList.add(babyAnimal);
            animal.setAnimalMemory("Breed:", "Yes, I gave birth to a baby " + AvailableAnimals.getAvatarByAnimalName(babyAnimal.getClass().getSimpleName()));
            AdditionalBreedBehaviour(animal);
            System.out.printf("Breeding successful: The %s created a new baby %s%n",
                    animal.getClass().getSimpleName(),
                    babyAnimal.getClass().getSimpleName());
        } catch (Exception e) {
            System.err.printf("Error creating new baby animal instance: %s%n", e.getMessage());
        }

        //Adding new babies to the current cell
        if (!newBabyAnimalList.isEmpty()) {
            environmentInformation.getAnimalContainer()[row][col].addAll(newBabyAnimalList);
            System.out.printf("%d new animals added to cell [%d][%d]%n", newBabyAnimalList.size(), row, col);
        }
    }

    public void AdditionalBreedBehaviour(Animal animal) {
        switch (animal.getClass().getSimpleName()){
            case "Bear":
                Bear bear = (Bear) animal;
                bear.setAnimalMemory("AdditionalBehaviour:",bear.breedNewBaby());
                break;
            case "Boa":
                Boa boa = (Boa) animal;
                boa.setAnimalMemory("AdditionalBehaviour:",boa.hideAndLeave());
                break;
            case "Eagle":
                Eagle eagle = (Eagle) animal;
                eagle.setAnimalMemory("AdditionalBehaviour:",eagle.trill());
                break;
            case "Fox":
                Fox fox = (Fox) animal;
                fox.setAnimalMemory("AdditionalBehaviour:",fox.cleanNewBaby());
                break;
            case "Wolf":
                Wolf wolf = (Wolf) animal;
                wolf.setAnimalMemory("AdditionalBehaviour:", wolf.howl());
                break;

        }
    }

    public void verifyPossibilityOfBeEaten(Animal animalAsHunter, Animal animalAsPrey) {
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

       // int possibility = possibilityMatrix[AvailableAnimals.getAnimalCodeByName(animalAsHunter.getClass().getSimpleName().toUpperCase())][AvailableAnimals.getAnimalCodeByName(animalAsPrey.getClass().getSimpleName().toUpperCase())];
        animalAsPrey.setPossibilityOfBeingEaten(possibilityMatrix[AvailableAnimals.getAnimalCodeByName(animalAsHunter.getClass().getSimpleName().toUpperCase())][AvailableAnimals.getAnimalCodeByName(animalAsPrey.getClass().getSimpleName().toUpperCase())]);
    }

}
