/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import trypResources.ActionCodes;

/**
 *
 * @author amnesia
 */
public class Viewport implements ActionListener
{
    JFrame JF_Viewport = new JFrame("Viewport");
    
    JButton JB_Render = new JButton("Render Image");
    JButton JB_Refresh = new JButton("Refresh");
    
    ArrayList<BufferedImage> images;
    int currentImage = 0;
    
    public void setImages(ArrayList<BufferedImage> images) {
        this.images = images;
        currentImage = 0;
        drawImage();
    }

    public void addImage(BufferedImage toAdd) {

        try
        {
            images.add(toAdd);
        }
        catch(Exception e){}
    }

    

    public int getImageIndex()
    {
        return currentImage;
    }
    
    public void setImageIndex(int index) 
    {
        try
        {
            currentImage = normalize(index, images.size());
        }
        catch(Exception e){}
        
    }
    
    public boolean imagesExist()
    {
        return images!=null;
    }
    

    public int normalize(int toNormalize, int normal) 
    {
        while (toNormalize >= normal) 
        {
            toNormalize -= normal;
        }
        while (toNormalize < 0) 
        {
            toNormalize += normal;
        }
        return toNormalize;
    }

    public boolean checkGraphics()
    {
        return JF_Viewport.getGraphics() != null;
    }
    
    public void drawImage() 
    {
        
        try
        {
            JF_Viewport.getGraphics().drawImage(images.get(currentImage), 0, 0, null);
            JB_Render.repaint();
            JB_Refresh.repaint();
        }
        catch(Exception e){}
    }
    
    public void initGUI(ActionListener A) 
    {
        JF_Viewport.setLayout(null);
        JF_Viewport.setBounds(10, 10, 500, 500);
        JF_Viewport.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JF_Viewport.setResizable(false);
        JF_Viewport.setLocationRelativeTo(null);
        JF_Viewport.setVisible(false);
        
        JB_Render.setBounds(0, 440, 250, 30);
        JB_Render.addActionListener(A);
        JF_Viewport.add(JB_Render);
        
        JB_Refresh.setBounds(250, 440, 250, 30);
        JB_Refresh.addActionListener(A);
        JF_Viewport.add(JB_Refresh);
    }
    
    public int getWidth()
    {
        return JF_Viewport.getWidth();
    }
    
    public int getHeight()
    {
        return JF_Viewport.getHeight();
    }
    
    public void show()
    {
        JF_Viewport.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int checkActions(Object toCheck)
    {
        if(toCheck == JB_Render) return ActionCodes.CODE_VIEWPORT_RENDER;
        else if(toCheck == JB_Refresh) return ActionCodes.CODE_VIEWPORT_REFRESH;
        else return ActionCodes.NULLCODE;
    }
}
