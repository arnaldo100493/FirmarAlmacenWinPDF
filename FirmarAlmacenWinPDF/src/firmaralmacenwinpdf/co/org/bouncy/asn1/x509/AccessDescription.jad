// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AccessDescription.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            GeneralName

public class AccessDescription extends ASN1Object
{

    public static AccessDescription getInstance(Object obj)
    {
        if(obj instanceof AccessDescription)
            return (AccessDescription)obj;
        if(obj != null)
            return new AccessDescription(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private AccessDescription(ASN1Sequence seq)
    {
        accessMethod = null;
        accessLocation = null;
        if(seq.size() != 2)
        {
            throw new IllegalArgumentException("wrong number of elements in sequence");
        } else
        {
            accessMethod = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
            accessLocation = GeneralName.getInstance(seq.getObjectAt(1));
            return;
        }
    }

    public AccessDescription(ASN1ObjectIdentifier oid, GeneralName location)
    {
        accessMethod = null;
        accessLocation = null;
        accessMethod = oid;
        accessLocation = location;
    }

    public ASN1ObjectIdentifier getAccessMethod()
    {
        return accessMethod;
    }

    public GeneralName getAccessLocation()
    {
        return accessLocation;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector accessDescription = new ASN1EncodableVector();
        accessDescription.add(accessMethod);
        accessDescription.add(accessLocation);
        return new DERSequence(accessDescription);
    }

    public String toString()
    {
        return (new StringBuilder()).append("AccessDescription: Oid(").append(accessMethod.getId()).append(")").toString();
    }

    public static final ASN1ObjectIdentifier id_ad_caIssuers = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.48.2");
    public static final ASN1ObjectIdentifier id_ad_ocsp = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.48.1");
    ASN1ObjectIdentifier accessMethod;
    GeneralName accessLocation;

}
