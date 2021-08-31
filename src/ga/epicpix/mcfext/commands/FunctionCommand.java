package ga.epicpix.mcfext.commands;

import ga.epicpix.mcfext.Command;
import ga.epicpix.mcfext.CommandStringIterator;
import ga.epicpix.mcfext.MinecraftVersion;
import ga.epicpix.mcfext.ResourceLocation;
import ga.epicpix.mcfext.Variables;

import static ga.epicpix.mcfext.Utils.error;

public class FunctionCommand extends Command {

    public FunctionCommand() {
        super("function", MinecraftVersion.MC1_12, null);
    }

    public String parse(String commandName, CommandStringIterator data, Variables vars) {
        ResourceLocation loc = new ResourceLocation(data.nextWord());
        if(!loc.isValid()) {
            error("Function Resource Locator is not valid");
            return null;
        }
        return commandName + " " + loc.getResourceLocation();
    }
}
