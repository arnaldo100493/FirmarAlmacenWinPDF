// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ISO7816d4Padding.java

package co.org.bouncy.crypto.paddings;

import co.org.bouncy.crypto.InvalidCipherTextException;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.paddings:
//            BlockCipherPadding

public class ISO7816d4Padding
    implements BlockCipherPadding
{

    public ISO7816d4Padding()
    {
    }

    public void init(SecureRandom securerandom)
        throws IllegalArgumentException
    {
    }

    public String getPaddingName()
    {
        return "ISO7816-4";
    }

    public int addPadding(byte in[], int inOff)
    {
        int added = in.length - inOff;
        in[inOff] = -128;
        for(inOff++; inOff < in.length; inOff++)
            in[inOff] = 0;

        return added;
    }

    public int padCount(byte in[])
        throws InvalidCipherTextException
    {
        int count;
        for(count = in.length - 1; count > 0 && in[count] == 0; count--);
        if(in[count] != -128)
            throw new InvalidCipherTextException("pad block corrupted");
        else
            return in.length - count;
    }
}
