package dev.mayaqq.modlister.files;

import com.google.gson.Gson;

import java.io.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static dev.mayaqq.modlister.gui.Gui.cLog;

public class ListModJson {
    public static void ListJar(String jarPath, String modListPath) throws IOException {
        //someone please make this prettier
        JarFile modJar = new JarFile(jarPath);
        final Enumeration<JarEntry> entries = modJar.entries();
        while (entries.hasMoreElements()) {
            final JarEntry entry = entries.nextElement();
            if (entry.getName().equals("fabric.mod.json") || entry.getName().equals("quilt.mod.json")) {
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
                    cLog(e.toString(), 1);
                }

                Gson gson = new Gson();
                Map map = gson.fromJson(new FileReader(temp), Map.class);
                File modList = new File(modListPath);
                FileWriter writer = new FileWriter(modList, true);
                String sources = null;
                Object contact = map.get("contact");
                Object modid = map.get("id");
                if (contact != null) {
                    Map<String, Object> contactMap = (Map<String, Object>) contact;
                    sources = (String) contactMap.get("sources");
                }
                String name = (String) map.get("name");
                if (Objects.equals(sources, "")) {
                    writer.write(name + " - " + modid + " - null\n");
                } else {
                    writer.write(name  + " - " + modid + " - " + sources + "\n");
                }
                writer.close();
            }
        }
    }
}
