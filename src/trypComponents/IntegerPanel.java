/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypComponents;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**A panel which allows entering of an integer value
 *
 * @author tiggerbiggo
 */
public class IntegerPanel extends JPanel implements ActionListener
{
    String text;
    boolean canBeNegative;
    
    JLabel textLabel;
    JTextField integerField;

    /**
     * 
     * @param text The text to display
     * @param canBeNegative Whether the integer can be negative or not
     */
    public IntegerPanel(String text, boolean canBeNegative) 
    {
        this.text = text;
        this.canBeNegative = canBeNegative;
        init();
    }

    /**Sets the value of the field
     * 
     * @param n The number to set
     */
    public void setInt(int n)
    {
        if(canBeNegative) integerField.setText(""+n);
        else
        {
            if(n<0) integerField.setText("0");
            else integerField.setText(""+n);
        }
    }
    
    /**Gets the parsed integer value from the field
     * 
     * @return The integer value found
     * @throws Exception When the field is not a number or the number is invalid
     */
    public int getInt() throws Exception
    {
        try
        {
            int tmp = Integer.parseInt(integerField.getText());
            if(canBeNegative)
            {
                return tmp;
            }
            else if(tmp>=0)
            {
                return tmp;
            }
        }
        catch(NumberFormatException e)
        {
        }
        throw new Exception();
        
    }
    
    /**basic initialisation method which sets up the layout of the panel
     * 
     */
    public void init()
    {
        this.setLayout(new GridLayout(0,1));
        
        textLabel = new JLabel(text);
        
        integerField = new JTextField("0");
        integerField.addActionListener(this);
        
        this.add(textLabel);
        
        this.add(integerField);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}
