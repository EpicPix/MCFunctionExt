package ga.epicpix.mcfext.command;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ga.epicpix.mcfext.MinecraftVersion;
import ga.epicpix.mcfext.Variables;
import ga.epicpix.mcfext.exceptions.NoCompatibilityException;

import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class Command {

    private static final ArrayList<CommandBuilder> COMMANDS;

    static {
        COMMANDS = new Gson().fromJson(new InputStreamReader(Command.class.getClassLoader().getResourceAsStream("assets/commands.json")), new TypeToken<ArrayList<Command>>(){}.getType());
    }

    private String name;
    private CommandVersion version;

    public String parse(String commandName, CommandStringIterator data, Variables vars) {
        return commandName + " " + vars.placeVariables(data.rest());
    }

    public String compatibility(String commandName, CommandStringIterator data, MinecraftVersion version, Variables vars) {
        throw new NoCompatibilityException(commandName);
    }

    public CommandVersion getVersion() {
        return version;
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
