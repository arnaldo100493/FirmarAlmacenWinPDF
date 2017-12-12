// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Target.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            GeneralName

public class Target extends ASN1Object
    implements ASN1Choice
{

    public static Target getInstance(Object obj)
    {
        if(obj == null || (obj instanceof Target))
            return (Target)obj;
        if(obj instanceof ASN1TaggedObject)
            return new Target((ASN1TaggedObject)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown object in factory: ").append(obj.getClass()).toString());
    }

    private Target(ASN1TaggedObject tagObj)
    {
        switch(tagObj.getTagNo())
        {
        case 0: // '\0'
            targName = GeneralName.getInstance(tagObj, true);
            break;

        case 1: // '\001'
            targGroup = GeneralName.getInstance(tagObj, true);
            break;

        default:
            throw new IllegalArgumentException((new StringBuilder()).append("unknown tag: ").append(tagObj.getTagNo()).toString());
        }
    }

    public Target(int type, GeneralName name)
    {
        this(((ASN1TaggedObject) (new DERTaggedObject(type, name))));
    }

    public GeneralName getTargetGroup()
    {
        return targGroup;
    }

    public GeneralName getTargetName()
    {
        return targName;
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(targName != null)
            return new DERTaggedObject(true, 0, targName);
        else
            return new DERTaggedObject(true, 1, targGroup);
    }

    public static final int targetName = 0;
    public static final int targetGroup = 1;
    private GeneralName targName;
    private GeneralName targGroup;
}
