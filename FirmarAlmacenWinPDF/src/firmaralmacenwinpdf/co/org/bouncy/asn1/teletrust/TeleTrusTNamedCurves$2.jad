// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TeleTrusTNamedCurves.java

package co.org.bouncy.asn1.teletrust;

import co.org.bouncy.asn1.x9.X9ECParameters;
import co.org.bouncy.asn1.x9.X9ECParametersHolder;
import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.util.encoders.Hex;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.teletrust:
//            TeleTrusTNamedCurves

static class TeleTrusTNamedCurves$2 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.Fp(new BigInteger("E95E4A5F737059DC60DFC7AD95B3D8139515620F", 16), new BigInteger("E95E4A5F737059DC60DFC7AD95B3D8139515620C", 16), new BigInteger("7A556B6DAE535B7B51ED2C4D7DAA7A0B5C55F380", 16));
        return new X9ECParameters(curve, curve.decodePoint(Hex.decode("04B199B13B9B34EFC1397E64BAEB05ACC265FF2378ADD6718B7C7C1961F0991B842443772152C9E0AD")), new BigInteger("E95E4A5F737059DC60DF5991D45029409E60FC09", 16), new BigInteger("01", 16));
    }

    TeleTrusTNamedCurves$2()
    {
    }
}
