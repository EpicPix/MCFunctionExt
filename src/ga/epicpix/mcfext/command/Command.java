package ga.epicpix.mcfext.command;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ga.epicpix.mcfext.VersionInfo;
import ga.epicpix.mcfext.Variables;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public final class Command {

    private static final ArrayList<Command> COMMANDS = new ArrayList<>();

    public static void init() {
        COMMANDS.addAll(new Gson().fromJson(new InputStreamReader(Command.class.getClassLoader().getResourceAsStream("assets/commands.json")), new TypeToken<ArrayList<Command>>(){}.getType()));
    }

    private String name;
    private VersionInfo version;
    private Object syntax;

    private Object parseObjs(Object syntax, CommandStringIterator data, Variables vars) {
        if(syntax instanceof String) {
            String syn = (String) syntax;
            if(syn.equals("@any")) {
                return data.restW();
            }
        }
        return new CommandError("Not handled syntax");
    }

    public Object parse(CommandStringIterator data, Variables vars) {
        Object objs = parseObjs(syntax, data, vars);

        if(objs==null) return null;
        if(objs instanceof CommandError) return objs;

        if(objs instanceof Object[]) {
            return new CommandData(this, (Object[]) objs);
        }else {
            return new CommandData(this, new Object[] {objs});
        }
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return getName();
    }

    public VersionInfo getVersion() {
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
