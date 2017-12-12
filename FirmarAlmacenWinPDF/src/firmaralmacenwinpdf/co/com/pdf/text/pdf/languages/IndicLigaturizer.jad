// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IndicLigaturizer.java

package co.com.pdf.text.pdf.languages;


// Referenced classes of package co.com.pdf.text.pdf.languages:
//            LanguageProcessor

public abstract class IndicLigaturizer
    implements LanguageProcessor
{

    public IndicLigaturizer()
    {
    }

    public String process(String s)
    {
        if(s == null || s.length() == 0)
            return "";
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < s.length(); i++)
        {
            char letter = s.charAt(i);
            if(IsVyanjana(letter) || IsSwaraLetter(letter))
            {
                res.append(letter);
                continue;
            }
            if(IsSwaraMatra(letter))
            {
                int prevCharIndex = res.length() - 1;
                if(prevCharIndex >= 0)
                {
                    if(res.charAt(prevCharIndex) == langTable[10])
                        res.deleteCharAt(prevCharIndex);
                    res.append(letter);
                    int prevPrevCharIndex = res.length() - 2;
                    if(letter == langTable[1] && prevPrevCharIndex >= 0)
                        swap(res, prevPrevCharIndex, res.length() - 1);
                } else
                {
                    res.append(letter);
                }
            } else
            {
                res.append(letter);
            }
        }

        return res.toString();
    }

    public boolean isRTL()
    {
        return false;
    }

    protected boolean IsSwaraLetter(char ch)
    {
        return ch >= langTable[6] && ch <= langTable[7];
    }

    protected boolean IsSwaraMatra(char ch)
    {
        return ch >= langTable[0] && ch <= langTable[3] || ch == langTable[4] || ch == langTable[5];
    }

    protected boolean IsVyanjana(char ch)
    {
        return ch >= langTable[8] && ch <= langTable[9];
    }

    private static void swap(StringBuilder s, int i, int j)
    {
        char temp = s.charAt(i);
        s.setCharAt(i, s.charAt(j));
        s.setCharAt(j, temp);
    }

    public static final int MATRA_AA = 0;
    public static final int MATRA_I = 1;
    public static final int MATRA_E = 2;
    public static final int MATRA_AI = 3;
    public static final int MATRA_HLR = 4;
    public static final int MATRA_HLRR = 5;
    public static final int LETTER_A = 6;
    public static final int LETTER_AU = 7;
    public static final int LETTER_KA = 8;
    public static final int LETTER_HA = 9;
    public static final int HALANTA = 10;
    protected char langTable[];
}
