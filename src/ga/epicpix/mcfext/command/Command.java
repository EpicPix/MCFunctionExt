package ga.epicpix.mcfext.command;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import ga.epicpix.mcfext.MinecraftVersion;
import ga.epicpix.mcfext.Variables;
import ga.epicpix.mcfext.exceptions.NoCompatibilityException;

import java.io.InputStreamReader;
import java.util.ArrayList;

public class Command {

    private static final ArrayList<Command> COMMANDS = new ArrayList<>();

    public static void init() {
        COMMANDS.addAll(new Gson().fromJson(new InputStreamReader(Command.class.getClassLoader().getResourceAsStream("assets/commands.json")), new TypeToken<ArrayList<Command>>(){}.getType()));
    }

    private String name;
    private CommandVersion version;
    private JsonObject syntax;

    public final CommandData parse(String commandName, CommandStringIterator data, Variables vars) {
        return new CommandData(this, new Object[] {data.removeNextWhitespace().rest()});
    }

    public String toString() {
        return name;
    }

    public CommandVersion getVersion() {
        return version;
    }

    public static Command getCommand(String name) {
        for(Command cmd : COMMANDS) {
            if(cmd.name.equals(name)) {
                return cmd;
            }
        }
        return null;
    }

}
