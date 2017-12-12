// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfImageObject.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.Version;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.exceptions.UnsupportedPdfException;
import co.com.pdf.text.pdf.*;
import co.com.pdf.text.pdf.codec.PngWriter;
import co.com.pdf.text.pdf.codec.TiffWriter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class PdfImageObject
{
    private static class TrackingFilter
        implements co.com.pdf.text.pdf.FilterHandlers.FilterHandler
    {

        public byte[] decode(byte b[], PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary)
            throws IOException
        {
            lastFilterName = filterName;
            return b;
        }

        public PdfName lastFilterName;

        private TrackingFilter()
        {
            lastFilterName = null;
        }

    }

    public static final class ImageBytesType extends Enum
    {

        public static ImageBytesType[] values()
        {
            return (ImageBytesType[])$VALUES.clone();
        }

        public static ImageBytesType valueOf(String name)
        {
            return (ImageBytesType)Enum.valueOf(co/com/pdf/text/pdf/parser/PdfImageObject$ImageBytesType, name);
        }

        public String getFileExtension()
        {
            return fileExtension;
        }

        public static final ImageBytesType PNG;
        public static final ImageBytesType JPG;
        public static final ImageBytesType JP2;
        public static final ImageBytesType CCITT;
        public static final ImageBytesType JBIG2;
        private final String fileExtension;
        private static final ImageBytesType $VALUES[];

        static 
        {
            PNG = new ImageBytesType("PNG", 0, "png");
            JPG = new ImageBytesType("JPG", 1, "jpg");
            JP2 = new ImageBytesType("JP2", 2, "jp2");
            CCITT = new ImageBytesType("CCITT", 3, "tif");
            JBIG2 = new ImageBytesType("JBIG2", 4, "jbig2");
            $VALUES = (new ImageBytesType[] {
                PNG, JPG, JP2, CCITT, JBIG2
            });
        }

        private ImageBytesType(String s, int i, String fileExtension)
        {
            super(s, i);
            this.fileExtension = fileExtension;
        }
    }


    public String getFileType()
    {
        return streamContentType.getFileExtension();
    }

    public ImageBytesType getImageBytesType()
    {
        return streamContentType;
    }

    public PdfImageObject(PRStream stream)
        throws IOException
    {
        this(((PdfDictionary) (stream)), PdfReader.getStreamBytesRaw(stream), null);
    }

    public PdfImageObject(PRStream stream, PdfDictionary colorSpaceDic)
        throws IOException
    {
        this(((PdfDictionary) (stream)), PdfReader.getStreamBytesRaw(stream), colorSpaceDic);
    }

    protected PdfImageObject(PdfDictionary dictionary, byte samples[], PdfDictionary colorSpaceDic)
        throws IOException
    {
        pngColorType = -1;
        streamContentType = null;
        this.dictionary = dictionary;
        this.colorSpaceDic = colorSpaceDic;
        TrackingFilter trackingFilter = new TrackingFilter();
        Map handlers = new HashMap(FilterHandlers.getDefaultFilterHandlers());
        handlers.put(PdfName.JBIG2DECODE, trackingFilter);
        handlers.put(PdfName.DCTDECODE, trackingFilter);
        handlers.put(PdfName.JPXDECODE, trackingFilter);
        imageBytes = PdfReader.decodeBytes(samples, dictionary, handlers);
        if(trackingFilter.lastFilterName != null)
        {
            if(PdfName.JBIG2DECODE.equals(trackingFilter.lastFilterName))
                streamContentType = ImageBytesType.JBIG2;
            else
            if(PdfName.DCTDECODE.equals(trackingFilter.lastFilterName))
                streamContentType = ImageBytesType.JPG;
            else
            if(PdfName.JPXDECODE.equals(trackingFilter.lastFilterName))
                streamContentType = ImageBytesType.JP2;
        } else
        {
            decodeImageBytes();
        }
    }

    public PdfObject get(PdfName key)
    {
        return dictionary.get(key);
    }

    public PdfDictionary getDictionary()
    {
        return dictionary;
    }

    private void findColorspace(PdfObject colorspace, boolean allowIndexed)
        throws IOException
    {
        if(colorspace == null && bpc == 1)
        {
            stride = (width * bpc + 7) / 8;
            pngColorType = 0;
        } else
        if(PdfName.DEVICEGRAY.equals(colorspace))
        {
            stride = (width * bpc + 7) / 8;
            pngColorType = 0;
        } else
        if(PdfName.DEVICERGB.equals(colorspace))
        {
            if(bpc == 8 || bpc == 16)
            {
                stride = (width * bpc * 3 + 7) / 8;
                pngColorType = 2;
            }
        } else
        if(colorspace instanceof PdfArray)
        {
            PdfArray ca = (PdfArray)colorspace;
            PdfObject tyca = ca.getDirectObject(0);
            if(PdfName.CALGRAY.equals(tyca))
            {
                stride = (width * bpc + 7) / 8;
                pngColorType = 0;
            } else
            if(PdfName.CALRGB.equals(tyca))
            {
                if(bpc == 8 || bpc == 16)
                {
                    stride = (width * bpc * 3 + 7) / 8;
                    pngColorType = 2;
                }
            } else
            if(PdfName.ICCBASED.equals(tyca))
            {
                PRStream pr = (PRStream)ca.getDirectObject(1);
                int n = pr.getAsNumber(PdfName.N).intValue();
                if(n == 1)
                {
                    stride = (width * bpc + 7) / 8;
                    pngColorType = 0;
                    icc = PdfReader.getStreamBytes(pr);
                } else
                if(n == 3)
                {
                    stride = (width * bpc * 3 + 7) / 8;
                    pngColorType = 2;
                    icc = PdfReader.getStreamBytes(pr);
                }
            } else
            if(allowIndexed && PdfName.INDEXED.equals(tyca))
            {
                findColorspace(ca.getDirectObject(1), false);
                if(pngColorType == 2)
                {
                    PdfObject id2 = ca.getDirectObject(3);
                    if(id2 instanceof PdfString)
                        palette = ((PdfString)id2).getBytes();
                    else
                    if(id2 instanceof PRStream)
                        palette = PdfReader.getStreamBytes((PRStream)id2);
                    stride = (width * bpc + 7) / 8;
                    pngColorType = 3;
                }
            }
        }
    }

    private void decodeImageBytes()
        throws IOException
    {
        if(streamContentType != null)
            throw new IllegalStateException(MessageLocalization.getComposedMessage("Decoding.can't.happen.on.this.type.of.stream.(.1.)", new Object[] {
                streamContentType
            }));
        pngColorType = -1;
        PdfArray decode = dictionary.getAsArray(PdfName.DECODE);
        width = dictionary.getAsNumber(PdfName.WIDTH).intValue();
        height = dictionary.getAsNumber(PdfName.HEIGHT).intValue();
        bpc = dictionary.getAsNumber(PdfName.BITSPERCOMPONENT).intValue();
        pngBitDepth = bpc;
        PdfObject colorspace = dictionary.getDirectObject(PdfName.COLORSPACE);
        if((colorspace instanceof PdfName) && colorSpaceDic != null)
        {
            PdfObject csLookup = colorSpaceDic.getDirectObject((PdfName)colorspace);
            if(csLookup != null)
                colorspace = csLookup;
        }
        palette = null;
        icc = null;
        stride = 0;
        findColorspace(colorspace, true);
        ByteArrayOutputStream ms = new ByteArrayOutputStream();
        if(pngColorType < 0)
        {
            if(bpc != 8)
                throw new UnsupportedPdfException(MessageLocalization.getComposedMessage("the.color.depth.1.is.not.supported", bpc));
            if(!PdfName.DEVICECMYK.equals(colorspace))
                if(colorspace instanceof PdfArray)
                {
                    PdfArray ca = (PdfArray)colorspace;
                    PdfObject tyca = ca.getDirectObject(0);
                    if(!PdfName.ICCBASED.equals(tyca))
                        throw new UnsupportedPdfException(MessageLocalization.getComposedMessage("the.color.space.1.is.not.supported", new Object[] {
                            colorspace
                        }));
                    PRStream pr = (PRStream)ca.getDirectObject(1);
                    int n = pr.getAsNumber(PdfName.N).intValue();
                    if(n != 4)
                        throw new UnsupportedPdfException(MessageLocalization.getComposedMessage("N.value.1.is.not.supported", n));
                    icc = PdfReader.getStreamBytes(pr);
                } else
                {
                    throw new UnsupportedPdfException(MessageLocalization.getComposedMessage("the.color.space.1.is.not.supported", new Object[] {
                        colorspace
                    }));
                }
            stride = 4 * width;
            TiffWriter wr = new TiffWriter();
            wr.addField(new co.com.pdf.text.pdf.codec.TiffWriter.FieldShort(277, 4));
            wr.addField(new co.com.pdf.text.pdf.codec.TiffWriter.FieldShort(258, new int[] {
                8, 8, 8, 8
            }));
            wr.addField(new co.com.pdf.text.pdf.codec.TiffWriter.FieldShort(262, 5));
            wr.addField(new co.com.pdf.text.pdf.codec.TiffWriter.FieldLong(256, width));
            wr.addField(new co.com.pdf.text.pdf.codec.TiffWriter.FieldLong(257, height));
            wr.addField(new co.com.pdf.text.pdf.codec.TiffWriter.FieldShort(259, 5));
            wr.addField(new co.com.pdf.text.pdf.codec.TiffWriter.FieldShort(317, 2));
            wr.addField(new co.com.pdf.text.pdf.codec.TiffWriter.FieldLong(278, height));
            wr.addField(new co.com.pdf.text.pdf.codec.TiffWriter.FieldRational(282, new int[] {
                300, 1
            }));
            wr.addField(new co.com.pdf.text.pdf.codec.TiffWriter.FieldRational(283, new int[] {
                300, 1
            }));
            wr.addField(new co.com.pdf.text.pdf.codec.TiffWriter.FieldShort(296, 2));
            wr.addField(new co.com.pdf.text.pdf.codec.TiffWriter.FieldAscii(305, Version.getInstance().getVersion()));
            ByteArrayOutputStream comp = new ByteArrayOutputStream();
            TiffWriter.compressLZW(comp, 2, imageBytes, height, 4, stride);
            byte buf[] = comp.toByteArray();
            wr.addField(new co.com.pdf.text.pdf.codec.TiffWriter.FieldImage(buf));
            wr.addField(new co.com.pdf.text.pdf.codec.TiffWriter.FieldLong(279, buf.length));
            if(icc != null)
                wr.addField(new co.com.pdf.text.pdf.codec.TiffWriter.FieldUndefined(34675, icc));
            wr.writeFile(ms);
            streamContentType = ImageBytesType.CCITT;
            imageBytes = ms.toByteArray();
            return;
        }
        PngWriter png = new PngWriter(ms);
        if(decode != null && pngBitDepth == 1 && decode.getAsNumber(0).intValue() == 1 && decode.getAsNumber(1).intValue() == 0)
        {
            int len = imageBytes.length;
            for(int t = 0; t < len; t++)
                imageBytes[t] ^= 0xff;

        }
        png.writeHeader(width, height, pngBitDepth, pngColorType);
        if(icc != null)
            png.writeIccProfile(icc);
        if(palette != null)
            png.writePalette(palette);
        png.writeData(imageBytes, stride);
        png.writeEnd();
        streamContentType = ImageBytesType.PNG;
        imageBytes = ms.toByteArray();
    }

    public byte[] getImageAsBytes()
    {
        return imageBytes;
    }

    public BufferedImage getBufferedImage()
        throws IOException
    {
        byte img[] = getImageAsBytes();
        if(img == null)
            return null;
        else
            return ImageIO.read(new ByteArrayInputStream(img));
    }

    private PdfDictionary dictionary;
    private byte imageBytes[];
    private PdfDictionary colorSpaceDic;
    private int pngColorType;
    private int pngBitDepth;
    private int width;
    private int height;
    private int bpc;
    private byte palette[];
    private byte icc[];
    private int stride;
    private ImageBytesType streamContentType;
}
