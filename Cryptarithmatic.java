package Cryptarithmatic;
import java.util.*;
public class Cryptarithmatic {
    public static int getNumber(String s,HashMap<Character,Integer>hm)
    {
        String num="";
        for(int i=0;i<s.length();i++)
        {
            num+=hm.get(s.charAt(i));
        }
        return Integer.parseInt(num);
    }
    public static void solve(int i,String unique,HashMap<Character,Integer>hm,boolean[]used,String[]words,String result)
    {
        if(i==unique.length())
        {
            int sum=0;
            for(String s:words)
            {
                sum+=getNumber(s,hm);
            }
            int r=getNumber(result,hm);
            if(sum==r)
            {
                for(int k=0;k<=25;k++)
                {
                    char ch=(char)('A'+k);
                    if(hm.containsKey(ch))
                    {
                        System.out.print(ch+" -> "+hm.get(ch)+" ");
                    }
                }
                System.out.println();
            }
            return;

        }
        char ch=unique.charAt(i);
        for(int j=0;j<=9;j++)
        {
            if(!used[j])
            {

                hm.put(ch,j);
                used[j]=true;
                solve(i+1,unique,hm,used,words,result);

                hm.put(ch,-1);
                used[j]=false;

            }
        }

    }
    public static void main(String[]args)
    {
        String[]words={"SEND","MORE"};
        String result="MONEY";
        HashMap<Character,Integer>hm=new HashMap<>();
        String unique="";

        for(String s:words)
        {
            for(int i=0;i<s.length();i++)
            {
                char ch=s.charAt(i);
                if(!hm.containsKey(ch))
                {
                    hm.put(ch,-1);
                    unique+=ch;
                }
            }
        }
        for(int i=0;i<result.length();i++)
        {
            char ch=result.charAt(i);
            if(!hm.containsKey(ch))
            {
                hm.put(ch,-1);
                unique+=ch;
            }
        }
        boolean []used=new boolean[10];
          solve(0,unique,hm,used,words,result);
    }


}