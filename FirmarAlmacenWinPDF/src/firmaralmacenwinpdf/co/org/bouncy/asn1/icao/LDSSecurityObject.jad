// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LDSSecurityObject.java

package co.org.bouncy.asn1.icao;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import java.math.BigInteger;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.icao:
//            DataGroupHash, ICAOObjectIdentifiers, LDSVersionInfo

public class LDSSecurityObject extends ASN1Object
    implements ICAOObjectIdentifiers
{

    public static LDSSecurityObject getInstance(Object obj)
    {
        if(obj instanceof LDSSecurityObject)
            return (LDSSecurityObject)obj;
        if(obj != null)
            return new LDSSecurityObject(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private LDSSecurityObject(ASN1Sequence seq)
    {
        version = new ASN1Integer(0L);
        if(seq == null || seq.size() == 0)
            throw new IllegalArgumentException("null or empty sequence passed.");
        Enumeration e = seq.getObjects();
        version = ASN1Integer.getInstance(e.nextElement());
        digestAlgorithmIdentifier = AlgorithmIdentifier.getInstance(e.nextElement());
        ASN1Sequence datagroupHashSeq = ASN1Sequence.getInstance(e.nextElement());
        if(version.getValue().intValue() == 1)
            versionInfo = LDSVersionInfo.getInstance(e.nextElement());
        checkDatagroupHashSeqSize(datagroupHashSeq.size());
        datagroupHash = new DataGroupHash[datagroupHashSeq.size()];
        for(int i = 0; i < datagroupHashSeq.size(); i++)
            datagroupHash[i] = DataGroupHash.getInstance(datagroupHashSeq.getObjectAt(i));

    }

    public LDSSecurityObject(AlgorithmIdentifier digestAlgorithmIdentifier, DataGroupHash datagroupHash[])
    {
        version = new ASN1Integer(0L);
        version = new ASN1Integer(0L);
        this.digestAlgorithmIdentifier = digestAlgorithmIdentifier;
        this.datagroupHash = datagroupHash;
        checkDatagroupHashSeqSize(datagroupHash.length);
    }

    public LDSSecurityObject(AlgorithmIdentifier digestAlgorithmIdentifier, DataGroupHash datagroupHash[], LDSVersionInfo versionInfo)
    {
        version = new ASN1Integer(0L);
        version = new ASN1Integer(1L);
        this.digestAlgorithmIdentifier = digestAlgorithmIdentifier;
        this.datagroupHash = datagroupHash;
        this.versionInfo = versionInfo;
        checkDatagroupHashSeqSize(datagroupHash.length);
    }

    private void checkDatagroupHashSeqSize(int size)
    {
        if(size < 2 || size > 16)
            throw new IllegalArgumentException("wrong size in DataGroupHashValues : not in (2..16)");
        else
            return;
    }

    public int getVersion()
    {
        return version.getValue().intValue();
    }

    public AlgorithmIdentifier getDigestAlgorithmIdentifier()
    {
        return digestAlgorithmIdentifier;
    }

    public DataGroupHash[] getDatagroupHash()
    {
        return datagroupHash;
    }

    public LDSVersionInfo getVersionInfo()
    {
        return versionInfo;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector seq = new ASN1EncodableVector();
        seq.add(version);
        seq.add(digestAlgorithmIdentifier);
        ASN1EncodableVector seqname = new ASN1EncodableVector();
        for(int i = 0; i < datagroupHash.length; i++)
            seqname.add(datagroupHash[i]);

        seq.add(new DERSequence(seqname));
        if(versionInfo != null)
            seq.add(versionInfo);
        return new DERSequence(seq);
    }

    public static final int ub_DataGroups = 16;
    private ASN1Integer version;
    private AlgorithmIdentifier digestAlgorithmIdentifier;
    private DataGroupHash datagroupHash[];
    private LDSVersionInfo versionInfo;
}
