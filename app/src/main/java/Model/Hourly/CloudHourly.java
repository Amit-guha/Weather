package Model.Hourly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CloudHourly implements Serializable
{

    @SerializedName("all")
    private Integer all;
    private final static long serialVersionUID = 8852868845898850336L;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

}

