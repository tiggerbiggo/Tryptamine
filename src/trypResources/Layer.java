/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypResources;

import trypGenerators.GenType;
import trypGenerators.AbstractGenerator;
import trypParams.Parameter;

/**
 *
 * @author amnesia
 */
public class Layer 
{
    AbstractGenerator gen;
    Parameter[] params;
    int type;
    String name;
    int paletteNum;

    public Layer(String name)
    {
        this.type=GenType.NULL;
        this.name=name;
        paletteNum = -1;
    }
    
    public Layer(String name, int type) 
    {
        this.type = type;
        this.name = name;
        paletteNum = -1;
    }
    
    
    
    public Layer(String name, int type, Parameter[] params)
    {
        this.type = type;
        setParams(params);
        this.name=name;
        paletteNum = -1;
    }
    
    public int getType()
    {
        return type;
    }
    
    public void setType(int type)
    {
        if(type>=0)
        {
            this.type=type;
        }
    }
    
    public void setName(String name)
    {
        if(name != null)
            this.name=name;
    }
    
    public void setPaletteNum(int num)
    {
        paletteNum = num;
    }
    
    public int getPaletteNum()
    {
        return paletteNum;
    }
    
    public Parameter[] getParams()
    {
        return params;
    }
    
    public void setParams(Parameter[] params)
    {
        AbstractGenerator tmpGen = GenType.getNewGenerator(type, params);
        if(tmpGen != null)
        {
            gen = tmpGen;
            this.params = params;
        }
    }
    
    public AbstractGenerator getGenerator()
    {
        return gen;
    }
    
    
    
    @Override
    public String toString()
    {
        return name;
    }
    
}
