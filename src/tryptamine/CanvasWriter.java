package tryptamine;

import trypGenerators.*;
import trypParams.*;
import trypResources.Formula;
import trypResources.Function;

public class CanvasWriter 
{
    AbstractGenerator[] gens;
    
    public DynamicCanvas draw(DynamicCanvas DC, int[] PaletteNums)
    {
        if(gens != null && 
                PaletteNums != null && 
                PaletteNums.length>=gens.length)
        {
            for(int i=0; i<gens.length; i++)
            {
                DC = gens[i].draw(DC, PaletteNums[i]);
            }
        }
        else System.out.println("nope");
        
        
        return DC;
    }
    
    public static DynamicCanvas draw(DynamicCanvas DC, AbstractGenerator[] gens, int[] PaletteNums)
    {
        if(gens != null && 
                checkGenerators(gens) &&
                PaletteNums != null &&
                PaletteNums.length>=gens.length)
        {
            for(int i=0; i<gens.length; i++)
            {
                DC = gens[i].draw(DC, PaletteNums[0]);
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
