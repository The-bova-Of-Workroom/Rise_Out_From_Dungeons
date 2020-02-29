package net.bova.riseoutfromdungeons;

import android.app.Activity;

import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;

import net.bova.OpenGL_ES_11_2D.deviceInfo;


public class MainActivity extends Activity {
    private viewOpenGL view;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        new deviceInfo( 640, 384, this);

        view= new viewOpenGL(this);
        setContentView(view);
    }

    @Override public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            View decor = getWindow().getDecorView();
            int type = getWindow().getDecorView().getSystemUiVisibility();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) type |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) type |= View.SYSTEM_UI_FLAG_FULLSCREEN;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) type |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decor.setSystemUiVisibility(type);
        }
    }

    @Override protected void onResume() {
        super.onResume();

        view.onResume();
    }

    @Override protected void onPause() {
        super.onPause();

        view.onPause();
    }

    @Override  protected void onDestroy() {
        super.onDestroy();
    }

    @Override public void onBackPressed() {
        super.onBackPressed();
    }


}
