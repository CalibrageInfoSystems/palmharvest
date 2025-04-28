package in.calibrage.palmharvest.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.calibrage.palmharvest.Activities.HomeActivity;
import in.calibrage.palmharvest.Model.SyncLabourResponse;
import in.calibrage.palmharvest.R;
import in.calibrage.palmharvest.database.DatabaseQueryClass;
import in.calibrage.palmharvest.sync.SyncRequestHeaderResponse;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>{

    private LayoutInflater layoutInflater;

    DatabaseQueryClass databaseQueryClass;
    HomeActivity homeActivity;
    private List<SyncLabourResponse.ListResult> items;
    private List<SyncLabourResponse.ListResult> allitems;
    private List<SyncRequestHeaderResponse.ListResult> nextItems;
    private Context ctx;

    public HomeRecyclerViewAdapter(Context ctx,List<SyncLabourResponse.ListResult> items, List<SyncRequestHeaderResponse.ListResult> nextItems) {
        this.layoutInflater = LayoutInflater.from(ctx);
        this.items = items;
        this.allitems = new ArrayList<>(items);
        this.ctx = ctx;
        this.nextItems = nextItems;
    }


    @NonNull
    @Override
    public HomeRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.home_recycler_layout,parent, false);

        return new HomeRecyclerViewAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.id.setText(":  "+items.get(position).getId());
        holder.requestCode.setText(":  "+items.get(position).getRequestCode());
        holder.startDate.setText(":  "+items.get(position).getStartDate()  );
        holder.durationId.setText(":  "+items.get(position).getDurationId());
        holder.leaderId.setText(":  "+items.get(position).getLeaderId());
        holder.pin.setText(":  "+items.get(position).getPin());
        holder.jobDoneDate.setText(":  "+items.get(position).getJobDoneDate());
        holder.createdByUserId.setText(""+items.get(position).getCreatedByUserId());
        holder.createdDate.setText(":  "+items.get(position).getCreatedDate());
        holder.updatedByUserId.setText(""+items.get(position).getUpdatedByUserId());
        holder.updatedDate.setText(":  "+items.get(position).getUpdatedDate());
        holder.assignedDate.setText(":  "+items.get(position).getAssignedDate());
        holder.netWeight.setText(":  "+items.get(position).getNetWeight());
        holder.amount.setText(":  "+items.get(position).getAmount());
        holder.harvestingAmount.setText(":  "+items.get(position).getHarvestingAmount());
        holder.pruningAmount.setText(":  "+items.get(position).getPruningAmount());
        holder.unknown1Amount.setText(":  "+items.get(position).getPruningWithIntercropAmount());
        holder.unknown2Amount.setText(":  "+items.get(position).getHarvestingWithIntercropAmount());
        holder.treesCount.setText(":  "+items.get(position).getTreesCount());
        holder.serverUpdatedStatus.setText(":  "+items.get(position).getServerUpdatedStatus());


    }

    @Override
    public int getItemCount() {
        return items.size();
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
        TextView treesCount;
        TextView serverUpdatedStatus;
        Button closeRequestBtn;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            linearLayout = itemView.findViewById(R.id.labour_request);
            id = itemView.findViewById(R.id.id);
            requestCode = itemView.findViewById(R.id.requestCode);
            startDate = itemView.findViewById(R.id.startDate);
            durationId = itemView.findViewById(R.id.durationId);
            leaderId = itemView.findViewById(R.id.leaderId);
            pin = itemView.findViewById(R.id.pin);
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

            ctx = itemView.getContext();

        }
    }
}
