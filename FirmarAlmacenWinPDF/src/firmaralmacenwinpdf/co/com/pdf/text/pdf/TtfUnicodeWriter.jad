// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TtfUnicodeWriter.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocumentException;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            CFFFontSubset, RandomAccessFileOrArray, TrueTypeFontUnicode, PdfIndirectReference, 
//            PdfObject, PdfIndirectObject, PdfDictionary, BaseFont, 
//            PdfWriter

public class TtfUnicodeWriter
{

    public TtfUnicodeWriter(PdfWriter writer)
    {
        this.writer = null;
        this.writer = writer;
    }

    public void writeFont(TrueTypeFontUnicode font, PdfIndirectReference ref, Object params[], byte rotbits[])
        throws DocumentException, IOException
    {
        HashMap longTag = (HashMap)params[0];
        font.addRangeUni(longTag, true, font.subset);
        int metrics[][] = (int[][])longTag.values().toArray(new int[0][]);
        Arrays.sort(metrics, font);
        PdfIndirectReference ind_font = null;
        PdfObject pobj = null;
        PdfIndirectObject obj = null;
        PdfIndirectReference cidset = null;
        if(font.cff)
        {
            byte b[] = font.readCffFont();
            if(font.subset || font.subsetRanges != null)
            {
                CFFFontSubset cff = new CFFFontSubset(new RandomAccessFileOrArray(b), longTag);
                b = cff.Process(cff.getNames()[0]);
            }
            pobj = new BaseFont.StreamFont(b, "CIDFontType0C", font.compressionLevel);
            obj = writer.addToBody(pobj);
            ind_font = obj.getIndirectReference();
        } else
        {
            byte b[];
            if(font.subset || font.directoryOffset != 0)
                b = font.getSubSet(new HashSet(longTag.keySet()), true);
            else
                b = font.getFullFont();
            int lengths[] = {
                b.length
            };
            pobj = new BaseFont.StreamFont(b, lengths, font.compressionLevel);
            obj = writer.addToBody(pobj);
            ind_font = obj.getIndirectReference();
        }
        String subsetPrefix = "";
        if(font.subset)
        {
            TrueTypeFontUnicode _tmp = font;
            subsetPrefix = TrueTypeFontUnicode.createSubsetPrefix();
        }
        PdfDictionary dic = font.getFontDescriptor(ind_font, subsetPrefix, cidset);
        obj = writer.addToBody(dic);
        ind_font = obj.getIndirectReference();
        pobj = font.getCIDFontType2(ind_font, subsetPrefix, metrics);
        obj = writer.addToBody(pobj);
        ind_font = obj.getIndirectReference();
        pobj = font.getToUnicode(metrics);
        PdfIndirectReference toUnicodeRef = null;
        if(pobj != null)
        {
            obj = writer.addToBody(pobj);
            toUnicodeRef = obj.getIndirectReference();
        }
        pobj = font.getFontBaseType(ind_font, subsetPrefix, toUnicodeRef);
        writer.addToBody(pobj, ref);
    }

    protected PdfWriter writer;
}
