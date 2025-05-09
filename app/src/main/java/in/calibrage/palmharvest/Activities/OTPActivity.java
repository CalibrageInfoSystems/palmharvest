package in.calibrage.palmharvest.Activities;

import androidx.annotation.RequiresApi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
//    private ImageView backImg;
    TextView otp_desc;
    String Reg_mobilenumber;
    private SpotsDialog mdilogue;
    String F_number,S_number;
    String    userOtp;
    EditText otp1, otp2, otp3, otp4, otp5, otp6;
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
//        backImg = (ImageView) findViewById(R.id.back);
        otp_desc =(TextView)findViewById(R.id.otp_desc);
//        pinEntry = findViewById(R.id.txt_pin_entry);
//        pinEntry.requestFocus();
        mdilogue = new SpotsDialog(this, R.style.Custom);

        // Get intent extras
//        Intent intent = getIntent();
//        String mobileNumber = intent.getStringExtra("mobile");
//        otp_title.setText("Enter the 6 Digits Code Sent your Registered Mobile Number(s) " + mobileNumber);

        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        otp5 = findViewById(R.id.otp5);
        otp6 = findViewById(R.id.otp6);

        setOtpFocusListeners();

        SharedPreferences pref = getSharedPreferences("FARMER", MODE_PRIVATE);

        userOtp = pref.getString("userName", "");

    }


    private void setOtpFocusListeners() {
        otp1.addTextChangedListener(new SimpleTextWatcher(otp2));
        otp2.addTextChangedListener(new SimpleTextWatcher(otp3));
        otp3.addTextChangedListener(new SimpleTextWatcher(otp4));
        otp4.addTextChangedListener(new SimpleTextWatcher(otp5));
        otp5.addTextChangedListener(new SimpleTextWatcher(otp6));

        setBackspaceHandler(otp2,otp1);
        setBackspaceHandler(otp3,otp2);
        setBackspaceHandler(otp4,otp3);
        setBackspaceHandler(otp5,otp4);
        setBackspaceHandler(otp6,otp5);
    }

//    private void setBackspaceHandler(EditText current, EditText previous) {
//        current.setOnKeyListener((v, keyCode, event) -> {
//            if (event.getAction() == KeyEvent.ACTION_DOWN &&
//                    keyCode == KeyEvent.KEYCODE_DEL &&
//                    current.getText().toString().isEmpty()) {
//                previous.requestFocus();
//                previous.setText("");
//                return true;
//            }
//            return false;
//        });
//    }
private void setBackspaceHandler(final EditText current, final EditText previous) {
    current.setOnKeyListener(new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_DEL &&
                    current.getText().toString().isEmpty()) {
                previous.requestFocus();
                previous.setText("");
                return true;
            }
            return false;
        }
    });
}

    private String getOtpCode() {
        return otp1.getText().toString() + otp2.getText().toString() + otp3.getText().toString() +
                otp4.getText().toString() + otp5.getText().toString() + otp6.getText().toString();
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
//        sub_Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (pinEntry.getText() != null & pinEntry.getText().toString().trim() != "" & !TextUtils.isEmpty(pinEntry.getText())) {
//                    if (isOnline())
//                        GetUserOtp();
//
//                    else {
//                        showDialog(OTPActivity.this, getResources().getString(R.string.Internet));
//                    }
//                } else {
//                    showDialog(OTPActivity.this, getResources().getString(R.string.ente_pin));
//
//                }
//            }
//        });
        sub_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String otpCode = getOtpCode();
                if (TextUtils.isEmpty(otpCode)) {
                    showDialog(OTPActivity.this, getResources().getString(R.string.ente_pin));
                } else if (otpCode.length() < 6) {
                    showDialog(OTPActivity.this, "Please enter a valid OTP.");
                } else {
                    if (isOnline()) {
                        GetUserOtp(otpCode);
                    }
                    else {
                        showDialog(OTPActivity.this, getResources().getString(R.string.Internet));
                    }
                }
            }
        });

//        backImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
    }

    private void GetUserOtp(String otpCode) {
        mdilogue.show();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getUserOTPResponsePage(APIConstantURL.get_userOTP + userOtp + "/" + otpCode)
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


    private class SimpleTextWatcher implements android.text.TextWatcher {
        private final EditText nextEditText;

        public SimpleTextWatcher(EditText nextEditText) {
            this.nextEditText = nextEditText;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.length() > 0 && nextEditText != null) {
                nextEditText.requestFocus();
            }
        }

    }
}
