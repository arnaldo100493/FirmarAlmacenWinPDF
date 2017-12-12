// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContents.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfStream, BadPdfFormatException, PdfNumber, PdfContentByte, 
//            PdfWriter, ByteBuffer, PdfName

class PdfContents extends PdfStream
{

    PdfContents(PdfContentByte under, PdfContentByte content, PdfContentByte text, PdfContentByte secondContent, Rectangle page)
        throws BadPdfFormatException
    {
        try
        {
            OutputStream out = null;
            Deflater deflater = null;
            streamBytes = new ByteArrayOutputStream();
            if(Document.compress)
            {
                compressed = true;
                if(text != null)
                    compressionLevel = text.getPdfWriter().getCompressionLevel();
                else
                if(content != null)
                    compressionLevel = content.getPdfWriter().getCompressionLevel();
                deflater = new Deflater(compressionLevel);
                out = new DeflaterOutputStream(streamBytes, deflater);
            } else
            {
                out = streamBytes;
            }
            int rotation = page.getRotation();
            switch(rotation)
            {
            case 90: // 'Z'
                out.write(ROTATE90);
                out.write(DocWriter.getISOBytes(ByteBuffer.formatDouble(page.getTop())));
                out.write(32);
                out.write(48);
                out.write(ROTATEFINAL);
                break;

            case 180: 
                out.write(ROTATE180);
                out.write(DocWriter.getISOBytes(ByteBuffer.formatDouble(page.getRight())));
                out.write(32);
                out.write(DocWriter.getISOBytes(ByteBuffer.formatDouble(page.getTop())));
                out.write(ROTATEFINAL);
                break;

            case 270: 
                out.write(ROTATE270);
                out.write(48);
                out.write(32);
                out.write(DocWriter.getISOBytes(ByteBuffer.formatDouble(page.getRight())));
                out.write(ROTATEFINAL);
                break;
            }
            if(under.size() > 0)
            {
                out.write(SAVESTATE);
                under.getInternalBuffer().writeTo(out);
                out.write(RESTORESTATE);
            }
            if(content.size() > 0)
            {
                out.write(SAVESTATE);
                content.getInternalBuffer().writeTo(out);
                out.write(RESTORESTATE);
            }
            if(text != null)
            {
                out.write(SAVESTATE);
                text.getInternalBuffer().writeTo(out);
                out.write(RESTORESTATE);
            }
            if(secondContent.size() > 0)
                secondContent.getInternalBuffer().writeTo(out);
            out.close();
            if(deflater != null)
                deflater.end();
        }
        catch(Exception e)
        {
            throw new BadPdfFormatException(e.getMessage());
        }
        put(PdfName.LENGTH, new PdfNumber(streamBytes.size()));
        if(compressed)
            put(PdfName.FILTER, PdfName.FLATEDECODE);
    }

    static final byte SAVESTATE[] = DocWriter.getISOBytes("q\n");
    static final byte RESTORESTATE[] = DocWriter.getISOBytes("Q\n");
    static final byte ROTATE90[] = DocWriter.getISOBytes("0 1 -1 0 ");
    static final byte ROTATE180[] = DocWriter.getISOBytes("-1 0 0 -1 ");
    static final byte ROTATE270[] = DocWriter.getISOBytes("0 -1 1 0 ");
    static final byte ROTATEFINAL[] = DocWriter.getISOBytes(" cm\n");

}
