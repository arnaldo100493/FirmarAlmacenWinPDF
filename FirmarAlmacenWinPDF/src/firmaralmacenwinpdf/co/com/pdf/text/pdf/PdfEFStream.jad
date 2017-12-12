// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfEFStream.java

package co.com.pdf.text.pdf;

import java.io.*;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfStream, PdfArray, PdfDictionary, PdfNull, 
//            PdfNumber, OutputStreamCounter, PdfEncryption, PdfObject, 
//            PdfWriter, OutputStreamEncryption, PdfName

public class PdfEFStream extends PdfStream
{

    public PdfEFStream(InputStream in, PdfWriter writer)
    {
        super(in, writer);
    }

    public PdfEFStream(byte fileStore[])
    {
        super(fileStore);
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
        if(crypto != null && crypto.isEmbeddedFilesOnly())
        {
            PdfArray filter = new PdfArray();
            PdfArray decodeparms = new PdfArray();
            PdfDictionary crypt = new PdfDictionary();
            crypt.put(PdfName.NAME, PdfName.STDCF);
            filter.add(PdfName.CRYPT);
            decodeparms.add(crypt);
            if(compressed)
            {
                filter.add(PdfName.FLATEDECODE);
                decodeparms.add(new PdfNull());
            }
            put(PdfName.FILTER, filter);
            put(PdfName.DECODEPARMS, decodeparms);
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
        os.write(STARTSTREAM);
        if(inputStream != null)
        {
            rawLength = 0;
            DeflaterOutputStream def = null;
            OutputStreamCounter osc = new OutputStreamCounter(os);
            OutputStreamEncryption ose = null;
            OutputStream fout = osc;
            if(crypto != null)
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
        if(crypto == null)
        {
            if(streamBytes != null)
                streamBytes.writeTo(os);
            else
                os.write(bytes);
        } else
        {
            byte b[];
            if(streamBytes != null)
                b = crypto.encryptByteArray(streamBytes.toByteArray());
            else
                b = crypto.encryptByteArray(bytes);
            os.write(b);
        }
        os.write(ENDSTREAM);
    }
}
