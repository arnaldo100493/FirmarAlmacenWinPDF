// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BERSetParser.java

package co.org.bouncy.asn1;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            BERSet, ASN1ParsingException, ASN1SetParser, ASN1StreamParser, 
//            ASN1Encodable, ASN1Primitive

public class BERSetParser
    implements ASN1SetParser
{

    BERSetParser(ASN1StreamParser parser)
    {
        _parser = parser;
    }

    public ASN1Encodable readObject()
        throws IOException
    {
        return _parser.readObject();
    }

    public ASN1Primitive getLoadedObject()
        throws IOException
    {
        return new BERSet(_parser.readVector());
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

    private ASN1StreamParser _parser;
}
