// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RevAnnContent.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.crmf.CertId;
import co.org.bouncy.asn1.x509.Extensions;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            PKIStatus

public class RevAnnContent extends ASN1Object
{

    private RevAnnContent(ASN1Sequence seq)
    {
        status = PKIStatus.getInstance(seq.getObjectAt(0));
        certId = CertId.getInstance(seq.getObjectAt(1));
        willBeRevokedAt = ASN1GeneralizedTime.getInstance(seq.getObjectAt(2));
        badSinceDate = ASN1GeneralizedTime.getInstance(seq.getObjectAt(3));
        if(seq.size() > 4)
            crlDetails = Extensions.getInstance(seq.getObjectAt(4));
    }

    public static RevAnnContent getInstance(Object o)
    {
        if(o instanceof RevAnnContent)
            return (RevAnnContent)o;
        if(o != null)
            return new RevAnnContent(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public PKIStatus getStatus()
    {
        return status;
    }

    public CertId getCertId()
    {
        return certId;
    }

    public ASN1GeneralizedTime getWillBeRevokedAt()
    {
        return willBeRevokedAt;
    }

    public ASN1GeneralizedTime getBadSinceDate()
    {
        return badSinceDate;
    }

    public Extensions getCrlDetails()
    {
        return crlDetails;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(status);
        v.add(certId);
        v.add(willBeRevokedAt);
        v.add(badSinceDate);
        if(crlDetails != null)
            v.add(crlDetails);
        return new DERSequence(v);
    }

    private PKIStatus status;
    private CertId certId;
    private ASN1GeneralizedTime willBeRevokedAt;
    private ASN1GeneralizedTime badSinceDate;
    private Extensions crlDetails;
}
