package Model.Hourly;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CoordHourly implements Serializable
    {

        @SerializedName("lat")
        private Float lat;

        @SerializedName("lon")
        private Float lon;
        private final static long serialVersionUID = 6839666351481655890L;

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

    }

