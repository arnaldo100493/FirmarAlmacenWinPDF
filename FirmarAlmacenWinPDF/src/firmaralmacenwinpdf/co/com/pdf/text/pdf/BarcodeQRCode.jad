// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BarcodeQRCode.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.BadElementException;
import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.Image;
import co.com.pdf.text.pdf.codec.CCITTG4Encoder;
import co.com.pdf.text.pdf.qrcode.ByteMatrix;
import co.com.pdf.text.pdf.qrcode.QRCodeWriter;
import co.com.pdf.text.pdf.qrcode.WriterException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.MemoryImageSource;
import java.util.Map;

public class BarcodeQRCode
{

    public BarcodeQRCode(String content, int width, int height, Map hints)
    {
        try
        {
            QRCodeWriter qc = new QRCodeWriter();
            bm = qc.encode(content, width, height, hints);
        }
        catch(WriterException ex)
        {
            throw new ExceptionConverter(ex);
        }
    }

    private byte[] getBitMatrix()
    {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int stride = (width + 7) / 8;
        byte b[] = new byte[stride * height];
        byte mt[][] = bm.getArray();
        for(int y = 0; y < height; y++)
        {
            byte line[] = mt[y];
            for(int x = 0; x < width; x++)
                if(line[x] != 0)
                {
                    int offset = stride * y + x / 8;
                    b[offset] |= (byte)(128 >> x % 8);
                }

        }

        return b;
    }

    public Image getImage()
        throws BadElementException
    {
        byte b[] = getBitMatrix();
        byte g4[] = CCITTG4Encoder.compress(b, bm.getWidth(), bm.getHeight());
        return Image.getInstance(bm.getWidth(), bm.getHeight(), false, 256, 1, g4, null);
    }

    public java.awt.Image createAwtImage(Color foreground, Color background)
    {
        int f = foreground.getRGB();
        int g = background.getRGB();
        Canvas canvas = new Canvas();
        int width = bm.getWidth();
        int height = bm.getHeight();
        int pix[] = new int[width * height];
        byte mt[][] = bm.getArray();
        for(int y = 0; y < height; y++)
        {
            byte line[] = mt[y];
            for(int x = 0; x < width; x++)
                pix[y * width + x] = line[x] != 0 ? g : f;

        }

        java.awt.Image img = canvas.createImage(new MemoryImageSource(width, height, pix, 0, width));
        return img;
    }

    ByteMatrix bm;
}
