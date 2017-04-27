/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tryptamine;

/**
 *
 * @author amnesia
 */
public class GapPresets 
{
    public static int[][] gaps =
    {
        {0},
        {1},
        {1,0}
    };
    
    public static String[] GAPNAMES = 
    {
        "No Gaps",
        "Alternate 1",
        "Alternate 2",
    };
    
    public static int parseArray(int[] index)
    {
        for(int i=0;i<gaps.length;i++)
        {
            if(index==gaps[i])
            {
                return i;
            }
        }
        return -1;
    }
}
