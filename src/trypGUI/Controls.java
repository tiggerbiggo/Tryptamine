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

/**A window used to control which frame the animation is currently on. Currently only contains left and right arrows
 *
 * @author tiggerbiggo
 */
public class Controls
{
    
    JFrame controlFrame = new JFrame("Controls");
    JButton forwardButton = new JButton(">");
    JButton backwardButton = new JButton("<");
    
    /**
     * 
     * @param A The action listener of the parent object to allow for data transfer between objects
     */
    public void initGUI(ActionListener A)
    {
        controlFrame.setLayout(null);
        controlFrame.setBounds(10, 10, 500, 500);
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setResizable(false);
        controlFrame.setLocationRelativeTo(null);
        controlFrame.setVisible(false);

        forwardButton.setBounds(60, 10, 40, 40);
        forwardButton.setMargin(new Insets(0, 0, 0, 0));
        forwardButton.addActionListener(A);
        controlFrame.add(forwardButton);
        
        backwardButton.setBounds(10, 10, 40, 40);
        backwardButton.setMargin(new Insets(0, 0, 0, 0));
        backwardButton.addActionListener(A);
        controlFrame.add(backwardButton);
    }
    
    /**
     * 
     * @see trypResources.ActionCodes
     * @param toCheck The object to check against elements in this class
     * @return An action code based on trypResources.ActionCodes
     */
    public int checkActions(Object toCheck)
    {
        if(toCheck == forwardButton) 
        {
            return ActionCodes.CODE_CONTROL_FORWARD;
        }
        else if (toCheck == backwardButton) 
        {
            return ActionCodes.CODE_CONTROL_BACKWARD;
        }
        else return ActionCodes.NULLCODE;
    }
    
    /**Shows the frame
     * 
     */
    public void show()
    {
        controlFrame.setVisible(true);
    }
}
