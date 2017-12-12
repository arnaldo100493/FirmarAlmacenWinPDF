// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Flags.java

package co.org.bouncy.asn1.eac;


// Referenced classes of package co.org.bouncy.asn1.eac:
//            Flags

private class Flags$StringJoiner
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

    public Flags$StringJoiner(String separator)
    {
        this$0 = Flags.this;
        super();
        First = true;
        b = new StringBuffer();
        mSeparator = separator;
    }
}
