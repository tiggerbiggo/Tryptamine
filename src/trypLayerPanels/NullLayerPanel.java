/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypLayerPanels;

import trypParams.Parameter;

/**
 *
 * @author root
 */
public class NullLayerPanel extends AbstractLayerPanel
{

    @Override
    public Parameter[] getParams() 
    {
        return null;
    }

    @Override
    public void initGUI() 
    {
        
    }

    @Override
    public void setParams(Parameter[] params) 
    {
    }
    
}
