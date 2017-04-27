/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypLayerPanels;

import java.awt.GridLayout;
import javax.swing.JLabel;
import trypParams.Parameter;

/**
 *
 * @author root
 */
public class NullLayerPanel extends AbstractLayerPanel
{
    JLabel label;
    
    @Override
    public Parameter[] getParams() 
    {
        return null;
    }

    @Override
    public void initGUI() 
    {
        this.setLayout(new GridLayout(1,1));
        label = new JLabel("No Layer Selected, or layer type null");
        this.add(label);
    }

    @Override
    public void setParams(Parameter[] params) 
    {
    }
    
}
