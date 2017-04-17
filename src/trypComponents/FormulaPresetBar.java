package trypComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import trypGenerators.AbstractGenerator;
import trypGenerators.Gen_Formula;
import trypResources.Formula;
import trypResources.Function;
import trypResources.Operation;
import trypResources.Palette;
import tryptamine.CanvasWriter;
import tryptamine.DynamicCanvas;
import tryptamine.GapPresets;
import tryptamine.ImageManager;

/**
 *
 * @author tiggerbiggo
 */
public class FormulaPresetBar extends JPanel implements ActionListener, ChangeListener
{
    ArrayList<JButton> buttons;
    ArrayList<Formula> formulas;
    ArrayList<DynamicCanvas> canvases;
    Palette P, R;
    
    JSlider[] sliders;
    
    JPanel buttonPanel, sliderPanel;
    
    
    int x, y, selected; 
    double coeff, freq, coCoeff, freqEff;
    
    public FormulaPresetBar()
    {
        buttonPanel = new JPanel();
        sliderPanel = new JPanel();
        
        buttons = new ArrayList();
        
        x=60;
        y=40;
        this.coeff=0;
        this.freq=0;
        this.selected=-1;
        
        this.coCoeff = 1/25.0;
        this.freqEff = 1/100.0;
        
        setupFormulas();
        
        buttonPanel.setLayout(new GridLayout(1,0));
        sliderPanel.setLayout(new GridLayout(1,2));
        this.setLayout(new GridLayout(2,1));
        
        P = new Palette(10, 0);
        P.setGradient(0, 9, Color.black, Color.white);
        R = new Palette(10, 0);
        R.setGradient(0, 9, Color.red, Color.yellow);
        
        for(Formula f : formulas)
        {
            buttons.add(new JButton());
            
        }
        for(JButton j : buttons)
        {
            j.addActionListener(this);
            buttonPanel.add(j);
        }
        
        sliders=new JSlider[2];
        
        sliders[0]=new JSlider(0,100);//coeff
        sliders[1]=new JSlider(0,100);//freq
        
        sliders[0].addChangeListener(this);
        sliders[1].addChangeListener(this);
        
        sliderPanel.add(sliders[0]);
        sliderPanel.add(sliders[1]);
        
        this.add(buttonPanel);
        this.add(sliderPanel);
        
        
        redrawButtons();
    }
    
    public void setAll(double coeff, double freq, int selected)
    {
        this.coeff=coeff;
        this.freq=freq;
        this.selected=selected;
        updateValues(coeff, freq);
        redrawButtons();
    }
    
    public void setValues(double coeff, double freq)
    {
        this.coeff=coeff;
        this.freq=freq;
        updateValues(coeff, freq);
        redrawButtons();
    }
    
    public void setSelected(int selected)
    {
        this.selected=selected;
        redrawButtons();
    }
    
    public void updateValues()
    {
        coeff=sliders[0].getValue(); 
        freq=sliders[1].getValue();
        setupFormulas();
    }
    
    public void updateValues(double coeff, double freq)
    {
        sliders[0].setValue((int)coeff);
        sliders[1].setValue((int)freq);
        updateValues();
    }
    
    public void redrawButtons()
    {
        canvases = new ArrayList();
        for(int i=0; i<buttons.size(); i++)
        {
            if(i==selected)
            {
                canvases.add(new DynamicCanvas(x,y,R));
            }
            else
            {
                canvases.add(new DynamicCanvas(x,y,P));
            }
            AbstractGenerator[] gens = new AbstractGenerator[1];
            
            gens[0] = new Gen_Formula(Gen_Formula.constructParams(true, true, 1, GapPresets.gaps[0], formulas.get(i)));
            
            canvases.set(i, CanvasWriter.draw(canvases.get(i), gens, 0));
            
            
            buttons.get(i).setIcon(new ImageIcon(ImageManager.constructImage(canvases.get(i), x, y)));
        }
    }
    
    public Formula getFormula()
    {
        try
        {
            return formulas.get(selected);
        }
        catch(Exception e){}
        return null;
    }
    
    private Formula setFValues(Formula F, int i)
    {
        try
        {
            F.setCoeff(coeff);
            System.out.println("coeff: " + coeff);
            F.setFreq(freq);
            System.out.println("freq: " + freq);
            F.setCoCoeff(coCoeff);
            F.setFreqEff(freqEff);
            F.setSelected(i);
            return F;
        }
        catch(Exception e)
        {
            
            return null;
        }
    }
    
    private void setupFormulas()
    {
        formulas = new ArrayList();
        
        Formula F;
        int i=0;
        
        F = new Formula(Function.SIN);
        F = setFValues(F, i);
        i++;
        formulas.add(F);
        
        F = new Formula(Function.COS, F, Operation.ADD);
        F = setFValues(F, i);
        i++;
        formulas.add(F);
        
        F = new Formula(Function.LOG, F, Operation.MULTIPLY);
        F = setFValues(F, i);
        i++;
        formulas.add(F);
        
        F = new Formula(Function.TAN);
        F = setFValues(F, i);
        i++;
        formulas.add(F);
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object toCheck = e.getSource();
        try
        {
            for(int i=0; i<buttons.size(); i++)
            {
                if(buttons.get(i)==toCheck)
                {
                    selected=i;
                }
            }
        }
        catch(Exception ex){}
        redrawButtons();
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        updateValues();
        redrawButtons();
    }
}