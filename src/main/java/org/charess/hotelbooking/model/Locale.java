package org.charess.hotelbooking.model;

public enum Locale {

    ENGLISH("en"),
    FRANCAIS("fr"),
    ESPANOL("es");

    String locale;

    Locale(String locale){
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }
}
