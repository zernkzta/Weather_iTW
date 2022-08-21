package com.example.weather_itw.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather_itw.R;
import com.example.weather_itw.Utils.ImgUtil;
import com.example.weather_itw.model.hour.WeatherElement;


import java.util.ArrayList;

public class WeekQuickListAdapter extends RecyclerView.Adapter<WeekQuickListAdapter.WeatherViewHolder> {
    private Context context;
    private ArrayList<WeatherElement> weatherElementArrayList;
    public WeekQuickListAdapter(Context context, ArrayList<WeatherElement> weatherElementArrayList) {
        this.context = context;
        this.weatherElementArrayList = weatherElementArrayList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.week_weather_recyclerview,parent,false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        String getTemp = weatherElementArrayList.get(1).getTime().get(position).getElementValue().get(0).getValue()+"℃";
        String getPop = weatherElementArrayList.get(4).getTime().get(position/2).getElementValue().get(0).getValue()+" %";
        holder.wea_img.setImageResource(ImgUtil.convertImg(weatherElementArrayList.get(0).getTime().get(position).getElementValue().get(0).getValue()));
        holder.wea_wx.setText(weatherElementArrayList.get(0).getTime().get(position).getElementValue().get(0).getValue());
        holder.wea_T.setText(getTemp);
        holder.wea_pop.setText(getPop);
        holder.wea_time.setText(convert_Date(weatherElementArrayList.get(0).getTime().get(position).getStartTime()));
    }

    @Override
    public int getItemCount() {
        return weatherElementArrayList.size();          // default:7
    }



    private String convert_Date(String getDate){        //轉換日期 原先格式YYYY-MM-DD HH:MM:SS
        int count = 0;
        String[] token = getDate.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<token[1].length();i++){          //只取後半部時間部分，HH:MM
            if(token[1].charAt(i)==':'){
                count++;
            }
            if(count>1){                                //不顯示到秒數
                break;
            }
            sb.append(token[1].charAt(i));
        }
        return sb.toString();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder{
        private final TextView wea_time;        //逐三小時天氣_時間
        private final TextView wea_wx;          //逐三小時天氣_天氣現象
        private final TextView wea_T;           //逐三小時天氣_天氣溫度
        private final TextView wea_pop;         //逐三小時天氣_降雨機率
        private final ImageView wea_img;        //逐三小時天氣_氣象圖示(依天氣現象轉換為天氣圖示)
        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            wea_time = (TextView) itemView.findViewById(R.id.wea_time);
            wea_wx = (TextView) itemView.findViewById(R.id.wea_wx);
            wea_T = (TextView)itemView.findViewById(R.id.wea_T);
            wea_pop = (TextView)itemView.findViewById(R.id.wea_pop);
            wea_img = (ImageView) itemView.findViewById(R.id.wea_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(v,getAdapterPosition());
                    }
                }
            });

        }
    }

    public interface OnItemClickListener{
        public void OnItemClick(View view,int position);
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
