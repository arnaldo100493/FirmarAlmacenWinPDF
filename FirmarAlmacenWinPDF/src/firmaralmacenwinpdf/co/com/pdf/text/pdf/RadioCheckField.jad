// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadioCheckField.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf:
//            BaseField, PdfBorderDictionary, PdfDashPattern, PdfAppearance, 
//            BaseFont, PdfFormField, PdfAnnotation, PdfWriter

public class RadioCheckField extends BaseField
{

    public RadioCheckField(PdfWriter writer, Rectangle box, String fieldName, String onValue)
    {
        super(writer, box, fieldName);
        setOnValue(onValue);
        setCheckType(2);
    }

    public int getCheckType()
    {
        return checkType;
    }

    public void setCheckType(int checkType)
    {
        if(checkType < 1 || checkType > 6)
            checkType = 2;
        this.checkType = checkType;
        setText(typeChars[checkType - 1]);
        try
        {
            setFont(BaseFont.createFont("ZapfDingbats", "Cp1252", false));
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public String getOnValue()
    {
        return onValue;
    }

    public void setOnValue(String onValue)
    {
        this.onValue = onValue;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public PdfAppearance getAppearance(boolean isRadio, boolean on)
        throws IOException, DocumentException
    {
        if(isRadio && checkType == 2)
            return getAppearanceRadioCircle(on);
        PdfAppearance app = getBorderAppearance();
        if(!on)
            return app;
        BaseFont ufont = getRealFont();
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
        float offX = Math.min(bw2, offsetX);
        float wt = box.getWidth() - 2.0F * offX;
        float ht = box.getHeight() - 2.0F * offX;
        float fsize = fontSize;
        if(fsize == 0.0F)
        {
            float bw = ufont.getWidthPoint(text, 1.0F);
            if(bw == 0.0F)
                fsize = 12F;
            else
                fsize = wt / bw;
            float nfsize = h / ufont.getFontDescriptor(1, 1.0F);
            fsize = Math.min(fsize, nfsize);
        }
        app.saveState();
        app.rectangle(offX, offX, wt, ht);
        app.clip();
        app.newPath();
        if(textColor == null)
            app.resetGrayFill();
        else
            app.setColorFill(textColor);
        app.beginText();
        app.setFontAndSize(ufont, fsize);
        app.setTextMatrix((box.getWidth() - ufont.getWidthPoint(text, fsize)) / 2.0F, (box.getHeight() - ufont.getAscentPoint(text, fsize)) / 2.0F);
        app.showText(text);
        app.endText();
        app.restoreState();
        return app;
    }

    public PdfAppearance getAppearanceRadioCircle(boolean on)
    {
        PdfAppearance app = PdfAppearance.createAppearance(writer, this.box.getWidth(), this.box.getHeight());
        switch(rotation)
        {
        case 90: // 'Z'
            app.setMatrix(0.0F, 1.0F, -1F, 0.0F, this.box.getHeight(), 0.0F);
            break;

        case 180: 
            app.setMatrix(-1F, 0.0F, 0.0F, -1F, this.box.getWidth(), this.box.getHeight());
            break;

        case 270: 
            app.setMatrix(0.0F, -1F, 1.0F, 0.0F, 0.0F, this.box.getWidth());
            break;
        }
        Rectangle box = new Rectangle(app.getBoundingBox());
        float cx = box.getWidth() / 2.0F;
        float cy = box.getHeight() / 2.0F;
        float r = (Math.min(box.getWidth(), box.getHeight()) - borderWidth) / 2.0F;
        if(r <= 0.0F)
            return app;
        if(backgroundColor != null)
        {
            app.setColorFill(backgroundColor);
            app.circle(cx, cy, r + borderWidth / 2.0F);
            app.fill();
        }
        if(borderWidth > 0.0F && borderColor != null)
        {
            app.setLineWidth(borderWidth);
            app.setColorStroke(borderColor);
            app.circle(cx, cy, r);
            app.stroke();
        }
        if(on)
        {
            if(textColor == null)
                app.resetGrayFill();
            else
                app.setColorFill(textColor);
            app.circle(cx, cy, r / 2.0F);
            app.fill();
        }
        return app;
    }

    public PdfFormField getRadioGroup(boolean noToggleToOff, boolean radiosInUnison)
    {
        PdfFormField field = PdfFormField.createRadioButton(writer, noToggleToOff);
        if(radiosInUnison)
            field.setFieldFlags(0x2000000);
        field.setFieldName(fieldName);
        if((options & 1) != 0)
            field.setFieldFlags(1);
        if((options & 2) != 0)
            field.setFieldFlags(2);
        field.setValueAsName(checked ? onValue : "Off");
        return field;
    }

    public PdfFormField getRadioField()
        throws IOException, DocumentException
    {
        return getField(true);
    }

    public PdfFormField getCheckField()
        throws IOException, DocumentException
    {
        return getField(false);
    }

    protected PdfFormField getField(boolean isRadio)
        throws IOException, DocumentException
    {
        PdfFormField field = null;
        if(isRadio)
            field = PdfFormField.createEmpty(writer);
        else
            field = PdfFormField.createCheckBox(writer);
        field.setWidget(box, PdfAnnotation.HIGHLIGHT_INVERT);
        if(!isRadio)
        {
            if(!"Yes".equals(onValue))
                throw new DocumentException(MessageLocalization.getComposedMessage("1.is.not.a.valid.name.for.checkbox.appearance", new Object[] {
                    onValue
                }));
            field.setFieldName(fieldName);
            if((options & 1) != 0)
                field.setFieldFlags(1);
            if((options & 2) != 0)
                field.setFieldFlags(2);
            field.setValueAsName(checked ? onValue : "Off");
            setCheckType(checkType);
        }
        if(text != null)
            field.setMKNormalCaption(text);
        if(rotation != 0)
            field.setMKRotation(rotation);
        field.setBorderStyle(new PdfBorderDictionary(borderWidth, borderStyle, new PdfDashPattern(3F)));
        PdfAppearance tpon = getAppearance(isRadio, true);
        PdfAppearance tpoff = getAppearance(isRadio, false);
        field.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, onValue, tpon);
        field.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, "Off", tpoff);
        field.setAppearanceState(checked ? onValue : "Off");
        PdfAppearance da = (PdfAppearance)tpon.getDuplicate();
        BaseFont realFont = getRealFont();
        if(realFont != null)
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
        return field;
    }

    public static final int TYPE_CHECK = 1;
    public static final int TYPE_CIRCLE = 2;
    public static final int TYPE_CROSS = 3;
    public static final int TYPE_DIAMOND = 4;
    public static final int TYPE_SQUARE = 5;
    public static final int TYPE_STAR = 6;
    protected static String typeChars[] = {
        "4", "l", "8", "u", "n", "H"
    };
    protected int checkType;
    private String onValue;
    private boolean checked;

}
