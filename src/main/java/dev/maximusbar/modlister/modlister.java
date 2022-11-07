package dev.maximusbar.modlister;

import com.google.gson.Gson;

import java.io.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static dev.maximusbar.modlister.modlister.ListModJson.ListJar;

public class modlister {
    public static void main(String[] arg) throws IOException {
        String jarPath;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your desired file path below: ");
        String filePath = sc.nextLine();
        File f = new File(filePath);
        File temp = new File("temp.json");
        String[] modNames;
        File modlist = new File("modlist.txt");
        if (modlist.exists()) {
            modlist.delete();
        }
        modNames = f.list();
        for (String modName : modNames) {
            jarPath = filePath + "/" + modName;
            if (modName.contains(".jar") && !modName.contains(".disabled")) {
                ListJar(jarPath);
            }
        }
        System.out.println("Listing of Mods Complete!");
        if (temp.exists()) {
            temp.delete();
        }
        sc.close();
    }

    public static class ListModJson {
        public static void ListJar(String jarPath) throws IOException {
            JarFile modJar = new JarFile(jarPath);
            final Enumeration<JarEntry> entries = modJar.entries();
            while (entries.hasMoreElements()) {
                final JarEntry entry = entries.nextElement();
                if (entry.getName().contains("fabric.mod.json")) {
                    JarEntry fileEntry = modJar.getJarEntry(entry.getName());
                    InputStream input = modJar.getInputStream(fileEntry);
                    InputStreamReader isr = new InputStreamReader(input);
                    BufferedReader reader = new BufferedReader(isr);
                    File temp = new File("temp.json");
                    try {
                        FileWriter writer = new FileWriter(temp);
                        String line;
                        while ((line = reader.readLine()) != null) {
                            writer.write(line);
                        }
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Gson gson = new Gson();
                    Map map = gson.fromJson(new FileReader(temp), Map.class);
                    Object contact = map.get("contact");
                    String sources = null;
                    if (contact != null) {
                        Map<String, Object> contactMap = (Map<String, Object>) contact;
                        sources = (String) contactMap.get("sources");
                    }
                    String name = (String) map.get("name");
                    File modList = new File("modlist.txt");
                    if (!modList.exists()) {
                        modList.createNewFile();
                    }
                    FileWriter writer = new FileWriter(modList, true);
                    if (Objects.equals(sources, "")) {
                        writer.write(name + " - null\n");
                    } else {
                        writer.write(name + " - " + sources + "\n");
                    }
                    writer.close();
                }
            }
        }
    }
}


