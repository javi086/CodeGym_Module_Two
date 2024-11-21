package org.codeGym.javiModuleTwo.models;

import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;

import java.util.*;

public abstract class Animal {

    public float weight;
    public boolean isAlive;
    public char gender;
    public Map<String, String> animalMemory = new HashMap<>();
    //public Enviroment enviroment = new Enviroment();


    public void setAnimalMemory(String key, String value) {
        animalMemory.put(key, value);
    }

    public String getAnimalMemory() {
        String memory = "";
        for (String memoryRecord : animalMemory.values()){
           memory=memoryRecord;
        }
   return memory;
    }

    public abstract void eat(List<Animal> animalList);

    public void move(int row, int col, int radomMovement, Enviroment enviroment) {
        //System.out.println("6. Moviendo animal");
        //System.out.println("Animal recibido " + this.getClass().getSimpleName());
        String message = "%s: Initial-[%d][%d]-Final[%d][%d]";
        String recordOfMovement;

        if (radomMovement == 0 && enviroment.getAnimalContainer()[row][col].size() > 1) {
            //System.out.printf("[%d][%d]-Inicial\n", i, j);
            enviroment.getAnimalContainer()[row][col - 1].add(this);
            enviroment.getAnimalContainer()[row][col].remove(this);
            //System.out.printf("%s: Initial-[%d][%d]-Final[%d][%d]\n", this.getClass().getSimpleName(), row, col, row, col - 1);
            recordOfMovement = String.format(message, this.getClass().getSimpleName(), row, col, row, col - 1);
            setAnimalMemory("Movement",recordOfMovement);
        } else if (radomMovement == 1 && enviroment.getAnimalContainer()[row][col].size() > 1) {
            //System.out.printf("[%d][%d]-Inicial\n", i, j);
            enviroment.getAnimalContainer()[row - 1][col].add(this);
            enviroment.getAnimalContainer()[row][col].remove(this);
            //System.out.printf("%s: Initial-[%d][%d]-Final[%d][%d]\n", this.getClass().getSimpleName(), row, col, row - 1, col);
            //setAnimalMemory(String.format(recordOfMovement, enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove).getClass().getSimpleName(), i, j, i-1, j));
            recordOfMovement = String.format(message, this.getClass().getSimpleName(), row, col, row - 1, col);
            setAnimalMemory("Movement",recordOfMovement);
        } else if (radomMovement == 2 && enviroment.getAnimalContainer()[row][col].size() > 1) {
            //System.out.printf("[%d][%d]-Inicial\n", i, j);
            enviroment.getAnimalContainer()[row][col + 1].add(this);
            enviroment.getAnimalContainer()[row][col].remove(this);
            //System.out.printf("%s: Initial-[%d][%d]-Final[%d][%d]\n", this.getClass().getSimpleName(), row, col, row, col + 1);
            //setAnimalMemory(String.format(recordOfMovement, enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove).getClass().getSimpleName(), i, j, i, j+1));
            recordOfMovement = String.format(message, this.getClass().getSimpleName(), row, col, row, col + 1);
            setAnimalMemory("Movement",recordOfMovement);
        } else if (radomMovement == 3 && enviroment.getAnimalContainer()[row][col].size() > 1) {
            //System.out.printf("[%d][%d]-Inicial\n", i, j);
            enviroment.getAnimalContainer()[row + 1][col].add(this);
            enviroment.getAnimalContainer()[row][col].remove(this);
            //System.out.printf("%s: Initial-[%d][%d]-Final[%d][%d]\n", this.getClass().getSimpleName(), row, col, row + 1, col);
            //setAnimalMemory(String.format(recordOfMovement, enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove).getClass().getSimpleName(), i, j, i+1, j));
            recordOfMovement = String.format(message, this.getClass().getSimpleName(), row, col, row + 1, col);
            setAnimalMemory("Movement", recordOfMovement);
        } else {
            //recordOfMovement = String.format(message, this.getClass().getSimpleName(), row, col, row, col);
            setAnimalMemory("Movement", "The animal is sleepy and it didn't move at all");
            //System.out.println("Animal is sleepy and it didn't move");
            //setAnimalMemory("The animal didn't want to move is lazy.");
        }

    }

    /* public void move(Enviroment enviroment) {
         System.out.println("6. Moviendo animal");
         int radomMovement;
         int indexOfAnimalToMove;
         Random random = new Random();
         String recordOfMovement = "The %s moved from the C-[%d][%d] to the C-[%d][%d]";

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
                     setAnimalMemory(String.format(recordOfMovement, enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove).getClass().getSimpleName(), i, j, i, j-1));
                 }

                 if (radomMovement == 1 && enviroment.getAnimalContainer()[i][j].size() > 1) {
                     //System.out.printf("[%d][%d]-Inicial\n", i, j);
                     enviroment.getAnimalContainer()[i - 1][j].add(enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove));
                     enviroment.getAnimalContainer()[i][j].remove(indexOfAnimalToMove);
                     //System.out.printf("[%d][%d]-Final", i - 1, j);
                    setAnimalMemory(String.format(recordOfMovement, enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove).getClass().getSimpleName(), i, j, i-1, j));

                 }
                 if (radomMovement == 2 && enviroment.getAnimalContainer()[i][j].size() > 1) {
                     //System.out.printf("[%d][%d]-Inicial\n", i, j);
                     enviroment.getAnimalContainer()[i][j + 1].add(enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove));
                     enviroment.getAnimalContainer()[i][j].remove(indexOfAnimalToMove);
                     //System.out.printf("[%d][%d]-Final", i, j + 1);
                     setAnimalMemory(String.format(recordOfMovement, enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove).getClass().getSimpleName(), i, j, i, j+1));

                 }
                 if (radomMovement == 3 && enviroment.getAnimalContainer()[i][j].size() > 1) {
                     //System.out.printf("[%d][%d]-Inicial\n", i, j);
                     enviroment.getAnimalContainer()[i + 1][j].add(enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove));
                     enviroment.getAnimalContainer()[i][j].remove(indexOfAnimalToMove);
                     //System.out.printf("[%d][%d]-Final", i + 1, j);
                     setAnimalMemory(String.format(recordOfMovement, enviroment.getAnimalContainer()[i][j].get(indexOfAnimalToMove).getClass().getSimpleName(), i, j, i+1, j));

                 }
                 else {
                     setAnimalMemory("The animal didn't want to move is lazy.");
                 }

                // System.out.println();
             }
            // System.out.println();
         }

     }*/
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
}
