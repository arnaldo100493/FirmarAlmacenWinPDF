// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignerLocation.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.DirectoryString;
import java.util.Enumeration;

public class SignerLocation extends ASN1Object
{

    private SignerLocation(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        do
        {
            if(!e.hasMoreElements())
                break;
            DERTaggedObject o = (DERTaggedObject)e.nextElement();
            switch(o.getTagNo())
            {
            case 0: // '\0'
                DirectoryString countryNameDirectoryString = DirectoryString.getInstance(o, true);
                countryName = new DERUTF8String(countryNameDirectoryString.getString());
                break;

            case 1: // '\001'
                DirectoryString localityNameDirectoryString = DirectoryString.getInstance(o, true);
                localityName = new DERUTF8String(localityNameDirectoryString.getString());
                break;

            case 2: // '\002'
                if(o.isExplicit())
                    postalAddress = ASN1Sequence.getInstance(o, true);
                else
                    postalAddress = ASN1Sequence.getInstance(o, false);
                if(postalAddress != null && postalAddress.size() > 6)
                    throw new IllegalArgumentException("postal address must contain less than 6 strings");
                break;

            default:
                throw new IllegalArgumentException("illegal tag");
            }
        } while(true);
    }

    public SignerLocation(DERUTF8String countryName, DERUTF8String localityName, ASN1Sequence postalAddress)
    {
        if(postalAddress != null && postalAddress.size() > 6)
            throw new IllegalArgumentException("postal address must contain less than 6 strings");
        if(countryName != null)
            this.countryName = DERUTF8String.getInstance(countryName.toASN1Primitive());
        if(localityName != null)
            this.localityName = DERUTF8String.getInstance(localityName.toASN1Primitive());
        if(postalAddress != null)
            this.postalAddress = ASN1Sequence.getInstance(postalAddress.toASN1Primitive());
    }

    public static SignerLocation getInstance(Object obj)
    {
        if(obj == null || (obj instanceof SignerLocation))
            return (SignerLocation)obj;
        else
            return new SignerLocation(ASN1Sequence.getInstance(obj));
    }

    public DERUTF8String getCountryName()
    {
        return countryName;
    }

    public DERUTF8String getLocalityName()
    {
        return localityName;
    }

    public ASN1Sequence getPostalAddress()
    {
        return postalAddress;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(countryName != null)
            v.add(new DERTaggedObject(true, 0, countryName));
        if(localityName != null)
            v.add(new DERTaggedObject(true, 1, localityName));
        if(postalAddress != null)
            v.add(new DERTaggedObject(true, 2, postalAddress));
        return new DERSequence(v);
    }

    private DERUTF8String countryName;
    private DERUTF8String localityName;
    private ASN1Sequence postalAddress;
}
