package trypGUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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

    JButton[] buttons;

    JLabel typeLabel, paletteLabel;

    JComboBox typeComboBox, paletteComboBox;

    GridBagConstraints panelConstraints;
    
    Layer currentLayer;
    int selectedIndex;

    public void initGUI(ActionListener A) {
        layerFrame = new JFrame("Layers");
        layerFrame.setLayout(new GridBagLayout());//new GridBagLayout()
        layerFrame.setBounds(10, 10, 500, 500);
        layerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        layerFrame.setResizable(false);
        layerFrame.setLocationRelativeTo(null);
        layerFrame.setVisible(false);

        panelConstraints = new GridBagConstraints();
        panelConstraints.gridwidth = 2;
        panelConstraints.gridheight = 1;
        panelConstraints.gridx = 2;
        panelConstraints.gridy = 2;
        panelConstraints.fill = GridBagConstraints.BOTH;
        panelConstraints.weighty=1;

        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1;
        c.weighty = 1;

        layerScroll = new JScrollPane();

        c.gridwidth = 2;
        c.gridheight = 3;
        c.fill = GridBagConstraints.BOTH;

        layerFrame.add(layerScroll, c);

        c.gridwidth = 1;
        c.gridheight = 1;

        typeLabel = new JLabel("Type:");
        paletteLabel = new JLabel("Palette");

        c.gridx = 2;
        c.fill = GridBagConstraints.BOTH;

        layerFrame.add(typeLabel, c);

        c.gridy = 1;

        layerFrame.add(paletteLabel, c);

        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 2;

        layerPanel = new NullLayerPanel();

        layerFrame.add(layerPanel, c);

        c.gridwidth = 1;

        typeComboBox = new JComboBox(GenType.TYPENAMES);
        typeComboBox.addActionListener(this);
        
        paletteComboBox = new JComboBox();
        paletteComboBox.addActionListener(this);
        
        c.gridx = 3;
        c.gridy = 0;

        layerFrame.add(typeComboBox, c);

        c.gridy = 1;

        layerFrame.add(paletteComboBox, c);

        String[] buttonText = {"New", "Delete", "Save", "Cancel"};
        buttons = new JButton[4];

        c.gridy = 3;
        c.weighty = 0.05;
        for (int i = 0; i <= 3; i++) {
            buttons[i] = new JButton(buttonText[i]);
            buttons[i].addActionListener(this);
            c.gridx = i;
            layerFrame.add(buttons[i], c);
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
        layerFrame.remove(layerPanel);
        layerPanel = GenType.getPanel(type);
        layerPanel.initGUI();
        
        if(currentLayer != null)
        {
            layerPanel.setParams(currentLayer.getParams());
        }
        
        layerFrame.add(layerPanel, panelConstraints);
        layerFrame.repaint();
        layerFrame.revalidate();
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