package in.calibrage.palmharvest.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import in.calibrage.palmharvest.Model.LabourLeaderLoginRequest;
import in.calibrage.palmharvest.Model.LabourLeaderLoginResponse;
import in.calibrage.palmharvest.R;
import in.calibrage.palmharvest.common.BaseActivity;
import in.calibrage.palmharvest.service.ApiService;
import in.calibrage.palmharvest.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LoginActivity extends BaseActivity {
    private EditText userNameEdt, passwordEdt;
    private Button loginBtn;
    private Subscription mSubscription;
    private SpotsDialog mdilogue;
    private String otp;

    String mobile_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /* intializing and assigning ID's */
        initViews();
        /* Navigation's and using the views */
        setViews();
    }

    private void initViews() {
        loginBtn = findViewById(R.id.loginBtn);
        userNameEdt = findViewById(R.id.username_edittxt);
        passwordEdt = findViewById(R.id.password_edittxt);
        mdilogue = new SpotsDialog(this, R.style.Custom);
//        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
//                .setContext(this)
//                .setTheme(R.style.Custom)
//                .build();
    }

    private void setViews() {


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOnline()) {
                    if (validations()) {

                        labourLeaderLogin();
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("FARMER", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("userName", userNameEdt.getText().toString());// Saving string data of your editext
                        editor.commit();
                    }
                } else {
                    showDialog(LoginActivity.this, getResources().getString(R.string.Internet));
                }


            }
        });
    }

    private boolean validations() {
        if (TextUtils.isEmpty(userNameEdt.getText().toString())) {
            // userNameEdt.setError(getString(R.string.err_please_enter_username));
            showDialog(LoginActivity.this, getResources().getString(R.string.err_please_enter_username));
            return false;
//        } else if (TextUtils.isEmpty(passwordEdt.getText().toString().trim())) {
//            //  passwordEdt.setError(getString(R.string.err_please_enter_password));
//            showDialog(LoginActivity.this, getResources().getString(R.string.err_please_enter_password));
//            return false;
        }
        return true;
    }


    private void labourLeaderLogin() {
        mdilogue.show();
        JsonObject object = labourLeaderLoginPageObject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getLabourLeaderResponsePage(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LabourLeaderLoginResponse>() {
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
                        mdilogue.dismiss();
                    }

                    @Override
                    public void onNext(final LabourLeaderLoginResponse labourLeaderLoginResponse) {
                        mdilogue.dismiss();
                        if (labourLeaderLoginResponse.getIsSuccess()) {

                            if (labourLeaderLoginResponse.getResult()!=null) {
                                mobile_number = labourLeaderLoginResponse.getResult();

                                Log.d("mobile_number===", mobile_number);
                            }
                            else {
                                showDialog(LoginActivity.this, "No Register Mobile Number for Send Otp");
                            }
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    Intent i = new Intent(LoginActivity.this, OTPActivity.class);
                                    i.putExtra("mobile", mobile_number);
                                    startActivity(i);
                                    /* Create an Intent that will start the Menu-Activity. */
//                                    SharedPrefsData.putBool(LoginActivity.this, Constants.IS_LOGIN, true);
//                                    SharedPrefsData.saveeCatagories(LoginActivity.this, labourLeaderLoginResponse);
//                                    SharedPrefsData.getInstance(LoginActivity.this).updateStringValue(LoginActivity.this, Constants.createduser_ID, labourLeaderLoginResponse.getResult() + "");
//                                    Log.e("created_useid==", labourLeaderLoginResponse.getResult() + "");
                                  //  startActivity(new Intent(getApplicationContext(), OTPActivity.class));
                                    finish();
                                }
                            }, 300);

                        } else {
                            showDialog(LoginActivity.this, getResources().getString(R.string.Invalid_user));
                        }
                    }

                });
    }

    private JsonObject labourLeaderLoginPageObject() {
        LabourLeaderLoginRequest labourLeaderLoginRequestModel = new LabourLeaderLoginRequest();
        labourLeaderLoginRequestModel.setUserName(userNameEdt.getText().toString().trim());
        return new Gson().toJsonTree(labourLeaderLoginRequestModel).getAsJsonObject();
    }


//    private void harvest_Login() {
//        mdilogue.show();
//        JsonObject object = loginPageObject();
//        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
//        mSubscription = service.getLoginPage(object)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Labourloginresponse>() {
//                    @Override
//                    public void onCompleted() {
//                        mdilogue.dismiss();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (e instanceof HttpException) {
//                            ((HttpException) e).code();
//                            ((HttpException) e).message();
//                            ((HttpException) e).response().errorBody();
//                            try {
//                                ((HttpException) e).response().errorBody().string();
//                            } catch (IOException e1) {
//                                e1.printStackTrace();
//                            }
//                            e.printStackTrace();
//                        }
//                        mdilogue.dismiss();
//                    }
//
//                    @Override
//                    public void onNext(final Labourloginresponse loginResponse) {
//                        mdilogue.dismiss();
//                        if (loginResponse.getIsSuccess()) {
//                            new Handler().postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    /* Create an Intent that will start the Menu-Activity. */
//                                    SharedPrefsData.putBool(LoginActivity.this, Constants.IS_LOGIN, true);
//                                    SharedPrefsData.saveCatagories(LoginActivity.this, loginResponse);
//                                    SharedPrefsData.getInstance(LoginActivity.this).updateStringValue(LoginActivity.this, Constants.createduser_ID, loginResponse.getResult().getUserInfos().getId() + "");
//                                    Log.e("created_useid==", loginResponse.getResult().getUserInfos().getId() + "");
//                                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                                    finish();
//                                }
//                            }, 300);
//
//                        } else {
//                            showDialog(LoginActivity.this, getResources().getString(R.string.Invalid_user));
//                        }
//                    }
//
//
//                });
//    }
//
//    private JsonObject loginPageObject() {
//        LoginRequest requestModel = new LoginRequest();
//        requestModel.setUserName(userNameEdt.getText().toString().trim());
//        requestModel.setPassword(passwordEdt.getText().toString().trim());
//        requestModel.setRoleId(2);
//        return new Gson().toJsonTree(requestModel).getAsJsonObject();
//    }

    private Boolean exit = false;

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            //   Toast.makeText(this, "Press Back again to Exit.", Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
}

