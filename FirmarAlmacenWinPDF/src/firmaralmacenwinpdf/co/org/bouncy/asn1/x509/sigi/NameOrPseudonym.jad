// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NameOrPseudonym.java

package co.org.bouncy.asn1.x509.sigi;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.DirectoryString;
import java.util.Enumeration;

public class NameOrPseudonym extends ASN1Object
    implements ASN1Choice
{

    public static NameOrPseudonym getInstance(Object obj)
    {
        if(obj == null || (obj instanceof NameOrPseudonym))
            return (NameOrPseudonym)obj;
        if(obj instanceof ASN1String)
            return new NameOrPseudonym(DirectoryString.getInstance(obj));
        if(obj instanceof ASN1Sequence)
            return new NameOrPseudonym((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public NameOrPseudonym(DirectoryString pseudonym)
    {
        this.pseudonym = pseudonym;
    }

    private NameOrPseudonym(ASN1Sequence seq)
    {
        if(seq.size() != 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        if(!(seq.getObjectAt(0) instanceof ASN1String))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad object encountered: ").append(seq.getObjectAt(0).getClass()).toString());
        } else
        {
            surname = DirectoryString.getInstance(seq.getObjectAt(0));
            givenName = ASN1Sequence.getInstance(seq.getObjectAt(1));
            return;
        }
    }

    public NameOrPseudonym(String pseudonym)
    {
        this(new DirectoryString(pseudonym));
    }

    public NameOrPseudonym(DirectoryString surname, ASN1Sequence givenName)
    {
        this.surname = surname;
        this.givenName = givenName;
    }

    public DirectoryString getPseudonym()
    {
        return pseudonym;
    }

    public DirectoryString getSurname()
    {
        return surname;
    }

    public DirectoryString[] getGivenName()
    {
        DirectoryString items[] = new DirectoryString[givenName.size()];
        int count = 0;
        for(Enumeration e = givenName.getObjects(); e.hasMoreElements();)
            items[count++] = DirectoryString.getInstance(e.nextElement());

        return items;
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(pseudonym != null)
        {
            return pseudonym.toASN1Primitive();
        } else
        {
            ASN1EncodableVector vec1 = new ASN1EncodableVector();
            vec1.add(surname);
            vec1.add(givenName);
            return new DERSequence(vec1);
        }
    }

    private DirectoryString pseudonym;
    private DirectoryString surname;
    private ASN1Sequence givenName;
}
