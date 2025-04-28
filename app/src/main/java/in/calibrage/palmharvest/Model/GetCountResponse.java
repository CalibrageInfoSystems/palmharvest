package in.calibrage.palmharvest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCountResponse {

    @SerializedName("requestHeader")
    @Expose
    private RequestHeader requestHeader;
    @SerializedName("labourRequest")
    @Expose
    private LabourRequest labourRequest;
    @SerializedName("labourServiceXref")
    @Expose
    private LabourServiceXref labourServiceXref;
    @SerializedName("farmerDetails")
    @Expose
    private FarmerDetails farmerDetails;
    @SerializedName("labourLeaderXref")
    @Expose
    private LabourLeaderXref labourLeaderXref;

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    public LabourRequest getLabourRequest() {
        return labourRequest;
    }

    public void setLabourRequest(LabourRequest labourRequest) {
        this.labourRequest = labourRequest;
    }

    public LabourServiceXref getLabourServiceXref() {
        return labourServiceXref;
    }

    public void setLabourServiceXref(LabourServiceXref labourServiceXref) {
        this.labourServiceXref = labourServiceXref;
    }

    public FarmerDetails getFarmerDetails() {
        return farmerDetails;
    }

    public void setFarmerDetails(FarmerDetails farmerDetails) {
        this.farmerDetails = farmerDetails;
    }

    public LabourLeaderXref getLabourLeaderXref() {
        return labourLeaderXref;
    }

    public void setLabourLeaderXref(LabourLeaderXref labourLeaderXref) {
        this.labourLeaderXref = labourLeaderXref;
    }
    public class LabourRequest {

        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("tableName")
        @Expose
        private String tableName;
        @SerializedName("methodName")
        @Expose
        private String methodName;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

    }
    public class LabourServiceXref {

        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("tableName")
        @Expose
        private String tableName;
        @SerializedName("methodName")
        @Expose
        private String methodName;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

    }
    public class RequestHeader {

        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("tableName")
        @Expose
        private String tableName;
        @SerializedName("methodName")
        @Expose
        private String methodName;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

    }
    public class FarmerDetails {

        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("tableName")
        @Expose
        private String tableName;
        @SerializedName("methodName")
        @Expose
        private String methodName;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

    }

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("tableName")
    @Expose
    private String tableName;
    @SerializedName("methodName")
    @Expose
    private String methodName;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public class LabourLeaderXref {

        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("tableName")
        @Expose
        private String tableName;
        @SerializedName("methodName")
        @Expose
        private String methodName;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

    }

}
