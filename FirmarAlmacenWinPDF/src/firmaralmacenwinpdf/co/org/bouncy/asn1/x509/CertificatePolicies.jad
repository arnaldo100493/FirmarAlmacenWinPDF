// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificatePolicies.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            PolicyInformation

public class CertificatePolicies extends ASN1Object
{

    public static CertificatePolicies getInstance(Object obj)
    {
        if(obj instanceof CertificatePolicies)
            return (CertificatePolicies)obj;
        if(obj != null)
            return new CertificatePolicies(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public static CertificatePolicies getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public CertificatePolicies(PolicyInformation name)
    {
        policyInformation = (new PolicyInformation[] {
            name
        });
    }

    public CertificatePolicies(PolicyInformation policyInformation[])
    {
        this.policyInformation = policyInformation;
    }

    private CertificatePolicies(ASN1Sequence seq)
    {
        policyInformation = new PolicyInformation[seq.size()];
        for(int i = 0; i != seq.size(); i++)
            policyInformation[i] = PolicyInformation.getInstance(seq.getObjectAt(i));

    }

    public PolicyInformation[] getPolicyInformation()
    {
        PolicyInformation tmp[] = new PolicyInformation[policyInformation.length];
        System.arraycopy(policyInformation, 0, tmp, 0, policyInformation.length);
        return tmp;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return new DERSequence(policyInformation);
    }

    public String toString()
    {
        String p = null;
        for(int i = 0; i < policyInformation.length; i++)
        {
            if(p != null)
                p = (new StringBuilder()).append(p).append(", ").toString();
            p = (new StringBuilder()).append(p).append(policyInformation[i]).toString();
        }

        return (new StringBuilder()).append("CertificatePolicies: ").append(p).toString();
    }

    private final PolicyInformation policyInformation[];
}
