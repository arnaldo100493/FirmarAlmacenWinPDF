// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfShading.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.BaseColor;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf:
//            SpotColor, GrayColor, CMYKColor, PdfDictionary, 
//            PdfNumber, PdfArray, PdfName, PdfObject, 
//            PdfWriter, PdfFunction, PdfBoolean, ExtendedColor, 
//            ColorDetails, PdfIndirectReference

public class PdfShading
{

    protected PdfShading(PdfWriter writer)
    {
        antiAlias = false;
        this.writer = writer;
    }

    protected void setColorSpace(BaseColor color)
    {
        cspace = color;
        int type = ExtendedColor.getType(color);
        PdfObject colorSpace = null;
        switch(type)
        {
        case 1: // '\001'
            colorSpace = PdfName.DEVICEGRAY;
            break;

        case 2: // '\002'
            colorSpace = PdfName.DEVICECMYK;
            break;

        case 3: // '\003'
            SpotColor spot = (SpotColor)color;
            colorDetails = writer.addSimple(spot.getPdfSpotColor());
            colorSpace = colorDetails.getIndirectReference();
            break;

        case 4: // '\004'
        case 5: // '\005'
            throwColorSpaceError();
            // fall through

        default:
            colorSpace = PdfName.DEVICERGB;
            break;
        }
        shading.put(PdfName.COLORSPACE, colorSpace);
    }

    public BaseColor getColorSpace()
    {
        return cspace;
    }

    public static void throwColorSpaceError()
    {
        throw new IllegalArgumentException(MessageLocalization.getComposedMessage("a.tiling.or.shading.pattern.cannot.be.used.as.a.color.space.in.a.shading.pattern", new Object[0]));
    }

    public static void checkCompatibleColors(BaseColor c1, BaseColor c2)
    {
        int type1 = ExtendedColor.getType(c1);
        int type2 = ExtendedColor.getType(c2);
        if(type1 != type2)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("both.colors.must.be.of.the.same.type", new Object[0]));
        if(type1 == 3 && ((SpotColor)c1).getPdfSpotColor() != ((SpotColor)c2).getPdfSpotColor())
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.spot.color.must.be.the.same.only.the.tint.can.vary", new Object[0]));
        if(type1 == 4 || type1 == 5)
            throwColorSpaceError();
    }

    public static float[] getColorArray(BaseColor color)
    {
        int type = ExtendedColor.getType(color);
        switch(type)
        {
        case 1: // '\001'
            return (new float[] {
                ((GrayColor)color).getGray()
            });

        case 2: // '\002'
            CMYKColor cmyk = (CMYKColor)color;
            return (new float[] {
                cmyk.getCyan(), cmyk.getMagenta(), cmyk.getYellow(), cmyk.getBlack()
            });

        case 3: // '\003'
            return (new float[] {
                ((SpotColor)color).getTint()
            });

        case 0: // '\0'
            return (new float[] {
                (float)color.getRed() / 255F, (float)color.getGreen() / 255F, (float)color.getBlue() / 255F
            });
        }
        throwColorSpaceError();
        return null;
    }

    public static PdfShading type1(PdfWriter writer, BaseColor colorSpace, float domain[], float tMatrix[], PdfFunction function)
    {
        PdfShading sp = new PdfShading(writer);
        sp.shading = new PdfDictionary();
        sp.shadingType = 1;
        sp.shading.put(PdfName.SHADINGTYPE, new PdfNumber(sp.shadingType));
        sp.setColorSpace(colorSpace);
        if(domain != null)
            sp.shading.put(PdfName.DOMAIN, new PdfArray(domain));
        if(tMatrix != null)
            sp.shading.put(PdfName.MATRIX, new PdfArray(tMatrix));
        sp.shading.put(PdfName.FUNCTION, function.getReference());
        return sp;
    }

    public static PdfShading type2(PdfWriter writer, BaseColor colorSpace, float coords[], float domain[], PdfFunction function, boolean extend[])
    {
        PdfShading sp = new PdfShading(writer);
        sp.shading = new PdfDictionary();
        sp.shadingType = 2;
        sp.shading.put(PdfName.SHADINGTYPE, new PdfNumber(sp.shadingType));
        sp.setColorSpace(colorSpace);
        sp.shading.put(PdfName.COORDS, new PdfArray(coords));
        if(domain != null)
            sp.shading.put(PdfName.DOMAIN, new PdfArray(domain));
        sp.shading.put(PdfName.FUNCTION, function.getReference());
        if(extend != null && (extend[0] || extend[1]))
        {
            PdfArray array = new PdfArray(extend[0] ? ((PdfObject) (PdfBoolean.PDFTRUE)) : ((PdfObject) (PdfBoolean.PDFFALSE)));
            array.add(extend[1] ? ((PdfObject) (PdfBoolean.PDFTRUE)) : ((PdfObject) (PdfBoolean.PDFFALSE)));
            sp.shading.put(PdfName.EXTEND, array);
        }
        return sp;
    }

    public static PdfShading type3(PdfWriter writer, BaseColor colorSpace, float coords[], float domain[], PdfFunction function, boolean extend[])
    {
        PdfShading sp = type2(writer, colorSpace, coords, domain, function, extend);
        sp.shadingType = 3;
        sp.shading.put(PdfName.SHADINGTYPE, new PdfNumber(sp.shadingType));
        return sp;
    }

    public static PdfShading simpleAxial(PdfWriter writer, float x0, float y0, float x1, float y1, BaseColor startColor, BaseColor endColor, boolean extendStart, 
            boolean extendEnd)
    {
        checkCompatibleColors(startColor, endColor);
        PdfFunction function = PdfFunction.type2(writer, new float[] {
            0.0F, 1.0F
        }, null, getColorArray(startColor), getColorArray(endColor), 1.0F);
        return type2(writer, startColor, new float[] {
            x0, y0, x1, y1
        }, null, function, new boolean[] {
            extendStart, extendEnd
        });
    }

    public static PdfShading simpleAxial(PdfWriter writer, float x0, float y0, float x1, float y1, BaseColor startColor, BaseColor endColor)
    {
        return simpleAxial(writer, x0, y0, x1, y1, startColor, endColor, true, true);
    }

    public static PdfShading simpleRadial(PdfWriter writer, float x0, float y0, float r0, float x1, float y1, float r1, BaseColor startColor, 
            BaseColor endColor, boolean extendStart, boolean extendEnd)
    {
        checkCompatibleColors(startColor, endColor);
        PdfFunction function = PdfFunction.type2(writer, new float[] {
            0.0F, 1.0F
        }, null, getColorArray(startColor), getColorArray(endColor), 1.0F);
        return type3(writer, startColor, new float[] {
            x0, y0, r0, x1, y1, r1
        }, null, function, new boolean[] {
            extendStart, extendEnd
        });
    }

    public static PdfShading simpleRadial(PdfWriter writer, float x0, float y0, float r0, float x1, float y1, float r1, BaseColor startColor, 
            BaseColor endColor)
    {
        return simpleRadial(writer, x0, y0, r0, x1, y1, r1, startColor, endColor, true, true);
    }

    PdfName getShadingName()
    {
        return shadingName;
    }

    PdfIndirectReference getShadingReference()
    {
        if(shadingReference == null)
            shadingReference = writer.getPdfIndirectReference();
        return shadingReference;
    }

    void setName(int number)
    {
        shadingName = new PdfName((new StringBuilder()).append("Sh").append(number).toString());
    }

    public void addToBody()
        throws IOException
    {
        if(bBox != null)
            shading.put(PdfName.BBOX, new PdfArray(bBox));
        if(antiAlias)
            shading.put(PdfName.ANTIALIAS, PdfBoolean.PDFTRUE);
        writer.addToBody(shading, getShadingReference());
    }

    PdfWriter getWriter()
    {
        return writer;
    }

    ColorDetails getColorDetails()
    {
        return colorDetails;
    }

    public float[] getBBox()
    {
        return bBox;
    }

    public void setBBox(float bBox[])
    {
        if(bBox.length != 4)
        {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("bbox.must.be.a.4.element.array", new Object[0]));
        } else
        {
            this.bBox = bBox;
            return;
        }
    }

    public boolean isAntiAlias()
    {
        return antiAlias;
    }

    public void setAntiAlias(boolean antiAlias)
    {
        this.antiAlias = antiAlias;
    }

    protected PdfDictionary shading;
    protected PdfWriter writer;
    protected int shadingType;
    protected ColorDetails colorDetails;
    protected PdfName shadingName;
    protected PdfIndirectReference shadingReference;
    private BaseColor cspace;
    protected float bBox[];
    protected boolean antiAlias;
}
