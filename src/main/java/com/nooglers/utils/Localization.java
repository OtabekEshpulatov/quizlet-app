package com.nooglers.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localization {
    private static final ThreadLocal<ResourceBundle> en = ThreadLocal.withInitial(() -> ResourceBundle.getBundle("messages", Locale.forLanguageTag("en")));
    private static final ThreadLocal<ResourceBundle> uz = ThreadLocal.withInitial(() -> ResourceBundle.getBundle("messages", Locale.forLanguageTag("uz")));
    public static String getlocalizedMessage(String key, String language){
        if (language.equals("uz"))
            return uz.get().getString(key);
        return en.get().getString(key);
    }
}