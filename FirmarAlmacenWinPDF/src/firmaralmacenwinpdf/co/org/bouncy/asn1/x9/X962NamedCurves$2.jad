// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X962NamedCurves.java

package co.org.bouncy.asn1.x9;

import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.util.encoders.Hex;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.x9:
//            X9ECParametersHolder, X9ECParameters, X962NamedCurves

static class X962NamedCurves$2 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        ECCurve cFp192v2 = new co.org.bouncy.math.ec.ECCurve.Fp(new BigInteger("6277101735386680763835789423207666416083908700390324961279"), new BigInteger("fffffffffffffffffffffffffffffffefffffffffffffffc", 16), new BigInteger("cc22d6dfb95c6b25e49c0d6364a4e5980c393aa21668d953", 16));
        return new X9ECParameters(cFp192v2, cFp192v2.decodePoint(Hex.decode("03eea2bae7e1497842f2de7769cfe9c989c072ad696f48034a")), new BigInteger("fffffffffffffffffffffffe5fb1a724dc80418648d8dd31", 16), BigInteger.valueOf(1L), Hex.decode("31a92ee2029fd10d901b113e990710f0d21ac6b6"));
    }

    X962NamedCurves$2()
    {
    }
}
