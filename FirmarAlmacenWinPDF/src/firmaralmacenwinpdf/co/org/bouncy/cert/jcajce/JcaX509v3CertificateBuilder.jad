// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaX509v3CertificateBuilder.java

package co.org.bouncy.cert.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.cert.X509v3CertificateBuilder;
import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package co.org.bouncy.cert.jcajce:
//            JcaX509CertificateHolder

public class JcaX509v3CertificateBuilder extends X509v3CertificateBuilder
{

    public JcaX509v3CertificateBuilder(X500Name issuer, BigInteger serial, Date notBefore, Date notAfter, X500Name subject, PublicKey publicKey)
    {
        super(issuer, serial, notBefore, notAfter, subject, SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()));
    }

    public JcaX509v3CertificateBuilder(X500Principal issuer, BigInteger serial, Date notBefore, Date notAfter, X500Principal subject, PublicKey publicKey)
    {
        super(X500Name.getInstance(issuer.getEncoded()), serial, notBefore, notAfter, X500Name.getInstance(subject.getEncoded()), SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()));
    }

    public JcaX509v3CertificateBuilder(X509Certificate issuerCert, BigInteger serial, Date notBefore, Date notAfter, X500Principal subject, PublicKey publicKey)
    {
        this(issuerCert.getSubjectX500Principal(), serial, notBefore, notAfter, subject, publicKey);
    }

    public JcaX509v3CertificateBuilder(X509Certificate issuerCert, BigInteger serial, Date notBefore, Date notAfter, X500Name subject, PublicKey publicKey)
    {
        this(X500Name.getInstance(issuerCert.getSubjectX500Principal().getEncoded()), serial, notBefore, notAfter, subject, publicKey);
    }

    public JcaX509v3CertificateBuilder copyAndAddExtension(ASN1ObjectIdentifier oid, boolean critical, X509Certificate certificate)
        throws CertificateEncodingException
    {
        copyAndAddExtension(oid, critical, ((co.org.bouncy.cert.X509CertificateHolder) (new JcaX509CertificateHolder(certificate))));
        return this;
    }
}
