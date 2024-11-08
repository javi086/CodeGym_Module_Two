package org.codeGym.javiModuleTwo;

import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Enviroment enviroment = new Enviroment();
        enviroment.setEnviroment();
        int numberOfCreatures = enviroment.determineNumberOfCreaturesByCell();
        String[] creatureNames = enviroment.determineCreatureByCode(numberOfCreatures);
        List<Animal> animals = enviroment.provideCreature(creatureNames);
        enviroment.setCreaturesInCell(animals);


    }
}