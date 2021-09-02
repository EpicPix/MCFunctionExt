package ga.epicpix.mcfext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;

import static ga.epicpix.mcfext.Utils.error;
import static ga.epicpix.mcfext.Utils.getErrorCount;

public class Start {

    public static void main(String[] args) throws Exception {
        Advancement.init();
        Command.init();
        int ret = run(args);
        if(ret!=0) {
            System.exit(ret);
        }
    }

    public static void deleteFolder(File folder) {
        File[] contents = folder.listFiles();
        if(contents != null) {
            for(File file : contents) {
                if(file.isDirectory()) {
                    deleteFolder(file);
                }
                file.delete();
            }
        }
        folder.delete();
    }

    public static int run(String[] args) throws IOException {
        String compile = String.join(" ", args);
        if(compile.isEmpty()) {
            error("Missing compile path in arguments");
            return 1;
        }
        File file = new File(compile);
        if(!file.exists()) {
            error("File does not exist");
            return 1;
        }
        if(!file.isDirectory()) {
            error("File is not a directory");
            return 1;
        }

        File compiled = new File("compiled");
        if(compiled.exists()) {
            deleteFolder(compiled);
        }
        if(!compiled.mkdir()) {
            error("Failed to create compiled folder");
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
                File out = new File(compiled, f.getPath()
                        .split("/", 2)[1]
                );
                String data;
                if(f.getName().endsWith(".emcfun")) {
                    out = new File(compiled, f.getPath()
                            .split("/", 2)[1]
                            .split("\\.", 2)[0] + ".mcfunction"
                    );
                    data = Compiler.compileFile(f);
                }else {
                    data = new String(Files.readAllBytes(f.toPath()));
                }
                out.getParentFile().mkdirs();
                if (data != null) {
                    Files.write(out.toPath(), data.getBytes());
                } else {
                    error(out.getPath() + " could not be saved");
                }
            }
            files.remove(0);
        }
        int errorc = getErrorCount();
        if(errorc!=0) {
            System.out.println(errorc + " error" + (errorc==1?"":"s"));
            return 1;
        }
        return 0;
    }

}
