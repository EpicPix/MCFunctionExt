package ga.epicpix.mcfext.datapacks;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ga.epicpix.mcfext.Compiler;
import ga.epicpix.mcfext.command.CommandData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

import static ga.epicpix.mcfext.Utils.error;
import static ga.epicpix.mcfext.Utils.info;

public class Datapack {

    private String description = "Unknown";
    private int pack_format = Compiler.COMPILE_TO.getPackFormat();

    private final ArrayList<Namespace> namespaces = new ArrayList<>();

    public static Datapack readDatapack(File root) throws IOException {
        Datapack pack = new Datapack();
        boolean hasMeta = new File(root, "pack.mcmeta").exists();
        if(hasMeta) {
            // TODO
        }else {
            Namespace unknown = new Namespace("unknown");
            pack.namespaces.add(unknown);
            ArrayList<File> files = new ArrayList<>();
            File[] rfiles = root.listFiles();
            if(rfiles == null) {
                return null;
            }
            Collections.addAll(files, rfiles);
            while(files.size()!=0) {
                File f = files.get(0);
                if(f.isDirectory()) {
                    File[] containing = f.listFiles();
                    if(containing!=null) {
                        Collections.addAll(files, containing);
                    }
                }else {
                    if(f.getName().endsWith(".emcfun")) {
                        unknown.addFunction(f.getPath().split(Pattern.quote(File.separator), 2)[1].split("\\.", 2)[0], Compiler.compileFunctionFile(f));
                    }

                }
                files.remove(0);
            }
        }
        return pack;
    }

    public void save(File where) throws IOException {
        if(!where.mkdir()) {
            error("Failed to create output folder");
            return;
        }

        {
            JsonObject meta = new JsonObject();
            JsonObject data = new JsonObject();
            data.addProperty("description", description);
            data.addProperty("pack_format", pack_format);
            meta.add("data", data);
            Files.write(new File(where, "pack.mcmeta").toPath(), new GsonBuilder().setPrettyPrinting().create().toJson(meta).getBytes());
        }

        File data = new File(where, "data");

        for(Namespace namespace : namespaces) {
            File ns = new File(data, namespace.getName());
            ArrayList<Function> functions = namespace.getFunctions();

            if(functions.size() != 0) {
                File fs = new File(ns, "functions");
                
                for(Function function : functions) {
                    File out = new File(fs, function.getResourceLocation().getLocation() + ".mcfunction");
                    out.getParentFile().mkdirs();
                    StringBuilder funcOut = new StringBuilder();
                    for(CommandData cmdData : function.getCommands()) {
                        funcOut.append(cmdData.toString()).append("\n");
                    }
                    Files.write(out.toPath(), funcOut.toString().getBytes());
                }
            }
        }


    }

}
