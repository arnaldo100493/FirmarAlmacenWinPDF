// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PRStream.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.Document;
import co.com.pdf.text.ExceptionConverter;
import java.io.*;
import java.util.HashMap;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfStream, PdfNumber, PdfDictionary, PdfReader, 
//            PdfEncryption, PdfObject, PdfName, PdfWriter

public class PRStream extends PdfStream
{

    public PRStream(PRStream stream, PdfDictionary newDic)
    {
        objNum = 0;
        objGen = 0;
        reader = stream.reader;
        offset = stream.offset;
        length = stream.length;
        compressed = stream.compressed;
        compressionLevel = stream.compressionLevel;
        streamBytes = stream.streamBytes;
        bytes = stream.bytes;
        objNum = stream.objNum;
        objGen = stream.objGen;
        if(newDic != null)
            putAll(newDic);
        else
            hashMap.putAll(stream.hashMap);
    }

    public PRStream(PRStream stream, PdfDictionary newDic, PdfReader reader)
    {
        this(stream, newDic);
        this.reader = reader;
    }

    public PRStream(PdfReader reader, long offset)
    {
        objNum = 0;
        objGen = 0;
        this.reader = reader;
        this.offset = offset;
    }

    public PRStream(PdfReader reader, byte conts[])
    {
        this(reader, conts, -1);
    }

    public PRStream(PdfReader reader, byte conts[], int compressionLevel)
    {
        objNum = 0;
        objGen = 0;
        this.reader = reader;
        offset = -1L;
        if(Document.compress)
        {
            try
            {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Deflater deflater = new Deflater(compressionLevel);
                DeflaterOutputStream zip = new DeflaterOutputStream(stream, deflater);
                zip.write(conts);
                zip.close();
                deflater.end();
                bytes = stream.toByteArray();
            }
            catch(IOException ioe)
            {
                throw new ExceptionConverter(ioe);
            }
            put(PdfName.FILTER, PdfName.FLATEDECODE);
        } else
        {
            bytes = conts;
        }
        setLength(bytes.length);
    }

    public void setData(byte data[], boolean compress)
    {
        setData(data, compress, -1);
    }

    public void setData(byte data[], boolean compress, int compressionLevel)
    {
        remove(PdfName.FILTER);
        offset = -1L;
        if(Document.compress && compress)
        {
            try
            {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Deflater deflater = new Deflater(compressionLevel);
                DeflaterOutputStream zip = new DeflaterOutputStream(stream, deflater);
                zip.write(data);
                zip.close();
                deflater.end();
                bytes = stream.toByteArray();
                this.compressionLevel = compressionLevel;
            }
            catch(IOException ioe)
            {
                throw new ExceptionConverter(ioe);
            }
            put(PdfName.FILTER, PdfName.FLATEDECODE);
        } else
        {
            bytes = data;
        }
        setLength(bytes.length);
    }

    public void setData(byte data[])
    {
        setData(data, true);
    }

    public void setLength(int length)
    {
        this.length = length;
        put(PdfName.LENGTH, new PdfNumber(length));
    }

    public long getOffset()
    {
        return offset;
    }

    public int getLength()
    {
        return length;
    }

    public PdfReader getReader()
    {
        return reader;
    }

    public byte[] getBytes()
    {
        return bytes;
    }

    public void setObjNum(int objNum, int objGen)
    {
        this.objNum = objNum;
        this.objGen = objGen;
    }

    int getObjNum()
    {
        return objNum;
    }

    int getObjGen()
    {
        return objGen;
    }

    public void toPdf(PdfWriter writer, OutputStream os)
        throws IOException
    {
        byte b[] = PdfReader.getStreamBytesRaw(this);
        PdfEncryption crypto = null;
        if(writer != null)
            crypto = writer.getEncryption();
        PdfObject objLen = get(PdfName.LENGTH);
        int nn = b.length;
        if(crypto != null)
            nn = crypto.calculateStreamSize(nn);
        put(PdfName.LENGTH, new PdfNumber(nn));
        superToPdf(writer, os);
        put(PdfName.LENGTH, objLen);
        os.write(STARTSTREAM);
        if(length > 0)
        {
            if(crypto != null && !crypto.isEmbeddedFilesOnly())
                b = crypto.encryptByteArray(b);
            os.write(b);
        }
        os.write(ENDSTREAM);
    }

    protected PdfReader reader;
    protected long offset;
    protected int length;
    protected int objNum;
    protected int objGen;
}
