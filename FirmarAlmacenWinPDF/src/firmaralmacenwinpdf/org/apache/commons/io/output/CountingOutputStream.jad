// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CountingOutputStream.java

package org.apache.commons.io.output;

import java.io.OutputStream;

// Referenced classes of package org.apache.commons.io.output:
//            ProxyOutputStream

public class CountingOutputStream extends ProxyOutputStream
{

    public CountingOutputStream(OutputStream out)
    {
        super(out);
        count = 0L;
    }

    protected synchronized void beforeWrite(int n)
    {
        count += n;
    }

    public int getCount()
    {
        long result = getByteCount();
        if(result > 0x7fffffffL)
            throw new ArithmeticException((new StringBuilder()).append("The byte count ").append(result).append(" is too large to be converted to an int").toString());
        else
            return (int)result;
    }

    public int resetCount()
    {
        long result = resetByteCount();
        if(result > 0x7fffffffL)
            throw new ArithmeticException((new StringBuilder()).append("The byte count ").append(result).append(" is too large to be converted to an int").toString());
        else
            return (int)result;
    }

    public synchronized long getByteCount()
    {
        return count;
    }

    public synchronized long resetByteCount()
    {
        long tmp = count;
        count = 0L;
        return tmp;
    }

    private long count;
}
