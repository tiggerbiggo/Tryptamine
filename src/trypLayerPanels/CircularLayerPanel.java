package trypLayerPanels;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import trypComponents.IntegerPanel;
import trypGenerators.Gen_Circular;
import trypParams.Parameter;
import trypResources.CircleModes;


public class CircularLayerPanel extends AbstractLayerPanel
{
    IntegerPanel[] intPanels;
    JComboBox modes;
    int panelNum;
    
    @Override
    public void initGUI()
    {
        this.setLayout(new GridLayout(0,2));
        
        panelNum = 5;
        intPanels = new IntegerPanel[panelNum];
        String[] panelTexts = {"Radius", "X", "Y", "Color Offset", "Color Speed"};
        boolean[] canBeNegative = {false, true, true, true, true};
        for(int i=0;i<=panelNum-1;i++)
        {
            intPanels[i]=new IntegerPanel(panelTexts[i], canBeNegative[i]);
            this.add(intPanels[i]);
        }
        
        modes = new JComboBox(CircleModes.values());
        this.add(modes);
    }
    
    @Override
    public Parameter[] getParams() 
    {
        int[] tempArray = new int[panelNum];
        for(int i=0;i<panelNum;i++)
        {
            try
            {
                tempArray[i] = intPanels[i].getInt();
            }
            catch(Exception e)
            {
                System.out.println("oops");
                return null;
            }
        }
        return Gen_Circular.constructParams(
                tempArray[0], 
                tempArray[1], 
                tempArray[2],
                tempArray[3],
                tempArray[4],
                CircleModes.values()[modes.getSelectedIndex()]);
    }

    @Override
    public void setParams(Parameter[] params) 
    {
        if(Gen_Circular.validateParams(params))
        {
            for(int i=0; i<panelNum; i++)
            {
                intPanels[i].setInt(params[i].getInt());
            }
            CircleModes[] values = CircleModes.values();
            for(int i=0; i<values.length; i++)
            {
                if(values[i] == params[panelNum].getCircleMode())
                {
                    modes.setSelectedIndex(i);
                    break;
                }
            }
        }
    }
    
}
