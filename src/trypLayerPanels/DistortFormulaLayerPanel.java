/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypLayerPanels;

import java.awt.GridLayout;
import trypComponents.*;
import trypGenerators.Gen_DistortFormula;
import trypGenerators.Gen_Formula;
import trypParams.Parameter;
import trypResources.Formula;
import trypResources.Function;
import tryptamine.GapPresets;

/**
 *
 * @author root
 */
public class DistortFormulaLayerPanel extends AbstractLayerPanel
{
    //hv, dir, gaps, formula
    
    BooleanPanel boolHVPanel;
    BooleanPanel boolDirPanel;
    GapPanel gapPanel;
    FormulaPresetBar presets;
    
    @Override
    public Parameter[] getParams() 
    {
        //hv, dir, gaps, formula
        try
        {
            Formula F = new Formula(Function.SIN);
            F.setCoeff(20);
            return Gen_DistortFormula.constructParams(
                    boolHVPanel.getBoolean(), 
                    boolDirPanel.getBoolean(), 
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
        
        String[][] labelNames = {
            {"Horizontal", "Vertical"},
            {"+", "-"}
        };
        boolHVPanel = new BooleanPanel(labelNames[0]);
        this.add(boolHVPanel);
        
        boolDirPanel = new BooleanPanel(labelNames[1]);
        this.add(boolDirPanel);
        
        gapPanel = new GapPanel();
        this.add(gapPanel);
        
        presets = new FormulaPresetBar();
        this.add(presets);
    }

    @Override
    public void setParams(Parameter[] params) 
    {
        //hv, colorspeed, gaps, formula
        if(Gen_DistortFormula.validateParams(params))
        {
            try
            {
                boolHVPanel.setBoolean(params[0].getBoolean());
                boolDirPanel.setBoolean(params[1].getBoolean());
                gapPanel.setSelected(GapPresets.parseArray(params[2].getIntArray()));
                double coeff = params[3].getFormula().getCoeff();
                double freq = params[3].getFormula().getFreq();
                int selected = params[3].getFormula().getSelected();
                presets.setAll(coeff, freq, selected);
            }
            catch(Exception e){}
        }
    }
    
}
