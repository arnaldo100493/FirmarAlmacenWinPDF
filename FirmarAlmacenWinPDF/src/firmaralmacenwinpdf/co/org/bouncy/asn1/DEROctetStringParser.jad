// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DEROctetStringParser.java

package co.org.bouncy.asn1;

import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package co.org.bouncy.asn1:
//            DEROctetString, ASN1ParsingException, ASN1OctetStringParser, DefiniteLengthInputStream, 
//            ASN1Primitive

public class DEROctetStringParser
    implements ASN1OctetStringParser
{

    DEROctetStringParser(DefiniteLengthInputStream stream)
    {
        this.stream = stream;
    }

    public InputStream getOctetStream()
    {
        return stream;
    }

    public ASN1Primitive getLoadedObject()
        throws IOException
    {
        return new DEROctetString(stream.toByteArray());
    }

    public ASN1Primitive toASN1Primitive()
    {
        try
        {
            return getLoadedObject();
        }
        catch(IOException e)
        {
            throw new ASN1ParsingException((new StringBuilder()).append("IOException converting stream to byte array: ").append(e.getMessage()).toString(), e);
        }
    }

    private DefiniteLengthInputStream stream;
}
