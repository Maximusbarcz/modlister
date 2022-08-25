package dev.maximusbar.modlister;

import java.io.*;
import java.util.Scanner;

public class modlister {
    public static void main(String[] args) throws IOException {
        String[] modNames;
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter your desired file path below:");
        String filePath = sc.nextLine();
        File f = new File(filePath);

        modNames = f.list();

        System.out.println("List of mods: ");
        for (String modName : modNames) {
            System.out.println(modName);
        }
        System.out.println("Exported "+ modNames.length + " mods to mods.txt");
        FileWriter fw = new FileWriter(new File(filePath, "mods.txt"));
        for(String str: modNames) {
            fw.write(str.replaceAll(".jar", "") + System.lineSeparator());
        }
        fw.close();
        sc.close();
    }
}


