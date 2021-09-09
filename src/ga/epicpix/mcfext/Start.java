package ga.epicpix.mcfext;

import ga.epicpix.mcfext.advancements.Advancement;
import ga.epicpix.mcfext.biomes.Biome;
import ga.epicpix.mcfext.command.Command;
import ga.epicpix.mcfext.datapacks.Datapack;
import java.io.File;
import java.io.IOException;

import static ga.epicpix.mcfext.Utils.error;
import static ga.epicpix.mcfext.Utils.getErrorCount;

public class Start {

    public static void main(String[] args) throws Exception {
        Advancement.init();
        Biome.init();
        Command.init();
        Effect.init();
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

        Datapack pack = Datapack.readDatapack(file);
        pack.save(compiled);

        int errorc = getErrorCount();
        if(errorc!=0) {
            System.out.println(errorc + " error" + (errorc==1?"":"s"));
            return 1;
        }
        return 0;
    }

}
