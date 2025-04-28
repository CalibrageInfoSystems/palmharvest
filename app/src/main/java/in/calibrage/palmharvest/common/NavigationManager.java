package in.calibrage.palmharvest.common;

import android.content.Context;
import android.content.Intent;

import in.calibrage.palmharvest.Activities.HomeActivity;

public class NavigationManager {

    public static void onNavigateHome(Context ctx)
    {
        Intent intent =new Intent(ctx, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ctx.startActivity(intent);

        /*
        * after Calling this You have to Finish your Activity
        * */
    }
}
