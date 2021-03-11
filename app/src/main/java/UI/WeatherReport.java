package UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.jsonparsing.BuildConfig;
import com.example.jsonparsing.Clickcardview;
import com.example.jsonparsing.MyReceiver;
import com.example.jsonparsing.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;


import Interface.OpenWeatherApi;

import Model.CurrentWeather;
import Model.Hourly.HourlyAdapter;
import Model.Hourly.HourlyTemp;
import Model.Hourly.MainHourly;
import Model.Hourly.WeatherHourly;
import Model.SevenDays.Example;
import Model.SevenDays.Temp;
import Model.SevenDays.Weak;
import Model.SevenDays.WeeklyAdapter;
import Utills.Aputill;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.String.valueOf;

public class WeatherReport extends AppCompatActivity {

    CurrentWeather currentWeather;
    MaterialSearchView searchView;
    private MaterialCardView cardView;

    private TextView text_temp, text_desc, text_humidity, text_wind, tvMinMax, toolbarText;
    OpenWeatherApi openWeatherApi;

    private static final String APPID = BuildConfig.API_KEY;

    private NestedScrollView nestedScrollView;
    private RelativeLayout emptylayout;

    private LottieAnimationView lottieAnimationView;

    private Typeface typeface;

    //shimmer
    private ShimmerFrameLayout shimmerFrameLayout, hourlyShimmerFram, ShimmerSecond;

    //TextView current
    private TextView tvPresenttemp, Texthourly;


    //Hourlydata -->>Recyclerview,Arraylist,HourlyTemp
    private RecyclerView mRecyclerView;
    private List<MainHourly> hourlyTempList;
    private List<Model.Hourly.List> TimeList;
    private List<WeatherHourly> IdList;
    private List<Weak> SevenIdList;
    private HourlyTemp hourlyTemp;
    private Example example;
    private HourlyAdapter adapter;


    //sevendays view
    private RecyclerView mRecyclerviewSevendays;
    private List<Temp> mTemplist;
    private WeeklyAdapter weeklyAdapter;

    //Date
    private List<String> DayMonth;
    private List<String> DateMOnth;

    //Broadcast Receiver
    MyReceiver myReceiver = new MyReceiver();
    IntentFilter filter = new IntentFilter();


    //layout
    private LinearLayout mlayout, layoutoff, layouton, temp_current, temp_shimmer;

    //coordinatorlayout
    private CoordinatorLayout coordinatorLayout;


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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Change the status bar background color
        Window w = WeatherReport.this.getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        w.setStatusBarColor(ContextCompat.getColor(WeatherReport.this, R.color.white));


        //To change the status bar Text and icon color
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        //Logger
        Logger.addLogAdapter(new AndroidLogAdapter());

        //Broadcast Receiver
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_HEADSET_PLUG);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_OKAY);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);


        //coordinat layout
        coordinatorLayout = findViewById(R.id.coordinator_layout);

        //NestedScrollview
        nestedScrollView = findViewById(R.id.nested_scroll_view);
        emptylayout = findViewById(R.id.emtpylayout);

        //Cardview
        cardView = findViewById(R.id.todayMaterialCard);

        //font
        typeface = Typeface.createFromAsset(getAssets(), "fonts/Vazir.ttf");

        //Animation
        lottieAnimationView = findViewById(R.id.animation_view);

        //textSwitcher
        text_temp = findViewById(R.id.temp_text_view);
        text_desc = findViewById(R.id.description_text_view);
        text_humidity = findViewById(R.id.humidity_text_view);
        text_wind = findViewById(R.id.wind_text_view);
        tvMinMax = findViewById(R.id.MinandMax);
        toolbarText = findViewById(R.id.cityname);

        //Textview
        tvPresenttemp = findViewById(R.id.Presentcurrent);
        Texthourly = findViewById(R.id.MinandMax);


        //shimmer
        shimmerFrameLayout = findViewById(R.id.shimfram);
        hourlyShimmerFram = findViewById(R.id.shimframrecy);
        ShimmerSecond = findViewById(R.id.shimmerSecond);

        //RecyclerView and Adapter
        mRecyclerView = findViewById(R.id.recycler_view_hourly);
        hourlyTempList = new ArrayList<>();
        TimeList = new ArrayList<>();
        IdList = new ArrayList<>();
        SevenIdList = new ArrayList<>();
        DayMonth = new ArrayList<>();
        DateMOnth = new ArrayList<>();

        //Recyclerview with sevendays
        mRecyclerviewSevendays = findViewById(R.id.recyclerview_sevendays);
        mTemplist = new ArrayList<>();

        //Linerlayot
        mlayout = findViewById(R.id.Linersecond);
        layoutoff = findViewById(R.id.sevenlayouthide);
        layouton = findViewById(R.id.sevenlayouton);
        temp_current = findViewById(R.id.temp_current);
        temp_shimmer = findViewById(R.id.temp_shimmer);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        openWeatherApi = retrofit.create(OpenWeatherApi.class);

        //SearchView in toolbar
        searchView = findViewById(R.id.serachview);
        searchView.setVoiceSearch(false);
        searchView.setHint("Home District");
        searchView.setCursorDrawable(R.drawable.search_pic);
        searchView.setEllipsize(true);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onQueryTextSubmit(String query) {
                // currentWeather = new CurrentWeather();
                //WeatherReport.this.registerReceiver(myReceiver,filter);


                getdata(query);
                getHourly(query);

              /*  if(isNetworkConnected()==true){
                    getdata(query);
                    getHourly(query);
                }else{
                    Toast.makeText(WeatherReport.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
*/
                // SevendaysWeather(51.5085, -0.1257);

                // ServiceWeather(query);
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
                // Toast.makeText(WeatherReport.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getHourly(String query) {
        Map<String, String> map = new HashMap<>();
        map.put("q", query);
        map.put("lang", "en");
        map.put("units", "metric");
        map.put("appid", APPID);


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(WeatherReport.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);


        Call<HourlyTemp> call = openWeatherApi.getHourlyStatus(map);

        call.enqueue(new Callback<HourlyTemp>() {
            @Override
            public void onResponse(Call<HourlyTemp> call, Response<HourlyTemp> response) {
                hourlyTemp = response.body();
                if (response.isSuccessful()) {

                    hourlyTempList.clear();
                    TimeList.clear();
                    IdList.clear();


                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate = formatter.format(date);

                    for (Integer i = 0; i < hourlyTemp.getList().size(); i++) {

                        //For time
                        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                        calendar.setTimeInMillis(hourlyTemp.getList().get(i).getDt() * 1000L);

                        Float temp = (hourlyTemp.getList().get(i).getMain().getTemp());
                        String check = hourlyTemp.getList().get(i).getDtTxt();
                        Integer currenttime = hourlyTemp.getList().get(i).getDt();

                        Integer Id = hourlyTemp.getList().get(i).getWeather().get(0).getId();


                        // Logger.d(check);
                        String[] split = check.split(" ");
                        System.out.println(split[0]);

                        if ((split[0].trim()).equals(strDate.trim())) {
                            // System.out.println("Same");
                            hourlyTempList.add(new MainHourly(temp));
                            TimeList.add(new Model.Hourly.List(currenttime));
                            IdList.add(new WeatherHourly(Id));

                        } else {
                            // System.out.println("not same");

                        }

                    }

                    adapter = new HourlyAdapter(WeatherReport.this, hourlyTempList, TimeList, IdList);
                    // Toast.makeText(WeatherReport.this, "ADapter", Toast.LENGTH_SHORT).show();
                    mRecyclerView.setAdapter(adapter);
                    ShimmerSecond.stopShimmer();
                    ShimmerSecond.hideShimmer();
                    mlayout.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);

                } else {
                    //  Toast.makeText(WeatherReport.this, "Sorry!This city is not included in Openweathermapapi.org website", Toast.LENGTH_SHORT).show();
                }
              /*  LinearLayoutManager layoutManager=new
                        LinearLayoutManager(WeatherReport.this,LinearLayoutManager.HORIZONTAL,false);
                mRecyclerView.setLayoutManager(layoutManager);
*/
                // mRecyclerView.setAdapter(new HourlyItemAdapter(hourlyTemp,WeatherReport.this));

            }

            @Override
            public void onFailure(Call<HourlyTemp> call, Throwable t) {
                Logger.d(t.getMessage());
                Toast.makeText(WeatherReport.this, "Fetch : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

 /*   private void ServiceWeather(String query) {
        Map<String, String> map = new HashMap<>();
        map.put("q", query);
        map.put("lang", "en");
        map.put("units", "metric");
        map.put("appid", "69c6aa61a0f3e87516a4192d1666ebdd");

        MyApiService myApiService = new NetworkCall();
        myApiService.getdata(map, new SetApiKeyEventLisitiner<String>() {
            @Override
            public void onSuccess(String data) {
                Toast.makeText(WeatherReport.this, "" + data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(WeatherReport.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
*/

    private void getdata(String query) {
        Map<String, String> map = new HashMap<>();
        map.put("q", query);
        map.put("lang", "en");
        map.put("units", "metric");
        map.put("appid", APPID);

        Call<CurrentWeather> call = openWeatherApi.getWeatherCityName(map);
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (!response.isSuccessful()) {
                    //Toast.makeText(WeatherReport.this, "Code :" + response.code(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(WeatherReport.this, "Sorry! This city is not included in Openweathermapapi.org website", Toast.LENGTH_SHORT).show();
                    return;
                }

                currentWeather = response.body();

                String content = "";
                content += "base : " + currentWeather.getBase() + "\n";
                content += "name : " + currentWeather.getName() + "\n";
                // Toast.makeText(WeatherReport.this, ""+currentWeather.getBase(), Toast.LENGTH_SHORT).show();
                content += "lat : " + currentWeather.getCoord().getLat() + "\n";
                content += "lan : " + currentWeather.getCoord().getLon() + "\n";

                content += "id : " + currentWeather.getWeather().get(0).getId() + "\n";
                content += "main : " + currentWeather.getWeather().get(0).getMain() + "\n";
                content += "description : " + currentWeather.getWeather().get(0).getDescription() + "\n";

                content += "temp : " + currentWeather.getMain().getTemp() + "\n";
                content += "temp_min :" + currentWeather.getMain().getTemp_min() + "\n";
                content += "temp_max :" + currentWeather.getMain().getTemp_max() + "\n";
                content += "Pressure :" + currentWeather.getMain().getPressure() + "\n";
                content += "Humidity :" + currentWeather.getMain().getHumidity() + "\n";

                content += "speed :" + currentWeather.getWind().getSpeed() + "\n";
                content += "deg : " + currentWeather.getWind().getDeg() + "\n";

                if (String.valueOf(currentWeather.getMain().getTemp()) != null) {
                    //nestedScrollView.setVisibility(View.VISIBLE);
                    // emptylayout.setVisibility(View.GONE);


                    //All values in textswitcher
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.hideShimmer();

                    tvPresenttemp.setVisibility(View.VISIBLE);

                    temp_shimmer.setVisibility(View.GONE);
                    temp_current.setVisibility(View.VISIBLE);

                    if (currentWeather.getMain().getTemp() < 0 && currentWeather.getMain().getTemp() > -0.5) {
                        currentWeather.getMain().setTemp(0);
                    }

                    text_temp.setText(String.format(Locale.getDefault(), "%.0f°", currentWeather.getMain().getTemp()) + "C");
                    //text_temp.setText((int)currentWeather.getMain().getTemp()+"\u2103");
                    text_temp.setTypeface(typeface);

                    // String description = currentWeather.getWeather().get(0).getDescription();
                    // text_desc.setText(description);
                    // text_desc.setTypeface(typeface);
                    // text_humidity.setText(currentWeather.getMain().getHumidity());
                    //  text_wind.setText((int) currentWeather.getWind().getSpeed());

                    int humdity = currentWeather.getMain().getHumidity();
                    // Toast.makeText(WeatherReport.this, ""+humdity, Toast.LENGTH_SHORT).show();
                    int weatherid = currentWeather.getWeather().get(0).getId();
                    Integer dt = currentWeather.getDt();
                    Integer sunrise = currentWeather.getSys().getSunrise();
                    Integer sunset = currentWeather.getSys().getSunset();

                    text_desc.setText(Aputill.getWeatherStatus(weatherid, Aputill.isRTL(WeatherReport.this)));
                    text_desc.setTypeface(typeface);

                    text_humidity.setText("Humidity " + (String.format(Locale.getDefault(), "%d%%", humdity)));
                    text_humidity.setTypeface(typeface);

                    text_wind.setText("Wind " + (String.format(Locale.getDefault(), getResources().getString(R.string.wind_unit_label),
                            currentWeather.getWind().getSpeed())));
                    text_wind.setTypeface(typeface);

                    toolbarText.setText(currentWeather.getName() + "," + currentWeather.getSys().getCountry());


                    //cardView Lisitiner
                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Toast.makeText(WeatherReport.this, "next Activity", Toast.LENGTH_SHORT).show();
                            // Integer Iddes=example.getDaily().get(0).getWeather().get(0).getId();
                            Intent intent = new Intent(WeatherReport.this, Clickcardview.class);

                            if (currentWeather.getMain().getTemp() < 0 && currentWeather.getMain().getTemp() > -0.5) {
                                currentWeather.getMain().setTemp(0);
                            }

                            if (example.getDaily().get(0).getTemp().getMin() < 0 && example.getDaily().get(0).getTemp().getMin() > -0.5) {
                                example.getDaily().get(0).getTemp().setMin(Float.valueOf(0));
                            }

                            if (example.getDaily().get(0).getTemp().getMax() < 0 && example.getDaily().get(0).getTemp().getMax() > -0.5) {
                                example.getDaily().get(0).getTemp().setMax(Float.valueOf(0));
                            }


                            intent.putExtra("temp", String.format(Locale.getDefault(), "%.0f°", currentWeather.getMain().getTemp()));
                            intent.putExtra("tempmin", String.format(Locale.getDefault(), "%.0f°", example.getDaily().get(0).getTemp().getMin()));
                            intent.putExtra("tempmax", String.format(Locale.getDefault(), "%.0f°", example.getDaily().get(0).getTemp().getMax()));
                            intent.putExtra("id", weatherid);
                            intent.putExtra("dt", dt);
                            intent.putExtra("sunrise", sunrise);
                            intent.putExtra("sunset", sunset);
                            startActivity(intent);
                        }
                    });

                    //tvMinMax.setText(String.format(Locale.getDefault(), "%.0f°", currentWeather.getMain().getTemp_min()));

                    lottieAnimationView.setAnimation(Aputill.getWeatherAnimation(weatherid));
                    lottieAnimationView.playAnimation();

                    double latitude = currentWeather.getCoord().getLat();
                    double longitude = currentWeather.getCoord().getLon();

                    SevendaysWeather(latitude, longitude);

                    // Toast.makeText(WeatherReport.this, ""+latitude+" "+longitude, Toast.LENGTH_SHORT).show();

                } else {
                    // nestedScrollView.setVisibility(View.GONE);
                    //emptylayout.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                // tvreport.setText(t.getMessage());
            }
        });


    }

    private void SevendaysWeather(double latitude, double longitude) {
       /* Map<String, ArrayList<Object>> map = new HashMap<>();
        ArrayList<Object>list1=new ArrayList<>();
        ArrayList<Object>list2=new ArrayList<>();
        ArrayList<Object>list3=new ArrayList<>();
        ArrayList<Object>list4=new ArrayList<>();
        ArrayList<Object>list5=new ArrayList<>();

        list1.add(latitude);

        list2.add(longitude);

        list3.add("current");
        list3.add("minutely");
        list3.add("hourly");
        list3.add("alerts");



        list4.add("metric");
        list5.add(APPID);


        map.put("lat",list1);
        map.put("lon",list2);
        map.put("exclude",list3);
        map.put("units",list4);
        map.put("appid",list5);*/

     /*   Map<String,Object>newmap=new HashMap<>();
        newmap.put("lat",latitude);
        newmap.put("lon",longitude);
        newmap.put("units", "metric");
        newmap.put("appid", "69c6aa61a0f3e87516a4192d1666ebdd");
*/

        Call<Example> call = openWeatherApi.getSevenDaysStatus(latitude, longitude,
                new String[]{"current", "hourly", "alerts", "minutely"},
                "metric", APPID);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this);
        mRecyclerviewSevendays.setLayoutManager(layoutManager);
        mRecyclerviewSevendays.setHasFixedSize(true);


        try {
            call.enqueue(new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    if (response.isSuccessful()) {


                        Calendar start = Calendar.getInstance();
                        start.set(Calendar.DAY_OF_WEEK, Calendar.getInstance()
                                .getActualMinimum(Calendar.DAY_OF_MONTH));
                        Calendar end = Calendar.getInstance();
                        end.set(Calendar.DAY_OF_MONTH,
                                Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
                        end.add(Calendar.DATE, 7);

                        example = response.body();
                        mTemplist.clear();

                        for (int i = 0; i < example.getDaily().size(); i++) {
                            Float maxTemp = example.getDaily().get(i).getTemp().getMax();
                            Float minTemp = example.getDaily().get(i).getTemp().getMin();
                            Integer IdSeven = example.getDaily().get(i).getWeather().get(0).getId();
                            mTemplist.add(new Temp(maxTemp, minTemp));
                            SevenIdList.add(new Weak(IdSeven));
                        }

                        // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
                        SimpleDateFormat sdf = new SimpleDateFormat("E");
                        for (int i = 0; i <= 7; i++) {
                            Calendar calendar = new GregorianCalendar();
                            calendar.add(Calendar.DATE, i);
                            String day = sdf.format(calendar.getTime());
                            //Log.d("day ",day);
                            DayMonth.add(day);

                        }

                        SimpleDateFormat sdfe = new SimpleDateFormat("dd/MM");
                        for (int i = 0; i <= 7; i++) {
                            Calendar calendar = new GregorianCalendar();
                            calendar.add(Calendar.DATE, i);
                            String day = sdfe.format(calendar.getTime());
                            // Log.d("day ",day);
                            DateMOnth.add(day);

                        }

                        weeklyAdapter = new WeeklyAdapter(WeatherReport.this, mTemplist, DayMonth, DateMOnth, SevenIdList);
                        hourlyShimmerFram.stopShimmer();
                        hourlyShimmerFram.hideShimmer();
                        // Toast.makeText(WeatherReport.this, "Data going...", Toast.LENGTH_SHORT).show();
                        mRecyclerviewSevendays.setAdapter(weeklyAdapter);
                        layoutoff.setVisibility(View.GONE);
                        layouton.setVisibility(View.VISIBLE);


                    } else {
                        // Toast.makeText(WeatherReport.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Example> call, Throwable t) {
                    Log.d("Check", t.getMessage());
                    // tvMinMax.setText(t.getMessage());
                    Toast.makeText(WeatherReport.this, "Find issues" + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.d("Wrong", e.getLocalizedMessage().toString());
        }


    }


    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

 /*   //Check Network connection
    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isNetworkConnected(){
        ConnectivityManager cm= (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetwork() != null && cm.getActiveNetworkInfo().isConnected();
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }


   /* @Override
    protected void onDestroy() {
        super.onDestroy();
        WeatherReport.this.unregisterReceiver(myReceiver);
    }*/
}