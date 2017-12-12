// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERSetParser.java

package co.org.bouncy.asn1;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            DERSet, ASN1ParsingException, ASN1SetParser, ASN1StreamParser, 
//            ASN1Encodable, ASN1Primitive

public class DERSetParser
    implements ASN1SetParser
{

    DERSetParser(ASN1StreamParser parser)
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
        return new DERSet(_parser.readVector(), false);
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
