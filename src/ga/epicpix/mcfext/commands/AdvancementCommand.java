package ga.epicpix.mcfext.commands;

import ga.epicpix.mcfext.Advancement;
import ga.epicpix.mcfext.Command;
import ga.epicpix.mcfext.CommandStringIterator;
import ga.epicpix.mcfext.AbstractCriterion;
import ga.epicpix.mcfext.MinecraftVersion;
import ga.epicpix.mcfext.ResourceLocation;
import ga.epicpix.mcfext.Selector;
import ga.epicpix.mcfext.Variables;

import static ga.epicpix.mcfext.Utils.error;
import static ga.epicpix.mcfext.Utils.warn;

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
        Selector selector = data.nextSelector();
        String type = data.nextWord();

        if(type.equals("everything")) {
            return commandName + " " + mode + " " + selector + " " + type;
        }else {
            ResourceLocation location = data.nextResourceLocation();
            if(!location.isValid()) {
                error("Invalid Resource Location");
                return null;
            }
            Advancement advancement = Advancement.getAdvancement(location);
            if(advancement == null && location.getNamespace().equals("minecraft")) {
                warn("Unknown advancement: " + location); //TODO Will be error after adding all advancements
            }

            if(type.equals("only")) {
                String criterionName = data.nextWord();
                if(advancement != null) {
                    AbstractCriterion criterion = advancement.getCriterion(criterionName);
                    if(criterion == null) {
                        error("Unknown criterion: " + criterionName);
                        return null;
                    }else {
                        criterionName = criterion.getNames()[0];
                    }
                }
                return commandName + " " + mode + " " + selector + " " + type + " " + location + (criterionName == null ? "" : " " + criterionName);
            }else if(type.equals("from") || type.equals("through") || type.equals("until")) {
                return commandName + " " + mode + " " + selector + " " + type + " " + location;
            }else {
                error("Unknown advancement type found " + type);
                return null;
            }

        }
    }
}
