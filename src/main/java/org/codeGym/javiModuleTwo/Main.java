package org.codeGym.javiModuleTwo;

import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;

public class Main {
    public static void main(String[] args) {
        Enviroment enviroment = new Enviroment();
        enviroment.setEnviroment();
        enviroment.determineNumberOfAnimalsByCell();
        enviroment.determineAnimalsByCode();
        enviroment.displayEnviroment();
        //List<Animal> animals = enviroment.provideCreature(creatureNames);
        //enviroment.setCreaturesInCell(animals);


    }
}