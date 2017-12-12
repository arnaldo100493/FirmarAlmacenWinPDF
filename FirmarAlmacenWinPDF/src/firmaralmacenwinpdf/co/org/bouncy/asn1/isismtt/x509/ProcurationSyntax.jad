// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProcurationSyntax.java

package co.org.bouncy.asn1.isismtt.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.DirectoryString;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.asn1.x509.IssuerSerial;
import java.util.Enumeration;

public class ProcurationSyntax extends ASN1Object
{

    public static ProcurationSyntax getInstance(Object obj)
    {
        if(obj == null || (obj instanceof ProcurationSyntax))
            return (ProcurationSyntax)obj;
        if(obj instanceof ASN1Sequence)
            return new ProcurationSyntax((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    private ProcurationSyntax(ASN1Sequence seq)
    {
        if(seq.size() < 1 || seq.size() > 3)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        Enumeration e = seq.getObjects();
        do
        {
            if(!e.hasMoreElements())
                break;
            ASN1TaggedObject o = ASN1TaggedObject.getInstance(e.nextElement());
            switch(o.getTagNo())
            {
            case 1: // '\001'
                country = DERPrintableString.getInstance(o, true).getString();
                break;

            case 2: // '\002'
                typeOfSubstitution = DirectoryString.getInstance(o, true);
                break;

            case 3: // '\003'
                ASN1Encodable signingFor = o.getObject();
                if(signingFor instanceof ASN1TaggedObject)
                    thirdPerson = GeneralName.getInstance(signingFor);
                else
                    certRef = IssuerSerial.getInstance(signingFor);
                break;

            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Bad tag number: ").append(o.getTagNo()).toString());
            }
        } while(true);
    }

    public ProcurationSyntax(String country, DirectoryString typeOfSubstitution, IssuerSerial certRef)
    {
        this.country = country;
        this.typeOfSubstitution = typeOfSubstitution;
        thirdPerson = null;
        this.certRef = certRef;
    }

    public ProcurationSyntax(String country, DirectoryString typeOfSubstitution, GeneralName thirdPerson)
    {
        this.country = country;
        this.typeOfSubstitution = typeOfSubstitution;
        this.thirdPerson = thirdPerson;
        certRef = null;
    }

    public String getCountry()
    {
        return country;
    }

    public DirectoryString getTypeOfSubstitution()
    {
        return typeOfSubstitution;
    }

    public GeneralName getThirdPerson()
    {
        return thirdPerson;
    }

    public IssuerSerial getCertRef()
    {
        return certRef;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector vec = new ASN1EncodableVector();
        if(country != null)
            vec.add(new DERTaggedObject(true, 1, new DERPrintableString(country, true)));
        if(typeOfSubstitution != null)
            vec.add(new DERTaggedObject(true, 2, typeOfSubstitution));
        if(thirdPerson != null)
            vec.add(new DERTaggedObject(true, 3, thirdPerson));
        else
            vec.add(new DERTaggedObject(true, 3, certRef));
        return new DERSequence(vec);
    }

    private String country;
    private DirectoryString typeOfSubstitution;
    private GeneralName thirdPerson;
    private IssuerSerial certRef;
}
