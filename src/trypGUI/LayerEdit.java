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
import trypLayerPanels.*;

public class LayerEdit implements ActionListener, ChangeListener, ListSelectionListener
{
    JFrame JF_LayerFrame;
    
    JScrollPane JS_LayerScroll;
    
    JList JLS_LayerList;
    
    AbstractLayerPanel LayerPanel;
    
    JButton[] buttons;
    
    JLabel JL_Type, JL_Palette;
    
    JComboBox JC_Type, JC_Palette;
    
    public void initGUI(ActionListener A)
    {
        JF_LayerFrame = new JFrame("Layers");
        JF_LayerFrame.setLayout(new GridBagLayout());//new GridBagLayout()
        JF_LayerFrame.setBounds(10, 10, 500, 500);
        JF_LayerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JF_LayerFrame.setResizable(false);
        JF_LayerFrame.setLocationRelativeTo(null);
        JF_LayerFrame.setVisible(false);
        
        
        
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.weightx=1;
        c.weighty=1;
        
        JS_LayerScroll = new JScrollPane();
        
        c.gridwidth=2;
        c.gridheight=3;
        c.fill=GridBagConstraints.BOTH;
        
        JF_LayerFrame.add(JS_LayerScroll, c);
        
        c.gridwidth=1;
        c.gridheight=1;
        
        JL_Type = new JLabel("Type:");
        JL_Palette = new JLabel("Palette");
        
        c.gridx=2;
        c.fill=GridBagConstraints.BOTH;
        
        JF_LayerFrame.add(JL_Type, c);
        
        c.gridy=1;
        
        JF_LayerFrame.add(JL_Palette, c);
        
        LayerPanel = new CircularLayerPanel();
        LayerPanel.initGUI();
        
        c.gridx=1;
        c.gridy=2;
        c.gridwidth=3;
        
        JF_LayerFrame.add(LayerPanel, c);
        
        c.gridwidth=1;
        
        JC_Type=new JComboBox();
        JC_Palette = new JComboBox();
        
        c.gridx=3;
        c.gridy=0;
        
        JF_LayerFrame.add(JC_Type, c);
        
        c.gridy=1;
        
        JF_LayerFrame.add(JC_Palette, c);
        
        
        String[] buttonText = {"New","Delete","Save","Cancel"};
        buttons = new JButton[4];
        
        c.gridy=3;
        c.weighty=0.05;
        for(int i=0;i<=3;i++)
        {
            buttons[i]=new JButton(buttonText[i]);
            c.gridx=i;
            JF_LayerFrame.add(buttons[i], c);
        }
    }
    
    public void show()
    {
        JF_LayerFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }

    @Override
    public void stateChanged(ChangeEvent e) 
    {
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        
    }
}
