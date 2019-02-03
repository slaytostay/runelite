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
    private static Map<Integer, SlayerArea> areas;

    private static final String AREA_FILE = "AreaMonsterData";

    public static Boolean configLockedShader = true;
    public static Boolean configSlayerIcons = true;
    public static Boolean configLockedMap = true;
    public static Boolean configRegionId = true;
    public static Boolean isUnlocked(int id) {
        return areas.get(id).unlocked;
    }

    public static void readAreas() {
        Type mapType = new TypeToken<Map<Integer, SlayerArea>>(){}.getType();
        try (JsonReader reader = new JsonReader(new FileReader(AREA_FILE + ".json"));) {
            Gson gson = new Gson();
            areas = gson.fromJson(reader, mapType);
        } catch(IOException ex) {
            System.out.println(ex.toString());
        }
        fixBelow();
    }

    public static void fixBelow() {
        for (Map.Entry<Integer, SlayerArea> entry : areas.entrySet()) {
            int id = entry.getKey();
            SlayerArea area = entry.getValue();
            if (area.below.below != null) {
                area.below.below = null;
                areas.replace(id, area);
            }
        }
    }

    public static void backup(String fname) {
        try (Writer writer = new FileWriter(fname + ".json");) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(areas, writer);
        } catch(IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void sortAreas() {
        areas = new TreeMap<>(areas);
    }

    public static void saveAreas() {
        sortAreas();
        backup(AREA_FILE);
    }

    public static void setArea(int id, SlayerArea area) {
        if (area.name.equals("")) {
            areas.remove(id);
            return;
        }
        areas.replace(id, area);
        saveAreas();
    }

    public static void addArea(int id, SlayerArea area) {
        if (areas.containsKey(id)) setArea(id, area);
        areas.put(id, area);
        saveAreas();
    }

    public static void removeArea(int id) {
        if (!areas.containsKey(id)) return;
        areas.remove(id);
        saveAreas();
    }

}
