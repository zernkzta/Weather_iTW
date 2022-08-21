package com.example.weather_itw.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.weather_itw.R;
import com.example.weather_itw.Utils.Constant;
import com.example.weather_itw.Utils.MiscUtil;
    /** Custom View
     * 引用：Github/MyLifeMyTravel/CircleProgress
     **/

public class CircleProgress extends View {
    private static final String TAG = CircleProgress.class.getSimpleName();
    private Context mContext;

    //默認大小
    private int mDefaultSize;
    //是否開啟抗鋸齒
    private boolean antiAlias;
    //繪製提示
    private TextPaint mHintPaint;
    private CharSequence mHint;
    private int mHintColor;
    private float mHintSize;
    private float mHintOffset;

    //繪製單位
    private TextPaint mUnitPaint;
    private CharSequence mUnit;
    private int mUnitColor;
    private float mUnitSize;
    private float mUnitOffset;

    //繪製數值
    private TextPaint mValuePaint;
    private float mValue;
    private float mMaxValue;
    private float mValueOffset;
    private int mPrecision;
    private String mPrecisionFormat;
    private int mValueColor;
    private float mValueSize;

    //繪製圓弧
    private Paint mArcPaint;
    private float mArcWidth;
    private float mStartAngle, mSweepAngle;
    private RectF mRectF;


    //漸層的顏色為360度，如果只顯示270，那麼則會缺失部分顏色
    private SweepGradient mSweepGradient;
    int orange = Color.parseColor("#F6AD33");
    int dark_red = Color.parseColor("#762424");
    int purple = Color.parseColor("#C233F6");
    private int[] mGradientColors = {Color.GREEN, Color.YELLOW,orange,Color.RED,purple,dark_red};


    //當前進度，[0.0f,1.0f]
    private float mPercent;
    //動畫時間
    private long mAnimTime;
    //屬性動畫
    private ValueAnimator mAnimator;

    //繪製背景圓弧
    private Paint mBgArcPaint;
    private int mBgArcColor;
    private float mBgArcWidth;

    //圓心座標，半徑
    private Point mCenterPoint;
    private float mRadius;
    private float[] pos = new float[6];
    private float mTextOffsetPercentInRadius;


    public CircleProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        mDefaultSize = MiscUtil.dipToPx(mContext, Constant.DEFAULT_SIZE);
        mAnimator = new ValueAnimator();
        mRectF = new RectF();
        mCenterPoint = new Point();
        initAttrs(attrs);
        initPaint();
        setValue(mValue);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);

        antiAlias = typedArray.getBoolean(R.styleable.CircleProgressBar_antiAlias, Constant.ANTI_ALIAS);

        mHint = typedArray.getString(R.styleable.CircleProgressBar_hint);
        mHintColor = typedArray.getColor(R.styleable.CircleProgressBar_hintColor, Color.WHITE);
        mHintSize = typedArray.getDimension(R.styleable.CircleProgressBar_hintSize, Constant.DEFAULT_HINT_SIZE);

        mValue = typedArray.getFloat(R.styleable.CircleProgressBar_value, Constant.DEFAULT_VALUE);
        mMaxValue = typedArray.getFloat(R.styleable.CircleProgressBar_maxValue, Constant.DEFAULT_MAX_VALUE);
        //內容數值精度格式
        mPrecision = typedArray.getInt(R.styleable.CircleProgressBar_precision, 0);
        mPrecisionFormat = MiscUtil.getPrecisionFormat(mPrecision);
        mValueColor = typedArray.getColor(R.styleable.CircleProgressBar_valueColor, Color.WHITE);
        mValueSize = typedArray.getDimension(R.styleable.CircleProgressBar_valueSize, Constant.DEFAULT_VALUE_SIZE);

        mUnit = typedArray.getString(R.styleable.CircleProgressBar_unit);
        mUnitColor = typedArray.getColor(R.styleable.CircleProgressBar_unitColor, Color.WHITE);
        mUnitSize = typedArray.getDimension(R.styleable.CircleProgressBar_unitSize, Constant.DEFAULT_UNIT_SIZE);

        mArcWidth = typedArray.getDimension(R.styleable.CircleProgressBar_arcWidth, Constant.DEFAULT_ARC_WIDTH);
        mStartAngle = typedArray.getFloat(R.styleable.CircleProgressBar_startAngle, Constant.DEFAULT_START_ANGLE);
        mSweepAngle = typedArray.getFloat(R.styleable.CircleProgressBar_sweepAngle, Constant.DEFAULT_SWEEP_ANGLE);

        mBgArcColor = typedArray.getColor(R.styleable.CircleProgressBar_bgArcColor, Color.WHITE);
        mBgArcWidth = typedArray.getDimension(R.styleable.CircleProgressBar_bgArcWidth, Constant.DEFAULT_ARC_WIDTH);
        mTextOffsetPercentInRadius = typedArray.getFloat(R.styleable.CircleProgressBar_textOffsetPercentInRadius, 0.33f);

        //mPercent = typedArray.getFloat(R.styleable.CircleProgressBar_percent, 0);
        mAnimTime = typedArray.getInt(R.styleable.CircleProgressBar_animTime, Constant.DEFAULT_ANIM_TIME);

        int gradientArcColors = typedArray.getResourceId(R.styleable.CircleProgressBar_arcColors, 0);
        if (gradientArcColors != 0) {
            try {
                int[] gradientColors = getResources().getIntArray(gradientArcColors);
                if (gradientColors.length == 0) {//如果漸層陣列長度為0，則以單色讀取色值
                    int color = getResources().getColor(gradientArcColors);
                    mGradientColors = new int[2];
                    mGradientColors[0] = color;
                    mGradientColors[1] = color;
                } else if (gradientColors.length == 1) {//如果漸層陣列只有一種顏色，默認設置為兩種相同顏色
                    mGradientColors = new int[2];
                    mGradientColors[0] = gradientColors[0];
                    mGradientColors[1] = gradientColors[0];
                } else {
                    mGradientColors = gradientColors;
                }
            } catch (Resources.NotFoundException e) {
                throw new Resources.NotFoundException("the give resource not found.");
            }
        }

        typedArray.recycle();
    }

    private void initPaint() {
        mHintPaint = new TextPaint();
        // 設置抗鋸齒，會消耗較大資源，繪製圖形速度會變慢
        mHintPaint.setAntiAlias(antiAlias);
        // 設置繪製文字大小
        mHintPaint.setTextSize(mHintSize);
        // 設置畫筆顏色
        mHintPaint.setColor(mHintColor);
        // 從中間向兩邊繪製，不需要再次計算文字
        mHintPaint.setTextAlign(Paint.Align.CENTER);

        mValuePaint = new TextPaint();
        mValuePaint.setAntiAlias(antiAlias);
        mValuePaint.setTextSize(mValueSize);
        mValuePaint.setColor(mValueColor);
        // 設置Typeface對象，即字體風格，包括粗體、斜體
        mValuePaint.setTypeface(Typeface.DEFAULT_BOLD);
        mValuePaint.setTextAlign(Paint.Align.CENTER);

        mUnitPaint = new TextPaint();
        mUnitPaint.setAntiAlias(antiAlias);
        mUnitPaint.setTextSize(mUnitSize);
        mUnitPaint.setColor(mUnitColor);
        mUnitPaint.setTextAlign(Paint.Align.CENTER);

        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(antiAlias);
        // 設置畫筆的樣式，為FILL、FILL_OR_STROKE或STROKE
        mArcPaint.setStyle(Paint.Style.STROKE);
        // 設置畫筆粗細
        mArcPaint.setStrokeWidth(mArcWidth);
        // 當畫筆樣式為STROKE或FILL_OR_STROKE時，設置筆刷的圖形樣式，如圓形樣式
        // Cap.ROUND,或方形樣式 Cap.SQUARE
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);

        mBgArcPaint = new Paint();
        mBgArcPaint.setAntiAlias(antiAlias);
        mBgArcPaint.setColor(mBgArcColor);

        mBgArcPaint.setStyle(Paint.Style.STROKE);
        mBgArcPaint.setStrokeWidth(mBgArcWidth);
        mBgArcPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MiscUtil.measure(widthMeasureSpec, mDefaultSize),
                MiscUtil.measure(heightMeasureSpec, mDefaultSize));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged: w = " + w + "; h = " + h + "; oldw = " + oldw + "; oldh = " + oldh);
        //求圓弧和背景圓弧的最大寬度
        float maxArcWidth = Math.max(mArcWidth, mBgArcWidth);
        //求最小值作為實際值
        int minSize = Math.min(w - getPaddingLeft() - getPaddingRight() - 2 * (int) maxArcWidth,
                h - getPaddingTop() - getPaddingBottom() - 2 * (int) maxArcWidth);
        //減去圓弧的寬度，否則會造成部分圓弧繪製在外圍
        mRadius = minSize / 2;
        //獲取圓的相關參數
        mCenterPoint.x = w / 2;
        mCenterPoint.y = h / 2;
        //繪製圓弧的邊界
        mRectF.left = mCenterPoint.x - mRadius - maxArcWidth / 2;
        mRectF.top = mCenterPoint.y - mRadius - maxArcWidth / 2;
        mRectF.right = mCenterPoint.x + mRadius + maxArcWidth / 2;
        mRectF.bottom = mCenterPoint.y + mRadius + maxArcWidth / 2;
        //計算文字繪製時的 baseline
        //由於文字的baseline、descent、ascent等屬性只與textSize和typeface有關，所以此時可以直接計算
        //若value、hint、unit由同一個畫筆繪製或者需要動態設置文字的大小，則需要在每次更新後再次計算
        mValueOffset = mCenterPoint.y + getBaselineOffsetFromY(mValuePaint);
        mHintOffset = ((mCenterPoint.y - mRadius * mTextOffsetPercentInRadius + getBaselineOffsetFromY(mHintPaint))-50);
        mUnitOffset = ((mCenterPoint.y + mRadius * mTextOffsetPercentInRadius + getBaselineOffsetFromY(mUnitPaint))+60);
        pos[0] = 0;
        pos[1] = 0.10f;
        pos[2] = 0.20f;
        pos[3] = 0.30f;
        pos[4] = 0.50f;
        pos[5] = 0.70f;

        updateArcPaint();
       /* Log.d(TAG, "onSizeChanged: 控件大小 = " + "(" + w + ", " + h + ")"
                + "圓心坐標 = " + mCenterPoint.toString()
                + ";圓半徑 = " + mRadius
                + ";圓的外接矩形 = " + mRectF.toString());*/

    }
    private float getBaselineOffsetFromY(Paint paint) {
        return MiscUtil.measureTextHeight(paint) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        updateText();
        drawText(canvas);
        drawArc(canvas);
    }
    /**
     * 繪製內容文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        // 計算文字寬度，由於Paint已設置為居中繪製，故此處不需要重新計算
        // float textWidth = mValuePaint.measureText(mValue.toString());
        // float x = mCenterPoint.x - textWidth / 2;
        canvas.drawText(String.format(mPrecisionFormat, mValue), mCenterPoint.x, mValueOffset, mValuePaint);

        if (mHint != null) {
            canvas.drawText(mHint.toString(), mCenterPoint.x, mHintOffset, mHintPaint);
        }

        if (mUnit != null) {
            canvas.drawText(mUnit.toString(), mCenterPoint.x, mUnitOffset, mUnitPaint);
        }
    }

    private void updateText(){

        if(mValue>=0 && mValue<=50){
            mUnit = "良好";

        }
        else if(mValue>50 && mValue<=100){
            mUnit = "普通";
        }
        else if(mValue>100 && mValue<=150){
            mUnit = "敏感族群不健康";
        }
        else if(mValue>150 && mValue<=200){
            mUnit = "所有族群不健康";
        }
        else if(mValue>200 && mValue<=300){
            mUnit = "非常不健康";
        }
        else{
            mUnit = "危害";
            mUnitColor = Color.RED;
            mUnitPaint.setColor(mUnitColor);
        }
    }
    private void drawArc(Canvas canvas) {
        // 繪製背景圓弧
        // 從進度圓弧結束的地方開始重新繪製，優化性能
        canvas.save();
        float currentAngle = mSweepAngle * mPercent;
        canvas.rotate(mStartAngle, mCenterPoint.x, mCenterPoint.y);
        canvas.drawArc(mRectF, currentAngle, mSweepAngle - currentAngle + 2, false, mBgArcPaint);
        // 第一個參數 oval 為 RectF 類型，即圓弧顯示區域
        // startAngle 和 sweepAngle  均為 float 類型，分別表示圓弧起始角度和圓弧度數
        // 3點鐘方向為0度，順時針遞增
        // 如果 startAngle < 0 或者 > 360,則相當於 startAngle % 360
        // useCenter:如果為True時，在繪製圓弧時將圓心包括在內，通常用來繪製扇形
        //mArcPaint.setStrokeWidth(30);
        if(mValue>=1 && mValue<=5){
            Paint paint = new Paint();
            paint.setAntiAlias(antiAlias);
            // 設置畫筆的樣式，為FILL，FILL_OR_STROKE，或STROKE
            paint.setStyle(Paint.Style.STROKE);
            // 設置畫筆粗細
            paint.setStrokeWidth(16);
            // 當畫筆樣式為STROKE或FILL_OR_STROKE時，設置筆刷的圖形樣式，如圓形樣式
            // Cap.ROUND,或方形樣式 Cap.SQUARE
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setColor(Color.GREEN);
            canvas.drawArc(mRectF, 0f, currentAngle, false, paint);
        }
        canvas.drawArc(mRectF, 2F, currentAngle, false, mArcPaint);
        canvas.restore();
    }

    /**
     * 更新圓弧畫筆
     */
    private void updateArcPaint() {
        // 設置漸層
        mSweepGradient = new SweepGradient(mCenterPoint.x, mCenterPoint.y,mGradientColors, pos);
        mArcPaint.setShader(mSweepGradient);
    }

    public boolean isAntiAlias() {
        return antiAlias;
    }

    public CharSequence getHint() {
        return mHint;
    }

    public void setHint(CharSequence hint) {
        mHint = hint;
    }

    public CharSequence getUnit() {
        return mUnit;
    }

    public void setUnit(CharSequence unit) {
        mUnit = unit;
    }

    public float getValue() {
        return mValue;
    }

    /**
     * 設置當前值
     *
     * @param value
     */
    public void setValue(float value) {
        if (value > mMaxValue) {
            value = mMaxValue;
        }
        float start = mPercent;
        float end = value / mMaxValue;
        startAnimator(start, end, mAnimTime);
    }

    private void startAnimator(float start, float end, long animTime) {
        mAnimator = ValueAnimator.ofFloat(start, end);
        mAnimator.setDuration(animTime);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPercent = (float) animation.getAnimatedValue();
                mValue = mPercent * mMaxValue;
             /*   if (BuildConfig.DEBUG) {
                    Log.d(TAG, "onAnimationUpdate: percent = " + mPercent
                            + ";currentAngle = " + (mSweepAngle * mPercent)
                            + ";value = " + mValue);
                }*/
                invalidate();
            }
        });
        mAnimator.start();
    }

    /**
     * 獲取最大值
     *
     * @return
     */
    public float getMaxValue() {
        return mMaxValue;
    }

    /**
     * 設置最大值
     *
     * @param maxValue
     */
    public void setMaxValue(float maxValue) {
        mMaxValue = maxValue;
    }

    /**
     * 獲取精度
     *
     * @return
     */
    public int getPrecision() {
        return mPrecision;
    }

    public void setPrecision(int precision) {
        mPrecision = precision;
        mPrecisionFormat = MiscUtil.getPrecisionFormat(precision);
    }

    public int[] getGradientColors() {
        return mGradientColors;
    }

    /**
     * 設置漸層
     *
     * @param gradientColors
     */
    public void setGradientColors(int[] gradientColors) {
        mGradientColors = gradientColors;
        updateArcPaint();
    }

    public long getAnimTime() {
        return mAnimTime;
    }

    public void setAnimTime(long animTime) {
        mAnimTime = animTime;
    }

    /**
     * 重置
     */
    public void reset() {
        startAnimator(mPercent, 0.0f, 1000L);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //釋放資源
    }
}

