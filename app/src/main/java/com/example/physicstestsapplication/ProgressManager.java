package com.example.physicstestsapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.util.Calendar;

public class ProgressManager {
    private static final String PREFS_NAME = "GameProgress";
    private static final String GEMS_KEY = "gems";
    private static final String STREAK_KEY = "streak";
    private static final String LAST_DAY_KEY = "lastDay";

    private final SharedPreferences prefs;

    public ProgressManager(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        initializeDefaults();
    }

    private void initializeDefaults() {
        if (!prefs.contains(GEMS_KEY)) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(GEMS_KEY, 0);
            editor.putInt(STREAK_KEY, 0);
            editor.putLong(LAST_DAY_KEY, 0);
            editor.apply();
        }
    }

    public void updateStreak() {
        long lastDay = prefs.getLong(LAST_DAY_KEY, 0);
        Calendar todayCal = Calendar.getInstance();
        long today = todayCal.getTimeInMillis();

        if (lastDay == 0) {
            prefs.edit().putLong(LAST_DAY_KEY, today).apply();
            return;
        }

        Calendar lastDayCal = Calendar.getInstance();
        lastDayCal.setTimeInMillis(lastDay);

        if (isNextDay(lastDayCal, todayCal)) {
            int newStreak = getStreak() + 1;
            prefs.edit().putInt(STREAK_KEY, newStreak).apply();
            Log.d("Progress", "Серия увеличена " + newStreak + " дней");
        } else if (!isSameDay(lastDayCal, todayCal)) {
            prefs.edit().putInt(STREAK_KEY, 1).apply();
            Log.d("Progress", "Новая серия начата");
        }

        prefs.edit().putLong(LAST_DAY_KEY, today).apply();
    }

    private boolean isSameDay(Calendar cal1, Calendar cal2) {
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    private boolean isNextDay(Calendar prev, Calendar current) {
        Calendar nextDay = (Calendar) prev.clone();
        nextDay.add(Calendar.DAY_OF_YEAR, 1);
        return isSameDay(nextDay, current);
    }

    public void addGems(int amount) {
        int current = getGems();
        prefs.edit().putInt(GEMS_KEY, current + amount).apply();
        Log.d("Progress", "Добавлено " + amount + ", теперь " + (current + amount));
    }

    public int getGems() {
        return prefs.getInt(GEMS_KEY, 50);
    }

    public int getStreak() {
        return prefs.getInt(STREAK_KEY, 0);
    }
}