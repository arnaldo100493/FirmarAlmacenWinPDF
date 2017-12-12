// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TBSCertList.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;
import java.math.BigInteger;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            Time, AlgorithmIdentifier, Extensions

public class TBSCertList extends ASN1Object
{
    private class EmptyEnumeration
        implements Enumeration
    {

        public boolean hasMoreElements()
        {
            return false;
        }

        public Object nextElement()
        {
            return null;
        }

        final TBSCertList this$0;

        private EmptyEnumeration()
        {
            this$0 = TBSCertList.this;
            super();
        }

    }

    private class RevokedCertificatesEnumeration
        implements Enumeration
    {

        public boolean hasMoreElements()
        {
            return en.hasMoreElements();
        }

        public Object nextElement()
        {
            return CRLEntry.getInstance(en.nextElement());
        }

        private final Enumeration en;
        final TBSCertList this$0;

        RevokedCertificatesEnumeration(Enumeration en)
        {
            this$0 = TBSCertList.this;
            super();
            this.en = en;
        }
    }

    public static class CRLEntry extends ASN1Object
    {

        public static CRLEntry getInstance(Object o)
        {
            if(o instanceof CRLEntry)
                return (CRLEntry)o;
            if(o != null)
                return new CRLEntry(ASN1Sequence.getInstance(o));
            else
                return null;
        }

        public ASN1Integer getUserCertificate()
        {
            return ASN1Integer.getInstance(seq.getObjectAt(0));
        }

        public Time getRevocationDate()
        {
            return Time.getInstance(seq.getObjectAt(1));
        }

        public Extensions getExtensions()
        {
            if(crlEntryExtensions == null && seq.size() == 3)
                crlEntryExtensions = Extensions.getInstance(seq.getObjectAt(2));
            return crlEntryExtensions;
        }

        public ASN1Primitive toASN1Primitive()
        {
            return seq;
        }

        public boolean hasExtensions()
        {
            return seq.size() == 3;
        }

        ASN1Sequence seq;
        Extensions crlEntryExtensions;

        private CRLEntry(ASN1Sequence seq)
        {
            if(seq.size() < 2 || seq.size() > 3)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
            } else
            {
                this.seq = seq;
                return;
            }
        }
    }


    public static TBSCertList getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static TBSCertList getInstance(Object obj)
    {
        if(obj instanceof TBSCertList)
            return (TBSCertList)obj;
        if(obj != null)
            return new TBSCertList(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public TBSCertList(ASN1Sequence seq)
    {
        if(seq.size() < 3 || seq.size() > 7)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        int seqPos = 0;
        if(seq.getObjectAt(seqPos) instanceof ASN1Integer)
            version = ASN1Integer.getInstance(seq.getObjectAt(seqPos++));
        else
            version = null;
        signature = AlgorithmIdentifier.getInstance(seq.getObjectAt(seqPos++));
        issuer = X500Name.getInstance(seq.getObjectAt(seqPos++));
        thisUpdate = Time.getInstance(seq.getObjectAt(seqPos++));
        if(seqPos < seq.size() && ((seq.getObjectAt(seqPos) instanceof DERUTCTime) || (seq.getObjectAt(seqPos) instanceof DERGeneralizedTime) || (seq.getObjectAt(seqPos) instanceof Time)))
            nextUpdate = Time.getInstance(seq.getObjectAt(seqPos++));
        if(seqPos < seq.size() && !(seq.getObjectAt(seqPos) instanceof DERTaggedObject))
            revokedCertificates = ASN1Sequence.getInstance(seq.getObjectAt(seqPos++));
        if(seqPos < seq.size() && (seq.getObjectAt(seqPos) instanceof DERTaggedObject))
            crlExtensions = Extensions.getInstance(ASN1Sequence.getInstance((ASN1TaggedObject)seq.getObjectAt(seqPos), true));
    }

    public int getVersionNumber()
    {
        if(version == null)
            return 1;
        else
            return version.getValue().intValue() + 1;
    }

    public ASN1Integer getVersion()
    {
        return version;
    }

    public AlgorithmIdentifier getSignature()
    {
        return signature;
    }

    public X500Name getIssuer()
    {
        return issuer;
    }

    public Time getThisUpdate()
    {
        return thisUpdate;
    }

    public Time getNextUpdate()
    {
        return nextUpdate;
    }

    public CRLEntry[] getRevokedCertificates()
    {
        if(revokedCertificates == null)
            return new CRLEntry[0];
        CRLEntry entries[] = new CRLEntry[revokedCertificates.size()];
        for(int i = 0; i < entries.length; i++)
            entries[i] = CRLEntry.getInstance(revokedCertificates.getObjectAt(i));

        return entries;
    }

    public Enumeration getRevokedCertificateEnumeration()
    {
        if(revokedCertificates == null)
            return new EmptyEnumeration();
        else
            return new RevokedCertificatesEnumeration(revokedCertificates.getObjects());
    }

    public Extensions getExtensions()
    {
        return crlExtensions;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(version != null)
            v.add(version);
        v.add(signature);
        v.add(issuer);
        v.add(thisUpdate);
        if(nextUpdate != null)
            v.add(nextUpdate);
        if(revokedCertificates != null)
            v.add(revokedCertificates);
        if(crlExtensions != null)
            v.add(new DERTaggedObject(0, crlExtensions));
        return new DERSequence(v);
    }

    ASN1Integer version;
    AlgorithmIdentifier signature;
    X500Name issuer;
    Time thisUpdate;
    Time nextUpdate;
    ASN1Sequence revokedCertificates;
    Extensions crlExtensions;
}
