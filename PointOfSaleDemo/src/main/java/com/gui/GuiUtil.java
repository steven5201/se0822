package com.gui;

import java.awt.*;

/**
 * A utility class for GUI operations
 */
public class GuiUtil {
    public static class Constants {
        public static final String FONT_NAME = "Arial";
        public static final int FONT_STYLE = Font.PLAIN;
        public static final Font DEFAULT_FONT = createFont(20);
        public static final String DATE_FORMAT = "MM/dd/yy";
    }

    /**
     * Creates a font using the default font and style
     *
     * @param fontSize The size of the font
     * @return A new Font
     */
    public static Font createFont(int fontSize) {
        return new Font(Constants.FONT_NAME, Constants.FONT_STYLE, fontSize);
    }
}
