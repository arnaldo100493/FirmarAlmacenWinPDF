// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AttCertIssuer.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            V2Form, GeneralNames

public class AttCertIssuer extends ASN1Object
    implements ASN1Choice
{

    public static AttCertIssuer getInstance(Object obj)
    {
        if(obj == null || (obj instanceof AttCertIssuer))
            return (AttCertIssuer)obj;
        if(obj instanceof V2Form)
            return new AttCertIssuer(V2Form.getInstance(obj));
        if(obj instanceof GeneralNames)
            return new AttCertIssuer((GeneralNames)obj);
        if(obj instanceof ASN1TaggedObject)
            return new AttCertIssuer(V2Form.getInstance((ASN1TaggedObject)obj, false));
        if(obj instanceof ASN1Sequence)
            return new AttCertIssuer(GeneralNames.getInstance(obj));
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown object in factory: ").append(obj.getClass().getName()).toString());
    }

    public static AttCertIssuer getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(obj.getObject());
    }

    public AttCertIssuer(GeneralNames names)
    {
        obj = names;
        choiceObj = obj.toASN1Primitive();
    }

    public AttCertIssuer(V2Form v2Form)
    {
        obj = v2Form;
        choiceObj = new DERTaggedObject(false, 0, obj);
    }

    public ASN1Encodable getIssuer()
    {
        return obj;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return choiceObj;
    }

    ASN1Encodable obj;
    ASN1Primitive choiceObj;
}
