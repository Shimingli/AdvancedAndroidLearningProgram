package com.lsm.advancedandroid.learningprogram.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * @NAME: CustomViewDemo1
 * @Des:
 * @USER: shiming
 * @DATE: 2020/4/14 22:08
 * @PROJECT_NAME: AdvancedAndroidLearningProgram
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
@SuppressLint("AppCompatCustomView")
public class CustomViewDemo1 extends View {
    public CustomViewDemo1(Context context) {
        super(context);
    }

    public CustomViewDemo1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    Paint mPaint = new Paint();
    Path path = new Path(); // 初始化 Path 对象
    {
        // 使用 path 对图形进行描述（这段描述代码不必看懂）
        path.addArc(200, 200, 400, 400, -225, 225);
        path.arcTo(400, 200, 600, 400, -180, 225, false);
        path.lineTo(400, 542);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //function1(canvas);


        //function2(canvas);

        //function3(canvas);

        //drawPath(Path path, Paint paint) 画自定义图形
        canvas.drawPath(path,mPaint);




    }

    private void function3(Canvas canvas) {
        //画圆角矩形 left , top , right , bottom 是四条边的坐标，rx 和 ry 是圆角的横向半径和纵向
        //半径。
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.RED);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //drawRoundRect(RectF rect, float rx, float ry, Paint paint) ，让你可以
            //直接填写 RectF 来绘制圆角矩形。
            canvas.drawRoundRect(200, 200, 400, 400, 200, 500, mPaint);
        }

        mPaint.setStyle(Paint.Style.FILL); // 填充模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /*
           drawArc() 是使用一个椭圆来描述弧形的。left , top , right , bottom 描述的是
           这个弧形所在的椭圆；startAngle 是弧形的起始角度（x 轴的正向，即正右的方
           向，是 0 度的位置；顺时针为正角度，逆时针为负角度），sweepAngle 是弧形划
           过的角度；useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果
           连接到圆心，就是扇形。
             */
            canvas.drawArc(200, 100, 800, 500, -110, 100, true, mPaint); // 绘制扇
            canvas.drawArc(200, 100, 800, 500, 20, 140, false, mPaint); // 绘制弧形
            mPaint.setStyle(Paint.Style.STROKE); // 画线模式
            canvas.drawArc(200, 100, 800, 500, 180, 60, false, mPaint); // 绘制不

        }
    }

    private void function2(Canvas canvas) {
        //left , top , right , bottom 是矩形四条边的坐标。 画矩形
        mPaint.setStyle(Paint.Style.STROKE);
        //两个重载方法 drawRect(RectF rect, Paint paint) 和
        //drawRect(Rect rect, Paint paint) ，让你可以直接填写 RectF 或 Rect 对象
        //来绘制矩形。
        canvas.drawRect(100, 100, 400, 400, mPaint);

        mPaint.setStrokeWidth(200);
        //点的形状可以通过 paint.setStrokeCap(cap) 来设置：ROUND 画出来是圆形
        //的点， SQUARE 或 BUTT 画出来是方形的点。点还有形状区别
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(500, 500, mPaint);

        mPaint.setStrokeWidth(10);
        //pts 这个数组是点的坐标，每两个成一对 在源码里面至少长度为 Size(multiple = 2)
        float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 20};
        // 绘制四个点：
        //canvas.drawPoints(points,mPaint);
        // 2  * 跳过两个数，即前两个 0 * /* 一共绘制 4 个数（2 个点）*/,
        canvas.drawPoints(points, 2, 4, mPaint);

        //api大于21
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //。 left , top , right , bottom
            //是这个椭圆的左、上、右、下四个边界点的坐标。
            canvas.drawOval(200, 200, 100, 500, mPaint);
            //，它还有一个重载方法 drawOval(RectF rect, Paint paint) ，让你可以直
            //接填写 RectF 来绘制椭圆。
        }
        //画线  由于直线不是封闭图形，所以 setStyle(style) 对直线没有影响。
        canvas.drawLine(200, 200, 600, 300, mPaint);

        //drawLines() 是 drawLine() 的复数版。
        mPaint.setColor(Color.RED);
        //其实我就花了3条线，所以这里需要去在脑海里面去想想这个问题
        float[] points1 = {20, 20, 120, 20, 70, 20, 70, 120, 20, 120, 120, 120};
        canvas.drawLines(points1, mPaint);
    }

    private void function1(Canvas canvas) {

//        Paint.setStyle(Style style) 设置绘制模式
//        Paint.setColor(int color) 设置颜色
//        Paint.setStrokeWidth(float width) 设置线条宽度
//        Paint.setTextSize(float textSize) 设置文字大小
//        Paint.setAntiAlias(boolean aa) 设置抗锯齿开关
        //Style 具体来说有三
        //种： FILL , STROKE 和 FILL_AND_STROKE 。FILL 是填充模式，STROKE 是画线模
        //式（即勾边模式），FILL_AND_STROKE 是两种模式一并使用：既画线又填充。它的
        //默认值是 FILL ，填充模式
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#ff0000"));
        //在 STROKE 和 FILL_AND_STROKE 下，还可以使用
        //paint.setStrokeWidth(float width) 来设置线条的宽度
        mPaint.setStrokeWidth(100);

        //在绘制的时候，往往需要开启抗锯齿来让图形和文字的边缘更加平滑。开启抗锯齿
        //很简单，只要在 new Paint() 的时候加上一个 ANTI_ALIAS_FLAG 参数就行

        /*
         * 未开启抗锯齿的
         * 圆，所有像素都是同样的黑色，而开启了抗锯齿的圆，边缘的颜色被略微改变
         * 了。这种改变可以让人眼有边缘平滑的感觉，但从某种角度讲，它也造成了图
         * 形的颜色失真。
         * 所以，抗锯齿好不好？好，大多数情况下它都应该是开启的；但在极少数的某
         * 些时候，你还真的需要把它关闭
         * 开启抗锯齿的话，图像的边缘颜色会改变，不开的话，就是图像原本的颜色
         */
        mPaint.setAntiAlias(true);


        //Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //起始点，和半径
        //前两个参数 centerX centerY 是圆心的坐标，第三个参数 radius 是圆的半径，
        //单位都是像素，它们共同构成了这个圆的基本信息（即用这几个信息可以构建出一
        //个确定的圆）；第四个参数 paint 我在视频里面已经说过了，它提供基本信息之外
        //的所有风格信息，例如颜色、线条粗细、阴影等。
        //一个 View 的坐标 (x, y) 处，指的就是相对它的左上角那个点的水平方向 x 像
        //素、竖直方向 y 像素的点。例如，(300, 300) 指的就是左上角的点向右 300 、向下
        //300 的位置
        canvas.drawCircle(300, 300, 200, mPaint);
        // 会在原有的绘制效果上加一层半透明的遮罩
        // canvas.drawColor(Color.parseColor("#8800ff00"));
        //类似的方法还有 drawRGB(int r, int g, int b) 和
        //drawARGB(int a, int r, int g, int b) ，它们和 drawColor(color) 只是使
        //用方式不同，作用都是一样的。
        canvas.drawARGB(100, 200, 8, 8);


    }
}
