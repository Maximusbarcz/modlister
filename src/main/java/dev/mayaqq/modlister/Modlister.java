package dev.mayaqq.modlister;

import dev.mayaqq.modlister.files.ListModJson;
import dev.mayaqq.modlister.gui.SaveGui;
import dev.mayaqq.modlister.utils.Hastebin;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static dev.mayaqq.modlister.gui.Gui.cLog;

public class Modlister {
    public static void getMods(String filePath) throws IOException {
        String jarPath;
        File f = new File(filePath);
        File temp = new File("temp.json");
        String[] modNames;
        modNames = f.list();
        Integer modCount = 0;

        try {
            File modListFile = new File(SaveGui.SaveGui());
            Path modListPath = modListFile.toPath();
            if (modListFile.exists()) {
                modListFile.delete();
                modListFile.createNewFile();
            } else {
                modListFile.createNewFile();
            }
            cLog("Listing: " + filePath, 0);
            cLog("[", 2);
            for (String modName : modNames) {
                jarPath = filePath + "/" + modName;
                if (modName.contains(".jar") && !modName.contains(".disabled")) {
                    cLog("|", 2);
                    modCount++;
                    ListModJson.ListJar(jarPath, modListPath.toString());
                }
            }
            String modlisttxt = modListPath.toString().replaceAll(".*[/\\\\]", "");
            cLog("] [" + modCount + "]\n", 2);
            cLog("Listing of Mods Complete!", 0);
            if (modListFile.exists()) {
                cLog(modlisttxt + " has been created to " + modListPath, 0);
            }
            if (temp.exists()) {
                temp.delete();
            }
            String url = null;
            Hastebin hastebin = new Hastebin();
            try {
                url = hastebin.post(Files.readString(modListPath));
                cLog("Modlist: " + url, 0);
            } catch (IOException e) {
                cLog(e.toString(), 1);
            }

            try {
                cLog("Opening: " + modlisttxt, 0);
                //check if the user is on linux
                if (System.getProperty("os.name").toLowerCase().contains("linux")) {
                    cLog("If you are using gnome you need to close to application to open the link", 0);
                }
                Desktop.getDesktop().browse(java.net.URI.create(url));
            } catch (IOException e) {
                cLog("Error opening the link.", 1);
            }
        } catch (Exception e) {
            cLog("Process aborted: " + e, 1);
        }
    }
}


