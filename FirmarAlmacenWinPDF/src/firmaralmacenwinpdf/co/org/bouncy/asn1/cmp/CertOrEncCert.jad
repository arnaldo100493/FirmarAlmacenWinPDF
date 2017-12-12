// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertOrEncCert.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.crmf.EncryptedValue;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            CMPCertificate

public class CertOrEncCert extends ASN1Object
    implements ASN1Choice
{

    private CertOrEncCert(ASN1TaggedObject tagged)
    {
        if(tagged.getTagNo() == 0)
            certificate = CMPCertificate.getInstance(tagged.getObject());
        else
        if(tagged.getTagNo() == 1)
            encryptedCert = EncryptedValue.getInstance(tagged.getObject());
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown tag: ").append(tagged.getTagNo()).toString());
    }

    public static CertOrEncCert getInstance(Object o)
    {
        if(o instanceof CertOrEncCert)
            return (CertOrEncCert)o;
        if(o instanceof ASN1TaggedObject)
            return new CertOrEncCert((ASN1TaggedObject)o);
        else
            return null;
    }

    public CertOrEncCert(CMPCertificate certificate)
    {
        if(certificate == null)
        {
            throw new IllegalArgumentException("'certificate' cannot be null");
        } else
        {
            this.certificate = certificate;
            return;
        }
    }

    public CertOrEncCert(EncryptedValue encryptedCert)
    {
        if(encryptedCert == null)
        {
            throw new IllegalArgumentException("'encryptedCert' cannot be null");
        } else
        {
            this.encryptedCert = encryptedCert;
            return;
        }
    }

    public CMPCertificate getCertificate()
    {
        return certificate;
    }

    public EncryptedValue getEncryptedCert()
    {
        return encryptedCert;
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(certificate != null)
            return new DERTaggedObject(true, 0, certificate);
        else
            return new DERTaggedObject(true, 1, encryptedCert);
    }

    private CMPCertificate certificate;
    private EncryptedValue encryptedCert;
}
