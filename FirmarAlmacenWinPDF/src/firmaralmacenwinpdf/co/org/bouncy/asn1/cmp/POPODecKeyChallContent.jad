// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   POPODecKeyChallContent.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            Challenge

public class POPODecKeyChallContent extends ASN1Object
{

    private POPODecKeyChallContent(ASN1Sequence seq)
    {
        content = seq;
    }

    public static POPODecKeyChallContent getInstance(Object o)
    {
        if(o instanceof POPODecKeyChallContent)
            return (POPODecKeyChallContent)o;
        if(o != null)
            return new POPODecKeyChallContent(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public Challenge[] toChallengeArray()
    {
        Challenge result[] = new Challenge[content.size()];
        for(int i = 0; i != result.length; i++)
            result[i] = Challenge.getInstance(content.getObjectAt(i));

        return result;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return content;
    }

    private ASN1Sequence content;
}
