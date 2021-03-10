package Model.Hourly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WindHourly implements Serializable
{

    @SerializedName("speed")
    private Float speed;
    @SerializedName("deg")
    private Integer deg;
    private final static long serialVersionUID = -6539755587554383425L;

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

}