// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RC2CBCParameter.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;

public class RC2CBCParameter extends ASN1Object
{

    public static RC2CBCParameter getInstance(Object o)
    {
        if(o instanceof RC2CBCParameter)
            return (RC2CBCParameter)o;
        if(o != null)
            return new RC2CBCParameter(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public RC2CBCParameter(byte iv[])
    {
        version = null;
        this.iv = new DEROctetString(iv);
    }

    public RC2CBCParameter(int parameterVersion, byte iv[])
    {
        version = new ASN1Integer(parameterVersion);
        this.iv = new DEROctetString(iv);
    }

    private RC2CBCParameter(ASN1Sequence seq)
    {
        if(seq.size() == 1)
        {
            version = null;
            iv = (ASN1OctetString)seq.getObjectAt(0);
        } else
        {
            version = (ASN1Integer)seq.getObjectAt(0);
            iv = (ASN1OctetString)seq.getObjectAt(1);
        }
    }

    public BigInteger getRC2ParameterVersion()
    {
        if(version == null)
            return null;
        else
            return version.getValue();
    }

    public byte[] getIV()
    {
        return iv.getOctets();
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(version != null)
            v.add(version);
        v.add(iv);
        return new DERSequence(v);
    }

    ASN1Integer version;
    ASN1OctetString iv;
}
