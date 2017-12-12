// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECPoint.java

package co.org.bouncy.math.ec;

import co.org.bouncy.asn1.x9.X9IntegerConverter;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.math.ec:
//            ECPoint, WTauNafMultiplier, WNafMultiplier, ECCurve, 
//            ECFieldElement, ECConstants

public static class ECPoint$F2m extends ECPoint
{

    public byte[] getEncoded(boolean compressed)
    {
        if(isInfinity())
            return new byte[1];
        int byteCount = ECPoint.access$000().getByteLength(x);
        byte X[] = ECPoint.access$000().integerToBytes(getX().toBigInteger(), byteCount);
        byte PO[];
        if(compressed)
        {
            PO = new byte[byteCount + 1];
            PO[0] = 2;
            if(!getX().toBigInteger().equals(ECConstants.ZERO) && getY().multiply(getX().invert()).toBigInteger().testBit(0))
                PO[0] = 3;
            System.arraycopy(X, 0, PO, 1, byteCount);
        } else
        {
            byte Y[] = ECPoint.access$000().integerToBytes(getY().toBigInteger(), byteCount);
            PO = new byte[byteCount + byteCount + 1];
            PO[0] = 4;
            System.arraycopy(X, 0, PO, 1, byteCount);
            System.arraycopy(Y, 0, PO, byteCount + 1, byteCount);
        }
        return PO;
    }

    private static void checkPoints(ECPoint a, ECPoint b)
    {
        if(!a.curve.equals(b.curve))
            throw new IllegalArgumentException("Only points on the same curve can be added or subtracted");
        else
            return;
    }

    public ECPoint add(ECPoint b)
    {
        checkPoints(this, b);
        return addSimple((ECPoint$F2m)b);
    }

    public ECPoint$F2m addSimple(ECPoint$F2m b)
    {
        ECPoint$F2m other = b;
        if(isInfinity())
            return other;
        if(other.isInfinity())
            return this;
        nt.F2m x2 = (nt.F2m)other.getX();
        nt.F2m y2 = (nt.F2m)other.getY();
        if(x.equals(x2))
        {
            if(y.equals(y2))
                return (ECPoint$F2m)twice();
            else
                return (ECPoint$F2m)curve.getInfinity();
        } else
        {
            nt.F2m lambda = (nt.F2m)y.add(y2).divide(x.add(x2));
            nt.F2m x3 = (nt.F2m)lambda.square().add(lambda).add(x).add(x2).add(curve.getA());
            nt.F2m y3 = (nt.F2m)lambda.multiply(x.add(x3)).add(x3).add(y);
            return new ECPoint$F2m(curve, x3, y3, withCompression);
        }
    }

    public ECPoint subtract(ECPoint b)
    {
        checkPoints(this, b);
        return subtractSimple((ECPoint$F2m)b);
    }

    public ECPoint$F2m subtractSimple(ECPoint$F2m b)
    {
        if(b.isInfinity())
            return this;
        else
            return addSimple((ECPoint$F2m)b.negate());
    }

    public ECPoint twice()
    {
        if(isInfinity())
            return this;
        if(x.toBigInteger().signum() == 0)
        {
            return curve.getInfinity();
        } else
        {
            nt.F2m lambda = (nt.F2m)x.add(y.divide(x));
            nt.F2m x3 = (nt.F2m)lambda.square().add(lambda).add(curve.getA());
            ECFieldElement ONE = curve.fromBigInteger(ECConstants.ONE);
            nt.F2m y3 = (nt.F2m)x.square().add(x3.multiply(lambda.add(ONE)));
            return new ECPoint$F2m(curve, x3, y3, withCompression);
        }
    }

    public ECPoint negate()
    {
        return new ECPoint$F2m(curve, getX(), getY().add(getX()), withCompression);
    }

    synchronized void assertECMultiplier()
    {
        if(multiplier == null)
            if(((ECCurve$F2m)curve).isKoblitz())
                multiplier = new WTauNafMultiplier();
            else
                multiplier = new WNafMultiplier();
    }

    public ECPoint$F2m(ECCurve curve, ECFieldElement x, ECFieldElement y)
    {
        this(curve, x, y, false);
    }

    public ECPoint$F2m(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression)
    {
        super(curve, x, y);
        if(x != null && y == null || x == null && y != null)
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        if(x != null)
        {
            nt.F2m.checkFieldElements(this.x, this.y);
            if(curve != null)
                nt.F2m.checkFieldElements(this.x, this.curve.getA());
        }
        this.withCompression = withCompression;
    }
}
