// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSTU4145PointEncoder.java

package co.org.bouncy.asn1.ua;

import co.org.bouncy.asn1.x9.X9IntegerConverter;
import co.org.bouncy.math.ec.*;
import co.org.bouncy.util.Arrays;
import java.math.BigInteger;
import java.util.Random;

public abstract class DSTU4145PointEncoder
{

    public DSTU4145PointEncoder()
    {
    }

    private static BigInteger trace(ECFieldElement fe)
    {
        ECFieldElement t = fe;
        for(int i = 0; i < fe.getFieldSize() - 1; i++)
            t = t.square().add(fe);

        return t.toBigInteger();
    }

    private static ECFieldElement solveQuadradicEquation(ECFieldElement beta)
    {
        co.org.bouncy.math.ec.ECFieldElement.F2m b = (co.org.bouncy.math.ec.ECFieldElement.F2m)beta;
        ECFieldElement zeroElement = new co.org.bouncy.math.ec.ECFieldElement.F2m(b.getM(), b.getK1(), b.getK2(), b.getK3(), ECConstants.ZERO);
        if(beta.toBigInteger().equals(ECConstants.ZERO))
            return zeroElement;
        ECFieldElement z = null;
        ECFieldElement gamma = zeroElement;
        Random rand = new Random();
        int m = b.getM();
        do
        {
            ECFieldElement t = new co.org.bouncy.math.ec.ECFieldElement.F2m(b.getM(), b.getK1(), b.getK2(), b.getK3(), new BigInteger(m, rand));
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

    public static byte[] encodePoint(ECPoint Q)
    {
        int byteCount = converter.getByteLength(Q.getX());
        byte bytes[] = converter.integerToBytes(Q.getX().toBigInteger(), byteCount);
        if(!Q.getX().toBigInteger().equals(ECConstants.ZERO))
        {
            ECFieldElement y = Q.getY().multiply(Q.getX().invert());
            if(trace(y).equals(ECConstants.ONE))
                bytes[bytes.length - 1] |= 1;
            else
                bytes[bytes.length - 1] &= 0xfe;
        }
        return bytes;
    }

    public static ECPoint decodePoint(ECCurve curve, byte bytes[])
    {
        BigInteger k = BigInteger.valueOf(bytes[bytes.length - 1] & 1);
        if(!trace(curve.fromBigInteger(new BigInteger(1, bytes))).equals(curve.getA().toBigInteger()))
        {
            bytes = Arrays.clone(bytes);
            bytes[bytes.length - 1] ^= 1;
        }
        co.org.bouncy.math.ec.ECCurve.F2m c = (co.org.bouncy.math.ec.ECCurve.F2m)curve;
        ECFieldElement xp = curve.fromBigInteger(new BigInteger(1, bytes));
        ECFieldElement yp = null;
        if(xp.toBigInteger().equals(ECConstants.ZERO))
        {
            yp = (co.org.bouncy.math.ec.ECFieldElement.F2m)curve.getB();
            for(int i = 0; i < c.getM() - 1; i++)
                yp = yp.square();

        } else
        {
            ECFieldElement beta = xp.add(curve.getA()).add(curve.getB().multiply(xp.square().invert()));
            ECFieldElement z = solveQuadradicEquation(beta);
            if(z == null)
                throw new RuntimeException("Invalid point compression");
            if(!trace(z).equals(k))
                z = z.add(curve.fromBigInteger(ECConstants.ONE));
            yp = xp.multiply(z);
        }
        return new co.org.bouncy.math.ec.ECPoint.F2m(curve, xp, yp);
    }

    private static X9IntegerConverter converter = new X9IntegerConverter();

}
