package dev.mayaqq.modlister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

class Gui extends JFrame implements ActionListener {
    static JTextField textField;
    static JFrame frame;
    static JButton button;
    static JLabel label;

    public static void CreateGui() {
        frame = new JFrame("Mod Lister");
        label = new JLabel("Enter a Mods Folder Path!");
        button = new JButton("submit");
        Gui gui = new Gui();
        // addActionListener to button
        button.addActionListener(gui);

        // create an object of JTextField with 16 columns
        textField = new JTextField(16);

        JPanel panel = new JPanel();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel.add(textField);
        panel.add(button);
        panel.add(label);
        frame.add(panel);
        frame.setSize(300, 100);

        frame.setVisible(true);
    }
    //Submit button press
    public void actionPerformed(ActionEvent event) {
        String s = event.getActionCommand();
        if (s.equals("submit")) {
            String text = textField.getText();
            if (!text.endsWith("mods")) {
                label.setText("Please enter a valid mod folder path!");
            } else {
                label.setText(text);
                try {
                    Modlister.getMods(text);
                    label.setText("Mods Listed! You can now close this window.");
                    Desktop.getDesktop().open(new File(text));
                    frame.dispose();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}

