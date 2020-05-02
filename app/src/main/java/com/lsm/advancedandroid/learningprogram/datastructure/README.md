# ArrayList、LinkeList、HashMap和SparseArray数据结构透彻的理解
* 在2年前左右我有写过 [常用集合的原理分析](https://www.jianshu.com/p/a5f638bafd3b),当时主要是自己的思考，但是我发现有些不太透彻的地方，便有此文，对了，祝五一快乐
####  ArrayList思考为什么我们在工作中没有去使用数组，而是更多的使用ArrayList？
数组的性能肯定比集合高，这点毋庸置疑，但是到底为什么呢？
* ArrayList每次扩容都是增长原来长度的一半，就是右移，>>1，ArrayList初始的长度是10，那么第一次扩容的时候就是15长度
![image.png](https://upload-images.jianshu.io/upload_images/5363507-72303244a408f78a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image.png](https://upload-images.jianshu.io/upload_images/5363507-795bae77d9bdaa88.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/5363507-a323513bfd7490cc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/5363507-d2256dd38a79c00e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

关键的方法就是：  ` elementData = Arrays.copyOf(elementData, newCapacity);` 把原来的数据copy到新的长度的集合中去，这个方法底层调用的就是 ：` System.arraycopy(original, 0, copy, 0,
                         Math.min(original.length, newLength));` ,这个方法的意思就是：从指定的源数组复制一个数组，从
指定位置，到目标数组的指定位置。可以这样理解，就是把一个集合放到一个新的集合中，只不过新的集合的长度比原来的长，扩容后的长度，先记住这个方法，后面我会提到
![image.png](https://upload-images.jianshu.io/upload_images/5363507-80abafbc4c2deb7f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
* **分析一下 add(int index, E element)**
![image.png](https://upload-images.jianshu.io/upload_images/5363507-27efffaf29934433.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

我们又看到这个方法:`  System.arraycopy(elementData, index, elementData, index + 1,
                         size - index);`  那么这下就得好好的理解一下，这几个参数的意思是：
Object src : 原数组
int srcPos : 从元数据的起始位置开始
Object dest : 目标数组
int destPos : 目标数组的开始起始位置
int length  : 要copy的数组的长度
* 假如我们有个ArrayList list.add(1) ,它的初始的长度是10 ，我们在 add（2,1），调用了这个方法，流程就是这样的 ，我们在原始数组的其实位置就是1，其实就是角标0，然后呢目标数组也是我们这个数组的起始位置的2，包含2，合并成一个新的集合，那么这个index=1的位置就空出来了呀，然后`elementData[index] = element;`,这样就插入进去了：通俗的说就是整体的元素往后移动，但是这个有个问题，当集合的数据量大的时候，这就很耗性能了  
* **ArrayList查找快，删除和增加慢**
#### ArrayList查找快，删除和增加慢，所以LinkedList就出来了 
它的特点就是，查找慢，增加快
![image.png](https://upload-images.jianshu.io/upload_images/5363507-ebbd505db1871eec.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image.png](https://upload-images.jianshu.io/upload_images/5363507-99524e198f73409c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* add就是改变节点连接的指向的问题
![image.png](https://upload-images.jianshu.io/upload_images/5363507-bc371b629c5f3be4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image.png](https://upload-images.jianshu.io/upload_images/5363507-9b83ea1abc4c17cb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* clear  这个我提一句，指向null，其实就是Java的回收的方法，GC扫描到null，就回收了，不会有什么幺蛾子，你看源码也是这样用的  
![image.png](https://upload-images.jianshu.io/upload_images/5363507-7fef7248da520c5d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
* LinkedList在首部和尾部添加、删除元素效率高效，在中间添加、删除元素效率较低
* LinkedList是一个以双向链表实现的List，它除了作为List使用，还可以作为队列或者堆栈使用

#### HashMap 其实在安卓上水土不服，因为内存对安卓端的性能尤为关键，所以有了SpareArray 这个出现 

**HashMap的主干是一个Entry数组。Entry是HashMap的基本组成单元，每一个Entry包含一个key-value键值对。（其实所谓Map其实就是保存了两个对象之间的映射关系的一种集合），在这里我不说明红黑树了，因为我想说明其他的一个问题**
![image.png](https://upload-images.jianshu.io/upload_images/5363507-66571d709397acc1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

HashMap由数组+链表组成的，数组是HashMap的主体，链表则是主要为了解决哈希冲突而存在的 ，那是不是有种可能性，Entry上存在空指向，导致内存的浪费，是的，你没有想错真的是的，有了 SparseArray

####  SparseArray  底层就是：  private int[] mKeys;  private Object[] mValues;
关注put方法，其实就是一个二分查找，找到位置，然后插入进去
![image.png](https://upload-images.jianshu.io/upload_images/5363507-7cbb191477284148.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/5363507-dbf136343d1f61ae.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
* get也是一个二分查找，这样子性能高很多 
![image.png](https://upload-images.jianshu.io/upload_images/5363507-2e80ee09aa40e5bb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
* 最为关键的是：SparseArray删除元素的时候，不会向前移动而是使用DELETED标记 
![image.png](https://upload-images.jianshu.io/upload_images/5363507-16b124c6c9b513ac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

*  容器在进行深拷贝的时候，每一项都得拷贝，对于对象也是这样的
![image.png](https://upload-images.jianshu.io/upload_images/5363507-1c714e0bc707360b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


##### HashMap 和 SparseArray的性能对比 
结果就是 ：HashMap申请10万个空间的话大约7.8m，而SparseArray大约在2.3M，取出数据HashMap需要110ms，而SparseArray在20ms左右 
![image.png](https://upload-images.jianshu.io/upload_images/5363507-9a967298432ee461.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/5363507-cfd46b411ffa9570.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

主要就是使用 AndroidStudio的工具 profiler 去查看

* **最后**
* 比较荣幸的是，我现在的小伙伴应该有看过ArrayList的源码，设计模式（状态设计模式、观察者设计模式）用的那叫一个6，我比较欣慰
* 当我们需要在一个MainActivity显示4个Fragment的时候，其实这个是固定的，所以使用相对于的性能会更加的好:  `PageModel[] pageModels1= new PageModel[]{};`
* [常用集合的原理分析](https://www.jianshu.com/p/a5f638bafd3b) 一起来看  










