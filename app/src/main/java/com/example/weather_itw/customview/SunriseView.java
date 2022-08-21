package com.example.weather_itw.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.weather_itw.R;

public class SunriseView extends View {
    private int mCricleRadius;
    private int mHalfCirColor;
    private int mPathCirColor;
    private int mSunCirColor;

    private ValueAnimator valueAnimator;
    private Paint mHalfCirPaint;
    private Paint mPathCirPaint;
    private Paint mSunCirPaint;

    private RectF mRectF;
    private int mWidth;
    private int mTop;
    private float mCurrentTime;
    private float mAngle;
    private String start_time,end_time;
   // private final static String START_TIME = "05:35";
   // private final static String END_TIME = "17:54";
    private final static String TAG = "SunView";

    private float mPositionX;
    private float mPositionY;
    //private final static float mIconRadius = 15;

    public SunriseView(Context context) {
        this(context, null);
    }

    public SunriseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SunriseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intView(context, attrs);
    }

    private void intView(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SunAnimationView);
        mHalfCirColor = array.getColor(R.styleable.SunAnimationView_halfcircle_color,
                ContextCompat.getColor(context, R.color.white));
        mPathCirColor = array.getColor(R.styleable.SunAnimationView_path_color,
                ContextCompat.getColor(context, R.color.BENIHI));
        mSunCirColor = array.getColor(R.styleable.SunAnimationView_sun_color,
                ContextCompat.getColor(context, R.color.BENIHI));
        mCricleRadius = array.getInteger(R.styleable.SunAnimationView_circle_radius,
                200);

        mHalfCirPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHalfCirPaint.setStyle(Paint.Style.STROKE);
        mHalfCirPaint.setAntiAlias(true);
        mHalfCirPaint.setColor(mHalfCirColor);
        mHalfCirPaint.setStrokeWidth(5);
        mHalfCirPaint.setStrokeJoin(Paint.Join.ROUND);

        mSunCirPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSunCirPaint.setStyle(Paint.Style.FILL);
        mSunCirPaint.setAntiAlias(true);
        mSunCirPaint.setColor(mSunCirColor);
        mSunCirPaint.setStrokeWidth(5);
        mSunCirPaint.setStrokeJoin(Paint.Join.ROUND);

        mPathCirPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPathCirPaint.setStyle(Paint.Style.STROKE);
        mPathCirPaint.setAntiAlias(true);
        mPathCirPaint.setColor(mPathCirColor);
        mPathCirPaint.setStrokeWidth(10);
        mPathCirPaint.setStrokeJoin(Paint.Join.ROUND);

        array.recycle();

    }

    public void setStart_Time(String start_time){
        this.start_time = start_time;
    }
    public void setEnd_Time(String end_time){
        this.end_time = end_time;
    }
    public String getStart_Time(){
        return start_time;
    }
    public String getEnd_time(){
        return end_time;
    }
    public void setTime(String time) {
    //public void setTime(String time,String start_time,String end_time) {
        mCurrentTime = transTime(time);
        int start = transTime(getStart_Time());
        int end = transTime(getEnd_time());
        mAngle = (mCurrentTime - start) / (end - start) * 180;
        Log.d(TAG, "mCurrentTime:" + mCurrentTime + " start:" + start + " end:" + end + " mAngle:" + mAngle);
        startAnimation(0, mAngle, 2500);
    }


    private void invalidateView() {
        mPositionX = mWidth / 2 - (float) (mCricleRadius * Math.cos(mAngle * Math.PI / 180));//- mIconRadius;
        mPositionY = mTop + mCricleRadius - (float) (mCricleRadius * Math.sin(mAngle * Math.PI / 180));
        Log.d(TAG, "mPositionX:" + mPositionX + " mPositionY" + mPositionY);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //畫圓
        drawCricle(canvas);
        //畫太陽走過的圓弧
        drawOld(canvas);
        //畫太陽
        drawCircle(canvas);
        canvas.save();
    }

    private void drawOld(Canvas canvas) {
        canvas.drawArc(mRectF, 180, mAngle, false, mPathCirPaint);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(mPositionX, mPositionY, 15, mSunCirPaint);
    }

    private void drawCricle(Canvas canvas) {
        mRectF = new RectF(mWidth / 2 - mCricleRadius, mTop, mWidth / 2 + mCricleRadius, 2 * mCricleRadius + mTop);
        canvas.drawArc(mRectF, 180, 180, false, mHalfCirPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth();
        mTop = getHeight() / 6;
        mPositionX = mWidth / 2 - mCricleRadius;
        mPositionY = mCricleRadius + mTop;

    }

    //將時間轉換為分鐘
    private int transTime(String time) {
        int value = 0;
        if (time == null) {
            return value;
        }
        String[] s = time.split(":");
        int hour = Integer.parseInt(s[0]);
        int minute = Integer.parseInt(s[1]);
        value = hour * 60 + minute;
        Log.d("SUN","hour = "+hour+", min = "+minute+", Time tran = "+value);
        return value;
    }

    private void startAnimation(float startAngle, float currentAngle, int duration) {
        ValueAnimator sunAnimator = ValueAnimator.ofFloat(startAngle, currentAngle);
        sunAnimator.setDuration(duration);
        sunAnimator.setTarget(currentAngle);
        sunAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //每次要繪製的圓弧角度
                mAngle = (float) animation.getAnimatedValue();
                invalidateView();
            }

        });
        sunAnimator.start();
    }
}