package in.calibrage.palmharvest.common;

import android.util.Log;

public class MLog {
    public static void onPrintDebugLog(String TAG,String methodName,String message)
    {
        Log.d(TAG,"-----start >>-------->> "+methodName +"<<-------------------");
        Log.d(TAG, message);
        Log.d(TAG,"------ End >>-------->> "+methodName +"<<-------------------");
    }
}
