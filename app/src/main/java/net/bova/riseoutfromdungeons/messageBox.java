package net.bova.riseoutfromdungeons;


public class messageBox {
    private mStructure mb;
    private int line, x, xe, y, ye, ym;


    public messageBox(int c, int r) {
        mb= new mStructure();

        x= c;
        y= ym= r;

        mb.load("BOX.m+");
    }

    public void draw() {
        int cc = line;

        for (int r = y; r < ye; r++)
            for (int c = x; c < xe; c++) tiled.draw(c, r, mb.m[cc++]);
    }

    public void open() {
        line= mb.MAX;

        xe= x + mb.COLS;
        y= ye= ym;
    }

    public boolean loop() {
        draw();

        if (line == 0) return true;
        line-= mb.COLS;
        ye++;

        return false;
    }

    public void incY() {
        y++;
        ye++;
    }
}
