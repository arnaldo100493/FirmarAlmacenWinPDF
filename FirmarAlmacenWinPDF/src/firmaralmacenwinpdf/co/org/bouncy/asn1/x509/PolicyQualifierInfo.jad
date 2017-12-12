// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PolicyQualifierInfo.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            PolicyQualifierId

public class PolicyQualifierInfo extends ASN1Object
{

    public PolicyQualifierInfo(ASN1ObjectIdentifier policyQualifierId, ASN1Encodable qualifier)
    {
        this.policyQualifierId = policyQualifierId;
        this.qualifier = qualifier;
    }

    public PolicyQualifierInfo(String cps)
    {
        policyQualifierId = PolicyQualifierId.id_qt_cps;
        qualifier = new DERIA5String(cps);
    }

    public PolicyQualifierInfo(ASN1Sequence as)
    {
        if(as.size() != 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(as.size()).toString());
        } else
        {
            policyQualifierId = ASN1ObjectIdentifier.getInstance(as.getObjectAt(0));
            qualifier = as.getObjectAt(1);
            return;
        }
    }

    public static PolicyQualifierInfo getInstance(Object obj)
    {
        if(obj instanceof PolicyQualifierInfo)
            return (PolicyQualifierInfo)obj;
        if(obj != null)
            return new PolicyQualifierInfo(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public ASN1ObjectIdentifier getPolicyQualifierId()
    {
        return policyQualifierId;
    }

    public ASN1Encodable getQualifier()
    {
        return qualifier;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector dev = new ASN1EncodableVector();
        dev.add(policyQualifierId);
        dev.add(qualifier);
        return new DERSequence(dev);
    }

    private ASN1ObjectIdentifier policyQualifierId;
    private ASN1Encodable qualifier;
}
