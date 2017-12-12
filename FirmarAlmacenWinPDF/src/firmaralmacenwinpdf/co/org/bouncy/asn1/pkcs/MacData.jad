// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MacData.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.DigestInfo;
import java.math.BigInteger;

public class MacData extends ASN1Object
{

    public static MacData getInstance(Object obj)
    {
        if(obj instanceof MacData)
            return (MacData)obj;
        if(obj != null)
            return new MacData(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private MacData(ASN1Sequence seq)
    {
        digInfo = DigestInfo.getInstance(seq.getObjectAt(0));
        salt = ((ASN1OctetString)seq.getObjectAt(1)).getOctets();
        if(seq.size() == 3)
            iterationCount = ((ASN1Integer)seq.getObjectAt(2)).getValue();
        else
            iterationCount = ONE;
    }

    public MacData(DigestInfo digInfo, byte salt[], int iterationCount)
    {
        this.digInfo = digInfo;
        this.salt = salt;
        this.iterationCount = BigInteger.valueOf(iterationCount);
    }

    public DigestInfo getMac()
    {
        return digInfo;
    }

    public byte[] getSalt()
    {
        return salt;
    }

    public BigInteger getIterationCount()
    {
        return iterationCount;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(digInfo);
        v.add(new DEROctetString(salt));
        if(!iterationCount.equals(ONE))
            v.add(new ASN1Integer(iterationCount));
        return new DERSequence(v);
    }

    private static final BigInteger ONE = BigInteger.valueOf(1L);
    DigestInfo digInfo;
    byte salt[];
    BigInteger iterationCount;

}
