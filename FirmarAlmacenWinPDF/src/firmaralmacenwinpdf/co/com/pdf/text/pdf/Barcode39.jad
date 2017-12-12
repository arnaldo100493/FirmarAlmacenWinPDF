// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Barcode39.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.awt.*;
import java.awt.image.MemoryImageSource;

// Referenced classes of package co.com.pdf.text.pdf:
//            Barcode, BaseFont, PdfContentByte

public class Barcode39 extends Barcode
{

    public Barcode39()
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
            startStopText = true;
            extended = false;
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public static byte[] getBarsCode39(String text)
    {
        text = (new StringBuilder()).append("*").append(text).append("*").toString();
        byte bars[] = new byte[text.length() * 10 - 1];
        for(int k = 0; k < text.length(); k++)
        {
            int idx = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%*".indexOf(text.charAt(k));
            if(idx < 0)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.character.1.is.illegal.in.code.39", text.charAt(k)));
            System.arraycopy(BARS[idx], 0, bars, k * 10, 9);
        }

        return bars;
    }

    public static String getCode39Ex(String text)
    {
        StringBuilder out = new StringBuilder("");
        for(int k = 0; k < text.length(); k++)
        {
            char c = text.charAt(k);
            if(c > '\177')
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.character.1.is.illegal.in.code.39.extended", c));
            char c1 = "%U$A$B$C$D$E$F$G$H$I$J$K$L$M$N$O$P$Q$R$S$T$U$V$W$X$Y$Z%A%B%C%D%E  /A/B/C/D/E/F/G/H/I/J/K/L - ./O 0 1 2 3 4 5 6 7 8 9/Z%F%G%H%I%J%V A B C D E F G H I J K L M N O P Q R S T U V W X Y Z%K%L%M%N%O%W+A+B+C+D+E+F+G+H+I+J+K+L+M+N+O+P+Q+R+S+T+U+V+W+X+Y+Z%P%Q%R%S%T".charAt(c * 2);
            char c2 = "%U$A$B$C$D$E$F$G$H$I$J$K$L$M$N$O$P$Q$R$S$T$U$V$W$X$Y$Z%A%B%C%D%E  /A/B/C/D/E/F/G/H/I/J/K/L - ./O 0 1 2 3 4 5 6 7 8 9/Z%F%G%H%I%J%V A B C D E F G H I J K L M N O P Q R S T U V W X Y Z%K%L%M%N%O%W+A+B+C+D+E+F+G+H+I+J+K+L+M+N+O+P+Q+R+S+T+U+V+W+X+Y+Z%P%Q%R%S%T".charAt(c * 2 + 1);
            if(c1 != ' ')
                out.append(c1);
            out.append(c2);
        }

        return out.toString();
    }

    static char getChecksum(String text)
    {
        int chk = 0;
        for(int k = 0; k < text.length(); k++)
        {
            int idx = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%*".indexOf(text.charAt(k));
            if(idx < 0)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.character.1.is.illegal.in.code.39", text.charAt(k)));
            chk += idx;
        }

        return "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%*".charAt(chk % 43);
    }

    public Rectangle getBarcodeSize()
    {
        float fontX = 0.0F;
        float fontY = 0.0F;
        String fCode = code;
        if(extended)
            fCode = getCode39Ex(code);
        if(font != null)
        {
            if(baseline > 0.0F)
                fontY = baseline - font.getFontDescriptor(3, size);
            else
                fontY = -baseline + size;
            String fullCode = code;
            if(generateChecksum && checksumText)
                fullCode = (new StringBuilder()).append(fullCode).append(getChecksum(fCode)).toString();
            if(startStopText)
                fullCode = (new StringBuilder()).append("*").append(fullCode).append("*").toString();
            fontX = font.getWidthPoint(altText == null ? fullCode : altText, size);
        }
        int len = fCode.length() + 2;
        if(generateChecksum)
            len++;
        float fullWidth = (float)len * (6F * x + 3F * x * n) + (float)(len - 1) * x;
        fullWidth = Math.max(fullWidth, fontX);
        float fullHeight = barHeight + fontY;
        return new Rectangle(fullWidth, fullHeight);
    }

    public Rectangle placeBarcode(PdfContentByte cb, BaseColor barColor, BaseColor textColor)
    {
        String fullCode = code;
        float fontX = 0.0F;
        String bCode = code;
        if(extended)
            bCode = getCode39Ex(code);
        if(font != null)
        {
            if(generateChecksum && checksumText)
                fullCode = (new StringBuilder()).append(fullCode).append(getChecksum(bCode)).toString();
            if(startStopText)
                fullCode = (new StringBuilder()).append("*").append(fullCode).append("*").toString();
            fontX = font.getWidthPoint(fullCode = altText == null ? fullCode : altText, size);
        }
        if(generateChecksum)
            bCode = (new StringBuilder()).append(bCode).append(getChecksum(bCode)).toString();
        int len = bCode.length() + 2;
        float fullWidth = (float)len * (6F * x + 3F * x * n) + (float)(len - 1) * x;
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
        byte bars[] = getBarsCode39(bCode);
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
        String bCode = code;
        if(extended)
            bCode = getCode39Ex(code);
        if(generateChecksum)
            bCode = (new StringBuilder()).append(bCode).append(getChecksum(bCode)).toString();
        int len = bCode.length() + 2;
        int nn = (int)n;
        int fullWidth = len * (6 + 3 * nn) + (len - 1);
        byte bars[] = getBarsCode39(bCode);
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
            0, 0, 0, 1, 1, 0, 1, 0, 0
        }, {
            1, 0, 0, 1, 0, 0, 0, 0, 1
        }, {
            0, 0, 1, 1, 0, 0, 0, 0, 1
        }, {
            1, 0, 1, 1, 0, 0, 0, 0, 0
        }, {
            0, 0, 0, 1, 1, 0, 0, 0, 1
        }, {
            1, 0, 0, 1, 1, 0, 0, 0, 0
        }, {
            0, 0, 1, 1, 1, 0, 0, 0, 0
        }, {
            0, 0, 0, 1, 0, 0, 1, 0, 1
        }, {
            1, 0, 0, 1, 0, 0, 1, 0, 0
        }, {
            0, 0, 1, 1, 0, 0, 1, 0, 0
        }, {
            1, 0, 0, 0, 0, 1, 0, 0, 1
        }, {
            0, 0, 1, 0, 0, 1, 0, 0, 1
        }, {
            1, 0, 1, 0, 0, 1, 0, 0, 0
        }, {
            0, 0, 0, 0, 1, 1, 0, 0, 1
        }, {
            1, 0, 0, 0, 1, 1, 0, 0, 0
        }, {
            0, 0, 1, 0, 1, 1, 0, 0, 0
        }, {
            0, 0, 0, 0, 0, 1, 1, 0, 1
        }, {
            1, 0, 0, 0, 0, 1, 1, 0, 0
        }, {
            0, 0, 1, 0, 0, 1, 1, 0, 0
        }, {
            0, 0, 0, 0, 1, 1, 1, 0, 0
        }, {
            1, 0, 0, 0, 0, 0, 0, 1, 1
        }, {
            0, 0, 1, 0, 0, 0, 0, 1, 1
        }, {
            1, 0, 1, 0, 0, 0, 0, 1, 0
        }, {
            0, 0, 0, 0, 1, 0, 0, 1, 1
        }, {
            1, 0, 0, 0, 1, 0, 0, 1, 0
        }, {
            0, 0, 1, 0, 1, 0, 0, 1, 0
        }, {
            0, 0, 0, 0, 0, 0, 1, 1, 1
        }, {
            1, 0, 0, 0, 0, 0, 1, 1, 0
        }, {
            0, 0, 1, 0, 0, 0, 1, 1, 0
        }, {
            0, 0, 0, 0, 1, 0, 1, 1, 0
        }, {
            1, 1, 0, 0, 0, 0, 0, 0, 1
        }, {
            0, 1, 1, 0, 0, 0, 0, 0, 1
        }, {
            1, 1, 1, 0, 0, 0, 0, 0, 0
        }, {
            0, 1, 0, 0, 1, 0, 0, 0, 1
        }, {
            1, 1, 0, 0, 1, 0, 0, 0, 0
        }, {
            0, 1, 1, 0, 1, 0, 0, 0, 0
        }, {
            0, 1, 0, 0, 0, 0, 1, 0, 1
        }, {
            1, 1, 0, 0, 0, 0, 1, 0, 0
        }, {
            0, 1, 1, 0, 0, 0, 1, 0, 0
        }, {
            0, 1, 0, 1, 0, 1, 0, 0, 0
        }, {
            0, 1, 0, 1, 0, 0, 0, 1, 0
        }, {
            0, 1, 0, 0, 0, 1, 0, 1, 0
        }, {
            0, 0, 0, 1, 0, 1, 0, 1, 0
        }, {
            0, 1, 0, 0, 1, 0, 1, 0, 0
        }
    };
    private static final String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%*";
    private static final String EXTENDED = "%U$A$B$C$D$E$F$G$H$I$J$K$L$M$N$O$P$Q$R$S$T$U$V$W$X$Y$Z%A%B%C%D%E  /A/B/C/D/E/F/G/H/I/J/K/L - ./O 0 1 2 3 4 5 6 7 8 9/Z%F%G%H%I%J%V A B C D E F G H I J K L M N O P Q R S T U V W X Y Z%K%L%M%N%O%W+A+B+C+D+E+F+G+H+I+J+K+L+M+N+O+P+Q+R+S+T+U+V+W+X+Y+Z%P%Q%R%S%T";

}
