package com.example.jsonparsing;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import UI.WeatherReport;
import Utills.Aputill;
import Utills.Constants;

public class Clickcardview extends AppCompatActivity {
    private TextView dayNameText, MinTempText, MaxtempText, Temp, Sunrise, Sunset;
    private Typeface typeface;

    private LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickcardview);

        //Change the status bar background color
        Window w = Clickcardview.this.getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        w.setStatusBarColor(ContextCompat.getColor(Clickcardview.this, R.color.white));


        //To change the status bar Text and icon color
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        dayNameText = findViewById(R.id.day_name_text_view);
        MinTempText = findViewById(R.id.min_temp_text_view);
        MaxtempText = findViewById(R.id.max_temp_text_view);
        Temp = findViewById(R.id.temp_text_view);
        Sunrise = findViewById(R.id.tv_sunrise_time);
        Sunset = findViewById(R.id.tv_sunset_time);

        //font
        typeface = Typeface.createFromAsset(getAssets(), "fonts/Vazir.ttf");

        //Animation
        lottieAnimationView = findViewById(R.id.animation_view1);


        //Calender Full day Name
        Calendar cal = Calendar.getInstance();
        Format f = new SimpleDateFormat("EEEE");
        String str = f.format(new Date());

        // dayNameText.setText(str);
        //dayNameText.setTypeface(typeface);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            Integer dt = bundle.getInt("dt");
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            // Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.setTimeInMillis(dt * 1000L);


            if (Aputill.isRTL(this)) {
                dayNameText.setText(Constants.DAYS_OF_WEEK_PERSIAN[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
            } else {
                dayNameText.setText(Constants.DAYS_OF_WEEK[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
            }

            String temp = bundle.getString("temp");
            Temp.setText(temp + "C");
            Temp.setTypeface(typeface);

            String tempmin = bundle.getString("tempmin");
            MinTempText.setText(tempmin + "C");
            MinTempText.setTypeface(typeface);

            String tempmax = bundle.getString("tempmax");
            MaxtempText.setText(tempmax + "C");
            MaxtempText.setTypeface(typeface);

            Integer sunr = bundle.getInt("sunrise");
            // Toast.makeText(this, ""+sunr, Toast.LENGTH_SHORT).show();
            Integer sunst = bundle.getInt("sunset");


            //Sunrise
            Calendar cale = Calendar.getInstance(TimeZone.getDefault());
            cale.setTimeInMillis(sunr * 1000L);

            String datestring = Aputill.getTime(cale, this);
            Sunrise.setText(datestring);

          /*  SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
            try {
                Date date3 = sdf.parse(datestring);
                SimpleDateFormat sdf2 = new SimpleDateFormat("hh.mm aa");
                Sunrise.setText(sdf2.format(date3));
                // Toast.makeText(this, "Time : "+sdf2.format(date3), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }*/


            //Sunset
            Calendar cale2 = Calendar.getInstance(TimeZone.getDefault());
            cale2.setTimeInMillis(sunst * 1000L);

            String datestring2 = Aputill.getTime(cale2, this);
            Sunset.setText(datestring2);

         /*   Toast.makeText(this, ""+datestring+" = "+datestring2, Toast.LENGTH_SHORT).show();
            SimpleDateFormat sdfs = new SimpleDateFormat("HH.mm");
            try {
                Date date32 = sdfs.parse(datestring2);
                SimpleDateFormat sdf21 = new SimpleDateFormat("hh.mm aa");
                Sunset.setText(sdf21.format(date32));
                // Toast.makeText(this, "Time : "+sdf2.format(date3), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }*/


            int id = bundle.getInt("id");
            // int tx= Integer.valueOf(id);

            lottieAnimationView.setAnimation(Aputill.getWeatherAnimation(id));
            lottieAnimationView.playAnimation();

        }
    }

    //Sunrise and Sunset
    public static String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh.mm aa");
        return timeFormat.format(dateObject);
    }

}