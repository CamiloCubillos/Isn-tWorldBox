package isntworldbox.drawing.canvas;

import isntworldbox.drawing.controller.DrawController;
import isntworldbox.drawing.tmr.TMR;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JComponent;

public class Canvas extends JComponent {
    private int width;
    private int height;
    public Graphics2D painter;
    private BufferedImage image;
    public Brush brush;
    private TMR tmr;
    public HashMap<String,Terrain> TERRAINS;
    
    public Canvas(int _width, int _height){
        width = _width;
        height = _height;
        tmr = new TMR(_height, _width);
        loadTerrainsData();
        init();
    }
    
    private void loadTerrainsData(){
        TERRAINS = new HashMap<String,Terrain>();
        TERRAINS.put("WATER", new Terrain(0,new Color(0,0,255)));
        TERRAINS.put("GRASS", new Terrain(1,new Color(0,255,0)));
    }
    
    private void init(){        
        image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        painter = image.createGraphics();
        painter.setColor(new Color(50,70,231));
        painter.fill(new Rectangle2D.Double(0,0,width,height));

        MouseAdapter mouseHandler = new DrawController(this);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);     
        
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0,null);
    }
    
    public TMR getTMR(){
        return tmr;
    }
}
