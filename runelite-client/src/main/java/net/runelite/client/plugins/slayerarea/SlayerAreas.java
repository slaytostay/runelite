package net.runelite.client.plugins.slayerarea;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.Getter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

public class SlayerAreas {

    @Getter
    private static Map<String, SlayerArea> areas;

    private static final String AREA_FILE = "AreaMonsterData.json";

    public static void readAreas() {
        Type mapType = new TypeToken<Map<String, SlayerArea>>(){}.getType();
        try (JsonReader reader = new JsonReader(new FileReader(AREA_FILE));) {
            Gson gson = new Gson();
            areas = gson.fromJson(reader, mapType);
        } catch(IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void backup(String fname) {
        try (Writer writer = new FileWriter(fname);) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(areas, writer);
        } catch(IOException ex) {
            System.out.println(ex.toString());
        }
    }


    public static void setArea(int id, SlayerArea area) {
        String key = Integer.toString(id);
        if (area.name.equals("")) {
            areas.remove(key);
            return;
        }
        areas.replace(key, area);
        areas = new TreeMap<>(areas);
    }

    public static void addArea(int id, SlayerArea area) {
        String key = Integer.toString(id);
        areas.put(key, area);
        areas = new TreeMap<>(areas);
    }

}
