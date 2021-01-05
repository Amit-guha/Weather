package Interface;

import java.util.List;
import java.util.Map;

import Model.CurrentWeather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface OpenWeatherApi {
    String RELATIVEURL="weather";

    @GET(RELATIVEURL)
    Call<CurrentWeather>getWeatherCityName(@QueryMap Map<String,String> map);

}
