// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PushbuttonField.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf:
//            BaseField, PdfTemplate, PdfDictionary, PdfBorderDictionary, 
//            PdfDashPattern, PdfAppearance, BaseFont, PdfArray, 
//            PdfNumber, PdfFormField, PdfName, PdfWriter, 
//            PdfReader, PdfAnnotation, PRIndirectReference

public class PushbuttonField extends BaseField
{

    public PushbuttonField(PdfWriter writer, Rectangle box, String fieldName)
    {
        super(writer, box, fieldName);
        layout = 1;
        scaleIcon = 1;
        proportionalIcon = true;
        iconVerticalAdjustment = 0.5F;
        iconHorizontalAdjustment = 0.5F;
    }

    public int getLayout()
    {
        return layout;
    }

    public void setLayout(int layout)
    {
        if(layout < 1 || layout > 7)
        {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("layout.out.of.bounds", new Object[0]));
        } else
        {
            this.layout = layout;
            return;
        }
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
        template = null;
    }

    public PdfTemplate getTemplate()
    {
        return template;
    }

    public void setTemplate(PdfTemplate template)
    {
        this.template = template;
        image = null;
    }

    public int getScaleIcon()
    {
        return scaleIcon;
    }

    public void setScaleIcon(int scaleIcon)
    {
        if(scaleIcon < 1 || scaleIcon > 4)
            scaleIcon = 1;
        this.scaleIcon = scaleIcon;
    }

    public boolean isProportionalIcon()
    {
        return proportionalIcon;
    }

    public void setProportionalIcon(boolean proportionalIcon)
    {
        this.proportionalIcon = proportionalIcon;
    }

    public float getIconVerticalAdjustment()
    {
        return iconVerticalAdjustment;
    }

    public void setIconVerticalAdjustment(float iconVerticalAdjustment)
    {
        if(iconVerticalAdjustment < 0.0F)
            iconVerticalAdjustment = 0.0F;
        else
        if(iconVerticalAdjustment > 1.0F)
            iconVerticalAdjustment = 1.0F;
        this.iconVerticalAdjustment = iconVerticalAdjustment;
    }

    public float getIconHorizontalAdjustment()
    {
        return iconHorizontalAdjustment;
    }

    public void setIconHorizontalAdjustment(float iconHorizontalAdjustment)
    {
        if(iconHorizontalAdjustment < 0.0F)
            iconHorizontalAdjustment = 0.0F;
        else
        if(iconHorizontalAdjustment > 1.0F)
            iconHorizontalAdjustment = 1.0F;
        this.iconHorizontalAdjustment = iconHorizontalAdjustment;
    }

    private float calculateFontSize(float w, float h)
        throws IOException, DocumentException
    {
        BaseFont ufont = getRealFont();
        float fsize = fontSize;
        if(fsize == 0.0F)
        {
            float bw = ufont.getWidthPoint(text, 1.0F);
            if(bw == 0.0F)
                fsize = 12F;
            else
                fsize = w / bw;
            float nfsize = h / (1.0F - ufont.getFontDescriptor(3, 1.0F));
            fsize = Math.min(fsize, nfsize);
            if(fsize < 4F)
                fsize = 4F;
        }
        return fsize;
    }

    public PdfAppearance getAppearance()
        throws IOException, DocumentException
    {
        PdfAppearance app;
        Rectangle box;
        BaseFont ufont;
        float offX;
        float textX;
        float textY;
        float fsize;
        float wt;
        float ht;
        float adj;
        int nlayout;
        Rectangle iconBox;
        app = getBorderAppearance();
        box = new Rectangle(app.getBoundingBox());
        if((text == null || text.length() == 0) && (layout == 1 || image == null && template == null && iconReference == null))
            return app;
        if(layout == 2 && image == null && template == null && iconReference == null)
            return app;
        ufont = getRealFont();
        boolean borderExtra = borderStyle == 2 || borderStyle == 3;
        float h = box.getHeight() - borderWidth * 2.0F;
        float bw2 = borderWidth;
        if(borderExtra)
        {
            h -= borderWidth * 2.0F;
            bw2 *= 2.0F;
        }
        float offsetX = borderExtra ? 2.0F * borderWidth : borderWidth;
        offsetX = Math.max(offsetX, 1.0F);
        offX = Math.min(bw2, offsetX);
        tp = null;
        textX = (0.0F / 0.0F);
        textY = 0.0F;
        fsize = fontSize;
        wt = box.getWidth() - 2.0F * offX - 2.0F;
        ht = box.getHeight() - 2.0F * offX;
        adj = iconFitToBounds ? 0.0F : offX + 1.0F;
        nlayout = layout;
        if(image == null && template == null && iconReference == null)
            nlayout = 1;
        iconBox = null;
_L9:
        nlayout;
        JVM INSTR tableswitch 1 7: default 1112
    //                   1 340
    //                   2 416
    //                   3 469
    //                   4 611
    //                   5 939
    //                   6 770
    //                   7 340;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L2
_L1:
        break MISSING_BLOCK_LABEL_1115;
_L2:
        if(text != null && text.length() > 0 && wt > 0.0F && ht > 0.0F)
        {
            fsize = calculateFontSize(wt, ht);
            textX = (box.getWidth() - ufont.getWidthPoint(text, fsize)) / 2.0F;
            textY = (box.getHeight() - ufont.getFontDescriptor(1, fsize)) / 2.0F;
        }
_L3:
        if(nlayout == 7 || nlayout == 2)
            iconBox = new Rectangle(box.getLeft() + adj, box.getBottom() + adj, box.getRight() - adj, box.getTop() - adj);
        break MISSING_BLOCK_LABEL_1115;
_L4:
        if(text == null || text.length() == 0 || wt <= 0.0F || ht <= 0.0F)
        {
            nlayout = 2;
            continue; /* Loop/switch isn't completed */
        }
        float nht = box.getHeight() * 0.35F - offX;
        if(nht > 0.0F)
            fsize = calculateFontSize(wt, nht);
        else
            fsize = 4F;
        textX = (box.getWidth() - ufont.getWidthPoint(text, fsize)) / 2.0F;
        textY = offX - ufont.getFontDescriptor(3, fsize);
        iconBox = new Rectangle(box.getLeft() + adj, textY + fsize, box.getRight() - adj, box.getTop() - adj);
        break MISSING_BLOCK_LABEL_1115;
_L5:
        if(text == null || text.length() == 0 || wt <= 0.0F || ht <= 0.0F)
        {
            nlayout = 2;
            continue; /* Loop/switch isn't completed */
        }
        float nht = box.getHeight() * 0.35F - offX;
        if(nht > 0.0F)
            fsize = calculateFontSize(wt, nht);
        else
            fsize = 4F;
        textX = (box.getWidth() - ufont.getWidthPoint(text, fsize)) / 2.0F;
        textY = box.getHeight() - offX - fsize;
        if(textY < offX)
            textY = offX;
        iconBox = new Rectangle(box.getLeft() + adj, box.getBottom() + adj, box.getRight() - adj, textY + ufont.getFontDescriptor(3, fsize));
        break MISSING_BLOCK_LABEL_1115;
_L7:
        if(text == null || text.length() == 0 || wt <= 0.0F || ht <= 0.0F)
        {
            nlayout = 2;
            continue; /* Loop/switch isn't completed */
        }
        float nw = box.getWidth() * 0.35F - offX;
        if(nw > 0.0F)
            fsize = calculateFontSize(wt, nw);
        else
            fsize = 4F;
        if(ufont.getWidthPoint(text, fsize) >= wt)
        {
            nlayout = 1;
            fsize = fontSize;
            continue; /* Loop/switch isn't completed */
        }
        textX = offX + 1.0F;
        textY = (box.getHeight() - ufont.getFontDescriptor(1, fsize)) / 2.0F;
        iconBox = new Rectangle(textX + ufont.getWidthPoint(text, fsize), box.getBottom() + adj, box.getRight() - adj, box.getTop() - adj);
        break MISSING_BLOCK_LABEL_1115;
_L6:
        if(text == null || text.length() == 0 || wt <= 0.0F || ht <= 0.0F)
        {
            nlayout = 2;
            continue; /* Loop/switch isn't completed */
        }
        float nw = box.getWidth() * 0.35F - offX;
        if(nw > 0.0F)
            fsize = calculateFontSize(wt, nw);
        else
            fsize = 4F;
        if(ufont.getWidthPoint(text, fsize) < wt)
            break; /* Loop/switch isn't completed */
        nlayout = 1;
        fsize = fontSize;
        if(true) goto _L9; else goto _L8
_L8:
        textX = box.getWidth() - ufont.getWidthPoint(text, fsize) - offX - 1.0F;
        textY = (box.getHeight() - ufont.getFontDescriptor(1, fsize)) / 2.0F;
        iconBox = new Rectangle(box.getLeft() + adj, box.getBottom() + adj, textX - 1.0F, box.getTop() - adj);
        if(textY < box.getBottom() + offX)
            textY = box.getBottom() + offX;
        if(iconBox != null && (iconBox.getWidth() <= 0.0F || iconBox.getHeight() <= 0.0F))
            iconBox = null;
        boolean haveIcon = false;
        float boundingBoxWidth = 0.0F;
        float boundingBoxHeight = 0.0F;
        PdfArray matrix = null;
        if(iconBox != null)
            if(image != null)
            {
                tp = new PdfTemplate(writer);
                tp.setBoundingBox(new Rectangle(image));
                writer.addDirectTemplateSimple(tp, PdfName.FRM);
                tp.addImage(image, image.getWidth(), 0.0F, 0.0F, image.getHeight(), 0.0F, 0.0F);
                haveIcon = true;
                boundingBoxWidth = tp.getBoundingBox().getWidth();
                boundingBoxHeight = tp.getBoundingBox().getHeight();
            } else
            if(template != null)
            {
                tp = new PdfTemplate(writer);
                tp.setBoundingBox(new Rectangle(template.getWidth(), template.getHeight()));
                writer.addDirectTemplateSimple(tp, PdfName.FRM);
                tp.addTemplate(template, template.getBoundingBox().getLeft(), template.getBoundingBox().getBottom());
                haveIcon = true;
                boundingBoxWidth = tp.getBoundingBox().getWidth();
                boundingBoxHeight = tp.getBoundingBox().getHeight();
            } else
            if(iconReference != null)
            {
                PdfDictionary dic = (PdfDictionary)PdfReader.getPdfObject(iconReference);
                if(dic != null)
                {
                    Rectangle r2 = PdfReader.getNormalizedRectangle(dic.getAsArray(PdfName.BBOX));
                    matrix = dic.getAsArray(PdfName.MATRIX);
                    haveIcon = true;
                    boundingBoxWidth = r2.getWidth();
                    boundingBoxHeight = r2.getHeight();
                }
            }
        if(haveIcon)
        {
            float icx = iconBox.getWidth() / boundingBoxWidth;
            float icy = iconBox.getHeight() / boundingBoxHeight;
            if(proportionalIcon)
            {
                switch(scaleIcon)
                {
                case 3: // '\003'
                    icx = Math.min(icx, icy);
                    icx = Math.min(icx, 1.0F);
                    break;

                case 4: // '\004'
                    icx = Math.min(icx, icy);
                    icx = Math.max(icx, 1.0F);
                    break;

                case 2: // '\002'
                    icx = 1.0F;
                    break;

                default:
                    icx = Math.min(icx, icy);
                    break;
                }
                icy = icx;
            } else
            {
                switch(scaleIcon)
                {
                case 3: // '\003'
                    icx = Math.min(icx, 1.0F);
                    icy = Math.min(icy, 1.0F);
                    break;

                case 4: // '\004'
                    icx = Math.max(icx, 1.0F);
                    icy = Math.max(icy, 1.0F);
                    break;

                case 2: // '\002'
                    icx = icy = 1.0F;
                    break;
                }
            }
            float xpos = iconBox.getLeft() + (iconBox.getWidth() - boundingBoxWidth * icx) * iconHorizontalAdjustment;
            float ypos = iconBox.getBottom() + (iconBox.getHeight() - boundingBoxHeight * icy) * iconVerticalAdjustment;
            app.saveState();
            app.rectangle(iconBox.getLeft(), iconBox.getBottom(), iconBox.getWidth(), iconBox.getHeight());
            app.clip();
            app.newPath();
            if(tp != null)
            {
                app.addTemplate(tp, icx, 0.0F, 0.0F, icy, xpos, ypos);
            } else
            {
                float cox = 0.0F;
                float coy = 0.0F;
                if(matrix != null && matrix.size() == 6)
                {
                    PdfNumber nm = matrix.getAsNumber(4);
                    if(nm != null)
                        cox = nm.floatValue();
                    nm = matrix.getAsNumber(5);
                    if(nm != null)
                        coy = nm.floatValue();
                }
                app.addTemplateReference(iconReference, PdfName.FRM, icx, 0.0F, 0.0F, icy, xpos - cox * icx, ypos - coy * icy);
            }
            app.restoreState();
        }
        if(!Float.isNaN(textX))
        {
            app.saveState();
            app.rectangle(offX, offX, box.getWidth() - 2.0F * offX, box.getHeight() - 2.0F * offX);
            app.clip();
            app.newPath();
            if(textColor == null)
                app.resetGrayFill();
            else
                app.setColorFill(textColor);
            app.beginText();
            app.setFontAndSize(ufont, fsize);
            app.setTextMatrix(textX, textY);
            app.showText(text);
            app.endText();
            app.restoreState();
        }
        return app;
    }

    public PdfFormField getField()
        throws IOException, DocumentException
    {
        PdfFormField field = PdfFormField.createPushButton(writer);
        field.setWidget(box, PdfAnnotation.HIGHLIGHT_INVERT);
        if(fieldName != null)
        {
            field.setFieldName(fieldName);
            if((options & 1) != 0)
                field.setFieldFlags(1);
            if((options & 2) != 0)
                field.setFieldFlags(2);
        }
        if(text != null)
            field.setMKNormalCaption(text);
        if(rotation != 0)
            field.setMKRotation(rotation);
        field.setBorderStyle(new PdfBorderDictionary(borderWidth, borderStyle, new PdfDashPattern(3F)));
        PdfAppearance tpa = getAppearance();
        field.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, tpa);
        PdfAppearance da = (PdfAppearance)tpa.getDuplicate();
        da.setFontAndSize(getRealFont(), fontSize);
        if(textColor == null)
            da.setGrayFill(0.0F);
        else
            da.setColorFill(textColor);
        field.setDefaultAppearanceString(da);
        if(borderColor != null)
            field.setMKBorderColor(borderColor);
        if(backgroundColor != null)
            field.setMKBackgroundColor(backgroundColor);
        switch(visibility)
        {
        case 1: // '\001'
            field.setFlags(6);
            break;

        case 3: // '\003'
            field.setFlags(36);
            break;

        default:
            field.setFlags(4);
            break;

        case 2: // '\002'
            break;
        }
        if(tp != null)
            field.setMKNormalIcon(tp);
        field.setMKTextPosition(layout - 1);
        PdfName scale = PdfName.A;
        if(scaleIcon == 3)
            scale = PdfName.B;
        else
        if(scaleIcon == 4)
            scale = PdfName.S;
        else
        if(scaleIcon == 2)
            scale = PdfName.N;
        field.setMKIconFit(scale, proportionalIcon ? PdfName.P : PdfName.A, iconHorizontalAdjustment, iconVerticalAdjustment, iconFitToBounds);
        return field;
    }

    public boolean isIconFitToBounds()
    {
        return iconFitToBounds;
    }

    public void setIconFitToBounds(boolean iconFitToBounds)
    {
        this.iconFitToBounds = iconFitToBounds;
    }

    public PRIndirectReference getIconReference()
    {
        return iconReference;
    }

    public void setIconReference(PRIndirectReference iconReference)
    {
        this.iconReference = iconReference;
    }

    public static final int LAYOUT_LABEL_ONLY = 1;
    public static final int LAYOUT_ICON_ONLY = 2;
    public static final int LAYOUT_ICON_TOP_LABEL_BOTTOM = 3;
    public static final int LAYOUT_LABEL_TOP_ICON_BOTTOM = 4;
    public static final int LAYOUT_ICON_LEFT_LABEL_RIGHT = 5;
    public static final int LAYOUT_LABEL_LEFT_ICON_RIGHT = 6;
    public static final int LAYOUT_LABEL_OVER_ICON = 7;
    public static final int SCALE_ICON_ALWAYS = 1;
    public static final int SCALE_ICON_NEVER = 2;
    public static final int SCALE_ICON_IS_TOO_BIG = 3;
    public static final int SCALE_ICON_IS_TOO_SMALL = 4;
    private int layout;
    private Image image;
    private PdfTemplate template;
    private int scaleIcon;
    private boolean proportionalIcon;
    private float iconVerticalAdjustment;
    private float iconHorizontalAdjustment;
    private boolean iconFitToBounds;
    private PdfTemplate tp;
    private PRIndirectReference iconReference;
}
