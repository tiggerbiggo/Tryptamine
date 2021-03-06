/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypLayerPanels;

import java.awt.GridLayout;
import trypComponents.*;
import trypGenerators.Gen_Formula;
import trypParams.Parameter;
import trypResources.Formula;
import trypResources.Function;
import tryptamine.GapPresets;

/**
 *
 * @author root
 */
public class FormulaLayerPanel extends AbstractLayerPanel
{
    //hv, dir, colorspeed, gaps, formula
    
    BooleanPanel[] bPanels;
    IntegerPanel colorSpeed;
    GapPanel gapPanel;
    FormulaPresetBar presets;
    
    @Override
    public Parameter[] getParams() 
    {
        //hv, dir, colorspeed, gaps, formula
        try
        {
            Formula F = new Formula(Function.SIN);
            F.setCoeff(20);
            return Gen_Formula.constructParams(
                    bPanels[0].getBoolean(), 
                    bPanels[0].getBoolean(), 
                    colorSpeed.getInt(), 
                    gapPanel.getGaps(), 
                    presets.getFormula());
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    public void initGUI() 
    {
        //
        this.setLayout(new GridLayout(0,1));
        
        String[][] labelNames = 
        {
            {"Horizontal", "Vertical"},
            {"+", "-"}
        };
        bPanels = new BooleanPanel[2];
        for(int i=0;i<=1;i++)
        {
            bPanels[i]=new BooleanPanel(labelNames[i]);
            this.add(bPanels[i]);
        }
        
        colorSpeed = new IntegerPanel("Color Speed", true);
        this.add(colorSpeed);
        
        gapPanel = new GapPanel();
        this.add(gapPanel);
        
        presets = new FormulaPresetBar();
        this.add(presets);
    }

    @Override
    public void setParams(Parameter[] params) 
    {
        //hv, dir, colorspeed, gaps, formula
        if(Gen_Formula.validateParams(params))
        {
            bPanels[0].setBoolean(params[0].getBoolean());
            bPanels[1].setBoolean(params[1].getBoolean());
            
            colorSpeed.setInt(params[2].getInt());
            gapPanel.setSelected(GapPresets.parseArray(params[3].getIntArray()));
            double coeff = params[4].getFormula().getCoeff();
            double freq = params[4].getFormula().getFreq();
            int selected = params[4].getFormula().getSelected();
            presets.setAll(coeff, freq, selected);
        }
    }
    
}
