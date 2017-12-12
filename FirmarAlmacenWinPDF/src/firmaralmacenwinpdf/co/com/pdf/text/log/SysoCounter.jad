// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SysoCounter.java

package co.com.pdf.text.log;

import java.io.PrintStream;

// Referenced classes of package co.com.pdf.text.log:
//            Counter

public class SysoCounter
    implements Counter
{

    public SysoCounter()
    {
        name = "iText";
    }

    protected SysoCounter(Class klass)
    {
        name = klass.getName();
    }

    public Counter getCounter(Class klass)
    {
        return new SysoCounter(klass);
    }

    public void read(long l)
    {
        System.out.println(String.format("[%s] %s bytes read", new Object[] {
            name, Long.valueOf(l)
        }));
    }

    public void written(long l)
    {
        System.out.println(String.format("[%s] %s bytes written", new Object[] {
            name, Long.valueOf(l)
        }));
    }

    protected String name;
}
