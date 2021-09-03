package ga.epicpix.mcfext.datapacks;

import ga.epicpix.mcfext.command.Command;
import java.util.ArrayList;

public class Namespace {

    private final ArrayList<Function> functions = new ArrayList<>();

    private final String name;

    public Namespace(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addFunction(String name, ArrayList<Command> commands) {
        functions.add(new Function(this, name, commands));
    }

    public ArrayList<Function> getFunctions() {
        return functions;
    }
}
