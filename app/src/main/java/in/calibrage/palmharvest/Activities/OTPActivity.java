package in.calibrage.palmharvest.Activities;

import androidx.annotation.RequiresApi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dmax.dialog.SpotsDialog;
import in.calibrage.palmharvest.Model.GetUserOTPResponse;
import in.calibrage.palmharvest.R;
import in.calibrage.palmharvest.common.BaseActivity;
import in.calibrage.palmharvest.common.Constants;
import in.calibrage.palmharvest.common.PinEntryEditText;
import in.calibrage.palmharvest.localData.SharedPrefsData;
import in.calibrage.palmharvest.service.APIConstantURL;
import in.calibrage.palmharvest.service.ApiService;
import in.calibrage.palmharvest.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OTPActivity extends BaseActivity {
    public static final String TAG = OTPActivity.class.getSimpleName();
    private Subscription mSubscription;
    private Button sub_Btn;
    private String currentDate,Farmer_code;
    private PinEntryEditText pinEntry;
    private ImageView backImg;
    TextView otp_desc;
    String Reg_mobilenumber;
    private SpotsDialog mdilogue;
    String F_number,S_number;
    String    userOtp;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); final int langID = SharedPrefsData.getInstance(this).getIntFromSharedPrefs("lang");

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp);

        init();
        setview();
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init() {

        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//        String  Device_id= Settings.Secure.getString(this.getContentResolver(),
//                Settings.Secure.ANDROID_ID);
//        Log.e("deviece==id", Device_id);
        sub_Btn = (Button) findViewById(R.id.btn_otp_login);
        backImg = (ImageView) findViewById(R.id.back);
        otp_desc =(TextView)findViewById(R.id.otp_desc);
        pinEntry = findViewById(R.id.txt_pin_entry);
        pinEntry.requestFocus();
        mdilogue = new SpotsDialog(this, R.style.Custom);
//        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
//                .setContext(this)
//                .setTheme(R.style.Custom)
//                .build();


        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);

        userOtp = pref.getString("userName", "");

    }


    private void setview() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Reg_mobilenumber = extras.getString("mobile");
            if (Reg_mobilenumber.contains(",")) {
                String[] separated = Reg_mobilenumber.split(",");
                F_number = separated[0].replaceAll("\\d(?=(?:\\D*\\d){4})", "*");
                S_number = separated[1].replaceAll("\\d(?=(?:\\D*\\d){4})", "*");
                otp_desc.setText(getString(R.string.otp_desc) + " " + F_number + "," + S_number);
            } else {
                String number = Reg_mobilenumber
                        .replaceAll("\\d(?=(?:\\D*\\d){4})", "*");
                otp_desc.setText(getString(R.string.otp_desc) + " " + number);
            }
        }
        sub_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pinEntry.getText() != null & pinEntry.getText().toString().trim() != "" & !TextUtils.isEmpty(pinEntry.getText())) {
                    if (isOnline())
                        GetUserOtp();

                    else {
                        showDialog(OTPActivity.this, getResources().getString(R.string.Internet));
                    }
                } else {
                    showDialog(OTPActivity.this, getResources().getString(R.string.ente_pin));

                }
            }
        });
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetUserOtp() {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getUserOTPResponsePage(APIConstantURL.get_userOTP + userOtp + "/" + pinEntry.getText().toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetUserOTPResponse>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ((HttpException) e).code();
                            ((HttpException) e).message();
                            ((HttpException) e).response().errorBody();
                            try {
                                ((HttpException) e).response().errorBody().string();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            e.printStackTrace();
                        }
                        mdilogue.cancel();
                    }

                    @Override
                    public void onNext(final GetUserOTPResponse getUserOTPResponse) {
                        mdilogue.dismiss();
                        if (getUserOTPResponse.getIsSuccess()) {


                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    /* Create an Intent that will start the Menu-Activity. */
                                    SharedPrefsData.putBool(OTPActivity.this, Constants.IS_LOGIN, true);
                                    SharedPrefsData.saveCatagoriesforUserOtp(OTPActivity.this, getUserOTPResponse);
                                    SharedPrefsData.getInstance(OTPActivity.this).updateStringValue(OTPActivity.this, Constants.USER_ID, getUserOTPResponse.getResult().getId()+ "");
                                    Log.e("Formarcode==", getUserOTPResponse.getResult().getId()+ "");
                                    SharedPrefsData.getInstance(OTPActivity.this).updateStringValue(OTPActivity.this, "statecode", getUserOTPResponse.getResult().getId()+ "");
if(getUserOTPResponse.getResult().getRoleId() ==2)
{
    SharedPrefsData.putBool(OTPActivity.this, Constants.ISLABOUR, true);
}else
{
    SharedPrefsData.putBool(OTPActivity.this, Constants.ISLABOUR, false);
}

                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                                    finish();

                                }
                            }, 000);

                        } else {
                            showDialog(OTPActivity.this, getUserOTPResponse.getEndUserMessage());
                        }
                    }


                });


    }

}
