# CaptchaGenerator
Easy to implement Captcha Control

To implement in your project modify your app build.gradle and add below lines and sync.

```
repositories {
    jcenter()
}

dependencies {
    compile 'rr.captachcontrollibrary:captachcontrollibrary:0.0.1'
}
```

Ones successfully synced, you can add captcha control to your layout.
```
<rr.captachcontrollibrary.CaptchaControl
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:id="@+id/captcha_control" />
```

Initialize the control in your activity or fragment as you do with other widgets and for checking captcha enterred is correct or not use

isCaptchaValid() method, which will return true if entered captcha is correct, false otherwise. Also everytime you call this method, captcha text will be refreshed. 

New captcha image will be generated with different color and based on background color darknesss text color will be either black or white.
