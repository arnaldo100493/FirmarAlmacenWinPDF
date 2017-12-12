// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfIndirectReference.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfObject

public class PdfIndirectReference extends PdfObject
{

    protected PdfIndirectReference()
    {
        super(0);
        generation = 0;
    }

    PdfIndirectReference(int type, int number, int generation)
    {
        super(0, number + " " + generation + " R");
        this.generation = 0;
        this.number = number;
        this.generation = generation;
    }

    protected PdfIndirectReference(int type, int number)
    {
        this(type, number, 0);
    }

    public int getNumber()
    {
        return number;
    }

    public int getGeneration()
    {
        return generation;
    }

    public String toString()
    {
        return number + " " + generation + " R";
    }

    protected int number;
    protected int generation;
}
