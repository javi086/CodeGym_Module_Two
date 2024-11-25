package org.codeGym.javiModuleTwo.models.Plant;


import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;
import org.codeGym.javiModuleTwo.services.Photosynthetic;

import java.util.List;

public class Plant  extends Animal implements Photosynthetic {



    public void eat(){
        System.out.println("The plant is feeding from the ground.");
    }

    public void breed() {
        System.out.println("The plant spreads its spores to reproduce");
    }


    public void die() {
        System.out.println("The was eaten by an animal");
    }

    @Override
    public void performPhotosynthesis() {
        System.out.println("The plant is happy and implementing photosynthesis");
    }
}
