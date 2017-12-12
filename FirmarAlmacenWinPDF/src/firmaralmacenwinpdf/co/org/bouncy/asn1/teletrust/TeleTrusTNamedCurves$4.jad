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

static class TeleTrusTNamedCurves$4 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.Fp(new BigInteger("C302F41D932A36CDA7A3463093D18DB78FCE476DE1A86297", 16), new BigInteger("C302F41D932A36CDA7A3463093D18DB78FCE476DE1A86294", 16), new BigInteger("13D56FFAEC78681E68F9DEB43B35BEC2FB68542E27897B79", 16));
        return new X9ECParameters(curve, curve.decodePoint(Hex.decode("043AE9E58C82F63C30282E1FE7BBF43FA72C446AF6F4618129097E2C5667C2223A902AB5CA449D0084B7E5B3DE7CCC01C9")), new BigInteger("C302F41D932A36CDA7A3462F9E9E916B5BE8F1029AC4ACC1", 16), new BigInteger("01", 16));
    }

    TeleTrusTNamedCurves$4()
    {
    }
}
