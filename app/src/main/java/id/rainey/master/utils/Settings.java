package id.rainey.master.utils;

import android.content.SharedPreferences;

import javax.inject.Inject;

import id.rainey.master.session.ObscuredSharedPreferences;

/**
 * Created by Dark on 15/12/2015.
 */

public class Settings {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private boolean bulkUpdating = false;

    @Inject
    public Settings(ObscuredSharedPreferences obscuredSharedPreferences) {
        sp = obscuredSharedPreferences;
    }

    public void removeAll() {
        remove(Key.HAS_LOGGED_IN,
                Key.HAS_USER_LEARNED_DRAWER);
    }

    public void put(Key key, String val) {
        doEdit();
        editor.putString(key.name(), val);
        doApply();
    }

    public void put(Key key, int val) {
        doEdit();
        editor.putInt(key.name(), val);
        doApply();
    }

    public void put(Key key, boolean val) {
        doEdit();
        editor.putBoolean(key.name(), val);
        doApply();
    }

    public void put(Key key, float val) {
        doEdit();
        editor.putFloat(key.name(), val);
        doApply();
    }

    /**
     * Convenience method for storing doubles.
     * <p/>
     * There may be instances where the accuracy of a double is desired.
     * SharedPreferences does not handle doubles so they have to
     * cast to and from String.
     *
     * @param key The enum of the preference to store.
     * @param val The new value for the preference.
     */
    public void put(Key key, double val) {
        doEdit();
        editor.putString(key.name(), String.valueOf(val));
        doApply();
    }

    public void put(Key key, long val) {
        doEdit();
        editor.putLong(key.name(), val);
        doApply();
    }

    public String getString(Key key, String defaultValue) {
        return sp.getString(key.name(), defaultValue);
    }

    public String getString(Key key) {
        return sp.getString(key.name(), null);
    }

    public int getInt(Key key) {
        return sp.getInt(key.name(), 0);
    }

    public int getInt(Key key, int defaultValue) {
        return sp.getInt(key.name(), defaultValue);
    }

    public long getLong(Key key) {
        return sp.getLong(key.name(), 0);
    }

    public long getLong(Key key, long defaultValue) {
        return sp.getLong(key.name(), defaultValue);
    }

    public float getFloat(Key key) {
        return sp.getFloat(key.name(), 0);
    }

    public float getFloat(Key key, float defaultValue) {
        return sp.getFloat(key.name(), defaultValue);
    }

    /**
     * Convenience method for retrieving doubles.
     * <p/>
     * There may be instances where the accuracy of a double is desired.
     * SharedPreferences does not handle doubles so they have to
     * cast to and from String.
     *
     * @param key The enum of the preference to fetch.
     */
    public double getDouble(Key key) {
        return getDouble(key, 0);
    }

    /**
     * Convenience method for retrieving doubles.
     * <p/>
     * There may be instances where the accuracy of a double is desired.
     * SharedPreferences does not handle doubles so they have to
     * cast to and from String.
     *
     * @param key The enum of the preference to fetch.
     */
    public double getDouble(Key key, double defaultValue) {
        try {
            return Double.valueOf(sp.getString(key.name(), String.valueOf(defaultValue)));
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public boolean getBoolean(Key key, boolean defaultValue) {
        return sp.getBoolean(key.name(), defaultValue);
    }

    public boolean getBoolean(Key key) {
        return sp.getBoolean(key.name(), false);
    }

    /**
     * Remove keys from SharedPreferences.
     *
     * @param keys The enum of the key(s) to be removed.
     */
    public void remove(Key... keys) {
        doEdit();
        for (Key key : keys) {
            editor.remove(key.name());
        }
        doApply();
    }

    public void edit() {
        editor = sp.edit();
        bulkUpdating = true;
    }

    public void apply() {
        editor.apply();
        editor = null;
        bulkUpdating = false;
    }

    private void doEdit() {
        if (!bulkUpdating && editor == null) {
            editor = sp.edit();
        }
    }

    private void doApply() {
        if (!bulkUpdating && editor != null) {
            editor.apply();
            editor = null;
        }
    }

    public enum Key {
        HAS_LOGGED_IN,
        /**
         * Per the design guidelines, you should show the drawer on launch until the user manually
         * expands it. This shared preference tracks this.
         */
        HAS_USER_LEARNED_DRAWER
    }

}
