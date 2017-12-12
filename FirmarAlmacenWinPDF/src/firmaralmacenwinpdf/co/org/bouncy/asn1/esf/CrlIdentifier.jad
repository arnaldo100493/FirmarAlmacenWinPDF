// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CrlIdentifier.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;
import java.math.BigInteger;

public class CrlIdentifier extends ASN1Object
{

    public static CrlIdentifier getInstance(Object obj)
    {
        if(obj instanceof CrlIdentifier)
            return (CrlIdentifier)obj;
        if(obj != null)
            return new CrlIdentifier(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private CrlIdentifier(ASN1Sequence seq)
    {
        if(seq.size() < 2 || seq.size() > 3)
            throw new IllegalArgumentException();
        crlIssuer = X500Name.getInstance(seq.getObjectAt(0));
        crlIssuedTime = ASN1UTCTime.getInstance(seq.getObjectAt(1));
        if(seq.size() > 2)
            crlNumber = ASN1Integer.getInstance(seq.getObjectAt(2));
    }

    public CrlIdentifier(X500Name crlIssuer, ASN1UTCTime crlIssuedTime)
    {
        this(crlIssuer, crlIssuedTime, null);
    }

    public CrlIdentifier(X500Name crlIssuer, ASN1UTCTime crlIssuedTime, BigInteger crlNumber)
    {
        this.crlIssuer = crlIssuer;
        this.crlIssuedTime = crlIssuedTime;
        if(null != crlNumber)
            this.crlNumber = new ASN1Integer(crlNumber);
    }

    public X500Name getCrlIssuer()
    {
        return crlIssuer;
    }

    public ASN1UTCTime getCrlIssuedTime()
    {
        return crlIssuedTime;
    }

    public BigInteger getCrlNumber()
    {
        if(null == crlNumber)
            return null;
        else
            return crlNumber.getValue();
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(crlIssuer.toASN1Primitive());
        v.add(crlIssuedTime);
        if(null != crlNumber)
            v.add(crlNumber);
        return new DERSequence(v);
    }

    private X500Name crlIssuer;
    private ASN1UTCTime crlIssuedTime;
    private ASN1Integer crlNumber;
}
