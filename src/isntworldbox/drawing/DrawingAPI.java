
package isntworldbox.drawing;

import isntworldbox.drawing.canvas.Brush;
import isntworldbox.drawing.canvas.Canvas;
import isntworldbox.drawing.canvas.Terrain;

public class DrawingAPI {
    private Canvas canvas;
    
    public DrawingAPI(){}
    
    public void createCanvas(int _width, int _height){
        canvas = new Canvas(_width, _height);
    }
    
    public void setBrushSize(int _size){
        canvas.brush.size = _size;
    }
    
    public void setBrushTerrain(Terrain _terrain){
        canvas.brush.terrain = _terrain;
    }
    
    public void setBrush(Brush _brush){
        canvas.brush = _brush;
    }
    
    public Canvas getCanvas(){
        return canvas;
    }
    
}
