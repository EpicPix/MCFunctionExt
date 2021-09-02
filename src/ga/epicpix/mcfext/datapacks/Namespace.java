package ga.epicpix.mcfext.datapacks;

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

    public void addFunction(String name, String data) {
        functions.add(new Function(this, name, data));
    }

    public ArrayList<Function> getFunctions() {
        return functions;
    }
}
