package net.runelite.client.plugins.dollycam;

import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.api.events.ConfigChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;

@PluginDescriptor(
    name = "Dolly Camera",
    description = "Cinematic camera shots",
    tags = {"dolly", "cam", "cinematic", "camera", "gpu"}
)
public class DollyCamPlugin extends Plugin {
    @Inject
    private Client client;

    @Inject
    private DollyCamConfig config;

    private boolean state;

    private int yaw,pitch,centerX,centerY,scale,camX,camY,camZ;

    @Provides
    DollyCamConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(DollyCamConfig.class);
    }

    @Override
    protected void startUp() throws Exception
    {

    }

    @Override
    protected void shutDown() throws Exception
    {

    }

    @Subscribe
    public void onConfigChanged(ConfigChanged event)
    {
        if (!event.getGroup().equals("dollycam"))
        {
            return;
        }
        state = config.dollyState();
        if (state) {
            setCoords();
        }
    }

    public void setCoords() {
        yaw = client.getCameraYaw();
        pitch = client.getCameraPitch();
        centerX = client.getCenterX();
        centerY = client.getCenterY();
        scale = client.getScale();
        camX = client.getCameraX2();
        camY = client.getCameraY2();
        camZ = client.getCameraZ2();
    }

    public int getCameraYaw() {
        return state ? yaw : client.getCameraYaw();
    }
    public int getCameraPitch() {
        return state ? pitch : client.getCameraPitch();
    }
    public int getCenterX() {
        return state ? client.getCenterX() : client.getCenterX();
    }
    public int getCenterY() {
        return state ? client.getCenterY() : client.getCenterY();
    }
    public int getScale() {
        return state ? scale : client.getScale();
    }
    public int getCameraX2() {
        return state ? camX : client.getCameraX2();
    }
    public int getCameraY2() {
        return state ? camY : client.getCameraY2();
    }
    public int getCameraZ2() {
        return state ? camZ : client.getCameraZ2();
    }
}
