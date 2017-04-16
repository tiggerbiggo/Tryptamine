package trypGUI;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.*;
import javax.swing.*;
import trypResources.ActionCodes;

/**A settings window which allows the user to change the name, resolution and file type of the image render
 *
 * @author tiggerbiggo
 */
public class Settings
{
    
    JFrame settingsFrame = new JFrame("Settings");
    
    JTextField fileNameField = new JTextField(20);
    JTextField resolutionX = new JTextField(5);
    JTextField resolutionY = new JTextField(5);
    
    JRadioButton gifRadioButton = new JRadioButton("Gif", true);
    JRadioButton pngRadioButton = new JRadioButton("Png", false);
    
    ButtonGroup buttonGroup = new ButtonGroup();
    
    JButton applyButton = new JButton("Apply");
    
    JLabel fileNameLabel = new JLabel("File Name:");
    JLabel outputLabel = new JLabel("Output Type:");
    JLabel resLabel = new JLabel("Resolution:");
    
    JLabel resXLabel = new JLabel("X");
    JLabel resYLabel = new JLabel("Y");
    
    /**Initialises the frame and its components
     * 
     * @param A The actionListener of the parent class
     */
    public void initGUI(ActionListener A)
    {
        settingsFrame.setLayout(null);
        settingsFrame.setBounds(10, 10, 220, 220);
        settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingsFrame.setResizable(false);
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setVisible(false);

        fileNameField.setBounds(110, 10, 100, 20);
        fileNameField.addActionListener(A);
        settingsFrame.add(fileNameField);
        resolutionX.setBounds(110, 70, 50, 20);
        resolutionX.addActionListener(A);
        settingsFrame.add(resolutionX);
        resolutionY.setBounds(160, 70, 50, 20);
        resolutionY.addActionListener(A);
        settingsFrame.add(resolutionY);
        
        fileNameLabel.setBounds(10, 10, 100, 20);
        settingsFrame.add(fileNameLabel);
        outputLabel.setBounds(10, 40, 100, 20);
        settingsFrame.add(outputLabel);
        resLabel.setBounds(10, 70, 100, 20);
        settingsFrame.add(resLabel);
        resXLabel.setBounds(125, 90, 20, 20);
        settingsFrame.add(resXLabel);
        resYLabel.setBounds(175, 90, 20, 20);
        settingsFrame.add(resYLabel);
        
        gifRadioButton.setBounds(110, 40, 50, 20);
        gifRadioButton.setMargin(new Insets(0, 0, 0, 0));
        gifRadioButton.addActionListener(A);
        buttonGroup.add(gifRadioButton);
        settingsFrame.add(gifRadioButton);
        
        
        pngRadioButton.setBounds(160, 40, 50, 20);
        pngRadioButton.setMargin(new Insets(0, 0, 0, 0));
        pngRadioButton.addActionListener(A);
        buttonGroup.add(pngRadioButton);
        settingsFrame.add(pngRadioButton);
        
        applyButton.setBounds(10, 110, 200, 20);
        applyButton.setMargin(new Insets(0, 0, 0, 0));
        applyButton.addActionListener(A);
        settingsFrame.add(applyButton);
        
    }
    
    /**
     * 
     * @see trypResources.ActionCodes
     * @param toCheck The object to check against elements in this class
     * @return An action code based on trypResources.ActionCodes
     */
    public int checkActions(Object toCheck)
    {
        checkFields();
        if(toCheck == fileNameField) return ActionCodes.CODE_SETTINGS_FILENAME;
        else if (toCheck == resolutionX) return ActionCodes.CODE_SETTINGS_RESX;
        else if (toCheck == resolutionY) return ActionCodes.CODE_SETTINGS_RESY;
        else if (toCheck == gifRadioButton) return ActionCodes.CODE_SETTINGS_GIF;
        else if (toCheck == pngRadioButton) return ActionCodes.CODE_SETTINGS_PNG;
        else if (toCheck == applyButton) return ActionCodes.CODE_SETTINGS_APPLY;
        else return ActionCodes.NULLCODE;
    }
    
    /**Checks user input and highlights incorrect fields
     * 
     * @return True if all fields valid, else false
     */
    public boolean checkFields()
    {
        boolean isValid = true;
        
        int maxFilenameLength = 20;
        int maxRes = 10000;
        
        Color error = Color.red;
        Color normal = Color.white;
        
        
        if(fileNameField.getText().length()>maxFilenameLength)
        {
            fileNameField.setBackground(error);
            isValid = false;
        }
        else fileNameField.setBackground(normal);
        
        try
        {
            int tmp = Integer.parseInt(resolutionX.getText());
            if(tmp >maxRes && tmp >=1)
            {
                resolutionX.setBackground(error);
                isValid = false;
            }
            else resolutionX.setBackground(normal);
        }
        catch(NumberFormatException e)
        {
            resolutionX.setBackground(error);
            isValid = false;
        }
        
        try
        {
            int tmp = Integer.parseInt(resolutionY.getText());
            if(tmp >maxRes && tmp >=1)
            {
                resolutionY.setBackground(error);
                isValid = false;
            }
            else resolutionY.setBackground(normal);
        }
        catch(NumberFormatException e)
        {
            resolutionY.setBackground(error);
            isValid = false;
        }
        
        return isValid;
        
    }
    
    /**
     * Displays the JFrame
     */
    public void show()
    {
        settingsFrame.setVisible(true);
    }
    
    /**
     * 
     * @return The X resolution input by the user, -1 if invalid
     */
    public int getResX()
    {
        if(checkFields())
        {
            return Integer.parseInt(resolutionX.getText());
        }
        return -1;
    }
    
    /**
     * 
     * @return The Y resolution input by the user, -1 if invalid
     */
    public int getResY()
    {
        if(checkFields())
        {
            return Integer.parseInt(resolutionY.getText());
        }
        return -1;
    }
    
    /**
     * 
     * @return The file name input by the user, null if invalid
     */
    public String getFilename()
    {
        if(checkFields())
        {
            return fileNameField.getText();
        }
        return null;
    }
    
    /**
     * 
     * @return The state of the Gif radio button
     */
    public boolean getGif()
    {
        return gifRadioButton.isSelected();
    }
}
