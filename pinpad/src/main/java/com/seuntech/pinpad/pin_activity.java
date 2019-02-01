package com.seuntech.pinpad;


import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by seuntech on 10/27/2017.
 */

public abstract class pin_activity extends AppCompatActivity implements View.OnClickListener, FingerprintUiHelper.Callback {
    protected FingerprintManager mFingerprintManager;
    protected FingerprintUiHelper mFingerprintUiHelper;
    String pincode1 = null;
    String pincode2 = null;
    String pincode3 = null;
    ImageView circle1, circle2, circle3, circle4, seuntech_fingerprint;
    String seuntechtype = "1";
    int pintattempt = 1;
    int current_pin_pos = 1;
    LinearLayout seuntech_keyboard_view;
    private ManagePin managePin;
    private TextView seuntech_button_0, seuntech_button_1, seuntech_button_2, seuntech_button_3, seuntech_button_4, seuntech_button_5, seuntech_button_6, seuntech_button_7, seuntech_button_8, seuntech_button_9, seuntech_button_clear, seuntech_instruction, seuntech_forgot, seuntech_fingerprint_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AppConf.HIDE_BAR) {
            ActionBar ac = getSupportActionBar();
            ac.hide();
        }

        setContentView(get_layout());

        Intent intent = getIntent();

        if (intent.getStringExtra("type") != null) {
            seuntechtype = intent.getStringExtra("type");
        }


        seuntech_fingerprint_message = findViewById(R.id.seuntech_fingerprint_message);
        seuntech_fingerprint_message.setTextSize(get_font_size());
        seuntech_fingerprint = findViewById(R.id.seuntech_fingerprint);


        managePin = ManagePin.getInstance(this);

        managePin.exp_LastActivetime();

        seuntech_button_clear = findViewById(R.id.seuntech_button_clear);
        seuntech_button_clear.setText(Html.fromHtml("&laquo;"));

        seuntech_forgot = findViewById(R.id.seuntech_forgot);
        seuntech_forgot.setTextColor(ContextCompat.getColor(this, get_BodyColor()));
        seuntech_forgot.setOnClickListener(this);
        seuntech_forgot.setTextSize(get_font_size());

        seuntech_instruction = findViewById(R.id.seuntech_instruction);
        seuntech_instruction.setTextColor(ContextCompat.getColor(this, get_BodyColor()));
        seuntech_instruction.setTextSize(get_font_size());

        switch (seuntechtype) {
            case AppConf.CREATE_PIN:
                seuntech_forgot.setText("");
                seuntech_forgot.setVisibility(View.GONE);
                seuntech_instruction.setText(this.getResources().getString(R.string.st_creat_for_digit_pin));
                break;

            case AppConf.RESET_PIN:
                seuntech_forgot.setText("");
                seuntech_forgot.setVisibility(View.GONE);
                seuntech_instruction.setText(this.getResources().getString(R.string.st_enter_current_pin));
                break;

            case AppConf.AUTH_PIN:
                seuntech_forgot.setText(this.getResources().getString(R.string.st_forgot_password));
                seuntech_forgot.setVisibility(View.VISIBLE);
                seuntech_instruction.setText(this.getResources().getString(R.string.st_enterpin_tounlock));
                break;

        }

        seuntech_keyboard_view = findViewById(R.id.seuntech_keyboard);


        circle1 = findViewById(R.id.seuntech_circle1);
        circle2 = findViewById(R.id.seuntech_circle2);
        circle3 = findViewById(R.id.seuntech_circle3);
        circle4 = findViewById(R.id.seuntech_circle4);


        seuntech_button_0 = findViewById(R.id.seuntech_button_0);
        seuntech_button_1 = findViewById(R.id.seuntech_button_1);
        seuntech_button_2 = findViewById(R.id.seuntech_button_2);
        seuntech_button_3 = findViewById(R.id.seuntech_button_3);
        seuntech_button_4 = findViewById(R.id.seuntech_button_4);
        seuntech_button_5 = findViewById(R.id.seuntech_button_5);
        seuntech_button_6 = findViewById(R.id.seuntech_button_6);
        seuntech_button_7 = findViewById(R.id.seuntech_button_7);
        seuntech_button_8 = findViewById(R.id.seuntech_button_8);
        seuntech_button_9 = findViewById(R.id.seuntech_button_9);
        seuntech_button_0 = findViewById(R.id.seuntech_button_0);
        seuntech_button_clear = findViewById(R.id.seuntech_button_clear);


        seuntech_button_0.setTextColor(ContextCompat.getColor(this, get_BodyColor()));
        seuntech_button_1.setTextColor(ContextCompat.getColor(this, get_BodyColor()));
        seuntech_button_2.setTextColor(ContextCompat.getColor(this, get_BodyColor()));
        seuntech_button_3.setTextColor(ContextCompat.getColor(this, get_BodyColor()));
        seuntech_button_4.setTextColor(ContextCompat.getColor(this, get_BodyColor()));
        seuntech_button_5.setTextColor(ContextCompat.getColor(this, get_BodyColor()));
        seuntech_button_6.setTextColor(ContextCompat.getColor(this, get_BodyColor()));
        seuntech_button_7.setTextColor(ContextCompat.getColor(this, get_BodyColor()));
        seuntech_button_8.setTextColor(ContextCompat.getColor(this, get_BodyColor()));
        seuntech_button_9.setTextColor(ContextCompat.getColor(this, get_BodyColor()));
        seuntech_button_0.setTextColor(ContextCompat.getColor(this, get_BodyColor()));
        seuntech_button_clear.setTextColor(ContextCompat.getColor(this, get_BodyColor()));


        seuntech_button_0.setOnClickListener(this);
        seuntech_button_1.setOnClickListener(this);
        seuntech_button_2.setOnClickListener(this);
        seuntech_button_3.setOnClickListener(this);
        seuntech_button_4.setOnClickListener(this);
        seuntech_button_5.setOnClickListener(this);
        seuntech_button_6.setOnClickListener(this);
        seuntech_button_7.setOnClickListener(this);
        seuntech_button_8.setOnClickListener(this);
        seuntech_button_9.setOnClickListener(this);
        seuntech_button_0.setOnClickListener(this);
        seuntech_button_clear.setOnClickListener(this);


        int logoId = managePin.getLogoId();
        ImageView logoImage = findViewById(R.id.seuntech_logo);
        if (logoId != -1) {
            logoImage.setVisibility(View.VISIBLE);
            logoImage.setImageResource(logoId);
        }


    }

    public int get_BodyColor() {
        return R.color.st_body;
    }

    public int get_font_size() {
        return 14;
    }


    @Override
    protected void onResume() {
        super.onResume();
        //Init layout for Fingerprint
        initLayoutForFingerprint();
    }

    private void initLayoutForFingerprint() {
        if (AppConf.AUTH_PIN.equals(seuntechtype) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mFingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
            mFingerprintUiHelper = new FingerprintUiHelper.FingerprintUiHelperBuilder(mFingerprintManager).build(seuntech_fingerprint, seuntech_fingerprint_message, this);
            try {
                if (mFingerprintManager.isHardwareDetected() && mFingerprintUiHelper.isFingerprintAuthAvailable()) {
                    seuntech_fingerprint.setVisibility(View.VISIBLE);
                    seuntech_fingerprint_message.setVisibility(View.VISIBLE);
                    mFingerprintUiHelper.startListening();
                } else {
                    seuntech_fingerprint.setVisibility(View.GONE);
                    seuntech_fingerprint_message.setVisibility(View.GONE);
                }
            } catch (SecurityException e) {
                seuntech_fingerprint.setVisibility(View.GONE);
                seuntech_fingerprint_message.setVisibility(View.GONE);
            }
        } else {
            seuntech_fingerprint.setVisibility(View.GONE);
            seuntech_fingerprint_message.setVisibility(View.GONE);
        }
    }


    private void check_circle(String code) {

        int lenth = 0;
        if (pincode1 != null && current_pin_pos == 1) {
            lenth = pincode1.length();
        }

        if (pincode2 != null && current_pin_pos == 2) {
            lenth = pincode2.length();
        }

        if (pincode3 != null && current_pin_pos == 3) {
            lenth = pincode3.length();
        }

        if (lenth >= 4) {

        } else {


            if (pincode1 == null && current_pin_pos == 1) {
                pincode1 = code;
            } else if (pincode2 == null && current_pin_pos == 2) {
                pincode2 = code;
            } else if (pincode3 == null && current_pin_pos == 3) {
                pincode3 = code;
            } else {

                if (current_pin_pos == 1) {
                    pincode1 = pincode1 + code;
                } else if (current_pin_pos == 2) {
                    pincode2 = pincode2 + code;
                } else if (current_pin_pos == 3) {
                    pincode3 = pincode3 + code;
                }
            }


            if (pincode1 != null && current_pin_pos == 1) {
                lenth = pincode1.length();
            }

            if (pincode2 != null && current_pin_pos == 2) {
                lenth = pincode2.length();
            }

            if (pincode3 != null && current_pin_pos == 3) {
                lenth = pincode3.length();
            }


            switch (lenth) {
                case 1:
                    circle1.setBackgroundResource(R.drawable.pin_circle2);
                    ;
                    break;
                case 2:
                    circle2.setBackgroundResource(R.drawable.pin_circle2);
                    ;
                    break;
                case 3:
                    circle3.setBackgroundResource(R.drawable.pin_circle2);
                    ;
                    break;
                case 4:
                    circle4.setBackgroundResource(R.drawable.pin_circle2);

                    break;
            }


            if (lenth == 4) {
                final Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        process_pin();
                    }
                }, 200);

            }
        }
    }


    private boolean process_pin() {

        switch (seuntechtype) {
            case AppConf.CREATE_PIN:

                if (current_pin_pos == 1) {
                    current_pin_pos = 2;
                    clear_state();
                    seuntech_instruction.setText(this.getResources().getString(R.string.st_confirm_pin));
                    return false;
                }


                if (current_pin_pos == 2) {

                    //check if pin 1 = 2
                    if (pincode1.equals(pincode2)) {
                        clear_state();
                        seuntech_instruction.setText(this.getResources().getString(R.string.st_enterpin_tounlock));
                        current_pin_pos = 3;
                    } else {
                        pincode2 = null;
                        clear_state();
                        animate_activity();
                    }
                    return false;
                }

                if (current_pin_pos == 3) {
                    //check if pin 2 = 3
                    if (pincode3.equals(pincode2)) {
                        current_pin_pos = 1;
                        //create pin
                        if (managePin.setPin(pincode2)) {
                            onPinOk();
                            clear_all();
                            finish();
                        } else {
                            clear_state();
                            animate_activity();
                            return false;
                        }
                    } else {
                        pincode3 = null;
                        clear_state();
                        animate_activity();
                    }
                    //if not say error else current_pin_pos == 1; complete
                    return false;
                }
                break;

            case AppConf.RESET_PIN:
                if (current_pin_pos == 1) {
                    if (managePin.MyPin(pincode1).equals(managePin.getPin())) {
                        current_pin_pos = 2;
                        clear_state();
                        seuntech_instruction.setText(this.getResources().getString(R.string.st_enter_new_pin));
                    } else {
                        animate_activity();
                        onPinError();
                        clear_all();
                    }
                    return false;
                }


                if (current_pin_pos == 2) {
                    clear_state();
                    seuntech_instruction.setText(this.getResources().getString(R.string.st_confirm_pin));
                    current_pin_pos = 3;
                    return false;
                }

                if (current_pin_pos == 3) {
                    //check if pin 2 = 3
                    if (pincode3.equals(pincode2)) {
                        current_pin_pos = 1;
                        //create pin
                        if (managePin.setPin(pincode2)) {
                            onPinOk();
                            clear_all();
                            finish();
                        } else {
                            clear_state();
                            animate_activity();
                            return false;
                        }
                    } else {
                        pincode3 = null;
                        clear_state();
                        animate_activity();
                    }
                    //if not say error else current_pin_pos == 1; complete
                    return false;
                }
                break;

            case AppConf.AUTH_PIN:
                if (managePin.MyPin(pincode1).equals(managePin.getPin())) {
                    onPinOk();
                    clear_all();
                    finish();
                } else {
                    animate_activity();
                    onPinError();
                    clear_all();
                }
                break;
        }
        return false;

    }

    private void animate_activity() {
        seuntech_keyboard_view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake));
        Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vb.vibrate(400);
    }

    public void clear_all() {
        pincode1 = null;
        pincode2 = null;
        pincode3 = null;
        circle1.setBackgroundResource(R.drawable.pin_circle);
        circle2.setBackgroundResource(R.drawable.pin_circle);
        circle3.setBackgroundResource(R.drawable.pin_circle);
        circle4.setBackgroundResource(R.drawable.pin_circle);
    }

    public void clear_state() {
        circle1.setBackgroundResource(R.drawable.pin_circle);
        circle2.setBackgroundResource(R.drawable.pin_circle);
        circle3.setBackgroundResource(R.drawable.pin_circle);
        circle4.setBackgroundResource(R.drawable.pin_circle);
    }


    public int get_layout() {
        return R.layout.activity_pin;
    }


    protected void onPinError() {
        onPinFail(pintattempt++);
        Thread thread = new Thread() {
            public void run() {

            }
        };
        runOnUiThread(thread);
    }

    protected void onPinOk() {
        managePin.set_LastActivetime();
        onPinSuccess(pintattempt++);
        pintattempt = 1;
        Thread thread = new Thread() {
            public void run() {

            }
        };
        runOnUiThread(thread);
    }


    @Override
    public void finish() {
        super.finish();
    }


    private void clear_one() {
        int lenth = 0;

        if (pincode1 != null && current_pin_pos == 1) {
            lenth = pincode1.length();
            pincode1 = remove_last(pincode1);
        }

        if (pincode2 != null && current_pin_pos == 2) {
            lenth = pincode2.length();
            pincode2 = remove_last(pincode2);
        }

        if (pincode3 != null && current_pin_pos == 3) {
            lenth = pincode3.length();
            pincode3 = remove_last(pincode3);
        }

        switch (lenth) {
            case 1:
                circle1.setBackgroundResource(R.drawable.pin_circle);
                break;
            case 2:
                circle2.setBackgroundResource(R.drawable.pin_circle);
                break;
            case 3:
                circle3.setBackgroundResource(R.drawable.pin_circle);
                break;
            case 4:
                circle4.setBackgroundResource(R.drawable.pin_circle);
                break;
        }


    }


    public String remove_last(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length() - 1);
    }


    @Override
    public void onClick(View v) {
        // So we will make
        if (v.getId() == R.id.seuntech_button_0) {
            check_circle("0");
        } else if (v.getId() == R.id.seuntech_button_1) {
            check_circle("1");
        } else if (v.getId() == R.id.seuntech_button_2) {
            check_circle("2");
        } else if (v.getId() == R.id.seuntech_button_3) {
            check_circle("3");
        } else if (v.getId() == R.id.seuntech_button_4) {
            check_circle("4");
        } else if (v.getId() == R.id.seuntech_button_5) {
            check_circle("5");
        } else if (v.getId() == R.id.seuntech_button_6) {
            check_circle("6");
        } else if (v.getId() == R.id.seuntech_button_7) {
            check_circle("7");
        } else if (v.getId() == R.id.seuntech_button_8) {
            check_circle("8");
        } else if (v.getId() == R.id.seuntech_button_9) {
            check_circle("9");
        } else if (v.getId() == R.id.seuntech_button_clear) {
            clear_one();
        } else if (v.getId() == R.id.seuntech_forgot) {
            onForgot();
        } else {

        }

    }

    public abstract void onForgot();

    public abstract void onPinFail(int attempts);

    public abstract void onPinSuccess(int attempts);

}