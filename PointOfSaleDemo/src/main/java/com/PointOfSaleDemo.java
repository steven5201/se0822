package com;

import com.gui.MainFrame;

public class PointOfSaleDemo {

    public static Tool[] TOOLS = new Tool[]{
            new Tool("CHNS", ToolType.Chainsaw, "Stihl"),
            new Tool("LADW", ToolType.Ladder, "Werner"),
            new Tool("JAKD", ToolType.Jackhammer, "DeWalt"),
            new Tool("JAKR", ToolType.Jackhammer, "Ridgid")
    };

    public static void main(String[] args) throws Exception {
        MainFrame mainFrame = new MainFrame();
    }
}
