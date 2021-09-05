package ga.epicpix.mcfext;

import ga.epicpix.mcfext.command.Command;
import ga.epicpix.mcfext.command.CommandData;
import ga.epicpix.mcfext.command.CommandError;
import ga.epicpix.mcfext.command.CommandStringIterator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static ga.epicpix.mcfext.Utils.error;
import static ga.epicpix.mcfext.Utils.warn;

public class Compiler {

    public static final MinecraftVersion COMPILE_TO = MinecraftVersion.MC1_17;

    public static ArrayList<CommandData> compileFunctionFile(File file) {
        try {
            return compileFunction(Files.readAllLines(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<CommandData> compileFunction(List<String> data) {
        List<String> lines = compile0(data);

        Variables variables = new Variables();
        ArrayList<CommandData> output = new ArrayList<>();
        Iterator<String> lineIterator = lines.iterator();

        while(lineIterator.hasNext()) {
            CommandData cline = compileLine(new CommandStringIterator(lineIterator.next()), lineIterator, variables);
            if (cline != null) {
                output.add(cline);
            }
        }

        return output;
    }

    private static List<String> compile0(List<String> lines) {
        ArrayList<String> array = new ArrayList<>(lines);
        array.removeIf(a -> a.trim().isEmpty() || a.trim().startsWith("#"));
        ArrayList<String> out = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for(String str : array) {
            str = str.trim();
            if(str.endsWith("\\")) {
                temp.append(str.substring(0, str.length() - 1).trim());
            }else if(temp.length() > 0) {
                out.add(temp + str);
                temp = new StringBuilder();
            }else {
                out.add(str);
            }
        }
        return out;
    }

    public static CommandData compileLine(CommandStringIterator line, Iterator<String> lines, Variables vars) {
        String wcmd = line.nextWord();
        if(wcmd.startsWith("$")) {
            String name = wcmd.substring(1);
            String operation = line.nextWord();
            String value = vars.placeVariables(line.removeNextWhitespace().rest());
            if(operation.equals("=")) {
                vars.set(name, value);
            }else {
                warn("Unknown operation: " + operation);
            }
            return null;
        }
        CommandStringIterator iter = new CommandStringIterator(vars.placeVariables(line.reset().removeNextWhitespace().rest()));

        String cmdName = iter.nextWord();
        Command cmd = Command.getCommand(cmdName);
        if (cmd != null) {
            VersionInfo version = cmd.getVersion();
            boolean accessible = version.getRemovedVersion() == null || (version.getAddedVersion().getId() < COMPILE_TO.getId() && version.getRemovedVersion().getId() > COMPILE_TO.getId());
            if (accessible) {
                Object out = cmd.parse(iter, vars);
                if(out==null) {
                    error("Command output is null");
                    return null;
                }
                if(out instanceof CommandError) {
                    error(out);
                    return null;
                }
                return (CommandData) out;
            }else {
                error("Command not available for this version: " + cmdName);
                return null;
            }
        } else {
            error("Unknown command: " + cmdName + " | " + line.reset().rest());
            return null;
        }
    }

}
