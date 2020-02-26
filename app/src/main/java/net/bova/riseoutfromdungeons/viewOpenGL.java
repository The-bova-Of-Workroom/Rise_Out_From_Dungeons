package net.bova.riseoutfromdungeons;

import android.content.Context;

import android.opengl.GLES11;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class viewOpenGL extends GLSurfaceView implements GLSurfaceView.Renderer {

    public viewOpenGL(Context context) {
        super(context);

        setEGLContextClientVersion(1);
        setPreserveEGLContextOnPause(true);
        setRenderer(this);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setZOrderOnTop(true);

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

    }

    @Override public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES11.glViewport(0,0,width,height);

        GLES11.glMatrixMode(GLES11.GL_PROJECTION);
        GLES11.glLoadIdentity();

        GLES11.glOrthof(0,320, 240,0, 0, 1);
        GLU.gluLookAt(gl10, 0F,0F,1F, 0F,0F,0F, 0F,1F,0F);

        GLES11.glMatrixMode(GLES11.GL_MODELVIEW);
        GLES11.glLoadIdentity();

    }

    @Override public void onDrawFrame(GL10 gl10) {
        GLES11.glClear(GLES11.GL_COLOR_BUFFER_BIT | GLES11.GL_DEPTH_BUFFER_BIT);


    }
}
