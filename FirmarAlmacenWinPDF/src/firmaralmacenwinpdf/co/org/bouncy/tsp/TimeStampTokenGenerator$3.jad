// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeStampTokenGenerator.java

package co.org.bouncy.tsp;

import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.DigestCalculator;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Referenced classes of package co.org.bouncy.tsp:
//            TimeStampTokenGenerator

class TimeStampTokenGenerator$3
    implements DigestCalculator
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
    }

    public OutputStream getOutputStream()
    {
        return bOut;
    }

    public byte[] getDigest()
    {
        try
        {
            return MessageDigest.getInstance("SHA-1").digest(bOut.toByteArray());
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new IllegalStateException((new StringBuilder()).append("cannot find sha-1: ").append(e.getMessage()).toString());
        }
    }

    private ByteArrayOutputStream bOut;

    TimeStampTokenGenerator$3()
    {
        bOut = new ByteArrayOutputStream();
    }
}
