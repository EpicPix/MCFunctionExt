package ga.epicpix.mcfext;

import ga.epicpix.mcfext.commands.SayCommand;
import java.util.ArrayList;

public abstract class Command {

    private static final ArrayList<Command> COMMANDS = new ArrayList<>();

    public static void init() {
        COMMANDS.add(new SayCommand());
    }

    private final String name;

    public Command(String name, boolean exists, MinecraftVersion addedIn, MinecraftVersion removedIn) {
        this.name = name;
    }

    public String parse(String data) {
        return data;
    }

    public String getName() {
        return name;
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
