// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BERSequenceParser.java

package co.org.bouncy.asn1;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            BERSequence, ASN1SequenceParser, ASN1StreamParser, ASN1Encodable, 
//            ASN1Primitive

public class BERSequenceParser
    implements ASN1SequenceParser
{

    BERSequenceParser(ASN1StreamParser parser)
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
        return new BERSequence(_parser.readVector());
    }

    public ASN1Primitive toASN1Primitive()
    {
        try
        {
            return getLoadedObject();
        }
        catch(IOException e)
        {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private ASN1StreamParser _parser;
}
