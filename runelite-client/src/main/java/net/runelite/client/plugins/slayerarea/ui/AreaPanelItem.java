package net.runelite.client.plugins.slayerarea.ui;

import net.runelite.client.plugins.slayerarea.SlayerArea;
import net.runelite.client.plugins.slayerarea.SlayerAreas;
import net.runelite.client.ui.ColorScheme;
import org.apache.commons.text.similarity.JaroWinklerDistance;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class AreaPanelItem extends JPanel {
    private static final JaroWinklerDistance DISTANCE = new JaroWinklerDistance();
    protected GridBagConstraints gbc;

    public int id;
    public SlayerArea area;
    protected SlayerArea oldArea;

    public AreaPanelItem(int id, SlayerArea area) {
        this.id = id;
        this.area = new SlayerArea(area);
        this.oldArea = new SlayerArea(area);

        setLayout(new GridBagLayout());
        setBackground(ColorScheme.DARKER_GRAY_COLOR);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        buildPanel();
    }

    static GridBagConstraints buildConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        return constraints;
    }

    public void childPanel() {
        addButtons();
    }

    private void buildPanel() {
        gbc = buildConstraints();

        addElement("ID", Integer.toString(id), (field) -> {
            try {
                id = Integer.parseInt(field.getText());
            } catch (NumberFormatException n) {
                id = -1;
            }
        });

        addElement("Name", area.name, (field) -> {
            area.name = field.getText();
        });

        addElement("Monsters", area.monsters, (field) -> {
            if (field.getText().equals("")) {
                area.monsters = new ArrayList<>();
                return;
            }
            area.monsters = Arrays.asList(field.getText().split(", "));
        });

        if (area.below == null) area.below = new SlayerArea(true);
        if (area.below.name == null) area.below.name = "";
        if (area.below.monsters == null) area.below.monsters = new ArrayList<>();

        addElement("Below Name", area.below.name, (field) -> {
            area.below.name = field.getText();
        });

        addElement("Below Monsters", area.below.monsters, (field) -> {
            if (field.getText().equals("")) {
                area.below.monsters = new ArrayList<>();
                return;
            };
            area.below.monsters = Arrays.asList(field.getText().split(", "));
        });

        if (area.strongest == null) area.strongest = "";

        addElement("Strongest", area.strongest, (field) -> {
            area.strongest = field.getText();
        });

        childPanel();
    }

    private void addElement(String name, Object e, AreaTextRunnable r) {
        final JTextComponent field = buildText(e);
        if (field == null) return;
        field.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        field.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent e)
            {
                r.run(field);
            }
        });

        addLabel(name);
        add(field, gbc);
        gbc.gridy++;
    }

    private void addLabel(String name) {
        JLabel label = new JLabel(name);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(label, gbc);
        gbc.gridy++;
    }

    private void addButtons() {
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            area = new SlayerArea(oldArea);
            rebuild();
        });
        add(resetButton, gbc);
        gbc.gridy++;

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            SlayerAreas.removeArea(id);
            if (id < 0) {
                rebuild();
                return;
            }
            oldArea = new SlayerArea(area);
            SlayerAreas.addArea(id, area);
        });
        add(saveButton, gbc);
    }

    private JTextComponent buildText(Object e) {
        JTextComponent text = null;
        if (e instanceof String) {
            text = buildTextField((String)e);
        } else if (e instanceof ArrayList<?>) {
            text = buildTextArea((ArrayList<String>)e);
        }
        return text;
    }

    private JTextField buildTextField(String s) {
        JTextField textField = new JTextField();
        if (s.equals("-1")) s = "";
        textField.setText(s);
        return textField;
    }

    private JTextArea buildTextArea(ArrayList<String> s) {
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText(String.join(", ", s));
        return textArea;
    }

    protected void rebuild() {
        removeAll();
        revalidate();
        repaint();
        buildPanel();
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
