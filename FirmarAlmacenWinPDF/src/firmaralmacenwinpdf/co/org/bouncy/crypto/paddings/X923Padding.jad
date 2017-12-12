// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X923Padding.java

package co.org.bouncy.crypto.paddings;

import co.org.bouncy.crypto.InvalidCipherTextException;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.paddings:
//            BlockCipherPadding

public class X923Padding
    implements BlockCipherPadding
{

    public X923Padding()
    {
        random = null;
    }

    public void init(SecureRandom random)
        throws IllegalArgumentException
    {
        this.random = random;
    }

    public String getPaddingName()
    {
        return "X9.23";
    }

    public int addPadding(byte in[], int inOff)
    {
        byte code = (byte)(in.length - inOff);
        for(; inOff < in.length - 1; inOff++)
            if(random == null)
                in[inOff] = 0;
            else
                in[inOff] = (byte)random.nextInt();

        in[inOff] = code;
        return code;
    }

    public int padCount(byte in[])
        throws InvalidCipherTextException
    {
        int count = in[in.length - 1] & 0xff;
        if(count > in.length)
            throw new InvalidCipherTextException("pad block corrupted");
        else
            return count;
    }

    SecureRandom random;
}
