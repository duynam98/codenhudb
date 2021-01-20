package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class OnCreate extends View {

    private Paint mPaint;
    private Paint pain_background;
    private int width_mode, height_mode;
    private int width_size, height_size;
    private Rect rect;
    private int leftX=0;

    private Paint mPain_oval;

    public OnCreate(Context context) {
        super(context);
        init();
    }

    public OnCreate(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OnCreate(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public OnCreate(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        mPain_oval = new Paint();
        mPain_oval.setColor(Color.RED);

        pain_background = new Paint();
        pain_background.setStyle(Paint.Style.STROKE);
        pain_background.setColor(Color.YELLOW);

        rect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width_mode = MeasureSpec.getMode(widthMeasureSpec);
        height_mode = MeasureSpec.getMode(heightMeasureSpec);

        width_size = MeasureSpec.getSize(widthMeasureSpec);
        height_size = MeasureSpec.getSize(heightMeasureSpec);

        int width = 200;
        int height = 50;

        if (width_mode == MeasureSpec.EXACTLY) {
            width = width_size;
        } else if (width_mode == MeasureSpec.AT_MOST) {
            width = Math.min(width, width_size);
        } else if (width_mode == MeasureSpec.UNSPECIFIED) {
            width = 200;
        }

        if (height_mode == MeasureSpec.EXACTLY) {
            height = height_size;
        } else if (height_mode == MeasureSpec.AT_MOST) {
            height = Math.min(height, height_size);
        } else if (height_mode == MeasureSpec.UNSPECIFIED) {
            height = 30;
        }

        setMeasuredDimension(width, height);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(0.1f * getMeasuredHeight());
        pain_background.setStrokeWidth(0.1f * getMeasuredHeight());

        canvas.drawLine(getPaddingLeft(), 0.5f * getMeasuredHeight(),
                getWidth() - getPaddingRight(), 0.5f * getMeasuredHeight(), mPaint);

        canvas.drawLine(getPaddingLeft(), 0, getPaddingLeft(), getHeight(), mPaint);
        canvas.drawLine(0.25f * getWidth() - getPaddingLeft(), 0,
                0.25f * getWidth() - getPaddingLeft(), getHeight(), mPaint);
        canvas.drawLine(0.5f * getWidth() - getPaddingLeft(), 0,
                0.5f * getWidth() - getPaddingLeft(), getHeight(), mPaint);
        canvas.drawLine(0.75f * getWidth() - getPaddingLeft(), 0,
                0.75f * getWidth() - getPaddingLeft(), getHeight(), mPaint);
        canvas.drawLine(getWidth()-getPaddingRight(), 0,
                getWidth()-getPaddingRight(), getHeight(), mPaint);

        canvas.drawLine(getPaddingLeft()+leftX, 0.5f * getMeasuredHeight(),
                getWidth() - getPaddingRight(), 0.5f * getMeasuredHeight(), pain_background);

        canvas.drawOval(getPaddingLeft() - 22 + leftX,
                0, getPaddingLeft() + 22 + leftX, getMeasuredHeight(), mPain_oval);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPain_oval.setColor(Color.BLUE);
                break;
            case MotionEvent.ACTION_MOVE:
                leftX = (int) event.getX();
                if (leftX >= getWidth() - getPaddingLeft()*2){
                    leftX = getWidth() - getPaddingLeft()*2;
                }
                if (leftX < getPaddingLeft()){
                    leftX = getPaddingLeft();
                }
                break;
            case MotionEvent.ACTION_UP:
                mPain_oval.setColor(Color.RED);
                break;
        }
        invalidate();
        return true;
    }
}
