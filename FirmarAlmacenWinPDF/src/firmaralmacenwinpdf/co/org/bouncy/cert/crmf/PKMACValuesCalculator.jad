// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKMACValuesCalculator.java

package co.org.bouncy.cert.crmf;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

// Referenced classes of package co.org.bouncy.cert.crmf:
//            CRMFException

public interface PKMACValuesCalculator
{

    public abstract void setup(AlgorithmIdentifier algorithmidentifier, AlgorithmIdentifier algorithmidentifier1)
        throws CRMFException;

    public abstract byte[] calculateDigest(byte abyte0[])
        throws CRMFException;

    public abstract byte[] calculateMac(byte abyte0[], byte abyte1[])
        throws CRMFException;
}
