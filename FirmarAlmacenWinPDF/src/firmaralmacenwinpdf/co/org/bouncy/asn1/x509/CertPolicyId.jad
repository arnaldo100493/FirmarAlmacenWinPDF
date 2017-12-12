// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertPolicyId.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

public class CertPolicyId extends ASN1Object
{

    private CertPolicyId(ASN1ObjectIdentifier id)
    {
        this.id = id;
    }

    public static CertPolicyId getInstance(Object o)
    {
        if(o instanceof CertPolicyId)
            return (CertPolicyId)o;
        if(o != null)
            return new CertPolicyId(ASN1ObjectIdentifier.getInstance(o));
        else
            return null;
    }

    public String getId()
    {
        return id.getId();
    }

    public ASN1Primitive toASN1Primitive()
    {
        return id;
    }

    private ASN1ObjectIdentifier id;
}
