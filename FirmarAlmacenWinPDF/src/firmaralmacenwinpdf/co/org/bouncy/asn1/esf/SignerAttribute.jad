// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignerAttribute.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.Attribute;
import co.org.bouncy.asn1.x509.AttributeCertificate;
import java.util.Enumeration;

public class SignerAttribute extends ASN1Object
{

    public static SignerAttribute getInstance(Object o)
    {
        if(o instanceof SignerAttribute)
            return (SignerAttribute)o;
        if(o != null)
            return new SignerAttribute(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    private SignerAttribute(ASN1Sequence seq)
    {
        int index = 0;
        values = new Object[seq.size()];
        for(Enumeration e = seq.getObjects(); e.hasMoreElements(); index++)
        {
            ASN1TaggedObject taggedObject = ASN1TaggedObject.getInstance(e.nextElement());
            if(taggedObject.getTagNo() == 0)
            {
                ASN1Sequence attrs = ASN1Sequence.getInstance(taggedObject, true);
                Attribute attributes[] = new Attribute[attrs.size()];
                for(int i = 0; i != attributes.length; i++)
                    attributes[i] = Attribute.getInstance(attrs.getObjectAt(i));

                values[index] = attributes;
                continue;
            }
            if(taggedObject.getTagNo() == 1)
                values[index] = AttributeCertificate.getInstance(ASN1Sequence.getInstance(taggedObject, true));
            else
                throw new IllegalArgumentException((new StringBuilder()).append("illegal tag: ").append(taggedObject.getTagNo()).toString());
        }

    }

    public SignerAttribute(Attribute claimedAttributes[])
    {
        values = new Object[1];
        values[0] = claimedAttributes;
    }

    public SignerAttribute(AttributeCertificate certifiedAttributes)
    {
        values = new Object[1];
        values[0] = certifiedAttributes;
    }

    public Object[] getValues()
    {
        return values;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i != values.length; i++)
            if(values[i] instanceof Attribute[])
                v.add(new DERTaggedObject(0, new DERSequence((Attribute[])(Attribute[])values[i])));
            else
                v.add(new DERTaggedObject(1, (AttributeCertificate)values[i]));

        return new DERSequence(v);
    }

    private Object values[];
}
