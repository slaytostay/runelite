package net.runelite.client.plugins.slayerarea;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.Getter;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class SlayerAreas {

    @Getter
    private Map<String, SlayerArea> areas;

    private static final String AREA_FILE = "AreaMonsterData.json";

    public SlayerAreas() {
        ReadAreas();
    }

    public void ReadAreas() {
        Type mapType = new TypeToken<Map<String, SlayerArea>>(){}.getType();
        try (JsonReader reader = new JsonReader(new FileReader(AREA_FILE));) {
            Gson gson = new Gson();
            areas = gson.fromJson(reader, mapType);
        } catch(IOException ex) {
            System.out.println (ex.toString());
        }
    }

}
