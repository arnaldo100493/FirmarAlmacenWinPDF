// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Barcode128.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.awt.*;
import java.awt.image.MemoryImageSource;

// Referenced classes of package co.com.pdf.text.pdf:
//            Barcode, IntHashtable, BaseFont, PdfContentByte

public class Barcode128 extends Barcode
{

    public Barcode128()
    {
        try
        {
            x = 0.8F;
            font = BaseFont.createFont("Helvetica", "winansi", false);
            size = 8F;
            baseline = size;
            barHeight = size * 3F;
            textAlignment = 1;
            codeType = 9;
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public static String removeFNC1(String code)
    {
        int len = code.length();
        StringBuffer buf = new StringBuffer(len);
        for(int k = 0; k < len; k++)
        {
            char c = code.charAt(k);
            if(c >= ' ' && c <= '~')
                buf.append(c);
        }

        return buf.toString();
    }

    public static String getHumanReadableUCCEAN(String code)
    {
        StringBuffer buf = new StringBuffer();
        String fnc1 = String.valueOf('\312');
        do
        {
            try
            {
                while(code.startsWith(fnc1)) 
                    code = code.substring(1);
                int n = 0;
                int idlen = 0;
                int k = 2;
                do
                {
                    if(k >= 5 || code.length() < k)
                        break;
                    if((n = ais.get(Integer.parseInt(code.substring(0, k)))) != 0)
                    {
                        idlen = k;
                        break;
                    }
                    k++;
                } while(true);
                if(idlen == 0)
                    break;
                buf.append('(').append(code.substring(0, idlen)).append(')');
                code = code.substring(idlen);
                if(n > 0)
                {
                    n -= idlen;
                    if(code.length() <= n)
                        break;
                    buf.append(removeFNC1(code.substring(0, n)));
                    code = code.substring(n);
                    continue;
                }
                int idx = code.indexOf('\312');
                if(idx < 0)
                    break;
                buf.append(code.substring(0, idx));
                code = code.substring(idx + 1);
                continue;
            }
            catch(Exception e) { }
            break;
        } while(true);
        buf.append(removeFNC1(code));
        return buf.toString();
    }

    static boolean isNextDigits(String text, int textIndex, int numDigits)
    {
        for(int len = text.length(); textIndex < len && numDigits > 0;)
            if(text.charAt(textIndex) == '\312')
            {
                textIndex++;
            } else
            {
                int n = Math.min(2, numDigits);
                if(textIndex + n > len)
                    return false;
                while(n-- > 0) 
                {
                    char c = text.charAt(textIndex++);
                    if(c < '0' || c > '9')
                        return false;
                    numDigits--;
                }
            }

        return numDigits == 0;
    }

    static String getPackedRawDigits(String text, int textIndex, int numDigits)
    {
        StringBuilder out = new StringBuilder("");
        int start = textIndex;
        while(numDigits > 0) 
            if(text.charAt(textIndex) == '\312')
            {
                out.append('f');
                textIndex++;
            } else
            {
                numDigits -= 2;
                int c1 = text.charAt(textIndex++) - 48;
                int c2 = text.charAt(textIndex++) - 48;
                out.append((char)(c1 * 10 + c2));
            }
        return (new StringBuilder()).append((char)(textIndex - start)).append(out.toString()).toString();
    }

    public static String getRawText(String text, boolean ucc)
    {
        String out = "";
        int tLen = text.length();
        if(tLen == 0)
        {
            out = (new StringBuilder()).append(out).append('h').toString();
            if(ucc)
                out = (new StringBuilder()).append(out).append('f').toString();
            return out;
        }
        int c = 0;
        for(int k = 0; k < tLen; k++)
        {
            c = text.charAt(k);
            if(c > 127 && c != 202)
                throw new RuntimeException(MessageLocalization.getComposedMessage("there.are.illegal.characters.for.barcode.128.in.1", new Object[] {
                    text
                }));
        }

        c = text.charAt(0);
        char currentCode = 'h';
        int index = 0;
        if(isNextDigits(text, index, 2))
        {
            currentCode = 'i';
            out = (new StringBuilder()).append(out).append(currentCode).toString();
            if(ucc)
                out = (new StringBuilder()).append(out).append('f').toString();
            String out2 = getPackedRawDigits(text, index, 2);
            index += out2.charAt(0);
            out = (new StringBuilder()).append(out).append(out2.substring(1)).toString();
        } else
        if(c < 32)
        {
            currentCode = 'g';
            out = (new StringBuilder()).append(out).append(currentCode).toString();
            if(ucc)
                out = (new StringBuilder()).append(out).append('f').toString();
            out = (new StringBuilder()).append(out).append((char)(c + 64)).toString();
            index++;
        } else
        {
            out = (new StringBuilder()).append(out).append(currentCode).toString();
            if(ucc)
                out = (new StringBuilder()).append(out).append('f').toString();
            if(c == 202)
                out = (new StringBuilder()).append(out).append('f').toString();
            else
                out = (new StringBuilder()).append(out).append((char)(c - 32)).toString();
            index++;
        }
        do
            if(index < tLen)
                switch(currentCode)
                {
                case 103: // 'g'
                    if(isNextDigits(text, index, 4))
                    {
                        currentCode = 'i';
                        out = (new StringBuilder()).append(out).append('c').toString();
                        String out2 = getPackedRawDigits(text, index, 4);
                        index += out2.charAt(0);
                        out = (new StringBuilder()).append(out).append(out2.substring(1)).toString();
                    } else
                    {
                        c = text.charAt(index++);
                        if(c == 202)
                            out = (new StringBuilder()).append(out).append('f').toString();
                        else
                        if(c > 95)
                        {
                            currentCode = 'h';
                            out = (new StringBuilder()).append(out).append('d').toString();
                            out = (new StringBuilder()).append(out).append((char)(c - 32)).toString();
                        } else
                        if(c < 32)
                            out = (new StringBuilder()).append(out).append((char)(c + 64)).toString();
                        else
                            out = (new StringBuilder()).append(out).append((char)(c - 32)).toString();
                    }
                    break;

                case 104: // 'h'
                    if(isNextDigits(text, index, 4))
                    {
                        currentCode = 'i';
                        out = (new StringBuilder()).append(out).append('c').toString();
                        String out2 = getPackedRawDigits(text, index, 4);
                        index += out2.charAt(0);
                        out = (new StringBuilder()).append(out).append(out2.substring(1)).toString();
                    } else
                    {
                        c = text.charAt(index++);
                        if(c == 202)
                            out = (new StringBuilder()).append(out).append('f').toString();
                        else
                        if(c < 32)
                        {
                            currentCode = 'g';
                            out = (new StringBuilder()).append(out).append('e').toString();
                            out = (new StringBuilder()).append(out).append((char)(c + 64)).toString();
                        } else
                        {
                            out = (new StringBuilder()).append(out).append((char)(c - 32)).toString();
                        }
                    }
                    break;

                case 105: // 'i'
                    if(isNextDigits(text, index, 2))
                    {
                        String out2 = getPackedRawDigits(text, index, 2);
                        index += out2.charAt(0);
                        out = (new StringBuilder()).append(out).append(out2.substring(1)).toString();
                    } else
                    {
                        c = text.charAt(index++);
                        if(c == 202)
                            out = (new StringBuilder()).append(out).append('f').toString();
                        else
                        if(c < 32)
                        {
                            currentCode = 'g';
                            out = (new StringBuilder()).append(out).append('e').toString();
                            out = (new StringBuilder()).append(out).append((char)(c + 64)).toString();
                        } else
                        {
                            currentCode = 'h';
                            out = (new StringBuilder()).append(out).append('d').toString();
                            out = (new StringBuilder()).append(out).append((char)(c - 32)).toString();
                        }
                    }
                    break;
                }
            else
                return out;
        while(true);
    }

    public static byte[] getBarsCode128Raw(String text)
    {
        int idx = text.indexOf('\uFFFF');
        if(idx >= 0)
            text = text.substring(0, idx);
        int chk = text.charAt(0);
        for(int k = 1; k < text.length(); k++)
            chk += k * text.charAt(k);

        chk %= 103;
        text = (new StringBuilder()).append(text).append((char)chk).toString();
        byte bars[] = new byte[(text.length() + 1) * 6 + 7];
        int k;
        for(k = 0; k < text.length(); k++)
            System.arraycopy(BARS[text.charAt(k)], 0, bars, k * 6, 6);

        System.arraycopy(BARS_STOP, 0, bars, k * 6, 7);
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
            if(codeType == 11)
            {
                int idx = code.indexOf('\uFFFF');
                if(idx < 0)
                    fullCode = "";
                else
                    fullCode = code.substring(idx + 1);
            } else
            if(codeType == 10)
                fullCode = getHumanReadableUCCEAN(code);
            else
                fullCode = removeFNC1(code);
            fontX = font.getWidthPoint(altText == null ? fullCode : altText, size);
        }
        if(codeType == 11)
        {
            int idx = code.indexOf('\uFFFF');
            if(idx >= 0)
                fullCode = code.substring(0, idx);
            else
                fullCode = code;
        } else
        {
            fullCode = getRawText(code, codeType == 10);
        }
        int len = fullCode.length();
        float fullWidth = (float)((len + 2) * 11) * x + 2.0F * x;
        fullWidth = Math.max(fullWidth, fontX);
        float fullHeight = barHeight + fontY;
        return new Rectangle(fullWidth, fullHeight);
    }

    public Rectangle placeBarcode(PdfContentByte cb, BaseColor barColor, BaseColor textColor)
    {
        String fullCode;
        if(codeType == 11)
        {
            int idx = code.indexOf('\uFFFF');
            if(idx < 0)
                fullCode = "";
            else
                fullCode = code.substring(idx + 1);
        } else
        if(codeType == 10)
            fullCode = getHumanReadableUCCEAN(code);
        else
            fullCode = removeFNC1(code);
        float fontX = 0.0F;
        if(font != null)
            fontX = font.getWidthPoint(fullCode = altText == null ? fullCode : altText, size);
        String bCode;
        if(codeType == 11)
        {
            int idx = code.indexOf('\uFFFF');
            if(idx >= 0)
                bCode = code.substring(0, idx);
            else
                bCode = code;
        } else
        {
            bCode = getRawText(code, codeType == 10);
        }
        int len = bCode.length();
        float fullWidth = (float)((len + 2) * 11) * x + 2.0F * x;
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
        byte bars[] = getBarsCode128Raw(bCode);
        boolean print = true;
        if(barColor != null)
            cb.setColorFill(barColor);
        for(int k = 0; k < bars.length; k++)
        {
            float w = (float)bars[k] * x;
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

    public void setCode(String code)
    {
label0:
        {
label1:
            {
                StringBuilder ret;
label2:
                {
                    if(getCodeType() != 10 || !code.startsWith("("))
                        break label1;
                    int idx = 0;
                    ret = new StringBuilder("");
                    int end;
                    String sai;
                    int len;
                    int next;
label3:
                    do
                    {
                        while(idx >= 0) 
                        {
                            end = code.indexOf(')', idx);
                            if(end < 0)
                                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("badly.formed.ucc.string.1", new Object[] {
                                    code
                                }));
                            sai = code.substring(idx + 1, end);
                            if(sai.length() < 2)
                                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("ai.too.short.1", new Object[] {
                                    sai
                                }));
                            int ai = Integer.parseInt(sai);
                            len = ais.get(ai);
                            if(len == 0)
                                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("ai.not.found.1", new Object[] {
                                    sai
                                }));
                            sai = String.valueOf(ai);
                            if(sai.length() == 1)
                                sai = (new StringBuilder()).append("0").append(sai).toString();
                            idx = code.indexOf('(', end);
                            next = idx >= 0 ? idx : code.length();
                            ret.append(sai).append(code.substring(end + 1, next));
                            if(len >= 0)
                                continue label3;
                            if(idx >= 0)
                                ret.append('\312');
                        }
                        break label2;
                    } while((next - end - 1) + sai.length() == len);
                    throw new IllegalArgumentException(MessageLocalization.getComposedMessage("invalid.ai.length.1", new Object[] {
                        sai
                    }));
                }
                super.setCode(ret.toString());
                break label0;
            }
            super.setCode(code);
        }
    }

    public Image createAwtImage(Color foreground, Color background)
    {
        int f = foreground.getRGB();
        int g = background.getRGB();
        Canvas canvas = new Canvas();
        String bCode;
        if(codeType == 11)
        {
            int idx = code.indexOf('\uFFFF');
            if(idx >= 0)
                bCode = code.substring(0, idx);
            else
                bCode = code;
        } else
        {
            bCode = getRawText(code, codeType == 10);
        }
        int len = bCode.length();
        int fullWidth = (len + 2) * 11 + 2;
        byte bars[] = getBarsCode128Raw(bCode);
        boolean print = true;
        int ptr = 0;
        int height = (int)barHeight;
        int pix[] = new int[fullWidth * height];
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

        for(int k = fullWidth; k < pix.length; k += fullWidth)
            System.arraycopy(pix, 0, pix, k, fullWidth);

        Image img = canvas.createImage(new MemoryImageSource(fullWidth, height, pix, 0, fullWidth));
        return img;
    }

    private static final byte BARS[][] = {
        {
            2, 1, 2, 2, 2, 2
        }, {
            2, 2, 2, 1, 2, 2
        }, {
            2, 2, 2, 2, 2, 1
        }, {
            1, 2, 1, 2, 2, 3
        }, {
            1, 2, 1, 3, 2, 2
        }, {
            1, 3, 1, 2, 2, 2
        }, {
            1, 2, 2, 2, 1, 3
        }, {
            1, 2, 2, 3, 1, 2
        }, {
            1, 3, 2, 2, 1, 2
        }, {
            2, 2, 1, 2, 1, 3
        }, {
            2, 2, 1, 3, 1, 2
        }, {
            2, 3, 1, 2, 1, 2
        }, {
            1, 1, 2, 2, 3, 2
        }, {
            1, 2, 2, 1, 3, 2
        }, {
            1, 2, 2, 2, 3, 1
        }, {
            1, 1, 3, 2, 2, 2
        }, {
            1, 2, 3, 1, 2, 2
        }, {
            1, 2, 3, 2, 2, 1
        }, {
            2, 2, 3, 2, 1, 1
        }, {
            2, 2, 1, 1, 3, 2
        }, {
            2, 2, 1, 2, 3, 1
        }, {
            2, 1, 3, 2, 1, 2
        }, {
            2, 2, 3, 1, 1, 2
        }, {
            3, 1, 2, 1, 3, 1
        }, {
            3, 1, 1, 2, 2, 2
        }, {
            3, 2, 1, 1, 2, 2
        }, {
            3, 2, 1, 2, 2, 1
        }, {
            3, 1, 2, 2, 1, 2
        }, {
            3, 2, 2, 1, 1, 2
        }, {
            3, 2, 2, 2, 1, 1
        }, {
            2, 1, 2, 1, 2, 3
        }, {
            2, 1, 2, 3, 2, 1
        }, {
            2, 3, 2, 1, 2, 1
        }, {
            1, 1, 1, 3, 2, 3
        }, {
            1, 3, 1, 1, 2, 3
        }, {
            1, 3, 1, 3, 2, 1
        }, {
            1, 1, 2, 3, 1, 3
        }, {
            1, 3, 2, 1, 1, 3
        }, {
            1, 3, 2, 3, 1, 1
        }, {
            2, 1, 1, 3, 1, 3
        }, {
            2, 3, 1, 1, 1, 3
        }, {
            2, 3, 1, 3, 1, 1
        }, {
            1, 1, 2, 1, 3, 3
        }, {
            1, 1, 2, 3, 3, 1
        }, {
            1, 3, 2, 1, 3, 1
        }, {
            1, 1, 3, 1, 2, 3
        }, {
            1, 1, 3, 3, 2, 1
        }, {
            1, 3, 3, 1, 2, 1
        }, {
            3, 1, 3, 1, 2, 1
        }, {
            2, 1, 1, 3, 3, 1
        }, {
            2, 3, 1, 1, 3, 1
        }, {
            2, 1, 3, 1, 1, 3
        }, {
            2, 1, 3, 3, 1, 1
        }, {
            2, 1, 3, 1, 3, 1
        }, {
            3, 1, 1, 1, 2, 3
        }, {
            3, 1, 1, 3, 2, 1
        }, {
            3, 3, 1, 1, 2, 1
        }, {
            3, 1, 2, 1, 1, 3
        }, {
            3, 1, 2, 3, 1, 1
        }, {
            3, 3, 2, 1, 1, 1
        }, {
            3, 1, 4, 1, 1, 1
        }, {
            2, 2, 1, 4, 1, 1
        }, {
            4, 3, 1, 1, 1, 1
        }, {
            1, 1, 1, 2, 2, 4
        }, {
            1, 1, 1, 4, 2, 2
        }, {
            1, 2, 1, 1, 2, 4
        }, {
            1, 2, 1, 4, 2, 1
        }, {
            1, 4, 1, 1, 2, 2
        }, {
            1, 4, 1, 2, 2, 1
        }, {
            1, 1, 2, 2, 1, 4
        }, {
            1, 1, 2, 4, 1, 2
        }, {
            1, 2, 2, 1, 1, 4
        }, {
            1, 2, 2, 4, 1, 1
        }, {
            1, 4, 2, 1, 1, 2
        }, {
            1, 4, 2, 2, 1, 1
        }, {
            2, 4, 1, 2, 1, 1
        }, {
            2, 2, 1, 1, 1, 4
        }, {
            4, 1, 3, 1, 1, 1
        }, {
            2, 4, 1, 1, 1, 2
        }, {
            1, 3, 4, 1, 1, 1
        }, {
            1, 1, 1, 2, 4, 2
        }, {
            1, 2, 1, 1, 4, 2
        }, {
            1, 2, 1, 2, 4, 1
        }, {
            1, 1, 4, 2, 1, 2
        }, {
            1, 2, 4, 1, 1, 2
        }, {
            1, 2, 4, 2, 1, 1
        }, {
            4, 1, 1, 2, 1, 2
        }, {
            4, 2, 1, 1, 1, 2
        }, {
            4, 2, 1, 2, 1, 1
        }, {
            2, 1, 2, 1, 4, 1
        }, {
            2, 1, 4, 1, 2, 1
        }, {
            4, 1, 2, 1, 2, 1
        }, {
            1, 1, 1, 1, 4, 3
        }, {
            1, 1, 1, 3, 4, 1
        }, {
            1, 3, 1, 1, 4, 1
        }, {
            1, 1, 4, 1, 1, 3
        }, {
            1, 1, 4, 3, 1, 1
        }, {
            4, 1, 1, 1, 1, 3
        }, {
            4, 1, 1, 3, 1, 1
        }, {
            1, 1, 3, 1, 4, 1
        }, {
            1, 1, 4, 1, 3, 1
        }, {
            3, 1, 1, 1, 4, 1
        }, {
            4, 1, 1, 1, 3, 1
        }, {
            2, 1, 1, 4, 1, 2
        }, {
            2, 1, 1, 2, 1, 4
        }, {
            2, 1, 1, 2, 3, 2
        }
    };
    private static final byte BARS_STOP[] = {
        2, 3, 3, 1, 1, 1, 2
    };
    public static final char CODE_AB_TO_C = 99;
    public static final char CODE_AC_TO_B = 100;
    public static final char CODE_BC_TO_A = 101;
    public static final char FNC1_INDEX = 102;
    public static final char START_A = 103;
    public static final char START_B = 104;
    public static final char START_C = 105;
    public static final char FNC1 = 202;
    public static final char DEL = 195;
    public static final char FNC3 = 196;
    public static final char FNC2 = 197;
    public static final char SHIFT = 198;
    public static final char CODE_C = 199;
    public static final char CODE_A = 200;
    public static final char FNC4 = 200;
    public static final char STARTA = 203;
    public static final char STARTB = 204;
    public static final char STARTC = 205;
    private static final IntHashtable ais;

    static 
    {
        ais = new IntHashtable();
        ais.put(0, 20);
        ais.put(1, 16);
        ais.put(2, 16);
        ais.put(10, -1);
        ais.put(11, 9);
        ais.put(12, 8);
        ais.put(13, 8);
        ais.put(15, 8);
        ais.put(17, 8);
        ais.put(20, 4);
        ais.put(21, -1);
        ais.put(22, -1);
        ais.put(23, -1);
        ais.put(240, -1);
        ais.put(241, -1);
        ais.put(250, -1);
        ais.put(251, -1);
        ais.put(252, -1);
        ais.put(30, -1);
        for(int k = 3100; k < 3700; k++)
            ais.put(k, 10);

        ais.put(37, -1);
        for(int k = 3900; k < 3940; k++)
            ais.put(k, -1);

        ais.put(400, -1);
        ais.put(401, -1);
        ais.put(402, 20);
        ais.put(403, -1);
        for(int k = 410; k < 416; k++)
            ais.put(k, 16);

        ais.put(420, -1);
        ais.put(421, -1);
        ais.put(422, 6);
        ais.put(423, -1);
        ais.put(424, 6);
        ais.put(425, 6);
        ais.put(426, 6);
        ais.put(7001, 17);
        ais.put(7002, -1);
        for(int k = 7030; k < 7040; k++)
            ais.put(k, -1);

        ais.put(8001, 18);
        ais.put(8002, -1);
        ais.put(8003, -1);
        ais.put(8004, -1);
        ais.put(8005, 10);
        ais.put(8006, 22);
        ais.put(8007, -1);
        ais.put(8008, -1);
        ais.put(8018, 22);
        ais.put(8020, -1);
        ais.put(8100, 10);
        ais.put(8101, 14);
        ais.put(8102, 6);
        for(int k = 90; k < 100; k++)
            ais.put(k, -1);

    }
}
