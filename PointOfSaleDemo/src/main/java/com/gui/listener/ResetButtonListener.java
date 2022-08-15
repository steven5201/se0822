package com.gui.listener;

import com.gui.InputPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Event listener for the reset button
 */
public class ResetButtonListener implements ActionListener {

    private final InputPanel[] panels;

    /**
     * Listener for the reset button
     *
     * @param panels InputPanels that should be reset
     */
    public ResetButtonListener(InputPanel[] panels) {
        this.panels = panels;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Arrays.stream(panels).forEach(panel -> panel.reset());
    }
}
