package com.lsm.advancedandroid.learningprogram.custom_view.practice;

import android.content.Context;
import android.graphics.Canvas;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形

        Path path = new Path();
        /**
         * 又是一个弧形的方法。一个叫 arcTo ，一个叫 addArc()，都是弧形，区别在哪
         * 里？其实很简单： addArc() 只是一个直接使用了 forceMoveTo = true 的简化版
         * arcTo() 。
         */
        //我理解这个方法的意思，就是起点200和200，终点为400，400，逆时针方向的-225度,画的圆弧的大小为225度
        //
        path.addArc(200, 200, 400, 400, -225, 225);
        //开始的角度为-180度，扫过225度  forceMoveTo的意思就是需要抬一下笔移动过去，还是直接拖过去，区别是否留下移动的痕迹
        path.arcTo(400, 200, 600, 400, -180, 225, false);
//        path.lineTo(400, 542);
        Paint paint = new Paint();
        canvas.drawPath(path,paint);
    }
}
