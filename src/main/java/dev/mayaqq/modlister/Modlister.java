package dev.mayaqq.modlister;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static dev.mayaqq.modlister.Gui.cLog;

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
            cLog("] [" + modCount + "]\n", 2);
            cLog("Listing of Mods Complete!", 0);
            cLog("Modlist.txt has been created to " + modListPath, 0);
            if (temp.exists()) {
                temp.delete();
            }
            try {
                cLog("Opening: " + modListPath.toString().replaceAll(".*[/\\\\]", ""), 0);
                Desktop.getDesktop().open(modListFile);
            } catch (IOException e) {
                cLog("Error opening the file, you can open it manually in your directory.", 1);
            }
        } catch (Exception e) {
            cLog("Process aborted: " + e, 1);
        }
    }
}


