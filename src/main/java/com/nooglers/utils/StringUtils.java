package com.nooglers.utils;

import jakarta.servlet.http.Part;

public class StringUtils {
    static boolean isValidString(String s) {
    return s != null && !s.isBlank();
}

    static String getExtension(Part bookFile) {
        return getExtension(bookFile.getSubmittedFileName());
    }

    static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
