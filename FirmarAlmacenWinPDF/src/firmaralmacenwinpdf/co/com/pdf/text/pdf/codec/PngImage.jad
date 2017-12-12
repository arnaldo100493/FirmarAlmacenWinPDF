// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PngImage.java

package co.com.pdf.text.pdf.codec;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.*;
import java.io.*;
import java.net.URL;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class PngImage
{
    static class NewByteArrayOutputStream extends ByteArrayOutputStream
    {

        public byte[] getBuf()
        {
            return buf;
        }

        NewByteArrayOutputStream()
        {
        }
    }


    PngImage(InputStream is)
    {
        additional = new PdfDictionary();
        idat = new NewByteArrayOutputStream();
        transRedGray = -1;
        transGreen = -1;
        transBlue = -1;
        gamma = 1.0F;
        hasCHRM = false;
        this.is = is;
    }

    public static Image getImage(URL url)
        throws IOException
    {
        InputStream is = null;
        Image image1;
        is = url.openStream();
        Image img = getImage(is);
        img.setUrl(url);
        image1 = img;
        if(is != null)
            is.close();
        return image1;
        Exception exception;
        exception;
        if(is != null)
            is.close();
        throw exception;
    }

    public static Image getImage(InputStream is)
        throws IOException
    {
        PngImage png = new PngImage(is);
        return png.getImage();
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

    boolean checkMarker(String s)
    {
        if(s.length() != 4)
            return false;
        for(int k = 0; k < 4; k++)
        {
            char c = s.charAt(k);
            if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z'))
                return false;
        }

        return true;
    }

    void readPng()
        throws IOException
    {
        for(int i = 0; i < PNGID.length; i++)
            if(PNGID[i] != is.read())
                throw new IOException(MessageLocalization.getComposedMessage("file.is.not.a.valid.png", new Object[0]));

        byte buffer[] = new byte[4096];
        do
        {
            int len = getInt(is);
            String marker = getString(is);
            if(len < 0 || !checkMarker(marker))
                throw new IOException(MessageLocalization.getComposedMessage("corrupted.png.file", new Object[0]));
            if("IDAT".equals(marker))
            {
                int size;
                for(; len != 0; len -= size)
                {
                    size = is.read(buffer, 0, Math.min(len, 4096));
                    if(size < 0)
                        return;
                    idat.write(buffer, 0, size);
                }

            } else
            if("tRNS".equals(marker))
            {
                switch(colorType)
                {
                case 1: // '\001'
                default:
                    break;

                case 0: // '\0'
                    if(len >= 2)
                    {
                        len -= 2;
                        int gray = getWord(is);
                        if(bitDepth == 16)
                            transRedGray = gray;
                        else
                            additional.put(PdfName.MASK, new PdfLiteral((new StringBuilder()).append("[").append(gray).append(" ").append(gray).append("]").toString()));
                    }
                    break;

                case 2: // '\002'
                    if(len < 6)
                        break;
                    len -= 6;
                    int red = getWord(is);
                    int green = getWord(is);
                    int blue = getWord(is);
                    if(bitDepth == 16)
                    {
                        transRedGray = red;
                        transGreen = green;
                        transBlue = blue;
                    } else
                    {
                        additional.put(PdfName.MASK, new PdfLiteral((new StringBuilder()).append("[").append(red).append(" ").append(red).append(" ").append(green).append(" ").append(green).append(" ").append(blue).append(" ").append(blue).append("]").toString()));
                    }
                    break;

                case 3: // '\003'
                    if(len <= 0)
                        break;
                    trans = new byte[len];
                    for(int k = 0; k < len; k++)
                        trans[k] = (byte)is.read();

                    len = 0;
                    break;
                }
                Utilities.skip(is, len);
            } else
            if("IHDR".equals(marker))
            {
                width = getInt(is);
                height = getInt(is);
                bitDepth = is.read();
                colorType = is.read();
                compressionMethod = is.read();
                filterMethod = is.read();
                interlaceMethod = is.read();
            } else
            if("PLTE".equals(marker))
            {
                if(colorType == 3)
                {
                    PdfArray colorspace = new PdfArray();
                    colorspace.add(PdfName.INDEXED);
                    colorspace.add(getColorspace());
                    colorspace.add(new PdfNumber(len / 3 - 1));
                    ByteBuffer colortable = new ByteBuffer();
                    while(len-- > 0) 
                        colortable.append_i(is.read());
                    colorspace.add(new PdfString(colorTable = colortable.toByteArray()));
                    additional.put(PdfName.COLORSPACE, colorspace);
                } else
                {
                    Utilities.skip(is, len);
                }
            } else
            if("pHYs".equals(marker))
            {
                int dx = getInt(is);
                int dy = getInt(is);
                int unit = is.read();
                if(unit == 1)
                {
                    dpiX = (int)((float)dx * 0.0254F + 0.5F);
                    dpiY = (int)((float)dy * 0.0254F + 0.5F);
                } else
                if(dy != 0)
                    XYRatio = (float)dx / (float)dy;
            } else
            if("cHRM".equals(marker))
            {
                xW = (float)getInt(is) / 100000F;
                yW = (float)getInt(is) / 100000F;
                xR = (float)getInt(is) / 100000F;
                yR = (float)getInt(is) / 100000F;
                xG = (float)getInt(is) / 100000F;
                yG = (float)getInt(is) / 100000F;
                xB = (float)getInt(is) / 100000F;
                yB = (float)getInt(is) / 100000F;
                hasCHRM = Math.abs(xW) >= 0.0001F && Math.abs(yW) >= 0.0001F && Math.abs(xR) >= 0.0001F && Math.abs(yR) >= 0.0001F && Math.abs(xG) >= 0.0001F && Math.abs(yG) >= 0.0001F && Math.abs(xB) >= 0.0001F && Math.abs(yB) >= 0.0001F;
            } else
            if("sRGB".equals(marker))
            {
                int ri = is.read();
                intent = intents[ri];
                gamma = 2.2F;
                xW = 0.3127F;
                yW = 0.329F;
                xR = 0.64F;
                yR = 0.33F;
                xG = 0.3F;
                yG = 0.6F;
                xB = 0.15F;
                yB = 0.06F;
                hasCHRM = true;
            } else
            if("gAMA".equals(marker))
            {
                int gm = getInt(is);
                if(gm != 0)
                {
                    gamma = 100000F / (float)gm;
                    if(!hasCHRM)
                    {
                        xW = 0.3127F;
                        yW = 0.329F;
                        xR = 0.64F;
                        yR = 0.33F;
                        xG = 0.3F;
                        yG = 0.6F;
                        xB = 0.15F;
                        yB = 0.06F;
                        hasCHRM = true;
                    }
                }
            } else
            if("iCCP".equals(marker))
            {
                do
                    len--;
                while(is.read() != 0);
                is.read();
                byte icccom[] = new byte[--len];
                int p = 0;
                int r;
                for(; len > 0; len -= r)
                {
                    r = is.read(icccom, p, len);
                    if(r < 0)
                        throw new IOException(MessageLocalization.getComposedMessage("premature.end.of.file", new Object[0]));
                    p += r;
                }

                byte iccp[] = PdfReader.FlateDecode(icccom, true);
                icccom = null;
                try
                {
                    icc_profile = ICC_Profile.getInstance(iccp);
                }
                catch(RuntimeException e)
                {
                    icc_profile = null;
                }
            } else
            {
                if("IEND".equals(marker))
                    break;
                Utilities.skip(is, len);
            }
            Utilities.skip(is, 4);
        } while(true);
    }

    PdfObject getColorspace()
    {
        if(icc_profile != null)
            if((colorType & 2) == 0)
                return PdfName.DEVICEGRAY;
            else
                return PdfName.DEVICERGB;
        if(gamma == 1.0F && !hasCHRM)
            if((colorType & 2) == 0)
                return PdfName.DEVICEGRAY;
            else
                return PdfName.DEVICERGB;
        PdfArray array = new PdfArray();
        PdfDictionary dic = new PdfDictionary();
        if((colorType & 2) == 0)
        {
            if(gamma == 1.0F)
                return PdfName.DEVICEGRAY;
            array.add(PdfName.CALGRAY);
            dic.put(PdfName.GAMMA, new PdfNumber(gamma));
            dic.put(PdfName.WHITEPOINT, new PdfLiteral("[1 1 1]"));
            array.add(dic);
        } else
        {
            PdfObject wp = new PdfLiteral("[1 1 1]");
            array.add(PdfName.CALRGB);
            if(gamma != 1.0F)
            {
                PdfArray gm = new PdfArray();
                PdfNumber n = new PdfNumber(gamma);
                gm.add(n);
                gm.add(n);
                gm.add(n);
                dic.put(PdfName.GAMMA, gm);
            }
            if(hasCHRM)
            {
                float z = yW * (((xG - xB) * yR - (xR - xB) * yG) + (xR - xG) * yB);
                float YA = (yR * (((xG - xB) * yW - (xW - xB) * yG) + (xW - xG) * yB)) / z;
                float XA = (YA * xR) / yR;
                float ZA = YA * ((1.0F - xR) / yR - 1.0F);
                float YB = (-yG * (((xR - xB) * yW - (xW - xB) * yR) + (xW - xR) * yB)) / z;
                float XB = (YB * xG) / yG;
                float ZB = YB * ((1.0F - xG) / yG - 1.0F);
                float YC = (yB * (((xR - xG) * yW - (xW - xG) * yW) + (xW - xR) * yG)) / z;
                float XC = (YC * xB) / yB;
                float ZC = YC * ((1.0F - xB) / yB - 1.0F);
                float XW = XA + XB + XC;
                float YW = 1.0F;
                float ZW = ZA + ZB + ZC;
                PdfArray wpa = new PdfArray();
                wpa.add(new PdfNumber(XW));
                wpa.add(new PdfNumber(YW));
                wpa.add(new PdfNumber(ZW));
                wp = wpa;
                PdfArray matrix = new PdfArray();
                matrix.add(new PdfNumber(XA));
                matrix.add(new PdfNumber(YA));
                matrix.add(new PdfNumber(ZA));
                matrix.add(new PdfNumber(XB));
                matrix.add(new PdfNumber(YB));
                matrix.add(new PdfNumber(ZB));
                matrix.add(new PdfNumber(XC));
                matrix.add(new PdfNumber(YC));
                matrix.add(new PdfNumber(ZC));
                dic.put(PdfName.MATRIX, matrix);
            }
            dic.put(PdfName.WHITEPOINT, wp);
            array.add(dic);
        }
        return array;
    }

    Image getImage()
        throws IOException
    {
        readPng();
        try
        {
            int pal0 = 0;
            int palIdx = 0;
            palShades = false;
            if(trans != null)
            {
                int k = 0;
                do
                {
                    if(k >= trans.length)
                        break;
                    int n = trans[k] & 0xff;
                    if(n == 0)
                    {
                        pal0++;
                        palIdx = k;
                    }
                    if(n != 0 && n != 255)
                    {
                        palShades = true;
                        break;
                    }
                    k++;
                } while(true);
            }
            if((colorType & 4) != 0)
                palShades = true;
            genBWMask = !palShades && (pal0 > 1 || transRedGray >= 0);
            if(!palShades && !genBWMask && pal0 == 1)
                additional.put(PdfName.MASK, new PdfLiteral((new StringBuilder()).append("[").append(palIdx).append(" ").append(palIdx).append("]").toString()));
            boolean needDecode = interlaceMethod == 1 || bitDepth == 16 || (colorType & 4) != 0 || palShades || genBWMask;
            switch(colorType)
            {
            case 0: // '\0'
                inputBands = 1;
                break;

            case 2: // '\002'
                inputBands = 3;
                break;

            case 3: // '\003'
                inputBands = 1;
                break;

            case 4: // '\004'
                inputBands = 2;
                break;

            case 6: // '\006'
                inputBands = 4;
                break;
            }
            if(needDecode)
                decodeIdat();
            int components = inputBands;
            if((colorType & 4) != 0)
                components--;
            int bpc = bitDepth;
            if(bpc == 16)
                bpc = 8;
            Image img;
            if(image != null)
            {
                if(colorType == 3)
                    img = new ImgRaw(width, height, components, bpc, image);
                else
                    img = Image.getInstance(width, height, components, bpc, image);
            } else
            {
                img = new ImgRaw(width, height, components, bpc, idat.toByteArray());
                img.setDeflated(true);
                PdfDictionary decodeparms = new PdfDictionary();
                decodeparms.put(PdfName.BITSPERCOMPONENT, new PdfNumber(bitDepth));
                decodeparms.put(PdfName.PREDICTOR, new PdfNumber(15));
                decodeparms.put(PdfName.COLUMNS, new PdfNumber(width));
                decodeparms.put(PdfName.COLORS, new PdfNumber(colorType != 3 && (colorType & 2) != 0 ? 3 : 1));
                additional.put(PdfName.DECODEPARMS, decodeparms);
            }
            if(additional.get(PdfName.COLORSPACE) == null)
                additional.put(PdfName.COLORSPACE, getColorspace());
            if(intent != null)
                additional.put(PdfName.INTENT, intent);
            if(additional.size() > 0)
                img.setAdditional(additional);
            if(icc_profile != null)
                img.tagICC(icc_profile);
            if(palShades)
            {
                Image im2 = Image.getInstance(width, height, 1, 8, smask);
                im2.makeMask();
                img.setImageMask(im2);
            }
            if(genBWMask)
            {
                Image im2 = Image.getInstance(width, height, 1, 1, smask);
                im2.makeMask();
                img.setImageMask(im2);
            }
            img.setDpi(dpiX, dpiY);
            img.setXYRatio(XYRatio);
            img.setOriginalType(2);
            return img;
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    void decodeIdat()
    {
        int nbitDepth = bitDepth;
        if(nbitDepth == 16)
            nbitDepth = 8;
        int size = -1;
        bytesPerPixel = bitDepth != 16 ? 1 : 2;
        switch(colorType)
        {
        case 0: // '\0'
            size = ((nbitDepth * width + 7) / 8) * height;
            break;

        case 2: // '\002'
            size = width * 3 * height;
            bytesPerPixel *= 3;
            break;

        case 3: // '\003'
            if(interlaceMethod == 1)
                size = ((nbitDepth * width + 7) / 8) * height;
            bytesPerPixel = 1;
            break;

        case 4: // '\004'
            size = width * height;
            bytesPerPixel *= 2;
            break;

        case 6: // '\006'
            size = width * 3 * height;
            bytesPerPixel *= 4;
            break;
        }
        if(size >= 0)
            image = new byte[size];
        if(palShades)
            smask = new byte[width * height];
        else
        if(genBWMask)
            smask = new byte[((width + 7) / 8) * height];
        ByteArrayInputStream bai = new ByteArrayInputStream(idat.getBuf(), 0, idat.size());
        InputStream infStream = new InflaterInputStream(bai, new Inflater());
        dataStream = new DataInputStream(infStream);
        if(interlaceMethod != 1)
        {
            decodePass(0, 0, 1, 1, width, height);
        } else
        {
            decodePass(0, 0, 8, 8, (width + 7) / 8, (height + 7) / 8);
            decodePass(4, 0, 8, 8, (width + 3) / 8, (height + 7) / 8);
            decodePass(0, 4, 4, 8, (width + 3) / 4, (height + 3) / 8);
            decodePass(2, 0, 4, 4, (width + 1) / 4, (height + 3) / 4);
            decodePass(0, 2, 2, 4, (width + 1) / 2, (height + 1) / 4);
            decodePass(1, 0, 2, 2, width / 2, (height + 1) / 2);
            decodePass(0, 1, 1, 2, width, height / 2);
        }
    }

    void decodePass(int xOffset, int yOffset, int xStep, int yStep, int passWidth, int passHeight)
    {
        if(passWidth == 0 || passHeight == 0)
            return;
        int bytesPerRow = (inputBands * passWidth * bitDepth + 7) / 8;
        byte curr[] = new byte[bytesPerRow];
        byte prior[] = new byte[bytesPerRow];
        int srcY = 0;
        for(int dstY = yOffset; srcY < passHeight; dstY += yStep)
        {
            int filter = 0;
            try
            {
                filter = dataStream.read();
                dataStream.readFully(curr, 0, bytesPerRow);
            }
            catch(Exception e) { }
            switch(filter)
            {
            case 1: // '\001'
                decodeSubFilter(curr, bytesPerRow, bytesPerPixel);
                break;

            case 2: // '\002'
                decodeUpFilter(curr, prior, bytesPerRow);
                break;

            case 3: // '\003'
                decodeAverageFilter(curr, prior, bytesPerRow, bytesPerPixel);
                break;

            case 4: // '\004'
                decodePaethFilter(curr, prior, bytesPerRow, bytesPerPixel);
                break;

            default:
                throw new RuntimeException(MessageLocalization.getComposedMessage("png.filter.unknown", new Object[0]));

            case 0: // '\0'
                break;
            }
            processPixels(curr, xOffset, xStep, dstY, passWidth);
            byte tmp[] = prior;
            prior = curr;
            curr = tmp;
            srcY++;
        }

    }

    void processPixels(byte curr[], int xOffset, int step, int y, int width)
    {
        int out[] = getPixel(curr);
        int sizes = 0;
        switch(colorType)
        {
        case 0: // '\0'
        case 3: // '\003'
        case 4: // '\004'
            sizes = 1;
            break;

        case 2: // '\002'
        case 6: // '\006'
            sizes = 3;
            break;
        }
        if(image != null)
        {
            int dstX = xOffset;
            int yStride = (sizes * this.width * (bitDepth != 16 ? bitDepth : 8) + 7) / 8;
            for(int srcX = 0; srcX < width; srcX++)
            {
                setPixel(image, out, inputBands * srcX, sizes, dstX, y, bitDepth, yStride);
                dstX += step;
            }

        }
        if(palShades)
        {
            if((colorType & 4) != 0)
            {
                if(bitDepth == 16)
                {
                    for(int k = 0; k < width; k++)
                        out[k * inputBands + sizes] >>>= 8;

                }
                int yStride = this.width;
                int dstX = xOffset;
                for(int srcX = 0; srcX < width; srcX++)
                {
                    setPixel(smask, out, inputBands * srcX + sizes, 1, dstX, y, 8, yStride);
                    dstX += step;
                }

            } else
            {
                int yStride = this.width;
                int v[] = new int[1];
                int dstX = xOffset;
                for(int srcX = 0; srcX < width; srcX++)
                {
                    int idx = out[srcX];
                    if(idx < trans.length)
                        v[0] = trans[idx];
                    else
                        v[0] = 255;
                    setPixel(smask, v, 0, 1, dstX, y, 8, yStride);
                    dstX += step;
                }

            }
        } else
        if(genBWMask)
            switch(colorType)
            {
            case 1: // '\001'
            default:
                break;

            case 3: // '\003'
            {
                int yStride = (this.width + 7) / 8;
                int v[] = new int[1];
                int dstX = xOffset;
                for(int srcX = 0; srcX < width; srcX++)
                {
                    int idx = out[srcX];
                    v[0] = idx >= trans.length || trans[idx] != 0 ? 0 : 1;
                    setPixel(smask, v, 0, 1, dstX, y, 1, yStride);
                    dstX += step;
                }

                break;
            }

            case 0: // '\0'
            {
                int yStride = (this.width + 7) / 8;
                int v[] = new int[1];
                int dstX = xOffset;
                for(int srcX = 0; srcX < width; srcX++)
                {
                    int g = out[srcX];
                    v[0] = g != transRedGray ? 0 : 1;
                    setPixel(smask, v, 0, 1, dstX, y, 1, yStride);
                    dstX += step;
                }

                break;
            }

            case 2: // '\002'
            {
                int yStride = (this.width + 7) / 8;
                int v[] = new int[1];
                int dstX = xOffset;
                for(int srcX = 0; srcX < width; srcX++)
                {
                    int markRed = inputBands * srcX;
                    v[0] = out[markRed] != transRedGray || out[markRed + 1] != transGreen || out[markRed + 2] != transBlue ? 0 : 1;
                    setPixel(smask, v, 0, 1, dstX, y, 1, yStride);
                    dstX += step;
                }

                break;
            }
            }
    }

    static int getPixel(byte image[], int x, int y, int bitDepth, int bytesPerRow)
    {
        if(bitDepth == 8)
        {
            int pos = bytesPerRow * y + x;
            return image[pos] & 0xff;
        } else
        {
            int pos = bytesPerRow * y + x / (8 / bitDepth);
            int v = image[pos] >> 8 - bitDepth * (x % (8 / bitDepth)) - bitDepth;
            return v & (1 << bitDepth) - 1;
        }
    }

    static void setPixel(byte image[], int data[], int offset, int size, int x, int y, int bitDepth, int bytesPerRow)
    {
        if(bitDepth == 8)
        {
            int pos = bytesPerRow * y + size * x;
            for(int k = 0; k < size; k++)
                image[pos + k] = (byte)data[k + offset];

        } else
        if(bitDepth == 16)
        {
            int pos = bytesPerRow * y + size * x;
            for(int k = 0; k < size; k++)
                image[pos + k] = (byte)(data[k + offset] >>> 8);

        } else
        {
            int pos = bytesPerRow * y + x / (8 / bitDepth);
            int v = data[offset] << 8 - bitDepth * (x % (8 / bitDepth)) - bitDepth;
            image[pos] |= v;
        }
    }

    int[] getPixel(byte curr[])
    {
        int out[];
        switch(bitDepth)
        {
        case 8: // '\b'
            out = new int[curr.length];
            for(int k = 0; k < out.length; k++)
                out[k] = curr[k] & 0xff;

            return out;

        case 16: // '\020'
            out = new int[curr.length / 2];
            for(int k = 0; k < out.length; k++)
                out[k] = ((curr[k * 2] & 0xff) << 8) + (curr[k * 2 + 1] & 0xff);

            return out;
        }
        out = new int[(curr.length * 8) / bitDepth];
        int idx = 0;
        int passes = 8 / bitDepth;
        int mask = (1 << bitDepth) - 1;
        for(int k = 0; k < curr.length; k++)
        {
            for(int j = passes - 1; j >= 0; j--)
                out[idx++] = curr[k] >>> bitDepth * j & mask;

        }

        return out;
    }

    private static void decodeSubFilter(byte curr[], int count, int bpp)
    {
        for(int i = bpp; i < count; i++)
        {
            int val = curr[i] & 0xff;
            val += curr[i - bpp] & 0xff;
            curr[i] = (byte)val;
        }

    }

    private static void decodeUpFilter(byte curr[], byte prev[], int count)
    {
        for(int i = 0; i < count; i++)
        {
            int raw = curr[i] & 0xff;
            int prior = prev[i] & 0xff;
            curr[i] = (byte)(raw + prior);
        }

    }

    private static void decodeAverageFilter(byte curr[], byte prev[], int count, int bpp)
    {
        for(int i = 0; i < bpp; i++)
        {
            int raw = curr[i] & 0xff;
            int priorRow = prev[i] & 0xff;
            curr[i] = (byte)(raw + priorRow / 2);
        }

        for(int i = bpp; i < count; i++)
        {
            int raw = curr[i] & 0xff;
            int priorPixel = curr[i - bpp] & 0xff;
            int priorRow = prev[i] & 0xff;
            curr[i] = (byte)(raw + (priorPixel + priorRow) / 2);
        }

    }

    private static int paethPredictor(int a, int b, int c)
    {
        int p = (a + b) - c;
        int pa = Math.abs(p - a);
        int pb = Math.abs(p - b);
        int pc = Math.abs(p - c);
        if(pa <= pb && pa <= pc)
            return a;
        if(pb <= pc)
            return b;
        else
            return c;
    }

    private static void decodePaethFilter(byte curr[], byte prev[], int count, int bpp)
    {
        for(int i = 0; i < bpp; i++)
        {
            int raw = curr[i] & 0xff;
            int priorRow = prev[i] & 0xff;
            curr[i] = (byte)(raw + priorRow);
        }

        for(int i = bpp; i < count; i++)
        {
            int raw = curr[i] & 0xff;
            int priorPixel = curr[i - bpp] & 0xff;
            int priorRow = prev[i] & 0xff;
            int priorRowPixel = prev[i - bpp] & 0xff;
            curr[i] = (byte)(raw + paethPredictor(priorPixel, priorRow, priorRowPixel));
        }

    }

    public static final int getInt(InputStream is)
        throws IOException
    {
        return (is.read() << 24) + (is.read() << 16) + (is.read() << 8) + is.read();
    }

    public static final int getWord(InputStream is)
        throws IOException
    {
        return (is.read() << 8) + is.read();
    }

    public static final String getString(InputStream is)
        throws IOException
    {
        StringBuffer buf = new StringBuffer();
        for(int i = 0; i < 4; i++)
            buf.append((char)is.read());

        return buf.toString();
    }

    public static final int PNGID[] = {
        137, 80, 78, 71, 13, 10, 26, 10
    };
    public static final String IHDR = "IHDR";
    public static final String PLTE = "PLTE";
    public static final String IDAT = "IDAT";
    public static final String IEND = "IEND";
    public static final String tRNS = "tRNS";
    public static final String pHYs = "pHYs";
    public static final String gAMA = "gAMA";
    public static final String cHRM = "cHRM";
    public static final String sRGB = "sRGB";
    public static final String iCCP = "iCCP";
    private static final int TRANSFERSIZE = 4096;
    private static final int PNG_FILTER_NONE = 0;
    private static final int PNG_FILTER_SUB = 1;
    private static final int PNG_FILTER_UP = 2;
    private static final int PNG_FILTER_AVERAGE = 3;
    private static final int PNG_FILTER_PAETH = 4;
    private static final PdfName intents[];
    InputStream is;
    DataInputStream dataStream;
    int width;
    int height;
    int bitDepth;
    int colorType;
    int compressionMethod;
    int filterMethod;
    int interlaceMethod;
    PdfDictionary additional;
    byte image[];
    byte smask[];
    byte trans[];
    NewByteArrayOutputStream idat;
    int dpiX;
    int dpiY;
    float XYRatio;
    boolean genBWMask;
    boolean palShades;
    int transRedGray;
    int transGreen;
    int transBlue;
    int inputBands;
    int bytesPerPixel;
    byte colorTable[];
    float gamma;
    boolean hasCHRM;
    float xW;
    float yW;
    float xR;
    float yR;
    float xG;
    float yG;
    float xB;
    float yB;
    PdfName intent;
    ICC_Profile icc_profile;

    static 
    {
        intents = (new PdfName[] {
            PdfName.PERCEPTUAL, PdfName.RELATIVECOLORIMETRIC, PdfName.SATURATION, PdfName.ABSOLUTECOLORIMETRIC
        });
    }
}
