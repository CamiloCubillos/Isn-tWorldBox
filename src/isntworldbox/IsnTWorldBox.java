package isntworldbox;

import isntworldbox.drawing.DrawingAPI;
import isntworldbox.drawing.canvas.Brush;
import isntworldbox.drawing.canvas.Canvas;
import isntworldbox.drawing.canvas.Terrain;
import javax.swing.*;
import java.awt.Color;
import java.awt.*;  
import java.awt.event.*;  


public class IsnTWorldBox{    
    
    public static void main(String[] args) {
        int width = 1280;
        int height = 720;
        
        JFrame jFrame = new JFrame();
        DrawingAPI dapi = new DrawingAPI();
        dapi.createCanvas(width, height);
        dapi.setBrush(new Brush(10,dapi.getCanvas().TERRAINS.get("WATER")));
        
        jFrame.setSize(width, height + 26);
        String[] terrains = {"WATER", "GRASS", "LAVA", "MOUNTAIN", "SAND", "JUNGLEGRASS", "SNOW", "NOTHING"};
        JComboBox comboBox = new JComboBox(terrains);
        comboBox.setBounds(10, 10, 100, 40);
        jFrame.add(comboBox);
        Integer[] sizes = {10,20,50,80};
        JComboBox sizeBox = new JComboBox(sizes);
        sizeBox.setBounds(10, 60, 100, 40);
        jFrame.add(sizeBox);

        //Click listener
        comboBox.addActionListener(new ActionListener(){
            int size;
            @Override
            
            public void actionPerformed(ActionEvent e) {
                size = Integer.parseInt(sizeBox.getSelectedItem().toString());
                if (e.getSource() == comboBox) {
                    dapi.setBrush(new Brush(size ,dapi.getCanvas().TERRAINS.get(comboBox.getSelectedItem())));
                }
            }
        });
        sizeBox.addActionListener(new ActionListener(){
           int size;
           @Override           
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == sizeBox) {
                        size = Integer.parseInt(sizeBox.getSelectedItem().toString());
                        dapi.setBrush(new Brush(size ,dapi.getCanvas().TERRAINS.get(comboBox.getSelectedItem())));
                }}            
        });
        
        jFrame.setTitle("Isn'tWorldBox");
        jFrame.add(dapi.getCanvas());
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);   

    }


}
