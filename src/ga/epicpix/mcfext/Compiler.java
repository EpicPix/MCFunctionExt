package ga.epicpix.mcfext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static ga.epicpix.mcfext.Utils.warn;

public class Compiler {

    public static final MinecraftVersion COMPILE_TO = MinecraftVersion.MC1_17;

    public static String compileFile(File file) {
        try {
            return compile(new String(Files.readAllBytes(file.toPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String compile(String data) {
        List<String> lines = new ArrayList<>();
        Collections.addAll(lines, data.split("\n"));
        lines = compile0(lines);

        Variables variables = new Variables();

        ArrayList<String> output = new ArrayList<>();

        Iterator<String> lineIterator = lines.iterator();

        while(lineIterator.hasNext()) {
            String cline = compileLine(new CommandStringIterator(lineIterator.next()), lineIterator, variables);
            if (cline != null) {
                output.add(cline);
            }
        }

        return String.join("\n", output.toArray(new String[0]));
    }

    private static List<String> compile0(List<String> lines) {
        ArrayList<String> array = new ArrayList<>(lines);
        array.removeIf(String::isEmpty);
        array.removeIf(a -> a.startsWith("#"));
        ArrayList<String> out = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for(String str : array) {
            if(str.endsWith("\\")) {
                temp.append(str.substring(0, str.length() - 1).trim());
            }else if(temp.length() > 0) {
                out.add(temp + str.trim());
                temp = new StringBuilder();
            }else {
                out.add(str);
            }
        }
        return out;
    }

    public static String compileLine(CommandStringIterator line, Iterator<String> lines, Variables vars) {
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
        String runCmd = vars.placeVariables(wcmd);
        CommandStringIterator iter = new CommandStringIterator(runCmd + " " + line.removeNextWhitespace().rest());
        runCmd = iter.nextWord();
        Command cmd = Command.getCommand(runCmd);
        if(cmd!=null) {
            boolean accessible = cmd.getRemovedVersion() == null || (cmd.getAddedVersion().getId()<COMPILE_TO.getId() && cmd.getRemovedVersion().getId()>COMPILE_TO.getId());
            if(!accessible) {
                return cmd.compatibility(runCmd, iter, COMPILE_TO, vars);
            }
            return cmd.parse(runCmd, iter, vars);
        }else {
            warn("Unknown command: " + runCmd);
        }
        String rest = iter.removeNextWhitespace().rest();
        return runCmd + (rest==null ? "" : " " + rest);
    }

}
