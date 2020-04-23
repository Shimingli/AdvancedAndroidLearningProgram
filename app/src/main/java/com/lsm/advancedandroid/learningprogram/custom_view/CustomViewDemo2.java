package com.lsm.advancedandroid.learningprogram.custom_view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.lsm.advancedandroid.learningprogram.R;

/**
 * @NAME: CustomViewDemo2
 * @Des:
 * @USER: shiming
 * @DATE: 2020/4/14 22:08
 * @PROJECT_NAME: AdvancedAndroidLearningProgram
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
@SuppressLint("AppCompatCustomView")
public class CustomViewDemo2 extends View {

    private Context mContext;

    public CustomViewDemo2(Context context) {
        this(context, null);
    }

    public CustomViewDemo2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Paint 的 API 大致可以分为 4 类：
//        颜色 效果 drawText()相关 初始化

        color(canvas);


    }

    private void color(Canvas canvas) {
        /*
        Canvas 的颜色填充
类方法 drawColor/RGB/ARGB() 的颜色，是直接写在方法的参数里，通过参数来设
置的； drawBitmap() 的颜色，是直接由 Bitmap 对象来提供的
；除此之外，是图形和文字的绘制，它们的颜色就需要使用
paint 参数来额外设置了
         */

        //Paint 设置颜色的方法有两种：一种是直接用 Paint.setColor/ARGB() 来设置颜
        //色，另一种是使用 Shader 来指定着色方案。


        Paint paint = new Paint();

//        paint.setColor(Color.parseColor("#ff5511"));
        canvas.drawRect(100, 100, 300, 300, paint);
        //其实和 setColor(color) 都是一样一样儿的，只是它的参数用的是更直接的三原
        //色与透明度的值。实际运用中，setColor() 和 setARGB() 哪个方便和顺手用哪个
        //吧。
//        paint.setARGB(100,500,100,100);
        canvas.drawRect(300, 300, 400, 400, paint);


        //Shader 这个英文单词很多人没有见过，它的中文叫做「着色器」，也是用于设置绘
        //制颜色的。除了直接设置颜色， Paint 还可以使用 Shader
        //当设置了 Shader 之后，Paint 在绘制图形和文字时就不使用
        //setColor/ARGB() 设置的颜色了，而是使用 Shader 的方案中的颜色。

        /*
        在 Android 的绘制里使用 Shader ，并不直接用 Shader 这个类，而是用它的几个
子类。具体来讲有 LinearGradient RadialGradient SweepGradient
BitmapShader ComposeShader 这么几个
         */

        //LinearGradient 线性渐变
        //设置两个点和两种颜色，以这两个点作为端点，使用两种颜色的渐变来绘制颜色。
        //CLAMP , MIRROR 和 REPEAT 。CLAMP （夹子模式？？？算了这个词我不会
        //翻）会在端点之外延续端点处的颜色；MIRROR 是镜像模式；REPEAT 是重复模式
//        LinearGradient linearGradient = new LinearGradient(100, 100, 500, 500, Color.parseColor("#2196F3"), Color.RED,
//                Shader.TileMode.REPEAT);
//        paint.setShader(linearGradient);
//        canvas.drawCircle(300,300,200,paint);

        LinearGradient linearGradient = new LinearGradient(getWidth(), 400, 0, 0, Color.RED, Color.GREEN, Shader.TileMode.REPEAT);
        paint.setShader(linearGradient);
        canvas.drawRect(0, 0, getWidth(), 300, paint);
//        Paint paint1 = new Paint();
        //paint.setColor(Color.parseColor("#ff0000"));
//        canvas.drawCircle(700,300,200,paint);

//        RadialGradient 辐射渐变
//        辐射渐变很好理解，就是从中心向周围辐射状的渐变
        /*
        centerX centerY ：辐射中心的坐标
radius ：辐射半径
centerColor ：辐射中心的颜色
edgeColor ：辐射边缘的颜色
tileMode ：辐射范围之外的着色模式。
         */
        Paint paint1 = new Paint();
        Shader shader = new RadialGradient(300, 300, 200, Color.RED,
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        paint1.setShader(shader);
        canvas.drawCircle(300, 300, 200, paint1);


        //又是一个渐变。「扫描渐变」这个翻译我也不知道精确不精确
        //cx cy ：扫描的中心
        //color0 ：扫描的起始颜色
        //color1 ：扫描的终止颜色
        Paint paint2 = new Paint();
        Shader shader2 = new SweepGradient(600, 300, Color.parseColor("#E91E61"),
                Color.parseColor("#2196F3"));
        paint2.setShader(shader2);
        canvas.drawCircle(600, 300, 200, paint2);


        //也就是用 Bitmap 的像素来作为图形
        //或文字的填充



//        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        /**
         * Canvas.drawBitmap() 好像啊？事实上也是一样的效果。如果你
         * 想绘制圆形的 Bitmap ，就别用 drawBitmap() 了，改用 drawCircle() +
         * BitmapShader 就可以了（其他形状同理）。
         */
        Paint paint3 = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.device);
        //tileX：横向的 TileMode
        //tileY：纵向的 TileMode 。
        Shader shader3 = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint3.setShader(shader3);
        canvas.drawCircle(300, 300, 200, paint3);


    }
}
