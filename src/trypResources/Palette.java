package trypResources;

import java.awt.Color;

public class Palette 
{
    String name = "New Palette";
    
    int num; 
    int index;
    Color[] colorArray;

    public Palette(int num, int id) 
    {
        
        this.num = num;
        this.index = id;
        
        name = "Palette " + id + " (" + num + ")";
        setAll(Color.BLACK);
    }
    
    
    public void setNum(int newNum)
    {
        if(newNum >=0)
        {
            Color[] tmp = new Color[newNum];
            int i=0;
            for(Color c : colorArray)
            {
                if(i<newNum)
                {
                    tmp[i] = c;
                    i++;
                }
                else break;
            }
            num=newNum;
            colorArray = tmp;
        }
        setName();
    }
    
    public void setName()
    {
        name = "Palette " + index + " (" + num + ")";
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getNum()
    {
        return num;
    }
    
    @Override
    public String toString()
    {
        return name;
    }
    
    public boolean setGradient(int start, int end, Color color1, Color color2)
    {
        if(checkRange(start) && checkRange(end) && end>=start)
        {
            //System.out.println("Start, End: "+start + ", " + end);
            int range;
            int cNum = end-start;
            
            int[] c1 = {color1.getRed(), color1.getGreen(), color1.getBlue()};
            int[] c2 = {color2.getRed(), color2.getGreen(), color2.getBlue()};
            int[] c3 = new int[3];
            
            double d = 0;
            
            if(end-start == 0)
            {
                colorArray[start] = color1;
            }
            
            for(int i=0; i<=end-start; i++)
            {
                
                for(int j=0; j<=2; j++)
                {
                    range=c2[j]-c1[j];
                    d=range/cNum;
                    //System.out.println("range: " + range + " cNum: "+cNum);
                    c3[j] = c1[j]+((int)Math.round(d)*i);
                }
                //System.out.println(i + " " + c3[0] + " " + c3[1] + " " + c3[2]);
                if(i==end-start)
                {
                    colorArray[i+start]=new Color(c2[0], c2[1],c2[2]);
                }
                else colorArray[i+start]=new Color(c3[0], c3[1],c3[2]);
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void InvertColors(int startIndex, int endIndex)
    {
        if(checkRange(startIndex) && checkRange(endIndex) && startIndex<=endIndex)
        {
            for(int i=startIndex; i<=endIndex; i++)
            {
                try
                {
                    colorArray[i] = ColorTools.invert(colorArray[i]);
                }
                catch(Exception e)
                {
                    return;
                }
            }
        }
    }
    
    public boolean setColor(int index, Color C)
    {
        if(checkRange(index))
        {
            colorArray[index] = C;
            return true;
        }
        return false;
    }
    
    public PaletteReference getReference(int index)
    {
        if(checkRange(index))
        {
            return new PaletteReference(index, this.index);
        }
        
        return null;
    }
    
    public void cycle(int count)
    {
        Color temp;
        int n=0;
        
        for(int i=0; i<num; i++)
        {
            temp=colorArray[normalize(i+count)];
            colorArray[normalize(i+count)]=colorArray[n];
            colorArray[n]=temp;
            n++;
            if(n>=count)
            {
                n=0;
            }
        }
    }
    
    public Color getColor(int index)
    {
        try
        {
            return colorArray[index];
        }
        catch(Exception e){return null;}
    }
    
    public int getIndex()
    {
        return index;
    }
    
    public Color[] getColorArray()
    {
        return colorArray;
    }
    
    private void setAll(Color c)
    {
        colorArray = new Color[num];
        for(int i= 0; i<num; i++)
        {
            colorArray[i] = c;
        }
    }
    
    public boolean checkRange(int n)
    {
        return n >=0 && n<num;
    }
    
    public int normalize(int n)
    {
        while(n>=num)
        {
            n-=num;
        }
        while(n<0)
        {
            n+=num;
        }
        return n;
    }
}
