package net.bova.riseoutfromdungeons;

import net.bova.OpenGL_ES_11_2D.plane4Texture;


public class tiled {
    public static plane4Texture p4t;

    private static int DOT;


    public tiled(int dot) {
        DOT= dot;
        p4t= new plane4Texture(16, 16, 16, 6, "TILE_16x16-16x6.png");
    }

    public static int DOTX(int x) {
        return x << DOT;
    }
    public static int DOTY(int y) {
        return y << DOT;
    }

    public static void draw(int col, int row, int c) {
        if (c != 0) p4t.draw(DOTX(col), DOTY(row), c);
    }
}
