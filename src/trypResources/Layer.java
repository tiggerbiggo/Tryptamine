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
    GenType type;

    public Layer(GenType type) 
    {
        this.type = type;
    }
    
    public Layer()
    {
        this.type=GenType.NULL;
    }
    
}
