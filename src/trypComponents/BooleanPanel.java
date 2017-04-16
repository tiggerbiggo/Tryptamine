package trypComponents;

import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**A panel for selecting boolean parameters
 *
 * @author tiggerbiggo
 */
public class BooleanPanel extends JPanel
{
    
    JRadioButton buttons[];
    ButtonGroup bGroup;

    /**Creates a new Boolean Panel with the text specified in each element of the String array
     *
     * If the array is null or has &lt;=1 elements the buttons will be labelled "true" and "false"
     * 
     * TODO: Make this better, passing an array for something like this sucks, don't do it
     * 
     * @param text The array of text to display
     */
    public BooleanPanel(String[] text) 
    {
        int tLength;
        if(text!= null) tLength = text.length;
        else tLength = 0;
        
        this.setLayout(new GridLayout(0,2));
        
        buttons = new JRadioButton[2];
        bGroup = new ButtonGroup();
        for(int i=0;i<2;i++)
        {
            if(i<tLength) buttons[i]=new JRadioButton(text[i]);
            else buttons[i]=new JRadioButton("NULL");
            
            bGroup.add(buttons[i]);
            this.add(buttons[i]);
        }
        buttons[0].setSelected(true);
    }
    
    /**
     *
     * @return Returns the selected button. Button 1 = true, Button 2 = false
     */
    public boolean getBoolean()
    {
        return buttons[0].isSelected();
    }
    
    /**Sets which button should be selected, for loading parameters in
     *
     * @param b True for button 1, false for button 2
     */
    public void setBoolean(boolean b)
    {
        if(b)buttons[0].setSelected(true);
        else buttons[1].setSelected(true);
    }
}
