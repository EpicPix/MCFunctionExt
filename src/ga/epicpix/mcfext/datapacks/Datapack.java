package ga.epicpix.mcfext.datapacks;

import com.google.gson.Gson;
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
    public static final int PACK_FORMAT = 7; // 1.17

    private final ArrayList<Namespace> namespaces = new ArrayList<>();

    public static Datapack readDatapack(File root) throws IOException {
        Datapack pack = new Datapack();
        boolean hasMeta = new File(root, "pack.mcmeta").exists();
        if(hasMeta) {
            JsonObject metaData = new Gson().fromJson(new String(Files.readAllBytes(new File(root, "pack.mcmeta").toPath())), JsonObject.class);
            if(metaData.getAsJsonObject("data").get("pack_format").getAsInt() != PACK_FORMAT) {
                error("Unsupported version");
                return null;
            }
            File data = new File(root, "data");
            if(!data.exists()) {
                error("Data folder doesn't exist");
                return null;
            }
            File[] dataContents = data.listFiles(File::isDirectory);
            if(dataContents == null) {
                error("Could not read data folder");
                return null;
            }
            for(File fileNamespace : dataContents) {
                Namespace ns = new Namespace(fileNamespace.getName());
                pack.namespaces.add(ns);
                ArrayList<File> files = new ArrayList<>();
                File[] rfiles = fileNamespace.listFiles();
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
                            ns.declaredFunctions.add(new DeclaredFunction(ns, f.getPath().split(Pattern.quote(File.separator), 2)[1].split("\\.", 2)[0], f));
                        }

                    }
                    files.remove(0);
                }
            }
            for(Namespace ns : pack.namespaces) {
                for (DeclaredFunction func : ns.declaredFunctions) {
                    ns.addFunction(func.getResourceLocation().getLocation(), Compiler.compileFunctionFile(pack, func, func.getFile()));
                }
            }
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
                        unknown.declaredFunctions.add(new DeclaredFunction(unknown, f.getPath().split(Pattern.quote(File.separator), 2)[1].split("\\.", 2)[0], f));
                    }

                }
                files.remove(0);
            }
            for(DeclaredFunction func : unknown.declaredFunctions) {
                unknown.addFunction(func.getResourceLocation().getLocation(), Compiler.compileFunctionFile(pack, func, func.getFile()));
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
            data.addProperty("pack_format", PACK_FORMAT);
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

    public Namespace getNamespace(String namespace) {
        for(Namespace n : namespaces) {
            if(n.getName().equals(namespace)) {
                return n;
            }
        }
        return null;
    }
}
