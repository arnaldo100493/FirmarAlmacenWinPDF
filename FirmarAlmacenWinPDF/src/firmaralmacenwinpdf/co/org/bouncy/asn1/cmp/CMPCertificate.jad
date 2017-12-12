// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMPCertificate.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AttributeCertificate;
import co.org.bouncy.asn1.x509.Certificate;

public class CMPCertificate extends ASN1Object
    implements ASN1Choice
{

    public CMPCertificate(AttributeCertificate x509v2AttrCert)
    {
        this.x509v2AttrCert = x509v2AttrCert;
    }

    public CMPCertificate(Certificate x509v3PKCert)
    {
        if(x509v3PKCert.getVersionNumber() != 3)
        {
            throw new IllegalArgumentException("only version 3 certificates allowed");
        } else
        {
            this.x509v3PKCert = x509v3PKCert;
            return;
        }
    }

    public static CMPCertificate getInstance(Object o)
    {
        if(o == null || (o instanceof CMPCertificate))
            return (CMPCertificate)o;
        if((o instanceof ASN1Sequence) || (o instanceof byte[]))
            return new CMPCertificate(Certificate.getInstance(o));
        if(o instanceof ASN1TaggedObject)
            return new CMPCertificate(AttributeCertificate.getInstance(((ASN1TaggedObject)o).getObject()));
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid object: ").append(o.getClass().getName()).toString());
    }

    public boolean isX509v3PKCert()
    {
        return x509v3PKCert != null;
    }

    public Certificate getX509v3PKCert()
    {
        return x509v3PKCert;
    }

    public AttributeCertificate getX509v2AttrCert()
    {
        return x509v2AttrCert;
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(x509v2AttrCert != null)
            return new DERTaggedObject(true, 1, x509v2AttrCert);
        else
            return x509v3PKCert.toASN1Primitive();
    }

    private Certificate x509v3PKCert;
    private AttributeCertificate x509v2AttrCert;
}
