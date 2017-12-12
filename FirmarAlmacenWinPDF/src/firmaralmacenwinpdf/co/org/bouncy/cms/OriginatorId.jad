// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OriginatorId.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Selector;
import java.math.BigInteger;

class OriginatorId
    implements Selector
{

    public OriginatorId(byte subjectKeyId[])
    {
        setSubjectKeyID(subjectKeyId);
    }

    private void setSubjectKeyID(byte subjectKeyId[])
    {
        this.subjectKeyId = subjectKeyId;
    }

    public OriginatorId(X500Name issuer, BigInteger serialNumber)
    {
        setIssuerAndSerial(issuer, serialNumber);
    }

    private void setIssuerAndSerial(X500Name issuer, BigInteger serialNumber)
    {
        this.issuer = issuer;
        this.serialNumber = serialNumber;
    }

    public OriginatorId(X500Name issuer, BigInteger serialNumber, byte subjectKeyId[])
    {
        setIssuerAndSerial(issuer, serialNumber);
        setSubjectKeyID(subjectKeyId);
    }

    public X500Name getIssuer()
    {
        return issuer;
    }

    public Object clone()
    {
        return new OriginatorId(issuer, serialNumber, subjectKeyId);
    }

    public int hashCode()
    {
        int code = Arrays.hashCode(subjectKeyId);
        if(serialNumber != null)
            code ^= serialNumber.hashCode();
        if(issuer != null)
            code ^= issuer.hashCode();
        return code;
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof OriginatorId))
        {
            return false;
        } else
        {
            OriginatorId id = (OriginatorId)o;
            return Arrays.areEqual(subjectKeyId, id.subjectKeyId) && equalsObj(serialNumber, id.serialNumber) && equalsObj(issuer, id.issuer);
        }
    }

    private boolean equalsObj(Object a, Object b)
    {
        return a == null ? b == null : a.equals(b);
    }

    public boolean match(Object obj)
    {
        return false;
    }

    private byte subjectKeyId[];
    private X500Name issuer;
    private BigInteger serialNumber;
}
