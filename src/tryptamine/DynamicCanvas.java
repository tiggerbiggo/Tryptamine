package tryptamine;
import trypResources.Palette;
import trypResources.PaletteReference;
import java.awt.Color;
import java.util.ArrayList;

public class DynamicCanvas 
{
    int x, y, paletteNum;
    ArrayList<Palette> Palettes;
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

    public DynamicCanvas(int x, int y, ArrayList<Palette> Palettes) throws IllegalArgumentException
    {
        if(checkValid(x) && checkValid(y))
        {
            this.x = x;
            this.y = y;
        }
        else throw new IllegalArgumentException("Cannot have negative x or y");
        
        this.Palettes = Palettes;
        
        paletteNum=Palettes.size();
        
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
        
        this.Palettes = new ArrayList();
        Palettes.add(P);
        
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
            try
            {
                return Palettes.get(PI).getColor(CI);
            }
            catch(Exception e){}
        }
        return null;
    }
    
    public PaletteReference getReference(int x, int y)
    {
        x=normalizeX(x);
        y=normalizeY(y);
        return PR[x][y];
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
    
    public int normalizePalette(int num, int index)
    {
        try
        {
            return Palettes.get(index).normalize(num);
        }
        catch(Exception e){return -1;}
    }
    
    public int normalizeX(int num)
    {
        while(num>=x)
        {
            num-=x;
        }
        while(num<0)
        {
            num+=x;
        }
        return num;
    }
    
    public int normalizeY(int num)
    {
        while(num>=y)
        {
            num-=y;
        }
        while(num<0)
        {
            num+=y;
        }
        return num;
    }
    
    public void draw(int x, int y, int paletteIndex, int colorIndex)
    {
        try
        {
            PR[x][y]=new PaletteReference(colorIndex, paletteIndex);
        }
        catch(Exception e)
        {
        }
    }
    
    public void setReference(int x, int y, PaletteReference ref)
    {
        x=normalizeX(x);
        y=normalizeY(y);
        this.PR[x][y]=ref;
    }
    
    public Palette getPalette(int index)
    {
        try
        {
            return Palettes.get(index);
        }
        catch(Exception e){return null;}
    }
    
    
    
    private boolean checkValid(int c)
    {
        return c>=0;
    }
    
    public boolean checkDimensions(int x, int y)
    {
        return x>=0 && x<this.x && y>=0 && y<this.y;
    }
    
    
}
