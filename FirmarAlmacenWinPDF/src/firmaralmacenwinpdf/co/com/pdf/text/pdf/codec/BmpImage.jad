// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BmpImage.java

package co.com.pdf.text.pdf.codec;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.*;
import java.io.*;
import java.net.URL;
import java.util.HashMap;

public class BmpImage
{

    BmpImage(InputStream is, boolean noHeader, int size)
        throws IOException
    {
        properties = new HashMap();
        bitmapFileSize = size;
        bitmapOffset = 0L;
        process(is, noHeader);
    }

    public static Image getImage(URL url)
        throws IOException
    {
        InputStream is = null;
        Image image;
        is = url.openStream();
        Image img = getImage(is);
        img.setUrl(url);
        image = img;
        if(is != null)
            is.close();
        return image;
        Exception exception;
        exception;
        if(is != null)
            is.close();
        throw exception;
    }

    public static Image getImage(InputStream is)
        throws IOException
    {
        return getImage(is, false, 0);
    }

    public static Image getImage(InputStream is, boolean noHeader, int size)
        throws IOException
    {
        BmpImage bmp = new BmpImage(is, noHeader, size);
        try
        {
            Image img = bmp.getImage();
            img.setDpi((int)((double)bmp.xPelsPerMeter * 0.025399999999999999D + 0.5D), (int)((double)bmp.yPelsPerMeter * 0.025399999999999999D + 0.5D));
            img.setOriginalType(4);
            return img;
        }
        catch(BadElementException be)
        {
            throw new ExceptionConverter(be);
        }
    }

    public static Image getImage(String file)
        throws IOException
    {
        return getImage(Utilities.toURL(file));
    }

    public static Image getImage(byte data[])
        throws IOException
    {
        ByteArrayInputStream is = new ByteArrayInputStream(data);
        Image img = getImage(((InputStream) (is)));
        img.setOriginalData(data);
        return img;
    }

    protected void process(InputStream stream, boolean noHeader)
        throws IOException
    {
        if(noHeader || (stream instanceof BufferedInputStream))
            inputStream = stream;
        else
            inputStream = new BufferedInputStream(stream);
        if(!noHeader)
        {
            if(readUnsignedByte(inputStream) != 66 || readUnsignedByte(inputStream) != 77)
                throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.magic.value.for.bmp.file", new Object[0]));
            bitmapFileSize = readDWord(inputStream);
            readWord(inputStream);
            readWord(inputStream);
            bitmapOffset = readDWord(inputStream);
        }
        long size = readDWord(inputStream);
        if(size == 12L)
        {
            width = readWord(inputStream);
            height = readWord(inputStream);
        } else
        {
            width = readLong(inputStream);
            height = readLong(inputStream);
        }
        int planes = readWord(inputStream);
        bitsPerPixel = readWord(inputStream);
        properties.put("color_planes", Integer.valueOf(planes));
        properties.put("bits_per_pixel", Integer.valueOf(bitsPerPixel));
        numBands = 3;
        if(bitmapOffset == 0L)
            bitmapOffset = size;
        if(size == 12L)
        {
            properties.put("bmp_version", "BMP v. 2.x");
            if(bitsPerPixel == 1)
                imageType = 0;
            else
            if(bitsPerPixel == 4)
                imageType = 1;
            else
            if(bitsPerPixel == 8)
                imageType = 2;
            else
            if(bitsPerPixel == 24)
                imageType = 3;
            int numberOfEntries = (int)((bitmapOffset - 14L - size) / 3L);
            int sizeOfPalette = numberOfEntries * 3;
            if(bitmapOffset == size)
            {
                switch(imageType)
                {
                case 0: // '\0'
                    sizeOfPalette = 6;
                    break;

                case 1: // '\001'
                    sizeOfPalette = 48;
                    break;

                case 2: // '\002'
                    sizeOfPalette = 768;
                    break;

                case 3: // '\003'
                    sizeOfPalette = 0;
                    break;
                }
                bitmapOffset = size + (long)sizeOfPalette;
            }
            readPalette(sizeOfPalette);
        } else
        {
            compression = readDWord(inputStream);
            imageSize = readDWord(inputStream);
            xPelsPerMeter = readLong(inputStream);
            yPelsPerMeter = readLong(inputStream);
            long colorsUsed = readDWord(inputStream);
            long colorsImportant = readDWord(inputStream);
            switch((int)compression)
            {
            case 0: // '\0'
                properties.put("compression", "BI_RGB");
                break;

            case 1: // '\001'
                properties.put("compression", "BI_RLE8");
                break;

            case 2: // '\002'
                properties.put("compression", "BI_RLE4");
                break;

            case 3: // '\003'
                properties.put("compression", "BI_BITFIELDS");
                break;
            }
            properties.put("x_pixels_per_meter", Long.valueOf(xPelsPerMeter));
            properties.put("y_pixels_per_meter", Long.valueOf(yPelsPerMeter));
            properties.put("colors_used", Long.valueOf(colorsUsed));
            properties.put("colors_important", Long.valueOf(colorsImportant));
            if(size == 40L || size == 52L || size == 56L)
                switch((int)compression)
                {
                case 0: // '\0'
                case 1: // '\001'
                case 2: // '\002'
                    if(bitsPerPixel == 1)
                        imageType = 4;
                    else
                    if(bitsPerPixel == 4)
                        imageType = 5;
                    else
                    if(bitsPerPixel == 8)
                        imageType = 6;
                    else
                    if(bitsPerPixel == 24)
                        imageType = 7;
                    else
                    if(bitsPerPixel == 16)
                    {
                        imageType = 8;
                        redMask = 31744;
                        greenMask = 992;
                        blueMask = 31;
                        properties.put("red_mask", Integer.valueOf(redMask));
                        properties.put("green_mask", Integer.valueOf(greenMask));
                        properties.put("blue_mask", Integer.valueOf(blueMask));
                    } else
                    if(bitsPerPixel == 32)
                    {
                        imageType = 9;
                        redMask = 0xff0000;
                        greenMask = 65280;
                        blueMask = 255;
                        properties.put("red_mask", Integer.valueOf(redMask));
                        properties.put("green_mask", Integer.valueOf(greenMask));
                        properties.put("blue_mask", Integer.valueOf(blueMask));
                    }
                    if(size >= 52L)
                    {
                        redMask = (int)readDWord(inputStream);
                        greenMask = (int)readDWord(inputStream);
                        blueMask = (int)readDWord(inputStream);
                        properties.put("red_mask", Integer.valueOf(redMask));
                        properties.put("green_mask", Integer.valueOf(greenMask));
                        properties.put("blue_mask", Integer.valueOf(blueMask));
                    }
                    if(size == 56L)
                    {
                        alphaMask = (int)readDWord(inputStream);
                        properties.put("alpha_mask", Integer.valueOf(alphaMask));
                    }
                    int numberOfEntries = (int)((bitmapOffset - 14L - size) / 4L);
                    int sizeOfPalette = numberOfEntries * 4;
                    if(bitmapOffset == size)
                    {
                        switch(imageType)
                        {
                        case 4: // '\004'
                            sizeOfPalette = (int)(colorsUsed != 0L ? colorsUsed : 2L) * 4;
                            break;

                        case 5: // '\005'
                            sizeOfPalette = (int)(colorsUsed != 0L ? colorsUsed : 16L) * 4;
                            break;

                        case 6: // '\006'
                            sizeOfPalette = (int)(colorsUsed != 0L ? colorsUsed : 256L) * 4;
                            break;

                        default:
                            sizeOfPalette = 0;
                            break;
                        }
                        bitmapOffset = size + (long)sizeOfPalette;
                    }
                    readPalette(sizeOfPalette);
                    properties.put("bmp_version", "BMP v. 3.x");
                    break;

                case 3: // '\003'
                    if(bitsPerPixel == 16)
                        imageType = 8;
                    else
                    if(bitsPerPixel == 32)
                        imageType = 9;
                    redMask = (int)readDWord(inputStream);
                    greenMask = (int)readDWord(inputStream);
                    blueMask = (int)readDWord(inputStream);
                    if(size == 56L)
                    {
                        alphaMask = (int)readDWord(inputStream);
                        properties.put("alpha_mask", Integer.valueOf(alphaMask));
                    }
                    properties.put("red_mask", Integer.valueOf(redMask));
                    properties.put("green_mask", Integer.valueOf(greenMask));
                    properties.put("blue_mask", Integer.valueOf(blueMask));
                    if(colorsUsed != 0L)
                    {
                        int sizeOfPalette = (int)colorsUsed * 4;
                        readPalette(sizeOfPalette);
                    }
                    properties.put("bmp_version", "BMP v. 3.x NT");
                    break;

                default:
                    throw new RuntimeException("Invalid compression specified in BMP file.");
                }
            else
            if(size == 108L)
            {
                properties.put("bmp_version", "BMP v. 4.x");
                redMask = (int)readDWord(inputStream);
                greenMask = (int)readDWord(inputStream);
                blueMask = (int)readDWord(inputStream);
                alphaMask = (int)readDWord(inputStream);
                long csType = readDWord(inputStream);
                int redX = readLong(inputStream);
                int redY = readLong(inputStream);
                int redZ = readLong(inputStream);
                int greenX = readLong(inputStream);
                int greenY = readLong(inputStream);
                int greenZ = readLong(inputStream);
                int blueX = readLong(inputStream);
                int blueY = readLong(inputStream);
                int blueZ = readLong(inputStream);
                long gammaRed = readDWord(inputStream);
                long gammaGreen = readDWord(inputStream);
                long gammaBlue = readDWord(inputStream);
                if(bitsPerPixel == 1)
                    imageType = 10;
                else
                if(bitsPerPixel == 4)
                    imageType = 11;
                else
                if(bitsPerPixel == 8)
                    imageType = 12;
                else
                if(bitsPerPixel == 16)
                {
                    imageType = 13;
                    if((int)compression == 0)
                    {
                        redMask = 31744;
                        greenMask = 992;
                        blueMask = 31;
                    }
                } else
                if(bitsPerPixel == 24)
                    imageType = 14;
                else
                if(bitsPerPixel == 32)
                {
                    imageType = 15;
                    if((int)compression == 0)
                    {
                        redMask = 0xff0000;
                        greenMask = 65280;
                        blueMask = 255;
                    }
                }
                properties.put("red_mask", Integer.valueOf(redMask));
                properties.put("green_mask", Integer.valueOf(greenMask));
                properties.put("blue_mask", Integer.valueOf(blueMask));
                properties.put("alpha_mask", Integer.valueOf(alphaMask));
                int numberOfEntries = (int)((bitmapOffset - 14L - size) / 4L);
                int sizeOfPalette = numberOfEntries * 4;
                if(bitmapOffset == size)
                {
                    switch(imageType)
                    {
                    case 10: // '\n'
                        sizeOfPalette = (int)(colorsUsed != 0L ? colorsUsed : 2L) * 4;
                        break;

                    case 11: // '\013'
                        sizeOfPalette = (int)(colorsUsed != 0L ? colorsUsed : 16L) * 4;
                        break;

                    case 12: // '\f'
                        sizeOfPalette = (int)(colorsUsed != 0L ? colorsUsed : 256L) * 4;
                        break;

                    default:
                        sizeOfPalette = 0;
                        break;
                    }
                    bitmapOffset = size + (long)sizeOfPalette;
                }
                readPalette(sizeOfPalette);
                switch((int)csType)
                {
                case 0: // '\0'
                    properties.put("color_space", "LCS_CALIBRATED_RGB");
                    properties.put("redX", Integer.valueOf(redX));
                    properties.put("redY", Integer.valueOf(redY));
                    properties.put("redZ", Integer.valueOf(redZ));
                    properties.put("greenX", Integer.valueOf(greenX));
                    properties.put("greenY", Integer.valueOf(greenY));
                    properties.put("greenZ", Integer.valueOf(greenZ));
                    properties.put("blueX", Integer.valueOf(blueX));
                    properties.put("blueY", Integer.valueOf(blueY));
                    properties.put("blueZ", Integer.valueOf(blueZ));
                    properties.put("gamma_red", Long.valueOf(gammaRed));
                    properties.put("gamma_green", Long.valueOf(gammaGreen));
                    properties.put("gamma_blue", Long.valueOf(gammaBlue));
                    throw new RuntimeException("Not implemented yet.");

                case 1: // '\001'
                    properties.put("color_space", "LCS_sRGB");
                    break;

                case 2: // '\002'
                    properties.put("color_space", "LCS_CMYK");
                    throw new RuntimeException("Not implemented yet.");
                }
            } else
            {
                properties.put("bmp_version", "BMP v. 5.x");
                throw new RuntimeException("BMP version 5 not implemented yet.");
            }
        }
        if(height > 0)
        {
            isBottomUp = true;
        } else
        {
            isBottomUp = false;
            height = Math.abs(height);
        }
        if(bitsPerPixel == 1 || bitsPerPixel == 4 || bitsPerPixel == 8)
        {
            numBands = 1;
            if(imageType == 0 || imageType == 1 || imageType == 2)
            {
                int sizep = palette.length / 3;
                if(sizep > 256)
                    sizep = 256;
                byte r[] = new byte[sizep];
                byte g[] = new byte[sizep];
                byte b[] = new byte[sizep];
                for(int i = 0; i < sizep; i++)
                {
                    int off = 3 * i;
                    b[i] = palette[off];
                    g[i] = palette[off + 1];
                    r[i] = palette[off + 2];
                }

            } else
            {
                int sizep = palette.length / 4;
                if(sizep > 256)
                    sizep = 256;
                byte r[] = new byte[sizep];
                byte g[] = new byte[sizep];
                byte b[] = new byte[sizep];
                for(int i = 0; i < sizep; i++)
                {
                    int off = 4 * i;
                    b[i] = palette[off];
                    g[i] = palette[off + 1];
                    r[i] = palette[off + 2];
                }

            }
        } else
        if(bitsPerPixel == 16)
            numBands = 3;
        else
        if(bitsPerPixel == 32)
            numBands = alphaMask != 0 ? 4 : 3;
        else
            numBands = 3;
    }

    private byte[] getPalette(int group)
    {
        if(palette == null)
            return null;
        byte np[] = new byte[(palette.length / group) * 3];
        int e = palette.length / group;
        for(int k = 0; k < e; k++)
        {
            int src = k * group;
            int dest = k * 3;
            np[dest + 2] = palette[src++];
            np[dest + 1] = palette[src++];
            np[dest] = palette[src];
        }

        return np;
    }

    private Image getImage()
        throws IOException, BadElementException
    {
        byte bdata[] = null;
        switch(imageType)
        {
        case 0: // '\0'
            return read1Bit(3);

        case 1: // '\001'
            return read4Bit(3);

        case 2: // '\002'
            return read8Bit(3);

        case 3: // '\003'
            bdata = new byte[width * height * 3];
            read24Bit(bdata);
            return new ImgRaw(width, height, 3, 8, bdata);

        case 4: // '\004'
            return read1Bit(4);

        case 5: // '\005'
            switch((int)compression)
            {
            case 0: // '\0'
                return read4Bit(4);

            case 2: // '\002'
                return readRLE4();
            }
            throw new RuntimeException("Invalid compression specified for BMP file.");

        case 6: // '\006'
            switch((int)compression)
            {
            case 0: // '\0'
                return read8Bit(4);

            case 1: // '\001'
                return readRLE8();
            }
            throw new RuntimeException("Invalid compression specified for BMP file.");

        case 7: // '\007'
            bdata = new byte[width * height * 3];
            read24Bit(bdata);
            return new ImgRaw(width, height, 3, 8, bdata);

        case 8: // '\b'
            return read1632Bit(false);

        case 9: // '\t'
            return read1632Bit(true);

        case 10: // '\n'
            return read1Bit(4);

        case 11: // '\013'
            switch((int)compression)
            {
            case 0: // '\0'
                return read4Bit(4);

            case 2: // '\002'
                return readRLE4();
            }
            throw new RuntimeException("Invalid compression specified for BMP file.");

        case 12: // '\f'
            switch((int)compression)
            {
            case 0: // '\0'
                return read8Bit(4);

            case 1: // '\001'
                return readRLE8();
            }
            throw new RuntimeException("Invalid compression specified for BMP file.");

        case 13: // '\r'
            return read1632Bit(false);

        case 14: // '\016'
            bdata = new byte[width * height * 3];
            read24Bit(bdata);
            return new ImgRaw(width, height, 3, 8, bdata);

        case 15: // '\017'
            return read1632Bit(true);
        }
        return null;
    }

    private Image indexedModel(byte bdata[], int bpc, int paletteEntries)
        throws BadElementException
    {
        Image img = new ImgRaw(width, height, 1, bpc, bdata);
        PdfArray colorspace = new PdfArray();
        colorspace.add(PdfName.INDEXED);
        colorspace.add(PdfName.DEVICERGB);
        byte np[] = getPalette(paletteEntries);
        int len = np.length;
        colorspace.add(new PdfNumber(len / 3 - 1));
        colorspace.add(new PdfString(np));
        PdfDictionary ad = new PdfDictionary();
        ad.put(PdfName.COLORSPACE, colorspace);
        img.setAdditional(ad);
        return img;
    }

    private void readPalette(int sizeOfPalette)
        throws IOException
    {
        if(sizeOfPalette == 0)
            return;
        palette = new byte[sizeOfPalette];
        int r;
        for(int bytesRead = 0; bytesRead < sizeOfPalette; bytesRead += r)
        {
            r = inputStream.read(palette, bytesRead, sizeOfPalette - bytesRead);
            if(r < 0)
                throw new RuntimeException(MessageLocalization.getComposedMessage("incomplete.palette", new Object[0]));
        }

        properties.put("palette", palette);
    }

    private Image read1Bit(int paletteEntries)
        throws IOException, BadElementException
    {
        byte bdata[] = new byte[((width + 7) / 8) * height];
        int padding = 0;
        int bytesPerScanline = (int)Math.ceil((double)width / 8D);
        int remainder = bytesPerScanline % 4;
        if(remainder != 0)
            padding = 4 - remainder;
        int imSize = (bytesPerScanline + padding) * height;
        byte values[] = new byte[imSize];
        for(int bytesRead = 0; bytesRead < imSize; bytesRead += inputStream.read(values, bytesRead, imSize - bytesRead));
        if(isBottomUp)
        {
            for(int i = 0; i < height; i++)
                System.arraycopy(values, imSize - (i + 1) * (bytesPerScanline + padding), bdata, i * bytesPerScanline, bytesPerScanline);

        } else
        {
            for(int i = 0; i < height; i++)
                System.arraycopy(values, i * (bytesPerScanline + padding), bdata, i * bytesPerScanline, bytesPerScanline);

        }
        return indexedModel(bdata, 1, paletteEntries);
    }

    private Image read4Bit(int paletteEntries)
        throws IOException, BadElementException
    {
        byte bdata[] = new byte[((width + 1) / 2) * height];
        int padding = 0;
        int bytesPerScanline = (int)Math.ceil((double)width / 2D);
        int remainder = bytesPerScanline % 4;
        if(remainder != 0)
            padding = 4 - remainder;
        int imSize = (bytesPerScanline + padding) * height;
        byte values[] = new byte[imSize];
        for(int bytesRead = 0; bytesRead < imSize; bytesRead += inputStream.read(values, bytesRead, imSize - bytesRead));
        if(isBottomUp)
        {
            for(int i = 0; i < height; i++)
                System.arraycopy(values, imSize - (i + 1) * (bytesPerScanline + padding), bdata, i * bytesPerScanline, bytesPerScanline);

        } else
        {
            for(int i = 0; i < height; i++)
                System.arraycopy(values, i * (bytesPerScanline + padding), bdata, i * bytesPerScanline, bytesPerScanline);

        }
        return indexedModel(bdata, 4, paletteEntries);
    }

    private Image read8Bit(int paletteEntries)
        throws IOException, BadElementException
    {
        byte bdata[] = new byte[width * height];
        int padding = 0;
        int bitsPerScanline = width * 8;
        if(bitsPerScanline % 32 != 0)
        {
            padding = (bitsPerScanline / 32 + 1) * 32 - bitsPerScanline;
            padding = (int)Math.ceil((double)padding / 8D);
        }
        int imSize = (width + padding) * height;
        byte values[] = new byte[imSize];
        for(int bytesRead = 0; bytesRead < imSize; bytesRead += inputStream.read(values, bytesRead, imSize - bytesRead));
        if(isBottomUp)
        {
            for(int i = 0; i < height; i++)
                System.arraycopy(values, imSize - (i + 1) * (width + padding), bdata, i * width, width);

        } else
        {
            for(int i = 0; i < height; i++)
                System.arraycopy(values, i * (width + padding), bdata, i * width, width);

        }
        return indexedModel(bdata, 8, paletteEntries);
    }

    private void read24Bit(byte bdata[])
    {
        int padding = 0;
        int bitsPerScanline = width * 24;
        if(bitsPerScanline % 32 != 0)
        {
            padding = (bitsPerScanline / 32 + 1) * 32 - bitsPerScanline;
            padding = (int)Math.ceil((double)padding / 8D);
        }
        int imSize = ((width * 3 + 3) / 4) * 4 * height;
        byte values[] = new byte[imSize];
        try
        {
            int bytesRead = 0;
            do
            {
                if(bytesRead >= imSize)
                    break;
                int r = inputStream.read(values, bytesRead, imSize - bytesRead);
                if(r < 0)
                    break;
                bytesRead += r;
            } while(true);
        }
        catch(IOException ioe)
        {
            throw new ExceptionConverter(ioe);
        }
        int l = 0;
        if(isBottomUp)
        {
            int max = width * height * 3 - 1;
            int count = -padding;
            for(int i = 0; i < height; i++)
            {
                l = (max - (i + 1) * width * 3) + 1;
                count += padding;
                for(int j = 0; j < width; j++)
                {
                    bdata[l + 2] = values[count++];
                    bdata[l + 1] = values[count++];
                    bdata[l] = values[count++];
                    l += 3;
                }

            }

        } else
        {
            int count = -padding;
            for(int i = 0; i < height; i++)
            {
                count += padding;
                for(int j = 0; j < width; j++)
                {
                    bdata[l + 2] = values[count++];
                    bdata[l + 1] = values[count++];
                    bdata[l] = values[count++];
                    l += 3;
                }

            }

        }
    }

    private int findMask(int mask)
    {
        for(int k = 0; k < 32 && (mask & 1) != 1; k++)
            mask >>>= 1;

        return mask;
    }

    private int findShift(int mask)
    {
        int k;
        for(k = 0; k < 32 && (mask & 1) != 1; k++)
            mask >>>= 1;

        return k;
    }

    private Image read1632Bit(boolean is32)
        throws IOException, BadElementException
    {
        int red_mask = findMask(redMask);
        int red_shift = findShift(redMask);
        int red_factor = red_mask + 1;
        int green_mask = findMask(greenMask);
        int green_shift = findShift(greenMask);
        int green_factor = green_mask + 1;
        int blue_mask = findMask(blueMask);
        int blue_shift = findShift(blueMask);
        int blue_factor = blue_mask + 1;
        byte bdata[] = new byte[width * height * 3];
        int padding = 0;
        if(!is32)
        {
            int bitsPerScanline = width * 16;
            if(bitsPerScanline % 32 != 0)
            {
                padding = (bitsPerScanline / 32 + 1) * 32 - bitsPerScanline;
                padding = (int)Math.ceil((double)padding / 8D);
            }
        }
        int imSize = (int)imageSize;
        if(imSize == 0)
            imSize = (int)(bitmapFileSize - bitmapOffset);
        int l = 0;
        if(isBottomUp)
        {
            for(int i = height - 1; i >= 0; i--)
            {
                l = width * 3 * i;
                for(int j = 0; j < width; j++)
                {
                    int v;
                    if(is32)
                        v = (int)readDWord(inputStream);
                    else
                        v = readWord(inputStream);
                    bdata[l++] = (byte)(((v >>> red_shift & red_mask) * 256) / red_factor);
                    bdata[l++] = (byte)(((v >>> green_shift & green_mask) * 256) / green_factor);
                    bdata[l++] = (byte)(((v >>> blue_shift & blue_mask) * 256) / blue_factor);
                }

                for(int m = 0; m < padding; m++)
                    inputStream.read();

            }

        } else
        {
            for(int i = 0; i < height; i++)
            {
                for(int j = 0; j < width; j++)
                {
                    int v;
                    if(is32)
                        v = (int)readDWord(inputStream);
                    else
                        v = readWord(inputStream);
                    bdata[l++] = (byte)(((v >>> red_shift & red_mask) * 256) / red_factor);
                    bdata[l++] = (byte)(((v >>> green_shift & green_mask) * 256) / green_factor);
                    bdata[l++] = (byte)(((v >>> blue_shift & blue_mask) * 256) / blue_factor);
                }

                for(int m = 0; m < padding; m++)
                    inputStream.read();

            }

        }
        return new ImgRaw(width, height, 3, 8, bdata);
    }

    private Image readRLE8()
        throws IOException, BadElementException
    {
        int imSize = (int)imageSize;
        if(imSize == 0)
            imSize = (int)(bitmapFileSize - bitmapOffset);
        byte values[] = new byte[imSize];
        for(int bytesRead = 0; bytesRead < imSize; bytesRead += inputStream.read(values, bytesRead, imSize - bytesRead));
        byte val[] = decodeRLE(true, values);
        imSize = width * height;
        if(isBottomUp)
        {
            byte temp[] = new byte[val.length];
            int bytesPerScanline = width;
            for(int i = 0; i < height; i++)
                System.arraycopy(val, imSize - (i + 1) * bytesPerScanline, temp, i * bytesPerScanline, bytesPerScanline);

            val = temp;
        }
        return indexedModel(val, 8, 4);
    }

    private Image readRLE4()
        throws IOException, BadElementException
    {
        int imSize = (int)imageSize;
        if(imSize == 0)
            imSize = (int)(bitmapFileSize - bitmapOffset);
        byte values[] = new byte[imSize];
        for(int bytesRead = 0; bytesRead < imSize; bytesRead += inputStream.read(values, bytesRead, imSize - bytesRead));
        byte val[] = decodeRLE(false, values);
        if(isBottomUp)
        {
            byte inverted[] = val;
            val = new byte[width * height];
            int l = 0;
            for(int i = height - 1; i >= 0; i--)
            {
                int index = i * width;
                for(int lineEnd = l + width; l != lineEnd;)
                    val[l++] = inverted[index++];

            }

        }
        int stride = (width + 1) / 2;
        byte bdata[] = new byte[stride * height];
        int ptr = 0;
        int sh = 0;
        for(int h = 0; h < height; h++)
        {
            for(int w = 0; w < width; w++)
                if((w & 1) == 0)
                    bdata[sh + w / 2] = (byte)(val[ptr++] << 4);
                else
                    bdata[sh + w / 2] |= (byte)(val[ptr++] & 0xf);

            sh += stride;
        }

        return indexedModel(bdata, 4, 4);
    }

    private byte[] decodeRLE(boolean is8, byte values[])
    {
        byte val[] = new byte[width * height];
        try
        {
            int ptr = 0;
            int x = 0;
            int q = 0;
            int y = 0;
            do
            {
                if(y >= height || ptr >= values.length)
                    break;
                int count = values[ptr++] & 0xff;
                if(count != 0)
                {
                    int bt = values[ptr++] & 0xff;
                    if(is8)
                    {
                        for(int i = count; i != 0; i--)
                            val[q++] = (byte)bt;

                    } else
                    {
                        for(int i = 0; i < count; i++)
                            val[q++] = (byte)((i & 1) != 1 ? bt >>> 4 & 0xf : bt & 0xf);

                    }
                    x += count;
                    continue;
                }
                count = values[ptr++] & 0xff;
                if(count == 1)
                    break;
                switch(count)
                {
                case 0: // '\0'
                    x = 0;
                    q = ++y * width;
                    break;

                case 2: // '\002'
                    x += values[ptr++] & 0xff;
                    y += values[ptr++] & 0xff;
                    q = y * width + x;
                    break;

                default:
                    if(is8)
                    {
                        for(int i = count; i != 0; i--)
                            val[q++] = (byte)(values[ptr++] & 0xff);

                    } else
                    {
                        int bt = 0;
                        for(int i = 0; i < count; i++)
                        {
                            if((i & 1) == 0)
                                bt = values[ptr++] & 0xff;
                            val[q++] = (byte)((i & 1) != 1 ? bt >>> 4 & 0xf : bt & 0xf);
                        }

                    }
                    x += count;
                    if(is8)
                    {
                        if((count & 1) == 1)
                            ptr++;
                        break;
                    }
                    if((count & 3) == 1 || (count & 3) == 2)
                        ptr++;
                    break;
                }
            } while(true);
        }
        catch(RuntimeException e) { }
        return val;
    }

    private int readUnsignedByte(InputStream stream)
        throws IOException
    {
        return stream.read() & 0xff;
    }

    private int readUnsignedShort(InputStream stream)
        throws IOException
    {
        int b1 = readUnsignedByte(stream);
        int b2 = readUnsignedByte(stream);
        return (b2 << 8 | b1) & 0xffff;
    }

    private int readShort(InputStream stream)
        throws IOException
    {
        int b1 = readUnsignedByte(stream);
        int b2 = readUnsignedByte(stream);
        return b2 << 8 | b1;
    }

    private int readWord(InputStream stream)
        throws IOException
    {
        return readUnsignedShort(stream);
    }

    private long readUnsignedInt(InputStream stream)
        throws IOException
    {
        int b1 = readUnsignedByte(stream);
        int b2 = readUnsignedByte(stream);
        int b3 = readUnsignedByte(stream);
        int b4 = readUnsignedByte(stream);
        long l = b4 << 24 | b3 << 16 | b2 << 8 | b1;
        return l & -1L;
    }

    private int readInt(InputStream stream)
        throws IOException
    {
        int b1 = readUnsignedByte(stream);
        int b2 = readUnsignedByte(stream);
        int b3 = readUnsignedByte(stream);
        int b4 = readUnsignedByte(stream);
        return b4 << 24 | b3 << 16 | b2 << 8 | b1;
    }

    private long readDWord(InputStream stream)
        throws IOException
    {
        return readUnsignedInt(stream);
    }

    private int readLong(InputStream stream)
        throws IOException
    {
        return readInt(stream);
    }

    private InputStream inputStream;
    private long bitmapFileSize;
    private long bitmapOffset;
    private long compression;
    private long imageSize;
    private byte palette[];
    private int imageType;
    private int numBands;
    private boolean isBottomUp;
    private int bitsPerPixel;
    private int redMask;
    private int greenMask;
    private int blueMask;
    private int alphaMask;
    public HashMap properties;
    private long xPelsPerMeter;
    private long yPelsPerMeter;
    private static final int VERSION_2_1_BIT = 0;
    private static final int VERSION_2_4_BIT = 1;
    private static final int VERSION_2_8_BIT = 2;
    private static final int VERSION_2_24_BIT = 3;
    private static final int VERSION_3_1_BIT = 4;
    private static final int VERSION_3_4_BIT = 5;
    private static final int VERSION_3_8_BIT = 6;
    private static final int VERSION_3_24_BIT = 7;
    private static final int VERSION_3_NT_16_BIT = 8;
    private static final int VERSION_3_NT_32_BIT = 9;
    private static final int VERSION_4_1_BIT = 10;
    private static final int VERSION_4_4_BIT = 11;
    private static final int VERSION_4_8_BIT = 12;
    private static final int VERSION_4_16_BIT = 13;
    private static final int VERSION_4_24_BIT = 14;
    private static final int VERSION_4_32_BIT = 15;
    private static final int LCS_CALIBRATED_RGB = 0;
    private static final int LCS_sRGB = 1;
    private static final int LCS_CMYK = 2;
    private static final int BI_RGB = 0;
    private static final int BI_RLE8 = 1;
    private static final int BI_RLE4 = 2;
    private static final int BI_BITFIELDS = 3;
    int width;
    int height;
}
