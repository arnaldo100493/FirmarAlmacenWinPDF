// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfAnnotation.java

package co.com.pdf.text.pdf;

import co.com.pdf.awt.geom.AffineTransform;
import co.com.pdf.text.BaseColor;
import co.com.pdf.text.Rectangle;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfRectangle, PdfBorderArray, PdfColor, 
//            PdfNumber, PdfName, PdfString, PdfArray, 
//            GrayColor, CMYKColor, PdfWriter, PdfFileSpecification, 
//            PdfIndirectReference, PdfAction, PdfObject, PdfTemplate, 
//            PdfIndirectObject, PdfBoolean, PdfDestination, PdfContentByte, 
//            ByteBuffer, ExtendedColor, PdfOCG, PdfBorderDictionary, 
//            PRIndirectReference, PdfReader

public class PdfAnnotation extends PdfDictionary
{
    public static class PdfImportedLink
    {

        public boolean isInternal()
        {
            return destination != null;
        }

        public int getDestinationPage()
        {
            if(!isInternal())
                return 0;
            PdfIndirectReference ref = destination.getAsIndirectObject(0);
            PRIndirectReference pr = (PRIndirectReference)ref;
            PdfReader r = pr.getReader();
            for(int i = 1; i <= r.getNumberOfPages(); i++)
            {
                PRIndirectReference pp = r.getPageOrigRef(i);
                if(pp.getGeneration() == pr.getGeneration() && pp.getNumber() == pr.getNumber())
                    return i;
            }

            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("page.not.found", new Object[0]));
        }

        public void setDestinationPage(int newPage)
        {
            if(!isInternal())
            {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("cannot.change.destination.of.external.link", new Object[0]));
            } else
            {
                this.newPage = newPage;
                return;
            }
        }

        public void transformDestination(float a, float b, float c, float d, float e, float f)
        {
            if(!isInternal())
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("cannot.change.destination.of.external.link", new Object[0]));
            if(destination.getAsName(1).equals(PdfName.XYZ))
            {
                float x = destination.getAsNumber(2).floatValue();
                float y = destination.getAsNumber(3).floatValue();
                float xx = x * a + y * c + e;
                float yy = x * b + y * d + f;
                destination.set(2, new PdfNumber(xx));
                destination.set(3, new PdfNumber(yy));
            }
        }

        public void transformRect(float a, float b, float c, float d, float e, float f)
        {
            float x = llx * a + lly * c + e;
            float y = llx * b + lly * d + f;
            llx = x;
            lly = y;
            x = urx * a + ury * c + e;
            y = urx * b + ury * d + f;
            urx = x;
            ury = y;
        }

        public PdfAnnotation createAnnotation(PdfWriter writer)
        {
            PdfAnnotation annotation = new PdfAnnotation(writer, new Rectangle(llx, lly, urx, ury));
            if(newPage != 0)
            {
                PdfIndirectReference ref = writer.getPageReference(newPage);
                destination.set(0, ref);
            }
            if(destination != null)
                annotation.put(PdfName.DEST, destination);
            annotation.hashMap.putAll(parameters);
            return annotation;
        }

        public String toString()
        {
            StringBuffer buf = new StringBuffer("Imported link: location [");
            buf.append(llx);
            buf.append(' ');
            buf.append(lly);
            buf.append(' ');
            buf.append(urx);
            buf.append(' ');
            buf.append(ury);
            buf.append("] destination ");
            buf.append(destination);
            buf.append(" parameters ");
            buf.append(parameters);
            return buf.toString();
        }

        float llx;
        float lly;
        float urx;
        float ury;
        HashMap parameters;
        PdfArray destination;
        int newPage;

        PdfImportedLink(PdfDictionary annotation)
        {
            parameters = new HashMap();
            destination = null;
            newPage = 0;
            parameters.putAll(annotation.hashMap);
            try
            {
                destination = (PdfArray)parameters.remove(PdfName.DEST);
            }
            catch(ClassCastException ex)
            {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("you.have.to.consolidate.the.named.destinations.of.your.reader", new Object[0]));
            }
            if(destination != null)
                destination = new PdfArray(destination);
            PdfArray rc = (PdfArray)parameters.remove(PdfName.RECT);
            llx = rc.getAsNumber(0).floatValue();
            lly = rc.getAsNumber(1).floatValue();
            urx = rc.getAsNumber(2).floatValue();
            ury = rc.getAsNumber(3).floatValue();
        }
    }


    public PdfAnnotation(PdfWriter writer, Rectangle rect)
    {
        form = false;
        annotation = true;
        used = false;
        placeInPage = -1;
        this.writer = writer;
        if(rect != null)
            put(PdfName.RECT, new PdfRectangle(rect));
    }

    public PdfAnnotation(PdfWriter writer, float llx, float lly, float urx, float ury, PdfString title, PdfString content)
    {
        form = false;
        annotation = true;
        used = false;
        placeInPage = -1;
        this.writer = writer;
        put(PdfName.SUBTYPE, PdfName.TEXT);
        put(PdfName.T, title);
        put(PdfName.RECT, new PdfRectangle(llx, lly, urx, ury));
        put(PdfName.CONTENTS, content);
    }

    public PdfAnnotation(PdfWriter writer, float llx, float lly, float urx, float ury, PdfAction action)
    {
        form = false;
        annotation = true;
        used = false;
        placeInPage = -1;
        this.writer = writer;
        put(PdfName.SUBTYPE, PdfName.LINK);
        put(PdfName.RECT, new PdfRectangle(llx, lly, urx, ury));
        put(PdfName.A, action);
        put(PdfName.BORDER, new PdfBorderArray(0.0F, 0.0F, 0.0F));
        put(PdfName.C, new PdfColor(0, 0, 255));
    }

    public static PdfAnnotation createScreen(PdfWriter writer, Rectangle rect, String clipTitle, PdfFileSpecification fs, String mimeType, boolean playOnDisplay)
        throws IOException
    {
        PdfAnnotation ann = new PdfAnnotation(writer, rect);
        ann.put(PdfName.SUBTYPE, PdfName.SCREEN);
        ann.put(PdfName.F, new PdfNumber(4));
        ann.put(PdfName.TYPE, PdfName.ANNOT);
        ann.setPage();
        PdfIndirectReference ref = ann.getIndirectReference();
        PdfAction action = PdfAction.rendition(clipTitle, fs, mimeType, ref);
        PdfIndirectReference actionRef = writer.addToBody(action).getIndirectReference();
        if(playOnDisplay)
        {
            PdfDictionary aa = new PdfDictionary();
            aa.put(new PdfName("PV"), actionRef);
            ann.put(PdfName.AA, aa);
        }
        ann.put(PdfName.A, actionRef);
        return ann;
    }

    public PdfIndirectReference getIndirectReference()
    {
        if(reference == null)
            reference = writer.getPdfIndirectReference();
        return reference;
    }

    public static PdfAnnotation createText(PdfWriter writer, Rectangle rect, String title, String contents, boolean open, String icon)
    {
        PdfAnnotation annot = new PdfAnnotation(writer, rect);
        annot.put(PdfName.SUBTYPE, PdfName.TEXT);
        if(title != null)
            annot.put(PdfName.T, new PdfString(title, "UnicodeBig"));
        if(contents != null)
            annot.put(PdfName.CONTENTS, new PdfString(contents, "UnicodeBig"));
        if(open)
            annot.put(PdfName.OPEN, PdfBoolean.PDFTRUE);
        if(icon != null)
            annot.put(PdfName.NAME, new PdfName(icon));
        return annot;
    }

    protected static PdfAnnotation createLink(PdfWriter writer, Rectangle rect, PdfName highlight)
    {
        PdfAnnotation annot = new PdfAnnotation(writer, rect);
        annot.put(PdfName.SUBTYPE, PdfName.LINK);
        if(!highlight.equals(HIGHLIGHT_INVERT))
            annot.put(PdfName.H, highlight);
        return annot;
    }

    public static PdfAnnotation createLink(PdfWriter writer, Rectangle rect, PdfName highlight, PdfAction action)
    {
        PdfAnnotation annot = createLink(writer, rect, highlight);
        annot.putEx(PdfName.A, action);
        return annot;
    }

    public static PdfAnnotation createLink(PdfWriter writer, Rectangle rect, PdfName highlight, String namedDestination)
    {
        PdfAnnotation annot = createLink(writer, rect, highlight);
        annot.put(PdfName.DEST, new PdfString(namedDestination));
        return annot;
    }

    public static PdfAnnotation createLink(PdfWriter writer, Rectangle rect, PdfName highlight, int page, PdfDestination dest)
    {
        PdfAnnotation annot = createLink(writer, rect, highlight);
        PdfIndirectReference ref = writer.getPageReference(page);
        dest.addPage(ref);
        annot.put(PdfName.DEST, dest);
        return annot;
    }

    public static PdfAnnotation createFreeText(PdfWriter writer, Rectangle rect, String contents, PdfContentByte defaultAppearance)
    {
        PdfAnnotation annot = new PdfAnnotation(writer, rect);
        annot.put(PdfName.SUBTYPE, PdfName.FREETEXT);
        annot.put(PdfName.CONTENTS, new PdfString(contents, "UnicodeBig"));
        annot.setDefaultAppearanceString(defaultAppearance);
        return annot;
    }

    public static PdfAnnotation createLine(PdfWriter writer, Rectangle rect, String contents, float x1, float y1, float x2, float y2)
    {
        PdfAnnotation annot = new PdfAnnotation(writer, rect);
        annot.put(PdfName.SUBTYPE, PdfName.LINE);
        annot.put(PdfName.CONTENTS, new PdfString(contents, "UnicodeBig"));
        PdfArray array = new PdfArray(new PdfNumber(x1));
        array.add(new PdfNumber(y1));
        array.add(new PdfNumber(x2));
        array.add(new PdfNumber(y2));
        annot.put(PdfName.L, array);
        return annot;
    }

    public static PdfAnnotation createSquareCircle(PdfWriter writer, Rectangle rect, String contents, boolean square)
    {
        PdfAnnotation annot = new PdfAnnotation(writer, rect);
        if(square)
            annot.put(PdfName.SUBTYPE, PdfName.SQUARE);
        else
            annot.put(PdfName.SUBTYPE, PdfName.CIRCLE);
        annot.put(PdfName.CONTENTS, new PdfString(contents, "UnicodeBig"));
        return annot;
    }

    public static PdfAnnotation createMarkup(PdfWriter writer, Rectangle rect, String contents, int type, float quadPoints[])
    {
        PdfAnnotation annot = new PdfAnnotation(writer, rect);
        PdfName name = PdfName.HIGHLIGHT;
        switch(type)
        {
        case 1: // '\001'
            name = PdfName.UNDERLINE;
            break;

        case 2: // '\002'
            name = PdfName.STRIKEOUT;
            break;

        case 3: // '\003'
            name = PdfName.SQUIGGLY;
            break;
        }
        annot.put(PdfName.SUBTYPE, name);
        annot.put(PdfName.CONTENTS, new PdfString(contents, "UnicodeBig"));
        PdfArray array = new PdfArray();
        for(int k = 0; k < quadPoints.length; k++)
            array.add(new PdfNumber(quadPoints[k]));

        annot.put(PdfName.QUADPOINTS, array);
        return annot;
    }

    public static PdfAnnotation createStamp(PdfWriter writer, Rectangle rect, String contents, String name)
    {
        PdfAnnotation annot = new PdfAnnotation(writer, rect);
        annot.put(PdfName.SUBTYPE, PdfName.STAMP);
        annot.put(PdfName.CONTENTS, new PdfString(contents, "UnicodeBig"));
        annot.put(PdfName.NAME, new PdfName(name));
        return annot;
    }

    public static PdfAnnotation createInk(PdfWriter writer, Rectangle rect, String contents, float inkList[][])
    {
        PdfAnnotation annot = new PdfAnnotation(writer, rect);
        annot.put(PdfName.SUBTYPE, PdfName.INK);
        annot.put(PdfName.CONTENTS, new PdfString(contents, "UnicodeBig"));
        PdfArray outer = new PdfArray();
        for(int k = 0; k < inkList.length; k++)
        {
            PdfArray inner = new PdfArray();
            float deep[] = inkList[k];
            for(int j = 0; j < deep.length; j++)
                inner.add(new PdfNumber(deep[j]));

            outer.add(inner);
        }

        annot.put(PdfName.INKLIST, outer);
        return annot;
    }

    public static PdfAnnotation createFileAttachment(PdfWriter writer, Rectangle rect, String contents, byte fileStore[], String file, String fileDisplay)
        throws IOException
    {
        return createFileAttachment(writer, rect, contents, PdfFileSpecification.fileEmbedded(writer, file, fileDisplay, fileStore));
    }

    public static PdfAnnotation createFileAttachment(PdfWriter writer, Rectangle rect, String contents, PdfFileSpecification fs)
        throws IOException
    {
        PdfAnnotation annot = new PdfAnnotation(writer, rect);
        annot.put(PdfName.SUBTYPE, PdfName.FILEATTACHMENT);
        if(contents != null)
            annot.put(PdfName.CONTENTS, new PdfString(contents, "UnicodeBig"));
        annot.put(PdfName.FS, fs.getReference());
        return annot;
    }

    public static PdfAnnotation createPopup(PdfWriter writer, Rectangle rect, String contents, boolean open)
    {
        PdfAnnotation annot = new PdfAnnotation(writer, rect);
        annot.put(PdfName.SUBTYPE, PdfName.POPUP);
        if(contents != null)
            annot.put(PdfName.CONTENTS, new PdfString(contents, "UnicodeBig"));
        if(open)
            annot.put(PdfName.OPEN, PdfBoolean.PDFTRUE);
        return annot;
    }

    public static PdfAnnotation createPolygonPolyline(PdfWriter writer, Rectangle rect, String contents, boolean polygon, PdfArray vertices)
    {
        PdfAnnotation annot = new PdfAnnotation(writer, rect);
        if(polygon)
            annot.put(PdfName.SUBTYPE, PdfName.POLYGON);
        else
            annot.put(PdfName.SUBTYPE, PdfName.POLYLINE);
        annot.put(PdfName.CONTENTS, new PdfString(contents, "UnicodeBig"));
        annot.put(PdfName.VERTICES, new PdfArray(vertices));
        return annot;
    }

    public void setDefaultAppearanceString(PdfContentByte cb)
    {
        byte b[] = cb.getInternalBuffer().toByteArray();
        int len = b.length;
        for(int k = 0; k < len; k++)
            if(b[k] == 10)
                b[k] = 32;

        put(PdfName.DA, new PdfString(b));
    }

    public void setFlags(int flags)
    {
        if(flags == 0)
            remove(PdfName.F);
        else
            put(PdfName.F, new PdfNumber(flags));
    }

    public void setBorder(PdfBorderArray border)
    {
        put(PdfName.BORDER, border);
    }

    public void setBorderStyle(PdfBorderDictionary border)
    {
        put(PdfName.BS, border);
    }

    public void setHighlighting(PdfName highlight)
    {
        if(highlight.equals(HIGHLIGHT_INVERT))
            remove(PdfName.H);
        else
            put(PdfName.H, highlight);
    }

    public void setAppearance(PdfName ap, PdfTemplate template)
    {
        PdfDictionary dic = (PdfDictionary)get(PdfName.AP);
        if(dic == null)
            dic = new PdfDictionary();
        dic.put(ap, template.getIndirectReference());
        put(PdfName.AP, dic);
        if(!form)
            return;
        if(templates == null)
            templates = new HashSet();
        templates.add(template);
    }

    public void setAppearance(PdfName ap, String state, PdfTemplate template)
    {
        PdfDictionary dicAp = (PdfDictionary)get(PdfName.AP);
        if(dicAp == null)
            dicAp = new PdfDictionary();
        PdfObject obj = dicAp.get(ap);
        PdfDictionary dic;
        if(obj != null && obj.isDictionary())
            dic = (PdfDictionary)obj;
        else
            dic = new PdfDictionary();
        dic.put(new PdfName(state), template.getIndirectReference());
        dicAp.put(ap, dic);
        put(PdfName.AP, dicAp);
        if(!form)
            return;
        if(templates == null)
            templates = new HashSet();
        templates.add(template);
    }

    public void setAppearanceState(String state)
    {
        if(state == null)
        {
            remove(PdfName.AS);
            return;
        } else
        {
            put(PdfName.AS, new PdfName(state));
            return;
        }
    }

    public void setColor(BaseColor color)
    {
        put(PdfName.C, new PdfColor(color));
    }

    public void setTitle(String title)
    {
        if(title == null)
        {
            remove(PdfName.T);
            return;
        } else
        {
            put(PdfName.T, new PdfString(title, "UnicodeBig"));
            return;
        }
    }

    public void setPopup(PdfAnnotation popup)
    {
        put(PdfName.POPUP, popup.getIndirectReference());
        popup.put(PdfName.PARENT, getIndirectReference());
    }

    public void setAction(PdfAction action)
    {
        put(PdfName.A, action);
    }

    public void setAdditionalActions(PdfName key, PdfAction action)
    {
        PdfObject obj = get(PdfName.AA);
        PdfDictionary dic;
        if(obj != null && obj.isDictionary())
            dic = (PdfDictionary)obj;
        else
            dic = new PdfDictionary();
        dic.put(key, action);
        put(PdfName.AA, dic);
    }

    public boolean isUsed()
    {
        return used;
    }

    public void setUsed()
    {
        used = true;
    }

    public HashSet getTemplates()
    {
        return templates;
    }

    public boolean isForm()
    {
        return form;
    }

    public boolean isAnnotation()
    {
        return annotation;
    }

    public void setPage(int page)
    {
        put(PdfName.P, writer.getPageReference(page));
    }

    public void setPage()
    {
        put(PdfName.P, writer.getCurrentPage());
    }

    public int getPlaceInPage()
    {
        return placeInPage;
    }

    public void setPlaceInPage(int placeInPage)
    {
        this.placeInPage = placeInPage;
    }

    public void setRotate(int v)
    {
        put(PdfName.ROTATE, new PdfNumber(v));
    }

    PdfDictionary getMK()
    {
        PdfDictionary mk = (PdfDictionary)get(PdfName.MK);
        if(mk == null)
        {
            mk = new PdfDictionary();
            put(PdfName.MK, mk);
        }
        return mk;
    }

    public void setMKRotation(int rotation)
    {
        getMK().put(PdfName.R, new PdfNumber(rotation));
    }

    public static PdfArray getMKColor(BaseColor color)
    {
        PdfArray array = new PdfArray();
        int type = ExtendedColor.getType(color);
        switch(type)
        {
        case 1: // '\001'
            array.add(new PdfNumber(((GrayColor)color).getGray()));
            break;

        case 2: // '\002'
            CMYKColor cmyk = (CMYKColor)color;
            array.add(new PdfNumber(cmyk.getCyan()));
            array.add(new PdfNumber(cmyk.getMagenta()));
            array.add(new PdfNumber(cmyk.getYellow()));
            array.add(new PdfNumber(cmyk.getBlack()));
            break;

        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
            throw new RuntimeException(MessageLocalization.getComposedMessage("separations.patterns.and.shadings.are.not.allowed.in.mk.dictionary", new Object[0]));

        default:
            array.add(new PdfNumber((float)color.getRed() / 255F));
            array.add(new PdfNumber((float)color.getGreen() / 255F));
            array.add(new PdfNumber((float)color.getBlue() / 255F));
            break;
        }
        return array;
    }

    public void setMKBorderColor(BaseColor color)
    {
        if(color == null)
            getMK().remove(PdfName.BC);
        else
            getMK().put(PdfName.BC, getMKColor(color));
    }

    public void setMKBackgroundColor(BaseColor color)
    {
        if(color == null)
            getMK().remove(PdfName.BG);
        else
            getMK().put(PdfName.BG, getMKColor(color));
    }

    public void setMKNormalCaption(String caption)
    {
        getMK().put(PdfName.CA, new PdfString(caption, "UnicodeBig"));
    }

    public void setMKRolloverCaption(String caption)
    {
        getMK().put(PdfName.RC, new PdfString(caption, "UnicodeBig"));
    }

    public void setMKAlternateCaption(String caption)
    {
        getMK().put(PdfName.AC, new PdfString(caption, "UnicodeBig"));
    }

    public void setMKNormalIcon(PdfTemplate template)
    {
        getMK().put(PdfName.I, template.getIndirectReference());
    }

    public void setMKRolloverIcon(PdfTemplate template)
    {
        getMK().put(PdfName.RI, template.getIndirectReference());
    }

    public void setMKAlternateIcon(PdfTemplate template)
    {
        getMK().put(PdfName.IX, template.getIndirectReference());
    }

    public void setMKIconFit(PdfName scale, PdfName scalingType, float leftoverLeft, float leftoverBottom, boolean fitInBounds)
    {
        PdfDictionary dic = new PdfDictionary();
        if(!scale.equals(PdfName.A))
            dic.put(PdfName.SW, scale);
        if(!scalingType.equals(PdfName.P))
            dic.put(PdfName.S, scalingType);
        if(leftoverLeft != 0.5F || leftoverBottom != 0.5F)
        {
            PdfArray array = new PdfArray(new PdfNumber(leftoverLeft));
            array.add(new PdfNumber(leftoverBottom));
            dic.put(PdfName.A, array);
        }
        if(fitInBounds)
            dic.put(PdfName.FB, PdfBoolean.PDFTRUE);
        getMK().put(PdfName.IF, dic);
    }

    public void setMKTextPosition(int tp)
    {
        getMK().put(PdfName.TP, new PdfNumber(tp));
    }

    public void setLayer(PdfOCG layer)
    {
        put(PdfName.OC, layer.getRef());
    }

    public void setName(String name)
    {
        put(PdfName.NM, new PdfString(name));
    }

    public void applyCTM(AffineTransform ctm)
    {
        PdfArray origRect = getAsArray(PdfName.RECT);
        if(origRect != null)
        {
            PdfRectangle rect;
            if(origRect.size() == 4)
                rect = new PdfRectangle(origRect.getAsNumber(0).floatValue(), origRect.getAsNumber(1).floatValue(), origRect.getAsNumber(2).floatValue(), origRect.getAsNumber(3).floatValue());
            else
                rect = new PdfRectangle(origRect.getAsNumber(0).floatValue(), origRect.getAsNumber(1).floatValue());
            put(PdfName.RECT, rect.transform(ctm));
        }
    }

    public void toPdf(PdfWriter writer, OutputStream os)
        throws IOException
    {
        PdfWriter.checkPdfIsoConformance(writer, 13, this);
        super.toPdf(writer, os);
    }

    public static final PdfName HIGHLIGHT_NONE;
    public static final PdfName HIGHLIGHT_INVERT;
    public static final PdfName HIGHLIGHT_OUTLINE;
    public static final PdfName HIGHLIGHT_PUSH;
    public static final PdfName HIGHLIGHT_TOGGLE;
    public static final int FLAGS_INVISIBLE = 1;
    public static final int FLAGS_HIDDEN = 2;
    public static final int FLAGS_PRINT = 4;
    public static final int FLAGS_NOZOOM = 8;
    public static final int FLAGS_NOROTATE = 16;
    public static final int FLAGS_NOVIEW = 32;
    public static final int FLAGS_READONLY = 64;
    public static final int FLAGS_LOCKED = 128;
    public static final int FLAGS_TOGGLENOVIEW = 256;
    public static final PdfName APPEARANCE_NORMAL;
    public static final PdfName APPEARANCE_ROLLOVER;
    public static final PdfName APPEARANCE_DOWN;
    public static final PdfName AA_ENTER;
    public static final PdfName AA_EXIT;
    public static final PdfName AA_DOWN;
    public static final PdfName AA_UP;
    public static final PdfName AA_FOCUS;
    public static final PdfName AA_BLUR;
    public static final PdfName AA_JS_KEY;
    public static final PdfName AA_JS_FORMAT;
    public static final PdfName AA_JS_CHANGE;
    public static final PdfName AA_JS_OTHER_CHANGE;
    public static final int MARKUP_HIGHLIGHT = 0;
    public static final int MARKUP_UNDERLINE = 1;
    public static final int MARKUP_STRIKEOUT = 2;
    public static final int MARKUP_SQUIGGLY = 3;
    protected PdfWriter writer;
    protected PdfIndirectReference reference;
    protected HashSet templates;
    protected boolean form;
    protected boolean annotation;
    protected boolean used;
    private int placeInPage;

    static 
    {
        HIGHLIGHT_NONE = PdfName.N;
        HIGHLIGHT_INVERT = PdfName.I;
        HIGHLIGHT_OUTLINE = PdfName.O;
        HIGHLIGHT_PUSH = PdfName.P;
        HIGHLIGHT_TOGGLE = PdfName.T;
        APPEARANCE_NORMAL = PdfName.N;
        APPEARANCE_ROLLOVER = PdfName.R;
        APPEARANCE_DOWN = PdfName.D;
        AA_ENTER = PdfName.E;
        AA_EXIT = PdfName.X;
        AA_DOWN = PdfName.D;
        AA_UP = PdfName.U;
        AA_FOCUS = PdfName.FO;
        AA_BLUR = PdfName.BL;
        AA_JS_KEY = PdfName.K;
        AA_JS_FORMAT = PdfName.F;
        AA_JS_CHANGE = PdfName.V;
        AA_JS_OTHER_CHANGE = PdfName.C;
    }
}
