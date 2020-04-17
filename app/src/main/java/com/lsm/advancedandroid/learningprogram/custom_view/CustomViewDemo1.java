package com.lsm.advancedandroid.learningprogram.custom_view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

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

    private Context mContext;

    public CustomViewDemo1(Context context) {
        this(context,null);
    }

    public CustomViewDemo1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

    }

    Paint mPaint = new Paint();

    Path path = new Path(); // 初始化 Path 对象

    //Path 可以描述直线、二次曲线、三次曲线、圆、椭圆、弧形、矩形、圆角矩形。
    //把这些图形结合起来，就可以描述出很多复杂的图形。 下面是画的是心得形状
    {
        // 使用 path 对图形进行描述（这段描述代码不必看懂）
        path.addArc(200, 200, 400, 400, -225, 225);
        path.arcTo(400, 200, 600, 400, -180, 225, false);
        path.lineTo(400, 542);
    }

    //辅助的类的计算
    Path mPath = new Path();


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //function1(canvas);


        //function2(canvas);

        //function3(canvas);


        //function4(canvas);


        //function5(canvas);

        //function6(canvas);

        /**
         * 字图形没有被封闭
         */
        mPaint.setStyle(Paint.Style.FILL);
        path.reset();
        path.moveTo(100, 100);
        path.lineTo(200, 100);
        path.lineTo(150, 150);
        path.setFillType(Path.FillType.EVEN_ODD);
        //封闭图形，相当于 path.lineTo(100,100)  close() 和 lineTo(起点坐标) 是完全等价的。
        path.close();
        canvas.drawPath(path,mPaint);


        mPaint.setColor(Color.GREEN);
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);

         Bitmap    bitmap = Bitmap.createBitmap(dm.widthPixels, dm.heightPixels, Bitmap.Config.ARGB_8888);
        //用指定的方式填充位图的像素。
        bitmap.eraseColor(Color.rgb(Color.red(mPaint.getColor()),
                Color.green(mPaint.getColor()), Color.blue(mPaint.getColor())));
        canvas.drawBitmap(bitmap, 200, 200, mPaint);


    }

    private void function6(Canvas canvas) {
        //画的椭圆
//        mPaint.setStyle(Paint.Style.FILL); // 填充模式
//        canvas.drawArc(200, 100, 800, 500, -110, 100, true, mPaint); // 绘制扇
//        canvas.drawArc(200, 100, 800, 500, 20, 140, false, mPaint); // 绘制弧形
//        mPaint.setStyle(Paint.Style.STROKE); // 画线模式
//        canvas.drawArc(200, 100, 800, 500, 180, 60, false, mPaint); // 绘制不


        mPaint.setStyle(Paint.Style.STROKE);
        path.reset();
        path.lineTo(100, 100);
        //画弧形
        /*
        forceMoveTo 参数的意思是，绘制是要「抬一
        下笔移动过去」，还是「直接拖着笔过去」，区别在于是否留下移动的痕
         */
        path.arcTo(100, 100, 300, 300, -90, 90, false); //

        /*
        一个叫 arcTo ，一个叫 addArc()，都是弧形，区别在哪
      里？其实很简单： addArc() 只是一个直接使用了 forceMoveTo = true 的简化版
       arcTo() 。
      paint.
         */
        canvas.drawPath(path,mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        path.lineTo(100, 100);
        path.addArc(100, 100, 300, 300, -90, 90);
    }

    private void function5(Canvas canvas) {
        //        mPaint.setColor(Color.RED);
//        mPaint.setStyle(Paint.Style.STROKE);
//        //由当前位置 (0, 0) 向 (100, 100) 画一条直线
//        mPath.lineTo(100,100);
//        // 由当前位置 (100, 100) 向正右方 100 像素的位置画一
//        mPath.rLineTo(100,1200);
//        canvas.drawPath(mPath,mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(2);
        mPath.reset();
        /*
        贝塞尔曲线：贝塞尔曲线是几何上的一种曲线。它通过起点、控制点和终点来
        描述一条曲线，主要用于计算机图形学。概念总是说着容易听着难，总之使用
        它可以绘制很多圆润又好看的图形，但要把它熟练掌握、灵活使用却是不容易
        的。不过还好的是，一般情况下，贝塞尔曲线并没有什么用处，只在少数场景
        下绘制一些特殊图形的时候才会用到，所以如果你还没掌握自定义绘制，可以
        先把贝塞尔曲线放一放，稍后再学也完全没问题。
         */
        //而参数中的 x1 , y1 和 x2 , y2 则分别
        //是控制点和终点的坐标   起点是屏幕的左上角
        mPath.quadTo(100, 100, 800, 200);
        //这边也是同理 dx1,dy1,dx2,dy2 也是控制点和终点的坐标
        mPath.rQuadTo(300, 100, 100, 500);
        canvas.drawPath(mPath, mPaint);
        /**
         * cubicTo(oat x1, oat y1, oat x2, oat y2, oat x3, oat y3) /
         * rCubicTo(oat x1, oat y1, oat x2, oat y2, oat x3, oat y3) 画三次贝塞
         * 尔曲线
         * 和上面这个 quadTo() rQuadTo() 的二次贝塞尔曲线同理，cubicTo() 和
         * rCubicTo() 是三次贝塞尔曲线，不再解释。
         */
        //只不过呢 (x1,y1) and (x2,y2)是控制点, and ending at (x3,y3).用法其实差不多呀！
    }

    /**
     * 的 Path.add-() 方法和这类似，例如：
     * addOval(oat left, oat top, oat right, oat bottom, Direction dir) /
     * addOval(RectF oval, Direction dir) 添加椭圆
     * addRect(oat left, oat top, oat right, oat bottom, Direction dir) /
     * addRect(RectF rect, Direction dir) 添加矩形
     * addRoundRect(RectF rect, oat rx, oat ry, Direction dir) /
     * addRoundRect(oat left, oat top, oat right, oat bottom, oat rx,
     * oat ry, Direction dir) / addRoundRect(RectF rect, oat[] radii,
     * Direction dir) / addRoundRect(oat left, oat top, oat right, oat
     * bottom, oat[] radii, Direction dir) 添加圆角矩形
     * addPath(Path path) 添加另一个 Path
     * 上面这几个方法和 addCircle() 的使用都差不多，
     *
     * @param canvas
     */
    private void function4(Canvas canvas) {
        // 画自定义图形
        // 一类是自己独立描述路径，一类是辅助的设置或者是计算
        canvas.drawPath(path, mPaint);
        //路径方向有两种：顺时针 ( CW clockwise) 和逆时针 ( CCW counter-clockwise) 。
        //对于普通情况，这个参数填 CW 还是填 CCW 没有影响。它只是在需要填充图形
        //( Paint.Style 为 FILL 或 FILL_AND_STROKE ) ，并且图形出现自相交时，用
        //于判断填充范围的
        mPaint.setStyle(Paint.Style.FILL);
        /*
        path.AddCircle(x, y, radius, dir) +
        canvas.drawPath(path, paint) 这种写法，和直接使用
        canvas.drawCircle(x, y, radius, paint) 的效果是一样的，区别只是它的写
       法更复杂。所以如果只画一个圆，没必要用 Path ，直接用 drawCircle() 就行
      了。drawPath() 一般是在绘制组合图形时才会用到的。
         */
        mPath.addCircle(500, 900, 200, Path.Direction.CW);
        mPath.addCircle(700, 900, 200, Path.Direction.CCW);
        canvas.drawPath(mPath, mPaint);

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
