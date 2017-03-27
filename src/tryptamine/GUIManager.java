package tryptamine;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import trypGUI.*;
import trypGenerators.Gen_Circular;
import trypGenerators.Gen_Formula;
import trypGenerators.Gen_SimpleLines;
import trypGenerators.Generator;
import trypParams.Parameter;
import trypResources.*;

/**
 *
 * @author amnesia
 */
public class GUIManager implements ActionListener {

    
    
    Viewport VP = new Viewport();
    Controls C = new Controls();
    Settings S = new Settings();
    PalEdit PE = new PalEdit();
    LayerEdit LE = new LayerEdit();
    
    Palette[] P;

    int code = 0;

    
    
    public void initGUI() 
    {
        PE.initGUI(this);
        VP.initGUI(this);
        C.initGUI(this);
        S.initGUI(this);
        LE.initGUI(this);
    }
    
    public synchronized void show() 
    {
        
        
        P = new Palette[2];
        P[0]=new Palette(100,0);
        
        P[0].setGradient(0, 99, Color.blue, Color.white);
        
        P[1] = new Palette(25, 0);
        P[1].setGradient(0, 24, Color.black, Color.green);
        
        DynamicCanvas DC = new DynamicCanvas(500, 500, P);
        
        int[][] gaps = {{2}, {1}};
        
        Formula F = new Formula(
                Function.COS, 
                new Formula(
                        Function.SIN), 
                Operation.MULTIPLY);
        
        F.setCoeff(10);
        F.setFreq(0.3);
        
        F.getNext().setCoeff(2);
        F.getNext().setFreq(0.01);
        
        Parameter[][] params = {
            Gen_Formula.constructParams(true, true, 1, gaps[0], F),
            Gen_Circular.constructParams(400, 250, 250)};
        
        Generator[] gens = {
            new Gen_Formula(params[0]),
            new Gen_Circular(params[1])};
        
        DC = CanvasWriter.draw(DC, gens);
        
        C.show();
        S.show();
        
        PE.show();
        PE.addPalette(P[0]);
        PE.addPalette(P[1]);
        
        LE.show();
        
        VP.show();
        
        VP.setImages(drawPreview(VP, P, gens));
        
        do {
            try 
            {
                wait();
            } 
            catch (Exception e) {}
            
            switch (code) {
                case ActionCodes.CODE_CONTROL_FORWARD:
                    VP.setImageIndex(VP.getImageIndex()+1);
                    VP.drawImage();
                    break;
                case ActionCodes.CODE_CONTROL_BACKWARD:
                    VP.setImageIndex(VP.getImageIndex()-1);
                    VP.drawImage();
                    break;
                case ActionCodes.CODE_VIEWPORT_RENDER:
                    if(S.checkFields())
                    {
                        DC.setDimensions(S.getResX(), S.getResY());
                        DC = CanvasWriter.draw(DC, gens);
                        if(S.getGif())
                        {
                            fileManager.writeGif(ImageManager.constructSequence(DC), "Gifs/" + S.getFilename());
                        }
                        else
                        {
                            fileManager.writeSequence(ImageManager.constructSequence(DC), S.getFilename());
                        }
                    }
                    break;
                case ActionCodes.CODE_VIEWPORT_REFRESH:
                    updatePalettes();
                    VP.setImages(drawPreview(VP, P, gens));
                    break;
                    
            }
            code = 0;
        } while (code >= 0);
    }
    
    public BufferedImage[] drawPreview(Viewport VP, Palette[] P, Generator[] gens)
    {
        return ImageManager.constructSequence(
                CanvasWriter.draw(
                        new DynamicCanvas(
                                VP.getWidth(), 
                                VP.getHeight(), 
                                P), 
                        gens));
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        Object toCheck = e.getSource();

        System.out.println("Action performed: " + toCheck);
        
        //System.out.println(toCheck.);
        
        
        
        
        code = C.checkActions(toCheck);
        if(code == ActionCodes.NULLCODE)
        {
            code = S.checkActions(toCheck);
        }
        if(code == ActionCodes.NULLCODE)
        {
            code = VP.checkActions(toCheck);
        }
        if(code == ActionCodes.NULLCODE)
        {
            code = PE.checkActions(toCheck);
        }
        
        
        
        notify();
    }

    private void updatePalettes() 
    {
        P=PE.getPalettes();
    }

    

}
