package in.calibrage.palmharvest.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import in.calibrage.palmharvest.R;
import in.calibrage.palmharvest.common.BaseActivity;
import in.calibrage.palmharvest.common.Constants;
import in.calibrage.palmharvest.localData.SharedPrefsData;

import static in.calibrage.palmharvest.common.CommonUtil.updateResources;


public class LanguageActivity extends BaseActivity {
    public int langCode = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        final LinearLayout rbEng = findViewById(R.id.english);
        final LinearLayout rbTelugu = findViewById(R.id.telugu);
        final Button btnNext = findViewById(R.id.btnNext);


        // SharedPrefsData.putBool(this, Constants.WELCOME,true);
        rbEng.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                rbTelugu.setBackgroundResource(R.drawable.border_gray);
                rbEng.setBackgroundColor(Color.rgb(60, 180, 110));
                langCode = 100;
            }
        });

        rbTelugu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                rbEng.setBackgroundResource(R.drawable.border_gray);
                rbTelugu.setBackgroundColor(Color.rgb(60, 180, 110));

                langCode = 101;

            }
        });



        btnNext.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(langCode != -1){
                    String selectedLang = getLanguage(langCode);
                    updateResources(LanguageActivity.this, selectedLang);
                    SharedPrefsData.getInstance(LanguageActivity.this).updateIntValue(LanguageActivity.this, "lang", 1);
                    SharedPrefsData.putBool(LanguageActivity.this, Constants.WELCOME,true);
                    Intent refresh = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(refresh);
                    finish();
                } else{
                    Toast.makeText(getApplicationContext(), "Please select language", Toast.LENGTH_SHORT).show();
                }


                //Toast.makeText(getApplicationContext(), R.string.language_notification, Toast.LENGTH_SHORT).show();

            }
        });



    }

    public void onBackPressed() {
        super.onBackPressed();
        //  moveTaskToBack(true);

    }

    public static String getLanguage(int code) {
        switch (code) {
            case 100:
                return "en-US";
            case 101:
                return "te";
            default:
                return "te";
        }
    }
}
