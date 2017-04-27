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

/**
 *
 * @author amnesia
 */
public class ColorPicker extends JPanel implements FocusListener, ChangeListener, ActionListener
{
    JTextField[] fields;
    JSlider[] sliders;
    JButton colorButton;
    
    Color C;
    
    public ColorPicker(boolean orientation, ActionListener A)
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
        colorButton.addActionListener(A);
        this.add(colorButton, c);
        
        updateColor();
        
    }
    
    public void updateColor()
    {
        C=new Color(sliders[0].getValue(),
                    sliders[1].getValue(),
                    sliders[2].getValue());
        
        if(colorButton != null) colorButton.setBackground(C);
    }
    
    public Color getColor()
    {
        return C;
    }
    
    public void setColor(Color C)
    {
        try
        {
            sliders[0].setValue(C.getRed());
            sliders[1].setValue(C.getGreen());
            sliders[2].setValue(C.getBlue());
            
            fields[0].setText("" + C.getRed());
            fields[1].setText("" + C.getGreen());
            fields[2].setText("" + C.getBlue());
            
            this.C = C;
        }
        catch(Exception e){}
    }
    
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
