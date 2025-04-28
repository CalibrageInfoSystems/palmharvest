package in.calibrage.palmharvest.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.ParseException;

import in.calibrage.palmharvest.Model.SyncFarmerDetailsResponse;
import in.calibrage.palmharvest.Model.SyncLabourResponse;
import in.calibrage.palmharvest.R;
import in.calibrage.palmharvest.common.CommonUtil;
import in.calibrage.palmharvest.common.DateTimeUtil;
import in.calibrage.palmharvest.database.DatabaseQueryClass;
import in.calibrage.palmharvest.sync.SyncRequestHeaderResponse;

public class ViewDetailsActivity extends AppCompatActivity {

    private DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(this);

    TextView id, requestCode, startDate, durationId, leaderId, pin, jobDoneDate, createdByUserId, createdDate, updatedByUserId, updatedDate, assignedDate, netWeight,
            amount, harvestingAmount, pruningAmount, unknown1Amount, unknown2Amount, treesCount, serverUpdatedStatus, farmerName, farmerMobileNumber,
            farmerContactNumber, palmArea, yearofPlanting, plotVillage, landmark, serviceType, tressCountwithintercrop,netweight,exp_bunches;

    LinearLayout lyt_id, lyt_requestCode, lyt_startDate, lyt_durationId, lyt_leaderId, lyt_pin, lyt_jobDoneDate, lyt_createdByUserId, lyt_createdDate, lyt_updatedByUserId,
            lyt_updatedDate, lyt_assignedDate, lyt_netWeight, lyt_amount, lyt_harvestingAmount, lyt_pruningAmount, lyt_unknown1Amount, lyt_unknown2Amount, lyt_treesCount,
            lyt_serverUpdatedStatus, lyt_farmerName, lyt_farmerMobileNumber,
            lyt_farmerContactNumber, lyt_palmArea, lyt_yearofPlanting, lyt_plotVillage, lyt_landmark, lyt_serviceType, lyt_treesCountwithintercrop,lyt_expected_bunches;

    ImageView back;
    Button close;
    DecimalFormat dec = new DecimalFormat("####0.00");
    SyncRequestHeaderResponse.ListResult RequestData;
    SyncLabourResponse.ListResult labourData;

    SyncFarmerDetailsResponse.ListResult farmerDetailsData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        id = findViewById(R.id.id);
        requestCode = findViewById(R.id.requestCode);
        startDate = findViewById(R.id.startDate);
        durationId = findViewById(R.id.durationId);
        leaderId = findViewById(R.id.leaderId);
        pin = findViewById(R.id.pin);
        jobDoneDate = findViewById(R.id.jobDoneDate);
        createdByUserId = findViewById(R.id.createdByUserId);
        createdDate = findViewById(R.id.createdDate);
        updatedByUserId = findViewById(R.id.updatedUserId);
        updatedDate = findViewById(R.id.updatedDate);
        assignedDate = findViewById(R.id.assignedDate);
       // netWeight = findViewById(R.id.netWeight);
        amount = findViewById(R.id.amount);
        harvestingAmount = findViewById(R.id.harvestingAmount);
        pruningAmount = findViewById(R.id.pruningAmount);
        unknown1Amount = findViewById(R.id.unknown1Amount);
        unknown2Amount = findViewById(R.id.unknown2Amount);
        treesCount = findViewById(R.id.tressCount);
        serverUpdatedStatus = findViewById(R.id.serverUpdatedStatus);
        farmerName = findViewById(R.id.farmerName);
        farmerMobileNumber = findViewById(R.id.farmerMobileNumber);
        farmerContactNumber = findViewById(R.id.farmerContactNumber);
        palmArea = findViewById(R.id.palmArea);
        yearofPlanting = findViewById(R.id.yearOfPlanting);
        plotVillage = findViewById(R.id.plotVillage);
        landmark = findViewById(R.id.landmark);
        serviceType = findViewById(R.id.serviceTypes);
      netweight=findViewById(R.id.netWeight);
        exp_bunches = findViewById(R.id.exp_bunches);
        tressCountwithintercrop = findViewById(R.id.tressCountwithintercrop);

        lyt_netWeight=findViewById(R.id.lyt_netWeight);
        lyt_farmerName = findViewById(R.id.lyt_farmerName);
        lyt_farmerMobileNumber = findViewById(R.id.lyt_farmerMobileNumber);
        lyt_farmerContactNumber = findViewById(R.id.lyt_farmerContactNumber);
        lyt_palmArea = findViewById(R.id.lyt_palmArea);
        lyt_yearofPlanting = findViewById(R.id.lyt_yearOfPlanting);
        lyt_plotVillage = findViewById(R.id.lyt_plotVillage);
        lyt_landmark = findViewById(R.id.lyt_landmark);
        lyt_serviceType = findViewById(R.id.lyt_serviceTypes);


        lyt_id = findViewById(R.id.lyt_id);
        lyt_requestCode = findViewById(R.id.lyt_requestCode);
        lyt_startDate = findViewById(R.id.lyt_startDate);
        lyt_durationId = findViewById(R.id.lyt_durationId);
        lyt_leaderId = findViewById(R.id.lyt_leaderId);
        lyt_pin = findViewById(R.id.lyt_pin);
        lyt_jobDoneDate = findViewById(R.id.lyt_jobDoneDate);
        lyt_createdByUserId = findViewById(R.id.lyt_createdByUserId);
        lyt_createdDate = findViewById(R.id.lyt_createdDate);
        lyt_updatedByUserId = findViewById(R.id.lyt_updatedByUserId);
        lyt_updatedDate = findViewById(R.id.lyt_updatedDate);
        lyt_assignedDate = findViewById(R.id.lyt_assignedDate);
        lyt_netWeight = findViewById(R.id.lyt_netWeight);
        lyt_amount = findViewById(R.id.lyt_amount);
        lyt_harvestingAmount = findViewById(R.id.lyt_harvestingAmount);
        lyt_pruningAmount = findViewById(R.id.lyt_pruningAmount);
        lyt_unknown1Amount = findViewById(R.id.lyt_unknown1Amount);
        lyt_unknown2Amount = findViewById(R.id.lyt_unknown2Amount);
        lyt_treesCount = findViewById(R.id.lyt_treesCount);
        tressCountwithintercrop = findViewById(R.id.tressCountwithintercrop);
        lyt_treesCountwithintercrop = findViewById(R.id.lyt_treesCountwithintercrop);
        lyt_serverUpdatedStatus = findViewById(R.id.lyt_serverStatus);
        lyt_expected_bunches=findViewById(R.id.lyt_expected_bunches);

        String RequestCode = getIntent().getExtras().getString("id");
        Log.d("FullDetails", "Code ;" + RequestCode);
        if (!TextUtils.isEmpty(RequestCode)) {
            RequestData = databaseQueryClass.getRequestsByID(getIntent().getExtras().getString("id")).get(0);
            labourData = databaseQueryClass.getLabourRequestByID(getIntent().getExtras().getString("id")).get(0);

            farmerDetailsData = databaseQueryClass.getFarmerDetailsByID(getIntent().getExtras().getString("id")).get(0);
        }

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        close = findViewById(R.id.close_Btn);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


        if (RequestData.getRequestCode() != null && !(TextUtils.isEmpty("" + RequestData.getRequestCode()))) {

            requestCode.setText(":  " + RequestData.getRequestCode());

        } else {

            lyt_requestCode.setVisibility(View.GONE);
        }

        if (labourData.getStartDate() != null && !(TextUtils.isEmpty("" + labourData.getStartDate()))) {

            try {
                startDate.setText(":  " + DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_20, DateTimeUtil.DATE_FORMAT_22, labourData.getAssignedDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else {

            lyt_startDate.setVisibility(View.GONE);
        }


        if (labourData.getJobDoneDate() != null && !(TextUtils.isEmpty("" + labourData.getJobDoneDate()))) {

            try {
                jobDoneDate.setText(":  " + DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_20, DateTimeUtil.DATE_FORMAT_22, labourData.getJobDoneDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else {

            lyt_jobDoneDate.setVisibility(View.GONE);
        }


        if (labourData.getTreesCount() != 0 && labourData.getTreesCount() != null && !(TextUtils.isEmpty("" + labourData.getTreesCount()))) {

            treesCount.setText(":  " + labourData.getTreesCount());

        } else {

            lyt_treesCount.setVisibility(View.GONE);
        }

        if (  labourData.getExpectedNetWeight() != 0.0 && labourData.getExpectedNetWeight() != null && !(TextUtils.isEmpty("" + labourData.getExpectedNetWeight()))) {

            netweight.setText(":  " +labourData.getExpectedNetWeight());

        } else {

            lyt_netWeight.setVisibility(View.GONE);
        }
        if (  labourData.getExpectedBunches() != 0 && labourData.getExpectedBunches() != null && !(TextUtils.isEmpty("" + labourData.getExpectedBunches()))) {

            exp_bunches.setText(":  " + labourData.getExpectedBunches());

        } else {

            lyt_expected_bunches.setVisibility(View.GONE);
        }

        if (labourData.getTreesCountWithIntercrop() != null && labourData.getTreesCountWithIntercrop() != 0 && !(TextUtils.isEmpty("" + labourData.getTreesCountWithIntercrop()))) {

            tressCountwithintercrop.setText(":  " + labourData.getTreesCountWithIntercrop());
        } else {

            lyt_treesCountwithintercrop.setVisibility(View.GONE);
        }

        if (RequestData.getFarmerName() != null && !(TextUtils.isEmpty("" + RequestData.getFarmerName()))) {

            farmerName.setText(":  " + RequestData.getFarmerName());

        } else {

            lyt_farmerName.setVisibility(View.GONE);
        }

        if (farmerDetailsData.getMobileNumber() != null && !(TextUtils.isEmpty("" + farmerDetailsData.getMobileNumber()))) {

            farmerMobileNumber.setText(":  " + farmerDetailsData.getMobileNumber());

        } else {

            lyt_farmerMobileNumber.setVisibility(View.GONE);
        }

        if (farmerDetailsData.getContactNumber() != null && !(TextUtils.isEmpty("" + farmerDetailsData.getContactNumber()))) {

            farmerContactNumber.setText(":  " + farmerDetailsData.getContactNumber());

        } else {

            lyt_farmerContactNumber.setVisibility(View.GONE);
        }

        if ((RequestData.getPalmArea() != 0) && !(TextUtils.isEmpty("" + RequestData.getPalmArea()))) {

            palmArea.setText(":  " + RequestData.getPalmArea() + " Ha");

        } else {

            lyt_palmArea.setVisibility(View.GONE);
        }

        if ((RequestData.getYearofPlanting() != null) && !(TextUtils.isEmpty("" + RequestData.getYearofPlanting()))) {

            try {
                yearofPlanting.setText(":  " + DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_20, DateTimeUtil.DATE_FORMAT_23, RequestData.getYearofPlanting()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else {

            lyt_yearofPlanting.setVisibility(View.GONE);
        }

        if ((RequestData.getPlotVillage() != null) && !(TextUtils.isEmpty("" + RequestData.getPlotVillage()))) {

            plotVillage.setText(":  " + RequestData.getPlotVillage());

        } else {

            lyt_plotVillage.setVisibility(View.GONE);
        }

        if ((farmerDetailsData.getLandmark() != null) && !(TextUtils.isEmpty("" + farmerDetailsData.getLandmark()))) {

            landmark.setText(":  " + farmerDetailsData.getLandmark());

        } else {

            lyt_landmark.setVisibility(View.GONE);
        }


        serviceType.setText(":  " + CommonUtil.getServicename(databaseQueryClass.getLabouServicestByID(RequestCode)));


        farmerMobileNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" + farmerDetailsData.getMobileNumber());


                Intent i = new Intent(Intent.ACTION_DIAL, u);

                try {

                    startActivity(i);
                } catch (SecurityException s) {

                    Toast.makeText(ViewDetailsActivity.this, "SecurityException", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        farmerContactNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" + farmerDetailsData.getContactNumber());


                Intent i = new Intent(Intent.ACTION_DIAL, u);

                try {

                    startActivity(i);
                } catch (SecurityException s) {

                    Toast.makeText(ViewDetailsActivity.this, "SecurityException", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

      //  lyt_netWeight.setVisibility(View.GONE);

//        for (SyncLabourServicesResponse.ListResult item :
//                databaseQueryClass.getLabouServicestByID(RequestCode)) {
//            // check request type
//            if(item.getServiceTypeId() == 19 || item.getServiceTypeId()==33)
//            {
//                lyt_treesCountwithintercrop.setVisibility(View.GONE);
//                lyt_treesCount.setVisibility(View.GONE);
//            }
//
//        }

    }
}
