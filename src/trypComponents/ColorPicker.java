package trypComponents;

import java.awt.Color;
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

/**A color picker with 3 bars, 3 text fields for entry and a button for displaying the color
 *
 * @author tiggerbiggo
 */
public class ColorPicker extends JPanel implements FocusListener, ChangeListener, ActionListener
{
    JTextField[] fields;
    JSlider[] sliders;
    JButton colorButton;
    
    Color c;
    
    /**Creates a new Color Picker
     * 
     * @param orientation Whether the elements are arranged to the left or right of the sliders
     * @param a The action listener of the parent object
     */
    public ColorPicker(boolean orientation, ActionListener a)
    {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        this.setBackground(Color.black);
        
        fields = new JTextField[3];
        
        c.weightx =0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty=0;
        
        for(int i=0; i<=2; i++)
        {
            
            fields[i] = new JTextField("0");
            
            c.gridx=1;
            c.gridy=i;
            
            fields[i].addFocusListener(this);
            fields[i].addActionListener(this);
            
            this.add(fields[i], c);
        }
        
        sliders = new JSlider[3];
        for(int i=0; i<=2; i++)
        {
            sliders[i]=new JSlider();
            
            sliders[i].setMaximum(255);
            sliders[i].setValue(0);
            sliders[i].addChangeListener(this);
            
            if(orientation)c.gridx = 0;
            else c.gridx=2;
            
            c.gridy=i;
            
            this.add(sliders[i], c);
        }
        
        colorButton = new JButton();
        c.gridx=0;
        c.gridy=3;
        c.gridwidth=3;
        c.weighty=1;
        c.fill = GridBagConstraints.BOTH;
        colorButton.addActionListener(a);
        this.add(colorButton, c);
        
        updateColor();
        
    }
    
    /**Updates the color object based on the values of the sliders
     * 
     */
    public void updateColor()
    {
        c=new Color(sliders[0].getValue(),
                    sliders[1].getValue(),
                    sliders[2].getValue());
        
        if(colorButton != null) colorButton.setBackground(c);
    }
    
    /**
     * 
     * @return The current color
     */
    public Color getColor()
    {
        return c;
    }
    
    /**Parses the user input to get a number, includes validation to check if it's within range and a valid number
     * 
     */
    public void parseFields()
    {
        for(int i=0; i<=2; i++)
        {
            try
            {
                int tmp = Integer.parseInt(fields[i].getText());
                if(tmp >=0 && tmp <=255)
                {
                    sliders[i].setValue(tmp);
                }
                else if(tmp <0)
                {
                    sliders[i].setValue(0);
                    fields[i].setText("0");
                }
                else
                {
                    sliders[i].setValue(255);
                    fields[i].setText("255");
                }
            }
            catch(Exception ex)
            {
                fields[i].setText(""+sliders[i].getValue());
            }
        }
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

    /**Fires when the user changes a slider, updating the color and text fields in real time
     * 
     * @param e The event object
     */
    @Override
    public void stateChanged(ChangeEvent e) 
    {
        Object toCheck = e.getSource();
        
        for(int i=0; i<=2; i++)
        {
            if(toCheck == sliders[i])
            {
                fields[i].setText(""+sliders[i].getValue());
                
            }
        }
        updateColor();
    }
    
    /**Checks if an object passed is the color button
     * 
     * @see trypResources.ActionCodes
     * @param toCheck The object to check
     * @return A code based on trypResources.ActionCodes
     */
    public int checkActions(Object toCheck)
    {
        if(toCheck == colorButton)
        {
            return ActionCodes.CODE_COLORPICKER_BUTTON;
        }
        return ActionCodes.NULLCODE;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        parseFields();
    }
}
