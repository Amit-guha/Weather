package Model.SevenDays;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsonparsing.R;

import java.util.List;
import java.util.Locale;

import Utills.Aputill;

public class WeeklyAdapter extends RecyclerView.Adapter<WeeklyAdapter.Perday> {
    private Context context;
    private final List<Temp> mTemplist;
    private List<String>DayMonth;
    private List<String>DateMOnth;
    private List<Weak> SevenIdList;

    public WeeklyAdapter(Context context, List<Temp> mTemplist,List<String>DayMonth,List<String>DateMOnth
    ,List<Weak> SevenIdList) {
        this.context = context;
        this.mTemplist = mTemplist;
        this.DayMonth=DayMonth;
        this.DateMOnth=DateMOnth;
        this.SevenIdList=SevenIdList;
    }

    @NonNull
    @Override
    public Perday onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.weekly,parent,false);
        return new Perday(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Perday holder, int position) {

        Temp temp=mTemplist.get(position);
        holder.maxtemp.setText(String.format(Locale.getDefault(), "%.0f°",temp.getMax())+"C");

       // Toast.makeText(context, "herel", Toast.LENGTH_SHORT).show();
        holder.mintemp.setText(String.format(Locale.getDefault(), "%.0f°",temp.getMin())+"C");

        //icon
        Weak weak=SevenIdList.get(position);
        Aputill.setWeatherIcon(context, (AppCompatImageView) holder.mimageweekly,weak.getId());

        holder.day.setText(DayMonth.get(position));
        holder.date.setText(DateMOnth.get(position));
    }

    @Override
    public int getItemCount() {
        return mTemplist.size();
    }

    public class Perday extends RecyclerView.ViewHolder{
        private TextView date,day,maxtemp,mintemp;
        private ImageView mimageweekly;
        public Perday(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.tv_date);
            day=itemView.findViewById(R.id.tv_day);
            maxtemp=itemView.findViewById(R.id.tv_maxtemp);
            mintemp=itemView.findViewById(R.id.tv_mintemp);
            mimageweekly=itemView.findViewById(R.id.sevendays_view);
        }
    }

}
