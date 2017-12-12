// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MonetaryLimit.java

package co.org.bouncy.asn1.isismtt.x509;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;
import java.util.Enumeration;

public class MonetaryLimit extends ASN1Object
{

    public static MonetaryLimit getInstance(Object obj)
    {
        if(obj == null || (obj instanceof MonetaryLimit))
            return (MonetaryLimit)obj;
        if(obj instanceof ASN1Sequence)
            return new MonetaryLimit(ASN1Sequence.getInstance(obj));
        else
            throw new IllegalArgumentException("unknown object in getInstance");
    }

    private MonetaryLimit(ASN1Sequence seq)
    {
        if(seq.size() != 3)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        } else
        {
            Enumeration e = seq.getObjects();
            currency = DERPrintableString.getInstance(e.nextElement());
            amount = ASN1Integer.getInstance(e.nextElement());
            exponent = ASN1Integer.getInstance(e.nextElement());
            return;
        }
    }

    public MonetaryLimit(String currency, int amount, int exponent)
    {
        this.currency = new DERPrintableString(currency, true);
        this.amount = new ASN1Integer(amount);
        this.exponent = new ASN1Integer(exponent);
    }

    public String getCurrency()
    {
        return currency.getString();
    }

    public BigInteger getAmount()
    {
        return amount.getValue();
    }

    public BigInteger getExponent()
    {
        return exponent.getValue();
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector seq = new ASN1EncodableVector();
        seq.add(currency);
        seq.add(amount);
        seq.add(exponent);
        return new DERSequence(seq);
    }

    DERPrintableString currency;
    ASN1Integer amount;
    ASN1Integer exponent;
}
