
package in.calibrage.palmharvest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CloseLabourRequest {

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
    @SerializedName("assignedDate")
    @Expose
    private String assignedDate;
    @SerializedName("netWeight")
    @Expose
    private Double netWeight;
    @SerializedName("amount")
    @Expose
    private Object amount;
    @SerializedName("harvestingAmount")
    @Expose
    private Double harvestingAmount;
    @SerializedName("pruningAmount")
    @Expose
    private Double pruningAmount;
    @SerializedName("unKnown1Amount")
    @Expose
    private Object unKnown1Amount;
    @SerializedName("unKnown2Amount")
    @Expose
    private Object unKnown2Amount;
    @SerializedName("treesCount")
    @Expose
    private Integer treesCount;
    @SerializedName("serverUpdatedStatus")
    @Expose
    private Integer serverUpdatedStatus;

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

    public Object getAmount() {
        return amount;
    }

    public void setAmount(Object amount) {
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

    public Object getUnKnown1Amount() {
        return unKnown1Amount;
    }

    public void setUnKnown1Amount(Object unKnown1Amount) {
        this.unKnown1Amount = unKnown1Amount;
    }

    public Object getUnKnown2Amount() {
        return unKnown2Amount;
    }

    public void setUnKnown2Amount(Object unKnown2Amount) {
        this.unKnown2Amount = unKnown2Amount;
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

}
