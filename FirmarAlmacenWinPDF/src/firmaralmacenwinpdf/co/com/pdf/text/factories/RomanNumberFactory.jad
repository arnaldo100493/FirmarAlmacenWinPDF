// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RomanNumberFactory.java

package co.com.pdf.text.factories;


public class RomanNumberFactory
{
    private static class RomanDigit
    {

        public char digit;
        public int value;
        public boolean pre;

        RomanDigit(char digit, int value, boolean pre)
        {
            this.digit = digit;
            this.value = value;
            this.pre = pre;
        }
    }


    public RomanNumberFactory()
    {
    }

    public static final String getString(int index)
    {
        StringBuffer buf = new StringBuffer();
        if(index < 0)
        {
            buf.append('-');
            index = -index;
        }
        if(index > 3000)
        {
            buf.append('|');
            buf.append(getString(index / 1000));
            buf.append('|');
            index -= (index / 1000) * 1000;
        }
        int pos = 0;
        do
        {
            RomanDigit dig;
            for(dig = roman[pos]; index >= dig.value; index -= dig.value)
                buf.append(dig.digit);

            if(index > 0)
            {
                int j;
                for(j = pos; !roman[++j].pre;);
                if(index + roman[j].value >= dig.value)
                {
                    buf.append(roman[j].digit).append(dig.digit);
                    index -= dig.value - roman[j].value;
                }
                pos++;
            } else
            {
                return buf.toString();
            }
        } while(true);
    }

    public static final String getLowerCaseString(int index)
    {
        return getString(index);
    }

    public static final String getUpperCaseString(int index)
    {
        return getString(index).toUpperCase();
    }

    public static final String getString(int index, boolean lowercase)
    {
        if(lowercase)
            return getLowerCaseString(index);
        else
            return getUpperCaseString(index);
    }

    private static final RomanDigit roman[] = {
        new RomanDigit('m', 1000, false), new RomanDigit('d', 500, false), new RomanDigit('c', 100, true), new RomanDigit('l', 50, false), new RomanDigit('x', 10, true), new RomanDigit('v', 5, false), new RomanDigit('i', 1, true)
    };

}
