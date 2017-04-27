/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypListModels;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import trypResources.Palette;

/**
 *
 * @author amnesia
 */
public class PaletteListModel extends AbstractListModel
{
    ArrayList<Palette> palettes;
    
    public PaletteListModel()
    {
        palettes = new ArrayList();
    }
    
    public void add(Palette P)
    {
        try
        {
            palettes.add(P);
        }
        catch(Exception e){}
    }
    
    public void addList(ArrayList<Palette> palettes)
    {
        try
        {
            for(Palette p : palettes)
            {
                this.palettes.add(p);
            }
        }
        catch(Exception e){}
    }
    
    @Override
    public int getSize() 
    {
        return palettes.size();
    }

    @Override
    public Object getElementAt(int index) 
    {
        try
        {
            return palettes.get(index);
        }
        catch(Exception e){return null;}
    }
    
    public ArrayList<Palette> getList()
    {
        return palettes;
    }
    
}
