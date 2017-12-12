// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSASecretBCPGKey.java

package co.org.bouncy.bcpg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.bcpg:
//            BCPGObject, MPInteger, BCPGOutputStream, BCPGKey, 
//            BCPGInputStream

public class RSASecretBCPGKey extends BCPGObject
    implements BCPGKey
{

    public RSASecretBCPGKey(BCPGInputStream in)
        throws IOException
    {
        d = new MPInteger(in);
        p = new MPInteger(in);
        q = new MPInteger(in);
        u = new MPInteger(in);
        expP = d.getValue().remainder(p.getValue().subtract(BigInteger.valueOf(1L)));
        expQ = d.getValue().remainder(q.getValue().subtract(BigInteger.valueOf(1L)));
        crt = q.getValue().modInverse(p.getValue());
    }

    public RSASecretBCPGKey(BigInteger d, BigInteger p, BigInteger q)
    {
        int cmp = p.compareTo(q);
        if(cmp >= 0)
        {
            if(cmp == 0)
                throw new IllegalArgumentException("p and q cannot be equal");
            BigInteger tmp = p;
            p = q;
            q = tmp;
        }
        this.d = new MPInteger(d);
        this.p = new MPInteger(p);
        this.q = new MPInteger(q);
        u = new MPInteger(p.modInverse(q));
        expP = d.remainder(p.subtract(BigInteger.valueOf(1L)));
        expQ = d.remainder(q.subtract(BigInteger.valueOf(1L)));
        crt = q.modInverse(p);
    }

    public BigInteger getModulus()
    {
        return p.getValue().multiply(q.getValue());
    }

    public BigInteger getPrivateExponent()
    {
        return d.getValue();
    }

    public BigInteger getPrimeP()
    {
        return p.getValue();
    }

    public BigInteger getPrimeQ()
    {
        return q.getValue();
    }

    public BigInteger getPrimeExponentP()
    {
        return expP;
    }

    public BigInteger getPrimeExponentQ()
    {
        return expQ;
    }

    public BigInteger getCrtCoefficient()
    {
        return crt;
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
        out.writeObject(d);
        out.writeObject(p);
        out.writeObject(q);
        out.writeObject(u);
    }

    MPInteger d;
    MPInteger p;
    MPInteger q;
    MPInteger u;
    BigInteger expP;
    BigInteger expQ;
    BigInteger crt;
}
