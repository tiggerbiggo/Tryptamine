package trypComponents;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import trypResources.ColorTools;

/**A set of buttons which can be filled with colours, used to display a gradient to the user
 *
 * @author tiggerbiggo
 */
public class ColorBar extends JPanel
{
    int num;
    
    ActionListener A;
    String nullText = "...";
    JButton[] buttons;

    /**Creates a new Color Bar
     *
     * @param num The number of buttons to generate
     * @param A The action listener of the parent class to trigger events
     */
    public ColorBar(int num, ActionListener A)
    {
        //if(num<0)
        //{
        //    throw new IllegalArgumentException("Number cannot be negative!");
        //}
        this.num=num;
        this.A=A;
        init();
    }
    
    /**Sets the text of a button given its index
     *
     * @param index The index of the button to change
     * @param text The text to display on the button
     */
    public void setText(int index, String text)
    {
        if(checkIndex(index))
        {
            buttons[index].setText(text);
        }
    }
    
    /**Basic initialisation of the object
     * 
     */
    private void init()
    {
        
        this.setVisible(true);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.weightx =1;
        c.fill = GridBagConstraints.BOTH;
        c.weighty=1;
        
        buttons = new JButton[num];
        for(int i=0; i<num; i++)
        {
            c.gridx=i;
            buttons[i] = new JButton(nullText);
            buttons[i].addActionListener(A);
            this.add(buttons[i], c);
        }
    }
    
    /**Checks which button was pressed and returns its index
     * 
     * @param toCheck The object to check against the button array
     * @return The index of the pressed button, -1 if no match found
     */
    public int checkActions(Object toCheck)
    {
        for(int i=0; i<num; i++)
        {
            if(toCheck == buttons[i])
            {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * 
     * @return The number of buttons
     */
    public int getNum()
    {
        return num;
    }
    
    /**Basic validation method, checks that a given number is within range 0&gt;= n &gt; num where num is the number of buttons
     * 
     * @param index The index to check
     * @return True if within range, otherwise false
     */
    public boolean checkIndex(int index)
    {
        return index>=0 && index<num;
    }
    /**Sets the color of a button given its index and a number to display in the button
     * 
     * @param index The index of the button to set the color
     * @param c the color to change to
     * @param colorIndex TODO: Rework and remove this, it's inconsistent with the method's purpose
     */
    public void setColor(int index, Color c, int colorIndex)
    {
        if(checkIndex(index))
        {
            if(c != null)
            {
                buttons[index].setBackground(c);
                buttons[index].setText("" + colorIndex);
                buttons[index].setForeground(ColorTools.invert(c));
            }
            else
            {
                buttons[index].setBackground(Color.white);
                buttons[index].setText(nullText);
            }
        }
    }
    
    
    
}
