package trypGUI;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import trypResources.ActionCodes;

/**A window to allow the user to control the frames in the animation
 *
 * @author tiggerbiggo
 */
public class Controls implements ActionListener
{
    
    JFrame controlFrame;
    JButton forward;
    JButton playPause;
    JButton back;
    
    public void initGUI(ActionListener A)
    {
        controlFrame = new JFrame("Controls");
        controlFrame.setLayout(new GridLayout(1,0));
        controlFrame.setBounds(10, 10, 300, 200);
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setResizable(false);
        controlFrame.setLocationRelativeTo(null);
        controlFrame.setVisible(false);

        back = new JButton("<");
        back.addActionListener(A);
        controlFrame.add(back);
        
        playPause = new JButton("Play");
        playPause.addActionListener(A);
        controlFrame.add(playPause);
        
        forward = new JButton(">");
        forward.addActionListener(A);
        controlFrame.add(forward);
    }
    
    public int checkActions(Object toCheck)
    {
        if(toCheck == forward) 
        {
            return ActionCodes.CODE_CONTROL_FORWARD;
        }
        else if (toCheck == back) 
        {
            return ActionCodes.CODE_CONTROL_BACKWARD;
        }
        else if (toCheck == playPause) 
        {
            return ActionCodes.CODE_CONTROL_PLAYPAUSE;
        }
        else return ActionCodes.NULLCODE;
    }
    
    public void show()
    {
        controlFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
    }
}
