// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasicOCSPRespBuilder.java

package co.org.bouncy.cert.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.ocsp.*;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.ContentSigner;
import co.org.bouncy.operator.DigestCalculator;
import java.io.OutputStream;
import java.util.*;

// Referenced classes of package co.org.bouncy.cert.ocsp:
//            RespID, OCSPException, BasicOCSPResp, CertificateID, 
//            CertificateStatus, UnknownStatus, RevokedStatus

public class BasicOCSPRespBuilder
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
        Extensions extensions;
        final BasicOCSPRespBuilder this$0;

        public ResponseObject(CertificateID certId, CertificateStatus certStatus, Date thisUpdate, Date nextUpdate, Extensions extensions)
        {
            this$0 = BasicOCSPRespBuilder.this;
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


    public BasicOCSPRespBuilder(RespID responderID)
    {
        list = new ArrayList();
        responseExtensions = null;
        this.responderID = responderID;
    }

    public BasicOCSPRespBuilder(SubjectPublicKeyInfo key, DigestCalculator digCalc)
        throws OCSPException
    {
        list = new ArrayList();
        responseExtensions = null;
        responderID = new RespID(key, digCalc);
    }

    public BasicOCSPRespBuilder addResponse(CertificateID certID, CertificateStatus certStatus)
    {
        list.add(new ResponseObject(certID, certStatus, new Date(), null, null));
        return this;
    }

    public BasicOCSPRespBuilder addResponse(CertificateID certID, CertificateStatus certStatus, Extensions singleExtensions)
    {
        list.add(new ResponseObject(certID, certStatus, new Date(), null, singleExtensions));
        return this;
    }

    public BasicOCSPRespBuilder addResponse(CertificateID certID, CertificateStatus certStatus, Date nextUpdate, Extensions singleExtensions)
    {
        list.add(new ResponseObject(certID, certStatus, new Date(), nextUpdate, singleExtensions));
        return this;
    }

    public BasicOCSPRespBuilder addResponse(CertificateID certID, CertificateStatus certStatus, Date thisUpdate, Date nextUpdate, Extensions singleExtensions)
    {
        list.add(new ResponseObject(certID, certStatus, thisUpdate, nextUpdate, singleExtensions));
        return this;
    }

    public BasicOCSPRespBuilder setResponseExtensions(Extensions responseExtensions)
    {
        this.responseExtensions = responseExtensions;
        return this;
    }

    public BasicOCSPResp build(ContentSigner signer, X509CertificateHolder chain[], Date producedAt)
        throws OCSPException
    {
        Iterator it = list.iterator();
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
        ResponseData tbsResp = new ResponseData(responderID.toASN1Object(), new ASN1GeneralizedTime(producedAt), new DERSequence(responses), responseExtensions);
        DERBitString bitSig;
        try
        {
            OutputStream sigOut = signer.getOutputStream();
            sigOut.write(tbsResp.getEncoded("DER"));
            sigOut.close();
            bitSig = new DERBitString(signer.getSignature());
        }
        catch(Exception e)
        {
            throw new OCSPException((new StringBuilder()).append("exception processing TBSRequest: ").append(e.getMessage()).toString(), e);
        }
        AlgorithmIdentifier sigAlgId = signer.getAlgorithmIdentifier();
        DERSequence chainSeq = null;
        if(chain != null && chain.length > 0)
        {
            ASN1EncodableVector v = new ASN1EncodableVector();
            for(int i = 0; i != chain.length; i++)
                v.add(chain[i].toASN1Structure());

            chainSeq = new DERSequence(v);
        }
        return new BasicOCSPResp(new BasicOCSPResponse(tbsResp, sigAlgId, bitSig, chainSeq));
    }

    private List list;
    private Extensions responseExtensions;
    private RespID responderID;
}
