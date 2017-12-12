// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   V2Form.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            GeneralNames, IssuerSerial, ObjectDigestInfo

public class V2Form extends ASN1Object
{

    public static V2Form getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static V2Form getInstance(Object obj)
    {
        if(obj instanceof V2Form)
            return (V2Form)obj;
        if(obj != null)
            return new V2Form(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public V2Form(GeneralNames issuerName)
    {
        this(issuerName, null, null);
    }

    public V2Form(GeneralNames issuerName, IssuerSerial baseCertificateID)
    {
        this(issuerName, baseCertificateID, null);
    }

    public V2Form(GeneralNames issuerName, ObjectDigestInfo objectDigestInfo)
    {
        this(issuerName, null, objectDigestInfo);
    }

    public V2Form(GeneralNames issuerName, IssuerSerial baseCertificateID, ObjectDigestInfo objectDigestInfo)
    {
        this.issuerName = issuerName;
        this.baseCertificateID = baseCertificateID;
        this.objectDigestInfo = objectDigestInfo;
    }

    /**
     * @deprecated Method V2Form is deprecated
     */

    public V2Form(ASN1Sequence seq)
    {
        if(seq.size() > 3)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        int index = 0;
        if(!(seq.getObjectAt(0) instanceof ASN1TaggedObject))
        {
            index++;
            issuerName = GeneralNames.getInstance(seq.getObjectAt(0));
        }
        for(int i = index; i != seq.size(); i++)
        {
            ASN1TaggedObject o = ASN1TaggedObject.getInstance(seq.getObjectAt(i));
            if(o.getTagNo() == 0)
            {
                baseCertificateID = IssuerSerial.getInstance(o, false);
                continue;
            }
            if(o.getTagNo() == 1)
                objectDigestInfo = ObjectDigestInfo.getInstance(o, false);
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Bad tag number: ").append(o.getTagNo()).toString());
        }

    }

    public GeneralNames getIssuerName()
    {
        return issuerName;
    }

    public IssuerSerial getBaseCertificateID()
    {
        return baseCertificateID;
    }

    public ObjectDigestInfo getObjectDigestInfo()
    {
        return objectDigestInfo;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(issuerName != null)
            v.add(issuerName);
        if(baseCertificateID != null)
            v.add(new DERTaggedObject(false, 0, baseCertificateID));
        if(objectDigestInfo != null)
            v.add(new DERTaggedObject(false, 1, objectDigestInfo));
        return new DERSequence(v);
    }

    GeneralNames issuerName;
    IssuerSerial baseCertificateID;
    ObjectDigestInfo objectDigestInfo;
}
