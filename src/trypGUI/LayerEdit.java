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

/**Allows the editing of layers to change the look of the image
 * 
 * @author tiggerbiggo
 */
public class LayerEdit implements ActionListener, ChangeListener, ListSelectionListener 
{

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

    /**Sets up the components of the GUI
     * 
     * @param A The action listener of the parent object
     */
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

    /**Removes and re creates the JList to update the elements displayed
     * 
     */
    public void updateList() {
        layerScroll.removeAll();
        layerList = new JList(listModel);
        layerList.setBounds(0, 0, 500, 1000);
        layerList.addListSelectionListener(this);
        layerScroll.add(layerList);
        layerScroll.repaint();
    }

    /**Displays the JFrame
     * 
     */
    public void show() {
        layerFrame.setVisible(true);
    }

    /**Sets the enabled state of select elements to control program flow
     * 
     * @param state The state to set the elements to
     */
    public void setAllEnabled(boolean state) 
    {
        paletteComboBox.setEnabled(state);
        typeComboBox.setEnabled(state);
        for (int i = 1; i < 4; i++) 
        {
            buttons[i].setEnabled(state);
        }
    }

    /**Performs an update on the currently selected layer, and the AbstractLayerPanel object
     * @see trypLayerPanels
     */
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
    
    /**Updates the layer panel and the parameters contained within it
     * 
     * @see trypGenerators.GenType
     * @param type The type of panel to fetch
     */
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

    /**Updates the layer panel and the Layer object with the appropriate type
     * 
     */
    public void updateType()
    {
        updateLayerPanel(typeComboBox.getSelectedIndex());
        if(currentLayer != null) currentLayer.setType(typeComboBox.getSelectedIndex());
    }
    
    /**Gets an array of all the generators from the list model
     * 
     * @return The array of generators, any given element will be null if parameters entered by the user are invalid or missing, and if no elements exist will return null
     */
    public AbstractGenerator[] getGens()
    {
        if(listModel.getSize()>=1)
        {
            AbstractGenerator[] toReturn = new AbstractGenerator[listModel.getSize()];

            for(int i=0;i<toReturn.length;i++)
            {
                toReturn[i] = ((Layer)listModel.getElementAt(i)).getGenerator();
            }

            return toReturn;
        }
        return null;
    }
    
    /**Deals with actions fired from elements and calls the appropriate methods
     * 
     * @param e The event object
     */
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
                        if(updateParams())
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

    /**Updates the parameters contained in the currentLayer object
     * 
     * @return true if parameters are valid and currentLayer exists, else false
     */
    public boolean updateParams()
    {
        Parameter[] toCheck = layerPanel.getParams();
        if(toCheck != null && currentLayer != null)
        {
            currentLayer.setParams(toCheck);
            return true;
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