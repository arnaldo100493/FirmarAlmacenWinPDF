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

static class X962NamedCurves$5 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        ECCurve cFp239v2 = new co.org.bouncy.math.ec.ECCurve.Fp(new BigInteger("883423532389192164791648750360308885314476597252960362792450860609699839"), new BigInteger("7fffffffffffffffffffffff7fffffffffff8000000000007ffffffffffc", 16), new BigInteger("617fab6832576cbbfed50d99f0249c3fee58b94ba0038c7ae84c8c832f2c", 16));
        return new X9ECParameters(cFp239v2, cFp239v2.decodePoint(Hex.decode("0238af09d98727705120c921bb5e9e26296a3cdcf2f35757a0eafd87b830e7")), new BigInteger("7fffffffffffffffffffffff800000cfa7e8594377d414c03821bc582063", 16), BigInteger.valueOf(1L), Hex.decode("e8b4011604095303ca3b8099982be09fcb9ae616"));
    }

    X962NamedCurves$5()
    {
    }
}
