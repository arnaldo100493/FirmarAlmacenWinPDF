// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasicOCSPRespGenerator.java

package co.org.bouncy.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.ocsp.*;
import co.org.bouncy.asn1.x509.*;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.*;

// Referenced classes of package co.org.bouncy.ocsp:
//            RespID, OCSPException, BasicOCSPResp, OCSPUtil, 
//            CertificateID, CertificateStatus, UnknownStatus, RevokedStatus

/**
 * @deprecated Class BasicOCSPRespGenerator is deprecated
 */

public class BasicOCSPRespGenerator
{
    private class ResponseObject
    {

        public SingleResponse toResponse()
            throws Exception
        {
            return new SingleResponse(certId.toASN1Object(), certStatus, thisUpdate, nextUpdate, extensions);
        }

        CertificateID certId;
        CertStatus certStatus;
        DERGeneralizedTime thisUpdate;
        DERGeneralizedTime nextUpdate;
        X509Extensions extensions;
        final BasicOCSPRespGenerator this$0;

        public ResponseObject(CertificateID certId, CertificateStatus certStatus, Date thisUpdate, Date nextUpdate, X509Extensions extensions)
        {
            this$0 = BasicOCSPRespGenerator.this;
            super();
            this.certId = certId;
            if(certStatus == null)
                this.certStatus = new CertStatus();
            else
            if(certStatus instanceof UnknownStatus)
            {
                this.certStatus = new CertStatus(2, DERNull.INSTANCE);
            } else
            {
                RevokedStatus rs = (RevokedStatus)certStatus;
                if(rs.hasRevocationReason())
                    this.certStatus = new CertStatus(new RevokedInfo(new ASN1GeneralizedTime(rs.getRevocationTime()), CRLReason.lookup(rs.getRevocationReason())));
                else
                    this.certStatus = new CertStatus(new RevokedInfo(new ASN1GeneralizedTime(rs.getRevocationTime()), null));
            }
            this.thisUpdate = new DERGeneralizedTime(thisUpdate);
            if(nextUpdate != null)
                this.nextUpdate = new DERGeneralizedTime(nextUpdate);
            else
                this.nextUpdate = null;
            this.extensions = extensions;
        }
    }


    public BasicOCSPRespGenerator(RespID responderID)
    {
        list = new ArrayList();
        responseExtensions = null;
        this.responderID = responderID;
    }

    public BasicOCSPRespGenerator(PublicKey key)
        throws OCSPException
    {
        list = new ArrayList();
        responseExtensions = null;
        responderID = new RespID(key);
    }

    public void addResponse(CertificateID certID, CertificateStatus certStatus)
    {
        list.add(new ResponseObject(certID, certStatus, new Date(), null, null));
    }

    public void addResponse(CertificateID certID, CertificateStatus certStatus, X509Extensions singleExtensions)
    {
        list.add(new ResponseObject(certID, certStatus, new Date(), null, singleExtensions));
    }

    public void addResponse(CertificateID certID, CertificateStatus certStatus, Date nextUpdate, X509Extensions singleExtensions)
    {
        list.add(new ResponseObject(certID, certStatus, new Date(), nextUpdate, singleExtensions));
    }

    public void addResponse(CertificateID certID, CertificateStatus certStatus, Date thisUpdate, Date nextUpdate, X509Extensions singleExtensions)
    {
        list.add(new ResponseObject(certID, certStatus, thisUpdate, nextUpdate, singleExtensions));
    }

    public void setResponseExtensions(X509Extensions responseExtensions)
    {
        this.responseExtensions = responseExtensions;
    }

    private BasicOCSPResp generateResponse(String signatureName, PrivateKey key, X509Certificate chain[], Date producedAt, String provider, SecureRandom random)
        throws OCSPException, NoSuchProviderException
    {
        Iterator it = list.iterator();
        DERObjectIdentifier signingAlgorithm;
        try
        {
            signingAlgorithm = OCSPUtil.getAlgorithmOID(signatureName);
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("unknown signing algorithm specified");
        }
        ASN1EncodableVector responses = new ASN1EncodableVector();
        while(it.hasNext()) 
            try
            {
                responses.add(((ResponseObject)it.next()).toResponse());
            }
            catch(Exception e)
            {
                throw new OCSPException("exception creating Request", e);
            }
        ResponseData tbsResp = new ResponseData(responderID.toASN1Object(), new DERGeneralizedTime(producedAt), new DERSequence(responses), responseExtensions);
        Signature sig = null;
        try
        {
            sig = OCSPUtil.createSignatureInstance(signatureName, provider);
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
            sig.update(tbsResp.getEncoded("DER"));
            bitSig = new DERBitString(sig.sign());
        }
        catch(Exception e)
        {
            throw new OCSPException((new StringBuilder()).append("exception processing TBSRequest: ").append(e).toString(), e);
        }
        AlgorithmIdentifier sigAlgId = OCSPUtil.getSigAlgID(signingAlgorithm);
        DERSequence chainSeq = null;
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
            chainSeq = new DERSequence(v);
        }
        return new BasicOCSPResp(new BasicOCSPResponse(tbsResp, sigAlgId, bitSig, chainSeq));
    }

    public BasicOCSPResp generate(String signingAlgorithm, PrivateKey key, X509Certificate chain[], Date thisUpdate, String provider)
        throws OCSPException, NoSuchProviderException, IllegalArgumentException
    {
        return generate(signingAlgorithm, key, chain, thisUpdate, provider, null);
    }

    public BasicOCSPResp generate(String signingAlgorithm, PrivateKey key, X509Certificate chain[], Date producedAt, String provider, SecureRandom random)
        throws OCSPException, NoSuchProviderException, IllegalArgumentException
    {
        if(signingAlgorithm == null)
            throw new IllegalArgumentException("no signing algorithm specified");
        else
            return generateResponse(signingAlgorithm, key, chain, producedAt, provider, random);
    }

    public Iterator getSignatureAlgNames()
    {
        return OCSPUtil.getAlgNames();
    }

    private List list;
    private X509Extensions responseExtensions;
    private RespID responderID;
}
