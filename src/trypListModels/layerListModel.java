package trypListModels;

import javax.swing.AbstractListModel;
import trypResources.Layer;

/**
 *
 * @author amnesia
 */
public class LayerListModel extends AbstractListModel
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
    
    public boolean checkIndex(int index)
    {
        return index>=0 && index<getSize();
    }
    
    public void setElement(int index, Layer layer)
    {
        if(checkIndex(index) && layer != null)
        {
            layers[index] = layer;
        }
    }
    
    @Override
    public int getSize() 
    {
        if(layers != null)
        {
            return layers.length;
        }
        else return 0;
    }

    @Override
    public Object getElementAt(int index) 
    {
        if(layers != null && checkIndex(index))
        {
            return layers[index];
        }
        else return null;
    }
    
    public Layer[] getList()
    {
        return layers;
    }
    
    public void removeElement(int index)
    {
        if(checkIndex(index))
        {
            Layer[] tmpLayer = new Layer[layers.length-1];
            for(int i=0;i<layers.length-1;i++)
            {
                if(i<index)
                {
                    tmpLayer[i]=layers[i];
                }
                else
                {
                    tmpLayer[i]=layers[i+1];
                }
            }
            layers=tmpLayer;
        }
    }
}
