// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSUtils.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.ocsp.OCSPResponse;
import co.org.bouncy.asn1.ocsp.OCSPResponseStatus;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.cert.*;
import co.org.bouncy.operator.DigestCalculator;
import co.org.bouncy.util.Store;
import co.org.bouncy.util.io.*;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSException, SignerInfoGenerator, NullOutputStream

class CMSUtils
{

    CMSUtils()
    {
    }

    static ContentInfo readContentInfo(byte input[])
        throws CMSException
    {
        return readContentInfo(new ASN1InputStream(input));
    }

    static ContentInfo readContentInfo(InputStream input)
        throws CMSException
    {
        return readContentInfo(new ASN1InputStream(input));
    }

    static List getCertificatesFromStore(CertStore certStore)
        throws CertStoreException, CMSException
    {
        List certs = new ArrayList();
        try
        {
            X509Certificate c;
            for(Iterator it = certStore.getCertificates(null).iterator(); it.hasNext(); certs.add(Certificate.getInstance(ASN1Primitive.fromByteArray(c.getEncoded()))))
                c = (X509Certificate)it.next();

            return certs;
        }
        catch(IllegalArgumentException e)
        {
            throw new CMSException("error processing certs", e);
        }
        catch(IOException e)
        {
            throw new CMSException("error processing certs", e);
        }
        catch(CertificateEncodingException e)
        {
            throw new CMSException("error encoding certs", e);
        }
    }

    static List getCertificatesFromStore(Store certStore)
        throws CMSException
    {
        List certs = new ArrayList();
        try
        {
            X509CertificateHolder c;
            for(Iterator it = certStore.getMatches(null).iterator(); it.hasNext(); certs.add(c.toASN1Structure()))
                c = (X509CertificateHolder)it.next();

            return certs;
        }
        catch(ClassCastException e)
        {
            throw new CMSException("error processing certs", e);
        }
    }

    static List getAttributeCertificatesFromStore(Store attrStore)
        throws CMSException
    {
        List certs = new ArrayList();
        try
        {
            X509AttributeCertificateHolder attrCert;
            for(Iterator it = attrStore.getMatches(null).iterator(); it.hasNext(); certs.add(new DERTaggedObject(false, 2, attrCert.toASN1Structure())))
                attrCert = (X509AttributeCertificateHolder)it.next();

            return certs;
        }
        catch(ClassCastException e)
        {
            throw new CMSException("error processing certs", e);
        }
    }

    static List getCRLsFromStore(CertStore certStore)
        throws CertStoreException, CMSException
    {
        List crls = new ArrayList();
        try
        {
            X509CRL c;
            for(Iterator it = certStore.getCRLs(null).iterator(); it.hasNext(); crls.add(CertificateList.getInstance(ASN1Primitive.fromByteArray(c.getEncoded()))))
                c = (X509CRL)it.next();

            return crls;
        }
        catch(IllegalArgumentException e)
        {
            throw new CMSException("error processing crls", e);
        }
        catch(IOException e)
        {
            throw new CMSException("error processing crls", e);
        }
        catch(CRLException e)
        {
            throw new CMSException("error encoding crls", e);
        }
    }

    static List getCRLsFromStore(Store crlStore)
        throws CMSException
    {
        List certs = new ArrayList();
        try
        {
            X509CRLHolder c;
            for(Iterator it = crlStore.getMatches(null).iterator(); it.hasNext(); certs.add(c.toASN1Structure()))
                c = (X509CRLHolder)it.next();

            return certs;
        }
        catch(ClassCastException e)
        {
            throw new CMSException("error processing certs", e);
        }
    }

    static Collection getOthersFromStore(ASN1ObjectIdentifier otherRevocationInfoFormat, Store otherRevocationInfos)
    {
        List others = new ArrayList();
        ASN1Encodable info;
        for(Iterator it = otherRevocationInfos.getMatches(null).iterator(); it.hasNext(); others.add(new DERTaggedObject(false, 1, new OtherRevocationInfoFormat(otherRevocationInfoFormat, info))))
        {
            info = (ASN1Encodable)it.next();
            if(!CMSObjectIdentifiers.id_ri_ocsp_response.equals(otherRevocationInfoFormat))
                continue;
            OCSPResponse resp = OCSPResponse.getInstance(info);
            if(resp.getResponseStatus().getValue().intValue() != 0)
                throw new IllegalArgumentException("cannot add unsuccessful OCSP response to CMS SignedData");
        }

        return others;
    }

    static ASN1Set createBerSetFromList(List derObjects)
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(Iterator it = derObjects.iterator(); it.hasNext(); v.add((ASN1Encodable)it.next()));
        return new BERSet(v);
    }

    static ASN1Set createDerSetFromList(List derObjects)
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(Iterator it = derObjects.iterator(); it.hasNext(); v.add((ASN1Encodable)it.next()));
        return new DERSet(v);
    }

    static OutputStream createBEROctetOutputStream(OutputStream s, int tagNo, boolean isExplicit, int bufferSize)
        throws IOException
    {
        BEROctetStringGenerator octGen = new BEROctetStringGenerator(s, tagNo, isExplicit);
        if(bufferSize != 0)
            return octGen.getOctetOutputStream(new byte[bufferSize]);
        else
            return octGen.getOctetOutputStream();
    }

    static TBSCertificate getTBSCertificateStructure(X509Certificate cert)
    {
        try
        {
            return TBSCertificate.getInstance(ASN1Primitive.fromByteArray(cert.getTBSCertificate()));
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("can't extract TBS structure from this cert");
        }
    }

    static IssuerAndSerialNumber getIssuerAndSerialNumber(X509Certificate cert)
    {
        TBSCertificate tbsCert = getTBSCertificateStructure(cert);
        return new IssuerAndSerialNumber(tbsCert.getIssuer(), tbsCert.getSerialNumber().getValue());
    }

    private static ContentInfo readContentInfo(ASN1InputStream in)
        throws CMSException
    {
        try
        {
            return ContentInfo.getInstance(in.readObject());
        }
        catch(IOException e)
        {
            throw new CMSException("IOException reading content.", e);
        }
        catch(ClassCastException e)
        {
            throw new CMSException("Malformed content.", e);
        }
        catch(IllegalArgumentException e)
        {
            throw new CMSException("Malformed content.", e);
        }
    }

    public static byte[] streamToByteArray(InputStream in)
        throws IOException
    {
        return Streams.readAll(in);
    }

    public static byte[] streamToByteArray(InputStream in, int limit)
        throws IOException
    {
        return Streams.readAllLimited(in, limit);
    }

    public static Provider getProvider(String providerName)
        throws NoSuchProviderException
    {
        if(providerName != null)
        {
            Provider prov = Security.getProvider(providerName);
            if(prov != null)
                return prov;
            else
                throw new NoSuchProviderException((new StringBuilder()).append("provider ").append(providerName).append(" not found.").toString());
        } else
        {
            return null;
        }
    }

    static InputStream attachDigestsToInputStream(Collection digests, InputStream s)
    {
        InputStream result = s;
        for(Iterator it = digests.iterator(); it.hasNext();)
        {
            DigestCalculator digest = (DigestCalculator)it.next();
            result = new TeeInputStream(result, digest.getOutputStream());
        }

        return result;
    }

    static OutputStream attachSignersToOutputStream(Collection signers, OutputStream s)
    {
        OutputStream result = s;
        for(Iterator it = signers.iterator(); it.hasNext();)
        {
            SignerInfoGenerator signerGen = (SignerInfoGenerator)it.next();
            result = getSafeTeeOutputStream(result, signerGen.getCalculatingOutputStream());
        }

        return result;
    }

    static OutputStream getSafeOutputStream(OutputStream s)
    {
        return ((OutputStream) (s != null ? s : new NullOutputStream()));
    }

    static OutputStream getSafeTeeOutputStream(OutputStream s1, OutputStream s2)
    {
        return ((OutputStream) (s1 != null ? s2 != null ? new TeeOutputStream(s1, s2) : getSafeOutputStream(s1) : getSafeOutputStream(s2)));
    }
}
