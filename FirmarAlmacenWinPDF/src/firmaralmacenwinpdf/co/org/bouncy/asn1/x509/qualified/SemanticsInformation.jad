// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SemanticsInformation.java

package co.org.bouncy.asn1.x509.qualified;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.GeneralName;
import java.util.Enumeration;

public class SemanticsInformation extends ASN1Object
{

    public static SemanticsInformation getInstance(Object obj)
    {
        if(obj instanceof SemanticsInformation)
            return (SemanticsInformation)obj;
        if(obj != null)
            return new SemanticsInformation(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private SemanticsInformation(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        if(seq.size() < 1)
            throw new IllegalArgumentException("no objects in SemanticsInformation");
        Object object = e.nextElement();
        if(object instanceof ASN1ObjectIdentifier)
        {
            semanticsIdentifier = ASN1ObjectIdentifier.getInstance(object);
            if(e.hasMoreElements())
                object = e.nextElement();
            else
                object = null;
        }
        if(object != null)
        {
            ASN1Sequence generalNameSeq = ASN1Sequence.getInstance(object);
            nameRegistrationAuthorities = new GeneralName[generalNameSeq.size()];
            for(int i = 0; i < generalNameSeq.size(); i++)
                nameRegistrationAuthorities[i] = GeneralName.getInstance(generalNameSeq.getObjectAt(i));

        }
    }

    public SemanticsInformation(ASN1ObjectIdentifier semanticsIdentifier, GeneralName generalNames[])
    {
        this.semanticsIdentifier = semanticsIdentifier;
        nameRegistrationAuthorities = generalNames;
    }

    public SemanticsInformation(ASN1ObjectIdentifier semanticsIdentifier)
    {
        this.semanticsIdentifier = semanticsIdentifier;
        nameRegistrationAuthorities = null;
    }

    public SemanticsInformation(GeneralName generalNames[])
    {
        semanticsIdentifier = null;
        nameRegistrationAuthorities = generalNames;
    }

    public ASN1ObjectIdentifier getSemanticsIdentifier()
    {
        return semanticsIdentifier;
    }

    public GeneralName[] getNameRegistrationAuthorities()
    {
        return nameRegistrationAuthorities;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector seq = new ASN1EncodableVector();
        if(semanticsIdentifier != null)
            seq.add(semanticsIdentifier);
        if(nameRegistrationAuthorities != null)
        {
            ASN1EncodableVector seqname = new ASN1EncodableVector();
            for(int i = 0; i < nameRegistrationAuthorities.length; i++)
                seqname.add(nameRegistrationAuthorities[i]);

            seq.add(new DERSequence(seqname));
        }
        return new DERSequence(seq);
    }

    private ASN1ObjectIdentifier semanticsIdentifier;
    private GeneralName nameRegistrationAuthorities[];
}
