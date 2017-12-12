// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TBCPadding.java

package co.org.bouncy.crypto.paddings;

import co.org.bouncy.crypto.InvalidCipherTextException;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.paddings:
//            BlockCipherPadding

public class TBCPadding
    implements BlockCipherPadding
{

    public TBCPadding()
    {
    }

    public void init(SecureRandom securerandom)
        throws IllegalArgumentException
    {
    }

    public String getPaddingName()
    {
        return "TBC";
    }

    public int addPadding(byte in[], int inOff)
    {
        int count = in.length - inOff;
        byte code;
        if(inOff > 0)
            code = (byte)((in[inOff - 1] & 1) != 0 ? 0 : 255);
        else
            code = (byte)((in[in.length - 1] & 1) != 0 ? 0 : 255);
        for(; inOff < in.length; inOff++)
            in[inOff] = code;

        return count;
    }

    public int padCount(byte in[])
        throws InvalidCipherTextException
    {
        byte code = in[in.length - 1];
        int index;
        for(index = in.length - 1; index > 0 && in[index - 1] == code; index--);
        return in.length - index;
    }
}
