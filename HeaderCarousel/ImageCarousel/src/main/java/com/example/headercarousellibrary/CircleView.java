package com.example.headercarousellibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义圆形view（小圆点）
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
        //设置圆点颜色
        circlePaint.setColor(resource);
        //刷新onDraw
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center = getWidth() / 2;
        //以view的1/2作为圆心画圆
        canvas.drawCircle(center, center, center, circlePaint);

    }

}
