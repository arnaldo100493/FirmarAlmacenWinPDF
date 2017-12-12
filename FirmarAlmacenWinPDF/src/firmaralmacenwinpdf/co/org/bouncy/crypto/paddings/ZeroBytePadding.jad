// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ZeroBytePadding.java

package co.org.bouncy.crypto.paddings;

import co.org.bouncy.crypto.InvalidCipherTextException;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.paddings:
//            BlockCipherPadding

public class ZeroBytePadding
    implements BlockCipherPadding
{

    public ZeroBytePadding()
    {
    }

    public void init(SecureRandom securerandom)
        throws IllegalArgumentException
    {
    }

    public String getPaddingName()
    {
        return "ZeroByte";
    }

    public int addPadding(byte in[], int inOff)
    {
        int added = in.length - inOff;
        for(; inOff < in.length; inOff++)
            in[inOff] = 0;

        return added;
    }

    public int padCount(byte in[])
        throws InvalidCipherTextException
    {
        int count;
        for(count = in.length; count > 0 && in[count - 1] == 0; count--);
        return in.length - count;
    }
}
