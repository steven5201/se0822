package com.gui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Predicate;

/**
 * Input group for user inputs
 */
public class InputPanel extends InputGroup {

    private static final Font FONT = GuiUtil.Constants.DEFAULT_FONT;
    private static final Dimension LABEL_SIZE = new Dimension(50, 20);
    private static final Dimension TEXT_FIELD_SIZE = new Dimension(250, 20);

    private final JTextField textField;
    private final Predicate validator;

    public InputPanel(String labelText, Predicate<String> validator) {
        super();
        this.validator = validator;

        JLabel label = new JLabel(labelText);
        label.setSize(LABEL_SIZE);
        label.setFont(FONT);
        this.add(label);

        this.textField = new JTextField();
        this.textField.setPreferredSize(TEXT_FIELD_SIZE);
        this.textField.setFont(FONT);
        this.add(this.textField);

        reset();
    }

    /**
     * Run validator for this input
     *
     * @return True if passed validation, False otherwise
     */
    public boolean validateInput() {
        return validator.test(getRawInput());
    }

    /**
     * Get the raw input from user
     *
     * @return User input string
     */
    public String getRawInput() {
        return textField.getText();
    }

    /**
     * Reset this input
     */
    public void reset() {
        this.textField.setText("");
    }
}
