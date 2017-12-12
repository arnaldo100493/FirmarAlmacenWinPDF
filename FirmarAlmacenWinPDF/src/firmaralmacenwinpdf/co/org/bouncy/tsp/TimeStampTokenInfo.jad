// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeStampTokenInfo.java

package co.org.bouncy.tsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.tsp.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.GeneralName;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;

// Referenced classes of package co.org.bouncy.tsp:
//            TSPException, GenTimeAccuracy

public class TimeStampTokenInfo
{

    TimeStampTokenInfo(TSTInfo tstInfo)
        throws TSPException, IOException
    {
        this.tstInfo = tstInfo;
        try
        {
            genTime = tstInfo.getGenTime().getDate();
        }
        catch(ParseException e)
        {
            throw new TSPException("unable to parse genTime field");
        }
    }

    public boolean isOrdered()
    {
        return tstInfo.getOrdering().isTrue();
    }

    public Accuracy getAccuracy()
    {
        return tstInfo.getAccuracy();
    }

    public Date getGenTime()
    {
        return genTime;
    }

    public GenTimeAccuracy getGenTimeAccuracy()
    {
        if(getAccuracy() != null)
            return new GenTimeAccuracy(getAccuracy());
        else
            return null;
    }

    public ASN1ObjectIdentifier getPolicy()
    {
        return tstInfo.getPolicy();
    }

    public BigInteger getSerialNumber()
    {
        return tstInfo.getSerialNumber().getValue();
    }

    public GeneralName getTsa()
    {
        return tstInfo.getTsa();
    }

    public BigInteger getNonce()
    {
        if(tstInfo.getNonce() != null)
            return tstInfo.getNonce().getValue();
        else
            return null;
    }

    public AlgorithmIdentifier getHashAlgorithm()
    {
        return tstInfo.getMessageImprint().getHashAlgorithm();
    }

    public ASN1ObjectIdentifier getMessageImprintAlgOID()
    {
        return tstInfo.getMessageImprint().getHashAlgorithm().getAlgorithm();
    }

    public byte[] getMessageImprintDigest()
    {
        return tstInfo.getMessageImprint().getHashedMessage();
    }

    public byte[] getEncoded()
        throws IOException
    {
        return tstInfo.getEncoded();
    }

    /**
     * @deprecated Method toTSTInfo is deprecated
     */

    public TSTInfo toTSTInfo()
    {
        return tstInfo;
    }

    public TSTInfo toASN1Structure()
    {
        return tstInfo;
    }

    TSTInfo tstInfo;
    Date genTime;
}
