// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSASecretBCPGKey.java

package co.org.bouncy.bcpg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.bcpg:
//            BCPGObject, MPInteger, BCPGOutputStream, BCPGKey, 
//            BCPGInputStream

public class DSASecretBCPGKey extends BCPGObject
    implements BCPGKey
{

    public DSASecretBCPGKey(BCPGInputStream in)
        throws IOException
    {
        x = new MPInteger(in);
    }

    public DSASecretBCPGKey(BigInteger x)
    {
        this.x = new MPInteger(x);
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
        out.writeObject(x);
    }

    public BigInteger getX()
    {
        return x.getValue();
    }

    MPInteger x;
}
