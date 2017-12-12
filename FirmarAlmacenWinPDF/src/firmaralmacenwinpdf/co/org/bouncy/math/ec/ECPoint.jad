// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECPoint.java

package co.org.bouncy.math.ec;

import co.org.bouncy.asn1.x9.X9IntegerConverter;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.math.ec:
//            FpNafMultiplier, ECCurve, ECMultiplier, ECFieldElement, 
//            PreCompInfo, WTauNafMultiplier, WNafMultiplier, ECConstants

public abstract class ECPoint
{
    public static class F2m extends ECPoint
    {

        public byte[] getEncoded(boolean compressed)
        {
            if(isInfinity())
                return new byte[1];
            int byteCount = ECPoint.converter.getByteLength(x);
            byte X[] = ECPoint.converter.integerToBytes(getX().toBigInteger(), byteCount);
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
                byte Y[] = ECPoint.converter.integerToBytes(getY().toBigInteger(), byteCount);
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
            return addSimple((F2m)b);
        }

        public F2m addSimple(F2m b)
        {
            F2m other = b;
            if(isInfinity())
                return other;
            if(other.isInfinity())
                return this;
            ECFieldElement.F2m x2 = (ECFieldElement.F2m)other.getX();
            ECFieldElement.F2m y2 = (ECFieldElement.F2m)other.getY();
            if(x.equals(x2))
            {
                if(y.equals(y2))
                    return (F2m)twice();
                else
                    return (F2m)curve.getInfinity();
            } else
            {
                ECFieldElement.F2m lambda = (ECFieldElement.F2m)y.add(y2).divide(x.add(x2));
                ECFieldElement.F2m x3 = (ECFieldElement.F2m)lambda.square().add(lambda).add(x).add(x2).add(curve.getA());
                ECFieldElement.F2m y3 = (ECFieldElement.F2m)lambda.multiply(x.add(x3)).add(x3).add(y);
                return new F2m(curve, x3, y3, withCompression);
            }
        }

        public ECPoint subtract(ECPoint b)
        {
            checkPoints(this, b);
            return subtractSimple((F2m)b);
        }

        public F2m subtractSimple(F2m b)
        {
            if(b.isInfinity())
                return this;
            else
                return addSimple((F2m)b.negate());
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
                ECFieldElement.F2m lambda = (ECFieldElement.F2m)x.add(y.divide(x));
                ECFieldElement.F2m x3 = (ECFieldElement.F2m)lambda.square().add(lambda).add(curve.getA());
                ECFieldElement ONE = curve.fromBigInteger(ECConstants.ONE);
                ECFieldElement.F2m y3 = (ECFieldElement.F2m)x.square().add(x3.multiply(lambda.add(ONE)));
                return new F2m(curve, x3, y3, withCompression);
            }
        }

        public ECPoint negate()
        {
            return new F2m(curve, getX(), getY().add(getX()), withCompression);
        }

        synchronized void assertECMultiplier()
        {
            if(multiplier == null)
                if(((ECCurve.F2m)curve).isKoblitz())
                    multiplier = new WTauNafMultiplier();
                else
                    multiplier = new WNafMultiplier();
        }

        public F2m(ECCurve curve, ECFieldElement x, ECFieldElement y)
        {
            this(curve, x, y, false);
        }

        public F2m(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression)
        {
            super(curve, x, y);
            if(x != null && y == null || x == null && y != null)
                throw new IllegalArgumentException("Exactly one of the field elements is null");
            if(x != null)
            {
                ECFieldElement.F2m.checkFieldElements(this.x, this.y);
                if(curve != null)
                    ECFieldElement.F2m.checkFieldElements(this.x, this.curve.getA());
            }
            this.withCompression = withCompression;
        }
    }

    public static class Fp extends ECPoint
    {

        public byte[] getEncoded(boolean compressed)
        {
            if(isInfinity())
                return new byte[1];
            int qLength = ECPoint.converter.getByteLength(x);
            if(compressed)
            {
                byte PC;
                if(getY().toBigInteger().testBit(0))
                    PC = 3;
                else
                    PC = 2;
                byte X[] = ECPoint.converter.integerToBytes(getX().toBigInteger(), qLength);
                byte PO[] = new byte[X.length + 1];
                PO[0] = PC;
                System.arraycopy(X, 0, PO, 1, X.length);
                return PO;
            } else
            {
                byte X[] = ECPoint.converter.integerToBytes(getX().toBigInteger(), qLength);
                byte Y[] = ECPoint.converter.integerToBytes(getY().toBigInteger(), qLength);
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
                return new Fp(curve, x3, y3, withCompression);
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
                return new Fp(curve, x3, y3, withCompression);
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
            return new Fp(curve, x, y.negate(), withCompression);
        }

        synchronized void assertECMultiplier()
        {
            if(multiplier == null)
                multiplier = new WNafMultiplier();
        }

        public Fp(ECCurve curve, ECFieldElement x, ECFieldElement y)
        {
            this(curve, x, y, false);
        }

        public Fp(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression)
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


    protected ECPoint(ECCurve curve, ECFieldElement x, ECFieldElement y)
    {
        multiplier = null;
        preCompInfo = null;
        this.curve = curve;
        this.x = x;
        this.y = y;
    }

    public ECCurve getCurve()
    {
        return curve;
    }

    public ECFieldElement getX()
    {
        return x;
    }

    public ECFieldElement getY()
    {
        return y;
    }

    public boolean isInfinity()
    {
        return x == null && y == null;
    }

    public boolean isCompressed()
    {
        return withCompression;
    }

    public boolean equals(Object other)
    {
        if(other == this)
            return true;
        if(!(other instanceof ECPoint))
            return false;
        ECPoint o = (ECPoint)other;
        if(isInfinity())
            return o.isInfinity();
        else
            return x.equals(o.x) && y.equals(o.y);
    }

    public int hashCode()
    {
        if(isInfinity())
            return 0;
        else
            return x.hashCode() ^ y.hashCode();
    }

    void setPreCompInfo(PreCompInfo preCompInfo)
    {
        this.preCompInfo = preCompInfo;
    }

    public byte[] getEncoded()
    {
        return getEncoded(withCompression);
    }

    public abstract byte[] getEncoded(boolean flag);

    public abstract ECPoint add(ECPoint ecpoint);

    public abstract ECPoint subtract(ECPoint ecpoint);

    public abstract ECPoint negate();

    public abstract ECPoint twice();

    synchronized void assertECMultiplier()
    {
        if(multiplier == null)
            multiplier = new FpNafMultiplier();
    }

    public ECPoint multiply(BigInteger k)
    {
        if(k.signum() < 0)
            throw new IllegalArgumentException("The multiplicator cannot be negative");
        if(isInfinity())
            return this;
        if(k.signum() == 0)
        {
            return curve.getInfinity();
        } else
        {
            assertECMultiplier();
            return multiplier.multiply(this, k, preCompInfo);
        }
    }

    ECCurve curve;
    ECFieldElement x;
    ECFieldElement y;
    protected boolean withCompression;
    protected ECMultiplier multiplier;
    protected PreCompInfo preCompInfo;
    private static X9IntegerConverter converter = new X9IntegerConverter();


}
