/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypComponents;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author timot_000
 */
public class IntegerPanel extends JPanel implements ActionListener
{
    String text;
    boolean canBeNegative;
    
    JLabel JL_Text;
    JTextField JT_Field;

    public IntegerPanel(String text, boolean canBeNegative) 
    {
        this.text = text;
        this.canBeNegative = canBeNegative;
        init();
    }
    
    public void init()
    {
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill=GridBagConstraints.BOTH;
        
        c.weightx=1;
        c.weighty=1;
        
        JL_Text = new JLabel(text);
        
        JT_Field = new JTextField();
        JT_Field.addActionListener(this);
        
        this.add(JL_Text, c);
        
        c.gridx=1;
        
        this.add(JT_Field, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}
