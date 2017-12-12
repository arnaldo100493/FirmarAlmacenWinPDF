// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GOST3410Parameters.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.crypto.CipherParameters;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.crypto.params:
//            GOST3410ValidationParameters

public class GOST3410Parameters
    implements CipherParameters
{

    public GOST3410Parameters(BigInteger p, BigInteger q, BigInteger a)
    {
        this.p = p;
        this.q = q;
        this.a = a;
    }

    public GOST3410Parameters(BigInteger p, BigInteger q, BigInteger a, GOST3410ValidationParameters params)
    {
        this.a = a;
        this.p = p;
        this.q = q;
        validation = params;
    }

    public BigInteger getP()
    {
        return p;
    }

    public BigInteger getQ()
    {
        return q;
    }

    public BigInteger getA()
    {
        return a;
    }

    public GOST3410ValidationParameters getValidationParameters()
    {
        return validation;
    }

    public int hashCode()
    {
        return p.hashCode() ^ q.hashCode() ^ a.hashCode();
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof GOST3410Parameters))
        {
            return false;
        } else
        {
            GOST3410Parameters pm = (GOST3410Parameters)obj;
            return pm.getP().equals(p) && pm.getQ().equals(q) && pm.getA().equals(a);
        }
    }

    private BigInteger p;
    private BigInteger q;
    private BigInteger a;
    private GOST3410ValidationParameters validation;
}
