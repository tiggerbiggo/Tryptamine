package trypResources;

public class Formula
{

    Function func;
    boolean fin;
    Formula next;
    Operation op;
    double coeff, freq, freqEff, coCoeff;
    int selected;

    public Formula(Function func)
    {
        this.func = func;
        fin = true;
        
        coeff=1;
        coCoeff=1;
        freq=1;
        freqEff=1;
        selected=-1;
    }

    public Formula(Function func, Formula next, Operation op)
    {
        this.func = func;
        this.next = next;
        this.op = op;
        fin = false;
        
        coeff=1;
        freq=1;
        freqEff=1;
        coCoeff=1;
        selected=-1;
    }

    public Formula getNext()
    {
        return next;
    }

    public void setCoeff(double coeff)
    {
        this.coeff = coeff;
    }

    public void setFreqEff(double freqEff)
    {
        this.freqEff = freqEff;
    }

    public void setFreq(double freq)
    {
        this.freq = freq;
    }
    
    public void setCoCoeff(double coCoeff)
    {
        this.coCoeff = coCoeff;
    }
    
    public void setSelected(int selected)
    {
        this.selected=selected;
    }

    public double getCoeff()
    {
        return coeff;
    }

    public double getFreqEff()
    {
        return freqEff;
    }
    
    public double getCoCoeff(double coCoeff)
    {
        return coCoeff;
    }

    public double getFreq()
    {
        return freq;
    }
    
    
    public int getSelected()
    {
        return selected;
    }

    public double recursiveCalc(double val)
    {
        if (fin)
        {
            return basicCalc(val);
        } else
        {
            switch (op)
            {
                case ADD:
                    //System.out.println("ADD");
                    return basicCalc(val) + next.recursiveCalc(val);
                case SUBTRACT:
                    //System.out.println("SUBTRACT");
                    return basicCalc(val) - next.recursiveCalc(val);
                case MULTIPLY:
                    //System.out.println("MULTIPLY");
                    return basicCalc(val) * next.recursiveCalc(val);
                case DIVIDE:
                    //System.out.println("DIVIDE");
                    return basicCalc(val) / next.recursiveCalc(val);
                case ENCLOSE:
                    //System.out.println("ENCLOSE");
                    return basicCalc(next.recursiveCalc(val));
                case POWER:
                    //System.out.println("EXPONENT");
                    return Math.pow(basicCalc(val), next.recursiveCalc(val));
                default:
                    //System.out.println("DEFAULT");
                    return basicCalc(val);
            }
        }
    }

    public double basicCalc(double val)
    {
        switch (func)
        {
            case SIN:
                return coeff * coCoeff * Math.sin(val * freq * freqEff);
            case COS:
                return coeff * coCoeff * Math.cos(val * freq * freqEff);
            case TAN:
                return coeff * coCoeff * Math.tan(val * freq * freqEff);
            case LOG:
                return coeff * coCoeff * Math.log(val * freq * freqEff);
            default:
                return 0;
        }
    }
}
