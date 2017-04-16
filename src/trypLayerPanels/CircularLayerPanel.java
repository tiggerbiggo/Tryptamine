/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypLayerPanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import trypComponents.IntegerPanel;
import trypGenerators.Gen_Circular;
import trypParams.Parameter;

/**
 *
 * @author amnesia
 */
public class CircularLayerPanel extends AbstractLayerPanel
{
    IntegerPanel[] intPanels;
    
    @Override
    public void initGUI()
    {
        
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.weightx=1;
        c.weighty=1;
        c.fill=GridBagConstraints.BOTH;
        
        intPanels = new IntegerPanel[3];
        String[] panelTexts = {"Radius", "X", "Y"};
        for(int i=0;i<=2;i++)
        {
            c.gridy=i;
            intPanels[i]=new IntegerPanel(panelTexts[i], false);
            this.add(intPanels[i], c);
        }
    }
    
    @Override
    public Parameter[] getParams() 
    {
        int[] tempArray = new int[3];
        for(int i=0;i<3;i++)
        {
            try
            {
                tempArray[i] = intPanels[i].getInt();
            }
            catch(Exception e)
            {
                return null;
            }
        }
        return Gen_Circular.constructParams(
                tempArray[0], 
                tempArray[1], 
                tempArray[2]);
    }

    @Override
    public void setParams(Parameter[] params) 
    {
        if(Gen_Circular.validateParams(params))
        {
            for(int i=0; i<=2; i++)
            {
                intPanels[i].setInt(params[i].getInt());
            }
        }
    }
    
}
