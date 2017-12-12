// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECFieldElement.java

package co.org.bouncy.math.ec;

import java.math.BigInteger;
import java.util.Random;

// Referenced classes of package co.org.bouncy.math.ec:
//            ECConstants, IntArray

public abstract class ECFieldElement
    implements ECConstants
{
    public static class F2m extends ECFieldElement
    {

        public BigInteger toBigInteger()
        {
            return x.toBigInteger();
        }

        public String getFieldName()
        {
            return "F2m";
        }

        public int getFieldSize()
        {
            return m;
        }

        public static void checkFieldElements(ECFieldElement a, ECFieldElement b)
        {
            if(!(a instanceof F2m) || !(b instanceof F2m))
                throw new IllegalArgumentException("Field elements are not both instances of ECFieldElement.F2m");
            F2m aF2m = (F2m)a;
            F2m bF2m = (F2m)b;
            if(aF2m.m != bF2m.m || aF2m.k1 != bF2m.k1 || aF2m.k2 != bF2m.k2 || aF2m.k3 != bF2m.k3)
                throw new IllegalArgumentException("Field elements are not elements of the same field F2m");
            if(aF2m.representation != bF2m.representation)
                throw new IllegalArgumentException("One of the field elements are not elements has incorrect representation");
            else
                return;
        }

        public ECFieldElement add(ECFieldElement b)
        {
            IntArray iarrClone = (IntArray)x.clone();
            F2m bF2m = (F2m)b;
            iarrClone.addShifted(bF2m.x, 0);
            return new F2m(m, k1, k2, k3, iarrClone);
        }

        public ECFieldElement subtract(ECFieldElement b)
        {
            return add(b);
        }

        public ECFieldElement multiply(ECFieldElement b)
        {
            F2m bF2m = (F2m)b;
            IntArray mult = x.multiply(bF2m.x, m);
            mult.reduce(m, new int[] {
                k1, k2, k3
            });
            return new F2m(m, k1, k2, k3, mult);
        }

        public ECFieldElement divide(ECFieldElement b)
        {
            ECFieldElement bInv = b.invert();
            return multiply(bInv);
        }

        public ECFieldElement negate()
        {
            return this;
        }

        public ECFieldElement square()
        {
            IntArray squared = x.square(m);
            squared.reduce(m, new int[] {
                k1, k2, k3
            });
            return new F2m(m, k1, k2, k3, squared);
        }

        public ECFieldElement invert()
        {
            IntArray uz = (IntArray)x.clone();
            IntArray vz = new IntArray(t);
            vz.setBit(m);
            vz.setBit(0);
            vz.setBit(k1);
            if(representation == 3)
            {
                vz.setBit(k2);
                vz.setBit(k3);
            }
            IntArray g1z = new IntArray(t);
            g1z.setBit(0);
            IntArray g2z = new IntArray(t);
            int jInt;
            IntArray g2zShift;
            for(; !uz.isZero(); g1z.addShifted(g2zShift, jInt))
            {
                int j = uz.bitLength() - vz.bitLength();
                if(j < 0)
                {
                    IntArray uzCopy = uz;
                    uz = vz;
                    vz = uzCopy;
                    IntArray g1zCopy = g1z;
                    g1z = g2z;
                    g2z = g1zCopy;
                    j = -j;
                }
                jInt = j >> 5;
                int jBit = j & 0x1f;
                IntArray vzShift = vz.shiftLeft(jBit);
                uz.addShifted(vzShift, jInt);
                g2zShift = g2z.shiftLeft(jBit);
            }

            return new F2m(m, k1, k2, k3, g2z);
        }

        public ECFieldElement sqrt()
        {
            throw new RuntimeException("Not implemented");
        }

        public int getRepresentation()
        {
            return representation;
        }

        public int getM()
        {
            return m;
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

        public boolean equals(Object anObject)
        {
            if(anObject == this)
                return true;
            if(!(anObject instanceof F2m))
            {
                return false;
            } else
            {
                F2m b = (F2m)anObject;
                return m == b.m && k1 == b.k1 && k2 == b.k2 && k3 == b.k3 && representation == b.representation && x.equals(b.x);
            }
        }

        public int hashCode()
        {
            return x.hashCode() ^ m ^ k1 ^ k2 ^ k3;
        }

        public static final int GNB = 1;
        public static final int TPB = 2;
        public static final int PPB = 3;
        private int representation;
        private int m;
        private int k1;
        private int k2;
        private int k3;
        private IntArray x;
        private int t;

        public F2m(int m, int k1, int k2, int k3, BigInteger x)
        {
            t = m + 31 >> 5;
            this.x = new IntArray(x, t);
            if(k2 == 0 && k3 == 0)
            {
                representation = 2;
            } else
            {
                if(k2 >= k3)
                    throw new IllegalArgumentException("k2 must be smaller than k3");
                if(k2 <= 0)
                    throw new IllegalArgumentException("k2 must be larger than 0");
                representation = 3;
            }
            if(x.signum() < 0)
            {
                throw new IllegalArgumentException("x value cannot be negative");
            } else
            {
                this.m = m;
                this.k1 = k1;
                this.k2 = k2;
                this.k3 = k3;
                return;
            }
        }

        public F2m(int m, int k, BigInteger x)
        {
            this(m, k, 0, 0, x);
        }

        private F2m(int m, int k1, int k2, int k3, IntArray x)
        {
            t = m + 31 >> 5;
            this.x = x;
            this.m = m;
            this.k1 = k1;
            this.k2 = k2;
            this.k3 = k3;
            if(k2 == 0 && k3 == 0)
                representation = 2;
            else
                representation = 3;
        }
    }

    public static class Fp extends ECFieldElement
    {

        public BigInteger toBigInteger()
        {
            return x;
        }

        public String getFieldName()
        {
            return "Fp";
        }

        public int getFieldSize()
        {
            return q.bitLength();
        }

        public BigInteger getQ()
        {
            return q;
        }

        public ECFieldElement add(ECFieldElement b)
        {
            return new Fp(q, x.add(b.toBigInteger()).mod(q));
        }

        public ECFieldElement subtract(ECFieldElement b)
        {
            return new Fp(q, x.subtract(b.toBigInteger()).mod(q));
        }

        public ECFieldElement multiply(ECFieldElement b)
        {
            return new Fp(q, x.multiply(b.toBigInteger()).mod(q));
        }

        public ECFieldElement divide(ECFieldElement b)
        {
            return new Fp(q, x.multiply(b.toBigInteger().modInverse(q)).mod(q));
        }

        public ECFieldElement negate()
        {
            return new Fp(q, x.negate().mod(q));
        }

        public ECFieldElement square()
        {
            return new Fp(q, x.multiply(x).mod(q));
        }

        public ECFieldElement invert()
        {
            return new Fp(q, x.modInverse(q));
        }

        public ECFieldElement sqrt()
        {
            if(!q.testBit(0))
                throw new RuntimeException("not done yet");
            if(q.testBit(1))
            {
                ECFieldElement z = new Fp(q, x.modPow(q.shiftRight(2).add(ECConstants.ONE), q));
                return z.square().equals(this) ? z : null;
            }
            BigInteger qMinusOne = q.subtract(ECConstants.ONE);
            BigInteger legendreExponent = qMinusOne.shiftRight(1);
            if(!x.modPow(legendreExponent, q).equals(ECConstants.ONE))
                return null;
            BigInteger u = qMinusOne.shiftRight(2);
            BigInteger k = u.shiftLeft(1).add(ECConstants.ONE);
            BigInteger Q = x;
            BigInteger fourQ = Q.shiftLeft(2).mod(q);
            Random rand = new Random();
            BigInteger U;
            do
            {
                BigInteger P;
                do
                    P = new BigInteger(q.bitLength(), rand);
                while(P.compareTo(q) >= 0 || !P.multiply(P).subtract(fourQ).modPow(legendreExponent, q).equals(qMinusOne));
                BigInteger result[] = lucasSequence(q, P, Q, k);
                U = result[0];
                BigInteger V = result[1];
                if(V.multiply(V).mod(q).equals(fourQ))
                {
                    if(V.testBit(0))
                        V = V.add(q);
                    V = V.shiftRight(1);
                    return new Fp(q, V);
                }
            } while(U.equals(ECConstants.ONE) || U.equals(qMinusOne));
            return null;
        }

        private static BigInteger[] lucasSequence(BigInteger p, BigInteger P, BigInteger Q, BigInteger k)
        {
            int n = k.bitLength();
            int s = k.getLowestSetBit();
            BigInteger Uh = ECConstants.ONE;
            BigInteger Vl = ECConstants.TWO;
            BigInteger Vh = P;
            BigInteger Ql = ECConstants.ONE;
            BigInteger Qh = ECConstants.ONE;
            for(int j = n - 1; j >= s + 1; j--)
            {
                Ql = Ql.multiply(Qh).mod(p);
                if(k.testBit(j))
                {
                    Qh = Ql.multiply(Q).mod(p);
                    Uh = Uh.multiply(Vh).mod(p);
                    Vl = Vh.multiply(Vl).subtract(P.multiply(Ql)).mod(p);
                    Vh = Vh.multiply(Vh).subtract(Qh.shiftLeft(1)).mod(p);
                } else
                {
                    Qh = Ql;
                    Uh = Uh.multiply(Vl).subtract(Ql).mod(p);
                    Vh = Vh.multiply(Vl).subtract(P.multiply(Ql)).mod(p);
                    Vl = Vl.multiply(Vl).subtract(Ql.shiftLeft(1)).mod(p);
                }
            }

            Ql = Ql.multiply(Qh).mod(p);
            Qh = Ql.multiply(Q).mod(p);
            Uh = Uh.multiply(Vl).subtract(Ql).mod(p);
            Vl = Vh.multiply(Vl).subtract(P.multiply(Ql)).mod(p);
            Ql = Ql.multiply(Qh).mod(p);
            for(int j = 1; j <= s; j++)
            {
                Uh = Uh.multiply(Vl).mod(p);
                Vl = Vl.multiply(Vl).subtract(Ql.shiftLeft(1)).mod(p);
                Ql = Ql.multiply(Ql).mod(p);
            }

            return (new BigInteger[] {
                Uh, Vl
            });
        }

        public boolean equals(Object other)
        {
            if(other == this)
                return true;
            if(!(other instanceof Fp))
            {
                return false;
            } else
            {
                Fp o = (Fp)other;
                return q.equals(o.q) && x.equals(o.x);
            }
        }

        public int hashCode()
        {
            return q.hashCode() ^ x.hashCode();
        }

        BigInteger x;
        BigInteger q;

        public Fp(BigInteger q, BigInteger x)
        {
            this.x = x;
            if(x.compareTo(q) >= 0)
            {
                throw new IllegalArgumentException("x value too large in field element");
            } else
            {
                this.q = q;
                return;
            }
        }
    }


    public ECFieldElement()
    {
    }

    public abstract BigInteger toBigInteger();

    public abstract String getFieldName();

    public abstract int getFieldSize();

    public abstract ECFieldElement add(ECFieldElement ecfieldelement);

    public abstract ECFieldElement subtract(ECFieldElement ecfieldelement);

    public abstract ECFieldElement multiply(ECFieldElement ecfieldelement);

    public abstract ECFieldElement divide(ECFieldElement ecfieldelement);

    public abstract ECFieldElement negate();

    public abstract ECFieldElement square();

    public abstract ECFieldElement invert();

    public abstract ECFieldElement sqrt();

    public String toString()
    {
        return toBigInteger().toString(2);
    }
}
