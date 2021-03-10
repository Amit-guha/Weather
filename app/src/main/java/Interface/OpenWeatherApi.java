package Interface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Model.CurrentWeather;
import Model.Hourly.HourlyTemp;
import Model.SevenDays.Daily;
import Model.SevenDays.Example;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface OpenWeatherApi {
    String RELATIVEURL="weather";

    @GET(RELATIVEURL)
    Call<CurrentWeather>getWeatherCityName(@QueryMap Map<String,String> map);

    @GET("forecast")
    Call<HourlyTemp>getHourlyStatus(@QueryMap Map<String,String>map);
    //

    @GET("onecall")
    Call<Example>getSevenDaysStatus(@Query("lat") double latitude,
                                    @Query("lon") double longitude,
                                    @Query("exclude")String [] all,
                                    @Query("units") String unit,
                                    @Query("appid")String Appid);


     //Call<Example>getSevenDaysStatus(@QueryMap Map<String,Object>map);

}
