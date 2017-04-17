package trypGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

/**
 *
 * @author amnesia
 */
public class PalEdit implements ActionListener, ListSelectionListener, ChangeListener
{
    JFrame JF_PalEdit;
    
    Palette currentPalette;
    
    JScrollPane JS_PaletteScroll;
    
    JPanel JP_EditPane;
    
    JSlider JSL_ColorIndex;
    
    ColorBar CB;
    
    ColorPicker CP1;
    ColorPicker CP2;
    
    GradientPanel GP;
    
    final int barNum = 13;
    
    int code, barPressed, barIndex, barSelected;
    
    
    JList JL_PaletteList;
    
    PaletteListModel PLM = new PaletteListModel();
    
    public void initGUI(ActionListener A)
    {
        JF_PalEdit = new JFrame("Palette Editor");
        JF_PalEdit.setLayout(null);//new GridBagLayout()
        JF_PalEdit.setBounds(10, 10, 1000, 500);
        JF_PalEdit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JF_PalEdit.setResizable(false);
        JF_PalEdit.setLocationRelativeTo(null);
        JF_PalEdit.setVisible(false);
        
        JS_PaletteScroll = new JScrollPane();
        JS_PaletteScroll.setBounds(0, 0, 200, 500);
        JF_PalEdit.add(JS_PaletteScroll);
        
        JP_EditPane = new JPanel();
        JP_EditPane.setLayout(new GridBagLayout());//new GridBagLayout()
        JP_EditPane.setBounds(200, 0, 800, 450);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill=GridBagConstraints.BOTH;
        c.weightx=0;
        c.weighty=0.1;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        c.gridheight=1;
        
        CB = new ColorBar(barNum, this);
        JP_EditPane.add(CB, c);
        
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx=1;
        c.weighty=0.1;
        c.gridx=0;
        c.gridy=1;
        c.gridwidth=2;
        c.gridheight=1;
        
        JSL_ColorIndex = new JSlider();
        JSL_ColorIndex.setEnabled(false);
        JSL_ColorIndex.addChangeListener(this);
        JP_EditPane.add(JSL_ColorIndex, c);
        
        CP1 = new ColorPicker(true, this);
        CP2 = new ColorPicker(false, this);
        
        c.fill=GridBagConstraints.BOTH;
        c.weightx=1;
        c.weighty=0.4;
        c.gridx=0;
        c.gridy=2;
        c.gridwidth=1;
        c.gridheight=1;
        
        c.insets=new Insets(30,30,30,30);
        
        JP_EditPane.add(CP1, c);
        
        c.fill=GridBagConstraints.BOTH;
        c.weightx=1;
        c.weighty=0.4;
        c.gridx=1;
        c.gridy=2;
        c.gridwidth=1;
        c.gridheight=1;
        
        
        
        JP_EditPane.add(CP2, c);
        
        c.insets=new Insets(0,0,0,0);
        
        c.fill=GridBagConstraints.BOTH;
        c.weightx=0;
        c.weighty=0.1;
        c.gridx=0;
        c.gridy=3;
        c.gridwidth=2;
        c.gridheight=1;
        
        GP=new GradientPanel(this);
        JP_EditPane.add(GP, c);
        
        JF_PalEdit.add(JP_EditPane);
        
        updatePaletteList();
    }
    
    public void show()
    {
        JF_PalEdit.setVisible(true);
        updatePaletteList();
    }
    
    public void addPalette(Palette P)
    {
        PLM.add(P);
        updatePaletteList();
    }
    
    public void addPaletteList(ArrayList<Palette> P)
    {
        PLM.addList(P);
        updatePaletteList();
    }
    
    public void updatePaletteList()
    {
        JS_PaletteScroll.removeAll();
        JL_PaletteList = new JList(PLM);
        JL_PaletteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JL_PaletteList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        JL_PaletteList.setBounds(0, 0, 200, 1000);
        JL_PaletteList.addListSelectionListener(this);
        JS_PaletteScroll.add(JL_PaletteList);
    }
    
    public void updateColorBar()
    {
        if(currentPalette != null)
        {
            for(int i=0; i<CB.getNum(); i++)
            {
                CB.setColor(i, currentPalette.getColor(i+barIndex), i+barIndex);
                if(i+barIndex == barSelected)   CB.setText(i, "*"+barSelected+"*");
            }
        }
    }
    
    public int checkActions(Object toCheck)
    {
        return ActionCodes.NULLCODE;
    }
    
    
    
    public void updateCurrentPalette()
    {
        currentPalette = (Palette)PLM.getElementAt(JL_PaletteList.getSelectedIndex());
        updateColorBar();
        if(currentPalette != null)
        {
            if(currentPalette.getNum()>CB.getNum())
            {
                JSL_ColorIndex.setEnabled(true);
                JSL_ColorIndex.setMinimum(0);
                JSL_ColorIndex.setMaximum(currentPalette.getNum()-CB.getNum());
                
                
                
                GP.setAllEnabled(true);
                GP.setMax(currentPalette.getNum()-1);
                GP.updateFields();
            }
            else 
            {
                JSL_ColorIndex.setEnabled(false);
            }
            JSL_ColorIndex.setValue(0);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object toCheck = e.getSource();
        int tmp = CB.checkActions(toCheck);
        if(tmp != -1)
        {
            
            barPressed = tmp;
            barSelected=barPressed+barIndex;
            updateColorBar();
            
            
        }
        
        tmp = CP1.checkActions(toCheck);
        if(tmp != ActionCodes.NULLCODE)
        {
            if(tmp == ActionCodes.CODE_COLORPICKER_BUTTON)
            {
                
            }
        }
        
        tmp=CP2.checkActions(toCheck);
        
        tmp=GP.checkActions(toCheck);
        if(tmp != ActionCodes.NULLCODE)
        {
            switch(tmp)
            {
                case ActionCodes.CODE_GRADIENTPANEL_SELECTSTART:
                    if(barSelected != -1)
                    {
                        GP.setStart(barSelected);
                    }
                    break;
                case ActionCodes.CODE_GRADIENTPANEL_SELECTEND:
                    if(barSelected != -1)
                    {
                        GP.setEnd(barSelected);
                    }
                    break;
                case ActionCodes.CODE_GRADIENTPANEL_MAKE:
                    currentPalette.setGradient(GP.getStart(), GP.getEnd(), CP1.getColor(), CP2.getColor());
                    updateColorBar();
                    break;
                
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        if(e.getSource() == JL_PaletteList)
        {
            updateCurrentPalette();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) 
    {
        barIndex = JSL_ColorIndex.getValue();
        updateColorBar();
    }
    
    public ArrayList<Palette> getPalettes()
    {
        return PLM.getList();
    }
    
}
