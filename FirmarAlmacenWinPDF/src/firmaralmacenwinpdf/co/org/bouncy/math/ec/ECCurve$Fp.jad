// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECCurve.java

package co.org.bouncy.math.ec;

import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.math.ec:
//            ECCurve, ECFieldElement, ECPoint

public static class ECCurve$Fp extends ECCurve
{

    public BigInteger getQ()
    {
        return q;
    }

    public int getFieldSize()
    {
        return q.bitLength();
    }

    public ECFieldElement fromBigInteger(BigInteger x)
    {
        return new ent.Fp(q, x);
    }

    public ECPoint createPoint(BigInteger x, BigInteger y, boolean withCompression)
    {
        return new ECPoint$Fp(this, fromBigInteger(x), fromBigInteger(y), withCompression);
    }

    protected ECPoint decompressPoint(int yTilde, BigInteger X1)
    {
        ECFieldElement x = fromBigInteger(X1);
        ECFieldElement alpha = x.multiply(x.square().add(a)).add(b);
        ECFieldElement beta = alpha.sqrt();
        if(beta == null)
            throw new RuntimeException("Invalid point compression");
        BigInteger betaValue = beta.toBigInteger();
        int bit0 = betaValue.testBit(0) ? 1 : 0;
        if(bit0 != yTilde)
            beta = fromBigInteger(q.subtract(betaValue));
        return new ECPoint$Fp(this, x, beta, true);
    }

    public ECPoint getInfinity()
    {
        return infinity;
    }

    public boolean equals(Object anObject)
    {
        if(anObject == this)
            return true;
        if(!(anObject instanceof ECCurve$Fp))
        {
            return false;
        } else
        {
            ECCurve$Fp other = (ECCurve$Fp)anObject;
            return q.equals(other.q) && a.equals(other.a) && b.equals(other.b);
        }
    }

    public int hashCode()
    {
        return a.hashCode() ^ b.hashCode() ^ q.hashCode();
    }

    BigInteger q;
    ECPoint$Fp infinity;

    public ECCurve$Fp(BigInteger q, BigInteger a, BigInteger b)
    {
        this.q = q;
        this.a = fromBigInteger(a);
        this.b = fromBigInteger(b);
        infinity = new ECPoint$Fp(this, null, null);
    }
}
