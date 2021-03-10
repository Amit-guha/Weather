package Model.SevenDays;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feelslike {

        @SerializedName("day")
        @Expose
        private Float day;
        @SerializedName("night")
        @Expose
        private Float night;
        @SerializedName("eve")
        @Expose
        private Float eve;
        @SerializedName("morn")
        @Expose
        private Float morn;

        public Float getDay() {
            return day;
        }

        public void setDay(Float day) {
            this.day = day;
        }

        public Float getNight() {
            return night;
        }

        public void setNight(Float night) {
            this.night = night;
        }

        public Float getEve() {
            return eve;
        }

        public void setEve(Float eve) {
            this.eve = eve;
        }

        public Float getMorn() {
            return morn;
        }

        public void setMorn(Float morn) {
            this.morn = morn;
        }

    }


