package tryptamine;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import trypGUI.*;
import trypGenerators.*;
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
    
    Palette[] palettes;
    int[] paletteNums;
    
    Layer[] layers;
    
    AbstractGenerator[] gens;

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
        
        int PLength = 40;
        
        palettes = new Palette[3];
        palettes[0]=new Palette(PLength,0);
        
        palettes[0].setGradient(0, PLength-1, Color.blue, Color.white);
        
        palettes[1] = new Palette(PLength, 0);
        palettes[1].setGradient(0, PLength-1, Color.black, Color.green);
        
        palettes[2] = new Palette(PLength, 0);
        palettes[2].setGradient(0, PLength-1, Color.red, Color.blue);
        
        DynamicCanvas DC = new DynamicCanvas(500, 500, palettes);
        
        Formula F = new Formula(
                Function.COS, 
                new Formula(
                        Function.SIN), 
                Operation.ADD);
        
        F.setCoeff(10);
        F.setFreq(0.02);
        
        F.getNext().setCoeff(8);
        F.getNext().setFreq(0.1);
        
        //Parameter[][] params = {
        //    Gen_Formula.constructParams(true, true, 1, gaps[0], F),
        //    Gen_Formula.constructParams(true, false, 1, gaps[1], F),
        //    Gen_Circular.constructParams(100, 250, 250)};
        
        //Generator[] gens = {
        //    new Gen_Formula(params[0]),
        //    new Gen_Formula(params[1]),
        //    new Gen_Circular(params[2])};
        
        Parameter[][] params = {
            Gen_Formula.constructParams(true, false, 1, GapPresets.gaps[0], F),
            Gen_Formula.constructParams(true, true, 1, GapPresets.gaps[1], F),
            Gen_DistortFormula.constructParams(false, false, GapPresets.gaps[0], F)};
        
        
        paletteNums = new int[3];
        
        paletteNums[0]=0;
        paletteNums[1]=1;
        paletteNums[2]=2;
        
        DC = CanvasWriter.draw(DC, gens, paletteNums);
        
        C.show();
        S.show();
        
        PE.show();
        PE.addPalette(palettes[0]);
        PE.addPalette(palettes[1]);
        
        LE.show();
        
        VP.show();
        
        VP.setImages(drawPreview(VP, palettes, gens));
        
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
                        DC = CanvasWriter.draw(DC, gens, paletteNums);
                        if(S.getGif())
                        {
                            fileManager.writeGif(ImageManager.constructSequence(DC), "Gifs/" + S.getFilename());
                        }
                        else
                        {
                            fileManager.writeSequence(ImageManager.constructSequence(DC), "Png/"+S.getFilename());
                        }
                    }
                    break;
                case ActionCodes.CODE_VIEWPORT_REFRESH:
                    updatePalettes();
                    updateLayers();
                    VP.setImages(drawPreview(VP, palettes, gens));
                    break;
                    
            }
            code = 0;
        } while (code >= 0);
    }
    
    public BufferedImage[] drawPreview(Viewport VP, Palette[] P, AbstractGenerator[] gens)
    {
        return ImageManager.constructSequence(CanvasWriter.draw(new DynamicCanvas(
                                VP.getWidth(), 
                                VP.getHeight(), 
                                P), 
                        gens, paletteNums));
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
        palettes=PE.getPalettes();
    }

    private void updateLayers() 
    {
        gens = LE.getGens();
    }

    

}
