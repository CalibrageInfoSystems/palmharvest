package in.calibrage.palmharvest.Activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import in.calibrage.palmharvest.Model.CloseLabourResponse;
import in.calibrage.palmharvest.Model.GetCountRequest;
import in.calibrage.palmharvest.Model.GetCountResponse;
import in.calibrage.palmharvest.Model.LabourLeadersync;
import in.calibrage.palmharvest.Model.SyncFarmerDetailsRequest;
import in.calibrage.palmharvest.Model.SyncFarmerDetailsResponse;
import in.calibrage.palmharvest.Model.SyncLabourLeaderDetailsRequest;
import in.calibrage.palmharvest.Model.SyncLabourLeaderDetailsResponse;
import in.calibrage.palmharvest.Model.SyncLabourRequest;
import in.calibrage.palmharvest.Model.SyncLabourResponse;
import in.calibrage.palmharvest.Model.SyncLabourSerivesRequest;
import in.calibrage.palmharvest.Model.SyncLabourServicesResponse;
import in.calibrage.palmharvest.R;
import in.calibrage.palmharvest.common.BaseActivity;
import in.calibrage.palmharvest.common.DateTimeUtil;
import in.calibrage.palmharvest.database.DatabaseQueryClass;
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

import static in.calibrage.palmharvest.common.DateTimeUtil.onGetCurrentDate;
import static in.calibrage.palmharvest.common.MLog.onPrintDebugLog;

public class SyncActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = SyncActivity.class.getSimpleName();
    private DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(this);
    private TextView txt_header_count, txt_labour_count, txt_service_count, txt_details_count, txt_last_sync;
    private Button btn_sync, btn_clear, btn_sync_all, btn_forwardsync;
    private Context ctx;
    private TextView txt_title;
    private boolean syncInProgress = false;

    private boolean syncStatus = false;
    ProgressDialog progressDialog;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private Subscription mSubscription;
    private int Requestsize = 0;
    private int LabourRequestsize = 0;
    private int ServiceRequestsize = 0;
    private int FormerRequestsize = 0;
    private int LabourCountSize = 0;

    private int Labour_Request_index = 0;
    private int Request_Header_index = 0;
    private int Labour_Services_index = 0;
    private int Farmer_Details_index = 0;
    private int Labour_Count_index = 0;
    private SpotsDialog mdilogue;
    ImageView back;
    private TextView dialogMessage;

    private Button ok_btn, cancel_btn;
    private String currentSYNCdate = "",newsyncDate="";
    private CheckBox checkbox;
    private LinearLayout lyt_sync_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
        init();
        setviews();

        currentSYNCdate = SharedPrefsData.onGetSyncDate(ctx);
        newsyncDate = onGetCurrentDate(ctx);
        Log.d(TAG, " **************** current sync Date :" + currentSYNCdate);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getResources().getString(R.string.pleasewait));
        progressDialog.setMessage(getResources().getString(R.string.downloadvideo));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        mdilogue = new SpotsDialog(this, R.style.Custom);
//        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
//                .setContext(this)
//                .setCancelable(false)
//                .setTheme(R.style.Custom)
//                .build();
        Log.d(TAG, "user ID:" + SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
        //onSyncAllData(true);
        //getSyncLabourRequest();
        //getSyncLabourServices();
        //getSyncFarmerDetails();
        // syncRequestHeaders();


        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

    }


    private void init() {
        ctx = this;
        btn_sync = findViewById(R.id.btn_sync);
        btn_sync_all = findViewById(R.id.btn_sync_all);
        checkbox = findViewById(R.id.checkbox);
        btn_forwardsync = findViewById(R.id.btn_forwardsync);
        btn_clear = findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(this);
        btn_sync.setOnClickListener(this);
        btn_sync_all.setOnClickListener(this);
        btn_forwardsync.setOnClickListener(this);
        txt_header_count = findViewById(R.id.txt_header_count);
        txt_labour_count = findViewById(R.id.txt_labour_count);
        txt_service_count = findViewById(R.id.txt_service_count);
        txt_details_count = findViewById(R.id.txt_details_count);
        txt_last_sync = findViewById(R.id.txt_last_sync);
        lyt_sync_info = findViewById(R.id.lyt_sync_info);

        txt_header_count.setText(": " + databaseQueryClass.getRequestCount());
        txt_labour_count.setText(": " + databaseQueryClass.getPendingRequestCount());
        txt_service_count.setText(": " + databaseQueryClass.getJobDoneRequestCount());
        txt_details_count.setText(": " + databaseQueryClass.getFormerCount());
        txt_last_sync.setText("" + DateTimeUtil.LastSyncTime(ctx));

    }

    public void updateCount() {

        txt_header_count.setText(": " + databaseQueryClass.getRequestCount());
        txt_labour_count.setText(": " + databaseQueryClass.getPendingRequestCount());
        txt_service_count.setText(": " + databaseQueryClass.getJobDoneRequestCount());
        txt_details_count.setText(": " + databaseQueryClass.getFormerCount());
        txt_last_sync.setText("" + DateTimeUtil.LastSyncTime(ctx));
       // txt_last_sync.setText("" + DateTimeUtil.LastSyncTime(ctx));

    }

    private void setviews() {
        checkbox.setChecked(false);
        lyt_sync_info.setVisibility(View.GONE);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    lyt_sync_info.setVisibility(View.VISIBLE);
                else
                    lyt_sync_info.setVisibility(View.GONE);

            }
        });

    }

    private void startSyncAnimation(View view) {

        Animation rotation = AnimationUtils
                .loadAnimation(ctx,
                        R.anim.ic_play_sound_animation);
        view.startAnimation(rotation);
        syncInProgress = true;
    }

    private void stopBtnAnimation(View view) {
        view.clearAnimation();
        syncInProgress = false;

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_clear:
                if (isOnline())
                    clear_poop();
                else
                    Toast.makeText(ctx, getString(R.string.Internet), Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_sync:
                if (isOnline()) {
                    if (databaseQueryClass.getPendingRequests().size() > 0) {
                        //   onClearAllData();
                        closeLabourRequest();

                        // updateCount();
                        //getCount();
                    } else {
                        updateCount();
                        getCount();
                        updateCount();

                    }
//                    getCount();
                } else
                    Toast.makeText(ctx, getString(R.string.Internet), Toast.LENGTH_SHORT).show();

                break;

            case R.id.btn_forwardsync:
                if (databaseQueryClass.getPendingRequests().size() > 0) {
                    if (isOnline()) {
                        updateCount();
                        closeLabourRequest();
                        updateCount();
                    } else
                        Toast.makeText(ctx,  getString(R.string.Internet), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(ctx, getString(R.string.All_updated), Toast.LENGTH_SHORT).show();

                break;

            case R.id.btn_sync_all:
                Requestsize = 0;
                LabourRequestsize = 0;
                ServiceRequestsize = 0;
                FormerRequestsize = 0;
                LabourCountSize = 0;
                Labour_Request_index = 0;
                Request_Header_index = 0;
                Labour_Services_index = 0;
                Farmer_Details_index = 0;
                Labour_Count_index = 0;
                onClearAllData();
                SharedPrefsData.saveSyncdateToEMPTY(this);
                getCount();
                updateCount();
                break;


        }
    }

    private void clear_poop() {
        final Dialog dialog = new Dialog(SyncActivity.this, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_logout);
        dialogMessage = dialog.findViewById(R.id.dialogMessage);
        dialogMessage.setText(getString(R.string.alert_sync));
        cancel_btn = dialog.findViewById(R.id.cancel_btn);
        ok_btn = dialog.findViewById(R.id.ok_btn);
/**
 * @param OnClickListner
 */
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClearAllData();
                dialog.dismiss();
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

    private void onClearAllData() {
        Requestsize = 0;
        LabourRequestsize = 0;
        ServiceRequestsize = 0;
        FormerRequestsize = 0;
        LabourCountSize = 0;
        Labour_Request_index = 0;
        Request_Header_index = 0;
        Labour_Services_index = 0;
        Farmer_Details_index = 0;
        Labour_Count_index = 0;
        databaseQueryClass.deleteAllRequests();
        databaseQueryClass.deleteAllabour();
        databaseQueryClass.deleteAllFormerDetails();
        databaseQueryClass.deleteAllServices();
        databaseQueryClass.deleteAllabourDetails();
      //  databaseQueryClass.DeleteAllUsers();

        SharedPrefsData.saveSyncdateToEMPTY(this);

        getCount();
    }

//    private void onSyncAllData(boolean syncall) {
//        if (isOnline())
//            getCount();
//        else
//            Toast.makeText(ctx, getString(R.string.Internet), Toast.LENGTH_SHORT).show();
//    }

    private void getCount() {
        mdilogue.show();
        JsonObject object = getCountObject();
        ApiService service = ServiceFactory.createRetrofitService(ctx, ApiService.class);
        mSubscription = service.getCountPage(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetCountResponse>() {
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
                        mdilogue.cancel();
                    }


                    @Override
                    public void onNext(final GetCountResponse getCountResponse) {

                        try {
                            if (getCountResponse != null) {
                                if (getCountResponse.getRequestHeader().getCount() > 0) {
                                    Requestsize = getCountResponse.getRequestHeader().getCount();
                                    LabourRequestsize = getCountResponse.getLabourRequest().getCount();
                                    FormerRequestsize = getCountResponse.getFarmerDetails().getCount();
                                    ServiceRequestsize = getCountResponse.getLabourServiceXref().getCount();
                                    LabourCountSize = getCountResponse.getLabourLeaderXref().getCount();
                                    Log.e("LabourCountSize========",LabourCountSize+"");
                                    syncRequestHeaders();

                                } else {
                                    Toast.makeText(ctx,getString(R.string.updated_data) , Toast.LENGTH_SHORT).show();
                                    mdilogue.dismiss();
                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            onPrintDebugLog(TAG, "getCount Error :", e.getLocalizedMessage());
                        }
                    }
                });
    }

    private JsonObject getCountObject() {
        currentSYNCdate = SharedPrefsData.onGetSyncDate(ctx);
        GetCountRequest requestModel = new GetCountRequest();
        requestModel.setDate(currentSYNCdate);
        requestModel.setUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }


    private void syncRequestHeaders() {

        JsonObject object = syncRequestObject(Request_Header_index);
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getSyncRequestHeaders(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SyncRequestHeaderResponse>() {
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
                        mdilogue.dismiss();
                    }

                    @Override
                    public void onNext(SyncRequestHeaderResponse syncRequestHeaderResponse) {

                        if (syncRequestHeaderResponse.getListResult() != null && syncRequestHeaderResponse.getIsSuccess()) {
                            for (SyncRequestHeaderResponse.ListResult requestHeader :
                                    syncRequestHeaderResponse.getListResult()) {
                                databaseQueryClass.deleteRequestHeaderRequestCode(requestHeader.getRequestCode());
                                requestHeader.setServerUpdatedStatus(0);
                                databaseQueryClass.insertRequestHeader(requestHeader);
                            }

                        }

                        Request_Header_index = Request_Header_index + 1;
                        if (Request_Header_index < Requestsize) {
                            syncRequestHeaders();
                        } else {
                            getSyncLabourRequest();
                        }
                        updateCount();
                    }
                });

    }

    private JsonObject syncRequestObject(int index) {

        SyncRequestObject requestModel = new SyncRequestObject();
        requestModel.setDate(currentSYNCdate);
        requestModel.setIndex(index);
        requestModel.setUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());

        return new Gson().toJsonTree(requestModel).getAsJsonObject();

    }

    private void getSyncLabourRequest() {


        JsonObject object = syncLabourRequestPageObject(Labour_Request_index);
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
                        Log.d("Result Printed",syncLabourResponse + "");
                        if (syncLabourResponse.getListResult() != null && syncLabourResponse.getIsSuccess()) {
                            for (SyncLabourResponse.ListResult labourResponce : syncLabourResponse.getListResult()
                            ) {
                                databaseQueryClass.deleteLabourusingRequestCode(labourResponce.getRequestCode());
                                databaseQueryClass.insertLabourRequest(labourResponce);
                            }
                        } else {
                        }
                        Labour_Request_index = Labour_Request_index + 1;
                        if (Labour_Request_index < LabourRequestsize) {
                            getSyncLabourRequest();
                        } else {
                            getSyncLabourServices();
                        }
                        updateCount();
                    }
                });
    }

    private JsonObject syncLabourRequestPageObject(int index) {
        SyncLabourRequest requestModel = new SyncLabourRequest();
        requestModel.setDate(currentSYNCdate);
        requestModel.setUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
        requestModel.setIndex(index);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    private void getSyncLabourServices() {

        JsonObject object = syncLabourServicesPageObject(Labour_Services_index);
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


                        Log.e("ServicesResponse=======",syncLabourServicesResponse+"");
                        if (syncLabourServicesResponse.getListResult() != null && syncLabourServicesResponse.getIsSuccess()) {
                            for (SyncLabourServicesResponse.ListResult labourServiceResponse : syncLabourServicesResponse.getListResult()
                            ) {
                                databaseQueryClass.deleteServiceRequest(labourServiceResponse.getRequestCode(), labourServiceResponse.getServiceTypeId());
                                databaseQueryClass.insertLabourServices(labourServiceResponse);
                            }
                        } else {
                        }
                        Labour_Services_index = Labour_Services_index + 1;
                        if (Labour_Services_index < ServiceRequestsize) {
                            getSyncLabourServices();
                        } else {
                            getSyncFarmerDetails();
                        }
                        updateCount();
                    }
                });
    }

    private JsonObject syncLabourServicesPageObject(int index) {
        SyncLabourSerivesRequest requestModel = new SyncLabourSerivesRequest();
        requestModel.setDate(currentSYNCdate);
        requestModel.setUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
        requestModel.setIndex(index);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    private void getSyncFarmerDetails() {
        JsonObject object = syncFarmerDetailsPageObject(Farmer_Details_index);
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
                        if (syncFarmerDetailsResponse.getListResult() != null && syncFarmerDetailsResponse.getIsSuccess()) {
                            for (SyncFarmerDetailsResponse.ListResult farmerDetailsResponse : syncFarmerDetailsResponse.getListResult()
                            ) {
                                databaseQueryClass.deleteRequestFormerDetails(farmerDetailsResponse.getRequestCode());
                                databaseQueryClass.insertFarmerDetails(farmerDetailsResponse);
                            }
                        } else {
                        }
                        Farmer_Details_index = Farmer_Details_index + 1;
                        if (Farmer_Details_index < FormerRequestsize) {
                            getSyncFarmerDetails();
                        } else {
                         getSyncLabourLeaderDetails();
                        }
                        updateCount();

                    }
                });
    }

    private JsonObject syncFarmerDetailsPageObject(int index) {
        SyncFarmerDetailsRequest requestModel = new SyncFarmerDetailsRequest();
        requestModel.setDate(currentSYNCdate);
        requestModel.setUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
        requestModel.setIndex(index);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    private void getSyncLabourLeaderDetails() {

        JsonObject object = syncLabourLeaderDetailsPageObject(Labour_Count_index);
        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.getLabourLeaderDetailsResponsePage(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SyncLabourLeaderDetailsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Mahesh","******************* Sync Completed *************ERROR*********************");
                        Log.d(" ERROR :", e.getLocalizedMessage());
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
                    public void onNext(final SyncLabourLeaderDetailsResponse syncLabourLeaderDetailsResponse) {

                        Log.e("*** Sync-Data ****","Count OF ITEMS :"+syncLabourLeaderDetailsResponse.getListResult().size());

                        if (syncLabourLeaderDetailsResponse.getListResult() != null && syncLabourLeaderDetailsResponse.getIsSuccess()) {

//                            for(int i=0; i< syncLabourLeaderDetailsResponse.getListResult().size(); i++)
//                            {
//                                databaseQueryClass.deleteLabourLeaderDetails(syncLabourLeaderDetailsResponse.getListResult().get(i).getRequestCode(),syncLabourLeaderDetailsResponse.getListResult().get(i).getLabourId()+"");
//                            }

                            for(int i=0; i< syncLabourLeaderDetailsResponse.getListResult().size(); i++)
                            {

                                databaseQueryClass.deleteLabourLeaderDetails(syncLabourLeaderDetailsResponse.getListResult().get(i).getRequestCode(),syncLabourLeaderDetailsResponse.getListResult().get(i).getLabourId()+"");
                                databaseQueryClass.insertLabourLeaderDetails(syncLabourLeaderDetailsResponse.getListResult().get(i));
//                                int   labour_count =syncLabourLeaderDetailsResponse.getListResult().get(i).getLabourCount();

                                String labour_reqcode =syncLabourLeaderDetailsResponse.getListResult().get(i).getRequestCode();
//                                Log.e("labour_count===",labour_count+"====" +labour_reqcode);



                            }

//                            for (SyncLabourLeaderDetailsResponse.ListResult labourLeaderDetails : syncLabourLeaderDetailsResponse.getListResult()) {
//                              // databaseQueryClass.deleteLabourLeaderDetails(labourLeaderDetails.getRequestCode());
//                                databaseQueryClass.insertLabourLeaderDetails(labourLeaderDetails);
//
//                                int  labour_count =labourLeaderDetails.getLabourCount();
//                                String requestCodecount = labourLeaderDetails.getRequestCode();
//                                Log.e("labour_count===",labour_count+"");
//                                Log.e("requestCodecount===",requestCodecount+"");
//
//                            }
                        } else {
                        }
                        Labour_Count_index = Labour_Count_index + 1;
                        Log.e("Labour_Count_index===",Labour_Count_index+"");
                        if (Labour_Count_index < LabourCountSize) {
                            Log.d("****Sync ***", " Leader Details Sync Progress ");
                            getSyncLabourLeaderDetails();
                        } else {

                            // Todo this is last item we need to update date here
                            Log.d("Mahesh","******************* Sync Completed **********************************");
                            SharedPrefsData.saveSyncdate(SyncActivity.this,newsyncDate);
                            //   SharedPrefsData.saveSyncdatewithold(SyncActivity.this,currentSYNCdate);
                            mdilogue.dismiss();
                            finish();
                        }

                        updateCount();
                    }
                });

    }

    private JsonObject syncLabourLeaderDetailsPageObject(int index) {
        SyncLabourLeaderDetailsRequest requestModel = new SyncLabourLeaderDetailsRequest();
        requestModel.setDate(currentSYNCdate);
        requestModel.setUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
        requestModel.setIndex(index);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }


    private void closeLabourRequest() {
        mdilogue.show();
        JsonObject object = closeLabourRequestObjectPage();

        ApiService service = ServiceFactory.createRetrofitService(this, ApiService.class);
        mSubscription = service.closeLabourResponsePage(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CloseLabourResponse>() {
                    @Override
                    public void onCompleted() {
                        mdilogue.cancel();
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
                    public void onNext(final CloseLabourResponse closeLabourResponse) {
                        mdilogue.cancel();
                        if (closeLabourResponse.getIsSuccess()) {

                            List<SyncRequestHeaderResponse.ListResult> RequestDataList = databaseQueryClass.getPendingRequests();
                            for (int i = 0; i < RequestDataList.size(); i++) {

                                SyncRequestHeaderResponse.ListResult RequestData = RequestDataList.get(i);
                                SyncLabourResponse.ListResult labourData = databaseQueryClass.getLabourRequestByID(RequestData.getRequestCode()).get(0);

                                databaseQueryClass.deleteRequestHeaderRequestCode(RequestData.getRequestCode());
                                RequestData.setStatusTypeId(39);
                                RequestData.setUpdatedByUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
                                RequestData.setUpdatedDate(RequestData.getUpdatedDate());
                                RequestData.setServerUpdatedStatus(0);

                                databaseQueryClass.insertRequestHeader(RequestData);

                                databaseQueryClass.deleteLabourusingRequestCode(labourData.getRequestCode());
                                labourData.setTreesCount(labourData.getTreesCount());
                                labourData.setExpectedNetWeight(labourData.getNetWeight());
                                labourData.setUpdatedByUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
                                labourData.setUpdatedDate(labourData.getUpdatedDate());
                                labourData.setJobDoneDate(labourData.getJobDoneDate());
                                labourData.setServerUpdatedStatus(0);
                                databaseQueryClass.insertLabourRequest(labourData);

                            }
                            updateCount();
                            getCount();
                            //  databaseQueryClass.insertLabourRequest(labourData);
                            Toast.makeText(ctx, getString(R.string.updated_data), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(ctx, getString(R.string.reset_data), Toast.LENGTH_SHORT).show();
                        //    getCount();
                            updateCount();
                        }
                    }

                });

    }

    private JsonObject closeLabourRequestObjectPage() {


        List<LabourLeadersync.RequestHeader> requestHeaderList = new ArrayList<>();
        List<LabourLeadersync.LabourRequest> labourRequestList = new ArrayList<>();
        List<LabourLeadersync.LabourLeaderXref> labourleaderDetails = new ArrayList<>();
        List<SyncRequestHeaderResponse.ListResult> RequestDataList = databaseQueryClass.getPendingRequests();


        for (int i = 0; i < RequestDataList.size(); i++) {
// Request HEADER DETAILS CHANGE OR UPDATE
            SyncRequestHeaderResponse.ListResult RequestData = RequestDataList.get(i);
            LabourLeadersync.RequestHeader requestHeader = new LabourLeadersync.RequestHeader();
            SyncLabourResponse.ListResult labourData = databaseQueryClass.getLabourRequestByID(RequestDataList.get(i).getRequestCode()).get(0);
            List<SyncLabourLeaderDetailsResponse.ListResult> laboursinfo = databaseQueryClass.getRequestandLabours(labourData.getRequestCode());
            requestHeader.setId(RequestData.getId());
//        requestHeader.setId(0);
            requestHeader.setRequestCode(RequestData.getRequestCode());
            requestHeader.setFarmerCode(RequestData.getFarmerCode());
            requestHeader.setPlotCode(RequestData.getPlotCode());
            requestHeader.setReqCreatedDate(RequestData.getCreatedDate());
            requestHeader.setStatusTypeId(39);
            requestHeader.setIsFarmerRequest(RequestData.getIsFarmerRequest());
            requestHeader.setCreatedDate(RequestData.getCreatedDate());
            requestHeader.setUpdatedByUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
            requestHeader.setUpdatedDate(RequestData.getUpdatedDate());
            requestHeader.setTotalCost(RequestData.getTotalCost());
            requestHeader.setComments(RequestData.getComments());
            requestHeader.setCropMaintainceDate(RequestData.getCropMaintainceDate());
            requestHeader.setRequestTypeId(RequestData.getRequestTypeId());
            requestHeader.setIssueTypeId(RequestData.getIssueTypeId());
            requestHeader.setFarmerName(RequestData.getFarmerName());
            requestHeader.setPlotVillage(RequestData.getPlotVillage());
            requestHeader.setPalmArea((double) RequestData.getPalmArea());
            requestHeader.setServerUpdatedStatus(RequestData.getServerUpdatedStatus());
            requestHeader.setYearofPlanting(RequestData.getYearofPlanting());
            requestHeader.setTotalCostWithoutServiceCharge(RequestData.getTotalCostWithoutServiceCharge());
            requestHeader.setServiceChargeAmount(RequestData.getServiceChargeAmount());
            requestHeader.setParentRequestCode(RequestData.getParentRequestCode());
            requestHeader.setRecoveryFarmerCode(RequestData.getRecoveryFarmerCode());
            requestHeader.setClusterId(RequestData.getClusterId());

            requestHeaderList.add(requestHeader);
            // Request LABOUR DETAILS CHANGE OR UPDATE


            SyncLabourLeaderDetailsResponse.ListResult labourDetailsData = databaseQueryClass.getRequestandLabours(RequestDataList.get(i).getRequestCode()).get(0);
            LabourLeadersync.LabourRequest labourRequest = new LabourLeadersync.LabourRequest();
            labourRequest.setId(labourData.getId());
            labourRequest.setRequestCode(labourData.getRequestCode());
            labourRequest.setDurationId(labourData.getDurationId());
            labourRequest.setLeaderId(labourData.getLeaderId());
            labourRequest.setPin(labourData.getPin());
            labourRequest.setJobDoneDate(labourData.getUpdatedDate());
            labourRequest.setCreatedByUserId(labourData.getCreatedByUserId());
            labourRequest.setCreatedDate(labourData.getCreatedDate());
            labourRequest.setStartDate(labourData.getStartDate());
            labourRequest.setUpdatedByUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
            labourRequest.setUpdatedDate(onGetCurrentDate(this));
            labourRequest.setAssignedDate(labourData.getAssignedDate());
            labourRequest.setNetWeight(labourData.getNetWeight());
            labourRequest.setAmount(labourData.getAmount());
            labourRequest.setHarvestingAmount(labourData.getHarvestingAmount());
            labourRequest.setPruningAmount(labourData.getPruningAmount());
            labourRequest.setPruningWithIntercropAmount(labourData.getPruningWithIntercropAmount());
            labourRequest.setHarvestingWithIntercropAmount(labourData.getHarvestingWithIntercropAmount());
            labourRequest.setTreesCount(labourData.getTreesCount());
            labourRequest.setServerUpdatedStatus(labourData.getServerUpdatedStatus());
            labourRequest.setOrderId(labourData.getOrderId());
            labourRequest.setTreesCountWithIntercrop(labourData.getTreesCountWithIntercrop());
            labourRequest.setNetWeightIntercrop(labourData.getNetWeightIntercrop());
            labourRequest.setExpectedBunches(labourData.getExpectedBunches());
            labourRequest.setExpectedNetWeight(labourData.getExpectedNetWeight());
            labourData.setOwnPole(labourData.getOwnPole());
            labourRequestList.add(labourRequest);

//            LabourLeadersync.LabourLeaderXref labourLeaderXref = new LabourLeadersync.LabourLeaderXref();
//            labourLeaderXref.setId(labourDetailsData.getId());
//            labourLeaderXref.setRequestCode(labourDetailsData.getRequestCode());
//            labourLeaderXref.setLabourId(labourDetailsData.getLabourId());
//            labourLeaderXref.setIsActive(labourDetailsData.getIsActive());
//            labourLeaderXref.setCreatedByUserId(labourDetailsData.getCreatedByUserId());
//            labourLeaderXref.setCreatedDate(labourDetailsData.getCreatedDate());
//            labourLeaderXref.setUpdatedByUserId(labourDetailsData.getUpdatedByUserId());
//            labourLeaderXref.setUpdatedDate(labourDetailsData.getUpdatedDate());
//            labourLeaderXref.setTreesCount(labourDetailsData.getTreesCount());
//            labourLeaderXref.setNetWeight(labourDetailsData.getNetWeight());
//            labourLeaderXref.setServerUpdatedStatus(labourDetailsData.getServerUpdatedStatus());
//            labourLeaderXref.setLabourCount(labourDetailsData.getLabourCount());
//            labourleaderDetails.add(labourLeaderXref);


            for (int j=0;j<laboursinfo.size();j++)
            {
                LabourLeadersync.LabourLeaderXref labourLeaderXref = new LabourLeadersync.LabourLeaderXref();
                labourLeaderXref.setId(laboursinfo.get(j).getId());
                labourLeaderXref.setRequestCode(laboursinfo.get(j).getRequestCode());
                labourLeaderXref.setLabourId(laboursinfo.get(j).getLabourId());
                labourLeaderXref.setTreesCount(laboursinfo.get(j).getTreesCount());

                labourLeaderXref.setIsActive(laboursinfo.get(j).getIsActive());
                labourLeaderXref.setCreatedByUserId(laboursinfo.get(j).getCreatedByUserId());
                labourLeaderXref.setCreatedDate(laboursinfo.get(j).getCreatedDate());
                labourLeaderXref.setUpdatedByUserId(laboursinfo.get(j).getUpdatedByUserId());
                labourLeaderXref.setUpdatedDate(laboursinfo.get(j).getUpdatedDate());
                labourLeaderXref.setServerUpdatedStatus(laboursinfo.get(j).getServerUpdatedStatus());
                labourLeaderXref.setNetWeight(laboursinfo.get(j).getNetWeight());
                labourleaderDetails.add(labourLeaderXref);

            }

        }
        // Todo insert labour details
        LabourLeadersync labourLeadersync = new LabourLeadersync(requestHeaderList, labourRequestList,labourleaderDetails);
        return new Gson().toJsonTree(labourLeadersync).getAsJsonObject();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
