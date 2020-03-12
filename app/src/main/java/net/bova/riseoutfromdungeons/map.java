package net.bova.riseoutfromdungeons;


public class map {
    public static mStructure ms;
    public static int men, level, score;

    private static int line, ye, i;
    private static boolean b;


    public map() {
        ms= new mStructure();
        ms.load("MAP.m+");
    }

    public static void init() {
        line= ms.MAX - 22 * ms.COLS;
        b= true;
        ye= 3;
        level= 0;
        men= 5;
        score= 0;

    }

    public static void draw() {
        tiled.print(2, 0, "SCORE:       MEN: " + men + " LEVEL: " + level);
        tiled.print(13, 0, score);
        score++;

        int cc = line;
        for (int r = 2; r < ye; r++) {
            for (int c = 0; c < ms.COLS; c++) tiled.draw(c, r, ms.m[cc++]);
        }
    }

    public static void open() {
        i= 1;
    }

    public static boolean loop(boolean is) {
        draw();

        if (i++ > 22) {
            if (b) { b= false;  ye--;       }
            level+= (is) ? 1 : -1;
            return true;
        }

        line+= (is) ? -ms.COLS : +ms.COLS;

        if (b) ye++;

        return false;
    }

}
