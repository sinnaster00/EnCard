package com.joma.encard.ui;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class Prefs {
    public SharedPreferences preferences;

    @Inject
    public Prefs(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void saveBoardState() {
        preferences.edit().putBoolean(Constant.PREFS, true).apply();
    }

    public boolean isBoardShow() {
        return preferences.getBoolean(Constant.PREFS, false);
    }
}
