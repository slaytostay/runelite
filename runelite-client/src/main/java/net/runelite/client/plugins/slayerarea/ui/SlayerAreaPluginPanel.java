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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class SlayerAreaPluginPanel extends PluginPanel {

    @Inject
    private SlayerAreaPlugin plugin;

    private final List<JPanel> areaPanelList = new ArrayList<>();

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

        buildAreaList();
        for (JPanel areaPanel : areaPanelList) {
            markerView.add(areaPanel, constraints);
            constraints.gridy++;

            markerView.add(Box.createRigidArea(new Dimension(0, 10)), constraints);
            constraints.gridy++;

        }

        centerPanel.add(markerView, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void buildAreaList() {
        Map<String, SlayerArea> areas = SlayerAreas.getAreas();
        for (Map.Entry<String, SlayerArea> entry : areas.entrySet()) {
            int id = Integer.parseInt(entry.getKey());
            SlayerArea area = entry.getValue();
            areaPanelList.add(buildAreaPanel(id, area));
        }
    }

    public JPanel buildAreaPanel(int id, SlayerArea area) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;

        JPanel areaPanel = new JPanel(new GridBagLayout());
        areaPanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        areaPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        areaPanel.add(new JLabel("Name ("+id+")"), constraints);
        constraints.gridy++;
        areaPanel.add(buildAreaName(id, area), constraints);
        constraints.gridy++;

        areaPanel.add(new JLabel("Monsters"), constraints);
        constraints.gridy++;
        areaPanel.add(buildAreaMonsters(id, area), constraints);
        constraints.gridy++;

        if (area.below != null && area.below.name != "" && area.below.monsters != null) {
            areaPanel.add(new JLabel("Below Name"), constraints);
            constraints.gridy++;
            areaPanel.add(buildBelowName(id, area), constraints);
            constraints.gridy++;

            areaPanel.add(new JLabel("Below Monsters"), constraints);
            constraints.gridy++;
            areaPanel.add(buildBelowMonsters(id, area), constraints);
            constraints.gridy++;
        }

        areaPanel.add(new JLabel("Strongest"), constraints);
        constraints.gridy++;
        areaPanel.add(buildAreaStrongest(id, area), constraints);
        constraints.gridy++;

        return areaPanel;
    }

    public JTextField buildAreaName(int id, SlayerArea area) {
        JTextField nameField = new JTextField();
        nameField.setText(area.name);
        nameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        nameField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent e)
            {
                area.name = nameField.getText();
                SlayerAreas.setArea(id, area);
            }
        });
        return nameField;
    }

    public JTextArea buildAreaMonsters(int id, SlayerArea area) {
        JTextArea monstersField = new JTextArea();
        monstersField.setLineWrap(true);
        monstersField.setWrapStyleWord(true);
        monstersField.setText(String.join(", ", area.monsters));
        monstersField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        monstersField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent e)
            {
                area.monsters = Arrays.asList(monstersField.getText().split(", "));
                SlayerAreas.setArea(id, area);
            }
        });
        return monstersField;
    }

    public JTextField buildBelowName(int id, SlayerArea area) {
        JTextField nameField = new JTextField();
        nameField.setText(area.below.name);
        nameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        nameField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent e)
            {
                area.below.name = nameField.getText();
                SlayerAreas.setArea(id, area);
            }
        });
        return nameField;
    }

    public JTextArea buildBelowMonsters(int id, SlayerArea area) {
        JTextArea monstersField = new JTextArea();
        monstersField.setLineWrap(true);
        monstersField.setWrapStyleWord(true);
        monstersField.setText(String.join(", ", area.below.monsters));
        monstersField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        monstersField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent e)
            {
                area.below.monsters = Arrays.asList(monstersField.getText().split(", "));
                SlayerAreas.setArea(id, area);
            }
        });
        return monstersField;
    }

    public JTextField buildAreaStrongest(int id, SlayerArea area) {
        JTextField strongestField = new JTextField();
        strongestField.setText(area.strongest);
        strongestField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        strongestField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent e)
            {
                area.strongest = strongestField.getText();
                SlayerAreas.setArea(id, area);
            }
        });
        return strongestField;
    }

    public void rebuild() {
        removeAll();
        repaint();
        revalidate();
        init();
    }
}
