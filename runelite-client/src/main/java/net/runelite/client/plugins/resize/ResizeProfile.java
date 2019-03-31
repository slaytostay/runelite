package net.runelite.client.plugins.resize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResizeProfile {
    private long id;
    private String name;
    private Dimension position;
    private Dimension size;
    private boolean visible;
}
