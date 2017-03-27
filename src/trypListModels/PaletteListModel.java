/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypListModels;

import javax.swing.AbstractListModel;
import trypResources.Palette;

/**
 *
 * @author amnesia
 */
public class PaletteListModel extends AbstractListModel
{
    Palette[] palettes;
    
    public void add(Palette P)
    {
        if(palettes != null)
        {
            Palette[] temp = new Palette[palettes.length+1];
            for(int i=0; i<palettes.length; i++)
            {
                temp[i]=palettes[i];
            }
            temp[temp.length-1]=P;
            palettes=temp;
        }
        else 
        {
            palettes = new Palette[1];
            palettes[0]=P;
        }
    }
    
    
    @Override
    public int getSize() 
    {
        if(palettes != null)
        {
            return palettes.length;
        }
        else return -1;
    }

    @Override
    public Object getElementAt(int index) 
    {
        if(palettes != null && index >=0 && index<palettes.length)
        {
            return palettes[index];
        }
        else return null;
    }
    
    public Palette[] getList()
    {
        return palettes;
    }
    
}
