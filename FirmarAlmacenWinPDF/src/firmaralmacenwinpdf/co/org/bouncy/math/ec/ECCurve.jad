// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECCurve.java

package co.org.bouncy.math.ec;

import java.math.BigInteger;
import java.util.Random;

// Referenced classes of package co.org.bouncy.math.ec:
//            ECPoint, ECFieldElement, ECConstants, Tnaf

public abstract class ECCurve
{
    public static class F2m extends ECCurve
    {

        public int getFieldSize()
        {
            return m;
        }

        public ECFieldElement fromBigInteger(BigInteger x)
        {
            return new ECFieldElement.F2m(m, k1, k2, k3, x);
        }

        public ECPoint createPoint(BigInteger x, BigInteger y, boolean withCompression)
        {
            return new ECPoint.F2m(this, fromBigInteger(x), fromBigInteger(y), withCompression);
        }

        public ECPoint getInfinity()
        {
            return infinity;
        }

        public boolean isKoblitz()
        {
            return n != null && h != null && (a.toBigInteger().equals(ECConstants.ZERO) || a.toBigInteger().equals(ECConstants.ONE)) && b.toBigInteger().equals(ECConstants.ONE);
        }

        synchronized byte getMu()
        {
            if(mu == 0)
                mu = Tnaf.getMu(this);
            return mu;
        }

        synchronized BigInteger[] getSi()
        {
            if(si == null)
                si = Tnaf.getSi(this);
            return si;
        }

        protected ECPoint decompressPoint(int yTilde, BigInteger X1)
        {
            ECFieldElement xp = fromBigInteger(X1);
            ECFieldElement yp = null;
            if(xp.toBigInteger().equals(ECConstants.ZERO))
            {
                yp = (ECFieldElement.F2m)b;
                for(int i = 0; i < m - 1; i++)
                    yp = yp.square();

            } else
            {
                ECFieldElement beta = xp.add(a).add(b.multiply(xp.square().invert()));
                ECFieldElement z = solveQuadradicEquation(beta);
                if(z == null)
                    throw new IllegalArgumentException("Invalid point compression");
                int zBit = z.toBigInteger().testBit(0) ? 1 : 0;
                if(zBit != yTilde)
                    z = z.add(fromBigInteger(ECConstants.ONE));
                yp = xp.multiply(z);
            }
            return new ECPoint.F2m(this, xp, yp, true);
        }

        private ECFieldElement solveQuadradicEquation(ECFieldElement beta)
        {
            ECFieldElement zeroElement = new ECFieldElement.F2m(m, k1, k2, k3, ECConstants.ZERO);
            if(beta.toBigInteger().equals(ECConstants.ZERO))
                return zeroElement;
            ECFieldElement z = null;
            ECFieldElement gamma = zeroElement;
            Random rand = new Random();
            do
            {
                ECFieldElement t = new ECFieldElement.F2m(m, k1, k2, k3, new BigInteger(m, rand));
                z = zeroElement;
                ECFieldElement w = beta;
                for(int i = 1; i <= m - 1; i++)
                {
                    ECFieldElement w2 = w.square();
                    z = z.square().add(w2.multiply(t));
                    w = w2.add(beta);
                }

                if(!w.toBigInteger().equals(ECConstants.ZERO))
                    return null;
                gamma = z.square().add(z);
            } while(gamma.toBigInteger().equals(ECConstants.ZERO));
            return z;
        }

        public boolean equals(Object anObject)
        {
            if(anObject == this)
                return true;
            if(!(anObject instanceof F2m))
            {
                return false;
            } else
            {
                F2m other = (F2m)anObject;
                return m == other.m && k1 == other.k1 && k2 == other.k2 && k3 == other.k3 && a.equals(other.a) && b.equals(other.b);
            }
        }

        public int hashCode()
        {
            return a.hashCode() ^ b.hashCode() ^ m ^ k1 ^ k2 ^ k3;
        }

        public int getM()
        {
            return m;
        }

        public boolean isTrinomial()
        {
            return k2 == 0 && k3 == 0;
        }

        public int getK1()
        {
            return k1;
        }

        public int getK2()
        {
            return k2;
        }

        public int getK3()
        {
            return k3;
        }

        public BigInteger getN()
        {
            return n;
        }

        public BigInteger getH()
        {
            return h;
        }

        private int m;
        private int k1;
        private int k2;
        private int k3;
        private BigInteger n;
        private BigInteger h;
        private ECPoint.F2m infinity;
        private byte mu;
        private BigInteger si[];

        public F2m(int m, int k, BigInteger a, BigInteger b)
        {
            this(m, k, 0, 0, a, b, null, null);
        }

        public F2m(int m, int k, BigInteger a, BigInteger b, BigInteger n, BigInteger h)
        {
            this(m, k, 0, 0, a, b, n, h);
        }

        public F2m(int m, int k1, int k2, int k3, BigInteger a, BigInteger b)
        {
            this(m, k1, k2, k3, a, b, null, null);
        }

        public F2m(int m, int k1, int k2, int k3, BigInteger a, BigInteger b, BigInteger n, 
                BigInteger h)
        {
            mu = 0;
            si = null;
            this.m = m;
            this.k1 = k1;
            this.k2 = k2;
            this.k3 = k3;
            this.n = n;
            this.h = h;
            if(k1 == 0)
                throw new IllegalArgumentException("k1 must be > 0");
            if(k2 == 0)
            {
                if(k3 != 0)
                    throw new IllegalArgumentException("k3 must be 0 if k2 == 0");
            } else
            {
                if(k2 <= k1)
                    throw new IllegalArgumentException("k2 must be > k1");
                if(k3 <= k2)
                    throw new IllegalArgumentException("k3 must be > k2");
            }
            this.a = fromBigInteger(a);
            this.b = fromBigInteger(b);
            infinity = new ECPoint.F2m(this, null, null);
        }
    }

    public static class Fp extends ECCurve
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
            return new ECFieldElement.Fp(q, x);
        }

        public ECPoint createPoint(BigInteger x, BigInteger y, boolean withCompression)
        {
            return new ECPoint.Fp(this, fromBigInteger(x), fromBigInteger(y), withCompression);
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
            return new ECPoint.Fp(this, x, beta, true);
        }

        public ECPoint getInfinity()
        {
            return infinity;
        }

        public boolean equals(Object anObject)
        {
            if(anObject == this)
                return true;
            if(!(anObject instanceof Fp))
            {
                return false;
            } else
            {
                Fp other = (Fp)anObject;
                return q.equals(other.q) && a.equals(other.a) && b.equals(other.b);
            }
        }

        public int hashCode()
        {
            return a.hashCode() ^ b.hashCode() ^ q.hashCode();
        }

        BigInteger q;
        ECPoint.Fp infinity;

        public Fp(BigInteger q, BigInteger a, BigInteger b)
        {
            this.q = q;
            this.a = fromBigInteger(a);
            this.b = fromBigInteger(b);
            infinity = new ECPoint.Fp(this, null, null);
        }
    }


    public ECCurve()
    {
    }

    public abstract int getFieldSize();

    public abstract ECFieldElement fromBigInteger(BigInteger biginteger);

    public abstract ECPoint createPoint(BigInteger biginteger, BigInteger biginteger1, boolean flag);

    public abstract ECPoint getInfinity();

    public ECFieldElement getA()
    {
        return a;
    }

    public ECFieldElement getB()
    {
        return b;
    }

    protected abstract ECPoint decompressPoint(int i, BigInteger biginteger);

    public ECPoint decodePoint(byte encoded[])
    {
        ECPoint p = null;
        int expectedLength = (getFieldSize() + 7) / 8;
        switch(encoded[0])
        {
        case 0: // '\0'
        {
            if(encoded.length != 1)
                throw new IllegalArgumentException("Incorrect length for infinity encoding");
            p = getInfinity();
            break;
        }

        case 2: // '\002'
        case 3: // '\003'
        {
            if(encoded.length != expectedLength + 1)
                throw new IllegalArgumentException("Incorrect length for compressed encoding");
            int yTilde = encoded[0] & 1;
            BigInteger X1 = fromArray(encoded, 1, expectedLength);
            p = decompressPoint(yTilde, X1);
            break;
        }

        case 4: // '\004'
        case 6: // '\006'
        case 7: // '\007'
        {
            if(encoded.length != 2 * expectedLength + 1)
                throw new IllegalArgumentException("Incorrect length for uncompressed/hybrid encoding");
            BigInteger X1 = fromArray(encoded, 1, expectedLength);
            BigInteger Y1 = fromArray(encoded, 1 + expectedLength, expectedLength);
            p = createPoint(X1, Y1, false);
            break;
        }

        case 1: // '\001'
        case 5: // '\005'
        default:
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid point encoding 0x").append(Integer.toString(encoded[0], 16)).toString());
        }
        }
        return p;
    }

    private static BigInteger fromArray(byte buf[], int off, int length)
    {
        byte mag[] = new byte[length];
        System.arraycopy(buf, off, mag, 0, length);
        return new BigInteger(1, mag);
    }

    ECFieldElement a;
    ECFieldElement b;
}
