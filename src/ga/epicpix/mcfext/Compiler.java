package ga.epicpix.mcfext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
        lines.removeIf(String::isEmpty);
        lines.removeIf(a -> a.startsWith("#"));

        Variables variables = new Variables();

        ArrayList<String> output = new ArrayList<>();

        Iterator<String> lineIterator = lines.iterator();

        while(lineIterator.hasNext()) {
            String cline = compileLine(lineIterator.next(), lineIterator, variables);
            if (cline != null) {
                output.add(cline);
            }
        }

        return String.join("\n", output.toArray(new String[0]));
    }

    public static String compileLine(String line, Iterator<String> lines, Variables vars) {
        if(line.startsWith("$")) {
            String[] data = line.substring(1).split(" ", 3);
            String name = data[0];
            String operation = data[1];
            String value = vars.placeVariables(data[2]);
            if(operation.equals("=")) {
                vars.set(name, value);
            }else {
                System.err.println("[WARNING] Unknown operation: " + operation);
            }
            return null;
        }
        String placedLine = vars.placeVariables(line);
        String[] split = placedLine.split(" ", 2);
        Command cmd = Command.getCommand(split[0]);
        if(cmd!=null) {
            boolean accessible = cmd.getRemovedVersion() == null || (cmd.getAddedVersion().getId()<COMPILE_TO.getId() && cmd.getRemovedVersion().getId()>COMPILE_TO.getId());
            String args = split.length > 1 ? split[1] : null;
            if(!accessible) {
                return cmd.compatibility(args, COMPILE_TO);
            }
            return cmd.parse(args);
        }
        return placedLine;
    }

}
