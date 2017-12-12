// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MPInteger.java

package co.org.bouncy.bcpg;

import java.io.IOException;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.bcpg:
//            BCPGObject, BCPGInputStream, BCPGOutputStream

public class MPInteger extends BCPGObject
{

    public MPInteger(BCPGInputStream in)
        throws IOException
    {
        value = null;
        int length = in.read() << 8 | in.read();
        byte bytes[] = new byte[(length + 7) / 8];
        in.readFully(bytes);
        value = new BigInteger(1, bytes);
    }

    public MPInteger(BigInteger value)
    {
        this.value = null;
        if(value == null || value.signum() < 0)
        {
            throw new IllegalArgumentException("value must not be null, or negative");
        } else
        {
            this.value = value;
            return;
        }
    }

    public BigInteger getValue()
    {
        return value;
    }

    public void encode(BCPGOutputStream out)
        throws IOException
    {
        int length = value.bitLength();
        out.write(length >> 8);
        out.write(length);
        byte bytes[] = value.toByteArray();
        if(bytes[0] == 0)
            out.write(bytes, 1, bytes.length - 1);
        else
            out.write(bytes, 0, bytes.length);
    }

    BigInteger value;
}
