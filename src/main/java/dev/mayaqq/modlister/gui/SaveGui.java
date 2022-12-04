package dev.mayaqq.modlister.gui;

import javax.swing.*;
import java.io.File;

public class SaveGui {
    public static String SaveGui() {
        JFileChooser chooser = new JFileChooser();
        //some chooser parameters
        chooser.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "/Downloads/"));
        chooser.setSelectedFile(new File("modlist.txt"));
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        chooser.setDialogTitle("Choose Folder to save the modlist");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            return(chooser.getSelectedFile().toString());
        } else {
            return null;
        }
    }
}
