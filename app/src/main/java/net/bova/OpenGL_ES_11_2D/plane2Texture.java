package net.bova.OpenGL_ES_11_2D;

import java.nio.FloatBuffer;


public class plane2Texture extends planeTexture {
    public int COLS;


    public plane2Texture(int width, int cols, String path) {
        super();

        init(width, cols, path);

        HEIGH= bm.getHeight();

        size(WIDTH, HEIGH);

        uvsBuffer= new FloatBuffer[COLS];

        float w = 1.0F / COLS, x = 0F;
        for (int c = 0; c < COLS; c++) {
            uvsBuffer[c]= uvs(x, x + w, 0F, 1.0F);
            x+= w;
        }
    }

    public plane2Texture(int width, int height, int cols, String path) {
        super();

        init(width, cols, path);

        HEIGH= height;

        size(WIDTH, HEIGH);
    }

    private void init(int width, int cols, String path) {
        bm= loadBitmap(path);
        id= -1;
        WIDTH= width;
        COLS= cols;
    }
}
