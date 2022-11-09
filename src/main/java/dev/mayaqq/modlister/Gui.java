package dev.mayaqq.modlister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

class Gui extends JFrame implements ActionListener {
    static JTextField textField;
    static JFrame frame;
    static JButton submit;
    static JLabel status;
    static JLabel info;
    static JButton discord;
    static JButton github;

    public static void CreateGui() {

        frame = new JFrame("Mod Lister");
        status = new JLabel("Enter a Mods Folder Path!");
        submit = new JButton("submit");
        discord = new JButton("Discord");
        github = new JButton("Github");

        Gui gui = new Gui();
        JPanel panel = new JPanel();

        // addActionListener to buttons
        submit.addActionListener(gui);
        discord.addActionListener(gui);
        github.addActionListener(gui);

        // create an object of JTextField with 16 columns
        textField = new JTextField(16);
        info = new JLabel("Copy the path of a mods folder and paste it into the text field. Then click submit.");

        //All the colors used in the GUI
        Color Background = new Color(40, 42, 54);
        Color CurrentLine = new Color(68, 71, 90);
        Color White = new Color(248, 248, 242);
        Color Purple = new Color(189, 147, 249);

        //Sets the close operation to exit on close
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //properties of info label
        info.setForeground(White);
        info.setFont(new Font("Arial", Font.BOLD, 15));
        info.setHorizontalAlignment(SwingConstants.CENTER);

        //properties of status label
        status.setForeground(Purple);
        status.setPreferredSize(new Dimension(800, 50));
        status.setFont(new Font("Arial", Font.BOLD, 20));
        status.setHorizontalAlignment(SwingConstants.CENTER);

        //properties of submit button
        submit.setBackground(CurrentLine);
        submit.setForeground(White);
        submit.setBorder(BorderFactory.createBevelBorder( 2, White, White));
        submit.setPreferredSize(new Dimension(100, 50));
        submit.setFont(new Font("Arial", Font.BOLD, 20));

        //properties of the imput field
        textField.setBackground(CurrentLine);
        textField.setForeground(White);
        textField.setBorder(BorderFactory.createBevelBorder( 2, White, White));
        textField.setPreferredSize(new Dimension(800, 50));
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setCaretColor(White);

        //properties of the discord button
        discord.setBackground(CurrentLine);
        discord.setForeground(White);
        discord.setBorder(BorderFactory.createBevelBorder( 2, White, White));
        discord.setPreferredSize(new Dimension(100, 50));
        discord.setFont(new Font("Arial", Font.BOLD, 20));
        discord.setHorizontalAlignment(SwingConstants.CENTER);

        //properties of the github button
        github.setBackground(CurrentLine);
        github.setForeground(White);
        github.setBorder(BorderFactory.createBevelBorder( 2, White, White));
        github.setPreferredSize(new Dimension(100, 50));
        github.setFont(new Font("Arial", Font.BOLD, 20));
        github.setHorizontalAlignment(SwingConstants.CENTER);

        //register all the elements to the panel and also configure it
        frame.setSize(854, 480);
        panel.setBackground(Background);
        panel.add(info);
        panel.add(textField);
        panel.add(submit);
        panel.add(status);
        panel.add(discord);
        panel.add(github);
        frame.add(panel);

        frame.setVisible(true);
    }
    //makes sure the buttons actually do something
    public void actionPerformed(ActionEvent event) {
        String s = event.getActionCommand();
        switch (s) {
            case "submit":
                String text = textField.getText();
                if (!text.endsWith("mods")) {
                    status.setText("Please enter a valid mods folder path!");
                } else {
                    status.setText(text);
                    try {
                        Modlister.getMods(text);
                        status.setText("Mods Listed! You can now close this window.");
                        Desktop.getDesktop().open(new File(text));
                        frame.dispose();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case "Discord":
                try {
                    Desktop.getDesktop().browse(new URI("https://discord.gg/w7PpGax9Bq"));
                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Github":
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/Maximusbarcz/modlister"));
                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
                break;
        }
    }
}

