package trypGenerators;

import trypParams.Parameter;
import tryptamine.DynamicCanvas;

/**
 *
 * @author timot_000
 */
public abstract class Generator 
{
    
    public abstract DynamicCanvas draw(DynamicCanvas DC);
    
    public DynamicCanvas lineDraw(DynamicCanvas DC, boolean HV, int colorIncrement, int pos, int colorNum)
    {
        int x=0, y=0;
        
        if(HV) y=pos;
        else x=pos;
        
        while(DC.checkDimensions(x,y))
        {
            colorNum=DC.getPalette(0).normalize(colorNum);
            DC.draw(x, y, 0, colorNum);
            colorNum+=colorIncrement;
            if(HV) x++;
            else y++;
        }
        return DC;
    }
    
}
