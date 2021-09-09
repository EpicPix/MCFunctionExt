package ga.epicpix.mcfext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ga.epicpix.mcfext.command.Command;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Effect implements Resource {

    public static final ArrayList<Effect> EFFECTS = new ArrayList<>();

    private String id;

    public static void init() {
        EFFECTS.addAll(new Gson().fromJson(new InputStreamReader(Command.class.getClassLoader().getResourceAsStream("assets/effects.json")), new TypeToken<ArrayList<Effect>>(){}.getType()));
    }

    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(id);
    }

    public static Effect getEffect(ResourceLocation location) {
        for(Effect eff : EFFECTS) {
            if(eff.getResourceLocation().getResourceLocation().equals(location.getResourceLocation())) {
                return eff;
            }
        }
        return null;
    }
}
