/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypComponents;

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

/**A prefab panel containing various buttons and sliders used to edit and create gradients
 *
 * @author tiggerbiggo
 */
public class GradientPanel extends JPanel implements ActionListener, ChangeListener, FocusListener
{
    JSlider[] sliders;
    JTextField[] fields;
    JButton makeButton, startButton, endButton;
    
    int min, max;
    
    /**Creates a new gradient panel
     * 
     * @param A The action listener of the parent object
     */
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
        
        makeButton = new JButton("Make Gradient");
        makeButton.addActionListener(A);
        
        startButton = new JButton("Select Start");
        startButton.addActionListener(A);
        
        endButton = new JButton("Select End");
        endButton.addActionListener(A);
        
        
        c.gridx=0;
        c.gridy=0;
        this.add(startButton,c);
        
        
        
        c.gridx=2;
        c.gridy=0;
        c.gridheight=2;
        this.add(makeButton,c);
        
        c.gridheight=1;
        
        c.gridx=4;
        c.gridy=0;
        this.add(endButton,c);
        
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
    
    /**Sets the enabled state of all the elements in the panel based on the parameter passed
     * 
     * @param state The state to set the elements to
     */
    public void setAllEnabled(boolean state)
    {
        sliders[0].setEnabled(state);
        sliders[1].setEnabled(state);
        fields[0].setEnabled(state); 
        fields[1].setEnabled(state);
        makeButton.setEnabled(state);
        startButton.setEnabled(state);
        endButton.setEnabled(state);
    }

    /**Sets the maximum index the sliders and text boxes will allow
     * 
     * @param max The maximum index
     */
    public void setMax(int max)
    {
        this.max=max;
        
        for(int i=0;i<=1;i++)
        {
            fields[i].setText("0");
            sliders[i].setValue(0);
            sliders[i].setMaximum(max);
        }
    }

    /**Updates the text fields based on the slider values, also contains validation so min can never be more than max
     * 
     */
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
    
    /**Parses a text field to get the int value it contains
     * 
     * TODO: Take a look at this one, it's probably not done right...
     * @param JT The textField to parse
     * @return The int value contained in the field, -1 if invalid
     */
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
    
    /**Performs an integer parse on the text fields and sets the values of the sliders and fields according to the value entered
     * 
     */
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
    
    /**Checks an object against the buttons and sliders to check if it matches
     * 
     * @see trypResources.ActionCodes
     * @param toCheck The object to check
     * @return A code based on trypResources.ActionCodes
     */
    public int checkActions(Object toCheck) 
    {
        Tools.p("Action Triggered");
        if(toCheck == startButton)
        {
            return ActionCodes.CODE_GRADIENTPANEL_SELECTSTART;
        }
        else if(toCheck == endButton)
        {
            Tools.p("Selected End");
            return ActionCodes.CODE_GRADIENTPANEL_SELECTEND;
        }
        else if(toCheck == makeButton)
        {
            
            return ActionCodes.CODE_GRADIENTPANEL_MAKE;
        }
        return ActionCodes.NULLCODE;
    }

    /**Sets the start value of the sliders and fields
     * 
     * @param val The value to set
     */
    public void setStart(int val) 
    {
        if(val>sliders[1].getValue())
        {
            sliders[1].setValue(val);
        }
        sliders[0].setValue(val);
        
        updateFields();
    }
    
    /**Sets the end value of the sliders and fields
     * 
     * @param val The value to set
     */
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
