package com.seuntech.seuntechpincode;

import android.content.Intent;
import android.widget.Toast;

import com.seuntech.pinpad.pin_fragment;

/**
 * Created by seuntech on 1/29/2019.
 */

public class example_custom_fragment extends pin_fragment {

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
        Toast.makeText(getActivity(), "FORGOTSSSSSSSSSS", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPinFail(int attempts) {
        Toast.makeText(getActivity(), String.valueOf(attempts), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPinSuccess(int attempts) {
        Toast.makeText(getActivity(), String.valueOf(attempts), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), example_MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFingerSuccess() {

    }

    @Override
    public void onFingerFail() {

    }
}
