// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MessageImprintBuilder.java

package co.org.bouncy.dvcs;

import co.org.bouncy.asn1.x509.DigestInfo;
import co.org.bouncy.operator.DigestCalculator;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.dvcs:
//            MessageImprint, DVCSException

public class MessageImprintBuilder
{

    public MessageImprintBuilder(DigestCalculator digestCalculator)
    {
        this.digestCalculator = digestCalculator;
    }

    public MessageImprint build(byte message[])
        throws DVCSException
    {
        try
        {
            OutputStream dOut = digestCalculator.getOutputStream();
            dOut.write(message);
            dOut.close();
            return new MessageImprint(new DigestInfo(digestCalculator.getAlgorithmIdentifier(), digestCalculator.getDigest()));
        }
        catch(Exception e)
        {
            throw new DVCSException((new StringBuilder()).append("unable to build MessageImprint: ").append(e.getMessage()).toString(), e);
        }
    }

    private final DigestCalculator digestCalculator;
}
