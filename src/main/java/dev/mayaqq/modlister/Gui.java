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
    static JButton openFolder;
    static JTextArea log;
    static JScrollPane scrollPane;

    //All the colors used in the GUI
    static Color Background = new Color(40, 42, 54);
    static Color CurrentLine = new Color(68, 71, 90);
    static Color White = new Color(248, 248, 242);
    static Color Purple = new Color(189, 147, 249);

    public static void CreateGui() {

        frame = new JFrame("Mod Lister");
        status = new JLabel("Enter a Mods Folder Path!");
        submit = new JButton("submit");
        discord = new JButton("Discord");
        github = new JButton("Github");
        openFolder = new JButton("Open Mods Folder \uD83D\uDCC2");
        log = new JTextArea();
        scrollPane = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        Gui gui = new Gui();
        JPanel panel = new JPanel();

        // addActionListener to buttons
        submit.addActionListener(gui);
        discord.addActionListener(gui);
        github.addActionListener(gui);
        openFolder.addActionListener(gui);

        // create an object of JTextField with 16 columns
        textField = new JTextField(16);
        info = new JLabel("Copy the path of a mods folder and paste it into the text field. Then click submit.");

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

        //properties of the input field
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

        //properties of the open folder button

        openFolder.setBackground(CurrentLine);
        openFolder.setForeground(White);
        openFolder.setBorder(BorderFactory.createBevelBorder( 2, White, White));
        openFolder.setPreferredSize(new Dimension(250, 50));
        openFolder.setFont(new Font("Arial", Font.BOLD, 20));
        openFolder.setHorizontalAlignment(SwingConstants.CENTER);

        //log ui
        log.setBackground(CurrentLine);
        log.setForeground(White);
        log.setBorder(BorderFactory.createBevelBorder( 2, White, White));
        log.setPreferredSize(new Dimension(800, 243));
        log.setFont(new Font("Arial", Font.PLAIN, 20));
        log.setCaretColor(White);
        log.setEditable(false);

        //scroll pane ui
        scrollPane.setBackground(CurrentLine);
        scrollPane.setForeground(Purple);
        scrollPane.setBorder(BorderFactory.createBevelBorder( 2, White, White));
        scrollPane.setPreferredSize(new Dimension(20, 243));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.createVerticalScrollBar();

        //register all the elements to the panel and also configure it
        frame.setSize(854, 480);
        panel.setBackground(Background);
        panel.add(info);
        panel.add(textField);
        panel.add(submit);
        panel.add(status);
        panel.add(discord);
        panel.add(github);
        panel.add(openFolder);
        panel.add(log);
        panel.add(scrollPane);
        frame.add(panel);

        frame.setVisible(true);
    }

    //This method is called when the submit button is clicked and the Path is not valid
    public static void NotValidPathError() {
        cLog("Error! Invalid Path Entered");
        status.setText("Error! Please enter a valid path!");
        status.setForeground(Color.RED);
    }
    //log
    public static void cLog(String text) {
        log.append(text + "\n");
    }

    //makes sure the buttons actually do something
    public void actionPerformed(ActionEvent event) {
        String s = event.getActionCommand();
        switch (s) {
            case "submit":
                String text = textField.getText();
                if (!text.endsWith("mods")) {
                    NotValidPathError();
                } else {
                    status.setText(text);
                    try {
                        Modlister.getMods(text);
                        status.setText("Mods Listed! You can now close this window.");
                        status.setForeground(Purple);
                        Desktop.getDesktop().open(new File(text));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case "Discord":
                try {
                    cLog("Opening Discord Invite Link...");
                    Desktop.getDesktop().browse(new URI("https://discord.gg/w7PpGax9Bq"));
                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Github":
                try {
                    cLog("Opening Github Link...");
                    Desktop.getDesktop().browse(new URI("https://github.com/Maximusbarcz/modlister"));
                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Open Mods Folder \uD83D\uDCC2":
                try {
                    if (textField.getText().endsWith("mods")) {
                        cLog("Opening Mods Folder...");
                        Desktop.getDesktop().open(new File(textField.getText()));
                    } else {
                        NotValidPathError();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
        }
    }
}

