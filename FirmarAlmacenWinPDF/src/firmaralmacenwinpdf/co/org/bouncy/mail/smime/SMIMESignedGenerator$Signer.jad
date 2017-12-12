// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMESignedGenerator.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.cms.AttributeTable;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

// Referenced classes of package co.org.bouncy.mail.smime:
//            SMIMESignedGenerator

private class SMIMESignedGenerator$Signer
{

    public X509Certificate getCert()
    {
        return cert;
    }

    public ASN1ObjectIdentifier getEncryptionOID()
    {
        return encryptionOID;
    }

    public ASN1ObjectIdentifier getDigestOID()
    {
        return digestOID;
    }

    public PrivateKey getKey()
    {
        return key;
    }

    public AttributeTable getSignedAttr()
    {
        return signedAttr;
    }

    public AttributeTable getUnsignedAttr()
    {
        return unsignedAttr;
    }

    final PrivateKey key;
    final X509Certificate cert;
    final ASN1ObjectIdentifier encryptionOID;
    final ASN1ObjectIdentifier digestOID;
    final AttributeTable signedAttr;
    final AttributeTable unsignedAttr;
    final SMIMESignedGenerator this$0;

    SMIMESignedGenerator$Signer(PrivateKey key, X509Certificate cert, ASN1ObjectIdentifier digestOID, AttributeTable signedAttr, AttributeTable unsignedAttr)
    {
        this(key, cert, null, digestOID, signedAttr, unsignedAttr);
    }

    SMIMESignedGenerator$Signer(PrivateKey key, X509Certificate cert, ASN1ObjectIdentifier encryptionOID, ASN1ObjectIdentifier digestOID, AttributeTable signedAttr, AttributeTable unsignedAttr)
    {
        this$0 = SMIMESignedGenerator.this;
        super();
        this.key = key;
        this.cert = cert;
        this.encryptionOID = encryptionOID;
        this.digestOID = digestOID;
        this.signedAttr = signedAttr;
        this.unsignedAttr = unsignedAttr;
    }
}
