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

static class TeleTrusTNamedCurves$1 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.Fp(new BigInteger("E95E4A5F737059DC60DFC7AD95B3D8139515620F", 16), new BigInteger("340E7BE2A280EB74E2BE61BADA745D97E8F7C300", 16), new BigInteger("1E589A8595423412134FAA2DBDEC95C8D8675E58", 16));
        return new X9ECParameters(curve, curve.decodePoint(Hex.decode("04BED5AF16EA3F6A4F62938C4631EB5AF7BDBCDBC31667CB477A1A8EC338F94741669C976316DA6321")), new BigInteger("E95E4A5F737059DC60DF5991D45029409E60FC09", 16), new BigInteger("01", 16));
    }

    TeleTrusTNamedCurves$1()
    {
    }
}
