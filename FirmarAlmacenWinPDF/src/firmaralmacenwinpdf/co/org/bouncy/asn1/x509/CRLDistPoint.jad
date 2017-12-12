// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CRLDistPoint.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            DistributionPoint

public class CRLDistPoint extends ASN1Object
{

    public static CRLDistPoint getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static CRLDistPoint getInstance(Object obj)
    {
        if(obj instanceof CRLDistPoint)
            return (CRLDistPoint)obj;
        if(obj != null)
            return new CRLDistPoint(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private CRLDistPoint(ASN1Sequence seq)
    {
        this.seq = null;
        this.seq = seq;
    }

    public CRLDistPoint(DistributionPoint points[])
    {
        seq = null;
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i != points.length; i++)
            v.add(points[i]);

        seq = new DERSequence(v);
    }

    public DistributionPoint[] getDistributionPoints()
    {
        DistributionPoint dp[] = new DistributionPoint[seq.size()];
        for(int i = 0; i != seq.size(); i++)
            dp[i] = DistributionPoint.getInstance(seq.getObjectAt(i));

        return dp;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return seq;
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        String sep = System.getProperty("line.separator");
        buf.append("CRLDistPoint:");
        buf.append(sep);
        DistributionPoint dp[] = getDistributionPoints();
        for(int i = 0; i != dp.length; i++)
        {
            buf.append("    ");
            buf.append(dp[i]);
            buf.append(sep);
        }

        return buf.toString();
    }

    ASN1Sequence seq;
}
