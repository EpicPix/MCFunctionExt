package ga.epicpix.mcfext.datapacks;

import ga.epicpix.mcfext.Resource;
import ga.epicpix.mcfext.ResourceLocation;

import java.io.File;
import java.util.List;

public class DeclaredFunction implements Resource {

    private final Namespace namespace;
    private final String name;
    private final File file;
    private final boolean vanillaMode;

    public List<String> data;

    public DeclaredFunction(Namespace ns, String name, File file, boolean vanillaMode) {
        namespace = ns;
        this.name = name;
        this.file = file;
        this.vanillaMode = vanillaMode;
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

    public boolean isVanillaMode() {
        return vanillaMode;
    }
}
