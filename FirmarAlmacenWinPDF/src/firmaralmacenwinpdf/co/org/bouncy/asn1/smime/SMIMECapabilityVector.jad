// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMECapabilityVector.java

package co.org.bouncy.asn1.smime;

import co.org.bouncy.asn1.*;

public class SMIMECapabilityVector
{

    public SMIMECapabilityVector()
    {
        capabilities = new ASN1EncodableVector();
    }

    public void addCapability(ASN1ObjectIdentifier capability)
    {
        capabilities.add(new DERSequence(capability));
    }

    public void addCapability(ASN1ObjectIdentifier capability, int value)
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(capability);
        v.add(new ASN1Integer(value));
        capabilities.add(new DERSequence(v));
    }

    public void addCapability(ASN1ObjectIdentifier capability, ASN1Encodable params)
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(capability);
        v.add(params);
        capabilities.add(new DERSequence(v));
    }

    public ASN1EncodableVector toASN1EncodableVector()
    {
        return capabilities;
    }

    private ASN1EncodableVector capabilities;
}
