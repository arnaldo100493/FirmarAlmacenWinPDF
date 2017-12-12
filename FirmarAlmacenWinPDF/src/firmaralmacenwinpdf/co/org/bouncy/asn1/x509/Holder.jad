// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Holder.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            IssuerSerial, GeneralNames, ObjectDigestInfo

public class Holder extends ASN1Object
{

    public static Holder getInstance(Object obj)
    {
        if(obj instanceof Holder)
            return (Holder)obj;
        if(obj instanceof ASN1TaggedObject)
            return new Holder(ASN1TaggedObject.getInstance(obj));
        if(obj != null)
            return new Holder(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private Holder(ASN1TaggedObject tagObj)
    {
        version = 1;
        switch(tagObj.getTagNo())
        {
        case 0: // '\0'
            baseCertificateID = IssuerSerial.getInstance(tagObj, false);
            break;

        case 1: // '\001'
            entityName = GeneralNames.getInstance(tagObj, false);
            break;

        default:
            throw new IllegalArgumentException("unknown tag in Holder");
        }
        version = 0;
    }

    private Holder(ASN1Sequence seq)
    {
        version = 1;
        if(seq.size() > 3)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        for(int i = 0; i != seq.size(); i++)
        {
            ASN1TaggedObject tObj = ASN1TaggedObject.getInstance(seq.getObjectAt(i));
            switch(tObj.getTagNo())
            {
            case 0: // '\0'
                baseCertificateID = IssuerSerial.getInstance(tObj, false);
                break;

            case 1: // '\001'
                entityName = GeneralNames.getInstance(tObj, false);
                break;

            case 2: // '\002'
                objectDigestInfo = ObjectDigestInfo.getInstance(tObj, false);
                break;

            default:
                throw new IllegalArgumentException("unknown tag in Holder");
            }
        }

        version = 1;
    }

    public Holder(IssuerSerial baseCertificateID)
    {
        this(baseCertificateID, 1);
    }

    public Holder(IssuerSerial baseCertificateID, int version)
    {
        this.version = 1;
        this.baseCertificateID = baseCertificateID;
        this.version = version;
    }

    public int getVersion()
    {
        return version;
    }

    public Holder(GeneralNames entityName)
    {
        this(entityName, 1);
    }

    public Holder(GeneralNames entityName, int version)
    {
        this.version = 1;
        this.entityName = entityName;
        this.version = version;
    }

    public Holder(ObjectDigestInfo objectDigestInfo)
    {
        version = 1;
        this.objectDigestInfo = objectDigestInfo;
    }

    public IssuerSerial getBaseCertificateID()
    {
        return baseCertificateID;
    }

    public GeneralNames getEntityName()
    {
        return entityName;
    }

    public ObjectDigestInfo getObjectDigestInfo()
    {
        return objectDigestInfo;
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(version == 1)
        {
            ASN1EncodableVector v = new ASN1EncodableVector();
            if(baseCertificateID != null)
                v.add(new DERTaggedObject(false, 0, baseCertificateID));
            if(entityName != null)
                v.add(new DERTaggedObject(false, 1, entityName));
            if(objectDigestInfo != null)
                v.add(new DERTaggedObject(false, 2, objectDigestInfo));
            return new DERSequence(v);
        }
        if(entityName != null)
            return new DERTaggedObject(false, 1, entityName);
        else
            return new DERTaggedObject(false, 0, baseCertificateID);
    }

    public static final int V1_CERTIFICATE_HOLDER = 0;
    public static final int V2_CERTIFICATE_HOLDER = 1;
    IssuerSerial baseCertificateID;
    GeneralNames entityName;
    ObjectDigestInfo objectDigestInfo;
    private int version;
}
