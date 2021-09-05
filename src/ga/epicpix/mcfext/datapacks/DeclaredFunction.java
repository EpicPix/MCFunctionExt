package ga.epicpix.mcfext.datapacks;

import ga.epicpix.mcfext.Resource;
import ga.epicpix.mcfext.ResourceLocation;

import java.io.File;

public class DeclaredFunction implements Resource {

    private final Namespace namespace;
    private final String name;
    private final File file;

    public DeclaredFunction(Namespace ns, String name, File file) {
        namespace = ns;
        this.name = name;
        this.file = file;
    }

    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(namespace.getName(), name, "minecraft");
    }

    public File getFile() {
        return file;
    }

    public String toString() {
        return getResourceLocation().toString();
    }
}
