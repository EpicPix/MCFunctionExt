package ga.epicpix.mcfext.datapacks;

import ga.epicpix.mcfext.command.CommandData;
import ga.epicpix.mcfext.methods.Method;

import java.util.ArrayList;

public class Namespace {

    public final ArrayList<DeclaredFunction> declaredFunctions = new ArrayList<>();

    private final ArrayList<Function> functions = new ArrayList<>();

    private final String name;

    public Namespace(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addFunction(String name, ArrayList<CommandData> commands) {
        functions.add(new Function(this, name, commands));
    }

    public ArrayList<Function> getFunctions() {
        return functions;
    }

    public DeclaredFunction getDeclaredFunction(String name) {
        for(DeclaredFunction f : declaredFunctions) {
            if(f.getResourceLocation().getLocation().equals(name)) {
                return f;
            }
        }
        return null;
    }
}
