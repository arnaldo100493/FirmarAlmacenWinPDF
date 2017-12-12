// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PrincipalUtil.java

package co.org.bouncy.jce;

import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.x509.*;
import java.io.IOException;
import java.security.cert.*;

// Referenced classes of package co.org.bouncy.jce:
//            X509Principal

public class PrincipalUtil
{

    public PrincipalUtil()
    {
    }

    public static X509Principal getIssuerX509Principal(X509Certificate cert)
        throws CertificateEncodingException
    {
        try
        {
            TBSCertificateStructure tbsCert = TBSCertificateStructure.getInstance(ASN1Primitive.fromByteArray(cert.getTBSCertificate()));
            return new X509Principal(X509Name.getInstance(tbsCert.getIssuer()));
        }
        catch(IOException e)
        {
            throw new CertificateEncodingException(e.toString());
        }
    }

    public static X509Principal getSubjectX509Principal(X509Certificate cert)
        throws CertificateEncodingException
    {
        try
        {
            TBSCertificateStructure tbsCert = TBSCertificateStructure.getInstance(ASN1Primitive.fromByteArray(cert.getTBSCertificate()));
            return new X509Principal(X509Name.getInstance(tbsCert.getSubject()));
        }
        catch(IOException e)
        {
            throw new CertificateEncodingException(e.toString());
        }
    }

    public static X509Principal getIssuerX509Principal(X509CRL crl)
        throws CRLException
    {
        try
        {
            TBSCertList tbsCertList = TBSCertList.getInstance(ASN1Primitive.fromByteArray(crl.getTBSCertList()));
            return new X509Principal(X509Name.getInstance(tbsCertList.getIssuer()));
        }
        catch(IOException e)
        {
            throw new CRLException(e.toString());
        }
    }
}
