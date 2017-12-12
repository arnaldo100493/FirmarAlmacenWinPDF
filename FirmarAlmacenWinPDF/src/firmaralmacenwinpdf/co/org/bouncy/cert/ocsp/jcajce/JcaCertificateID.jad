// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaCertificateID.java

package co.org.bouncy.cert.ocsp.jcajce;

import co.org.bouncy.cert.jcajce.JcaX509CertificateHolder;
import co.org.bouncy.cert.ocsp.CertificateID;
import co.org.bouncy.cert.ocsp.OCSPException;
import co.org.bouncy.operator.DigestCalculator;
import java.math.BigInteger;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

public class JcaCertificateID extends CertificateID
{

    public JcaCertificateID(DigestCalculator digestCalculator, X509Certificate issuerCert, BigInteger number)
        throws OCSPException, CertificateEncodingException
    {
        super(digestCalculator, new JcaX509CertificateHolder(issuerCert), number);
    }
}
