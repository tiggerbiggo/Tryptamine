package tryptamine;

import java.util.ArrayList;
import trypGenerators.*;
import trypResources.Layer;

public class CanvasWriter 
{
    AbstractGenerator[] gens;
    
    public DynamicCanvas draw(DynamicCanvas DC, int[] PaletteNums)
    {
        
        
        return DC;
    }
    
    public static DynamicCanvas draw(DynamicCanvas DC, AbstractGenerator[] gens, int[] PaletteNums)
    {
        for(int i=0; i<gens.length; i++)
        {
            try
            {
                DC = gens[i].draw(DC, PaletteNums[0]);
            }
            catch(Exception e){}
        }
        return DC;
    }
    
    public static DynamicCanvas draw(DynamicCanvas DC, ArrayList<Layer> layers)
    {
        for(Layer L : layers)
        {
            try
            {
                int tmp = L.getPaletteNum();
                if(tmp<=-1) tmp = 0;
                DC = L.getGenerator().draw(DC, tmp);
            }
            catch(Exception e){}
        }
        return DC;
    }
    
    public static DynamicCanvas draw(DynamicCanvas DC, AbstractGenerator[] gens, int PaletteNum)
    {
        if(gens != null && 
                checkGenerators(gens))
        {
            for(int i=0; i<gens.length; i++)
            {
                DC = gens[i].draw(DC, PaletteNum);
            }
        }
        return DC;
    }
    
    public void setGenerators(AbstractGenerator[] gens)
    {
        this.gens = gens;
    }
    
    public static boolean checkGenerators(AbstractGenerator[] gens)
    {
        if(gens!= null)
        {
            for(AbstractGenerator g : gens)
            {
                if(g==null)
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
