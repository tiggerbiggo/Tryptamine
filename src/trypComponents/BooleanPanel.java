package trypComponents;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class BooleanPanel extends JPanel implements ActionListener
{
    
    JRadioButton buttons[];
    ButtonGroup bGroup;

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
            buttons[i].addActionListener(this);
        }
        buttons[0].setSelected(true);
    }
    
    public boolean getBoolean()
    {
        return buttons[0].isSelected();
    }
    
    public void setBoolean(boolean b)
    {
        if(b)buttons[0].setSelected(true);
        else buttons[1].setSelected(true);
    }
    
    public void init()
    {
        this.setLayout(new GridLayout(0,2));
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}
