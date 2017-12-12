// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BERTaggedObjectParser.java

package co.org.bouncy.asn1;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1ParsingException, ASN1TaggedObjectParser, ASN1StreamParser, ASN1Encodable, 
//            ASN1Primitive

public class BERTaggedObjectParser
    implements ASN1TaggedObjectParser
{

    BERTaggedObjectParser(boolean constructed, int tagNumber, ASN1StreamParser parser)
    {
        _constructed = constructed;
        _tagNumber = tagNumber;
        _parser = parser;
    }

    public boolean isConstructed()
    {
        return _constructed;
    }

    public int getTagNo()
    {
        return _tagNumber;
    }

    public ASN1Encodable getObjectParser(int tag, boolean isExplicit)
        throws IOException
    {
        if(isExplicit)
        {
            if(!_constructed)
                throw new IOException("Explicit tags must be constructed (see X.690 8.14.2)");
            else
                return _parser.readObject();
        } else
        {
            return _parser.readImplicit(_constructed, tag);
        }
    }

    public ASN1Primitive getLoadedObject()
        throws IOException
    {
        return _parser.readTaggedObject(_constructed, _tagNumber);
    }

    public ASN1Primitive toASN1Primitive()
    {
        try
        {
            return getLoadedObject();
        }
        catch(IOException e)
        {
            throw new ASN1ParsingException(e.getMessage());
        }
    }

    private boolean _constructed;
    private int _tagNumber;
    private ASN1StreamParser _parser;
}
