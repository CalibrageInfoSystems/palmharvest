package in.calibrage.palmharvest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import in.calibrage.palmharvest.common.Config;

import static in.calibrage.palmharvest.common.MLog.onPrintDebugLog;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TAG = DatabaseHelper.class.getSimpleName();
    private static DatabaseHelper databaseHelper;

    // All Static variables
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = Config.DATABASE_NAME;

    // Constructor
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        onPrintDebugLog(TAG,"constructor",DATABASE_NAME);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (databaseHelper == null) {
            synchronized (DatabaseHelper.class) {
                if (databaseHelper == null)
                    databaseHelper = new DatabaseHelper(context);
            }
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tables SQL execution


        String  CREATE_USERINFO_TABLE=" CREATE TABLE " +Config.TABLE_USERINFO + "("
                +Config.COLUMN_USERINFO_ID + " INTEGER ,"
                +Config.COLUMN_USERINFO_USERID+ " TEXT, "
                +Config.COLUMN_USERINFO_FIRSTNAME+ " TEXT, "
                +Config.COLUMN_USERINFO_LASTNAME+ " TEXT, "
                +Config.COLUMN_USERINFO_MIDDLENAME+" TEXT, "
                +Config.COLUMN_USERINFO_CONTACTNUMBER+" TEXT, "
                +Config.COLUMN_USERINFO_MOBILENUMBER+" TEXT, "
                +Config.COLUMN_USERINFO_USERNAME+" TEXT, "
                +Config.COLUMN_USERINFO_PASSWORD+" TEXT, "
                +Config.COLUMN_USERINFO_ROLEID+" INTEGER, "
                +Config.COLUMN_USERINFO_EMAIL+" TEXT, "
                +Config.COLUMN_USERINFO_MANAGERID+" INTEGER, "
                +Config.COLUMN_USERINFO_USERCODE+" TEXT, "
                +Config.COLUMN_USERINFO_EMPLOYEECODE+" TEXT, "
                +Config.COLUMN_USERINFO_ISACTIVE+" BOOL, "
                +Config.COLUMN_USERINFO_CREATEDBYUSERID+" INTEGER, "
                +Config.COLUMN_USERINFO_CREATEDDATE+" DATETIME, "
                +Config.COLUMN_USERINFO_UPDATEDBYUSERID+" INTEGER, "
                +Config.COLUMN_USERINFO_UPDATEDDATE+" DATETIME " + ")";


        String  CREATE_REQUEST_TABLE=" CREATE TABLE " +Config.TABLE_REQUEST + "("
                +Config.COLUMN_REQUEST_ID + " INTEGER ,"
                +Config.COLUMN_REQUESTCODE+ " TEXT, "
                +Config.COLUMN_FORMERCODE+ " TEXT, "
                +Config.COLUMN_PLATCODE+ " TEXT, "
                +Config.COLUMN_REQ_CREATEDDATE+" DATETIME, "
                +Config.COLUMN_STATUS_TYPE_ID+" INTEGER, "
                +Config.COLUMN_IS_FORMER_REQUEST+" BOOL, "
                +Config.COLUMN_CREATED_BY_USERID+" INTEGER, "
                +Config.COLUMN_CREATED_DATE+" DATETIME, "
                +Config.COLUMN_REQ_YEAR_OF_PLANTING+" DATETIME, "
                +Config.COLUMN_UPDATED_BY_USERID+" INTEGER, "
                +Config.COLUMN_UPDATED_DATE+" DATETIME, "
                +Config.COLUMN_TotalCoast+" FLOAT, "
                +Config.COLUMN_COMMENTS+" TEXT, "
                +Config.COLUMN_CROPMAINTANCE_DATE+" DATETIME, "
                +Config.COLUMN_REQUEST_TYPE_ID+" INTEGER, "
                +Config.COLUMN_ISSUE_TYPE_ID+" INTEGER, "
                +Config.COLUMN_FARMERNAME+" TEXT, "
                +Config.COLUMN_PLOT_VILLAGE+" TEXT, "
                +Config.COLUMN_PALM_AREA+" FLOAT, "
                +Config.COLUMN_REQ_SERVERUPDATED_STATUS+" INTEGER, "
                +Config.COLUMN_TOTAL_COST_WITHOUT_SERVICE_CHARGE +" FLOAT, "
                +Config.COLUMN_SERCVICE_CHARGE_AMOUNT+" FLOAT, "
                +Config.COLUMN_PARENT_REQUEST_CODE+" TEXT, "
                +Config.COLUMN_RECOVERY_FARMER_CODE+" TEXT, "
                +Config.COLUMN_CLUSTER_ID+" INTEGER " + ")";

        String CREATE_LABOUR_REQUEST_TABLE="CREATE TABLE "+Config.TABLE_LABOUR_REQUEST+"("
                +Config.COLUMN_LABOUR_ID+" INTEGER ,"
                +Config.COLUMN_LABOUR_REQUEST_CODE+" TEXT, "
                +Config.COLUMN_LABOUR_STARTDATE+" DATETIME, "
                +Config.COLUMN_LABOUR_DURATION+" INTEGER ,"
                +Config.COLUMN_LABOUR_LEADERID+" INTEGER ,"
                +Config.COLUMN_LABOUR_PIN+" INTEGER ,"
                +Config.COLUMN_LABOUR_JOBDONE_DATE+" DATETIME, "
                +Config.COLUMN_LABOUR_CREATED_BY_USER_ID+" INTEGER ,"
                +Config.COLUMN_LABOUR_CREATED_DATE+" DATETIME, "
                +Config.COLUMN_LABOUR_UPDATED_BY_USER_ID+" INTEGER ,"
                +Config.COLUMN_LABOUR_UPDATED_DATE+" DATETIME, "
                +Config.COLUMN_LABOUR_ASSIGNED_DATE+" DATETIME, "
                +Config.COLUMN_LABOUR_NET_WEIGHT+" FLOAT, "
                +Config.COLUMN_LABOUR_AMOUNT+" FLOAT, "
                +Config.COLUMN_LABOUR_HARVESTING_AMOUNT+" FLOAT, "
                +Config.COLUMN_LABOUR_PRUNING_AMOUNT+" FLOAT, "
                +Config.COLUMN_LABOUR_PRUNING_WITH_INTERCROP_AMOUNT+" FLOAT, "
                +Config.COLUMN_LABOUR_HARVESTING_WITH_INTERCROP_AMOUNT+" FLOAT, "
                +Config.COLUMN_LABOUR_TRESS_COUNT+" INTEGER,"
                +Config.COLUMN_LABOUR_OderId+" TEXT, "
                +Config.COLUMN_LABOUR_treesCountWithIntercrop+" INTEGER, "
                +Config.COLUMN_LABOUR_netWeightIntercrop+" INTEGER, "
                +Config.COLUMN_LABOUR_SERVER_UPDATED_STATUS+" INTEGER, "
                +Config.COLUMN_LABOUR_EXPECTED_BUNCHES+" INTEGER, "
                +Config.COLUMN_LABOUR_EXPECTED_NET_WEIGHT+" FLOAT, "
                +Config.COLUMN_LABOUR_OWN_POLE+" BOOL " +")";

        String CREATE_FORWARD_SYNC_TABLE="CREATE TABLE "+Config.TABLE_SYNC_FORWARD+"("
                +Config.COLUMN_SYNC_F_ID+" INTEGER ,"
                +Config.COLUMN_SYNC_F_Comments+" TEXT, "
                +Config.COLUMN_SYNC_F_Sync_DATE+" DATETIME "
                +")";

        String CREATE_FORWARD_REVERSE_TABLE="CREATE TABLE "+Config.TABLE_SYNC_REVERSE+"("
                +Config.COLUMN_SYNC_R_ID+" INTEGER ,"
                +Config.COLUMN_SYNC_R_Comments+" TEXT, "
                +Config.COLUMN_SYNC_R_Sync_DATE+" DATETIME "
                +")";

        String CREATE_LABOUR_SERVICE_TABLE="CREATE TABLE "+Config.TABLE_LABOUR_SERVICES+"("
                +Config.COLUMN_SERVICE_REQUEST_CODE+" TEXT ,"
                +Config.COLUMN_SERVICE_TYPE_CDID+" INTEGER "
                +")";

        String CREATE_FORMER_DETAILS="CREATE TABLE "+Config.TABLE_FORMER_TABLE+"("
                +Config.COLUMN_FORMER_DETAILS_REQUEST_CODE+" TEXT ,"
                +Config.COLUMN_FORMER_DETAILS_FORMER_CODE+" TEXT ,"
                +Config.COLUMN_FORMER_DETAILS_PLATCODE+" TEXT ,"

                +Config.COLUMN_FORMER_DETAILS_LATITUDE+" FLOAT ,"
                +Config.COLUMN_FORMER_DETAILS_LONGITUDE+" FLOAT ,"

                +Config.COLUMN_FORMER_DETAILS_MOBILENUMBER+" TEXT ,"
                +Config.COLUMN_FORMER_DETAILS_CONTACT_NUMBER+" TEXT ,"
                +Config.COLUMN_FORMER_DETAILS_LANDMARK+" TEXT "
                +")";



        String  CREATE_LABOUR_LEADER_DETAILS="CREATE TABLE "+Config.TABLE_LABOUR_LEADER_XRef+"("
                +Config.COLUMN_LABOUR_LEADER_XRef_ID+" INTEGER ,"
                +Config.COLUMN_LABOUR_LEADER_XRef_REQUESTCODE+" TEXT ,"
                +Config.COLUMN_LABOUR_LEADER_XRef_LABOURID+" INTEGER ,"
                +Config.COLUMN_LABOUR_LEADER_XRef_TREESCOUNT+" INTEGER ,"
                +Config.COLUMN_LABOUR_LEADER_XRef_ISACTIVE+" BOOL ,"
                +Config.COLUMN_LABOUR_LEADER_XRef_CREATEDBYUSERID+" INTEGER ,"
                +Config.COLUMN_LABOUR_LEADER_XRef__CREATEDDATE+" DATETIME ,"
                +Config.COLUMN_LABOUR_LEADER_XRef_UPDATEDBYUSERID+" INTEGER ,"
                +Config.COLUMN_LABOUR_LEADER_XRef_UPDATEDDATE+" DATETIME ,"
                +Config.COLUMN_LABOUR_LEADER_XRef_SERVERUPDATEDSTATUS+" BOOL ,"
                +Config.COLUMN_LABOUR_LEADER_XRef_NET_WEIGHT+" FLOAT ,"
                +Config.COLUMN_LABOUR_LEADER_XRef_LABOURCOUNT+" INTEGER "
                +")";



        db.execSQL(CREATE_LABOUR_REQUEST_TABLE);
        db.execSQL(CREATE_REQUEST_TABLE);
        db.execSQL(CREATE_FORWARD_SYNC_TABLE);
        db.execSQL(CREATE_FORWARD_REVERSE_TABLE);
        db.execSQL(CREATE_LABOUR_SERVICE_TABLE);
        db.execSQL(CREATE_FORMER_DETAILS);
        db.execSQL(CREATE_USERINFO_TABLE);
        db.execSQL(CREATE_LABOUR_LEADER_DETAILS);
      //  db.execSQL(CREATE_LABOURINFO);

        onPrintDebugLog(TAG,"DB created!","");;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_REQUEST);
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_LABOUR_REQUEST);

        // Create tables again
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        //enable foreign key constraints like ON UPDATE CASCADE, ON DELETE CASCADE
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

}
