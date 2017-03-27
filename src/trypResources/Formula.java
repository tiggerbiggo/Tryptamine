package trypResources;

public class Formula 
{
	
	Function func;
	boolean fin;
	Formula next;
	Operation op;
	double coeff = 1;
	double freq = 1;
	
	
	
    public Formula(Function func) 
	{
		this.func = func;
		fin=true;
    }
	
	public Formula(Function func, Formula next, Operation op)
	{
		this.func=func;
		this.next=next;
		this.op=op;
		fin=false;
	}
	
	public Formula getNext()
	{
		return next;
	}
	
	public void setCoeff(double coeff)
	{
		this.coeff=coeff;
                if(next !=  null)
                {
                    next.setCoeff(coeff);
                }
	}
	
	public void setFreq(double freq)
	{
		this.freq=freq;
                if(next !=  null)
                {
                    next.setFreq(freq);
                }
	}
	
	public double recursiveCalc(double val)
	{
		if(fin)
		{
			return basicCalc(val);
		}
		else
		{
			switch(op)
			{
				case ADD:
                                    //System.out.println("ADD");
                                    return basicCalc(val)+next.recursiveCalc(val);
				case SUBTRACT:
                                    //System.out.println("SUBTRACT");
                                    return basicCalc(val)-next.recursiveCalc(val);
				case MULTIPLY:
                                    //System.out.println("MULTIPLY");
                                    return basicCalc(val)*next.recursiveCalc(val);
				case DIVIDE:
                                    //System.out.println("DIVIDE");
                                    return basicCalc(val)/next.recursiveCalc(val);
				case ENCLOSE:
                                    //System.out.println("ENCLOSE");
                                    return basicCalc(next.recursiveCalc(val));
                                case EXPONENT:
                                    //System.out.println("EXPONENT");
                                    return Math.pow(basicCalc(val),next.recursiveCalc(val));
				default:
                                    //System.out.println("DEFAULT");
                                    return basicCalc(val);
			}
		}
	}
	
	public double basicCalc(double val)
	{
		switch(func)
		{
			case SIN:
				return coeff*Math.sin(val*freq);
			case COS:
				return coeff*Math.cos(val*freq);
			case TAN:
				return coeff*Math.tan(val*freq);
			case LOG:
				return coeff*Math.log(val*freq);
			default:
				return 0;
		}
	}
	
	
}
