/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypListModels;

import javax.swing.AbstractListModel;
import trypResources.Layer;

/**
 *
 * @author amnesia
 */
public class layerListModel extends AbstractListModel
{
    Layer[] layers;
    
    public void add(Layer L)
    {
        if(layers != null)
        {
            Layer[] temp = new Layer[layers.length+1];
            for(int i=0; i<layers.length; i++)
            {
                temp[i]=layers[i];
            }
            temp[temp.length-1]=L;
            layers=temp;
        }
        else 
        {
            layers = new Layer[1];
            layers[0]=L;
        }
    }
    
    
    @Override
    public int getSize() 
    {
        if(layers != null)
        {
            return layers.length;
        }
        else return -1;
    }

    @Override
    public Object getElementAt(int index) 
    {
        if(layers != null && index >=0 && index<layers.length)
        {
            return layers[index];
        }
        else return null;
    }
    
    public Layer[] getList()
    {
        return layers;
    }
    
}
