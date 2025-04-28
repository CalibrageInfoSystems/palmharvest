package in.calibrage.palmharvest.sync;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import in.calibrage.palmharvest.Model.GetCountRequest;
import in.calibrage.palmharvest.Model.GetCountResponse;
import in.calibrage.palmharvest.localData.SharedPrefsData;
import in.calibrage.palmharvest.service.ApiService;
import in.calibrage.palmharvest.service.ServiceFactory;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class SyncManager {
    public  static  final String TAG= SyncManager.class.getSimpleName();
    private Subscription mSubscription;
    GetCountResponse responseModel =new GetCountResponse();

    public Context ctx;
    public Activity activity;

    public SyncManager(Context ctx, Activity activity) {
        this.ctx = ctx;
        this.activity = activity;
    }
    private JsonObject getCountObject() {
        GetCountRequest requestModel = new GetCountRequest();
        requestModel.setDate(SharedPrefsData.onGetSyncDate(ctx));
        requestModel.setUserId(SharedPrefsData.getCatagoriess(ctx).getResult().getId());
        return new Gson().toJsonTree(requestModel).getAsJsonObject();
    }
    public GetCountResponse onCheckSyncStatus()
    {

        JsonObject object = getCountObject();
        Log.d(TAG, "******** analysis ***** getCountObject = >"+object);
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
                    public void onNext(GetCountResponse getCountResponse) {

                        responseModel = getCountResponse;
                    }
                });
          return  responseModel;
    }

}
