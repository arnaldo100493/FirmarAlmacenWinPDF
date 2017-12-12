// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OCSPReq.java

package co.org.bouncy.cert.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.ocsp.*;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.cert.CertIOException;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.ContentVerifier;
import co.org.bouncy.operator.ContentVerifierProvider;
import java.io.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

// Referenced classes of package co.org.bouncy.cert.ocsp:
//            Req, OCSPException, OCSPUtils

public class OCSPReq
{

    public OCSPReq(OCSPRequest req)
    {
        this.req = req;
        extensions = req.getTbsRequest().getRequestExtensions();
    }

    public OCSPReq(byte req[])
        throws IOException
    {
        this(new ASN1InputStream(req));
    }

    private OCSPReq(ASN1InputStream aIn)
        throws IOException
    {
        try
        {
            req = OCSPRequest.getInstance(aIn.readObject());
            if(req == null)
                throw new CertIOException("malformed request: no request data found");
            extensions = req.getTbsRequest().getRequestExtensions();
        }
        catch(IllegalArgumentException e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed request: ").append(e.getMessage()).toString(), e);
        }
        catch(ClassCastException e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed request: ").append(e.getMessage()).toString(), e);
        }
        catch(ASN1Exception e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed request: ").append(e.getMessage()).toString(), e);
        }
    }

    public int getVersionNumber()
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
        if(!isSigned())
            return null;
        else
            return req.getOptionalSignature().getSignatureAlgorithm().getAlgorithm();
    }

    public byte[] getSignature()
    {
        if(!isSigned())
            return null;
        else
            return req.getOptionalSignature().getSignature().getBytes();
    }

    public X509CertificateHolder[] getCerts()
    {
        if(req.getOptionalSignature() != null)
        {
            ASN1Sequence s = req.getOptionalSignature().getCerts();
            if(s != null)
            {
                X509CertificateHolder certs[] = new X509CertificateHolder[s.size()];
                for(int i = 0; i != certs.length; i++)
                    certs[i] = new X509CertificateHolder(Certificate.getInstance(s.getObjectAt(i)));

                return certs;
            } else
            {
                return EMPTY_CERTS;
            }
        } else
        {
            return EMPTY_CERTS;
        }
    }

    public boolean isSigned()
    {
        return req.getOptionalSignature() != null;
    }

    public boolean isSignatureValid(ContentVerifierProvider verifierProvider)
        throws OCSPException
    {
        if(!isSigned())
            throw new OCSPException("attempt to verify signature on unsigned object");
        try
        {
            ContentVerifier verifier = verifierProvider.get(req.getOptionalSignature().getSignatureAlgorithm());
            OutputStream sOut = verifier.getOutputStream();
            sOut.write(req.getTbsRequest().getEncoded("DER"));
            return verifier.verify(getSignature());
        }
        catch(Exception e)
        {
            throw new OCSPException((new StringBuilder()).append("exception processing signature: ").append(e).toString(), e);
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

    private static final X509CertificateHolder EMPTY_CERTS[] = new X509CertificateHolder[0];
    private OCSPRequest req;
    private Extensions extensions;

}
