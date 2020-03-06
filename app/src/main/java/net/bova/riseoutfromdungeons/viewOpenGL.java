package net.bova.riseoutfromdungeons;

import android.content.Context;

import android.opengl.GLES11;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import net.bova.OpenGL_ES_11_2D.deviceInfo;
import net.bova.OpenGL_ES_11_2D.plane2Texture;
import net.bova.OpenGL_ES_11_2D.plane4Texture;


public class viewOpenGL extends GLSurfaceView implements GLSurfaceView.Renderer {
    private plane2Texture p2t;
    private plane4Texture p4t;
    private int count;


    public viewOpenGL(Context context) {
        super(context);

        setEGLContextClientVersion(1);
        setPreserveEGLContextOnPause(true);
        setRenderer(this);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setZOrderOnTop(true);


        new tiled();


        p2t= new plane2Texture(36, 5, "CAMP-FIRE-36x57-5.png");
        p4t= new plane4Texture(8, 8, 16, 6, "TILE_16x16-16x6.png");


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


        p2t.set();
        p4t.set();


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


        tiled.print(24, 1, "" + count + " COUNT");
        tiled.print(24, 2, count+= 100);


        for (int c = 0; c < p2t.COLS; c++) p2t.draw(c * p2t.WIDTH, 80, c);

        int cc = 0;
        for (int r = 0; r < p4t.ROWS; r++)
            for (int c = 0; c < p4t.COLS; c++) p4t.draw(c * p4t.WIDTH, r * p4t.HEIGH, cc++);

        cc= 0;
        for (int r = 5; r < tiled.p4t.ROWS + 5; r++)
            for (int c = 0; c < tiled.p4t.COLS; c++) tiled.draw(c * 2, r * 2, cc++);

    }
}
