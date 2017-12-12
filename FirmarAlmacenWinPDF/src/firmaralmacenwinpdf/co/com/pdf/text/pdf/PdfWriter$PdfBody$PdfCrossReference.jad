// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfWriter.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocWriter;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfWriter

public static class PdfWriter$PdfBody$PdfCrossReference
    implements Comparable
{

    public int getRefnum()
    {
        return refnum;
    }

    public void toPdf(OutputStream os)
        throws IOException
    {
        StringBuffer off = (new StringBuffer("0000000000")).append(offset);
        off.delete(0, off.length() - 10);
        StringBuffer gen = (new StringBuffer("00000")).append(generation);
        gen.delete(0, gen.length() - 5);
        off.append(' ').append(gen).append(generation != 65535 ? " n \n" : " f \n");
        os.write(DocWriter.getISOBytes(off.toString()));
    }

    public void toPdf(int midSize, OutputStream os)
        throws IOException
    {
        os.write((byte)type);
        while(--midSize >= 0) 
            os.write((byte)(int)(offset >>> 8 * midSize & 255L));
        os.write((byte)(generation >>> 8 & 0xff));
        os.write((byte)(generation & 0xff));
    }

    public int compareTo(PdfWriter$PdfBody$PdfCrossReference other)
    {
        return refnum >= other.refnum ? ((int) (refnum != other.refnum ? 1 : 0)) : -1;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof PdfWriter$PdfBody$PdfCrossReference)
        {
            PdfWriter$PdfBody$PdfCrossReference other = (PdfWriter$PdfBody$PdfCrossReference)obj;
            return refnum == other.refnum;
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return refnum;
    }

    public volatile int compareTo(Object x0)
    {
        return compareTo((PdfWriter$PdfBody$PdfCrossReference)x0);
    }

    private final int type;
    private final long offset;
    private final int refnum;
    private final int generation;

    public PdfWriter$PdfBody$PdfCrossReference(int refnum, long offset, int generation)
    {
        type = 0;
        this.offset = offset;
        this.refnum = refnum;
        this.generation = generation;
    }

    public PdfWriter$PdfBody$PdfCrossReference(int refnum, long offset)
    {
        type = 1;
        this.offset = offset;
        this.refnum = refnum;
        generation = 0;
    }

    public PdfWriter$PdfBody$PdfCrossReference(int type, int refnum, long offset, int generation)
    {
        this.type = type;
        this.offset = offset;
        this.refnum = refnum;
        this.generation = generation;
    }
}
