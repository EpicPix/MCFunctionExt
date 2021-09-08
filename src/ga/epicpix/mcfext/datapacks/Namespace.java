package ga.epicpix.mcfext.datapacks;

import ga.epicpix.mcfext.command.CommandData;
import ga.epicpix.mcfext.methods.Method;

import java.util.ArrayList;

public class Namespace {

    public final ArrayList<DeclaredFunction> declaredFunctions = new ArrayList<>();

    private final ArrayList<Function> functions = new ArrayList<>();

    private final String name;
    private final Datapack datapack;

    public Namespace(Datapack pack, String name) {
        this.name = name;
        datapack = pack;
    }

    public String getName() {
        return name;
    }

    public void addFunction(String name, ArrayList<CommandData> commands) {
        functions.add(new Function(this, name, commands));
    }

    public void addMethod(DeclaredFunction fun, String name, ArrayList<CommandData> commands) {
        functions.add(new Method(datapack, fun, name, commands));
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
