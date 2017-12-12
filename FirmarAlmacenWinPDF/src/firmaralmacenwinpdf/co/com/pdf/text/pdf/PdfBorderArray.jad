// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfBorderArray.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfArray, PdfNumber, PdfDashPattern

public class PdfBorderArray extends PdfArray
{

    public PdfBorderArray(float hRadius, float vRadius, float width)
    {
        this(hRadius, vRadius, width, null);
    }

    public PdfBorderArray(float hRadius, float vRadius, float width, PdfDashPattern dash)
    {
        super(new PdfNumber(hRadius));
        add(new PdfNumber(vRadius));
        add(new PdfNumber(width));
        if(dash != null)
            add(dash);
    }
}
