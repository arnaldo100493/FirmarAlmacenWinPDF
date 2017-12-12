// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfSpotColor.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.BaseColor;
import co.com.pdf.text.error_messages.MessageLocalization;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfName, PdfArray, ExtendedColor, GrayColor, 
//            CMYKColor, PdfFunction, PdfWriter, PdfObject

public class PdfSpotColor
{

    public PdfSpotColor(String name, BaseColor altcs)
    {
        this.name = new PdfName(name);
        this.altcs = altcs;
    }

    public BaseColor getAlternativeCS()
    {
        return altcs;
    }

    protected PdfObject getSpotObject(PdfWriter writer)
    {
        PdfArray array = new PdfArray(PdfName.SEPARATION);
        array.add(name);
        PdfFunction func = null;
        if(altcs instanceof ExtendedColor)
        {
            int type = ((ExtendedColor)altcs).type;
            switch(type)
            {
            case 1: // '\001'
                array.add(PdfName.DEVICEGRAY);
                func = PdfFunction.type2(writer, new float[] {
                    0.0F, 1.0F
                }, null, new float[] {
                    0.0F
                }, new float[] {
                    ((GrayColor)altcs).getGray()
                }, 1.0F);
                break;

            case 2: // '\002'
                array.add(PdfName.DEVICECMYK);
                CMYKColor cmyk = (CMYKColor)altcs;
                func = PdfFunction.type2(writer, new float[] {
                    0.0F, 1.0F
                }, null, new float[] {
                    0.0F, 0.0F, 0.0F, 0.0F
                }, new float[] {
                    cmyk.getCyan(), cmyk.getMagenta(), cmyk.getYellow(), cmyk.getBlack()
                }, 1.0F);
                break;

            default:
                throw new RuntimeException(MessageLocalization.getComposedMessage("only.rgb.gray.and.cmyk.are.supported.as.alternative.color.spaces", new Object[0]));
            }
        } else
        {
            array.add(PdfName.DEVICERGB);
            func = PdfFunction.type2(writer, new float[] {
                0.0F, 1.0F
            }, null, new float[] {
                1.0F, 1.0F, 1.0F
            }, new float[] {
                (float)altcs.getRed() / 255F, (float)altcs.getGreen() / 255F, (float)altcs.getBlue() / 255F
            }, 1.0F);
        }
        array.add(func.getReference());
        return array;
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof PdfSpotColor) && ((PdfSpotColor)obj).name == name && ((PdfSpotColor)obj).altcs == altcs;
    }

    public PdfName name;
    public BaseColor altcs;
}
