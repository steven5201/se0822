package com.gui;

import com.PointOfSaleDemo;
import com.gui.listener.ResetButtonListener;
import com.gui.listener.SubmitButtonListener;
import com.predicate.validator.CheckOutDateValidator;
import com.predicate.validator.DiscountPercentValidator;
import com.predicate.validator.RentalDayCountValidator;
import com.predicate.validator.ToolCodeValidator;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final String APP_TITLE_TEXT = "Point of Sale Demo";
    private static final String TITLE_LABEL_TEXT = "Dome Depot";
    private static final int TITLE_SIZE = 30;
    private static final String[] TOOL_TABLE_TITLE = new String[]{
            "Tool Code",
            "Tool Type",
            "Brand"
    };

    public MainFrame() {
        setTitle(APP_TITLE_TEXT);
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));

        mainContainer.add(createTitle());
        mainContainer.add(createEditorPanel());
        mainContainer.add(createToolTable());

        setVisible(true);
    }

    /**
     * Create the title label
     *
     * @return The title label
     */
    private JLabel createTitle() {
        JLabel titleLabel = new JLabel(TITLE_LABEL_TEXT);

        titleLabel.setPreferredSize(new Dimension(getWidth(), getHeight() / 4));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setFont(GuiUtil.createFont(TITLE_SIZE));

        return titleLabel;
    }

    /**
     * Create tool information table
     *
     * @return The tool table
     */
    private JScrollPane createToolTable() {
        Object[][] toolsDisplay = new Object[PointOfSaleDemo.TOOLS.length][];

        for (int i = 0; i < toolsDisplay.length; i++) {
            toolsDisplay[i] = PointOfSaleDemo.TOOLS[i].getTableMapForGUI();
        }

        JTable toolTable = new JTable(toolsDisplay, TOOL_TABLE_TITLE);
        toolTable.setFont(GuiUtil.Constants.DEFAULT_FONT);
        toolTable.getTableHeader().setFont(GuiUtil.Constants.DEFAULT_FONT);
        toolTable.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(toolTable);
        scrollPane.setPreferredSize(new Dimension(400, GuiUtil.Constants.DEFAULT_FONT.getSize() * (PointOfSaleDemo.TOOLS.length + 1)));

        return scrollPane;
    }

    /**
     * Create the editor panel
     * This panel contains all the user inputs
     *
     * @return The editor panel
     */
    private JPanel createEditorPanel() {
        JPanel editorPanel = new JPanel();
        GroupLayout editorPaneLayout = new GroupLayout(editorPanel);

        editorPanel.setAlignmentX(CENTER_ALIGNMENT);
        editorPanel.setAlignmentY(CENTER_ALIGNMENT);
        editorPanel.setLayout(editorPaneLayout);
        editorPaneLayout.setAutoCreateGaps(true);

        // Inputs
        InputPanel toolCodeInput = new InputPanel("Tool Code", new ToolCodeValidator());
        InputPanel rentalDayCountInput = new InputPanel("Rental Day Count", new RentalDayCountValidator());
        InputPanel discountPercentInput = new InputPanel("Discount Percent", new DiscountPercentValidator());
        InputPanel checkOutDateInput = new InputPanel("Check Out Date (mm/dd/yy)", new CheckOutDateValidator());

        // Buttons
        JButton submit = new JButton("Submit");
        submit.setFont(GuiUtil.Constants.DEFAULT_FONT);
        submit.addActionListener(new SubmitButtonListener(
                this,
                toolCodeInput,
                rentalDayCountInput,
                discountPercentInput,
                checkOutDateInput
        ));

        JButton reset = new JButton("Reset");
        reset.setFont(GuiUtil.Constants.DEFAULT_FONT);
        reset.addActionListener(new ResetButtonListener(new InputPanel[]{
                toolCodeInput,
                rentalDayCountInput,
                discountPercentInput,
                checkOutDateInput
        }));

        InputGroup submitResetButtons = new InputGroup(submit, reset);

        // Set the layout information
        editorPaneLayout.setHorizontalGroup(
                editorPaneLayout.createParallelGroup()
                        .addComponent(toolCodeInput)
                        .addComponent(rentalDayCountInput)
                        .addComponent(discountPercentInput)
                        .addComponent(checkOutDateInput)
                        .addComponent(submitResetButtons)
        );
        editorPaneLayout.setVerticalGroup(
                editorPaneLayout.createSequentialGroup()
                        .addGroup(editorPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE))
                        .addComponent(toolCodeInput)
                        .addComponent(rentalDayCountInput)
                        .addComponent(discountPercentInput)
                        .addComponent(checkOutDateInput)
                        .addComponent(submitResetButtons)
        );

        return editorPanel;
    }
}
