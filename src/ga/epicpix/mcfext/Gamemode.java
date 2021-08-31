package ga.epicpix.mcfext;

public final class Gamemode {

    public static String getCompatible(String val) {
        if(val.equals("0") || val.equals("s") || val.equals("survival")) return "survival";
        if(val.equals("1") || val.equals("c") || val.equals("creative")) return "creative";
        if(val.equals("2") || val.equals("a") || val.equals("adventure")) return "adventure";
        if(val.equals("3") || val.equals("sp") || val.equals("spectator")) return "spectator";
        return null;
    }

}
