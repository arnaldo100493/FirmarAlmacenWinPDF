// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSContentInfoParser.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.ASN1SequenceParser;
import co.org.bouncy.asn1.ASN1StreamParser;
import co.org.bouncy.asn1.cms.ContentInfoParser;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package co.org.bouncy.cms:
//            CMSException

public class CMSContentInfoParser
{

    protected CMSContentInfoParser(InputStream data)
        throws CMSException
    {
        _data = data;
        try
        {
            ASN1StreamParser in = new ASN1StreamParser(data);
            _contentInfo = new ContentInfoParser((ASN1SequenceParser)in.readObject());
        }
        catch(IOException e)
        {
            throw new CMSException("IOException reading content.", e);
        }
        catch(ClassCastException e)
        {
            throw new CMSException("Unexpected object reading content.", e);
        }
    }

    public void close()
        throws IOException
    {
        _data.close();
    }

    protected ContentInfoParser _contentInfo;
    protected InputStream _data;
}
