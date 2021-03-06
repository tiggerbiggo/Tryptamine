package trypParams;

import trypResources.Formula;

/**
 *
 * @author timot_000
 */
public final class Parameter 
{
    
    String paramString;
    int paramInt=0;
    int[] paramIntArray;
    Formula paramFormula;
    boolean paramBoolean;
    
    paramType type = paramType.NULL;

    
    
    public Parameter(String paramString) 
    {
        setNull();
        
        this.paramString = paramString;
        type=paramType.STRING;
    }
    
    public Parameter(Formula paramFormula) 
    {
        setNull();
        
        this.paramFormula = paramFormula;
        type=paramType.FORMULA;
    }
    
    public Parameter(boolean paramBoolean) 
    {
        setNull();
        
        this.paramBoolean = paramBoolean;
        type=paramType.BOOLEAN;
    }
    
    public Parameter(int paramInt) 
    {
        setNull();
        
        this.paramInt = paramInt;
        type=paramType.INTEGER;
    }
    public Parameter(int[] paramIntArray) 
    {
        setNull();
        
        this.paramIntArray = paramIntArray;
        type=paramType.INTARRAY;
    }
    
    public paramType getType()
    {
        return type;
    }
    
    public String getString()
    {
        return paramString;
    }
    
    public Formula getFormula()
    {
        return paramFormula;
    }
    
    public boolean getBoolean()
    {
        return paramBoolean;
    }
    
    public int getInt()
    {
        return paramInt;
    }
    
    public int[] getIntArray()
    {
        return paramIntArray;
    }
    
    
    
    protected void setNull()
    {
        paramString = null;
        paramFormula = null;
        paramBoolean = false;
        paramInt = 0;
        
        type = paramType.NULL;
    }
    
    
}
