package net.runelite.client.plugins.slayerarea.ui;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.inject.Inject;
import net.runelite.client.plugins.slayerarea.SlayerArea;
import net.runelite.client.plugins.slayerarea.SlayerAreaPlugin;
import net.runelite.client.plugins.slayerarea.SlayerAreas;
import net.runelite.client.plugins.worldmap.WorldMapPlugin;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.FlatTextField;
import net.runelite.client.util.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class SlayerAreaPluginPanel extends PluginPanel {

    @Inject
    private SlayerAreaPlugin plugin;

    public void init() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(ColorScheme.DARK_GRAY_COLOR);

        JPanel markerView = new JPanel(new GridBagLayout());
        markerView.setBackground(ColorScheme.DARK_GRAY_COLOR);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;

        SlayerAreas slayerAreas = new SlayerAreas();
        Map<String, SlayerArea> areas = slayerAreas.getAreas();

        for (Map.Entry<String, SlayerArea> entry : areas.entrySet()) {
            int id = Integer.parseInt(entry.getKey());
            SlayerArea area = entry.getValue();


            markerView.add(new JLabel(area.name), constraints);
            constraints.gridy++;

            JTextArea nameInput = new JTextArea();
            nameInput.setLineWrap(true);
            nameInput.setWrapStyleWord(true);
            nameInput.setText(String.join(", ", area.monsters));
            nameInput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            markerView.add(nameInput, constraints);

            markerView.add(new JLabel(area.strongest), constraints);
            constraints.gridy++;

            markerView.add(Box.createRigidArea(new Dimension(0, 10)), constraints);
            constraints.gridy++;

        }

        markerView.add(new JLabel("xd"), constraints);
        constraints.gridy++;

        markerView.add(Box.createRigidArea(new Dimension(0, 10)), constraints);
        constraints.gridy++;

        centerPanel.add(markerView, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void rebuild() {
        removeAll();
        repaint();
        revalidate();
        init();
    }
}
