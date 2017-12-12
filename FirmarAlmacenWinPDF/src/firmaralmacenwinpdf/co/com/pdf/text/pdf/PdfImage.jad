// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfImage.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.Image;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.*;
import java.net.URL;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfStream, PdfName, PdfNumber, PdfLiteral, 
//            PdfDictionary, BadPdfFormatException, PdfIndirectReference, PdfOCG, 
//            PdfBoolean

public class PdfImage extends PdfStream
{

    public PdfImage(Image image, String name, PdfIndirectReference maskRef)
        throws BadPdfFormatException
    {
        InputStream is;
        this.name = null;
        this.image = null;
        this.image = image;
        if(name == null)
            generateImgResName(image);
        else
            this.name = new PdfName(name);
        put(PdfName.TYPE, PdfName.XOBJECT);
        put(PdfName.SUBTYPE, PdfName.IMAGE);
        put(PdfName.WIDTH, new PdfNumber(image.getWidth()));
        put(PdfName.HEIGHT, new PdfNumber(image.getHeight()));
        if(image.getLayer() != null)
            put(PdfName.OC, image.getLayer().getRef());
        if(image.isMask() && (image.getBpc() == 1 || image.getBpc() > 255))
            put(PdfName.IMAGEMASK, PdfBoolean.PDFTRUE);
        if(maskRef != null)
            if(image.isSmask())
                put(PdfName.SMASK, maskRef);
            else
                put(PdfName.MASK, maskRef);
        if(image.isMask() && image.isInverted())
            put(PdfName.DECODE, new PdfLiteral("[1 0]"));
        if(image.isInterpolation())
            put(PdfName.INTERPOLATE, PdfBoolean.PDFTRUE);
        is = null;
label0:
        {
            Exception ee;
            String errorID;
            PdfDictionary decodeparms;
            Exception ee;
            try
            {
                int transparency[] = image.getTransparency();
                if(transparency != null && !image.isMask() && maskRef == null)
                {
                    StringBuilder s = new StringBuilder("[");
                    for(int k = 0; k < transparency.length; k++)
                        s.append(transparency[k]).append(" ");

                    s.append("]");
                    put(PdfName.MASK, new PdfLiteral(s.toString()));
                }
                if(!image.isImgRaw())
                    break label0;
                int colorspace = image.getColorspace();
                bytes = image.getRawData();
                put(PdfName.LENGTH, new PdfNumber(bytes.length));
                int bpc = image.getBpc();
                if(bpc > 255)
                {
                    if(!image.isMask())
                        put(PdfName.COLORSPACE, PdfName.DEVICEGRAY);
                    put(PdfName.BITSPERCOMPONENT, new PdfNumber(1));
                    put(PdfName.FILTER, PdfName.CCITTFAXDECODE);
                    int k = bpc - 257;
                    PdfDictionary decodeparms = new PdfDictionary();
                    if(k != 0)
                        decodeparms.put(PdfName.K, new PdfNumber(k));
                    if((colorspace & 1) != 0)
                        decodeparms.put(PdfName.BLACKIS1, PdfBoolean.PDFTRUE);
                    if((colorspace & 2) != 0)
                        decodeparms.put(PdfName.ENCODEDBYTEALIGN, PdfBoolean.PDFTRUE);
                    if((colorspace & 4) != 0)
                        decodeparms.put(PdfName.ENDOFLINE, PdfBoolean.PDFTRUE);
                    if((colorspace & 8) != 0)
                        decodeparms.put(PdfName.ENDOFBLOCK, PdfBoolean.PDFFALSE);
                    decodeparms.put(PdfName.COLUMNS, new PdfNumber(image.getWidth()));
                    decodeparms.put(PdfName.ROWS, new PdfNumber(image.getHeight()));
                    put(PdfName.DECODEPARMS, decodeparms);
                } else
                {
                    switch(colorspace)
                    {
                    case 1: // '\001'
                        put(PdfName.COLORSPACE, PdfName.DEVICEGRAY);
                        if(image.isInverted())
                            put(PdfName.DECODE, new PdfLiteral("[1 0]"));
                        break;

                    case 3: // '\003'
                        put(PdfName.COLORSPACE, PdfName.DEVICERGB);
                        if(image.isInverted())
                            put(PdfName.DECODE, new PdfLiteral("[1 0 1 0 1 0]"));
                        break;

                    case 2: // '\002'
                    case 4: // '\004'
                    default:
                        put(PdfName.COLORSPACE, PdfName.DEVICECMYK);
                        if(image.isInverted())
                            put(PdfName.DECODE, new PdfLiteral("[1 0 1 0 1 0 1 0]"));
                        break;
                    }
                    PdfDictionary additional = image.getAdditional();
                    if(additional != null)
                        putAll(additional);
                    if(image.isMask() && (image.getBpc() == 1 || image.getBpc() > 8))
                        remove(PdfName.COLORSPACE);
                    put(PdfName.BITSPERCOMPONENT, new PdfNumber(image.getBpc()));
                    if(image.isDeflated())
                        put(PdfName.FILTER, PdfName.FLATEDECODE);
                    else
                        flateCompress(image.getCompressionLevel());
                }
            }
            catch(IOException ioe)
            {
                throw new BadPdfFormatException(ioe.getMessage());
            }
            if(is != null)
                try
                {
                    is.close();
                }
                catch(Exception ee) { }
            return;
        }
        if(image.getRawData() == null)
        {
            is = image.getUrl().openStream();
            errorID = image.getUrl().toString();
        } else
        {
            is = new ByteArrayInputStream(image.getRawData());
            errorID = "Byte array";
        }
        image.type();
        JVM INSTR tableswitch 32 36: default 1433
    //                   32 920
    //                   33 1149
    //                   34 1433
    //                   35 1433
    //                   36 1324;
           goto _L1 _L2 _L3 _L1 _L1 _L4
_L2:
        put(PdfName.FILTER, PdfName.DCTDECODE);
        if(image.getColorTransform() == 0)
        {
            decodeparms = new PdfDictionary();
            decodeparms.put(PdfName.COLORTRANSFORM, new PdfNumber(0));
            put(PdfName.DECODEPARMS, decodeparms);
        }
        switch(image.getColorspace())
        {
        case 1: // '\001'
            put(PdfName.COLORSPACE, PdfName.DEVICEGRAY);
            break;

        case 3: // '\003'
            put(PdfName.COLORSPACE, PdfName.DEVICERGB);
            break;

        default:
            put(PdfName.COLORSPACE, PdfName.DEVICECMYK);
            if(image.isInverted())
                put(PdfName.DECODE, new PdfLiteral("[1 0 1 0 1 0 1 0]"));
            break;
        }
        put(PdfName.BITSPERCOMPONENT, new PdfNumber(8));
        if(image.getRawData() == null) goto _L6; else goto _L5
_L5:
        bytes = image.getRawData();
        put(PdfName.LENGTH, new PdfNumber(bytes.length));
        if(is != null)
            try
            {
                is.close();
            }
            // Misplaced declaration of an exception variable
            catch(Exception ee) { }
        return;
_L6:
        streamBytes = new ByteArrayOutputStream();
        transferBytes(is, streamBytes, -1);
        break MISSING_BLOCK_LABEL_1455;
_L3:
        put(PdfName.FILTER, PdfName.JPXDECODE);
        if(image.getColorspace() > 0)
        {
            switch(image.getColorspace())
            {
            case 1: // '\001'
                put(PdfName.COLORSPACE, PdfName.DEVICEGRAY);
                break;

            case 3: // '\003'
                put(PdfName.COLORSPACE, PdfName.DEVICERGB);
                break;

            default:
                put(PdfName.COLORSPACE, PdfName.DEVICECMYK);
                break;
            }
            put(PdfName.BITSPERCOMPONENT, new PdfNumber(image.getBpc()));
        }
        if(image.getRawData() == null) goto _L8; else goto _L7
_L7:
        bytes = image.getRawData();
        put(PdfName.LENGTH, new PdfNumber(bytes.length));
        if(is != null)
            try
            {
                is.close();
            }
            // Misplaced declaration of an exception variable
            catch(Exception ee) { }
        return;
_L8:
        streamBytes = new ByteArrayOutputStream();
        transferBytes(is, streamBytes, -1);
        break MISSING_BLOCK_LABEL_1455;
_L4:
        put(PdfName.FILTER, PdfName.JBIG2DECODE);
        put(PdfName.COLORSPACE, PdfName.DEVICEGRAY);
        put(PdfName.BITSPERCOMPONENT, new PdfNumber(1));
        if(image.getRawData() == null) goto _L10; else goto _L9
_L9:
        bytes = image.getRawData();
        put(PdfName.LENGTH, new PdfNumber(bytes.length));
        if(is != null)
            try
            {
                is.close();
            }
            // Misplaced declaration of an exception variable
            catch(Exception ee) { }
        return;
_L10:
        streamBytes = new ByteArrayOutputStream();
        transferBytes(is, streamBytes, -1);
        break MISSING_BLOCK_LABEL_1455;
_L1:
        throw new BadPdfFormatException(MessageLocalization.getComposedMessage("1.is.an.unknown.image.format", new Object[] {
            errorID
        }));
        put(PdfName.LENGTH, new PdfNumber(streamBytes.size()));
          goto _L11
        exception;
        if(is != null)
            try
            {
                is.close();
            }
            catch(Exception ee) { }
        throw exception;
_L11:
        Exception exception;
        if(is != null)
            try
            {
                is.close();
            }
            // Misplaced declaration of an exception variable
            catch(Exception ee) { }
        return;
    }

    public PdfName name()
    {
        return name;
    }

    public Image getImage()
    {
        return image;
    }

    static void transferBytes(InputStream in, OutputStream out, int len)
        throws IOException
    {
        byte buffer[] = new byte[4096];
        if(len < 0)
            len = 0x7fff0000;
        int size;
        for(; len != 0; len -= size)
        {
            size = in.read(buffer, 0, Math.min(len, 4096));
            if(size < 0)
                return;
            out.write(buffer, 0, size);
        }

    }

    protected void importAll(PdfImage dup)
    {
        name = dup.name;
        compressed = dup.compressed;
        compressionLevel = dup.compressionLevel;
        streamBytes = dup.streamBytes;
        bytes = dup.bytes;
        hashMap = dup.hashMap;
    }

    private void generateImgResName(Image img)
    {
        name = new PdfName((new StringBuilder()).append("img").append(Long.toHexString(img.getMySerialId().longValue())).toString());
    }

    static final int TRANSFERSIZE = 4096;
    protected PdfName name;
    protected Image image;
}
