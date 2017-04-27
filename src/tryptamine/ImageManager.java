/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tryptamine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author amnesia
 */
public class ImageManager 
{
    public static BufferedImage constructImage(DynamicCanvas DC, int x, int y)
    {
        BufferedImage img = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<x; i++)
        {
            for(int k=0; k<y; k++)
            {
                Color C = DC.getColor(i, k);
                if(C != null) img.setRGB(i, k, C.getRGB());
            }
        }
        return img;
    }
    
    public static void constructImage(DynamicCanvas DC, int x, int y, Graphics G)
    {
        BufferedImage img = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<x; i++)
        {
            for(int k=0; k<y; k++)
            {
                G.setColor(DC.getColor(i, k));
                G.drawLine(i-1, k-1, i-1, k-1);
            }
        }
    }
    
    public static ArrayList<BufferedImage> constructSequence(DynamicCanvas DC)
    {
        try
        {
            int pNum = DC.getPalette(0).getNum()-1;

            ArrayList<BufferedImage> ImageSequence = new ArrayList();
            for(int o=0; o<=pNum; o++)
            {


                ImageSequence.add(ImageManager.constructImage(DC, DC.getX(), DC.getY()));
                DC.cycleAll(1);
            }
            return ImageSequence;
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    public static ArrayList<BufferedImage> constructSequence(DynamicCanvas DC, int num)
    {
        try
        {
            ArrayList<BufferedImage> ImageSequence = new ArrayList();
            for(int o=0; o<=num; o++)
            {


                ImageSequence.add(ImageManager.constructImage(DC, DC.getX(), DC.getY()));
                DC.cycleAll(1);
            }
            return ImageSequence;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
