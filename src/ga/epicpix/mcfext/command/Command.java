package ga.epicpix.mcfext.command;

import ga.epicpix.mcfext.MinecraftVersion;
import ga.epicpix.mcfext.Variables;
import ga.epicpix.mcfext.command.impl.AdvancementCommand;
import ga.epicpix.mcfext.command.impl.BlockDataCommand;
import ga.epicpix.mcfext.command.impl.BlockedCommands;
import ga.epicpix.mcfext.command.impl.DefaultGamemodeCommand;
import ga.epicpix.mcfext.command.impl.FunctionCommand;
import ga.epicpix.mcfext.command.impl.ReplaceItemCommand;
import ga.epicpix.mcfext.command.impl.SayCommand;
import ga.epicpix.mcfext.exceptions.NoCompatibilityException;
import java.util.ArrayList;

public abstract class Command {

    private static final ArrayList<CommandBuilder> COMMANDS = new ArrayList<>();

    public static void init() {
        COMMANDS.add(new CommandBuilder(AdvancementCommand.class));
        COMMANDS.add(new CommandBuilder(BlockDataCommand.class));
        COMMANDS.add(new CommandBuilder(BlockedCommands.class));
        COMMANDS.add(new CommandBuilder(DefaultGamemodeCommand.class));
        COMMANDS.add(new CommandBuilder(FunctionCommand.class));
        COMMANDS.add(new CommandBuilder(ReplaceItemCommand.class));
        COMMANDS.add(new CommandBuilder(SayCommand.class));
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

    public static class CommandBuilder {

        private final Class<? extends Command> type;

        CommandBuilder(Class<? extends Command> type) {
            this.type = type;
        }

        public Command create() {
            try {
                return type.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {}
            return null;
        }

    }

    public static Command getCommand(String name) {
//        for(CommandBuilder cmd : COMMANDS) {
//            if(cmd.getName().equalsIgnoreCase(name)) {
//                return cmd;
//            }
//        }
        return null;
    }

}
