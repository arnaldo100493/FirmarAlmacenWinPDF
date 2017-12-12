// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OCSPReqGenerator.java

package co.org.bouncy.ocsp;

import co.org.bouncy.asn1.ASN1EncodableVector;
import co.org.bouncy.asn1.ASN1OutputStream;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.DERBitString;
import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.DERObjectIdentifier;
import co.org.bouncy.asn1.DERSequence;
import co.org.bouncy.asn1.ocsp.OCSPRequest;
import co.org.bouncy.asn1.ocsp.Request;
import co.org.bouncy.asn1.ocsp.Signature;
import co.org.bouncy.asn1.ocsp.TBSRequest;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.Extensions;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.asn1.x509.X509CertificateStructure;
import co.org.bouncy.asn1.x509.X509Extensions;
import co.org.bouncy.jce.X509Principal;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package co.org.bouncy.ocsp:
//            OCSPException, OCSPReq, OCSPUtil, CertificateID

/**
 * @deprecated Class OCSPReqGenerator is deprecated
 */

public class OCSPReqGenerator
{
    private class RequestObject
    {

        public Request toRequest()
            throws Exception
        {
            return new Request(certId.toASN1Object(), Extensions.getInstance(extensions));
        }

        CertificateID certId;
        X509Extensions extensions;
        final OCSPReqGenerator this$0;

        public RequestObject(CertificateID certId, X509Extensions extensions)
        {
            this$0 = OCSPReqGenerator.this;
            super();
            this.certId = certId;
            this.extensions = extensions;
        }
    }


    public OCSPReqGenerator()
    {
        list = new ArrayList();
        requestorName = null;
        requestExtensions = null;
    }

    public void addRequest(CertificateID certId)
    {
        list.add(new RequestObject(certId, null));
    }

    public void addRequest(CertificateID certId, X509Extensions singleRequestExtensions)
    {
        list.add(new RequestObject(certId, singleRequestExtensions));
    }

    public void setRequestorName(X500Principal requestorName)
    {
        try
        {
            this.requestorName = new GeneralName(4, new X509Principal(requestorName.getEncoded()));
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("cannot encode principal: ").append(e).toString());
        }
    }

    public void setRequestorName(GeneralName requestorName)
    {
        this.requestorName = requestorName;
    }

    public void setRequestExtensions(X509Extensions requestExtensions)
    {
        this.requestExtensions = requestExtensions;
    }

    private OCSPReq generateRequest(DERObjectIdentifier signingAlgorithm, PrivateKey key, X509Certificate chain[], String provider, SecureRandom random)
        throws OCSPException, NoSuchProviderException
    {
        Iterator it = list.iterator();
        ASN1EncodableVector requests = new ASN1EncodableVector();
        while(it.hasNext()) 
            try
            {
                requests.add(((RequestObject)it.next()).toRequest());
            }
            catch(Exception e)
            {
                throw new OCSPException("exception creating Request", e);
            }
        TBSRequest tbsReq = new TBSRequest(requestorName, new DERSequence(requests), requestExtensions);
        java.security.Signature sig = null;
        Signature signature = null;
        if(signingAlgorithm != null)
        {
            if(requestorName == null)
                throw new OCSPException("requestorName must be specified if request is signed.");
            try
            {
                sig = OCSPUtil.createSignatureInstance(signingAlgorithm.getId(), provider);
                if(random != null)
                    sig.initSign(key, random);
                else
                    sig.initSign(key);
            }
            catch(NoSuchProviderException e)
            {
                throw e;
            }
            catch(GeneralSecurityException e)
            {
                throw new OCSPException((new StringBuilder()).append("exception creating signature: ").append(e).toString(), e);
            }
            DERBitString bitSig = null;
            try
            {
                ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                ASN1OutputStream aOut = new ASN1OutputStream(bOut);
                aOut.writeObject(tbsReq);
                sig.update(bOut.toByteArray());
                bitSig = new DERBitString(sig.sign());
            }
            catch(Exception e)
            {
                throw new OCSPException((new StringBuilder()).append("exception processing TBSRequest: ").append(e).toString(), e);
            }
            AlgorithmIdentifier sigAlgId = new AlgorithmIdentifier(signingAlgorithm, DERNull.INSTANCE);
            if(chain != null && chain.length > 0)
            {
                ASN1EncodableVector v = new ASN1EncodableVector();
                try
                {
                    for(int i = 0; i != chain.length; i++)
                        v.add(new X509CertificateStructure((ASN1Sequence)ASN1Primitive.fromByteArray(chain[i].getEncoded())));

                }
                catch(IOException e)
                {
                    throw new OCSPException("error processing certs", e);
                }
                catch(CertificateEncodingException e)
                {
                    throw new OCSPException("error encoding certs", e);
                }
                signature = new Signature(sigAlgId, bitSig, new DERSequence(v));
            } else
            {
                signature = new Signature(sigAlgId, bitSig);
            }
        }
        return new OCSPReq(new OCSPRequest(tbsReq, signature));
    }

    public OCSPReq generate()
        throws OCSPException
    {
        try
        {
            return generateRequest(null, null, null, null, null);
        }
        catch(NoSuchProviderException e)
        {
            throw new OCSPException((new StringBuilder()).append("no provider! - ").append(e).toString(), e);
        }
    }

    public OCSPReq generate(String signingAlgorithm, PrivateKey key, X509Certificate chain[], String provider)
        throws OCSPException, NoSuchProviderException, IllegalArgumentException
    {
        return generate(signingAlgorithm, key, chain, provider, null);
    }

    public OCSPReq generate(String signingAlgorithm, PrivateKey key, X509Certificate chain[], String provider, SecureRandom random)
        throws OCSPException, NoSuchProviderException, IllegalArgumentException
    {
        if(signingAlgorithm == null)
            throw new IllegalArgumentException("no signing algorithm specified");
        try
        {
            DERObjectIdentifier oid = OCSPUtil.getAlgorithmOID(signingAlgorithm);
            return generateRequest(oid, key, chain, provider, random);
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("unknown signing algorithm specified: ").append(signingAlgorithm).toString());
        }
    }

    public Iterator getSignatureAlgNames()
    {
        return OCSPUtil.getAlgNames();
    }

    private List list;
    private GeneralName requestorName;
    private X509Extensions requestExtensions;
}
