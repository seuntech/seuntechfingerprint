package com.seuntech.pinpad;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by seuntech on 1/29/2019.
 */

public class ManagePin<T extends pin_activity> {

    private SharedPreferences prefs;
    private static ManagePin PinInstance;
    private static AppConf appcall;
    static Context c;

    public static ManagePin getInstance(Context context) {
        c = context;
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



    private long get_LastActivetime(){
        Long defaultValue = 0l;
        return prefs.getLong("timeout", defaultValue);
    }


    protected void set_LastActivetime(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("timeout", System.currentTimeMillis());
        editor.apply();
    }

    protected void exp_LastActivetime(){
        SharedPreferences.Editor editor = prefs.edit();
        Long defaultValue = 0l;
        editor.putLong("timeout",defaultValue);
        editor.apply();
    }



    public boolean isTimeout(){

        long lastActiveMillis = get_LastActivetime();
        long passedTime = System.currentTimeMillis() - lastActiveMillis;

        long timeout = (AppConf.TIME_OUT*1000);
        if (lastActiveMillis > 0 && passedTime >= timeout) {

            return  true;
        }else {
            set_LastActivetime();
        }

        return false;
    }




    protected String getPin() {
        return prefs.getString("STPIN", "u");
    }

    protected boolean setPin(String pin) {

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


    protected String MyPin(String pin) {
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
