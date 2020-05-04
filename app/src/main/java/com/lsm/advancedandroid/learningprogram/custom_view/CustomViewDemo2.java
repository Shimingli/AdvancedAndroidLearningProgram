package com.lsm.advancedandroid.learningprogram.custom_view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ComposeShader;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Xfermode;
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
        // color(canvas);

        //效果类的 API ，指的就是抗锯齿、填充/轮廓、线条宽度等等这些。

        effect();


    }

    private void effect() {

    }

    /**
     * 关于颜色的三层设置：直接
     * 设置颜色的 API 用来给图形和文字设置颜色；
     * canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);    canvas 的参数
     * canvas.drawBitmap(circleBitmap, 0, 0, paint);   bitmap的参数
     * paint.setShader(linearGradient);   paint的参数
     * <p>
     * <p>
     * <p>
     * setColorFilter() 用来基于颜色进行过滤处理； paint4.setColorFilter(lightingColorFilter);
     * <p>
     * <p>
     * setXfermode() 用来处理源图像和 View 已有内容的关系：paint.setXfermode(xfermode); // 设置 Xfermode
     * https://github.com/chengdazhi/StyleImageView  这个做的很好 对piant的颜色的处理
     *
     * @param canvas
     */
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
        //bitmap像素作为图形
        Shader shader3 = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint3.setShader(shader3);
        // 圆形的范围，需要绘制的区域
        canvas.drawCircle(300, 300, 400, paint3);

        canvas.drawLine(400, 400, 400, 800, paint);

        //ComposeShader 混合着色器

        /**
         * 这段代码中我使用了两个 BitmapShader 来作为
         * ComposeShader() 的参数，而 ComposeShader() 在硬件加速下是不支持两个
         * 相同类型的 Shader 的，所以这里也需要关闭硬件加速才能看到效果。
         */
// 第一个 Shader：头像的 Bitmap
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.device);
        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
// 第二个 Shader：从上到下的线性渐变（由透明到黑色）
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.sample_path);
        Shader shader4 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
// ComposeShader：结合两个 Shader
        /**
         * PorterDuff.Mode
         * PorterDuff.Mode 是用来指定两个图像共同绘制时的颜色策略的。它是一个
         * enum，不同的 Mode 可以指定不同的策略。「颜色策略」的意思，就是说把源
         * 图像绘制到目标图像处时应该怎样确定二者结合后的颜色，而对于
         * ComposeShader(shaderA, shaderB, mode) 这个具体的方法，就是指应该怎
         * 样把 shaderB 绘制在 shaderA 上来得到一个结合后的 Shader。
         * 没有听说过 PorterDuff.Mode 的人，看到这里很可能依然会一头雾水：「什
         * 么怎么结合？就……两个图像一叠加，结合呗？还能怎么结合？」你还别说，
         * 还真的是有很多种策略来结合。
         */
        Shader shader5 = new ComposeShader(shader1, shader4, PorterDuff.Mode.ADD);
        paint.setShader(shader5);
        canvas.drawCircle(300, 600, 300, paint);


        /**
         * setColorFilter(ColorFilter colorFilter)
         * ColorFilter 这个类，它的名字已经足够解释它的作用：为绘制设置颜色过滤。颜
         * 色过滤的意思，就是为绘制的内容设置一个统一的过滤策略，然后
         * Canvas.drawXXX() 方法会对每个像素都进行过滤后再绘制出来
         *
         * 在 Paint 里设置 ColorFilter ，使用的是
         * Paint.setColorFilter(ColorFilter filter) 方法。 ColorFilter 并不直接
         * 使用，而是使用它的子类。它共有三个子类：LightingColorFilter
         * PorterDuffColorFilter 和 ColorMatrixColorFilter。
         */
        //LightingColorFilter 是用来模拟简单的光照效果的
        Paint paint4 = new Paint();
        //改光照的颜色 滤镜？？？
        // TODO: 4/24/2020 https://github.com/chengdazhi/StyleImageView/wiki/%E4%B8%AD%E6%96%87%E8%AF%B4%E6%98%8E%E9%A1%B5
        //
        ColorFilter lightingColorFilter = new LightingColorFilter(0xffffff, 0x003000);
        paint4.setColorFilter(lightingColorFilter);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.mipmap.device);
        //
        canvas.drawBitmap(bitmap3, 500, 500, paint4);

        /**
         * PorterDuffColorFilter
         * PorterDuffColorFilter 的作用是使用一个指定的颜色和一种指定的
         * PorterDuff.Mode 来与绘制对象进行合成。它的构造方法是
         * PorterDuffColorFilter(int color, PorterDuff.Mode mode) 其中的 color
         * 参数是指定的颜色， mode 参数是指定的 Mode。同样也是 PorterDuff.Mode ，不
         * 过和 ComposeShader 不同的是，PorterDuffColorFilter 作为一个
         * ColorFilter，只能指定一种颜色作为源，而不是一个 Bitmap。
         */

        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(new ColorMatrix());
        paint.setColorFilter(colorMatrixColorFilter);
        /**
         * ColorMatrixColorFilter
         * 这个就厉害了。ColorMatrixColorFilter 使用一个 ColorMatrix 来对颜色进行
         * 处理。 ColorMatrix 这个类，内部是一个 4x5 的矩阵：
         * [ a, b, c, d, e,
         * f, g, h, i, j,
         * k, l, m, n, o,
         * p, q, r, s, t ]
         * 通过计算， ColorMatrix 可以把要绘制的像素进行转换。对于颜色 [R, G, B, A] ，
         * 转换算法是这样的：
         * R’ = a*R + b*G + c*B + d*A + e;
         * G’ = f*R + g*G + h*B + i*A + j;
         * B’ = k*R + l*G + m*B + n*A + o;
         * A’ = p*R + q*G + r*B + s*A + t;
         *
         * ColorMatrix 有一些自带的方法可以做简单的转换，例如可以使用
         * setSaturation(float sat) 来设置饱和度；另外你也可以自己去设置它的每一个
         * 元素来对转换效果做精细调整
         *
         * StyleImageView  https://github.com/chengdazhi/StyleImageView
         *
         * Paint 对颜色的第二层处理：通过 setColorFilter(colorFilter)
         * 来加工颜色。
         * 除了基本颜色的设置（ setColor/ARGB() , setShader() ）以及基于原始颜色的过
         * 滤（ setColorFilter() ）之外，Paint 最后一层处理颜色的方法是
         * setXfermode(Xfermode xfermode) ，它处理的是「当颜色遇上 View」的问题。
         *
         *
         *
         */

        /**
         * setXfermode(Xfermode xfermode)
         * "Xfermode" 其实就是 "Transfer mode"，用 "X" 来代替 "Trans" 是一些美国人喜欢用
         * 的简写方式。严谨地讲， Xfermode 指的是你要绘制的内容和 Canvas 的目标位置
         * 的内容应该怎样结合计算出最终的颜色。但通俗地说，其实就是要你以绘制的内容
         * 作为源图像，以 View 中已有的内容作为目标图像，选取一个 PorterDuff.Mode 作
         * 为绘制内容的颜色处理方案。就像这样
         */
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        canvas.drawBitmap(bitmap3, 0, 0, paint); // 画方
        paint.setXfermode(xfermode); // 设置 Xfermode
        canvas.drawBitmap(bitmap3, 0, 0, paint); // 画圆
        paint.setXfermode(null); // 用完及时清除 Xfermode


    }
}
