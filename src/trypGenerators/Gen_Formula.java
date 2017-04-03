package trypGenerators;

import trypParams.Parameter;
import trypParams.paramType;
import trypResources.Formula;
import tryptamine.DynamicCanvas;

/**
 *
 * @author timot_000
 */
public class Gen_Formula extends AbstractGenerator
{
    
    boolean HV;
    boolean dir;
    int colorSpeed;
    int[] gaps;
    Formula F;
    
    public Gen_Formula(Parameter[] params) throws IllegalArgumentException
    {
        if(parseParams(params))
        {
            //all is well
        }
        else throw new IllegalArgumentException("INVALID PARAMETERS");
    }
    
    
    @Override
    public DynamicCanvas draw(DynamicCanvas DC, int PaletteNum) 
    {
        
        int startPos, maxPos;
        if(HV) maxPos = DC.getY();
        else maxPos = DC.getX();
        
        
        if(!dir) colorSpeed = Math.abs(colorSpeed)*-1; //Squished :)
        int n=0;
        int c=0;
        for(int i=0; i<maxPos; i++)
        {
            startPos=(int)Math.round(F.recursiveCalc(i));
            
            
            
            if(n>=gaps.length)
            {
                n=0;
            }
            if(c>=gaps[n])
            {
                c=0;
                n++;
                DC = lineDraw(DC, HV, colorSpeed, i, startPos, PaletteNum);
            }
            else
            {
                c++;
            }
        }
        return DC;
    }
    
    public static Parameter[] constructParams(boolean HV, boolean dir, int colorSpeed, int[] gaps, Formula F)
    {
        Parameter[] params = {new Parameter(HV), new Parameter(dir), new Parameter(colorSpeed), new Parameter(gaps), new Parameter(F)};
        
        return params;
    }
    
    public final boolean parseParams(Parameter[] params)
    {
        if(params != null && params.length == 5)
        {
            if(     params[0].getType()==paramType.BOOLEAN && 
                    params[1].getType()==paramType.BOOLEAN && 
                    params[2].getType()==paramType.INTEGER &&
                    params[3].getType()==paramType.INTARRAY &&
                    params[4].getType()==paramType.FORMULA )
            {
                HV=params[0].getBoolean();
                dir=params[1].getBoolean();
                colorSpeed = params[2].getInt();
                gaps=params[3].getIntArray();
                F=params[4].getFormula();
                return true;
            }
        }
        
        return false;
    }
    
}
