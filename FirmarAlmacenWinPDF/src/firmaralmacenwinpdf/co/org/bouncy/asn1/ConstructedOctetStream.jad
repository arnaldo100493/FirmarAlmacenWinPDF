// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConstructedOctetStream.java

package co.org.bouncy.asn1;

import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1OctetStringParser, ASN1StreamParser

class ConstructedOctetStream extends InputStream
{

    ConstructedOctetStream(ASN1StreamParser parser)
    {
        _first = true;
        _parser = parser;
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        if(_currentStream == null)
        {
            if(!_first)
                return -1;
            ASN1OctetStringParser s = (ASN1OctetStringParser)_parser.readObject();
            if(s == null)
                return -1;
            _first = false;
            _currentStream = s.getOctetStream();
        }
        int totalRead = 0;
        do
        {
            int numRead = _currentStream.read(b, off + totalRead, len - totalRead);
            if(numRead >= 0)
            {
                totalRead += numRead;
                if(totalRead == len)
                    return totalRead;
            } else
            {
                ASN1OctetStringParser aos = (ASN1OctetStringParser)_parser.readObject();
                if(aos == null)
                {
                    _currentStream = null;
                    return totalRead >= 1 ? totalRead : -1;
                }
                _currentStream = aos.getOctetStream();
            }
        } while(true);
    }

    public int read()
        throws IOException
    {
        if(_currentStream == null)
        {
            if(!_first)
                return -1;
            ASN1OctetStringParser s = (ASN1OctetStringParser)_parser.readObject();
            if(s == null)
                return -1;
            _first = false;
            _currentStream = s.getOctetStream();
        }
        do
        {
            int b = _currentStream.read();
            if(b >= 0)
                return b;
            ASN1OctetStringParser s = (ASN1OctetStringParser)_parser.readObject();
            if(s == null)
            {
                _currentStream = null;
                return -1;
            }
            _currentStream = s.getOctetStream();
        } while(true);
    }

    private final ASN1StreamParser _parser;
    private boolean _first;
    private InputStream _currentStream;
}
