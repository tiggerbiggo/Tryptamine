/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypLayerPanels;

import javax.swing.JPanel;
import trypParams.Parameter;

/**
 *
 * @author amnesia
 */
public abstract class AbstractLayerPanel extends JPanel
{
    public abstract Parameter[] getParams();
    
    public abstract void initGUI();
    
    public abstract void setParams(Parameter[] params);
}
