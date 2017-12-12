// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FontSelector.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf:
//            BaseFont

public class FontSelector
{

    public FontSelector()
    {
        fonts = new ArrayList();
        currentFont = null;
    }

    public void addFont(Font font)
    {
        if(font.getBaseFont() != null)
        {
            fonts.add(font);
            return;
        } else
        {
            BaseFont bf = font.getCalculatedBaseFont(true);
            Font f2 = new Font(bf, font.getSize(), font.getCalculatedStyle(), font.getColor());
            fonts.add(f2);
            return;
        }
    }

    public Phrase process(String text)
    {
        if(fonts.size() == 0)
            throw new IndexOutOfBoundsException(MessageLocalization.getComposedMessage("no.font.is.defined", new Object[0]));
        char cc[] = text.toCharArray();
        int len = cc.length;
        StringBuffer sb = new StringBuffer();
        Phrase ret = new Phrase();
        currentFont = null;
        for(int k = 0; k < len; k++)
        {
            Chunk newChunk = processChar(cc, k, sb);
            if(newChunk != null)
                ret.add(newChunk);
        }

        if(sb.length() > 0)
        {
            Chunk ck = new Chunk(sb.toString(), currentFont == null ? (Font)fonts.get(0) : currentFont);
            ret.add(ck);
        }
        return ret;
    }

    protected Chunk processChar(char cc[], int k, StringBuffer sb)
    {
        Chunk newChunk = null;
        char c = cc[k];
        if(c == '\n' || c == '\r')
        {
            sb.append(c);
        } else
        {
            Font font = null;
            if(Utilities.isSurrogatePair(cc, k))
            {
                int u = Utilities.convertToUtf32(cc, k);
                int f = 0;
                do
                {
                    if(f >= fonts.size())
                        break;
                    font = (Font)fonts.get(f);
                    if(font.getBaseFont().charExists(u))
                    {
                        if(currentFont != font)
                        {
                            if(sb.length() > 0 && currentFont != null)
                            {
                                newChunk = new Chunk(sb.toString(), currentFont);
                                sb.setLength(0);
                            }
                            currentFont = font;
                        }
                        sb.append(c);
                        sb.append(cc[++k]);
                        break;
                    }
                    f++;
                } while(true);
            } else
            {
                int f = 0;
                do
                {
                    if(f >= fonts.size())
                        break;
                    font = (Font)fonts.get(f);
                    if(font.getBaseFont().charExists(c))
                    {
                        if(currentFont != font)
                        {
                            if(sb.length() > 0 && currentFont != null)
                            {
                                newChunk = new Chunk(sb.toString(), currentFont);
                                sb.setLength(0);
                            }
                            currentFont = font;
                        }
                        sb.append(c);
                        break;
                    }
                    f++;
                } while(true);
            }
        }
        return newChunk;
    }

    protected ArrayList fonts;
    protected Font currentFont;
}
