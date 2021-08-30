package ga.epicpix.mcfext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
        return null; //TODO
    }

}
