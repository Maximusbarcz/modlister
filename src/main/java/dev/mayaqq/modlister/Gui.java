package dev.mayaqq.modlister;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

class Gui extends JFrame implements ActionListener {
    static JTextField textField;
    static JLabel status;
    static JTextArea log;

    //All the colors used in the GUI
    static Color Background = new Color(40, 42, 54);
    static Color CurrentLine = new Color(68, 71, 90);
    static Color White = new Color(248, 248, 242);
    static Color Purple = new Color(189, 147, 249);

    public static void CreateGui() {

        JFrame frame = new JFrame("Mod Lister");
        JPanel submitRow = new JPanel();
        JPanel buttonRow = new JPanel();
        JPanel infoRow = new JPanel();
        JPanel statusRow = new JPanel();
        JButton submit = new JButton("submit");
        JButton discord = new JButton("Discord");
        JButton github = new JButton("Github");
        JButton openFolder = new JButton("Open Mods Folder");
        JLabel info = new JLabel("Copy the path of a mods folder and paste it into the text field. Then click submit.");

        //Elements used in other code
        log = new JTextArea();
        textField = new JTextField(16);
        status = new JLabel("Enter a Mods Folder Path!");

        JScrollPane scrollPane = new JScrollPane(log);

        Gui gui = new Gui();

        // addActionListener to buttons
        submit.addActionListener(gui);
        discord.addActionListener(gui);
        github.addActionListener(gui);
        openFolder.addActionListener(gui);

        //Sets the close operation to exit on close
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //properties of info label
        info.setForeground(White);
        info.setFont(new Font("Arial", Font.BOLD, 15));

        infoRow.setBackground(Background);
        infoRow.setLayout(new FlowLayout());
        infoRow.add(info);

        //properties of status label
        status.setForeground(Purple);
        status.setPreferredSize(new Dimension(800, 50));
        status.setFont(new Font("Arial", Font.BOLD, 20));
        status.setHorizontalAlignment(SwingConstants.CENTER);

        statusRow.setBackground(Background);
        statusRow.setLayout(new FlowLayout());
        statusRow.add(status);

        //properties of the input field
        textField.setBackground(CurrentLine);
        textField.setForeground(White);
        textField.setBorder(BorderFactory.createBevelBorder( 2, White, White));
        textField.setPreferredSize(new Dimension(800, 50));
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setCaretColor(White);

        //properties of submit button
        submit.setBackground(CurrentLine);
        submit.setForeground(White);
        submit.setBorder(BorderFactory.createBevelBorder( 2, White, White));
        submit.setPreferredSize(new Dimension(100, 50));
        submit.setFont(new Font("Arial", Font.BOLD, 20));

        submitRow.setBackground(Background);
        submitRow.setLayout(new FlowLayout());
        submitRow.add(textField);
        submitRow.add(submit);

        //properties of the discord button
        discord.setBackground(CurrentLine);
        discord.setForeground(White);
        discord.setBorder(BorderFactory.createBevelBorder( 2, White, White));
        discord.setPreferredSize(new Dimension(100, 50));
        discord.setFont(new Font("Arial", Font.BOLD, 20));
        discord.setAlignmentX(Component.CENTER_ALIGNMENT);

        //properties of the github button
        github.setBackground(CurrentLine);
        github.setForeground(White);
        github.setBorder(BorderFactory.createBevelBorder( 2, White, White));
        github.setPreferredSize(new Dimension(100, 50));
        github.setFont(new Font("Arial", Font.BOLD, 20));
        github.setAlignmentX(Component.CENTER_ALIGNMENT);

        //properties of the open folder button
        openFolder.setBackground(CurrentLine);
        openFolder.setForeground(White);
        openFolder.setBorder(BorderFactory.createBevelBorder( 2, White, White));
        openFolder.setPreferredSize(new Dimension(220, 50));
        openFolder.setFont(new Font("Arial", Font.BOLD, 20));
        openFolder.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonRow.setBackground(Background);
        buttonRow.setLayout(new FlowLayout());
        buttonRow.add(discord);
        buttonRow.add(github);
        buttonRow.add(openFolder);

        //log ui
        log.setBackground(CurrentLine);
        log.setForeground(White);
        log.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder( 2, Background, Background), "Log", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 20), White));
        log.setFont(new Font("Arial", Font.PLAIN, 20));
        log.setCaretColor(White);
        log.setEditable(false);

        //scroll pane ui
        scrollPane.setBackground(CurrentLine);
        scrollPane.setBorder(BorderFactory.createBevelBorder(2, Purple, Purple));
        scrollPane.setPreferredSize(new Dimension(20, frame.getHeight() + 243));
        scrollPane.createVerticalScrollBar();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setBackground(Background);


        //register all the elements to the panel and also configure it
        frame.setSize(854, 480);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().setBackground(Background);

        frame.add(infoRow);
        frame.add(submitRow);
        frame.add(statusRow);
        frame.add(buttonRow);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    //log util
    public static void cLog(String text, boolean Error) {
        if (Error) {
            log.append("Error: " + text + "\n");
        } else {
            log.append(text + "\n");
        }
    }

    //makes sure the buttons actually do something
    public void actionPerformed(ActionEvent event) {
        String s = event.getActionCommand();
        switch (s) {
            case "submit":
                String text = textField.getText();
                if (!text.endsWith("mods")) {
                    cLog("Invalid Path Entered", true);
                    status.setForeground(Color.RED);
                    status.setText("Invalid Path Entered");
                } else {
                    status.setForeground(Purple);
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
                    cLog("Opening Discord Invite Link...", false);
                    Desktop.getDesktop().browse(new URI("https://discord.gg/w7PpGax9Bq"));
                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Github":
                try {
                    cLog("Opening Github Link...", false);
                    Desktop.getDesktop().browse(new URI("https://github.com/Maximusbarcz/modlister"));
                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Open Mods Folder":
                try {
                    if (textField.getText().endsWith("mods")) {
                        cLog("Opening Mods Folder...", false);
                        Desktop.getDesktop().open(new File(textField.getText()));
                    } else {
                        cLog("Invalid Path Entered", true);
                        status.setForeground(Color.RED);
                        status.setText("Invalid Path Entered");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
        }
    }
}

