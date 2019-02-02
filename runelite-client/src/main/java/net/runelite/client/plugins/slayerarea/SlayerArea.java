package net.runelite.client.plugins.slayerarea;

import net.runelite.api.coords.WorldPoint;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.slayer.Task;
import net.runelite.client.ui.overlay.worldmap.WorldMapPoint;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SlayerArea {
    public String name;
    public List<String> monsters;
    public SlayerArea below;
    public String strongest;

    public BufferedImage getImage(ItemManager itemManager) {
        if (strongest == null || strongest.equals("")) return null;
        Task task = Task.getTask(strongest);
        if (task == null || itemManager == null) return null;

        final BufferedImage image = itemManager.getImage(task.getItemSpriteId());

        if (image == null) return null;
        return image;
    }

    public List<String> getValues() {
        List<String> values = new ArrayList<>();
        values.add(name.toLowerCase());
        monsters.forEach(monster -> values.add(monster.toLowerCase()));
        if (below != null && below.name != null && !below.name.equals("")) {
            values.add(below.name.toLowerCase());
            below.monsters.forEach(monster -> values.add(monster.toLowerCase()));
        }
        if (strongest != null) values.add(strongest.toLowerCase());
        return values;
    }

    // Slow (O(n) instead of O(1)) use getX(id) if the id is known
    public int getX() {
        for (Map.Entry<Integer, SlayerArea> item : SlayerAreas.getAreas().entrySet()) {
            if (item.getValue().name.equals(name)) {
                return SlayerArea.getX(item.getKey());
            }
        }
        return -1;
    }

    public int getY() {
        for (Map.Entry<Integer, SlayerArea> item : SlayerAreas.getAreas().entrySet()) {
            if (item.getValue().name.equals(this.name)) {
                return SlayerArea.getY(item.getKey());
            }
        }
        return -1;
    }

    public static int getX(int id) {
        return ((id >> 8) << 6);
    }

    public static int getY(int id) {
        return ((id & 255) << 6);
    }
}


