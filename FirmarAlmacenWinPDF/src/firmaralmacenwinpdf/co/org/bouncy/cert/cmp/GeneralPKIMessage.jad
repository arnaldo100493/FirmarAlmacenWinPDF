// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneralPKIMessage.java

package co.org.bouncy.cert.cmp;

import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.cmp.*;
import co.org.bouncy.cert.CertIOException;
import java.io.IOException;

public class GeneralPKIMessage
{

    private static PKIMessage parseBytes(byte encoding[])
        throws IOException
    {
        try
        {
            return PKIMessage.getInstance(ASN1Primitive.fromByteArray(encoding));
        }
        catch(ClassCastException e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
        catch(IllegalArgumentException e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
    }

    public GeneralPKIMessage(byte encoding[])
        throws IOException
    {
        this(parseBytes(encoding));
    }

    public GeneralPKIMessage(PKIMessage pkiMessage)
    {
        this.pkiMessage = pkiMessage;
    }

    public PKIHeader getHeader()
    {
        return pkiMessage.getHeader();
    }

    public PKIBody getBody()
    {
        return pkiMessage.getBody();
    }

    public boolean hasProtection()
    {
        return pkiMessage.getHeader().getProtectionAlg() != null;
    }

    public PKIMessage toASN1Structure()
    {
        return pkiMessage;
    }

    private final PKIMessage pkiMessage;
}
