// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OCSPReq.java

package co.org.bouncy.ocsp;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.ASN1OutputStream;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.DERBitString;
import co.org.bouncy.asn1.ocsp.OCSPRequest;
import co.org.bouncy.asn1.ocsp.Request;
import co.org.bouncy.asn1.ocsp.Signature;
import co.org.bouncy.asn1.ocsp.TBSRequest;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.asn1.x509.X509Extensions;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.cert.CertStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.security.cert.X509Extension;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Referenced classes of package co.org.bouncy.ocsp:
//            OCSPException, Req, OCSPUtil

/**
 * @deprecated Class OCSPReq is deprecated
 */

public class OCSPReq
    implements X509Extension
{

    public OCSPReq(OCSPRequest req)
    {
        this.req = req;
    }

    public OCSPReq(byte req[])
        throws IOException
    {
        this(new ASN1InputStream(req));
    }

    public OCSPReq(InputStream in)
        throws IOException
    {
        this(new ASN1InputStream(in));
    }

    private OCSPReq(ASN1InputStream aIn)
        throws IOException
    {
        try
        {
            req = OCSPRequest.getInstance(aIn.readObject());
        }
        catch(IllegalArgumentException e)
        {
            throw new IOException((new StringBuilder()).append("malformed request: ").append(e.getMessage()).toString());
        }
        catch(ClassCastException e)
        {
            throw new IOException((new StringBuilder()).append("malformed request: ").append(e.getMessage()).toString());
        }
    }

    public byte[] getTBSRequest()
        throws OCSPException
    {
        try
        {
            return req.getTbsRequest().getEncoded();
        }
        catch(IOException e)
        {
            throw new OCSPException("problem encoding tbsRequest", e);
        }
    }

    public int getVersion()
    {
        return req.getTbsRequest().getVersion().getValue().intValue() + 1;
    }

    public GeneralName getRequestorName()
    {
        return GeneralName.getInstance(req.getTbsRequest().getRequestorName());
    }

    public Req[] getRequestList()
    {
        ASN1Sequence seq = req.getTbsRequest().getRequestList();
        Req requests[] = new Req[seq.size()];
        for(int i = 0; i != requests.length; i++)
            requests[i] = new Req(Request.getInstance(seq.getObjectAt(i)));

        return requests;
    }

    public X509Extensions getRequestExtensions()
    {
        return X509Extensions.getInstance(req.getTbsRequest().getRequestExtensions());
    }

    public String getSignatureAlgOID()
    {
        if(!isSigned())
            return null;
        else
            return req.getOptionalSignature().getSignatureAlgorithm().getObjectId().getId();
    }

    public byte[] getSignature()
    {
        if(!isSigned())
            return null;
        else
            return req.getOptionalSignature().getSignature().getBytes();
    }

    private List getCertList(String provider)
        throws OCSPException, NoSuchProviderException
    {
        List certs = new ArrayList();
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ASN1OutputStream aOut = new ASN1OutputStream(bOut);
        CertificateFactory cf;
        try
        {
            cf = OCSPUtil.createX509CertificateFactory(provider);
        }
        catch(CertificateException ex)
        {
            throw new OCSPException("can't get certificate factory.", ex);
        }
        ASN1Sequence s = req.getOptionalSignature().getCerts();
        if(s != null)
        {
            for(Enumeration e = s.getObjects(); e.hasMoreElements(); bOut.reset())
                try
                {
                    aOut.writeObject((ASN1Encodable)e.nextElement());
                    certs.add(cf.generateCertificate(new ByteArrayInputStream(bOut.toByteArray())));
                }
                catch(IOException ex)
                {
                    throw new OCSPException("can't re-encode certificate!", ex);
                }
                catch(CertificateException ex)
                {
                    throw new OCSPException("can't re-encode certificate!", ex);
                }

        }
        return certs;
    }

    public X509Certificate[] getCerts(String provider)
        throws OCSPException, NoSuchProviderException
    {
        if(!isSigned())
        {
            return null;
        } else
        {
            List certs = getCertList(provider);
            return (X509Certificate[])(X509Certificate[])certs.toArray(new X509Certificate[certs.size()]);
        }
    }

    public CertStore getCertificates(String type, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, OCSPException
    {
        if(!isSigned())
            return null;
        try
        {
            java.security.cert.CertStoreParameters params = new CollectionCertStoreParameters(getCertList(provider));
            return OCSPUtil.createCertStoreInstance(type, params, provider);
        }
        catch(InvalidAlgorithmParameterException e)
        {
            throw new OCSPException("can't setup the CertStore", e);
        }
    }

    public boolean isSigned()
    {
        return req.getOptionalSignature() != null;
    }

    public boolean verify(PublicKey key, String sigProvider)
        throws OCSPException, NoSuchProviderException
    {
        if(!isSigned())
            throw new OCSPException("attempt to verify signature on unsigned object");
        try
        {
            java.security.Signature signature = OCSPUtil.createSignatureInstance(getSignatureAlgOID(), sigProvider);
            signature.initVerify(key);
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            ASN1OutputStream aOut = new ASN1OutputStream(bOut);
            aOut.writeObject(req.getTbsRequest());
            signature.update(bOut.toByteArray());
            return signature.verify(getSignature());
        }
        catch(NoSuchProviderException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new OCSPException((new StringBuilder()).append("exception processing sig: ").append(e).toString(), e);
        }
    }

    public byte[] getEncoded()
        throws IOException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ASN1OutputStream aOut = new ASN1OutputStream(bOut);
        aOut.writeObject(req);
        return bOut.toByteArray();
    }

    public boolean hasUnsupportedCriticalExtension()
    {
        Set extns = getCriticalExtensionOIDs();
        return extns != null && !extns.isEmpty();
    }

    private Set getExtensionOIDs(boolean critical)
    {
        Set set = new HashSet();
        X509Extensions extensions = getRequestExtensions();
        if(extensions != null)
        {
            Enumeration e = extensions.oids();
            do
            {
                if(!e.hasMoreElements())
                    break;
                ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier)e.nextElement();
                co.org.bouncy.asn1.x509.X509Extension ext = extensions.getExtension(oid);
                if(critical == ext.isCritical())
                    set.add(oid.getId());
            } while(true);
        }
        return set;
    }

    public Set getCriticalExtensionOIDs()
    {
        return getExtensionOIDs(true);
    }

    public Set getNonCriticalExtensionOIDs()
    {
        return getExtensionOIDs(false);
    }

    public byte[] getExtensionValue(String oid)
    {
        X509Extensions exts = getRequestExtensions();
        if(exts != null)
        {
            co.org.bouncy.asn1.x509.X509Extension ext = exts.getExtension(new ASN1ObjectIdentifier(oid));
            if(ext != null)
                try
                {
                    return ext.getValue().getEncoded("DER");
                }
                catch(Exception e)
                {
                    throw new RuntimeException((new StringBuilder()).append("error encoding ").append(e.toString()).toString());
                }
        }
        return null;
    }

    private OCSPRequest req;
}
