package ga.epicpix.mcfext.datapacks;

import ga.epicpix.mcfext.Resource;
import ga.epicpix.mcfext.ResourceLocation;

public class Function implements Resource {

    private final Namespace namespace;
    private final String name;
    private final String data;

    public Function(Namespace ns, String name, String data) {
        namespace = ns;
        this.name = name;
        this.data = data;
    }

    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(namespace.getName(), name);
    }

    public String getData() {
        return data;
    }

}
