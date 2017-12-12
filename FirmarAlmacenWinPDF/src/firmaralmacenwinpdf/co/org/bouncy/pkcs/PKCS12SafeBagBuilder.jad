// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS12SafeBagBuilder.java

package co.org.bouncy.pkcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.asn1.x509.CertificateList;
import co.org.bouncy.cert.X509CRLHolder;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.OutputEncryptor;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.pkcs:
//            PKCS8EncryptedPrivateKeyInfoBuilder, PKCS12SafeBag, PKCS8EncryptedPrivateKeyInfo

public class PKCS12SafeBagBuilder
{

    public PKCS12SafeBagBuilder(PrivateKeyInfo privateKeyInfo, OutputEncryptor encryptor)
    {
        bagAttrs = new ASN1EncodableVector();
        bagType = PKCSObjectIdentifiers.pkcs8ShroudedKeyBag;
        bagValue = (new PKCS8EncryptedPrivateKeyInfoBuilder(privateKeyInfo)).build(encryptor).toASN1Structure();
    }

    public PKCS12SafeBagBuilder(PrivateKeyInfo privateKeyInfo)
    {
        bagAttrs = new ASN1EncodableVector();
        bagType = PKCSObjectIdentifiers.keyBag;
        bagValue = privateKeyInfo;
    }

    public PKCS12SafeBagBuilder(X509CertificateHolder certificate)
        throws IOException
    {
        this(certificate.toASN1Structure());
    }

    public PKCS12SafeBagBuilder(X509CRLHolder crl)
        throws IOException
    {
        this(crl.toASN1Structure());
    }

    public PKCS12SafeBagBuilder(Certificate certificate)
        throws IOException
    {
        bagAttrs = new ASN1EncodableVector();
        bagType = PKCSObjectIdentifiers.certBag;
        bagValue = new CertBag(PKCSObjectIdentifiers.x509Certificate, new DEROctetString(certificate.getEncoded()));
    }

    public PKCS12SafeBagBuilder(CertificateList crl)
        throws IOException
    {
        bagAttrs = new ASN1EncodableVector();
        bagType = PKCSObjectIdentifiers.crlBag;
        bagValue = new CertBag(PKCSObjectIdentifiers.x509Crl, new DEROctetString(crl.getEncoded()));
    }

    public PKCS12SafeBagBuilder addBagAttribute(ASN1ObjectIdentifier attrType, ASN1Encodable attrValue)
    {
        bagAttrs.add(new Attribute(attrType, new DERSet(attrValue)));
        return this;
    }

    public PKCS12SafeBag build()
    {
        return new PKCS12SafeBag(new SafeBag(bagType, bagValue, new DERSet(bagAttrs)));
    }

    private ASN1ObjectIdentifier bagType;
    private ASN1Encodable bagValue;
    private ASN1EncodableVector bagAttrs;
}
