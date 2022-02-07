package isntworldbox.drawing.controller;

import isntworldbox.drawing.canvas.Canvas;
import isntworldbox.drawing.tmr.TMR;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class DrawController extends MouseAdapter{
    
    //private Vector<Point> realPoints = new Vector<Point>();
    //private Vector<Point> sensePoints = new Vector<Point>();
    
    
    private Queue<Point> LMP;  // Store each point register on Mosue Dragging to recreate loss data on drawing path
    private Canvas canvas;
    private Point currPoint = new Point();
    
    public DrawController(Canvas _canvas){
        canvas = _canvas;
        LMP = new LinkedList<Point>();
    }
            
    @Override
    public void mousePressed(MouseEvent e){
        currPoint.setLocation(e.getPoint());
        if(canvas.brush != null){
            canvas.painter.setColor(canvas.brush.terrain.color);
            canvas.painter.setStroke(new BasicStroke(canvas.brush.size));
        }else{
            // DEFAULT BRUSH PROPERTIES
            canvas.painter.setColor(Color.BLACK);
            canvas.painter.setStroke(new BasicStroke(10));
        }   
        canvas.painter.drawLine(currPoint.x,currPoint.y, e.getX(), e.getY());
        canvas.repaint();
        LMP.add((Point) currPoint.getLocation().clone());
        System.out.println("presionado");
    }
            
    @Override
    public void mouseDragged(MouseEvent e){              
        canvas.painter.drawLine(currPoint.x,currPoint.y, e.getX(), e.getY());
        currPoint.setLocation(e.getPoint());
        LMP.add((Point) currPoint.getLocation().clone());
        calculateLossPoints();
        //System.out.println(String.format("[ %s , %s ]", currPoint.x,currPoint.y));
        //realPoints.add((Point) currPoint.clone());
        canvas.repaint();
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
        System.out.println("liberado");
        LMP.clear();
        /*
        System.out.println("Real points:");
        for (Point realPoint : realPoints) {
            System.out.println(String.format("[ %s , %s ]", realPoint.getX(), realPoint.getY()));
        }
        System.out.println("Sense points:");
        for(Point sensePoint : sensePoints){
            System.out.println(String.format("[ %s , %s ]", sensePoint.getX(), sensePoint.getY()));
        }
        calculateLossPoints();
        sensePoints.clear();
        realPoints.clear();*/
        System.out.println(canvas.getTMR().getTerrainID(0, 0));
        
    }
    
    private void calculateLossPoints(){
        Point p1 = LMP.poll();
        Point p2;
        if(!LMP.isEmpty()){
            p2 = LMP.peek();
        }else{
            p2 = p1;
        }
        Double b = p1.getY();
        Double m = (p2.getY() - p1.getY())/(p2.getX() - p1.getX());
        if(m.isInfinite()){
            m = 0.0;
        }
        
        if(m != 0.0){
            for(double x = p1.getX(); x <= p2.getX(); x++){
                Point fixedPoint = new Point();
                int xCoord = (int) x;
                int yCoord = (int) (m*xCoord + b);
                fixedPoint.setLocation(xCoord, yCoord);
                
                TMR tmr = canvas.getTMR();
                if((xCoord >= 0 && xCoord < tmr.cols) && (yCoord >= 0 && yCoord < tmr.rows)){
                    tmr.setTerrain(yCoord, xCoord, canvas.brush.terrain.id);
                }
            }           
        }else{
            for(double y = p1.getY(); y <= p2.getY(); y++){
                Point fixedPoint = new Point();
                int xCoord = (int) p2.getX();
                int yCoord = (int) y;
                fixedPoint.setLocation(xCoord, yCoord);
                //sensePoints.add(fixedPoint);
                TMR tmr = canvas.getTMR();
                if((xCoord >= 0 && xCoord < tmr.cols) && (yCoord >= 0 && yCoord < tmr.rows)){
                    tmr.setTerrain(yCoord, xCoord, canvas.brush.terrain.id);
                }
                
            }    
        }

    }
}
