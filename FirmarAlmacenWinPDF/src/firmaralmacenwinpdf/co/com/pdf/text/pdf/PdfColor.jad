// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfColor.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.BaseColor;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfArray, PdfNumber

class PdfColor extends PdfArray
{

    PdfColor(int red, int green, int blue)
    {
        super(new PdfNumber((double)(red & 0xff) / 255D));
        add(new PdfNumber((double)(green & 0xff) / 255D));
        add(new PdfNumber((double)(blue & 0xff) / 255D));
    }

    PdfColor(BaseColor color)
    {
        this(color.getRed(), color.getGreen(), color.getBlue());
    }
}
