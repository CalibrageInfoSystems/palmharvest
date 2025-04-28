package in.calibrage.palmharvest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class LabourLeadersync {


    public LabourLeadersync(List<RequestHeader> requestHeader, List<LabourRequest> labourRequest, List<LabourLeaderXref> labourLeaderXref) {
        this.requestHeader = requestHeader;
        this.labourRequest = labourRequest;
        this.labourLeaderXref = labourLeaderXref;
    }

    @SerializedName("requestHeader")
    @Expose
    private List<RequestHeader> requestHeader = null;
    @SerializedName("labourRequest")
    @Expose
    private List<LabourRequest> labourRequest = null;
    @SerializedName("labourLeaderXref")
    @Expose
    private List<LabourLeaderXref> labourLeaderXref = null;

    public List<RequestHeader> getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(List<RequestHeader> requestHeader) {
        this.requestHeader = requestHeader;
    }

    public List<LabourRequest> getLabourRequest() {
        return labourRequest;
    }

    public void setLabourRequest(List<LabourRequest> labourRequest) {
        this.labourRequest = labourRequest;
    }

    public List<LabourLeaderXref> getLabourLeaderXref() {
        return labourLeaderXref;
    }

    public void setLabourLeaderXref(List<LabourLeaderXref> labourLeaderXref) {
        this.labourLeaderXref = labourLeaderXref;
    }

    public static class LabourRequest {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("requestCode")
        @Expose
        private String requestCode;
        @SerializedName("startDate")
        @Expose
        private String startDate;
        @SerializedName("durationId")
        @Expose
        private Integer durationId;
        @SerializedName("leaderId")
        @Expose
        private Integer leaderId;
        @SerializedName("pin")
        @Expose
        private Integer pin;
        @SerializedName("jobDoneDate")
        @Expose
        private String jobDoneDate;
        @SerializedName("createdByUserId")
        @Expose
        private Integer createdByUserId;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("updatedByUserId")
        @Expose
        private Integer updatedByUserId;
        @SerializedName("updatedDate")
        @Expose
        private String updatedDate;
        @SerializedName("assignedDate")
        @Expose
        private String assignedDate;
        @SerializedName("netWeight")
        @Expose
        private Double netWeight;
        @SerializedName("amount")
        @Expose
        private Double amount;
        @SerializedName("harvestingAmount")
        @Expose
        private Double harvestingAmount;
        @SerializedName("pruningAmount")
        @Expose
        private Double pruningAmount;
        @SerializedName("pruningWithIntercropAmount")
        @Expose
        private Double pruningWithIntercropAmount;
        @SerializedName("harvestingWithIntercropAmount")
        @Expose
        private Double harvestingWithIntercropAmount;
        @SerializedName("treesCount")
        @Expose
        private Integer treesCount;
        @SerializedName("serverUpdatedStatus")
        @Expose
        private Integer serverUpdatedStatus;
        @SerializedName("serviceChargePercentage")
        @Expose
        private Double serviceChargePercentage;
        @SerializedName("orderId")
        @Expose
        private String orderId;
        @SerializedName("treesCountWithIntercrop")
        @Expose
        private Integer treesCountWithIntercrop;
        @SerializedName("netWeightIntercrop")
        @Expose
        private Double netWeightIntercrop;
        @SerializedName("expectedBunches")
        @Expose
        private Integer expectedBunches;
        @SerializedName("expectedNetWeight")
        @Expose
        private Double expectedNetWeight;
        @SerializedName("ownPole")
        @Expose
        private Boolean ownPole;
        @SerializedName("actualPrice")
        @Expose
        private Double actualPrice;

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

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public Integer getDurationId() {
            return durationId;
        }

        public void setDurationId(Integer durationId) {
            this.durationId = durationId;
        }

        public Integer getLeaderId() {
            return leaderId;
        }

        public void setLeaderId(Integer leaderId) {
            this.leaderId = leaderId;
        }

        public Integer getPin() {
            return pin;
        }

        public void setPin(Integer pin) {
            this.pin = pin;
        }

        public String getJobDoneDate() {
            return jobDoneDate;
        }

        public void setJobDoneDate(String jobDoneDate) {
            this.jobDoneDate = jobDoneDate;
        }

        public Integer getCreatedByUserId() {
            return createdByUserId;
        }

        public void setCreatedByUserId(Integer createdByUserId) {
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

        public String getAssignedDate() {
            return assignedDate;
        }

        public void setAssignedDate(String assignedDate) {
            this.assignedDate = assignedDate;
        }

        public Double getNetWeight() {
            return netWeight;
        }

        public void setNetWeight(Double netWeight) {
            this.netWeight = netWeight;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Double getHarvestingAmount() {
            return harvestingAmount;
        }

        public void setHarvestingAmount(Double harvestingAmount) {
            this.harvestingAmount = harvestingAmount;
        }

        public Double getPruningAmount() {
            return pruningAmount;
        }

        public void setPruningAmount(Double pruningAmount) {
            this.pruningAmount = pruningAmount;
        }

        public Double getPruningWithIntercropAmount() {
            return pruningWithIntercropAmount;
        }

        public void setPruningWithIntercropAmount(Double pruningWithIntercropAmount) {
            this.pruningWithIntercropAmount = pruningWithIntercropAmount;
        }

        public Double getHarvestingWithIntercropAmount() {
            return harvestingWithIntercropAmount;
        }

        public void setHarvestingWithIntercropAmount(Double harvestingWithIntercropAmount) {
            this.harvestingWithIntercropAmount = harvestingWithIntercropAmount;
        }

        public Integer getTreesCount() {
            return treesCount;
        }

        public void setTreesCount(Integer treesCount) {
            this.treesCount = treesCount;
        }

        public Integer getServerUpdatedStatus() {
            return serverUpdatedStatus;
        }

        public void setServerUpdatedStatus(Integer serverUpdatedStatus) {
            this.serverUpdatedStatus = serverUpdatedStatus;
        }

        public Double getServiceChargePercentage() {
            return serviceChargePercentage;
        }

        public void setServiceChargePercentage(Double serviceChargePercentage) {
            this.serviceChargePercentage = serviceChargePercentage;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public Integer getTreesCountWithIntercrop() {
            return treesCountWithIntercrop;
        }

        public void setTreesCountWithIntercrop(Integer treesCountWithIntercrop) {
            this.treesCountWithIntercrop = treesCountWithIntercrop;
        }

        public Double getNetWeightIntercrop() {
            return netWeightIntercrop;
        }

        public void setNetWeightIntercrop(Double netWeightIntercrop) {
            this.netWeightIntercrop = netWeightIntercrop;
        }

        public Integer getExpectedBunches() {
            return expectedBunches;
        }

        public void setExpectedBunches(Integer expectedBunches) {
            this.expectedBunches = expectedBunches;
        }

        public Double getExpectedNetWeight() {
            return expectedNetWeight;
        }

        public void setExpectedNetWeight(Double expectedNetWeight) {
            this.expectedNetWeight = expectedNetWeight;
        }

        public Boolean getOwnPole() {
            return ownPole;
        }

        public void setOwnPole(Boolean ownPole) {
            this.ownPole = ownPole;
        }

        public Double getActualPrice() {
            return actualPrice;
        }

        public void setActualPrice(Double actualPrice) {
            this.actualPrice = actualPrice;
        }
    }

    public static class RequestHeader {

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
        private Integer createdByUserId;
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

        public Integer getCreatedByUserId() {
            return createdByUserId;
        }

        public void setCreatedByUserId(Integer createdByUserId) {
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

    }

    public static class LabourLeaderXref {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("requestCode")
        @Expose
        private String requestCode;
        @SerializedName("labourId")
        @Expose
        private Integer labourId;
        @SerializedName("treesCount")
        @Expose
        private Integer treesCount;
        @SerializedName("isActive")
        @Expose
        private Integer isActive;
        @SerializedName("createdByUserId")
        @Expose
        private Integer createdByUserId;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("updatedByUserId")
        @Expose
        private Integer updatedByUserId;
        @SerializedName("updatedDate")
        @Expose
        private String updatedDate;
        @SerializedName("serverUpdatedStatus")
        @Expose
        private Integer serverUpdatedStatus;
        @SerializedName("netWeight")
        @Expose
        private Double netWeight;
        @SerializedName("labourCount")
        @Expose
        private Integer labourCount;

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

        public Integer getLabourId() {
            return labourId;
        }

        public void setLabourId(Integer labourId) {
            this.labourId = labourId;
        }

        public Integer getTreesCount() {
            return treesCount;
        }

        public void setTreesCount(Integer treesCount) {
            this.treesCount = treesCount;
        }

        public Integer getIsActive() {
            return isActive;
        }

        public void setIsActive(Integer isActive) {
            this.isActive = isActive;
        }

        public Integer getCreatedByUserId() {
            return createdByUserId;
        }

        public void setCreatedByUserId(Integer createdByUserId) {
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

        public Integer getServerUpdatedStatus() {
            return serverUpdatedStatus;
        }

        public void setServerUpdatedStatus(Integer serverUpdatedStatus) {
            this.serverUpdatedStatus = serverUpdatedStatus;
        }

        public Double getNetWeight() {
            return netWeight;
        }

        public void setNetWeight(Double netWeight) {
            this.netWeight = netWeight;
        }

        public Integer getLabourCount() {
            return labourCount;
        }

        public void setLabourCount(Integer labourCount) {
            this.labourCount = labourCount;
        }
    }
}