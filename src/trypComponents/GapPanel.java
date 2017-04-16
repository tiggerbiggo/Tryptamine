package trypComponents;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tryptamine.GapPresets;

/**A prefab panel to allow the user to select gaps, uses the GapPresets class
 * 
 * @see tryptamine.GapPresets
 * @author tiggerbiggo
 */
public class GapPanel extends JPanel
{
    String text;
    
    JLabel textLabel;
    JComboBox comboBox;

    public GapPanel() 
    {
        this.setLayout(new GridLayout(0,1));
        
        textLabel = new JLabel(text);
        
        comboBox = new JComboBox(GapPresets.GAPNAMES);
        
        this.add(textLabel);
        
        this.add(comboBox);
    }
    
    /**Gets the currently selected int array of gaps
     * 
     * @return The gaps the user has selected
     */
    public int[] getGaps()
    {
        return GapPresets.gaps[comboBox.getSelectedIndex()];
    }
    
    /**
     * 
     * @param index Sets the index, for loading from a layer object
     */
    public void setSelected(int index)
    {
        if(index>=0 && index<comboBox.getItemCount()) comboBox.setSelectedIndex(index);
    }
}
