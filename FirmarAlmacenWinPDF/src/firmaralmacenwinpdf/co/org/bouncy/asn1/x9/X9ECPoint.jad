// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X9ECPoint.java

package co.org.bouncy.asn1.x9;

import co.org.bouncy.asn1.*;
import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.math.ec.ECPoint;

public class X9ECPoint extends ASN1Object
{

    public X9ECPoint(ECPoint p)
    {
        this.p = p;
    }

    public X9ECPoint(ECCurve c, ASN1OctetString s)
    {
        p = c.decodePoint(s.getOctets());
    }

    public ECPoint getPoint()
    {
        return p;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return new DEROctetString(p.getEncoded());
    }

    ECPoint p;
}
