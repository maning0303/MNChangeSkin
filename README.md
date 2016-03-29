# AndroidChangeSkinDemo
        夜间模式的测试Demo，通过Theme实现(attrs.xml+styles.xml+Activity.setTheme())

##实现步骤：
#### 1.自定义属性
        <attr name="app_background" format="reference|color" />

#### 2.定义两种主题，一个白天，一个夜间
        <!--白天主题-->
        <style name="DayTheme" parent="@android:style/Theme.Light.NoTitleBar">
            <!--测试-->
            <item name="app_background">@color/backgroundColor</item>
        </style>
        <!--黑夜主题-->
        <style name="NightTheme" parent="@android:style/Theme.Light.NoTitleBar">
            <!--测试-->
            <item name="app_background">@color/backgroundColor_night</item>
        </style>

#### 3.布局中使用：
        android:background="?app_background"

#### 注意：
        1.需要实现夜间模式的控件都要在attr里面添加，然后在两种主题都写上才可以。
        2.XML写不了的就手动获取当前主题，手动设置（部分ListView里面可能需要手动设置）：
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

##效果展示：
![](https://github.com/maning0303/AndroidChangeSkinDemo/raw/master/screenshots/001.gif)
