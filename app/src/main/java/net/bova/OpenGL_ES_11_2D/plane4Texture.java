package net.bova.OpenGL_ES_11_2D;

import java.nio.FloatBuffer;


public class plane4Texture extends plane2Texture {
    public int ROWS, MAX;


    public plane4Texture(int width, int height, int cols, int rows, String path) {
        super(width, width, cols, path);

        ROWS= rows;
        MAX= COLS * ROWS;

        uvsBuffer= new FloatBuffer[MAX];

        float w = 1.0F / COLS, h = 1.0F / ROWS;
        int n = 0;
        float y = 0.0F, y2 = h;
        for (int r = 0; r < ROWS; r++) {
            float x = 0.0F;
            for (int c = 0; c < COLS; c++) {
                uvsBuffer[n++]= uvs(x, x + w, y, y2);
                x+= w;
            }
            y+= h;
            y2+= h;
        }
    }
}
