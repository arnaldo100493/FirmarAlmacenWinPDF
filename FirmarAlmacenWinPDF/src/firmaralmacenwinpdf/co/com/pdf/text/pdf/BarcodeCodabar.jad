// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BarcodeCodabar.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.awt.*;
import java.awt.image.MemoryImageSource;

// Referenced classes of package co.com.pdf.text.pdf:
//            Barcode, BaseFont, PdfContentByte

public class BarcodeCodabar extends Barcode
{

    public BarcodeCodabar()
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
            startStopText = false;
            codeType = 12;
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public static byte[] getBarsCodabar(String text)
    {
        text = text.toUpperCase();
        int len = text.length();
        if(len < 2)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("codabar.must.have.at.least.a.start.and.stop.character", new Object[0]));
        if("0123456789-$:/.+ABCD".indexOf(text.charAt(0)) < 16 || "0123456789-$:/.+ABCD".indexOf(text.charAt(len - 1)) < 16)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("codabar.must.have.one.of.abcd.as.start.stop.character", new Object[0]));
        byte bars[] = new byte[text.length() * 8 - 1];
        for(int k = 0; k < len; k++)
        {
            int idx = "0123456789-$:/.+ABCD".indexOf(text.charAt(k));
            if(idx >= 16 && k > 0 && k < len - 1)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("in.codabar.start.stop.characters.are.only.allowed.at.the.extremes", new Object[0]));
            if(idx < 0)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.character.1.is.illegal.in.codabar", text.charAt(k)));
            System.arraycopy(BARS[idx], 0, bars, k * 8, 7);
        }

        return bars;
    }

    public static String calculateChecksum(String code)
    {
        if(code.length() < 2)
            return code;
        String text = code.toUpperCase();
        int sum = 0;
        int len = text.length();
        for(int k = 0; k < len; k++)
            sum += "0123456789-$:/.+ABCD".indexOf(text.charAt(k));

        sum = ((sum + 15) / 16) * 16 - sum;
        return (new StringBuilder()).append(code.substring(0, len - 1)).append("0123456789-$:/.+ABCD".charAt(sum)).append(code.substring(len - 1)).toString();
    }

    public Rectangle getBarcodeSize()
    {
        float fontX = 0.0F;
        float fontY = 0.0F;
        String text = code;
        if(generateChecksum && checksumText)
            text = calculateChecksum(code);
        if(!startStopText)
            text = text.substring(1, text.length() - 1);
        if(font != null)
        {
            if(baseline > 0.0F)
                fontY = baseline - font.getFontDescriptor(3, size);
            else
                fontY = -baseline + size;
            fontX = font.getWidthPoint(altText == null ? text : altText, size);
        }
        text = code;
        if(generateChecksum)
            text = calculateChecksum(code);
        byte bars[] = getBarsCodabar(text);
        int wide = 0;
        for(int k = 0; k < bars.length; k++)
            wide += bars[k];

        int narrow = bars.length - wide;
        float fullWidth = x * ((float)narrow + (float)wide * n);
        fullWidth = Math.max(fullWidth, fontX);
        float fullHeight = barHeight + fontY;
        return new Rectangle(fullWidth, fullHeight);
    }

    public Rectangle placeBarcode(PdfContentByte cb, BaseColor barColor, BaseColor textColor)
    {
        String fullCode = code;
        if(generateChecksum && checksumText)
            fullCode = calculateChecksum(code);
        if(!startStopText)
            fullCode = fullCode.substring(1, fullCode.length() - 1);
        float fontX = 0.0F;
        if(font != null)
            fontX = font.getWidthPoint(fullCode = altText == null ? fullCode : altText, size);
        byte bars[] = getBarsCodabar(generateChecksum ? calculateChecksum(code) : code);
        int wide = 0;
        for(int k = 0; k < bars.length; k++)
            wide += bars[k];

        int narrow = bars.length - wide;
        float fullWidth = x * ((float)narrow + (float)wide * n);
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
        String fullCode = code;
        if(generateChecksum && checksumText)
            fullCode = calculateChecksum(code);
        if(!startStopText)
            fullCode = fullCode.substring(1, fullCode.length() - 1);
        byte bars[] = getBarsCodabar(generateChecksum ? calculateChecksum(code) : code);
        int wide = 0;
        for(int k = 0; k < bars.length; k++)
            wide += bars[k];

        int narrow = bars.length - wide;
        int fullWidth = narrow + wide * (int)n;
        boolean print = true;
        int ptr = 0;
        int height = (int)barHeight;
        int pix[] = new int[fullWidth * height];
        for(int k = 0; k < bars.length; k++)
        {
            int w = bars[k] != 0 ? (int)n : 1;
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
            0, 0, 0, 0, 0, 1, 1
        }, {
            0, 0, 0, 0, 1, 1, 0
        }, {
            0, 0, 0, 1, 0, 0, 1
        }, {
            1, 1, 0, 0, 0, 0, 0
        }, {
            0, 0, 1, 0, 0, 1, 0
        }, {
            1, 0, 0, 0, 0, 1, 0
        }, {
            0, 1, 0, 0, 0, 0, 1
        }, {
            0, 1, 0, 0, 1, 0, 0
        }, {
            0, 1, 1, 0, 0, 0, 0
        }, {
            1, 0, 0, 1, 0, 0, 0
        }, {
            0, 0, 0, 1, 1, 0, 0
        }, {
            0, 0, 1, 1, 0, 0, 0
        }, {
            1, 0, 0, 0, 1, 0, 1
        }, {
            1, 0, 1, 0, 0, 0, 1
        }, {
            1, 0, 1, 0, 1, 0, 0
        }, {
            0, 0, 1, 0, 1, 0, 1
        }, {
            0, 0, 1, 1, 0, 1, 0
        }, {
            0, 1, 0, 1, 0, 0, 1
        }, {
            0, 0, 0, 1, 0, 1, 1
        }, {
            0, 0, 0, 1, 1, 1, 0
        }
    };
    private static final String CHARS = "0123456789-$:/.+ABCD";
    private static final int START_STOP_IDX = 16;

}
