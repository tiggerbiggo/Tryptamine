package tryptamine;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.stream.*;
import javax.imageio.*;
import java.io.*;

/**This class manages the output of images and animated Gif files
 *
 * @author amnesia
 */
public class fileManager 
{

    /** Writes a single image as a PNG with a filename
     *
     * @param img The image to write
     * @param filename The name of the file
     */
    public static void write(BufferedImage img, String filename)
    {
        try
        {
            File out = new File(filename + ".png");
            ImageIO.write(img, "png", out);
        }
        catch(Exception e)
        {
            
        }
    }
    
    /**Writes a sequence of PNG images with an incremental filename
     *
     * @param sequence
     * @param filename
     */
    public static void writeSequence(BufferedImage[] sequence, String filename)
    {
        if(sequence != null)
        {
            for(int i=0; i<sequence.length; i++)
            {
                write(sequence[i], filename + i);
            }
        }
    }
    
    /**Writes a Gif
     *
     * @param DC The Dynamic Canvas to use as a template
     * @param filename The name of the Gif to output
     */
    public static void writeGif(DynamicCanvas DC, String filename)
    {
        writeGif(ImageManager.constructSequence(DC), filename);
    }
    
    /**Writes a Gif
     *
     * @param imgSequence A sequence of BufferedImage objects to output
     * @param filename The name of the Gif to output
     */
    public static void writeGif(BufferedImage[] imgSequence, String filename)
    {
        try
        {
            try (ImageOutputStream output = new FileImageOutputStream(new File(filename + ".gif"))) 
            {
                GifSequenceWriter writer = new GifSequenceWriter(output, BufferedImage.TYPE_INT_RGB, 0, true);
                for(BufferedImage B : imgSequence)
                {
                    writer.writeToSequence(B);
                }
                writer.close();
            }
        }
        catch(Exception e)
        {
            //oops
            System.out.println("OH NO");
            e.printStackTrace();
        }
    }
}
