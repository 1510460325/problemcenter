package com.kelab.problemcenter.util;


import java.io.*;
import java.util.Enumeration;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    public static boolean zipWithoutRoot(String sourcePath, String zipPath) {
        ZipOutputStream zipOutputStream = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipPath));
            File file = new File(sourcePath);
            for (File child : Objects.requireNonNull(file.listFiles())) {
                zipOne(child.getPath(), zipOutputStream, "");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (zipOutputStream != null) {
                try {
                    zipOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean zip(String sourcePath, String zipPath) {
        ZipOutputStream zipOutputStream = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipPath));
            write_readme(zipOutputStream);
            zipOne(sourcePath, zipOutputStream, "");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (zipOutputStream != null) {
                try {
                    zipOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    private static void zipOne(String filePath, ZipOutputStream zipOutputStream, String relative_path) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File tmp : Objects.requireNonNull(file.listFiles())) {
                zipOne(tmp.getPath(), zipOutputStream, relative_path + file.getName() + "/");
            }
        } else {
            zipOutputStream.putNextEntry(new ZipEntry(relative_path + file.getName()));
            write(new FileInputStream(file), zipOutputStream);
            zipOutputStream.flush();
            zipOutputStream.closeEntry();
        }
    }

    public static void write(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] by = new byte[1024];
        int n;
        while ((n = inputStream.read(by)) != -1) {
            outputStream.write(by, 0, n);
        }
    }

    private static void write_readme(ZipOutputStream zipOutputStream) {
        try {
            zipOutputStream.putNextEntry(new ZipEntry("readme.txt"));
            zipOutputStream.write("这是在压缩过程中产生的readme文件".getBytes());
            zipOutputStream.flush();
            zipOutputStream.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean unzip(String zipPath, String targetPath) throws IOException {
        if (!new File(targetPath).exists()) {
            return false;
        }
        ZipFile zipFile = new ZipFile(zipPath);
        Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (entry.isDirectory()) {
                File file = new File(targetPath + "/" + entry.getName());
                if (!file.exists()) {
                    file.mkdirs();
                }
            } else {
                File file = new File(targetPath + "/" + entry.getName());
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                write(zipFile.getInputStream(entry), new FileOutputStream(file));
            }
        }
        zipFile.close();
        return true;
    }

}
