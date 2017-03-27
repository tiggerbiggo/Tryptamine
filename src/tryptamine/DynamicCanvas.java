package tryptamine;
import trypResources.Palette;
import trypResources.PaletteReference;
import java.awt.Color;

public class DynamicCanvas 
{
    int x, y, paletteNum;
    Palette[] Palettes;
    PaletteReference[][] PR;
    
    Color BG = Color.BLACK;

    /*public DynamicCanvas(int x, int y) throws IllegalArgumentException
    {
        if(checkValid(x) && checkValid(y))
        {
            this.x = x;
            this.y = y;
        }
        else throw new IllegalArgumentException("Cannot have negative x or y");
        
        paletteNum = 0;
        
        PR = new PaletteReference[x][y];
        
        initCanvas();
    }*/

    public DynamicCanvas(int x, int y, Palette[] Palettes) throws IllegalArgumentException
    {
        if(checkValid(x) && checkValid(y))
        {
            this.x = x;
            this.y = y;
        }
        else throw new IllegalArgumentException("Cannot have negative x or y");
        
        this.Palettes = Palettes;
        
        paletteNum=Palettes.length;
        
        PR = new PaletteReference[x][y];
        
        initCanvas();
    }
    
    public DynamicCanvas(int x, int y, Palette P) throws IllegalArgumentException
    {
        if(checkValid(x) && checkValid(y))
        {
            this.x = x;
            this.y = y;
        }
        else throw new IllegalArgumentException("Cannot have negative x or y");
        
        this.Palettes = new Palette[1];
        Palettes[0] = P;
        
        paletteNum=1;
        
        PR = new PaletteReference[x][y];
        
        initCanvas();
    }
    
    public void initCanvas()
    {
        for(int i=0; i<x; i++)
        {
            for(int j=0; j<y; j++)
            {
                PR[i][j] = new PaletteReference(-1, -1); //defines a grid of blank references
            }
        }
    }
    
    public void setDimensions(int x, int y)
    {
        if(checkValid(x) && checkValid(y))
        {
            this.x = x;
            this.y = y;
            PR = new PaletteReference[x][y];
            initCanvas();
        }
        //else wrong dimensions...
    }
    
    public Color getColor(int x, int y)
    {
        if(checkValid(x) && checkValid(y))
        {
            PaletteReference temp = PR[x][y];
            int PI=temp.getPaletteIndex();
            int CI=temp.getColorIndex();
            if(checkColorIndex(PI, CI))
            {
                return Palettes[PI].getColor(CI);
            }
        }
        return null;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void cycleAll(int cycles)
    {
        for(Palette P : Palettes)
        {
            P.cycle(cycles);
        }
    }
    
    public int normalize(int num, int index)
    {
        if(checkPaletteIndex(index))
        {
            return Palettes[index].normalize(num);
        }
        return -1;
    }
    
    public void draw(int x, int y, int paletteIndex, int colorIndex)
    {
        if(checkDimensions(x, y) && checkColorIndex(paletteIndex, colorIndex))
        {
            //System.out.println("VALID: " + x + ", " + y);
            PR[x][y]=new PaletteReference(colorIndex, paletteIndex);
        }
    }
    
    public Palette getPalette(int index)
    {
        if(checkPaletteIndex(index))
        {
            return Palettes[index];
        }
        else return null;
    }
    
    public Color getPixel(int x, int y)
    {
        if(checkDimensions(x, y))
        {
            if(PR[x][y].getPaletteIndex()>=0 || PR[x][y].getColorIndex()>=0)
            {
                if(checkPaletteIndex(PR[x][y].getPaletteIndex()))
                {
                    return Palettes[PR[x][y].getPaletteIndex()].getColor(PR[x][y].getColorIndex());
                }
            }
            
        }
        return BG;
    }
    
    private boolean checkValid(int c)
    {
        return c>=0;
    }
    
    public boolean checkDimensions(int x, int y)
    {
        return x>=0 && x<this.x && y>=0 && y<this.y;
    }
    
    private boolean checkPaletteIndex(int PI)
    {
        return PI>=0 && PI<paletteNum;
    }
    
    private boolean checkColorIndex(int PI, int CI)
    {
        if(checkPaletteIndex(PI))
        {
            return Palettes[PI].checkRange(CI);
        }
        return false;
    }
}