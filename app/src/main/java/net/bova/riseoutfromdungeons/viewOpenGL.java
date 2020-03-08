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


public class viewOpenGL extends GLSurfaceView implements GLSurfaceView.Renderer {
    public int mainState = 0;
    public int count = 0;

    private messageBox mb;


    public viewOpenGL(Context context) {
        super(context);

        setEGLContextClientVersion(1);
        setPreserveEGLContextOnPause(true);
        setRenderer(this);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setZOrderOnTop(true);


        new tiled();

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

        GLES11.glOrthof(0, deviceInfo.WIDTHG, deviceInfo.HEIGHG,0, 0, 1);
        GLU.gluLookAt(gl10, 0F,0F,1F, 0F,0F,0F, 0F,1F,0F);

        GLES11.glMatrixMode(GLES11.GL_MODELVIEW);
        GLES11.glLoadIdentity();
    }

    @Override public void onDrawFrame(GL10 gl10) {
        GLES11.glClear(GLES11.GL_COLOR_BUFFER_BIT | GLES11.GL_DEPTH_BUFFER_BIT);


        tiled.print(10, 1, count+= 100);
        tiled.print(10, 2, "S:"+mainState);
        switch (mainState) {
            case 0  :   return;
            case 1  :
                        break;
            case 2  :
                        break;
            case 3  :
                        if (mb.loop()) mainState= 4;
                        break;
            case 4  :
                        mb.draw();
                        break;
            case 88 :
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
                        mb.open();
                        mainState= 3;
                        return false;
            case 3  :
                        break;
            case 4  :
                        mainState= 2;
                        return false;

        }

        return true;
    }

}
