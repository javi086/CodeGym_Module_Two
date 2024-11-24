package org.codeGym.javiModuleTwo.models;

import org.codeGym.javiModuleTwo.config.constants.AvailableAnimals;
import org.codeGym.javiModuleTwo.models.Plant.Plant;
import org.codeGym.javiModuleTwo.models.carnivore.*;
import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;
import org.codeGym.javiModuleTwo.models.herbivore.*;
import org.codeGym.javiModuleTwo.services.Carnivore;

import java.util.*;

public abstract class Animal {

    public float weight;
    public boolean isAlive;
    public char gender;
    public int possibilityOfBeingEaten;
    public Map<String, String> animalMemory = new HashMap<>();
    //public Enviroment enviroment = new Enviroment();


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

    public void eat(List<Animal> animalList) {
        Random random = new Random();
        Set<Integer> indexOfAnimalsAlreadySelected = new HashSet<>();
        //List<Animal> animalList = enviromentInformation.getAnimalContainer()[row][col];

        while (indexOfAnimalsAlreadySelected.size() < animalList.size()) {
            int randomIndexAnimalSelect = random.nextInt(animalList.size());
            System.out.println("Indice random:" + randomIndexAnimalSelect);
            if (indexOfAnimalsAlreadySelected.add(randomIndexAnimalSelect)) { // add() returns false if the index already exists

                System.out.println(animalList.get(randomIndexAnimalSelect));

                if (animalList.get(randomIndexAnimalSelect) instanceof Wolf && animalList.get(randomIndexAnimalSelect).isAlive) {
                    Wolf wolf = (Wolf) animalList.get(randomIndexAnimalSelect);
                    wolf.hunt(animalList);

                }

            }
        }


        System.out.println("Hora de comer");
        //Determining who is going to eat first carnivore or herbivore

/*
        for (Animal animal : animalListInCell){
            if(animal instanceof Wolf){

                Wolf wolf = (Wolf) animal;

            }
        }
*/
    }


    public void breed() {
    }

    ;

    public void die() {
    }

    ;


    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
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

    public void verifyPossibilityOfBeEaten(Animal animalAsHunter, List<Animal> possiblePreys) {
        switch (animalAsHunter.getClass().getSimpleName()) {
            case "Wolf":
                for (Animal animal : possiblePreys) {
                    if (animal instanceof Boa || animal instanceof Fox || animal instanceof Bear || animal instanceof Eagle || animal instanceof Caterpillar || animal instanceof Plant) {
                        animal.setPossibilityOfBeingEaten(0);
                    } else if (animal instanceof Horse || animal instanceof Buffalo) {
                        animal.setPossibilityOfBeingEaten(10);
                    } else if (animal instanceof Deer || animal instanceof Boar) {
                        animal.setPossibilityOfBeingEaten(15);
                    } else if (animal instanceof Duck) {
                        animal.setPossibilityOfBeingEaten(40);
                    } else if (animal instanceof Rabbit || animal instanceof Goat) {
                        animal.setPossibilityOfBeingEaten(60);
                    } else if (animal instanceof Sheep) {
                        animal.setPossibilityOfBeingEaten(70);
                    } else if (animal instanceof Mouse) {
                        animal.setPossibilityOfBeingEaten(80);
                    }
                }
                break;

            case "Boa":
                for (Animal animal : possiblePreys) {
                    if (animal instanceof Wolf || animal instanceof Bear || animal instanceof Eagle || animal instanceof Horse || animal instanceof Deer || animal instanceof Goat || animal instanceof Sheep || animal instanceof Boar || animal instanceof Buffalo || animal instanceof Caterpillar || animal instanceof Plant) {
                        animal.setPossibilityOfBeingEaten(0);
                    } else if (animal instanceof Duck) {
                        animal.setPossibilityOfBeingEaten(10);
                    } else if (animal instanceof Fox) {
                        animal.setPossibilityOfBeingEaten(15);
                    } else if (animal instanceof Rabbit) {
                        animal.setPossibilityOfBeingEaten(20);
                    } else if (animal instanceof Mouse) {
                        animal.setPossibilityOfBeingEaten(40);
                    }
                }
                break;
            case "Fox":
                for (Animal animal : possiblePreys) {
                    if (animal instanceof Wolf || animal instanceof Boa || animal instanceof Bear || animal instanceof Eagle || animal instanceof Horse || animal instanceof Deer || animal instanceof Goat || animal instanceof Sheep || animal instanceof Boar || animal instanceof Buffalo || animal instanceof Plant) {
                        animal.setPossibilityOfBeingEaten(0);
                    } else if (animal instanceof Caterpillar) {
                        animal.setPossibilityOfBeingEaten(40);
                    } else if (animal instanceof Duck) {
                        animal.setPossibilityOfBeingEaten(60);
                    } else if (animal instanceof Rabbit) {
                        animal.setPossibilityOfBeingEaten(70);
                    } else if (animal instanceof Mouse) {
                        animal.setPossibilityOfBeingEaten(90);
                    }
                }
                break;
            case "Bear":
                for (Animal animal : possiblePreys) {
                    if (animal instanceof Wolf || animal instanceof Fox || animal instanceof Eagle || animal instanceof Caterpillar || animal instanceof Plant) {
                        animal.setPossibilityOfBeingEaten(0);
                    } else if (animal instanceof Duck) {
                        animal.setPossibilityOfBeingEaten(10);
                    } else if (animal instanceof Buffalo) {
                        animal.setPossibilityOfBeingEaten(20);
                    } else if (animal instanceof Horse) {
                        animal.setPossibilityOfBeingEaten(40);
                    } else if (animal instanceof Boar) {
                        animal.setPossibilityOfBeingEaten(50);
                    } else if (animal instanceof Goat || animal instanceof Sheep) {
                        animal.setPossibilityOfBeingEaten(70);
                    } else if (animal instanceof Boa || animal instanceof Deer || animal instanceof Rabbit) {
                        animal.setPossibilityOfBeingEaten(80);
                    } else if (animal instanceof Mouse) {
                        animal.setPossibilityOfBeingEaten(90);
                    }
                }
                break;
            case "Eagle":
                for (Animal animal : possiblePreys) {
                    if (animal instanceof Wolf || animal instanceof Boa ||
                            animal instanceof Bear || animal instanceof Horse || animal instanceof Deer ||
                            animal instanceof Goat || animal instanceof Sheep || animal instanceof Boar ||
                            animal instanceof Buffalo || animal instanceof Caterpillar || animal instanceof Plant) {
                        animal.setPossibilityOfBeingEaten(0);
                    } else if (animal instanceof Fox) {
                        animal.setPossibilityOfBeingEaten(10);
                    } else if (animal instanceof Duck) {
                        animal.setPossibilityOfBeingEaten(80);
                    } else if (animal instanceof Rabbit || animal instanceof Mouse) {
                        animal.setPossibilityOfBeingEaten(90);
                    }
                }
                break;
            case "Horse":
                for (Animal animal : possiblePreys) {
                    if (animal instanceof Wolf || animal instanceof Boa || animal instanceof Fox ||
                            animal instanceof Bear || animal instanceof Eagle || animal instanceof Deer ||
                            animal instanceof Rabbit || animal instanceof Mouse || animal instanceof Goat ||
                            animal instanceof Sheep || animal instanceof Boar || animal instanceof Buffalo ||
                            animal instanceof Duck || animal instanceof Caterpillar) {
                        animal.setPossibilityOfBeingEaten(0);
                    } else if (animal instanceof Plant) {
                        animal.setPossibilityOfBeingEaten(100);
                    }
                }
                break;
            case "Deer":
                for (Animal animal : possiblePreys) {
                    if (animal instanceof Wolf || animal instanceof Boa || animal instanceof Fox ||
                            animal instanceof Bear || animal instanceof Eagle || animal instanceof Horse ||
                            animal instanceof Rabbit || animal instanceof Mouse || animal instanceof Goat ||
                            animal instanceof Sheep || animal instanceof Boar || animal instanceof Buffalo ||
                            animal instanceof Duck || animal instanceof Caterpillar) {
                        animal.setPossibilityOfBeingEaten(0);
                    } else if (animal instanceof Plant) {
                        animal.setPossibilityOfBeingEaten(100);
                    }
                }
                break;
            case "Rabbit":
                for (Animal animal : possiblePreys) {
                    if (animal instanceof Wolf || animal instanceof Boa || animal instanceof Fox ||
                            animal instanceof Bear || animal instanceof Eagle || animal instanceof Horse ||
                            animal instanceof Deer || animal instanceof Goat || animal instanceof Sheep ||
                            animal instanceof Boar || animal instanceof Buffalo || animal instanceof Duck ||
                            animal instanceof Caterpillar || animal instanceof Mouse) {
                        animal.setPossibilityOfBeingEaten(0);
                    } else if (animal instanceof Plant) {
                        animal.setPossibilityOfBeingEaten(100);
                    }
                }
                break;
            case "Mouse":
                for (Animal animal : possiblePreys) {
                    if (animal instanceof Wolf || animal instanceof Boa || animal instanceof Fox ||
                            animal instanceof Bear || animal instanceof Eagle || animal instanceof Horse ||
                            animal instanceof Deer || animal instanceof Rabbit || animal instanceof Goat ||
                            animal instanceof Sheep || animal instanceof Boar || animal instanceof Buffalo || animal instanceof Duck) {
                        animal.setPossibilityOfBeingEaten(0);
                    } else if (animal instanceof Caterpillar) {
                        animal.setPossibilityOfBeingEaten(90);
                    } else if (animal instanceof Plant) {
                        animal.setPossibilityOfBeingEaten(100);
                    }
                }
                break;
            case "Goat":
                for (Animal animal : possiblePreys) {
                    if (animal instanceof Wolf || animal instanceof Boa || animal instanceof Fox ||
                            animal instanceof Bear || animal instanceof Eagle || animal instanceof Deer ||
                            animal instanceof Rabbit || animal instanceof Mouse || animal instanceof Horse ||
                            animal instanceof Sheep || animal instanceof Boar || animal instanceof Buffalo ||
                            animal instanceof Duck || animal instanceof Caterpillar) {
                        animal.setPossibilityOfBeingEaten(0);
                    } else if (animal instanceof Plant) {
                        animal.setPossibilityOfBeingEaten(100);
                    }
                }
                break;
            case "Sheep":
                for (Animal animal : possiblePreys) {
                    if (animal instanceof Wolf || animal instanceof Boa || animal instanceof Fox ||
                            animal instanceof Bear || animal instanceof Eagle || animal instanceof Deer ||
                            animal instanceof Rabbit || animal instanceof Mouse || animal instanceof Goat ||
                            animal instanceof Horse || animal instanceof Boar || animal instanceof Buffalo ||
                            animal instanceof Duck || animal instanceof Caterpillar) {
                        animal.setPossibilityOfBeingEaten(0);
                    } else if (animal instanceof Plant) {
                        animal.setPossibilityOfBeingEaten(100);
                    }
                }
                break;
            case "Boar":
                for (Animal animal : possiblePreys) {
                    if (animal instanceof Wolf || animal instanceof Boa || animal instanceof Fox ||
                            animal instanceof Bear || animal instanceof Eagle || animal instanceof Deer ||
                            animal instanceof Rabbit || animal instanceof Goat || animal instanceof Duck ||
                            animal instanceof Horse || animal instanceof Sheep || animal instanceof Buffalo) {
                        animal.setPossibilityOfBeingEaten(0);
                    }else if (animal instanceof Mouse) {
                            animal.setPossibilityOfBeingEaten(50);
                    } else if (animal instanceof Caterpillar) {
                        animal.setPossibilityOfBeingEaten(90);
                    }else if (animal instanceof Plant) {
                        animal.setPossibilityOfBeingEaten(100);
                    }
                }
                break;
            case "Buffalo":
                for (Animal animal : possiblePreys) {
                    if (animal instanceof Wolf || animal instanceof Boa || animal instanceof Fox ||
                            animal instanceof Bear || animal instanceof Eagle || animal instanceof Deer ||
                            animal instanceof Rabbit || animal instanceof Mouse || animal instanceof Goat ||
                            animal instanceof Horse || animal instanceof Boar || animal instanceof Sheep ||
                            animal instanceof Duck || animal instanceof Caterpillar) {
                        animal.setPossibilityOfBeingEaten(0);
                    } else if (animal instanceof Plant) {
                        animal.setPossibilityOfBeingEaten(100);
                    }
                }
                break;
            case "Duck":
                for (Animal animal : possiblePreys) {
                    if (animal instanceof Wolf || animal instanceof Boa || animal instanceof Fox ||
                            animal instanceof Bear || animal instanceof Eagle || animal instanceof Deer ||
                            animal instanceof Rabbit || animal instanceof Mouse || animal instanceof Goat ||
                            animal instanceof Horse || animal instanceof Boar || animal instanceof Buffalo){
                        animal.setPossibilityOfBeingEaten(0);
                    } else if (animal instanceof Caterpillar) {
                        animal.setPossibilityOfBeingEaten(90);
                    } else if (animal instanceof Plant) {
                        animal.setPossibilityOfBeingEaten(100);
                    }
                }
                break;
            case "Caterpillar":
                for (Animal animal : possiblePreys) {
                    if (animal instanceof Wolf || animal instanceof Boa || animal instanceof Fox ||
                            animal instanceof Bear || animal instanceof Eagle || animal instanceof Deer ||
                            animal instanceof Rabbit || animal instanceof Mouse || animal instanceof Goat ||
                            animal instanceof Horse || animal instanceof Boar || animal instanceof Sheep ||
                            animal instanceof Duck) {
                        animal.setPossibilityOfBeingEaten(0);
                    } else if (animal instanceof Plant) {
                        animal.setPossibilityOfBeingEaten(100);
                    }
                }
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + animalAsHunter.getClass().getSimpleName());
        }


    }


}
