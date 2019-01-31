package net.runelite.client.plugins.slayerarea;

import net.runelite.client.config.ConfigManager;
import net.runelite.client.input.MouseManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.slayerarea.ui.SlayerAreaPluginPanel;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import java.awt.image.BufferedImage;

@PluginDescriptor(
    name = "Slayer Areas",
    description = "Save slayer monster region locations.",
    tags = {"slayer", "region", "panel"}
)

public class SlayerAreaPlugin extends Plugin {

    private static final String PLUGIN_NAME = "Slayer Areas";
    private static final String CONFIG_GROUP = "slayerarea";
    private static final String CONFIG_KEY = "slayerarea";
    private static final String ICON_FILE = "panel_icon.png";

    @Inject
    private ConfigManager configManager;

    @Inject
    private ClientToolbar clientToolbar;

    private SlayerAreaPluginPanel pluginPanel;
    private NavigationButton navigationButton;

    @Override
    protected void startUp() throws Exception
    {
        pluginPanel = injector.getInstance(SlayerAreaPluginPanel.class);
        pluginPanel.rebuild();

        final BufferedImage icon = ImageUtil.getResourceStreamFromClass(getClass(), ICON_FILE);

        navigationButton = NavigationButton.builder()
                .tooltip(PLUGIN_NAME)
                .icon(icon)
                .priority(5)
                .panel(pluginPanel)
                .build();

        clientToolbar.addNavigation(navigationButton);
    }

    @Override
    protected void shutDown() throws Exception
    {
        clientToolbar.removeNavigation(navigationButton);

        pluginPanel = null;
        navigationButton = null;
    }
}
