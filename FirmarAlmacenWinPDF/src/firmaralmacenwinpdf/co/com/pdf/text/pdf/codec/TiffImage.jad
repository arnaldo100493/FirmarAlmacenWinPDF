// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TiffImage.java

package co.com.pdf.text.pdf.codec;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.exceptions.InvalidImageException;
import co.com.pdf.text.pdf.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.*;

// Referenced classes of package co.com.pdf.text.pdf.codec:
//            TIFFDirectory, CCITTG4Encoder, TIFFFaxDecoder, TIFFLZWDecoder, 
//            TIFFField

public class TiffImage
{

    public TiffImage()
    {
    }

    public static int getNumberOfPages(RandomAccessFileOrArray s)
    {
        try
        {
            return TIFFDirectory.getNumDirectories(s);
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    static int getDpi(TIFFField fd, int resolutionUnit)
    {
        if(fd == null)
            return 0;
        long res[] = fd.getAsRational(0);
        float frac = (float)res[0] / (float)res[1];
        int dpi = 0;
        switch(resolutionUnit)
        {
        case 1: // '\001'
        case 2: // '\002'
            dpi = (int)((double)frac + 0.5D);
            break;

        case 3: // '\003'
            dpi = (int)((double)frac * 2.54D + 0.5D);
            break;
        }
        return dpi;
    }

    public static Image getTiffImage(RandomAccessFileOrArray s, boolean handleIncorrectImage, int page, boolean direct)
    {
        if(page < 1)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.page.number.must.be.gt.eq.1", new Object[0]));
        TIFFDirectory dir;
        int compression;
        float rotation;
        int rot;
        Image img;
        long tiffT4Options;
        long tiffT6Options;
        int fillOrder;
        int h;
        int w;
        int dpiX;
        int dpiY;
        float XYRatio;
        int resolutionUnit;
        int rowsStrip;
        long offset[];
        long size[];
        boolean reverse;
        TIFFField fillOrderField;
        int params;
        long photo;
        int imagecomp;
        TIFFField t4OptionsField;
        byte im[];
        int rowsLeft;
        TIFFField fd;
        RuntimeException e;
        TIFFField t6OptionsField;
        CCITTG4Encoder g4;
        ICC_Profile icc_prof;
        int k;
        byte g4pic[];
        byte im[];
        int height;
        TIFFFaxDecoder decoder;
        byte outBuf[];
        RuntimeException e;
        RuntimeException e2;
        try
        {
            dir = new TIFFDirectory(s, page - 1);
            if(dir.isTagPresent(322))
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("tiles.are.not.supported", new Object[0]));
            compression = (int)dir.getFieldAsLong(259);
            switch(compression)
            {
            default:
                return getTiffImageColor(dir, s);

            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
            case 32771: 
                break;
            }
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        rotation = 0.0F;
        if(dir.isTagPresent(274))
        {
            rot = (int)dir.getFieldAsLong(274);
            if(rot == 3 || rot == 4)
                rotation = 3.141593F;
            else
            if(rot == 5 || rot == 8)
                rotation = 1.570796F;
            else
            if(rot == 6 || rot == 7)
                rotation = -1.570796F;
        }
        img = null;
        tiffT4Options = 0L;
        tiffT6Options = 0L;
        fillOrder = 1;
        h = (int)dir.getFieldAsLong(257);
        w = (int)dir.getFieldAsLong(256);
        dpiX = 0;
        dpiY = 0;
        XYRatio = 0.0F;
        resolutionUnit = 2;
        if(dir.isTagPresent(296))
            resolutionUnit = (int)dir.getFieldAsLong(296);
        dpiX = getDpi(dir.getField(282), resolutionUnit);
        dpiY = getDpi(dir.getField(283), resolutionUnit);
        if(resolutionUnit == 1)
        {
            if(dpiY != 0)
                XYRatio = (float)dpiX / (float)dpiY;
            dpiX = 0;
            dpiY = 0;
        }
        rowsStrip = h;
        if(dir.isTagPresent(278))
            rowsStrip = (int)dir.getFieldAsLong(278);
        if(rowsStrip <= 0 || rowsStrip > h)
            rowsStrip = h;
        offset = getArrayLongShort(dir, 273);
        size = getArrayLongShort(dir, 279);
        if((size == null || size.length == 1 && (size[0] == 0L || size[0] + offset[0] > s.length())) && h == rowsStrip)
            size = (new long[] {
                s.length() - (long)(int)offset[0]
            });
        reverse = false;
        fillOrderField = dir.getField(266);
        if(fillOrderField != null)
            fillOrder = fillOrderField.getAsInt(0);
        reverse = fillOrder == 2;
        params = 0;
        if(dir.isTagPresent(262))
        {
            photo = dir.getFieldAsLong(262);
            if(photo == 1L)
                params |= 1;
        }
        imagecomp = 0;
        switch(compression)
        {
        case 2: // '\002'
        case 32771: 
            imagecomp = 257;
            params |= 0xa;
            break;

        case 3: // '\003'
            imagecomp = 257;
            params |= 0xc;
            t4OptionsField = dir.getField(292);
            if(t4OptionsField != null)
            {
                tiffT4Options = t4OptionsField.getAsLong(0);
                if((tiffT4Options & 1L) != 0L)
                    imagecomp = 258;
                if((tiffT4Options & 4L) != 0L)
                    params |= 2;
            }
            break;

        case 4: // '\004'
            imagecomp = 256;
            t6OptionsField = dir.getField(293);
            if(t6OptionsField != null)
                tiffT6Options = t6OptionsField.getAsLong(0);
            break;
        }
        if(direct && rowsStrip == h)
        {
            im = new byte[(int)size[0]];
            s.seek(offset[0]);
            s.readFully(im);
            img = Image.getInstance(w, h, false, imagecomp, params, im);
            img.setInverted(true);
        } else
        {
            rowsLeft = h;
            g4 = new CCITTG4Encoder(w);
            for(k = 0; k < offset.length; k++)
            {
                im = new byte[(int)size[k]];
                s.seek(offset[k]);
                s.readFully(im);
                height = Math.min(rowsStrip, rowsLeft);
                decoder = new TIFFFaxDecoder(fillOrder, w, height);
                decoder.setHandleIncorrectImage(handleIncorrectImage);
                outBuf = new byte[((w + 7) / 8) * height];
                switch(compression)
                {
                default:
                    break;

                case 2: // '\002'
                case 32771: 
                    decoder.decode1D(outBuf, im, 0, height);
                    g4.fax4Encode(outBuf, height);
                    break;

                case 3: // '\003'
                    try
                    {
                        decoder.decode2D(outBuf, im, 0, height, tiffT4Options);
                    }
                    // Misplaced declaration of an exception variable
                    catch(RuntimeException e)
                    {
                        tiffT4Options ^= 4L;
                        try
                        {
                            decoder.decode2D(outBuf, im, 0, height, tiffT4Options);
                        }
                        // Misplaced declaration of an exception variable
                        catch(RuntimeException e2)
                        {
                            throw e;
                        }
                    }
                    g4.fax4Encode(outBuf, height);
                    break;

                case 4: // '\004'
                    try
                    {
                        decoder.decodeT6(outBuf, im, 0, height, tiffT6Options);
                    }
                    // Misplaced declaration of an exception variable
                    catch(RuntimeException e)
                    {
                        if(!handleIncorrectImage)
                            throw e;
                    }
                    g4.fax4Encode(outBuf, height);
                    break;
                }
                rowsLeft -= rowsStrip;
            }

            g4pic = g4.close();
            img = Image.getInstance(w, h, false, 256, params & 1, g4pic);
        }
        img.setDpi(dpiX, dpiY);
        img.setXYRatio(XYRatio);
        if(dir.isTagPresent(34675))
            try
            {
                fd = dir.getField(34675);
                icc_prof = ICC_Profile.getInstance(fd.getAsBytes());
                if(icc_prof.getNumComponents() == 1)
                    img.tagICC(icc_prof);
            }
            // Misplaced declaration of an exception variable
            catch(RuntimeException e) { }
        img.setOriginalType(5);
        if(rotation != 0.0F)
            img.setInitialRotation(rotation);
        return img;
    }

    public static Image getTiffImage(RandomAccessFileOrArray s, boolean handleIncorrectImage, int page)
    {
        return getTiffImage(s, handleIncorrectImage, page, false);
    }

    public static Image getTiffImage(RandomAccessFileOrArray s, int page)
    {
        return getTiffImage(s, page, false);
    }

    public static Image getTiffImage(RandomAccessFileOrArray s, int page, boolean direct)
    {
        return getTiffImage(s, false, page, direct);
    }

    protected static Image getTiffImageColor(TIFFDirectory dir, RandomAccessFileOrArray s)
    {
        try
        {
            int compression = (int)dir.getFieldAsLong(259);
            int predictor = 1;
            TIFFLZWDecoder lzwDecoder = null;
            int photometric;
            switch(compression)
            {
            default:
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.compression.1.is.not.supported", compression));

            case 1: // '\001'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            case 8: // '\b'
            case 32773: 
            case 32946: 
                photometric = (int)dir.getFieldAsLong(262);
                break;
            }
            float rotation;
            switch(photometric)
            {
            case 4: // '\004'
            default:
                if(compression != 6 && compression != 7)
                    throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.photometric.1.is.not.supported", photometric));
                // fall through

            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 5: // '\005'
                rotation = 0.0F;
                break;
            }
            if(dir.isTagPresent(274))
            {
                int rot = (int)dir.getFieldAsLong(274);
                if(rot == 3 || rot == 4)
                    rotation = 3.141593F;
                else
                if(rot == 5 || rot == 8)
                    rotation = 1.570796F;
                else
                if(rot == 6 || rot == 7)
                    rotation = -1.570796F;
            }
            if(dir.isTagPresent(284) && dir.getFieldAsLong(284) == 2L)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("planar.images.are.not.supported", new Object[0]));
            int extraSamples = 0;
            if(dir.isTagPresent(338))
                extraSamples = 1;
            int samplePerPixel = 1;
            if(dir.isTagPresent(277))
                samplePerPixel = (int)dir.getFieldAsLong(277);
            int bitsPerSample = 1;
            if(dir.isTagPresent(258))
                bitsPerSample = (int)dir.getFieldAsLong(258);
            Image img;
            switch(bitsPerSample)
            {
            case 3: // '\003'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            default:
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("bits.per.sample.1.is.not.supported", bitsPerSample));

            case 1: // '\001'
            case 2: // '\002'
            case 4: // '\004'
            case 8: // '\b'
                img = null;
                break;
            }
            int h = (int)dir.getFieldAsLong(257);
            int w = (int)dir.getFieldAsLong(256);
            int dpiX = 0;
            int dpiY = 0;
            int resolutionUnit = 2;
            if(dir.isTagPresent(296))
                resolutionUnit = (int)dir.getFieldAsLong(296);
            dpiX = getDpi(dir.getField(282), resolutionUnit);
            dpiY = getDpi(dir.getField(283), resolutionUnit);
            int fillOrder = 1;
            boolean reverse = false;
            TIFFField fillOrderField = dir.getField(266);
            if(fillOrderField != null)
                fillOrder = fillOrderField.getAsInt(0);
            reverse = fillOrder == 2;
            int rowsStrip = h;
            if(dir.isTagPresent(278))
                rowsStrip = (int)dir.getFieldAsLong(278);
            if(rowsStrip <= 0 || rowsStrip > h)
                rowsStrip = h;
            long offset[] = getArrayLongShort(dir, 273);
            long size[] = getArrayLongShort(dir, 279);
            if((size == null || size.length == 1 && (size[0] == 0L || size[0] + offset[0] > s.length())) && h == rowsStrip)
                size = (new long[] {
                    s.length() - (long)(int)offset[0]
                });
            if(compression == 5 || compression == 32946 || compression == 8)
            {
                TIFFField predictorField = dir.getField(317);
                if(predictorField != null)
                {
                    predictor = predictorField.getAsInt(0);
                    if(predictor != 1 && predictor != 2)
                        throw new RuntimeException(MessageLocalization.getComposedMessage("illegal.value.for.predictor.in.tiff.file", new Object[0]));
                    if(predictor == 2 && bitsPerSample != 8)
                        throw new RuntimeException(MessageLocalization.getComposedMessage("1.bit.samples.are.not.supported.for.horizontal.differencing.predictor", bitsPerSample));
                }
            }
            if(compression == 5)
                lzwDecoder = new TIFFLZWDecoder(w, predictor, samplePerPixel);
            int rowsLeft = h;
            ByteArrayOutputStream stream = null;
            ByteArrayOutputStream mstream = null;
            DeflaterOutputStream zip = null;
            DeflaterOutputStream mzip = null;
            if(extraSamples > 0)
            {
                mstream = new ByteArrayOutputStream();
                mzip = new DeflaterOutputStream(mstream);
            }
            CCITTG4Encoder g4 = null;
            if(bitsPerSample == 1 && samplePerPixel == 1 && photometric != 3)
            {
                g4 = new CCITTG4Encoder(w);
            } else
            {
                stream = new ByteArrayOutputStream();
                if(compression != 6 && compression != 7)
                    zip = new DeflaterOutputStream(stream);
            }
            if(compression == 6)
            {
                if(!dir.isTagPresent(513))
                    throw new IOException(MessageLocalization.getComposedMessage("missing.tag.s.for.ojpeg.compression", new Object[0]));
                int jpegOffset = (int)dir.getFieldAsLong(513);
                int jpegLength = (int)s.length() - jpegOffset;
                if(dir.isTagPresent(514))
                    jpegLength = (int)dir.getFieldAsLong(514) + (int)size[0];
                byte jpeg[] = new byte[Math.min(jpegLength, (int)s.length() - jpegOffset)];
                int posFilePointer = (int)s.getFilePointer();
                posFilePointer += jpegOffset;
                s.seek(posFilePointer);
                s.readFully(jpeg);
                img = new Jpeg(jpeg);
            } else
            if(compression == 7)
            {
                if(size.length > 1)
                    throw new IOException(MessageLocalization.getComposedMessage("compression.jpeg.is.only.supported.with.a.single.strip.this.image.has.1.strips", size.length));
                byte jpeg[] = new byte[(int)size[0]];
                s.seek(offset[0]);
                s.readFully(jpeg);
                TIFFField jpegtables = dir.getField(347);
                if(jpegtables != null)
                {
                    byte temp[] = jpegtables.getAsBytes();
                    int tableoffset = 0;
                    int tablelength = temp.length;
                    if(temp[0] == -1 && temp[1] == -40)
                    {
                        tableoffset = 2;
                        tablelength -= 2;
                    }
                    if(temp[temp.length - 2] == -1 && temp[temp.length - 1] == -39)
                        tablelength -= 2;
                    byte tables[] = new byte[tablelength];
                    System.arraycopy(temp, tableoffset, tables, 0, tablelength);
                    byte jpegwithtables[] = new byte[jpeg.length + tables.length];
                    System.arraycopy(jpeg, 0, jpegwithtables, 0, 2);
                    System.arraycopy(tables, 0, jpegwithtables, 2, tables.length);
                    System.arraycopy(jpeg, 2, jpegwithtables, tables.length + 2, jpeg.length - 2);
                    jpeg = jpegwithtables;
                }
                img = new Jpeg(jpeg);
                if(photometric == 2)
                    img.setColorTransform(0);
            } else
            {
                for(int k = 0; k < offset.length; k++)
                {
                    byte im[] = new byte[(int)size[k]];
                    s.seek(offset[k]);
                    s.readFully(im);
                    int height = Math.min(rowsStrip, rowsLeft);
                    byte outBuf[] = null;
                    if(compression != 1)
                        outBuf = new byte[((w * bitsPerSample * samplePerPixel + 7) / 8) * height];
                    if(reverse)
                        TIFFFaxDecoder.reverseBits(im);
                    switch(compression)
                    {
                    case 8: // '\b'
                    case 32946: 
                        inflate(im, outBuf);
                        applyPredictor(outBuf, predictor, w, height, samplePerPixel);
                        break;

                    case 1: // '\001'
                        outBuf = im;
                        break;

                    case 32773: 
                        decodePackbits(im, outBuf);
                        break;

                    case 5: // '\005'
                        lzwDecoder.decode(im, outBuf, height);
                        break;
                    }
                    if(bitsPerSample == 1 && samplePerPixel == 1 && photometric != 3)
                        g4.fax4Encode(outBuf, height);
                    else
                    if(extraSamples > 0)
                        ProcessExtraSamples(zip, mzip, outBuf, samplePerPixel, bitsPerSample, w, height);
                    else
                        zip.write(outBuf);
                    rowsLeft -= rowsStrip;
                }

                if(bitsPerSample == 1 && samplePerPixel == 1 && photometric != 3)
                {
                    img = Image.getInstance(w, h, false, 256, photometric != 1 ? 0 : 1, g4.close());
                } else
                {
                    zip.close();
                    img = new ImgRaw(w, h, samplePerPixel - extraSamples, bitsPerSample, stream.toByteArray());
                    img.setDeflated(true);
                }
            }
            img.setDpi(dpiX, dpiY);
            if(compression != 6 && compression != 7)
            {
                if(dir.isTagPresent(34675))
                    try
                    {
                        TIFFField fd = dir.getField(34675);
                        ICC_Profile icc_prof = ICC_Profile.getInstance(fd.getAsBytes());
                        if(samplePerPixel - extraSamples == icc_prof.getNumComponents())
                            img.tagICC(icc_prof);
                    }
                    catch(RuntimeException e) { }
                if(dir.isTagPresent(320))
                {
                    TIFFField fd = dir.getField(320);
                    char rgb[] = fd.getAsChars();
                    byte palette[] = new byte[rgb.length];
                    int gColor = rgb.length / 3;
                    int bColor = gColor * 2;
                    for(int k = 0; k < gColor; k++)
                    {
                        palette[k * 3] = (byte)(rgb[k] >>> 8);
                        palette[k * 3 + 1] = (byte)(rgb[k + gColor] >>> 8);
                        palette[k * 3 + 2] = (byte)(rgb[k + bColor] >>> 8);
                    }

                    boolean colormapBroken = true;
                    int k = 0;
                    do
                    {
                        if(k >= palette.length)
                            break;
                        if(palette[k] != 0)
                        {
                            colormapBroken = false;
                            break;
                        }
                        k++;
                    } while(true);
                    if(colormapBroken)
                        for(k = 0; k < gColor; k++)
                        {
                            palette[k * 3] = (byte)rgb[k];
                            palette[k * 3 + 1] = (byte)rgb[k + gColor];
                            palette[k * 3 + 2] = (byte)rgb[k + bColor];
                        }

                    PdfArray indexed = new PdfArray();
                    indexed.add(PdfName.INDEXED);
                    indexed.add(PdfName.DEVICERGB);
                    indexed.add(new PdfNumber(gColor - 1));
                    indexed.add(new PdfString(palette));
                    PdfDictionary additional = new PdfDictionary();
                    additional.put(PdfName.COLORSPACE, indexed);
                    img.setAdditional(additional);
                }
                img.setOriginalType(5);
            }
            if(photometric == 0)
                img.setInverted(true);
            if(rotation != 0.0F)
                img.setInitialRotation(rotation);
            if(extraSamples > 0)
            {
                mzip.close();
                Image mimg = Image.getInstance(w, h, 1, bitsPerSample, mstream.toByteArray());
                mimg.makeMask();
                mimg.setDeflated(true);
                img.setImageMask(mimg);
            }
            return img;
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    static Image ProcessExtraSamples(DeflaterOutputStream zip, DeflaterOutputStream mzip, byte outBuf[], int samplePerPixel, int bitsPerSample, int width, int height)
        throws IOException
    {
        if(bitsPerSample == 8)
        {
            byte mask[] = new byte[width * height];
            int mptr = 0;
            int optr = 0;
            int total = width * height * samplePerPixel;
            for(int k = 0; k < total; k += samplePerPixel)
            {
                for(int s = 0; s < samplePerPixel - 1; s++)
                    outBuf[optr++] = outBuf[k + s];

                mask[mptr++] = outBuf[(k + samplePerPixel) - 1];
            }

            zip.write(outBuf, 0, optr);
            mzip.write(mask, 0, mptr);
        } else
        {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("extra.samples.are.not.supported", new Object[0]));
        }
        return null;
    }

    static long[] getArrayLongShort(TIFFDirectory dir, int tag)
    {
        TIFFField field = dir.getField(tag);
        if(field == null)
            return null;
        long offset[];
        if(field.getType() == 4)
        {
            offset = field.getAsLongs();
        } else
        {
            char temp[] = field.getAsChars();
            offset = new long[temp.length];
            for(int k = 0; k < temp.length; k++)
                offset[k] = temp[k];

        }
        return offset;
    }

    public static void decodePackbits(byte data[], byte dst[])
    {
        int srcCount = 0;
        int dstCount = 0;
        try
        {
            while(dstCount < dst.length) 
            {
                byte b = data[srcCount++];
                if(b >= 0 && b <= 127)
                {
                    int i = 0;
                    while(i < b + 1) 
                    {
                        dst[dstCount++] = data[srcCount++];
                        i++;
                    }
                } else
                if(b <= -1 && b >= -127)
                {
                    byte repeat = data[srcCount++];
                    int i = 0;
                    while(i < -b + 1) 
                    {
                        dst[dstCount++] = repeat;
                        i++;
                    }
                } else
                {
                    srcCount++;
                }
            }
        }
        catch(Exception e) { }
    }

    public static void inflate(byte deflated[], byte inflated[])
    {
        Inflater inflater = new Inflater();
        inflater.setInput(deflated);
        try
        {
            inflater.inflate(inflated);
        }
        catch(DataFormatException dfe)
        {
            throw new ExceptionConverter(dfe);
        }
    }

    public static void applyPredictor(byte uncompData[], int predictor, int w, int h, int samplesPerPixel)
    {
        if(predictor != 2)
            return;
        for(int j = 0; j < h; j++)
        {
            int count = samplesPerPixel * (j * w + 1);
            for(int i = samplesPerPixel; i < w * samplesPerPixel; i++)
            {
                uncompData[count] += uncompData[count - samplesPerPixel];
                count++;
            }

        }

    }
}
