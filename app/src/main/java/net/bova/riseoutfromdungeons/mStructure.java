package net.bova.riseoutfromdungeons;

import android.content.res.AssetManager;

import net.bova.OpenGL_ES_11_2D.deviceInfo;

import java.io.BufferedInputStream;
import java.io.IOException;


public class mStructure {
    public int COLS, ROWS, MAX;
    public byte[] m;


    public mStructure() {
        m= null;
    }

    public void alloc() {
        if (m != null) m= null;
        MAX= COLS * ROWS;
        m= new byte[MAX];
    }

    public boolean load(String path) {
        AssetManager am = deviceInfo.context.getAssets();
        try {
                BufferedInputStream bis = new BufferedInputStream(am.open(path));
                StringBuffer sb = new StringBuffer();

                int ch;
                while ((ch= bis.read()) != ',') sb.append((char)ch);
                COLS= Integer.parseInt(sb.toString());

                sb.delete(0, sb.length());
                while ((ch= bis.read()) != '\n') sb.append((char)ch);
                ROWS= Integer.parseInt(sb.toString());

                alloc();
                bis.read(m,0,MAX);

                bis.close();
                return false;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
