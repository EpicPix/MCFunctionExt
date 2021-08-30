package ga.epicpix.mcfext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Compiler {

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

        for(String line : lines) {
            String cline = compileLine(line, variables);
            if (cline != null) {
                output.add(cline);
            }
        }

        return String.join("\n", output.toArray(new String[0]));
    }

    public static String compileLine(String line, Variables vars) {
        if(line.startsWith("$")) {
            String[] data = line.substring(1).split(" ", 3);
            String name = data[0];
            String operation = data[1];
            String value = data[2];
            if(operation.equals("=")) {
                vars.set(name, value);
            }else {
                System.err.println("[WARNING] Unknown operation: " + operation);
            }
            return null;
        }
        return vars.placeVariables(line);
    }

}
