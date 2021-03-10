package Model.Hourly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SysHourly implements Serializable
{

    @SerializedName("pod")
    private String pod;
    private final static long serialVersionUID = 9204794485053588513L;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

}