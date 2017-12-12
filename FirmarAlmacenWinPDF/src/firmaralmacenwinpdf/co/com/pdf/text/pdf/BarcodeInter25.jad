// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BarcodeInter25.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.awt.*;
import java.awt.image.MemoryImageSource;

// Referenced classes of package co.com.pdf.text.pdf:
//            Barcode, BaseFont, PdfContentByte

public class BarcodeInter25 extends Barcode
{

    public BarcodeInter25()
    {
        try
        {
            x = 0.8F;
            n = 2.0F;
            font = BaseFont.createFont("Helvetica", "winansi", false);
            size = 8F;
            baseline = size;
            barHeight = size * 3F;
            textAlignment = 1;
            generateChecksum = false;
            checksumText = false;
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public static String keepNumbers(String text)
    {
        StringBuffer sb = new StringBuffer();
        for(int k = 0; k < text.length(); k++)
        {
            char c = text.charAt(k);
            if(c >= '0' && c <= '9')
                sb.append(c);
        }

        return sb.toString();
    }

    public static char getChecksum(String text)
    {
        int mul = 3;
        int total = 0;
        for(int k = text.length() - 1; k >= 0; k--)
        {
            int n = text.charAt(k) - 48;
            total += mul * n;
            mul ^= 2;
        }

        return (char)((10 - total % 10) % 10 + 48);
    }

    public static byte[] getBarsInter25(String text)
    {
        text = keepNumbers(text);
        if((text.length() & 1) != 0)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.text.length.must.be.even", new Object[0]));
        byte bars[] = new byte[text.length() * 5 + 7];
        int pb = 0;
        bars[pb++] = 0;
        bars[pb++] = 0;
        bars[pb++] = 0;
        bars[pb++] = 0;
        int len = text.length() / 2;
        for(int k = 0; k < len; k++)
        {
            int c1 = text.charAt(k * 2) - 48;
            int c2 = text.charAt(k * 2 + 1) - 48;
            byte b1[] = BARS[c1];
            byte b2[] = BARS[c2];
            for(int j = 0; j < 5; j++)
            {
                bars[pb++] = b1[j];
                bars[pb++] = b2[j];
            }

        }

        bars[pb++] = 1;
        bars[pb++] = 0;
        bars[pb++] = 0;
        return bars;
    }

    public Rectangle getBarcodeSize()
    {
        float fontX = 0.0F;
        float fontY = 0.0F;
        String fullCode;
        if(font != null)
        {
            if(baseline > 0.0F)
                fontY = baseline - font.getFontDescriptor(3, size);
            else
                fontY = -baseline + size;
            fullCode = code;
            if(generateChecksum && checksumText)
                fullCode = (new StringBuilder()).append(fullCode).append(getChecksum(fullCode)).toString();
            fontX = font.getWidthPoint(altText == null ? fullCode : altText, size);
        }
        fullCode = keepNumbers(code);
        int len = fullCode.length();
        if(generateChecksum)
            len++;
        float fullWidth = (float)len * (3F * x + 2.0F * x * n) + (6F + n) * x;
        fullWidth = Math.max(fullWidth, fontX);
        float fullHeight = barHeight + fontY;
        return new Rectangle(fullWidth, fullHeight);
    }

    public Rectangle placeBarcode(PdfContentByte cb, BaseColor barColor, BaseColor textColor)
    {
        String fullCode = code;
        float fontX = 0.0F;
        if(font != null)
        {
            if(generateChecksum && checksumText)
                fullCode = (new StringBuilder()).append(fullCode).append(getChecksum(fullCode)).toString();
            fontX = font.getWidthPoint(fullCode = altText == null ? fullCode : altText, size);
        }
        String bCode = keepNumbers(code);
        if(generateChecksum)
            bCode = (new StringBuilder()).append(bCode).append(getChecksum(bCode)).toString();
        int len = bCode.length();
        float fullWidth = (float)len * (3F * x + 2.0F * x * n) + (6F + n) * x;
        float barStartX = 0.0F;
        float textStartX = 0.0F;
        switch(textAlignment)
        {
        case 0: // '\0'
            break;

        case 2: // '\002'
            if(fontX > fullWidth)
                barStartX = fontX - fullWidth;
            else
                textStartX = fullWidth - fontX;
            break;

        default:
            if(fontX > fullWidth)
                barStartX = (fontX - fullWidth) / 2.0F;
            else
                textStartX = (fullWidth - fontX) / 2.0F;
            break;
        }
        float barStartY = 0.0F;
        float textStartY = 0.0F;
        if(font != null)
            if(baseline <= 0.0F)
            {
                textStartY = barHeight - baseline;
            } else
            {
                textStartY = -font.getFontDescriptor(3, size);
                barStartY = textStartY + baseline;
            }
        byte bars[] = getBarsInter25(bCode);
        boolean print = true;
        if(barColor != null)
            cb.setColorFill(barColor);
        for(int k = 0; k < bars.length; k++)
        {
            float w = bars[k] != 0 ? x * n : x;
            if(print)
                cb.rectangle(barStartX, barStartY, w - inkSpreading, barHeight);
            print = !print;
            barStartX += w;
        }

        cb.fill();
        if(font != null)
        {
            if(textColor != null)
                cb.setColorFill(textColor);
            cb.beginText();
            cb.setFontAndSize(font, size);
            cb.setTextMatrix(textStartX, textStartY);
            cb.showText(fullCode);
            cb.endText();
        }
        return getBarcodeSize();
    }

    public Image createAwtImage(Color foreground, Color background)
    {
        int f = foreground.getRGB();
        int g = background.getRGB();
        Canvas canvas = new Canvas();
        String bCode = keepNumbers(code);
        if(generateChecksum)
            bCode = (new StringBuilder()).append(bCode).append(getChecksum(bCode)).toString();
        int len = bCode.length();
        int nn = (int)n;
        int fullWidth = len * (3 + 2 * nn) + (6 + nn);
        byte bars[] = getBarsInter25(bCode);
        boolean print = true;
        int ptr = 0;
        int height = (int)barHeight;
        int pix[] = new int[fullWidth * height];
        for(int k = 0; k < bars.length; k++)
        {
            int w = bars[k] != 0 ? nn : 1;
            int c = g;
            if(print)
                c = f;
            print = !print;
            for(int j = 0; j < w; j++)
                pix[ptr++] = c;

        }

        for(int k = fullWidth; k < pix.length; k += fullWidth)
            System.arraycopy(pix, 0, pix, k, fullWidth);

        Image img = canvas.createImage(new MemoryImageSource(fullWidth, height, pix, 0, fullWidth));
        return img;
    }

    private static final byte BARS[][] = {
        {
            0, 0, 1, 1, 0
        }, {
            1, 0, 0, 0, 1
        }, {
            0, 1, 0, 0, 1
        }, {
            1, 1, 0, 0, 0
        }, {
            0, 0, 1, 0, 1
        }, {
            1, 0, 1, 0, 0
        }, {
            0, 1, 1, 0, 0
        }, {
            0, 0, 0, 1, 1
        }, {
            1, 0, 0, 1, 0
        }, {
            0, 1, 0, 1, 0
        }
    };

}
