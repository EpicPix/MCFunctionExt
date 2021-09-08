package ga.epicpix.mcfext.methods;

import ga.epicpix.mcfext.command.CommandData;
import ga.epicpix.mcfext.datapacks.Datapack;
import ga.epicpix.mcfext.datapacks.DeclaredFunction;
import ga.epicpix.mcfext.datapacks.Function;

import java.util.ArrayList;

public class Method extends Function {

    private final String name;

    public Method(Datapack pack, DeclaredFunction func, String name, ArrayList<CommandData> commands) {
        super(pack.getNamespace(func.getResourceLocation().getNamespace()), func.getResourceLocation().getLocation() + "-" + name, commands);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
