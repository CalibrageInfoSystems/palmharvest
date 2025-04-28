package in.calibrage.palmharvest.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyncFarmerDetailsResponse {

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

        @SerializedName("requestCode")
        @Expose
        private String requestCode;
        @SerializedName("farmerCode")
        @Expose
        private String farmerCode;
        @SerializedName("plotCode")
        @Expose
        private String plotCode;
        @SerializedName("latitude")
        @Expose
        private Double latitude;
        @SerializedName("longitude")
        @Expose
        private Double longitude;
        @SerializedName("mobileNumber")
        @Expose
        private Object mobileNumber;
        @SerializedName("contactNumber")
        @Expose
        private String contactNumber;
        @SerializedName("landmark")
        @Expose
        private String landmark;

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

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Object getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(Object mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }

        public ListResult(String requestCode, String farmerCode, String plotCode, Double latitude, Double longitude, Object mobileNumber, String contactNumber, String landmark) {
            this.requestCode = requestCode;
            this.farmerCode = farmerCode;
            this.plotCode = plotCode;
            this.latitude = latitude;
            this.longitude = longitude;
            this.mobileNumber = mobileNumber;
            this.contactNumber = contactNumber;
            this.landmark = landmark;
        }
    }

    public SyncFarmerDetailsResponse(List<ListResult> listResult, Boolean isSuccess, Integer affectedRecords, String endUserMessage, List<Object> validationErrors, Object exception) {
        this.listResult = listResult;
        this.isSuccess = isSuccess;
        this.affectedRecords = affectedRecords;
        this.endUserMessage = endUserMessage;
        this.validationErrors = validationErrors;
        this.exception = exception;
    }
}
