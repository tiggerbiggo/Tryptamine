package tryptamine;

import trypGenerators.*;
import trypParams.*;
import trypResources.Formula;
import trypResources.Function;

public class CanvasWriter 
{
    Generator[] gens;
    
    public DynamicCanvas draw(DynamicCanvas DC)
    {
        
        
        for(Generator G : gens)
        {
            DC = G.draw(DC);
        }
        return DC;
    }
    
    public static DynamicCanvas draw(DynamicCanvas DC, Generator[] gens)
    {
        for(Generator G : gens)
        {
            DC = G.draw(DC);
        }
        return DC;
    }
    
    public void setGenerators(Generator[] gens)
    {
        this.gens = gens;
    }
}
