// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509CertificateHolder.java

package co.org.bouncy.cert;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.operator.ContentVerifier;
import co.org.bouncy.operator.ContentVerifierProvider;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.*;

// Referenced classes of package co.org.bouncy.cert:
//            CertIOException, CertException, CertUtils

public class X509CertificateHolder
{

    private static Certificate parseBytes(byte certEncoding[])
        throws IOException
    {
        try
        {
            return Certificate.getInstance(ASN1Primitive.fromByteArray(certEncoding));
        }
        catch(ClassCastException e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
        catch(IllegalArgumentException e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
    }

    public X509CertificateHolder(byte certEncoding[])
        throws IOException
    {
        this(parseBytes(certEncoding));
    }

    public X509CertificateHolder(Certificate x509Certificate)
    {
        this.x509Certificate = x509Certificate;
        extensions = x509Certificate.getTBSCertificate().getExtensions();
    }

    public int getVersionNumber()
    {
        return x509Certificate.getVersionNumber();
    }

    /**
     * @deprecated Method getVersion is deprecated
     */

    public int getVersion()
    {
        return x509Certificate.getVersionNumber();
    }

    public boolean hasExtensions()
    {
        return extensions != null;
    }

    public Extension getExtension(ASN1ObjectIdentifier oid)
    {
        if(extensions != null)
            return extensions.getExtension(oid);
        else
            return null;
    }

    public Extensions getExtensions()
    {
        return extensions;
    }

    public List getExtensionOIDs()
    {
        return CertUtils.getExtensionOIDs(extensions);
    }

    public Set getCriticalExtensionOIDs()
    {
        return CertUtils.getCriticalExtensionOIDs(extensions);
    }

    public Set getNonCriticalExtensionOIDs()
    {
        return CertUtils.getNonCriticalExtensionOIDs(extensions);
    }

    public BigInteger getSerialNumber()
    {
        return x509Certificate.getSerialNumber().getValue();
    }

    public X500Name getIssuer()
    {
        return X500Name.getInstance(x509Certificate.getIssuer());
    }

    public X500Name getSubject()
    {
        return X500Name.getInstance(x509Certificate.getSubject());
    }

    public Date getNotBefore()
    {
        return x509Certificate.getStartDate().getDate();
    }

    public Date getNotAfter()
    {
        return x509Certificate.getEndDate().getDate();
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo()
    {
        return x509Certificate.getSubjectPublicKeyInfo();
    }

    public Certificate toASN1Structure()
    {
        return x509Certificate;
    }

    public AlgorithmIdentifier getSignatureAlgorithm()
    {
        return x509Certificate.getSignatureAlgorithm();
    }

    public byte[] getSignature()
    {
        return x509Certificate.getSignature().getBytes();
    }

    public boolean isValidOn(Date date)
    {
        return !date.before(x509Certificate.getStartDate().getDate()) && !date.after(x509Certificate.getEndDate().getDate());
    }

    public boolean isSignatureValid(ContentVerifierProvider verifierProvider)
        throws CertException
    {
        TBSCertificate tbsCert = x509Certificate.getTBSCertificate();
        if(!CertUtils.isAlgIdEqual(tbsCert.getSignature(), x509Certificate.getSignatureAlgorithm()))
            throw new CertException("signature invalid - algorithm identifier mismatch");
        ContentVerifier verifier;
        try
        {
            verifier = verifierProvider.get(tbsCert.getSignature());
            OutputStream sOut = verifier.getOutputStream();
            DEROutputStream dOut = new DEROutputStream(sOut);
            dOut.writeObject(tbsCert);
            sOut.close();
        }
        catch(Exception e)
        {
            throw new CertException((new StringBuilder()).append("unable to process signature: ").append(e.getMessage()).toString(), e);
        }
        return verifier.verify(x509Certificate.getSignature().getBytes());
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(!(o instanceof X509CertificateHolder))
        {
            return false;
        } else
        {
            X509CertificateHolder other = (X509CertificateHolder)o;
            return x509Certificate.equals(other.x509Certificate);
        }
    }

    public int hashCode()
    {
        return x509Certificate.hashCode();
    }

    public byte[] getEncoded()
        throws IOException
    {
        return x509Certificate.getEncoded();
    }

    private Certificate x509Certificate;
    private Extensions extensions;
}
