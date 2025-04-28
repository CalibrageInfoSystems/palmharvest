package in.calibrage.palmharvest.sync;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SyncUtil {

  public static void getSyncTimeString()
    {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String inputString1 = "23 01 1997";
        String inputString2 = "27 04 1997";

        try {
            Date date1 = myFormat.parse(inputString1);
            Date date2 = myFormat.parse(inputString2);
            long diff = date2.getTime() - date1.getTime();
            System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
