
package trypGUI;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import trypResources.ActionCodes;

/**A viewing window allowing the user to see a preview of the current image
 *
 * @author tiggerbiggo
 */
public class Viewport
{
    JFrame JF_Viewport = new JFrame("Viewport");
    
    JButton JB_Render = new JButton("Render Image");
    JButton JB_Refresh = new JButton("Refresh");
    
    Image[] images;
    int currentImage = 0;
    
    /**Sets the images to be displayed by the viewport
     * 
     * @param images The image sequence to be displayed
     */
    public void setImages(BufferedImage[] images) {
        this.images = (Image[])images;
        currentImage = 0;
        drawImage();
    }

    /**Adds a single image to the array, will not add if the image is null
     * 
     * @param toAdd The image object to add
     */
    public void addImage(BufferedImage toAdd) 
    {

        if (images != null && toAdd != null) 
        {
            Image[] temp = images;
            images = new Image[images.length + 1];
            for (int i = 0; i <= temp.length; i++) 
            {
                images[i] = temp[i];
            }
            images[images.length] = (Image)toAdd;
            drawImage();
        }
    }

    
    /**
     * 
     * @return The current image being displayed to the user
     */
    public int getImageIndex()
    {
        return currentImage;
    }
    
    /**Sets the current image index, if it is outside the range, normalizes the input
     * 
     * see normalize()
     * @param index The index to set
     */
    public void setImageIndex(int index) 
    {
        currentImage = index;
        if (checkImageNumber()) 
        {
            //All is well!
        } 
        else if(imagesExist())
        {
            //Overflow
            currentImage = normalize(currentImage, images.length);
        }
        else 
        {
            //No images exist
        }
        
        System.out.println("Image Index: " + currentImage);
    }
    
    /**Basic validation method to check if images currently exist in memory
     * 
     * @return true if images exist, else false
     */
    public boolean imagesExist()
    {
        return images!=null;
    }
    
    /**Checks if an image exists at given index n
     * 
     * @param n The index of the image to check
     * @return True if the image exists at index n, false if n falls outside of range, no image exists at n or no images exist at all
     */
    public boolean imagesExist(int n)
    {
        if(imagesExist() && 
                n>=0 &&
                n<images.length)
            return images[n]!=null;
        return false;
    }

    /**Normalises the input toNormalize to within range 0 - normal
     * 
     * @param toNormalize the number to normalize
     * @param normal The maximum number
     * @return the normalized number
     */
    public int normalize(int toNormalize, int normal) 
    {
        while (toNormalize >= normal) 
        {
            toNormalize -= normal;
        }
        while (toNormalize < 0) 
        {
            toNormalize += normal;
        }
        return toNormalize;
    }

    /**Check to see if a graphics object exists for the JFrame
     * 
     * @return True if graphics exist, else false
     */
    public boolean checkGraphics()
    {
        return JF_Viewport.getGraphics() != null;
    }
    
    /**Draws the image from the images array based on the currentImage integer
     * 
     */
    public void drawImage() 
    {
        
        if (checkImageNumber() && checkGraphics()) 
        {
            JF_Viewport.getGraphics().drawImage(images[currentImage], 0, 0, null);
            JB_Render.repaint();
            JB_Refresh.repaint();
        }
    }

    /**Basic validation to check if the image number is in range
     * 
     * @return true if image is within range, false if it is not or if no images exist
     */
    public boolean checkImageNumber() 
    {
        if(imagesExist())
        {
            return currentImage < images.length && currentImage >= 0;
        }
        else return false;
    }
    
    /**Initialises the components
     * 
     * @param A The action listener of the parent method
     */
    public void initGUI(ActionListener A) 
    {
        JF_Viewport.setLayout(null);
        JF_Viewport.setBounds(10, 10, 500, 500);
        JF_Viewport.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JF_Viewport.setResizable(false);
        JF_Viewport.setLocationRelativeTo(null);
        JF_Viewport.setVisible(false);
        
        JB_Render.setBounds(0, 440, 250, 30);
        JB_Render.addActionListener(A);
        JF_Viewport.add(JB_Render);
        
        JB_Refresh.setBounds(250, 440, 250, 30);
        JB_Refresh.addActionListener(A);
        JF_Viewport.add(JB_Refresh);
    }
    
    /**
     * 
     * @return The width of the viewport
     */
    public int getWidth()
    {
        return JF_Viewport.getWidth();
    }
    
    /**
     * 
     * @return The height of the viewport
     */
    public int getHeight()
    {
        return JF_Viewport.getHeight();
    }
    
    /**Displays the viewport window
     * 
     */
    public void show()
    {
        JF_Viewport.setVisible(true);
    }
    
    /**
     * 
     * @see trypResources.ActionCodes
     * @param toCheck The object to check against elements in this class
     * @return An action code based on trypResources.ActionCodes
     */
    public int checkActions(Object toCheck)
    {
        if(toCheck == JB_Render) return ActionCodes.CODE_VIEWPORT_RENDER;
        else if(toCheck == JB_Refresh) return ActionCodes.CODE_VIEWPORT_REFRESH;
        else return ActionCodes.NULLCODE;
    }
}
