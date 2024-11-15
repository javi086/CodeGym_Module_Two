package org.codeGym.javiModuleTwo.config.constants;

public enum AvailableLivingCreatureCodes {
    WOLF(0, "🐺"),
    BOA(1, "🐍"),
    FOX(2, "🦊"),
    BEAR(3, "🐻"),
    EAGLE(4, "🦅"),
    HORSE(5, "🐴"),
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


    private final int livingCreatureCode;
    private final String avatar;

    AvailableLivingCreatureCodes(int code, String avatarPicture){
        this.livingCreatureCode = code;
        this.avatar = avatarPicture;
    }

    public int getLivingCreatureCode(){
        return livingCreatureCode;
    }
    public String getAvatar(){
        return avatar;
    }

    public static String getCreatureByType(int code){
        for (AvailableLivingCreatureCodes creature : AvailableLivingCreatureCodes.values()){
            if(creature.getLivingCreatureCode() == code){
                return creature.name();
            }
        }
        return "Unknown Code";
    }
}
