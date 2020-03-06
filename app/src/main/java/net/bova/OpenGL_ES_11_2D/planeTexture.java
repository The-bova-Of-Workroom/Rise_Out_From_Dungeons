package net.bova.OpenGL_ES_11_2D;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES11;
import android.opengl.GLUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL11;


public class planeTexture {
    public int WIDTH, HEIGH, WIDTHC, HEIGHC;
    public int id;
    public Bitmap bm;
    public FloatBuffer vertexBuffer;
    public FloatBuffer[] uvsBuffer;


    public planeTexture() {
    }

    public planeTexture(int width, int height, String path) {
        bm= loadBitmap(path);

        id= -1;

        size(width, height);

        uvsBuffer= new FloatBuffer[1];
        uvsBuffer[0]= uvs(0F, 1.0F, 0F, 1.0F);
    }

    public void size(int width, int height) {
        WIDTH= width;
        HEIGH= height;
        WIDTHC= WIDTH / 2;
        HEIGHC= HEIGH / 2;
        vertexBuffer= null;
        vertexBuffer= vertex(WIDTH, HEIGH);
    }

    public void set() {
        if (id != -1) {
            int[] d = new int[]{id, 0 };
            GLES11.glDeleteTextures(1,d,0);
        }

        id= loadTexture(bm);
    }

    public void draw(int x, int y, int ch) {
        GLES11.glEnable(GLES11.GL_TEXTURE_2D);
        GLES11.glEnableClientState(GLES11.GL_VERTEX_ARRAY);
        GLES11.glVertexPointer(3,GLES11.GL_FLOAT,0,vertexBuffer);

        GLES11.glEnableClientState(GLES11.GL_TEXTURE_COORD_ARRAY);
        GLES11.glTexCoordPointer(2,GLES11.GL_FLOAT,0,uvsBuffer[ch]);

        GLES11.glBindTexture(GLES11.GL_TEXTURE_2D,id);

        GLES11.glLoadIdentity();
        GLES11.glTranslatef(x,y,0);

        GLES11.glDrawArrays(GLES11.GL_TRIANGLES,0,6);

        GLES11.glDisableClientState(GLES11.GL_TEXTURE_COORD_ARRAY);
        GLES11.glDisableClientState(GLES11.GL_VERTEX_ARRAY);
        GLES11.glDisable(GLES11.GL_TEXTURE_2D);
    }

    public void draw(int x, int y) {
        draw(x, y, 0);
    }


    private FloatBuffer getBuffer(float[] buffer) {
        ByteBuffer bb = ByteBuffer.allocateDirect(buffer.length * 4);
        bb.order(ByteOrder.nativeOrder());

        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(buffer).position(0);

        return fb;
    }

    public FloatBuffer vertex(float width, float height) {
        float[] v = {
                0.0F,  0.0F,   0.0F,
                0.0F,  height, 0.0F,
                width, 0.0F,   0.0F,

                width, 0.0F,   0.0F,
                0.0F,  height, 0.0F,
                width, height, 0.0F    };

        return getBuffer(v);
    }

    public FloatBuffer uvs(float x, float x2, float y, float y2) {
        float[] u = {
                x,  y,
                x,  y2,
                x2, y,

                x2, y,
                x,  y2,
                x2, y2      };

        return getBuffer(u);
    }

    public Bitmap loadBitmap(String path) {
        try {
                BufferedInputStream bis = new BufferedInputStream(deviceInfo.context.getAssets().open(path));
                Bitmap bm = BitmapFactory.decodeStream(bis);
                bis.close();

                return bm;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private int loadTexture(Bitmap bm) {
        int[] c = new int[1];

        GLES11.glGenTextures(1,c,0);
        GLES11.glBindTexture(GL11.GL_TEXTURE_2D,c[0]);

        GLES11.glTexParameterf(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MIN_FILTER,GL11.GL_NEAREST);
        GLES11.glTexParameterf(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MAG_FILTER,GL11.GL_NEAREST);
        GLES11.glTexParameterf(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_S,GL11.GL_REPEAT);
        GLES11.glTexParameterf(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_T,GL11.GL_REPEAT);

        GLUtils.texImage2D(GL11.GL_TEXTURE_2D,0,bm,0);

        return c[0];
    }
}
