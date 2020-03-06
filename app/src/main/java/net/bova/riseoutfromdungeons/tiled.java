package net.bova.riseoutfromdungeons;

import net.bova.OpenGL_ES_11_2D.plane4Texture;


public class tiled {
    public static plane4Texture p4t;


    public tiled() {
        p4t= new plane4Texture(16, 16, 16, 6, "TILE_16x16-16x6.png");
    }

    public static void set() {
        p4t.set();
    }

    public static int CTOX(int col) {
        return col << 4;
    }
    public static int RTOY(int row) {
        return row << 4;
    }
    public static int XTOC(int x) {
        return x >> 4;
    }
    public static int YTOR(int y) {
        return y >> 4;
    }

    public static void draw(int c, int r, int ch) {
        if (c != 0) p4t.draw(CTOX(c), RTOY(r), ch);
    }

    public static void print(int c, int r, String s) {
        int y = RTOY(r);

        for (int cc = 0; cc < s.length(); cc++) p4t.draw(CTOX(c++), y, s.charAt(cc));
    }

    public static void print(int c, int r, int integer) {
        String s = "" + integer;
        int y = RTOY(r);

        for (int cc = s.length() - 1; cc > -1; cc--) p4t.draw(CTOX(c--), y, s.charAt(cc));
    }
}
