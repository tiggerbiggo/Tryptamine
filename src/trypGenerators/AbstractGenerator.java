package trypGenerators;

import tryptamine.DynamicCanvas;

/**<p>An abstract class to allow for iteration of generators in tryptamine.CanvasWriter.</p>
 *<p>In order to create a new generator, simply extend this class and implement the draw method. 
 * Draw desired pixels and return the DynamicCanvas object. You may also add parameters using the trypParams package. 
 * If you do so, for your generator to show up in the layer editor you must also create a new Layer Panel. 
 * This step can be skipped if you do not need parameters, however you must add an entry in trypParams.paramType 
 * for your generator to display in the list.</p>
 *
 * @see tryptamine.CanvasWriter
 * @see trypLayerPanels.AbstractLayerPanel
 * @see trypParams.Parameter
 * @see trypParams.paramType
 * @author tiggerbiggo
 */
public abstract class AbstractGenerator 
{
    
    /**The draw method is overridden in each subclass. The subclass can perform whatever action is 
     * needed on the DynamicCanvas object, allowing for new classes to be created for any effect. 
     * 
     * @param DC The DynamicCanvas to draw to
     * @param PaletteNum The number of the palette to use for drawing
     * @return The edited canvas to be passed to the next generator or rendered
     */
    public abstract DynamicCanvas draw(DynamicCanvas DC, int PaletteNum);
    
    /**A primitive line draw method which several of the default generators use
     * 
     * @param DC The DynamicCanvas to draw to
     * @param HV Horizontal if true, vertical if false
     * @param colorIncrement how much to increment the palette colour by each pixel
     * @param pos The position to start at
     * @param colorNum The color index to start at
     * @param PaletteNum The palette to use
     * @return The drawn canvas object
     */
    public DynamicCanvas lineDraw(DynamicCanvas DC, boolean HV, int colorIncrement, int pos, int colorNum, int PaletteNum)
    {
        int x=0, y=0;
        
        if(HV) y=pos;
        else x=pos;
        
        while(DC.checkDimensions(x,y))
        {
            colorNum=DC.getPalette(PaletteNum).normalize(colorNum);
            DC.draw(x, y, PaletteNum, colorNum);
            colorNum+=colorIncrement;
            if(HV) x++;
            else y++;
        }
        return DC;
    }
    
}
