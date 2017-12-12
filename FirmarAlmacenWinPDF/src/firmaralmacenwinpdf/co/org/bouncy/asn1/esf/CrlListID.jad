// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CrlListID.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.esf:
//            CrlValidatedID

public class CrlListID extends ASN1Object
{

    public static CrlListID getInstance(Object obj)
    {
        if(obj instanceof CrlListID)
            return (CrlListID)obj;
        if(obj != null)
            return new CrlListID(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private CrlListID(ASN1Sequence seq)
    {
        crls = (ASN1Sequence)seq.getObjectAt(0);
        for(Enumeration e = crls.getObjects(); e.hasMoreElements(); CrlValidatedID.getInstance(e.nextElement()));
    }

    public CrlListID(CrlValidatedID crls[])
    {
        this.crls = new DERSequence(crls);
    }

    public CrlValidatedID[] getCrls()
    {
        CrlValidatedID result[] = new CrlValidatedID[crls.size()];
        for(int idx = 0; idx < result.length; idx++)
            result[idx] = CrlValidatedID.getInstance(crls.getObjectAt(idx));

        return result;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return new DERSequence(crls);
    }

    private ASN1Sequence crls;
}
