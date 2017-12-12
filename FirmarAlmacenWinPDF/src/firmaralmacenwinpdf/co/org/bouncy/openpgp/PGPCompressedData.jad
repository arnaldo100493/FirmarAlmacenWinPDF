// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPCompressedData.java

package co.org.bouncy.openpgp;

import co.org.bouncy.apache.bzip2.CBZip2InputStream;
import co.org.bouncy.bcpg.*;
import java.io.*;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPException

public class PGPCompressedData
    implements CompressionAlgorithmTags
{

    public PGPCompressedData(BCPGInputStream pIn)
        throws IOException
    {
        data = (CompressedDataPacket)pIn.readPacket();
    }

    public int getAlgorithm()
    {
        return data.getAlgorithm();
    }

    public InputStream getInputStream()
    {
        return data.getInputStream();
    }

    public InputStream getDataStream()
        throws PGPException
    {
        if(getAlgorithm() == 0)
            return getInputStream();
        if(getAlgorithm() == 1)
            return new InflaterInputStream(getInputStream(), new Inflater(true)) {

                protected void fill()
                    throws IOException
                {
                    if(eof)
                        throw new EOFException("Unexpected end of ZIP input stream");
                    len = in.read(buf, 0, buf.length);
                    if(len == -1)
                    {
                        buf[0] = 0;
                        len = 1;
                        eof = true;
                    }
                    inf.setInput(buf, 0, len);
                }

                private boolean eof;
                final PGPCompressedData this$0;

            
            {
                this$0 = PGPCompressedData.this;
                super(x0, x1);
                eof = false;
            }
            }
;
        if(getAlgorithm() == 2)
            return new InflaterInputStream(getInputStream()) {

                protected void fill()
                    throws IOException
                {
                    if(eof)
                        throw new EOFException("Unexpected end of ZIP input stream");
                    len = in.read(buf, 0, buf.length);
                    if(len == -1)
                    {
                        buf[0] = 0;
                        len = 1;
                        eof = true;
                    }
                    inf.setInput(buf, 0, len);
                }

                private boolean eof;
                final PGPCompressedData this$0;

            
            {
                this$0 = PGPCompressedData.this;
                super(x0);
                eof = false;
            }
            }
;
        if(getAlgorithm() == 3)
            try
            {
                return new CBZip2InputStream(getInputStream());
            }
            catch(IOException e)
            {
                throw new PGPException((new StringBuilder()).append("I/O problem with stream: ").append(e).toString(), e);
            }
        else
            throw new PGPException((new StringBuilder()).append("can't recognise compression algorithm: ").append(getAlgorithm()).toString());
    }

    CompressedDataPacket data;
}
