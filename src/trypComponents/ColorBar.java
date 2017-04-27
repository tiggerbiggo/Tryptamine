/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypComponents;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import trypResources.ColorTools;

/**
 *
 * @author amnesia
 */
public class ColorBar extends JPanel
{
    int num;
    
    ActionListener A;
    String nullText = "...";
    JButton[] buttons;

    public ColorBar(int num, ActionListener A)
    {
        //if(num<0)
        //{
        //    throw new IllegalArgumentException("Number cannot be negative!");
        //}
        this.num=num;
        this.A=A;
        init();
    }
    
    public void setText(int index, String text)
    {
        if(checkIndex(index))
        {
            buttons[index].setText(text);
        }
    }
    
    private void init()
    {
        
        this.setVisible(true);
        this.setLayout(new GridLayout(1,0));
        
        buttons = new JButton[num];
        for(int i=0; i<num; i++)
        {
            buttons[i] = new JButton(nullText);
            buttons[i].addActionListener(A);
            this.add(buttons[i]);
        }
    }
    
    private boolean checkValid(int toCheck)
    {
        return toCheck >=0;
    }
    

    public int checkActions(Object toCheck)
    {
        for(int i=0; i<num; i++)
        {
            if(toCheck == buttons[i])
            {
                return i;
            }
        }
        return -1;
    }
    
    public int getNum()
    {
        return num;
    }
    
    public boolean checkIndex(int index)
    {
        return index>=0 && index<num;
    }
    
    public void setColor(int index, Color C, int ColorIndex)
    {
        if(checkIndex(index))
        {
            if(C != null)
            {
                buttons[index].setBackground(C);
                buttons[index].setText("" + ColorIndex);
                buttons[index].setForeground(ColorTools.invert(C));
            }
            else
            {
                buttons[index].setBackground(Color.white);
                buttons[index].setForeground(Color.black);
                buttons[index].setText(nullText);
            }
        }
    }
    
    public void reset()
    {
        int i=0;
        for(JButton b : buttons)
        {
            setColor(i, null, 0);
            i++;
        }
    }
    
    
}
