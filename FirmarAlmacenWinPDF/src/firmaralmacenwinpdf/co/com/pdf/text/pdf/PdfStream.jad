// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfStream.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.*;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfNumber, PdfArray, OutputStreamCounter, 
//            PdfObject, PdfEncryption, PdfWriter, OutputStreamEncryption, 
//            PdfName, PdfReader, PdfIndirectReference

public class PdfStream extends PdfDictionary
{

    public PdfStream(byte bytes[])
    {
        compressed = false;
        compressionLevel = 0;
        streamBytes = null;
        inputStreamLength = -1;
        type = 7;
        this.bytes = bytes;
        rawLength = bytes.length;
        put(PdfName.LENGTH, new PdfNumber(bytes.length));
    }

    public PdfStream(InputStream inputStream, PdfWriter writer)
    {
        compressed = false;
        compressionLevel = 0;
        streamBytes = null;
        inputStreamLength = -1;
        type = 7;
        this.inputStream = inputStream;
        this.writer = writer;
        ref = writer.getPdfIndirectReference();
        put(PdfName.LENGTH, ref);
    }

    protected PdfStream()
    {
        compressed = false;
        compressionLevel = 0;
        streamBytes = null;
        inputStreamLength = -1;
        type = 7;
    }

    public void writeLength()
        throws IOException
    {
        if(inputStream == null)
            throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("writelength.can.only.be.called.in.a.contructed.pdfstream.inputstream.pdfwriter", new Object[0]));
        if(inputStreamLength == -1)
        {
            throw new IOException(MessageLocalization.getComposedMessage("writelength.can.only.be.called.after.output.of.the.stream.body", new Object[0]));
        } else
        {
            writer.addToBody(new PdfNumber(inputStreamLength), ref, false);
            return;
        }
    }

    public int getRawLength()
    {
        return rawLength;
    }

    public void flateCompress()
    {
        flateCompress(-1);
    }

    public void flateCompress(int compressionLevel)
    {
        if(!Document.compress)
            return;
        if(compressed)
            return;
        this.compressionLevel = compressionLevel;
        if(inputStream != null)
        {
            compressed = true;
            return;
        }
        PdfObject filter = PdfReader.getPdfObject(get(PdfName.FILTER));
        if(filter != null)
            if(filter.isName())
            {
                if(PdfName.FLATEDECODE.equals(filter))
                    return;
            } else
            if(filter.isArray())
            {
                if(((PdfArray)filter).contains(PdfName.FLATEDECODE))
                    return;
            } else
            {
                throw new RuntimeException(MessageLocalization.getComposedMessage("stream.could.not.be.compressed.filter.is.not.a.name.or.array", new Object[0]));
            }
        try
        {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Deflater deflater = new Deflater(compressionLevel);
            DeflaterOutputStream zip = new DeflaterOutputStream(stream, deflater);
            if(streamBytes != null)
                streamBytes.writeTo(zip);
            else
                zip.write(bytes);
            zip.close();
            deflater.end();
            streamBytes = stream;
            bytes = null;
            put(PdfName.LENGTH, new PdfNumber(streamBytes.size()));
            if(filter == null)
            {
                put(PdfName.FILTER, PdfName.FLATEDECODE);
            } else
            {
                PdfArray filters = new PdfArray(filter);
                filters.add(PdfName.FLATEDECODE);
                put(PdfName.FILTER, filters);
            }
            compressed = true;
        }
        catch(IOException ioe)
        {
            throw new ExceptionConverter(ioe);
        }
    }

    protected void superToPdf(PdfWriter writer, OutputStream os)
        throws IOException
    {
        super.toPdf(writer, os);
    }

    public void toPdf(PdfWriter writer, OutputStream os)
        throws IOException
    {
        if(inputStream != null && compressed)
            put(PdfName.FILTER, PdfName.FLATEDECODE);
        PdfEncryption crypto = null;
        if(writer != null)
            crypto = writer.getEncryption();
        if(crypto != null)
        {
            PdfObject filter = get(PdfName.FILTER);
            if(filter != null)
                if(PdfName.CRYPT.equals(filter))
                    crypto = null;
                else
                if(filter.isArray())
                {
                    PdfArray a = (PdfArray)filter;
                    if(!a.isEmpty() && PdfName.CRYPT.equals(a.getPdfObject(0)))
                        crypto = null;
                }
        }
        PdfObject nn = get(PdfName.LENGTH);
        if(crypto != null && nn != null && nn.isNumber())
        {
            int sz = ((PdfNumber)nn).intValue();
            put(PdfName.LENGTH, new PdfNumber(crypto.calculateStreamSize(sz)));
            superToPdf(writer, os);
            put(PdfName.LENGTH, nn);
        } else
        {
            superToPdf(writer, os);
        }
        PdfWriter.checkPdfIsoConformance(writer, 9, this);
        os.write(STARTSTREAM);
        if(inputStream != null)
        {
            rawLength = 0;
            DeflaterOutputStream def = null;
            OutputStreamCounter osc = new OutputStreamCounter(os);
            OutputStreamEncryption ose = null;
            OutputStream fout = osc;
            if(crypto != null && !crypto.isEmbeddedFilesOnly())
                fout = ose = crypto.getEncryptionStream(fout);
            Deflater deflater = null;
            if(compressed)
            {
                deflater = new Deflater(compressionLevel);
                fout = def = new DeflaterOutputStream(fout, deflater, 32768);
            }
            byte buf[] = new byte[4192];
            do
            {
                int n = inputStream.read(buf);
                if(n <= 0)
                    break;
                fout.write(buf, 0, n);
                rawLength += n;
            } while(true);
            if(def != null)
            {
                def.finish();
                deflater.end();
            }
            if(ose != null)
                ose.finish();
            inputStreamLength = (int)osc.getCounter();
        } else
        if(crypto != null && !crypto.isEmbeddedFilesOnly())
        {
            byte b[];
            if(streamBytes != null)
                b = crypto.encryptByteArray(streamBytes.toByteArray());
            else
                b = crypto.encryptByteArray(bytes);
            os.write(b);
        } else
        if(streamBytes != null)
            streamBytes.writeTo(os);
        else
            os.write(bytes);
        os.write(ENDSTREAM);
    }

    public void writeContent(OutputStream os)
        throws IOException
    {
        if(streamBytes != null)
            streamBytes.writeTo(os);
        else
        if(bytes != null)
            os.write(bytes);
    }

    public String toString()
    {
        if(get(PdfName.TYPE) == null)
            return "Stream";
        else
            return (new StringBuilder()).append("Stream of type: ").append(get(PdfName.TYPE)).toString();
    }

    public static final int DEFAULT_COMPRESSION = -1;
    public static final int NO_COMPRESSION = 0;
    public static final int BEST_SPEED = 1;
    public static final int BEST_COMPRESSION = 9;
    protected boolean compressed;
    protected int compressionLevel;
    protected ByteArrayOutputStream streamBytes;
    protected InputStream inputStream;
    protected PdfIndirectReference ref;
    protected int inputStreamLength;
    protected PdfWriter writer;
    protected int rawLength;
    static final byte STARTSTREAM[];
    static final byte ENDSTREAM[];
    static final int SIZESTREAM;

    static 
    {
        STARTSTREAM = DocWriter.getISOBytes("stream\n");
        ENDSTREAM = DocWriter.getISOBytes("\nendstream");
        SIZESTREAM = STARTSTREAM.length + ENDSTREAM.length;
    }
}
