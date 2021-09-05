package ga.epicpix.mcfext.biomes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ga.epicpix.mcfext.Resource;
import ga.epicpix.mcfext.ResourceLocation;
import ga.epicpix.mcfext.command.Command;

import java.io.InputStreamReader;
import java.util.ArrayList;

public class Biome implements Resource {

    public static final ArrayList<Biome> BIOMES = new ArrayList<>();

    public static void init() {
        BIOMES.addAll(new Gson().fromJson(new InputStreamReader(Command.class.getClassLoader().getResourceAsStream("assets/biomes.json")), new TypeToken<ArrayList<Biome>>(){}.getType()));
    }

    private String id;
    private BiomeType type;

    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(id);
    }

    public BiomeType getType() {
        return type;
    }
}
