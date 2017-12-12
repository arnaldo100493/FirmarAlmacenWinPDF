// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ElGamalParameters.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.crypto.CipherParameters;
import java.math.BigInteger;

public class ElGamalParameters
    implements CipherParameters
{

    public ElGamalParameters(BigInteger p, BigInteger g)
    {
        this(p, g, 0);
    }

    public ElGamalParameters(BigInteger p, BigInteger g, int l)
    {
        this.g = g;
        this.p = p;
        this.l = l;
    }

    public BigInteger getP()
    {
        return p;
    }

    public BigInteger getG()
    {
        return g;
    }

    public int getL()
    {
        return l;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof ElGamalParameters))
        {
            return false;
        } else
        {
            ElGamalParameters pm = (ElGamalParameters)obj;
            return pm.getP().equals(p) && pm.getG().equals(g) && pm.getL() == l;
        }
    }

    public int hashCode()
    {
        return (getP().hashCode() ^ getG().hashCode()) + l;
    }

    private BigInteger g;
    private BigInteger p;
    private int l;
}
