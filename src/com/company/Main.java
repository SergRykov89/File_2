package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void saveGame(String path, GameProgress gamer) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gamer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(String pathZipFile, List<String> pathGamers) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(pathZipFile))) {
            for (int i = 0; i < pathGamers.size(); i++) {
                try (FileInputStream fis = new FileInputStream(pathGamers.get(i))) {
                    ZipEntry entry = new ZipEntry("save" + (i + 1) + ".dat");
                    zos.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zos.write(buffer);
                    zos.closeEntry();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        String path = "D://Games//savegames//";

        File save1 = new File(path + "save1.dat");
        File save2 = new File(path + "save2.dat");
        File save3 = new File(path + "save3.dat");
        try {
            save1.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            save2.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            save2.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        GameProgress gamer1 = new GameProgress(6, 10, 8, 589);
        GameProgress gamer2 = new GameProgress(15, 5, 11, 1548);
        GameProgress gamer3 = new GameProgress(28, 15, 18, 3689);

        saveGame(save1.getPath(), gamer1);
        saveGame(save2.getPath(), gamer2);
        saveGame(save3.getPath(), gamer3);

        List<String> list = new ArrayList<>();
        list.add(save1.getPath());
        list.add(save2.getPath());
        list.add(save3.getPath());

        zipFiles(path + "zip.zip", list);
        save1.delete();
        save2.delete();
        save3.delete();

    }
}

