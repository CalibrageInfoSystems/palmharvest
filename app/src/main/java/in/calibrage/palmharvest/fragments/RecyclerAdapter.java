package in.calibrage.palmharvest.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import in.calibrage.palmharvest.Activities.HomeActivity;
import in.calibrage.palmharvest.Activities.ViewDetailsActivity;
import in.calibrage.palmharvest.Model.RequestCompleteModel;
import in.calibrage.palmharvest.Model.SyncLabourLeaderDetailsResponse;
import in.calibrage.palmharvest.R;
import in.calibrage.palmharvest.adapter.NotificationListner;
import in.calibrage.palmharvest.common.DateTimeUtil;
import in.calibrage.palmharvest.database.DatabaseQueryClass;
import in.calibrage.palmharvest.localData.SharedPrefsData;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private LayoutInflater layoutInflater;

    DatabaseQueryClass _databaseQueryClass;
    HomeActivity homeActivity;
    private List<RequestCompleteModel> allrequests;
    private Context ctx;
    private NotificationListner listner;
    DecimalFormat dec = new DecimalFormat("####0.00");
    public RecyclerAdapter(Context ctx, List<RequestCompleteModel> allrequests, NotificationListner listner,DatabaseQueryClass databaseQueryClass) {
        this.layoutInflater = LayoutInflater.from(ctx);
        this.ctx = ctx;
        this.listner = listner;
        this.allrequests = allrequests;
        this._databaseQueryClass=databaseQueryClass;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.recycler_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        if (allrequests.get(position).getRequestCode() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getRequestCode()))) {
            holder.requestCode.setText(":  " + allrequests.get(position).getRequestCode());
        } else {
            holder.lytRequestCode.setVisibility(View.GONE);

        }

        if (allrequests.get(position).getServicesname() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getRequestCode()))){
            holder.serviceTypes.setText(":" + allrequests.get(position).getServicesname());
        }else{

            holder.lyt_serviceTypes.setVisibility(View.GONE);
        }


        //holder.startDate.setText(":  "+items.get(position).getStartDate()  );
        if (allrequests.get(position).getStartDate() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getStartDate()))) {

            try {
                holder.startDate.setText(":  " + DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_20, DateTimeUtil.DATE_FORMAT_22, allrequests.get(position).getStartDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {

            holder.lytStartDate.setVisibility(View.GONE);
        }
//        holder.durationId.setText(":  " + items.get(position).getDurationId());
//        holder.leaderId.setText(":  " + items.get(position).getLeaderId());
//        holder.pin.setText(":  " + items.get(position).getPin());
        // holder.jobDoneDate.setText(":  "+items.get(position).getJobDoneDate());

        if (allrequests.get(position).getCreatedDate() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getCreatedDate()))) {

            try {
                holder.createdDate.setText(":  " + DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_20, DateTimeUtil.DATE_FORMAT_22, allrequests.get(position).getCreatedDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            holder.lytCreatedDate.setVisibility(View.GONE);
        }

        if (allrequests.get(position).getUpdatedDate() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getUpdatedDate()))) {

            try {
                holder.updatedDate.setText(":  " + DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_20, DateTimeUtil.DATE_FORMAT_22, allrequests.get(position).getUpdatedDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            holder.lytUpdatedDate.setVisibility(View.GONE);
        }

        if (allrequests.get(position).getJobDoneDate() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getJobDoneDate()))) {

            try {
                holder.jobDoneDate.setText(":  " + DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_20, DateTimeUtil.DATE_FORMAT_22, allrequests.get(position).getJobDoneDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            holder.lytJobDoneDate.setVisibility(View.GONE);

        }
        holder.statusTypeId.setText(":  " + allrequests.get(position).getStatusTypeID());

        if (allrequests.get(position).getYearOfplanting() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getYearOfplanting()))) {

            try {
                holder.yearOfplanting.setText(":  " + DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_20, DateTimeUtil.DATE_FORMAT_23, allrequests.get(position).getYearOfplanting()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            holder.lyt_yearOfplanting.setVisibility(View.GONE);

        }

        if (allrequests.get(position).getTressCount() != null && !allrequests.get(position).getTressCount().isEmpty() && !allrequests.get(position).getTressCount().equals("null") && !allrequests.get(position).getTressCount().equals("0")  ){
            holder.lytTreesCount.setVisibility(View.VISIBLE);
            holder.treesCount.setText(":  " + allrequests.get(position).getTressCount());


        }

        else{
            holder.lytTreesCount.setVisibility(View.GONE);
        }

        Log.e("intercroptreescount",allrequests.get(position).getInter_treescount()+"");
        if(allrequests.get(position).getInter_treescount() != null && !allrequests.get(position).getInter_treescount().isEmpty() && !allrequests.get(position).getInter_treescount().equals("null") && !allrequests.get(position).getInter_treescount().equals("0")) {
 holder.lytintercropTreesCount.setVisibility(View.VISIBLE);
            holder.intercrop_treecount.setText(":  " + allrequests.get(position).getInter_treescount());

        }else{
            holder.lytintercropTreesCount.setVisibility(View.GONE);
        }
        Log.d("Servicesname===74", allrequests.get(position).getEXp_bunches()+"====="+ allrequests.get(position).getRequestCode());
        if (allrequests.get(position).getEXp_bunches() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getEXp_bunches())) && Integer.valueOf(allrequests.get(position).getEXp_bunches())!=0 && !allrequests.get(position).getEXp_bunches().equals("0")){
            holder.lyt_expected_bunches.setVisibility(View.VISIBLE);
            holder.exp_bunches.setText(":  " + allrequests.get(position).getEXp_bunches());
        }
else{
            holder.lyt_expected_bunches.setVisibility(View.GONE);
        }
        if (allrequests.get(position).getExp_netweight() != null &&  !(TextUtils.isEmpty("" + allrequests.get(position).getExp_netweight()))&& allrequests.get(position).getExp_netweight() !=0.0 ){
            holder.lytNetWeight.setVisibility(View.VISIBLE);
            holder.netWeight.setText(":  " + allrequests.get(position).getExp_netweight());
        }
        else{
            holder.lytNetWeight.setVisibility(View.GONE);
        }
//        holder.createdByUserId.setText("" + items.get(position).getCreatedByUserId());
//        //holder.createdDate.setText(":  "+items.get(position).getCreatedDate());
//
//
//        holder.updatedByUserId.setText("" + items.get(position).getUpdatedByUserId());
//        // holder.updatedDate.setText(":  "+items.get(position).getUpdatedDate());


        //  holder.assignedDate.setText(":  "+items.get(position).getAssignedDate());

//        if (items.get(position).getAssignedDate() != null && !(TextUtils.isEmpty("" + items.get(position).getAssignedDate()))) {
//            try {
//                holder.assignedDate.setText(":  " + DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_20, DateTimeUtil.DATE_FORMAT_21, items.get(position).getAssignedDate()));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        } else {
//            holder.lytAssignedDate.setVisibility(View.GONE);
//        }


//        if(allrequests.get(position).getNetWeight() == null) {
//
//            holder.lytNetWeight.setVisibility(View.GONE);
//        }
//        else{
//
//            holder.lytNetWeight.setVisibility(View.VISIBLE);
//            holder.treesCount.setText(":  " + allrequests.get(position).getNetWeight());
//        }


//        if (allrequests.get(position).getNetWeight() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getNetWeight()))) {
//            holder.netWeight.setText(":  " + allrequests.get(position).getNetWeight());
//            Log.e("====",allrequests.get(position).getNetWeight());

        Log.e("Roja============", allrequests.get(position).getRequestCode()+"==="+allrequests.get(position).getExp_netweight() + "======"+ allrequests.get(position).getEXp_bunches()+"======"+ allrequests.get(position).getTressCount());
//        holder.exp_bunches.setText(":  " + allrequests.get(position).getEXp_bunches());
//        holder.netWeight.setText(":  " + allrequests.get(position).getExp_netweight());
//        if (allrequests.get(position).getEXp_bunches() != null && !allrequests.get(position).getEXp_bunches().equalsIgnoreCase("0") && !(TextUtils.isEmpty("" + allrequests.get(position).getEXp_bunches()))) {
//            holder.exp_bunches.setText(":  " + allrequests.get(position).getEXp_bunches() );
//        } else {
//            holder.lyt_expected_bunches.setVisibility(View.GONE);
//        }
//
//        if (allrequests.get(position).getExp_netweight() != null  && !allrequests.get(position).getExp_netweight().equalsIgnoreCase("0") && !(TextUtils.isEmpty("" + allrequests.get(position).getExp_netweight()))) {
//            holder.netWeight.setText(":  " + allrequests.get(position).getExp_netweight() );
//        } else {
//            holder.lytNetWeight.setVisibility(View.GONE);
//        }
        if (allrequests.get(position).getPalmArea() != null  && !(TextUtils.isEmpty("" + allrequests.get(position).getPalmArea()))) {
            holder.palmArea.setText(":  " + allrequests.get(position).getPalmArea() + " Ha");
        } else {
            holder.lyt_palmArea.setVisibility(View.GONE);
        }
//        holder.amount.setText(":  " + items.get(position).getAmount());
//        holder.harvestingAmount.setText(":  " + items.get(position).getHarvestingAmount());
//        holder.pruningAmount.setText(":  " + items.get(position).getPruningAmount());
//        holder.unknown1Amount.setText(":  " + items.get(position).getUnKnown1Amount());
//        holder.unknown2Amount.setText(":  " + items.get(position).getUnKnown2Amount());
     Log.e("===========",allrequests.get(position).getTressCount()+"");


//        if ( allrequests.get(position).getTressCount() != null && !allrequests.get(position).getTressCount().equalsIgnoreCase("0") &&  !(TextUtils.isEmpty("" + allrequests.get(position).getTressCount()))) {
//
//            holder.treesCount.setText(":  " + allrequests.get(position).getTressCount());
//        } else {
//
//            holder.lytTreesCount.setVisibility(View.GONE);
//        }


        if (allrequests.get(position).getFarmername() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getFarmername()))) {

            holder.farmername.setText(":  " + allrequests.get(position).getFarmername());
        } else {

            holder.lyt_farmername.setVisibility(View.GONE);
        }

        if (allrequests.get(position).getFormerMobileNumber() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getFormerMobileNumber()))) {

            holder.formerMobileNumber.setText(":  " + allrequests.get(position).getFormerMobileNumber());
        } else {

            holder.lyt_formerMobileNumber.setVisibility(View.GONE);
        }

        if (allrequests.get(position).getFormerContactNumber() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getFormerContactNumber()))) {

            holder.formerContactNumber.setText(":  " + allrequests.get(position).getFormerContactNumber());
        } else {

            holder.lyt_formerContactNumber.setVisibility(View.GONE);
        }


        if (allrequests.get(position).getPlotVilalge() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getPlotVilalge()))) {

            holder.plotVillage.setText(":  " + allrequests.get(position).getPlotVilalge());
        } else {

            holder.lyt_plotvillage.setVisibility(View.GONE);
        }

        if (allrequests.get(position).getLandmark() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getLandmark()))) {

            holder.landmark.setText(":  " + allrequests.get(position).getLandmark());
        } else {

            holder.lyt_landmark.setVisibility(View.GONE);
        }

//        if (allrequests.get(position).getLattitude() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getLattitude()))) {
//
//            holder.lattitude.setText(":  " + allrequests.get(position).getLattitude());
//        } else {
//
//            holder.lyt_lattitude.setVisibility(View.GONE);
//        }
//
//        if (allrequests.get(position).getLongitude() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getLongitude()))) {
//
//            holder.longitude.setText(":  " + allrequests.get(position).getLongitude());
//        } else {
//
//            holder.lyt_longitude.setVisibility(View.GONE);
//        }

//        if (allrequests.get(position).getServiceTypes() != null && !(TextUtils.isEmpty("" + allrequests.get(position).getServiceTypes()))) {
//
//            holder.serviceTypes.setText(":  " + allrequests.get(position).getServicesname());
//        } else {
//
//            holder.lyt_serviceTypes.setVisibility(View.GONE);
//        }
        holder.serviceTypes.setText(":  " + allrequests.get(position).getServicesname());
        holder.formerMobileNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" + allrequests.get(position).getFormerMobileNumber());


                Intent i = new Intent(Intent.ACTION_DIAL, u);

                try {

                    ctx.startActivity(i);
                } catch (SecurityException s) {

                    Toast.makeText(ctx, "SecurityException", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        holder.formerContactNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" + allrequests.get(position).getFormerContactNumber());


                Intent i = new Intent(Intent.ACTION_DIAL, u);

                try {

                    ctx.startActivity(i);
                } catch (SecurityException s) {

                    Toast.makeText(ctx, "SecurityException", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        List<SyncLabourLeaderDetailsResponse.ListResult> data=  _databaseQueryClass.getRequestandLabours(allrequests.get(position).getRequestCode());
        if((data != null && data.size() > 0 && data.get(0).getLabourCount() > 1) || data.size() ==0 )
        {
            holder.closeRequestBtn.setVisibility(View.GONE);
        }else
        {
            holder.closeRequestBtn.setVisibility(View.VISIBLE);
        }

        if(SharedPrefsData.getCatagoriess(ctx).getResult().getRoleId() !=2)
        {
            Log.d("MAHESH","RoleID != 2");
            holder.closeRequestBtn.setVisibility(View.VISIBLE);
        }


        holder.closeRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.closeRequestBtn.isEnabled()) {
                    listner.onNotificationClick(position, allrequests.get(position));
//                    Intent intent = new Intent(ctx, CloseLabourRequestActivity.class);
//                    intent.putExtra("id", allrequests.get(position).getRequestCode());
//                    intent.putExtra("code", allrequests.get(position).getFarmername());
//                    intent.putExtra("name", allrequests.get(position).getFarmername());
//                    intent.putExtra("plotcode", allrequests.get(position).getPalmArea());
//                    ctx.startActivity(intent);
                } else {
                    Toast.makeText(homeActivity, "Alredy Job Done", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.closeRequestBtn.setEnabled(true);
        holder.closeRequestBtn.setBackgroundResource(R.drawable.button_bg);
        if (allrequests.size() > position && allrequests.get(position).getStatusTypeID() != 17) {
            holder.closeRequestBtn.setEnabled(false);
            holder.closeRequestBtn.setBackgroundResource(R.drawable.button_bg_disabled);
        }
        if(allrequests.get(position).getStatusTypeID() != 40){
            holder.cardview.setVisibility(View.VISIBLE);
        }
        else{
            holder.cardview.setVisibility(View.GONE);
        }


        holder.viewDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ctx, ViewDetailsActivity.class);

                intent.putExtra("id", "" + allrequests.get(position).getRequestCode());
                intent.putExtra("drequestCode", "" + allrequests.get(position).getRequestCode());

                if (!TextUtils.isEmpty(allrequests.get(position).getStartDate())) {

                    try {
                        intent.putExtra("dstartDate", "" + DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_20, DateTimeUtil.DATE_FORMAT_21, allrequests.get(position).getStartDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {

                    intent.putExtra("dstartDate", "");
                }
//                intent.putExtra("ddurationId", "" + allrequests.get(position).getDurationId());
//                intent.putExtra("dleaderId", "" + allrequests.get(position).getLeaderId());
//                intent.putExtra("dpin", "" + allrequests.get(position).getPin());
                if (!TextUtils.isEmpty(allrequests.get(position).getJobDoneDate())) {
                    try {
                        intent.putExtra("djobDoneDate", "" + DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_20, DateTimeUtil.DATE_FORMAT_22, allrequests.get(position).getJobDoneDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    intent.putExtra("djobDoneDate", "");
                }

                // intent.putExtra("dCreatedbyUserUd", "" + allrequests.get(position).getCreatedByUserId());


                if (!TextUtils.isEmpty(allrequests.get(position).getCreatedDate())) {
                    try {
                        intent.putExtra("dcreatedDate", "" + DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_20, DateTimeUtil.DATE_FORMAT_22, allrequests.get(position).getCreatedDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    intent.putExtra("dcreatedDate", "");

                }
                //intent.putExtra("dupdatedByuserId", "" + allrequests.get(position).getUpdatedByUserId());

                if (!TextUtils.isEmpty(allrequests.get(position).getUpdatedDate())) {

                    try {
                        intent.putExtra("dupdatedDate", "" + DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_20, DateTimeUtil.DATE_FORMAT_22, allrequests.get(position).getUpdatedDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {

                    intent.putExtra("dupdatedDate", "");
                }

//                if (!TextUtils.isEmpty(allrequests.get(position).getAssignedDate())) {
//
//                    try {
//                        intent.putExtra("dassignedDate", "" + DateTimeUtil.formatDateFromDateString(DateTimeUtil.DATE_FORMAT_20, DateTimeUtil.DATE_FORMAT_21, items.get(position).getAssignedDate()));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }else {
//                    intent.putExtra("dassignedDate", "");
//
//                }
//                intent.putExtra("dnetWeight", "" + items.get(position).getNetWeight());
//                intent.putExtra("dAmount", "" + items.get(position).getAmount());
//                intent.putExtra("dharvestingAmount", "" + items.get(position).getHarvestingAmount());
//                intent.putExtra("dPruningAmount", "" + items.get(position).getPruningAmount());
//                intent.putExtra("dunknwon1Amount", "" + items.get(position).getUnKnown1Amount());
//                intent.putExtra("dunknwon2Amount", "" + items.get(position).getUnKnown2Amount());
                intent.putExtra("dTressCount", "" + allrequests.get(position).getTressCount());
//                intent.putExtra("dServerStatus", "" + items.get(position).getServerUpdatedStatus());

                ctx.startActivity(intent);

            }
        });

        holder.viewOnMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(ctx, MapActivity.class);
//                ctx.startActivity(intent);
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("https")
                        .authority("www.google.com")
                        .appendPath("maps")
                        .appendPath("dir")
                        .appendPath("")
                        .appendQueryParameter("api", "1")
                        .appendQueryParameter("destination", allrequests.get(position).getLattitude() + "," + allrequests.get(position).getLongitude());
                String url = builder.build().toString();
                Log.d("Directions", url);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                ctx.startActivity(i);
            }
        });
       // holder.lytNetWeight.setVisibility(View.GONE);

      /*  try {
            for (SyncLabourServicesResponse.ListResult item :
                    databaseQueryClass.getLabouServicestByID(allrequests.get(position).getRequestCode())) {
                // check request type
                if(item.getServiceTypeId() == 19 || item.getServiceTypeId()==33)
                {

                    holder.lytTreesCount.setVisibility(View.GONE);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
//        if (holder.serviceTypes.getText().toString().contains("Pruning")) {
//            holder.lytTreesCount.setVisibility(View.VISIBLE);
//
//        }
//        else if(holder.serviceTypes.getText().toString().contains("Harvesting")){
//
//            holder.lytNetWeight.setVisibility(View.VISIBLE);
//            holder.lyt_expected_bunches.setVisibility(View.VISIBLE);
//        }


    }

    @Override
    public int getItemCount() {
        return allrequests.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView requestCode;
        TextView startDate;
        TextView durationId;
        TextView leaderId;
        TextView pin;
        TextView jobDoneDate;
        TextView createdByUserId;
        TextView createdDate;
        TextView updatedByUserId;
        TextView updatedDate;
        TextView assignedDate;
        TextView netWeight;
        TextView amount;
        TextView harvestingAmount;
        TextView pruningAmount;
        TextView unknown1Amount;
        TextView unknown2Amount;
        TextView treesCount,intercrop_treecount;
        TextView serverUpdatedStatus;
        CardView cardview;
        TextView statusTypeId;
        TextView yearOfplanting;
        TextView palmArea;
        TextView farmername;
        TextView formerMobileNumber;
        TextView formerContactNumber;
        TextView plotVillage;
        TextView landmark,exp_bunches;
        //        TextView lattitude;
//        TextView longitude;
        TextView serviceTypes;

        LinearLayout lyt_statusTypeId;
        LinearLayout lyt_yearOfplanting;
        LinearLayout lyt_palmArea;
        LinearLayout lyt_farmername;
        LinearLayout lyt_formerMobileNumber;
        LinearLayout lyt_formerContactNumber;
        LinearLayout lyt_plotvillage;
        LinearLayout lyt_landmark;
        //        LinearLayout lyt_lattitude;
//        LinearLayout lyt_longitude;
        LinearLayout lyt_serviceTypes;


        Button closeRequestBtn;
        ImageView viewOnMapBtn;
        Button viewDetailsBtn;
        LinearLayout linearLayout;

        LinearLayout lytRequestCode;
        LinearLayout lytStartDate;
        LinearLayout lytJobDoneDate;
        LinearLayout lytCreatedDate;
        LinearLayout lytUpdatedDate;
        LinearLayout lytAssignedDate;
        LinearLayout lytNetWeight,lyt_expected_bunches;
        LinearLayout lytTreesCount,lytintercropTreesCount;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.labour_request);
            id = itemView.findViewById(R.id.id);
            requestCode = itemView.findViewById(R.id.requestCode);
            startDate = itemView.findViewById(R.id.startDate);
            durationId = itemView.findViewById(R.id.durationId);
            leaderId = itemView.findViewById(R.id.leaderId);
            pin = itemView.findViewById(R.id.pin);
            exp_bunches=itemView.findViewById(R.id.exp_bunches);
            jobDoneDate = itemView.findViewById(R.id.jobDoneDate);
            createdByUserId = itemView.findViewById(R.id.createdByUserId);
            createdDate = itemView.findViewById(R.id.createdDate);
            updatedByUserId = itemView.findViewById(R.id.updatedUserId);
            updatedDate = itemView.findViewById(R.id.updatedDate);
            assignedDate = itemView.findViewById(R.id.assignedDate);
            netWeight = itemView.findViewById(R.id.netWeight);
            amount = itemView.findViewById(R.id.amount);
            harvestingAmount = itemView.findViewById(R.id.harvestingAmount);
            pruningAmount = itemView.findViewById(R.id.pruningAmount);
            unknown1Amount = itemView.findViewById(R.id.unknown1Amount);
            unknown2Amount = itemView.findViewById(R.id.unknown2Amount);
            treesCount = itemView.findViewById(R.id.tressCount);
            serverUpdatedStatus = itemView.findViewById(R.id.serverUpdatedStatus);
            closeRequestBtn = itemView.findViewById(R.id.close_request_btn);
            viewOnMapBtn = itemView.findViewById(R.id.viewOnMap_btn);
            viewDetailsBtn = itemView.findViewById(R.id.viewDetails_btn);


            statusTypeId = itemView.findViewById(R.id.statusTypeId);
            yearOfplanting = itemView.findViewById(R.id.yearOfPlanting);
            palmArea = itemView.findViewById(R.id.palmArea);
            farmername = itemView.findViewById(R.id.farmerName);
            formerMobileNumber = itemView.findViewById(R.id.farmerMobileNumber);
            formerContactNumber = itemView.findViewById(R.id.farmerContactNumber);
            plotVillage = itemView.findViewById(R.id.plotVillage);
            landmark = itemView.findViewById(R.id.landmark);
//            lattitude = itemView.findViewById(R.id.latitude);
//            longitude = itemView.findViewById(R.id.longitude);
            serviceTypes = itemView.findViewById(R.id.serviceTypes);

            lyt_statusTypeId = itemView.findViewById(R.id.lyt_statusTypeId);
            lyt_yearOfplanting = itemView.findViewById(R.id.lyt_yearOfPlanting);
            lyt_palmArea = itemView.findViewById(R.id.lyt_palmArea);
            lyt_farmername = itemView.findViewById(R.id.lyt_farmerName);
            lyt_formerMobileNumber = itemView.findViewById(R.id.lyt_farmerMobileNumber);
            lyt_formerContactNumber = itemView.findViewById(R.id.lyt_farmerContactNumber);
            lyt_plotvillage = itemView.findViewById(R.id.lyt_plotVillage);
            lyt_landmark = itemView.findViewById(R.id.lyt_landmark);
//            lyt_lattitude = itemView.findViewById(R.id.lyt_latitude);
//            lyt_longitude = itemView.findViewById(R.id.lyt_longitude);
            lyt_serviceTypes = itemView.findViewById(R.id.lyt_serviceTypes);
            intercrop_treecount= itemView.findViewById(R.id.intertressCount);
            lytRequestCode = itemView.findViewById(R.id.lyt_requestCode);
            lytStartDate = itemView.findViewById(R.id.lyt_startDate);
            lytJobDoneDate = itemView.findViewById(R.id.lyt_jobDoneDate);
            lytCreatedDate = itemView.findViewById(R.id.lyt_createdDate);
            lytUpdatedDate = itemView.findViewById(R.id.lyt_updatedDate);
            lytAssignedDate = itemView.findViewById(R.id.lyt_assignedDate);
            lytNetWeight = itemView.findViewById(R.id.lyt_netWeight);
            lyt_expected_bunches = itemView.findViewById(R.id.lyt_expected_bunches);
            lytTreesCount = itemView.findViewById(R.id.lyt_treesCount);
            lytintercropTreesCount = itemView.findViewById(R.id.inter_lyt_treesCount);
            cardview = itemView.findViewById(R.id.card_view);

            ctx = itemView.getContext();
        }
    }
}

