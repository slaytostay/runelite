package net.runelite.client.plugins.dollycam;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("dollycam")
public interface DollyCamConfig extends Config {
    @ConfigItem(
            keyName = "dollyState",
            name = "Dolly state",
            description = "Turn dolly on or off",
            position = 0
    )
    default boolean dollyState()
    {
        return false;
    }
}
