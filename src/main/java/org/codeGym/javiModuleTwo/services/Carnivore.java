package org.codeGym.javiModuleTwo.services;

import org.codeGym.javiModuleTwo.models.Animal;

import java.util.List;

public interface Carnivore {

    boolean hunt(List<Animal> preyList);
}
