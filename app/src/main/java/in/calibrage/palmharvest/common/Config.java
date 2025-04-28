package in.calibrage.palmharvest.common;

public class Config {
    public static final String TAG = Config.class.getSimpleName();
    public static final String DATABASE_NAME = "harvesting-db";

    //column names of Request table
    /**
     * REQUEST TABLE
     */
    public static final String TABLE_REQUEST = "RequestHeader";
    public static final String COLUMN_REQUEST_ID = "id";//int
    public static final String COLUMN_REQUESTCODE = "RequestCode";
    public static final String COLUMN_FORMERCODE = "FarmerCode";
    public static final String COLUMN_PLATCODE = "PlotCode";
    public static final String COLUMN_REQ_CREATEDDATE = "ReqCreatedDate";
    public static final String COLUMN_STATUS_TYPE_ID = "StatusTypeId";
    public static final String COLUMN_IS_FORMER_REQUEST = "IsFarmerRequest";
    public static final String COLUMN_CREATED_BY_USERID = "CreatedByUserId";
    public static final String COLUMN_CREATED_DATE = "CreatedDate";
    public static final String COLUMN_UPDATED_BY_USERID = "UpdatedByUserId";
    public static final String COLUMN_UPDATED_DATE = "UpdatedDate";// Date TIME
    public static final String COLUMN_TotalCoast = "TotalCost"; // Flot
    public static final String COLUMN_COMMENTS = "Comments";//varchar
    public static final String COLUMN_CROPMAINTANCE_DATE = "CropMaintainceDate";// DATE TIME
    public static final String COLUMN_REQUEST_TYPE_ID = "RequestTypeId"; //int
    public static final String COLUMN_ISSUE_TYPE_ID = "IssueTypeId";//int
    public static final String COLUMN_FARMERNAME = "FarmerName";//varchar
    public static final String COLUMN_PLOT_VILLAGE = "PlotVillage";//varchar
    public static final String COLUMN_PALM_AREA = "PalmArea";// Flot
    public static final String COLUMN_REQ_SERVERUPDATED_STATUS = "ServerUpdatedStatus";//Bool
    public static final String COLUMN_REQ_YEAR_OF_PLANTING = "yearofPlanting";//Bool
    public static final String COLUMN_TOTAL_COST_WITHOUT_SERVICE_CHARGE = "ToalCostWithoutServiceCharge";//Float
    public static final String COLUMN_SERCVICE_CHARGE_AMOUNT = "ServiceChargeAmount";//Float
    public static final String COLUMN_PARENT_REQUEST_CODE = "ParentRequestCode";// varchar
    public static final String COLUMN_RECOVERY_FARMER_CODE = "RecoveryFarmerCode";//varchar
    public static final String COLUMN_CLUSTER_ID = "ClusterId";//Int



    /**
     * LABOUR REQUEST
     */
    public static final String TABLE_LABOUR_REQUEST = "LabourRequest";
    public static final String COLUMN_LABOUR_ID = "Id";//int
    public static final String COLUMN_LABOUR_REQUEST_CODE = "RequestCode";//int
    public static final String COLUMN_LABOUR_STARTDATE = "StartDate";//int
    public static final String COLUMN_LABOUR_DURATION = "DurationId";//int
    public static final String COLUMN_LABOUR_LEADERID = "LeaderId";//int
    public static final String COLUMN_LABOUR_PIN = "Pin";//int
    public static final String COLUMN_LABOUR_JOBDONE_DATE = "JobDoneDate";//int
    public static final String COLUMN_LABOUR_CREATED_BY_USER_ID = "CreatedByUserId";//int
    public static final String COLUMN_LABOUR_CREATED_DATE = "CreatedDate";//int
    public static final String COLUMN_LABOUR_UPDATED_BY_USER_ID = "UpdatedByUserId";//int
    public static final String COLUMN_LABOUR_UPDATED_DATE = "UpdatedDate";//int
    public static final String COLUMN_LABOUR_ASSIGNED_DATE = "AssignedDate";//int
    public static final String COLUMN_LABOUR_NET_WEIGHT = "NetWeight";//int
    public static final String COLUMN_LABOUR_AMOUNT = "Amount";//int
    public static final String COLUMN_LABOUR_HARVESTING_AMOUNT = "HarvestingAmount";//int
    public static final String COLUMN_LABOUR_PRUNING_AMOUNT = "PruningAmount";//int
    public static final String COLUMN_LABOUR_PRUNING_WITH_INTERCROP_AMOUNT = "PruningWithIntercropAmount";//int
    public static final String COLUMN_LABOUR_HARVESTING_WITH_INTERCROP_AMOUNT = "HarvestingWithIntercropAmount";//int
    public static final String COLUMN_LABOUR_TRESS_COUNT = "TreesCount";//int
    public static final String COLUMN_LABOUR_SERVER_UPDATED_STATUS = "ServerUpdatedStatus";//int
    public static final String COLUMN_LABOUR_OderId= "OderId";//String
    public static final String COLUMN_LABOUR_treesCountWithIntercrop= "treesCountWithIntercrop";//int
    public static final String COLUMN_LABOUR_netWeightIntercrop= "netWeightIntercrop";//float
    public static final String COLUMN_LABOUR_EXPECTED_BUNCHES= "ExpectedBunches";//int
    public static final String COLUMN_LABOUR_EXPECTED_NET_WEIGHT= "ExpectedNetWeight";//float
    public static final String COLUMN_LABOUR_OWN_POLE= "OwnPole";//bool

   // OderId varchar(50)

    /*
     * @ FORWARD SYNC TABLE
     * */
    public static final String TABLE_SYNC_FORWARD = "sync_forward";
    public static final String COLUMN_SYNC_F_ID = "id";
    public static final String COLUMN_SYNC_F_Comments = "comments";
    public static final String COLUMN_SYNC_F_Sync_DATE = "Sync_date";

    /*
     * @ REVERSE SYNC TABLE
     * */
    public static final String TABLE_SYNC_REVERSE = "sync_reverse";
    public static final String COLUMN_SYNC_R_ID = "id";
    public static final String COLUMN_SYNC_R_Comments = "comments";
    public static final String COLUMN_SYNC_R_Sync_DATE = "Sync_date";

    /*
    * Services TAble LabourServiceXref
    * */
    public static final String TABLE_LABOUR_SERVICES = "LabourServiceXref";
    public static final String COLUMN_SERVICE_REQUEST_CODE = "RequestCode";
    public static final String COLUMN_SERVICE_TYPE_CDID = "TypeCdId";


    /*
    * @ FORMAR DETAILS
    * */
    public static final String TABLE_FORMER_TABLE = "FarmerDetails";
    public static final String COLUMN_FORMER_DETAILS_REQUEST_CODE = "requestCode";
    public static final String COLUMN_FORMER_DETAILS_FORMER_CODE = "farmerCode";
    public static final String COLUMN_FORMER_DETAILS_PLATCODE = "plotCode";
    public static final String COLUMN_FORMER_DETAILS_LATITUDE = "latitude";
    public static final String COLUMN_FORMER_DETAILS_LONGITUDE = "longitude";
    public static final String COLUMN_FORMER_DETAILS_MOBILENUMBER = "mobileNumber";
    public static final String COLUMN_FORMER_DETAILS_CONTACT_NUMBER = "contactNumber";
    public static final String COLUMN_FORMER_DETAILS_LANDMARK = "landmark";


    public static final String TABLE_USERINFO = "UserInfo";
    public static final String COLUMN_USERINFO_ID = "id";//int
    public static final String COLUMN_USERINFO_USERID = "UserId";
    public static final String COLUMN_USERINFO_FIRSTNAME = "FirstName";
    public static final String COLUMN_USERINFO_LASTNAME = "Lastname";
    public static final String COLUMN_USERINFO_MIDDLENAME = "MiddleName";
    public static final String COLUMN_USERINFO_CONTACTNUMBER = "ContactNumber";
    public static final String COLUMN_USERINFO_MOBILENUMBER = "MobileNumber";
    public static final String COLUMN_USERINFO_USERNAME = "UserName";
    public static final String COLUMN_USERINFO_PASSWORD = "Password";
    public static final String COLUMN_USERINFO_ROLEID = "RoleId";//int
    public static final String COLUMN_USERINFO_EMAIL = "Email";
    public static final String COLUMN_USERINFO_MANAGERID = "ManagerId";//int
    public static final String COLUMN_USERINFO_USERCODE = "UserCode";
    public static final String COLUMN_USERINFO_EMPLOYEECODE = "EmployeeCode";
    public static final String COLUMN_USERINFO_ISACTIVE = "IsActive";//Bool
    public static final String COLUMN_USERINFO_CREATEDBYUSERID = "CreatedByUserId";//int
    public static final String COLUMN_USERINFO_CREATEDDATE = "CreatedDate";//DATETIME
    public static final String COLUMN_USERINFO_UPDATEDBYUSERID = "UpdatedByUserId";//int
    public static final String COLUMN_USERINFO_UPDATEDDATE = "UpdatedDate";//DATETIME



    public static final String TABLE_LABOUR_LEADER_XRef= "LabourLeaderXref2";
    public static final String COLUMN_LABOUR_LEADER_XRef_ID = "Id";//int
    public static final String COLUMN_LABOUR_LEADER_XRef_REQUESTCODE = "RequestCode";//Varchar
    public static final String COLUMN_LABOUR_LEADER_XRef_LABOURID= "LabourId";//int
    public static final String COLUMN_LABOUR_LEADER_XRef_TREESCOUNT= "TreesCount";//int
    public static final String COLUMN_LABOUR_LEADER_XRef_ISACTIVE = "IsActive";//bool
    public static final String COLUMN_LABOUR_LEADER_XRef_CREATEDBYUSERID = "CreatedByUserId";//int
    public static final String COLUMN_LABOUR_LEADER_XRef__CREATEDDATE = "CreatedDate";//datetime
    public static final String COLUMN_LABOUR_LEADER_XRef_UPDATEDBYUSERID = "UpdatedByUserId";//int
    public static final String COLUMN_LABOUR_LEADER_XRef_UPDATEDDATE  = "UpdatedDate";//datetime
    public static final String COLUMN_LABOUR_LEADER_XRef_SERVERUPDATEDSTATUS = "ServerUpdatedStatus";//bool
    public static final String COLUMN_LABOUR_LEADER_XRef_NET_WEIGHT = "NetWeight";//float
    public static final String COLUMN_LABOUR_LEADER_XRef_LABOURCOUNT = "labourCount";//int






}
