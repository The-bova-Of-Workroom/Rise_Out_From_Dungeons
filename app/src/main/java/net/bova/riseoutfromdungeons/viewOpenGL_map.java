package net.bova.riseoutfromdungeons;

import android.annotation.SuppressLint;

import android.content.Context;

import android.opengl.GLES11;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import net.bova.OpenGL_ES_11_2D.deviceInfo;


public class viewOpenGL_map extends GLSurfaceView implements GLSurfaceView.Renderer {
    public int mainState = 0;
    public int count = 0;

    private messageBox mb;


    public viewOpenGL_map(Context context) {
        super(context);

        setEGLContextClientVersion(1);
        setPreserveEGLContextOnPause(true);
        setRenderer(this);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setZOrderOnTop(true);


        new tiled();
        new map();

        mb= new messageBox(7, 6);


    }

    @Override public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES11.glClearColor(0F,0F,0F,0F);
        GLES11.glClearDepthf(1.0F);
        GLES11.glEnable(GLES11.GL_DEPTH_TEST);
        GLES11.glDepthFunc(GLES11.GL_LEQUAL);
        GLES11.glHint(GLES11.GL_PERSPECTIVE_CORRECTION_HINT,GLES11.GL_NICEST);
        GLES11.glShadeModel(GLES11.GL_SMOOTH);
        GLES11.glDisable(GLES11.GL_DITHER);
        GLES11.glBlendFunc(GLES11.GL_SRC_ALPHA,GLES11.GL_ONE_MINUS_SRC_ALPHA);
        GLES11.glEnable(GLES11.GL_BLEND);
        GLES11.glCullFace(GLES11.GL_BACK);


        tiled.set();


        mainState= 88;

    }

    @Override public void onSurfaceChanged(GL10 gl10, int width, int height) {
        deviceInfo.set(width, height);

        GLES11.glViewport(0,0,width,height);

        GLES11.glMatrixMode(GLES11.GL_PROJECTION);
        GLES11.glLoadIdentity();

        GLES11.glOrthof(0,deviceInfo.WIDTHG, deviceInfo.HEIGHG,0, 0, 1);
        GLU.gluLookAt(gl10, 0F,0F,1F, 0F,0F,0F, 0F,1F,0F);

        GLES11.glMatrixMode(GLES11.GL_MODELVIEW);
        GLES11.glLoadIdentity();
    }

    @Override public void onDrawFrame(GL10 gl10) {
        GLES11.glClear(GLES11.GL_COLOR_BUFFER_BIT | GLES11.GL_DEPTH_BUFFER_BIT);


        switch (mainState) {
            case 0  :   return;
            case 1  :
                        break;
            case 2  :
                        if (map.loop(true)) mainState= 3;
                        break;
            case 3  :
                        map.draw();
                        break;
            case 4  :
                        map.draw();
                        if (mb.loop()) mainState= 5;
                        break;
            case 5  :
                        map.draw();
                        mb.draw();
                        break;
            case 6  :
                        if (map.loop(true)) mainState= 3;
                        mb.draw();
                        mb.incY();
                        break;
            case 9  :
                        if (map.loop(false)) {
                            if (map.level == 0) mainState= 10;
                            map.open();
                        }
                        break;
            case 10 :
                        map.draw();
                        break;
            case 88 :
                        map.init();
                        map.open();
                        mainState= 2;
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (mainState) {
            case 0  :   break;
            case 1  :
                        break;
            case 2  :
                        break;
            case 3  :
                        mb.open();
                        mainState= 4;
                        return false;
            case 4  :
                        break;
            case 5  :
                        map.open();
                        mainState= (map.level == 5) ? 9 : 6;
                        return false;
            case 6  :
            case 9  :
        }

        return true;
    }

}
