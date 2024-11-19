package org.codeGym.javiModuleTwo;

import org.codeGym.javiModuleTwo.models.enviroment.Enviroment;

public class Main {
    public static void main(String[] args) {
        Enviroment enviroment = new Enviroment();
        enviroment.startAnimalTasks(enviroment);
        }

}