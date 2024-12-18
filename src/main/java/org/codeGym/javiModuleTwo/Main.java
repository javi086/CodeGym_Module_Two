package org.codeGym.javiModuleTwo;

import org.codeGym.javiModuleTwo.models.enviroment.Environment;

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
                System.out.print("Please enter maximum number of animals you want per cell: ");
                numberOfAnimals = scanner.nextInt();

                isValidDimensions = rows > 0 && columns > 0 && rows <= 9 && columns <= 9;

                if (isValidDimensions) {
                    environment = new Environment(rows, columns, numberOfAnimals);
                    environment.prepareEnvironment();
                    try {
                        Environment finalEnvironment = environment;
                        Future<String> movenmentFuture = executorService.submit(() -> finalEnvironment.moveAnimal(finalEnvironment));
                        Future<String> eatFuture = executorService.submit(() -> finalEnvironment.eatAnotherAnimal(finalEnvironment));
                        Future<String> breedFuture = executorService.submit(() -> finalEnvironment.breedAnimal(finalEnvironment));

                        String movementResult = movenmentFuture.get();
                        String eatResult = eatFuture.get();
                        String breedResult = breedFuture.get();

                        System.out.println(movementResult);
                        System.out.println(eatResult);
                        System.out.println(breedResult);

                        System.out.printf("%n(In the following section, you will see new animal's positions, animals death and depending on the couples there could be new babies.");
                        System.out.printf("%nYou can verify the detail of each animal in the section: ACTIONS PERFORMED BY EACH ANIMAL IN EACH CELL.)%n");
                        System.out.printf("%n*** Final animal positions after all actions happened (Move, Eat, Breed). ***%n");

                          environment.displayAnimalLocation();
                          environment.displayAnimalInformationInEnvironment();
                          environment.displayAnimalMemories();

                    } catch (Exception e) {
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