// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509CRLHolder.java

package co.org.bouncy.cert;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.operator.ContentVerifier;
import co.org.bouncy.operator.ContentVerifierProvider;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

// Referenced classes of package co.org.bouncy.cert:
//            CertIOException, X509CRLEntryHolder, CertException, CertUtils

public class X509CRLHolder
{

    private static CertificateList parseStream(InputStream stream)
        throws IOException
    {
        try
        {
            return CertificateList.getInstance((new ASN1InputStream(stream, true)).readObject());
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

    private static boolean isIndirectCRL(Extensions extensions)
    {
        if(extensions == null)
        {
            return false;
        } else
        {
            Extension ext = extensions.getExtension(Extension.issuingDistributionPoint);
            return ext != null && IssuingDistributionPoint.getInstance(ext.getParsedValue()).isIndirectCRL();
        }
    }

    public X509CRLHolder(byte crlEncoding[])
        throws IOException
    {
        this(parseStream(new ByteArrayInputStream(crlEncoding)));
    }

    public X509CRLHolder(InputStream crlStream)
        throws IOException
    {
        this(parseStream(crlStream));
    }

    public X509CRLHolder(CertificateList x509CRL)
    {
        this.x509CRL = x509CRL;
        extensions = x509CRL.getTBSCertList().getExtensions();
        isIndirect = isIndirectCRL(extensions);
        issuerName = new GeneralNames(new GeneralName(x509CRL.getIssuer()));
    }

    public byte[] getEncoded()
        throws IOException
    {
        return x509CRL.getEncoded();
    }

    public X500Name getIssuer()
    {
        return X500Name.getInstance(x509CRL.getIssuer());
    }

    public X509CRLEntryHolder getRevokedCertificate(BigInteger serialNumber)
    {
        GeneralNames currentCA = issuerName;
        Enumeration en = x509CRL.getRevokedCertificateEnumeration();
        do
        {
            if(!en.hasMoreElements())
                break;
            co.org.bouncy.asn1.x509.TBSCertList.CRLEntry entry = (co.org.bouncy.asn1.x509.TBSCertList.CRLEntry)en.nextElement();
            if(entry.getUserCertificate().getValue().equals(serialNumber))
                return new X509CRLEntryHolder(entry, isIndirect, currentCA);
            if(isIndirect && entry.hasExtensions())
            {
                Extension currentCaName = entry.getExtensions().getExtension(Extension.certificateIssuer);
                if(currentCaName != null)
                    currentCA = GeneralNames.getInstance(currentCaName.getParsedValue());
            }
        } while(true);
        return null;
    }

    public Collection getRevokedCertificates()
    {
        co.org.bouncy.asn1.x509.TBSCertList.CRLEntry entries[] = x509CRL.getRevokedCertificates();
        List l = new ArrayList(entries.length);
        GeneralNames currentCA = issuerName;
        for(Enumeration en = x509CRL.getRevokedCertificateEnumeration(); en.hasMoreElements();)
        {
            co.org.bouncy.asn1.x509.TBSCertList.CRLEntry entry = (co.org.bouncy.asn1.x509.TBSCertList.CRLEntry)en.nextElement();
            X509CRLEntryHolder crlEntry = new X509CRLEntryHolder(entry, isIndirect, currentCA);
            l.add(crlEntry);
            currentCA = crlEntry.getCertificateIssuer();
        }

        return l;
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

    public CertificateList toASN1Structure()
    {
        return x509CRL;
    }

    public boolean isSignatureValid(ContentVerifierProvider verifierProvider)
        throws CertException
    {
        TBSCertList tbsCRL = x509CRL.getTBSCertList();
        if(!CertUtils.isAlgIdEqual(tbsCRL.getSignature(), x509CRL.getSignatureAlgorithm()))
            throw new CertException("signature invalid - algorithm identifier mismatch");
        ContentVerifier verifier;
        try
        {
            verifier = verifierProvider.get(tbsCRL.getSignature());
            OutputStream sOut = verifier.getOutputStream();
            DEROutputStream dOut = new DEROutputStream(sOut);
            dOut.writeObject(tbsCRL);
            sOut.close();
        }
        catch(Exception e)
        {
            throw new CertException((new StringBuilder()).append("unable to process signature: ").append(e.getMessage()).toString(), e);
        }
        return verifier.verify(x509CRL.getSignature().getBytes());
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(!(o instanceof X509CRLHolder))
        {
            return false;
        } else
        {
            X509CRLHolder other = (X509CRLHolder)o;
            return x509CRL.equals(other.x509CRL);
        }
    }

    public int hashCode()
    {
        return x509CRL.hashCode();
    }

    private CertificateList x509CRL;
    private boolean isIndirect;
    private Extensions extensions;
    private GeneralNames issuerName;
}
