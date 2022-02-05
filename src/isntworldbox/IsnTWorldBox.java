package isntworldbox;

import isntworldbox.drawing.DrawingAPI;
import isntworldbox.drawing.canvas.Brush;
import isntworldbox.drawing.canvas.Canvas;
import isntworldbox.drawing.canvas.Terrain;
import javax.swing.JFrame;
import java.awt.Color;

public class IsnTWorldBox {

    public static void main(String[] args) {
        int width = 1280;
        int height = 720;
        
        JFrame jFrame = new JFrame();
        DrawingAPI dapi = new DrawingAPI();
        dapi.createCanvas(width, height);
        dapi.setBrush(new Brush(10,dapi.getCanvas().TERRAINS.get("GRASS"))); 
        
        jFrame.setSize(width, height + 26);
        jFrame.setTitle("Isn'tWorldBox");
        jFrame.add(dapi.getCanvas());
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);   
    }
    
}
