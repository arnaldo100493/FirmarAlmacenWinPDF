// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CounterFactory.java

package co.com.pdf.text.log;


// Referenced classes of package co.com.pdf.text.log:
//            NoOpCounter, Counter

public class CounterFactory
{

    private CounterFactory()
    {
        counter = new NoOpCounter();
    }

    public static CounterFactory getInstance()
    {
        return myself;
    }

    public static Counter getCounter(Class klass)
    {
        return myself.counter.getCounter(klass);
    }

    public Counter getCounter()
    {
        return counter;
    }

    public void setCounter(Counter counter)
    {
        this.counter = counter;
    }

    private static CounterFactory myself = new CounterFactory();
    private Counter counter;

}
