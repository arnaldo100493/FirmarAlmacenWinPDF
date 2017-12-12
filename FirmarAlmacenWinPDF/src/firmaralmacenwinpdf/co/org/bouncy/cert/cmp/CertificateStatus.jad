// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateStatus.java

package co.org.bouncy.cert.cmp;

import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.cmp.CertStatus;
import co.org.bouncy.asn1.cmp.PKIStatusInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.*;
import co.org.bouncy.util.Arrays;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.cert.cmp:
//            CMPException, CMPUtil

public class CertificateStatus
{

    CertificateStatus(DigestAlgorithmIdentifierFinder digestAlgFinder, CertStatus certStatus)
    {
        this.digestAlgFinder = digestAlgFinder;
        this.certStatus = certStatus;
    }

    public PKIStatusInfo getStatusInfo()
    {
        return certStatus.getStatusInfo();
    }

    public BigInteger getCertRequestID()
    {
        return certStatus.getCertReqId().getValue();
    }

    public boolean isVerified(X509CertificateHolder certHolder, DigestCalculatorProvider digesterProvider)
        throws CMPException
    {
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
            throw new CMPException((new StringBuilder()).append("unable to create digester: ").append(e.getMessage()).toString(), e);
        }
        CMPUtil.derEncodeToStream(certHolder.toASN1Structure(), digester.getOutputStream());
        return Arrays.areEqual(certStatus.getCertHash().getOctets(), digester.getDigest());
    }

    private DigestAlgorithmIdentifierFinder digestAlgFinder;
    private CertStatus certStatus;
}
