package org.codeGym.javiModuleTwo.models;

import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.Plant.Plant;
import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;

import java.util.*;

public abstract class Animal {

    public boolean isHungry;
    public boolean isAlive;
    public String gender;
    public String typeOfAnimal;
    public int possibilityOfBeingEaten;
    public  Map<String, String> animalMemory = new HashMap<>();


    protected Animal() {
        this.setAlive(true);
        this.setHungry(true);
        this.assignGenderRandomly();
        //this.setAnimalMemory("Sex:", getGender());
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getGender() {
        return gender;
    }

    private void assignGenderRandomly(){
        Random random = new Random();
        int randomlySelectedSexuality = random.nextInt(2);
        if (randomlySelectedSexuality == 0){
            setGender("F");
        }else {
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

    public  Map<String, String> getAnimalMemory() {
        return animalMemory;
    }


    public void move(int row, int col, int radomMovement, Enviroment enviroment) {
        String memoryMessage = "The %s %s moved from [%d][%d] to [%d][%d]";
        String memoryMessageFormatted;

        if (radomMovement == 0 && enviroment.getAnimalContainer()[row][col].size() > 1) {
            //System.out.printf("[%d][%d]-Inicial\n", i, j);
            enviroment.getAnimalContainer()[row][col - 1].add(this);
            enviroment.getAnimalContainer()[row][col].remove(this);
            //System.out.printf("%s: Initial-[%d][%d]-Final[%d][%d]\n", this.getClass().getSimpleName(), row, col, row, col - 1);
            memoryMessageFormatted = String.format(memoryMessage, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col, row, col - 1);
            setAnimalMemory("Movement:", memoryMessageFormatted);
        } else if (radomMovement == 1 && enviroment.getAnimalContainer()[row][col].size() > 1) {
            enviroment.getAnimalContainer()[row - 1][col].add(this);
            enviroment.getAnimalContainer()[row][col].remove(this);
            memoryMessageFormatted = String.format(memoryMessage, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col, row - 1, col);
            setAnimalMemory("Movement:", memoryMessageFormatted);
        } else if (radomMovement == 2 && enviroment.getAnimalContainer()[row][col].size() > 1) {
            enviroment.getAnimalContainer()[row][col + 1].add(this);
            enviroment.getAnimalContainer()[row][col].remove(this);
            memoryMessageFormatted = String.format(memoryMessage, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col, row, col + 1);
            setAnimalMemory("Movement:", memoryMessageFormatted);
        } else if (radomMovement == 3 && enviroment.getAnimalContainer()[row][col].size() > 1) {
            enviroment.getAnimalContainer()[row + 1][col].add(this);
            enviroment.getAnimalContainer()[row][col].remove(this);
            memoryMessageFormatted = String.format(memoryMessage, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col, row + 1, col);
            setAnimalMemory("Movement:", memoryMessageFormatted);
        } else {
            memoryMessage = "The %s %s is sleepy and decided to take a nap. it didn't move at all,  stays in the position [%d][%d]";
            memoryMessageFormatted = String.format(memoryMessage, AvailableAnimals.getAvatarByAnimalName(this.getClass().getSimpleName()), this.getClass().getSimpleName(), row, col);
            setAnimalMemory("Movement", memoryMessageFormatted);
        }

    }

    public void eat(List<Animal> animalListInCell, Enviroment enviromentInformation) {
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
                //System.out.printf("Animal selected randomly: %s %n", hunter.getClass().getSimpleName());

                //Verify possibility of be eaten for each animal
                for (Animal prey : animalListInCell) {
                    if (!prey.equals(hunter)) {
                        verifyPossibilityOfBeEaten(hunter, prey);
                       // System.out.println("Animal as prey: " + prey.getClass().getSimpleName() + " Possibility of be eaten: " + prey.getPossibilityOfBeingEaten());
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
                    if (mostLikekyAnimalOfBeEaten != null  && mostLikekyAnimalOfBeEaten.getPossibilityOfBeingEaten() > 0) {
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
                        enviromentInformation.setDeadAnimals(mostLikekyAnimalOfBeEaten);
                        memoryMessageFormatted = String.format(preyMemoryMessage,
                                AvailableAnimals.getAvatarByAnimalName(mostLikekyAnimalOfBeEaten.getClass().getSimpleName()),
                                mostLikekyAnimalOfBeEaten.getClass().getSimpleName(),
                                AvailableAnimals.getAvatarByAnimalName(hunter.getClass().getSimpleName()),
                                hunter.getClass().getSimpleName());
                        mostLikekyAnimalOfBeEaten.setAnimalMemory("Eat:", memoryMessageFormatted);
                        animalListInCell.remove(mostLikekyAnimalOfBeEaten);
                    }else {
                        animalListInCell.get(hunterRandomlySelected).setAnimalMemory("Eat:", "There are more animals but none of them is a possible prey");
                    }
                } else if (animalListInCell.get(hunterRandomlySelected) instanceof Plant) {
                    ((Plant) animalListInCell.get(hunterRandomlySelected)).eat();
                }
            }
        }
    }


    public void breed(int row, int col, List<Animal> animalListInCell, Enviroment enviromentInformation) {
        System.out.println("Reproducing animal");
       List<Animal> newBabyAnimalList = new ArrayList<>();
               // List<Animal> animalListInCell = Arrays.stream(enviromentInformation.getAnimalContainer()).findAny(getGender('M'))
               // List<Animal> animalListInCell = animalList;

                         if(animalListInCell.size() > 1) {
                             Set<Animal> animalsAlreadySelected = new HashSet<>();

                             for (int k = 0; k <animalListInCell.size() ; k++) {
                                   Animal animal = animalListInCell.get(k);
                                // System.out.println(animal.getGender());
                                 for (int l = k + 1; l <animalListInCell.size() ; l++) {
                                    Animal animal1 = animalListInCell.get(l);
                                     //System.out.println(animal1.getGender());

                                     if (!animalsAlreadySelected.contains(animal) &&
                                         !animalsAlreadySelected.contains(animal1) &&
                                         animal.getGender() != animal1.getGender() &&
                                         animal.getClass().equals(animal1.getClass())){

                                        try {
                                            Animal newBabyAnimal = animal.getClass().getDeclaredConstructor().newInstance();
                                            newBabyAnimalList.add(newBabyAnimal);
                                            System.out.println("New baby: "+ newBabyAnimal.getClass().getSimpleName());
                                            animalsAlreadySelected.add(animal);
                                            animalsAlreadySelected.add(animal1);
                                        } catch (Exception e){
                                            e.printStackTrace();
                                        }

                                     }

                                 }
                             }
                             if (!newBabyAnimalList.isEmpty()){
                                 for (Animal babyAnimal : newBabyAnimalList){
                                     enviromentInformation.getAnimalContainer()[row][col].add(babyAnimal);
                                 }
                             }
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

        int possibility = possibilityMatrix[AvailableAnimals.getAnimalCodeByName(animalAsHunter.getClass().getSimpleName().toUpperCase())][AvailableAnimals.getAnimalCodeByName(animalAsPrey.getClass().getSimpleName().toUpperCase())];
        animalAsPrey.setPossibilityOfBeingEaten(possibility);
    }

}
