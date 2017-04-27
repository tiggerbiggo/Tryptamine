/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypGenerators;

import trypParams.Parameter;
import trypParams.paramType;
import trypResources.CircleModes;
import trypResources.Vector2;
import tryptamine.DynamicCanvas;

public class Gen_Polkadot extends AbstractGenerator
{

    int radius, offsetX, offsetY, colorOffset, colorSpeed, num;
    CircleModes mode;
    
    public Gen_Polkadot(Parameter[] params)throws IllegalArgumentException
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
        
        return DC;
    }
    
    public static Parameter[] constructParams(int radius, 
                                              int x, int y, 
                                              int colorOffset, 
                                              int colorSpeed,
                                              CircleModes mode)
    {
        Parameter[] params = {
            new Parameter(radius), 
            new Parameter(x), 
            new Parameter(y), 
            new Parameter(colorOffset), 
            new Parameter(colorSpeed), 
            new Parameter(mode)};
        return params;
    }
    
    public final boolean parseParams(Parameter[] params)
    {
        if(params != null && params.length == 6)
        {
            if(validateParams(params))
            {
                radius=params[0].getInt();
                offsetX=params[1].getInt();
                offsetY=params[2].getInt();
                colorOffset = params[3].getInt();
                colorSpeed = params[4].getInt();
                mode = params[5].getCircleMode();
                return true;
            }
        }
        
        return false;
    }

    public static boolean validateParams(Parameter[] params) 
    {
        return params != null &&
               params.length==6 &&
               params[0].getType()==paramType.INTEGER &&
               params[1].getType()==paramType.INTEGER &&
               params[2].getType()==paramType.INTEGER &&
               params[3].getType()==paramType.INTEGER &&
               params[4].getType()==paramType.INTEGER &&
               params[5].getType()==paramType.CIRCLEMODE;
    }

    
}
