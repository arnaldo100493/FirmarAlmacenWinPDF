// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseField.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfName, PdfAppearance, BaseFont, PdfDictionary, 
//            PdfCopyFieldsImp, PdfWriter

public abstract class BaseField
{

    public BaseField(PdfWriter writer, Rectangle box, String fieldName)
    {
        borderWidth = 1.0F;
        borderStyle = 0;
        fontSize = 0.0F;
        alignment = 0;
        rotation = 0;
        this.writer = writer;
        setBox(box);
        this.fieldName = fieldName;
    }

    protected BaseFont getRealFont()
        throws IOException, DocumentException
    {
        if(font == null)
            return BaseFont.createFont("Helvetica", "Cp1252", false);
        else
            return font;
    }

    protected PdfAppearance getBorderAppearance()
    {
        PdfAppearance app = PdfAppearance.createAppearance(writer, box.getWidth(), box.getHeight());
        switch(rotation)
        {
        case 90: // 'Z'
            app.setMatrix(0.0F, 1.0F, -1F, 0.0F, box.getHeight(), 0.0F);
            break;

        case 180: 
            app.setMatrix(-1F, 0.0F, 0.0F, -1F, box.getWidth(), box.getHeight());
            break;

        case 270: 
            app.setMatrix(0.0F, -1F, 1.0F, 0.0F, 0.0F, box.getWidth());
            break;
        }
        app.saveState();
        if(backgroundColor != null)
        {
            app.setColorFill(backgroundColor);
            app.rectangle(0.0F, 0.0F, box.getWidth(), box.getHeight());
            app.fill();
        }
        if(borderStyle == 4)
        {
            if(borderWidth != 0.0F && borderColor != null)
            {
                app.setColorStroke(borderColor);
                app.setLineWidth(borderWidth);
                app.moveTo(0.0F, borderWidth / 2.0F);
                app.lineTo(box.getWidth(), borderWidth / 2.0F);
                app.stroke();
            }
        } else
        if(borderStyle == 2)
        {
            if(borderWidth != 0.0F && borderColor != null)
            {
                app.setColorStroke(borderColor);
                app.setLineWidth(borderWidth);
                app.rectangle(borderWidth / 2.0F, borderWidth / 2.0F, box.getWidth() - borderWidth, box.getHeight() - borderWidth);
                app.stroke();
            }
            BaseColor actual = backgroundColor;
            if(actual == null)
                actual = BaseColor.WHITE;
            app.setGrayFill(1.0F);
            drawTopFrame(app);
            app.setColorFill(actual.darker());
            drawBottomFrame(app);
        } else
        if(borderStyle == 3)
        {
            if(borderWidth != 0.0F && borderColor != null)
            {
                app.setColorStroke(borderColor);
                app.setLineWidth(borderWidth);
                app.rectangle(borderWidth / 2.0F, borderWidth / 2.0F, box.getWidth() - borderWidth, box.getHeight() - borderWidth);
                app.stroke();
            }
            app.setGrayFill(0.5F);
            drawTopFrame(app);
            app.setGrayFill(0.75F);
            drawBottomFrame(app);
        } else
        if(borderWidth != 0.0F && borderColor != null)
        {
            if(borderStyle == 1)
                app.setLineDash(3F, 0.0F);
            app.setColorStroke(borderColor);
            app.setLineWidth(borderWidth);
            app.rectangle(borderWidth / 2.0F, borderWidth / 2.0F, box.getWidth() - borderWidth, box.getHeight() - borderWidth);
            app.stroke();
            if((options & 0x1000000) != 0 && maxCharacterLength > 1)
            {
                float step = box.getWidth() / (float)maxCharacterLength;
                float yb = borderWidth / 2.0F;
                float yt = box.getHeight() - borderWidth / 2.0F;
                for(int k = 1; k < maxCharacterLength; k++)
                {
                    float x = step * (float)k;
                    app.moveTo(x, yb);
                    app.lineTo(x, yt);
                }

                app.stroke();
            }
        }
        app.restoreState();
        return app;
    }

    protected static ArrayList getHardBreaks(String text)
    {
        ArrayList arr = new ArrayList();
        char cs[] = text.toCharArray();
        int len = cs.length;
        StringBuffer buf = new StringBuffer();
        for(int k = 0; k < len; k++)
        {
            char c = cs[k];
            if(c == '\r')
            {
                if(k + 1 < len && cs[k + 1] == '\n')
                    k++;
                arr.add(buf.toString());
                buf = new StringBuffer();
                continue;
            }
            if(c == '\n')
            {
                arr.add(buf.toString());
                buf = new StringBuffer();
            } else
            {
                buf.append(c);
            }
        }

        arr.add(buf.toString());
        return arr;
    }

    protected static void trimRight(StringBuffer buf)
    {
        int len = buf.length();
        do
        {
            if(len == 0)
                return;
            if(buf.charAt(--len) != ' ')
                return;
            buf.setLength(len);
        } while(true);
    }

    protected static ArrayList breakLines(ArrayList breaks, BaseFont font, float fontSize, float width)
    {
        ArrayList lines = new ArrayList();
        StringBuffer buf = new StringBuffer();
        for(int ck = 0; ck < breaks.size(); ck++)
        {
            buf.setLength(0);
            float w = 0.0F;
            char cs[] = ((String)breaks.get(ck)).toCharArray();
            int len = cs.length;
            int state = 0;
            int lastspace = -1;
            char c = '\0';
            int refk = 0;
            for(int k = 0; k < len; k++)
            {
                c = cs[k];
                switch(state)
                {
                default:
                    break;

                case 0: // '\0'
                    w += font.getWidthPoint(c, fontSize);
                    buf.append(c);
                    if(w > width)
                    {
                        w = 0.0F;
                        if(buf.length() > 1)
                        {
                            k--;
                            buf.setLength(buf.length() - 1);
                        }
                        lines.add(buf.toString());
                        buf.setLength(0);
                        refk = k;
                        if(c == ' ')
                            state = 2;
                        else
                            state = 1;
                    } else
                    if(c != ' ')
                        state = 1;
                    break;

                case 1: // '\001'
                    w += font.getWidthPoint(c, fontSize);
                    buf.append(c);
                    if(c == ' ')
                        lastspace = k;
                    if(w <= width)
                        break;
                    w = 0.0F;
                    if(lastspace >= 0)
                    {
                        k = lastspace;
                        buf.setLength(lastspace - refk);
                        trimRight(buf);
                        lines.add(buf.toString());
                        buf.setLength(0);
                        refk = k;
                        lastspace = -1;
                        state = 2;
                        break;
                    }
                    if(buf.length() > 1)
                    {
                        k--;
                        buf.setLength(buf.length() - 1);
                    }
                    lines.add(buf.toString());
                    buf.setLength(0);
                    refk = k;
                    if(c == ' ')
                        state = 2;
                    break;

                case 2: // '\002'
                    if(c != ' ')
                    {
                        w = 0.0F;
                        k--;
                        state = 1;
                    }
                    break;
                }
            }

            trimRight(buf);
            lines.add(buf.toString());
        }

        return lines;
    }

    private void drawTopFrame(PdfAppearance app)
    {
        app.moveTo(borderWidth, borderWidth);
        app.lineTo(borderWidth, box.getHeight() - borderWidth);
        app.lineTo(box.getWidth() - borderWidth, box.getHeight() - borderWidth);
        app.lineTo(box.getWidth() - 2.0F * borderWidth, box.getHeight() - 2.0F * borderWidth);
        app.lineTo(2.0F * borderWidth, box.getHeight() - 2.0F * borderWidth);
        app.lineTo(2.0F * borderWidth, 2.0F * borderWidth);
        app.lineTo(borderWidth, borderWidth);
        app.fill();
    }

    private void drawBottomFrame(PdfAppearance app)
    {
        app.moveTo(borderWidth, borderWidth);
        app.lineTo(box.getWidth() - borderWidth, borderWidth);
        app.lineTo(box.getWidth() - borderWidth, box.getHeight() - borderWidth);
        app.lineTo(box.getWidth() - 2.0F * borderWidth, box.getHeight() - 2.0F * borderWidth);
        app.lineTo(box.getWidth() - 2.0F * borderWidth, 2.0F * borderWidth);
        app.lineTo(2.0F * borderWidth, 2.0F * borderWidth);
        app.lineTo(borderWidth, borderWidth);
        app.fill();
    }

    public float getBorderWidth()
    {
        return borderWidth;
    }

    public void setBorderWidth(float borderWidth)
    {
        this.borderWidth = borderWidth;
    }

    public int getBorderStyle()
    {
        return borderStyle;
    }

    public void setBorderStyle(int borderStyle)
    {
        this.borderStyle = borderStyle;
    }

    public BaseColor getBorderColor()
    {
        return borderColor;
    }

    public void setBorderColor(BaseColor borderColor)
    {
        this.borderColor = borderColor;
    }

    public BaseColor getBackgroundColor()
    {
        return backgroundColor;
    }

    public void setBackgroundColor(BaseColor backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public BaseColor getTextColor()
    {
        return textColor;
    }

    public void setTextColor(BaseColor textColor)
    {
        this.textColor = textColor;
    }

    public BaseFont getFont()
    {
        return font;
    }

    public void setFont(BaseFont font)
    {
        this.font = font;
    }

    public float getFontSize()
    {
        return fontSize;
    }

    public void setFontSize(float fontSize)
    {
        this.fontSize = fontSize;
    }

    public int getAlignment()
    {
        return alignment;
    }

    public void setAlignment(int alignment)
    {
        this.alignment = alignment;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public Rectangle getBox()
    {
        return box;
    }

    public void setBox(Rectangle box)
    {
        if(box == null)
        {
            this.box = null;
        } else
        {
            this.box = new Rectangle(box);
            this.box.normalize();
        }
    }

    public int getRotation()
    {
        return rotation;
    }

    public void setRotation(int rotation)
    {
        if(rotation % 90 != 0)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("rotation.must.be.a.multiple.of.90", new Object[0]));
        rotation %= 360;
        if(rotation < 0)
            rotation += 360;
        this.rotation = rotation;
    }

    public void setRotationFromPage(Rectangle page)
    {
        setRotation(page.getRotation());
    }

    public int getVisibility()
    {
        return visibility;
    }

    public void setVisibility(int visibility)
    {
        this.visibility = visibility;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    public int getOptions()
    {
        return options;
    }

    public void setOptions(int options)
    {
        this.options = options;
    }

    public int getMaxCharacterLength()
    {
        return maxCharacterLength;
    }

    public void setMaxCharacterLength(int maxCharacterLength)
    {
        this.maxCharacterLength = maxCharacterLength;
    }

    public PdfWriter getWriter()
    {
        return writer;
    }

    public void setWriter(PdfWriter writer)
    {
        this.writer = writer;
    }

    public static void moveFields(PdfDictionary from, PdfDictionary to)
    {
        Iterator i = from.getKeys().iterator();
        do
        {
            if(!i.hasNext())
                break;
            PdfName key = (PdfName)i.next();
            if(fieldKeys.containsKey(key))
            {
                if(to != null)
                    to.put(key, from.get(key));
                i.remove();
            }
        } while(true);
    }

    public static final float BORDER_WIDTH_THIN = 1F;
    public static final float BORDER_WIDTH_MEDIUM = 2F;
    public static final float BORDER_WIDTH_THICK = 3F;
    public static final int VISIBLE = 0;
    public static final int HIDDEN = 1;
    public static final int VISIBLE_BUT_DOES_NOT_PRINT = 2;
    public static final int HIDDEN_BUT_PRINTABLE = 3;
    public static final int READ_ONLY = 1;
    public static final int REQUIRED = 2;
    public static final int MULTILINE = 4096;
    public static final int DO_NOT_SCROLL = 0x800000;
    public static final int PASSWORD = 8192;
    public static final int FILE_SELECTION = 0x100000;
    public static final int DO_NOT_SPELL_CHECK = 0x400000;
    public static final int EDIT = 0x40000;
    public static final int MULTISELECT = 0x200000;
    public static final int COMB = 0x1000000;
    protected float borderWidth;
    protected int borderStyle;
    protected BaseColor borderColor;
    protected BaseColor backgroundColor;
    protected BaseColor textColor;
    protected BaseFont font;
    protected float fontSize;
    protected int alignment;
    protected PdfWriter writer;
    protected String text;
    protected Rectangle box;
    protected int rotation;
    protected int visibility;
    protected String fieldName;
    protected int options;
    protected int maxCharacterLength;
    private static final HashMap fieldKeys;

    static 
    {
        fieldKeys = new HashMap();
        fieldKeys.putAll(PdfCopyFieldsImp.fieldKeys);
        fieldKeys.put(PdfName.T, Integer.valueOf(1));
    }
}
