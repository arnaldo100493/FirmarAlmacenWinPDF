// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PersonalData.java

package co.org.bouncy.asn1.x509.sigi;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.DirectoryString;
import java.math.BigInteger;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.x509.sigi:
//            NameOrPseudonym

public class PersonalData extends ASN1Object
{

    public static PersonalData getInstance(Object obj)
    {
        if(obj == null || (obj instanceof PersonalData))
            return (PersonalData)obj;
        if(obj instanceof ASN1Sequence)
            return new PersonalData((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    private PersonalData(ASN1Sequence seq)
    {
        if(seq.size() < 1)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        Enumeration e = seq.getObjects();
        nameOrPseudonym = NameOrPseudonym.getInstance(e.nextElement());
        do
        {
            if(!e.hasMoreElements())
                break;
            ASN1TaggedObject o = ASN1TaggedObject.getInstance(e.nextElement());
            int tag = o.getTagNo();
            switch(tag)
            {
            case 0: // '\0'
                nameDistinguisher = ASN1Integer.getInstance(o, false).getValue();
                break;

            case 1: // '\001'
                dateOfBirth = ASN1GeneralizedTime.getInstance(o, false);
                break;

            case 2: // '\002'
                placeOfBirth = DirectoryString.getInstance(o, true);
                break;

            case 3: // '\003'
                gender = DERPrintableString.getInstance(o, false).getString();
                break;

            case 4: // '\004'
                postalAddress = DirectoryString.getInstance(o, true);
                break;

            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Bad tag number: ").append(o.getTagNo()).toString());
            }
        } while(true);
    }

    public PersonalData(NameOrPseudonym nameOrPseudonym, BigInteger nameDistinguisher, ASN1GeneralizedTime dateOfBirth, DirectoryString placeOfBirth, String gender, DirectoryString postalAddress)
    {
        this.nameOrPseudonym = nameOrPseudonym;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nameDistinguisher = nameDistinguisher;
        this.postalAddress = postalAddress;
        this.placeOfBirth = placeOfBirth;
    }

    public NameOrPseudonym getNameOrPseudonym()
    {
        return nameOrPseudonym;
    }

    public BigInteger getNameDistinguisher()
    {
        return nameDistinguisher;
    }

    public ASN1GeneralizedTime getDateOfBirth()
    {
        return dateOfBirth;
    }

    public DirectoryString getPlaceOfBirth()
    {
        return placeOfBirth;
    }

    public String getGender()
    {
        return gender;
    }

    public DirectoryString getPostalAddress()
    {
        return postalAddress;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector vec = new ASN1EncodableVector();
        vec.add(nameOrPseudonym);
        if(nameDistinguisher != null)
            vec.add(new DERTaggedObject(false, 0, new ASN1Integer(nameDistinguisher)));
        if(dateOfBirth != null)
            vec.add(new DERTaggedObject(false, 1, dateOfBirth));
        if(placeOfBirth != null)
            vec.add(new DERTaggedObject(true, 2, placeOfBirth));
        if(gender != null)
            vec.add(new DERTaggedObject(false, 3, new DERPrintableString(gender, true)));
        if(postalAddress != null)
            vec.add(new DERTaggedObject(true, 4, postalAddress));
        return new DERSequence(vec);
    }

    private NameOrPseudonym nameOrPseudonym;
    private BigInteger nameDistinguisher;
    private ASN1GeneralizedTime dateOfBirth;
    private DirectoryString placeOfBirth;
    private String gender;
    private DirectoryString postalAddress;
}
