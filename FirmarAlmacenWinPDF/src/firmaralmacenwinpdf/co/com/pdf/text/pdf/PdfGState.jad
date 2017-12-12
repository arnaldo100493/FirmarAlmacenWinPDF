// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfGState.java

package co.com.pdf.text.pdf;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfNumber, PdfName, PdfBoolean, 
//            PdfWriter

public class PdfGState extends PdfDictionary
{

    public PdfGState()
    {
    }

    public void setOverPrintStroking(boolean ov)
    {
        put(PdfName.OP, ov ? ((PdfObject) (PdfBoolean.PDFTRUE)) : ((PdfObject) (PdfBoolean.PDFFALSE)));
    }

    public void setOverPrintNonStroking(boolean ov)
    {
        put(PdfName.op, ov ? ((PdfObject) (PdfBoolean.PDFTRUE)) : ((PdfObject) (PdfBoolean.PDFFALSE)));
    }

    public void setOverPrintMode(int ov)
    {
        put(PdfName.OPM, new PdfNumber(ov != 0 ? 1 : 0));
    }

    public void setStrokeOpacity(float n)
    {
        put(PdfName.CA, new PdfNumber(n));
    }

    public void setFillOpacity(float n)
    {
        put(PdfName.ca, new PdfNumber(n));
    }

    public void setAlphaIsShape(boolean v)
    {
        put(PdfName.AIS, v ? ((PdfObject) (PdfBoolean.PDFTRUE)) : ((PdfObject) (PdfBoolean.PDFFALSE)));
    }

    public void setTextKnockout(boolean v)
    {
        put(PdfName.TK, v ? ((PdfObject) (PdfBoolean.PDFTRUE)) : ((PdfObject) (PdfBoolean.PDFFALSE)));
    }

    public void setBlendMode(PdfName bm)
    {
        put(PdfName.BM, bm);
    }

    public void setRenderingIntent(PdfName ri)
    {
        put(PdfName.RI, ri);
    }

    public void toPdf(PdfWriter writer, OutputStream os)
        throws IOException
    {
        PdfWriter.checkPdfIsoConformance(writer, 6, this);
        super.toPdf(writer, os);
    }

    public static final PdfName BM_NORMAL = new PdfName("Normal");
    public static final PdfName BM_COMPATIBLE = new PdfName("Compatible");
    public static final PdfName BM_MULTIPLY = new PdfName("Multiply");
    public static final PdfName BM_SCREEN = new PdfName("Screen");
    public static final PdfName BM_OVERLAY = new PdfName("Overlay");
    public static final PdfName BM_DARKEN = new PdfName("Darken");
    public static final PdfName BM_LIGHTEN = new PdfName("Lighten");
    public static final PdfName BM_COLORDODGE = new PdfName("ColorDodge");
    public static final PdfName BM_COLORBURN = new PdfName("ColorBurn");
    public static final PdfName BM_HARDLIGHT = new PdfName("HardLight");
    public static final PdfName BM_SOFTLIGHT = new PdfName("SoftLight");
    public static final PdfName BM_DIFFERENCE = new PdfName("Difference");
    public static final PdfName BM_EXCLUSION = new PdfName("Exclusion");

}
