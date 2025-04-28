package in.calibrage.palmharvest.Activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import in.calibrage.palmharvest.BuildConfig;
import in.calibrage.palmharvest.Model.CloseLabourRequest;
import in.calibrage.palmharvest.Model.CloseLabourResponse;
import in.calibrage.palmharvest.Model.GetUserOTPResponse;
import in.calibrage.palmharvest.Model.SyncFarmerDetailsRequest;
import in.calibrage.palmharvest.Model.SyncFarmerDetailsResponse;
import in.calibrage.palmharvest.Model.SyncLabourRequest;
import in.calibrage.palmharvest.Model.SyncLabourResponse;
import in.calibrage.palmharvest.Model.SyncLabourSerivesRequest;
import in.calibrage.palmharvest.Model.SyncLabourServicesResponse;
import in.calibrage.palmharvest.R;
import in.calibrage.palmharvest.common.BaseActivity;
import in.calibrage.palmharvest.common.Constants;
import in.calibrage.palmharvest.database.DatabaseQueryClass;

import in.calibrage.palmharvest.fragments.HomeFragment;
import in.calibrage.palmharvest.fragments.ProfileFragment;
import in.calibrage.palmharvest.fragments.RequestsFragment;
import in.calibrage.palmharvest.localData.SharedPrefsData;
import in.calibrage.palmharvest.service.ApiService;
import in.calibrage.palmharvest.service.ServiceFactory;
import in.calibrage.palmharvest.sync.SyncRequestHeaderResponse;
import in.calibrage.palmharvest.sync.SyncRequestObject;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;
import static in.calibrage.palmharvest.common.CommonUtil.updateResources;


public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = HomeActivity.class.getSimpleName();
    private DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(this);
    private NavigationView nv;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private ImageView img_profile;
    private TextView txt_name, txt_phone, txt_adrs, dialogMessage, txt_requestHeader_count, txt_labourRequest_count;
    private Toolbar toolbar;
    private BottomNavigationView bottom_navigation;
    Integer mSelectedItem;
    private GetUserOTPResponse catagoriesList;
    private Button ok_btn, cancel_btn;

    private TextView textView;

    private Subscription mSubscription;
    private FrameLayout content_frame;
    private FragmentManager fragmentManager;
    List<SyncLabourResponse.ListResult> data;
    List<SyncRequestHeaderResponse.ListResult> farmerdata;
    List<SyncLabourServicesResponse.ListResult> labourServices;
    List<SyncFarmerDetailsResponse.ListResult> farmerDetails;

    private Integer id;
    AppCompatTextView app_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        init();
        setViews();
        // showSyncDialog();

        // showSyncDialog();

        // syncRequestHeaders();

        // getSyncTimeString();
        //  getCount();
//          getSyncLabourRequest();
//          syncRequestHeaders();
//         // closeLabourRequest();
//        getSyncLabourServices();
//        getSyncFarmerDetails();


        data = databaseQueryClass.getAllLabourRequests();
        farmerdata = databaseQueryClass.getAllRequestHeaders();
        labourServices = databaseQueryClass.getAllLabourServices();
        farmerDetails = databaseQueryClass.getAllFarmerDetails();
        textView = findViewById(R.id.calender_title);
    }


    private void closeLabourRequest() {

        JsonObject object = closeLabourRequestObjectPage();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.closeLabourResponsePage(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CloseLabourResponse>() {
                    @Override
                    public void onCompleted() {

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
                    }

                    @Override
                    public void onNext(final CloseLabourResponse closeLabourResponse) {
                        if (closeLabourResponse.getIsSuccess()) {

                        } else {
                        }

                    }


                });

    }

    private JsonObject closeLabourRequestObjectPage() {
        CloseLabourRequest requestModel = new CloseLabourRequest();
        requestModel.setId(10);
        requestModel.setRequestCode("REQVWGNJDC00030098L10");
        requestModel.setStartDate("2019-11-29T00:00:00");
        requestModel.setDurationId(21);
        requestModel.setLeaderId(2);
        requestModel.setPin(985686);
        requestModel.setJobDoneDate("2019-11-12T18:30:00");
        requestModel.setCreatedByUserId(null);
        requestModel.setCreatedDate("2019-11-09T12:31:34.793");
        requestModel.setUpdatedByUserId(6);
        requestModel.setUpdatedDate("2019-11-09T17:22:26.203");
        requestModel.setAssignedDate("2019-11-11T18:30:00");
        requestModel.setNetWeight(0.025);
        requestModel.setAmount(null);
        requestModel.setHarvestingAmount(280.0);
        requestModel.setPruningAmount(1800.0);
        requestModel.setUnKnown1Amount(null);
        requestModel.setUnKnown2Amount(null);
        requestModel.setTreesCount(10);
        requestModel.setServerUpdatedStatus(0);

        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    private void getSyncLabourRequest() {

        JsonObject object = syncLabourRequestPageObject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getSyncLabourRequestPage(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SyncLabourResponse>() {
                    @Override
                    public void onCompleted() {

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
                    }

                    @Override
                    public void onNext(final SyncLabourResponse syncLabourResponse) {
                        if (syncLabourResponse.getIsSuccess()) {
                            for (SyncLabourResponse.ListResult labourResponce : syncLabourResponse.getListResult()
                            ) {
                                databaseQueryClass.deleteLabourusingRequestCode(labourResponce.getRequestCode());
                                databaseQueryClass.insertLabourRequest(labourResponce);
                            }
                        } else {
                        }

                    }
                });

    }

    private JsonObject syncLabourRequestPageObject() {
        SyncLabourRequest requestModel = new SyncLabourRequest();
        requestModel.setDate("");
        requestModel.setUserId(SharedPrefsData.getCatagoriess(this).getResult().getId());
        requestModel.setIndex(0);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }


    private void getSyncLabourServices() {

        JsonObject object = syncLabourServicesPageObject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getsyncLabourServicesResponsePage(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SyncLabourServicesResponse>() {
                    @Override
                    public void onCompleted() {

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
                    }

                    @Override
                    public void onNext(final SyncLabourServicesResponse syncLabourServicesResponse) {
                        if (syncLabourServicesResponse.getIsSuccess()) {
                            for (SyncLabourServicesResponse.ListResult labourServiceResponse : syncLabourServicesResponse.getListResult()
                            ) {

                                databaseQueryClass.insertLabourServices(labourServiceResponse);

                            }
                        } else {
                        }

                    }
                });

    }

    private JsonObject syncLabourServicesPageObject() {
        SyncLabourSerivesRequest requestModel = new SyncLabourSerivesRequest();
        requestModel.setDate("");
        requestModel.setUserId(SharedPrefsData.getCatagoriess(this).getResult().getId());
        requestModel.setIndex(0);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    private void getSyncFarmerDetails() {

        JsonObject object = syncFarmerDetailsPageObject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getsyncFarmerDetailsResponsePage(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SyncFarmerDetailsResponse>() {
                    @Override
                    public void onCompleted() {

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
                    }

                    @Override
                    public void onNext(final SyncFarmerDetailsResponse syncFarmerDetailsResponse) {
                        if (syncFarmerDetailsResponse.getIsSuccess()) {
                            for (SyncFarmerDetailsResponse.ListResult farmerDetailsResponse : syncFarmerDetailsResponse.getListResult()
                            ) {

                                databaseQueryClass.insertFarmerDetails(farmerDetailsResponse);
                            }
                        } else {
                        }

                    }
                });

    }

    private JsonObject syncFarmerDetailsPageObject() {
        SyncFarmerDetailsRequest requestModel = new SyncFarmerDetailsRequest();
        requestModel.setDate("");
        requestModel.setUserId(SharedPrefsData.getCatagoriess(this).getResult().getId());
        requestModel.setIndex(0);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }


    private void init() {
        app_version = findViewById(R.id.app_version);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        nv = (NavigationView) findViewById(R.id.nv);
        dl = (DrawerLayout) findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.app_name, R.string.app_name);

        t.syncState();

        View headerLayout = nv.inflateHeaderView(R.layout.navigation_header);
        txt_name = headerLayout.findViewById(R.id.txt_name);
        txt_phone = headerLayout.findViewById(R.id.txt_phone);
        txt_adrs = headerLayout.findViewById(R.id.txt_adrs);


        txt_requestHeader_count = findViewById(R.id.txt_requestHeader_count);

    }

    private void setViews() {

        String versionName = BuildConfig.VERSION_NAME;
        app_version.setText(getResources().getString(R.string.App_version)+ " "+versionName);
        BottomNavigationViewHelper bottomNavigationViewHelper = new BottomNavigationViewHelper();
        bottomNavigationViewHelper.disableShiftMode(bottom_navigation);
        initToolBar();
        nv.setNavigationItemSelectedListener(this);

        catagoriesList = SharedPrefsData.getCatagoriess(HomeActivity.this);

        String name = catagoriesList.getResult().getFirstName() + " " + catagoriesList.getResult().getMiddleName() + " " + catagoriesList.getResult().getLastname();
        txt_name.setText(name.replace("null", ""));

Log.e("*****mobile","------ analysis ---------  :"+ catagoriesList.getResult().getMobileNumber()+"==="+catagoriesList.getResult().getContactNumber());
        if (catagoriesList.getResult().getMobileNumber() == null && TextUtils.isEmpty(catagoriesList.getResult().getMobileNumber()))
            txt_phone.setVisibility(View.GONE);
        else
            txt_phone.setText(catagoriesList.getResult().getMobileNumber());

        if (catagoriesList.getResult().getContactNumber() == null)
            txt_adrs.setVisibility(View.GONE);
        else
            txt_adrs.setText(catagoriesList.getResult().getContactNumber());


        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {

                    case R.id.action_home: {
                        mSelectedItem = item.getItemId();
                        viewFragment(new HomeFragment(), HomeFragment.TAG);
                        break;
                    }
                    case R.id.action_profile: {
                        mSelectedItem = item.getItemId();
                        viewFragment(new ProfileFragment(), ProfileFragment.TAG);
                        break;
                    }

                    case R.id.action_requests: {
                        databaseQueryClass.getAllLabourRequests();
                        databaseQueryClass.getAllRequestHeaders();
                        mSelectedItem = item.getItemId();
                        viewFragment(new RequestsFragment(), RequestsFragment.TAG);
                        break;
                    }
                    case R.id.action_logout: {
                        mSelectedItem = item.getItemId();
                        bottom_navigation.setSelectedItemId(R.id.action_requests);
                        logOutDialog();
                        break;
                    }
                }
                return true;
            }
        });
        fragmentManager = getSupportFragmentManager();
        content_frame = (FrameLayout) findViewById(R.id.content_frame);
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, homeFragment, "homeTag")
                .commit();
    }

    private void logOutDialog() {
        final Dialog dialog = new Dialog(HomeActivity.this, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_logout);
        dialogMessage = dialog.findViewById(R.id.dialogMessage);
        dialogMessage.setText(getString(R.string.alert_logout));
        cancel_btn = dialog.findViewById(R.id.cancel_btn);
        ok_btn = dialog.findViewById(R.id.ok_btn);
/**
 * @param OnClickListner
 */
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseQueryClass.deleteAllRequests();
                databaseQueryClass.deleteAllabour();
                databaseQueryClass.deleteAllFormerDetails();
                databaseQueryClass.deleteAllServices();
                databaseQueryClass.deleteAllabourDetails();
                databaseQueryClass.DeleteAllUsers();
                updateResources(getApplicationContext(), "en-US");
                SharedPrefsData.saveSyncdateToEMPTY(HomeActivity.this);
                SharedPrefsData.putBool(HomeActivity.this, Constants.IS_LOGIN, false);
                // SharedPrefsData.getInstance(getApplicationContext()).ClearData(HomeActivity.this);
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });
/**
 * @param OnClickListner
 */
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setTitle("");
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onClick(View v) {
                        if (!dl.isDrawerOpen(Gravity.START)) dl.openDrawer(Gravity.START);
                        else dl.closeDrawer(Gravity.END);
                    }
                }
        );
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Log.e("id===", String.valueOf(id));
        if (id == R.id.languageTitle) {

            selectLanguage();
            // Handle the camera action
        } else if (id == R.id.action_home) {

            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, homeFragment, null)
                    .commit();
            // Select home item
            bottom_navigation.setSelectedItemId(id);
            // finish();
            bottom_navigation.setSelectedItemId(R.id.action_home);
            if (this.dl.isDrawerOpen(GravityCompat.START))
                this.dl.closeDrawer(GravityCompat.START);

        } else if (id == R.id.action_profile) {


            mSelectedItem = item.getItemId();
            viewFragment(new RequestsFragment(), ProfileFragment.TAG);
            bottom_navigation.setSelectedItemId(R.id.action_profile);
            if (this.dl.isDrawerOpen(GravityCompat.START))
                this.dl.closeDrawer(GravityCompat.START);
        }

//        } else if (id == R.id.action_3f) {
//
//            mSelectedItem = item.getItemId();
//            viewFragment(new RequestsFragment(), My3FFragment.TAG);
//            bottom_navigation.setSelectedItemId(R.id.action_3f);
//            if (this.dl.isDrawerOpen(GravityCompat.START))
//                this.dl.closeDrawer(GravityCompat.START);
//        } else if (id == R.id.My_request) {
//            mSelectedItem = item.getItemId();
//            viewFragment(new RequestsFragment(), RequestsFragment.TAG);
//            bottom_navigation.setSelectedItemId(R.id.action_requests);
//            if (this.dl.isDrawerOpen(GravityCompat.START))
//                this.dl.closeDrawer(GravityCompat.START);
//        } \
        else if (id == R.id.nav_logout) {
            bottom_navigation.setSelectedItemId(R.id.action_requests);
            //popupdialog to show message to logout the application
            logOutDialog();
        }
        return true;

    }

    private void selectLanguage() {
        final Dialog dialog = new Dialog(HomeActivity.this, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_select_language);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setTitle("");

        // set the custom forgotPasswordDialog components - text, image and button
        final TextView rbEng = dialog.findViewById(R.id.rbEng);
        final TextView rbTelugu = dialog.findViewById(R.id.rbTelugu);


/**
 * @param OnClickListner
 */
        rbEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * "en" is the localization code for our default English language.
                 */
                updateResources(HomeActivity.this, "en-US");
                SharedPrefsData.getInstance(HomeActivity.this).updateIntValue(HomeActivity.this, "lang", 1);
                Intent refresh = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(refresh);
                finish();
                dialog.dismiss();
            }
        });

/**
 * @param OnClickListner
 */
        rbTelugu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * "te" is the localization code for our default Telugu language.
                 */
                updateResources(HomeActivity.this, "te");
                SharedPrefsData.getInstance(HomeActivity.this).updateIntValue(HomeActivity.this, "lang", 2);
                Intent refresh = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(refresh);
                finish();
                dialog.dismiss();
            }
        });


        dialog.show();
    }


    private void viewFragment(Fragment fragment, String name) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        // 1. Know how many fragments there are in the stack
        final int count = fragmentManager.getBackStackEntryCount();
        // 2. If the fragment is **not** "home type", save it to the stack
        if (name.equals(HomeFragment.TAG)) {
            fragmentTransaction.addToBackStack(name);
        }
        // Commit !
        fragmentTransaction.commit();
        // 3. After the commit, if the fragment is not an "home type" the back stack is changed, triggering the
        // OnBackStackChanged callback
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                // If the stack decreases it means I clicked the back button
                if (fragmentManager.getBackStackEntryCount() <= count) {
                    // pop all the fragment and remove the listener
                    fragmentManager.popBackStack(HomeFragment.TAG, POP_BACK_STACK_INCLUSIVE);
                    fragmentManager.removeOnBackStackChangedListener(this);
                    // set the home button selected
                    bottom_navigation.getMenu().getItem(0).setChecked(true);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

    public class BottomNavigationViewHelper {

        @SuppressLint("RestrictedApi")
        public void disableShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    //noinspection RestrictedApi
                    item.setSaveEnabled(false);
                    // set once again checked value, so view will be updated
                    //noinspection RestrictedApi
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("BNVHelper", "Unable to get shift mode field", e);
            } catch (IllegalAccessException e) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e);
            }
        }
    }

    private void syncRequestHeaders() {

        JsonObject object = syncRequestObject();
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getSyncRequestHeaders(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SyncRequestHeaderResponse>() {
                    @Override
                    public void onCompleted() {
                        // mdilogue.dismiss();
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
                        //  mdilogue.dismiss();
                    }

                    @Override
                    public void onNext(SyncRequestHeaderResponse syncRequestHeaderResponse) {

                        if (syncRequestHeaderResponse.getIsSuccess()) {
                            for (SyncRequestHeaderResponse.ListResult requestHeader :
                                    syncRequestHeaderResponse.getListResult()) {
                                databaseQueryClass.deleteRequestHeaderRequestCode(requestHeader.getRequestCode());
                                databaseQueryClass.insertRequestHeader(requestHeader);
                            }

                        }

                    }
                });

    }

    private JsonObject syncRequestObject() {

        SyncRequestObject requestModel = new SyncRequestObject();
        requestModel.setDate("");
        requestModel.setIndex(0);
        requestModel.setUserId(SharedPrefsData.getCatagoriess(this).getResult().getId());

        return new Gson().toJsonTree(requestModel).getAsJsonObject();

    }

    public void showSyncDialog() {
        final Dialog dialog = new Dialog(this, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.syncdialog);


        TextView lastSyncTime = dialog.findViewById(R.id.txt_lastSync);
        final Button btn_sync = dialog.findViewById(R.id.btn_sync);

        //lastSyncTime.setText(LastSyncTime());

        btn_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_sync.isActivated()) {
                    btn_sync.setEnabled(false);
                }
                getSyncLabourRequest();

            }
        });

        dialog.show();
    }
}
