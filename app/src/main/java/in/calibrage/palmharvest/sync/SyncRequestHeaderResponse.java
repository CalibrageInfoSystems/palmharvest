package in.calibrage.palmharvest.sync;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyncRequestHeaderResponse {

    @SerializedName("listResult")
    @Expose
    private List<ListResult> listResult = null;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("affectedRecords")
    @Expose
    private Integer affectedRecords;
    @SerializedName("endUserMessage")
    @Expose
    private String endUserMessage;
    @SerializedName("validationErrors")
    @Expose
    private List<Object> validationErrors = null;
    @SerializedName("exception")
    @Expose
    private Object exception;

    public List<ListResult> getListResult() {
        return listResult;
    }

    public void setListResult(List<ListResult> listResult) {
        this.listResult = listResult;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getAffectedRecords() {
        return affectedRecords;
    }

    public void setAffectedRecords(Integer affectedRecords) {
        this.affectedRecords = affectedRecords;
    }

    public String getEndUserMessage() {
        return endUserMessage;
    }

    public void setEndUserMessage(String endUserMessage) {
        this.endUserMessage = endUserMessage;
    }

    public List<Object> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<Object> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }

    public static class ListResult {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("requestCode")
        @Expose
        private String requestCode;
        @SerializedName("farmerCode")
        @Expose
        private String farmerCode;
        @SerializedName("plotCode")
        @Expose
        private String plotCode;
        @SerializedName("reqCreatedDate")
        @Expose
        private String reqCreatedDate;
        @SerializedName("statusTypeId")
        @Expose
        private Integer statusTypeId;
        @SerializedName("isFarmerRequest")
        @Expose
        private Integer isFarmerRequest;
        @SerializedName("createdByUserId")
        @Expose
        private Object createdByUserId;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("updatedByUserId")
        @Expose
        private Integer updatedByUserId;
        @SerializedName("updatedDate")
        @Expose
        private String updatedDate;
        @SerializedName("totalCost")
        @Expose
        private Double totalCost;
        @SerializedName("comments")
        @Expose
        private String comments;
        @SerializedName("cropMaintainceDate")
        @Expose
        private String cropMaintainceDate;
        @SerializedName("requestTypeId")
        @Expose
        private Integer requestTypeId;
        @SerializedName("issueTypeId")
        @Expose
        private Integer issueTypeId;
        @SerializedName("farmerName")
        @Expose
        private String farmerName;
        @SerializedName("plotVillage")
        @Expose
        private String plotVillage;
        @SerializedName("palmArea")
        @Expose
        private Double palmArea;
        @SerializedName("serverUpdatedStatus")
        @Expose
        private Integer serverUpdatedStatus;
        @SerializedName("yearofPlanting")
        @Expose
        private String yearofPlanting;
        @SerializedName("totalCostWithoutServiceCharge")
        @Expose
        private Double totalCostWithoutServiceCharge;
        @SerializedName("serviceChargeAmount")
        @Expose
        private Double serviceChargeAmount;
        @SerializedName("parentRequestCode")
        @Expose
        private String parentRequestCode;
        @SerializedName("recoveryFarmerCode")
        @Expose
        private String recoveryFarmerCode;
        @SerializedName("clusterId")
        @Expose
        private Integer clusterId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getRequestCode() {
            return requestCode;
        }

        public void setRequestCode(String requestCode) {
            this.requestCode = requestCode;
        }

        public String getFarmerCode() {
            return farmerCode;
        }

        public void setFarmerCode(String farmerCode) {
            this.farmerCode = farmerCode;
        }

        public String getPlotCode() {
            return plotCode;
        }

        public void setPlotCode(String plotCode) {
            this.plotCode = plotCode;
        }

        public String getReqCreatedDate() {
            return reqCreatedDate;
        }

        public void setReqCreatedDate(String reqCreatedDate) {
            this.reqCreatedDate = reqCreatedDate;
        }

        public Integer getStatusTypeId() {
            return statusTypeId;
        }

        public void setStatusTypeId(Integer statusTypeId) {
            this.statusTypeId = statusTypeId;
        }

        public Integer getIsFarmerRequest() {
            return isFarmerRequest;
        }

        public void setIsFarmerRequest(Integer isFarmerRequest) {
            this.isFarmerRequest = isFarmerRequest;
        }

        public Object getCreatedByUserId() {
            return createdByUserId;
        }

        public void setCreatedByUserId(Object createdByUserId) {
            this.createdByUserId = createdByUserId;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public Integer getUpdatedByUserId() {
            return updatedByUserId;
        }

        public void setUpdatedByUserId(Integer updatedByUserId) {
            this.updatedByUserId = updatedByUserId;
        }

        public String getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(String updatedDate) {
            this.updatedDate = updatedDate;
        }

        public Double getTotalCost() {
            return totalCost;
        }

        public void setTotalCost(Double totalCost) {
            this.totalCost = totalCost;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getCropMaintainceDate() {
            return cropMaintainceDate;
        }

        public void setCropMaintainceDate(String cropMaintainceDate) {
            this.cropMaintainceDate = cropMaintainceDate;
        }

        public Integer getRequestTypeId() {
            return requestTypeId;
        }

        public void setRequestTypeId(Integer requestTypeId) {
            this.requestTypeId = requestTypeId;
        }

        public Integer getIssueTypeId() {
            return issueTypeId;
        }

        public void setIssueTypeId(Integer issueTypeId) {
            this.issueTypeId = issueTypeId;
        }

        public String getFarmerName() {
            return farmerName;
        }

        public void setFarmerName(String farmerName) {
            this.farmerName = farmerName;
        }

        public String getPlotVillage() {
            return plotVillage;
        }

        public void setPlotVillage(String plotVillage) {
            this.plotVillage = plotVillage;
        }

        public Double getPalmArea() {
            return palmArea;
        }

        public void setPalmArea(Double palmArea) {
            this.palmArea = palmArea;
        }

        public Integer getServerUpdatedStatus() {
            return serverUpdatedStatus;
        }

        public void setServerUpdatedStatus(Integer serverUpdatedStatus) {
            this.serverUpdatedStatus = serverUpdatedStatus;
        }

        public String getYearofPlanting() {
            return yearofPlanting;
        }

        public void setYearofPlanting(String yearofPlanting) {
            this.yearofPlanting = yearofPlanting;
        }

        public Double getTotalCostWithoutServiceCharge() {
            return totalCostWithoutServiceCharge;
        }

        public void setTotalCostWithoutServiceCharge(Double totalCostWithoutServiceCharge) {
            this.totalCostWithoutServiceCharge = totalCostWithoutServiceCharge;
        }

        public Double getServiceChargeAmount() {
            return serviceChargeAmount;
        }

        public void setServiceChargeAmount(Double serviceChargeAmount) {
            this.serviceChargeAmount = serviceChargeAmount;
        }

        public String getParentRequestCode() {
            return parentRequestCode;
        }

        public void setParentRequestCode(String parentRequestCode) {
            this.parentRequestCode = parentRequestCode;
        }

        public String getRecoveryFarmerCode() {
            return recoveryFarmerCode;
        }

        public void setRecoveryFarmerCode(String recoveryFarmerCode) {
            this.recoveryFarmerCode = recoveryFarmerCode;
        }

        public Integer getClusterId() {
            return clusterId;
        }

        public void setClusterId(Integer clusterId) {
            this.clusterId = clusterId;
        }

        public ListResult(Integer id, String requestCode, String farmerCode, String plotCode, String reqCreatedDate, Integer statusTypeId, Integer isFarmerRequest, Object createdByUserId, String createdDate, Integer updatedByUserId, String updatedDate, Double totalCost, String comments, String cropMaintainceDate, Integer requestTypeId, Integer issueTypeId, String farmerName, String plotVillage, Double palmArea, Integer serverUpdatedStatus, String yearofPlanting, Double totalCostWithoutServiceCharge, Double serviceChargeAmount, String parentRequestCode, String recoveryFarmerCode, Integer clusterId) {
            this.id = id;
            this.requestCode = requestCode;
            this.farmerCode = farmerCode;
            this.plotCode = plotCode;
            this.reqCreatedDate = reqCreatedDate;
            this.statusTypeId = statusTypeId;
            this.isFarmerRequest = isFarmerRequest;
            this.createdByUserId = createdByUserId;
            this.createdDate = createdDate;
            this.updatedByUserId = updatedByUserId;
            this.updatedDate = updatedDate;
            this.totalCost = totalCost;
            this.comments = comments;
            this.cropMaintainceDate = cropMaintainceDate;
            this.requestTypeId = requestTypeId;
            this.issueTypeId = issueTypeId;
            this.farmerName = farmerName;
            this.plotVillage = plotVillage;
            this.palmArea = palmArea;
            this.serverUpdatedStatus = serverUpdatedStatus;
            this.yearofPlanting = yearofPlanting;
            this.totalCostWithoutServiceCharge = totalCostWithoutServiceCharge;
            this.serviceChargeAmount = serviceChargeAmount;
            this.parentRequestCode = parentRequestCode;
            this.recoveryFarmerCode = recoveryFarmerCode;
            this.clusterId = clusterId;
        }
    }

}