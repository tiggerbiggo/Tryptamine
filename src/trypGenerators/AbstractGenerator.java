package trypGenerators;

import trypParams.Parameter;
import tryptamine.DynamicCanvas;

/**
 *
 * @author timot_000
 */
public abstract class AbstractGenerator 
{
    
    public abstract DynamicCanvas draw(DynamicCanvas DC, int PaletteNum);
    
    public DynamicCanvas lineDraw(DynamicCanvas DC, boolean HV, int colorIncrement, int pos, int colorNum, int PaletteNum)
    {
        int x=0, y=0;
        
        if(HV) y=pos;
        else x=pos;
        
        while(DC.checkDimensions(x,y))
        {
            colorNum=DC.getPalette(PaletteNum).normalize(colorNum);
            DC.draw(x, y, PaletteNum, colorNum);
            colorNum+=colorIncrement;
            if(HV) x++;
            else y++;
        }
        return DC;
    }
    
}
