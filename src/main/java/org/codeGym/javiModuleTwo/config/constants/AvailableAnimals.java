package org.codeGym.javiModuleTwo.config.constants;

public enum AvailableAnimals {
    WOLF(0, "🐺"),
    BOA(1, "🐍"),
    FOX(2, "🦊"),
    BEAR(3, "🐻"),
    EAGLE(4, "🦅"),
    HORSE(5, "🦄"),
    DEER(6, "🦌"),
    RABBIT(7, "🐰"),
    MOUSE(8, "🐭"),
    GOAT(9, "🐐"),
    SHEEP(10, "🐑"),
    BOAR(11, "🐗"),
    BUFFALO(12, "🐃"),
    DUCK(13, "🦆"),
    CATERPILLAR(14, "🐛"),
    PLANT(15, "🌱");


    private final int animalCode;
    private final String avatar;

    AvailableAnimals(int code, String avatarPicture){
        this.animalCode = code;
        this.avatar = avatarPicture;
    }

    public int getAnimalCode(){
        return animalCode;
    }
    public String getAnimalAvatar(){
        return avatar;
    }

    public static String getAnimalNameByCode(int code){
        for (AvailableAnimals animal : AvailableAnimals.values()){
            if(animal.getAnimalCode() == code){
                return animal.name();
            }
        }
        return "Unknown Code";
    }

    public static String getAvatarByAnimalName(String animalName){
        for (AvailableAnimals animal : AvailableAnimals.values()){
            if(animal.name().equals(animalName.toUpperCase())){
                return animal.getAnimalAvatar();
            }
        }
        return "Unknown Code";
    }

    public static int getAnimalCodeByName(String name){
        int animalCode=0;
        for (AvailableAnimals animal : AvailableAnimals.values()){
            if(animal.name().equals(name)){
               animalCode=animal.getAnimalCode();
            }
        }
        return animalCode;
    }
}
