package net.bova.OpenGL_ES_11_2D;

import android.content.Context;

import android.os.SystemClock;


public class deviceInfo {
    public static int WIDTHD, HEIGHD, WIDTHDC, HEIGHDC, WIDTHG, HEIGHG, WIDTHGC, HEIGHGC;
    public static Context context;


    public deviceInfo(int gameWidth, int gameHeight, Context context) {
        this.context= context;

        WIDTHG= gameWidth;
        HEIGHG= gameHeight;
        WIDTHGC= WIDTHG / 2;
        HEIGHGC= HEIGHG / 2;

        WIDTHD= 0;
    }

    public static void setDevice(int width, int height) {
        if (WIDTHD != 0) return;

        WIDTHD= width;
        HEIGHD= height;
        WIDTHDC= WIDTHD / 2;
        HEIGHDC= HEIGHD / 2;
    }

    public static long getTime() {
        return SystemClock.uptimeMillis();
    }
    public static void sleep(long millis) {
        SystemClock.sleep(millis);
    }

}
