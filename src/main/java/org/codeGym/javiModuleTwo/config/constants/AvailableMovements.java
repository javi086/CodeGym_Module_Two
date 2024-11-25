package org.codeGym.javiModuleTwo.config.constants;

public enum AvailableMovements {
LEFT(0),
 UP(1),
 RIGHT(2),
 DOWN(3);

    private final int animalMoveCode;

    AvailableMovements(int codeOfMovement) {
        this.animalMoveCode =codeOfMovement;
    }
    public int getAnimalMoveCode(){
        return animalMoveCode;
    }


    public static String getAnimalMovement(int codeOfMovement){
        for (AvailableMovements movement : AvailableMovements.values()){
            if(movement.getAnimalMoveCode() == codeOfMovement){
                return movement.name();
            }
        }
        return "Unknown code";
    }

}
