// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PathProcInput.java

package co.org.bouncy.asn1.dvcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.PolicyInformation;

public class PathProcInput extends ASN1Object
{

    public PathProcInput(PolicyInformation acceptablePolicySet[])
    {
        inhibitPolicyMapping = false;
        explicitPolicyReqd = false;
        inhibitAnyPolicy = false;
        this.acceptablePolicySet = acceptablePolicySet;
    }

    public PathProcInput(PolicyInformation acceptablePolicySet[], boolean inhibitPolicyMapping, boolean explicitPolicyReqd, boolean inhibitAnyPolicy)
    {
        this.inhibitPolicyMapping = false;
        this.explicitPolicyReqd = false;
        this.inhibitAnyPolicy = false;
        this.acceptablePolicySet = acceptablePolicySet;
        this.inhibitPolicyMapping = inhibitPolicyMapping;
        this.explicitPolicyReqd = explicitPolicyReqd;
        this.inhibitAnyPolicy = inhibitAnyPolicy;
    }

    private static PolicyInformation[] fromSequence(ASN1Sequence seq)
    {
        PolicyInformation tmp[] = new PolicyInformation[seq.size()];
        for(int i = 0; i != tmp.length; i++)
            tmp[i] = PolicyInformation.getInstance(seq.getObjectAt(i));

        return tmp;
    }

    public static PathProcInput getInstance(Object obj)
    {
        if(obj instanceof PathProcInput)
            return (PathProcInput)obj;
        if(obj != null)
        {
            ASN1Sequence seq = ASN1Sequence.getInstance(obj);
            ASN1Sequence policies = ASN1Sequence.getInstance(seq.getObjectAt(0));
            PathProcInput result = new PathProcInput(fromSequence(policies));
            for(int i = 1; i < seq.size(); i++)
            {
                Object o = seq.getObjectAt(i);
                if(o instanceof ASN1Boolean)
                {
                    ASN1Boolean x = ASN1Boolean.getInstance(o);
                    result.setInhibitPolicyMapping(x.isTrue());
                    continue;
                }
                if(o instanceof ASN1TaggedObject)
                {
                    ASN1TaggedObject t = ASN1TaggedObject.getInstance(o);
                    switch(t.getTagNo())
                    {
                    case 0: // '\0'
                    {
                        ASN1Boolean x = ASN1Boolean.getInstance(t, false);
                        result.setExplicitPolicyReqd(x.isTrue());
                        break;
                    }

                    case 1: // '\001'
                    {
                        ASN1Boolean x = ASN1Boolean.getInstance(t, false);
                        result.setInhibitAnyPolicy(x.isTrue());
                        break;
                    }
                    }
                }
            }

            return result;
        } else
        {
            return null;
        }
    }

    public static PathProcInput getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        ASN1EncodableVector pV = new ASN1EncodableVector();
        for(int i = 0; i != acceptablePolicySet.length; i++)
            pV.add(acceptablePolicySet[i]);

        v.add(new DERSequence(pV));
        if(inhibitPolicyMapping)
            v.add(new ASN1Boolean(inhibitPolicyMapping));
        if(explicitPolicyReqd)
            v.add(new DERTaggedObject(false, 0, new ASN1Boolean(explicitPolicyReqd)));
        if(inhibitAnyPolicy)
            v.add(new DERTaggedObject(false, 1, new ASN1Boolean(inhibitAnyPolicy)));
        return new DERSequence(v);
    }

    public String toString()
    {
        return (new StringBuilder()).append("PathProcInput: {\nacceptablePolicySet: ").append(acceptablePolicySet).append("\n").append("inhibitPolicyMapping: ").append(inhibitPolicyMapping).append("\n").append("explicitPolicyReqd: ").append(explicitPolicyReqd).append("\n").append("inhibitAnyPolicy: ").append(inhibitAnyPolicy).append("\n").append("}\n").toString();
    }

    public PolicyInformation[] getAcceptablePolicySet()
    {
        return acceptablePolicySet;
    }

    public boolean isInhibitPolicyMapping()
    {
        return inhibitPolicyMapping;
    }

    private void setInhibitPolicyMapping(boolean inhibitPolicyMapping)
    {
        this.inhibitPolicyMapping = inhibitPolicyMapping;
    }

    public boolean isExplicitPolicyReqd()
    {
        return explicitPolicyReqd;
    }

    private void setExplicitPolicyReqd(boolean explicitPolicyReqd)
    {
        this.explicitPolicyReqd = explicitPolicyReqd;
    }

    public boolean isInhibitAnyPolicy()
    {
        return inhibitAnyPolicy;
    }

    private void setInhibitAnyPolicy(boolean inhibitAnyPolicy)
    {
        this.inhibitAnyPolicy = inhibitAnyPolicy;
    }

    private PolicyInformation acceptablePolicySet[];
    private boolean inhibitPolicyMapping;
    private boolean explicitPolicyReqd;
    private boolean inhibitAnyPolicy;
}
