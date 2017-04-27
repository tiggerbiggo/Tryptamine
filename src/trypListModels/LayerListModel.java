/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypListModels;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import trypResources.Layer;

/**
 *
 * @author amnesia
 */
public class LayerListModel extends AbstractListModel
{
    ArrayList<Layer> layers;
    
    public LayerListModel()
    {
        layers = new ArrayList();
    }
    
    public void add(Layer L)
    {
        layers.add(L);
    }
    
    
    public void removeElement(int index)
    {
        try {layers.remove(index);}
        catch(Exception e){}
    }
    
    public void setElement(int index, Layer L)
    {
        try{layers.set(index, L);}
        catch(Exception e){}
    }
    
    @Override
    public int getSize() 
    {
        return layers.size();
    }

    @Override
    public Object getElementAt(int index) 
    {
        try
        {
            return layers.get(index);
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    public ArrayList<Layer> getList()
    {
        return layers;
    }
    
}
