// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfFormField.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.Rectangle;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfAnnotation, PdfRectangle, PdfNumber, PdfArray, 
//            PdfString, PdfName, PdfDictionary, PdfTemplate, 
//            PdfWriter, PdfStamperImp, PdfReader, PdfAction, 
//            PdfSignature

public class PdfFormField extends PdfAnnotation
{

    public PdfFormField(PdfWriter writer, float llx, float lly, float urx, float ury, PdfAction action)
    {
        super(writer, llx, lly, urx, ury, action);
        put(PdfName.TYPE, PdfName.ANNOT);
        put(PdfName.SUBTYPE, PdfName.WIDGET);
        annotation = true;
    }

    protected PdfFormField(PdfWriter writer)
    {
        super(writer, null);
        form = true;
        annotation = false;
    }

    public void setWidget(Rectangle rect, PdfName highlight)
    {
        put(PdfName.TYPE, PdfName.ANNOT);
        put(PdfName.SUBTYPE, PdfName.WIDGET);
        put(PdfName.RECT, new PdfRectangle(rect));
        annotation = true;
        if(highlight != null && !highlight.equals(HIGHLIGHT_INVERT))
            put(PdfName.H, highlight);
    }

    public static PdfFormField createEmpty(PdfWriter writer)
    {
        PdfFormField field = new PdfFormField(writer);
        return field;
    }

    public void setButton(int flags)
    {
        put(PdfName.FT, PdfName.BTN);
        if(flags != 0)
            put(PdfName.FF, new PdfNumber(flags));
    }

    protected static PdfFormField createButton(PdfWriter writer, int flags)
    {
        PdfFormField field = new PdfFormField(writer);
        field.setButton(flags);
        return field;
    }

    public static PdfFormField createPushButton(PdfWriter writer)
    {
        return createButton(writer, 0x10000);
    }

    public static PdfFormField createCheckBox(PdfWriter writer)
    {
        return createButton(writer, 0);
    }

    public static PdfFormField createRadioButton(PdfWriter writer, boolean noToggleToOff)
    {
        return createButton(writer, 32768 + (noToggleToOff ? 16384 : 0));
    }

    public static PdfFormField createTextField(PdfWriter writer, boolean multiline, boolean password, int maxLen)
    {
        PdfFormField field = new PdfFormField(writer);
        field.put(PdfName.FT, PdfName.TX);
        int flags = multiline ? 4096 : 0;
        flags += password ? 8192 : 0;
        field.put(PdfName.FF, new PdfNumber(flags));
        if(maxLen > 0)
            field.put(PdfName.MAXLEN, new PdfNumber(maxLen));
        return field;
    }

    protected static PdfFormField createChoice(PdfWriter writer, int flags, PdfArray options, int topIndex)
    {
        PdfFormField field = new PdfFormField(writer);
        field.put(PdfName.FT, PdfName.CH);
        field.put(PdfName.FF, new PdfNumber(flags));
        field.put(PdfName.OPT, options);
        if(topIndex > 0)
            field.put(PdfName.TI, new PdfNumber(topIndex));
        return field;
    }

    public static PdfFormField createList(PdfWriter writer, String options[], int topIndex)
    {
        return createChoice(writer, 0, processOptions(options), topIndex);
    }

    public static PdfFormField createList(PdfWriter writer, String options[][], int topIndex)
    {
        return createChoice(writer, 0, processOptions(options), topIndex);
    }

    public static PdfFormField createCombo(PdfWriter writer, boolean edit, String options[], int topIndex)
    {
        return createChoice(writer, 0x20000 + (edit ? 0x40000 : 0), processOptions(options), topIndex);
    }

    public static PdfFormField createCombo(PdfWriter writer, boolean edit, String options[][], int topIndex)
    {
        return createChoice(writer, 0x20000 + (edit ? 0x40000 : 0), processOptions(options), topIndex);
    }

    protected static PdfArray processOptions(String options[])
    {
        PdfArray array = new PdfArray();
        for(int k = 0; k < options.length; k++)
            array.add(new PdfString(options[k], "UnicodeBig"));

        return array;
    }

    protected static PdfArray processOptions(String options[][])
    {
        PdfArray array = new PdfArray();
        for(int k = 0; k < options.length; k++)
        {
            String subOption[] = options[k];
            PdfArray ar2 = new PdfArray(new PdfString(subOption[0], "UnicodeBig"));
            ar2.add(new PdfString(subOption[1], "UnicodeBig"));
            array.add(ar2);
        }

        return array;
    }

    public static PdfFormField createSignature(PdfWriter writer)
    {
        PdfFormField field = new PdfFormField(writer);
        field.put(PdfName.FT, PdfName.SIG);
        return field;
    }

    public PdfFormField getParent()
    {
        return parent;
    }

    public void addKid(PdfFormField field)
    {
        field.parent = this;
        if(kids == null)
            kids = new ArrayList();
        kids.add(field);
    }

    public ArrayList getKids()
    {
        return kids;
    }

    public int setFieldFlags(int flags)
    {
        PdfNumber obj = (PdfNumber)get(PdfName.FF);
        int old;
        if(obj == null)
            old = 0;
        else
            old = obj.intValue();
        int v = old | flags;
        put(PdfName.FF, new PdfNumber(v));
        return old;
    }

    public void setValueAsString(String s)
    {
        put(PdfName.V, new PdfString(s, "UnicodeBig"));
    }

    public void setValueAsName(String s)
    {
        put(PdfName.V, new PdfName(s));
    }

    public void setValue(PdfSignature sig)
    {
        put(PdfName.V, sig);
    }

    public void setRichValue(String rv)
    {
        put(PdfName.RV, new PdfString(rv));
    }

    public void setDefaultValueAsString(String s)
    {
        put(PdfName.DV, new PdfString(s, "UnicodeBig"));
    }

    public void setDefaultValueAsName(String s)
    {
        put(PdfName.DV, new PdfName(s));
    }

    public void setFieldName(String s)
    {
        if(s != null)
            put(PdfName.T, new PdfString(s, "UnicodeBig"));
    }

    public void setUserName(String s)
    {
        put(PdfName.TU, new PdfString(s, "UnicodeBig"));
    }

    public void setMappingName(String s)
    {
        put(PdfName.TM, new PdfString(s, "UnicodeBig"));
    }

    public void setQuadding(int v)
    {
        put(PdfName.Q, new PdfNumber(v));
    }

    static void mergeResources(PdfDictionary result, PdfDictionary source, PdfStamperImp writer)
    {
        PdfDictionary dic = null;
        PdfDictionary res = null;
        PdfName target = null;
        for(int k = 0; k < mergeTarget.length; k++)
        {
            target = mergeTarget[k];
            PdfDictionary pdfDict = source.getAsDict(target);
            if((dic = pdfDict) == null)
                continue;
            if((res = (PdfDictionary)PdfReader.getPdfObject(result.get(target), result)) == null)
                res = new PdfDictionary();
            res.mergeDifferent(dic);
            result.put(target, res);
            if(writer != null)
                writer.markUsed(res);
        }

    }

    static void mergeResources(PdfDictionary result, PdfDictionary source)
    {
        mergeResources(result, source, null);
    }

    public void setUsed()
    {
        used = true;
        if(parent != null)
            put(PdfName.PARENT, parent.getIndirectReference());
        if(kids != null)
        {
            PdfArray array = new PdfArray();
            for(int k = 0; k < kids.size(); k++)
                array.add(((PdfFormField)kids.get(k)).getIndirectReference());

            put(PdfName.KIDS, array);
        }
        if(templates == null)
            return;
        PdfDictionary dic = new PdfDictionary();
        PdfTemplate template;
        for(Iterator i$ = templates.iterator(); i$.hasNext(); mergeResources(dic, (PdfDictionary)template.getResources()))
            template = (PdfTemplate)i$.next();

        put(PdfName.DR, dic);
    }

    public static PdfAnnotation shallowDuplicate(PdfAnnotation annot)
    {
        PdfAnnotation dup;
        if(annot.isForm())
        {
            dup = new PdfFormField(annot.writer);
            PdfFormField dupField = (PdfFormField)dup;
            PdfFormField srcField = (PdfFormField)annot;
            dupField.parent = srcField.parent;
            dupField.kids = srcField.kids;
        } else
        {
            dup = new PdfAnnotation(annot.writer, null);
        }
        dup.merge(annot);
        dup.form = annot.form;
        dup.annotation = annot.annotation;
        dup.templates = annot.templates;
        return dup;
    }

    public static final int FF_READ_ONLY = 1;
    public static final int FF_REQUIRED = 2;
    public static final int FF_NO_EXPORT = 4;
    public static final int FF_NO_TOGGLE_TO_OFF = 16384;
    public static final int FF_RADIO = 32768;
    public static final int FF_PUSHBUTTON = 0x10000;
    public static final int FF_MULTILINE = 4096;
    public static final int FF_PASSWORD = 8192;
    public static final int FF_COMBO = 0x20000;
    public static final int FF_EDIT = 0x40000;
    public static final int FF_FILESELECT = 0x100000;
    public static final int FF_MULTISELECT = 0x200000;
    public static final int FF_DONOTSPELLCHECK = 0x400000;
    public static final int FF_DONOTSCROLL = 0x800000;
    public static final int FF_COMB = 0x1000000;
    public static final int FF_RADIOSINUNISON = 0x2000000;
    public static final int FF_RICHTEXT = 0x2000000;
    public static final int Q_LEFT = 0;
    public static final int Q_CENTER = 1;
    public static final int Q_RIGHT = 2;
    public static final int MK_NO_ICON = 0;
    public static final int MK_NO_CAPTION = 1;
    public static final int MK_CAPTION_BELOW = 2;
    public static final int MK_CAPTION_ABOVE = 3;
    public static final int MK_CAPTION_RIGHT = 4;
    public static final int MK_CAPTION_LEFT = 5;
    public static final int MK_CAPTION_OVERLAID = 6;
    public static final PdfName IF_SCALE_ALWAYS;
    public static final PdfName IF_SCALE_BIGGER;
    public static final PdfName IF_SCALE_SMALLER;
    public static final PdfName IF_SCALE_NEVER;
    public static final PdfName IF_SCALE_ANAMORPHIC;
    public static final PdfName IF_SCALE_PROPORTIONAL;
    public static final boolean MULTILINE = true;
    public static final boolean SINGLELINE = false;
    public static final boolean PLAINTEXT = false;
    public static final boolean PASSWORD = true;
    static PdfName mergeTarget[];
    protected PdfFormField parent;
    protected ArrayList kids;

    static 
    {
        IF_SCALE_ALWAYS = PdfName.A;
        IF_SCALE_BIGGER = PdfName.B;
        IF_SCALE_SMALLER = PdfName.S;
        IF_SCALE_NEVER = PdfName.N;
        IF_SCALE_ANAMORPHIC = PdfName.A;
        IF_SCALE_PROPORTIONAL = PdfName.P;
        mergeTarget = (new PdfName[] {
            PdfName.FONT, PdfName.XOBJECT, PdfName.COLORSPACE, PdfName.PATTERN
        });
    }
}
