// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfFileSpecification.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.io.StreamUtil;
import co.com.pdf.text.pdf.collection.PdfCollectionItem;
import java.io.*;
import java.net.URL;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfString, PdfEFStream, PdfNumber, 
//            PdfName, PdfBoolean, PdfWriter, PdfIndirectReference, 
//            PdfIndirectObject

public class PdfFileSpecification extends PdfDictionary
{

    public PdfFileSpecification()
    {
        super(PdfName.FILESPEC);
    }

    public static PdfFileSpecification url(PdfWriter writer, String url)
    {
        PdfFileSpecification fs = new PdfFileSpecification();
        fs.writer = writer;
        fs.put(PdfName.FS, co.com.pdf.text.pdf.PdfName.URL);
        fs.put(PdfName.F, new PdfString(url));
        return fs;
    }

    public static PdfFileSpecification fileEmbedded(PdfWriter writer, String filePath, String fileDisplay, byte fileStore[])
        throws IOException
    {
        return fileEmbedded(writer, filePath, fileDisplay, fileStore, 9);
    }

    public static PdfFileSpecification fileEmbedded(PdfWriter writer, String filePath, String fileDisplay, byte fileStore[], int compressionLevel)
        throws IOException
    {
        return fileEmbedded(writer, filePath, fileDisplay, fileStore, ((String) (null)), ((PdfDictionary) (null)), compressionLevel);
    }

    public static PdfFileSpecification fileEmbedded(PdfWriter writer, String filePath, String fileDisplay, byte fileStore[], boolean compress)
        throws IOException
    {
        return fileEmbedded(writer, filePath, fileDisplay, fileStore, ((String) (null)), ((PdfDictionary) (null)), compress ? 9 : 0);
    }

    public static PdfFileSpecification fileEmbedded(PdfWriter writer, String filePath, String fileDisplay, byte fileStore[], boolean compress, String mimeType, PdfDictionary fileParameter)
        throws IOException
    {
        return fileEmbedded(writer, filePath, fileDisplay, fileStore, mimeType, fileParameter, compress ? 9 : 0);
    }

    public static PdfFileSpecification fileEmbedded(PdfWriter writer, String filePath, String fileDisplay, byte fileStore[], String mimeType, PdfDictionary fileParameter, int compressionLevel)
        throws IOException
    {
        PdfFileSpecification fs;
        InputStream in;
        PdfIndirectReference refFileLength;
        fs = new PdfFileSpecification();
        fs.writer = writer;
        fs.put(PdfName.F, new PdfString(fileDisplay));
        fs.setUnicodeFileName(fileDisplay, false);
        in = null;
        refFileLength = null;
        PdfIndirectReference ref;
        PdfEFStream stream;
        if(fileStore == null)
        {
            refFileLength = writer.getPdfIndirectReference();
            File file = new File(filePath);
            if(file.canRead())
                in = new FileInputStream(filePath);
            else
            if(filePath.startsWith("file:/") || filePath.startsWith("http://") || filePath.startsWith("https://") || filePath.startsWith("jar:"))
            {
                in = (new URL(filePath)).openStream();
            } else
            {
                in = StreamUtil.getResourceStream(filePath);
                if(in == null)
                    throw new IOException(MessageLocalization.getComposedMessage("1.not.found.as.file.or.resource", new Object[] {
                        filePath
                    }));
            }
            stream = new PdfEFStream(in, writer);
        } else
        {
            stream = new PdfEFStream(fileStore);
        }
        stream.put(PdfName.TYPE, PdfName.EMBEDDEDFILE);
        stream.flateCompress(compressionLevel);
        PdfDictionary param = new PdfDictionary();
        if(fileParameter != null)
            param.merge(fileParameter);
        if(fileStore != null)
        {
            param.put(PdfName.SIZE, new PdfNumber(stream.getRawLength()));
            stream.put(PdfName.PARAMS, param);
        } else
        {
            stream.put(PdfName.PARAMS, refFileLength);
        }
        if(mimeType != null)
            stream.put(PdfName.SUBTYPE, new PdfName(mimeType));
        ref = writer.addToBody(stream).getIndirectReference();
        if(fileStore == null)
        {
            stream.writeLength();
            param.put(PdfName.SIZE, new PdfNumber(stream.getRawLength()));
            writer.addToBody(param, refFileLength);
        }
        if(in != null)
            try
            {
                in.close();
            }
            catch(Exception e) { }
        break MISSING_BLOCK_LABEL_389;
        Exception exception;
        exception;
        if(in != null)
            try
            {
                in.close();
            }
            catch(Exception e) { }
        throw exception;
        PdfDictionary f = new PdfDictionary();
        f.put(PdfName.F, ref);
        f.put(PdfName.UF, ref);
        fs.put(PdfName.EF, f);
        return fs;
    }

    public static PdfFileSpecification fileExtern(PdfWriter writer, String filePath)
    {
        PdfFileSpecification fs = new PdfFileSpecification();
        fs.writer = writer;
        fs.put(PdfName.F, new PdfString(filePath));
        fs.setUnicodeFileName(filePath, false);
        return fs;
    }

    public PdfIndirectReference getReference()
        throws IOException
    {
        if(ref != null)
        {
            return ref;
        } else
        {
            ref = writer.addToBody(this).getIndirectReference();
            return ref;
        }
    }

    public void setMultiByteFileName(byte fileName[])
    {
        put(PdfName.F, (new PdfString(fileName)).setHexWriting(true));
    }

    public void setUnicodeFileName(String filename, boolean unicode)
    {
        put(PdfName.UF, new PdfString(filename, unicode ? "UnicodeBig" : "PDF"));
    }

    public void setVolatile(boolean volatile_file)
    {
        put(PdfName.V, new PdfBoolean(volatile_file));
    }

    public void addDescription(String description, boolean unicode)
    {
        put(PdfName.DESC, new PdfString(description, unicode ? "UnicodeBig" : "PDF"));
    }

    public void addCollectionItem(PdfCollectionItem ci)
    {
        put(PdfName.CI, ci);
    }

    public void toPdf(PdfWriter writer, OutputStream os)
        throws IOException
    {
        PdfWriter.checkPdfIsoConformance(writer, 10, this);
        super.toPdf(writer, os);
    }

    protected PdfWriter writer;
    protected PdfIndirectReference ref;
}
