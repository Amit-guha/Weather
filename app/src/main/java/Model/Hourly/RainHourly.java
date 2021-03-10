package Model.Hourly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RainHourly implements Serializable
{

    @SerializedName("3h")
    private Float _3h;
    private final static long serialVersionUID = -5093546695526026112L;

    public Float get3h() {
        return _3h;
    }

    public void set3h(Float _3h) {
        this._3h = _3h;
    }

}