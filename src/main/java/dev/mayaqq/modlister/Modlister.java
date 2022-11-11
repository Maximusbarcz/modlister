package dev.mayaqq.modlister;

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

        try {
            File modListFile = new File(SaveGui.SaveGui());
            Path modListPath = modListFile.toPath();
            for (String modName : modNames) {
                cLog("Listing: " + modName, 0);
                jarPath = filePath + "/" + modName;
                if (modName.contains(".jar") && !modName.contains(".disabled")) {
                    ListModJson.ListJar(jarPath, modListPath.toString());
                }
            }

            cLog("Listing of Mods Complete!", 0);
            cLog("Modlist.txt has been created to " + modListPath, 0);
            if (temp.exists()) {
                temp.delete();
            }
        } catch (Exception e) {
            cLog(e.toString(), 1);
        }
    }
}


