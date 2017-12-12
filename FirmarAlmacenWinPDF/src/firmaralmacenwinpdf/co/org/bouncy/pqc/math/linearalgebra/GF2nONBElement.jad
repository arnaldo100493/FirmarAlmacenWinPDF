// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GF2nONBElement.java

package co.org.bouncy.pqc.math.linearalgebra;

import java.math.BigInteger;
import java.util.Random;

// Referenced classes of package co.org.bouncy.pqc.math.linearalgebra:
//            GF2nElement, GF2nONBField, GFElement, GF2nField

public class GF2nONBElement extends GF2nElement
{

    public GF2nONBElement(GF2nONBField gf2n, Random rand)
    {
        mField = gf2n;
        mDegree = mField.getDegree();
        mLength = gf2n.getONBLength();
        mBit = gf2n.getONBBit();
        mPol = new long[mLength];
        if(mLength > 1)
        {
            for(int j = 0; j < mLength - 1; j++)
                mPol[j] = rand.nextLong();

            long last = rand.nextLong();
            mPol[mLength - 1] = last >>> 64 - mBit;
        } else
        {
            mPol[0] = rand.nextLong();
            mPol[0] = mPol[0] >>> 64 - mBit;
        }
    }

    public GF2nONBElement(GF2nONBField gf2n, byte e[])
    {
        mField = gf2n;
        mDegree = mField.getDegree();
        mLength = gf2n.getONBLength();
        mBit = gf2n.getONBBit();
        mPol = new long[mLength];
        assign(e);
    }

    public GF2nONBElement(GF2nONBField gf2n, BigInteger val)
    {
        mField = gf2n;
        mDegree = mField.getDegree();
        mLength = gf2n.getONBLength();
        mBit = gf2n.getONBBit();
        mPol = new long[mLength];
        assign(val);
    }

    private GF2nONBElement(GF2nONBField gf2n, long val[])
    {
        mField = gf2n;
        mDegree = mField.getDegree();
        mLength = gf2n.getONBLength();
        mBit = gf2n.getONBBit();
        mPol = val;
    }

    public GF2nONBElement(GF2nONBElement gf2n)
    {
        mField = gf2n.mField;
        mDegree = mField.getDegree();
        mLength = ((GF2nONBField)mField).getONBLength();
        mBit = ((GF2nONBField)mField).getONBBit();
        mPol = new long[mLength];
        assign(gf2n.getElement());
    }

    public Object clone()
    {
        return new GF2nONBElement(this);
    }

    public static GF2nONBElement ZERO(GF2nONBField gf2n)
    {
        long polynomial[] = new long[gf2n.getONBLength()];
        return new GF2nONBElement(gf2n, polynomial);
    }

    public static GF2nONBElement ONE(GF2nONBField gf2n)
    {
        int mLength = gf2n.getONBLength();
        long polynomial[] = new long[mLength];
        for(int i = 0; i < mLength - 1; i++)
            polynomial[i] = -1L;

        polynomial[mLength - 1] = mMaxmask[gf2n.getONBBit() - 1];
        return new GF2nONBElement(gf2n, polynomial);
    }

    void assignZero()
    {
        mPol = new long[mLength];
    }

    void assignOne()
    {
        for(int i = 0; i < mLength - 1; i++)
            mPol[i] = -1L;

        mPol[mLength - 1] = mMaxmask[mBit - 1];
    }

    private void assign(BigInteger val)
    {
        assign(val.toByteArray());
    }

    private void assign(long val[])
    {
        System.arraycopy(val, 0, mPol, 0, mLength);
    }

    private void assign(byte val[])
    {
        mPol = new long[mLength];
        for(int j = 0; j < val.length; j++)
            mPol[j >>> 3] |= ((long)val[val.length - 1 - j] & 255L) << ((j & 7) << 3);

    }

    public boolean isZero()
    {
        boolean result = true;
        for(int i = 0; i < mLength && result; i++)
            result = result && (mPol[i] & -1L) == 0L;

        return result;
    }

    public boolean isOne()
    {
        boolean result = true;
        for(int i = 0; i < mLength - 1 && result; i++)
            result = result && (mPol[i] & -1L) == -1L;

        if(result)
            result = result && (mPol[mLength - 1] & mMaxmask[mBit - 1]) == mMaxmask[mBit - 1];
        return result;
    }

    public boolean equals(Object other)
    {
        if(other == null || !(other instanceof GF2nONBElement))
            return false;
        GF2nONBElement otherElem = (GF2nONBElement)other;
        for(int i = 0; i < mLength; i++)
            if(mPol[i] != otherElem.mPol[i])
                return false;

        return true;
    }

    public int hashCode()
    {
        return mPol.hashCode();
    }

    public boolean testRightmostBit()
    {
        return (mPol[mLength - 1] & mBitmask[mBit - 1]) != 0L;
    }

    boolean testBit(int index)
    {
        if(index < 0 || index > mDegree)
        {
            return false;
        } else
        {
            long test = mPol[index >>> 6] & mBitmask[index & 0x3f];
            return test != 0L;
        }
    }

    private long[] getElement()
    {
        long result[] = new long[mPol.length];
        System.arraycopy(mPol, 0, result, 0, mPol.length);
        return result;
    }

    private long[] getElementReverseOrder()
    {
        long result[] = new long[mPol.length];
        for(int i = 0; i < mDegree; i++)
            if(testBit(mDegree - i - 1))
                result[i >>> 6] |= mBitmask[i & 0x3f];

        return result;
    }

    void reverseOrder()
    {
        mPol = getElementReverseOrder();
    }

    public GFElement add(GFElement addend)
        throws RuntimeException
    {
        GF2nONBElement result = new GF2nONBElement(this);
        result.addToThis(addend);
        return result;
    }

    public void addToThis(GFElement addend)
        throws RuntimeException
    {
        if(!(addend instanceof GF2nONBElement))
            throw new RuntimeException();
        if(!mField.equals(((GF2nONBElement)addend).mField))
            throw new RuntimeException();
        for(int i = 0; i < mLength; i++)
            mPol[i] ^= ((GF2nONBElement)addend).mPol[i];

    }

    public GF2nElement increase()
    {
        GF2nONBElement result = new GF2nONBElement(this);
        result.increaseThis();
        return result;
    }

    public void increaseThis()
    {
        addToThis(ONE((GF2nONBField)mField));
    }

    public GFElement multiply(GFElement factor)
        throws RuntimeException
    {
        GF2nONBElement result = new GF2nONBElement(this);
        result.multiplyThisBy(factor);
        return result;
    }

    public void multiplyThisBy(GFElement factor)
        throws RuntimeException
    {
        if(!(factor instanceof GF2nONBElement))
            throw new RuntimeException("The elements have different representation: not yet implemented");
        if(!mField.equals(((GF2nONBElement)factor).mField))
            throw new RuntimeException();
        if(equals(factor))
        {
            squareThis();
        } else
        {
            long a[] = mPol;
            long b[] = ((GF2nONBElement)factor).mPol;
            long c[] = new long[mLength];
            int m[][] = ((GF2nONBField)mField).mMult;
            int degf = mLength - 1;
            int degb = mBit - 1;
            int s = 0;
            long TWOTOMAXLONGM1 = mBitmask[63];
            long TWOTODEGB = mBitmask[degb];
            for(int k = 0; k < mDegree; k++)
            {
                s = 0;
                int fielda;
                int bita;
                for(int i = 0; i < mDegree; i++)
                {
                    fielda = mIBY64[i];
                    bita = i & 0x3f;
                    int fieldb = mIBY64[m[i][0]];
                    int bitb = m[i][0] & 0x3f;
                    if((a[fielda] & mBitmask[bita]) == 0L)
                        continue;
                    if((b[fieldb] & mBitmask[bitb]) != 0L)
                        s ^= 1;
                    if(m[i][1] == -1)
                        continue;
                    fieldb = mIBY64[m[i][1]];
                    bitb = m[i][1] & 0x3f;
                    if((b[fieldb] & mBitmask[bitb]) != 0L)
                        s ^= 1;
                }

                fielda = mIBY64[k];
                bita = k & 0x3f;
                if(s != 0)
                    c[fielda] ^= mBitmask[bita];
                boolean old;
                if(mLength > 1)
                {
                    old = (a[degf] & 1L) == 1L;
                    for(int i = degf - 1; i >= 0; i--)
                    {
                        boolean now = (a[i] & 1L) != 0L;
                        a[i] = a[i] >>> 1;
                        if(old)
                            a[i] ^= TWOTOMAXLONGM1;
                        old = now;
                    }

                    a[degf] = a[degf] >>> 1;
                    if(old)
                        a[degf] ^= TWOTODEGB;
                    old = (b[degf] & 1L) == 1L;
                    for(int i = degf - 1; i >= 0; i--)
                    {
                        boolean now = (b[i] & 1L) != 0L;
                        b[i] = b[i] >>> 1;
                        if(old)
                            b[i] ^= TWOTOMAXLONGM1;
                        old = now;
                    }

                    b[degf] = b[degf] >>> 1;
                    if(old)
                        b[degf] ^= TWOTODEGB;
                    continue;
                }
                old = (a[0] & 1L) == 1L;
                a[0] = a[0] >>> 1;
                if(old)
                    a[0] ^= TWOTODEGB;
                old = (b[0] & 1L) == 1L;
                b[0] = b[0] >>> 1;
                if(old)
                    b[0] ^= TWOTODEGB;
            }

            assign(c);
        }
    }

    public GF2nElement square()
    {
        GF2nONBElement result = new GF2nONBElement(this);
        result.squareThis();
        return result;
    }

    public void squareThis()
    {
        long pol[] = getElement();
        int f = mLength - 1;
        int b = mBit - 1;
        long TWOTOMAXLONGM1 = mBitmask[63];
        boolean old = (pol[f] & mBitmask[b]) != 0L;
        boolean now;
        for(int i = 0; i < f; i++)
        {
            now = (pol[i] & TWOTOMAXLONGM1) != 0L;
            pol[i] = pol[i] << 1;
            if(old)
                pol[i] ^= 1L;
            old = now;
        }

        now = (pol[f] & mBitmask[b]) != 0L;
        pol[f] = pol[f] << 1;
        if(old)
            pol[f] ^= 1L;
        if(now)
            pol[f] ^= mBitmask[b + 1];
        assign(pol);
    }

    public GFElement invert()
        throws ArithmeticException
    {
        GF2nONBElement result = new GF2nONBElement(this);
        result.invertThis();
        return result;
    }

    public void invertThis()
        throws ArithmeticException
    {
        if(isZero())
            throw new ArithmeticException();
        int r = 31;
        for(boolean found = false; !found && r >= 0; r--)
            if(((long)(mDegree - 1) & mBitmask[r]) != 0L)
                found = true;

        r++;
        GF2nElement m = ZERO((GF2nONBField)mField);
        GF2nElement n = new GF2nONBElement(this);
        int k = 1;
        for(int i = r - 1; i >= 0; i--)
        {
            m = (GF2nElement)n.clone();
            for(int j = 1; j <= k; j++)
                m.squareThis();

            n.multiplyThisBy(m);
            k <<= 1;
            if(((long)(mDegree - 1) & mBitmask[i]) != 0L)
            {
                n.squareThis();
                n.multiplyThisBy(this);
                k++;
            }
        }

        n.squareThis();
    }

    public GF2nElement squareRoot()
    {
        GF2nONBElement result = new GF2nONBElement(this);
        result.squareRootThis();
        return result;
    }

    public void squareRootThis()
    {
        long pol[] = getElement();
        int f = mLength - 1;
        int b = mBit - 1;
        long TWOTOMAXLONGM1 = mBitmask[63];
        boolean old = (pol[0] & 1L) != 0L;
        for(int i = f; i >= 0; i--)
        {
            boolean now = (pol[i] & 1L) != 0L;
            pol[i] = pol[i] >>> 1;
            if(old)
                if(i == f)
                    pol[i] ^= mBitmask[b];
                else
                    pol[i] ^= TWOTOMAXLONGM1;
            old = now;
        }

        assign(pol);
    }

    public int trace()
    {
        int result = 0;
        int max = mLength - 1;
        for(int i = 0; i < max; i++)
        {
            for(int j = 0; j < 64; j++)
                if((mPol[i] & mBitmask[j]) != 0L)
                    result ^= 1;

        }

        int b = mBit;
        for(int j = 0; j < b; j++)
            if((mPol[max] & mBitmask[j]) != 0L)
                result ^= 1;

        return result;
    }

    public GF2nElement solveQuadraticEquation()
        throws RuntimeException
    {
        if(trace() == 1)
            throw new RuntimeException();
        long TWOTOMAXLONGM1 = mBitmask[63];
        long ZERO = 0L;
        long ONE = 1L;
        long p[] = new long[mLength];
        long z = 0L;
        int j = 1;
        for(int i = 0; i < mLength - 1; i++)
        {
            for(j = 1; j < 64; j++)
                if(((mBitmask[j] & mPol[i]) == ZERO || (z & mBitmask[j - 1]) == ZERO) && ((mPol[i] & mBitmask[j]) != ZERO || (z & mBitmask[j - 1]) != ZERO))
                    z ^= mBitmask[j];

            p[i] = z;
            if((TWOTOMAXLONGM1 & z) != ZERO && (ONE & mPol[i + 1]) == ONE || (TWOTOMAXLONGM1 & z) == ZERO && (ONE & mPol[i + 1]) == ZERO)
                z = ZERO;
            else
                z = ONE;
        }

        int b = mDegree & 0x3f;
        long LASTLONG = mPol[mLength - 1];
        for(j = 1; j < b; j++)
            if(((mBitmask[j] & LASTLONG) == ZERO || (mBitmask[j - 1] & z) == ZERO) && ((mBitmask[j] & LASTLONG) != ZERO || (mBitmask[j - 1] & z) != ZERO))
                z ^= mBitmask[j];

        p[mLength - 1] = z;
        return new GF2nONBElement((GF2nONBField)mField, p);
    }

    public String toString()
    {
        return toString(16);
    }

    public String toString(int radix)
    {
        String s = "";
        long a[] = getElement();
        int b = mBit;
        if(radix == 2)
        {
            for(int j = b - 1; j >= 0; j--)
                if((a[a.length - 1] & 1L << j) == 0L)
                    s = (new StringBuilder()).append(s).append("0").toString();
                else
                    s = (new StringBuilder()).append(s).append("1").toString();

            for(int i = a.length - 2; i >= 0; i--)
            {
                for(int j = 63; j >= 0; j--)
                    if((a[i] & mBitmask[j]) == 0L)
                        s = (new StringBuilder()).append(s).append("0").toString();
                    else
                        s = (new StringBuilder()).append(s).append("1").toString();

            }

        } else
        if(radix == 16)
        {
            char HEX_CHARS[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
                'a', 'b', 'c', 'd', 'e', 'f'
            };
            for(int i = a.length - 1; i >= 0; i--)
            {
                s = (new StringBuilder()).append(s).append(HEX_CHARS[(int)(a[i] >>> 60) & 0xf]).toString();
                s = (new StringBuilder()).append(s).append(HEX_CHARS[(int)(a[i] >>> 56) & 0xf]).toString();
                s = (new StringBuilder()).append(s).append(HEX_CHARS[(int)(a[i] >>> 52) & 0xf]).toString();
                s = (new StringBuilder()).append(s).append(HEX_CHARS[(int)(a[i] >>> 48) & 0xf]).toString();
                s = (new StringBuilder()).append(s).append(HEX_CHARS[(int)(a[i] >>> 44) & 0xf]).toString();
                s = (new StringBuilder()).append(s).append(HEX_CHARS[(int)(a[i] >>> 40) & 0xf]).toString();
                s = (new StringBuilder()).append(s).append(HEX_CHARS[(int)(a[i] >>> 36) & 0xf]).toString();
                s = (new StringBuilder()).append(s).append(HEX_CHARS[(int)(a[i] >>> 32) & 0xf]).toString();
                s = (new StringBuilder()).append(s).append(HEX_CHARS[(int)(a[i] >>> 28) & 0xf]).toString();
                s = (new StringBuilder()).append(s).append(HEX_CHARS[(int)(a[i] >>> 24) & 0xf]).toString();
                s = (new StringBuilder()).append(s).append(HEX_CHARS[(int)(a[i] >>> 20) & 0xf]).toString();
                s = (new StringBuilder()).append(s).append(HEX_CHARS[(int)(a[i] >>> 16) & 0xf]).toString();
                s = (new StringBuilder()).append(s).append(HEX_CHARS[(int)(a[i] >>> 12) & 0xf]).toString();
                s = (new StringBuilder()).append(s).append(HEX_CHARS[(int)(a[i] >>> 8) & 0xf]).toString();
                s = (new StringBuilder()).append(s).append(HEX_CHARS[(int)(a[i] >>> 4) & 0xf]).toString();
                s = (new StringBuilder()).append(s).append(HEX_CHARS[(int)a[i] & 0xf]).toString();
                s = (new StringBuilder()).append(s).append(" ").toString();
            }

        }
        return s;
    }

    public BigInteger toFlexiBigInt()
    {
        return new BigInteger(1, toByteArray());
    }

    public byte[] toByteArray()
    {
        int k = (mDegree - 1 >> 3) + 1;
        byte result[] = new byte[k];
        for(int i = 0; i < k; i++)
            result[k - i - 1] = (byte)(int)((mPol[i >>> 3] & 255L << ((i & 7) << 3)) >>> ((i & 7) << 3));

        return result;
    }

    private static final long mBitmask[] = {
        1L, 2L, 4L, 8L, 16L, 32L, 64L, 128L, 256L, 512L, 
        1024L, 2048L, 4096L, 8192L, 16384L, 32768L, 0x10000L, 0x20000L, 0x40000L, 0x80000L, 
        0x100000L, 0x200000L, 0x400000L, 0x800000L, 0x1000000L, 0x2000000L, 0x4000000L, 0x8000000L, 0x10000000L, 0x20000000L, 
        0x40000000L, 0x80000000L, 0x100000000L, 0x200000000L, 0x400000000L, 0x800000000L, 0x1000000000L, 0x2000000000L, 0x4000000000L, 0x8000000000L, 
        0x10000000000L, 0x20000000000L, 0x40000000000L, 0x80000000000L, 0x100000000000L, 0x200000000000L, 0x400000000000L, 0x800000000000L, 0x1000000000000L, 0x2000000000000L, 
        0x4000000000000L, 0x8000000000000L, 0x10000000000000L, 0x20000000000000L, 0x40000000000000L, 0x80000000000000L, 0x100000000000000L, 0x200000000000000L, 0x400000000000000L, 0x800000000000000L, 
        0x1000000000000000L, 0x2000000000000000L, 0x4000000000000000L, 0x8000000000000000L
    };
    private static final long mMaxmask[] = {
        1L, 3L, 7L, 15L, 31L, 63L, 127L, 255L, 511L, 1023L, 
        2047L, 4095L, 8191L, 16383L, 32767L, 65535L, 0x1ffffL, 0x3ffffL, 0x7ffffL, 0xfffffL, 
        0x1fffffL, 0x3fffffL, 0x7fffffL, 0xffffffL, 0x1ffffffL, 0x3ffffffL, 0x7ffffffL, 0xfffffffL, 0x1fffffffL, 0x3fffffffL, 
        0x7fffffffL, 0xffffffffL, 0x1ffffffffL, 0x3ffffffffL, 0x7ffffffffL, 0xfffffffffL, 0x1fffffffffL, 0x3fffffffffL, 0x7fffffffffL, 0xffffffffffL, 
        0x1ffffffffffL, 0x3ffffffffffL, 0x7ffffffffffL, 0xfffffffffffL, 0x1fffffffffffL, 0x3fffffffffffL, 0x7fffffffffffL, 0xffffffffffffL, 0x1ffffffffffffL, 0x3ffffffffffffL, 
        0x7ffffffffffffL, 0xfffffffffffffL, 0x1fffffffffffffL, 0x3fffffffffffffL, 0x7fffffffffffffL, 0xffffffffffffffL, 0x1ffffffffffffffL, 0x3ffffffffffffffL, 0x7ffffffffffffffL, 0xfffffffffffffffL, 
        0x1fffffffffffffffL, 0x3fffffffffffffffL, 0x7fffffffffffffffL, -1L
    };
    private static final int mIBY64[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
        1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 
        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
        2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 
        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 
        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 
        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 
        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 
        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 
        3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 
        4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 
        4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 
        4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 
        4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 
        4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 
        4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 
        5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 
        5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 
        5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 
        5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 
        5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 
        5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 
        5, 5, 5, 5
    };
    private static final int MAXLONG = 64;
    private int mLength;
    private int mBit;
    private long mPol[];

}
