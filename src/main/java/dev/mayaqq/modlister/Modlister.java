package dev.mayaqq.modlister;

import java.io.File;
import java.io.IOException;

public class Modlister {
    public static void getMods(String filePath) throws IOException {
        String jarPath;
        File f = new File(filePath);
        File temp = new File("temp.json");
        String[] modNames;
        File modlist = new File(filePath + "/modlist.txt");
        if (modlist.exists()) {
            modlist.delete();
        }
        modNames = f.list();
        for (String modName : modNames) {
            System.out.println(modName);
            jarPath = filePath + "/" + modName;
            if (modName.contains(".jar") && !modName.contains(".disabled")) {
                ListModJson.ListJar(jarPath, filePath);
            }
        }

        Gui.cLog("Listing of Mods Complete!");
        if (temp.exists()) {
            temp.delete();
        }
    }
}


