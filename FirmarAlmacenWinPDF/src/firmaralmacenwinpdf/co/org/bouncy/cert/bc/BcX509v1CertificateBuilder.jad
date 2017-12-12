// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcX509v1CertificateBuilder.java

package co.org.bouncy.cert.bc;

import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.cert.X509v1CertificateBuilder;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.util.SubjectPublicKeyInfoFactory;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;

public class BcX509v1CertificateBuilder extends X509v1CertificateBuilder
{

    public BcX509v1CertificateBuilder(X500Name issuer, BigInteger serial, Date notBefore, Date notAfter, X500Name subject, AsymmetricKeyParameter publicKey)
        throws IOException
    {
        super(issuer, serial, notBefore, notAfter, subject, SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(publicKey));
    }
}
