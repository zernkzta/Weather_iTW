package com.example.weather_itw.customview;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ProgressBar;

import androidx.core.content.ContextCompat;

import com.example.weather_itw.R;


public class VerticalProgressBar extends ProgressBar {
    private int x, y, z, w;
    private int[] color = {R.color.L1,R.color.L2,R.color.L3,R.color.L4,R.color.L5,R.color.L6};
    private int L1 = 0,L2=1,L3=2,L4=3,L5=4,L6=5;
    @Override
    protected void drawableStateChanged() {
        // TODO Auto-generated method stub
        super.drawableStateChanged();
    }

    public VerticalProgressBar(Context context) {
        super(context);
    }

    public VerticalProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public VerticalProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
        this.x = w;
        this.y = h;
        this.z = oldw;
        this.w = oldh;
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec,
                                          int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    protected void onDraw(Canvas c) {
        c.rotate(-90);
        c.translate(-getHeight(), 0);
        super.onDraw(c);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                setSelected(true);
                setPressed(true);
                break;
            case MotionEvent.ACTION_MOVE:
                setProgress(getMax()
                        - (int) (getMax() * event.getY() / getHeight()));
                onSizeChanged(getWidth(), getHeight(), 0, 0);

                break;
            case MotionEvent.ACTION_UP:
                setSelected(false);
                setPressed(false);
                break;

            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    @Override
    public synchronized void setProgress(int progress) {

        if (progress >= 0)
            super.setProgress(progress);

        else
            super.setProgress(0);
        onSizeChanged(x, y, z, w);

    }
    public void setProgressColor(int level){
        this.setProgressTintList(ContextCompat.getColorStateList(getContext(),color[level]));
    }
    @SuppressLint("ResourceAsColor")
    public synchronized void setO3_Progress(String O3){

        if(O3.equals("N/A")){
            super.setProgress(0);
            setProgressColor(L1);
            return;
        }
        else {
            float tmp_O3 = Float.parseFloat(O3);
            float tmp = tmp_O3 / 604f;
            if (tmp_O3 >= 0 && tmp_O3 < 55) {
                setProgressColor(L1);
            } else if (tmp_O3 >= 55 && tmp_O3 < 125) {
                setProgressColor(L2);
            } else if (tmp_O3 >= 125 && tmp_O3 < 165) {
                setProgressColor(L3);
            } else if (tmp_O3 >= 165 && tmp_O3 < 205) {
                setProgressColor(L4);
            } else if (tmp_O3 >= 205 && tmp_O3 < 405) {
                setProgressColor(L5);
            } else if (tmp_O3 >= 405 && tmp_O3 < 605) {
                setProgressColor(L6);
            }
            int result = (int) (tmp * 100);
            //Log.d("AQI","tmp str = "+result);
            //Log.d("AQI","color value = "+color[0]);
            super.setProgress(result);
            onSizeChanged(x, y, z, w);
        }
    }
    public synchronized void setPM25_Progress(String pm25){
        if(pm25.equals("N/A")){
            super.setProgress(0);
            setProgressColor(L1);
            return;
        }
        else {
            float tmp_pm25 = Float.parseFloat(pm25);
            float tmp = tmp_pm25 / 500.4f;
            if (tmp_pm25 >= 0 && tmp_pm25 < 15.5) {
                setProgressColor(L1);
            } else if (tmp_pm25 >= 15.5 && tmp_pm25 < 35.5) {
                setProgressColor(L2);
            } else if (tmp_pm25 >= 35.5 && tmp_pm25 < 54.5) {
                setProgressColor(L3);
            } else if (tmp_pm25 >= 54.5 && tmp_pm25 < 150.5) {
                setProgressColor(L4);
            } else if (tmp_pm25 >= 150.5 && tmp_pm25 < 205.5) {
                setProgressColor(L5);
            } else if (tmp_pm25 >= 250.5 && tmp_pm25 < 350.5) {
                setProgressColor(L6);
            } else if (tmp_pm25 >= 350.5 && tmp_pm25 < 500.5) {
                setProgressColor(L6);
            }
            int result = (int) (tmp * 100);
            //Log.d("AQI","tmp str = "+result);
            //Log.d("AQI","color value = "+color[0]);
            super.setProgress(result);
            onSizeChanged(x, y, z, w);
        }
    }
    public synchronized void setPM10_Progress(String pm10){
        if(pm10.equals("N/A")){
            super.setProgress(0);
            setProgressColor(L1);
            return;
        }
        else {
            float tmp_pm10 = Float.parseFloat(pm10);
            float tmp = tmp_pm10 / 604f;
            if (tmp_pm10 >= 0 && tmp_pm10 < 51) {
                setProgressColor(L1);
            } else if (tmp_pm10 >= 51 && tmp_pm10 < 101) {
                setProgressColor(L2);
            } else if (tmp_pm10 >= 101 && tmp_pm10 < 255) {
                setProgressColor(L3);
            } else if (tmp_pm10 >= 255 && tmp_pm10 < 355) {
                setProgressColor(L4);
            } else if (tmp_pm10 >= 355 && tmp_pm10 < 425) {
                setProgressColor(L5);
            } else if (tmp_pm10 >= 425 && tmp_pm10 < 505) {
                setProgressColor(L6);
            } else if (tmp_pm10 >= 505 && tmp_pm10 < 605) {
                setProgressColor(L6);
            }
            int result = (int) (tmp * 100);
            //Log.d("AQI","tmp str = "+result);
            //Log.d("AQI","color value = "+color[0]);
            super.setProgress(result);
            onSizeChanged(x, y, z, w);
        }
    }
    public synchronized void setSO2_Progress(String so2){
        if(so2.equals("N/A")){
            super.setProgress(0);
            setProgressColor(L1);
            return;
        }
        else {
            float tmp_so2 = Float.parseFloat(so2);
            float tmp = tmp_so2 / 1004f;
            int result = (int) (tmp * 100);
            if (tmp_so2 >= 0 && tmp_so2 < 21) {
                setProgressColor(L1);
                super.setProgress(result + 3);
            } else if (tmp_so2 >= 21 && tmp_so2 < 76) {
                setProgressColor(L2);
                super.setProgress(result + 6);
            } else if (tmp_so2 >= 76 && tmp_so2 < 186) {
                setProgressColor(L3);

                if (tmp_so2 < 130) {
                    super.setProgress(result + 9);
                } else {
                    super.setProgress(result);
                }
            } else if (tmp_so2 >= 186 && tmp_so2 < 305) {
                setProgressColor(L4);
                super.setProgress(result);
            } else if (tmp_so2 >= 305 && tmp_so2 < 605) {
                setProgressColor(L5);
                super.setProgress(result);
            } else if (tmp_so2 >= 605 && tmp_so2 < 805) {
                setProgressColor(L6);
                super.setProgress(result);
            } else if (tmp_so2 >= 805 && tmp_so2 < 1005) {
                setProgressColor(L6);
                super.setProgress(result);
            }
            //Log.d("AQI","tmp str = "+result);
            //Log.d("AQI","color value = "+color[0]);
            onSizeChanged(x, y, z, w);
        }
    }

    public synchronized void setNO2_Progress(String no2){
        if(no2.equals("N/A")){
            super.setProgress(0);
            setProgressColor(L1);
            return;
        }
        else{
        float tmp_no2 = Float.parseFloat(no2);
        float tmp = tmp_no2/2049f;
        int result = (int)(tmp * 100);
        if(tmp_no2>=0 && tmp_no2<31){
            setProgressColor(L1);
            super.setProgress(result+3);
        }
        else if(tmp_no2>=31 && tmp_no2<101){
            setProgressColor(L2);
            super.setProgress(result+6);
        }
        else if(tmp_no2>=101 && tmp_no2<361){
            setProgressColor(L3);
            if(tmp_no2>250){
                super.setProgress(result+9);
            }
            else{
                super.setProgress(result);
            }

        }
        else if(tmp_no2>=361 && tmp_no2<650){
            setProgressColor(L4);
            super.setProgress(result);
        }
        else if(tmp_no2>=650 && tmp_no2<1250){
            setProgressColor(L5);
            super.setProgress(result);
        }
        else if(tmp_no2>=1250 && tmp_no2<1650){
            setProgressColor(L6);
            super.setProgress(result);
        }
        else if(tmp_no2>=1650 && tmp_no2<2050){
            setProgressColor(L6);
            super.setProgress(result);
        }
       /* else{
            super.setProgress(0);
            setProgressColor(L1);
        }*/

        //Log.d("AQI","tmp str = "+result);
        //Log.d("AQI","color value = "+color[0]);

        onSizeChanged(x,y,z,w);
        }
    }
    public synchronized void setCO_Progress(String co){
        if(co.equals("N/A")){
            super.setProgress(0);
            setProgressColor(L1);
            return;
        }
        else {
            float tmp_co = Float.parseFloat(co);
            float tmp = tmp_co / 50.4f;
            int result = (int) (tmp * 100);
            if (tmp_co >= 0 && tmp_co < 4.5) {
                setProgressColor(L1);
                super.setProgress(result + 4);
            } else if (tmp_co >= 4.5 && tmp_co < 9.5) {
                setProgressColor(L2);
                super.setProgress(result + 9);
            } else if (tmp_co >= 9.5 && tmp_co < 12.5) {
                setProgressColor(L3);
                super.setProgress(result);
            } else if (tmp_co >= 12.5 && tmp_co < 15.5) {
                setProgressColor(L4);
                super.setProgress(result);
            } else if (tmp_co >= 15.5 && tmp_co < 30.5) {
                setProgressColor(L5);
                super.setProgress(result);
            } else if (tmp_co >= 30.5 && tmp_co < 40.5) {
                setProgressColor(L6);
                super.setProgress(result);
            } else if (tmp_co >= 40.5 && tmp_co < 50.5) {
                setProgressColor(L6);
                super.setProgress(result);
            }

            //Log.d("AQI","tmp str = "+result);
            //Log.d("AQI","color value = "+color[0]);
            onSizeChanged(x, y, z, w);
        }
    }
}
