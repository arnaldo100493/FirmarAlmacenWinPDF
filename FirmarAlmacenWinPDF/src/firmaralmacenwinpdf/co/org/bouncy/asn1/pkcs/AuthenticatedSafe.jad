// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AuthenticatedSafe.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.pkcs:
//            ContentInfo

public class AuthenticatedSafe extends ASN1Object
{

    private AuthenticatedSafe(ASN1Sequence seq)
    {
        isBer = true;
        info = new ContentInfo[seq.size()];
        for(int i = 0; i != info.length; i++)
            info[i] = ContentInfo.getInstance(seq.getObjectAt(i));

        isBer = seq instanceof BERSequence;
    }

    public static AuthenticatedSafe getInstance(Object o)
    {
        if(o instanceof AuthenticatedSafe)
            return (AuthenticatedSafe)o;
        if(o != null)
            return new AuthenticatedSafe(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public AuthenticatedSafe(ContentInfo info[])
    {
        isBer = true;
        this.info = info;
    }

    public ContentInfo[] getContentInfo()
    {
        return info;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i != info.length; i++)
            v.add(info[i]);

        if(isBer)
            return new BERSequence(v);
        else
            return new DLSequence(v);
    }

    private ContentInfo info[];
    private boolean isBer;
}
