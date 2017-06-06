# AndroidChangeSkinDemo

### Android夜间模式，通过Theme实现(attrs.xml+styles.xml+Activity.setTheme())
[![](https://jitpack.io/v/maning0303/MNChangeSkin.svg)](https://jitpack.io/#maning0303/MNChangeSkin)

## 效果展示：
![](https://github.com/maning0303/MNChangeSkin/raw/master/screenshots/001.gif)

## 如何添加
### Gradle添加：
#### 1.在Project的build.gradle中添加仓库地址

``` gradle
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

#### 2.在Module目录下的build.gradle中添加依赖
``` gradle
	dependencies {
	     compile 'com.github.maning0303:MNChangeSkin:V1.0.0'
	}
```


## 实现步骤：

##### 1.自定义属性
```java
        <attr name="app_background" format="reference|color" />
```

##### 2.定义两种主题，一个白天，一个夜间
```java
        <!--白天主题-->
        <style name="DayTheme" parent="@android:style/Theme.Light.NoTitleBar">
            <item name="app_background">@color/backgroundColor</item>
        </style>
        <!--黑夜主题-->
        <style name="NightTheme" parent="@android:style/Theme.Light.NoTitleBar">
            <item name="app_background">@color/backgroundColor_night</item>
        </style>
```

##### 3.布局中使用：
```java

        android:background="?app_background"
        
```

#### 代码设置：
```java

        1.再Application的onCreate() 中初始化:
                //设置白天主题的和夜间主题
                SkinManager.setThemeID(R.style.DayTheme, R.style.NightTheme);

        2.新建一个BaseActivity,所有Activity继承它：
                public class BaseActivity extends Activity {
                        @Override
                        protected void onCreate(Bundle savedInstanceState) {
                                //设置主题
                                SkinManager.onActivityCreateSetSkin(this);
                                super.onCreate(savedInstanceState);
                        }
                }

        3.XML文件中设置不了的就获取当前主题，手动设置：
                int currentSkinType = SkinManager.getCurrentSkinType(this);
                if (SkinManager.THEME_DAY == currentSkinType) {
                    //改变颜色...
                } else {
                    //改变颜色...
                }
                
        4.广播的作用---主要防止设置界面太深，而设置界面之前的页面改不了（更换主题必须重启Activity才能有效果），如果更改主题的按钮在首页面，就没有必要使用注册广播了。
                public void registerSkinReceiver() {
                    skinBroadcastReceiver = new SkinBroadcastReceiver() {
                        @Override
                        public void onChangeSkin(int currentTheme) {
                            Log.i("onChangeSkin", "广播来了" + currentTheme);
                            //重启Activity
                            recreate();
                        }
                    };
                    SkinManager.registerSkinReceiver(OtherActivity.this, skinBroadcastReceiver);
                }

                public void unregisterSkinReceiver() {
                    if (skinBroadcastReceiver != null) {
                        SkinManager.unregisterSkinReceiver(this, skinBroadcastReceiver);
                    }
                }

        5.改变主题:
                //改变主题:自动根据当前主题改变白天和黑夜
                SkinManager.changeSkin(this);
                //改变主题:指定主题
                //SkinManager.changeSkin(this,R.style.DayTheme);
                //重启当前Activity
                startActivity(new Intent(this, SettingActivity.class));
                //重要:做一个自定义动画,避免出现闪烁现象
                overridePendingTransition(com.maning.themelibrary.R.anim.mn_theme_activity_enter, com.maning.themelibrary.R.anim.mn_theme_activity_exit);
                finish();


        6.关于WebView设置夜间模式:
                webView.setWebViewClient(new WebViewClient() {
                            ...

                            @Override
                            public void onPageFinished(WebView view, String url) {
                                super.onPageFinished(view, url);
                                //设置webView,这里是设置夜间模式的颜色
                                String backgroudColor = "#232736";
                                String fontColor = "#626f9b";
                                String urlColor = "#9AACEC";
                                SkinManager.setColorWebView(webView, backgroudColor, fontColor, urlColor);
                            }

                            ...
                        });

        
```

#### 详情见Demo

### 上线项目使用:
#### [GanKMM](https://github.com/maning0303/GankMM)

