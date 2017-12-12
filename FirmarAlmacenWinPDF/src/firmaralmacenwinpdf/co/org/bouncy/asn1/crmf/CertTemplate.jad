// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertTemplate.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.*;
import java.math.BigInteger;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.crmf:
//            OptionalValidity

public class CertTemplate extends ASN1Object
{

    private CertTemplate(ASN1Sequence seq)
    {
        this.seq = seq;
        Enumeration en = seq.getObjects();
        do
        {
            if(!en.hasMoreElements())
                break;
            ASN1TaggedObject tObj = (ASN1TaggedObject)en.nextElement();
            switch(tObj.getTagNo())
            {
            case 0: // '\0'
                version = ASN1Integer.getInstance(tObj, false);
                break;

            case 1: // '\001'
                serialNumber = ASN1Integer.getInstance(tObj, false);
                break;

            case 2: // '\002'
                signingAlg = AlgorithmIdentifier.getInstance(tObj, false);
                break;

            case 3: // '\003'
                issuer = X500Name.getInstance(tObj, true);
                break;

            case 4: // '\004'
                validity = OptionalValidity.getInstance(ASN1Sequence.getInstance(tObj, false));
                break;

            case 5: // '\005'
                subject = X500Name.getInstance(tObj, true);
                break;

            case 6: // '\006'
                publicKey = SubjectPublicKeyInfo.getInstance(tObj, false);
                break;

            case 7: // '\007'
                issuerUID = DERBitString.getInstance(tObj, false);
                break;

            case 8: // '\b'
                subjectUID = DERBitString.getInstance(tObj, false);
                break;

            case 9: // '\t'
                extensions = Extensions.getInstance(tObj, false);
                break;

            default:
                throw new IllegalArgumentException((new StringBuilder()).append("unknown tag: ").append(tObj.getTagNo()).toString());
            }
        } while(true);
    }

    public static CertTemplate getInstance(Object o)
    {
        if(o instanceof CertTemplate)
            return (CertTemplate)o;
        if(o != null)
            return new CertTemplate(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public int getVersion()
    {
        return version.getValue().intValue();
    }

    public ASN1Integer getSerialNumber()
    {
        return serialNumber;
    }

    public AlgorithmIdentifier getSigningAlg()
    {
        return signingAlg;
    }

    public X500Name getIssuer()
    {
        return issuer;
    }

    public OptionalValidity getValidity()
    {
        return validity;
    }

    public X500Name getSubject()
    {
        return subject;
    }

    public SubjectPublicKeyInfo getPublicKey()
    {
        return publicKey;
    }

    public DERBitString getIssuerUID()
    {
        return issuerUID;
    }

    public DERBitString getSubjectUID()
    {
        return subjectUID;
    }

    public Extensions getExtensions()
    {
        return extensions;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return seq;
    }

    private ASN1Sequence seq;
    private ASN1Integer version;
    private ASN1Integer serialNumber;
    private AlgorithmIdentifier signingAlg;
    private X500Name issuer;
    private OptionalValidity validity;
    private X500Name subject;
    private SubjectPublicKeyInfo publicKey;
    private DERBitString issuerUID;
    private DERBitString subjectUID;
    private Extensions extensions;
}
