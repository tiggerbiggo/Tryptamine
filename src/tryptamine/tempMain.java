package tryptamine;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import trypGenerators.*;
import trypFormula.Formula;
import java.util.regex.*;
import trypFormula.Function;
import trypFormula.Operation;
import trypResources.Palette;

/**
 *
 * @author tiggerbiggo
 */
public class tempMain 
{
    public static void main(String[] args)
    {
        /*GenFred GF = new GenFred();
        Palette[] P = {new Palette(10,0)};
        P[0].setGradient(0, 9, Color.green, Color.blue);
        //P[0].setGradient(16, 29, Color.white, Color.yellow);
        DynamicCanvas DC = new DynamicCanvas(50,50,P);
        
        DC=GF.draw(DC, 0);
        fileManager.writeGif(DC, "FredBoop");*/
        
        parseFormula("5S(0.12C(X))+30S(0.1C(X))/2.2C(X)");
        
        Formula F = new Formula(
                Function.SIN, 
                new Formula(
                        Function.COS, 
                        new Formula(
                                Function.SIN),
                        Operation.ADD), 
                Operation.ENCLOSE);
        
    }
    public static Formula parseFormula(String toParse)
    {
        Pattern catchAll= Pattern.compile("[\\+|\\-|\\/|\\*|X]|([\\d]+\\.\\d+|\\d+)|(S$|C$|T$|L$)");
        ArrayList<String> splitArray = new ArrayList<>(Arrays.asList(toParse.split("[\\(\\)]")));
        ArrayList<ArrayList<String>> finalResults = new ArrayList<>();
        
        for(String s : splitArray)
        {
            ArrayList<String> results = new ArrayList();
            try
            {
                Matcher m = catchAll.matcher(s); //Define new matcher
                String check = m.replaceAll(""); //Replace all, to check if the entire string matches
                m = catchAll.matcher(s);//Reset the matcher
                if(check.length()==0) //If the entire string matches
                {
                    for(int i=0;i<=2;i++)//iterate and add every element to the list
                    {
                        if(m.find())
                        {
                            System.out.println(m.group(0));
                            results.add(m.group(0));
                        }
                        else break;
                        
                    }
                    System.out.println("---");
                    finalResults.add(results);
                }
                else
                {
                    System.out.println("INVALID");
                    return null;
                }
                
            }
            catch(Exception e)
            {
                System.out.println("Exception");
                return null;
            }
            
        }
        
        //BEGIN CHECKS
        
        int nX = 0;
        int nOp = 0;
        
        for(ArrayList<String> AL : finalResults) //Perform check to see if n("X") == 1+n(operators)
        {
            if(!AL.isEmpty())//Avoid exception
            {
                String s = AL.get(0);
                if(s.equals("X"))
                {
                    nX++;
                }
                else if(s.equals("+") || 
                        s.equals("-") || 
                        s.equals("*") || 
                        s.equals("/"))
                {
                    nOp++;
                }
            }
        }
        
        System.out.println("nX = " 
                            + nX 
                            + ", nOp = " 
                            + nOp);
        
        if(nX == 1+nOp)
        {
            System.out.println("Valid");
        }
        else return null;
        
        return null;
    }
    
    public static String[] appendString(String toAppend, String[] a)
    {
        String[] b = new String[a.length+1];
        System.arraycopy(a, 0, b, 0, a.length);
        b[a.length]=toAppend;
        return b;
    }
}
