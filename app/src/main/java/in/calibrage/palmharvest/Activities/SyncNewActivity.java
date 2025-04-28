package in.calibrage.palmharvest.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.google.gson.Gson;

import in.calibrage.palmharvest.R;
import in.calibrage.palmharvest.common.CommonUtil;
import in.calibrage.palmharvest.sync.SyncManager;

public class SyncNewActivity extends AppCompatActivity {
    public  static  final String TAG= SyncNewActivity.class.getSimpleName();
    Gson gson = new Gson();
    private SyncManager syncManager;
    private Context ctx;

    // variables
    private int Requestsize = 0,LabourRequestsize = 0,ServiceRequestsize = 0,FormerRequestsize = 0,LabourCountSize = 0, Labour_Request_index = 0,
                Request_Header_index = 0,Labour_Services_index = 0,Farmer_Details_index = 0,Labour_Count_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_new);
        init();
        if(CommonUtil.isNetworkAvailable(ctx))
        {
            bind();
        }

    }

    private void init() {
        ctx = this;
        syncManager=new SyncManager(ctx,SyncNewActivity.this);
    }
    private  void makeCountZero()
    {
        Requestsize = 0;LabourRequestsize = 0;ServiceRequestsize = 0;
        FormerRequestsize = 0;LabourCountSize = 0;Labour_Request_index = 0;
        Request_Header_index = 0;Labour_Services_index = 0;
        Farmer_Details_index = 0;Labour_Count_index = 0;
    }
    private void bind() {

    }



}
