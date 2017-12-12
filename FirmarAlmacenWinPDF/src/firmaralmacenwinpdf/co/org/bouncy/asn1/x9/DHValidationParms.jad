// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHValidationParms.java

package co.org.bouncy.asn1.x9;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x9:
//            DHDomainParameters

public class DHValidationParms extends ASN1Object
{

    public static DHValidationParms getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static DHValidationParms getInstance(Object obj)
    {
        if(obj == null || (obj instanceof DHDomainParameters))
            return (DHValidationParms)obj;
        if(obj instanceof ASN1Sequence)
            return new DHValidationParms((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid DHValidationParms: ").append(obj.getClass().getName()).toString());
    }

    public DHValidationParms(DERBitString seed, ASN1Integer pgenCounter)
    {
        if(seed == null)
            throw new IllegalArgumentException("'seed' cannot be null");
        if(pgenCounter == null)
        {
            throw new IllegalArgumentException("'pgenCounter' cannot be null");
        } else
        {
            this.seed = seed;
            this.pgenCounter = pgenCounter;
            return;
        }
    }

    private DHValidationParms(ASN1Sequence seq)
    {
        if(seq.size() != 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        } else
        {
            seed = DERBitString.getInstance(seq.getObjectAt(0));
            pgenCounter = ASN1Integer.getInstance(seq.getObjectAt(1));
            return;
        }
    }

    public DERBitString getSeed()
    {
        return seed;
    }

    public ASN1Integer getPgenCounter()
    {
        return pgenCounter;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(seed);
        v.add(pgenCounter);
        return new DERSequence(v);
    }

    private DERBitString seed;
    private ASN1Integer pgenCounter;
}
