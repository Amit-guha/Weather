package Model.Hourly;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class List implements Serializable
{

    @SerializedName("dt")
    private Integer dt;
    @SerializedName("main")
    private MainHourly main;
    @SerializedName("weather")
    private java.util.List<WeatherHourly> weather = null;
    @SerializedName("clouds")
    private CoordHourly clouds;
    @SerializedName("wind")
    private WindHourly wind;
    @SerializedName("visibility")
    private Integer visibility;
    @SerializedName("pop")
    private Float pop;
    @SerializedName("sys")
    private SysHourly sys;
    @SerializedName("dt_txt")
    private String dtTxt;
    @SerializedName("rain")
    private RainHourly rain;
    private final static long serialVersionUID = 7437863954712598959L;

    public List(Integer dt) {
        this.dt = dt;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public MainHourly getMain() {
        return main;
    }

    public void setMain(MainHourly main) {
        this.main = main;
    }

    public java.util.List<WeatherHourly> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<WeatherHourly> weather) {
        this.weather = weather;
    }

    public CoordHourly getClouds() {
        return clouds;
    }

    public void setClouds(CoordHourly clouds) {
        this.clouds = clouds;
    }

    public WindHourly getWind() {
        return wind;
    }

    public void setWind(WindHourly wind) {
        this.wind = wind;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Float getPop() {
        return pop;
    }

    public void setPop(Float pop) {
        this.pop = pop;
    }

    public SysHourly getSys() {
        return sys;
    }

    public void setSys(SysHourly sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    public RainHourly getRain() {
        return rain;
    }

    public void setRain(RainHourly rain) {
        this.rain = rain;
    }
}