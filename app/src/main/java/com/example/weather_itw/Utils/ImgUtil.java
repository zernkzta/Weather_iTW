package com.example.weather_itw.Utils;

import com.example.weather_itw.R;

public class ImgUtil {
    private static int[] img = {R.drawable.sun,R.drawable.cloud,R.drawable.rain,R.drawable.sunny,R.drawable.fog,
                                R.drawable.hail,R.drawable.rain_cloud,R.drawable.snow,R.drawable.storm,
                                R.drawable.sun_thunder,R.drawable.thunderstorm,R.drawable.thunderstorms};
    public static int convertImg(String Weather_Wx){
        if(Weather_Wx.contains("雲") && Weather_Wx.contains("晴") && (Weather_Wx.contains("雨"))){
            return img[6];
        }
        else if(Weather_Wx.contains("雲") && Weather_Wx.contains("陰") && (Weather_Wx.contains("雨"))){
            return img[2];
        }
        else if(Weather_Wx.contains("雲") && Weather_Wx.contains("陰") && (Weather_Wx.contains("雷雨"))){
            return img[11];
        }
        else if(Weather_Wx.contains("雲") && (Weather_Wx.contains("雷雨"))){
            return img[11];
        }
        else if(Weather_Wx.contains("雲") && (Weather_Wx.contains("晴"))){
            return img[3];
        }
        else if(Weather_Wx.contains("雲") && (Weather_Wx.contains("陰"))){
            return img[1];
        }
        else if(Weather_Wx.contains("雲") && (Weather_Wx.contains("雨"))){
            return img[2];
        }
        else if(Weather_Wx.contains("晴") && (Weather_Wx.contains("雨"))){
            return img[6];
        }
        else if(Weather_Wx.contains("晴") && (Weather_Wx.contains("雷雨"))){
            return img[9];
        }
        else if(Weather_Wx.contains("陰") && (Weather_Wx.contains("雨"))){
            return img[2];
        }
        else if(Weather_Wx.contains("陰") && (Weather_Wx.contains("雷雨"))){
            return img[8];
        }
        else if(Weather_Wx.contains("晴")){
            return img[0];
        }
        else if(Weather_Wx.contains("雷雨")){
            return img[8];
        }
        else if(Weather_Wx.contains("雨")){
            return img[2];
        }
        else if(Weather_Wx.contains("雷")){
            return img[8];
        }
        else if(Weather_Wx.contains("霧")){
            return img[4];
        }
        else if(Weather_Wx.contains("雪")){
            return img[7];
        }
        else if(Weather_Wx.contains("冰")){
            return img[5];
        }
        else if(Weather_Wx.contains("雲") || (Weather_Wx.contains("陰"))){
            return img[1];
        }
        /*else if(Weather_Wx.contains("陰短暫雨")){
            return img[1];
        }*/
        else{
            return img[0];
        }
    }
}
