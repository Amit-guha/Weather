package Model.Hourly;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsonparsing.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Logger;

import Utills.Aputill;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ThreeHour> {

    private Context context;
    private List<MainHourly> hourlyTempList;
    private List<Model.Hourly.List>TimeList;
    private List<WeatherHourly> IdList;

    public HourlyAdapter(Context context, List<MainHourly> hourlyTempList,List<Model.Hourly.List>TimeList
    ,List<WeatherHourly> IdList) {
        this.context = context;
        this.hourlyTempList = hourlyTempList;
        this.TimeList=TimeList;
        this.IdList=IdList;
    }

    @NonNull
    @Override
    public ThreeHour onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.hourlysimilar,parent,false);
        return new ThreeHour(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThreeHour holder, int position) {
        //Time
        Model.Hourly.List list=TimeList.get(position);
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTimeInMillis(list.getDt() * 1000L);
        holder.tvtime.setText(Aputill.getTime(calendar, context));

        //Temperature
        MainHourly temp=hourlyTempList.get(position);

        holder.tvtemp.setText(String.format(Locale.getDefault(), "%.0f°",temp.getTemp())+"C");
       // holder.tvtemp.setText(String.format(Locale.getDefault(), "\u2103",temp.getTemp()));
        //holder.tvtemp.setText((Integer)temp.getTemp());
        Log.d("Adapter", String.valueOf(temp));


        //Icon
        WeatherHourly weatherHourly=IdList.get(position);
        Aputill.setWeatherIcon(context, (AppCompatImageView) holder.mImageview,weatherHourly.getId());


    }

    @Override
    public int getItemCount() {
        return hourlyTempList.size();
    }

    public class ThreeHour extends RecyclerView.ViewHolder{

        private TextView tvtemp,tvtime;
        private ImageView mImageview;
        public ThreeHour(@NonNull View itemView) {
            super(itemView);
            tvtime=itemView.findViewById(R.id.time_text);
            tvtemp=itemView.findViewById(R.id.temperature_text);
            mImageview=itemView.findViewById(R.id.weather_image_view);
        }
    }
}
