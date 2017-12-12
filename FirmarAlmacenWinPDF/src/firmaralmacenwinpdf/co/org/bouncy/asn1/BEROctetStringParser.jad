// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BEROctetStringParser.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.io.Streams;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package co.org.bouncy.asn1:
//            ConstructedOctetStream, BEROctetString, ASN1ParsingException, ASN1OctetStringParser, 
//            ASN1StreamParser, ASN1Primitive

public class BEROctetStringParser
    implements ASN1OctetStringParser
{

    BEROctetStringParser(ASN1StreamParser parser)
    {
        _parser = parser;
    }

    public InputStream getOctetStream()
    {
        return new ConstructedOctetStream(_parser);
    }

    public ASN1Primitive getLoadedObject()
        throws IOException
    {
        return new BEROctetString(Streams.readAll(getOctetStream()));
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

    private ASN1StreamParser _parser;
}
