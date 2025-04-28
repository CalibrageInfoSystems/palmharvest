package in.calibrage.palmharvest.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyncLabourLeaderDetailsResponse {

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


        public ListResult(Integer id, String requestCode, Integer labourId, Integer isActive, Integer createdByUserId, String createdDate, Integer updatedByUserId, String updatedDate, Integer treesCount, Double netWeight, Integer serverUpdatedStatus, Integer labourCount) {
            this.id = id;
            this.requestCode = requestCode;
            this.labourId = labourId;
            this.isActive = isActive;
            this.createdByUserId = createdByUserId;
            this.createdDate = createdDate;
            this.updatedByUserId = updatedByUserId;
            this.updatedDate = updatedDate;
            this.treesCount = treesCount;
            this.netWeight = netWeight;
            this.serverUpdatedStatus = serverUpdatedStatus;
            this.labourCount = labourCount;
        }

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("requestCode")
        @Expose
        private String requestCode;
        @SerializedName("labourId")
        @Expose
        private Integer labourId;
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
        @SerializedName("treesCount")
        @Expose
        private Integer treesCount;
        @SerializedName("netWeight")
        @Expose
        private Double netWeight;
        @SerializedName("serverUpdatedStatus")
        @Expose
        private Integer serverUpdatedStatus;
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

        public Integer getTreesCount() {
            return treesCount;
        }

        public void setTreesCount(Integer treesCount) {
            this.treesCount = treesCount;
        }

        public Double getNetWeight() {
            return netWeight;
        }

        public void setNetWeight(Double netWeight) {
            this.netWeight = netWeight;
        }

        public Integer getServerUpdatedStatus() {
            return serverUpdatedStatus;
        }

        public void setServerUpdatedStatus(Integer serverUpdatedStatus) {
            this.serverUpdatedStatus = serverUpdatedStatus;
        }

        public Integer getLabourCount() {
            return labourCount;
        }

        public void setLabourCount(Integer labourCount) {
            this.labourCount = labourCount;
        }



    }

}