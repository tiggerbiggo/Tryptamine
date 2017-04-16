package trypGenerators;

import trypLayerPanels.*;
import trypParams.Parameter;

/**<p>Manages different generator types and their names to display to the user</p>
*<p>If you want your generator to show up in the layer edit window, you need to do 4 things:</p>
*<ol>
*<li>Add a new <strong><em>public static final int</em></strong>.<strong>&nbsp;</strong>
* Make sure that your addition increments the number of the previous generator's entry by exactly 1. 
* This will ensure that when the user selects your generator in the combo box it will refer to your 
* generator and not a different one or worse, one that doesn't exist.</li>
* 
*<li>Add a new <strong><em>String</em> </strong>entry to <em><strong>TYPENAMES.&nbsp;</strong></em>
* Whatever string you add here will show up in the combo box when the user selects your generator, 
* so make it descriptive!</li>
* 
*<li>Add a new case in the&nbsp;<em><strong>getNewGenerator&nbsp;</strong></em>method, 
* which returns a new instance of your generator given&nbsp;<em><strong>params.&nbsp;</strong></em>
* This links the combo box entry to your actual generator class.</li>
*<li>Finally add a new case in the&nbsp;<em><strong>getPanel&nbsp;</strong></em>method. 
* If your generator needs parameters, first make a new subclass of&nbsp;<strong><em>
* AbstractLayerPanel,&nbsp;</em></strong>then add a return statement to return a new instance of your panel. 
* If your generator doesn't need parameters, simply return a new instance of <strong><em>
* NullLayerPanel&nbsp;</em></strong>instead, or skip this step. It is advised to add this for the 
* sanity of others wanting to add generators to this class, as it keeps the layout of the class consistent.</li>
* 
*</ol>
*<p>Assuming all went to plan your generator should now be added to the layer edit window.</p>
*<p>&nbsp;</p>
 *
 * @author tiggerbiggo
 */
public class GenType 
{
    public static final int NULL = 0;//0 indexed for easy indexing of combo box
    public static final int CIRCULAR = 1;
    public static final int FORMULA = 2;
    public static final int SIMPLELINES = 3;
    public static final int DISTORTFORMULA = 4;
    
    //ADD YOUR INT DECLARATION HERE
    
    public static String getNoot(int HowManyNoots)
    {
        
        return "NOOT";
    }
    
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
                return new NullLayerPanel();
            case DISTORTFORMULA:
                return new NullLayerPanel();
        }
        return new NullLayerPanel();
    }
}
