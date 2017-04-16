package trypComponents;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**A panel allowing the user to enter a string
 * 
 * @author tiggerbiggo
 */
public class StringPanel extends JPanel
{
    String text;
    
    JLabel JL_Text;
    JTextField JT_Field;

    public StringPanel(String text) 
    {
        this.text = text;
        init();
    }
    
    /**
     * 
     * @return The string entered in the text field
     */
    public String getString()
    {
        return JT_Field.getText();
        
    }
    
    /**Sets the layout of components in the panel
     * 
     */
    public void init()
    {
        this.setLayout(new GridLayout(0,1));
        
        JL_Text = new JLabel(text);
        
        JT_Field = new JTextField();
        
        this.add(JL_Text);
        
        this.add(JT_Field);
    }
}
