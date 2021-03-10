package Model.SevenDays;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

    public class Daily {

        @SerializedName("dt")
        @Expose
        private Integer dt;
        @SerializedName("sunrise")
        @Expose
        private Integer sunrise;
        @SerializedName("sunset")
        @Expose
        private Integer sunset;
        @SerializedName("temp")
        @Expose
        private Temp temp;
        @SerializedName("feels_like")
        @Expose
        private Feelslike feelsLike;
        @SerializedName("pressure")
        @Expose
        private Integer pressure;
        @SerializedName("humidity")
        @Expose
        private Integer humidity;
        @SerializedName("dew_point")
        @Expose
        private Float dewPoint;
        @SerializedName("wind_speed")
        @Expose
        private Float windSpeed;
        @SerializedName("wind_deg")
        @Expose
        private Integer windDeg;

        @SerializedName("weather")
        private List<Weak> weather = null;
        @SerializedName("clouds")
        @Expose
        private Integer clouds;
        @SerializedName("pop")
        @Expose
        private Float pop;
        @SerializedName("rain")
        @Expose
        private Float rain;
        @SerializedName("uvi")
        @Expose
        private Float uvi;

        public Integer getDt() {
            return dt;
        }

        public void setDt(Integer dt) {
            this.dt = dt;
        }

        public Integer getSunrise() {
            return sunrise;
        }

        public void setSunrise(Integer sunrise) {
            this.sunrise = sunrise;
        }

        public Integer getSunset() {
            return sunset;
        }

        public void setSunset(Integer sunset) {
            this.sunset = sunset;
        }

        public Temp getTemp() {
            return temp;
        }

        public void setTemp(Temp temp) {
            this.temp = temp;
        }

        public Feelslike getFeelsLike() {
            return feelsLike;
        }

        public void setFeelsLike(Feelslike feelsLike) {
            this.feelsLike = feelsLike;
        }

        public Integer getPressure() {
            return pressure;
        }

        public void setPressure(Integer pressure) {
            this.pressure = pressure;
        }

        public Integer getHumidity() {
            return humidity;
        }

        public void setHumidity(Integer humidity) {
            this.humidity = humidity;
        }

        public Float getDewPoint() {
            return dewPoint;
        }

        public void setDewPoint(Float dewPoint) {
            this.dewPoint = dewPoint;
        }

        public Float getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(Float windSpeed) {
            this.windSpeed = windSpeed;
        }

        public Integer getWindDeg() {
            return windDeg;
        }

        public void setWindDeg(Integer windDeg) {
            this.windDeg = windDeg;
        }

        public List<Weak> getWeather() {
            return weather;
        }

        public void setWeather(List<Weak> weather) {
            this.weather = weather;
        }

        public Integer getClouds() {
            return clouds;
        }

        public void setClouds(Integer clouds) {
            this.clouds = clouds;
        }

        public Float getPop() {
            return pop;
        }

        public void setPop(Float pop) {
            this.pop = pop;
        }

        public Float getRain() {
            return rain;
        }

        public void setRain(Float rain) {
            this.rain = rain;
        }

        public Float getUvi() {
            return uvi;
        }

        public void setUvi(Float uvi) {
            this.uvi = uvi;
        }
    }
