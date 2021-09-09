package ga.epicpix.mcfext;

import ga.epicpix.mcfext.command.Command;
import ga.epicpix.mcfext.command.CommandData;
import ga.epicpix.mcfext.command.CommandError;
import ga.epicpix.mcfext.command.CommandStringIterator;
import ga.epicpix.mcfext.datapacks.Datapack;
import ga.epicpix.mcfext.datapacks.DeclaredFunction;
import ga.epicpix.mcfext.exceptions.SyntaxNotHandledException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static ga.epicpix.mcfext.Utils.error;
import static ga.epicpix.mcfext.Utils.repeat;
import static ga.epicpix.mcfext.Utils.warn;

public class Compiler {

    public static ArrayList<CommandData> compileFunctionFile(Datapack pack, DeclaredFunction fun, File file) {
        try {
            return compileFunction(pack, fun, Files.readAllLines(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<CommandData> compileFunction(Datapack pack, DeclaredFunction fun, List<String> data) {
        CommandStringIterator iter = new CommandStringIterator(compileMin(data));

        Variables variables = new Variables();
        ArrayList<CommandData> output = new ArrayList<>();

        while(iter.hasNextLine()) {
            CommandData cline = compileLine(pack, fun, iter, variables);
            if (cline != null) {
                output.add(cline);
            }
        }

        return output;
    }

    private static List<String> compileMin(List<String> lines) {
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

    public static CommandData compileLine(Datapack pack, DeclaredFunction fun, CommandStringIterator iter, Variables vars) {
        iter.nextLine();
        String wcmd = iter.nextWord();
        if(wcmd.startsWith("$")) {
            String name = wcmd.substring(1);
            String operation = iter.nextWord();
            String value = vars.placeVariables(iter.removeNextWhitespace().rest());
            if(operation.equals("=")) {
                vars.set(name, value);
            }else {
                warn("Unknown operation: " + operation);
            }
            return null;
        }else if(wcmd.equals("defmethod")) {
            String name = iter.nextWord();
            if(iter.hasNext()) {
                if(!iter.nextWord().equals("{")) {
                    throw new SyntaxNotHandledException("Invalid method creation");
                }
            }else {
                if(!iter.nextLine().nextWord().equals("{")) {
                    throw new SyntaxNotHandledException("Could not create method");
                }
            }
            ArrayList<String> l = new ArrayList<>();
            while(iter.hasNextLine()) {
                String n = iter.nextLine().rest();
                if(n.equals("}")) {
                    break;
                }
                l.add(n);
            }
            ResourceLocation res = fun.getResourceLocation();
            DeclaredFunction func = new DeclaredFunction(pack.getNamespace(res.getNamespace()), res.getLocation() + "-" + name, null);
            ArrayList<CommandData> data = compileFunction(pack, func, l);
            pack.getNamespace(res.getNamespace()).addMethod(fun, name, data);
            return null;
        }

        iter.addLineVariables(vars);

        String cmdName = iter.nextWord();
        Command cmd = Command.getCommand(cmdName);
        if (cmd != null) {
            Object out = cmd.parse(pack, fun, iter, vars);
            if(out==null) {
                error("Command output is null");
                return null;
            }
            if(out instanceof CommandError) {
                error(iter.currentLine() + "\n" + repeat(" ", iter.getPosition()) + "^", out);
                return null;
            }
            return (CommandData) out;
        } else {
            error(iter.currentLine() + "\n^", new CommandError("Unknown command"));
            return null;
        }
    }

}
