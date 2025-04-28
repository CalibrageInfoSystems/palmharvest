package in.calibrage.a3fharvesting.database;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import in.calibrage.a3fharvesting.Model.GetCostConfigResponse;
import in.calibrage.a3fharvesting.Model.GetMasters;
import in.calibrage.a3fharvesting.Model.LabourDbModel;
import in.calibrage.a3fharvesting.Model.RequestCompleteModel;
import in.calibrage.a3fharvesting.Model.SyncFarmerDetailsResponse;
import in.calibrage.a3fharvesting.Model.SyncGetMastersResponse;
import in.calibrage.a3fharvesting.Model.SyncLabourLeaderDetailsResponse;
import in.calibrage.a3fharvesting.Model.SyncLabourResponse;
import in.calibrage.a3fharvesting.Model.SyncLabourServicesResponse;
import in.calibrage.a3fharvesting.common.CommonUtil;
import in.calibrage.a3fharvesting.common.Config;
import in.calibrage.a3fharvesting.common.DateTimeUtil;
import in.calibrage.a3fharvesting.sync.SyncRequestHeaderResponse;

public class DatabaseQueryClass {
    private Context context;

    public DatabaseQueryClass(Context context) {
        this.context = context;
//        Logger.addLogAdapter(new AndroidLogAdapter());
    }


    public long insertRequestHeader(SyncRequestHeaderResponse.ListResult requestHeader) {
        long rowId = -1;

        //Todo Check if value exist or not
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_REQUEST_ID, requestHeader.getId());//     +Config.COLUMN_REQUEST_ID + " INTEGER ,"
        contentValues.put(Config.COLUMN_REQUESTCODE, requestHeader.getRequestCode());//        + Config.COLUMN_REQUESTCODE + " TEXT, "
        contentValues.put(Config.COLUMN_FORMERCODE, requestHeader.getFarmerCode());//       + Config.COLUMN_FORMERCODE + " TEXT, "
        contentValues.put(Config.COLUMN_PLATCODE, requestHeader.getPlotCode());//      + Config.COLUMN_PLATCODE + " TEXT, "
        contentValues.put(Config.COLUMN_REQ_CREATEDDATE, requestHeader.getCreatedDate());//+ Config.COLUMN_REQ_CREATEDDATE + " DATETIME, "
        contentValues.put(Config.COLUMN_STATUS_TYPE_ID, requestHeader.getStatusTypeId());//  + Config.COLUMN_STATUS_TYPE_ID + " INTEGER, "
        contentValues.put(Config.COLUMN_IS_FORMER_REQUEST, requestHeader.getIsFarmerRequest());//  + Config.COLUMN_IS_FORMER_REQUEST + " BOOL, "
        contentValues.put(Config.COLUMN_CREATED_BY_USERID, 0);//+ Config.COLUMN_CREATED_BY_USERID + " INTEGER, "
        contentValues.put(Config.COLUMN_CREATED_DATE, requestHeader.getCreatedDate());    //+ Config.COLUMN_CREATED_DATE + " DATETIME, "
        contentValues.put(Config.COLUMN_UPDATED_BY_USERID, requestHeader.getUpdatedByUserId()); //  + Config.COLUMN_UPDATED_BY_USERID + " INTEGER, "
        contentValues.put(Config.COLUMN_UPDATED_DATE, requestHeader.getUpdatedDate());//  + Config.COLUMN_UPDATED_DATE + " DATETIME, "
        contentValues.put(Config.COLUMN_TotalCoast, requestHeader.getTotalCost());// + Config.COLUMN_TotalCoast + " FLOAT, "
        contentValues.put(Config.COLUMN_COMMENTS, requestHeader.getComments());// + Config.COLUMN_COMMENTS + " TEXT, "
        contentValues.put(Config.COLUMN_CROPMAINTANCE_DATE, "");// + Config.COLUMN_CROPMAINTANCE_DATE + " DATETIME, "
        contentValues.put(Config.COLUMN_REQUEST_TYPE_ID, requestHeader.getRequestTypeId());// + Config.COLUMN_REQUEST_TYPE_ID + " INTEGER, "
        contentValues.put(Config.COLUMN_ISSUE_TYPE_ID, requestHeader.getIssueTypeId());// + Config.COLUMN_ISSUE_TYPE_ID + " INTEGER, "
        contentValues.put(Config.COLUMN_FARMERNAME, requestHeader.getFarmerName());// + Config.COLUMN_FARMERNAME + " TEXT, "
        contentValues.put(Config.COLUMN_PLOT_VILLAGE, requestHeader.getPlotVillage());// + Config.COLUMN_PLOT_VILLAGE + " TEXT, "
        contentValues.put(Config.COLUMN_PALM_AREA, requestHeader.getPalmArea());// + Config.COLUMN_PALM_AREA + "FLOT, "
        contentValues.put(Config.COLUMN_REQ_SERVERUPDATED_STATUS, requestHeader.getServerUpdatedStatus());
        Log.d("****DB******", "COLUMN_REQ_status_ID :"+requestHeader.getStatusTypeId());
        contentValues.put(Config.COLUMN_REQ_YEAR_OF_PLANTING, requestHeader.getYearofPlanting());
        contentValues.put(Config.COLUMN_TOTAL_COST_WITHOUT_SERVICE_CHARGE, requestHeader.getTotalCostWithoutServiceCharge());
        contentValues.put(Config.COLUMN_SERCVICE_CHARGE_AMOUNT, requestHeader.getServiceChargeAmount());
        contentValues.put(Config.COLUMN_PARENT_REQUEST_CODE, requestHeader.getParentRequestCode());
        contentValues.put(Config.COLUMN_RECOVERY_FARMER_CODE, requestHeader.getRecoveryFarmerCode());
        contentValues.put(Config.COLUMN_CLUSTER_ID, requestHeader.getClusterId());
        try {
            rowId = sqLiteDatabase.insertOrThrow(Config.TABLE_REQUEST, null, contentValues);
        } catch (SQLiteException e) {
//            Logger.d(e);
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowId;
    }

    public long insertLabourRequest(SyncLabourResponse.ListResult labourResponce) {
        long rowId = -1;

        //Todo Check if value exist or not
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_LABOUR_ID, labourResponce.getId());
        contentValues.put(Config.COLUMN_LABOUR_REQUEST_CODE, labourResponce.getRequestCode());  // +Config.COLUMN_LABOUR_REQUEST_CODE+" TEXT, "
        contentValues.put(Config.COLUMN_LABOUR_STARTDATE, labourResponce.getStartDate());  //   +Config.COLUMN_LABOUR_STARTDATE+" DATETIME, "
        contentValues.put(Config.COLUMN_LABOUR_DURATION, labourResponce.getDurationId());  //   +Config.COLUMN_LABOUR_DURATION+" INTEGER ,"//TODO we need to check
        contentValues.put(Config.COLUMN_LABOUR_LEADERID, labourResponce.getLeaderId());    //    +Config.COLUMN_LABOUR_LEADERID+" INTEGER ,"
        contentValues.put(Config.COLUMN_LABOUR_PIN, labourResponce.getPin());   //     +Config.COLUMN_LABOUR_PIN+" INTEGER ,"
        contentValues.put(Config.COLUMN_LABOUR_JOBDONE_DATE, labourResponce.getJobDoneDate());   //     +Config.COLUMN_LABOUR_JOBDONE_DATE+" DATETIME, "
        contentValues.put(Config.COLUMN_LABOUR_CREATED_BY_USER_ID, labourResponce.getCreatedByUserId());      //  +Config.COLUMN_LABOUR_CREATED_BY_USER_ID+" INTEGER ,"
        contentValues.put(Config.COLUMN_LABOUR_CREATED_DATE, labourResponce.getCreatedDate());     //   +Config.COLUMN_LABOUR_CREATED_DATE+" DATETIME, "
        contentValues.put(Config.COLUMN_LABOUR_UPDATED_BY_USER_ID, labourResponce.getUpdatedByUserId());      // +Config.COLUMN_LABOUR_UPDATED_BY_USER_ID+" INTEGER ,"
        contentValues.put(Config.COLUMN_LABOUR_UPDATED_DATE, labourResponce.getUpdatedDate());      // +Config.COLUMN_LABOUR_UPDATED_DATE+" DATETIME, "
        contentValues.put(Config.COLUMN_LABOUR_ASSIGNED_DATE, labourResponce.getAssignedDate());    //   +Config.COLUMN_LABOUR_ASSIGNED_DATE+" DATETIME, "
        contentValues.put(Config.COLUMN_LABOUR_NET_WEIGHT, labourResponce.getNetWeight());       // +Config.COLUMN_LABOUR_NET_WEIGHT+"FLOAT, "
        contentValues.put(Config.COLUMN_LABOUR_AMOUNT, labourResponce.getAmount());   //    +Config.COLUMN_LABOUR_AMOUNT+"FLOAT, "
        contentValues.put(Config.COLUMN_LABOUR_HARVESTING_AMOUNT, labourResponce.getHarvestingAmount());     //  +Config.COLUMN_LABOUR_HARVESTING_AMOUNT+"FLOAT, "
        contentValues.put(Config.COLUMN_LABOUR_PRUNING_AMOUNT, labourResponce.getPruningAmount());    //    +Config.COLUMN_LABOUR_PRUNING_AMOUNT+"FLOAT, "
        contentValues.put(Config.COLUMN_LABOUR_PRUNING_WITH_INTERCROP_AMOUNT, labourResponce.getPruningWithIntercropAmount());      //  +Config.COLUMN_LABOUR_UNKNOWN1_AMOUNT+"FLOAT, "
        contentValues.put(Config.COLUMN_LABOUR_HARVESTING_WITH_INTERCROP_AMOUNT, labourResponce.getHarvestingWithIntercropAmount());      //  +Config.COLUMN_LABOUR_UNKNOWN2_AMOUNT+"FLOAT, "
        contentValues.put(Config.COLUMN_LABOUR_TRESS_COUNT, labourResponce.getTreesCount());   //    +Config.COLUMN_LABOUR_TRESS_COUNT+" INTEGER ,"
        contentValues.put(Config.COLUMN_LABOUR_SERVER_UPDATED_STATUS, labourResponce.getServerUpdatedStatus());     //  +Config.COLUMN_LABOUR_SERVER_UPDATED_STATUS+" BOOL "+")";
        contentValues.put(Config.COLUMN_LABOUR_OderId, labourResponce.getOrderId());     //  +Config.COLUMN_LABOUR_SERVER_UPDATED_STATUS+" BOOL "+")";
        contentValues.put(Config.COLUMN_LABOUR_treesCountWithIntercrop, labourResponce.getTreesCountWithIntercrop());     //  +Config.COLUMN_LABOUR_SERVER_UPDATED_STATUS+" BOOL "+")";
        contentValues.put(Config.COLUMN_LABOUR_netWeightIntercrop, labourResponce.getNetWeightIntercrop());     //  +Config.COLUMN_LABOUR_SERVER_UPDATED_STATUS+" BOOL "+")";
        contentValues.put(Config.COLUMN_LABOUR_EXPECTED_BUNCHES, labourResponce.getExpectedBunches());     //  +Config.COLUMN_LABOUR_SERVER_UPDATED_STATUS+" BOOL "+")";
        contentValues.put(Config.COLUMN_LABOUR_EXPECTED_NET_WEIGHT, labourResponce.getExpectedNetWeight());     //  +Config.COLUMN_LABOUR_SERVER_UPDATED_STATUS+" BOOL "+")";

        if(labourResponce.getOwnPole() == 0){
            contentValues.put(Config.COLUMN_LABOUR_OWN_POLE, 0);
        }else {
            contentValues.put(Config.COLUMN_LABOUR_OWN_POLE, 1);
        }

           //  +Config.COLUMN_LABOUR_SERVER_UPDATED_STATUS+" BOOL "+")";
        try {
            rowId = sqLiteDatabase.insertOrThrow(Config.TABLE_LABOUR_REQUEST, null, contentValues);
        } catch (SQLiteException e) {
//            Logger.d(e);
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowId;
    }


    public long insertLabourServices(SyncLabourServicesResponse.ListResult servicesResponse) {
        long rowId = -1;

        //Todo Check if value exist or not
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_SERVICE_REQUEST_CODE, servicesResponse.getRequestCode());
        contentValues.put(Config.COLUMN_SERVICE_TYPE_CDID, servicesResponse.getServiceTypeId());
        Log.d("tag.....",servicesResponse.getServiceTypeId()+"");

        try {
            rowId = sqLiteDatabase.insertOrThrow(Config.TABLE_LABOUR_SERVICES, null, contentValues);
        } catch (SQLiteException e) {
//            Logger.d(e);
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowId;
    }

    public long insertFarmerDetails(SyncFarmerDetailsResponse.ListResult farmerDetailsResponse) {
        long rowId = -1;

        //Todo Check if value exist or not
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_FORMER_DETAILS_REQUEST_CODE, farmerDetailsResponse.getRequestCode());
        contentValues.put(Config.COLUMN_FORMER_DETAILS_FORMER_CODE, farmerDetailsResponse.getFarmerCode());
        contentValues.put(Config.COLUMN_FORMER_DETAILS_PLATCODE, farmerDetailsResponse.getPlotCode());
        contentValues.put(Config.COLUMN_FORMER_DETAILS_LATITUDE, farmerDetailsResponse.getLatitude());
        contentValues.put(Config.COLUMN_FORMER_DETAILS_LONGITUDE, farmerDetailsResponse.getLongitude());
        contentValues.put(Config.COLUMN_FORMER_DETAILS_MOBILENUMBER, (String) farmerDetailsResponse.getMobileNumber());
        contentValues.put(Config.COLUMN_FORMER_DETAILS_CONTACT_NUMBER, farmerDetailsResponse.getContactNumber());
        contentValues.put(Config.COLUMN_FORMER_DETAILS_LANDMARK, farmerDetailsResponse.getLandmark());

        try {
            rowId = sqLiteDatabase.insertOrThrow(Config.TABLE_FORMER_TABLE, null, contentValues);
        } catch (SQLiteException e) {
//            Logger.d(e);
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowId;
    }


    public long insertLabourLeaderDetails(SyncLabourLeaderDetailsResponse.ListResult labourLeaderDetailsResponse) {
        long rowId = -1;

        //Todo Check if value exist or not
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_LABOUR_LEADER_XRef_ID, labourLeaderDetailsResponse.getId() );
        contentValues.put(Config.COLUMN_LABOUR_LEADER_XRef_REQUESTCODE, labourLeaderDetailsResponse.getRequestCode());
        contentValues.put(Config.COLUMN_LABOUR_LEADER_XRef_LABOURID, labourLeaderDetailsResponse.getLabourId());

       contentValues.put(Config.COLUMN_LABOUR_LEADER_XRef_TREESCOUNT, labourLeaderDetailsResponse.getTreesCount());
        contentValues.put(Config.COLUMN_LABOUR_LEADER_XRef_ISACTIVE, labourLeaderDetailsResponse.getIsActive());
        contentValues.put(Config.COLUMN_LABOUR_LEADER_XRef_CREATEDBYUSERID, labourLeaderDetailsResponse.getCreatedByUserId());
        contentValues.put(Config.COLUMN_LABOUR_LEADER_XRef__CREATEDDATE, labourLeaderDetailsResponse.getCreatedDate());
        contentValues.put(Config.COLUMN_LABOUR_LEADER_XRef_UPDATEDBYUSERID, labourLeaderDetailsResponse.getUpdatedByUserId());
        contentValues.put(Config.COLUMN_LABOUR_LEADER_XRef_UPDATEDDATE, labourLeaderDetailsResponse.getUpdatedDate());


       contentValues.put(Config.COLUMN_LABOUR_LEADER_XRef_SERVERUPDATEDSTATUS, labourLeaderDetailsResponse.getServerUpdatedStatus());
        contentValues.put(Config.COLUMN_LABOUR_LEADER_XRef_NET_WEIGHT, labourLeaderDetailsResponse.getNetWeight());
        contentValues.put(Config.COLUMN_LABOUR_LEADER_XRef_LABOURCOUNT, labourLeaderDetailsResponse.getLabourCount());

        Log.d(" *** contentValues", contentValues.toString() );

        try {
            rowId = sqLiteDatabase.insertOrThrow(Config.TABLE_LABOUR_LEADER_XRef, null, contentValues);
        } catch (SQLiteException e) {
//            Logger.d(e);
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowId;
    }


    public long insertGetMasters(GetMasters.ListResult getMasters) {
        long rowId = -1;

        //Todo Check if value exist or not
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_USERINFO_ID, getMasters.getId());
        contentValues.put(Config.COLUMN_USERINFO_USERID, getMasters.getUserId());
        contentValues.put(Config.COLUMN_USERINFO_FIRSTNAME, getMasters.getFirstName());
        contentValues.put(Config.COLUMN_USERINFO_LASTNAME, getMasters.getLastname());
        contentValues.put(Config.COLUMN_USERINFO_MIDDLENAME, getMasters.getMiddleName());
        contentValues.put(Config.COLUMN_USERINFO_CONTACTNUMBER, getMasters.getContactNumber());
        contentValues.put(Config.COLUMN_USERINFO_MOBILENUMBER, getMasters.getMobileNumber());
        contentValues.put(Config.COLUMN_USERINFO_USERNAME, getMasters.getUserName());
        contentValues.put(Config.COLUMN_USERINFO_PASSWORD, getMasters.getPassword());
        contentValues.put(Config.COLUMN_USERINFO_ROLEID, getMasters.getRoleId());
        contentValues.put(Config.COLUMN_USERINFO_EMAIL, getMasters.getEmail());
        contentValues.put(Config.COLUMN_USERINFO_MANAGERID, getMasters.getManagerId());
        contentValues.put(Config.COLUMN_USERINFO_USERCODE, getMasters.getUserCode());
        contentValues.put(Config.COLUMN_USERINFO_EMPLOYEECODE, getMasters.getEmployeeCode());
        contentValues.put(Config.COLUMN_USERINFO_ISACTIVE, getMasters.getIsActive());
        contentValues.put(Config.COLUMN_USERINFO_CREATEDBYUSERID, getMasters.getCreatedByUserId());
        contentValues.put(Config.COLUMN_USERINFO_CREATEDDATE, getMasters.getCreatedDate());
        contentValues.put(Config.COLUMN_USERINFO_UPDATEDBYUSERID, getMasters.getUpdatedByUserId());
        contentValues.put(Config.COLUMN_USERINFO_UPDATEDDATE, getMasters.getUpdatedDate());
        try {
            rowId = sqLiteDatabase.insertOrThrow(Config.TABLE_USERINFO, null, contentValues);
        } catch (SQLiteException e) {
//            Logger.d(e);
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowId;
    }


    public long deleteLabourusingRequestCode(String requestCode) {
        long deletedRowCount = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            deletedRowCount = sqLiteDatabase.delete(Config.TABLE_LABOUR_REQUEST,
                    Config.COLUMN_LABOUR_REQUEST_CODE + " = ? ",
                    new String[]{String.valueOf(requestCode)});
        } catch (SQLiteException e) {
//            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deletedRowCount;
    }

    public long deleteRequestHeaderRequestCode(String requestCode) {
        long deletedRowCount = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            deletedRowCount = sqLiteDatabase.delete(Config.TABLE_REQUEST,
                    Config.COLUMN_REQUESTCODE + " = ? ",
                    new String[]{String.valueOf(requestCode)});



        } catch (SQLiteException e) {
//            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deletedRowCount;
    }

    public long deleteRequestFormerDetails(String requestCode) {
        long deletedRowCount = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            deletedRowCount = sqLiteDatabase.delete(Config.TABLE_FORMER_TABLE,
                    Config.COLUMN_FORMER_DETAILS_REQUEST_CODE + " = ? ",
                    new String[]{String.valueOf(requestCode)});
        } catch (SQLiteException e) {
//            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deletedRowCount;
    }

    public long deleteLabourLeaderDetails(String requestCode,String labourid) {
        long deletedRowCount = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            deletedRowCount = sqLiteDatabase.delete(Config.TABLE_LABOUR_LEADER_XRef,Config.COLUMN_LABOUR_LEADER_XRef_REQUESTCODE + " = ? AND "+ Config.COLUMN_LABOUR_LEADER_XRef_LABOURID + " = ? ",new String[]{requestCode,labourid});


        } catch (SQLiteException e) {
//            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deletedRowCount;
//        String countQuery = "DELETE FROM LabourLeaderXref2 WHERE RequestCode ='" + requestCode + "' AND LabourId=" + labourid;
//        Log.d("mahesh","Delete Existing labour :"+countQuery);
//        Log.d("mahesh","Delete Existing labour :"+countQuery);
//        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
//        //int count = cursor.getCount();
//        cursor.close();
    }

    public void deleteServiceRequest(String requestCode, int TypeID) {
        String countQuery = "DELETE FROM LabourServiceXref WHERE RequestCode ='" + requestCode + "' AND TypeCdId=" + TypeID;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        //int count = cursor.getCount();
        cursor.close();
        // return count;
    }

    public List<SyncLabourResponse.ListResult> getAllLabourRequests() {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_LABOUR_REQUEST, new String[]{
                    Config.COLUMN_LABOUR_ID,
                    Config.COLUMN_LABOUR_REQUEST_CODE,
                    Config.COLUMN_LABOUR_STARTDATE,
                    Config.COLUMN_LABOUR_DURATION,
                    Config.COLUMN_LABOUR_LEADERID,
                    Config.COLUMN_LABOUR_PIN,
                    Config.COLUMN_LABOUR_JOBDONE_DATE,
                    Config.COLUMN_LABOUR_CREATED_BY_USER_ID,
                    Config.COLUMN_LABOUR_CREATED_DATE,
                    Config.COLUMN_LABOUR_UPDATED_BY_USER_ID,
                    Config.COLUMN_LABOUR_UPDATED_DATE,
                    Config.COLUMN_LABOUR_ASSIGNED_DATE,
                    Config.COLUMN_LABOUR_NET_WEIGHT,
                    Config.COLUMN_LABOUR_AMOUNT,
                    Config.COLUMN_LABOUR_HARVESTING_AMOUNT,
                    Config.COLUMN_LABOUR_PRUNING_AMOUNT,
                    Config.COLUMN_LABOUR_PRUNING_WITH_INTERCROP_AMOUNT,
                    Config.COLUMN_LABOUR_HARVESTING_WITH_INTERCROP_AMOUNT,
                    Config.COLUMN_LABOUR_TRESS_COUNT,
                    Config.COLUMN_LABOUR_SERVER_UPDATED_STATUS,
                    Config.COLUMN_LABOUR_OderId,
                    Config.COLUMN_LABOUR_treesCountWithIntercrop,
                    Config.COLUMN_LABOUR_netWeightIntercrop
            }, null, null, null, null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<SyncLabourResponse.ListResult> labbourlist = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_ID));
                        String requestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_REQUEST_CODE));
                        String startDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_STARTDATE));
                        int durationId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_DURATION));
                        int leaderId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADERID));
                        int pin = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_PIN));
                        String jobDoneDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_JOBDONE_DATE));
                        int createdByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_CREATED_BY_USER_ID));
                        String createdDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_CREATED_DATE));
                        int updatedByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_UPDATED_BY_USER_ID));
                        String updatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_UPDATED_DATE));
                        String assignedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_ASSIGNED_DATE));
                        Double netWeight = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_NET_WEIGHT));
                        Double amount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_AMOUNT));
                        double harvestingAmount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_HARVESTING_AMOUNT));
                        double pruningAmount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_PRUNING_AMOUNT));
                        double unKnown1Amount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_PRUNING_WITH_INTERCROP_AMOUNT));
                        double unKnown2Amount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_HARVESTING_WITH_INTERCROP_AMOUNT));
                        int treesCount = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_TRESS_COUNT));
                        int serverUpdatedStatus = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_SERVER_UPDATED_STATUS));
                        String COLUMN_LABOUR_OderId = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_OderId));
                        Integer COLUMN_LABOUR_treesCountWithIntercrop = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_treesCountWithIntercrop));
                        Double COLUMN_LABOUR_netWeightIntercrop = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_netWeightIntercrop));
                        int expectedBunches = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_EXPECTED_BUNCHES));
                        Double expectedNetWeight = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_EXPECTED_NET_WEIGHT));
                        int ownPoledb = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_OWN_POLE));
                         boolean ownPole= false;

                        if(ownPoledb == 0)
                        {
                            ownPole = true;
                        }
                        labbourlist.add(new SyncLabourResponse.ListResult(id, requestCode, startDate, durationId, leaderId, pin, jobDoneDate, createdByUserId,
                                createdDate, updatedByUserId, updatedDate, assignedDate, netWeight, amount, harvestingAmount, pruningAmount,
                                unKnown1Amount, unKnown2Amount, treesCount, serverUpdatedStatus, COLUMN_LABOUR_OderId, COLUMN_LABOUR_treesCountWithIntercrop, COLUMN_LABOUR_netWeightIntercrop,
                                expectedBunches,expectedNetWeight,ownPoledb));
                    } while (cursor.moveToNext());

                    return labbourlist;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: (getAllLabourRequests)" + e.getMessage());
            //Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public List<SyncLabourResponse.ListResult> getLabourRequestByID(String requestCode) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            String query = "SELECT * FROM LabourRequest WHERE RequestCode ='" + requestCode + "'";
            Log.d("SQL", "getlabour :" + query);
            cursor = sqLiteDatabase.rawQuery(query, null);


            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<SyncLabourResponse.ListResult> labbourlist = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_ID));
                        String requestCode1 = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_REQUEST_CODE));
                        String startDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_STARTDATE));
                        int durationId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_DURATION));
                        int leaderId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADERID));
                        int pin = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_PIN));
                        String jobDoneDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_JOBDONE_DATE));
                        int createdByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_CREATED_BY_USER_ID));
                        String createdDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_CREATED_DATE));
                        int updatedByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_UPDATED_BY_USER_ID));
                        String updatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_UPDATED_DATE));
                        String assignedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_ASSIGNED_DATE));
                        Double netWeight = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_NET_WEIGHT));
                        double amount = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_AMOUNT));
                        double harvestingAmount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_HARVESTING_AMOUNT));
                        double pruningAmount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_PRUNING_AMOUNT));
                        double unKnown1Amount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_PRUNING_WITH_INTERCROP_AMOUNT));
                        double unKnown2Amount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_HARVESTING_WITH_INTERCROP_AMOUNT));
                        int treesCount = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_TRESS_COUNT));
                        int serverUpdatedStatus = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_SERVER_UPDATED_STATUS));
                        String COLUMN_LABOUR_OderId = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_OderId));
                        Integer COLUMN_LABOUR_treesCountWithIntercrop = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_treesCountWithIntercrop));
                        Double COLUMN_LABOUR_netWeightIntercrop = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_netWeightIntercrop));
                        int expectedBunches = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_EXPECTED_BUNCHES));
                        Double expectedNetWeight = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_EXPECTED_NET_WEIGHT));
                        int ownPoledb = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_OWN_POLE));

                        boolean ownPole= false;

                        if(ownPoledb == 0)
                        {
                            ownPole = true;
                        }

                        labbourlist.add(new SyncLabourResponse.ListResult(id, requestCode1, startDate, durationId, leaderId, pin, jobDoneDate, createdByUserId,
                                createdDate, updatedByUserId, updatedDate, assignedDate, netWeight, amount, harvestingAmount, pruningAmount,
                                unKnown1Amount, unKnown2Amount, treesCount, serverUpdatedStatus, COLUMN_LABOUR_OderId, COLUMN_LABOUR_treesCountWithIntercrop, COLUMN_LABOUR_netWeightIntercrop,
                                expectedBunches,expectedNetWeight,ownPoledb));
                    } while (cursor.moveToNext());

                    return labbourlist;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public List<SyncLabourServicesResponse.ListResult> getLabouServicestByID(String requestCodee) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            String query = "SELECT DISTINCT * FROM LabourServiceXref WHERE RequestCode ='" + requestCodee + "'" ;
            Log.d("SQL", "getLabouServicestByID :" + query);
            cursor = sqLiteDatabase.rawQuery(query, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<SyncLabourServicesResponse.ListResult> labbourServicelist = new ArrayList<>();
                    do {
                        String requestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SERVICE_REQUEST_CODE));
                        int serviceTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_SERVICE_TYPE_CDID));

                        labbourServicelist.add(new SyncLabourServicesResponse.ListResult(requestCode, serviceTypeId));
                    } while (cursor.moveToNext());

                    return labbourServicelist;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
        //    Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public List<SyncFarmerDetailsResponse.ListResult> getFarmerDetailsByID(String requestCodee) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            String query = "SELECT * FROM FarmerDetails WHERE RequestCode ='" + requestCodee + "'";
            Log.d("SQL", "getlabour :" + query);
            cursor = sqLiteDatabase.rawQuery(query, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<SyncFarmerDetailsResponse.ListResult> farmerDetailsList = new ArrayList<>();
                    do {
                        String requestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_REQUEST_CODE));
                        String farmerCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_FORMER_CODE));
                        String plotCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_PLATCODE));
                        Double latitude = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_LATITUDE));
                        Double longitude = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_LONGITUDE));
                        String mobileNumber = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_MOBILENUMBER));
                        String contactNumber = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_CONTACT_NUMBER));
                        String landmark = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_LANDMARK));

                        farmerDetailsList.add(new SyncFarmerDetailsResponse.ListResult(requestCode, farmerCode, plotCode, latitude, longitude, mobileNumber, contactNumber,
                                landmark));
                    } while (cursor.moveToNext());

                    return farmerDetailsList;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
           // Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }


    public List<SyncRequestHeaderResponse.ListResult> getAllRequestHeaders() {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_REQUEST, new String[]{Config.COLUMN_REQUEST_ID, Config.COLUMN_REQUESTCODE, Config.COLUMN_FORMERCODE, Config.COLUMN_PLATCODE, Config.COLUMN_REQ_CREATEDDATE,
                    Config.COLUMN_STATUS_TYPE_ID, Config.COLUMN_IS_FORMER_REQUEST, Config.COLUMN_CREATED_BY_USERID, Config.COLUMN_CREATED_DATE, Config.COLUMN_UPDATED_BY_USERID,
                    Config.COLUMN_UPDATED_DATE, Config.COLUMN_TotalCoast, Config.COLUMN_COMMENTS, Config.COLUMN_CROPMAINTANCE_DATE, Config.COLUMN_REQUEST_TYPE_ID,
                    Config.COLUMN_ISSUE_TYPE_ID, Config.COLUMN_FARMERNAME, Config.COLUMN_PLOT_VILLAGE, Config.COLUMN_PALM_AREA,
                    Config.COLUMN_REQ_SERVERUPDATED_STATUS, Config.COLUMN_REQ_YEAR_OF_PLANTING}, null, null, null, null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<SyncRequestHeaderResponse.ListResult> requestHeaderList = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQUEST_ID));
                        String requestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQUESTCODE));
                        String farmerCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMERCODE));
                        String plotCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLATCODE));
                        String reqCreatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQ_CREATEDDATE));
                        int statusTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_STATUS_TYPE_ID));
                        int isFarmerRequest = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_IS_FORMER_REQUEST));
                        int createdByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_CREATED_BY_USERID));
                        String createdDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_CREATED_DATE));
                        int updatedByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_UPDATED_BY_USERID));
                        String updatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_UPDATED_DATE));
                        Double totalCost = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_TotalCoast));
                        String comments = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COMMENTS));
                        String cropMaintainceDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_CROPMAINTANCE_DATE));
                        int requestTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQUEST_TYPE_ID));
                        int issueTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ISSUE_TYPE_ID));
                        String farmerName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FARMERNAME));
                        String plotVillage = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLOT_VILLAGE));
                        Double palmArea = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_PALM_AREA));
                        int serverUpdatedStatus = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQ_SERVERUPDATED_STATUS));
                        String plantingYear = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQ_YEAR_OF_PLANTING));
                        Double totalCostWithoutServiceCharge = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_TOTAL_COST_WITHOUT_SERVICE_CHARGE));
                        Double serviceChargeAmount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_SERCVICE_CHARGE_AMOUNT));
                        String parentRequestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PARENT_REQUEST_CODE));
                        String recoveryFarmerCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_RECOVERY_FARMER_CODE));
                        int clusterId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_CLUSTER_ID));

                        requestHeaderList.add(new SyncRequestHeaderResponse.ListResult(id, requestCode, farmerCode, plotCode, reqCreatedDate, statusTypeId, isFarmerRequest, createdByUserId,
                                createdDate, updatedByUserId, updatedDate, totalCost, comments, cropMaintainceDate, requestTypeId, issueTypeId,
                                farmerName, plotVillage, palmArea, serverUpdatedStatus, plantingYear,totalCostWithoutServiceCharge,serviceChargeAmount,parentRequestCode,
                                recoveryFarmerCode,clusterId));
                    } while (cursor.moveToNext());

                    return requestHeaderList;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
          //  Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public List<SyncLabourServicesResponse.ListResult> getAllLabourServices() {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_LABOUR_SERVICES, new String[]{Config.COLUMN_SERVICE_REQUEST_CODE, Config.COLUMN_SERVICE_TYPE_CDID}, null, null, null, null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<SyncLabourServicesResponse.ListResult> labourServicesList = new ArrayList<>();
                    do {
                        String requestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SERVICE_REQUEST_CODE));
                        int serviceTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_SERVICE_TYPE_CDID));

                        labourServicesList.add(new SyncLabourServicesResponse.ListResult(requestCode, serviceTypeId));
                    } while (cursor.moveToNext());

                    return labourServicesList;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
           // Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public List<SyncFarmerDetailsResponse.ListResult> getAllFarmerDetails() {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_FORMER_TABLE, new String[]{Config.COLUMN_FORMER_DETAILS_REQUEST_CODE, Config.COLUMN_FORMER_DETAILS_FORMER_CODE,
                    Config.COLUMN_FORMER_DETAILS_PLATCODE, Config.COLUMN_FORMER_DETAILS_LATITUDE, Config.COLUMN_FORMER_DETAILS_LONGITUDE,
                    Config.COLUMN_FORMER_DETAILS_MOBILENUMBER, Config.COLUMN_FORMER_DETAILS_CONTACT_NUMBER, Config.COLUMN_FORMER_DETAILS_LANDMARK,}, null, null, null, null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<SyncFarmerDetailsResponse.ListResult> farmerDetailsList = new ArrayList<>();
                    do {
                        String requestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_REQUEST_CODE));
                        String farmerCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_FORMER_CODE));
                        String plotCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_PLATCODE));
                        Double latitude = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_LATITUDE));
                        Double longitude = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_LONGITUDE));
                        String mobileNumber = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_MOBILENUMBER));
                        String contactNumber = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_CONTACT_NUMBER));
                        String landmark = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_LANDMARK));

                        farmerDetailsList.add(new SyncFarmerDetailsResponse.ListResult(requestCode, farmerCode, plotCode, latitude, longitude, mobileNumber, contactNumber,
                                landmark));
                    } while (cursor.moveToNext());

                    return farmerDetailsList;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
           // Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }


    public List<SyncRequestHeaderResponse.ListResult> getCurrentDayRequests() {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            String query = "SELECT * FROM RequestHeader WHERE CreatedDate Like '%2019-11-25%'";
            cursor = sqLiteDatabase.rawQuery(query, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<SyncRequestHeaderResponse.ListResult> requestHeaderList = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQUEST_ID));
                        String requestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQUESTCODE));
                        String farmerCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMERCODE));
                        String plotCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLATCODE));
                        String reqCreatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQ_CREATEDDATE));
                        int statusTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_STATUS_TYPE_ID));
                        int isFarmerRequest = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_IS_FORMER_REQUEST));
                        int createdByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_CREATED_BY_USERID));
                        String createdDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_CREATED_DATE));
                        int updatedByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_UPDATED_BY_USERID));
                        String updatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_UPDATED_DATE));
                        Double totalCost = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_TotalCoast));
                        String comments = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COMMENTS));
                        String cropMaintainceDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_CROPMAINTANCE_DATE));
                        int requestTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQUEST_TYPE_ID));
                        int issueTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ISSUE_TYPE_ID));
                        String farmerName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FARMERNAME));
                        String plotVillage = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLOT_VILLAGE));
                        Double palmArea = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_PALM_AREA));
                        int serverUpdatedStatus = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQ_SERVERUPDATED_STATUS));
                        String YearOfplanting = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQ_YEAR_OF_PLANTING));
                        Double totalCostWithoutServiceCharge = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_TOTAL_COST_WITHOUT_SERVICE_CHARGE));
                        Double serviceChargeAmount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_SERCVICE_CHARGE_AMOUNT));
                        String parentRequestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PARENT_REQUEST_CODE));
                        String recoveryFarmerCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_RECOVERY_FARMER_CODE));
                        int clusterId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_CLUSTER_ID));

                        requestHeaderList.add(new SyncRequestHeaderResponse.ListResult(id, requestCode, farmerCode, plotCode, reqCreatedDate, statusTypeId, isFarmerRequest, createdByUserId,
                                createdDate, updatedByUserId, updatedDate, totalCost, comments, cropMaintainceDate, requestTypeId, issueTypeId,
                                farmerName, plotVillage, palmArea, serverUpdatedStatus, YearOfplanting,totalCostWithoutServiceCharge,serviceChargeAmount,
                                parentRequestCode,recoveryFarmerCode,clusterId));
                    } while (cursor.moveToNext());

                    return requestHeaderList;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
          //  Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public List<SyncRequestHeaderResponse.ListResult> getCurrentMonthRequests(String month) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            String query = "SELECT * FROM RequestHeader WHERE CreatedDate Like '%" + month + "%'";
            cursor = sqLiteDatabase.rawQuery(query, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<SyncRequestHeaderResponse.ListResult> requestHeaderList = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQUEST_ID));
                        String requestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQUESTCODE));
                        String farmerCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMERCODE));
                        String plotCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLATCODE));
                        String reqCreatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQ_CREATEDDATE));
                        int statusTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_STATUS_TYPE_ID));
                        int isFarmerRequest = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_IS_FORMER_REQUEST));
                        int createdByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_CREATED_BY_USERID));
                        String createdDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_CREATED_DATE));
                        int updatedByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_UPDATED_BY_USERID));
                        String updatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_UPDATED_DATE));
                        Double totalCost = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_TotalCoast));
                        String comments = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COMMENTS));
                        String cropMaintainceDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_CROPMAINTANCE_DATE));
                        int requestTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQUEST_TYPE_ID));
                        int issueTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ISSUE_TYPE_ID));
                        String farmerName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FARMERNAME));
                        String plotVillage = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLOT_VILLAGE));
                        Double palmArea = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_PALM_AREA));
                        int serverUpdatedStatus = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQ_SERVERUPDATED_STATUS));
                        String yearofplanting = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQ_YEAR_OF_PLANTING));
                        Double totalCostWithoutServiceCharge = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_TOTAL_COST_WITHOUT_SERVICE_CHARGE));
                        Double serviceChargeAmount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_SERCVICE_CHARGE_AMOUNT));
                        String parentRequestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PARENT_REQUEST_CODE));
                        String recoveryFarmerCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_RECOVERY_FARMER_CODE));
                        int clusterId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_CLUSTER_ID));

                        requestHeaderList.add(new SyncRequestHeaderResponse.ListResult(id, requestCode, farmerCode, plotCode, reqCreatedDate, statusTypeId, isFarmerRequest, createdByUserId,
                                createdDate, updatedByUserId, updatedDate, totalCost, comments, cropMaintainceDate, requestTypeId, issueTypeId,
                                farmerName, plotVillage, palmArea, serverUpdatedStatus, yearofplanting,totalCostWithoutServiceCharge,serviceChargeAmount,
                                parentRequestCode, recoveryFarmerCode,clusterId));
                    } while (cursor.moveToNext());

                    return requestHeaderList;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
           // Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public List<SyncLabourResponse.ListResult> getLabourRequestByMonth(String month) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            String query = "SELECT * FROM " + Config.TABLE_LABOUR_REQUEST + " WHERE AssignedDate Like '%" + month + "%'";
            Log.d("SQL", "getlabour By date :" + query);
            cursor = sqLiteDatabase.rawQuery(query, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<SyncLabourResponse.ListResult> labbourlist = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_ID));
                        String requestCode1 = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_REQUEST_CODE));
                        String startDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_ASSIGNED_DATE));
                        int durationId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_DURATION));
                        int leaderId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADERID));
                        int pin = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_PIN));
                        String jobDoneDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_JOBDONE_DATE));
                        int createdByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_CREATED_BY_USER_ID));
                        String createdDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_CREATED_DATE));
                        int updatedByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_UPDATED_BY_USER_ID));
                        String updatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_UPDATED_DATE));
                        String assignedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_ASSIGNED_DATE));
                        Double netWeight = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_NET_WEIGHT));
                        double amount = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_AMOUNT));
                        double harvestingAmount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_HARVESTING_AMOUNT));
                        double pruningAmount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_PRUNING_AMOUNT));
                        double unKnown1Amount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_PRUNING_WITH_INTERCROP_AMOUNT));
                        double unKnown2Amount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_HARVESTING_WITH_INTERCROP_AMOUNT));
                        int treesCount = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_TRESS_COUNT));
                        int serverUpdatedStatus = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_SERVER_UPDATED_STATUS));
                        String COLUMN_LABOUR_OderId = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_OderId));
                        Integer COLUMN_LABOUR_treesCountWithIntercrop = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_treesCountWithIntercrop));
                        Double COLUMN_LABOUR_netWeightIntercrop = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_netWeightIntercrop));
                        int expectedBunches = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_EXPECTED_BUNCHES));
                        Double expectedNetWeight = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_EXPECTED_NET_WEIGHT));
                        int ownPoledb = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_OWN_POLE));

                        boolean ownPole= false;

                        if(ownPoledb == 0)
                        {
                            ownPole = true;
                        }

                        labbourlist.add(new SyncLabourResponse.ListResult(id, requestCode1, startDate, durationId, leaderId, pin, jobDoneDate, createdByUserId,
                                createdDate, updatedByUserId, updatedDate, assignedDate, netWeight, amount, harvestingAmount, pruningAmount,
                                unKnown1Amount, unKnown2Amount, treesCount, serverUpdatedStatus, COLUMN_LABOUR_OderId, COLUMN_LABOUR_treesCountWithIntercrop, COLUMN_LABOUR_netWeightIntercrop,
                                expectedBunches,expectedNetWeight, ownPoledb));
                    } while (cursor.moveToNext());

                    return labbourlist;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
         //   Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public List<SyncRequestHeaderResponse.ListResult> getRequestsByID(String RequestID) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            String query = "SELECT * FROM RequestHeader WHERE RequestCode ='" + RequestID + "'";
            cursor = sqLiteDatabase.rawQuery(query, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<SyncRequestHeaderResponse.ListResult> requestHeaderList = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQUEST_ID));
                        String requestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQUESTCODE));
                        String farmerCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMERCODE));
                        String plotCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLATCODE));
                        String reqCreatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQ_CREATEDDATE));
                        int statusTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_STATUS_TYPE_ID));
                        int isFarmerRequest = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_IS_FORMER_REQUEST));
                        int createdByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_CREATED_BY_USERID));
                        String createdDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_CREATED_DATE));
                        int updatedByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_UPDATED_BY_USERID));
                        String updatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_UPDATED_DATE));
                        Double totalCost = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_TotalCoast));
                        String comments = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COMMENTS));
                        String cropMaintainceDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_CROPMAINTANCE_DATE));
                        int requestTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQUEST_TYPE_ID));
                        int issueTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ISSUE_TYPE_ID));
                        String farmerName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FARMERNAME));
                        String plotVillage = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLOT_VILLAGE));
                        Double palmArea = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_PALM_AREA));
                        int serverUpdatedStatus = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQ_SERVERUPDATED_STATUS));
                        String yearofPlanting = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQ_YEAR_OF_PLANTING));
                        Double totalCostWithoutServiceCharge = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_TOTAL_COST_WITHOUT_SERVICE_CHARGE));
                        Double serviceChargeAmount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_SERCVICE_CHARGE_AMOUNT));
                        String parentRequestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PARENT_REQUEST_CODE));
                        String recoveryFarmerCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_RECOVERY_FARMER_CODE));
                        int clusterId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_CLUSTER_ID));

                        requestHeaderList.add(new SyncRequestHeaderResponse.ListResult(id, requestCode, farmerCode, plotCode, reqCreatedDate, statusTypeId, isFarmerRequest, createdByUserId,
                                createdDate, updatedByUserId, updatedDate, totalCost, comments, cropMaintainceDate, requestTypeId, issueTypeId,
                                farmerName, plotVillage, palmArea, serverUpdatedStatus, yearofPlanting, totalCostWithoutServiceCharge,serviceChargeAmount,
                                parentRequestCode,recoveryFarmerCode,clusterId));
                    } while (cursor.moveToNext());

                    return requestHeaderList;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
            //Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public List<SyncRequestHeaderResponse.ListResult> getPendingRequests() {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            String query = "SELECT * FROM RequestHeader WHERE  ServerUpdatedStatus = 1";
            cursor = sqLiteDatabase.rawQuery(query, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<SyncRequestHeaderResponse.ListResult> requestHeaderList = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQUEST_ID));
                        String requestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQUESTCODE));
                        String farmerCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMERCODE));
                        String plotCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLATCODE));
                        String reqCreatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQ_CREATEDDATE));
                        int statusTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_STATUS_TYPE_ID));
                        int isFarmerRequest = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_IS_FORMER_REQUEST));
                        int createdByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_CREATED_BY_USERID));
                        String createdDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_CREATED_DATE));
                        int updatedByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_UPDATED_BY_USERID));
                        String updatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_UPDATED_DATE));
                        Double totalCost = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_TotalCoast));
                        String comments = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COMMENTS));
                        String cropMaintainceDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_CROPMAINTANCE_DATE));
                        int requestTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQUEST_TYPE_ID));
                        int issueTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ISSUE_TYPE_ID));
                        String farmerName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FARMERNAME));
                        String plotVillage = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLOT_VILLAGE));
                        Double palmArea = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_PALM_AREA));
                        int serverUpdatedStatus = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQ_SERVERUPDATED_STATUS));
                        String yearofPlanting = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQ_YEAR_OF_PLANTING));
                        Double totalCostWithoutServiceCharge = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_TOTAL_COST_WITHOUT_SERVICE_CHARGE));
                        Double serviceChargeAmount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_SERCVICE_CHARGE_AMOUNT));
                        String parentRequestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PARENT_REQUEST_CODE));
                        String recoveryFarmerCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_RECOVERY_FARMER_CODE));
                        int clusterId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_CLUSTER_ID));

                        requestHeaderList.add(new SyncRequestHeaderResponse.ListResult(id, requestCode, farmerCode, plotCode, reqCreatedDate, statusTypeId, isFarmerRequest, createdByUserId,
                                createdDate, updatedByUserId, updatedDate, totalCost, comments, cropMaintainceDate, requestTypeId, issueTypeId,
                                farmerName, plotVillage, palmArea, serverUpdatedStatus, yearofPlanting,totalCostWithoutServiceCharge,serviceChargeAmount,
                                parentRequestCode,recoveryFarmerCode,clusterId));
                    } while (cursor.moveToNext());

                    return requestHeaderList;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
         //   Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public List<SyncRequestHeaderResponse.ListResult> getRequestwithdate(String startdate, String endDate) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            String query = "SELECT * from " + Config.TABLE_REQUEST + "  WHERE CreatedDate BETWEEN '" + startdate + "' And '" + endDate + "'";
            cursor = sqLiteDatabase.rawQuery(query, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<SyncRequestHeaderResponse.ListResult> requestHeaderList = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQUEST_ID));
                        String requestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQUESTCODE));
                        String farmerCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMERCODE));
                        String plotCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLATCODE));
                        String reqCreatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQ_CREATEDDATE));
                        int statusTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_STATUS_TYPE_ID));
                        int isFarmerRequest = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_IS_FORMER_REQUEST));
                        int createdByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_CREATED_BY_USERID));
                        String createdDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_CREATED_DATE));
                        int updatedByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_UPDATED_BY_USERID));
                        String updatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_UPDATED_DATE));
                        Double totalCost = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_TotalCoast));
                        String comments = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COMMENTS));
                        String cropMaintainceDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_CROPMAINTANCE_DATE));
                        int requestTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQUEST_TYPE_ID));
                        int issueTypeId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ISSUE_TYPE_ID));
                        String farmerName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FARMERNAME));
                        String plotVillage = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLOT_VILLAGE));
                        Double palmArea = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_PALM_AREA));
                        int serverUpdatedStatus = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_REQ_SERVERUPDATED_STATUS));
                        String yearofPlanting = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQ_YEAR_OF_PLANTING));
                        Double totalCostWithoutServiceCharge = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_TOTAL_COST_WITHOUT_SERVICE_CHARGE));
                        Double serviceChargeAmount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_SERCVICE_CHARGE_AMOUNT));
                        String parentRequestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PARENT_REQUEST_CODE));
                        String recoveryFarmerCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_RECOVERY_FARMER_CODE));
                        int clusterId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_CLUSTER_ID));

                        requestHeaderList.add(new SyncRequestHeaderResponse.ListResult(id, requestCode, farmerCode, plotCode, reqCreatedDate, statusTypeId, isFarmerRequest, createdByUserId,
                                createdDate, updatedByUserId, updatedDate, totalCost, comments, cropMaintainceDate, requestTypeId, issueTypeId,
                                farmerName, plotVillage, palmArea, serverUpdatedStatus, yearofPlanting, totalCostWithoutServiceCharge,serviceChargeAmount,parentRequestCode,
                                recoveryFarmerCode, clusterId));
                    } while (cursor.moveToNext());

                    return requestHeaderList;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
            //Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public List<SyncLabourResponse.ListResult> getLabourRequestByDates(String startdate, String endDate) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            String query = "SELECT * from " + Config.TABLE_LABOUR_REQUEST + "  WHERE CreatedDate BETWEEN '" + startdate + "' And '" + endDate + "'";
            Log.d("SQL", "getlabour By date :" + query);
            cursor = sqLiteDatabase.rawQuery(query, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<SyncLabourResponse.ListResult> labbourlist = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_ID));
                        String requestCode1 = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_REQUEST_CODE));
                        String startDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_STARTDATE));
                        int durationId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_DURATION));
                        int leaderId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADERID));
                        int pin = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_PIN));
                        String jobDoneDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_JOBDONE_DATE));
                        int createdByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_CREATED_BY_USER_ID));
                        String createdDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_CREATED_DATE));
                        int updatedByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_UPDATED_BY_USER_ID));
                        String updatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_UPDATED_DATE));
                        String assignedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_ASSIGNED_DATE));
                        Double netWeight = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_NET_WEIGHT));
                        double amount = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_AMOUNT));
                        double harvestingAmount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_HARVESTING_AMOUNT));
                        double pruningAmount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_PRUNING_AMOUNT));
                        double unKnown1Amount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_PRUNING_WITH_INTERCROP_AMOUNT));
                        double unKnown2Amount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_HARVESTING_WITH_INTERCROP_AMOUNT));
                        int treesCount = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_TRESS_COUNT));
                        int serverUpdatedStatus = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_SERVER_UPDATED_STATUS));
                        String COLUMN_LABOUR_OderId = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_OderId));
                        Integer COLUMN_LABOUR_treesCountWithIntercrop = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_treesCountWithIntercrop));
                        Double COLUMN_LABOUR_netWeightIntercrop = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_netWeightIntercrop));
                        int expectedBunches = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_EXPECTED_BUNCHES));
                        Double expectedNetWeight = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_EXPECTED_NET_WEIGHT));
                        int ownPoledb = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_OWN_POLE));
                        boolean ownPole= false;

                        if(ownPoledb == 0)
                        {
                            ownPole = true;
                        }

                        labbourlist.add(new SyncLabourResponse.ListResult(id, requestCode1, startDate, durationId, leaderId, pin, jobDoneDate, createdByUserId,
                                createdDate, updatedByUserId, updatedDate, assignedDate, netWeight, amount, harvestingAmount, pruningAmount,
                                unKnown1Amount, unKnown2Amount, treesCount, serverUpdatedStatus, COLUMN_LABOUR_OderId, COLUMN_LABOUR_treesCountWithIntercrop, COLUMN_LABOUR_netWeightIntercrop,
                                expectedBunches,expectedNetWeight, ownPoledb));
                    } while (cursor.moveToNext());

                    return labbourlist;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
          //  Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public List<SyncLabourResponse.ListResult> getLabourRequestSingleDate(String date) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            String query = "SELECT * from " + Config.TABLE_LABOUR_REQUEST + "   WHERE StartDate like '" + date + "%' ";
            Log.d("SQL", "getlabour By date :" + query);
            cursor = sqLiteDatabase.rawQuery(query, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<SyncLabourResponse.ListResult> labbourlist = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_ID));
                        String requestCode1 = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_REQUEST_CODE));
                        String startDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_STARTDATE));
                        int durationId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_DURATION));
                        int leaderId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADERID));
                        int pin = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_PIN));
                        String jobDoneDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_JOBDONE_DATE));
                        int createdByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_CREATED_BY_USER_ID));
                        String createdDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_CREATED_DATE));
                        int updatedByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_UPDATED_BY_USER_ID));
                        String updatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_UPDATED_DATE));
                        String assignedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_ASSIGNED_DATE));
                        Double netWeight = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_NET_WEIGHT));
                        double amount = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_AMOUNT));
                        double harvestingAmount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_HARVESTING_AMOUNT));
                        double pruningAmount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_PRUNING_AMOUNT));
                        double unKnown1Amount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_PRUNING_WITH_INTERCROP_AMOUNT));
                        double unKnown2Amount = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_HARVESTING_WITH_INTERCROP_AMOUNT));
                        int treesCount = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_TRESS_COUNT));
                        int serverUpdatedStatus = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_SERVER_UPDATED_STATUS));
                        String COLUMN_LABOUR_OderId = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_OderId));
                        Integer COLUMN_LABOUR_treesCountWithIntercrop = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_treesCountWithIntercrop));
                        Double COLUMN_LABOUR_netWeightIntercrop = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_netWeightIntercrop));
                        int expectedBunches = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_EXPECTED_BUNCHES));
                        Double expectedNetWeight = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_EXPECTED_NET_WEIGHT));
                        int ownPoledb = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_OWN_POLE));

                        boolean ownPole= false;

                        if(ownPoledb == 0)
                        {
                            ownPole = true;
                        }


                        labbourlist.add(new SyncLabourResponse.ListResult(id, requestCode1, startDate, durationId, leaderId, pin, jobDoneDate, createdByUserId,
                                createdDate, updatedByUserId, updatedDate, assignedDate, netWeight, amount, harvestingAmount, pruningAmount,
                                unKnown1Amount, unKnown2Amount, treesCount, serverUpdatedStatus, COLUMN_LABOUR_OderId, COLUMN_LABOUR_treesCountWithIntercrop, COLUMN_LABOUR_netWeightIntercrop,
                                expectedBunches,expectedNetWeight,ownPoledb));
                    } while (cursor.moveToNext());

                    return labbourlist;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
            //  Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }


    public boolean deleteAllRequests() {
        boolean deleteStatus = false;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            //for "1" delete() method returns number of deleted rows
            //if you don't want row count just use delete(TABLE_NAME, null, null)
            sqLiteDatabase.delete(Config.TABLE_REQUEST, null, null);

            long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_REQUEST);

            if (count == 0)
                deleteStatus = true;

        } catch (SQLiteException e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deleteStatus;
    }
    public boolean DeleteAllUsers() {
        boolean deleteStatus = false;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            //for "1" delete() method returns number of deleted rows
            //if you don't want row count just use delete(TABLE_NAME, null, null)
            sqLiteDatabase.delete(Config.TABLE_USERINFO, null, null);

            long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_USERINFO);

            if (count == 0)
                deleteStatus = true;

        } catch (SQLiteException e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deleteStatus;
    }
    public long getAllRequestsCount() {
        long count = 0;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            //for "1" delete() method returns number of deleted rows
            //if you don't want row count just use delete(TABLE_NAME, null, null)
            sqLiteDatabase.delete(Config.TABLE_REQUEST, null, null);

            count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_REQUEST);


        } catch (SQLiteException e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return count;
    }

    public boolean deleteAllabour() {
        boolean deleteStatus = false;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        try {
            //for "1" delete() method returns number of deleted rows
            //if you don't want row count just use delete(TABLE_NAME, null, null)
            sqLiteDatabase.delete(Config.TABLE_LABOUR_REQUEST, null, null);

            long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_LABOUR_REQUEST);

            if (count == 0)
                deleteStatus = true;

        } catch (SQLiteException e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }
        return deleteStatus;
    }


    public boolean deleteAllabourDetails() {
        boolean deleteStatus = false;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        try {
            //for "1" delete() method returns number of deleted rows
            //if you don't want row count just use delete(TABLE_NAME, null, null)
            sqLiteDatabase.delete(Config.TABLE_LABOUR_LEADER_XRef, null, null);

            long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_LABOUR_LEADER_XRef);

            if (count == 0)
                deleteStatus = true;

        } catch (SQLiteException e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }
        return deleteStatus;
    }

    public boolean deleteAllServices() {
        boolean deleteStatus = false;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        try {
            //for "1" delete() method returns number of deleted rows
            //if you don't want row count just use delete(TABLE_NAME, null, null)
            sqLiteDatabase.delete(Config.TABLE_LABOUR_SERVICES, null, null);

            long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_LABOUR_SERVICES);

            if (count == 0)
                deleteStatus = true;

        } catch (SQLiteException e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }
        return deleteStatus;
    }

    public boolean deleteAllFormerDetails() {
        boolean deleteStatus = false;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        try {
            //for "1" delete() method returns number of deleted rows
            //if you don't want row count just use delete(TABLE_NAME, null, null)
            sqLiteDatabase.delete(Config.TABLE_FORMER_TABLE, null, null);

            long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_FORMER_TABLE);

            if (count == 0)
                deleteStatus = true;

        } catch (SQLiteException e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }
        return deleteStatus;
    }


    public int getRequestCount() {
       // String countQuery = "SELECT  * FROM " + Config.TABLE_REQUEST;
        String countQuery = "SELECT  * FROM " + Config.TABLE_REQUEST + " WHERE StatusTypeId != 40";
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getPendingRequestCount() {
        String countQuery = "SELECT  * FROM " + Config.TABLE_REQUEST + " WHERE ServerUpdatedStatus = 1";
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getJobDoneRequestCount() {
        String countQuery = "SELECT  * FROM " + Config.TABLE_REQUEST + " WHERE ServerUpdatedStatus = 0";
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getlabourCount() {
        String countQuery = "SELECT  * FROM " + Config.TABLE_LABOUR_REQUEST;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getServiceCount() {
        String countQuery = "SELECT  * FROM " + Config.TABLE_LABOUR_SERVICES;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getFormerCount() {
        String countQuery = "SELECT  * FROM " + Config.TABLE_FORMER_TABLE;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public boolean updateTressCount(int labourID,int TressCount,String requestCode, int updatedbyuserID) {
       // String countQuery = "UPDATE LabourLeaderXref2 set TreesCount ="+TressCount+" where id ="+labourID;
//        String countQuery = "UPDATE LabourLeaderXref2 set TreesCount = "+TressCount+" where requestCode ="+"'"+requestCode +"'"  +" and LabourId = "+labourID;
//        Log.d("updateTressCount","--- :"+countQuery);
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_LABOUR_LEADER_XRef_TREESCOUNT,TressCount);
        contentValues.put(Config.COLUMN_LABOUR_LEADER_XRef_UPDATEDBYUSERID,updatedbyuserID);
        contentValues.put(Config.COLUMN_LABOUR_LEADER_XRef_UPDATEDDATE, DateTimeUtil.onGetCurrentDate(context));

        sqLiteDatabase.update(Config.TABLE_LABOUR_LEADER_XRef,
                contentValues,
                Config.COLUMN_LABOUR_LEADER_XRef_REQUESTCODE + " = ? AND " + Config.COLUMN_LABOUR_LEADER_XRef_LABOURID + " = ?"+ "AND " + Config.COLUMN_LABOUR_LEADER_XRef_ISACTIVE + " = ?",
                new String[]{requestCode, labourID+"","1"});
        return true;
//        sqLiteDatabase.update(Config.TABLE_LABOUR_LEADER_XRef,Config.COLUMN_LABOUR_LEADER_XRef_TREESCOUNT,)
//        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
//
//        cursor.close();

    }

    public List<RequestCompleteModel> getAllRequests() {
        String query = "SELECT \n" +
                "    RequestHeader.requestCode,LabourRequest.StartDate,LabourRequest.CreatedDate,LabourRequest.UpdatedDate,LabourRequest.JobDoneDate,RequestHeader.StatusTypeId,\n" +
                "\tRequestHeader.yearofPlanting,LabourRequest.NetWeight,RequestHeader.PalmArea,LabourRequest.TreesCount,RequestHeader.FarmerName,FarmerDetails.mobileNumber,FarmerDetails.contactNumber,\n" +
                "\tFarmerDetails.landmark,FarmerDetails.latitude,FarmerDetails.longitude,RequestHeader.PlotVillage ,LabourRequest.expectedNetWeight,LabourRequest.expectedBunches\n" +
                "FROM \n" +
                "    RequestHeader\n" +
                "INNER JOIN LabourRequest \n" +
                "    ON LabourRequest.RequestCode = RequestHeader.requestCode\n" +
                "\t INNER JOIN  FarmerDetails ON FarmerDetails.requestCode = LabourRequest.RequestCode";

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            Log.d("SQL", "getAllRequests :" + query);
            cursor = sqLiteDatabase.rawQuery(query, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<RequestCompleteModel> requestCompleteModels = new ArrayList<>();
                    do {

                        String RequestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQUESTCODE));
                        String StartDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_STARTDATE));
                        String CreatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_CREATED_DATE));
                        String UpdatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_UPDATED_DATE));
                        String JobDoneDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_JOBDONE_DATE));
                        int StatusTypeID = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_STATUS_TYPE_ID));
                        String YearOfplanting = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQ_YEAR_OF_PLANTING));
                        String NetWeight = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_NET_WEIGHT));
                        String PalmArea = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PALM_AREA));
                        String TressCount = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_TRESS_COUNT));
                        String treesCountWithIntercrop = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_treesCountWithIntercrop));
                        Double exp_netweight = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_EXPECTED_NET_WEIGHT));
                        String expectedBunches =cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_EXPECTED_BUNCHES));
                        String Farmername = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FARMERNAME));
                        String FormerMobileNumber = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_MOBILENUMBER));
                        String FormerContactNumber = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_CONTACT_NUMBER));
                        String Landmark = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_LANDMARK));
                        String lattitude = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_LATITUDE));
                        String Longitude = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_LONGITUDE));
                        String ServiceTypes = "";
                        String PlotVilalge = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLOT_VILLAGE));


                        List<SyncLabourServicesResponse.ListResult> data = getLabouServicestByID(RequestCode);
                        String Servicesname = "Not Available";
                        StringBuilder stringBuilder = null;
                        boolean hasproning = false, hasharvesting = false;
                        if (data.size() > 0) {
                            for (int i = 0; i < data.size(); i++) {

                                if (data.get(i).getServiceTypeId() == 19) {
                                    hasproning = true;
                                } else if (data.get(i).getServiceTypeId() == 20) {
                                    hasharvesting = true;
                                }
                            }
                        }

                        if (hasproning && hasharvesting) {
                            Servicesname = "Pruning And Harvesting";
                        } else if (hasproning) {
                            Servicesname = "Pruning";
                        } else if (hasharvesting) {
                            Servicesname = "Harvesting";
                        }

                        requestCompleteModels.add(new RequestCompleteModel(RequestCode, StartDate, CreatedDate, UpdatedDate, JobDoneDate, StatusTypeID, YearOfplanting, NetWeight,
                                PalmArea, TressCount, Farmername, FormerMobileNumber, FormerContactNumber, Landmark, lattitude, Longitude,
                                ServiceTypes, PlotVilalge, Servicesname,exp_netweight,expectedBunches,treesCountWithIntercrop));


                    } while (cursor.moveToNext());

                    return requestCompleteModels;
                }
        } catch (Exception e) {
            Log.d("Database :", "Exception: (getAllRequests)" + e.getMessage());
            //Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public List<RequestCompleteModel> getRequestsSpecificDate(String startDate) {

        String query = "SELECT \n" +
                "    RequestHeader.requestCode,LabourRequest.AssignedDate,LabourRequest.CreatedDate,LabourRequest.UpdatedDate,LabourRequest.JobDoneDate,RequestHeader.StatusTypeId,\n" +
                "\tRequestHeader.yearofPlanting,LabourRequest.NetWeight,RequestHeader.PalmArea,LabourRequest.TreesCount,LabourRequest.treesCountWithIntercrop,RequestHeader.FarmerName,FarmerDetails.mobileNumber,FarmerDetails.contactNumber,\n" +
                "\tFarmerDetails.landmark,FarmerDetails.latitude,FarmerDetails.longitude,RequestHeader.PlotVillage,LabourRequest.expectedNetWeight,LabourRequest.expectedBunches\n" +
                "FROM \n" +
                "    RequestHeader\n" +
                "INNER JOIN LabourRequest \n" +
                "    ON LabourRequest.RequestCode = RequestHeader.requestCode\n" +
                "\t INNER JOIN  FarmerDetails ON FarmerDetails.requestCode = LabourRequest.RequestCode WHERE date(LabourRequest.AssignedDate) like '" + startDate + "%' "+" ORDER BY LabourRequest.AssignedDate DESC";

        return getAllRequestsBetweenDates(query);
    }

    public List<RequestCompleteModel> getRequestsBetweenDate(String startDate, String enddate) {

        String query = "SELECT \n" +
                "    RequestHeader.requestCode,LabourRequest.AssignedDate,LabourRequest.CreatedDate,LabourRequest.UpdatedDate,LabourRequest.JobDoneDate,RequestHeader.StatusTypeId,\n" +
                "\tRequestHeader.yearofPlanting,LabourRequest.NetWeight,RequestHeader.PalmArea,LabourRequest.TreesCount,LabourRequest.treesCountWithIntercrop,RequestHeader.FarmerName,FarmerDetails.mobileNumber,FarmerDetails.contactNumber,\n" +
                "\tFarmerDetails.landmark,FarmerDetails.latitude,FarmerDetails.longitude, RequestHeader.PlotVillage,LabourRequest.expectedNetWeight,LabourRequest.expectedBunches\n" +
                "FROM \n" +
                "    RequestHeader\n" +
                "INNER JOIN LabourRequest \n" +
                "    ON LabourRequest.RequestCode = RequestHeader.requestCode\n" +
                "\t INNER JOIN  FarmerDetails ON FarmerDetails.requestCode = LabourRequest.RequestCode  WHERE date(LabourRequest.AssignedDate) BETWEEN  '" + startDate + "' And '" + enddate + "'"+" ORDER BY LabourRequest.AssignedDate DESC";;

        return getAllRequestsBetweenDates(query);
    }

    public List<RequestCompleteModel> getAllRequestsBetweenDates(String DynamicQuery) {


        String query = DynamicQuery;

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {


            Log.d("SQL", "getAllRequests :" + query);
            cursor = sqLiteDatabase.rawQuery(query, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<RequestCompleteModel> requestCompleteModels = new ArrayList<>();
                    do {

                        String RequestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQUESTCODE));
                        String StartDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_ASSIGNED_DATE));
                        String CreatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_CREATED_DATE));
                        String UpdatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_UPDATED_DATE));
                        String JobDoneDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_JOBDONE_DATE));
                        int StatusTypeID = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_STATUS_TYPE_ID));
                        String YearOfplanting = cursor.getString(cursor.getColumnIndex(Config.COLUMN_REQ_YEAR_OF_PLANTING));
                        String NetWeight = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_NET_WEIGHT));
                        String PalmArea = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PALM_AREA));
                        String TressCount = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_TRESS_COUNT));
                        String treesCountWithIntercrop = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_treesCountWithIntercrop));
                        Double exp_netweight = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_EXPECTED_NET_WEIGHT));
                        String expectedBunches =cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_EXPECTED_BUNCHES));
                        String Farmername = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FARMERNAME));
                        String FormerMobileNumber = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_MOBILENUMBER));
                        String FormerContactNumber = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_CONTACT_NUMBER));
                        String Landmark = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_LANDMARK));
                        String lattitude = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_LATITUDE));
                        String Longitude = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FORMER_DETAILS_LONGITUDE));
                        String PlotVilalge = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLOT_VILLAGE));
                        String ServiceTypes = "";


                        List<SyncLabourServicesResponse.ListResult> data = getLabouServicestByID(RequestCode);
                        Log.e("data============",data+"");

                        requestCompleteModels.add(new RequestCompleteModel(RequestCode, StartDate, CreatedDate, UpdatedDate, JobDoneDate, StatusTypeID, YearOfplanting, NetWeight,
                                PalmArea, TressCount, Farmername, FormerMobileNumber, FormerContactNumber, Landmark, lattitude, Longitude,
                                ServiceTypes, PlotVilalge, CommonUtil.getServicename(data),exp_netweight,expectedBunches,treesCountWithIntercrop));
//
//                               String serviceTypes, String PlotVilalge,String Servicesname, String Exp_netweight,String Exp_bunches,String treesCountWithIntercrop

                    } while (cursor.moveToNext());

                    return requestCompleteModels;
                }
        } catch (Exception e) {
            Log.e("DataBase", "Exception:(getAllRequestsBetweenDates) " + e.getMessage());
            //  Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public void  updateLabourDetails(int _labourid, int _treesCount,Integer updateduserid)
    {
        String countQuery = "update " + Config.TABLE_LABOUR_LEADER_XRef +" set TreesCount ="+_treesCount+"UpdatedByUserId = "+updateduserid+" where id = "+_labourid +" And isActive = 1";
        Log.d("--analysis --","query : "+countQuery);
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);

        cursor.close();

    }


    public List<LabourDbModel> getLabourinfoBylabourid(String labourID) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            String query = "select * from UserInfo where id ="+labourID;
            Log.d("SQL", "getLabouServicestByID :" + query);
            cursor = sqLiteDatabase.rawQuery(query, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<LabourDbModel> labourlist = new ArrayList<>();
                    do {
                        String firstname = cursor.getString(cursor.getColumnIndex(Config.COLUMN_USERINFO_FIRSTNAME));
                        String lastname = cursor.getString(cursor.getColumnIndex(Config.COLUMN_USERINFO_LASTNAME));
                        Integer userid = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_USERINFO_ID));
                       // Integer labourid = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADER_XRef_LABOURID));

                        labourlist.add(new LabourDbModel(firstname,lastname,userid));
                    } while (cursor.moveToNext());

                    return labourlist;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
            //    Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }
    public List<SyncLabourLeaderDetailsResponse.ListResult> getRequestandLabours(String requestCodee) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            //String query = "SELECT * from LabourLeaderXref2 where requestCode= "+"'"+requestCodee +"'"  +" GROUP By LabourId ";
            String query = "SELECT * from LabourLeaderXref2 where requestCode= "+"'"+requestCodee +"'"+" AND isActive =1 "  ;
            Log.d("SQL", "getRequestandLabours :" + query);
            cursor = sqLiteDatabase.rawQuery(query, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */


            if (cursor != null)
                if (cursor.moveToFirst()) {
                    List<SyncLabourLeaderDetailsResponse.ListResult> requestinfo = new ArrayList<>();
                    do {
                        int id  = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADER_XRef_ID));
                        String requestCode = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADER_XRef_REQUESTCODE));
                        int labourId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADER_XRef_LABOURID));
                        int isActive = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADER_XRef_ISACTIVE));
                        int createdByUserId = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADER_XRef_CREATEDBYUSERID));
                        String createdDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADER_XRef__CREATEDDATE));
                        int updatedByUserId  = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADER_XRef_UPDATEDBYUSERID));
                        String updatedDate = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADER_XRef_UPDATEDDATE));
                        int treesCount = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADER_XRef_TREESCOUNT));
                        Double netWeight = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADER_XRef_NET_WEIGHT));
                        int serverUpdatedStatus = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADER_XRef_SERVERUPDATEDSTATUS));
                        int labourCount = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_LABOUR_LEADER_XRef_LABOURCOUNT));

                        requestinfo.add(new SyncLabourLeaderDetailsResponse.ListResult(id,requestCode,labourId,isActive,createdByUserId,createdDate,updatedByUserId,updatedDate,treesCount, netWeight,0,labourCount ));
                } while (cursor.moveToNext());

                    return requestinfo;
                }
        } catch (Exception e) {
//            Logger.d("Exception: " + e.getMessage());
            Log.d("DataBase", "Exception: " + e.getMessage());
            //    Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

}
