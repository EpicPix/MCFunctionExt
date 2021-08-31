package ga.epicpix.mcfext;

import ga.epicpix.mcfext.commands.*;
import ga.epicpix.mcfext.exceptions.NoCompatibilityException;
import java.util.ArrayList;

public abstract class Command {

    private static final ArrayList<Command> COMMANDS = new ArrayList<>();

    public static void init() {
        COMMANDS.add(new AdvancementCommand());
        COMMANDS.add(new BlockDataCommand());
        COMMANDS.add(new BlockedCommands());
        COMMANDS.add(new DefaultGamemodeCommand());
        COMMANDS.add(new ReplaceItemCommand());
        COMMANDS.add(new SayCommand());
    }

    private final String name;
    private final MinecraftVersion addedIn;
    private final MinecraftVersion removedIn;
    private final String[] aliases;

    public Command(String name, MinecraftVersion addedIn, MinecraftVersion removedIn, String... aliases) {
        this.name = name;
        this.addedIn = addedIn;
        this.removedIn = removedIn;
        this.aliases = aliases;
    }

    public String parse(String commandName, CommandStringIterator data, Variables vars) {
        return commandName + " " + vars.placeVariables(data.rest());
    }

    public String compatibility(String commandName, CommandStringIterator data, MinecraftVersion version, Variables vars) {
        throw new NoCompatibilityException(commandName);
    }

    public String getName() {
        if(name==null) return aliases[0];
        return name;
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
