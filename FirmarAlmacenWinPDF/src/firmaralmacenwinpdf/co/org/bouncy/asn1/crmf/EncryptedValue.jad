// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EncryptedValue.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

public class EncryptedValue extends ASN1Object
{

    private EncryptedValue(ASN1Sequence seq)
    {
        int index;
        for(index = 0; seq.getObjectAt(index) instanceof ASN1TaggedObject; index++)
        {
            ASN1TaggedObject tObj = (ASN1TaggedObject)seq.getObjectAt(index);
            switch(tObj.getTagNo())
            {
            case 0: // '\0'
                intendedAlg = AlgorithmIdentifier.getInstance(tObj, false);
                break;

            case 1: // '\001'
                symmAlg = AlgorithmIdentifier.getInstance(tObj, false);
                break;

            case 2: // '\002'
                encSymmKey = DERBitString.getInstance(tObj, false);
                break;

            case 3: // '\003'
                keyAlg = AlgorithmIdentifier.getInstance(tObj, false);
                break;

            case 4: // '\004'
                valueHint = ASN1OctetString.getInstance(tObj, false);
                break;
            }
        }

        encValue = DERBitString.getInstance(seq.getObjectAt(index));
    }

    public static EncryptedValue getInstance(Object o)
    {
        if(o instanceof EncryptedValue)
            return (EncryptedValue)o;
        if(o != null)
            return new EncryptedValue(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public EncryptedValue(AlgorithmIdentifier intendedAlg, AlgorithmIdentifier symmAlg, DERBitString encSymmKey, AlgorithmIdentifier keyAlg, ASN1OctetString valueHint, DERBitString encValue)
    {
        if(encValue == null)
        {
            throw new IllegalArgumentException("'encValue' cannot be null");
        } else
        {
            this.intendedAlg = intendedAlg;
            this.symmAlg = symmAlg;
            this.encSymmKey = encSymmKey;
            this.keyAlg = keyAlg;
            this.valueHint = valueHint;
            this.encValue = encValue;
            return;
        }
    }

    public AlgorithmIdentifier getIntendedAlg()
    {
        return intendedAlg;
    }

    public AlgorithmIdentifier getSymmAlg()
    {
        return symmAlg;
    }

    public DERBitString getEncSymmKey()
    {
        return encSymmKey;
    }

    public AlgorithmIdentifier getKeyAlg()
    {
        return keyAlg;
    }

    public ASN1OctetString getValueHint()
    {
        return valueHint;
    }

    public DERBitString getEncValue()
    {
        return encValue;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        addOptional(v, 0, intendedAlg);
        addOptional(v, 1, symmAlg);
        addOptional(v, 2, encSymmKey);
        addOptional(v, 3, keyAlg);
        addOptional(v, 4, valueHint);
        v.add(encValue);
        return new DERSequence(v);
    }

    private void addOptional(ASN1EncodableVector v, int tagNo, ASN1Encodable obj)
    {
        if(obj != null)
            v.add(new DERTaggedObject(false, tagNo, obj));
    }

    private AlgorithmIdentifier intendedAlg;
    private AlgorithmIdentifier symmAlg;
    private DERBitString encSymmKey;
    private AlgorithmIdentifier keyAlg;
    private ASN1OctetString valueHint;
    private DERBitString encValue;
}
