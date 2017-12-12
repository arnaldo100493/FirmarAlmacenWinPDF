// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Flags.java

package co.org.bouncy.asn1.eac;

import java.util.Enumeration;
import java.util.Hashtable;

public class Flags
{
    private class StringJoiner
    {

        public void add(String str)
        {
            if(First)
                First = false;
            else
                b.append(mSeparator);
            b.append(str);
        }

        public String toString()
        {
            return b.toString();
        }

        String mSeparator;
        boolean First;
        StringBuffer b;
        final Flags this$0;

        public StringJoiner(String separator)
        {
            this$0 = Flags.this;
            super();
            First = true;
            b = new StringBuffer();
            mSeparator = separator;
        }
    }


    public Flags()
    {
        value = 0;
    }

    public Flags(int v)
    {
        value = 0;
        value = v;
    }

    public void set(int flag)
    {
        value |= flag;
    }

    public boolean isSet(int flag)
    {
        return (value & flag) != 0;
    }

    public int getFlags()
    {
        return value;
    }

    String decode(Hashtable decodeMap)
    {
        StringJoiner joiner = new StringJoiner(" ");
        Enumeration e = decodeMap.keys();
        do
        {
            if(!e.hasMoreElements())
                break;
            Integer i = (Integer)e.nextElement();
            if(isSet(i.intValue()))
                joiner.add((String)decodeMap.get(i));
        } while(true);
        return joiner.toString();
    }

    int value;
}
