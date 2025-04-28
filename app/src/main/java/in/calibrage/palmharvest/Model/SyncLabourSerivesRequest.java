
package in.calibrage.palmharvest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyncLabourSerivesRequest {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("userId")
    @Expose
    private Integer userId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
