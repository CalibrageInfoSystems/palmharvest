package in.calibrage.palmharvest.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import in.calibrage.palmharvest.Model.CloseLabourResponse;
import in.calibrage.palmharvest.Model.SyncFarmerDetailsResponse;
import in.calibrage.palmharvest.Model.SyncLabourLeaderDetailsResponse;
import in.calibrage.palmharvest.Model.SyncLabourResponse;
import in.calibrage.palmharvest.Model.SyncLabourServicesResponse;
import in.calibrage.palmharvest.Model.SyncPost;
import in.calibrage.palmharvest.Model.SyncPost.RequestHeader;
import in.calibrage.palmharvest.R;
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

public class CloseLabourRequestActivity extends BaseActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_labour_request);

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

       // serviceType =getIntent().getExtras().getString("serviceTypes");
        RequestData = databaseQueryClass.getRequestsByID(getIntent().getExtras().getString("id")).get(0);
        labourData = databaseQueryClass.getLabourRequestByID(getIntent().getExtras().getString("id")).get(0);
        labourServicesData = databaseQueryClass.getLabouServicestByID(getIntent().getExtras().getString("id")).get(0);
        farmerDetailsData = databaseQueryClass.getFarmerDetailsByID(getIntent().getExtras().getString("id")).get(0);

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
   bunchCount.setText("" + labourData.getExpectedBunches());

      weight.setText("" + labourData.getExpectedNetWeight());
        lyt_treesCount = findViewById(R.id.lyt_treesCount);
        TextView startDate = findViewById(R.id.startDate);
        try {
            startDate.setText("" + DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_20, DateTimeUtil.DATE_FORMAT_22, labourData.getAssignedDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

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

        if (serviceTypes.getText().toString().contains("Pruning")) {
            lyt_treesCount.setVisibility(View.VISIBLE);


        } else {
            lyt_treesCount.setVisibility(View.GONE);

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
                        CloseLabourRequestActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {


                        datePicker.setMaxDate(System.currentTimeMillis());

                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
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
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }



                if (validatation()) {
                    if (isOnline()){

                        closeLabourRequest();}
                    else
                        saveLocal();
                }
            }
        });
    }

    private void saveLocal() {

        String jobDoneDateString = null;
        try {
            jobDoneDateString = DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_22, DateTimeUtil.DATE_FORMAT_20, jobDoneDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        serviceType =CommonUtil.getServicename(databaseQueryClass.getLabouServicestByID(RequestData.getRequestCode()));
        Log.e("serviceType===260",serviceType);
        Toast.makeText(CloseLabourRequestActivity.this, R.string.recordupdatestatus, Toast.LENGTH_SHORT).show();
        databaseQueryClass.deleteRequestHeaderRequestCode(RequestData.getRequestCode());
        RequestData.setStatusTypeId(39);
        RequestData.setUpdatedByUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
        RequestData.setUpdatedDate(jobDoneDateString);
        RequestData.setServerUpdatedStatus(1);
        Log.e("*****jobDoneDate==1",jobDoneDateString);
        databaseQueryClass.insertRequestHeader(RequestData);

        databaseQueryClass.deleteLabourusingRequestCode(labourData.getRequestCode());
//        labourData.setTreesCount(count);
        labourData.setUpdatedByUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
        labourData.setUpdatedDate(jobDoneDateString);
        labourData.setJobDoneDate(jobDoneDateString);
        labourData.setServerUpdatedStatus(1);
        Log.e("*****jobDoneDate==2",jobDoneDateString);
        if (serviceType.contains("Pruning With Intercrop")) {
            Log.e("serviceType===278",serviceType);
            labourData.setTreesCount(0);
            labourData.setTreesCountWithIntercrop(count);
            //labourLeaderDetails.setTreesCountWithIntercrop(count);
        } else {
            labourData.setTreesCountWithIntercrop(0);
            labourData.setTreesCount(count);
            labourLeaderDetails.setTreesCount(count);
            Log.e("serviceType===284",serviceType);
        }
        databaseQueryClass.insertLabourRequest(labourData);
       // databaseQueryClass.updateLabourDetails(labourLeaderDetails.getLabourId(),count);
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
//
//         if (Integer.parseInt(String.valueOf(pin.getText())) != labourData.getPin()) {
//            Toast.makeText(this, R.string.entervalidPin, Toast.LENGTH_SHORT).show();
//            return false;
//        }
         if (TextUtils.isEmpty(jobDoneDate.getText())) {
            Toast.makeText(this, R.string.selectDates, Toast.LENGTH_SHORT).show();
            return false;
        }  if (CommonUtil.validateassighneddate(labourData.getAssignedDate(), jobDoneDate.getText().toString())) {
            Toast.makeText(this, R.string.jobDoneDategreaterthanAssignedDate, Toast.LENGTH_SHORT).show();
            return false;
        }  if (CommonUtil.tresscountvalidate(databaseQueryClass.getLabouServicestByID(labourData.getRequestCode())) && (count == 0 || count < 1)) {

            Toast.makeText(this, R.string.entertrees, Toast.LENGTH_SHORT).show();
            return false;

        }
        if ( CommonUtil.bunchesvalidate(databaseQueryClass.getLabouServicestByID(labourData.getRequestCode()))&& (bunchcounts == 0 || bunchcounts < 1)) {
            Toast.makeText(this, R.string.enter_bunch, Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( CommonUtil.bunchesvalidate(databaseQueryClass.getLabouServicestByID(labourData.getRequestCode()))  && (weights == 0 || weights < 1) && weight.getText() == null && TextUtils.isEmpty(weight.getText())) {
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

                        if (closeLabourResponse.getIsSuccess()) {
                            Toast.makeText(CloseLabourRequestActivity.this, closeLabourResponse.getEndUserMessage(), Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(CloseLabourRequestActivity.this, getString(R.string.reset_data), Toast.LENGTH_LONG).show();
                        }
                    }

                });

    }

    private JsonObject closeLabourRequestObjectPage() {

        String jobDoneDateString = null;
        try {
            jobDoneDateString = DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_22, DateTimeUtil.DATE_FORMAT_20, jobDoneDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RequestData = databaseQueryClass.getRequestsByID(getIntent().getExtras().getString("id")).get(0);
        labourData = databaseQueryClass.getLabourRequestByID(getIntent().getExtras().getString("id")).get(0);


        List<RequestHeader> requestHeaderList = new ArrayList<>();
        List<SyncPost.LabourRequest> labourRequestList = new ArrayList<>();
// Request HEADER DETAILS CHANGE OR UPDATE
        RequestHeader requestHeader = new RequestHeader();

        requestHeader.setId(RequestData.getId());
//        requestHeader.setId(0);
        //2302208033743
        requestHeader.setRequestCode(RequestData.getRequestCode());
        requestHeader.setFarmerCode(RequestData.getFarmerCode());
        requestHeader.setPlotCode(RequestData.getPlotCode());
        requestHeader.setReqCreatedDate(RequestData.getCreatedDate());
        requestHeader.setStatusTypeId(39);
        requestHeader.setIsFarmerRequest(RequestData.getIsFarmerRequest());
        requestHeader.setCreatedDate(RequestData.getCreatedDate());
        requestHeader.setUpdatedByUserId(SharedPrefsData.getCatagoriess(getApplication()).getResult().getId());
        requestHeader.setUpdatedDate(jobDoneDateString);
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

        SyncPost.LabourRequest labourRequest = new SyncPost.LabourRequest();
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
        labourRequest.setUnKnown1Amount(labourData.getPruningWithIntercropAmount());
        labourRequest.setUnKnown2Amount(labourData.getHarvestingWithIntercropAmount());

        labourRequest.setOrderId(labourData.getOrderId());
        labourRequest.setServerUpdatedStatus(labourData.getServerUpdatedStatus());

        labourRequest.setTreesCount(0);
        labourRequest.setTreesCountWithIntercrop(0);

        for (SyncLabourServicesResponse.ListResult item :
                databaseQueryClass.getLabouServicestByID(labourData.getRequestCode())) {
            // check request type

            if(item.getServiceTypeId() == 20){

                labourRequest.setExpectedBunches(bunchcounts);
                labourRequest.setExpectedNetWeight(weights);
            }

            if (item.getServiceTypeId() == 19) {
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


        labourRequestList.add(labourRequest);

        SyncPost syncPostModel = new SyncPost(requestHeaderList, labourRequestList);
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
}
