// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKMACValue.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cmp.CMPObjectIdentifiers;
import co.org.bouncy.asn1.cmp.PBMParameter;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

public class PKMACValue extends ASN1Object
{

    private PKMACValue(ASN1Sequence seq)
    {
        algId = AlgorithmIdentifier.getInstance(seq.getObjectAt(0));
        value = DERBitString.getInstance(seq.getObjectAt(1));
    }

    public static PKMACValue getInstance(Object o)
    {
        if(o instanceof PKMACValue)
            return (PKMACValue)o;
        if(o != null)
            return new PKMACValue(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public static PKMACValue getInstance(ASN1TaggedObject obj, boolean isExplicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, isExplicit));
    }

    public PKMACValue(PBMParameter params, DERBitString value)
    {
        this(new AlgorithmIdentifier(CMPObjectIdentifiers.passwordBasedMac, params), value);
    }

    public PKMACValue(AlgorithmIdentifier aid, DERBitString value)
    {
        algId = aid;
        this.value = value;
    }

    public AlgorithmIdentifier getAlgId()
    {
        return algId;
    }

    public DERBitString getValue()
    {
        return value;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(algId);
        v.add(value);
        return new DERSequence(v);
    }

    private AlgorithmIdentifier algId;
    private DERBitString value;
}
