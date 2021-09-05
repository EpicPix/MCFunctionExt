package ga.epicpix.mcfext.command;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ga.epicpix.mcfext.VersionInfo;
import ga.epicpix.mcfext.Variables;
import ga.epicpix.mcfext.exceptions.SyntaxNotHandledException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import static ga.epicpix.mcfext.Utils.debug;

public final class Command {

    private static final ArrayList<Command> COMMANDS = new ArrayList<>();

    public static void init() {
        COMMANDS.addAll(new Gson().fromJson(new InputStreamReader(Command.class.getClassLoader().getResourceAsStream("assets/commands.json")), new TypeToken<ArrayList<Command>>(){}.getType()));
    }

    private String name;
    private VersionInfo version;
    private Object syntax;

    private Object parseObjs(Object syntax, CommandStringIterator data, Variables vars, ArrayList<Object> vals) {
        debug("parseObjs() : " + syntax);
        if(syntax instanceof String) {
            String syn = (String) syntax;
            if(syn.equals("@any")) {
                vals.add(data.restW());
                return vals;
            }else if(syn.equals("@end")) {
                return vals;
            }
            throw new SyntaxNotHandledException("Not handled syntax: " + syn);
        }else if(syntax instanceof Map) {
            Map<String, Object> sMap = (Map<String, Object>) syntax;
            for(Entry<String, Object> entry : sMap.entrySet()) {
                String[] values = entry.getKey().split("\\|");
                for(String val : values) {
                    int pos = data.getPosition();
                    if(val.startsWith("@")) {
                    }else {
                        String v = data.nextWord();
                        if(val.equals(v)) {
                            vals.add(v);
                            return parseObjs(entry.getValue(), data, vars, new ArrayList<>(vals));
                        }
                    }
                    data.setPosition(pos);
                }
            }
            return new CommandError("Could not parse command");
        }else {
            throw new SyntaxNotHandledException("Not handled syntax, unknown syntax type " + syntax.getClass() + "  /  " + syntax);
        }
    }

    public Object parse(CommandStringIterator data, Variables vars) {
        Object objs = parseObjs(syntax, data, vars, new ArrayList<>());

        if(objs==null) return null;
        if(objs instanceof CommandError) return objs;

        if(objs instanceof ArrayList) {
            return new CommandData(this, ((ArrayList<Object>) objs).toArray(new Object[0]));
        }else if(objs instanceof Object[]) {
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
