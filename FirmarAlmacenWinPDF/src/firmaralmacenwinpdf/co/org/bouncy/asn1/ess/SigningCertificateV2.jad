// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SigningCertificateV2.java

package co.org.bouncy.asn1.ess;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.PolicyInformation;

// Referenced classes of package co.org.bouncy.asn1.ess:
//            ESSCertIDv2

public class SigningCertificateV2 extends ASN1Object
{

    public static SigningCertificateV2 getInstance(Object o)
    {
        if(o == null || (o instanceof SigningCertificateV2))
            return (SigningCertificateV2)o;
        if(o instanceof ASN1Sequence)
            return new SigningCertificateV2((ASN1Sequence)o);
        else
            return null;
    }

    private SigningCertificateV2(ASN1Sequence seq)
    {
        if(seq.size() < 1 || seq.size() > 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        certs = ASN1Sequence.getInstance(seq.getObjectAt(0));
        if(seq.size() > 1)
            policies = ASN1Sequence.getInstance(seq.getObjectAt(1));
    }

    public SigningCertificateV2(ESSCertIDv2 cert)
    {
        certs = new DERSequence(cert);
    }

    public SigningCertificateV2(ESSCertIDv2 certs[])
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i < certs.length; i++)
            v.add(certs[i]);

        this.certs = new DERSequence(v);
    }

    public SigningCertificateV2(ESSCertIDv2 certs[], PolicyInformation policies[])
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i < certs.length; i++)
            v.add(certs[i]);

        this.certs = new DERSequence(v);
        if(policies != null)
        {
            v = new ASN1EncodableVector();
            for(int i = 0; i < policies.length; i++)
                v.add(policies[i]);

            this.policies = new DERSequence(v);
        }
    }

    public ESSCertIDv2[] getCerts()
    {
        ESSCertIDv2 certIds[] = new ESSCertIDv2[certs.size()];
        for(int i = 0; i != certs.size(); i++)
            certIds[i] = ESSCertIDv2.getInstance(certs.getObjectAt(i));

        return certIds;
    }

    public PolicyInformation[] getPolicies()
    {
        if(policies == null)
            return null;
        PolicyInformation policyInformations[] = new PolicyInformation[policies.size()];
        for(int i = 0; i != policies.size(); i++)
            policyInformations[i] = PolicyInformation.getInstance(policies.getObjectAt(i));

        return policyInformations;
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
