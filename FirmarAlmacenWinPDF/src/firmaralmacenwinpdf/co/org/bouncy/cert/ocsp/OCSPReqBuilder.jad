// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OCSPReqBuilder.java

package co.org.bouncy.cert.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.ocsp.*;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.ContentSigner;
import java.io.OutputStream;
import java.util.*;

// Referenced classes of package co.org.bouncy.cert.ocsp:
//            OCSPException, OCSPReq, CertificateID

public class OCSPReqBuilder
{
    private class RequestObject
    {

        public Request toRequest()
            throws Exception
        {
            return new Request(certId.toASN1Object(), extensions);
        }

        CertificateID certId;
        Extensions extensions;
        final OCSPReqBuilder this$0;

        public RequestObject(CertificateID certId, Extensions extensions)
        {
            this$0 = OCSPReqBuilder.this;
            super();
            this.certId = certId;
            this.extensions = extensions;
        }
    }


    public OCSPReqBuilder()
    {
        list = new ArrayList();
        requestorName = null;
        requestExtensions = null;
    }

    public OCSPReqBuilder addRequest(CertificateID certId)
    {
        list.add(new RequestObject(certId, null));
        return this;
    }

    public OCSPReqBuilder addRequest(CertificateID certId, Extensions singleRequestExtensions)
    {
        list.add(new RequestObject(certId, singleRequestExtensions));
        return this;
    }

    public OCSPReqBuilder setRequestorName(X500Name requestorName)
    {
        this.requestorName = new GeneralName(4, requestorName);
        return this;
    }

    public OCSPReqBuilder setRequestorName(GeneralName requestorName)
    {
        this.requestorName = requestorName;
        return this;
    }

    public OCSPReqBuilder setRequestExtensions(Extensions requestExtensions)
    {
        this.requestExtensions = requestExtensions;
        return this;
    }

    private OCSPReq generateRequest(ContentSigner contentSigner, X509CertificateHolder chain[])
        throws OCSPException
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
        Signature signature = null;
        if(contentSigner != null)
        {
            if(requestorName == null)
                throw new OCSPException("requestorName must be specified if request is signed.");
            try
            {
                OutputStream sOut = contentSigner.getOutputStream();
                sOut.write(tbsReq.getEncoded("DER"));
                sOut.close();
            }
            catch(Exception e)
            {
                throw new OCSPException((new StringBuilder()).append("exception processing TBSRequest: ").append(e).toString(), e);
            }
            DERBitString bitSig = new DERBitString(contentSigner.getSignature());
            AlgorithmIdentifier sigAlgId = contentSigner.getAlgorithmIdentifier();
            if(chain != null && chain.length > 0)
            {
                ASN1EncodableVector v = new ASN1EncodableVector();
                for(int i = 0; i != chain.length; i++)
                    v.add(chain[i].toASN1Structure());

                signature = new Signature(sigAlgId, bitSig, new DERSequence(v));
            } else
            {
                signature = new Signature(sigAlgId, bitSig);
            }
        }
        return new OCSPReq(new OCSPRequest(tbsReq, signature));
    }

    public OCSPReq build()
        throws OCSPException
    {
        return generateRequest(null, null);
    }

    public OCSPReq build(ContentSigner signer, X509CertificateHolder chain[])
        throws OCSPException, IllegalArgumentException
    {
        if(signer == null)
            throw new IllegalArgumentException("no signer specified");
        else
            return generateRequest(signer, chain);
    }

    private List list;
    private GeneralName requestorName;
    private Extensions requestExtensions;
}
