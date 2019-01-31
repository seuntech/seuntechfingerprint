package com.seuntech.seuntechpincode;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by seuntech on 1/29/2019.
 */

public class ManagePin<T extends pin_activity> {

    private SharedPreferences prefs;
    private static ManagePin PinInstance;
    private static AppConf appcall;

    public static ManagePin getInstance(Context context) {
        synchronized (ManagePin.class) {
            if (PinInstance == null) {
                PinInstance = new ManagePin(context);
            }
        }
        return PinInstance;
    }


    private ManagePin(Context context) {
        super();
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public boolean isPinSet() {
        String pin = prefs.getString("STPIN", "u");
        if (pin.length() < 3) {
            return false;
        }
        return true;
    }

    public void clearPin() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("STPIN", "");
        editor.apply();
    }

    public String getPin() {
        return prefs.getString("STPIN", "u");
    }

    public boolean setPin(String pin) {

        String encrypted = "";
        try {
            encrypted = AESUtils.encrypt(pin);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("STPIN", encrypted);
        editor.apply();
        return true;
    }


    public String MyPin(String pin) {
        String encrypted = "";
        try {
            encrypted = AESUtils.encrypt(pin);
        } catch (Exception e) {
            e.printStackTrace();
            return pin;
        }
        return encrypted;
    }

    public void HideLogo() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("logoid", -1);
        editor.apply();
    }

    ;

    public void setLogoId(int logoId) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("logoid", logoId);
        editor.apply();
    }

    public int getLogoId() {
        return prefs.getInt("logoid", -1);
    }

}
