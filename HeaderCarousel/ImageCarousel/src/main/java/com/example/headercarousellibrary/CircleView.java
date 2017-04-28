package com.example.headercarousellibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义圆形view
 * Created by zhangzhiqiang on 2017/4/28.
 */

public class CircleView extends View {
    private Paint circlePaint;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.circlePaint = new Paint();
        this.circlePaint.setAntiAlias(true);//消除锯齿
        this.circlePaint.setStyle(Paint.Style.FILL);
    }


    public void setCircleColor(int resource) {
        circlePaint.setColor(resource);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center = getWidth() / 2;
        canvas.drawCircle(center, center, center, circlePaint);
    }

}
