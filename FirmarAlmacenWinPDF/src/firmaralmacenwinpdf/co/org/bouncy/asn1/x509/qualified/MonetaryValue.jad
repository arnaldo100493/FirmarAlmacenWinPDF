// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MonetaryValue.java

package co.org.bouncy.asn1.x509.qualified;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.x509.qualified:
//            Iso4217CurrencyCode

public class MonetaryValue extends ASN1Object
{

    public static MonetaryValue getInstance(Object obj)
    {
        if(obj instanceof MonetaryValue)
            return (MonetaryValue)obj;
        if(obj != null)
            return new MonetaryValue(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private MonetaryValue(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        currency = Iso4217CurrencyCode.getInstance(e.nextElement());
        amount = ASN1Integer.getInstance(e.nextElement());
        exponent = ASN1Integer.getInstance(e.nextElement());
    }

    public MonetaryValue(Iso4217CurrencyCode currency, int amount, int exponent)
    {
        this.currency = currency;
        this.amount = new ASN1Integer(amount);
        this.exponent = new ASN1Integer(exponent);
    }

    public Iso4217CurrencyCode getCurrency()
    {
        return currency;
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

    private Iso4217CurrencyCode currency;
    private ASN1Integer amount;
    private ASN1Integer exponent;
}
