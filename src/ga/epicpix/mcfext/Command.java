package ga.epicpix.mcfext;

import ga.epicpix.mcfext.commands.SayCommand;
import java.util.ArrayList;

public abstract class Command {

    private static final ArrayList<Command> COMMANDS = new ArrayList<>();

    public static void init() {
        COMMANDS.add(new SayCommand());
    }

    private final String name;
    private final boolean exists;
    private final MinecraftVersion addedIn;
    private final MinecraftVersion removedIn;

    public Command(String name, boolean exists, MinecraftVersion addedIn, MinecraftVersion removedIn) {
        this.name = name;
        this.exists = exists;
        this.addedIn = addedIn;
        this.removedIn = removedIn;
    }

    public String parse(String data) {
        return data;
    }

    public String compatibility(String data, MinecraftVersion version) {
        return null;
    }

    public String getName() {
        return name;
    }

    public boolean doesExist() {
        return exists;
    }

    public MinecraftVersion getAddedVersion() {
        return addedIn;
    }

    public MinecraftVersion getRemovedVersion() {
        return removedIn;
    }

    public static Command getCommand(String name) {
        for(Command cmd : COMMANDS) {
            if(cmd.getName().equalsIgnoreCase(name)) {
                return cmd;
            }
        }
        return null;
    }

}
