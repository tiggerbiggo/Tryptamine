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

public class Gen_Circular extends AbstractGenerator
{

    int r, x, y, colorOffset, colorSpeed;
    CircleModes mode;
    
    public Gen_Circular(Parameter[] params)throws IllegalArgumentException
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
        for(int i=0; i<=r; i++)
        {
            if(mode == CircleModes.FAN)
            {
                DC = drawFan(DC, x, y, i);
            }
            else if(mode == CircleModes.PULSE)
            {
                
                DC=drawCircle(DC, x+1, y, i, PaletteNum, (i+colorOffset)*colorSpeed);
                DC=drawCircle(DC, x, y+1, i, PaletteNum, (i+colorOffset)*colorSpeed);
                DC=drawCircle(DC, x-1, y, i, PaletteNum, (i+colorOffset)*colorSpeed);
                DC=drawCircle(DC, x, y-1, i, PaletteNum, (i+colorOffset)*colorSpeed);
            }
            
        }
        return DC;
    }
    
    /**It's not actually that bad :/
     *
     * @param DC
     * @param x
     * @param y
     * @return
     */
    public DynamicCanvas drawFan(DynamicCanvas DC, int x, int y, int r)
    {
        int H=DC.getX();
        int V=DC.getY();
        
        Vector2 origin = new Vector2(x, y);
        Vector2 current;
        Vector2 next;

        double theta=0;
        double delta;
        do
        {
            delta=0.05;
            current = calcPosition(theta, r);
            current.add(origin);
            DC.draw(current.getXInt(), current.getYInt(), 0, DC.normalizePalette((int)Math.round(theta * colorSpeed), 0));
            int steps=0;
            do
            {
                steps++;
                theta+=delta;
                next = calcPosition(theta, r);
                next.add(origin);
                if(calcDistance(current, next)<1)
                {
                    break;
                }
                else
                {
                    theta-=delta;
                    delta/=2;
                    theta+=delta;
                }
            }
            while(steps<10);
        }
        while(theta<=360);

        return DC;
    }
    
    public DynamicCanvas drawCircle(DynamicCanvas DC, int x0, int y0, int radius, int PaletteNum, int colorNum)
    {
        int x1=radius;
        int y1=0;
        int err=0;
        int count=0;
        while(x1>=y1)
        {
            
            DC.draw(x0+x1,y0+y1, PaletteNum, DC.normalizePalette(colorNum, PaletteNum));
            DC.draw(x0+y1,y0+x1, PaletteNum, DC.normalizePalette(colorNum, PaletteNum));
            DC.draw(x0-y1,y0+x1, PaletteNum, DC.normalizePalette(colorNum, PaletteNum));
            DC.draw(x0-x1,y0+y1, PaletteNum, DC.normalizePalette(colorNum, PaletteNum));
            DC.draw(x0-x1,y0-y1, PaletteNum, DC.normalizePalette(colorNum, PaletteNum));
            DC.draw(x0-y1,y0-x1, PaletteNum, DC.normalizePalette(colorNum, PaletteNum));
            DC.draw(x0+y1,y0-x1, PaletteNum, DC.normalizePalette(colorNum, PaletteNum));
            DC.draw(x0+x1,y0-y1, PaletteNum, DC.normalizePalette(colorNum, PaletteNum));
            
            //System.out.println("Drew circle part " + count);
            
            if(err<=0)
            {
                y1+=1;
                err+=2*y1 + 1;
            }
            if(err>0)
            {
                x1-=1;
                err-=2*x1+1;
            }
            count++;
        }
        
        return DC;
    }
    
    private double calcDistance(Vector2 first, Vector2 second)
    {
        double a = Math.abs(first.getX()-second.getX());
        double b = Math.abs(first.getY()-second.getY());
        
        return Math.sqrt(Math.pow(a, 2)+Math.pow(b, 2)); //a^2+b^2=c^2, sqrt(a^2+b^2)=c
    }
    
    private Vector2 calcPosition(double theta, int radius)
    {
        return new Vector2(Math.cos(Math.toRadians(theta))*radius, Math.sin(Math.toRadians(theta))*radius);
    }
    
    public static Parameter[] constructParams(
            int radius, 
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
                r=params[0].getInt();
                x=params[1].getInt();
                y=params[2].getInt();
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
