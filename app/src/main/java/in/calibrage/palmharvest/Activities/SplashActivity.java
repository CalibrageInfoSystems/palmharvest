package in.calibrage.palmharvest.Activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import in.calibrage.palmharvest.R;
import in.calibrage.palmharvest.common.BaseActivity;
import in.calibrage.palmharvest.common.Constants;
import in.calibrage.palmharvest.common.TypeWriter;
import in.calibrage.palmharvest.localData.SharedPrefsData;

import static in.calibrage.palmharvest.common.CommonUtil.getDeviceDensityString;
import static in.calibrage.palmharvest.common.CommonUtil.updateResources;


public class SplashActivity extends BaseActivity {
    public static final String TAG = SplashActivity.class.getSimpleName();
    private ImageView imgLogo;
    private TypeWriter txt_name, txt_desc;
    private Context context;
    private static int SPLASH_TIME_OUT = 6000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        init();
        setViews();
        Log.d(TAG, "------ analysis --------- >> GetDimens SIZE :" + getDeviceDensityString(this));
    }



    private void init() {
        imgLogo = findViewById(R.id.img_logo);
        txt_name = findViewById(R.id.txt_name);
        txt_desc = findViewById(R.id.txt_desc);
    }

    private void setViews() {
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.logo_spalsh);
        imgLogo.startAnimation(myanim);
        txt_name.setText("");
        txt_name.setCharacterDelay(30);
        txt_name.animateText("3F OIL PALM");

        txt_desc.setText("");
        txt_desc.setCharacterDelay(60);
        txt_desc.animateText("Sowing for a Better Future");


        final boolean is_login = SharedPrefsData.getBool(SplashActivity.this, Constants.IS_LOGIN);
        final boolean welcome = SharedPrefsData.getBool(SplashActivity.this, Constants.WELCOME);
        final int langID = SharedPrefsData.getInstance(SplashActivity.this).getIntFromSharedPrefs("lang");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (langID == 2)
                    updateResources(SplashActivity.this, "te");
                else
                    updateResources(SplashActivity.this, "en-US");

                if (is_login) {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                } else {
                    if (welcome) {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, LanguageActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }

                }
            }


        }, SPLASH_TIME_OUT);
    }

}
