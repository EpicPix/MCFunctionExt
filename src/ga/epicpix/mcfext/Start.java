package ga.epicpix.mcfext;

import ga.epicpix.mcfext.advancements.Advancement;
import ga.epicpix.mcfext.biomes.Biome;
import ga.epicpix.mcfext.command.Command;
import ga.epicpix.mcfext.datapacks.Datapack;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map.Entry;

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
        if(pack == null) {
            return 1;
        }
        pack.save(compiled);

        if(Command.PRINT_COMMAND_USAGE) {
            System.out.println("-----------------------");
            System.out.println("Printing command usages");
            System.out.println();
            Entry<String, Integer>[] a = Command.commandUsage.entrySet().toArray(new Entry[0]);
            Arrays.sort(a, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
            for(Entry<String, Integer> cmd : a) {
                System.out.println(cmd.getKey() + ": " + cmd.getValue());
            }
            System.out.println("-----------------------");
        }

        int errorc = getErrorCount();
        if(errorc!=0) {
            System.out.println(errorc + " error" + (errorc==1?"":"s"));
            return 1;
        }
        return 0;
    }

}
