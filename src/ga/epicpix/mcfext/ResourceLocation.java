package ga.epicpix.mcfext;

public final class ResourceLocation {

    private final String namespace;
    private final String location;

    public ResourceLocation(String location) {
        if(location.contains(":")) {
            String[] lsplit = location.split(":", 2);
            this.namespace = lsplit[0];
            this.location = lsplit[1];
        }else {
            this.namespace = "minecraft";
            this.location = location;
        }
    }

    public ResourceLocation(String namespace, String location) {
        this.namespace = namespace;
        this.location = location;
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

    public boolean isValid() {
        if(!namespace.matches("[0-9a-z_\\-./]*")) {
            return false;
        }
        return location.matches("[0-9a-z_\\-.]*");
    }
}
