package com.seuntech.seuntechpincode;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.seuntech.pinpad.AppConf;
import com.seuntech.pinpad.ManagePin;

public class example_MainActivity extends AppCompatActivity {

    ManagePin Mp;
    private final int SUCCESS_REQUEST = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Initialise Managepin
        Mp = ManagePin.getInstance(this);
        Mp.setLogoId(R.drawable.android);
        //Mp.HideLogo();
        //Mp.clearPin();
        //Mp.isPinSet();

        //fully customizable
        //translated to any language
        //pin encryption
        //createpin,authpin,resetpin
        //support fragment and non fragment
        //
    }


    //PINCODE Options
    //AppConf.CREATE_PIN = Create New PIN
    //AppConf.AUTH_PIN = Authenticate PIN
    //AppConf.RESET_PIN = Reset PIN
    //Intent Example
    public void inte(View v) {
        AppConf.HIDE_BAR = true;
        if (!Mp.isPinSet()) {
            Intent intent = new Intent(this, example_custom_activity.class);
            intent.putExtra(AppConf.AUTH_TYPE, AppConf.AUTH_PIN);
            startActivityForResult(intent, SUCCESS_REQUEST);
        } else {
            Intent intent = new Intent(this, example_custom_activity.class);
            intent.putExtra(AppConf.AUTH_TYPE, AppConf.AUTH_PIN);
            startActivityForResult(intent, SUCCESS_REQUEST);
        }
    }

    //Fragment Example
    public void frag(View v) {
        Fragment fragment = new example_custom_fragment();
        Bundle bundle = new Bundle();
        bundle.putString(AppConf.AUTH_TYPE, AppConf.AUTH_PIN);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("pin").commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case SUCCESS_REQUEST:
                Intent intent = new Intent(this, example_MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

}
