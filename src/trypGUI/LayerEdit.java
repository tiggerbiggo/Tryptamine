package trypGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import trypGenerators.AbstractGenerator;
import trypGenerators.GenType;
import trypLayerPanels.*;
import trypListModels.LayerListModel;
import trypParams.Parameter;
import trypResources.Layer;

public class LayerEdit implements ActionListener, ChangeListener, ListSelectionListener {

    JFrame layerFrame;

    JScrollPane layerScroll;

    JList layerList;
    LayerListModel listModel;

    AbstractLayerPanel layerPanel;
    
    JPanel layerContainer;

    JButton[] buttons;

    JLabel typeLabel, paletteLabel;

    JComboBox typeComboBox, paletteComboBox;

    GridBagConstraints panelConstraints;
    
    Layer currentLayer;
    int selectedIndex;

    public void initGUI(ActionListener A) {
        layerFrame = new JFrame("Layers");
        layerFrame.setLayout(null);
        layerFrame.setBounds(10, 10, 520, 540);
        layerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        layerFrame.setResizable(true);
        layerFrame.setLocationRelativeTo(null);
        layerFrame.setVisible(false);

        layerScroll = new JScrollPane();
        layerScroll.setBounds(0, 0, 200, 460);

        layerFrame.add(layerScroll);
        
        typeLabel = new JLabel("Type:");
        typeLabel.setBounds(200,0,100,30);
        layerFrame.add(typeLabel);
        
        paletteLabel = new JLabel("Palette");
        paletteLabel.setBounds(200,30,100,30);
        layerFrame.add(paletteLabel);

        layerContainer = new JPanel();
        layerContainer.setBounds(200,60,300,400);
        layerContainer.setBackground(Color.red);
        layerContainer.setLayout(new GridLayout(1,1));
        layerFrame.add(layerContainer);
        
        layerPanel = new NullLayerPanel();
        layerContainer.add(layerPanel);

        typeComboBox = new JComboBox(GenType.TYPENAMES);
        typeComboBox.addActionListener(this);
        typeComboBox.setBounds(300, 0, 200, 30);
        layerFrame.add(typeComboBox);
        
        paletteComboBox = new JComboBox();
        paletteComboBox.addActionListener(this);
        paletteComboBox.setBounds(300, 30, 200, 30);
        layerFrame.add(paletteComboBox);

        String[] buttonText = {"New", "Delete", "Save", "Cancel"};
        buttons = new JButton[4];
        for (int i = 0; i <= 3; i++) {
            buttons[i] = new JButton(buttonText[i]);
            buttons[i].addActionListener(this);
            buttons[i].setBounds(i*125, 460, 125, 40);
            layerFrame.add(buttons[i]);
        }
        listModel = new LayerListModel();
        selectedIndex=-1;

        listModel.add(new Layer("Circular", GenType.CIRCULAR));
        listModel.add(new Layer("Formula", GenType.FORMULA));
        updateList();

    }

    public void updateList() {
        layerScroll.removeAll();
        layerList = new JList(listModel);
        layerList.setBounds(0, 0, 500, 1000);
        layerList.addListSelectionListener(this);
        layerScroll.add(layerList);
        layerScroll.repaint();
    }

    public void show() {
        layerFrame.setVisible(true);
    }

    public void setAllEnabled(boolean state) 
    {
        paletteComboBox.setEnabled(state);
        typeComboBox.setEnabled(state);
        for (int i = 1; i < 4; i++) 
        {
            buttons[i].setEnabled(state);
        }
    }

    public void update() 
    {
        int type;
        currentLayer = (Layer) listModel.getElementAt(selectedIndex);
        if (currentLayer != null) 
        {
            setAllEnabled(true);
            type = currentLayer.getType();
            typeComboBox.setSelectedIndex(type);
            
        } 
        else 
        {
            setAllEnabled(false);
            type=GenType.NULL;
            typeComboBox.setSelectedIndex(0);
        }
        updateLayerPanel(type);
    }
    
    public void updateLayerPanel(int type)
    {
        layerContainer.remove(layerPanel);
        layerPanel = GenType.getPanel(type);
        layerPanel.initGUI();
        
        if(currentLayer != null)
        {
            layerPanel.setParams(currentLayer.getParams());
        }
        
        layerContainer.add(layerPanel, panelConstraints);
        
        layerFrame.revalidate();
        layerFrame.repaint();
    }

    public void updateType()
    {
        updateLayerPanel(typeComboBox.getSelectedIndex());
        if(currentLayer != null) currentLayer.setType(typeComboBox.getSelectedIndex());
    }
    
    public AbstractGenerator[] getGens()
    {
        AbstractGenerator[] toReturn = new AbstractGenerator[listModel.getSize()];
        
        for(int i=0;i<toReturn.length;i++)
        {
            toReturn[i] = ((Layer)listModel.getElementAt(i)).getGenerator();
        }
        
        return toReturn;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object toCheck = e.getSource();
        if(toCheck == typeComboBox)
        {
            updateType();
        }
        for(int i=0;i<4;i++)
        {
            if(toCheck == buttons[i])
            {
                switch(i)
                {
                    case 0://add
                        listModel.add(new Layer("New Layer"));
                        updateList();
                        break;
                    case 1://delete
                        if(selectedIndex!=-1)
                        {
                            listModel.removeElement(selectedIndex);
                            updateList();
                        }
                        break;
                    case 2://save
                        if(checkParams())
                        {
                            listModel.setElement(selectedIndex, currentLayer);
                        }
                        break;
                    case 3://cancel
                        break;
                }
            }
        }
    }

    public boolean checkParams()
    {
        Parameter[] toCheck = layerPanel.getParams();
        if(toCheck != null && currentLayer != null)
        {
            try
            {
                currentLayer.setParams(toCheck);
                return true;
            }
            catch(Exception e){}
        }
        return false;
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        selectedIndex=layerList.getSelectedIndex();
        update();
    }
}