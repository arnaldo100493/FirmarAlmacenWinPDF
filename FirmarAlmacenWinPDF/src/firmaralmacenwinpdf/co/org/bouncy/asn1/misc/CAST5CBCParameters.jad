// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CAST5CBCParameters.java

package co.org.bouncy.asn1.misc;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;

public class CAST5CBCParameters extends ASN1Object
{

    public static CAST5CBCParameters getInstance(Object o)
    {
        if(o instanceof CAST5CBCParameters)
            return (CAST5CBCParameters)o;
        if(o != null)
            return new CAST5CBCParameters(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public CAST5CBCParameters(byte iv[], int keyLength)
    {
        this.iv = new DEROctetString(iv);
        this.keyLength = new ASN1Integer(keyLength);
    }

    public CAST5CBCParameters(ASN1Sequence seq)
    {
        iv = (ASN1OctetString)seq.getObjectAt(0);
        keyLength = (ASN1Integer)seq.getObjectAt(1);
    }

    public byte[] getIV()
    {
        return iv.getOctets();
    }

    public int getKeyLength()
    {
        return keyLength.getValue().intValue();
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(iv);
        v.add(keyLength);
        return new DERSequence(v);
    }

    ASN1Integer keyLength;
    ASN1OctetString iv;
}
