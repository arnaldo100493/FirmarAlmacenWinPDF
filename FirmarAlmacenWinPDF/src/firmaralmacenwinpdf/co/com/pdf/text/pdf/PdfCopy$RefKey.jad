// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfCopy.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfIndirectReference, PRIndirectReference, PdfCopy

protected static class PdfCopy$RefKey
{

    public int hashCode()
    {
        return (gen << 16) + num;
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof PdfCopy$RefKey))
        {
            return false;
        } else
        {
            PdfCopy$RefKey other = (PdfCopy$RefKey)o;
            return gen == other.gen && num == other.num;
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append(Integer.toString(num)).append(' ').append(gen).toString();
    }

    int num;
    int gen;

    PdfCopy$RefKey(int num, int gen)
    {
        this.num = num;
        this.gen = gen;
    }

    PdfCopy$RefKey(PdfIndirectReference ref)
    {
        num = ref.getNumber();
        gen = ref.getGeneration();
    }

    PdfCopy$RefKey(PRIndirectReference ref)
    {
        num = ref.getNumber();
        gen = ref.getGeneration();
    }
}
