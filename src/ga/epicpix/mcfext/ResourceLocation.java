package ga.epicpix.mcfext;

import java.util.Objects;

public final class ResourceLocation {

    private final String namespace;
    private final String location;

    public ResourceLocation(String location, String def) {
        if(location.contains(":")) {
            String[] lsplit = location.split(":", 2);
            this.namespace = lsplit[0];
            this.location = lsplit[1];
        }else {
            this.namespace = def;
            this.location = location;
        }
    }

    public ResourceLocation(String location) {
        this(location, "minecraft");
    }

    public ResourceLocation(String namespace, String location, String def) {
        this((namespace != null ? (namespace + ":") : "") + location, def);
    }

    public String getNamespace() {
        return namespace;
    }

    public String getLocation() {
        return location;
    }

    public String getResourceLocation() {
        return getNamespace() + ":" + getLocation();
    }

    public String toString() {
        return getResourceLocation();
    }

    public boolean isValid() {
        if(!namespace.matches("[0-9a-z_\\-./]*")) {
            return false;
        }
        return location.matches("[0-9a-z_\\-.]*");
    }

    public String getName() {
        String[] s = getLocation().split("/");
        return s[s.length-1];
    }
}
