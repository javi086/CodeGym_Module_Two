package org.codeGym.javiModuleTwo.models.carnivore;

import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.services.Carnivore;

import java.util.List;
import java.util.Random;

public class Wolf extends Animal implements Carnivore {


    public Wolf() {
    }
    @Override
    public void eat(List<Animal> preyList) {
        boolean wasTheHuntSuccessful = false;
        System.out.println("El lobo se prepara para cazar");
        wasTheHuntSuccessful = hunt(preyList);
        if (wasTheHuntSuccessful){
            System.out.println("El lobo esta comiendo");
        }
        else{
            System.out.println("El lobo sigue con hambre");
        }

    }


    @Override
    public void move() {

    }

    @Override
    public void breed() {

    }

    @Override
    public void die() {

    }

    @Override
    public boolean hunt(List<Animal> preyList) {
        Random random = new Random();
            int preyIndex = random.nextInt(preyList.size());
            preyList.get(preyIndex).setAlive(false);
            return true;
    }
}
