// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CountingInputStream.java

package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package org.apache.commons.io.input:
//            ProxyInputStream

public class CountingInputStream extends ProxyInputStream
{

    public CountingInputStream(InputStream in)
    {
        super(in);
    }

    public synchronized long skip(long length)
        throws IOException
    {
        long skip = super.skip(length);
        count += skip;
        return skip;
    }

    protected synchronized void afterRead(int n)
    {
        if(n != -1)
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
