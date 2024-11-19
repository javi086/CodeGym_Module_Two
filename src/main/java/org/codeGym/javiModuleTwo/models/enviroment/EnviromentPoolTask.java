package org.codeGym.javiModuleTwo.models.enviroment;

public class EnviromentPoolTask implements Runnable{

    private String enviromentTaskName;

    public EnviromentPoolTask(String taskName){
        this.enviromentTaskName = taskName;
    }

    @Override
    public void run() {
        Enviroment enviroment = new Enviroment();
        System.out.println(enviromentTaskName + "Is running on: " + Thread.currentThread().getName());

        try {
            enviroment.prepareEnviromentArrays();
            enviroment.determineNumberOfAnimalsByCell();
            enviroment.determineAnimalsByCode();
            enviroment.displayAnimalLocation();
            Thread.sleep(1000);
           } catch (InterruptedException e) {
                System.out.println(enviromentTaskName + " was interrupted.");
            }
            System.out.println(enviromentTaskName + " finished.");
        }
}
