// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSAPublicBCPGKey.java

package co.org.bouncy.bcpg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.bcpg:
//            BCPGObject, MPInteger, BCPGOutputStream, BCPGKey, 
//            BCPGInputStream

public class DSAPublicBCPGKey extends BCPGObject
    implements BCPGKey
{

    public DSAPublicBCPGKey(BCPGInputStream in)
        throws IOException
    {
        p = new MPInteger(in);
        q = new MPInteger(in);
        g = new MPInteger(in);
        y = new MPInteger(in);
    }

    public DSAPublicBCPGKey(BigInteger p, BigInteger q, BigInteger g, BigInteger y)
    {
        this.p = new MPInteger(p);
        this.q = new MPInteger(q);
        this.g = new MPInteger(g);
        this.y = new MPInteger(y);
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
        out.writeObject(p);
        out.writeObject(q);
        out.writeObject(g);
        out.writeObject(y);
    }

    public BigInteger getG()
    {
        return g.getValue();
    }

    public BigInteger getP()
    {
        return p.getValue();
    }

    public BigInteger getQ()
    {
        return q.getValue();
    }

    public BigInteger getY()
    {
        return y.getValue();
    }

    MPInteger p;
    MPInteger q;
    MPInteger g;
    MPInteger y;
}
