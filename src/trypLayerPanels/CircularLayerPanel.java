/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypLayerPanels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import trypComponents.IntegerPanel;
import trypParams.Parameter;

/**
 *
 * @author amnesia
 */
public class CircularLayerPanel extends AbstractLayerPanel
{
    //radius, x, y
    
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
        
        this.setBackground(Color.RED);
    }
    
    @Override
    public Parameter[] getParams() 
    {
        return null;
    }
    
}
