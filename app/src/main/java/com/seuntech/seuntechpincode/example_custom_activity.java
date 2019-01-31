package com.seuntech.seuntechpincode;

import android.widget.Toast;

/**
 * Created by seuntech on 1/29/2019.
 */

public class example_custom_activity extends pin_activity {


    @Override
    public int get_layout() {
        return R.layout.activity_pin;
    }

    @Override
    public int get_BodyColor() {
        return R.color.st_body;
    }

    @Override
    public int get_font_size(){
        return 14;
    }

    @Override
    public void onForgot() {
        Toast.makeText(getApplicationContext(), "FORGOTSSSSSSSSSS", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPinFail(int attempts) {
        Toast.makeText(getApplicationContext(), String.valueOf(attempts), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPinSuccess(int attempts) {
        Toast.makeText(getApplicationContext(), String.valueOf(attempts), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onFingerFail() {
        Toast.makeText(getApplicationContext(), "Yess Finger", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFingerSuccess() {
        Toast.makeText(getApplicationContext(), "No finger", Toast.LENGTH_SHORT).show();
    }


}
