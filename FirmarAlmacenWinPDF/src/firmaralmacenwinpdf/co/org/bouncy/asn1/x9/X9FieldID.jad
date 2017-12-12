// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X9FieldID.java

package co.org.bouncy.asn1.x9;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.x9:
//            X9ObjectIdentifiers

public class X9FieldID extends ASN1Object
    implements X9ObjectIdentifiers
{

    public X9FieldID(BigInteger primeP)
    {
        id = prime_field;
        parameters = new ASN1Integer(primeP);
    }

    public X9FieldID(int m, int k1, int k2, int k3)
    {
        id = characteristic_two_field;
        ASN1EncodableVector fieldIdParams = new ASN1EncodableVector();
        fieldIdParams.add(new ASN1Integer(m));
        if(k2 == 0)
        {
            fieldIdParams.add(tpBasis);
            fieldIdParams.add(new ASN1Integer(k1));
        } else
        {
            fieldIdParams.add(ppBasis);
            ASN1EncodableVector pentanomialParams = new ASN1EncodableVector();
            pentanomialParams.add(new ASN1Integer(k1));
            pentanomialParams.add(new ASN1Integer(k2));
            pentanomialParams.add(new ASN1Integer(k3));
            fieldIdParams.add(new DERSequence(pentanomialParams));
        }
        parameters = new DERSequence(fieldIdParams);
    }

    public X9FieldID(ASN1Sequence seq)
    {
        id = (ASN1ObjectIdentifier)seq.getObjectAt(0);
        parameters = (ASN1Primitive)seq.getObjectAt(1);
    }

    public ASN1ObjectIdentifier getIdentifier()
    {
        return id;
    }

    public ASN1Primitive getParameters()
    {
        return parameters;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(id);
        v.add(parameters);
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier id;
    private ASN1Primitive parameters;
}
