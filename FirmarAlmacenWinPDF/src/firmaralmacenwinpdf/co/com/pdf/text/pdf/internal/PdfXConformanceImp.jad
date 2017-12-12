// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfXConformanceImp.java

package co.com.pdf.text.pdf.internal;

import co.com.pdf.text.BaseColor;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.*;
import co.com.pdf.text.pdf.interfaces.PdfXConformance;

public class PdfXConformanceImp
    implements PdfXConformance
{

    public PdfXConformanceImp(PdfWriter writer)
    {
        pdfxConformance = 0;
        this.writer = writer;
    }

    public void setPDFXConformance(int pdfxConformance)
    {
        this.pdfxConformance = pdfxConformance;
    }

    public int getPDFXConformance()
    {
        return pdfxConformance;
    }

    public boolean isPdfIso()
    {
        return isPdfX();
    }

    public boolean isPdfX()
    {
        return pdfxConformance != 0;
    }

    public boolean isPdfX1A2001()
    {
        return pdfxConformance == 1;
    }

    public boolean isPdfX32002()
    {
        return pdfxConformance == 2;
    }

    public void checkPdfIsoConformance(int key, Object obj1)
    {
        if(writer == null || !writer.isPdfX())
            return;
        int conf = writer.getPDFXConformance();
label0:
        switch(key)
        {
        case 2: // '\002'
        default:
            break;

        case 1: // '\001'
            switch(conf)
            {
            case 1: // '\001'
                if(obj1 instanceof ExtendedColor)
                {
                    ExtendedColor ec = (ExtendedColor)obj1;
                    switch(ec.getType())
                    {
                    case 1: // '\001'
                    case 2: // '\002'
                        return;

                    case 0: // '\0'
                        throw new PdfXConformanceException(MessageLocalization.getComposedMessage("colorspace.rgb.is.not.allowed", new Object[0]));

                    case 3: // '\003'
                        SpotColor sc = (SpotColor)ec;
                        checkPdfIsoConformance(1, sc.getPdfSpotColor().getAlternativeCS());
                        break;

                    case 5: // '\005'
                        ShadingColor xc = (ShadingColor)ec;
                        checkPdfIsoConformance(1, xc.getPdfShadingPattern().getShading().getColorSpace());
                        break;

                    case 4: // '\004'
                        PatternColor pc = (PatternColor)ec;
                        checkPdfIsoConformance(1, pc.getPainter().getDefaultColor());
                        break;
                    }
                } else
                if(obj1 instanceof BaseColor)
                    throw new PdfXConformanceException(MessageLocalization.getComposedMessage("colorspace.rgb.is.not.allowed", new Object[0]));
                break;
            }
            break;

        case 3: // '\003'
            if(conf == 1)
                throw new PdfXConformanceException(MessageLocalization.getComposedMessage("colorspace.rgb.is.not.allowed", new Object[0]));
            break;

        case 4: // '\004'
            if(!((BaseFont)obj1).isEmbedded())
                throw new PdfXConformanceException(MessageLocalization.getComposedMessage("all.the.fonts.must.be.embedded.this.one.isn.t.1", new Object[] {
                    ((BaseFont)obj1).getPostscriptFontName()
                }));
            break;

        case 5: // '\005'
            PdfImage image = (PdfImage)obj1;
            if(image.get(PdfName.SMASK) != null)
                throw new PdfXConformanceException(MessageLocalization.getComposedMessage("the.smask.key.is.not.allowed.in.images", new Object[0]));
            switch(conf)
            {
            default:
                break;

            case 1: // '\001'
                PdfObject cs = image.get(PdfName.COLORSPACE);
                if(cs == null)
                    return;
                if(cs.isName())
                {
                    if(PdfName.DEVICERGB.equals(cs))
                        throw new PdfXConformanceException(MessageLocalization.getComposedMessage("colorspace.rgb.is.not.allowed", new Object[0]));
                    break label0;
                }
                if(cs.isArray() && PdfName.CALRGB.equals(((PdfArray)cs).getPdfObject(0)))
                    throw new PdfXConformanceException(MessageLocalization.getComposedMessage("colorspace.calrgb.is.not.allowed", new Object[0]));
                break;
            }
            break;

        case 6: // '\006'
            PdfDictionary gs = (PdfDictionary)obj1;
            PdfObject obj = gs.get(PdfName.BM);
            if(obj != null && !PdfGState.BM_NORMAL.equals(obj) && !PdfGState.BM_COMPATIBLE.equals(obj))
                throw new PdfXConformanceException(MessageLocalization.getComposedMessage("blend.mode.1.not.allowed", new Object[] {
                    obj.toString()
                }));
            obj = gs.get(PdfName.CA);
            double v = 0.0D;
            if(obj != null && (v = ((PdfNumber)obj).doubleValue()) != 1.0D)
                throw new PdfXConformanceException(MessageLocalization.getComposedMessage("transparency.is.not.allowed.ca.eq.1", new Object[] {
                    String.valueOf(v)
                }));
            obj = gs.get(PdfName.ca);
            v = 0.0D;
            if(obj != null && (v = ((PdfNumber)obj).doubleValue()) != 1.0D)
                throw new PdfXConformanceException(MessageLocalization.getComposedMessage("transparency.is.not.allowed.ca.eq.1", new Object[] {
                    String.valueOf(v)
                }));
            break;

        case 7: // '\007'
            throw new PdfXConformanceException(MessageLocalization.getComposedMessage("layers.are.not.allowed", new Object[0]));
        }
    }

    protected int pdfxConformance;
    protected PdfWriter writer;
}
