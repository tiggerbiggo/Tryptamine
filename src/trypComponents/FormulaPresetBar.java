/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trypComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import trypFormula.*;
import trypGenerators.AbstractGenerator;
import trypGenerators.Gen_Formula;
import trypResources.Palette;
import tryptamine.CanvasWriter;
import tryptamine.DynamicCanvas;
import tryptamine.GapPresets;
import tryptamine.ImageManager;

/**
 *
 * @author tiggerbiggo
 */
public class FormulaPresetBar extends JPanel implements ActionListener
{
    ArrayList<JButton> buttons;
    ArrayList<Formula> formulas;
    ArrayList<Graphics> graphicsObjects;
    ArrayList<DynamicCanvas> canvases;
    Palette[] P;
    
    JPanel buttonPanel;
    
    int x, y;
    
    public FormulaPresetBar()
    {
        buttonPanel = new JPanel();
        
        buttons = new ArrayList();
        graphicsObjects = new ArrayList();
        
        x=60;
        y=40;
        
        setupFormulas(10, 0.1);
        
        buttonPanel.setLayout(new GridLayout(1,0));
        this.setLayout(new GridLayout(2,1));
        P = new Palette[1];
        
        P[0] = new Palette(10, 0);
        P[0].setGradient(0, 9, Color.black, Color.white);
        
        for(Formula f : formulas)
        {
            buttons.add(new JButton());
        }
        for(JButton j : buttons)
        {
            j.addActionListener(this);
            buttonPanel.add(j);
        }
        
        this.add(buttonPanel);
        
        redrawButtons();
    }
    
    public void redrawButtons()
    {
        canvases = new ArrayList();
        for(int i=0; i<buttons.size(); i++)
        {
            canvases.add(new DynamicCanvas(x,y,P));
            AbstractGenerator[] gens = new AbstractGenerator[1];
            
            gens[0] = new Gen_Formula(Gen_Formula.constructParams(true, true, 1, GapPresets.gaps[0], formulas.get(i)));
            
            canvases.set(i, CanvasWriter.draw(canvases.get(i), gens, 0));
            
            System.out.println();
            
            buttons.get(i).setIcon(new ImageIcon(ImageManager.constructImage(canvases.get(i), x, y)));
        }
    }
    
    private void setupFormulas(double coeff, double freq)
    {
        formulas = new ArrayList();
        
        Formula F;
        
        F = new Formula(Function.SIN);
        F.setCoeff(coeff);
        F.setFreq(freq);
        
        formulas.add(F);
        
        F = new Formula(Function.COS, F, Operation.ADD);
        F.setCoeff(coeff);
        F.setFreq(freq*0.321);
        
        formulas.add(F);
        
        F = new Formula(Function.LOG, F, Operation.ENCLOSE);
        F.setCoeff(coeff);
        F.setFreq(freq);
        
        formulas.add(F);
        
        F = new Formula(Function.TAN);
        F.setCoeff(coeff);
        F.setFreq(freq);
        
        formulas.add(F);
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        redrawButtons();
    }
}
