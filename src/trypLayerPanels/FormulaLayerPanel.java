/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypLayerPanels;

import java.awt.Dimension;
import java.awt.GridLayout;
import trypComponents.*;
import trypGenerators.Gen_Formula;
import trypParams.Parameter;
import trypFormula.Formula;
import trypFormula.Function;
import trypFormula.Operation;
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
    FormulaPresetBar formulaPanel;
    
    @Override
    public Parameter[] getParams() 
    {
        //hv, dir, colorspeed, gaps, formula
        try
        {
            Formula F = new Formula(Function.SIN, new Formula(Function.COS), Operation.ADD);
            F.setCoeff(20);
            F.setFreq(0.1);
            F.getNext().setCoeff(10);
            F.getNext().setFreq(0.2);
            return Gen_Formula.constructParams(
                    bPanels[0].getBoolean(), 
                    bPanels[0].getBoolean(), 
                    colorSpeed.getInt(), 
                    gapPanel.getGaps(), 
                    F);
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
        
        this.setPreferredSize(new Dimension(300,200));
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
        
        formulaPanel = new FormulaPresetBar();
        this.add(formulaPanel);
    }

    @Override
    public void setParams(Parameter[] params) 
    {
        //hv, dir, colorspeed, gaps, formula
        if(Gen_Formula.validateParams(params))
        {
            bPanels[0].setBoolean(params[0].getBoolean());
            bPanels[1].setBoolean(params[1].getBoolean());
            System.out.println(Boolean.toString(params[0].getBoolean()));
            
            colorSpeed.setInt(params[2].getInt());
            gapPanel.setSelected(GapPresets.parseArray(params[3].getIntArray()));
        }
    }
    
}
