// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AuthorityKeyIdentifierStructure.java

package co.org.bouncy.x509.extension;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.jce.PrincipalUtil;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;

// Referenced classes of package co.org.bouncy.x509.extension:
//            X509ExtensionUtil

/**
 * @deprecated Class AuthorityKeyIdentifierStructure is deprecated
 */

public class AuthorityKeyIdentifierStructure extends AuthorityKeyIdentifier
{

    public AuthorityKeyIdentifierStructure(byte encodedValue[])
        throws IOException
    {
        super((ASN1Sequence)X509ExtensionUtil.fromExtensionValue(encodedValue));
    }

    /**
     * @deprecated Method AuthorityKeyIdentifierStructure is deprecated
     */

    public AuthorityKeyIdentifierStructure(X509Extension extension)
    {
        super((ASN1Sequence)extension.getParsedValue());
    }

    public AuthorityKeyIdentifierStructure(Extension extension)
    {
        super((ASN1Sequence)extension.getParsedValue());
    }

    private static ASN1Sequence fromCertificate(X509Certificate certificate)
        throws CertificateParsingException
    {
        GeneralName genName;
        byte ext[];
        try
        {
            if(certificate.getVersion() != 3)
            {
                genName = new GeneralName(PrincipalUtil.getIssuerX509Principal(certificate));
                SubjectPublicKeyInfo info = new SubjectPublicKeyInfo((ASN1Sequence)(new ASN1InputStream(certificate.getPublicKey().getEncoded())).readObject());
                return (ASN1Sequence)(new AuthorityKeyIdentifier(info, new GeneralNames(genName), certificate.getSerialNumber())).toASN1Object();
            }
        }
        catch(Exception e)
        {
            throw new CertificateParsingException((new StringBuilder()).append("Exception extracting certificate details: ").append(e.toString()).toString());
        }
        genName = new GeneralName(PrincipalUtil.getIssuerX509Principal(certificate));
        ext = certificate.getExtensionValue(X509Extensions.SubjectKeyIdentifier.getId());
        if(ext != null)
        {
            ASN1OctetString str = (ASN1OctetString)X509ExtensionUtil.fromExtensionValue(ext);
            return (ASN1Sequence)(new AuthorityKeyIdentifier(str.getOctets(), new GeneralNames(genName), certificate.getSerialNumber())).toASN1Object();
        }
        SubjectPublicKeyInfo info = new SubjectPublicKeyInfo((ASN1Sequence)(new ASN1InputStream(certificate.getPublicKey().getEncoded())).readObject());
        return (ASN1Sequence)(new AuthorityKeyIdentifier(info, new GeneralNames(genName), certificate.getSerialNumber())).toASN1Object();
    }

    private static ASN1Sequence fromKey(PublicKey pubKey)
        throws InvalidKeyException
    {
        try
        {
            SubjectPublicKeyInfo info = new SubjectPublicKeyInfo((ASN1Sequence)(new ASN1InputStream(pubKey.getEncoded())).readObject());
            return (ASN1Sequence)(new AuthorityKeyIdentifier(info)).toASN1Object();
        }
        catch(Exception e)
        {
            throw new InvalidKeyException((new StringBuilder()).append("can't process key: ").append(e).toString());
        }
    }

    public AuthorityKeyIdentifierStructure(X509Certificate certificate)
        throws CertificateParsingException
    {
        super(fromCertificate(certificate));
    }

    public AuthorityKeyIdentifierStructure(PublicKey pubKey)
        throws InvalidKeyException
    {
        super(fromKey(pubKey));
    }
}
