package ga.epicpix.mcfext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;

public class Start {

    public static void main(String[] args) throws Exception {
        Command.init();
        int ret = run(args);
        if(ret!=0) {
            System.exit(ret);
        }
    }

    public static int run(String[] args) throws IOException {
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

        File compiled = new File("compiled");
        if(compiled.exists()) {
            ArrayList<File> files = new ArrayList<>();
            files.add(compiled);
            while(files.size()!=0) {
                File f = files.get(0);
                files.remove(0);
                if(f.isDirectory()) {
                    File[] containing = f.listFiles();
                    if(containing!=null && containing.length!=0) {
                        Collections.addAll(files, containing);
                        files.add(f);
                    }else {
                        if(!f.delete()) {
                            System.err.println("Failed to delete compiled folder");
                            return 1;
                        }
                    }
                }else {
                    if(!f.delete()) {
                        System.err.println("Failed to delete compiled folder");
                        return 1;
                    }
                }
            }
        }
        if(!compiled.mkdir()) {
            System.err.println("Failed to create compiled folder");
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
                String data = Compiler.compileFile(f);
                File out = new File(compiled, f.getPath()
                        .split("/", 2)[1]
                );;
                if(f.getName().endsWith(".emcfun")) {
                    out = new File(compiled, f.getPath()
                            .split("/", 2)[1]
                            .split("\\.", 2)[0] + ".mcfunction"
                    );
                }
                if (out.getParentFile().mkdirs()) {
                    System.err.println("Failed to create folders");
                    return 1;
                }
                if (data != null) {
                    Files.write(out.toPath(), data.getBytes());
                } else {
                    System.err.println(out.getPath() + " could not be saved");
                }
            }
            files.remove(0);
        }
        return 0;
    }

}
