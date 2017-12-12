// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Pfx.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.pkcs:
//            PKCSObjectIdentifiers, ContentInfo, MacData

public class Pfx extends ASN1Object
    implements PKCSObjectIdentifiers
{

    private Pfx(ASN1Sequence seq)
    {
        macData = null;
        BigInteger version = ((ASN1Integer)seq.getObjectAt(0)).getValue();
        if(version.intValue() != 3)
            throw new IllegalArgumentException("wrong version for PFX PDU");
        contentInfo = ContentInfo.getInstance(seq.getObjectAt(1));
        if(seq.size() == 3)
            macData = MacData.getInstance(seq.getObjectAt(2));
    }

    public static Pfx getInstance(Object obj)
    {
        if(obj instanceof Pfx)
            return (Pfx)obj;
        if(obj != null)
            return new Pfx(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public Pfx(ContentInfo contentInfo, MacData macData)
    {
        this.macData = null;
        this.contentInfo = contentInfo;
        this.macData = macData;
    }

    public ContentInfo getAuthSafe()
    {
        return contentInfo;
    }

    public MacData getMacData()
    {
        return macData;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(3L));
        v.add(contentInfo);
        if(macData != null)
            v.add(macData);
        return new BERSequence(v);
    }

    private ContentInfo contentInfo;
    private MacData macData;
}
