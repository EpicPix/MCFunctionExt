package ga.epicpix.mcfext;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Start {

    public static void main(String[] args) {
        int ret = run(args);
        if(ret!=0) {
            System.exit(ret);
        }
    }

    public static int run(String[] args) {
        String compile = String.join(" ", args);
        if(compile.isEmpty()) {
            System.err.println("Missing compile path in arguments");
            return 1;
        }
        File file = new File(compile);
        if(!file.exists()) {
            System.err.println("File does not exist");
            return 1;
        }
        if(!file.isDirectory()) {
            System.err.println("File is not a directory");
            return 1;
        }
        ArrayList<File> files = new ArrayList<>();
        files.add(file);
        while(files.size()!=0) {
            File f = files.get(0);
            if(f.isDirectory()) {
                File[] containing = f.listFiles();
                if(containing!=null) {
                    Collections.addAll(files, containing);
                }
            }else {
                // Compiler.compileFile(f);
            }
            files.remove(0);
        }
        return 0;
    }

}
