// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BERApplicationSpecificParser.java

package co.org.bouncy.asn1;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            BERApplicationSpecific, ASN1ParsingException, ASN1ApplicationSpecificParser, ASN1StreamParser, 
//            ASN1Encodable, ASN1Primitive

public class BERApplicationSpecificParser
    implements ASN1ApplicationSpecificParser
{

    BERApplicationSpecificParser(int tag, ASN1StreamParser parser)
    {
        this.tag = tag;
        this.parser = parser;
    }

    public ASN1Encodable readObject()
        throws IOException
    {
        return parser.readObject();
    }

    public ASN1Primitive getLoadedObject()
        throws IOException
    {
        return new BERApplicationSpecific(tag, parser.readVector());
    }

    public ASN1Primitive toASN1Primitive()
    {
        try
        {
            return getLoadedObject();
        }
        catch(IOException e)
        {
            throw new ASN1ParsingException(e.getMessage(), e);
        }
    }

    private final int tag;
    private final ASN1StreamParser parser;
}
