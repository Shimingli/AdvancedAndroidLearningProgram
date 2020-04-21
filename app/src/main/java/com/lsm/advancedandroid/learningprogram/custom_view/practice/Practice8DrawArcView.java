package com.lsm.advancedandroid.learningprogram.custom_view.practice;

import android.content.Context;
import android.graphics.Canvas;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        Paint paint = new Paint();
        /**
         * float left, float top, float right, float bottom, float startAngle,
         *             float sweepAngle, boolean useCenter, @NonNull Paint paint
         *
         *             startAngle 是弧形的起始角度，x轴的正向，是0度，顺时针是正角度，逆时针为负
         *             sweepAngle 是扫过的幅度
         *             userCenter 是否连接到圆形
         *
         *
         */
        canvas.drawArc(100,100,500,400,90,90,false,paint);


         paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(100,100,500,400,190,60,false,paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        canvas.drawArc(100,100,500,400,240,60,true,paint);

    }
}
