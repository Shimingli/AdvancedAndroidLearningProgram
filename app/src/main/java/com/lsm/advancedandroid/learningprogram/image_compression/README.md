# 深入了解安卓图片优化到底是个什么东西
** 这篇文章相对于以前的理解要深入好多
 #### 图片压缩
 好处：节约用户流量，降低服务器的带宽，降低App的内存使用 
 分类：质量压缩，尺寸压缩
 #### 尺寸压缩之邻近采样
 临近采样：是基于邻近点插值算法实现的，会根据相对位置取一个像素代替原图几个像素  
 ```
         val option=BitmapFactory.Options()
         //采样率
         option.inSampleSize=1
         val bitmap=BitmapFactory.decodeResource(resources,R.mipmap.demo,option)
         mImageView.setImageBitmap(bitmap)
 ```
 
 * 领近采样有个缺陷，如果原图是一张红白相间的图片，如果尺寸合适的话，就会采集成一张红色的或者是白色的图片 
 
 ![demo.png](https://upload-images.jianshu.io/upload_images/5363507-83c3abb1deae22f7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 
 
 #### 尺寸压缩之双线性采样
 双线性采样：采用双线性插值算法，会根据相对位置取一个像素点替换掉几个像素，其实就是周围的 2*2的点 
 ```
   val bitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.demo)
         val bitmap2 = Bitmap.createScaledBitmap(bitmap1,bitmap1.width/2,bitmap1.height/2,true)
         mImageView.setImageBitmap(bitmap2)
 ```
 * 它的效果就是出现一种混合的效果，如果是一张间隔图，那么他的颜色就是居中取 
 
 * **一般都是邻近采样和双线性采样一起使用，具体的时候**
 ![image.png](https://upload-images.jianshu.io/upload_images/5363507-5feab68703d11d74.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 
 
 #### 质量压缩:SKia 图形引擎 libjpeg，这种会把图片生产新的，同时放在本地，通过BitmapFactory.decodeFile 去加载本地图片
 quality 0-100 ，0代表质量越小，100越大，
 ```
         //最好把这个图片copy到本地区，这样就能够看到真正质量的却别 
         val byteArrayOutputStream = ByteArrayOutputStream()
         val compress = bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
 ```
 假如一张图片的质量在80k，那么通过这种方式的压缩的结果 webp-2k png-50k jepg-10k，所以webp是最优的结果，其实如果压缩的是PNG格式的话，改变quality的时候，压缩的大小其实不会改变，这点注意 
 * **直接调用的是nativeCompress，其实最底层调用的是 SKImageEncoder，其实就是SKia**
 ![image.png](https://upload-images.jianshu.io/upload_images/5363507-1056991cd6dc0804.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 * skia是个2D向量图形处理函数库，包含字型、坐标转换，以及点阵图都有高效能且简洁的表现。不仅用于Google Chrome浏览器，新兴的Android开放手机平台也采用skia作为绘图处理，搭配OpenGL/ES与特定的硬件特征，强化显示的效果，包括现在的Flutter也是使用这个引擎，所以Flutter的打包的出来的ios其实比较大，这点注意 
 
 #### skia 
 [skia](https://github.com/google/skia) 这是开源的 
 
 skia 已被应用于[Android](https://zh.m.wikipedia.org/wiki/Android "Android")、[Google Chrome](https://zh.m.wikipedia.org/wiki/Google_Chrome "Google Chrome")、[Chrome OS](https://zh.m.wikipedia.org/wiki/Chrome_OS "Chrome OS")、[Chromium OS](https://zh.m.wikipedia.org/wiki/Chromium_OS "Chromium OS")、[Mozilla Firefox](https://zh.m.wikipedia.org/wiki/Mozilla_Firefox "Mozilla Firefox")、[Firefox OS](https://zh.m.wikipedia.org/wiki/Firefox_OS "Firefox OS")以及[Sublime Text](https://zh.m.wikipedia.org/wiki/Sublime_Text "Sublime Text")。[](https://zh.m.wikipedia.org/wiki/Android "Android")[](https://zh.m.wikipedia.org/wiki/Google_Chrome "Google Chrome")[](https://zh.m.wikipedia.org/wiki/Chrome_OS "Chrome OS")[](https://zh.m.wikipedia.org/wiki/Chromium_OS "Chromium OS")[](https://zh.m.wikipedia.org/wiki/Mozilla_Firefox "Mozilla Firefox")[](https://zh.m.wikipedia.org/wiki/Firefox_OS "Firefox OS")[](https://zh.m.wikipedia.org/wiki/Sublime_Text "Sublime Text")
 
 2012年时Skia有大概80,000行程式码，以C++开发而成，所以牛逼呀，我们写个80000行代码，都是面向白的CV工程师，哈哈，我写到这里我自己都笑了 
 
 *  skia 在安卓中调用的过程：
   Java函数-native函数-skia函数-对应的三方库 libijpeg ，哈夫曼表就在libjpeg
 
 * 安卓版本图，来源于维基百科
 ![image.png](https://upload-images.jianshu.io/upload_images/5363507-074a8b6035842499.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 
 * **在安卓6.0和安卓7.0压缩JPEG图片的区别，实际上是Android在调用libjpeg的时候有一些区别，在Android7.0 SKImageDecoder_libjpeg.cpp中多了一个optimize_coding的标记是否计算哈夫曼表**
 官方的说法：为了提高运行的效率，Google在Android的Skia实现中，对JPG压缩处理算法改写来代替调用libjpeg-turbo，实现了一个低精度的[YUV](https://zh.wikipedia.org/wiki/YUV "YUV")转换为[RGB](https://zh.wikipedia.org/wiki/RGB "RGB")的算法，但该改写算法中除法取整的方式不合理，不仅每次压缩后画质劣化更严重，随着误差逐步累积，还会导致图片会越来越偏向绿色。最终该缺陷在2016年4月得到修复，修改回直接使用libjpeg-turbo的调用。而作为Android基础库的一部分，该修复也被认为预计于[Android 7](https://zh.wikipedia.org/wiki/Android_7 "Android 7")中修复。
 
 
 #### 两种算法 定长算法和哈夫曼：libjpeg在压缩图片使用的就是这种算法，我其实也说不清哈，这个算法需要的请详细了解 
 
 ![image.png](https://upload-images.jianshu.io/upload_images/5363507-91dfa5f013eebdad.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 
 ![image.png](https://upload-images.jianshu.io/upload_images/5363507-22c3bca4f8d301de.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 ![image.png](https://upload-images.jianshu.io/upload_images/5363507-7ff64d9ed8d3f40d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 
 #### 最后
 * 在以前我写过一篇文章 [安卓代码、图片、布局、网络和电量优化]([https://www.jianshu.com/p/82b76e0cb41e](https://www.jianshu.com/p/82b76e0cb41e)其实现在看来还是太简单，这篇文章要详细一些
 
 * 什么时候需要，比如说我们在开发聊天页面的时候，发送本地图片，现在手机都是10m的图片，肯定需要去优化他的大小，要么尺寸压缩，或者质量压缩，在质量压缩的时候，当然选择WEBP格式，只不过要copy一份到本地
 
 * 能灵活运用才是最好的 
 
 
 
 
