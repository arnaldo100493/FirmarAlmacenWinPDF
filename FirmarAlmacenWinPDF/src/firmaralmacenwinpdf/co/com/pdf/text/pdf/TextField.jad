// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TextField.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import java.io.IOException;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf:
//            BaseField, FontSelector, BaseFont, ColumnText, 
//            PdfBorderDictionary, PdfDashPattern, PdfAppearance, PdfNumber, 
//            PdfArray, PdfString, PdfFormField, PdfWriter, 
//            GrayColor, PdfAnnotation, PdfName

public class TextField extends BaseField
{

    public TextField(PdfWriter writer, Rectangle box, String fieldName)
    {
        super(writer, box, fieldName);
        choiceSelections = new ArrayList();
    }

    private static boolean checkRTL(String text)
    {
        if(text == null || text.length() == 0)
            return false;
        char cc[] = text.toCharArray();
        for(int k = 0; k < cc.length; k++)
        {
            int c = cc[k];
            if(c >= 1424 && c < 1920)
                return true;
        }

        return false;
    }

    private static void changeFontSize(Phrase p, float size)
    {
        for(int k = 0; k < p.size(); k++)
            ((Chunk)p.get(k)).getFont().setSize(size);

    }

    private Phrase composePhrase(String text, BaseFont ufont, BaseColor color, float fontSize)
    {
        Phrase phrase = null;
        if(extensionFont == null && (substitutionFonts == null || substitutionFonts.isEmpty()))
        {
            phrase = new Phrase(new Chunk(text, new Font(ufont, fontSize, 0, color)));
        } else
        {
            FontSelector fs = new FontSelector();
            fs.addFont(new Font(ufont, fontSize, 0, color));
            if(extensionFont != null)
                fs.addFont(new Font(extensionFont, fontSize, 0, color));
            if(substitutionFonts != null)
            {
                for(int k = 0; k < substitutionFonts.size(); k++)
                    fs.addFont(new Font((BaseFont)substitutionFonts.get(k), fontSize, 0, color));

            }
            phrase = fs.process(text);
        }
        return phrase;
    }

    public static String removeCRLF(String text)
    {
        if(text.indexOf('\n') >= 0 || text.indexOf('\r') >= 0)
        {
            char p[] = text.toCharArray();
            StringBuffer sb = new StringBuffer(p.length);
            for(int k = 0; k < p.length; k++)
            {
                char c = p[k];
                if(c == '\n')
                {
                    sb.append(' ');
                    continue;
                }
                if(c == '\r')
                {
                    sb.append(' ');
                    if(k < p.length - 1 && p[k + 1] == '\n')
                        k++;
                } else
                {
                    sb.append(c);
                }
            }

            return sb.toString();
        } else
        {
            return text;
        }
    }

    public static String obfuscatePassword(String text)
    {
        char pchar[] = new char[text.length()];
        for(int i = 0; i < text.length(); i++)
            pchar[i] = '*';

        return new String(pchar);
    }

    public PdfAppearance getAppearance()
        throws IOException, DocumentException
    {
        PdfAppearance app = getBorderAppearance();
        app.beginVariableText();
        if(text == null || text.length() == 0)
        {
            app.endVariableText();
            return app;
        }
        boolean borderExtra = borderStyle == 2 || borderStyle == 3;
        float h = box.getHeight() - borderWidth * 2.0F - extraMarginTop;
        float bw2 = borderWidth;
        if(borderExtra)
        {
            h -= borderWidth * 2.0F;
            bw2 *= 2.0F;
        }
        float offsetX = Math.max(bw2, 1.0F);
        float offX = Math.min(bw2, offsetX);
        app.saveState();
        app.rectangle(offX, offX, box.getWidth() - 2.0F * offX, box.getHeight() - 2.0F * offX);
        app.clip();
        app.newPath();
        String ptext;
        if((options & 0x2000) != 0)
            ptext = obfuscatePassword(text);
        else
        if((options & 0x1000) == 0)
            ptext = removeCRLF(text);
        else
            ptext = text;
        BaseFont ufont = getRealFont();
        BaseColor fcolor = ((BaseColor) (textColor != null ? textColor : ((BaseColor) (GrayColor.GRAYBLACK))));
        int rtl = checkRTL(ptext) ? 2 : 1;
        float usize = fontSize;
        Phrase phrase = composePhrase(ptext, ufont, fcolor, usize);
        if((options & 0x1000) != 0)
        {
            float width = box.getWidth() - 4F * offsetX - extraMarginLeft;
            float factor = ufont.getFontDescriptor(8, 1.0F) - ufont.getFontDescriptor(6, 1.0F);
            ColumnText ct = new ColumnText(null);
            if(usize == 0.0F)
            {
                usize = h / factor;
                if(usize > 4F)
                {
                    if(usize > 12F)
                        usize = 12F;
                    float step = Math.max((usize - 4F) / 10F, 0.2F);
                    ct.setSimpleColumn(0.0F, -h, width, 0.0F);
                    ct.setAlignment(alignment);
                    ct.setRunDirection(rtl);
                    do
                    {
                        if(usize <= 4F)
                            break;
                        ct.setYLine(0.0F);
                        changeFontSize(phrase, usize);
                        ct.setText(phrase);
                        ct.setLeading(factor * usize);
                        int status = ct.go(true);
                        if((status & 2) == 0)
                            break;
                        usize -= step;
                    } while(true);
                }
                if(usize < 4F)
                    usize = 4F;
            }
            changeFontSize(phrase, usize);
            ct.setCanvas(app);
            float leading = usize * factor;
            float offsetY = (offsetX + h) - ufont.getFontDescriptor(8, usize);
            ct.setSimpleColumn(extraMarginLeft + 2.0F * offsetX, -20000F, box.getWidth() - 2.0F * offsetX, offsetY + leading);
            ct.setLeading(leading);
            ct.setAlignment(alignment);
            ct.setRunDirection(rtl);
            ct.setText(phrase);
            ct.go();
        } else
        {
            if(usize == 0.0F)
            {
                float maxCalculatedSize = h / (ufont.getFontDescriptor(7, 1.0F) - ufont.getFontDescriptor(6, 1.0F));
                changeFontSize(phrase, 1.0F);
                float wd = ColumnText.getWidth(phrase, rtl, 0);
                if(wd == 0.0F)
                    usize = maxCalculatedSize;
                else
                    usize = Math.min(maxCalculatedSize, (box.getWidth() - extraMarginLeft - 4F * offsetX) / wd);
                if(usize < 4F)
                    usize = 4F;
            }
            changeFontSize(phrase, usize);
            float offsetY = offX + (box.getHeight() - 2.0F * offX - ufont.getFontDescriptor(1, usize)) / 2.0F;
            if(offsetY < offX)
                offsetY = offX;
            if(offsetY - offX < -ufont.getFontDescriptor(3, usize))
            {
                float ny = -ufont.getFontDescriptor(3, usize) + offX;
                float dy = box.getHeight() - offX - ufont.getFontDescriptor(1, usize);
                offsetY = Math.min(ny, Math.max(offsetY, dy));
            }
            if((options & 0x1000000) != 0 && maxCharacterLength > 0)
            {
                int textLen = Math.min(maxCharacterLength, ptext.length());
                int position = 0;
                if(alignment == 2)
                    position = maxCharacterLength - textLen;
                else
                if(alignment == 1)
                    position = (maxCharacterLength - textLen) / 2;
                float step = (box.getWidth() - extraMarginLeft) / (float)maxCharacterLength;
                float start = step / 2.0F + (float)position * step;
                if(textColor == null)
                    app.setGrayFill(0.0F);
                else
                    app.setColorFill(textColor);
                app.beginText();
                for(int k = 0; k < phrase.size(); k++)
                {
                    Chunk ck = (Chunk)phrase.get(k);
                    BaseFont bf = ck.getFont().getBaseFont();
                    app.setFontAndSize(bf, usize);
                    StringBuffer sb = ck.append("");
                    for(int j = 0; j < sb.length(); j++)
                    {
                        String c = sb.substring(j, j + 1);
                        float wd = bf.getWidthPoint(c, usize);
                        app.setTextMatrix((extraMarginLeft + start) - wd / 2.0F, offsetY - extraMarginTop);
                        app.showText(c);
                        start += step;
                    }

                }

                app.endText();
            } else
            {
                float x;
                switch(alignment)
                {
                case 2: // '\002'
                    x = (extraMarginLeft + box.getWidth()) - 2.0F * offsetX;
                    break;

                case 1: // '\001'
                    x = extraMarginLeft + box.getWidth() / 2.0F;
                    break;

                default:
                    x = extraMarginLeft + 2.0F * offsetX;
                    break;
                }
                ColumnText.showTextAligned(app, alignment, phrase, x, offsetY - extraMarginTop, 0.0F, rtl, 0);
            }
        }
        app.restoreState();
        app.endVariableText();
        return app;
    }

    PdfAppearance getListAppearance()
        throws IOException, DocumentException
    {
        PdfAppearance app = getBorderAppearance();
        if(choices == null || choices.length == 0)
            return app;
        app.beginVariableText();
        int topChoice = getTopChoice();
        BaseFont ufont = getRealFont();
        float usize = fontSize;
        if(usize == 0.0F)
            usize = 12F;
        boolean borderExtra = borderStyle == 2 || borderStyle == 3;
        float h = box.getHeight() - borderWidth * 2.0F;
        float offsetX = borderWidth;
        if(borderExtra)
        {
            h -= borderWidth * 2.0F;
            offsetX *= 2.0F;
        }
        float leading = ufont.getFontDescriptor(8, usize) - ufont.getFontDescriptor(6, usize);
        int maxFit = (int)(h / leading) + 1;
        int first = 0;
        int last = 0;
        first = topChoice;
        last = first + maxFit;
        if(last > choices.length)
            last = choices.length;
        topFirst = first;
        app.saveState();
        app.rectangle(offsetX, offsetX, box.getWidth() - 2.0F * offsetX, box.getHeight() - 2.0F * offsetX);
        app.clip();
        app.newPath();
        BaseColor fcolor = ((BaseColor) (textColor != null ? textColor : ((BaseColor) (GrayColor.GRAYBLACK))));
        app.setColorFill(new BaseColor(10, 36, 106));
        for(int curVal = 0; curVal < choiceSelections.size(); curVal++)
        {
            int curChoice = ((Integer)choiceSelections.get(curVal)).intValue();
            if(curChoice >= first && curChoice <= last)
            {
                app.rectangle(offsetX, (offsetX + h) - (float)((curChoice - first) + 1) * leading, box.getWidth() - 2.0F * offsetX, leading);
                app.fill();
            }
        }

        float xp = offsetX * 2.0F;
        float yp = (offsetX + h) - ufont.getFontDescriptor(8, usize);
        for(int idx = first; idx < last;)
        {
            String ptext = choices[idx];
            int rtl = checkRTL(ptext) ? 2 : 1;
            ptext = removeCRLF(ptext);
            BaseColor textCol = ((BaseColor) (choiceSelections.contains(Integer.valueOf(idx)) ? ((BaseColor) (GrayColor.GRAYWHITE)) : fcolor));
            Phrase phrase = composePhrase(ptext, ufont, textCol, usize);
            ColumnText.showTextAligned(app, 0, phrase, xp, yp, 0.0F, rtl, 0);
            idx++;
            yp -= leading;
        }

        app.restoreState();
        app.endVariableText();
        return app;
    }

    public PdfFormField getTextField()
        throws IOException, DocumentException
    {
        if(maxCharacterLength <= 0)
            options &= 0xfeffffff;
        if((options & 0x1000000) != 0)
            options &= 0xffffefff;
        PdfFormField field = PdfFormField.createTextField(writer, false, false, maxCharacterLength);
        field.setWidget(box, PdfAnnotation.HIGHLIGHT_INVERT);
        switch(alignment)
        {
        case 1: // '\001'
            field.setQuadding(1);
            break;

        case 2: // '\002'
            field.setQuadding(2);
            break;
        }
        if(rotation != 0)
            field.setMKRotation(rotation);
        if(fieldName != null)
        {
            field.setFieldName(fieldName);
            if(!"".equals(text))
                field.setValueAsString(text);
            if(defaultText != null)
                field.setDefaultValueAsString(defaultText);
            if((options & 1) != 0)
                field.setFieldFlags(1);
            if((options & 2) != 0)
                field.setFieldFlags(2);
            if((options & 0x1000) != 0)
                field.setFieldFlags(4096);
            if((options & 0x800000) != 0)
                field.setFieldFlags(0x800000);
            if((options & 0x2000) != 0)
                field.setFieldFlags(8192);
            if((options & 0x100000) != 0)
                field.setFieldFlags(0x100000);
            if((options & 0x400000) != 0)
                field.setFieldFlags(0x400000);
            if((options & 0x1000000) != 0)
                field.setFieldFlags(0x1000000);
        }
        field.setBorderStyle(new PdfBorderDictionary(borderWidth, borderStyle, new PdfDashPattern(3F)));
        PdfAppearance tp = getAppearance();
        field.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, tp);
        PdfAppearance da = (PdfAppearance)tp.getDuplicate();
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

    public PdfFormField getComboField()
        throws IOException, DocumentException
    {
        return getChoiceField(false);
    }

    public PdfFormField getListField()
        throws IOException, DocumentException
    {
        return getChoiceField(true);
    }

    private int getTopChoice()
    {
        if(choiceSelections == null || choiceSelections.size() == 0)
            return 0;
        Integer firstValue = (Integer)choiceSelections.get(0);
        if(firstValue == null)
            return 0;
        int topChoice = 0;
        if(choices != null)
        {
            topChoice = firstValue.intValue();
            topChoice = Math.min(topChoice, choices.length);
            topChoice = Math.max(0, topChoice);
        }
        return topChoice;
    }

    protected PdfFormField getChoiceField(boolean isList)
        throws IOException, DocumentException
    {
        options &= 0xfeffefff;
        String uchoices[] = choices;
        if(uchoices == null)
            uchoices = new String[0];
        int topChoice = getTopChoice();
        if(uchoices.length > 0 && topChoice >= 0)
            text = uchoices[topChoice];
        if(text == null)
            text = "";
        PdfFormField field = null;
        String mix[][] = (String[][])null;
        if(choiceExports == null)
        {
            if(isList)
                field = PdfFormField.createList(writer, uchoices, topChoice);
            else
                field = PdfFormField.createCombo(writer, (options & 0x40000) != 0, uchoices, topChoice);
        } else
        {
            mix = new String[uchoices.length][2];
            for(int k = 0; k < mix.length; k++)
                mix[k][0] = mix[k][1] = uchoices[k];

            int top = Math.min(uchoices.length, choiceExports.length);
            for(int k = 0; k < top; k++)
                if(choiceExports[k] != null)
                    mix[k][0] = choiceExports[k];

            if(isList)
                field = PdfFormField.createList(writer, mix, topChoice);
            else
                field = PdfFormField.createCombo(writer, (options & 0x40000) != 0, mix, topChoice);
        }
        field.setWidget(box, PdfAnnotation.HIGHLIGHT_INVERT);
        if(rotation != 0)
            field.setMKRotation(rotation);
        if(fieldName != null)
        {
            field.setFieldName(fieldName);
            if(uchoices.length > 0)
                if(mix != null)
                {
                    if(choiceSelections.size() < 2)
                    {
                        field.setValueAsString(mix[topChoice][0]);
                        field.setDefaultValueAsString(mix[topChoice][0]);
                    } else
                    {
                        writeMultipleValues(field, mix);
                    }
                } else
                if(choiceSelections.size() < 2)
                {
                    field.setValueAsString(text);
                    field.setDefaultValueAsString(text);
                } else
                {
                    writeMultipleValues(field, (String[][])null);
                }
            if((options & 1) != 0)
                field.setFieldFlags(1);
            if((options & 2) != 0)
                field.setFieldFlags(2);
            if((options & 0x400000) != 0)
                field.setFieldFlags(0x400000);
            if((options & 0x200000) != 0)
                field.setFieldFlags(0x200000);
        }
        field.setBorderStyle(new PdfBorderDictionary(borderWidth, borderStyle, new PdfDashPattern(3F)));
        PdfAppearance tp;
        if(isList)
        {
            tp = getListAppearance();
            if(topFirst > 0)
                field.put(PdfName.TI, new PdfNumber(topFirst));
        } else
        {
            tp = getAppearance();
        }
        field.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, tp);
        PdfAppearance da = (PdfAppearance)tp.getDuplicate();
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

    private void writeMultipleValues(PdfFormField field, String mix[][])
    {
        PdfArray indexes = new PdfArray();
        PdfArray values = new PdfArray();
        for(int i = 0; i < choiceSelections.size(); i++)
        {
            int idx = ((Integer)choiceSelections.get(i)).intValue();
            indexes.add(new PdfNumber(idx));
            if(mix != null)
            {
                values.add(new PdfString(mix[idx][0]));
                continue;
            }
            if(choices != null)
                values.add(new PdfString(choices[idx]));
        }

        field.put(PdfName.V, values);
        field.put(PdfName.I, indexes);
    }

    public String getDefaultText()
    {
        return defaultText;
    }

    public void setDefaultText(String defaultText)
    {
        this.defaultText = defaultText;
    }

    public String[] getChoices()
    {
        return choices;
    }

    public void setChoices(String choices[])
    {
        this.choices = choices;
    }

    public String[] getChoiceExports()
    {
        return choiceExports;
    }

    public void setChoiceExports(String choiceExports[])
    {
        this.choiceExports = choiceExports;
    }

    public int getChoiceSelection()
    {
        return getTopChoice();
    }

    public ArrayList getChoiceSelections()
    {
        return choiceSelections;
    }

    public void setChoiceSelection(int choiceSelection)
    {
        choiceSelections = new ArrayList();
        choiceSelections.add(Integer.valueOf(choiceSelection));
    }

    public void addChoiceSelection(int selection)
    {
        if((options & 0x200000) != 0)
            choiceSelections.add(Integer.valueOf(selection));
    }

    public void setChoiceSelections(ArrayList selections)
    {
        if(selections != null)
        {
            choiceSelections = new ArrayList(selections);
            if(choiceSelections.size() > 1 && (options & 0x200000) == 0)
                for(; choiceSelections.size() > 1; choiceSelections.remove(1));
        } else
        {
            choiceSelections.clear();
        }
    }

    int getTopFirst()
    {
        return topFirst;
    }

    public void setExtraMargin(float extraMarginLeft, float extraMarginTop)
    {
        this.extraMarginLeft = extraMarginLeft;
        this.extraMarginTop = extraMarginTop;
    }

    public ArrayList getSubstitutionFonts()
    {
        return substitutionFonts;
    }

    public void setSubstitutionFonts(ArrayList substitutionFonts)
    {
        this.substitutionFonts = substitutionFonts;
    }

    public BaseFont getExtensionFont()
    {
        return extensionFont;
    }

    public void setExtensionFont(BaseFont extensionFont)
    {
        this.extensionFont = extensionFont;
    }

    private String defaultText;
    private String choices[];
    private String choiceExports[];
    private ArrayList choiceSelections;
    private int topFirst;
    private float extraMarginLeft;
    private float extraMarginTop;
    private ArrayList substitutionFonts;
    private BaseFont extensionFont;
}
