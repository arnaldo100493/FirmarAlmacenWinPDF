// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SequenceList.java

package co.com.pdf.text.pdf;

import java.util.*;

public class SequenceList
{

    protected SequenceList(String range)
    {
        ptr = 0;
        text = range.toCharArray();
    }

    protected char nextChar()
    {
        char c;
        do
        {
            if(ptr >= text.length)
                return '\uFFFF';
            c = text[ptr++];
        } while(c <= ' ');
        return c;
    }

    protected void putBack()
    {
        ptr--;
        if(ptr < 0)
            ptr = 0;
    }

    protected int getType()
    {
        StringBuffer buf = new StringBuffer();
        int state = 0;
        do
        {
            char c = nextChar();
            if(c == '\uFFFF')
            {
                if(state == 1)
                {
                    number = Integer.parseInt(other = buf.toString());
                    return 5;
                }
                if(state == 2)
                {
                    other = buf.toString().toLowerCase();
                    return 4;
                } else
                {
                    return 6;
                }
            }
            switch(state)
            {
            case 0: // '\0'
                switch(c)
                {
                case 33: // '!'
                    return 3;

                case 45: // '-'
                    return 2;

                case 44: // ','
                    return 1;
                }
                buf.append(c);
                if(c >= '0' && c <= '9')
                    state = 1;
                else
                    state = 2;
                break;

            case 1: // '\001'
                if(c >= '0' && c <= '9')
                {
                    buf.append(c);
                } else
                {
                    putBack();
                    number = Integer.parseInt(other = buf.toString());
                    return 5;
                }
                break;

            case 2: // '\002'
                if("-,!0123456789".indexOf(c) < 0)
                {
                    buf.append(c);
                } else
                {
                    putBack();
                    other = buf.toString().toLowerCase();
                    return 4;
                }
                break;
            }
        } while(true);
    }

    private void otherProc()
    {
        if(other.equals("odd") || other.equals("o"))
        {
            odd = true;
            even = false;
        } else
        if(other.equals("even") || other.equals("e"))
        {
            odd = false;
            even = true;
        }
    }

    protected boolean getAttributes()
    {
        low = -1;
        high = -1;
        odd = even = inverse = false;
        int state = 2;
        do
        {
            int type = getType();
            if(type == 6 || type == 1)
            {
                if(state == 1)
                    high = low;
                return type == 6;
            }
            switch(state)
            {
            case 2: // '\002'
                switch(type)
                {
                case 3: // '\003'
                    inverse = true;
                    break;

                case 2: // '\002'
                    state = 3;
                    break;

                default:
                    if(type == 5)
                    {
                        low = number;
                        state = 1;
                    } else
                    {
                        otherProc();
                    }
                    break;
                }
                break;

            case 1: // '\001'
                switch(type)
                {
                case 3: // '\003'
                    inverse = true;
                    state = 2;
                    high = low;
                    break;

                case 2: // '\002'
                    state = 3;
                    break;

                default:
                    high = low;
                    state = 2;
                    otherProc();
                    break;
                }
                break;

            case 3: // '\003'
                switch(type)
                {
                case 3: // '\003'
                    inverse = true;
                    state = 2;
                    break;

                case 5: // '\005'
                    high = number;
                    state = 2;
                    break;

                case 4: // '\004'
                default:
                    state = 2;
                    otherProc();
                    break;

                case 2: // '\002'
                    break;
                }
                break;
            }
        } while(true);
    }

    public static List expand(String ranges, int maxNumber)
    {
        SequenceList parse = new SequenceList(ranges);
        LinkedList list = new LinkedList();
        boolean sair = false;
label0:
        do
        {
            if(sair)
                break;
            sair = parse.getAttributes();
            if(parse.low == -1 && parse.high == -1 && !parse.even && !parse.odd)
                continue;
            if(parse.low < 1)
                parse.low = 1;
            if(parse.high < 1 || parse.high > maxNumber)
                parse.high = maxNumber;
            if(parse.low > maxNumber)
                parse.low = maxNumber;
            int inc = 1;
            if(parse.inverse)
            {
                if(parse.low > parse.high)
                {
                    int t = parse.low;
                    parse.low = parse.high;
                    parse.high = t;
                }
                ListIterator it = list.listIterator();
                do
                {
                    int n;
                    do
                    {
                        if(!it.hasNext())
                            continue label0;
                        n = ((Integer)it.next()).intValue();
                    } while(parse.even && (n & 1) == 1 || parse.odd && (n & 1) == 0 || (n < parse.low || n > parse.high));
                    it.remove();
                } while(true);
            }
            if(parse.low > parse.high)
            {
                inc = -1;
                if(parse.odd || parse.even)
                {
                    inc--;
                    if(parse.even)
                        parse.low &= -2;
                    else
                        parse.low -= (parse.low & 1) != 1 ? 1 : 0;
                }
                int k = parse.low;
                while(k >= parse.high) 
                {
                    list.add(Integer.valueOf(k));
                    k += inc;
                }
            } else
            {
                if(parse.odd || parse.even)
                {
                    inc++;
                    if(parse.odd)
                        parse.low |= 1;
                    else
                        parse.low += (parse.low & 1) != 1 ? 0 : 1;
                }
                int k = parse.low;
                while(k <= parse.high) 
                {
                    list.add(Integer.valueOf(k));
                    k += inc;
                }
            }
        } while(true);
        return list;
    }

    protected static final int COMMA = 1;
    protected static final int MINUS = 2;
    protected static final int NOT = 3;
    protected static final int TEXT = 4;
    protected static final int NUMBER = 5;
    protected static final int END = 6;
    protected static final char EOT = 65535;
    private static final int FIRST = 0;
    private static final int DIGIT = 1;
    private static final int OTHER = 2;
    private static final int DIGIT2 = 3;
    private static final String NOT_OTHER = "-,!0123456789";
    protected char text[];
    protected int ptr;
    protected int number;
    protected String other;
    protected int low;
    protected int high;
    protected boolean odd;
    protected boolean even;
    protected boolean inverse;
}
