// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSAPublicKey.java

package co.org.bouncy.asn1.eac;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.eac:
//            PublicKeyDataObject, UnsignedInteger

public class RSAPublicKey extends PublicKeyDataObject
{

    RSAPublicKey(ASN1Sequence seq)
    {
        valid = 0;
        Enumeration en = seq.getObjects();
        usage = ASN1ObjectIdentifier.getInstance(en.nextElement());
        do
        {
            if(!en.hasMoreElements())
                break;
            UnsignedInteger val = UnsignedInteger.getInstance(en.nextElement());
            switch(val.getTagNo())
            {
            case 1: // '\001'
                setModulus(val);
                break;

            case 2: // '\002'
                setExponent(val);
                break;

            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown DERTaggedObject :").append(val.getTagNo()).append("-> not an Iso7816RSAPublicKeyStructure").toString());
            }
        } while(true);
        if(valid != 3)
            throw new IllegalArgumentException("missing argument -> not an Iso7816RSAPublicKeyStructure");
        else
            return;
    }

    public RSAPublicKey(ASN1ObjectIdentifier usage, BigInteger modulus, BigInteger exponent)
    {
        valid = 0;
        this.usage = usage;
        this.modulus = modulus;
        this.exponent = exponent;
    }

    public ASN1ObjectIdentifier getUsage()
    {
        return usage;
    }

    public BigInteger getModulus()
    {
        return modulus;
    }

    public BigInteger getPublicExponent()
    {
        return exponent;
    }

    private void setModulus(UnsignedInteger modulus)
    {
        if((valid & modulusValid) == 0)
        {
            valid |= modulusValid;
            this.modulus = modulus.getValue();
        } else
        {
            throw new IllegalArgumentException("Modulus already set");
        }
    }

    private void setExponent(UnsignedInteger exponent)
    {
        if((valid & exponentValid) == 0)
        {
            valid |= exponentValid;
            this.exponent = exponent.getValue();
        } else
        {
            throw new IllegalArgumentException("Exponent already set");
        }
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(usage);
        v.add(new UnsignedInteger(1, getModulus()));
        v.add(new UnsignedInteger(2, getPublicExponent()));
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier usage;
    private BigInteger modulus;
    private BigInteger exponent;
    private int valid;
    private static int modulusValid = 1;
    private static int exponentValid = 2;

}
