// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasicOCSPResp.java

package co.org.bouncy.ocsp;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1GeneralizedTime;
import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.ASN1OutputStream;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.DERBitString;
import co.org.bouncy.asn1.DERObjectIdentifier;
import co.org.bouncy.asn1.ocsp.BasicOCSPResponse;
import co.org.bouncy.asn1.ocsp.ResponseData;
import co.org.bouncy.asn1.ocsp.SingleResponse;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.X509Extensions;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.security.cert.X509Extension;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Referenced classes of package co.org.bouncy.ocsp:
//            OCSPException, RespID, SingleResp, RespData, 
//            OCSPUtil

/**
 * @deprecated Class BasicOCSPResp is deprecated
 */

public class BasicOCSPResp
    implements X509Extension
{

    public BasicOCSPResp(BasicOCSPResponse resp)
    {
        chain = null;
        this.resp = resp;
        data = resp.getTbsResponseData();
    }

    public byte[] getTBSResponseData()
        throws OCSPException
    {
        try
        {
            return resp.getTbsResponseData().getEncoded();
        }
        catch(IOException e)
        {
            throw new OCSPException("problem encoding tbsResponseData", e);
        }
    }

    public int getVersion()
    {
        return data.getVersion().getValue().intValue() + 1;
    }

    public RespID getResponderId()
    {
        return new RespID(data.getResponderID());
    }

    public Date getProducedAt()
    {
        try
        {
            return data.getProducedAt().getDate();
        }
        catch(ParseException e)
        {
            throw new IllegalStateException((new StringBuilder()).append("ParseException:").append(e.getMessage()).toString());
        }
    }

    public SingleResp[] getResponses()
    {
        ASN1Sequence s = data.getResponses();
        SingleResp rs[] = new SingleResp[s.size()];
        for(int i = 0; i != rs.length; i++)
            rs[i] = new SingleResp(SingleResponse.getInstance(s.getObjectAt(i)));

        return rs;
    }

    public X509Extensions getResponseExtensions()
    {
        return X509Extensions.getInstance(data.getResponseExtensions());
    }

    public boolean hasUnsupportedCriticalExtension()
    {
        Set extns = getCriticalExtensionOIDs();
        return extns != null && !extns.isEmpty();
    }

    private Set getExtensionOIDs(boolean critical)
    {
        Set set = new HashSet();
        X509Extensions extensions = getResponseExtensions();
        if(extensions != null)
        {
            Enumeration e = extensions.oids();
            do
            {
                if(!e.hasMoreElements())
                    break;
                DERObjectIdentifier oid = (DERObjectIdentifier)e.nextElement();
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
        X509Extensions exts = getResponseExtensions();
        if(exts != null)
        {
            co.org.bouncy.asn1.x509.X509Extension ext = exts.getExtension(new DERObjectIdentifier(oid));
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

    public String getSignatureAlgName()
    {
        return OCSPUtil.getAlgorithmName(resp.getSignatureAlgorithm().getObjectId());
    }

    public String getSignatureAlgOID()
    {
        return resp.getSignatureAlgorithm().getObjectId().getId();
    }

    /**
     * @deprecated Method getResponseData is deprecated
     */

    public RespData getResponseData()
    {
        return new RespData(resp.getTbsResponseData());
    }

    public byte[] getSignature()
    {
        return resp.getSignature().getBytes();
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
        ASN1Sequence s = resp.getCerts();
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
        List certs = getCertList(provider);
        return (X509Certificate[])(X509Certificate[])certs.toArray(new X509Certificate[certs.size()]);
    }

    public CertStore getCertificates(String type, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, OCSPException
    {
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

    public boolean verify(PublicKey key, String sigProvider)
        throws OCSPException, NoSuchProviderException
    {
        try
        {
            Signature signature = OCSPUtil.createSignatureInstance(getSignatureAlgName(), sigProvider);
            signature.initVerify(key);
            signature.update(resp.getTbsResponseData().getEncoded("DER"));
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
        return resp.getEncoded();
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(!(o instanceof BasicOCSPResp))
        {
            return false;
        } else
        {
            BasicOCSPResp r = (BasicOCSPResp)o;
            return resp.equals(r.resp);
        }
    }

    public int hashCode()
    {
        return resp.hashCode();
    }

    BasicOCSPResponse resp;
    ResponseData data;
    X509Certificate chain[];
}
