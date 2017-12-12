// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSAPublicBCPGKey.java

package co.org.bouncy.bcpg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.bcpg:
//            BCPGObject, MPInteger, BCPGOutputStream, BCPGKey, 
//            BCPGInputStream

public class RSAPublicBCPGKey extends BCPGObject
    implements BCPGKey
{

    public RSAPublicBCPGKey(BCPGInputStream in)
        throws IOException
    {
        n = new MPInteger(in);
        e = new MPInteger(in);
    }

    public RSAPublicBCPGKey(BigInteger n, BigInteger e)
    {
        this.n = new MPInteger(n);
        this.e = new MPInteger(e);
    }

    public BigInteger getPublicExponent()
    {
        return e.getValue();
    }

    public BigInteger getModulus()
    {
        return n.getValue();
    }

    public String getFormat()
    {
        return "PGP";
    }

    public byte[] getEncoded()
    {
        try
        {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            BCPGOutputStream pgpOut = new BCPGOutputStream(bOut);
            pgpOut.writeObject(this);
            return bOut.toByteArray();
        }
        catch(IOException e)
        {
            return null;
        }
    }

    public void encode(BCPGOutputStream out)
        throws IOException
    {
        out.writeObject(n);
        out.writeObject(e);
    }

    MPInteger n;
    MPInteger e;
}
