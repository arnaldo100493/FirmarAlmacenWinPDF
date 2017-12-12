// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PolicyMappings.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;
import java.util.Hashtable;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            CertPolicyId

public class PolicyMappings extends ASN1Object
{

    public static PolicyMappings getInstance(Object obj)
    {
        if(obj instanceof PolicyMappings)
            return (PolicyMappings)obj;
        if(obj != null)
            return new PolicyMappings(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private PolicyMappings(ASN1Sequence seq)
    {
        this.seq = null;
        this.seq = seq;
    }

    /**
     * @deprecated Method PolicyMappings is deprecated
     */

    public PolicyMappings(Hashtable mappings)
    {
        seq = null;
        ASN1EncodableVector dev = new ASN1EncodableVector();
        ASN1EncodableVector dv;
        for(Enumeration it = mappings.keys(); it.hasMoreElements(); dev.add(new DERSequence(dv)))
        {
            String idp = (String)it.nextElement();
            String sdp = (String)mappings.get(idp);
            dv = new ASN1EncodableVector();
            dv.add(new ASN1ObjectIdentifier(idp));
            dv.add(new ASN1ObjectIdentifier(sdp));
        }

        seq = new DERSequence(dev);
    }

    public PolicyMappings(CertPolicyId issuerDomainPolicy, CertPolicyId subjectDomainPolicy)
    {
        seq = null;
        ASN1EncodableVector dv = new ASN1EncodableVector();
        dv.add(issuerDomainPolicy);
        dv.add(subjectDomainPolicy);
        seq = new DERSequence(new DERSequence(dv));
    }

    public PolicyMappings(CertPolicyId issuerDomainPolicy[], CertPolicyId subjectDomainPolicy[])
    {
        seq = null;
        ASN1EncodableVector dev = new ASN1EncodableVector();
        for(int i = 0; i != issuerDomainPolicy.length; i++)
        {
            ASN1EncodableVector dv = new ASN1EncodableVector();
            dv.add(issuerDomainPolicy[i]);
            dv.add(subjectDomainPolicy[i]);
            dev.add(new DERSequence(dv));
        }

        seq = new DERSequence(dev);
    }

    public ASN1Primitive toASN1Primitive()
    {
        return seq;
    }

    ASN1Sequence seq;
}
