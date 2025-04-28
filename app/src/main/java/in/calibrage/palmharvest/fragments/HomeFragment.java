package in.calibrage.palmharvest.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.skyhope.eventcalenderlibrary.CalenderEvent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;
import in.calibrage.palmharvest.Activities.CloseLabourleaderRequestActivity;
import in.calibrage.palmharvest.Activities.SyncActivity;
import in.calibrage.palmharvest.Model.GetCountRequest;
import in.calibrage.palmharvest.Model.GetCountResponse;
import in.calibrage.palmharvest.Model.GetMasters;
import in.calibrage.palmharvest.Model.RequestCompleteModel;
import in.calibrage.palmharvest.Model.SyncLabourLeaderDetailsRequest;
import in.calibrage.palmharvest.Model.SyncLabourLeaderDetailsResponse;
import in.calibrage.palmharvest.Model.SyncLabourRequest;
import in.calibrage.palmharvest.Model.SyncLabourResponse;
import in.calibrage.palmharvest.R;
import in.calibrage.palmharvest.adapter.NotificationListner;
import in.calibrage.palmharvest.common.DateTimeUtil;
import in.calibrage.palmharvest.database.DatabaseQueryClass;
import in.calibrage.palmharvest.localData.SharedPrefsData;
import in.calibrage.palmharvest.service.APIConstantURL;
import in.calibrage.palmharvest.service.ApiService;
import in.calibrage.palmharvest.service.ServiceFactory;
import in.calibrage.palmharvest.sync.SyncRequestHeaderResponse;
import in.calibrage.palmharvest.sync.SyncRequestObject;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static in.calibrage.palmharvest.common.DateTimeUtil.LastSyncTime;
import static in.calibrage.palmharvest.common.DateTimeUtil.stringTodate;
import static in.calibrage.palmharvest.common.MLog.onPrintDebugLog;


public class HomeFragment extends BaseFragment implements NotificationListner {
    public static String TAG = HomeFragment.class.getSimpleName();
    private Context ctx;
    private Subscription mSubscription;
    private TextView txt_requestHeader_count, txt_lastSync;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ImageButton img_f_sync, img_r_sync;
    CalenderEvent calenderEvent;

    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private boolean shouldShow = false;
    private CompactCalendarView compactCalendarView;
    private ActionBar toolbar;
    private TextView textView, txt_pending_sync_count;
    ProgressDialog progressDialog;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());
    private SpotsDialog mdilogue;
    RecyclerView recyclerView;
    HomeRecyclerViewAdapter homeRecyclerAdapter;
    RecyclerAdapter recyclerAdapter;
    TextView emptyView;
    private String currentSYNCdate ;
    private Date selecteddate = null;
    Integer role_id;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ctx = v.getContext();
        init(v);
        setview();

        emptyView = v.findViewById(R.id.empty_view);
        txt_pending_sync_count = v.findViewById(R.id.txt_pending_sync_count);


        setHasOptionsMenu(true);
        databaseQueryClass = new DatabaseQueryClass(getContext());

        recyclerView = v.findViewById(R.id.event_listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        txt_pending_sync_count.setText(": " + databaseQueryClass.getPendingRequestCount());

//        homeRecyclerAdapter = new HomeRecyclerViewAdapter(getActivity(), databaseQueryClass.getAllLabourRequests(), databaseQueryClass.getAllRequestHeaders());
//        recyclerView.setAdapter(homeRecyclerAdapter);

        List<RequestCompleteModel> data1 = databaseQueryClass.getRequestsSpecificDate(DateTimeUtil.onGetCurrentDateForDB(getContext()));
        data1.clear();
        if (data1.size() > 0) {
            recyclerAdapter = new RecyclerAdapter(getActivity(), data1, this,databaseQueryClass);
            Log.e("data1===",data1+"");
            recyclerView.setAdapter(recyclerAdapter);
            emptyView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }


        final List<String> mutableBookings = new ArrayList<>();

        final ImageButton showPreviousMonthBut = v.findViewById(R.id.prev_button);
        final ImageButton showNextMonthBut = v.findViewById(R.id.next_button);
        textView = v.findViewById(R.id.month_title);

        compactCalendarView = v.findViewById(R.id.compactcalendar_view);
        compactCalendarView.setUseThreeLetterAbbreviation(false);
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        compactCalendarView.setIsRtl(false);
        compactCalendarView.displayOtherMonthDays(false);

        logEventsByMonth(compactCalendarView, DateTimeUtil.onGetCurrentDate(getContext()).substring(0, 7));
        toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        textView.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));


        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                textView.setText(dateFormatForMonth.format(dateClicked));

                Log.d(TAG, "OnDate Selected :" + DateTimeUtil.DateToString(dateClicked));
                selecteddate = dateClicked;
                List<RequestCompleteModel> data2 = databaseQueryClass.getRequestsSpecificDate(DateTimeUtil.DateToString(dateClicked));

                if (data2.size() > 0) {

                    recyclerAdapter = new RecyclerAdapter(getActivity(), data2, HomeFragment.this,databaseQueryClass);
                    recyclerView.setAdapter(recyclerAdapter);
                    emptyView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    emptyView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);

                }


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                textView.setText(dateFormatForMonth.format(firstDayOfNewMonth));
                Log.d(TAG, "On Month Change :" + DateTimeUtil.DateToString(firstDayOfNewMonth));
                logEventsByMonth(compactCalendarView, DateTimeUtil.DateToString(firstDayOfNewMonth).substring(0, 7));
            }
        });


        showPreviousMonthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendarView.scrollLeft();
            }
        });

        showNextMonthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendarView.scrollRight();
            }
        });

        final View.OnClickListener showCalendarOnClickLis = getCalendarShowLis();

        final View.OnClickListener exposeCalendarListener = getCalendarExposeLis();

        compactCalendarView.setAnimationListener(new CompactCalendarView.CompactCalendarAnimationListener() {
            @Override
            public void onOpened() {
            }

            @Override
            public void onClosed() {
            }
        });
        mdilogue = new SpotsDialog(getContext(), R.style.Custom);
//        mdilogue = (SpotsDialog) new SpotsDialog.Builder()
//                .setContext(getContext())
//                .setTheme(R.style.Custom)
//                .build();
        //  getSyncLabourRequest();

        // Log.d(TAG,"Created DATE :"+databaseQueryClass.getCurrentDayRequests().get(0).getReqCreatedDate());
        for (SyncRequestHeaderResponse.ListResult data : databaseQueryClass.getCurrentMonthRequests(DateTimeUtil.onGetCurrentDate(getContext()))
        ) {
            Log.d(TAG, "Current Month :" + data.getReqCreatedDate());
        }

        return v;
    }


    @NonNull
    private View.OnClickListener getCalendarShowLis() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!compactCalendarView.isAnimating()) {
                    if (shouldShow) {
                        compactCalendarView.showCalendar();
                    } else {
                        compactCalendarView.hideCalendar();
                    }
                    shouldShow = !shouldShow;
                }
            }
        };
    }


    @NonNull
    private View.OnClickListener getCalendarExposeLis() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!compactCalendarView.isAnimating()) {
                    if (shouldShow) {
                        compactCalendarView.showCalendarWithAnimation();
                    } else {
                        compactCalendarView.hideCalendarWithAnimation();
                    }
                    shouldShow = !shouldShow;
                }
            }
        };
    }

    private void openCalendarOnCreate(View v) {
        final RelativeLayout layout = v.findViewById(R.id.main_content);
        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < 16) {
                    layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                compactCalendarView.showCalendarWithAnimation();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        textView.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        // Set to current day on resume to set calendar to latest day
        textView.setText(dateFormatForMonth.format(new Date()));
    }


    private void logEventsByMonth(CompactCalendarView compactCalendarView, String Date) {


        currentCalender.setTime(new Date());
        currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        //for (SyncRequestHeaderResponse.ListResult data:databaseQueryClass.getCurrentMonthRequests(DateTimeUtil.onGetCurrentDate(getContext()))) {
        //String date=DateTimeUtil.onGetCurrentDate(getContext()).substring(0,7);
        String date = Date;
        Log.d(TAG, "final Month :" + date);
        for (SyncLabourResponse.ListResult data : databaseQueryClass.getLabourRequestByMonth(date)) {
            //Event ev1 = new Event(Color.GREEN, stringTodate(data.getReqCreatedDate()).getTime(),"mallem");
            Log.d(TAG, "Each Event :" + data.getStartDate());
            Event ev1 = new Event(Color.GREEN, stringTodate(data.getStartDate()).getTime(), "mallem");
            try {
                compactCalendarView.removeEvent(ev1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            compactCalendarView.addEvent(ev1);
        }

    }


    private void init(View view) {

        txt_requestHeader_count = view.findViewById(R.id.txt_requestHeader_count);
        txt_lastSync = view.findViewById(R.id.txt_lastSync);
        img_f_sync = view.findViewById(R.id.img_f_sync);
        img_r_sync = view.findViewById(R.id.img_r_sync);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        txt_lastSync.setText(LastSyncTime(ctx));
        Log.e("LastSyncTime==",LastSyncTime(ctx));

    }

    private void setview() {
         role_id =SharedPrefsData.getCatagoriess(ctx).getResult().getRoleId();

        Log.e("role_id==",role_id+"");
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO we need to update

                currentSYNCdate = SharedPrefsData.onGetSyncDate(ctx);
                Log.d(TAG, "======date==" + currentSYNCdate);
                if (isOnline(getContext()))

                    getCount();
                else
                    Toast.makeText(ctx, getString(R.string.Internet), Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        if (isOnline(getContext())) {
          //  getCount();
            GetMasters();
        }
        else
            Toast.makeText(ctx, getString(R.string.Internet), Toast.LENGTH_SHORT).show();


        img_f_sync.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                //getSyncLabourLeaderDetails();
                if (img_f_sync.isEnabled()) {
                    ctx.startActivity(new Intent(ctx, SyncActivity.class));
                } else {
                    ctx.startActivity(new Intent(ctx, SyncActivity.class));
                    Toast.makeText(ctx, getResources().getString(R.string.allDataUpdated), Toast.LENGTH_SHORT).show();
                }
            }
        });
        img_r_sync.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (img_r_sync.isEnabled()) {
                    ctx.startActivity(new Intent(ctx, SyncActivity.class));
                } else {
                    ctx.startActivity(new Intent(ctx, SyncActivity.class));
                    Toast.makeText(ctx, getResources().getString(R.string.allDataUpdated), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void GetMasters() {
        ApiService service = ServiceFactory.createRetrofitService(ctx, ApiService.class);
        mSubscription = service.getmasterdata(APIConstantURL.getMasters)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GetMasters>() {
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
                    public void onNext(GetMasters getMasters) {
                      //  SharedPrefsData.saveLabourLeaderDetails(getContext(), getMasters);
                        if (getMasters.getIsSuccess()) {
                            for (GetMasters.ListResult labourdetails : getMasters.getListResult()
                            ) {

                                    //databaseQueryClass.deleteLabourusingRequestCode(labourdetails.get());
                                databaseQueryClass.insertGetMasters(labourdetails);
                            }
                        } else {
                        }
                    }


                });
    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onNotificationClick(int po, RequestCompleteModel requestCompleteModel) {
   if(role_id == 2) {

     Intent intent = new Intent(getContext(), CloseLabourleaderRequestActivity.class);
     intent.putExtra("id", requestCompleteModel.getRequestCode());
     intent.putExtra("islabour",true);


     startActivityForResult(intent, 101);
  }
  else {
//    Intent intent = new Intent(getContext(), CloseLabourleaderRequestActivity.class);
////    intent.putExtra("id", requestCompleteModel.getRequestCode());
////
////    startActivityForResult(intent, 101);
     Intent intent = new Intent(getContext(), CloseLabourleaderRequestActivity.class);
     intent.putExtra("id", requestCompleteModel.getRequestCode());
     intent.putExtra("islabour",false);


     startActivityForResult(intent, 101);
}

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101) {
            Log.d(TAG, "Came From Close Request");
            onRefreshData();
        }
    }

    private void onRefreshData() {
        if (selecteddate != null) {
            List<RequestCompleteModel> data2 = databaseQueryClass.getRequestsSpecificDate(DateTimeUtil.DateToString(selecteddate));

            Log.e("data2==496",data2.size()+"");
            if (data2.size() > 0) {
                recyclerAdapter = new RecyclerAdapter(getActivity(), data2, HomeFragment.this,databaseQueryClass);
                recyclerView.setAdapter(recyclerAdapter);
                emptyView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            } else {
                emptyView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        } else {
            List<RequestCompleteModel> data1 = databaseQueryClass.getRequestsSpecificDate(DateTimeUtil.onGetCurrentDateForDB2(getContext()));
            Log.e("data2==508",data1.size()+"");
            if (data1.size() > 0) {
                recyclerAdapter = new RecyclerAdapter(getActivity(), data1, this,databaseQueryClass);
                recyclerView.setAdapter(recyclerAdapter);
                emptyView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            } else {
                emptyView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void getCount() {

        JsonObject object = getCountObject();

        Log.e("object===",object+"");
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
                    }

                    @Override
                    public void onNext(final GetCountResponse getCountResponse) {



                        try {
                            if (getCountResponse != null) {
                                txt_requestHeader_count.setText(" : " + getCountResponse.getRequestHeader().getCount());
                                Log.e(" Count============== " ,getCountResponse.getRequestHeader().getCount()+"");
                                if (getCountResponse.getRequestHeader().getCount() > 0) {
                                    img_f_sync.setImageResource(R.drawable.ic_f_sync);
                                    img_f_sync.setEnabled(true);
                                } else {
                                    img_f_sync.setImageResource(R.drawable.ic_r_sync);
                                 //  img_f_sync.setEnabled(false);
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
        GetCountRequest requestModel = new GetCountRequest();
        //requestModel.setDate("");
        requestModel.setDate(SharedPrefsData.onGetSyncDate(ctx));
        requestModel.setUserId(SharedPrefsData.getCatagoriess(getContext()).getResult().getId());


        return new Gson().toJsonTree(requestModel).getAsJsonObject();

    }

    private void getSyncLabourLeaderDetails() {

        JsonObject object = syncLabourLeaderDetailsPageObject();
        ApiService service = ServiceFactory.createRetrofitService(getContext(), ApiService.class);
        mSubscription = service.getLabourLeaderDetailsResponsePage(object)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SyncLabourLeaderDetailsResponse>() {
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
                    public void onNext(final SyncLabourLeaderDetailsResponse syncLabourLeaderDetailsResponse) {
                        if (syncLabourLeaderDetailsResponse.getIsSuccess()) {
                            for (SyncLabourLeaderDetailsResponse.ListResult labourLeaderDetails : syncLabourLeaderDetailsResponse.getListResult()
                            ) {
                              //  databaseQueryClass.insertGetMasters(labourLeaderDetails);
                            }
                        } else {
                        }

                    }
                });

    }

    private JsonObject syncLabourLeaderDetailsPageObject() {
        SyncLabourLeaderDetailsRequest requestModel = new SyncLabourLeaderDetailsRequest();
        requestModel.setDate("");
        requestModel.setUserId(SharedPrefsData.getCatagoriess(getContext()).getResult().getId());
        requestModel.setIndex(0);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }


    private void openProgressDialogue(int MAXCOUNT) {
//        progressDialog = new ProgressDialog(getContext());
//        progressDialog.setTitle("Please Wait..");
//        progressDialog.setMessage("Downloading Video ...");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progressDialog.setCancelable(false);
//        progressDialog.setMax(MAXCOUNT);
//        progressDialog.show();
//
//
//        try {
//
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    progressStatus += 1;
//                    progressDialog.setProgress(progressStatus);
//                    if (progressDialog.getProgress() == progressDialog.getMax()) {
//                        progressDialog.dismiss();
//                    }
//                }
//            }, 300);
//
//        } catch (Exception e) {
//            onPrintDebugLog(TAG, "handler", "error :");
//            e.printStackTrace();
//        }
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMax(100); // Progress Dialog Max Value
        progressDialog.setMessage(getResources().getString(R.string.loading)); // Setting Message
        progressDialog.setTitle(getResources().getString(R.string.progressDailog)); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); // Progress Dialog Style Horizontal
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progressDialog.getProgress() <= progressDialog.getMax()) {
                        Thread.sleep(200);
                        handler.sendMessage(handler.obtainMessage());
                        if (progressDialog.getProgress() == progressDialog.getMax()) {
                            progressDialog.dismiss();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void showSyncDialog() {
        final Dialog dialog = new Dialog(getContext(), R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.syncdialog);


        TextView lastSyncTime = dialog.findViewById(R.id.txt_lastSync);
        final Button btn_sync = dialog.findViewById(R.id.btn_sync);

        lastSyncTime.setText(LastSyncTime(ctx));

        btn_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_sync.isActivated()) {
                    btn_sync.setEnabled(false);
                }
                // getSyncLabourRequest();

            }
        });

        dialog.show();
    }

    private void getSyncLabourRequest() {
        mdilogue.show();
        JsonObject object = syncLabourRequestPageObject();
        ApiService service = ServiceFactory.createRetrofitService(getContext(), ApiService.class);
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
                        mdilogue.cancel();
                    }

                    @Override
                    public void onNext(final SyncLabourResponse syncLabourResponse) {
                        if (syncLabourResponse.getIsSuccess()) {
                            for (SyncLabourResponse.ListResult labourResponce : syncLabourResponse.getListResult()
                            ) {
                                databaseQueryClass.deleteLabourusingRequestCode(labourResponce.getRequestCode());
                                databaseQueryClass.insertLabourRequest(labourResponce);
                            }
                        }
                        mdilogue.cancel();
                        // syncRequestHeaders();
                    }
                });

    }

    private JsonObject syncLabourRequestPageObject() {
        SyncLabourRequest requestModel = new SyncLabourRequest();
        requestModel.setDate(SharedPrefsData.onGetSyncDate(getContext()));
        requestModel.setUserId(SharedPrefsData.getCatagoriess(getContext()).getResult().getId());
        requestModel.setIndex(0);
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    private void syncRequestHeaders() {
        mdilogue.show();
        JsonObject object = syncRequestObject();
        ApiService service = ServiceFactory.createRetrofitService(getContext(), ApiService.class);
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
                        mdilogue.cancel();
                        //  mdilogue.dismiss();
                    }

                    @Override
                    public void onNext(SyncRequestHeaderResponse syncRequestHeaderResponse) {

                        if (syncRequestHeaderResponse.getIsSuccess()) {
                            for (SyncRequestHeaderResponse.ListResult requestHeader :
                                    syncRequestHeaderResponse.getListResult()) {
//                                databaseQueryClass.deleteRequestHeaderRequestCode(requestHeader.getRequestCode());
//                                databaseQueryClass.insertRequestHeader(requestHeader);

                                databaseQueryClass.deleteRequestHeaderRequestCode(requestHeader.getRequestCode());
                                databaseQueryClass.insertRequestHeader(requestHeader);
                            }

                        }
                        mdilogue.cancel();
                        Log.d("--- SYNC ------","last Sync Date :"+SharedPrefsData.onGetSyncDate(ctx));
                       //SharedPrefsData.saveSyncdate(getContext());

                       // SharedPrefsData.saveSyncdatewithold(getContext(),"");
                    }
                });

    }

    private JsonObject syncRequestObject() {
        SyncRequestObject requestModel = new SyncRequestObject();
        requestModel.setDate("");
        requestModel.setIndex(0);
        requestModel.setUserId(SharedPrefsData.getCatagoriess(getContext()).getResult().getId());
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
        getCount();
        onRefreshData();
        logEventsByMonth(compactCalendarView, DateTimeUtil.onGetCurrentDate(getContext()).substring(0, 7));
        txt_pending_sync_count.setText(": " + databaseQueryClass.getPendingRequestCount());

        if (databaseQueryClass.getPendingRequestCount() > 0) {
            img_r_sync.setImageResource(R.drawable.ic_f_sync);
            img_r_sync.setEnabled(true);
        } else {
            img_r_sync.setImageResource(R.drawable.ic_r_sync);
            img_r_sync.setEnabled(false);
        }

    }
}
