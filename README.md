# 这是一个日常练习的程序

# TabLayout
介绍：

源码路径：
`android.support.design.widget.TabLayout.java`

`TabLayout provides a horizontal layout to display tabs.`
简单翻译下：TabLayout提供一个水平的布局来显示tabs。


## 一. 引入依赖库：
`compile 'com.android.support:design:26.0.0-alpha1'`

## 二. 使用TabLayout

### 第一种方法：
使用`TabLayout.newTab()`来创建Tab
```
    <android.support.design.widget.TabLayout
        android:id="@+id/tab_layout1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

    </android.support.design.widget.TabLayout>
```

```
        mTabLayout1.addTab(mTabLayout1.newTab().setText("item1"));
        mTabLayout1.addTab(mTabLayout1.newTab().setText("item2"));
        mTabLayout1.addTab(mTabLayout1.newTab().setText("item3"));
        mTabLayout1.addTab(mTabLayout1.newTab().setText("item4"));
```

### 第二种方法：
使用`TabItem`

```
    <android.support.design.widget.TabLayout
        android:id="@+id/tab_layout2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <android.support.design.widget.TabItem
            android:id="@+id/tab_item1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="tab_item1"/>

        <android.support.design.widget.TabItem
            android:id="@+id/tab_item2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="tab_item2"/>

        <android.support.design.widget.TabItem
            android:id="@+id/tab_item3"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="tab_item3"/>

        <android.support.design.widget.TabItem
            android:id="@+id/tab_item4"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="tab_item4"/>

    </android.support.design.widget.TabLayout>
```

### 第三种方法：
绑定ViewPager，调用方法：`setupWithViewPager(ViewPager)`，重写`PagerAdapter`的`getPageTitle`方法后，就可以添加导航的item。

```
    <android.support.design.widget.TabLayout
        android:id="@+id/tab_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:tabIndicatorColor="@color/red"
        app:tabSelectedTextColor="@color/red"
        app:tabTextColor="@color/black"
        app:tabIndicatorHeight="@dimen/tab_indicator_height"
        app:tabTextAppearance="@style/MyTabLayoutTextAppearance"
        />

    <android.support.v4.view.ViewPager
        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        />
```

Activity代码：

```
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private List<String> mTitleList = Arrays.asList("1","2","3","4","5","6","7","8");
    private List<Fragment> mFragmentList = new ArrayList<>();
    private TabFragmentPagerAdapter mTabFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        initFragmentViewPager();
    }

    private void initFragmentViewPager(){
        mFragmentList.add(new Fragment1());
        mFragmentList.add(new Fragment2());
        mFragmentList.add(new Fragment3());
        mFragmentList.add(new Fragment4());
        mFragmentList.add(new Fragment1());
        mFragmentList.add(new Fragment2());
        mFragmentList.add(new Fragment3());
        mFragmentList.add(new Fragment4());
        mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(
                getSupportFragmentManager(),
                mTitleList,
                mFragmentList);

        mViewPager.setAdapter(mTabFragmentPagerAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);

    }
```
Adapter:

```
    private List<String> mTitles;
    private List<Fragment> mFragments;

    public TabFragmentPagerAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragments) {
        super(fm);
        mTitles = titles;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
```

## 三 设置TabLayout的自定义属性
**设置指示器indicator的颜色：**

`app:tabIndicatorColor="@color/red"`

**设置指示器indicator的高度：**

`app:tabIndicatorHeight="@dimen/tab_indicator_height"`

**设置tab的字体颜色：**

`app:tabTextColor="@color/black"`

**设置tab选中的字体颜色：**

`app:tabSelectedTextColor="@color/red"`

**设置tab的字体大小：**

`app:tabTextAppearance="@style/MyTabLayoutTextAppearance"`

其中，MyTabLayoutTextAppearance为自定义的style：

```
    <style name="MyTabLayoutTextAppearance" parent="TextAppearance.Design.Tab">
        <item name="android:textSize">@dimen/tab_text_size</item>
    </style>
```

**添加icon：**

`android:icon="@mipmap/ic_launcher"`

或者

`mTabLayout1.addTab(mTabLayout1.newTab().setText("item1").setIcon(R.mipmap.ic_launcher));`

**设置TabLayout为可以滑动：**

`app:tabMode="scrollable"`

或者

`mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);`

默认是fixed：固定的，标签很多时候会被挤压，不能滑动。

**tab内部的padding：**

```
        app:tabPadding="2dp"
        app:tabPaddingTop="2dp"
        app:tabPaddingBottom="2dp"
        app:tabPaddingStart="2dp"
        app:tabPaddingEnd="2dp"
```

**内容的显示模式：**

`app:tabGravity="center"`

center表示居中，如果是fill，则是充满。

**tab的宽度：**

```
app:tabMinWidth="120dp"
app:tabMaxWidth="120dp"
```

**TabLayout开始位置的偏移量：**

`app:tabContentStart="20dp"`

demo地址：
https://github.com/YoungBear/Hello

参考:

http://www.jianshu.com/p/2b2bb6be83a8

http://www.jianshu.com/p/ce1d060573ba#



# TableLayout
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

# DragView

## View的位置参数
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

# AndroidStudio插件ButterKnife
　　Android Butterknife Zelezny这个插件，可以以图形化的操作添加Butterkinfe注解。而Butterkinfe注解可以代替完成view的findViewById的操作，这样会加快开发速度。

使用步骤：

1. 在AndroidStudio上安装Android Butterknife Zelezny插件，完成后重启AS；
2. 导入butterknife.jar或者在build.gradle添加compile 'com.jakewharton:butterknife:7.0.0'
3. 在layout资源文件上点击右键，选择Generate 然后选择 Generate ButterKnife Injections，生成对话框，在里边选择需要的控件，可以对其进行命名，添加onClick监听。

![](https://github.com/avast/android-butterknife-zelezny/blob/master/img/zelezny_animated.gif)

# BroadcastReceiver

## 只能通过动态注册的广播接收器：

You cannot receive this through components declared in manifests, only by explicitly registering for it with{@link Context#registerReceiver(BroadcastReceiver, IntentFilter) Context.registerReceiver()}

```
//Intent.java
public static final String ACTION_SCREEN_OFF = "android.intent.action.SCREEN_OFF";//屏幕关闭
public static final String ACTION_SCREEN_ON = "android.intent.action.SCREEN_ON";//屏幕点亮
public static final String ACTION_TIME_TICK = "android.intent.action.TIME_TICK";//每分钟触发一次
```

# Get Time
　　Android获取时间：(以下时间单位都是毫秒)

## System.currentTimeMillis()：

　　该时间是基于世界时间的，它返回的是从January 1, 1970 00:00:00 UTC到现在时间已经逝去了多少毫秒，当我设置Android手机的系统时间时，会应该影响该值。

## SystemClock.uptimeMillis()：

　　它表示的是手机从启动到现在的运行时间，且不包括系统sleep(CPU关闭)的时间，很多系统的内部时间都是基于此。

## SystemClock.elapsedRealtime()：

　　它表示的是手机从启动到现在的运行时间，且包括系统sleep(CPU关闭)的时间。

## SystemClock.currentThreadTimeMillis()：

　　在当前线程中已运行的时间。

# Android通过Intent.ACTION_CLOSE_SYSTEM_DIALOGS监听Home按键事件

参考：http://blog.csdn.net/qiantujava/article/details/50581026

　　应用层不能直接监听HOME键，而只能使用广播监听。
在每次点击Home按键时都会发出一个action为Intent.ACTION_CLOSE_SYSTEM_DIALOGS的广播，它是关闭系统Dialog的广播，我们可以通过注册它来监听Home按键消息。

# 监听网络变化

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

# 使用反射获取Android Properties

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

# Android Stuio 多渠道打包并签名

参考：

http://www.cnblogs.com/gao-chun/p/4891275.html

http://unclechen.github.io/2015/10/22/Android-Studio-Gradle%E5%AE%9E%E8%B7%B5%E4%B9%8B%E5%A4%9A%E6%B8%A0%E9%81%93%E8%87%AA%E5%8A%A8%E5%8C%96%E6%89%93%E5%8C%85+%E7%89%88%E6%9C%AC%E5%8F%B7%E7%AE%A1%E7%90%86/

http://stormzhang.com/devtools/2015/01/15/android-studio-tutorial6/

# 获取应用信息

获取所有应用：

```
    public static List<ResolveInfo> getAllApps(Context context) {
        List<ResolveInfo> appList = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        appList.addAll(pm.queryIntentActivities(mainIntent, 0));
        return appList;
    }
```

根据包名获取应用信息：

```
    public static PackageInfo getPackageInfo(Context context, String packageName) {
        try {
            if (context == null) {
                return null;
            }
            return context.getPackageManager().getPackageInfo(
                    packageName, PackageManager.GET_ACTIVITIES);
        } catch (Exception e) {
            return null;
        }
    }
```

自定义封装AppInfo，并获取相关信息：

```
    public class AppInfo{

        public String packageName;
        public String versionName;
        public int versionCode;
        public Drawable icon;
        public String appName;
    }

    public static AppInfo getAppInfo(Context context, String packageName) {
        if (!TextUtils.isEmpty(packageName)) {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = getPackageInfo(context, packageName);
            if (packageInfo != null) {
                AppInfo appInfo = new AppInfo();
                appInfo.packageName = packageInfo.packageName;
                appInfo.versionName = packageInfo.versionName;
                appInfo.versionCode = packageInfo.versionCode;
                appInfo.appName = packageInfo.applicationInfo.loadLabel(pm).toString();
                appInfo.icon = packageInfo.applicationInfo.loadIcon(pm);
                return appInfo;
            }
        }
        return null;
    }

```

# StrictMode

Android 2.3(API Level 9) 提供了一个称为严苛模式(StrictMode)的调试特性，Google称该特性已经使数百个Android上的Google应用程序受益。那它都做了什么呢？它将报告与线程与虚拟机相关的策略违例。一旦检测到策略违例(policy violation)，你将获得警告，其包含了一个栈trace显示你的应用在何处发生违例。你可以强制用警告代替崩溃(crash)，也可以仅将警告计入日志，让你的应用继续执行。

官方文档：https://developer.android.com/reference/android/os/StrictMode.html

StrictMode is a developer tool which detects things you might be doing by accident and brings them to your attention so you can fix them.

StrictMode意思为严格模式，是用来检测程序中违例情况的开发者工具。

StrictMode is most commonly used to catch accidental disk or network on the application's main thread, where UI operations are received and animations take place.

最常用的场景就是检测主线程中本地磁盘和网络读写等耗时的操作。

Keeping disk and network operations off the main thread makes for much smoother, more responsive applications. By keeping your application's main thread responsive, you also prevent ANR dialogs from being shown to users.

在子线程做磁盘和网络操作，可以让应用更加平滑，及时响应。这样也可以避免ANR。

参考：

http://droidyue.com/blog/2015/09/26/android-tuning-tool-strictmode/
http://blog.csdn.net/brokge/article/details/8543145

用严格模式，系统检测出主线程违例的情况会做出相应的反应，如日志打印，弹出对话框亦或者崩溃等。换言之，严格模式会将应用的违例细节暴露给开发者方便优化与改善。

注意：我们只需要在app的开发版本下使用 StrictMode，线上版本避免使用 StrictMode，随意需要通过 诸如 DEVELOPER_MODE 这样的配置变量来进行控制。

StrictMode通过策略方式来让你自定义需要检查哪方面的问题。主要有两种策略：

## 两种策略
1. 线程策略(ThreadPolicy)
2. 虚拟机策略(VMPolicy)

### ThreadPolicy
线程策略检测的内容有：

- 自定义的耗时调用，使用detectCustomSlowCalls()开启
- 磁盘读取操作，使用detectDiskReads()开启
- 磁盘写入操作，使用detectDiskWrites()开启
- 网络操作，使用detectNetwork()开启

### VMPolicy
虚拟机策略检测的内容有：

- Activity泄漏，使用detectActivityLeaks()开启
- 未关闭的Closable对象泄漏，使用detectLeakedClosableObjects()开启
- 泄露的Sqlite对象，使用detectLeakedSqlLiteObjects()开启
- 检测实例数量，使用setClassInstanceLimit()开启

## 如何使用StrictMode

严格模式的开启可以放在Application或者Activity以及其他组件的onCreate方法。为了更好地分析应用中的问题，建议放在Application的onCreate方法中。

```
    if (IS_DEBUG && SDK_INT >= GINGERBREAD) {
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
              .detectAll() //
              .penaltyLog() //
              .penaltyDeath() //
              .build());

      StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
              .detectAll()
              .penaltyLog()
              .build());
    }

```

严格模式需要在debug模式开启，不要在release版本中启用。

同时，严格模式自API 9 开始引入，某些API方法也从 API 11 引入。使用时应该注意 API 级别。

如有需要，也可以开启部分的严格模式。

## 查看结果

严格模式有很多种报告违例的形式，但是想要分析具体违例情况，还是需要查看日志，终端下过滤StrictMode就能得到违例的具体stacktrace信息。

`adb logcat | grep StrictMode`


## 检测内存泄漏

通常情况下，检测内存泄露，我们需要使用MAT对heap dump 文件进行分析，这种操作不困难，但也不容易。使用严格模式，只需要过滤日志就能发现内存泄露。

这里以Activity为例说明，首先我们需要开启对检测Activity泄露的违例检测。使用上面的detectAll或者detectActivityLeaks()均可。其次写一段能够产生Activity泄露的代码。

```
public class LeakyActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.sLeakyActivities.add(this);
    }
}
```
MyApplication中关于sLeakyActivities的部分实现

```
public class MyApplication extends Application {
  public static final boolean IS_DEBUG = true;
    public static ArrayList<Activity> sLeakyActivities = new ArrayList<Activity>();

}
```

当我们反复进入LeakyActivity再退出，过滤StrictMode就会得到这样的日志

```
E/StrictMode( 2622): class com.example.strictmodedemo.LeakyActivity; instances=2; limit=1
E/StrictMode( 2622): android.os.StrictMode$InstanceCountViolation: class com.example.strictmodedemo.LeakyActivity; instances=2; limit=1
E/StrictMode( 2622):    at android.os.StrictMode.setClassInstanceLimit(StrictMode.java:1)

```

在我的demo中，使用[LeakCanary](https://github.com/square/leakcanary "")官方的内存泄漏sample，即在Activity的onCreate方法中，使用匿名内部类，启动一个AsyncTask任务，这样，当Activity执行到onDestry的时候，该任务并没有停止，并且保存着一个该Activity的强引用，这样就会导致内存泄漏。

```
    private void startAsyncTask() {
        // This async task is an anonymous class and therefore has a hidden reference to the outer
        // class MainActivity. If the activity gets destroyed before the task finishes (e.g. rotation),
        // the activity instance will leak.
        new AsyncTask<Void, Void, Void>() {
            @Override protected Void doInBackground(Void... params) {
                // Do some slow work in background
                SystemClock.sleep(20000);
                return null;
            }
        }.execute();
    }
```

```
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strict_mode);
        ButterKnife.bind(this);

        enabledStrictMode();
    }

    private void enabledStrictMode() {
        if (IS_DEBUG && SDK_INT >= GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                    .detectAll() //
                    .penaltyLog() //
                    .penaltyDeath() //
                    .build());

            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }
    }

    @OnClick(R.id.async_task)
    public void onClick() {
        startAsyncTask();
    }
```

操作：进入StrictModeActivity中，点击按钮，然后在旋转屏幕，这时候Activity会重启，onDestroy方法执行了，但是由于匿名内部类仍然保留着Activity的一个强引用，导致资源不能释放，就多了一个Activity实例，造成了内存泄漏。通过`adb logcat | grep StrictMode`，可以得到相关的log：

```
01-10 17:16:35.501 3778-3778/com.example.hello E/StrictMode: class com.example.hello.activity.StrictModeActivity; instances=2; limit=1
        android.os.StrictMode$InstanceCountViolation: class com.example.hello.activity.StrictModeActivity; instances=2; limit=1
        at android.os.StrictMode.setClassInstanceLimit(StrictMode.java:1)

```

这样，开发者就可以定位到这个Activity,去优化或者解决内存泄漏的问题。



