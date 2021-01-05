package UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jsonparsing.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import Interface.OpenWeatherApi;
import Model.CurrentWeather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.String.valueOf;
import static java.util.Locale.*;

public class WeatherReport extends AppCompatActivity {
    private TextView tvreport;
    public static final String APPID = "69c6aa61a0f3e87516a4192d1666ebdd";
    CurrentWeather currentWeather;
    MaterialSearchView searchView;

    private TextSwitcher text_temp,text_desc,text_humidity,text_wind;
    OpenWeatherApi openWeatherApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_report);


        /*
        *    <TextView
        android:id="@+id/wettv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textAppearance="@style/TextAppearance.AppCompat.Medium"
        android:textStyle="bold"
        android:gravity="center"/>

        *  */

        //Add custom toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //textSwitcher
        text_temp=findViewById(R.id.temp_text_view);
        text_desc=findViewById(R.id.description_text_view);
        text_humidity=findViewById(R.id.humidity_text_view);
        text_wind=findViewById(R.id.wind_text_view);


        // tvreport = findViewById(R.id.wettv);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        openWeatherApi = retrofit.create(OpenWeatherApi.class);



        //SearchView in toolbar
        searchView =findViewById(R.id.serachview);
        searchView.setVoiceSearch(false);
        searchView.setHint("Search");
        searchView.setCursorDrawable(R.drawable.search_pic);
        searchView.setEllipsize(true);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // currentWeather = new CurrentWeather();

                getdata(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.showSearch();
                Toast.makeText(WeatherReport.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void getdata(String query) {
        Map<String, String> map = new HashMap<>();
        map.put("q", query);
        map.put("lang", "en");
        map.put("appid", "69c6aa61a0f3e87516a4192d1666ebdd");

        Call<CurrentWeather> call = openWeatherApi.getWeatherCityName(map);
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (!response.isSuccessful()) {
                    tvreport.setText(response.code());
                    return;
                }

                currentWeather = response.body();

                String content = "";
                content += "base : " + currentWeather.getBase() + "\n";
                content += "name : " + currentWeather.getName() + "\n";
                // Toast.makeText(WeatherReport.this, ""+currentWeather.getBase(), Toast.LENGTH_SHORT).show();
                content += "lat : " + currentWeather.getCoord().getLat() + "\n";
                content += "lan : " + currentWeather.getCoord().getLon() + "\n";

                content+="id : "+currentWeather.getWeather().get(0).getId()+"\n";
                content+="main : "+currentWeather.getWeather().get(0).getMain()+"\n";
                content+="description : "+currentWeather.getWeather().get(0).getDescription()+"\n";

                content+= "temp : "+currentWeather.getMain().getTemp()+"\n";
                content+= "temp_min :"+currentWeather.getMain().getTemp_min()+"\n";
                content+= "temp_max :"+currentWeather.getMain().getTemp_max()+"\n";
                content+= "Pressure :"+currentWeather.getMain().getPressure()+"\n";
                content+= "Humidity :"+currentWeather.getMain().getHumidity()+"\n";

                content+="speed :"+currentWeather.getWind().getSpeed()+"\n";
                content+="deg : "+currentWeather.getWind().getDeg()+"\n";

                Toast.makeText(WeatherReport.this, ""+String.format(Locale.getDefault(),"%.0f°",currentWeather.getMain().getTemp()), Toast.LENGTH_SHORT).show();
               // text_temp.setCurrentText(String.format(Locale.getDefault(),"%.0f°",currentWeather.getMain().getTemp()));
                // tvreport.setText(content);


            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                // tvreport.setText(t.getMessage());
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }
}