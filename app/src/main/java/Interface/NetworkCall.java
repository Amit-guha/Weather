/*
package Interface;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.Map;

import Model.CurrentWeather;
import UI.WeatherReport;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall implements MyApiService {
    CurrentWeather currentWeather;
    Context context;
    @Override
    public void getdata(Map map, SetApiKeyEventLisitiner<String> callback) {
        OpenWeatherApi openWeatherApi=ApiClient.getClient().create(OpenWeatherApi.class);
        Call<CurrentWeather> call=openWeatherApi.getWeatherCityName(map);
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (!response.isSuccessful()) {
                    Log.d("code : ", String.valueOf(response.code()));
                    return;
                }
                currentWeather=response.body();
                if(currentWeather !=null){
                    String a=currentWeather.getWeather().get(0).getDescription();
                    Toast.makeText(context, ""+a, Toast.LENGTH_SHORT).show();
                   callback.onSuccess(currentWeather.getWeather().get(0).getDescription());
                }


            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {

            }
        });
    }
}
*/
