// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RevReqContent.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            RevDetails

public class RevReqContent extends ASN1Object
{

    private RevReqContent(ASN1Sequence seq)
    {
        content = seq;
    }

    public static RevReqContent getInstance(Object o)
    {
        if(o instanceof RevReqContent)
            return (RevReqContent)o;
        if(o != null)
            return new RevReqContent(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public RevReqContent(RevDetails revDetails)
    {
        content = new DERSequence(revDetails);
    }

    public RevReqContent(RevDetails revDetailsArray[])
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i != revDetailsArray.length; i++)
            v.add(revDetailsArray[i]);

        content = new DERSequence(v);
    }

    public RevDetails[] toRevDetailsArray()
    {
        RevDetails result[] = new RevDetails[content.size()];
        for(int i = 0; i != result.length; i++)
            result[i] = RevDetails.getInstance(content.getObjectAt(i));

        return result;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return content;
    }

    private ASN1Sequence content;
}
