/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypGenerators;

import trypLayerPanels.*;
import trypParams.Parameter;

/**
 *
 * @author amnesia
 */
public class GenType 
{
    public static final int NULL = 0;//0 indexed for easy indexing of combo box
    public static final int CIRCULAR = 1;
    public static final int FORMULA = 2;
    public static final int SIMPLELINES = 3;
    public static final int DISTORTFORMULA = 4;
    
    //ADD YOUR INT DECLARATION HERE
    
    public static final String[] TYPENAMES =
    {
        "Null",
        "Circular",
        "Formula",
        "Simple Lines",
        "Distort Formula"
        //ADD YOUR STRING HERE
    };
    
    
    
    public static AbstractGenerator getNewGenerator(int ID, Parameter[] params)
    {
        try
        {
            switch(ID) // ADD YOUR GENERATOR CALL HERE
            {
                case CIRCULAR:
                    return new Gen_Circular(params);
                case FORMULA:
                    return new Gen_Formula(params);
                case SIMPLELINES:
                    return new Gen_SimpleLines(params);
                case DISTORTFORMULA:
                    return new Gen_DistortFormula(params);
            }
        }
        catch(IllegalArgumentException e){}
            
        return null;
    }
    
    public static AbstractLayerPanel getPanel(int type)
    {
        switch(type)
        {
            case CIRCULAR:
                return new CircularLayerPanel();
            case FORMULA:
                return new FormulaLayerPanel();
            case SIMPLELINES:
                return new SimpleLinesLayerPanel();
            case DISTORTFORMULA:
                return new DistortFormulaLayerPanel();
        }
        return new NullLayerPanel();
    }
}
