// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServiceLocator.java

package co.org.bouncy.asn1.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;

public class ServiceLocator extends ASN1Object
{

    public ServiceLocator()
    {
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(issuer);
        if(locator != null)
            v.add(locator);
        return new DERSequence(v);
    }

    X500Name issuer;
    ASN1Primitive locator;
}
