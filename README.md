# AndroidChangeSkinDemo

        夜间模式的测试Demo，通过Theme实现(attrs.xml+styles.xml+Activity.setTheme())

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

#### 注意（具体的看代码）：
```java

        1.最好设置一个BaseActivity,继承它：
                public class BaseActivity extends Activity {
                        //当前Activity的主题
                        public int skinBase;
                        @Override
                        protected void onCreate(Bundle savedInstanceState) {
                                //设置主题
                                SkinManager.onActivityCreateSetSkin(this);
                                super.onCreate(savedInstanceState);
                                //获取当前的主题
                                skinBase = SkinManager.getCurrentSkinType(this);
                        }
                }
                
        2.需要实现夜间模式的控件都要在attr里面添加，然后在两种主题都写上才可以。
        
        3.XML写不了的就手动获取当前主题，手动设置（部分ListView里面可能需要手动设置）：
                int currentSkinType = SkinManager.getCurrentSkinType(SettingActivity.this);
                if (SkinManager.THEME_DAY == currentSkinType) {
                    SkinManager.changeSkin(SettingActivity.this, SkinManager.THEME_NIGHT);
                    btn_checked.setBackColorRes(R.color.kswBackColor_night);
                    btn_checked.setThumbColorRes(R.color.kswThumbColor_night);
                } else {
                    SkinManager.changeSkin(SettingActivity.this, SkinManager.THEME_DAY);
                    btn_checked.setBackColorRes(R.color.kswBackColor);
                    btn_checked.setThumbColorRes(R.color.kswThumbColor);
                }
                
        4.广播的作用---主要防止设置界面太深，而设置界面之前的页面改不了（更换主题必须重启Activity才能有效果），如果更改主题的按钮在首页面，就没有必要使用注册广播了。
        
```


## 效果展示：
![](https://github.com/maning0303/AndroidChangeSkinDemo/raw/master/screenshots/001.gif)
