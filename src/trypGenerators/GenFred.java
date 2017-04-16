/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypGenerators;

import tryptamine.DynamicCanvas;

/**
 *
 * @author tiggerbiggo
 */
public class GenFred extends AbstractGenerator
{

    @Override
    public DynamicCanvas draw(DynamicCanvas DC, int PaletteNum) 
    {
        int fredX = 20 ;
        int fredY = 20;
        for(int i=fredX;i<20;i++)
        {
            DC.draw(i,fredY,0,i-10);
            fredX+=1;
            System.out.println(i);
        }
        for (int i=fredY;i<20;i++)
        {
            DC.draw(fredX,i,0,i);
            fredY++;
            System.out.println(i);
        }
        for (int i=fredX;i>10;i--)
        {
            DC.draw(i,fredY,0,-i);
            fredX--;
        }
        for (int i=fredY;i>10;i--)
        {
            DC.draw(fredX,i,0,-i);
            fredY--;
            System.out.println(i);
        }
        return DC;
    }
    
    
}
