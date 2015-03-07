package com.picserver.utils;

public class FileUtils {
    public static String getFileExtension(String file) {
        if (file.lastIndexOf(".") != -1 && file.lastIndexOf(".") != 0) {
            return file.substring(file.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}
