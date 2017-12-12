// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MetaDo.java

package co.com.pdf.text.pdf.codec.wmf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.BaseFont;
import co.com.pdf.text.pdf.PdfContentByte;
import co.com.pdf.text.pdf.codec.BmpImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf.codec.wmf:
//            MetaState, InputMeta, MetaObject, MetaPen, 
//            MetaBrush, MetaFont, Point

public class MetaDo
{

    public MetaDo(InputStream in, PdfContentByte cb)
    {
        state = new MetaState();
        this.cb = cb;
        this.in = new InputMeta(in);
    }

    public void readAll()
        throws IOException, DocumentException
    {
        if(in.readInt() != 0x9ac6cdd7)
            throw new DocumentException(MessageLocalization.getComposedMessage("not.a.placeable.windows.metafile", new Object[0]));
        in.readWord();
        left = in.readShort();
        top = in.readShort();
        right = in.readShort();
        bottom = in.readShort();
        inch = in.readWord();
        state.setScalingX(((float)(right - left) / (float)inch) * 72F);
        state.setScalingY(((float)(bottom - top) / (float)inch) * 72F);
        state.setOffsetWx(left);
        state.setOffsetWy(top);
        state.setExtentWx(right - left);
        state.setExtentWy(bottom - top);
        in.readInt();
        in.readWord();
        in.skip(18);
        cb.setLineCap(1);
        cb.setLineJoin(1);
        do
        {
            int lenMarker = in.getLength();
            int tsize = in.readInt();
            if(tsize >= 3)
            {
                int function = in.readWord();
                switch(function)
                {
                case 0: // '\0'
                default:
                    break;

                case 247: 
                case 322: 
                case 1791: 
                {
                    state.addMetaObject(new MetaObject());
                    break;
                }

                case 762: 
                {
                    MetaPen pen = new MetaPen();
                    pen.init(in);
                    state.addMetaObject(pen);
                    break;
                }

                case 764: 
                {
                    MetaBrush brush = new MetaBrush();
                    brush.init(in);
                    state.addMetaObject(brush);
                    break;
                }

                case 763: 
                {
                    MetaFont font = new MetaFont();
                    font.init(in);
                    state.addMetaObject(font);
                    break;
                }

                case 301: 
                {
                    int idx = in.readWord();
                    state.selectMetaObject(idx, cb);
                    break;
                }

                case 496: 
                {
                    int idx = in.readWord();
                    state.deleteMetaObject(idx);
                    break;
                }

                case 30: // '\036'
                {
                    state.saveState(cb);
                    break;
                }

                case 295: 
                {
                    int idx = in.readShort();
                    state.restoreState(idx, cb);
                    break;
                }

                case 523: 
                {
                    state.setOffsetWy(in.readShort());
                    state.setOffsetWx(in.readShort());
                    break;
                }

                case 524: 
                {
                    state.setExtentWy(in.readShort());
                    state.setExtentWx(in.readShort());
                    break;
                }

                case 532: 
                {
                    int y = in.readShort();
                    Point p = new Point(in.readShort(), y);
                    state.setCurrentPoint(p);
                    break;
                }

                case 531: 
                {
                    int y = in.readShort();
                    int x = in.readShort();
                    Point p = state.getCurrentPoint();
                    cb.moveTo(state.transformX(p.x), state.transformY(p.y));
                    cb.lineTo(state.transformX(x), state.transformY(y));
                    cb.stroke();
                    state.setCurrentPoint(new Point(x, y));
                    break;
                }

                case 805: 
                {
                    state.setLineJoinPolygon(cb);
                    int len = in.readWord();
                    int x = in.readShort();
                    int y = in.readShort();
                    cb.moveTo(state.transformX(x), state.transformY(y));
                    for(int k = 1; k < len; k++)
                    {
                        x = in.readShort();
                        y = in.readShort();
                        cb.lineTo(state.transformX(x), state.transformY(y));
                    }

                    cb.stroke();
                    break;
                }

                case 804: 
                {
                    if(isNullStrokeFill(false))
                        break;
                    int len = in.readWord();
                    int sx = in.readShort();
                    int sy = in.readShort();
                    cb.moveTo(state.transformX(sx), state.transformY(sy));
                    for(int k = 1; k < len; k++)
                    {
                        int x = in.readShort();
                        int y = in.readShort();
                        cb.lineTo(state.transformX(x), state.transformY(y));
                    }

                    cb.lineTo(state.transformX(sx), state.transformY(sy));
                    strokeAndFill();
                    break;
                }

                case 1336: 
                {
                    if(isNullStrokeFill(false))
                        break;
                    int numPoly = in.readWord();
                    int lens[] = new int[numPoly];
                    for(int k = 0; k < lens.length; k++)
                        lens[k] = in.readWord();

                    for(int j = 0; j < lens.length; j++)
                    {
                        int len = lens[j];
                        int sx = in.readShort();
                        int sy = in.readShort();
                        cb.moveTo(state.transformX(sx), state.transformY(sy));
                        for(int k = 1; k < len; k++)
                        {
                            int x = in.readShort();
                            int y = in.readShort();
                            cb.lineTo(state.transformX(x), state.transformY(y));
                        }

                        cb.lineTo(state.transformX(sx), state.transformY(sy));
                    }

                    strokeAndFill();
                    break;
                }

                case 1048: 
                {
                    if(!isNullStrokeFill(state.getLineNeutral()))
                    {
                        int b = in.readShort();
                        int r = in.readShort();
                        int t = in.readShort();
                        int l = in.readShort();
                        cb.arc(state.transformX(l), state.transformY(b), state.transformX(r), state.transformY(t), 0.0F, 360F);
                        strokeAndFill();
                    }
                    break;
                }

                case 2071: 
                {
                    if(isNullStrokeFill(state.getLineNeutral()))
                        break;
                    float yend = state.transformY(in.readShort());
                    float xend = state.transformX(in.readShort());
                    float ystart = state.transformY(in.readShort());
                    float xstart = state.transformX(in.readShort());
                    float b = state.transformY(in.readShort());
                    float r = state.transformX(in.readShort());
                    float t = state.transformY(in.readShort());
                    float l = state.transformX(in.readShort());
                    float cx = (r + l) / 2.0F;
                    float cy = (t + b) / 2.0F;
                    float arc1 = getArc(cx, cy, xstart, ystart);
                    float arc2 = getArc(cx, cy, xend, yend);
                    arc2 -= arc1;
                    if(arc2 <= 0.0F)
                        arc2 += 360F;
                    cb.arc(l, b, r, t, arc1, arc2);
                    cb.stroke();
                    break;
                }

                case 2074: 
                {
                    if(isNullStrokeFill(state.getLineNeutral()))
                        break;
                    float yend = state.transformY(in.readShort());
                    float xend = state.transformX(in.readShort());
                    float ystart = state.transformY(in.readShort());
                    float xstart = state.transformX(in.readShort());
                    float b = state.transformY(in.readShort());
                    float r = state.transformX(in.readShort());
                    float t = state.transformY(in.readShort());
                    float l = state.transformX(in.readShort());
                    float cx = (r + l) / 2.0F;
                    float cy = (t + b) / 2.0F;
                    float arc1 = getArc(cx, cy, xstart, ystart);
                    float arc2 = getArc(cx, cy, xend, yend);
                    arc2 -= arc1;
                    if(arc2 <= 0.0F)
                        arc2 += 360F;
                    ArrayList ar = PdfContentByte.bezierArc(l, b, r, t, arc1, arc2);
                    if(ar.isEmpty())
                        break;
                    float pt[] = (float[])ar.get(0);
                    cb.moveTo(cx, cy);
                    cb.lineTo(pt[0], pt[1]);
                    for(int k = 0; k < ar.size(); k++)
                    {
                        pt = (float[])ar.get(k);
                        cb.curveTo(pt[2], pt[3], pt[4], pt[5], pt[6], pt[7]);
                    }

                    cb.lineTo(cx, cy);
                    strokeAndFill();
                    break;
                }

                case 2096: 
                {
                    if(isNullStrokeFill(state.getLineNeutral()))
                        break;
                    float yend = state.transformY(in.readShort());
                    float xend = state.transformX(in.readShort());
                    float ystart = state.transformY(in.readShort());
                    float xstart = state.transformX(in.readShort());
                    float b = state.transformY(in.readShort());
                    float r = state.transformX(in.readShort());
                    float t = state.transformY(in.readShort());
                    float l = state.transformX(in.readShort());
                    float cx = (r + l) / 2.0F;
                    float cy = (t + b) / 2.0F;
                    float arc1 = getArc(cx, cy, xstart, ystart);
                    float arc2 = getArc(cx, cy, xend, yend);
                    arc2 -= arc1;
                    if(arc2 <= 0.0F)
                        arc2 += 360F;
                    ArrayList ar = PdfContentByte.bezierArc(l, b, r, t, arc1, arc2);
                    if(ar.isEmpty())
                        break;
                    float pt[] = (float[])ar.get(0);
                    cx = pt[0];
                    cy = pt[1];
                    cb.moveTo(cx, cy);
                    for(int k = 0; k < ar.size(); k++)
                    {
                        pt = (float[])ar.get(k);
                        cb.curveTo(pt[2], pt[3], pt[4], pt[5], pt[6], pt[7]);
                    }

                    cb.lineTo(cx, cy);
                    strokeAndFill();
                    break;
                }

                case 1051: 
                {
                    if(!isNullStrokeFill(true))
                    {
                        float b = state.transformY(in.readShort());
                        float r = state.transformX(in.readShort());
                        float t = state.transformY(in.readShort());
                        float l = state.transformX(in.readShort());
                        cb.rectangle(l, b, r - l, t - b);
                        strokeAndFill();
                    }
                    break;
                }

                case 1564: 
                {
                    if(!isNullStrokeFill(true))
                    {
                        float h = state.transformY(0) - state.transformY(in.readShort());
                        float w = state.transformX(in.readShort()) - state.transformX(0);
                        float b = state.transformY(in.readShort());
                        float r = state.transformX(in.readShort());
                        float t = state.transformY(in.readShort());
                        float l = state.transformX(in.readShort());
                        cb.roundRectangle(l, b, r - l, t - b, (h + w) / 4F);
                        strokeAndFill();
                    }
                    break;
                }

                case 1046: 
                {
                    float b = state.transformY(in.readShort());
                    float r = state.transformX(in.readShort());
                    float t = state.transformY(in.readShort());
                    float l = state.transformX(in.readShort());
                    cb.rectangle(l, b, r - l, t - b);
                    cb.eoClip();
                    cb.newPath();
                    break;
                }

                case 2610: 
                {
                    int y = in.readShort();
                    int x = in.readShort();
                    int count = in.readWord();
                    int flag = in.readWord();
                    int x1 = 0;
                    int y1 = 0;
                    int x2 = 0;
                    int y2 = 0;
                    if((flag & 6) != 0)
                    {
                        x1 = in.readShort();
                        y1 = in.readShort();
                        x2 = in.readShort();
                        y2 = in.readShort();
                    }
                    byte text[] = new byte[count];
                    int k = 0;
                    do
                    {
                        if(k >= count)
                            break;
                        byte c = (byte)in.readByte();
                        if(c == 0)
                            break;
                        text[k] = c;
                        k++;
                    } while(true);
                    String s;
                    try
                    {
                        s = new String(text, 0, k, "Cp1252");
                    }
                    catch(UnsupportedEncodingException e)
                    {
                        s = new String(text, 0, k);
                    }
                    outputText(x, y, flag, x1, y1, x2, y2, s);
                    break;
                }

                case 1313: 
                {
                    int count = in.readWord();
                    byte text[] = new byte[count];
                    int k = 0;
                    do
                    {
                        if(k >= count)
                            break;
                        byte c = (byte)in.readByte();
                        if(c == 0)
                            break;
                        text[k] = c;
                        k++;
                    } while(true);
                    String s;
                    try
                    {
                        s = new String(text, 0, k, "Cp1252");
                    }
                    catch(UnsupportedEncodingException e)
                    {
                        s = new String(text, 0, k);
                    }
                    count = count + 1 & 0xfffe;
                    in.skip(count - k);
                    int y = in.readShort();
                    int x = in.readShort();
                    outputText(x, y, 0, 0, 0, 0, 0, s);
                    break;
                }

                case 513: 
                {
                    state.setCurrentBackgroundColor(in.readColor());
                    break;
                }

                case 521: 
                {
                    state.setCurrentTextColor(in.readColor());
                    break;
                }

                case 302: 
                {
                    state.setTextAlign(in.readWord());
                    break;
                }

                case 258: 
                {
                    state.setBackgroundMode(in.readWord());
                    break;
                }

                case 262: 
                {
                    state.setPolyFillMode(in.readWord());
                    break;
                }

                case 1055: 
                {
                    BaseColor color = in.readColor();
                    int y = in.readShort();
                    int x = in.readShort();
                    cb.saveState();
                    cb.setColorFill(color);
                    cb.rectangle(state.transformX(x), state.transformY(y), 0.2F, 0.2F);
                    cb.fill();
                    cb.restoreState();
                    break;
                }

                case 2881: 
                case 3907: 
                {
                    int rop = in.readInt();
                    if(function == 3907)
                        in.readWord();
                    int srcHeight = in.readShort();
                    int srcWidth = in.readShort();
                    int ySrc = in.readShort();
                    int xSrc = in.readShort();
                    float destHeight = state.transformY(in.readShort()) - state.transformY(0);
                    float destWidth = state.transformX(in.readShort()) - state.transformX(0);
                    float yDest = state.transformY(in.readShort());
                    float xDest = state.transformX(in.readShort());
                    byte b[] = new byte[tsize * 2 - (in.getLength() - lenMarker)];
                    for(int k = 0; k < b.length; k++)
                        b[k] = (byte)in.readByte();

                    try
                    {
                        ByteArrayInputStream inb = new ByteArrayInputStream(b);
                        Image bmp = BmpImage.getImage(inb, true, b.length);
                        cb.saveState();
                        cb.rectangle(xDest, yDest, destWidth, destHeight);
                        cb.clip();
                        cb.newPath();
                        bmp.scaleAbsolute((destWidth * bmp.getWidth()) / (float)srcWidth, (-destHeight * bmp.getHeight()) / (float)srcHeight);
                        bmp.setAbsolutePosition(xDest - (destWidth * (float)xSrc) / (float)srcWidth, (yDest + (destHeight * (float)ySrc) / (float)srcHeight) - bmp.getScaledHeight());
                        cb.addImage(bmp);
                        cb.restoreState();
                    }
                    catch(Exception e) { }
                    break;
                }
                }
                in.skip(tsize * 2 - (in.getLength() - lenMarker));
            } else
            {
                state.cleanup(cb);
                return;
            }
        } while(true);
    }

    public void outputText(int x, int y, int flag, int x1, int y1, int x2, int y2, 
            String text)
    {
        MetaFont font = state.getCurrentFont();
        float refX = state.transformX(x);
        float refY = state.transformY(y);
        float angle = state.transformAngle(font.getAngle());
        float sin = (float)Math.sin(angle);
        float cos = (float)Math.cos(angle);
        float fontSize = font.getFontSize(state);
        BaseFont bf = font.getFont();
        int align = state.getTextAlign();
        float textWidth = bf.getWidthPoint(text, fontSize);
        float tx = 0.0F;
        float ty = 0.0F;
        float descender = bf.getFontDescriptor(3, fontSize);
        float ury = bf.getFontDescriptor(8, fontSize);
        cb.saveState();
        cb.concatCTM(cos, sin, -sin, cos, refX, refY);
        if((align & 6) == 6)
            tx = -textWidth / 2.0F;
        else
        if((align & 2) == 2)
            tx = -textWidth;
        if((align & 0x18) == 24)
            ty = 0.0F;
        else
        if((align & 8) == 8)
            ty = -descender;
        else
            ty = -ury;
        BaseColor textColor;
        if(state.getBackgroundMode() == 2)
        {
            textColor = state.getCurrentBackgroundColor();
            cb.setColorFill(textColor);
            cb.rectangle(tx, ty + descender, textWidth, ury - descender);
            cb.fill();
        }
        textColor = state.getCurrentTextColor();
        cb.setColorFill(textColor);
        cb.beginText();
        cb.setFontAndSize(bf, fontSize);
        cb.setTextMatrix(tx, ty);
        cb.showText(text);
        cb.endText();
        if(font.isUnderline())
        {
            cb.rectangle(tx, ty - fontSize / 4F, textWidth, fontSize / 15F);
            cb.fill();
        }
        if(font.isStrikeout())
        {
            cb.rectangle(tx, ty + fontSize / 3F, textWidth, fontSize / 15F);
            cb.fill();
        }
        cb.restoreState();
    }

    public boolean isNullStrokeFill(boolean isRectangle)
    {
        MetaPen pen = state.getCurrentPen();
        MetaBrush brush = state.getCurrentBrush();
        boolean noPen = pen.getStyle() == 5;
        int style = brush.getStyle();
        boolean isBrush = style == 0 || style == 2 && state.getBackgroundMode() == 2;
        boolean result = noPen && !isBrush;
        if(!noPen)
            if(isRectangle)
                state.setLineJoinRectangle(cb);
            else
                state.setLineJoinPolygon(cb);
        return result;
    }

    public void strokeAndFill()
    {
        MetaPen pen = state.getCurrentPen();
        MetaBrush brush = state.getCurrentBrush();
        int penStyle = pen.getStyle();
        int brushStyle = brush.getStyle();
        if(penStyle == 5)
        {
            cb.closePath();
            if(state.getPolyFillMode() == 1)
                cb.eoFill();
            else
                cb.fill();
        } else
        {
            boolean isBrush = brushStyle == 0 || brushStyle == 2 && state.getBackgroundMode() == 2;
            if(isBrush)
            {
                if(state.getPolyFillMode() == 1)
                    cb.closePathEoFillStroke();
                else
                    cb.closePathFillStroke();
            } else
            {
                cb.closePathStroke();
            }
        }
    }

    static float getArc(float xCenter, float yCenter, float xDot, float yDot)
    {
        double s = Math.atan2(yDot - yCenter, xDot - xCenter);
        if(s < 0.0D)
            s += 6.2831853071795862D;
        return (float)((s / 3.1415926535897931D) * 180D);
    }

    public static byte[] wrapBMP(Image image)
        throws IOException
    {
        if(image.getOriginalType() != 4)
            throw new IOException(MessageLocalization.getComposedMessage("only.bmp.can.be.wrapped.in.wmf", new Object[0]));
        byte data[] = null;
        if(image.getOriginalData() == null)
        {
            InputStream imgIn = image.getUrl().openStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            for(int b = 0; (b = imgIn.read()) != -1;)
                out.write(b);

            imgIn.close();
            data = out.toByteArray();
        } else
        {
            data = image.getOriginalData();
        }
        int sizeBmpWords = (data.length - 14) + 1 >>> 1;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        writeWord(os, 1);
        writeWord(os, 9);
        writeWord(os, 768);
        writeDWord(os, 36 + sizeBmpWords + 3);
        writeWord(os, 1);
        writeDWord(os, 14 + sizeBmpWords);
        writeWord(os, 0);
        writeDWord(os, 4);
        writeWord(os, 259);
        writeWord(os, 8);
        writeDWord(os, 5);
        writeWord(os, 523);
        writeWord(os, 0);
        writeWord(os, 0);
        writeDWord(os, 5);
        writeWord(os, 524);
        writeWord(os, (int)image.getHeight());
        writeWord(os, (int)image.getWidth());
        writeDWord(os, 13 + sizeBmpWords);
        writeWord(os, 2881);
        writeDWord(os, 0xcc0020);
        writeWord(os, (int)image.getHeight());
        writeWord(os, (int)image.getWidth());
        writeWord(os, 0);
        writeWord(os, 0);
        writeWord(os, (int)image.getHeight());
        writeWord(os, (int)image.getWidth());
        writeWord(os, 0);
        writeWord(os, 0);
        os.write(data, 14, data.length - 14);
        if((data.length & 1) == 1)
            os.write(0);
        writeDWord(os, 3);
        writeWord(os, 0);
        os.close();
        return os.toByteArray();
    }

    public static void writeWord(OutputStream os, int v)
        throws IOException
    {
        os.write(v & 0xff);
        os.write(v >>> 8 & 0xff);
    }

    public static void writeDWord(OutputStream os, int v)
        throws IOException
    {
        writeWord(os, v & 0xffff);
        writeWord(os, v >>> 16 & 0xffff);
    }

    public static final int META_SETBKCOLOR = 513;
    public static final int META_SETBKMODE = 258;
    public static final int META_SETMAPMODE = 259;
    public static final int META_SETROP2 = 260;
    public static final int META_SETRELABS = 261;
    public static final int META_SETPOLYFILLMODE = 262;
    public static final int META_SETSTRETCHBLTMODE = 263;
    public static final int META_SETTEXTCHAREXTRA = 264;
    public static final int META_SETTEXTCOLOR = 521;
    public static final int META_SETTEXTJUSTIFICATION = 522;
    public static final int META_SETWINDOWORG = 523;
    public static final int META_SETWINDOWEXT = 524;
    public static final int META_SETVIEWPORTORG = 525;
    public static final int META_SETVIEWPORTEXT = 526;
    public static final int META_OFFSETWINDOWORG = 527;
    public static final int META_SCALEWINDOWEXT = 1040;
    public static final int META_OFFSETVIEWPORTORG = 529;
    public static final int META_SCALEVIEWPORTEXT = 1042;
    public static final int META_LINETO = 531;
    public static final int META_MOVETO = 532;
    public static final int META_EXCLUDECLIPRECT = 1045;
    public static final int META_INTERSECTCLIPRECT = 1046;
    public static final int META_ARC = 2071;
    public static final int META_ELLIPSE = 1048;
    public static final int META_FLOODFILL = 1049;
    public static final int META_PIE = 2074;
    public static final int META_RECTANGLE = 1051;
    public static final int META_ROUNDRECT = 1564;
    public static final int META_PATBLT = 1565;
    public static final int META_SAVEDC = 30;
    public static final int META_SETPIXEL = 1055;
    public static final int META_OFFSETCLIPRGN = 544;
    public static final int META_TEXTOUT = 1313;
    public static final int META_BITBLT = 2338;
    public static final int META_STRETCHBLT = 2851;
    public static final int META_POLYGON = 804;
    public static final int META_POLYLINE = 805;
    public static final int META_ESCAPE = 1574;
    public static final int META_RESTOREDC = 295;
    public static final int META_FILLREGION = 552;
    public static final int META_FRAMEREGION = 1065;
    public static final int META_INVERTREGION = 298;
    public static final int META_PAINTREGION = 299;
    public static final int META_SELECTCLIPREGION = 300;
    public static final int META_SELECTOBJECT = 301;
    public static final int META_SETTEXTALIGN = 302;
    public static final int META_CHORD = 2096;
    public static final int META_SETMAPPERFLAGS = 561;
    public static final int META_EXTTEXTOUT = 2610;
    public static final int META_SETDIBTODEV = 3379;
    public static final int META_SELECTPALETTE = 564;
    public static final int META_REALIZEPALETTE = 53;
    public static final int META_ANIMATEPALETTE = 1078;
    public static final int META_SETPALENTRIES = 55;
    public static final int META_POLYPOLYGON = 1336;
    public static final int META_RESIZEPALETTE = 313;
    public static final int META_DIBBITBLT = 2368;
    public static final int META_DIBSTRETCHBLT = 2881;
    public static final int META_DIBCREATEPATTERNBRUSH = 322;
    public static final int META_STRETCHDIB = 3907;
    public static final int META_EXTFLOODFILL = 1352;
    public static final int META_DELETEOBJECT = 496;
    public static final int META_CREATEPALETTE = 247;
    public static final int META_CREATEPATTERNBRUSH = 505;
    public static final int META_CREATEPENINDIRECT = 762;
    public static final int META_CREATEFONTINDIRECT = 763;
    public static final int META_CREATEBRUSHINDIRECT = 764;
    public static final int META_CREATEREGION = 1791;
    public PdfContentByte cb;
    public InputMeta in;
    int left;
    int top;
    int right;
    int bottom;
    int inch;
    MetaState state;
}
