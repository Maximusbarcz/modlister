package dev.maximusbar.modlister;

import java.io.*;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class modlister {
    public static class ListJar {
        private static void process(InputStream input) throws IOException {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("  \"id\": ")) {
                    System.out.println(line.replace(  "  \"id\": \"", "").replace("\",", ""));
                }
            }
            reader.close();
        }

        public static void main(String arg[]) throws IOException {
            String jarPath;
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter your desired file path below:");
            String filePath = sc.nextLine();
            File f = new File(filePath);
            String[] modNames;
            modNames = f.list();
            for (String modName : modNames) {
                System.out.println(modName);
                jarPath = filePath + "\\" + modName;
                JarFile modJar = new JarFile(jarPath);
                final Enumeration<JarEntry> entries = modJar.entries();
                while (entries.hasMoreElements()) {
                    final JarEntry entry = entries.nextElement();
                    System.out.println("File : " + entry.getName());
                    if (entry.getName().contains("fabric.mod.json")) {
                        JarEntry fileEntry = modJar.getJarEntry(entry.getName());
                        InputStream input = modJar.getInputStream(fileEntry);
                        process(input);
                        break;
                    }
                }

            }
            sc.close();
        }
    }
}


