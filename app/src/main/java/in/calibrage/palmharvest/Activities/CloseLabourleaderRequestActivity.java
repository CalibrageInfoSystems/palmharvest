package in.calibrage.palmharvest.Activities;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import in.calibrage.palmharvest.Model.CloseLabourResponse;
import in.calibrage.palmharvest.Model.EditModel;
import in.calibrage.palmharvest.Model.LabourLeadersync;
import in.calibrage.palmharvest.Model.SyncFarmerDetailsResponse;
import in.calibrage.palmharvest.Model.SyncLabourLeaderDetailsResponse;
import in.calibrage.palmharvest.Model.SyncLabourResponse;
import in.calibrage.palmharvest.Model.SyncLabourServicesResponse;
import in.calibrage.palmharvest.R;
import in.calibrage.palmharvest.adapter.TreescountAdapter;
import in.calibrage.palmharvest.common.BaseActivity;
import in.calibrage.palmharvest.common.CommonUtil;
import in.calibrage.palmharvest.common.DateTimeUtil;
import in.calibrage.palmharvest.database.DatabaseQueryClass;
import in.calibrage.palmharvest.localData.SharedPrefsData;
import in.calibrage.palmharvest.service.ApiService;
import in.calibrage.palmharvest.service.ServiceFactory;
import in.calibrage.palmharvest.sync.SyncRequestHeaderResponse;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CloseLabourleaderRequestActivity extends BaseActivity implements TreescountAdapter.OnEditTextChanged {
    RecyclerView recyclerView_tresslist;
    Map<Integer,String> labourinfoMap;
    private TreescountAdapter adapter;
    public ArrayList<EditModel> editModelArrayList;
    TextView requestId;
    TextView farmerCode;
    TextView farmerName;
    TextView plotCode;
    EditText pin, bunchCount, weight;
    TextView jobDoneDate, serviceTypes;
    EditText collectionId;
    EditText netWeight;
    EditText harvestingAmount;
    EditText tressCount;
    EditText pruningAmount;
    TextView pinn, job_done;
    Button save_btn;

    DatePickerDialog.OnDateSetListener setListener;
    private Subscription mSubscription;
    private DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(this);
    private SyncRequestHeaderResponse.ListResult RequestData;
    private List<SyncLabourLeaderDetailsResponse.ListResult> laboursinfo;
    SyncLabourResponse.ListResult labourData;
    SyncLabourServicesResponse.ListResult labourServicesData;
    SyncFarmerDetailsResponse.ListResult farmerDetailsData;
    SyncLabourLeaderDetailsResponse.ListResult labourLeaderDetails;
    private int count = 0;
    private int bunchcounts ;
    private Double weights;
    ImageView back;
    String serviceType;
    private CheckBox ch_withintercrop;
    private LinearLayout lyt_treesCount, lyt_bunchCount, lyt_weight;
    Integer[] enteredNumber = new Integer[1000];
    String curTime ,date_time;

    private boolean isLabour= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_labourleader_request);


        Date dt = new Date();
        int hours = dt.getHours();
        int minutes = dt.getMinutes();
        int seconds = dt.getSeconds();
         curTime = hours + ":" + minutes + ":" + seconds;

        Log.e("localTime===",curTime);

//
//        for(int i=0; i< SharedPrefsData.getLabourdetails(this).getListResult().size();i++)
//        {
//            int Labour_id = SharedPrefsData.getLabourdetails(this).getListResult().get(i).getLabourId();
//            Log.e("Labour_id===",Labour_id+"");
//
//        }


        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        // serviceType =getIntent().getExtras().getString("serviceTypes");
        isLabour = getIntent().getExtras().getBoolean("islabour");
        Log.d("mahesh","----------------- is LABOUR :"+isLabour);
        RequestData = databaseQueryClass.getRequestsByID(getIntent().getExtras().getString("id")).get(0);
        labourData = databaseQueryClass.getLabourRequestByID(getIntent().getExtras().getString("id")).get(0);
        labourServicesData = databaseQueryClass.getLabouServicestByID(getIntent().getExtras().getString("id")).get(0);
        farmerDetailsData = databaseQueryClass.getFarmerDetailsByID(getIntent().getExtras().getString("id")).get(0);

        recyclerView_tresslist = findViewById(R.id.recyclerView_tresslist);
        laboursinfo =databaseQueryClass.getRequestandLabours(RequestData.getRequestCode());
        //  Log.d("Roja=====135","********* in LABOUR COunt ********* :"+laboursinfo.get(0).getLabourCount());
        adapter = new TreescountAdapter(this,laboursinfo, databaseQueryClass ,CloseLabourleaderRequestActivity.this);
        recyclerView_tresslist.setAdapter(adapter);
        recyclerView_tresslist.setItemViewCacheSize(laboursinfo.size());
        recyclerView_tresslist.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        Log.e("RequestData===",RequestData+"");
        Log.e("serviceType===1", CommonUtil.getServicename(databaseQueryClass.getLabouServicestByID(RequestData.getRequestCode())));
        requestId = findViewById(R.id.requestId);
        farmerCode = findViewById(R.id.farmerCode);
        farmerName = findViewById(R.id.farmerName);
        plotCode = findViewById(R.id.plotCode);
        save_btn = findViewById(R.id.save_btn);
        ch_withintercrop = findViewById(R.id.ch_withintercrop);
        ch_withintercrop.setChecked(true);
        lyt_bunchCount = findViewById(R.id.lyt_BunchesCount);
        lyt_weight = findViewById(R.id.lyt_weight);
        bunchCount = findViewById(R.id.bunchesCount_edittxt);
        weight = findViewById(R.id.weight_edittxt);

        pin = findViewById(R.id.pin_edittxt);
        jobDoneDate = findViewById(R.id.jobDoneDate_edittxt);
        tressCount = findViewById(R.id.tressCount_edittxt);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        jobDoneDate.setText(dateFormat.format(new Date()));

        requestId.setText(RequestData.getRequestCode());
        farmerCode.setText(RequestData.getFarmerCode());
        farmerName.setText(RequestData.getFarmerName());
        plotCode.setText(RequestData.getPlotCode());
        tressCount.setText("" + labourData.getTreesCount());
     // bunchCount.setText("" + labourData.getExpectedBunches());

      //weight.setText("" + labourData.getExpectedNetWeight());
        lyt_treesCount = findViewById(R.id.lyt_treesCount);
        TextView startDate = findViewById(R.id.startDate);
        try {
            startDate.setText("" + DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_20, DateTimeUtil.DATE_FORMAT_22, labourData.getAssignedDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("Roja", "------ analysis --------- DATE :" + startDate.getText() + "************"+labourData.getAssignedDate());
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        Log.d("Mahesh", "------ analysis --------- PIN :" + labourData.getPin());

//        for (SyncLabourServicesResponse.ListResult item :
//                databaseQueryClass.getLabouServicestByID(RequestData.getRequestCode())) {
//            // check request type
//            if (item.getServiceTypeId() == 19 || item.getServiceTypeId() == 33) {
//
//                lyt_treesCount.setVisibility(View.GONE);
//            }
//
//        }
//        if (holder.serviceTypes.getText().toString().contains("Harvesting")) {
//            holder.lytTreesCount.setVisibility(View.GONE);
//        }
        serviceTypes = findViewById(R.id.serviceTypes);
        serviceTypes.setText("" + CommonUtil.getServicename(databaseQueryClass.getLabouServicestByID(RequestData.getRequestCode())));
        TextView txt = findViewById(R.id.txt_treeCount);
        if (serviceTypes.getText().toString().contains("Pruning")) {
            lyt_treesCount.setVisibility(View.VISIBLE);
            txt.setVisibility(View.VISIBLE);
            recyclerView_tresslist.setVisibility(View.VISIBLE);


        } else {
            lyt_treesCount.setVisibility(View.GONE);
            txt.setVisibility(View.GONE);
            recyclerView_tresslist.setVisibility(View.GONE);



        }

        if (serviceTypes.getText().toString().contains("Harvesting")) {
            lyt_bunchCount.setVisibility(View.VISIBLE);
            lyt_weight.setVisibility(View.VISIBLE);

        } else {
            lyt_bunchCount.setVisibility(View.GONE);
            lyt_weight.setVisibility(View.GONE);
        }


        jobDoneDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        CloseLabourleaderRequestActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {


                        datePicker.setMaxDate(System.currentTimeMillis());

                        month = month + 1;
                        String date = day + "/" + month + "/" + year ;
                        jobDoneDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                for (Map.Entry<Integer,String> entry : labourinfoMap.entrySet())
//                {
//                  System.out.println("Key => " + entry.getKey() +
//                           ", Value = " + entry.getValue());
//                   databaseQueryClass.updateTressCount(entry.getKey(),Integer.parseInt(entry.getValue()),RequestData.getRequestCode());
//
//              }

                if (ch_withintercrop.isChecked())
                    Log.d("CLOSE LABOUR", "checkbox checked :" + true);
                else
                    Log.d("CLOSE LABOUR", "checkbox checked :" + false);


                if (tressCount.getText() != null || !TextUtils.isEmpty(tressCount.getText()) || !tressCount.getText().equals("") || !tressCount.getText().equals(" ")) {
                    try {
                        count = Integer.parseInt(tressCount.getText().toString());

                        // labourLeaderDetails.setTreesCount(count);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                if (bunchCount.getText() != null || !TextUtils.isEmpty(bunchCount.getText())  ) {
                    try {
                        bunchcounts = Integer.parseInt(bunchCount.getText().toString());
                        Log.e("bunchcounts===0",bunchcounts+"");
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }


                if (weight.getText() != null || !TextUtils.isEmpty(weight.getText()) || !weight.getText().equals("") || !weight.getText().equals(" ")) {

                    try {
                        weights = Double.parseDouble(weight.getText().toString());
                        Log.e("weights======",weights+"");

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }



                if (validatation()) {
                    if (isOnline()){

                        closeLabourRequest();}
                    else {
                        try {
                            saveLocal();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        if(isLabour)
        {
            if(laboursinfo != null && laboursinfo.size() > 1 )
            {
                Log.d("mahesh","********* in LABOUR COunt ********* :"+laboursinfo.get(0).getLabourCount());
                save_btn.setVisibility(View.GONE);
            }else
            {
                save_btn.setVisibility(View.VISIBLE);
            }
        }

    }
    private void saveLocal() throws ParseException {

        String jobDoneDateString = null;
        try {
             date_time = jobDoneDate.getText().toString() + curTime;
            Log.e("date_time==",date_time);
         //  jobDoneDateString = DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_19, DateTimeUtil.DATE_FORMAT_20, jobDoneDate.getText().toString());
            jobDoneDateString = DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_19, DateTimeUtil.DATE_FORMAT_20, date_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
  Log.e("jobDoneDateString==",jobDoneDateString);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeUtil.DATE_FORMAT_20);
        Date jobdone = simpleDateFormat.parse(jobDoneDateString);
        Log.e("jobDone====260",jobdone  + jobDoneDate.getText().toString());
        serviceType =CommonUtil.getServicename(databaseQueryClass.getLabouServicestByID(RequestData.getRequestCode()));
        Log.e("serviceType===260",jobDoneDateString + jobDoneDate.getText().toString());
        Toast.makeText(CloseLabourleaderRequestActivity.this, R.string.recordupdatestatus, Toast.LENGTH_SHORT).show();
        databaseQueryClass.deleteRequestHeaderRequestCode(RequestData.getRequestCode());
        RequestData.setStatusTypeId(39);
        RequestData.setUpdatedByUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
        RequestData.setUpdatedDate(jobDoneDateString);
        RequestData.setServerUpdatedStatus(1);

        databaseQueryClass.insertRequestHeader(RequestData);

        databaseQueryClass.deleteLabourusingRequestCode(labourData.getRequestCode());
//        labourData.setTreesCount(count);
        labourData.setUpdatedByUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
        labourData.setUpdatedDate(jobDoneDateString);
        labourData.setJobDoneDate(jobDoneDateString);
        labourData.setServerUpdatedStatus(1);

//        if (serviceType.contains("Pruning With Intercrop")) {
//            Log.e("serviceType===278",serviceType);
//            labourData.setTreesCount(0);
//            labourData.setTreesCountWithIntercrop(count);
//        } else {
//            labourData.setTreesCountWithIntercrop(0);
//            labourData.setTreesCount(count);
//            labourLeaderDetails.setTreesCount(count);
//            Log.e("serviceType===284",serviceType);
//        }
        for (SyncLabourServicesResponse.ListResult item :
                databaseQueryClass.getLabouServicestByID(labourData.getRequestCode())) {
            // check request type

            if(item.getServiceTypeId() == 20 || item.getServiceTypeId() == 34){

                labourData.setExpectedBunches(bunchcounts);
                labourData.setExpectedNetWeight(weights);
            }

            if (item.getServiceTypeId() == 19 ) {
// proning
                labourData.setTreesCountWithIntercrop(0);
                labourData.setTreesCount(count);
            } else if (item.getServiceTypeId() == 33) {
// proning with inter crop
                labourData.setTreesCount(0);
                labourData.setTreesCountWithIntercrop(count);
            }

        }
        databaseQueryClass.insertLabourRequest(labourData);
        //Todo Update
        //   databaseQueryClass.updateLabourDetails(labourLeaderDetails.getLabourId(),count);
        //  databaseQueryClass.insertLabourRequest(labourData);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", "Done");
        setResult(101, returnIntent);

        finish();

    }


    private boolean validatation() {
//        if (pin.getText() == null || TextUtils.isEmpty(pin.getText())) {
//            Toast.makeText(this, R.string.enterPin, Toast.LENGTH_SHORT).show();
//            return false;
//        }

//        if (Integer.parseInt(String.valueOf(pin.getText())) != labourData.getPin()) {
//            Toast.makeText(this, R.string.entervalidPin, Toast.LENGTH_SHORT).show();
//            return false;
     if (TextUtils.isEmpty(jobDoneDate.getText())) {
            Toast.makeText(this, R.string.selectDates, Toast.LENGTH_SHORT).show();
            return false;
        }  if (CommonUtil.validateassighneddate(labourData.getAssignedDate(), jobDoneDate.getText().toString())) {
            Toast.makeText(this, R.string.jobDoneDategreaterthanAssignedDate, Toast.LENGTH_SHORT).show();
            return false;
        }  if (CommonUtil.tresscountvalidate(databaseQueryClass.getLabouServicestByID(labourData.getRequestCode())) && (count == 0 || count < 1)) {

            Toast.makeText(this, R.string.entertrees,   Toast.LENGTH_SHORT).show();
            return false;

        }
        if ( CommonUtil.bunchesvalidate(databaseQueryClass.getLabouServicestByID(labourData.getRequestCode()))&& (bunchcounts == 0 || bunchcounts < 1)) {
            Toast.makeText(this, R.string.enter_bunch, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (lyt_weight.getVisibility() == View.VISIBLE ) {

            if (weight.getText() == null || TextUtils.isEmpty(weight.getText()) || weight.getText().equals("0")) {
                Toast.makeText(this, R.string.enter_weight, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if ( CommonUtil.bunchesvalidate(databaseQueryClass.getLabouServicestByID(labourData.getRequestCode()))  && (weights == 0.0 || weights < 1 )) {
            Toast.makeText(this, R.string.enter_weight, Toast.LENGTH_SHORT).show();
            return false;
        }
//        if (bunchCount.getText().toString().matches("0") && bunchCount.getText().toString().matches("") && (bunchcounts == 0 || bunchcounts < 1))  {
//            Toast.makeText(this, R.string.enter_bunch, Toast.LENGTH_SHORT).show();
//            return false;}
//        if( weight.getText().toString().matches("0.0") && (weight.getText().toString().matches("")) && weight.getText().toString().isEmpty() && weight.getText() == null && TextUtils.isEmpty(weight.getText())){
//
//            Toast.makeText(this, R.string.enter_weight, Toast.LENGTH_SHORT).show();
//            return false;
//        }


        return true;


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
                        String jobDoneDateString = null;
                        try {
                            jobDoneDateString = DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_22, DateTimeUtil.DATE_FORMAT_20, jobDoneDate.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
Log.e("jobDoneDateString===",jobDoneDateString +"**************"+ jobDoneDate.getText().toString());
                        if (closeLabourResponse.getIsSuccess()) {
                            Toast.makeText(CloseLabourleaderRequestActivity.this, closeLabourResponse.getEndUserMessage(), Toast.LENGTH_SHORT).show();
                            databaseQueryClass.deleteRequestHeaderRequestCode(RequestData.getRequestCode());
                            RequestData.setStatusTypeId(39);
                            RequestData.setUpdatedByUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
                            RequestData.setUpdatedDate(jobDoneDateString);
                            RequestData.setServerUpdatedStatus(0);
                            databaseQueryClass.insertRequestHeader(RequestData);

                            databaseQueryClass.deleteLabourusingRequestCode(labourData.getRequestCode());
                            labourData.setTreesCount(count);
                            labourData.setExpectedBunches(bunchcounts);
                            labourData.setExpectedNetWeight(weights);
                            labourData.setUpdatedByUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
                            labourData.setUpdatedDate(jobDoneDateString);
                            labourData.setJobDoneDate(jobDoneDateString);
                            labourData.setServerUpdatedStatus(1);
                            databaseQueryClass.insertLabourRequest(labourData);
                            //  databaseQueryClass.insertLabourRequest(labourData);
                            Intent returnIntent = new Intent();
                            returnIntent.putExtra("result", "Done");
                            setResult(101, returnIntent);
                            finish();
                        } else {
                            // Toast.makeText(CloseLabourleaderRequestActivity.this, "Please Reset Data", Toast.LENGTH_LONG).show();

                            Toast toast= Toast.makeText(getApplicationContext(),
                                    getString(R.string.reset_data), Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER| Gravity.CENTER_HORIZONTAL, 0, 0);
                            toast.show();
                        }
                    }





                });

    }

    private JsonObject closeLabourRequestObjectPage() {
        String date_time = jobDoneDate.getText().toString() + curTime;
        Log.e("date_time===",date_time);
        String jobDoneDateString = null;
        try {
            jobDoneDateString = DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_19, DateTimeUtil.DATE_FORMAT_20, date_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RequestData = databaseQueryClass.getRequestsByID(getIntent().getExtras().getString("id")).get(0);
        labourData = databaseQueryClass.getLabourRequestByID(getIntent().getExtras().getString("id")).get(0);


        List<LabourLeadersync.RequestHeader> requestHeaderList = new ArrayList<>();
        List<LabourLeadersync.LabourRequest> labourRequestList = new ArrayList<>();
        List<LabourLeadersync.LabourLeaderXref> LabourLeaderXreflist = new ArrayList<>();
// Request HEADER DETAILS CHANGE OR UPDATE
        LabourLeadersync.RequestHeader requestHeader = new LabourLeadersync.RequestHeader();

        requestHeader.setId(RequestData.getId());
//        requestHeader.setId(0);
        //2302208033743
        requestHeader.setRequestCode(RequestData.getRequestCode());
        requestHeader.setFarmerCode(RequestData.getFarmerCode());
        requestHeader.setPlotCode(RequestData.getPlotCode());
        requestHeader.setReqCreatedDate(RequestData.getCreatedDate());
        requestHeader.setStatusTypeId(39);
        requestHeader.setIsFarmerRequest(1);
        requestHeader.setCreatedDate(RequestData.getCreatedDate());
        requestHeader.setUpdatedByUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
        requestHeader.setUpdatedDate(DateTimeUtil.onGetCurrentDate(this));
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

        requestHeaderList.add(requestHeader);
        // Request LABOUR DETAILS CHANGE OR UPDATE

        LabourLeadersync.LabourRequest labourRequest = new LabourLeadersync.LabourRequest();
        labourRequest.setId(labourData.getId());
        labourRequest.setRequestCode(labourData.getRequestCode());
        labourRequest.setDurationId(labourData.getDurationId());
        labourRequest.setLeaderId(labourData.getLeaderId());
        labourRequest.setPin(labourData.getPin());
        labourRequest.setJobDoneDate(jobDoneDateString);
        labourRequest.setCreatedByUserId(labourData.getCreatedByUserId());
        labourRequest.setCreatedDate(labourData.getCreatedDate());
        labourRequest.setStartDate(labourData.getStartDate());
        labourRequest.setUpdatedByUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
        labourRequest.setUpdatedDate(DateTimeUtil.onGetCurrentDate(this));
        labourRequest.setAssignedDate(labourData.getAssignedDate());
        labourRequest.setNetWeight(labourData.getNetWeight());

        labourRequest.setAmount(labourData.getAmount());
        labourRequest.setHarvestingAmount(labourData.getHarvestingAmount());
        labourRequest.setPruningAmount(labourData.getPruningAmount());
        labourRequest.setPruningWithIntercropAmount(labourData.getPruningWithIntercropAmount());
        labourRequest.setHarvestingWithIntercropAmount(labourData.getHarvestingWithIntercropAmount());

        labourRequest.setOrderId(labourData.getOrderId());
        labourRequest.setServerUpdatedStatus(labourData.getServerUpdatedStatus());

        labourRequest.setTreesCount(0);
        labourRequest.setTreesCountWithIntercrop(0);

        for (SyncLabourServicesResponse.ListResult item :
                databaseQueryClass.getLabouServicestByID(labourData.getRequestCode())) {
            // check request type

            if(item.getServiceTypeId() == 20 || item.getServiceTypeId() == 34){

                labourRequest.setExpectedBunches(bunchcounts);
                labourRequest.setExpectedNetWeight(weights);
            }

            if (item.getServiceTypeId() == 19 ) {
// proning
                labourRequest.setTreesCountWithIntercrop(0);
                labourRequest.setTreesCount(count);
            } else if (item.getServiceTypeId() == 33) {
// proning with inter crop
                labourRequest.setTreesCount(0);
                labourRequest.setTreesCountWithIntercrop(count);
            }

        }


        labourRequest.setNetWeightIntercrop(labourData.getNetWeightIntercrop());

        List<SyncLabourLeaderDetailsResponse.ListResult> laboursinfo = databaseQueryClass.getRequestandLabours(labourData.getRequestCode());
        Log.e("============",laboursinfo.size()+"");
        for (int i=0;i<laboursinfo.size();i++)
        {
            LabourLeadersync.LabourLeaderXref labourLeaderXref = new LabourLeadersync.LabourLeaderXref();
            labourLeaderXref.setId(laboursinfo.get(i).getId());
            labourLeaderXref.setRequestCode(laboursinfo.get(i).getRequestCode());
            labourLeaderXref.setLabourId(laboursinfo.get(i).getLabourId());
            labourLeaderXref.setTreesCount(laboursinfo.get(i).getTreesCount());

            labourLeaderXref.setIsActive(laboursinfo.get(i).getIsActive());

            labourLeaderXref.setCreatedByUserId(laboursinfo.get(i).getCreatedByUserId());
            labourLeaderXref.setCreatedDate(laboursinfo.get(i).getCreatedDate());
            labourLeaderXref.setUpdatedByUserId(laboursinfo.get(i).getUpdatedByUserId());
            labourLeaderXref.setUpdatedDate(laboursinfo.get(i).getUpdatedDate());
            labourLeaderXref.setServerUpdatedStatus(laboursinfo.get(i).getServerUpdatedStatus());
            labourLeaderXref.setNetWeight(laboursinfo.get(i).getNetWeight());
            LabourLeaderXreflist.add(labourLeaderXref);

        }


        labourRequestList.add(labourRequest);





        LabourLeadersync syncPostModel = new LabourLeadersync(requestHeaderList, labourRequestList,LabourLeaderXreflist);
        return new Gson().toJsonTree(syncPostModel).getAsJsonObject();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", "Done");
        setResult(101, returnIntent);
        finish();
    }


    @Override
    public void onTextChanged(Integer charSeq,Map<Integer,String> items) {
        labourinfoMap = items;
        count =charSeq;
        tressCount.setText(String.valueOf(charSeq));

    }

//
//    @Override
//    public void onTextChanged(int position, String charSeq) {
//        enteredNumber[position] = Integer.valueOf(charSeq);
//        updateTotalValue();
//        //tressCount.setText(String.valueOf(charSeq));
//    }
//
//    private void updateTotalValue() {
//        int sum = 0;
//        for (int i = 0; i < 1000; i++) {
//            sum += enteredNumber[i];
//        }
//
//        tressCount.setText(String.valueOf(sum));
//    }
}
