package ga.epicpix.mcfext.datapacks;

import ga.epicpix.mcfext.Resource;
import ga.epicpix.mcfext.ResourceLocation;
import ga.epicpix.mcfext.command.Command;
import java.util.ArrayList;

public class Function implements Resource {

    private final Namespace namespace;
    private final String name;

    private final ArrayList<Command> commands = new ArrayList<>();

    public Function(Namespace ns, String name, ArrayList<Command> commands) {
        namespace = ns;
        this.name = name;
        this.commands.addAll(commands);
    }

    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(namespace.getName(), name);
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

}
