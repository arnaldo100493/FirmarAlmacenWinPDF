// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Type3Font.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocumentException;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.IOException;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf:
//            BaseFont, IntHashtable, PageResources, Type3Glyph, 
//            PdfArray, PdfDictionary, PdfNumber, PdfName, 
//            PdfStream, PdfRectangle, PdfWriter, PdfIndirectReference, 
//            GlyphList, PdfIndirectObject, PdfContentByte

public class Type3Font extends BaseFont
{

    public Type3Font(PdfWriter writer, char chars[], boolean colorized)
    {
        this(writer, colorized);
    }

    public Type3Font(PdfWriter writer, boolean colorized)
    {
        widths3 = new IntHashtable();
        char2glyph = new HashMap();
        llx = (0.0F / 0.0F);
        pageResources = new PageResources();
        this.writer = writer;
        this.colorized = colorized;
        fontType = 5;
        usedSlot = new boolean[256];
    }

    public PdfContentByte defineGlyph(char c, float wx, float llx, float lly, float urx, float ury)
    {
        if(c == 0 || c > '\377')
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.char.1.doesn.t.belong.in.this.type3.font", c));
        usedSlot[c] = true;
        Integer ck = Integer.valueOf(c);
        Type3Glyph glyph = (Type3Glyph)char2glyph.get(ck);
        if(glyph != null)
            return glyph;
        widths3.put(c, (int)wx);
        if(!colorized)
            if(Float.isNaN(this.llx))
            {
                this.llx = llx;
                this.lly = lly;
                this.urx = urx;
                this.ury = ury;
            } else
            {
                this.llx = Math.min(this.llx, llx);
                this.lly = Math.min(this.lly, lly);
                this.urx = Math.max(this.urx, urx);
                this.ury = Math.max(this.ury, ury);
            }
        glyph = new Type3Glyph(writer, pageResources, wx, llx, lly, urx, ury, colorized);
        char2glyph.put(ck, glyph);
        return glyph;
    }

    public String[][] getFamilyFontName()
    {
        return getFullFontName();
    }

    public float getFontDescriptor(int key, float fontSize)
    {
        return 0.0F;
    }

    public String[][] getFullFontName()
    {
        return (new String[][] {
            new String[] {
                "", "", "", ""
            }
        });
    }

    public String[][] getAllNameEntries()
    {
        return (new String[][] {
            new String[] {
                "4", "", "", "", ""
            }
        });
    }

    public int getKerning(int char1, int char2)
    {
        return 0;
    }

    public String getPostscriptFontName()
    {
        return "";
    }

    protected int[] getRawCharBBox(int c, String name)
    {
        return null;
    }

    int getRawWidth(int c, String name)
    {
        return 0;
    }

    public boolean hasKernPairs()
    {
        return false;
    }

    public boolean setKerning(int char1, int char2, int kern)
    {
        return false;
    }

    public void setPostscriptFontName(String s)
    {
    }

    void writeFont(PdfWriter writer, PdfIndirectReference ref, Object params[])
        throws DocumentException, IOException
    {
        if(this.writer != writer)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("type3.font.used.with.the.wrong.pdfwriter", new Object[0]));
        int firstChar;
        for(firstChar = 0; firstChar < usedSlot.length && !usedSlot[firstChar]; firstChar++);
        if(firstChar == usedSlot.length)
            throw new DocumentException(MessageLocalization.getComposedMessage("no.glyphs.defined.for.type3.font", new Object[0]));
        int lastChar;
        for(lastChar = usedSlot.length - 1; lastChar >= firstChar && !usedSlot[lastChar]; lastChar--);
        int widths[] = new int[(lastChar - firstChar) + 1];
        int invOrd[] = new int[(lastChar - firstChar) + 1];
        int invOrdIndx = 0;
        int w = 0;
        for(int u = firstChar; u <= lastChar;)
        {
            if(usedSlot[u])
            {
                invOrd[invOrdIndx++] = u;
                widths[w] = widths3.get(u);
            }
            u++;
            w++;
        }

        PdfArray diffs = new PdfArray();
        PdfDictionary charprocs = new PdfDictionary();
        int last = -1;
        for(int k = 0; k < invOrdIndx; k++)
        {
            int c = invOrd[k];
            if(c > last)
            {
                last = c;
                diffs.add(new PdfNumber(last));
            }
            last++;
            int c2 = invOrd[k];
            String s = GlyphList.unicodeToName(c2);
            if(s == null)
                s = (new StringBuilder()).append("a").append(c2).toString();
            PdfName n = new PdfName(s);
            diffs.add(n);
            Type3Glyph glyph = (Type3Glyph)char2glyph.get(Integer.valueOf(c2));
            PdfStream stream = new PdfStream(glyph.toPdf(null));
            stream.flateCompress(compressionLevel);
            PdfIndirectReference refp = writer.addToBody(stream).getIndirectReference();
            charprocs.put(n, refp);
        }

        PdfDictionary font = new PdfDictionary(PdfName.FONT);
        font.put(PdfName.SUBTYPE, PdfName.TYPE3);
        if(colorized)
            font.put(PdfName.FONTBBOX, new PdfRectangle(0.0F, 0.0F, 0.0F, 0.0F));
        else
            font.put(PdfName.FONTBBOX, new PdfRectangle(llx, lly, urx, ury));
        font.put(PdfName.FONTMATRIX, new PdfArray(new float[] {
            0.001F, 0.0F, 0.0F, 0.001F, 0.0F, 0.0F
        }));
        font.put(PdfName.CHARPROCS, writer.addToBody(charprocs).getIndirectReference());
        PdfDictionary encoding = new PdfDictionary();
        encoding.put(PdfName.DIFFERENCES, diffs);
        font.put(PdfName.ENCODING, writer.addToBody(encoding).getIndirectReference());
        font.put(PdfName.FIRSTCHAR, new PdfNumber(firstChar));
        font.put(PdfName.LASTCHAR, new PdfNumber(lastChar));
        font.put(PdfName.WIDTHS, writer.addToBody(new PdfArray(widths)).getIndirectReference());
        if(pageResources.hasResources())
            font.put(PdfName.RESOURCES, writer.addToBody(pageResources.getResources()).getIndirectReference());
        writer.addToBody(font, ref);
    }

    public PdfStream getFullFontStream()
    {
        return null;
    }

    public byte[] convertToBytes(String text)
    {
        char cc[] = text.toCharArray();
        byte b[] = new byte[cc.length];
        int p = 0;
        for(int k = 0; k < cc.length; k++)
        {
            char c = cc[k];
            if(charExists(c))
                b[p++] = (byte)c;
        }

        if(b.length == p)
        {
            return b;
        } else
        {
            byte b2[] = new byte[p];
            System.arraycopy(b, 0, b2, 0, p);
            return b2;
        }
    }

    byte[] convertToBytes(int char1)
    {
        if(charExists(char1))
            return (new byte[] {
                (byte)char1
            });
        else
            return new byte[0];
    }

    public int getWidth(int char1)
    {
        if(!widths3.containsKey(char1))
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.char.1.is.not.defined.in.a.type3.font", char1));
        else
            return widths3.get(char1);
    }

    public int getWidth(String text)
    {
        char c[] = text.toCharArray();
        int total = 0;
        for(int k = 0; k < c.length; k++)
            total += getWidth(c[k]);

        return total;
    }

    public int[] getCharBBox(int c)
    {
        return null;
    }

    public boolean charExists(int c)
    {
        if(c > 0 && c < 256)
            return usedSlot[c];
        else
            return false;
    }

    public boolean setCharAdvance(int c, int advance)
    {
        return false;
    }

    private boolean usedSlot[];
    private IntHashtable widths3;
    private HashMap char2glyph;
    private PdfWriter writer;
    private float llx;
    private float lly;
    private float urx;
    private float ury;
    private PageResources pageResources;
    private boolean colorized;
}
