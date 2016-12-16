#这是一个日常练习的程序

##TableLayout
Tablelayout类以行和列的形式对控件进行管理，每一行为一个TableRow对象，或一个View控件。

当为TableRow对象时，可在TableRow下添加子控件，默认情况下，每个子控件占据一列。

当为View时，该View将独占一行。


TableRow中的控件不能指定宽度，宽度永远是match_parent。

列的宽度由该列所有行的一个单元格决定。

shrinkColumns="0,1" 表示第0列和第1列可以收缩

stretchColumns="0,1" 表示第0列和第1列可以拉伸

collapseColumns="0,1" 表示隐藏第0列和第1列，相当于gone，不占空间

layout_span="2" 表示该控件占据两列的空间

words：

row 行

column 列

shrink 收缩

stretch 拉伸

##DragView

###View的位置参数
参考：
http://blog.csdn.net/jason0539/article/details/42743531

　　View的位置主要由它的四个顶点来决定，分别对应于View的四个属性：top,left,right,bottom。在Android中，x轴和y轴的正方向分别为右和下，不仅仅是Android，大部分显示系统都是按照这个标准来定义坐标系的。

　　从Android3.0开始，View增加了额外几个参数：x，y，translationX，translationY。其中x和y是View左上角的坐标，而translationX和translationY是View左上角相对于父容器的偏移量。这几个参数也是相对于父容器的坐标，并且translationX和translationY的默认值是0，和View的四个基本的位置参数一样，View也为它们提供了get/set方法，这个急参数的换算关系如下所示：

　　　　　　　　x = left + translationX

　　　　　　　　y = top + translationY

　　需要注意的是，View在平移的过程中，top和left表示的是原始左上角的位置信息，其值并不会发生改变，此时发生改变的是x，y，translationX和translationY这四个参数。

![](http://img.blog.csdn.net/20150115155321445?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvamFzb24wNTM5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

　　View获取自身坐标：getLeft(),getTop(),getRight(),getBottom()

　　View获取自身宽高：getHeight(),getWidth()

　　motionEvent获取坐标：getX(),getY(),getRawX(),getRawY()

　　getX()：获取点击事件相对控件左边的x轴坐标，即点击事件距离控件左边的距离

　　getY()：获取点击事件相对控件顶边的y轴坐标，即点击事件距离控件顶边的距离

　　getRawX()：获取点击事件相对整个屏幕左边的x轴坐标，即点击事件距离整个屏幕左边的距离

　　getRawY()：获取点击事件相对整个屏幕顶边的y轴坐标，即点击事件距离整个屏幕顶边的距离

　　实现一个跟手滑动的效果，自定义View，拖动它可以让它在整个屏幕上随意滑动。重写它的onTouchEvent方法并处理ACTION_MOVE事件，根据两次滑动之间的距离就可以实现他的滑动了。这里采用动画的方式。

```
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取点击事件的坐标，在屏幕中的坐标，使用getRawX()/getRawY()
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.d(TAG, "move, deltaX: " + deltaX + ", deltaY: " + deltaY);
                int translationX = (int) (getTranslationX() + deltaX);
                int translationY = (int) (getTranslationY() + deltaY);
                //使用位移动画
                setTranslationX(translationX);
                setTranslationY(translationY);
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
        }
        //记录上次的坐标
        mLastX = x;
        mLastY = y;
        return true;
    }
```

　　通过上述代码可以看出，这一全屏滑动的效果实现起来相当简单。首先，通过getRawX和getRawY方法来获取手指当前的坐标，注意不能使用getX和getY方法，因为这个是要全屏滑动的，所以需要获取当前点击事件在屏幕中的坐标而不是相当于View本身的坐标；其次，我们要得到两次滑动之间的位移，有了这个位移就可以移动当前的View，移动方法采用View的setTranslationX和setTranslationY，只能在Android3.0及其以上版本上使用。

##AndroidStudio插件ButterKnife
　　Android Butterknife Zelezny这个插件，可以以图形化的操作添加Butterkinfe注解。而Butterkinfe注解可以代替完成view的findViewById的操作，这样会加快开发速度。

使用步骤：

1. 在AndroidStudio上安装Android Butterknife Zelezny插件，完成后重启AS；
2. 导入butterknife.jar或者在build.gradle添加compile 'com.jakewharton:butterknife:7.0.0'
3. 在layout资源文件上点击右键，选择Generate 然后选择 Generate ButterKnife Injections，生成对话框，在里边选择需要的控件，可以对其进行命名，添加onClick监听。

![](https://github.com/avast/android-butterknife-zelezny/blob/master/img/zelezny_animated.gif)

##BroadcastReceiver

###只能通过动态注册的广播接收器：

You cannot receive this through components declared in manifests, only by explicitly registering for it with{@link Context#registerReceiver(BroadcastReceiver, IntentFilter) Context.registerReceiver()}

```
//Intent.java
public static final String ACTION_SCREEN_OFF = "android.intent.action.SCREEN_OFF";//屏幕关闭
public static final String ACTION_SCREEN_ON = "android.intent.action.SCREEN_ON";//屏幕点亮
public static final String ACTION_TIME_TICK = "android.intent.action.TIME_TICK";//每分钟触发一次
```

##Get Time
　　Android获取时间：(一下时间单位都是毫秒)

###System.currentTimeMillis()：

　　该时间是基于世界时间的，它返回的是从January 1, 1970 00:00:00 UTC到现在时间已经逝去了多少毫秒，当我设置Android手机的系统时间时，会应该影响该值。

###SystemClock.uptimeMillis()：

　　它表示的是手机从启动到现在的运行时间，且不包括系统sleep(CPU关闭)的时间，很多系统的内部时间都是基于此。

###SystemClock.elapsedRealtime()：

　　它表示的是手机从启动到现在的运行时间，且包括系统sleep(CPU关闭)的时间。

###SystemClock.currentThreadTimeMillis()：

　　在当前线程中已运行的时间。

##Android通过Intent.ACTION_CLOSE_SYSTEM_DIALOGS监听Home按键事件

参考：http://blog.csdn.net/qiantujava/article/details/50581026

　　应用层不能直接监听HOME键，而只能使用广播监听。
在每次点击Home按键时都会发出一个action为Intent.ACTION_CLOSE_SYSTEM_DIALOGS的广播，它是关闭系统Dialog的广播，我们可以通过注册它来监听Home按键消息。

##监听网络变化

　　可以注册BroadcastReceiver来监听，网络状态变化的时候，系统会自动发广播ConnectivityManager.CONNECTIVITY_ACTION即"android.net.conn.CONNECTIVITY_CHANGE"，我们可以静态注册，也可以动态初注册。

```
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "onReceiver, intent: " + intent.toString());
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                int networkType = intent.getExtras().getInt(ConnectivityManager.EXTRA_NETWORK_TYPE);
                //引起网络变化的type
                Log.d(TAG, "networkType: " + networkType);
                //TYPE_MOBILE:0, TYPE_WIFI:1
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    Log.d(TAG, "has connected, + typeName: " + networkInfo.getTypeName());
                } else {
                    Log.d(TAG, "has lost network, networkType: " + networkType);
                }


            }
        }
    };
```
　　可以使用`int networkType = intent.getExtras().getInt(ConnectivityManager.EXTRA_NETWORK_TYPE);`来获取引起网络变化的类型，其中Mobil为0，Wifi为1。可以通过`ConnectivityManager.getActiveNetworkInfo();`来获取当前可以使用的网络，如果为null则表示没有连接的网络。否则，如果`NetworkInfo.isConnected()`返回为true，则表示网络已连接，可以通过`NetworkInfo.getTypeName()`获取其类型。

别忘了添加权限：
`<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`

##使用反射获取Android Properties

```
    public static String getSystemProperty(String key) {
        Class<?> classType = null;
        Method getMethod = null;
        String ret = null;
        try {
            if (classType == null) {
                classType = Class.forName("android.os.SystemProperties");
                getMethod = classType.getDeclaredMethod("get", String.class);
            }
            ret = (String) getMethod.invoke(classType, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
```
