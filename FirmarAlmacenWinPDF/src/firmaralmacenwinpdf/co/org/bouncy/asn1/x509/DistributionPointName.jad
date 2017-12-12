// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DistributionPointName.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            GeneralNames

public class DistributionPointName extends ASN1Object
    implements ASN1Choice
{

    public static DistributionPointName getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1TaggedObject.getInstance(obj, true));
    }

    public static DistributionPointName getInstance(Object obj)
    {
        if(obj == null || (obj instanceof DistributionPointName))
            return (DistributionPointName)obj;
        if(obj instanceof ASN1TaggedObject)
            return new DistributionPointName((ASN1TaggedObject)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown object in factory: ").append(obj.getClass().getName()).toString());
    }

    public DistributionPointName(int type, ASN1Encodable name)
    {
        this.type = type;
        this.name = name;
    }

    public DistributionPointName(GeneralNames name)
    {
        this(0, ((ASN1Encodable) (name)));
    }

    public int getType()
    {
        return type;
    }

    public ASN1Encodable getName()
    {
        return name;
    }

    public DistributionPointName(ASN1TaggedObject obj)
    {
        type = obj.getTagNo();
        if(type == 0)
            name = GeneralNames.getInstance(obj, false);
        else
            name = ASN1Set.getInstance(obj, false);
    }

    public ASN1Primitive toASN1Primitive()
    {
        return new DERTaggedObject(false, type, name);
    }

    public String toString()
    {
        String sep = System.getProperty("line.separator");
        StringBuffer buf = new StringBuffer();
        buf.append("DistributionPointName: [");
        buf.append(sep);
        if(type == 0)
            appendObject(buf, sep, "fullName", name.toString());
        else
            appendObject(buf, sep, "nameRelativeToCRLIssuer", name.toString());
        buf.append("]");
        buf.append(sep);
        return buf.toString();
    }

    private void appendObject(StringBuffer buf, String sep, String name, String value)
    {
        String indent = "    ";
        buf.append(indent);
        buf.append(name);
        buf.append(":");
        buf.append(sep);
        buf.append(indent);
        buf.append(indent);
        buf.append(value);
        buf.append(sep);
    }

    ASN1Encodable name;
    int type;
    public static final int FULL_NAME = 0;
    public static final int NAME_RELATIVE_TO_CRL_ISSUER = 1;
}
