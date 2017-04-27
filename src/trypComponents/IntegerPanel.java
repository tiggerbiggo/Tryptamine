package trypComponents;

import java.awt.GridLayout;
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

    public void setInt(int n)
    {
        if(canBeNegative) JT_Field.setText(""+n);
        else
        {
            if(n<0) JT_Field.setText("0");
            else JT_Field.setText(""+n);
        }
    }
    
    public int getInt() throws Exception
    {
        try
        {
            int tmp = Integer.parseInt(JT_Field.getText());
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
    
    public void init()
    {
        this.setLayout(new GridLayout(0,1));
        
        JL_Text = new JLabel(text);
        
        JT_Field = new JTextField("0");
        JT_Field.addActionListener(this);
        
        this.add(JL_Text);
        
        this.add(JT_Field);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}
