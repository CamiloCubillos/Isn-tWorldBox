package isntworldbox;

import isntworldbox.drawing.DrawingAPI;
import isntworldbox.drawing.canvas.Brush;
import isntworldbox.drawing.canvas.Canvas;
import isntworldbox.drawing.canvas.Terrain;
import javax.swing.*;
import java.awt.Color;
import java.awt.*;  
import java.awt.event.*;  
import java.util.Set;


public class IsnTWorldBox{    
    
    public static void main(String[] args) {
        int width = 1280;
        int height = 720;
        
        JFrame jFrame = new JFrame();
        DrawingAPI dapi = new DrawingAPI();
        dapi.createCanvas(width, height);
        dapi.setBrush(new Brush(10,dapi.getCanvas().TERRAINS.get("WATER")));
        
        // Create brush's terrain ComboBox
        
        Set<String> terrains = dapi.getCanvas().TERRAINS.keySet();
        JComboBox terrainsBox = new JComboBox(terrains.toArray());
        terrainsBox.setBounds(10, 10, 100, 40);
        terrainsBox.addActionListener(new ActionListener(){
            int size;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == terrainsBox) {
                    dapi.setBrushTerrain(dapi.getCanvas().TERRAINS.get(terrainsBox.getSelectedItem()));
                }
            }
        });
        jFrame.add(terrainsBox);
        
        // Create brush's size ComboBox
        
        Integer[] sizes = {10,20,50,80};
        JComboBox sizeBox = new JComboBox(sizes);
        sizeBox.setBounds(10, 60, 100, 40);
        sizeBox.addActionListener(new ActionListener(){
           int size;
           @Override           
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == sizeBox) {
                    size = Integer.parseInt(sizeBox.getSelectedItem().toString());
                    dapi.setBrushSize(size);
                }}            
        });
        jFrame.add(sizeBox);
        
        jFrame.setSize(width, height + 26);
        jFrame.setTitle("Isn'tWorldBox");
        jFrame.add(dapi.getCanvas());
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);   

    }


}
