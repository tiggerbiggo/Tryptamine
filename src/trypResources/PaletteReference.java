package trypResources;

public class PaletteReference 
{
    int paletteIndex, colorIndex;

    public PaletteReference(int index, int id) {
        this.colorIndex = index;
        this.paletteIndex = id;
    }

    public int getPaletteIndex() {
        return paletteIndex;
    }

    public int getColorIndex() {
        return colorIndex;
    }
    
    
}
