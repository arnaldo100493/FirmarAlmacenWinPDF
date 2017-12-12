// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERExternalParser.java

package co.org.bouncy.asn1;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            DERExternal, ASN1Exception, ASN1ParsingException, ASN1Encodable, 
//            InMemoryRepresentable, ASN1StreamParser, ASN1Primitive

public class DERExternalParser
    implements ASN1Encodable, InMemoryRepresentable
{

    public DERExternalParser(ASN1StreamParser parser)
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
        try
        {
            return new DERExternal(_parser.readVector());
        }
        catch(IllegalArgumentException e)
        {
            throw new ASN1Exception(e.getMessage(), e);
        }
    }

    public ASN1Primitive toASN1Primitive()
    {
        try
        {
            return getLoadedObject();
        }
        catch(IOException ioe)
        {
            throw new ASN1ParsingException("unable to get DER object", ioe);
        }
        catch(IllegalArgumentException ioe)
        {
            throw new ASN1ParsingException("unable to get DER object", ioe);
        }
    }

    private ASN1StreamParser _parser;
}
