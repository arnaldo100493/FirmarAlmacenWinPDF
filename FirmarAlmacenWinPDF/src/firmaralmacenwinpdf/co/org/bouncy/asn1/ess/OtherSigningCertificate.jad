// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OtherSigningCertificate.java

package co.org.bouncy.asn1.ess;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.PolicyInformation;

// Referenced classes of package co.org.bouncy.asn1.ess:
//            OtherCertID

public class OtherSigningCertificate extends ASN1Object
{

    public static OtherSigningCertificate getInstance(Object o)
    {
        if(o instanceof OtherSigningCertificate)
            return (OtherSigningCertificate)o;
        if(o != null)
            return new OtherSigningCertificate(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    private OtherSigningCertificate(ASN1Sequence seq)
    {
        if(seq.size() < 1 || seq.size() > 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        certs = ASN1Sequence.getInstance(seq.getObjectAt(0));
        if(seq.size() > 1)
            policies = ASN1Sequence.getInstance(seq.getObjectAt(1));
    }

    public OtherSigningCertificate(OtherCertID otherCertID)
    {
        certs = new DERSequence(otherCertID);
    }

    public OtherCertID[] getCerts()
    {
        OtherCertID cs[] = new OtherCertID[certs.size()];
        for(int i = 0; i != certs.size(); i++)
            cs[i] = OtherCertID.getInstance(certs.getObjectAt(i));

        return cs;
    }

    public PolicyInformation[] getPolicies()
    {
        if(policies == null)
            return null;
        PolicyInformation ps[] = new PolicyInformation[policies.size()];
        for(int i = 0; i != policies.size(); i++)
            ps[i] = PolicyInformation.getInstance(policies.getObjectAt(i));

        return ps;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(certs);
        if(policies != null)
            v.add(policies);
        return new DERSequence(v);
    }

    ASN1Sequence certs;
    ASN1Sequence policies;
}
