/*
package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsonparsing.R;

import java.util.List;

import Model.HourlyTemp;

public class HourlyItemAdapter extends RecyclerView.Adapter<HourlyItemAdapter.HourlyViewHolder> {

    private HourlyTemp hourlyTemp;
    private Context context;

    public HourlyItemAdapter(HourlyTemp hourlyTemp, Context context) {
        this.hourlyTemp = hourlyTemp;
        this.context = context;
    }


    @NonNull
    @Override
    public HourlyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hourlysimilar,parent,false);
        return new HourlyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyViewHolder holder, int position) {
        HourlyTemp temp=hourlyTempList.get(position);
        holder.textTime.setText(temp.getDT_Text());
        holder.textTemp.setText(temp.getDt());
    }

    @Override
    public int getItemCount() {
        return hourlyTempList.size();
    }

    public class HourlyViewHolder extends RecyclerView.ViewHolder{

        TextView textTime;
         TextView textTemp;

        public HourlyViewHolder(@NonNull View itemView) {
            super(itemView);

            textTime=itemView.findViewById(R.id.time_text);
            textTemp=itemView.findViewById(R.id.temperature_text);


        }
    }
}
*/
