package net.runelite.client.plugins.slayerarea.ui;

import net.runelite.client.plugins.slayerarea.SlayerArea;
import net.runelite.client.plugins.slayerarea.SlayerAreas;
import net.runelite.client.ui.ColorScheme;
import org.apache.commons.text.similarity.JaroWinklerDistance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;

public class AreaPanelItem extends JPanel {
    private static final JaroWinklerDistance DISTANCE = new JaroWinklerDistance();
    private GridBagConstraints gbc;

    public int id;
    public SlayerArea area;


    public AreaPanelItem(int id, SlayerArea area) {
        this.id = id;
        this.area = area;

        gbc = buildConstraints();

        setLayout(new GridBagLayout());
        setBackground(ColorScheme.DARKER_GRAY_COLOR);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new JLabel("Name ("+id+")"), gbc);
        gbc.gridy++;
        add(buildAreaName(), gbc);
        gbc.gridy++;

        add(new JLabel("Monsters"), gbc);
        gbc.gridy++;
        add(buildAreaMonsters(), gbc);
        gbc.gridy++;

        if (area.below != null && area.below.name != null && !area.below.name.equals("") && area.below.monsters != null) {
            add(new JLabel("Below Name"), gbc);
            gbc.gridy++;
            add(buildBelowName(), gbc);
            gbc.gridy++;

            add(new JLabel("Below Monsters"), gbc);
            gbc.gridy++;
            add(buildBelowMonsters(), gbc);
            gbc.gridy++;
        }

        add(new JLabel("Strongest"), gbc);
        gbc.gridy++;
        add(buildAreaStrongest(), gbc);
        gbc.gridy++;
    }

    public static GridBagConstraints buildConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        return constraints;
    }

    public JTextField buildAreaName() {
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

    public JTextArea buildAreaMonsters() {
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

    public JTextField buildBelowName() {
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

    public JTextArea buildBelowMonsters() {
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

    public JTextField buildAreaStrongest() {
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

    boolean matchesSearchTerms(String[] searchTerms)
    {
        for (String term : searchTerms)
        {
            if (Integer.toString(id).contains(term)) return true;
            if (area.getValues().stream().noneMatch((t) -> t.contains(term) ||
                    DISTANCE.apply(t, term) > 0.9))
            {
                return false;
            }
        }
        return true;
    }
}
