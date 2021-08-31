package ga.epicpix.mcfext.commands;

import ga.epicpix.mcfext.Command;
import ga.epicpix.mcfext.CommandStringIterator;
import ga.epicpix.mcfext.MinecraftVersion;
import ga.epicpix.mcfext.ResourceLocation;
import ga.epicpix.mcfext.Variables;

import static ga.epicpix.mcfext.Utils.error;

public class AdvancementCommand extends Command {

    public AdvancementCommand() {
        super("advancement", MinecraftVersion.MC1_12, null);
    }

    public String parse(String commandName, CommandStringIterator data, Variables vars) { //TODO Add null checking
        String mode = data.nextWord();
        if(!mode.equals("grant") && !mode.equals("revoke")) {
            error("Advancement mode not grant/revoke, found " + mode);
            return null;
        }
        String selector = data.nextWord(); //TODO Will not work with spaces
        String type = data.nextWord();

        if(type.equals("everything")) {
            return commandName + " " + mode + " " + selector + " " + type;
        }else {
            ResourceLocation location = new ResourceLocation(data.nextWord());
            if(!location.isValid()) {
                error("Invalid Resource Locator");
                return null;
            }

            if(type.equals("only")) {
                //TODO Add criterion checking
                String criterion = data.nextWord();
                return commandName + " " + mode + " " + selector + " " + type + " " + location.getResourceLocation() + (criterion==null?"":" " + criterion);
            }else if(type.equals("from") || type.equals("through") || type.equals("until")) {
                return commandName + " " + mode + " " + selector + " " + type + " " + location.getResourceLocation();
            }else {
                error("Unknown advancement type found " + type);
                return null;
            }

        }

        //TODO If it's a minecraft resource location then check for the advancement if it exists

    }
}
