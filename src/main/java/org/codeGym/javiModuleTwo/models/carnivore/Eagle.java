package org.codeGym.javiModuleTwo.models.carnivore;

import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;
import org.codeGym.javiModuleTwo.services.Carnivore;

import java.util.List;

public class Eagle extends Animal implements  Carnivore{
    @Override
    public void eat(List<Animal> animalList) {
        System.out.println("La aguila va a comer");
    }


    @Override
    public void breed() {

    }

    @Override
    public void die() {

    }

    @Override
    public boolean hunt(List<Animal> preyList) {
        return false;
    }
}
