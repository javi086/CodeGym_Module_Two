package org.codeGym.javiModuleTwo;

import org.codeGym.javiModuleTwo.models.Animal;
import org.codeGym.javiModuleTwo.models.carnivore.Wolf;
import org.codeGym.javiModuleTwo.models.enviroment.Environment;
import org.codeGym.javiModuleTwo.models.herbivore.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Java Module Two!");
        System.out.println("Let's define the environment's dimensions");
        boolean isValidDimensions = false;
        int rows = 0;
        int columns = 0;
        int numberOfAnimals = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            Environment environment = null;
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            while (!isValidDimensions) {
                System.out.print("Please enter number of rows from 1-9: ");
                rows = scanner.nextInt();
                System.out.print("Please enter number of columns from 1-9: ");
                columns = scanner.nextInt();
                System.out.print("Please enter number of animals you want per cell: ");
                numberOfAnimals = scanner.nextInt();

                isValidDimensions = rows > 0 && columns > 0 && rows <= 9 && columns <= 9;
                if (isValidDimensions) {
                    environment = new Environment(rows, columns, numberOfAnimals);
                    environment.prepareEnvironment();
                    try {
                        Environment finalEnvironment = environment;
                        Future<?> movenmentFuture = executorService.submit(() -> finalEnvironment.moveAnimal(finalEnvironment));
                        //Future<?> eatFuture = executorService.submit(() -> finalEnvironment.eatAnotherAnimal(finalEnvironment));
                        //Future<?> breedFuture = executorService.submit(() -> finalEnvironment.breedAnimal(finalEnvironment));

                        movenmentFuture.get();
                        //eatFuture.get();
                        //breedFuture.get();

                        environment.displayAnimalLocation();
                        environment.displayAnimalHistory();

                    } catch (InterruptedException | ExecutionException e) {
                        System.err.println("An error occurred during execution: " + e.getMessage());
                    }
                    finally {
                        executorService.shutdown();
                    }
                }

            }
        } catch (Exception e) {
            System.err.println("Invalid input. Please enter valid integers for rows and columns.");
        }

    }

}