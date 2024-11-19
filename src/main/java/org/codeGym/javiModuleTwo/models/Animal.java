package org.codeGym.javiModuleTwo.models;

import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Animal {

    public float weight;
    public boolean isAlive;
    public char gender;
    public List<String> animalMemory = new ArrayList<>();
    //public Enviroment enviroment = new Enviroment();


    public abstract  void eat(List<Animal> animalList);

    public void move(Enviroment enviroment) {
        //System.out.println("6. Moviendo animal");

        int radomMovement;
        int indexOfAnimalToMove;
        Random random = new Random();
        String recordOfMovement = "The %s moved from the C-[%d][%d] to the C-[%d][%d]";
        String recordOfMovementFormatted;


        for (int i = 0; i < enviroment.getEnviromentRows(); i++) {
            //System.out.println("Row: " + i);
            for (int j = 0; j < enviroment.getEnviromentColumns(); j++) {
                //System.out.printf("Cell-[%d][%d]:\n ", i, j);
                List<Integer> movementsList = enviroment.getAvailableMovementsInEachCell()[i][j];
                radomMovement = movementsList.get(random.nextInt(movementsList.size()));
                indexOfAnimalToMove = random.nextInt(enviroment.getAnimalContainer()[i][j].size());
                //System.out.println("Movimiento determinado: " + radomMovement);
                //System.out.printf("Animal a mover %s con el indice %d: ", enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove).getClass().getSimpleName(), indexOfAnimalToMove);

                if (radomMovement == 0 && enviroment.getAnimalContainer()[i][j].size() > 1) {
                    //System.out.printf("[%d][%d]-Inicial\n", i, j);
                    enviroment.getAnimalContainer()[i][j - 1].add(enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove));
                    enviroment.getAnimalContainer()[i][j].remove(indexOfAnimalToMove);
                    //System.out.printf("[%d][%d]-Final", i, j - 1);
                    recordOfMovementFormatted = String.format(recordOfMovement, enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove).getClass().getSimpleName(), i, j, i, j-1);
                    animalMemory.add(recordOfMovementFormatted);

                }

                if (radomMovement == 1 && enviroment.getAnimalContainer()[i][j].size() > 1) {
                   // System.out.printf("[%d][%d]-Inicial\n", i, j);
                    enviroment.getAnimalContainer()[i - 1][j].add(enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove));
                    enviroment.getAnimalContainer()[i][j].remove(indexOfAnimalToMove);
                    //System.out.printf("[%d][%d]-Final", i - 1, j);
                    recordOfMovementFormatted = String.format(recordOfMovement, enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove).getClass().getSimpleName(), i, j, i-1, j);
                    animalMemory.add(recordOfMovementFormatted);
                }
                if (radomMovement == 2 && enviroment.getAnimalContainer()[i][j].size() > 1) {
                    //System.out.printf("[%d][%d]-Inicial\n", i, j);
                    enviroment.getAnimalContainer()[i][j + 1].add(enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove));
                    enviroment.getAnimalContainer()[i][j].remove(indexOfAnimalToMove);
                    //System.out.printf("[%d][%d]-Final", i, j + 1);
                    recordOfMovementFormatted = String.format(recordOfMovement, enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove).getClass().getSimpleName(), i, j, i, j+1);
                    animalMemory.add(recordOfMovementFormatted);
                }
                if (radomMovement == 3 && enviroment.getAnimalContainer()[i][j].size() > 1) {
                    //System.out.printf("[%d][%d]-Inicial\n", i, j);
                    enviroment.getAnimalContainer()[i + 1][j].add(enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove));
                    enviroment.getAnimalContainer()[i][j].remove(indexOfAnimalToMove);
                    //System.out.printf("[%d][%d]-Final", i + 1, j);
                    recordOfMovementFormatted = String.format(recordOfMovement, enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove).getClass().getSimpleName(), i, j, i+1, j);
                    animalMemory.add(recordOfMovementFormatted);
                }

               // System.out.println();
            }
           // System.out.println();
        }
    }
    public void breed(){};
    public void die(){};



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
}
