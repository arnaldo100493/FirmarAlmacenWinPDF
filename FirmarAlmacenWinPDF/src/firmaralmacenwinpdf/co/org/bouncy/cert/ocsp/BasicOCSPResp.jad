// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasicOCSPResp.java

package co.org.bouncy.cert.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.ocsp.*;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.ContentVerifier;
import co.org.bouncy.operator.ContentVerifierProvider;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.*;

// Referenced classes of package co.org.bouncy.cert.ocsp:
//            RespID, SingleResp, OCSPException, OCSPUtils

public class BasicOCSPResp
{

    public BasicOCSPResp(BasicOCSPResponse resp)
    {
        this.resp = resp;
        data = resp.getTbsResponseData();
        extensions = Extensions.getInstance(resp.getTbsResponseData().getResponseExtensions());
    }

    public byte[] getTBSResponseData()
    {
        try
        {
            return resp.getTbsResponseData().getEncoded("DER");
        }
        catch(IOException e)
        {
            return null;
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
        return OCSPUtils.extractDate(data.getProducedAt());
    }

    public SingleResp[] getResponses()
    {
        ASN1Sequence s = data.getResponses();
        SingleResp rs[] = new SingleResp[s.size()];
        for(int i = 0; i != rs.length; i++)
            rs[i] = new SingleResp(SingleResponse.getInstance(s.getObjectAt(i)));

        return rs;
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

    public List getExtensionOIDs()
    {
        return OCSPUtils.getExtensionOIDs(extensions);
    }

    public Set getCriticalExtensionOIDs()
    {
        return OCSPUtils.getCriticalExtensionOIDs(extensions);
    }

    public Set getNonCriticalExtensionOIDs()
    {
        return OCSPUtils.getNonCriticalExtensionOIDs(extensions);
    }

    public ASN1ObjectIdentifier getSignatureAlgOID()
    {
        return resp.getSignatureAlgorithm().getAlgorithm();
    }

    public byte[] getSignature()
    {
        return resp.getSignature().getBytes();
    }

    public X509CertificateHolder[] getCerts()
    {
        if(resp.getCerts() != null)
        {
            ASN1Sequence s = resp.getCerts();
            if(s != null)
            {
                X509CertificateHolder certs[] = new X509CertificateHolder[s.size()];
                for(int i = 0; i != certs.length; i++)
                    certs[i] = new X509CertificateHolder(Certificate.getInstance(s.getObjectAt(i)));

                return certs;
            } else
            {
                return OCSPUtils.EMPTY_CERTS;
            }
        } else
        {
            return OCSPUtils.EMPTY_CERTS;
        }
    }

    public boolean isSignatureValid(ContentVerifierProvider verifierProvider)
        throws OCSPException
    {
        try
        {
            ContentVerifier verifier = verifierProvider.get(resp.getSignatureAlgorithm());
            OutputStream vOut = verifier.getOutputStream();
            vOut.write(resp.getTbsResponseData().getEncoded("DER"));
            vOut.close();
            return verifier.verify(getSignature());
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

    private BasicOCSPResponse resp;
    private ResponseData data;
    private Extensions extensions;
}
