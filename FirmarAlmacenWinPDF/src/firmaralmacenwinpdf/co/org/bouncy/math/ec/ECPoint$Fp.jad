// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECPoint.java

package co.org.bouncy.math.ec;

import co.org.bouncy.asn1.x9.X9IntegerConverter;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.math.ec:
//            ECPoint, WNafMultiplier, ECCurve, ECFieldElement

public static class ECPoint$Fp extends ECPoint
{

    public byte[] getEncoded(boolean compressed)
    {
        if(isInfinity())
            return new byte[1];
        int qLength = ECPoint.access$000().getByteLength(x);
        if(compressed)
        {
            byte PC;
            if(getY().toBigInteger().testBit(0))
                PC = 3;
            else
                PC = 2;
            byte X[] = ECPoint.access$000().integerToBytes(getX().toBigInteger(), qLength);
            byte PO[] = new byte[X.length + 1];
            PO[0] = PC;
            System.arraycopy(X, 0, PO, 1, X.length);
            return PO;
        } else
        {
            byte X[] = ECPoint.access$000().integerToBytes(getX().toBigInteger(), qLength);
            byte Y[] = ECPoint.access$000().integerToBytes(getY().toBigInteger(), qLength);
            byte PO[] = new byte[X.length + Y.length + 1];
            PO[0] = 4;
            System.arraycopy(X, 0, PO, 1, X.length);
            System.arraycopy(Y, 0, PO, X.length + 1, Y.length);
            return PO;
        }
    }

    public ECPoint add(ECPoint b)
    {
        if(isInfinity())
            return b;
        if(b.isInfinity())
            return this;
        if(x.equals(b.x))
        {
            if(y.equals(b.y))
                return twice();
            else
                return curve.getInfinity();
        } else
        {
            ECFieldElement gamma = b.y.subtract(y).divide(b.x.subtract(x));
            ECFieldElement x3 = gamma.square().subtract(x).subtract(b.x);
            ECFieldElement y3 = gamma.multiply(x.subtract(x3)).subtract(y);
            return new ECPoint$Fp(curve, x3, y3, withCompression);
        }
    }

    public ECPoint twice()
    {
        if(isInfinity())
            return this;
        if(y.toBigInteger().signum() == 0)
        {
            return curve.getInfinity();
        } else
        {
            ECFieldElement TWO = curve.fromBigInteger(BigInteger.valueOf(2L));
            ECFieldElement THREE = curve.fromBigInteger(BigInteger.valueOf(3L));
            ECFieldElement gamma = x.square().multiply(THREE).add(curve.a).divide(y.multiply(TWO));
            ECFieldElement x3 = gamma.square().subtract(x.multiply(TWO));
            ECFieldElement y3 = gamma.multiply(x.subtract(x3)).subtract(y);
            return new ECPoint$Fp(curve, x3, y3, withCompression);
        }
    }

    public ECPoint subtract(ECPoint b)
    {
        if(b.isInfinity())
            return this;
        else
            return add(b.negate());
    }

    public ECPoint negate()
    {
        return new ECPoint$Fp(curve, x, y.negate(), withCompression);
    }

    synchronized void assertECMultiplier()
    {
        if(multiplier == null)
            multiplier = new WNafMultiplier();
    }

    public ECPoint$Fp(ECCurve curve, ECFieldElement x, ECFieldElement y)
    {
        this(curve, x, y, false);
    }

    public ECPoint$Fp(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression)
    {
        super(curve, x, y);
        if(x != null && y == null || x == null && y != null)
        {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        } else
        {
            this.withCompression = withCompression;
            return;
        }
    }
}
