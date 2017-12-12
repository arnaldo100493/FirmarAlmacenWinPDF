// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AuthorityInformationAccess.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            AccessDescription, GeneralName

public class AuthorityInformationAccess extends ASN1Object
{

    public static AuthorityInformationAccess getInstance(Object obj)
    {
        if(obj instanceof AuthorityInformationAccess)
            return (AuthorityInformationAccess)obj;
        if(obj != null)
            return new AuthorityInformationAccess(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private AuthorityInformationAccess(ASN1Sequence seq)
    {
        if(seq.size() < 1)
            throw new IllegalArgumentException("sequence may not be empty");
        descriptions = new AccessDescription[seq.size()];
        for(int i = 0; i != seq.size(); i++)
            descriptions[i] = AccessDescription.getInstance(seq.getObjectAt(i));

    }

    public AuthorityInformationAccess(ASN1ObjectIdentifier oid, GeneralName location)
    {
        descriptions = new AccessDescription[1];
        descriptions[0] = new AccessDescription(oid, location);
    }

    public AccessDescription[] getAccessDescriptions()
    {
        return descriptions;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector vec = new ASN1EncodableVector();
        for(int i = 0; i != descriptions.length; i++)
            vec.add(descriptions[i]);

        return new DERSequence(vec);
    }

    public String toString()
    {
        return (new StringBuilder()).append("AuthorityInformationAccess: Oid(").append(descriptions[0].getAccessMethod().getId()).append(")").toString();
    }

    private AccessDescription descriptions[];
}
