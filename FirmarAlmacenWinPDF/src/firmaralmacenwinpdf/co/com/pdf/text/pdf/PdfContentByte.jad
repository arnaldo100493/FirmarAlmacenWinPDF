// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentByte.java

package co.com.pdf.text.pdf;

import co.com.pdf.awt.FontMapper;
import co.com.pdf.awt.PdfGraphics2D;
import co.com.pdf.awt.PdfPrinterGraphics2D;
import co.com.pdf.awt.geom.AffineTransform;
import co.com.pdf.text.Annotation;
import co.com.pdf.text.BaseColor;
import co.com.pdf.text.DocumentException;
import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.Image;
import co.com.pdf.text.ImgJBIG2;
import co.com.pdf.text.Rectangle;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.exceptions.IllegalPdfSyntaxException;
import co.com.pdf.text.pdf.interfaces.IAccessibleElement;
import co.com.pdf.text.pdf.internal.PdfAnnotationsImp;
import java.awt.Graphics2D;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package co.com.pdf.text.pdf:
//            ByteBuffer, GrayColor, CMYKColor, ExtendedColor, 
//            PdfImage, PdfDictionary, PdfName, PdfArray, 
//            PdfTextArray, PdfPatternPainter, PdfTemplate, PdfAppearance, 
//            SpotColor, PatternColor, ShadingColor, PdfIndirectReference, 
//            PdfLayer, PdfLayerMembership, PdfNumber, PdfStructureElement, 
//            PdfObject, PdfWriter, PageResources, PdfAnnotation, 
//            BaseFont, ColorDetails, PdfIndirectObject, FontDetails, 
//            PdfDocument, PdfPSXObject, PdfShading, PdfShadingPattern, 
//            PdfOCG, PdfOutline, PdfSpotColor, PdfDestination, 
//            PdfAction, PdfGState

public class PdfContentByte
{
    static class UncoloredPattern extends PatternColor
    {

        public boolean equals(Object obj)
        {
            return (obj instanceof UncoloredPattern) && ((UncoloredPattern)obj).painter.equals(painter) && ((UncoloredPattern)obj).color.equals(color) && ((UncoloredPattern)obj).tint == tint;
        }

        protected BaseColor color;
        protected float tint;

        protected UncoloredPattern(PdfPatternPainter p, BaseColor color, float tint)
        {
            super(p);
            this.color = color;
            this.tint = tint;
        }
    }

    static class GraphicState
    {

        void copyParameters(GraphicState cp)
        {
            fontDetails = cp.fontDetails;
            colorDetails = cp.colorDetails;
            size = cp.size;
            xTLM = cp.xTLM;
            yTLM = cp.yTLM;
            aTLM = cp.aTLM;
            bTLM = cp.bTLM;
            cTLM = cp.cTLM;
            dTLM = cp.dTLM;
            tx = cp.tx;
            leading = cp.leading;
            scale = cp.scale;
            charSpace = cp.charSpace;
            wordSpace = cp.wordSpace;
            textColorFill = cp.textColorFill;
            graphicsColorFill = cp.graphicsColorFill;
            textColorStroke = cp.textColorStroke;
            graphicsColorStroke = cp.graphicsColorStroke;
            CTM = new AffineTransform(cp.CTM);
        }

        void restore(GraphicState restore)
        {
            copyParameters(restore);
        }

        FontDetails fontDetails;
        ColorDetails colorDetails;
        float size;
        protected float xTLM;
        protected float yTLM;
        protected float aTLM;
        protected float bTLM;
        protected float cTLM;
        protected float dTLM;
        protected float tx;
        protected float leading;
        protected float scale;
        protected float charSpace;
        protected float wordSpace;
        protected BaseColor textColorFill;
        protected BaseColor graphicsColorFill;
        protected BaseColor textColorStroke;
        protected BaseColor graphicsColorStroke;
        protected AffineTransform CTM;

        GraphicState()
        {
            xTLM = 0.0F;
            yTLM = 0.0F;
            aTLM = 1.0F;
            bTLM = 0.0F;
            cTLM = 0.0F;
            dTLM = 1.0F;
            tx = 0.0F;
            leading = 0.0F;
            scale = 100F;
            charSpace = 0.0F;
            wordSpace = 0.0F;
            textColorFill = new GrayColor(0);
            graphicsColorFill = new GrayColor(0);
            textColorStroke = new GrayColor(0);
            graphicsColorStroke = new GrayColor(0);
            CTM = new AffineTransform();
        }

        GraphicState(GraphicState cp)
        {
            xTLM = 0.0F;
            yTLM = 0.0F;
            aTLM = 1.0F;
            bTLM = 0.0F;
            cTLM = 0.0F;
            dTLM = 1.0F;
            tx = 0.0F;
            leading = 0.0F;
            scale = 100F;
            charSpace = 0.0F;
            wordSpace = 0.0F;
            textColorFill = new GrayColor(0);
            graphicsColorFill = new GrayColor(0);
            textColorStroke = new GrayColor(0);
            graphicsColorStroke = new GrayColor(0);
            CTM = new AffineTransform();
            copyParameters(cp);
        }
    }


    public PdfContentByte(PdfWriter wr)
    {
        content = new ByteBuffer();
        markedContentSize = 0;
        state = new GraphicState();
        stateList = new ArrayList();
        separator = 10;
        mcDepth = 0;
        inText = false;
        mcElements = new ArrayList();
        duplicatedFrom = null;
        if(wr != null)
        {
            writer = wr;
            pdf = writer.getPdfDocument();
        }
    }

    public String toString()
    {
        return content.toString();
    }

    public boolean isTagged()
    {
        return writer != null && writer.isTagged();
    }

    public ByteBuffer getInternalBuffer()
    {
        return content;
    }

    public byte[] toPdf(PdfWriter writer)
    {
        sanityCheck();
        return content.toByteArray();
    }

    public void add(PdfContentByte other)
    {
        if(other.writer != null && writer != other.writer)
        {
            throw new RuntimeException(MessageLocalization.getComposedMessage("inconsistent.writers.are.you.mixing.two.documents", new Object[0]));
        } else
        {
            content.append(other.content);
            markedContentSize += other.markedContentSize;
            return;
        }
    }

    public float getXTLM()
    {
        return state.xTLM;
    }

    public float getYTLM()
    {
        return state.yTLM;
    }

    public float getLeading()
    {
        return state.leading;
    }

    public float getCharacterSpacing()
    {
        return state.charSpace;
    }

    public float getWordSpacing()
    {
        return state.wordSpace;
    }

    public float getHorizontalScaling()
    {
        return state.scale;
    }

    public void setFlatness(float flatness)
    {
        if(flatness >= 0.0F && flatness <= 100F)
            content.append(flatness).append(" i").append_i(separator);
    }

    public void setLineCap(int style)
    {
        if(style >= 0 && style <= 2)
            content.append(style).append(" J").append_i(separator);
    }

    public void setLineDash(float phase)
    {
        content.append("[] ").append(phase).append(" d").append_i(separator);
    }

    public void setLineDash(float unitsOn, float phase)
    {
        content.append("[").append(unitsOn).append("] ").append(phase).append(" d").append_i(separator);
    }

    public void setLineDash(float unitsOn, float unitsOff, float phase)
    {
        content.append("[").append(unitsOn).append(' ').append(unitsOff).append("] ").append(phase).append(" d").append_i(separator);
    }

    public final void setLineDash(float array[], float phase)
    {
        content.append("[");
        for(int i = 0; i < array.length; i++)
        {
            content.append(array[i]);
            if(i < array.length - 1)
                content.append(' ');
        }

        content.append("] ").append(phase).append(" d").append_i(separator);
    }

    public void setLineJoin(int style)
    {
        if(style >= 0 && style <= 2)
            content.append(style).append(" j").append_i(separator);
    }

    public void setLineWidth(float w)
    {
        content.append(w).append(" w").append_i(separator);
    }

    public void setMiterLimit(float miterLimit)
    {
        if(miterLimit > 1.0F)
            content.append(miterLimit).append(" M").append_i(separator);
    }

    public void clip()
    {
        if(inText && isTagged())
            endText();
        content.append("W").append_i(separator);
    }

    public void eoClip()
    {
        if(inText && isTagged())
            endText();
        content.append("W*").append_i(separator);
    }

    public void setGrayFill(float gray)
    {
        saveColor(new GrayColor(gray), true);
        content.append(gray).append(" g").append_i(separator);
    }

    public void resetGrayFill()
    {
        saveColor(new GrayColor(0), true);
        content.append("0 g").append_i(separator);
    }

    public void setGrayStroke(float gray)
    {
        saveColor(new GrayColor(gray), false);
        content.append(gray).append(" G").append_i(separator);
    }

    public void resetGrayStroke()
    {
        saveColor(new GrayColor(0), false);
        content.append("0 G").append_i(separator);
    }

    private void HelperRGB(float red, float green, float blue)
    {
        PdfWriter.checkPdfIsoConformance(writer, 3, null);
        if(red < 0.0F)
            red = 0.0F;
        else
        if(red > 1.0F)
            red = 1.0F;
        if(green < 0.0F)
            green = 0.0F;
        else
        if(green > 1.0F)
            green = 1.0F;
        if(blue < 0.0F)
            blue = 0.0F;
        else
        if(blue > 1.0F)
            blue = 1.0F;
        content.append(red).append(' ').append(green).append(' ').append(blue);
    }

    public void setRGBColorFillF(float red, float green, float blue)
    {
        saveColor(new BaseColor(red, green, blue), true);
        HelperRGB(red, green, blue);
        content.append(" rg").append_i(separator);
    }

    public void resetRGBColorFill()
    {
        resetGrayFill();
    }

    public void setRGBColorStrokeF(float red, float green, float blue)
    {
        saveColor(new BaseColor(red, green, blue), false);
        HelperRGB(red, green, blue);
        content.append(" RG").append_i(separator);
    }

    public void resetRGBColorStroke()
    {
        resetGrayStroke();
    }

    private void HelperCMYK(float cyan, float magenta, float yellow, float black)
    {
        if(cyan < 0.0F)
            cyan = 0.0F;
        else
        if(cyan > 1.0F)
            cyan = 1.0F;
        if(magenta < 0.0F)
            magenta = 0.0F;
        else
        if(magenta > 1.0F)
            magenta = 1.0F;
        if(yellow < 0.0F)
            yellow = 0.0F;
        else
        if(yellow > 1.0F)
            yellow = 1.0F;
        if(black < 0.0F)
            black = 0.0F;
        else
        if(black > 1.0F)
            black = 1.0F;
        content.append(cyan).append(' ').append(magenta).append(' ').append(yellow).append(' ').append(black);
    }

    public void setCMYKColorFillF(float cyan, float magenta, float yellow, float black)
    {
        saveColor(new CMYKColor(cyan, magenta, yellow, black), true);
        HelperCMYK(cyan, magenta, yellow, black);
        content.append(" k").append_i(separator);
    }

    public void resetCMYKColorFill()
    {
        saveColor(new CMYKColor(0, 0, 0, 1), true);
        content.append("0 0 0 1 k").append_i(separator);
    }

    public void setCMYKColorStrokeF(float cyan, float magenta, float yellow, float black)
    {
        saveColor(new CMYKColor(cyan, magenta, yellow, black), false);
        HelperCMYK(cyan, magenta, yellow, black);
        content.append(" K").append_i(separator);
    }

    public void resetCMYKColorStroke()
    {
        saveColor(new CMYKColor(0, 0, 0, 1), false);
        content.append("0 0 0 1 K").append_i(separator);
    }

    public void moveTo(float x, float y)
    {
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
        content.append(x).append(' ').append(y).append(" m").append_i(separator);
    }

    public void lineTo(float x, float y)
    {
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
        content.append(x).append(' ').append(y).append(" l").append_i(separator);
    }

    public void curveTo(float x1, float y1, float x2, float y2, float x3, float y3)
    {
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
        content.append(x1).append(' ').append(y1).append(' ').append(x2).append(' ').append(y2).append(' ').append(x3).append(' ').append(y3).append(" c").append_i(separator);
    }

    public void curveTo(float x2, float y2, float x3, float y3)
    {
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
        content.append(x2).append(' ').append(y2).append(' ').append(x3).append(' ').append(y3).append(" v").append_i(separator);
    }

    public void curveFromTo(float x1, float y1, float x3, float y3)
    {
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
        content.append(x1).append(' ').append(y1).append(' ').append(x3).append(' ').append(y3).append(" y").append_i(separator);
    }

    public void circle(float x, float y, float r)
    {
        float b = 0.5523F;
        moveTo(x + r, y);
        curveTo(x + r, y + r * b, x + r * b, y + r, x, y + r);
        curveTo(x - r * b, y + r, x - r, y + r * b, x - r, y);
        curveTo(x - r, y - r * b, x - r * b, y - r, x, y - r);
        curveTo(x + r * b, y - r, x + r, y - r * b, x + r, y);
    }

    public void rectangle(float x, float y, float w, float h)
    {
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
        content.append(x).append(' ').append(y).append(' ').append(w).append(' ').append(h).append(" re").append_i(separator);
    }

    private boolean compareColors(BaseColor c1, BaseColor c2)
    {
        if(c1 == null && c2 == null)
            return true;
        if(c1 == null || c2 == null)
            return false;
        if(c1 instanceof ExtendedColor)
            return c1.equals(c2);
        else
            return c2.equals(c1);
    }

    public void variableRectangle(Rectangle rect)
    {
        float t = rect.getTop();
        float b = rect.getBottom();
        float r = rect.getRight();
        float l = rect.getLeft();
        float wt = rect.getBorderWidthTop();
        float wb = rect.getBorderWidthBottom();
        float wr = rect.getBorderWidthRight();
        float wl = rect.getBorderWidthLeft();
        BaseColor ct = rect.getBorderColorTop();
        BaseColor cb = rect.getBorderColorBottom();
        BaseColor cr = rect.getBorderColorRight();
        BaseColor cl = rect.getBorderColorLeft();
        saveState();
        setLineCap(0);
        setLineJoin(0);
        float clw = 0.0F;
        boolean cdef = false;
        BaseColor ccol = null;
        boolean cdefi = false;
        BaseColor cfil = null;
        if(wt > 0.0F)
        {
            setLineWidth(clw = wt);
            cdef = true;
            if(ct == null)
                resetRGBColorStroke();
            else
                setColorStroke(ct);
            ccol = ct;
            moveTo(l, t - wt / 2.0F);
            lineTo(r, t - wt / 2.0F);
            stroke();
        }
        if(wb > 0.0F)
        {
            if(wb != clw)
                setLineWidth(clw = wb);
            if(!cdef || !compareColors(ccol, cb))
            {
                cdef = true;
                if(cb == null)
                    resetRGBColorStroke();
                else
                    setColorStroke(cb);
                ccol = cb;
            }
            moveTo(r, b + wb / 2.0F);
            lineTo(l, b + wb / 2.0F);
            stroke();
        }
        if(wr > 0.0F)
        {
            if(wr != clw)
                setLineWidth(clw = wr);
            if(!cdef || !compareColors(ccol, cr))
            {
                cdef = true;
                if(cr == null)
                    resetRGBColorStroke();
                else
                    setColorStroke(cr);
                ccol = cr;
            }
            boolean bt = compareColors(ct, cr);
            boolean bb = compareColors(cb, cr);
            moveTo(r - wr / 2.0F, bt ? t : t - wt);
            lineTo(r - wr / 2.0F, bb ? b : b + wb);
            stroke();
            if(!bt || !bb)
            {
                cdefi = true;
                if(cr == null)
                    resetRGBColorFill();
                else
                    setColorFill(cr);
                cfil = cr;
                if(!bt)
                {
                    moveTo(r, t);
                    lineTo(r, t - wt);
                    lineTo(r - wr, t - wt);
                    fill();
                }
                if(!bb)
                {
                    moveTo(r, b);
                    lineTo(r, b + wb);
                    lineTo(r - wr, b + wb);
                    fill();
                }
            }
        }
        if(wl > 0.0F)
        {
            if(wl != clw)
                setLineWidth(wl);
            if(!cdef || !compareColors(ccol, cl))
                if(cl == null)
                    resetRGBColorStroke();
                else
                    setColorStroke(cl);
            boolean bt = compareColors(ct, cl);
            boolean bb = compareColors(cb, cl);
            moveTo(l + wl / 2.0F, bt ? t : t - wt);
            lineTo(l + wl / 2.0F, bb ? b : b + wb);
            stroke();
            if(!bt || !bb)
            {
                if(!cdefi || !compareColors(cfil, cl))
                    if(cl == null)
                        resetRGBColorFill();
                    else
                        setColorFill(cl);
                if(!bt)
                {
                    moveTo(l, t);
                    lineTo(l, t - wt);
                    lineTo(l + wl, t - wt);
                    fill();
                }
                if(!bb)
                {
                    moveTo(l, b);
                    lineTo(l, b + wb);
                    lineTo(l + wl, b + wb);
                    fill();
                }
            }
        }
        restoreState();
    }

    public void rectangle(Rectangle rectangle)
    {
        float x1 = rectangle.getLeft();
        float y1 = rectangle.getBottom();
        float x2 = rectangle.getRight();
        float y2 = rectangle.getTop();
        BaseColor background = rectangle.getBackgroundColor();
        if(background != null)
        {
            saveState();
            setColorFill(background);
            rectangle(x1, y1, x2 - x1, y2 - y1);
            fill();
            restoreState();
        }
        if(!rectangle.hasBorders())
            return;
        if(rectangle.isUseVariableBorders())
        {
            variableRectangle(rectangle);
        } else
        {
            if(rectangle.getBorderWidth() != -1F)
                setLineWidth(rectangle.getBorderWidth());
            BaseColor color = rectangle.getBorderColor();
            if(color != null)
                setColorStroke(color);
            if(rectangle.hasBorder(15))
            {
                rectangle(x1, y1, x2 - x1, y2 - y1);
            } else
            {
                if(rectangle.hasBorder(8))
                {
                    moveTo(x2, y1);
                    lineTo(x2, y2);
                }
                if(rectangle.hasBorder(4))
                {
                    moveTo(x1, y1);
                    lineTo(x1, y2);
                }
                if(rectangle.hasBorder(2))
                {
                    moveTo(x1, y1);
                    lineTo(x2, y1);
                }
                if(rectangle.hasBorder(1))
                {
                    moveTo(x1, y2);
                    lineTo(x2, y2);
                }
            }
            stroke();
            if(color != null)
                resetRGBColorStroke();
        }
    }

    public void closePath()
    {
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
        content.append("h").append_i(separator);
    }

    public void newPath()
    {
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
        content.append("n").append_i(separator);
    }

    public void stroke()
    {
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
        content.append("S").append_i(separator);
    }

    public void closePathStroke()
    {
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
        content.append("s").append_i(separator);
    }

    public void fill()
    {
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
        content.append("f").append_i(separator);
    }

    public void eoFill()
    {
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
        content.append("f*").append_i(separator);
    }

    public void fillStroke()
    {
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
        content.append("B").append_i(separator);
    }

    public void closePathFillStroke()
    {
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
        content.append("b").append_i(separator);
    }

    public void eoFillStroke()
    {
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
        content.append("B*").append_i(separator);
    }

    public void closePathEoFillStroke()
    {
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("path.construction.operator.inside.text.object", new Object[0]));
        content.append("b*").append_i(separator);
    }

    public void addImage(Image image)
        throws DocumentException
    {
        addImage(image, false);
    }

    public void addImage(Image image, boolean inlineImage)
        throws DocumentException
    {
        if(!image.hasAbsoluteY())
        {
            throw new DocumentException(MessageLocalization.getComposedMessage("the.image.must.have.absolute.positioning", new Object[0]));
        } else
        {
            float matrix[] = image.matrix();
            matrix[4] = image.getAbsoluteX() - matrix[4];
            matrix[5] = image.getAbsoluteY() - matrix[5];
            addImage(image, matrix[0], matrix[1], matrix[2], matrix[3], matrix[4], matrix[5], inlineImage);
            return;
        }
    }

    public void addImage(Image image, float a, float b, float c, float d, float e, float f)
        throws DocumentException
    {
        addImage(image, a, b, c, d, e, f, false);
    }

    public void addImage(Image image, AffineTransform transform)
        throws DocumentException
    {
        double matrix[] = new double[6];
        transform.getMatrix(matrix);
        addImage(image, (float)matrix[0], (float)matrix[1], (float)matrix[2], (float)matrix[3], (float)matrix[4], (float)matrix[5], false);
    }

    public void addImage(Image image, float a, float b, float c, float d, float e, float f, 
            boolean inlineImage)
        throws DocumentException
    {
        Annotation annot;
        float r[];
        float llx;
        float lly;
        float urx;
        float ury;
        PdfAnnotation an;
        try
        {
            if(image.getLayer() != null)
                beginLayer(image.getLayer());
            if(inText && isTagged())
                endText();
            if(writer != null && image.isImgTemplate())
            {
                writer.addDirectImageSimple(image);
                PdfTemplate template = image.getTemplateData();
                float w = template.getWidth();
                float h = template.getHeight();
                addTemplate(template, a / w, b / w, c / h, d / h, e, f);
            } else
            {
                content.append("q ");
                content.append(a).append(' ');
                content.append(b).append(' ');
                content.append(c).append(' ');
                content.append(d).append(' ');
                content.append(e).append(' ');
                content.append(f).append(" cm");
                if(inlineImage)
                {
                    content.append("\nBI\n");
                    PdfImage pimage = new PdfImage(image, "", null);
                    if(image instanceof ImgJBIG2)
                    {
                        byte globals[] = ((ImgJBIG2)image).getGlobalBytes();
                        if(globals != null)
                        {
                            PdfDictionary decodeparms = new PdfDictionary();
                            decodeparms.put(PdfName.JBIG2GLOBALS, writer.getReferenceJBIG2Globals(globals));
                            pimage.put(PdfName.DECODEPARMS, decodeparms);
                        }
                    }
                    Iterator i$ = pimage.getKeys().iterator();
                    do
                    {
                        if(!i$.hasNext())
                            break;
                        Object element = i$.next();
                        PdfName key = (PdfName)element;
                        PdfObject value = pimage.get(key);
                        String s = (String)abrev.get(key);
                        if(s != null)
                        {
                            content.append(s);
                            boolean check = true;
                            if(key.equals(PdfName.COLORSPACE) && value.isArray())
                            {
                                PdfArray ar = (PdfArray)value;
                                if(ar.size() == 4 && PdfName.INDEXED.equals(ar.getAsName(0)) && ar.getPdfObject(1).isName() && ar.getPdfObject(2).isNumber() && ar.getPdfObject(3).isString())
                                    check = false;
                            }
                            if(check && key.equals(PdfName.COLORSPACE) && !value.isName())
                            {
                                PdfName cs = writer.getColorspaceName();
                                PageResources prs = getPageResources();
                                prs.addColor(cs, writer.addToBody(value).getIndirectReference());
                                value = cs;
                            }
                            value.toPdf(null, content);
                            content.append('\n');
                        }
                    } while(true);
                    content.append("ID\n");
                    pimage.writeContent(content);
                    content.append("\nEI\nQ").append_i(separator);
                } else
                {
                    PageResources prs = getPageResources();
                    Image maskImage = image.getImageMask();
                    PdfName name;
                    if(maskImage != null)
                    {
                        name = writer.addDirectImageSimple(maskImage);
                        prs.addXObject(name, writer.getImageReference(name));
                    }
                    name = writer.addDirectImageSimple(image);
                    name = prs.addXObject(name, writer.getImageReference(name));
                    content.append(' ').append(name.getBytes()).append(" Do Q").append_i(separator);
                }
            }
            if(image.hasBorders())
            {
                saveState();
                float w = image.getWidth();
                float h = image.getHeight();
                concatCTM(a / w, b / w, c / h, d / h, e, f);
                rectangle(image);
                restoreState();
            }
            if(image.getLayer() != null)
                endLayer();
            annot = image.getAnnotation();
            if(annot == null)
                return;
        }
        catch(Exception ee)
        {
            throw new DocumentException(ee);
        }
        r = new float[unitRect.length];
        for(int k = 0; k < unitRect.length; k += 2)
        {
            r[k] = a * unitRect[k] + c * unitRect[k + 1] + e;
            r[k + 1] = b * unitRect[k] + d * unitRect[k + 1] + f;
        }

        llx = r[0];
        lly = r[1];
        urx = llx;
        ury = lly;
        for(int k = 2; k < r.length; k += 2)
        {
            llx = Math.min(llx, r[k]);
            lly = Math.min(lly, r[k + 1]);
            urx = Math.max(urx, r[k]);
            ury = Math.max(ury, r[k + 1]);
        }

        annot = new Annotation(annot);
        annot.setDimensions(llx, lly, urx, ury);
        an = PdfAnnotationsImp.convertAnnotation(writer, annot, new Rectangle(llx, lly, urx, ury));
        if(an == null)
            return;
        addAnnotation(an);
    }

    public void reset()
    {
        reset(true);
    }

    public void reset(boolean validateContent)
    {
        content.reset();
        markedContentSize = 0;
        if(validateContent)
            sanityCheck();
        state = new GraphicState();
        stateList = new ArrayList();
    }

    protected void beginText(boolean restoreTM)
    {
        if(inText)
        {
            if(!isTagged())
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.begin.end.text.operators", new Object[0]));
        } else
        {
            inText = true;
            content.append("BT").append_i(separator);
            if(restoreTM)
            {
                float xTLM = state.xTLM;
                float tx = state.tx;
                setTextMatrix(state.aTLM, state.bTLM, state.cTLM, state.dTLM, state.tx, state.yTLM);
                state.xTLM = xTLM;
                state.tx = tx;
            } else
            {
                state.xTLM = 0.0F;
                state.yTLM = 0.0F;
                state.tx = 0.0F;
            }
            if(isTagged())
                try
                {
                    restoreColor();
                }
                catch(IOException ioe) { }
        }
    }

    public void beginText()
    {
        beginText(false);
    }

    public void endText()
    {
        if(!inText)
        {
            if(!isTagged())
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.begin.end.text.operators", new Object[0]));
        } else
        {
            inText = false;
            content.append("ET").append_i(separator);
            if(isTagged())
                try
                {
                    restoreColor();
                }
                catch(IOException ioe) { }
        }
    }

    public void saveState()
    {
        PdfWriter.checkPdfIsoConformance(writer, 12, "q");
        if(inText && isTagged())
            endText();
        content.append("q").append_i(separator);
        stateList.add(new GraphicState(state));
    }

    public void restoreState()
    {
        PdfWriter.checkPdfIsoConformance(writer, 12, "Q");
        if(inText && isTagged())
            endText();
        content.append("Q").append_i(separator);
        int idx = stateList.size() - 1;
        if(idx < 0)
        {
            throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.save.restore.state.operators", new Object[0]));
        } else
        {
            state.restore((GraphicState)stateList.get(idx));
            stateList.remove(idx);
            return;
        }
    }

    public void setCharacterSpacing(float charSpace)
    {
        if(!inText && isTagged())
            beginText(true);
        state.charSpace = charSpace;
        content.append(charSpace).append(" Tc").append_i(separator);
    }

    public void setWordSpacing(float wordSpace)
    {
        if(!inText && isTagged())
            beginText(true);
        state.wordSpace = wordSpace;
        content.append(wordSpace).append(" Tw").append_i(separator);
    }

    public void setHorizontalScaling(float scale)
    {
        if(!inText && isTagged())
            beginText(true);
        state.scale = scale;
        content.append(scale).append(" Tz").append_i(separator);
    }

    public void setLeading(float leading)
    {
        if(!inText && isTagged())
            beginText(true);
        state.leading = leading;
        content.append(leading).append(" TL").append_i(separator);
    }

    public void setFontAndSize(BaseFont bf, float size)
    {
        if(!inText && isTagged())
            beginText(true);
        checkWriter();
        if(size < 0.0001F && size > -0.0001F)
        {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("font.size.too.small.1", new Object[] {
                String.valueOf(size)
            }));
        } else
        {
            state.size = size;
            state.fontDetails = writer.addSimple(bf);
            PageResources prs = getPageResources();
            PdfName name = state.fontDetails.getFontName();
            name = prs.addFont(name, state.fontDetails.getIndirectReference());
            content.append(name.getBytes()).append(' ').append(size).append(" Tf").append_i(separator);
            return;
        }
    }

    public void setTextRenderingMode(int rendering)
    {
        if(!inText && isTagged())
            beginText(true);
        content.append(rendering).append(" Tr").append_i(separator);
    }

    public void setTextRise(float rise)
    {
        if(!inText && isTagged())
            beginText(true);
        content.append(rise).append(" Ts").append_i(separator);
    }

    private void showText2(String text)
    {
        if(state.fontDetails == null)
        {
            throw new NullPointerException(MessageLocalization.getComposedMessage("font.and.size.must.be.set.before.writing.any.text", new Object[0]));
        } else
        {
            byte b[] = state.fontDetails.convertToBytes(text);
            escapeString(b, content);
            return;
        }
    }

    public void showText(String text)
    {
        if(!inText && isTagged())
            beginText(true);
        showText2(text);
        updateTx(text, 0.0F);
        content.append("Tj").append_i(separator);
    }

    public static PdfTextArray getKernArray(String text, BaseFont font)
    {
        PdfTextArray pa = new PdfTextArray();
        StringBuffer acc = new StringBuffer();
        int len = text.length() - 1;
        char c[] = text.toCharArray();
        if(len >= 0)
            acc.append(c, 0, 1);
        for(int k = 0; k < len; k++)
        {
            char c2 = c[k + 1];
            int kern = font.getKerning(c[k], c2);
            if(kern == 0)
            {
                acc.append(c2);
            } else
            {
                pa.add(acc.toString());
                acc.setLength(0);
                acc.append(c, k + 1, 1);
                pa.add(-kern);
            }
        }

        pa.add(acc.toString());
        return pa;
    }

    public void showTextKerned(String text)
    {
        if(state.fontDetails == null)
            throw new NullPointerException(MessageLocalization.getComposedMessage("font.and.size.must.be.set.before.writing.any.text", new Object[0]));
        BaseFont bf = state.fontDetails.getBaseFont();
        if(bf.hasKernPairs())
            showText(getKernArray(text, bf));
        else
            showText(text);
    }

    public void newlineShowText(String text)
    {
        if(!inText && isTagged())
            beginText(true);
        state.yTLM -= state.leading;
        showText2(text);
        content.append("'").append_i(separator);
        state.tx = state.xTLM;
        updateTx(text, 0.0F);
    }

    public void newlineShowText(float wordSpacing, float charSpacing, String text)
    {
        if(!inText && isTagged())
            beginText(true);
        state.yTLM -= state.leading;
        content.append(wordSpacing).append(' ').append(charSpacing);
        showText2(text);
        content.append("\"").append_i(separator);
        state.charSpace = charSpacing;
        state.wordSpace = wordSpacing;
        state.tx = state.xTLM;
        updateTx(text, 0.0F);
    }

    public void setTextMatrix(float a, float b, float c, float d, float x, float y)
    {
        if(!inText && isTagged())
            beginText(true);
        state.xTLM = x;
        state.yTLM = y;
        state.aTLM = a;
        state.bTLM = b;
        state.cTLM = c;
        state.dTLM = d;
        state.tx = state.xTLM;
        content.append(a).append(' ').append(b).append_i(32).append(c).append_i(32).append(d).append_i(32).append(x).append_i(32).append(y).append(" Tm").append_i(separator);
    }

    public void setTextMatrix(AffineTransform transform)
    {
        double matrix[] = new double[6];
        transform.getMatrix(matrix);
        setTextMatrix((float)matrix[0], (float)matrix[1], (float)matrix[2], (float)matrix[3], (float)matrix[4], (float)matrix[5]);
    }

    public void setTextMatrix(float x, float y)
    {
        setTextMatrix(1.0F, 0.0F, 0.0F, 1.0F, x, y);
    }

    public void moveText(float x, float y)
    {
        if(!inText && isTagged())
            beginText(true);
        state.xTLM += x;
        state.yTLM += y;
        if(isTagged() && state.xTLM != state.tx)
            setTextMatrix(state.aTLM, state.bTLM, state.cTLM, state.dTLM, state.xTLM, state.yTLM);
        else
            content.append(x).append(' ').append(y).append(" Td").append_i(separator);
    }

    public void moveTextWithLeading(float x, float y)
    {
        if(!inText && isTagged())
            beginText(true);
        state.xTLM += x;
        state.yTLM += y;
        state.leading = -y;
        if(isTagged() && state.xTLM != state.tx)
            setTextMatrix(state.aTLM, state.bTLM, state.cTLM, state.dTLM, state.xTLM, state.yTLM);
        else
            content.append(x).append(' ').append(y).append(" TD").append_i(separator);
    }

    public void newlineText()
    {
        if(!inText && isTagged())
            beginText(true);
        if(isTagged() && state.xTLM != state.tx)
            setTextMatrix(state.aTLM, state.bTLM, state.cTLM, state.dTLM, state.xTLM, state.yTLM);
        state.yTLM -= state.leading;
        content.append("T*").append_i(separator);
    }

    int size()
    {
        return size(true);
    }

    int size(boolean includeMarkedContentSize)
    {
        if(includeMarkedContentSize)
            return content.size();
        else
            return content.size() - markedContentSize;
    }

    static byte[] escapeString(byte b[])
    {
        ByteBuffer content = new ByteBuffer();
        escapeString(b, content);
        return content.toByteArray();
    }

    static void escapeString(byte b[], ByteBuffer content)
    {
        content.append_i(40);
        for(int k = 0; k < b.length; k++)
        {
            byte c = b[k];
            switch(c)
            {
            case 13: // '\r'
                content.append("\\r");
                break;

            case 10: // '\n'
                content.append("\\n");
                break;

            case 9: // '\t'
                content.append("\\t");
                break;

            case 8: // '\b'
                content.append("\\b");
                break;

            case 12: // '\f'
                content.append("\\f");
                break;

            case 40: // '('
            case 41: // ')'
            case 92: // '\\'
                content.append_i(92).append_i(c);
                break;

            default:
                content.append_i(c);
                break;
            }
        }

        content.append(")");
    }

    public void addOutline(PdfOutline outline, String name)
    {
        checkWriter();
        pdf.addOutline(outline, name);
    }

    public PdfOutline getRootOutline()
    {
        checkWriter();
        return pdf.getRootOutline();
    }

    public float getEffectiveStringWidth(String text, boolean kerned)
    {
        BaseFont bf = state.fontDetails.getBaseFont();
        float w;
        if(kerned)
            w = bf.getWidthPointKerned(text, state.size);
        else
            w = bf.getWidthPoint(text, state.size);
        if(state.charSpace != 0.0F && text.length() > 1)
            w += state.charSpace * (float)(text.length() - 1);
        if(state.wordSpace != 0.0F && !bf.isVertical())
        {
            for(int i = 0; i < text.length() - 1; i++)
                if(text.charAt(i) == ' ')
                    w += state.wordSpace;

        }
        if((double)state.scale != 100D)
            w = (w * state.scale) / 100F;
        return w;
    }

    private float getEffectiveStringWidth(String text, boolean kerned, float kerning)
    {
        BaseFont bf = state.fontDetails.getBaseFont();
        float w;
        if(kerned)
            w = bf.getWidthPointKerned(text, state.size);
        else
            w = bf.getWidthPoint(text, state.size);
        if(state.charSpace != 0.0F && text.length() > 0)
            w += state.charSpace * (float)text.length();
        if(state.wordSpace != 0.0F && !bf.isVertical())
        {
            for(int i = 0; i < text.length(); i++)
                if(text.charAt(i) == ' ')
                    w += state.wordSpace;

        }
        w -= (kerning / 1000F) * state.size;
        if((double)state.scale != 100D)
            w = (w * state.scale) / 100F;
        return w;
    }

    public void showTextAligned(int alignment, String text, float x, float y, float rotation)
    {
        showTextAligned(alignment, text, x, y, rotation, false);
    }

    private void showTextAligned(int alignment, String text, float x, float y, float rotation, boolean kerned)
    {
        if(state.fontDetails == null)
            throw new NullPointerException(MessageLocalization.getComposedMessage("font.and.size.must.be.set.before.writing.any.text", new Object[0]));
        if(rotation == 0.0F)
        {
            switch(alignment)
            {
            case 1: // '\001'
                x -= getEffectiveStringWidth(text, kerned) / 2.0F;
                break;

            case 2: // '\002'
                x -= getEffectiveStringWidth(text, kerned);
                break;
            }
            setTextMatrix(x, y);
            if(kerned)
                showTextKerned(text);
            else
                showText(text);
        } else
        {
            double alpha = ((double)rotation * 3.1415926535897931D) / 180D;
            float cos = (float)Math.cos(alpha);
            float sin = (float)Math.sin(alpha);
            switch(alignment)
            {
            case 1: // '\001'
            {
                float len = getEffectiveStringWidth(text, kerned) / 2.0F;
                x -= len * cos;
                y -= len * sin;
                break;
            }

            case 2: // '\002'
            {
                float len = getEffectiveStringWidth(text, kerned);
                x -= len * cos;
                y -= len * sin;
                break;
            }
            }
            setTextMatrix(cos, sin, -sin, cos, x, y);
            if(kerned)
                showTextKerned(text);
            else
                showText(text);
            setTextMatrix(0.0F, 0.0F);
        }
    }

    public void showTextAlignedKerned(int alignment, String text, float x, float y, float rotation)
    {
        showTextAligned(alignment, text, x, y, rotation, true);
    }

    public void concatCTM(float a, float b, float c, float d, float e, float f)
    {
        if(inText && isTagged())
            endText();
        state.CTM.concatenate(new AffineTransform(a, b, c, d, e, f));
        content.append(a).append(' ').append(b).append(' ').append(c).append(' ');
        content.append(d).append(' ').append(e).append(' ').append(f).append(" cm").append_i(separator);
    }

    public void concatCTM(AffineTransform transform)
    {
        double matrix[] = new double[6];
        transform.getMatrix(matrix);
        concatCTM((float)matrix[0], (float)matrix[1], (float)matrix[2], (float)matrix[3], (float)matrix[4], (float)matrix[5]);
    }

    public static ArrayList bezierArc(float x1, float y1, float x2, float y2, float startAng, float extent)
    {
        if(x1 > x2)
        {
            float tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
        if(y2 > y1)
        {
            float tmp = y1;
            y1 = y2;
            y2 = tmp;
        }
        float fragAngle;
        int Nfrag;
        if(Math.abs(extent) <= 90F)
        {
            fragAngle = extent;
            Nfrag = 1;
        } else
        {
            Nfrag = (int)Math.ceil(Math.abs(extent) / 90F);
            fragAngle = extent / (float)Nfrag;
        }
        float x_cen = (x1 + x2) / 2.0F;
        float y_cen = (y1 + y2) / 2.0F;
        float rx = (x2 - x1) / 2.0F;
        float ry = (y2 - y1) / 2.0F;
        float halfAng = (float)(((double)fragAngle * 3.1415926535897931D) / 360D);
        float kappa = (float)Math.abs((1.3333333333333333D * (1.0D - Math.cos(halfAng))) / Math.sin(halfAng));
        ArrayList pointList = new ArrayList();
        for(int i = 0; i < Nfrag; i++)
        {
            float theta0 = (float)(((double)(startAng + (float)i * fragAngle) * 3.1415926535897931D) / 180D);
            float theta1 = (float)(((double)(startAng + (float)(i + 1) * fragAngle) * 3.1415926535897931D) / 180D);
            float cos0 = (float)Math.cos(theta0);
            float cos1 = (float)Math.cos(theta1);
            float sin0 = (float)Math.sin(theta0);
            float sin1 = (float)Math.sin(theta1);
            if(fragAngle > 0.0F)
                pointList.add(new float[] {
                    x_cen + rx * cos0, y_cen - ry * sin0, x_cen + rx * (cos0 - kappa * sin0), y_cen - ry * (sin0 + kappa * cos0), x_cen + rx * (cos1 + kappa * sin1), y_cen - ry * (sin1 - kappa * cos1), x_cen + rx * cos1, y_cen - ry * sin1
                });
            else
                pointList.add(new float[] {
                    x_cen + rx * cos0, y_cen - ry * sin0, x_cen + rx * (cos0 + kappa * sin0), y_cen - ry * (sin0 - kappa * cos0), x_cen + rx * (cos1 - kappa * sin1), y_cen - ry * (sin1 + kappa * cos1), x_cen + rx * cos1, y_cen - ry * sin1
                });
        }

        return pointList;
    }

    public void arc(float x1, float y1, float x2, float y2, float startAng, float extent)
    {
        ArrayList ar = bezierArc(x1, y1, x2, y2, startAng, extent);
        if(ar.isEmpty())
            return;
        float pt[] = (float[])ar.get(0);
        moveTo(pt[0], pt[1]);
        for(int k = 0; k < ar.size(); k++)
        {
            pt = (float[])ar.get(k);
            curveTo(pt[2], pt[3], pt[4], pt[5], pt[6], pt[7]);
        }

    }

    public void ellipse(float x1, float y1, float x2, float y2)
    {
        arc(x1, y1, x2, y2, 0.0F, 360F);
    }

    public PdfPatternPainter createPattern(float width, float height, float xstep, float ystep)
    {
        checkWriter();
        if(xstep == 0.0F || ystep == 0.0F)
        {
            throw new RuntimeException(MessageLocalization.getComposedMessage("xstep.or.ystep.can.not.be.zero", new Object[0]));
        } else
        {
            PdfPatternPainter painter = new PdfPatternPainter(writer);
            painter.setWidth(width);
            painter.setHeight(height);
            painter.setXStep(xstep);
            painter.setYStep(ystep);
            writer.addSimplePattern(painter);
            return painter;
        }
    }

    public PdfPatternPainter createPattern(float width, float height)
    {
        return createPattern(width, height, width, height);
    }

    public PdfPatternPainter createPattern(float width, float height, float xstep, float ystep, BaseColor color)
    {
        checkWriter();
        if(xstep == 0.0F || ystep == 0.0F)
        {
            throw new RuntimeException(MessageLocalization.getComposedMessage("xstep.or.ystep.can.not.be.zero", new Object[0]));
        } else
        {
            PdfPatternPainter painter = new PdfPatternPainter(writer, color);
            painter.setWidth(width);
            painter.setHeight(height);
            painter.setXStep(xstep);
            painter.setYStep(ystep);
            writer.addSimplePattern(painter);
            return painter;
        }
    }

    public PdfPatternPainter createPattern(float width, float height, BaseColor color)
    {
        return createPattern(width, height, width, height, color);
    }

    public PdfTemplate createTemplate(float width, float height)
    {
        return createTemplate(width, height, null);
    }

    PdfTemplate createTemplate(float width, float height, PdfName forcedName)
    {
        checkWriter();
        PdfTemplate template = new PdfTemplate(writer);
        template.setWidth(width);
        template.setHeight(height);
        writer.addDirectTemplateSimple(template, forcedName);
        return template;
    }

    public PdfAppearance createAppearance(float width, float height)
    {
        return createAppearance(width, height, null);
    }

    PdfAppearance createAppearance(float width, float height, PdfName forcedName)
    {
        checkWriter();
        PdfAppearance template = new PdfAppearance(writer);
        template.setWidth(width);
        template.setHeight(height);
        writer.addDirectTemplateSimple(template, forcedName);
        return template;
    }

    public void addPSXObject(PdfPSXObject psobject)
    {
        if(inText && isTagged())
            endText();
        checkWriter();
        PdfName name = writer.addDirectTemplateSimple(psobject, null);
        PageResources prs = getPageResources();
        name = prs.addXObject(name, psobject.getIndirectReference());
        content.append(name.getBytes()).append(" Do").append_i(separator);
    }

    public void addTemplate(PdfTemplate template, float a, float b, float c, float d, float e, float f)
    {
        addTemplate(template, a, b, c, d, e, f, false);
    }

    public void addTemplate(PdfTemplate template, float a, float b, float c, float d, float e, float f, 
            boolean tagContent)
    {
        checkWriter();
        checkNoPattern(template);
        PdfName name = writer.addDirectTemplateSimple(template, null);
        PageResources prs = getPageResources();
        name = prs.addXObject(name, template.getIndirectReference());
        if(isTagged())
        {
            if(inText)
                endText();
            if(template.isContentTagged() || template.getPageReference() != null && tagContent)
                throw new RuntimeException(MessageLocalization.getComposedMessage("template.with.tagged.could.not.be.used.more.than.once", new Object[0]));
            template.setPageReference(writer.getCurrentPage());
            if(tagContent)
            {
                template.setContentTagged(true);
                ArrayList allMcElements = getMcElements();
                if(allMcElements != null && allMcElements.size() > 0)
                    template.getMcElements().add(allMcElements.get(allMcElements.size() - 1));
            } else
            {
                openMCBlock(template);
            }
        }
        content.append("q ");
        content.append(a).append(' ');
        content.append(b).append(' ');
        content.append(c).append(' ');
        content.append(d).append(' ');
        content.append(e).append(' ');
        content.append(f).append(" cm ");
        content.append(name.getBytes()).append(" Do Q").append_i(separator);
        if(isTagged() && !tagContent)
        {
            closeMCBlock(template);
            template.setId(null);
        }
    }

    public void addTemplate(PdfTemplate template, AffineTransform transform)
    {
        addTemplate(template, transform, false);
    }

    public void addTemplate(PdfTemplate template, AffineTransform transform, boolean tagContent)
    {
        double matrix[] = new double[6];
        transform.getMatrix(matrix);
        addTemplate(template, (float)matrix[0], (float)matrix[1], (float)matrix[2], (float)matrix[3], (float)matrix[4], (float)matrix[5], tagContent);
    }

    void addTemplateReference(PdfIndirectReference template, PdfName name, float a, float b, float c, float d, float e, 
            float f)
    {
        if(inText && isTagged())
            endText();
        checkWriter();
        PageResources prs = getPageResources();
        name = prs.addXObject(name, template);
        content.append("q ");
        content.append(a).append(' ');
        content.append(b).append(' ');
        content.append(c).append(' ');
        content.append(d).append(' ');
        content.append(e).append(' ');
        content.append(f).append(" cm ");
        content.append(name.getBytes()).append(" Do Q").append_i(separator);
    }

    public void addTemplate(PdfTemplate template, float x, float y)
    {
        addTemplate(template, 1.0F, 0.0F, 0.0F, 1.0F, x, y);
    }

    public void addTemplate(PdfTemplate template, float x, float y, boolean tagContent)
    {
        addTemplate(template, 1.0F, 0.0F, 0.0F, 1.0F, x, y, tagContent);
    }

    public void setCMYKColorFill(int cyan, int magenta, int yellow, int black)
    {
        saveColor(new CMYKColor(cyan, magenta, yellow, black), true);
        content.append((float)(cyan & 0xff) / 255F);
        content.append(' ');
        content.append((float)(magenta & 0xff) / 255F);
        content.append(' ');
        content.append((float)(yellow & 0xff) / 255F);
        content.append(' ');
        content.append((float)(black & 0xff) / 255F);
        content.append(" k").append_i(separator);
    }

    public void setCMYKColorStroke(int cyan, int magenta, int yellow, int black)
    {
        saveColor(new CMYKColor(cyan, magenta, yellow, black), false);
        content.append((float)(cyan & 0xff) / 255F);
        content.append(' ');
        content.append((float)(magenta & 0xff) / 255F);
        content.append(' ');
        content.append((float)(yellow & 0xff) / 255F);
        content.append(' ');
        content.append((float)(black & 0xff) / 255F);
        content.append(" K").append_i(separator);
    }

    public void setRGBColorFill(int red, int green, int blue)
    {
        saveColor(new BaseColor(red, green, blue), true);
        HelperRGB((float)(red & 0xff) / 255F, (float)(green & 0xff) / 255F, (float)(blue & 0xff) / 255F);
        content.append(" rg").append_i(separator);
    }

    public void setRGBColorStroke(int red, int green, int blue)
    {
        saveColor(new BaseColor(red, green, blue), false);
        HelperRGB((float)(red & 0xff) / 255F, (float)(green & 0xff) / 255F, (float)(blue & 0xff) / 255F);
        content.append(" RG").append_i(separator);
    }

    public void setColorStroke(BaseColor color)
    {
        PdfWriter.checkPdfIsoConformance(writer, 1, color);
        int type = ExtendedColor.getType(color);
        switch(type)
        {
        case 1: // '\001'
            setGrayStroke(((GrayColor)color).getGray());
            break;

        case 2: // '\002'
            CMYKColor cmyk = (CMYKColor)color;
            setCMYKColorStrokeF(cmyk.getCyan(), cmyk.getMagenta(), cmyk.getYellow(), cmyk.getBlack());
            break;

        case 3: // '\003'
            SpotColor spot = (SpotColor)color;
            setColorStroke(spot.getPdfSpotColor(), spot.getTint());
            break;

        case 4: // '\004'
            PatternColor pat = (PatternColor)color;
            setPatternStroke(pat.getPainter());
            break;

        case 5: // '\005'
            ShadingColor shading = (ShadingColor)color;
            setShadingStroke(shading.getPdfShadingPattern());
            break;

        default:
            setRGBColorStroke(color.getRed(), color.getGreen(), color.getBlue());
            break;
        }
    }

    public void setColorFill(BaseColor color)
    {
        PdfWriter.checkPdfIsoConformance(writer, 1, color);
        int type = ExtendedColor.getType(color);
        switch(type)
        {
        case 1: // '\001'
            setGrayFill(((GrayColor)color).getGray());
            break;

        case 2: // '\002'
            CMYKColor cmyk = (CMYKColor)color;
            setCMYKColorFillF(cmyk.getCyan(), cmyk.getMagenta(), cmyk.getYellow(), cmyk.getBlack());
            break;

        case 3: // '\003'
            SpotColor spot = (SpotColor)color;
            setColorFill(spot.getPdfSpotColor(), spot.getTint());
            break;

        case 4: // '\004'
            PatternColor pat = (PatternColor)color;
            setPatternFill(pat.getPainter());
            break;

        case 5: // '\005'
            ShadingColor shading = (ShadingColor)color;
            setShadingFill(shading.getPdfShadingPattern());
            break;

        default:
            setRGBColorFill(color.getRed(), color.getGreen(), color.getBlue());
            break;
        }
    }

    public void setColorFill(PdfSpotColor sp, float tint)
    {
        checkWriter();
        state.colorDetails = writer.addSimple(sp);
        PageResources prs = getPageResources();
        PdfName name = state.colorDetails.getColorName();
        name = prs.addColor(name, state.colorDetails.getIndirectReference());
        saveColor(new SpotColor(sp, tint), true);
        content.append(name.getBytes()).append(" cs ").append(tint).append(" scn").append_i(separator);
    }

    public void setColorStroke(PdfSpotColor sp, float tint)
    {
        checkWriter();
        state.colorDetails = writer.addSimple(sp);
        PageResources prs = getPageResources();
        PdfName name = state.colorDetails.getColorName();
        name = prs.addColor(name, state.colorDetails.getIndirectReference());
        saveColor(new SpotColor(sp, tint), false);
        content.append(name.getBytes()).append(" CS ").append(tint).append(" SCN").append_i(separator);
    }

    public void setPatternFill(PdfPatternPainter p)
    {
        if(p.isStencil())
        {
            setPatternFill(p, p.getDefaultColor());
            return;
        } else
        {
            checkWriter();
            PageResources prs = getPageResources();
            PdfName name = writer.addSimplePattern(p);
            name = prs.addPattern(name, p.getIndirectReference());
            saveColor(new PatternColor(p), true);
            content.append(PdfName.PATTERN.getBytes()).append(" cs ").append(name.getBytes()).append(" scn").append_i(separator);
            return;
        }
    }

    void outputColorNumbers(BaseColor color, float tint)
    {
        PdfWriter.checkPdfIsoConformance(writer, 1, color);
        int type = ExtendedColor.getType(color);
        switch(type)
        {
        case 0: // '\0'
            content.append((float)color.getRed() / 255F);
            content.append(' ');
            content.append((float)color.getGreen() / 255F);
            content.append(' ');
            content.append((float)color.getBlue() / 255F);
            break;

        case 1: // '\001'
            content.append(((GrayColor)color).getGray());
            break;

        case 2: // '\002'
            CMYKColor cmyk = (CMYKColor)color;
            content.append(cmyk.getCyan()).append(' ').append(cmyk.getMagenta());
            content.append(' ').append(cmyk.getYellow()).append(' ').append(cmyk.getBlack());
            break;

        case 3: // '\003'
            content.append(tint);
            break;

        default:
            throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.color.type", new Object[0]));
        }
    }

    public void setPatternFill(PdfPatternPainter p, BaseColor color)
    {
        if(ExtendedColor.getType(color) == 3)
            setPatternFill(p, color, ((SpotColor)color).getTint());
        else
            setPatternFill(p, color, 0.0F);
    }

    public void setPatternFill(PdfPatternPainter p, BaseColor color, float tint)
    {
        checkWriter();
        if(!p.isStencil())
        {
            throw new RuntimeException(MessageLocalization.getComposedMessage("an.uncolored.pattern.was.expected", new Object[0]));
        } else
        {
            PageResources prs = getPageResources();
            PdfName name = writer.addSimplePattern(p);
            name = prs.addPattern(name, p.getIndirectReference());
            ColorDetails csDetail = writer.addSimplePatternColorspace(color);
            PdfName cName = prs.addColor(csDetail.getColorName(), csDetail.getIndirectReference());
            saveColor(new UncoloredPattern(p, color, tint), true);
            content.append(cName.getBytes()).append(" cs").append_i(separator);
            outputColorNumbers(color, tint);
            content.append(' ').append(name.getBytes()).append(" scn").append_i(separator);
            return;
        }
    }

    public void setPatternStroke(PdfPatternPainter p, BaseColor color)
    {
        if(ExtendedColor.getType(color) == 3)
            setPatternStroke(p, color, ((SpotColor)color).getTint());
        else
            setPatternStroke(p, color, 0.0F);
    }

    public void setPatternStroke(PdfPatternPainter p, BaseColor color, float tint)
    {
        checkWriter();
        if(!p.isStencil())
        {
            throw new RuntimeException(MessageLocalization.getComposedMessage("an.uncolored.pattern.was.expected", new Object[0]));
        } else
        {
            PageResources prs = getPageResources();
            PdfName name = writer.addSimplePattern(p);
            name = prs.addPattern(name, p.getIndirectReference());
            ColorDetails csDetail = writer.addSimplePatternColorspace(color);
            PdfName cName = prs.addColor(csDetail.getColorName(), csDetail.getIndirectReference());
            saveColor(new UncoloredPattern(p, color, tint), false);
            content.append(cName.getBytes()).append(" CS").append_i(separator);
            outputColorNumbers(color, tint);
            content.append(' ').append(name.getBytes()).append(" SCN").append_i(separator);
            return;
        }
    }

    public void setPatternStroke(PdfPatternPainter p)
    {
        if(p.isStencil())
        {
            setPatternStroke(p, p.getDefaultColor());
            return;
        } else
        {
            checkWriter();
            PageResources prs = getPageResources();
            PdfName name = writer.addSimplePattern(p);
            name = prs.addPattern(name, p.getIndirectReference());
            saveColor(new PatternColor(p), false);
            content.append(PdfName.PATTERN.getBytes()).append(" CS ").append(name.getBytes()).append(" SCN").append_i(separator);
            return;
        }
    }

    public void paintShading(PdfShading shading)
    {
        writer.addSimpleShading(shading);
        PageResources prs = getPageResources();
        PdfName name = prs.addShading(shading.getShadingName(), shading.getShadingReference());
        content.append(name.getBytes()).append(" sh").append_i(separator);
        ColorDetails details = shading.getColorDetails();
        if(details != null)
            prs.addColor(details.getColorName(), details.getIndirectReference());
    }

    public void paintShading(PdfShadingPattern shading)
    {
        paintShading(shading.getShading());
    }

    public void setShadingFill(PdfShadingPattern shading)
    {
        writer.addSimpleShadingPattern(shading);
        PageResources prs = getPageResources();
        PdfName name = prs.addPattern(shading.getPatternName(), shading.getPatternReference());
        saveColor(new ShadingColor(shading), true);
        content.append(PdfName.PATTERN.getBytes()).append(" cs ").append(name.getBytes()).append(" scn").append_i(separator);
        ColorDetails details = shading.getColorDetails();
        if(details != null)
            prs.addColor(details.getColorName(), details.getIndirectReference());
    }

    public void setShadingStroke(PdfShadingPattern shading)
    {
        writer.addSimpleShadingPattern(shading);
        PageResources prs = getPageResources();
        PdfName name = prs.addPattern(shading.getPatternName(), shading.getPatternReference());
        saveColor(new ShadingColor(shading), false);
        content.append(PdfName.PATTERN.getBytes()).append(" CS ").append(name.getBytes()).append(" SCN").append_i(separator);
        ColorDetails details = shading.getColorDetails();
        if(details != null)
            prs.addColor(details.getColorName(), details.getIndirectReference());
    }

    protected void checkWriter()
    {
        if(writer == null)
            throw new NullPointerException(MessageLocalization.getComposedMessage("the.writer.in.pdfcontentbyte.is.null", new Object[0]));
        else
            return;
    }

    public void showText(PdfTextArray text)
    {
        if(!inText && isTagged())
            beginText(true);
        if(state.fontDetails == null)
            throw new NullPointerException(MessageLocalization.getComposedMessage("font.and.size.must.be.set.before.writing.any.text", new Object[0]));
        content.append("[");
        ArrayList arrayList = text.getArrayList();
        boolean lastWasNumber = false;
        for(Iterator i$ = arrayList.iterator(); i$.hasNext();)
        {
            Object obj = i$.next();
            if(obj instanceof String)
            {
                showText2((String)obj);
                updateTx((String)obj, 0.0F);
                lastWasNumber = false;
            } else
            {
                if(lastWasNumber)
                    content.append(' ');
                else
                    lastWasNumber = true;
                content.append(((Float)obj).floatValue());
                updateTx("", ((Float)obj).floatValue());
            }
        }

        content.append("]TJ").append_i(separator);
    }

    public PdfWriter getPdfWriter()
    {
        return writer;
    }

    public PdfDocument getPdfDocument()
    {
        return pdf;
    }

    public void localGoto(String name, float llx, float lly, float urx, float ury)
    {
        pdf.localGoto(name, llx, lly, urx, ury);
    }

    public boolean localDestination(String name, PdfDestination destination)
    {
        return pdf.localDestination(name, destination);
    }

    public PdfContentByte getDuplicate()
    {
        PdfContentByte cb = new PdfContentByte(writer);
        cb.duplicatedFrom = this;
        return cb;
    }

    public PdfContentByte getDuplicate(boolean inheritGraphicState)
    {
        PdfContentByte cb = getDuplicate();
        if(inheritGraphicState)
        {
            cb.state = state;
            cb.stateList = stateList;
        }
        return cb;
    }

    public void remoteGoto(String filename, String name, float llx, float lly, float urx, float ury)
    {
        pdf.remoteGoto(filename, name, llx, lly, urx, ury);
    }

    public void remoteGoto(String filename, int page, float llx, float lly, float urx, float ury)
    {
        pdf.remoteGoto(filename, page, llx, lly, urx, ury);
    }

    public void roundRectangle(float x, float y, float w, float h, float r)
    {
        if(w < 0.0F)
        {
            x += w;
            w = -w;
        }
        if(h < 0.0F)
        {
            y += h;
            h = -h;
        }
        if(r < 0.0F)
            r = -r;
        float b = 0.4477F;
        moveTo(x + r, y);
        lineTo((x + w) - r, y);
        curveTo((x + w) - r * b, y, x + w, y + r * b, x + w, y + r);
        lineTo(x + w, (y + h) - r);
        curveTo(x + w, (y + h) - r * b, (x + w) - r * b, y + h, (x + w) - r, y + h);
        lineTo(x + r, y + h);
        curveTo(x + r * b, y + h, x, (y + h) - r * b, x, (y + h) - r);
        lineTo(x, y + r);
        curveTo(x, y + r * b, x + r * b, y, x + r, y);
    }

    public void setAction(PdfAction action, float llx, float lly, float urx, float ury)
    {
        pdf.setAction(action, llx, lly, urx, ury);
    }

    public void setLiteral(String s)
    {
        content.append(s);
    }

    public void setLiteral(char c)
    {
        content.append(c);
    }

    public void setLiteral(float n)
    {
        content.append(n);
    }

    void checkNoPattern(PdfTemplate t)
    {
        if(t.getType() == 3)
            throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.use.of.a.pattern.a.template.was.expected", new Object[0]));
        else
            return;
    }

    public void drawRadioField(float llx, float lly, float urx, float ury, boolean on)
    {
        if(llx > urx)
        {
            float x = llx;
            llx = urx;
            urx = x;
        }
        if(lly > ury)
        {
            float y = lly;
            lly = ury;
            ury = y;
        }
        saveState();
        setLineWidth(1.0F);
        setLineCap(1);
        setColorStroke(new BaseColor(192, 192, 192));
        arc(llx + 1.0F, lly + 1.0F, urx - 1.0F, ury - 1.0F, 0.0F, 360F);
        stroke();
        setLineWidth(1.0F);
        setLineCap(1);
        setColorStroke(new BaseColor(160, 160, 160));
        arc(llx + 0.5F, lly + 0.5F, urx - 0.5F, ury - 0.5F, 45F, 180F);
        stroke();
        setLineWidth(1.0F);
        setLineCap(1);
        setColorStroke(new BaseColor(0, 0, 0));
        arc(llx + 1.5F, lly + 1.5F, urx - 1.5F, ury - 1.5F, 45F, 180F);
        stroke();
        if(on)
        {
            setLineWidth(1.0F);
            setLineCap(1);
            setColorFill(new BaseColor(0, 0, 0));
            arc(llx + 4F, lly + 4F, urx - 4F, ury - 4F, 0.0F, 360F);
            fill();
        }
        restoreState();
    }

    public void drawTextField(float llx, float lly, float urx, float ury)
    {
        if(llx > urx)
        {
            float x = llx;
            llx = urx;
            urx = x;
        }
        if(lly > ury)
        {
            float y = lly;
            lly = ury;
            ury = y;
        }
        saveState();
        setColorStroke(new BaseColor(192, 192, 192));
        setLineWidth(1.0F);
        setLineCap(0);
        rectangle(llx, lly, urx - llx, ury - lly);
        stroke();
        setLineWidth(1.0F);
        setLineCap(0);
        setColorFill(new BaseColor(255, 255, 255));
        rectangle(llx + 0.5F, lly + 0.5F, urx - llx - 1.0F, ury - lly - 1.0F);
        fill();
        setColorStroke(new BaseColor(192, 192, 192));
        setLineWidth(1.0F);
        setLineCap(0);
        moveTo(llx + 1.0F, lly + 1.5F);
        lineTo(urx - 1.5F, lly + 1.5F);
        lineTo(urx - 1.5F, ury - 1.0F);
        stroke();
        setColorStroke(new BaseColor(160, 160, 160));
        setLineWidth(1.0F);
        setLineCap(0);
        moveTo(llx + 1.0F, lly + 1.0F);
        lineTo(llx + 1.0F, ury - 1.0F);
        lineTo(urx - 1.0F, ury - 1.0F);
        stroke();
        setColorStroke(new BaseColor(0, 0, 0));
        setLineWidth(1.0F);
        setLineCap(0);
        moveTo(llx + 2.0F, lly + 2.0F);
        lineTo(llx + 2.0F, ury - 2.0F);
        lineTo(urx - 2.0F, ury - 2.0F);
        stroke();
        restoreState();
    }

    public void drawButton(float llx, float lly, float urx, float ury, String text, BaseFont bf, float size)
    {
        if(llx > urx)
        {
            float x = llx;
            llx = urx;
            urx = x;
        }
        if(lly > ury)
        {
            float y = lly;
            lly = ury;
            ury = y;
        }
        saveState();
        setColorStroke(new BaseColor(0, 0, 0));
        setLineWidth(1.0F);
        setLineCap(0);
        rectangle(llx, lly, urx - llx, ury - lly);
        stroke();
        setLineWidth(1.0F);
        setLineCap(0);
        setColorFill(new BaseColor(192, 192, 192));
        rectangle(llx + 0.5F, lly + 0.5F, urx - llx - 1.0F, ury - lly - 1.0F);
        fill();
        setColorStroke(new BaseColor(255, 255, 255));
        setLineWidth(1.0F);
        setLineCap(0);
        moveTo(llx + 1.0F, lly + 1.0F);
        lineTo(llx + 1.0F, ury - 1.0F);
        lineTo(urx - 1.0F, ury - 1.0F);
        stroke();
        setColorStroke(new BaseColor(160, 160, 160));
        setLineWidth(1.0F);
        setLineCap(0);
        moveTo(llx + 1.0F, lly + 1.0F);
        lineTo(urx - 1.0F, lly + 1.0F);
        lineTo(urx - 1.0F, ury - 1.0F);
        stroke();
        resetRGBColorFill();
        beginText();
        setFontAndSize(bf, size);
        showTextAligned(1, text, llx + (urx - llx) / 2.0F, lly + (ury - lly - size) / 2.0F, 0.0F);
        endText();
        restoreState();
    }

    PageResources getPageResources()
    {
        return pdf.getPageResources();
    }

    public void setGState(PdfGState gstate)
    {
        PdfObject obj[] = writer.addSimpleExtGState(gstate);
        PageResources prs = getPageResources();
        PdfName name = prs.addExtGState((PdfName)obj[0], (PdfIndirectReference)obj[1]);
        content.append(name.getBytes()).append(" gs").append_i(separator);
    }

    public void beginLayer(PdfOCG layer)
    {
        if((layer instanceof PdfLayer) && ((PdfLayer)layer).getTitle() != null)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("a.title.is.not.a.layer", new Object[0]));
        if(layerDepth == null)
            layerDepth = new ArrayList();
        if(layer instanceof PdfLayerMembership)
        {
            layerDepth.add(Integer.valueOf(1));
            beginLayer2(layer);
            return;
        }
        int n = 0;
        for(PdfLayer la = (PdfLayer)layer; la != null; la = la.getParent())
            if(la.getTitle() == null)
            {
                beginLayer2(la);
                n++;
            }

        layerDepth.add(Integer.valueOf(n));
    }

    private void beginLayer2(PdfOCG layer)
    {
        PdfName name = (PdfName)writer.addSimpleProperty(layer, layer.getRef())[0];
        PageResources prs = getPageResources();
        name = prs.addProperty(name, layer.getRef());
        content.append("/OC ").append(name.getBytes()).append(" BDC").append_i(separator);
    }

    public void endLayer()
    {
        int n = 1;
        if(layerDepth != null && !layerDepth.isEmpty())
        {
            n = ((Integer)layerDepth.get(layerDepth.size() - 1)).intValue();
            layerDepth.remove(layerDepth.size() - 1);
        } else
        {
            throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.layer.operators", new Object[0]));
        }
        while(n-- > 0) 
            content.append("EMC").append_i(separator);
    }

    public void transform(AffineTransform af)
    {
        if(inText && isTagged())
            endText();
        double matrix[] = new double[6];
        af.getMatrix(matrix);
        state.CTM.concatenate(af);
        content.append(matrix[0]).append(' ').append(matrix[1]).append(' ').append(matrix[2]).append(' ');
        content.append(matrix[3]).append(' ').append(matrix[4]).append(' ').append(matrix[5]).append(" cm").append_i(separator);
    }

    void addAnnotation(PdfAnnotation annot)
    {
        writer.addAnnotation(annot);
    }

    void addAnnotation(PdfAnnotation annot, boolean applyCTM)
    {
        if(applyCTM && state.CTM.getType() != 0)
            annot.applyCTM(state.CTM);
        addAnnotation(annot);
    }

    public void setDefaultColorspace(PdfName name, PdfObject obj)
    {
        PageResources prs = getPageResources();
        prs.addDefaultColor(name, obj);
    }

    public void beginMarkedContentSequence(PdfStructureElement struc)
    {
        PdfObject obj = struc.get(PdfName.K);
        int structParentMarkPoint[] = pdf.getStructParentIndexAndNextMarkPoint(getCurrentPage());
        int structParent = structParentMarkPoint[0];
        int mark = structParentMarkPoint[1];
        if(obj != null)
        {
            PdfArray ar = null;
            if(obj.isNumber())
            {
                ar = new PdfArray();
                ar.add(obj);
                struc.put(PdfName.K, ar);
            } else
            if(obj.isArray())
                ar = (PdfArray)obj;
            else
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("unknown.object.at.k.1", new Object[] {
                    obj.getClass().toString()
                }));
            if(ar.getAsNumber(0) != null)
            {
                PdfDictionary dic = new PdfDictionary(PdfName.MCR);
                dic.put(PdfName.PG, getCurrentPage());
                dic.put(PdfName.MCID, new PdfNumber(mark));
                ar.add(dic);
            }
            struc.setPageMark(pdf.getStructParentIndex(getCurrentPage()), -1);
        } else
        {
            struc.setPageMark(structParent, mark);
            struc.put(PdfName.PG, getCurrentPage());
        }
        setMcDepth(getMcDepth() + 1);
        int contentSize = content.size();
        content.append(struc.get(PdfName.S).getBytes()).append(" <</MCID ").append(mark).append(">> BDC").append_i(separator);
        markedContentSize += content.size() - contentSize;
    }

    protected PdfIndirectReference getCurrentPage()
    {
        return writer.getCurrentPage();
    }

    public void endMarkedContentSequence()
    {
        if(getMcDepth() == 0)
        {
            throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.begin.end.marked.content.operators", new Object[0]));
        } else
        {
            int contentSize = content.size();
            setMcDepth(getMcDepth() - 1);
            content.append("EMC").append_i(separator);
            markedContentSize += content.size() - contentSize;
            return;
        }
    }

    public void beginMarkedContentSequence(PdfName tag, PdfDictionary property, boolean inline)
    {
        int contentSize = content.size();
        if(property == null)
        {
            content.append(tag.getBytes()).append(" BMC").append_i(separator);
            setMcDepth(getMcDepth() + 1);
        } else
        {
            content.append(tag.getBytes()).append(' ');
            if(inline)
            {
                try
                {
                    property.toPdf(writer, content);
                }
                catch(Exception e)
                {
                    throw new ExceptionConverter(e);
                }
            } else
            {
                PdfObject objs[];
                if(writer.propertyExists(property))
                    objs = writer.addSimpleProperty(property, null);
                else
                    objs = writer.addSimpleProperty(property, writer.getPdfIndirectReference());
                PdfName name = (PdfName)objs[0];
                PageResources prs = getPageResources();
                name = prs.addProperty(name, (PdfIndirectReference)objs[1]);
                content.append(name.getBytes());
            }
            content.append(" BDC").append_i(separator);
            setMcDepth(getMcDepth() + 1);
        }
        markedContentSize += content.size() - contentSize;
    }

    public void beginMarkedContentSequence(PdfName tag)
    {
        beginMarkedContentSequence(tag, null, false);
    }

    public void sanityCheck()
    {
        if(getMcDepth() != 0)
            throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.marked.content.operators", new Object[0]));
        if(inText)
            if(isTagged())
                endText();
            else
                throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.begin.end.text.operators", new Object[0]));
        if(layerDepth != null && !layerDepth.isEmpty())
            throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.layer.operators", new Object[0]));
        if(!stateList.isEmpty())
            throw new IllegalPdfSyntaxException(MessageLocalization.getComposedMessage("unbalanced.save.restore.state.operators", new Object[0]));
        else
            return;
    }

    public void openMCBlock(IAccessibleElement element)
    {
        if(isTagged())
        {
            if(pdf.openMCDocument)
            {
                pdf.openMCDocument = false;
                writer.getDirectContentUnder().openMCBlock(((IAccessibleElement) (pdf)));
            }
            if(element != null && !getMcElements().contains(element))
            {
                PdfStructureElement structureElement = openMCBlockInt(element);
                getMcElements().add(element);
                if(structureElement != null)
                    pdf.structElements.put(element.getId(), structureElement);
            }
        }
    }

    private PdfDictionary getParentStructureElement()
    {
        PdfDictionary parent = null;
        if(getMcElements().size() > 0)
            parent = (PdfDictionary)pdf.structElements.get(((IAccessibleElement)getMcElements().get(getMcElements().size() - 1)).getId());
        if(parent == null)
            parent = writer.getStructureTreeRoot();
        return parent;
    }

    private PdfStructureElement openMCBlockInt(IAccessibleElement element)
    {
        PdfStructureElement structureElement = null;
        if(isTagged())
        {
            IAccessibleElement parent = null;
            if(getMcElements().size() > 0)
                parent = (IAccessibleElement)getMcElements().get(getMcElements().size() - 1);
            if(parent != null && (parent.getRole() == null || PdfName.ARTIFACT.equals(parent.getRole())))
                element.setRole(null);
            if(element.getRole() != null)
            {
                if(!PdfName.ARTIFACT.equals(element.getRole()))
                {
                    structureElement = (PdfStructureElement)pdf.structElements.get(element.getId());
                    if(structureElement == null)
                    {
                        structureElement = new PdfStructureElement(getParentStructureElement(), element.getRole());
                        structureElement.writeAttributes(element);
                    }
                }
                boolean inTextLocal = inText;
                if(inText)
                    endText();
                if(PdfName.ARTIFACT.equals(element.getRole()))
                {
                    HashMap properties = element.getAccessibleAttributes();
                    PdfDictionary propertiesDict = null;
                    if(properties != null && !properties.isEmpty())
                    {
                        propertiesDict = new PdfDictionary();
                        java.util.Map.Entry entry;
                        for(Iterator i$ = properties.entrySet().iterator(); i$.hasNext(); propertiesDict.put((PdfName)entry.getKey(), (PdfObject)entry.getValue()))
                            entry = (java.util.Map.Entry)i$.next();

                    }
                    beginMarkedContentSequence(element.getRole(), propertiesDict, true);
                } else
                {
                    beginMarkedContentSequence(structureElement);
                }
                if(inTextLocal)
                    beginText(true);
            }
        }
        return structureElement;
    }

    public void closeMCBlock(IAccessibleElement element)
    {
        if(isTagged() && element != null && getMcElements().contains(element))
        {
            closeMCBlockInt(element);
            getMcElements().remove(element);
        }
    }

    private void closeMCBlockInt(IAccessibleElement element)
    {
        if(isTagged() && element.getRole() != null)
        {
            boolean inTextLocal = inText;
            if(inText)
                endText();
            endMarkedContentSequence();
            if(inTextLocal)
                beginText(true);
        }
    }

    protected ArrayList saveMCBlocks()
    {
        ArrayList mc = new ArrayList();
        if(isTagged())
        {
            mc = getMcElements();
            for(int i = 0; i < mc.size(); i++)
                closeMCBlockInt((IAccessibleElement)mc.get(i));

            setMcElements(new ArrayList());
        }
        return mc;
    }

    protected void restoreMCBlocks(ArrayList mcElements)
    {
        if(isTagged() && mcElements != null)
        {
            setMcElements(mcElements);
            for(int i = 0; i < getMcElements().size(); i++)
                openMCBlockInt((IAccessibleElement)getMcElements().get(i));

        }
    }

    protected int getMcDepth()
    {
        if(duplicatedFrom != null)
            return duplicatedFrom.getMcDepth();
        else
            return mcDepth;
    }

    protected void setMcDepth(int value)
    {
        if(duplicatedFrom != null)
            duplicatedFrom.setMcDepth(value);
        else
            mcDepth = value;
    }

    protected ArrayList getMcElements()
    {
        if(duplicatedFrom != null)
            return duplicatedFrom.getMcElements();
        else
            return mcElements;
    }

    protected void setMcElements(ArrayList value)
    {
        if(duplicatedFrom != null)
            duplicatedFrom.setMcElements(value);
        else
            mcElements = value;
    }

    protected void updateTx(String text, float Tj)
    {
        state.tx += getEffectiveStringWidth(text, false, Tj);
    }

    private void saveColor(BaseColor color, boolean fill)
    {
        if(isTagged())
            if(inText)
            {
                if(fill)
                    state.textColorFill = color;
                else
                    state.textColorStroke = color;
            } else
            if(fill)
                state.graphicsColorFill = color;
            else
                state.graphicsColorStroke = color;
    }

    private void restoreColor(BaseColor color, boolean fill)
        throws IOException
    {
        if(isTagged())
            if(color instanceof UncoloredPattern)
            {
                UncoloredPattern c = (UncoloredPattern)color;
                if(fill)
                    setPatternFill(c.getPainter(), c.color, c.tint);
                else
                    setPatternStroke(c.getPainter(), c.color, c.tint);
            } else
            if(fill)
                setColorFill(color);
            else
                setColorStroke(color);
    }

    private void restoreColor()
        throws IOException
    {
        if(isTagged())
            if(inText)
            {
                if(!state.textColorFill.equals(state.graphicsColorFill))
                    restoreColor(state.textColorFill, true);
                if(!state.textColorStroke.equals(state.graphicsColorStroke))
                    restoreColor(state.textColorStroke, false);
            } else
            {
                if(!state.textColorFill.equals(state.graphicsColorFill))
                    restoreColor(state.graphicsColorFill, true);
                if(!state.textColorStroke.equals(state.graphicsColorStroke))
                    restoreColor(state.graphicsColorStroke, false);
            }
    }

    protected boolean getInText()
    {
        return inText;
    }

    /**
     * @deprecated Method createGraphicsShapes is deprecated
     */

    public Graphics2D createGraphicsShapes(float width, float height)
    {
        return new PdfGraphics2D(this, width, height, true);
    }

    /**
     * @deprecated Method createPrinterGraphicsShapes is deprecated
     */

    public Graphics2D createPrinterGraphicsShapes(float width, float height, PrinterJob printerJob)
    {
        return new PdfPrinterGraphics2D(this, width, height, true, printerJob);
    }

    /**
     * @deprecated Method createGraphics is deprecated
     */

    public Graphics2D createGraphics(float width, float height)
    {
        return new PdfGraphics2D(this, width, height);
    }

    /**
     * @deprecated Method createPrinterGraphics is deprecated
     */

    public Graphics2D createPrinterGraphics(float width, float height, PrinterJob printerJob)
    {
        return new PdfPrinterGraphics2D(this, width, height, printerJob);
    }

    /**
     * @deprecated Method createGraphics is deprecated
     */

    public Graphics2D createGraphics(float width, float height, boolean convertImagesToJPEG, float quality)
    {
        return new PdfGraphics2D(this, width, height, null, false, convertImagesToJPEG, quality);
    }

    /**
     * @deprecated Method createPrinterGraphics is deprecated
     */

    public Graphics2D createPrinterGraphics(float width, float height, boolean convertImagesToJPEG, float quality, PrinterJob printerJob)
    {
        return new PdfPrinterGraphics2D(this, width, height, null, false, convertImagesToJPEG, quality, printerJob);
    }

    /**
     * @deprecated Method createGraphicsShapes is deprecated
     */

    public Graphics2D createGraphicsShapes(float width, float height, boolean convertImagesToJPEG, float quality)
    {
        return new PdfGraphics2D(this, width, height, null, true, convertImagesToJPEG, quality);
    }

    /**
     * @deprecated Method createPrinterGraphicsShapes is deprecated
     */

    public Graphics2D createPrinterGraphicsShapes(float width, float height, boolean convertImagesToJPEG, float quality, PrinterJob printerJob)
    {
        return new PdfPrinterGraphics2D(this, width, height, null, true, convertImagesToJPEG, quality, printerJob);
    }

    /**
     * @deprecated Method createGraphics is deprecated
     */

    public Graphics2D createGraphics(float width, float height, FontMapper fontMapper)
    {
        return new PdfGraphics2D(this, width, height, fontMapper);
    }

    /**
     * @deprecated Method createPrinterGraphics is deprecated
     */

    public Graphics2D createPrinterGraphics(float width, float height, FontMapper fontMapper, PrinterJob printerJob)
    {
        return new PdfPrinterGraphics2D(this, width, height, fontMapper, printerJob);
    }

    /**
     * @deprecated Method createGraphics is deprecated
     */

    public Graphics2D createGraphics(float width, float height, FontMapper fontMapper, boolean convertImagesToJPEG, float quality)
    {
        return new PdfGraphics2D(this, width, height, fontMapper, false, convertImagesToJPEG, quality);
    }

    /**
     * @deprecated Method createPrinterGraphics is deprecated
     */

    public Graphics2D createPrinterGraphics(float width, float height, FontMapper fontMapper, boolean convertImagesToJPEG, float quality, PrinterJob printerJob)
    {
        return new PdfPrinterGraphics2D(this, width, height, fontMapper, false, convertImagesToJPEG, quality, printerJob);
    }

    /**
     * @deprecated Method addImage is deprecated
     */

    public void addImage(Image image, java.awt.geom.AffineTransform transform)
        throws DocumentException
    {
        double matrix[] = new double[6];
        transform.getMatrix(matrix);
        addImage(image, new AffineTransform(matrix));
    }

    /**
     * @deprecated Method addTemplate is deprecated
     */

    public void addTemplate(PdfTemplate template, java.awt.geom.AffineTransform transform)
    {
        double matrix[] = new double[6];
        transform.getMatrix(matrix);
        addTemplate(template, new AffineTransform(matrix));
    }

    /**
     * @deprecated Method concatCTM is deprecated
     */

    public void concatCTM(java.awt.geom.AffineTransform transform)
    {
        double matrix[] = new double[6];
        transform.getMatrix(matrix);
        concatCTM(new AffineTransform(matrix));
    }

    /**
     * @deprecated Method setTextMatrix is deprecated
     */

    public void setTextMatrix(java.awt.geom.AffineTransform transform)
    {
        double matrix[] = new double[6];
        transform.getMatrix(matrix);
        setTextMatrix(new AffineTransform(matrix));
    }

    /**
     * @deprecated Method transform is deprecated
     */

    public void transform(java.awt.geom.AffineTransform af)
    {
        double matrix[] = new double[6];
        af.getMatrix(matrix);
        transform(new AffineTransform(matrix));
    }

    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_RIGHT = 2;
    public static final int LINE_CAP_BUTT = 0;
    public static final int LINE_CAP_ROUND = 1;
    public static final int LINE_CAP_PROJECTING_SQUARE = 2;
    public static final int LINE_JOIN_MITER = 0;
    public static final int LINE_JOIN_ROUND = 1;
    public static final int LINE_JOIN_BEVEL = 2;
    public static final int TEXT_RENDER_MODE_FILL = 0;
    public static final int TEXT_RENDER_MODE_STROKE = 1;
    public static final int TEXT_RENDER_MODE_FILL_STROKE = 2;
    public static final int TEXT_RENDER_MODE_INVISIBLE = 3;
    public static final int TEXT_RENDER_MODE_FILL_CLIP = 4;
    public static final int TEXT_RENDER_MODE_STROKE_CLIP = 5;
    public static final int TEXT_RENDER_MODE_FILL_STROKE_CLIP = 6;
    public static final int TEXT_RENDER_MODE_CLIP = 7;
    private static final float unitRect[] = {
        0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 1.0F
    };
    protected ByteBuffer content;
    protected int markedContentSize;
    protected PdfWriter writer;
    protected PdfDocument pdf;
    protected GraphicState state;
    protected ArrayList stateList;
    protected ArrayList layerDepth;
    protected int separator;
    private int mcDepth;
    private boolean inText;
    private static HashMap abrev;
    private ArrayList mcElements;
    protected PdfContentByte duplicatedFrom;

    static 
    {
        abrev = new HashMap();
        abrev.put(PdfName.BITSPERCOMPONENT, "/BPC ");
        abrev.put(PdfName.COLORSPACE, "/CS ");
        abrev.put(PdfName.DECODE, "/D ");
        abrev.put(PdfName.DECODEPARMS, "/DP ");
        abrev.put(PdfName.FILTER, "/F ");
        abrev.put(PdfName.HEIGHT, "/H ");
        abrev.put(PdfName.IMAGEMASK, "/IM ");
        abrev.put(PdfName.INTENT, "/Intent ");
        abrev.put(PdfName.INTERPOLATE, "/I ");
        abrev.put(PdfName.WIDTH, "/W ");
    }
}
