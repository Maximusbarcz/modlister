package dev.mayaqq.modlister;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
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
    static JScrollPane scrollPane;

    //All the colors used in the GUI
    static Color Background = new Color(40, 42, 54);
    static Color CurrentLine = new Color(68, 71, 90);
    static Color White = new Color(248, 248, 242);
    static Color Purple = new Color(189, 147, 249);
    static Color Comment = new Color(98, 114, 164);

    public static void CreateGui() {

        JFrame frame = new JFrame("Mod Lister");
        JPanel submitRow = new JPanel();
        JPanel buttonRow = new JPanel();
        JPanel infoRow = new JPanel();
        JPanel statusRow = new JPanel();
        JPanel logRow = new JPanel();
        JFileChooser fileChooser = new JFileChooser();
        JButton chooseFolder = new JButton("\uD83D\uDCC1");
        JButton submit = new JButton("submit");
        JButton discord = new JButton("Discord");
        JButton github = new JButton("Github");
        JButton openFolder = new JButton("Open Mods Folder");
        JLabel info = new JLabel("Copy the path of a mods folder and paste it into the text field. Then click submit.");

        //Elements used in other code
        log = new JTextArea();
        textField = new JTextField(16);
        status = new JLabel("Enter a Mods Folder Path!");
        scrollPane = new JScrollPane(log);

        //Some misc stuff that is required for the GUI to work and to also look pretty but does not fit in any category I already made
        UIManager.put("Button.select", Purple);
        Gui gui = new Gui();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/icon.png"));

        //properties of info label
        info.setForeground(White);
        info.setFont(new Font("Arial", Font.BOLD, 15));

        //properties of status label
        status.setForeground(Purple);
        status.setPreferredSize(new Dimension(800, 50));
        status.setFont(new Font("Arial", Font.BOLD, 20));
        status.setHorizontalAlignment(SwingConstants.CENTER);

        //properties of the input field
        textField.setBackground(CurrentLine);
        textField.setForeground(White);
        textField.setBorder(BorderFactory.createBevelBorder( 1, Purple, Purple));
        textField.setPreferredSize(new Dimension(800, 50));
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setCaretColor(White);

        //properties of the choose button
        chooseFolder.addActionListener(gui);
        chooseFolder.setBackground(CurrentLine);
        chooseFolder.setForeground(White);
        chooseFolder.setBorder(BorderFactory.createBevelBorder( 1, Purple, Purple));
        chooseFolder.setPreferredSize(new Dimension(50, 50));
        chooseFolder.setFont(new Font("Arial", Font.BOLD, 20));
        chooseFolder.setFocusPainted(false);

        //properties file chooser
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Choose a Mods Folder");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setBackground(Background);
        fileChooser.setForeground(White);
        fileChooser.setFont(new Font("Arial", Font.PLAIN, 20));

        //properties of submit button
        submit.addActionListener(gui);
        submit.setBackground(CurrentLine);
        submit.setForeground(White);
        submit.setBorder(BorderFactory.createBevelBorder( 1, Purple, Purple));
        submit.setPreferredSize(new Dimension(100, 50));
        submit.setFont(new Font("Arial", Font.BOLD, 20));
        submit.setFocusPainted(false);

        //properties of the discord button
        discord.addActionListener(gui);
        discord.setBackground(CurrentLine);
        discord.setForeground(White);
        discord.setBorder(BorderFactory.createBevelBorder( 1, Purple, Purple));
        discord.setPreferredSize(new Dimension(100, 50));
        discord.setFont(new Font("Arial", Font.BOLD, 20));
        discord.setAlignmentX(Component.CENTER_ALIGNMENT);
        discord.setFocusPainted(false);

        //properties of the github button
        github.addActionListener(gui);
        github.setBackground(CurrentLine);
        github.setForeground(White);
        github.setBorder(BorderFactory.createBevelBorder( 1, Purple, Purple));
        github.setPreferredSize(new Dimension(100, 50));
        github.setFont(new Font("Arial", Font.BOLD, 20));
        github.setAlignmentX(Component.CENTER_ALIGNMENT);
        github.setFocusPainted(false);

        //properties of the open folder button
        openFolder.addActionListener(gui);
        openFolder.setBackground(CurrentLine);
        openFolder.setForeground(White);
        openFolder.setBorder(BorderFactory.createBevelBorder( 1, Purple, Purple));
        openFolder.setPreferredSize(new Dimension(220, 50));
        openFolder.setFont(new Font("Arial", Font.BOLD, 20));
        openFolder.setAlignmentX(Component.CENTER_ALIGNMENT);
        openFolder.setFocusPainted(false);

        //log ui
        log.setBackground(CurrentLine);
        log.setForeground(White);
        log.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder( 2, Background, Background), "Log", TitledBorder.LEFT, TitledBorder.LEFT, new Font("Arial", Font.BOLD, 20), Purple));
        log.setFont(new Font("Arial", Font.PLAIN, 20));
        log.setCaretColor(White);
        log.setEditable(false);

        //scroll pane ui
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        JScrollBar horizontal = scrollPane.getHorizontalScrollBar();
        scrollPane.setBackground(CurrentLine);
        scrollPane.setBorder(BorderFactory.createBevelBorder(1, Purple, Purple));
        scrollPane.createVerticalScrollBar();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setAutoscrolls(true);
        horizontal.setPreferredSize(new Dimension(0, 10));
        horizontal.setBackground(CurrentLine);
        vertical.setSize(new Dimension(10, 0));
        vertical.setBackground(CurrentLine);
        vertical.setUI(new BasicScrollBarUI() {
            protected void configureScrollBarColors() {
                this.thumbColor = Comment;
                this.thumbRect = new Rectangle(0, 0, 0, 0);
            }
        });
        horizontal.setUI(new BasicScrollBarUI() {
            protected void configureScrollBarColors() {
                this.thumbColor = Comment;
                this.thumbRect = new Rectangle(0, 0, 0, 0);
            }
        });

        //Register Rows
        infoRow.setBackground(Background);
        infoRow.setLayout(new FlowLayout());
        infoRow.add(info);

        submitRow.setBackground(Background);
        submitRow.setLayout(new FlowLayout());
        submitRow.add(chooseFolder);
        submitRow.add(textField);
        submitRow.add(submit);

        statusRow.setBackground(Background);
        statusRow.setLayout(new FlowLayout());
        statusRow.add(status);

        buttonRow.setBackground(Background);
        buttonRow.setLayout(new FlowLayout());
        buttonRow.add(discord);
        buttonRow.add(github);
        buttonRow.add(openFolder);

        logRow.setBackground(Background);
        logRow.setLayout(new CardLayout());
        logRow.add(scrollPane);

        //register all the elements to the panel and also configure it
        frame.setSize(854, 480);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().setBackground(Background);

        frame.add(infoRow);
        frame.add(submitRow);
        frame.add(statusRow);
        frame.add(buttonRow);
        frame.add(logRow);

        frame.setVisible(true);
    }

    //log util
    public static void cLog(String text, Integer ErrorType) {
        switch (ErrorType) {
            case 0 ->
                //Normal output
                    log.append(text + "\n");
            case 1 ->
                //Default Error output
                    log.append("Error: " + text + "\n");
            case 2 ->
                log.append(text);
        }
        //auto scrolls to the bottom of the log
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
    }

    //makes sure the buttons actually do something
    public void actionPerformed(ActionEvent event) {
        String s = event.getActionCommand();
        String text = textField.getText();
        switch (s) {
            case "submit":
                if (!text.endsWith("mods")) {
                    if (text.equals("")) {
                        cLog("Invalid Path Entered - null", 1);
                    } else {
                        cLog("Invalid Path Entered - " + text, 1);
                    }
                    status.setForeground(Color.RED);
                    status.setText("Invalid Path Entered");
                } else {
                    status.setForeground(Purple);
                    status.setText(text);
                    try {
                        Modlister.getMods(text);
                        status.setText("Mods Listed! You can now close this window.");
                        status.setForeground(Color.GREEN);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case "Discord":
                try {
                    cLog("Opening Discord Invite Link...", 0);
                    Desktop.getDesktop().browse(new URI("https://discord.gg/w7PpGax9Bq"));
                } catch (IOException | URISyntaxException ex) {
                    cLog(ex.toString(), 1);
                    throw new RuntimeException(ex);
                }
                break;
            case "Github":
                try {
                    cLog("Opening Github Link...", 0);
                    Desktop.getDesktop().browse(new URI("https://github.com/Maximusbarcz/modlister"));
                } catch (IOException | URISyntaxException ex) {
                    cLog(ex.toString(), 1);
                    throw new RuntimeException(ex);
                }
                break;
            case "Open Mods Folder":
                try {
                    if (textField.getText().endsWith("mods")) {
                        cLog("Opening Mods Folder...", 0);
                        Desktop.getDesktop().open(new File(textField.getText()));
                    } else {
                        if (text.equals("")) {
                            cLog("Invalid Path Entered - null", 1);
                        } else {
                            cLog("Invalid Path Entered - " + text, 1);
                        }
                        status.setForeground(Color.RED);
                        status.setText("Invalid Path Entered");
                    }
                } catch (IOException ex) {
                    cLog(ex.toString(), 1);
                    throw new RuntimeException(ex);
                }
                break;
            case "\uD83D\uDCC1":
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File(textField.getText()));
                chooser.setDialogTitle("Choose Mods Folder");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    textField.setText(chooser.getSelectedFile().toString());
                } else {
                    cLog("No Selection made", 1);
                }
                break;
        }
    }
}

