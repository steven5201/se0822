package com.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Class to group together inputs
 */
public class InputGroup extends JPanel {

    /**
     * Default input group
     */
    public InputGroup() {
        this.setLayout(new FlowLayout());
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }

    /**
     * Default input group with these components
     *
     * @param components Components to add to group
     */
    public InputGroup(Component... components) {
        this();
        Arrays.stream(components).forEach(comp -> this.add(comp));
    }
}
