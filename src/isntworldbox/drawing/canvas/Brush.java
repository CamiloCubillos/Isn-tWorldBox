package isntworldbox.drawing.canvas;

import java.awt.Color;

public class Brush {
    public Terrain terrain;
    public int size;
    
    public Brush(){
        terrain = new Terrain(1,new Color(0,255,0));
        size = 10;
    }
    
    public Brush(int _size, Terrain _terrain){
        terrain = _terrain;
        size = _size;
    }

}
