package com.gui.listener;

import com.HolidayUtil;
import com.RentalAgreement;
import com.gui.GuiUtil;
import com.gui.InputPanel;
import com.predicate.validator.CheckOutDateValidator;
import com.predicate.validator.DiscountPercentValidator;
import com.predicate.validator.RentalDayCountValidator;
import com.predicate.validator.ToolCodeValidator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Event Listener for the submit button
 */
public class SubmitButtonListener implements ActionListener {
    private static final Logger logger = Logger.getLogger(
            SubmitButtonListener.class.getPackage().getName() +
                    "." + SubmitButtonListener.class.getName());

    private final JFrame popupFrame;
    private final InputPanel toolCodeInput;
    private final InputPanel rentalDayCountInput;
    private final InputPanel discountPercentInput;
    private final InputPanel checkOutDateInput;

    public SubmitButtonListener(JFrame popupFrame,
                                InputPanel toolCodeInput,
                                InputPanel rentalDayCountInput,
                                InputPanel discountPercentInput,
                                InputPanel checkOutDateInput) {
        this.popupFrame = popupFrame;
        this.toolCodeInput = toolCodeInput;
        this.rentalDayCountInput = rentalDayCountInput;
        this.discountPercentInput = discountPercentInput;
        this.checkOutDateInput = checkOutDateInput;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            if (!toolCodeInput.validateInput()) {
                throw new Exception(ToolCodeValidator.ERROR_TEXT + " See table below for options.");
            }
            if (!rentalDayCountInput.validateInput()) {
                throw new Exception(RentalDayCountValidator.ERROR_TEXT + " Value must over 1 day.");
            }
            if (!discountPercentInput.validateInput()) {
                throw new Exception(DiscountPercentValidator.ERROR_TEXT + " Value must be between 0 and 100.");
            }
            if (!checkOutDateInput.validateInput()) {
                throw new Exception(CheckOutDateValidator.ERROR_TEXT + " Value must be in format " + GuiUtil.Constants.DATE_FORMAT);
            }

            LinkedHashMap<String, Object> rentalAgreementMap = RentalAgreement.generate(toolCodeInput.getRawInput(),
                    Integer.parseInt(rentalDayCountInput.getRawInput()),
                    Integer.parseInt(discountPercentInput.getRawInput()),
                    LocalDate.parse(checkOutDateInput.getRawInput(), DateTimeFormatter.ofPattern(GuiUtil.Constants.DATE_FORMAT))
            );

            String rentalAgreement = RentalAgreement.makeAgreement(rentalAgreementMap);

            logger.log(Level.INFO, rentalAgreement);
            JOptionPane.showMessageDialog(popupFrame, rentalAgreement, "Rental Agreement", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error", e.fillInStackTrace());
            JOptionPane.showMessageDialog(popupFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
