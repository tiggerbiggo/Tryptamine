package trypComponents;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StringPanel extends JPanel implements ActionListener
{
    String text;
    
    JLabel JL_Text;
    JTextField JT_Field;

    public StringPanel(String text) 
    {
        this.text = text;
        init();
    }
    
    public String getString()
    {
        return JT_Field.getText();
        
    }
    
    public void init()
    {
        this.setLayout(new GridLayout(0,1));
        
        JL_Text = new JLabel(text);
        
        JT_Field = new JTextField();
        JT_Field.addActionListener(this);
        
        this.add(JL_Text);
        
        this.add(JT_Field);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}
