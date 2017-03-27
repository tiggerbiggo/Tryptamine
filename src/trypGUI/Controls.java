/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypGUI;

import java.awt.Insets;
import java.awt.event.*;
import javax.swing.*;
import trypResources.ActionCodes;

/**
 *
 * @author amnesia
 */
public class Controls implements ActionListener
{
    
    JFrame JF_Controls = new JFrame("Controls");
    JButton JB_Forward = new JButton(">");
    JButton JB_Backward = new JButton("<");
    
    public void initGUI(ActionListener A)
    {
        JF_Controls.setLayout(null);
        JF_Controls.setBounds(10, 10, 500, 500);
        JF_Controls.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JF_Controls.setResizable(false);
        JF_Controls.setLocationRelativeTo(null);
        JF_Controls.setVisible(false);

        JB_Forward.setBounds(60, 10, 40, 40);
        JB_Forward.setMargin(new Insets(0, 0, 0, 0));
        JB_Forward.addActionListener(A);
        JF_Controls.add(JB_Forward);
        
        JB_Backward.setBounds(10, 10, 40, 40);
        JB_Backward.setMargin(new Insets(0, 0, 0, 0));
        JB_Backward.addActionListener(A);
        JF_Controls.add(JB_Backward);
    }
    
    public int checkActions(Object toCheck)
    {
        if(toCheck == JB_Forward) 
        {
            return ActionCodes.CODE_CONTROL_FORWARD;
        }
        else if (toCheck == JB_Backward) 
        {
            return ActionCodes.CODE_CONTROL_BACKWARD;
        }
        else return ActionCodes.NULLCODE;
    }
    
    public void show()
    {
        JF_Controls.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
    }
}
