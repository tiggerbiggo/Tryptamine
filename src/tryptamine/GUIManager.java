package tryptamine;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
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
    
    ArrayList<Palette> palettes;
    int[] paletteNums;
    
    ArrayList<Layer> layers;
    
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
        
        palettes = new ArrayList();
        
        
        for(int i=0;i<3;i++)
        {
            Random r = new Random();
            Palette P = new Palette(PLength,0);
            P.setGradient(0, PLength-1, 
                    new Color(
                            r.nextInt(255),
                            r.nextInt(255),
                            r.nextInt(255)), 
                    new Color(
                            r.nextInt(255),
                            r.nextInt(255),
                            r.nextInt(255)));
            palettes.add(P);
        }
        
        DynamicCanvas DC = new DynamicCanvas(500, 500, palettes);
        
        paletteNums = new int[3];
        
        paletteNums[0]=0;
        paletteNums[1]=1;
        paletteNums[2]=2;
        
        C.show();
        S.show();
        
        PE.show();
        PE.addPaletteList(palettes);
        
        LE.show();
        
        VP.show();
        
        VP.setImages(drawPreview());
        
        do {
            
            updatePalettes();
            updateLayers();
            
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
                        DC = CanvasWriter.draw(DC, layers);
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
                    VP.setImages(drawPreview());
                    break;
                    
            }
            code = 0;
        } while (code >= 0);
    }
    
    public ArrayList<BufferedImage> drawPreview()
    {
        try
        {
            return ImageManager.constructSequence(
                    CanvasWriter.draw(
                            new DynamicCanvas(
                                    VP.getWidth(), 
                                    VP.getHeight(), 
                                    palettes), 
                            layers));
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        Object toCheck = e.getSource();

        
        
        
        
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
        layers = LE.getLayers();
        LE.setPalettes(palettes);
    }

    

}
