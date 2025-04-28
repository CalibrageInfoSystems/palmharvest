package in.calibrage.palmharvest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCountRequest {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("userId")
    @Expose
    private Integer userId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
