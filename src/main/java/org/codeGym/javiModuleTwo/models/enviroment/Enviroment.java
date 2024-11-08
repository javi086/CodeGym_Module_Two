package org.codeGym.javiModuleTwo.models.enviroment;


import org.codeGym.javiModuleTwo.config.constants.AvailableLivingCreatureCodes;
import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.carnivore.*;
import org.codeGym.javiModuleTwo.models.herbivore.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Enviroment {
    private int enviromentWidth =5;
    private int enviromentHeight =5;
    private final List<Object>[][] enviromentContainer = new ArrayList[enviromentWidth][enviromentHeight];

    public Enviroment(){};

    public int getenviromentWidth(){
        return this.enviromentWidth;
    }
    public void setenviromentWidth(int width1){
        this.enviromentWidth = width1;
    };
    public int getenviromentHeight() {
        return enviromentHeight;
    }

    public void setenviromentHeight(int enviromentHeight) {
        this.enviromentHeight = enviromentHeight;
    }

        public void setEnviroment(){
            System.out.println("1. Hola setEnviroment");
            System.out.println("Enviroment lenght: " +enviromentContainer.length);
            for (int i = 0; i < enviromentWidth; i++) {
                for (int j = 0; j < enviromentHeight; j++) {
                    enviromentContainer[i][j] = new ArrayList<>();
                }
            }
        }

        public List<Object>[][] determineNumberOfCreaturesByCell() {
            System.out.println("2. Hola determineNumberOfCreaturesByCell");
            int limit = 4;
            Random random = new Random();
            //int numberOfCreatures;
            for (int i = 0; i < enviromentContainer.length; i++) {
                for (int j = 0; j < enviromentContainer.length; j++) {
                    enviromentContainer[i][j].add(random.nextInt(limit) + 1);
                }
            }
            // numberOfCreatures = random.nextInt(limit)+1;
            for (int i = 0; i < enviromentContainer.length; i++) {
                    for (int j = 0; j < enviromentContainer.length; j++) {
                        System.out.printf("Cell [%d][%d]: ", i, j);
                        for (Object numberOfCreatures : enviromentContainer[i][j]) {
                        System.out.print(numberOfCreatures + "\t");
                    }
                }
                System.out.println();
            }
            return enviromentContainer;
        }

        public String[] determineCreatureByCode(int numberOfCreatures) {
            System.out.println("3. Hola determineCreatureByCode");
            int limit = 16;
            Random random = new Random();
            int creatureCode;
            String[] creatureNames = new String[numberOfCreatures];
            System.out.println("Names and codes:");
            for (int i = 0; i < numberOfCreatures; i++) {
                creatureCode = random.nextInt(limit);
                for (AvailableLivingCreatureCodes creature : AvailableLivingCreatureCodes.values()){
                    if(creature.getLivingCreatureCode() == creatureCode){
                       creatureNames[i] = creature.name();
                        System.out.println(creature.name()+" code: " +creature.getLivingCreatureCode());
                    }
                }
            }
            return creatureNames;
        }

        public void setCreaturesInCell(List<Animal> animalList){
            System.out.println("Hola setCreaturesInCell");
            for (int i = 0; i <enviromentContainer.length ; i++) {
                for (int j = 0; j <enviromentContainer.length ; j++) {
                   for (Animal animalObject: animalList){
                       enviromentContainer[i][j].add(animalObject);
                   }
                }
            }
            System.out.println("Animals created:");
            for (int i = 0; i <enviromentContainer.length ; i++) {
                System.out.println("Row-"+i);
                for (Animal animal : animalList){
                for (int j = 0; j <enviromentContainer.length; j++) {
                        System.out.print("Col-"+ j + " " +animal.getClass().getSimpleName() + "\t");
                    }
                    System.out.println();
                }
                System.out.println();
            }

        }


    public  List<Animal> provideCreature(String[] creatureNames) {
        System.out.println("4. Hola provideCreature");
        List<Animal> animalList  = new ArrayList<>();

        System.out.println("Creaturas recibidas");
        for (String name : creatureNames){
            System.out.println(name);
        }


        for (String name : creatureNames) {
            switch (name) {
                case "BEAR":
                    Bear bear = new Bear();
                    animalList.add(bear);
                    break;
                case "BOA":
                    Boa boa = new Boa();
                    animalList.add(boa);
                    break;
                case "EAGLE":
                    Eagle eagle = new Eagle();
                    animalList.add(eagle);
                    break;
                case "FOX":
                    Fox fox = new Fox();
                    animalList.add(fox);
                    break;
                case "WOLF":
                    Wolf wolf = new Wolf();
                    animalList.add(wolf);
                    break;
                case "BOAR":
                    Boar boar = new Boar();
                    animalList.add(boar);
                    break;
                case "BUFFALO":
                    Buffalo buffalo = new Buffalo();
                    animalList.add(buffalo);
                    break;
                case "CATERPILLAR":
                    Caterpillar caterpillar = new Caterpillar();
                    animalList.add(caterpillar);
                    break;
                case "DEER":
                    Deer deer = new Deer();
                    animalList.add(deer);
                    break;
                case "DUCK":
                    Duck duck = new Duck();
                    animalList.add(duck);
                    break;
                case "GOAT":
                    Goat goat = new Goat();
                    animalList.add(goat);
                    break;
                case "HORSE":
                    Horse horse = new Horse();
                    animalList.add(horse);
                    break;
                case "MOUSE":
                    Mouse mouse = new Mouse();
                    animalList.add(mouse);
                    break;
                case "RABBIT":
                    Rabbit rabbit = new Rabbit();
                    animalList.add(rabbit);
                    break;
                case "SHEEP":
                    Sheep sheep = new Sheep();
                    animalList.add(sheep);
                    break;
                default:
                    System.out.println("Sorry! Creature code not recognized");
            }
        }
        return  animalList;
    }

}

