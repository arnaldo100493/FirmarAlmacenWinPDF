// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BarcodeEAN.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.awt.*;
import java.awt.image.MemoryImageSource;
import java.util.Arrays;

// Referenced classes of package co.com.pdf.text.pdf:
//            Barcode, PdfContentByte, BaseFont

public class BarcodeEAN extends Barcode
{

    public BarcodeEAN()
    {
        try
        {
            x = 0.8F;
            font = BaseFont.createFont("Helvetica", "winansi", false);
            size = 8F;
            baseline = size;
            barHeight = size * 3F;
            guardBars = true;
            codeType = 1;
            code = "";
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public static int calculateEANParity(String code)
    {
        int mul = 3;
        int total = 0;
        for(int k = code.length() - 1; k >= 0; k--)
        {
            int n = code.charAt(k) - 48;
            total += mul * n;
            mul ^= 2;
        }

        return (10 - total % 10) % 10;
    }

    public static String convertUPCAtoUPCE(String text)
    {
        if(text.length() != 12 || !text.startsWith("0") && !text.startsWith("1"))
            return null;
        if(text.substring(3, 6).equals("000") || text.substring(3, 6).equals("100") || text.substring(3, 6).equals("200"))
        {
            if(text.substring(6, 8).equals("00"))
                return (new StringBuilder()).append(text.substring(0, 1)).append(text.substring(1, 3)).append(text.substring(8, 11)).append(text.substring(3, 4)).append(text.substring(11)).toString();
        } else
        if(text.substring(4, 6).equals("00"))
        {
            if(text.substring(6, 9).equals("000"))
                return (new StringBuilder()).append(text.substring(0, 1)).append(text.substring(1, 4)).append(text.substring(9, 11)).append("3").append(text.substring(11)).toString();
        } else
        if(text.substring(5, 6).equals("0"))
        {
            if(text.substring(6, 10).equals("0000"))
                return (new StringBuilder()).append(text.substring(0, 1)).append(text.substring(1, 5)).append(text.substring(10, 11)).append("4").append(text.substring(11)).toString();
        } else
        if(text.charAt(10) >= '5' && text.substring(6, 10).equals("0000"))
            return (new StringBuilder()).append(text.substring(0, 1)).append(text.substring(1, 6)).append(text.substring(10, 11)).append(text.substring(11)).toString();
        return null;
    }

    public static byte[] getBarsEAN13(String _code)
    {
        int code[] = new int[_code.length()];
        for(int k = 0; k < code.length; k++)
            code[k] = _code.charAt(k) - 48;

        byte bars[] = new byte[59];
        int pb = 0;
        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 1;
        byte sequence[] = PARITY13[code[0]];
        for(int k = 0; k < sequence.length; k++)
        {
            int c = code[k + 1];
            byte stripes[] = BARS[c];
            if(sequence[k] == 0)
            {
                bars[pb++] = stripes[0];
                bars[pb++] = stripes[1];
                bars[pb++] = stripes[2];
                bars[pb++] = stripes[3];
            } else
            {
                bars[pb++] = stripes[3];
                bars[pb++] = stripes[2];
                bars[pb++] = stripes[1];
                bars[pb++] = stripes[0];
            }
        }

        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 1;
        for(int k = 7; k < 13; k++)
        {
            int c = code[k];
            byte stripes[] = BARS[c];
            bars[pb++] = stripes[0];
            bars[pb++] = stripes[1];
            bars[pb++] = stripes[2];
            bars[pb++] = stripes[3];
        }

        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 1;
        return bars;
    }

    public static byte[] getBarsEAN8(String _code)
    {
        int code[] = new int[_code.length()];
        for(int k = 0; k < code.length; k++)
            code[k] = _code.charAt(k) - 48;

        byte bars[] = new byte[43];
        int pb = 0;
        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 1;
        for(int k = 0; k < 4; k++)
        {
            int c = code[k];
            byte stripes[] = BARS[c];
            bars[pb++] = stripes[0];
            bars[pb++] = stripes[1];
            bars[pb++] = stripes[2];
            bars[pb++] = stripes[3];
        }

        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 1;
        for(int k = 4; k < 8; k++)
        {
            int c = code[k];
            byte stripes[] = BARS[c];
            bars[pb++] = stripes[0];
            bars[pb++] = stripes[1];
            bars[pb++] = stripes[2];
            bars[pb++] = stripes[3];
        }

        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 1;
        return bars;
    }

    public static byte[] getBarsUPCE(String _code)
    {
        int code[] = new int[_code.length()];
        for(int k = 0; k < code.length; k++)
            code[k] = _code.charAt(k) - 48;

        byte bars[] = new byte[33];
        boolean flip = code[0] != 0;
        int pb = 0;
        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 1;
        byte sequence[] = PARITYE[code[code.length - 1]];
        for(int k = 1; k < code.length - 1; k++)
        {
            int c = code[k];
            byte stripes[] = BARS[c];
            if(sequence[k - 1] == (((byte)(flip ? 1 : 0))))
            {
                bars[pb++] = stripes[0];
                bars[pb++] = stripes[1];
                bars[pb++] = stripes[2];
                bars[pb++] = stripes[3];
            } else
            {
                bars[pb++] = stripes[3];
                bars[pb++] = stripes[2];
                bars[pb++] = stripes[1];
                bars[pb++] = stripes[0];
            }
        }

        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 1;
        return bars;
    }

    public static byte[] getBarsSupplemental2(String _code)
    {
        int code[] = new int[2];
        for(int k = 0; k < code.length; k++)
            code[k] = _code.charAt(k) - 48;

        byte bars[] = new byte[13];
        int pb = 0;
        int parity = (code[0] * 10 + code[1]) % 4;
        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 2;
        byte sequence[] = PARITY2[parity];
        for(int k = 0; k < sequence.length; k++)
        {
            if(k == 1)
            {
                bars[pb++] = 1;
                bars[pb++] = 1;
            }
            int c = code[k];
            byte stripes[] = BARS[c];
            if(sequence[k] == 0)
            {
                bars[pb++] = stripes[0];
                bars[pb++] = stripes[1];
                bars[pb++] = stripes[2];
                bars[pb++] = stripes[3];
            } else
            {
                bars[pb++] = stripes[3];
                bars[pb++] = stripes[2];
                bars[pb++] = stripes[1];
                bars[pb++] = stripes[0];
            }
        }

        return bars;
    }

    public static byte[] getBarsSupplemental5(String _code)
    {
        int code[] = new int[5];
        for(int k = 0; k < code.length; k++)
            code[k] = _code.charAt(k) - 48;

        byte bars[] = new byte[31];
        int pb = 0;
        int parity = ((code[0] + code[2] + code[4]) * 3 + (code[1] + code[3]) * 9) % 10;
        bars[pb++] = 1;
        bars[pb++] = 1;
        bars[pb++] = 2;
        byte sequence[] = PARITY5[parity];
        for(int k = 0; k < sequence.length; k++)
        {
            if(k != 0)
            {
                bars[pb++] = 1;
                bars[pb++] = 1;
            }
            int c = code[k];
            byte stripes[] = BARS[c];
            if(sequence[k] == 0)
            {
                bars[pb++] = stripes[0];
                bars[pb++] = stripes[1];
                bars[pb++] = stripes[2];
                bars[pb++] = stripes[3];
            } else
            {
                bars[pb++] = stripes[3];
                bars[pb++] = stripes[2];
                bars[pb++] = stripes[1];
                bars[pb++] = stripes[0];
            }
        }

        return bars;
    }

    public Rectangle getBarcodeSize()
    {
        float width = 0.0F;
        float height = barHeight;
        if(font != null)
            if(baseline <= 0.0F)
                height += -baseline + size;
            else
                height += baseline - font.getFontDescriptor(3, size);
        switch(codeType)
        {
        case 1: // '\001'
            width = x * 95F;
            if(font != null)
                width += font.getWidthPoint(code.charAt(0), size);
            break;

        case 2: // '\002'
            width = x * 67F;
            break;

        case 3: // '\003'
            width = x * 95F;
            if(font != null)
                width += font.getWidthPoint(code.charAt(0), size) + font.getWidthPoint(code.charAt(11), size);
            break;

        case 4: // '\004'
            width = x * 51F;
            if(font != null)
                width += font.getWidthPoint(code.charAt(0), size) + font.getWidthPoint(code.charAt(7), size);
            break;

        case 5: // '\005'
            width = x * 20F;
            break;

        case 6: // '\006'
            width = x * 47F;
            break;

        default:
            throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.code.type", new Object[0]));
        }
        return new Rectangle(width, height);
    }

    public Rectangle placeBarcode(PdfContentByte cb, BaseColor barColor, BaseColor textColor)
    {
        Rectangle rect = getBarcodeSize();
        float barStartX = 0.0F;
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
        switch(codeType)
        {
        case 2: // '\002'
        default:
            break;

        case 1: // '\001'
        case 3: // '\003'
        case 4: // '\004'
            if(font != null)
                barStartX += font.getWidthPoint(code.charAt(0), size);
            break;
        }
        byte bars[] = null;
        int guard[] = GUARD_EMPTY;
        switch(codeType)
        {
        case 1: // '\001'
            bars = getBarsEAN13(code);
            guard = GUARD_EAN13;
            break;

        case 2: // '\002'
            bars = getBarsEAN8(code);
            guard = GUARD_EAN8;
            break;

        case 3: // '\003'
            bars = getBarsEAN13((new StringBuilder()).append("0").append(code).toString());
            guard = GUARD_UPCA;
            break;

        case 4: // '\004'
            bars = getBarsUPCE(code);
            guard = GUARD_UPCE;
            break;

        case 5: // '\005'
            bars = getBarsSupplemental2(code);
            break;

        case 6: // '\006'
            bars = getBarsSupplemental5(code);
            break;
        }
        float keepBarX = barStartX;
        boolean print = true;
        float gd = 0.0F;
        if(font != null && baseline > 0.0F && guardBars)
            gd = baseline / 2.0F;
        if(barColor != null)
            cb.setColorFill(barColor);
        for(int k = 0; k < bars.length; k++)
        {
            float w = (float)bars[k] * x;
            if(print)
                if(Arrays.binarySearch(guard, k) >= 0)
                    cb.rectangle(barStartX, barStartY - gd, w - inkSpreading, barHeight + gd);
                else
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
            switch(codeType)
            {
            default:
                break;

            case 1: // '\001'
                cb.setTextMatrix(0.0F, textStartY);
                cb.showText(code.substring(0, 1));
                for(int k = 1; k < 13; k++)
                {
                    String c = code.substring(k, k + 1);
                    float len = font.getWidthPoint(c, size);
                    float pX = (keepBarX + TEXTPOS_EAN13[k - 1] * x) - len / 2.0F;
                    cb.setTextMatrix(pX, textStartY);
                    cb.showText(c);
                }

                break;

            case 2: // '\002'
                for(int k = 0; k < 8; k++)
                {
                    String c = code.substring(k, k + 1);
                    float len = font.getWidthPoint(c, size);
                    float pX = TEXTPOS_EAN8[k] * x - len / 2.0F;
                    cb.setTextMatrix(pX, textStartY);
                    cb.showText(c);
                }

                break;

            case 3: // '\003'
                cb.setTextMatrix(0.0F, textStartY);
                cb.showText(code.substring(0, 1));
                for(int k = 1; k < 11; k++)
                {
                    String c = code.substring(k, k + 1);
                    float len = font.getWidthPoint(c, size);
                    float pX = (keepBarX + TEXTPOS_EAN13[k] * x) - len / 2.0F;
                    cb.setTextMatrix(pX, textStartY);
                    cb.showText(c);
                }

                cb.setTextMatrix(keepBarX + x * 95F, textStartY);
                cb.showText(code.substring(11, 12));
                break;

            case 4: // '\004'
                cb.setTextMatrix(0.0F, textStartY);
                cb.showText(code.substring(0, 1));
                for(int k = 1; k < 7; k++)
                {
                    String c = code.substring(k, k + 1);
                    float len = font.getWidthPoint(c, size);
                    float pX = (keepBarX + TEXTPOS_EAN13[k - 1] * x) - len / 2.0F;
                    cb.setTextMatrix(pX, textStartY);
                    cb.showText(c);
                }

                cb.setTextMatrix(keepBarX + x * 51F, textStartY);
                cb.showText(code.substring(7, 8));
                break;

            case 5: // '\005'
            case 6: // '\006'
                for(int k = 0; k < code.length(); k++)
                {
                    String c = code.substring(k, k + 1);
                    float len = font.getWidthPoint(c, size);
                    float pX = (7.5F + (float)(9 * k)) * x - len / 2.0F;
                    cb.setTextMatrix(pX, textStartY);
                    cb.showText(c);
                }

                break;
            }
            cb.endText();
        }
        return rect;
    }

    public Image createAwtImage(Color foreground, Color background)
    {
        int f = foreground.getRGB();
        int g = background.getRGB();
        Canvas canvas = new Canvas();
        int width = 0;
        byte bars[] = null;
        switch(codeType)
        {
        case 1: // '\001'
            bars = getBarsEAN13(code);
            width = 95;
            break;

        case 2: // '\002'
            bars = getBarsEAN8(code);
            width = 67;
            break;

        case 3: // '\003'
            bars = getBarsEAN13((new StringBuilder()).append("0").append(code).toString());
            width = 95;
            break;

        case 4: // '\004'
            bars = getBarsUPCE(code);
            width = 51;
            break;

        case 5: // '\005'
            bars = getBarsSupplemental2(code);
            width = 20;
            break;

        case 6: // '\006'
            bars = getBarsSupplemental5(code);
            width = 47;
            break;

        default:
            throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.code.type", new Object[0]));
        }
        boolean print = true;
        int ptr = 0;
        int height = (int)barHeight;
        int pix[] = new int[width * height];
        for(int k = 0; k < bars.length; k++)
        {
            int w = bars[k];
            int c = g;
            if(print)
                c = f;
            print = !print;
            for(int j = 0; j < w; j++)
                pix[ptr++] = c;

        }

        for(int k = width; k < pix.length; k += width)
            System.arraycopy(pix, 0, pix, k, width);

        Image img = canvas.createImage(new MemoryImageSource(width, height, pix, 0, width));
        return img;
    }

    private static final int GUARD_EMPTY[] = new int[0];
    private static final int GUARD_UPCA[] = {
        0, 2, 4, 6, 28, 30, 52, 54, 56, 58
    };
    private static final int GUARD_EAN13[] = {
        0, 2, 28, 30, 56, 58
    };
    private static final int GUARD_EAN8[] = {
        0, 2, 20, 22, 40, 42
    };
    private static final int GUARD_UPCE[] = {
        0, 2, 28, 30, 32
    };
    private static final float TEXTPOS_EAN13[] = {
        6.5F, 13.5F, 20.5F, 27.5F, 34.5F, 41.5F, 53.5F, 60.5F, 67.5F, 74.5F, 
        81.5F, 88.5F
    };
    private static final float TEXTPOS_EAN8[] = {
        6.5F, 13.5F, 20.5F, 27.5F, 39.5F, 46.5F, 53.5F, 60.5F
    };
    private static final byte BARS[][] = {
        {
            3, 2, 1, 1
        }, {
            2, 2, 2, 1
        }, {
            2, 1, 2, 2
        }, {
            1, 4, 1, 1
        }, {
            1, 1, 3, 2
        }, {
            1, 2, 3, 1
        }, {
            1, 1, 1, 4
        }, {
            1, 3, 1, 2
        }, {
            1, 2, 1, 3
        }, {
            3, 1, 1, 2
        }
    };
    private static final int TOTALBARS_EAN13 = 59;
    private static final int TOTALBARS_EAN8 = 43;
    private static final int TOTALBARS_UPCE = 33;
    private static final int TOTALBARS_SUPP2 = 13;
    private static final int TOTALBARS_SUPP5 = 31;
    private static final int ODD = 0;
    private static final int EVEN = 1;
    private static final byte PARITY13[][] = {
        {
            0, 0, 0, 0, 0, 0
        }, {
            0, 0, 1, 0, 1, 1
        }, {
            0, 0, 1, 1, 0, 1
        }, {
            0, 0, 1, 1, 1, 0
        }, {
            0, 1, 0, 0, 1, 1
        }, {
            0, 1, 1, 0, 0, 1
        }, {
            0, 1, 1, 1, 0, 0
        }, {
            0, 1, 0, 1, 0, 1
        }, {
            0, 1, 0, 1, 1, 0
        }, {
            0, 1, 1, 0, 1, 0
        }
    };
    private static final byte PARITY2[][] = {
        {
            0, 0
        }, {
            0, 1
        }, {
            1, 0
        }, {
            1, 1
        }
    };
    private static final byte PARITY5[][] = {
        {
            1, 1, 0, 0, 0
        }, {
            1, 0, 1, 0, 0
        }, {
            1, 0, 0, 1, 0
        }, {
            1, 0, 0, 0, 1
        }, {
            0, 1, 1, 0, 0
        }, {
            0, 0, 1, 1, 0
        }, {
            0, 0, 0, 1, 1
        }, {
            0, 1, 0, 1, 0
        }, {
            0, 1, 0, 0, 1
        }, {
            0, 0, 1, 0, 1
        }
    };
    private static final byte PARITYE[][] = {
        {
            1, 1, 1, 0, 0, 0
        }, {
            1, 1, 0, 1, 0, 0
        }, {
            1, 1, 0, 0, 1, 0
        }, {
            1, 1, 0, 0, 0, 1
        }, {
            1, 0, 1, 1, 0, 0
        }, {
            1, 0, 0, 1, 1, 0
        }, {
            1, 0, 0, 0, 1, 1
        }, {
            1, 0, 1, 0, 1, 0
        }, {
            1, 0, 1, 0, 0, 1
        }, {
            1, 0, 0, 1, 0, 1
        }
    };

}
