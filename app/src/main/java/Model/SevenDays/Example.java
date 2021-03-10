package Model.SevenDays;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {
        @SerializedName("lat")
        private Float lat;
        @SerializedName("lon")
        private Float lon;
        @SerializedName("timezone")
        @Expose
        private String timezone;
        @SerializedName("timezone_offset")
        @Expose
        private Integer timezoneOffset;
        @SerializedName("daily")
        private List<Daily> daily = null;

        @SerializedName("weather")
        private List<Weak> weak;


    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setTimezoneOffset(Integer timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    public List<Daily> getDaily() {
        return daily;
    }

    public void setDaily(List<Daily> daily) {
        this.daily = daily;
    }

    public List<Weak> getWeak() {
        return weak;
    }

    public void setWeak(List<Weak> weak) {
        this.weak = weak;
    }
}
