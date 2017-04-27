/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypGUI;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.*;
import javax.swing.*;
import trypResources.ActionCodes;

/**
 *
 * @author amnesia
 */
public class Settings implements ActionListener
{
    
    JFrame JF_Settings = new JFrame("Settings");
    
    JTextField TF_Filename = new JTextField(20);
    JTextField TF_ResX = new JTextField(5);
    JTextField TF_ResY = new JTextField(5);
    
    JRadioButton JR_Gif = new JRadioButton("Gif", true);
    JRadioButton JR_Png = new JRadioButton("Png", false);
    
    ButtonGroup BG = new ButtonGroup();
    
    JButton JB_Apply = new JButton("Apply");
    
    JLabel JL_Filename = new JLabel("File Name:");
    JLabel JL_Output = new JLabel("Output Type:");
    JLabel JL_Res = new JLabel("Resolution:");
    
    JLabel JL_ResX = new JLabel("X");
    JLabel JL_ResY = new JLabel("Y");
    
    public void initGUI(ActionListener A)
    {
        JF_Settings.setLayout(null);
        JF_Settings.setBounds(10, 10, 220, 220);
        JF_Settings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JF_Settings.setResizable(false);
        JF_Settings.setLocationRelativeTo(null);
        JF_Settings.setVisible(false);

        TF_Filename.setBounds(110, 10, 100, 20);
        TF_Filename.addActionListener(A);
        JF_Settings.add(TF_Filename);
        TF_ResX.setBounds(110, 70, 50, 20);
        TF_ResX.addActionListener(A);
        JF_Settings.add(TF_ResX);
        TF_ResY.setBounds(160, 70, 50, 20);
        TF_ResY.addActionListener(A);
        JF_Settings.add(TF_ResY);
        
        JL_Filename.setBounds(10, 10, 100, 20);
        JF_Settings.add(JL_Filename);
        JL_Output.setBounds(10, 40, 100, 20);
        JF_Settings.add(JL_Output);
        JL_Res.setBounds(10, 70, 100, 20);
        JF_Settings.add(JL_Res);
        JL_ResX.setBounds(125, 90, 20, 20);
        JF_Settings.add(JL_ResX);
        JL_ResY.setBounds(175, 90, 20, 20);
        JF_Settings.add(JL_ResY);
        
        JR_Gif.setBounds(110, 40, 50, 20);
        JR_Gif.setMargin(new Insets(0, 0, 0, 0));
        JR_Gif.addActionListener(A);
        BG.add(JR_Gif);
        JF_Settings.add(JR_Gif);
        
        
        JR_Png.setBounds(160, 40, 50, 20);
        JR_Png.setMargin(new Insets(0, 0, 0, 0));
        JR_Png.addActionListener(A);
        BG.add(JR_Png);
        JF_Settings.add(JR_Png);
        
        JB_Apply.setBounds(10, 110, 200, 20);
        JB_Apply.setMargin(new Insets(0, 0, 0, 0));
        JB_Apply.addActionListener(A);
        JF_Settings.add(JB_Apply);
        
    }
    
    public int checkActions(Object toCheck)
    {
        checkFields();
        if(toCheck == TF_Filename) return ActionCodes.CODE_SETTINGS_FILENAME;
        else if (toCheck == TF_ResX) return ActionCodes.CODE_SETTINGS_RESX;
        else if (toCheck == TF_ResY) return ActionCodes.CODE_SETTINGS_RESY;
        else if (toCheck == JR_Gif) return ActionCodes.CODE_SETTINGS_GIF;
        else if (toCheck == JR_Png) return ActionCodes.CODE_SETTINGS_PNG;
        else if (toCheck == JB_Apply) return ActionCodes.CODE_SETTINGS_APPLY;
        else return ActionCodes.NULLCODE;
    }
    
    public boolean checkFields()
    {
        boolean isValid = true;
        
        int maxFilenameLength = 20;
        int maxRes = 10000;
        
        Color error = Color.red;
        Color normal = Color.white;
        
        
        if(TF_Filename.getText().length()>maxFilenameLength)
        {
            TF_Filename.setBackground(error);
            isValid = false;
        }
        else TF_Filename.setBackground(normal);
        
        try
        {
            int tmp = Integer.parseInt(TF_ResX.getText());
            if(tmp >maxRes && tmp >=1)
            {
                TF_ResX.setBackground(error);
                isValid = false;
            }
            else TF_ResX.setBackground(normal);
        }
        catch(NumberFormatException e)
        {
            TF_ResX.setBackground(error);
            isValid = false;
        }
        
        try
        {
            int tmp = Integer.parseInt(TF_ResY.getText());
            if(tmp >maxRes && tmp >=1)
            {
                TF_ResY.setBackground(error);
                isValid = false;
            }
            else TF_ResY.setBackground(normal);
        }
        catch(NumberFormatException e)
        {
            TF_ResY.setBackground(error);
            isValid = false;
        }
        
        return isValid;
        
    }
    
    public void show()
    {
        JF_Settings.setVisible(true);
    }
    
    public int getResX()
    {
        if(checkFields())
        {
            return Integer.parseInt(TF_ResX.getText());
        }
        return -1;
    }
    
    public int getResY()
    {
        if(checkFields())
        {
            return Integer.parseInt(TF_ResY.getText());
        }
        return -1;
    }
    
    public String getFilename()
    {
        if(checkFields())
        {
            return TF_Filename.getText();
        }
        return null;
    }
    
    public boolean getGif()
    {
        return JR_Gif.isSelected();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
    }
}
