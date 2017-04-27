package trypComponents;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import tryptamine.GapPresets;

public class GapPanel extends JPanel implements ActionListener
{
    String text;
    
    JLabel JL_Text;
    JComboBox comboBox;

    public GapPanel() 
    {
        this.setLayout(new GridLayout(0,1));
        
        JL_Text = new JLabel(text);
        
        comboBox = new JComboBox(GapPresets.GAPNAMES);
        comboBox.addActionListener(this);
        
        this.add(JL_Text);
        
        this.add(comboBox);
    }
    
    public int[] getGaps()
    {
        return GapPresets.gaps[comboBox.getSelectedIndex()];
    }
    
    public void setSelected(int index)
    {
        if(index>=0 && index<comboBox.getItemCount()) comboBox.setSelectedIndex(index);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}
