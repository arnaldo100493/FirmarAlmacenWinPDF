// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSTU4145PublicKey.java

package co.org.bouncy.asn1.ua;

import co.org.bouncy.asn1.*;
import co.org.bouncy.math.ec.ECPoint;

// Referenced classes of package co.org.bouncy.asn1.ua:
//            DSTU4145PointEncoder

public class DSTU4145PublicKey extends ASN1Object
{

    public DSTU4145PublicKey(ECPoint pubKey)
    {
        this.pubKey = new DEROctetString(DSTU4145PointEncoder.encodePoint(pubKey));
    }

    private DSTU4145PublicKey(ASN1OctetString ocStr)
    {
        pubKey = ocStr;
    }

    public static DSTU4145PublicKey getInstance(Object obj)
    {
        if(obj instanceof DSTU4145PublicKey)
            return (DSTU4145PublicKey)obj;
        if(obj != null)
            return new DSTU4145PublicKey(ASN1OctetString.getInstance(obj));
        else
            return null;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return pubKey;
    }

    private ASN1OctetString pubKey;
}
