// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS12SafeBag.java

package co.org.bouncy.pkcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.asn1.x509.CertificateList;
import co.org.bouncy.cert.X509CRLHolder;
import co.org.bouncy.cert.X509CertificateHolder;

// Referenced classes of package co.org.bouncy.pkcs:
//            PKCS8EncryptedPrivateKeyInfo

public class PKCS12SafeBag
{

    public PKCS12SafeBag(SafeBag safeBag)
    {
        this.safeBag = safeBag;
    }

    public SafeBag toASN1Structure()
    {
        return safeBag;
    }

    public ASN1ObjectIdentifier getType()
    {
        return safeBag.getBagId();
    }

    public Attribute[] getAttributes()
    {
        ASN1Set attrs = safeBag.getBagAttributes();
        if(attrs == null)
            return null;
        Attribute attributes[] = new Attribute[attrs.size()];
        for(int i = 0; i != attrs.size(); i++)
            attributes[i] = Attribute.getInstance(attrs.getObjectAt(i));

        return attributes;
    }

    public Object getBagValue()
    {
        if(getType().equals(PKCSObjectIdentifiers.pkcs8ShroudedKeyBag))
            return new PKCS8EncryptedPrivateKeyInfo(EncryptedPrivateKeyInfo.getInstance(safeBag.getBagValue()));
        if(getType().equals(PKCSObjectIdentifiers.certBag))
        {
            CertBag certBag = CertBag.getInstance(safeBag.getBagValue());
            return new X509CertificateHolder(Certificate.getInstance(ASN1OctetString.getInstance(certBag.getCertValue()).getOctets()));
        }
        if(getType().equals(PKCSObjectIdentifiers.keyBag))
            return PrivateKeyInfo.getInstance(safeBag.getBagValue());
        if(getType().equals(PKCSObjectIdentifiers.crlBag))
        {
            CRLBag crlBag = CRLBag.getInstance(safeBag.getBagValue());
            return new X509CRLHolder(CertificateList.getInstance(ASN1OctetString.getInstance(crlBag.getCRLValue()).getOctets()));
        } else
        {
            return safeBag.getBagValue();
        }
    }

    public static final ASN1ObjectIdentifier friendlyNameAttribute;
    public static final ASN1ObjectIdentifier localKeyIdAttribute;
    private SafeBag safeBag;

    static 
    {
        friendlyNameAttribute = PKCSObjectIdentifiers.pkcs_9_at_friendlyName;
        localKeyIdAttribute = PKCSObjectIdentifiers.pkcs_9_at_localKeyId;
    }
}
