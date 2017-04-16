package trypGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import trypComponents.ColorBar;
import trypComponents.ColorPicker;
import trypComponents.GradientPanel;
import trypResources.ActionCodes;
import trypResources.Palette;
import trypListModels.PaletteListModel;

/**Allows the user to view and edit color palettes
 *
 * @author tiggerbiggo
 */
public class PalEdit implements ActionListener, ListSelectionListener, ChangeListener
{
    JFrame palEditFrame;
    
    Palette currentPalette;
    
    JScrollPane paletteScrollPane;
    
    JPanel editPanel;
    
    JSlider colorIndexSlider;
    
    ColorBar cBar;
    
    ColorPicker pickerStart;
    ColorPicker pickerEnd;
    
    GradientPanel gPanel;
    
    final int barNum = 13;
    
    int code, barPressed, barIndex, barSelected;
    
    
    JList paletteList;
    
    PaletteListModel pListModel = new PaletteListModel();
    
    /**Initialises the frame and it's components
     * 
     * @param A The action listener of the parent object
     */
    public void initGUI(ActionListener A)
    {
        palEditFrame = new JFrame("Palette Editor");
        palEditFrame.setLayout(null);
        palEditFrame.setBounds(10, 10, 1000, 500);
        palEditFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        palEditFrame.setResizable(false);
        palEditFrame.setLocationRelativeTo(null);
        palEditFrame.setVisible(false);
        
        paletteScrollPane = new JScrollPane();
        paletteScrollPane.setBounds(0, 0, 200, 500);
        palEditFrame.add(paletteScrollPane);
        
        editPanel = new JPanel();
        editPanel.setLayout(new GridBagLayout());
        editPanel.setBounds(200, 0, 800, 450);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill=GridBagConstraints.BOTH;
        c.weightx=0;
        c.weighty=0.1;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        c.gridheight=1;
        
        cBar = new ColorBar(barNum, this);
        editPanel.add(cBar, c);
        
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx=1;
        c.weighty=0.1;
        c.gridx=0;
        c.gridy=1;
        c.gridwidth=2;
        c.gridheight=1;
        
        colorIndexSlider = new JSlider();
        colorIndexSlider.setEnabled(false);
        colorIndexSlider.addChangeListener(this);
        editPanel.add(colorIndexSlider, c);
        
        pickerStart = new ColorPicker(true, this);
        pickerEnd = new ColorPicker(false, this);
        
        c.fill=GridBagConstraints.BOTH;
        c.weightx=1;
        c.weighty=0.4;
        c.gridx=0;
        c.gridy=2;
        c.gridwidth=1;
        c.gridheight=1;
        
        c.insets=new Insets(30,30,30,30);
        
        editPanel.add(pickerStart, c);
        
        c.fill=GridBagConstraints.BOTH;
        c.weightx=1;
        c.weighty=0.4;
        c.gridx=1;
        c.gridy=2;
        c.gridwidth=1;
        c.gridheight=1;
        
        
        
        editPanel.add(pickerEnd, c);
        
        c.insets=new Insets(0,0,0,0);
        
        c.fill=GridBagConstraints.BOTH;
        c.weightx=0;
        c.weighty=0.1;
        c.gridx=0;
        c.gridy=3;
        c.gridwidth=2;
        c.gridheight=1;
        
        gPanel=new GradientPanel(this);
        editPanel.add(gPanel, c);
        
        palEditFrame.add(editPanel);
        
        updatePaletteList();
    }
    
    /**Displays the frame and updates the Palette List
     * 
     */
    public void show()
    {
        palEditFrame.setVisible(true);
        updatePaletteList();
    }
    
    /**Adds a palette to the list model and updates the list
     * 
     * @param P The palette object to add
     */
    public void addPalette(Palette P)
    {
        pListModel.add(P);
        updatePaletteList();
    }
    
    /**Updates the palette list by removing it, assigning a new object then re-adding it to the scroll pane
     * 
     */
    public void updatePaletteList()
    {
        paletteScrollPane.removeAll();
        paletteList = new JList(pListModel);
        paletteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        paletteList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        paletteList.setBounds(0, 0, 200, 1000);
        paletteList.addListSelectionListener(this);
        paletteScrollPane.add(paletteList);
    }
    
    /**Updates the color bar with the current colours in the palette
     * 
     */
    public void updateColorBar()
    {
        if(currentPalette != null)
        {
            for(int i=0; i<cBar.getNum(); i++)
            {
                System.out.println("Setting Color: " + i + ", " + (i+barIndex));
                cBar.setColor(i, currentPalette.getColor(i+barIndex), i+barIndex);
                if(i+barIndex == barSelected)   cBar.setText(i, "*"+barSelected+"*");
            }
        }
    }
    
    /**Updates the palette from the list model and updates the color bar
     * 
     */
    public void updateCurrentPalette()
    {
        currentPalette = (Palette)pListModel.getElementAt(paletteList.getSelectedIndex());
        updateColorBar();
        if(currentPalette != null)
        {
            if(currentPalette.getNum()>cBar.getNum())
            {
                colorIndexSlider.setEnabled(true);
                colorIndexSlider.setMinimum(0);
                colorIndexSlider.setMaximum(currentPalette.getNum()-cBar.getNum());
                
                
                
                gPanel.setAllEnabled(true);
                gPanel.setMax(currentPalette.getNum()-1);
                gPanel.updateFields();
            }
            else 
            {
                colorIndexSlider.setEnabled(false);
            }
            colorIndexSlider.setValue(0);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object toCheck = e.getSource();
        int tmp = cBar.checkActions(toCheck);
        if(tmp != -1)
        {
            
            barPressed = tmp;
            barSelected=barPressed+barIndex;
            updateColorBar();
            
            
        }
        
        tmp = pickerStart.checkActions(toCheck);
        if(tmp != ActionCodes.NULLCODE)
        {
            if(tmp == ActionCodes.CODE_COLORPICKER_BUTTON)
            {
                
            }
        }
        
        tmp=pickerEnd.checkActions(toCheck);
        
        tmp=gPanel.checkActions(toCheck);
        if(tmp != ActionCodes.NULLCODE)
        {
            System.out.println(tmp);
            switch(tmp)
            {
                case ActionCodes.CODE_GRADIENTPANEL_SELECTSTART:
                    if(barSelected != -1)
                    {
                        gPanel.setStart(barSelected);
                    }
                    break;
                case ActionCodes.CODE_GRADIENTPANEL_SELECTEND:
                    if(barSelected != -1)
                    {
                        gPanel.setEnd(barSelected);
                    }
                    break;
                case ActionCodes.CODE_GRADIENTPANEL_MAKE:
                    currentPalette.setGradient(gPanel.getStart(), gPanel.getEnd(), pickerStart.getColor(), pickerEnd.getColor());
                    updateColorBar();
                    break;
                
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        if(e.getSource() == paletteList)
        {
            updateCurrentPalette();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) 
    {
        barIndex = colorIndexSlider.getValue();
        updateColorBar();
        System.out.println(barIndex);
    }
    
    /**
     * 
     * @return An array of all the palette objects
     */
    public Palette[] getPalettes()
    {
        return pListModel.getList();
    }
    
}
