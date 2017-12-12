// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateConfirmationContentBuilder.java

package co.org.bouncy.cert.cmp;

import co.org.bouncy.asn1.ASN1EncodableVector;
import co.org.bouncy.asn1.DERSequence;
import co.org.bouncy.asn1.cmp.CertConfirmContent;
import co.org.bouncy.asn1.cmp.CertStatus;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package co.org.bouncy.cert.cmp:
//            CMPException, CertificateConfirmationContent, CMPUtil

public class CertificateConfirmationContentBuilder
{

    public CertificateConfirmationContentBuilder()
    {
        this(((DigestAlgorithmIdentifierFinder) (new DefaultDigestAlgorithmIdentifierFinder())));
    }

    public CertificateConfirmationContentBuilder(DigestAlgorithmIdentifierFinder digestAlgFinder)
    {
        acceptedCerts = new ArrayList();
        acceptedReqIds = new ArrayList();
        this.digestAlgFinder = digestAlgFinder;
    }

    public CertificateConfirmationContentBuilder addAcceptedCertificate(X509CertificateHolder certHolder, BigInteger certReqID)
    {
        acceptedCerts.add(certHolder);
        acceptedReqIds.add(certReqID);
        return this;
    }

    public CertificateConfirmationContent build(DigestCalculatorProvider digesterProvider)
        throws CMPException
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i != acceptedCerts.size(); i++)
        {
            X509CertificateHolder certHolder = (X509CertificateHolder)acceptedCerts.get(i);
            BigInteger reqID = (BigInteger)acceptedReqIds.get(i);
            AlgorithmIdentifier digAlg = digestAlgFinder.find(certHolder.toASN1Structure().getSignatureAlgorithm());
            if(digAlg == null)
                throw new CMPException("cannot find algorithm for digest from signature");
            DigestCalculator digester;
            try
            {
                digester = digesterProvider.get(digAlg);
            }
            catch(OperatorCreationException e)
            {
                throw new CMPException((new StringBuilder()).append("unable to create digest: ").append(e.getMessage()).toString(), e);
            }
            CMPUtil.derEncodeToStream(certHolder.toASN1Structure(), digester.getOutputStream());
            v.add(new CertStatus(digester.getDigest(), reqID));
        }

        return new CertificateConfirmationContent(CertConfirmContent.getInstance(new DERSequence(v)), digestAlgFinder);
    }

    private DigestAlgorithmIdentifierFinder digestAlgFinder;
    private List acceptedCerts;
    private List acceptedReqIds;
}
