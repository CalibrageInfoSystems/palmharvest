package in.calibrage.palmharvest.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyncLabourResponse {

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
        private Integer ownPole;

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

        public void   setExpectedNetWeight(Double expectedNetWeight) {
            this.expectedNetWeight = expectedNetWeight;
        }

        public Integer getOwnPole() {
            return ownPole;
        }

        public void setOwnPole(Integer ownPole) {
            this.ownPole = ownPole;
        }

        public ListResult(Integer id, String requestCode, String startDate, Integer durationId, Integer leaderId, Integer pin, String jobDoneDate, Integer createdByUserId, String createdDate, Integer updatedByUserId, String updatedDate, String assignedDate, Double netWeight, Double amount, Double harvestingAmount, Double pruningAmount, Double pruningWithIntercropAmount, Double harvestingWithIntercropAmount, Integer treesCount, Integer serverUpdatedStatus, String orderId, Integer treesCountWithIntercrop, Double netWeightIntercrop, Integer expectedBunches, Double expectedNetWeight, Integer ownPole) {
            this.id = id;
            this.requestCode = requestCode;
            this.startDate = startDate;
            this.durationId = durationId;
            this.leaderId = leaderId;
            this.pin = pin;
            this.jobDoneDate = jobDoneDate;
            this.createdByUserId = createdByUserId;
            this.createdDate = createdDate;
            this.updatedByUserId = updatedByUserId;
            this.updatedDate = updatedDate;
            this.assignedDate = assignedDate;
            this.netWeight = netWeight;
            this.amount = amount;
            this.harvestingAmount = harvestingAmount;
            this.pruningAmount = pruningAmount;
            this.pruningWithIntercropAmount = pruningWithIntercropAmount;
            this.harvestingWithIntercropAmount = harvestingWithIntercropAmount;
            this.treesCount = treesCount;
            this.serverUpdatedStatus = serverUpdatedStatus;
            this.orderId = orderId;
            this.treesCountWithIntercrop = treesCountWithIntercrop;
            this.netWeightIntercrop = netWeightIntercrop;
            this.expectedBunches = expectedBunches;
            this.expectedNetWeight = expectedNetWeight;
            this.ownPole = ownPole;
        }
    }

}