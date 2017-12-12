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

static class X962NamedCurves$8 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        BigInteger c2m163v1n = new BigInteger("0400000000000000000001E60FC8821CC74DAEAFC1", 16);
        BigInteger c2m163v1h = BigInteger.valueOf(2L);
        ECCurve c2m163v1 = new co.org.bouncy.math.ec.ECCurve.F2m(163, 1, 2, 8, new BigInteger("072546B5435234A422E0789675F432C89435DE5242", 16), new BigInteger("00C9517D06D5240D3CFF38C74B20B6CD4D6F9DD4D9", 16), c2m163v1n, c2m163v1h);
        return new X9ECParameters(c2m163v1, c2m163v1.decodePoint(Hex.decode("0307AF69989546103D79329FCC3D74880F33BBE803CB")), c2m163v1n, c2m163v1h, Hex.decode("D2C0FB15760860DEF1EEF4D696E6768756151754"));
    }

    X962NamedCurves$8()
    {
    }
}
