package net.runelite.client.plugins.slayerarea;

import net.runelite.api.coords.WorldPoint;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.slayer.Task;
import net.runelite.client.ui.overlay.worldmap.WorldMapPoint;

import java.awt.image.BufferedImage;
import java.util.List;

public class SlayerArea {
    public String name;
    public List<String> monsters;
    public SlayerArea below;
    public String strongest;

    public BufferedImage getImage(ItemManager itemManager) {
        if (strongest == null || strongest == "") return null;
        Task task = Task.getTask(strongest);
        if (task == null || itemManager == null) return null;

        final BufferedImage image = itemManager.getImage(task.getItemSpriteId());

        if (image == null) return null;
        return image;
    }

    public static int getX(int id) {
        return ((id >> 8) << 6);
    }

    public static int getY(int id) {
        return ((id & 255) << 6);
    }
}


