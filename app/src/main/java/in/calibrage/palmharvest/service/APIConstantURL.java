package in.calibrage.palmharvest.service;

public interface APIConstantURL {

 public static  final  String LOCAL_URL="https://3fakshaya.com/api/";
//public static  final  String LOCAL_URL="http://103.241.144.240:9096/api/";
    //   public static  final String LOCAL_URL="http://3.108.135.34/3FAkshaya/API/api/"; //LIVe
//public static  final String LOCAL_URL="http://182.18.157.215/3FAkshaya/API/api/";//test local
//public static  final  String LOCAL_URL="http://183.82.111.111/3FFarmerAPI/api/";
 //public static  final  String LOCAL_URL="http://103.241.144.240:9098/api/";
        String Login = "User/Login";
        String GetCount = "Sync/GetCount";
        String post_syncLabourRequests = "Sync/SyncLabourRequests";
        String SyncRequestHeaders = "Sync/SyncRequestHeaders";
        String GetCostConfig = "CostConfig";
        String post_closeLabourRequest = "Sync/SyncTransactions";
        String post_syncLabourServices = "Sync/SyncLabourServices";
        String post_syncFarmerDetais = "Sync/SyncFarmerDetails";
        String get_syncGetMasters= "Sync/GetMasters/null";
        String post_labourLeaderLogin = "User/LabourLeaderLogin";
        String get_userOTP = "User/";
        String getMasters = "Sync/GetMasters/null";
        String post_syncLabourLeaderDetails = "Sync/SyncLabourLeaderDetails";
}


//       CREATE TABLE LabourLeaderXref(
//        Id INT IDENTITY(1,1) PRIMARY KEY,
//       RequestCode VARCHAR(50) NOT NULL FOREIGN KEY REFERENCES RequestHeader(RequestCode),
//        LabourId INT NOT NULL FOREIGN KEY REFERENCES UserInfo(Id),
//        TreesCount INT,
//        IsActive BIT,
//        CreatedByUserId INT NOT NULL FOREIGN KEY REFERENCES UserInfo(Id),
//        CreatedDate DATETIME NOT NULL
//        )
