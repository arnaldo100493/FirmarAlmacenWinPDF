// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeclarationOfMajority.java

package co.org.bouncy.asn1.isismtt.x509;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;

public class DeclarationOfMajority extends ASN1Object
    implements ASN1Choice
{

    public DeclarationOfMajority(int notYoungerThan)
    {
        declaration = new DERTaggedObject(false, 0, new ASN1Integer(notYoungerThan));
    }

    public DeclarationOfMajority(boolean fullAge, String country)
    {
        if(country.length() > 2)
            throw new IllegalArgumentException("country can only be 2 characters");
        if(fullAge)
        {
            declaration = new DERTaggedObject(false, 1, new DERSequence(new DERPrintableString(country, true)));
        } else
        {
            ASN1EncodableVector v = new ASN1EncodableVector();
            v.add(ASN1Boolean.FALSE);
            v.add(new DERPrintableString(country, true));
            declaration = new DERTaggedObject(false, 1, new DERSequence(v));
        }
    }

    public DeclarationOfMajority(ASN1GeneralizedTime dateOfBirth)
    {
        declaration = new DERTaggedObject(false, 2, dateOfBirth);
    }

    public static DeclarationOfMajority getInstance(Object obj)
    {
        if(obj == null || (obj instanceof DeclarationOfMajority))
            return (DeclarationOfMajority)obj;
        if(obj instanceof ASN1TaggedObject)
            return new DeclarationOfMajority((ASN1TaggedObject)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    private DeclarationOfMajority(ASN1TaggedObject o)
    {
        if(o.getTagNo() > 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad tag number: ").append(o.getTagNo()).toString());
        } else
        {
            declaration = o;
            return;
        }
    }

    public ASN1Primitive toASN1Primitive()
    {
        return declaration;
    }

    public int getType()
    {
        return declaration.getTagNo();
    }

    public int notYoungerThan()
    {
        if(declaration.getTagNo() != 0)
            return -1;
        else
            return ASN1Integer.getInstance(declaration, false).getValue().intValue();
    }

    public ASN1Sequence fullAgeAtCountry()
    {
        if(declaration.getTagNo() != 1)
            return null;
        else
            return ASN1Sequence.getInstance(declaration, false);
    }

    public ASN1GeneralizedTime getDateOfBirth()
    {
        if(declaration.getTagNo() != 2)
            return null;
        else
            return ASN1GeneralizedTime.getInstance(declaration, false);
    }

    public static final int notYoungerThan = 0;
    public static final int fullAgeAtCountry = 1;
    public static final int dateOfBirth = 2;
    private ASN1TaggedObject declaration;
}
