/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypGenerators;

import trypParams.Parameter;
import trypParams.paramType;
import tryptamine.DynamicCanvas;

class Vector
{
    double x, y;
    public Vector(double x, double y)
    {
        this.x=x;
        this.y=y;
    }
    
    public Vector add(Vector toAdd)
    {
        if(toAdd != null)
        {
            x+=toAdd.getX();
            y+=toAdd.getY();
        }
        return this;
    }
    
    public int getXInt()
    {
        return (int)Math.round(x);
    }
    
    public int getYInt()
    {
        return (int)Math.round(y);
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
}

/**
 *
 * @author amnesia
 */
public class Gen_Circular extends AbstractGenerator
{

    int r, x, y;
    
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
            DC=drawCircle(DC, x, y, i, PaletteNum);
        }
        return DC;
    }
    
    /**Very poorly written, old, don't use unless you want loads of unnecessary calculations
     *
     * @param DC
     * @return
     * @deprecated
     */
    @Deprecated
    public DynamicCanvas drawOld(DynamicCanvas DC)
    {
        int H=DC.getX();
        int V=DC.getY();
        
        Vector origin = new Vector(x, y);
        Vector current, next;
        
        for(int i=0; i<=r; i++)
        {
            double theta=0;
            double delta;
            do
            {
                delta=0.5;
                current = calcPosition(theta, i);
                current.add(origin);
                DC.draw(current.getXInt(), current.getYInt(), 0, DC.normalizePalette((int)Math.round(theta), 0));
                
                
                
                int steps=0;
                do
                {
                    steps++;
                    theta+=delta;
                    next = calcPosition(theta, i);
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
        }
        
        return DC;
    }
    
    public DynamicCanvas drawCircle(DynamicCanvas DC, int x0, int y0, int radius, int PaletteNum)
    {
        int x1=radius;
        int y1=0;
        int err=0;
        int count=0;
        while(x1>=y1)
        {
            DC.draw(x0+x1,y0+y1, PaletteNum, DC.normalizePalette(count/2, 0));
            DC.draw(x0+y1,y0+x1, PaletteNum, DC.normalizePalette(count, 0));
            DC.draw(x0-y1,y0+x1, PaletteNum, DC.normalizePalette(count/2, 0));
            DC.draw(x0-x1,y0+y1, PaletteNum, DC.normalizePalette(count, 0));
            DC.draw(x0-x1,y0-y1, PaletteNum, DC.normalizePalette(count/2, 0));
            DC.draw(x0-y1,y0-x1, PaletteNum, DC.normalizePalette(count, 0));
            DC.draw(x0+y1,y0-x1, PaletteNum, DC.normalizePalette(count/2, 0));
            DC.draw(x0+x1,y0-y1, PaletteNum, DC.normalizePalette(count, 0));
            
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
    
    private double calcDistance(Vector first, Vector second)
    {
        double a = Math.abs(first.getX()-second.getX());
        double b = Math.abs(first.getY()-second.getY());
        
        return Math.sqrt(Math.pow(a, 2)+Math.pow(b, 2)); //a^2+b^2=c^2, sqrt(a^2+b^2)=c
    }
    
    private Vector calcPosition(double theta, int radius)
    {
        return new Vector(Math.cos(Math.toRadians(theta))*radius, Math.sin(Math.toRadians(theta))*radius);
    }
    
    public static Parameter[] constructParams(int radius, int x, int y)
    {
        Parameter[] params = {new Parameter(radius), new Parameter(x), new Parameter(y)};
        return params;
    }
    
    public final boolean parseParams(Parameter[] params)
    {
        if(params != null && params.length == 3)
        {
            if(validateParams(params))
            {
                r=params[0].getInt();
                x=params[1].getInt();
                y=params[2].getInt();
                return true;
            }
        }
        
        return false;
    }

    public static boolean validateParams(Parameter[] params) 
    {
        return params != null &&
               params.length==3 &&
               params[0].getType()==paramType.INTEGER &&
               params[1].getType()==paramType.INTEGER &&
               params[2].getType()==paramType.INTEGER;
    }

    
}
