// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BEROctetStringGenerator.java

package co.org.bouncy.asn1;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.asn1:
//            DEROutputStream, BEROctetStringGenerator, DEROctetString

private class BEROctetStringGenerator$BufferedBEROctetStream extends OutputStream
{

    public void write(int b)
        throws IOException
    {
        _buf[_off++] = (byte)b;
        if(_off == _buf.length)
        {
            DEROctetString.encode(_derOut, _buf);
            _off = 0;
        }
    }

    public void write(byte b[], int off, int len)
        throws IOException
    {
        do
        {
            if(len <= 0)
                break;
            int numToCopy = Math.min(len, _buf.length - _off);
            System.arraycopy(b, off, _buf, _off, numToCopy);
            _off += numToCopy;
            if(_off < _buf.length)
                break;
            DEROctetString.encode(_derOut, _buf);
            _off = 0;
            off += numToCopy;
            len -= numToCopy;
        } while(true);
    }

    public void close()
        throws IOException
    {
        if(_off != 0)
        {
            byte bytes[] = new byte[_off];
            System.arraycopy(_buf, 0, bytes, 0, _off);
            DEROctetString.encode(_derOut, bytes);
        }
        writeBEREnd();
    }

    private byte _buf[];
    private int _off;
    private DEROutputStream _derOut;
    final BEROctetStringGenerator this$0;

    BEROctetStringGenerator$BufferedBEROctetStream(byte buf[])
    {
        this$0 = BEROctetStringGenerator.this;
        super();
        _buf = buf;
        _off = 0;
        _derOut = new DEROutputStream(_out);
    }
}
