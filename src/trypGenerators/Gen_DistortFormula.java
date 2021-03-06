/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypGenerators;

import trypParams.Parameter;
import trypParams.paramType;
import trypResources.Formula;
import trypResources.PaletteReference;
import tryptamine.DynamicCanvas;

/**
 *
 * @author timot_000
 */
public class Gen_DistortFormula extends AbstractGenerator
{
    boolean HV;
    boolean dir;
    int[] gaps;
    Formula F;
    
    public Gen_DistortFormula(Parameter[] params) throws IllegalArgumentException
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
        int n=0;
        int c=0;
        for(int i=0; i<maxPos; i++)
        {
            startPos=(int)Math.round(F.recursiveCalc(i));
            if(!dir)startPos*=-1;
            if(n>=gaps.length)
            {
                n=0;
            }
            if(c>=gaps[n])
            {
                c=0;
                n++;
                DC = shiftLine(DC, HV, startPos, i);
            }
            else
            {
                c++;
            }
        }
        
        
        return DC;
    }
    
    public DynamicCanvas shiftLine(DynamicCanvas DC, boolean HV, int num, int pos)
    {
        int max, dx, dy, cx, cy, n;
        PaletteReference[] RefArray;
        PaletteReference tempRef;
        
        
        if(HV) 
        {
            max=DC.getX();
            dx=Integer.signum(num);
            dy=0;
            cx=0;
            cy=pos;
        }
        else 
        {
            max=DC.getY();
            dx=0;
            dy=Integer.signum(num);
            cx=pos;
            cy=0;
        }
        
        RefArray = new PaletteReference[max];
        
        for(int i=0; i<max; i++)
        {
            RefArray[i]=DC.getReference(cx, cy);
            cx+=dx;
            cy+=dy;
        }
        n=0;
        for(int i=0; i<max; i++)
        {
            tempRef=RefArray[normalize(i+num,0,max)];
            RefArray[normalize(i+num,0,max)]=RefArray[normalize(n,0,max)];
            RefArray[normalize(n,0,max)]=tempRef;
            n++;
            if(num<0)
            {
                if(n>=max+num)
                {
                    n=0;
                }
            }
            else if(n>=num)
            {
                n=0;
            }
        }
        
        if(HV) 
        {
            cx=0;
            cy=pos;
        }
        else 
        {
            
            cx=pos;
            cy=0;
        }
        
        for(int i=0; i<max; i++)
        {
            DC.setReference(cx, cy, RefArray[i]);
            cx+=dx;
            cy+=dy;
        }
        
        return DC;
    }
    
    public int normalize(int n, int min, int max)
    {
        if(max>min)
        {
            while(n>=max)
            {
                n-=(max-min);
            }
            while(n<min)
            {
                n+=(max-min);
            }
        }
        return n;
    }
    @Deprecated
    public DynamicCanvas shiftLineOld(DynamicCanvas DC, boolean HV, int num, int pos)
    {
        int max, dx, dy, cx, cy;
        
        PaletteReference tempPR;
        
        if(Integer.signum(num)==-1)
        {
        }
        
        if(HV) 
        {
            max=DC.getX();
            dx=Integer.signum(num);
            dy=0;
            cx=0;
            cy=pos;
        }
        else 
        {
            max=DC.getY();
            dx=0;
            dy=Integer.signum(num);
            cx=pos;
            cy=0;
        }
        
        int n=0;
        
        for(int i=0; i<max; i++)
        {
            tempPR = DC.getReference(((n+num)*dx)+cx, ((n+num)*dy)+cy);
            DC.setReference(
                    ((n+num)*dx)+cx, 
                    ((n+num)*dy)+cy, 
                    DC.getReference(
                            (n*dx)+cx, 
                            (n*dy)+cy));
            DC.setReference((n*dx)+cx, (n*dy)+cy, tempPR);
            cx+=dx;
            cy+=dy;
            n++;
            if(n>=num)
            {
                n=0;
            }
        }
        return DC;
    }
    
    public static Parameter[] constructParams(boolean HV, boolean dir, int[] gaps, Formula F)
    {
        Parameter[] params = {new Parameter(HV), new Parameter(dir), new Parameter(gaps), new Parameter(F)};
        
        return params;
    }
    
    public final boolean parseParams(Parameter[] params)
    {
        if(params != null && params.length == 4)
        {
            if(validateParams(params))
            {
                HV=params[0].getBoolean();
                dir=params[1].getBoolean();
                gaps=params[2].getIntArray();
                F=params[3].getFormula();
                return true;
            }
        }
        
        return false;
    }

    public static boolean validateParams(Parameter[] params) 
    {
        
        return  params != null &&
                params.length==4 &&
                params[0].getType()==paramType.BOOLEAN && 
                params[1].getType()==paramType.BOOLEAN && 
                params[2].getType()==paramType.INTARRAY &&
                params[3].getType()==paramType.FORMULA;
    }
}
