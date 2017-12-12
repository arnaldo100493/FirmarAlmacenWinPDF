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

static class TeleTrusTNamedCurves$5 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.Fp(new BigInteger("D7C134AA264366862A18302575D1D787B09F075797DA89F57EC8C0FF", 16), new BigInteger("68A5E62CA9CE6C1C299803A6C1530B514E182AD8B0042A59CAD29F43", 16), new BigInteger("2580F63CCFE44138870713B1A92369E33E2135D266DBB372386C400B", 16));
        return new X9ECParameters(curve, curve.decodePoint(Hex.decode("040D9029AD2C7E5CF4340823B2A87DC68C9E4CE3174C1E6EFDEE12C07D58AA56F772C0726F24C6B89E4ECDAC24354B9E99CAA3F6D3761402CD")), new BigInteger("D7C134AA264366862A18302575D0FB98D116BC4B6DDEBCA3A5A7939F", 16), new BigInteger("01", 16));
    }

    TeleTrusTNamedCurves$5()
    {
    }
}
