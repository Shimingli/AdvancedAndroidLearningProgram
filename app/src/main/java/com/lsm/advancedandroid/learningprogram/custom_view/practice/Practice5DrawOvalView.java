package com.lsm.advancedandroid.learningprogram.custom_view.practice;

import android.content.Context;
import android.graphics.Canvas;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

public class Practice5DrawOvalView extends View {

    public Practice5DrawOvalView(Context context) {
        super(context);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawOval() 方法画椭圆
        Paint paint = new Paint();


        canvas.drawOval(200,200,600,300,paint);

        RectF rectF = new RectF();
        rectF.bottom=400;
        rectF.right=300;
        rectF.left=400;
        rectF.right=400;
        canvas.drawOval(rectF,paint);

    }
}
