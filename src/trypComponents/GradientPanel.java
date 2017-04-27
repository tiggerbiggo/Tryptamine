/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import trypResources.ActionCodes;
import trypResources.Tools;

/**
 *
 * @author amnesia
 */
public class GradientPanel extends JPanel implements ActionListener, ChangeListener, FocusListener
{
    JSlider[] sliders;
    JTextField[] fields;
    JButton JB_Make, JB_SelectStart, JB_SelectEnd, invertButton;
    
    int min, max;
    
    public GradientPanel(ActionListener A)
    {
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill=GridBagConstraints.BOTH;
        c.weightx=0.5;
        c.weighty=0;
        c.gridwidth=1;
        c.gridheight=1;
        c.ipadx=0;
        
        min=0;
        max=0;
        
        sliders = new JSlider[2];
        fields = new JTextField[2];
        
        sliders[0] = new JSlider(0,0,0);
        sliders[0].addChangeListener(this);
        
        sliders[1] = new JSlider(0,0,0);
        sliders[1].addChangeListener(this);
        
        fields[0] = new JTextField("10");
        fields[0].addFocusListener(this);
        fields[0].addActionListener(this);
        fields[0].setMinimumSize(new Dimension(80, 20));
        
        fields[1] = new JTextField("10");
        fields[1].addFocusListener(this);
        fields[1].addActionListener(this);
        
        JB_Make = new JButton("Make Gradient");
        JB_Make.addActionListener(A);
        
        JB_SelectStart = new JButton("Select Start");
        JB_SelectStart.addActionListener(A);
        
        JB_SelectEnd = new JButton("Select End");
        JB_SelectEnd.addActionListener(A);
        
        invertButton = new JButton("Invert Colors");
        invertButton.addActionListener(A);
        
        c.gridx=0;
        c.gridy=0;
        this.add(JB_SelectStart,c);
        
        
        
        c.gridx=2;
        c.gridy=0;
        this.add(JB_Make,c);
        
        c.gridy=1;
        this.add(invertButton, c);
        
        c.gridx=4;
        c.gridy=0;
        this.add(JB_SelectEnd,c);
        
        c.gridx=1;
        c.gridy=0;
        c.ipadx=30;
        this.add(fields[0],c);
        
        c.gridx=3;
        c.gridy=0;
        c.weightx=1;
        this.add(fields[1],c);
        
        c.gridx=0;
        c.gridy=1;
        c.gridwidth=2;
        this.add(sliders[0],c);
        
        c.gridx=3;
        c.gridy=1;
        this.add(sliders[1],c);
        
        setAllEnabled(true);
    }
    
    public void setAllEnabled(boolean state)
    {
        sliders[0].setEnabled(state);
        sliders[1].setEnabled(state);
        fields[0].setEnabled(state); 
        fields[1].setEnabled(state);
        JB_Make.setEnabled(state);
        JB_SelectStart.setEnabled(state);
        JB_SelectEnd.setEnabled(state);
    }

    public void setMax(int max)
    {
        if(max>=0)
        {
            this.max=max;

            for(int i=0;i<=1;i++)
            {
                fields[i].setText("0");
                sliders[i].setValue(0);
                sliders[i].setMaximum(max);
            }
        }
    }

    public void updateFields()
    {
        if(sliders[0].getValue()>=sliders[1].getValue())
        {
            if(sliders[0].getValueIsAdjusting())
            {
                sliders[1].setValue(sliders[0].getValue());
            }
            else sliders[0].setValue(sliders[1].getValue());
        }
        fields[0].setText(""+sliders[0].getValue());
        fields[1].setText(""+sliders[1].getValue());
    }
    
    public int parseField(JTextField JT)
    {
        try
        {
            return Integer.parseInt(JT.getText());
        }
        catch(Exception e)
        {
        }
        return -1;
    }
    
    public void parseFields()
    {
        int[] toCheck = new int[2];
        toCheck[0]=parseField(fields[0]);
        toCheck[1]=parseField(fields[1]);
        
        
        
        for(int i=0; i<=1; i++)
        {
            if(toCheck[i]==-1)
            {
                fields[i].setText("" + sliders[i].getValue());
            }
            else if(toCheck[i] <=0)
            {
                fields[i].setText("0");
                sliders[i].setValue(0);
            }
            else if(toCheck[i]>=max)
            {
                fields[i].setText(""+max);
                sliders[i].setValue(max);
            }
            else
            {
                sliders[i].setValue(toCheck[i]);
            }
        }
        updateFields();
    }
    
    @Override
    public void stateChanged(ChangeEvent e) 
    {
        updateFields();
    }

    @Override
    public void focusGained(FocusEvent e) 
    {
        parseFields();
    }

    @Override
    public void focusLost(FocusEvent e) 
    {
        parseFields();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        parseFields();
    }

    public int checkActions(Object toCheck) 
    {
        Tools.p("Action Triggered");
        if(toCheck == JB_SelectStart)
        {
            return ActionCodes.CODE_GRADIENTPANEL_SELECTSTART;
        }
        else if(toCheck == JB_SelectEnd)
        {
            Tools.p("Selected End");
            return ActionCodes.CODE_GRADIENTPANEL_SELECTEND;
        }
        else if(toCheck == JB_Make)
        {
            return ActionCodes.CODE_GRADIENTPANEL_MAKE;
        }
        else if(toCheck == invertButton)
        {
            return ActionCodes.CODE_GRADIENTPANEL_INVERT;
        }
        return ActionCodes.NULLCODE;
    }

    public void setStart(int val) 
    {
        if(val>sliders[1].getValue())
        {
            sliders[1].setValue(val);
        }
        sliders[0].setValue(val);
        
        updateFields();
    }
    
    public void setEnd(int val) 
    {
        if(val<sliders[0].getValue())
        {
            sliders[0].setValue(val);
        }
        sliders[1].setValue(val);
        
        updateFields();
    }
    
    public int getStart()
    {
        return sliders[0].getValue();
    }
    
    public int getEnd()
    {
        return sliders[1].getValue();
    }
}
