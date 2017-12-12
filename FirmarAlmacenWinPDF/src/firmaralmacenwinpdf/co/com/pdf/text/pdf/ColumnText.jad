// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ColumnText.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.Chunk;
import co.com.pdf.text.DocumentException;
import co.com.pdf.text.Element;
import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.Font;
import co.com.pdf.text.Image;
import co.com.pdf.text.List;
import co.com.pdf.text.ListBody;
import co.com.pdf.text.ListItem;
import co.com.pdf.text.ListLabel;
import co.com.pdf.text.Paragraph;
import co.com.pdf.text.Phrase;
import co.com.pdf.text.Rectangle;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.log.Logger;
import co.com.pdf.text.log.LoggerFactory;
import co.com.pdf.text.pdf.draw.DrawInterface;
import co.com.pdf.text.pdf.interfaces.IAccessibleElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

// Referenced classes of package co.com.pdf.text.pdf:
//            BidiLine, PdfPTable, PdfChunk, PdfPCell, 
//            PdfFont, PdfPRow, PdfPTableEventSplit, PdfPTableEventAfterSplit, 
//            FloatLayout, PdfDocument, PdfContentByte, PdfLine, 
//            PdfWriter, PdfPTableHeader, PdfPTableBody, PdfPTableFooter

public class ColumnText
{

    public ColumnText(PdfContentByte canvas)
    {
        runDirection = 0;
        alignment = 0;
        currentLeading = 16F;
        fixedLeading = 16F;
        multipliedLeading = 0.0F;
        indent = 0.0F;
        followingIndent = 0.0F;
        rightIndent = 0.0F;
        extraParagraphSpace = 0.0F;
        rectangularWidth = -1F;
        rectangularMode = false;
        spaceCharRatio = 0.0F;
        lastWasNewline = true;
        repeatFirstLineIndent = true;
        firstLineYDone = false;
        arabicOptions = 0;
        composite = false;
        listIdx = 0;
        rowIdx = 0;
        splittedRow = -1;
        useAscender = false;
        adjustFirstLine = true;
        inheritGraphicState = false;
        this.canvas = canvas;
    }

    public static ColumnText duplicate(ColumnText org)
    {
        ColumnText ct = new ColumnText(null);
        ct.setACopy(org);
        return ct;
    }

    public ColumnText setACopy(ColumnText org)
    {
        setSimpleVars(org);
        if(org.bidiLine != null)
            bidiLine = new BidiLine(org.bidiLine);
        return this;
    }

    protected void setSimpleVars(ColumnText org)
    {
        maxY = org.maxY;
        minY = org.minY;
        alignment = org.alignment;
        leftWall = null;
        if(org.leftWall != null)
            leftWall = new ArrayList(org.leftWall);
        rightWall = null;
        if(org.rightWall != null)
            rightWall = new ArrayList(org.rightWall);
        yLine = org.yLine;
        currentLeading = org.currentLeading;
        fixedLeading = org.fixedLeading;
        multipliedLeading = org.multipliedLeading;
        canvas = org.canvas;
        canvases = org.canvases;
        lineStatus = org.lineStatus;
        indent = org.indent;
        followingIndent = org.followingIndent;
        rightIndent = org.rightIndent;
        extraParagraphSpace = org.extraParagraphSpace;
        rectangularWidth = org.rectangularWidth;
        rectangularMode = org.rectangularMode;
        spaceCharRatio = org.spaceCharRatio;
        lastWasNewline = org.lastWasNewline;
        repeatFirstLineIndent = org.repeatFirstLineIndent;
        linesWritten = org.linesWritten;
        arabicOptions = org.arabicOptions;
        runDirection = org.runDirection;
        descender = org.descender;
        composite = org.composite;
        splittedRow = org.splittedRow;
        if(org.composite)
        {
            compositeElements = new LinkedList();
            for(Iterator i$ = org.compositeElements.iterator(); i$.hasNext();)
            {
                Element element = (Element)i$.next();
                if(element instanceof PdfPTable)
                    compositeElements.add(new PdfPTable((PdfPTable)element));
                else
                    compositeElements.add(element);
            }

            if(org.compositeColumn != null)
                compositeColumn = duplicate(org.compositeColumn);
        }
        listIdx = org.listIdx;
        rowIdx = org.rowIdx;
        firstLineY = org.firstLineY;
        leftX = org.leftX;
        rightX = org.rightX;
        firstLineYDone = org.firstLineYDone;
        waitPhrase = org.waitPhrase;
        useAscender = org.useAscender;
        filledWidth = org.filledWidth;
        adjustFirstLine = org.adjustFirstLine;
        inheritGraphicState = org.inheritGraphicState;
    }

    private void addWaitingPhrase()
    {
        if(bidiLine == null && waitPhrase != null)
        {
            bidiLine = new BidiLine();
            Chunk c;
            for(Iterator i$ = waitPhrase.getChunks().iterator(); i$.hasNext(); bidiLine.addChunk(new PdfChunk(c, null, waitPhrase.getTabSettings())))
                c = (Chunk)i$.next();

            waitPhrase = null;
        }
    }

    public void addText(Phrase phrase)
    {
        if(phrase == null || composite)
            return;
        addWaitingPhrase();
        if(bidiLine == null)
        {
            waitPhrase = phrase;
            return;
        }
        Object element;
        for(Iterator i$ = phrase.getChunks().iterator(); i$.hasNext(); bidiLine.addChunk(new PdfChunk((Chunk)element, null, phrase.getTabSettings())))
            element = i$.next();

    }

    public void setText(Phrase phrase)
    {
        bidiLine = null;
        composite = false;
        compositeColumn = null;
        compositeElements = null;
        listIdx = 0;
        rowIdx = 0;
        splittedRow = -1;
        waitPhrase = phrase;
    }

    public void addText(Chunk chunk)
    {
        if(chunk == null || composite)
        {
            return;
        } else
        {
            addText(new Phrase(chunk));
            return;
        }
    }

    public void addElement(Element element)
    {
        if(element == null)
            return;
        if(element instanceof Image)
        {
            Image img = (Image)element;
            PdfPTable t = new PdfPTable(1);
            float w = img.getWidthPercentage();
            if(w == 0.0F)
            {
                t.setTotalWidth(img.getScaledWidth());
                t.setLockedWidth(true);
            } else
            {
                t.setWidthPercentage(w);
            }
            t.setSpacingAfter(img.getSpacingAfter());
            t.setSpacingBefore(img.getSpacingBefore());
            switch(img.getAlignment())
            {
            case 0: // '\0'
                t.setHorizontalAlignment(0);
                break;

            case 2: // '\002'
                t.setHorizontalAlignment(2);
                break;

            default:
                t.setHorizontalAlignment(1);
                break;
            }
            PdfPCell c = new PdfPCell(img, true);
            c.setPadding(0.0F);
            c.setBorder(img.getBorder());
            c.setBorderColor(img.getBorderColor());
            c.setBorderWidth(img.getBorderWidth());
            c.setBackgroundColor(img.getBackgroundColor());
            t.addCell(c);
            element = t;
        }
        if(element.type() == 10)
            element = new Paragraph((Chunk)element);
        else
        if(element.type() == 11)
            element = new Paragraph((Phrase)element);
        if(element.type() != 12 && element.type() != 14 && element.type() != 23 && element.type() != 55 && element.type() != 37)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("element.not.allowed", new Object[0]));
        if(!composite)
        {
            composite = true;
            compositeElements = new LinkedList();
            bidiLine = null;
            waitPhrase = null;
        }
        if(element.type() == 12)
        {
            Paragraph p = (Paragraph)element;
            compositeElements.addAll(p.breakUp());
            return;
        } else
        {
            compositeElements.add(element);
            return;
        }
    }

    public static boolean isAllowedElement(Element element)
    {
        int type = element.type();
        if(type == 10 || type == 11 || type == 37 || type == 12 || type == 14 || type == 55 || type == 23)
            return true;
        return element instanceof Image;
    }

    protected ArrayList convertColumn(float cLine[])
    {
        if(cLine.length < 4)
            throw new RuntimeException(MessageLocalization.getComposedMessage("no.valid.column.line.found", new Object[0]));
        ArrayList cc = new ArrayList();
        for(int k = 0; k < cLine.length - 2; k += 2)
        {
            float x1 = cLine[k];
            float y1 = cLine[k + 1];
            float x2 = cLine[k + 2];
            float y2 = cLine[k + 3];
            if(y1 != y2)
            {
                float a = (x1 - x2) / (y1 - y2);
                float b = x1 - a * y1;
                float r[] = new float[4];
                r[0] = Math.min(y1, y2);
                r[1] = Math.max(y1, y2);
                r[2] = a;
                r[3] = b;
                cc.add(r);
                maxY = Math.max(maxY, r[1]);
                minY = Math.min(minY, r[0]);
            }
        }

        if(cc.isEmpty())
            throw new RuntimeException(MessageLocalization.getComposedMessage("no.valid.column.line.found", new Object[0]));
        else
            return cc;
    }

    protected float findLimitsPoint(ArrayList wall)
    {
        lineStatus = 0;
        if(yLine < minY || yLine > maxY)
        {
            lineStatus = 1;
            return 0.0F;
        }
        for(int k = 0; k < wall.size(); k++)
        {
            float r[] = (float[])wall.get(k);
            if(yLine >= r[0] && yLine <= r[1])
                return r[2] * yLine + r[3];
        }

        lineStatus = 2;
        return 0.0F;
    }

    protected float[] findLimitsOneLine()
    {
        float x1 = findLimitsPoint(leftWall);
        if(lineStatus == 1 || lineStatus == 2)
            return null;
        float x2 = findLimitsPoint(rightWall);
        if(lineStatus == 2)
            return null;
        else
            return (new float[] {
                x1, x2
            });
    }

    protected float[] findLimitsTwoLines()
    {
        boolean repeat = false;
        float x1[];
        float x2[];
        do
            do
            {
                do
                {
                    if(repeat && currentLeading == 0.0F)
                        return null;
                    repeat = true;
                    x1 = findLimitsOneLine();
                    if(lineStatus == 1)
                        return null;
                    yLine -= currentLeading;
                } while(lineStatus == 2);
                x2 = findLimitsOneLine();
                if(lineStatus == 1)
                    return null;
                if(lineStatus != 2)
                    break;
                yLine -= currentLeading;
            } while(true);
        while(x1[0] >= x2[1] || x2[0] >= x1[1]);
        return (new float[] {
            x1[0], x1[1], x2[0], x2[1]
        });
    }

    public void setColumns(float leftLine[], float rightLine[])
    {
        maxY = -1E+021F;
        minY = 1E+021F;
        setYLine(Math.max(leftLine[1], leftLine[leftLine.length - 1]));
        rightWall = convertColumn(rightLine);
        leftWall = convertColumn(leftLine);
        rectangularWidth = -1F;
        rectangularMode = false;
    }

    public void setSimpleColumn(Phrase phrase, float llx, float lly, float urx, float ury, float leading, int alignment)
    {
        addText(phrase);
        setSimpleColumn(llx, lly, urx, ury, leading, alignment);
    }

    public void setSimpleColumn(float llx, float lly, float urx, float ury, float leading, int alignment)
    {
        setLeading(leading);
        this.alignment = alignment;
        setSimpleColumn(llx, lly, urx, ury);
    }

    public void setSimpleColumn(float llx, float lly, float urx, float ury)
    {
        leftX = Math.min(llx, urx);
        maxY = Math.max(lly, ury);
        minY = Math.min(lly, ury);
        rightX = Math.max(llx, urx);
        yLine = maxY;
        rectangularWidth = rightX - leftX;
        if(rectangularWidth < 0.0F)
            rectangularWidth = 0.0F;
        rectangularMode = true;
    }

    public void setSimpleColumn(Rectangle rect)
    {
        setSimpleColumn(rect.getLeft(), rect.getBottom(), rect.getRight(), rect.getTop());
    }

    public void setLeading(float leading)
    {
        fixedLeading = leading;
        multipliedLeading = 0.0F;
    }

    public void setLeading(float fixedLeading, float multipliedLeading)
    {
        this.fixedLeading = fixedLeading;
        this.multipliedLeading = multipliedLeading;
    }

    public float getLeading()
    {
        return fixedLeading;
    }

    public float getMultipliedLeading()
    {
        return multipliedLeading;
    }

    public void setYLine(float yLine)
    {
        this.yLine = yLine;
    }

    public float getYLine()
    {
        return yLine;
    }

    public int getRowsDrawn()
    {
        return rowIdx;
    }

    public void setAlignment(int alignment)
    {
        this.alignment = alignment;
    }

    public int getAlignment()
    {
        return alignment;
    }

    public void setIndent(float indent)
    {
        setIndent(indent, true);
    }

    public void setIndent(float indent, boolean repeatFirstLineIndent)
    {
        this.indent = indent;
        lastWasNewline = true;
        this.repeatFirstLineIndent = repeatFirstLineIndent;
    }

    public float getIndent()
    {
        return indent;
    }

    public void setFollowingIndent(float indent)
    {
        followingIndent = indent;
        lastWasNewline = true;
    }

    public float getFollowingIndent()
    {
        return followingIndent;
    }

    public void setRightIndent(float indent)
    {
        rightIndent = indent;
        lastWasNewline = true;
    }

    public float getRightIndent()
    {
        return rightIndent;
    }

    public float getCurrentLeading()
    {
        return currentLeading;
    }

    public boolean getInheritGraphicState()
    {
        return inheritGraphicState;
    }

    public void setInheritGraphicState(boolean inheritGraphicState)
    {
        this.inheritGraphicState = inheritGraphicState;
    }

    public int go()
        throws DocumentException
    {
        return go(false);
    }

    public int go(boolean simulate)
        throws DocumentException
    {
        return go(simulate, null);
    }

    public int go(boolean simulate, IAccessibleElement elementToGo)
        throws DocumentException
    {
        if(composite)
            return goComposite(simulate);
        ListBody lBody = null;
        if(isTagged(canvas) && (elementToGo instanceof ListItem))
            lBody = ((ListItem)elementToGo).getListBody();
        addWaitingPhrase();
        if(bidiLine == null)
            return 1;
        descender = 0.0F;
        linesWritten = 0;
        lastX = 0.0F;
        boolean dirty = false;
        float ratio = spaceCharRatio;
        Object currentValues[] = new Object[2];
        PdfFont currentFont = null;
        Float lastBaseFactor = new Float(0.0F);
        currentValues[1] = lastBaseFactor;
        PdfDocument pdf = null;
        PdfContentByte graphics = null;
        PdfContentByte text = null;
        firstLineY = (0.0F / 0.0F);
        int localRunDirection = 1;
        if(runDirection != 0)
            localRunDirection = runDirection;
        if(canvas != null)
        {
            graphics = canvas;
            pdf = canvas.getPdfDocument();
            if(!isTagged(canvas))
                text = canvas.getDuplicate(inheritGraphicState);
            else
                text = canvas;
        } else
        if(!simulate)
            throw new NullPointerException(MessageLocalization.getComposedMessage("columntext.go.with.simulate.eq.eq.false.and.text.eq.eq.null", new Object[0]));
        if(!simulate)
            if(ratio == 0.0F)
                ratio = text.getPdfWriter().getSpaceCharRatio();
            else
            if(ratio < 0.001F)
                ratio = 0.001F;
        if(!rectangularMode)
        {
            float max = 0.0F;
            for(Iterator i$ = bidiLine.chunks.iterator(); i$.hasNext();)
            {
                PdfChunk c = (PdfChunk)i$.next();
                max = Math.max(max, c.height());
            }

            currentLeading = fixedLeading + max * multipliedLeading;
        }
        float firstIndent = 0.0F;
        int status = 0;
        do
        {
            firstIndent = lastWasNewline ? indent : followingIndent;
            PdfLine line;
            float x1;
            if(rectangularMode)
            {
                if(rectangularWidth <= firstIndent + rightIndent)
                {
                    status = 2;
                    if(bidiLine.isEmpty())
                        status |= 1;
                    break;
                }
                if(bidiLine.isEmpty())
                {
                    status = 1;
                    break;
                }
                line = bidiLine.processLine(leftX, rectangularWidth - firstIndent - rightIndent, alignment, localRunDirection, arabicOptions, minY, yLine, descender);
                if(line == null)
                {
                    status = 1;
                    break;
                }
                float maxSize[] = line.getMaxSize(fixedLeading, multipliedLeading);
                if(isUseAscender() && Float.isNaN(firstLineY))
                    currentLeading = line.getAscender();
                else
                    currentLeading = Math.max(maxSize[0], maxSize[1] - descender);
                if(yLine > maxY || yLine - currentLeading < minY)
                {
                    status = 2;
                    bidiLine.restore();
                    break;
                }
                yLine -= currentLeading;
                if(!simulate && !dirty)
                {
                    text.beginText();
                    dirty = true;
                }
                if(Float.isNaN(firstLineY))
                    firstLineY = yLine;
                updateFilledWidth(rectangularWidth - line.widthLeft());
                x1 = leftX;
            } else
            {
                float yTemp = yLine - currentLeading;
                float xx[] = findLimitsTwoLines();
                if(xx == null)
                {
                    status = 2;
                    if(bidiLine.isEmpty())
                        status |= 1;
                    yLine = yTemp;
                    break;
                }
                if(bidiLine.isEmpty())
                {
                    status = 1;
                    yLine = yTemp;
                    break;
                }
                x1 = Math.max(xx[0], xx[2]);
                float x2 = Math.min(xx[1], xx[3]);
                if(x2 - x1 <= firstIndent + rightIndent)
                    continue;
                if(!simulate && !dirty)
                {
                    text.beginText();
                    dirty = true;
                }
                line = bidiLine.processLine(x1, x2 - x1 - firstIndent - rightIndent, alignment, localRunDirection, arabicOptions, minY, yLine, descender);
                if(line == null)
                {
                    status = 1;
                    yLine = yTemp;
                    break;
                }
            }
            if(isTagged(canvas) && (elementToGo instanceof ListItem) && !Float.isNaN(firstLineY) && !firstLineYDone)
            {
                if(!simulate)
                {
                    ListLabel lbl = ((ListItem)elementToGo).getListLabel();
                    canvas.openMCBlock(lbl);
                    Chunk symbol = new Chunk(((ListItem)elementToGo).getListSymbol());
                    if(!lbl.getTagLabelContent())
                        symbol.setRole(null);
                    showTextAligned(canvas, 0, new Phrase(symbol), leftX + lbl.getIndentation(), firstLineY, 0.0F);
                    canvas.closeMCBlock(lbl);
                }
                firstLineYDone = true;
            }
            if(!simulate)
            {
                if(lBody != null)
                {
                    canvas.openMCBlock(lBody);
                    lBody = null;
                }
                currentValues[0] = currentFont;
                text.setTextMatrix(x1 + (line.isRTL() ? rightIndent : firstIndent) + line.indentLeft(), yLine);
                lastX = pdf.writeLineToContent(line, text, graphics, currentValues, ratio);
                currentFont = (PdfFont)currentValues[0];
            }
            lastWasNewline = repeatFirstLineIndent && line.isNewlineSplit();
            yLine -= line.isNewlineSplit() ? extraParagraphSpace : 0.0F;
            linesWritten++;
            descender = line.getDescender();
        } while(true);
        if(dirty)
        {
            text.endText();
            if(canvas != text)
                canvas.add(text);
        }
        return status;
    }

    public float getExtraParagraphSpace()
    {
        return extraParagraphSpace;
    }

    public void setExtraParagraphSpace(float extraParagraphSpace)
    {
        this.extraParagraphSpace = extraParagraphSpace;
    }

    public void clearChunks()
    {
        if(bidiLine != null)
            bidiLine.clearChunks();
    }

    public float getSpaceCharRatio()
    {
        return spaceCharRatio;
    }

    public void setSpaceCharRatio(float spaceCharRatio)
    {
        this.spaceCharRatio = spaceCharRatio;
    }

    public void setRunDirection(int runDirection)
    {
        if(runDirection < 0 || runDirection > 3)
        {
            throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.run.direction.1", runDirection));
        } else
        {
            this.runDirection = runDirection;
            return;
        }
    }

    public int getRunDirection()
    {
        return runDirection;
    }

    public int getLinesWritten()
    {
        return linesWritten;
    }

    public float getLastX()
    {
        return lastX;
    }

    public int getArabicOptions()
    {
        return arabicOptions;
    }

    public void setArabicOptions(int arabicOptions)
    {
        this.arabicOptions = arabicOptions;
    }

    public float getDescender()
    {
        return descender;
    }

    public static float getWidth(Phrase phrase, int runDirection, int arabicOptions)
    {
        ColumnText ct = new ColumnText(null);
        ct.addText(phrase);
        ct.addWaitingPhrase();
        PdfLine line = ct.bidiLine.processLine(0.0F, 20000F, 0, runDirection, arabicOptions, 0.0F, 0.0F, 0.0F);
        if(line == null)
            return 0.0F;
        else
            return 20000F - line.widthLeft();
    }

    public static float getWidth(Phrase phrase)
    {
        return getWidth(phrase, 1, 0);
    }

    public static void showTextAligned(PdfContentByte canvas, int alignment, Phrase phrase, float x, float y, float rotation, int runDirection, int arabicOptions)
    {
        if(alignment != 0 && alignment != 1 && alignment != 2)
            alignment = 0;
        canvas.saveState();
        ColumnText ct = new ColumnText(canvas);
        float lly = -1F;
        float ury = 2.0F;
        float llx;
        float urx;
        switch(alignment)
        {
        case 0: // '\0'
            llx = 0.0F;
            urx = 20000F;
            break;

        case 2: // '\002'
            llx = -20000F;
            urx = 0.0F;
            break;

        default:
            llx = -20000F;
            urx = 20000F;
            break;
        }
        if(rotation == 0.0F)
        {
            llx += x;
            lly += y;
            urx += x;
            ury += y;
        } else
        {
            double alpha = ((double)rotation * 3.1415926535897931D) / 180D;
            float cos = (float)Math.cos(alpha);
            float sin = (float)Math.sin(alpha);
            canvas.concatCTM(cos, sin, -sin, cos, x, y);
        }
        ct.setSimpleColumn(phrase, llx, lly, urx, ury, 2.0F, alignment);
        if(runDirection == 3)
            if(alignment == 0)
                alignment = 2;
            else
            if(alignment == 2)
                alignment = 0;
        ct.setAlignment(alignment);
        ct.setArabicOptions(arabicOptions);
        ct.setRunDirection(runDirection);
        try
        {
            ct.go();
        }
        catch(DocumentException e)
        {
            throw new ExceptionConverter(e);
        }
        canvas.restoreState();
    }

    public static void showTextAligned(PdfContentByte canvas, int alignment, Phrase phrase, float x, float y, float rotation)
    {
        showTextAligned(canvas, alignment, phrase, x, y, rotation, 1, 0);
    }

    public static float fitText(Font font, String text, Rectangle rect, float maxFontSize, int runDirection)
    {
        int status;
        float precision;
        float min;
        float max;
        float size;
        int k;
        try
        {
            ColumnText ct = null;
            status = 0;
            if(maxFontSize <= 0.0F)
            {
                int cr = 0;
                int lf = 0;
                char t[] = text.toCharArray();
                for(int k = 0; k < t.length; k++)
                {
                    if(t[k] == '\n')
                    {
                        lf++;
                        continue;
                    }
                    if(t[k] == '\r')
                        cr++;
                }

                int minLines = Math.max(cr, lf) + 1;
                maxFontSize = Math.abs(rect.getHeight()) / (float)minLines - 0.001F;
            }
            font.setSize(maxFontSize);
            Phrase ph = new Phrase(text, font);
            ct = new ColumnText(null);
            ct.setSimpleColumn(ph, rect.getLeft(), rect.getBottom(), rect.getRight(), rect.getTop(), maxFontSize, 0);
            ct.setRunDirection(runDirection);
            status = ct.go(true);
            if((status & 1) != 0)
                return maxFontSize;
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        precision = 0.1F;
        min = 0.0F;
        max = maxFontSize;
        size = maxFontSize;
        k = 0;
_L3:
        if(k >= 50)
            break MISSING_BLOCK_LABEL_308;
        size = (min + max) / 2.0F;
        ColumnText ct = new ColumnText(null);
        font.setSize(size);
        ct.setSimpleColumn(new Phrase(text, font), rect.getLeft(), rect.getBottom(), rect.getRight(), rect.getTop(), size, 0);
        ct.setRunDirection(runDirection);
        status = ct.go(true);
        if((status & 1) == 0) goto _L2; else goto _L1
_L1:
        if(max - min < size * precision)
            return size;
        min = size;
        break MISSING_BLOCK_LABEL_302;
_L2:
        max = size;
        k++;
          goto _L3
        return size;
    }

    protected int goComposite(boolean simulate)
        throws DocumentException
    {
        PdfDocument pdf = null;
        if(canvas != null)
            pdf = canvas.pdf;
        if(!rectangularMode)
            throw new DocumentException(MessageLocalization.getComposedMessage("irregular.columns.are.not.supported.in.composite.mode", new Object[0]));
        linesWritten = 0;
        descender = 0.0F;
        boolean firstPass = true;
label0:
        do
        {
            if(compositeElements.isEmpty())
                return 1;
            Element element = (Element)compositeElements.getFirst();
            if(element.type() == 12)
            {
                Paragraph para = (Paragraph)element;
                int status = 0;
                for(int keep = 0; keep < 2; keep++)
                {
                    float lastY = yLine;
                    boolean createHere = false;
                    if(compositeColumn == null)
                    {
                        compositeColumn = new ColumnText(canvas);
                        compositeColumn.setAlignment(para.getAlignment());
                        compositeColumn.setIndent(para.getIndentationLeft() + para.getFirstLineIndent(), false);
                        compositeColumn.setExtraParagraphSpace(para.getExtraParagraphSpace());
                        compositeColumn.setFollowingIndent(para.getIndentationLeft());
                        compositeColumn.setRightIndent(para.getIndentationRight());
                        compositeColumn.setLeading(para.getLeading(), para.getMultipliedLeading());
                        compositeColumn.setRunDirection(runDirection);
                        compositeColumn.setArabicOptions(arabicOptions);
                        compositeColumn.setSpaceCharRatio(spaceCharRatio);
                        compositeColumn.addText(para);
                        if(!firstPass || !adjustFirstLine)
                            yLine -= para.getSpacingBefore();
                        createHere = true;
                    }
                    compositeColumn.setUseAscender(!firstPass && descender != 0.0F || !adjustFirstLine ? false : useAscender);
                    compositeColumn.setInheritGraphicState(inheritGraphicState);
                    compositeColumn.leftX = leftX;
                    compositeColumn.rightX = rightX;
                    compositeColumn.yLine = yLine;
                    compositeColumn.rectangularWidth = rectangularWidth;
                    compositeColumn.rectangularMode = rectangularMode;
                    compositeColumn.minY = minY;
                    compositeColumn.maxY = maxY;
                    boolean keepCandidate = para.getKeepTogether() && createHere && (!firstPass || !adjustFirstLine);
                    boolean s = simulate || keepCandidate && keep == 0;
                    if(isTagged(canvas) && !s)
                        canvas.openMCBlock(para);
                    status = compositeColumn.go(s);
                    if(isTagged(canvas) && !s)
                        canvas.closeMCBlock(para);
                    lastX = compositeColumn.getLastX();
                    updateFilledWidth(compositeColumn.filledWidth);
                    if((status & 1) == 0 && keepCandidate)
                    {
                        compositeColumn = null;
                        yLine = lastY;
                        return 2;
                    }
                    if(simulate || !keepCandidate)
                        break;
                    if(keep == 0)
                    {
                        compositeColumn = null;
                        yLine = lastY;
                    }
                }

                firstPass = false;
                if(compositeColumn.getLinesWritten() > 0)
                {
                    yLine = compositeColumn.yLine;
                    linesWritten += compositeColumn.linesWritten;
                    descender = compositeColumn.descender;
                }
                currentLeading = compositeColumn.currentLeading;
                if((status & 1) != 0)
                {
                    compositeColumn = null;
                    compositeElements.removeFirst();
                    yLine -= para.getSpacingAfter();
                }
                if((status & 2) != 0)
                    return 2;
                continue;
            }
            if(element.type() == 14)
            {
                List list = (List)element;
                ArrayList items = list.getItems();
                ListItem item = null;
                float listIndentation = list.getIndentationLeft();
                int count = 0;
                Stack stack = new Stack();
                for(int k = 0; k < items.size(); k++)
                {
                    Object obj = items.get(k);
                    if(obj instanceof ListItem)
                    {
                        if(count == listIdx)
                        {
                            item = (ListItem)obj;
                            break;
                        }
                        count++;
                    } else
                    if(obj instanceof List)
                    {
                        stack.push(((Object) (new Object[] {
                            list, Integer.valueOf(k), new Float(listIndentation)
                        })));
                        list = (List)obj;
                        items = list.getItems();
                        listIndentation += list.getIndentationLeft();
                        k = -1;
                        continue;
                    }
                    if(k == items.size() - 1 && !stack.isEmpty())
                    {
                        Object objs[] = (Object[])stack.pop();
                        list = (List)objs[0];
                        items = list.getItems();
                        k = ((Integer)objs[1]).intValue();
                        listIndentation = ((Float)objs[2]).floatValue();
                    }
                }

                int status = 0;
                for(int keep = 0; keep < 2; keep++)
                {
                    float lastY = yLine;
                    boolean createHere = false;
                    if(compositeColumn == null)
                    {
                        if(item == null)
                        {
                            listIdx = 0;
                            compositeElements.removeFirst();
                            continue label0;
                        }
                        compositeColumn = new ColumnText(canvas);
                        compositeColumn.setUseAscender(!firstPass && descender != 0.0F || !adjustFirstLine ? false : useAscender);
                        compositeColumn.setInheritGraphicState(inheritGraphicState);
                        compositeColumn.setAlignment(item.getAlignment());
                        compositeColumn.setIndent(item.getIndentationLeft() + listIndentation + item.getFirstLineIndent(), false);
                        compositeColumn.setExtraParagraphSpace(item.getExtraParagraphSpace());
                        compositeColumn.setFollowingIndent(compositeColumn.getIndent());
                        compositeColumn.setRightIndent(item.getIndentationRight() + list.getIndentationRight());
                        compositeColumn.setLeading(item.getLeading(), item.getMultipliedLeading());
                        compositeColumn.setRunDirection(runDirection);
                        compositeColumn.setArabicOptions(arabicOptions);
                        compositeColumn.setSpaceCharRatio(spaceCharRatio);
                        compositeColumn.addText(item);
                        if(!firstPass || !adjustFirstLine)
                            yLine -= item.getSpacingBefore();
                        createHere = true;
                    }
                    compositeColumn.leftX = leftX;
                    compositeColumn.rightX = rightX;
                    compositeColumn.yLine = yLine;
                    compositeColumn.rectangularWidth = rectangularWidth;
                    compositeColumn.rectangularMode = rectangularMode;
                    compositeColumn.minY = minY;
                    compositeColumn.maxY = maxY;
                    boolean keepCandidate = item.getKeepTogether() && createHere && (!firstPass || !adjustFirstLine);
                    boolean s = simulate || keepCandidate && keep == 0;
                    if(isTagged(canvas) && !s)
                    {
                        item.getListLabel().setIndentation(listIndentation);
                        if(list.getFirstItem() == item || compositeColumn != null && compositeColumn.bidiLine != null)
                            canvas.openMCBlock(list);
                        canvas.openMCBlock(item);
                    }
                    status = compositeColumn.go(simulate || keepCandidate && keep == 0, item);
                    if(isTagged(canvas) && !s)
                    {
                        canvas.closeMCBlock(item.getListBody());
                        canvas.closeMCBlock(item);
                        if(list.getLastItem() == item && (status & 1) != 0 || (status & 2) != 0)
                            canvas.closeMCBlock(list);
                    }
                    lastX = compositeColumn.getLastX();
                    updateFilledWidth(compositeColumn.filledWidth);
                    if((status & 1) == 0 && keepCandidate)
                    {
                        compositeColumn = null;
                        yLine = lastY;
                        return 2;
                    }
                    if(simulate || !keepCandidate)
                        break;
                    if(keep == 0)
                    {
                        compositeColumn = null;
                        yLine = lastY;
                    }
                }

                firstPass = false;
                yLine = compositeColumn.yLine;
                linesWritten += compositeColumn.linesWritten;
                descender = compositeColumn.descender;
                currentLeading = compositeColumn.currentLeading;
                if(!isTagged(canvas) && !Float.isNaN(compositeColumn.firstLineY) && !compositeColumn.firstLineYDone)
                {
                    if(!simulate)
                        showTextAligned(canvas, 0, new Phrase(item.getListSymbol()), compositeColumn.leftX + listIndentation, compositeColumn.firstLineY, 0.0F);
                    compositeColumn.firstLineYDone = true;
                }
                if((status & 1) != 0)
                {
                    compositeColumn = null;
                    listIdx++;
                    yLine -= item.getSpacingAfter();
                }
                if((status & 2) != 0)
                    return 2;
                continue;
            }
            if(element.type() == 23)
            {
                PdfPTable table = (PdfPTable)element;
                if(table.size() <= table.getHeaderRows())
                {
                    compositeElements.removeFirst();
                    continue;
                }
                float yTemp = yLine;
                yTemp += descender;
                if(rowIdx == 0 && adjustFirstLine)
                    yTemp -= table.spacingBefore();
                if(yTemp < minY || yTemp > maxY)
                    return 2;
                float yLineWrite = yTemp;
                float x1 = leftX;
                currentLeading = 0.0F;
                float tableWidth;
                if(table.isLockedWidth())
                {
                    tableWidth = table.getTotalWidth();
                    updateFilledWidth(tableWidth);
                } else
                {
                    tableWidth = (rectangularWidth * table.getWidthPercentage()) / 100F;
                    table.setTotalWidth(tableWidth);
                }
                table.normalizeHeadersFooters();
                int headerRows = table.getHeaderRows();
                int footerRows = table.getFooterRows();
                int realHeaderRows = headerRows - footerRows;
                float headerHeight = table.getHeaderHeight();
                float footerHeight = table.getFooterHeight();
                boolean skipHeader = table.isSkipFirstHeader() && rowIdx <= realHeaderRows && (table.isComplete() || rowIdx != realHeaderRows);
                if(!skipHeader)
                {
                    yTemp -= headerHeight;
                    if(yTemp < minY || yTemp > maxY)
                        return 2;
                }
                int k = 0;
                if(rowIdx < headerRows)
                    rowIdx = headerRows;
                if(!table.isComplete())
                    yTemp -= footerHeight;
                PdfPTable.FittingRows fittingRows = table.getFittingRows(yTemp - minY, rowIdx);
                k = fittingRows.lastRow + 1;
                yTemp -= fittingRows.height;
                LOGGER.info((new StringBuilder()).append("Want to split at row ").append(k).toString());
                int kTemp;
                for(kTemp = k; kTemp > rowIdx && kTemp < table.size() && table.getRow(kTemp).isMayNotBreak(); kTemp--);
                if(kTemp > rowIdx && kTemp < k || kTemp == 0 && table.getRow(0).isMayNotBreak() && table.isLoopCheck())
                {
                    yTemp = minY;
                    k = kTemp;
                    table.setLoopCheck(false);
                }
                LOGGER.info((new StringBuilder()).append("Will split at row ").append(k).toString());
                if(table.isSplitLate() && k > 0)
                    fittingRows.correctLastRowChosen(table, k - 1);
                if(!table.isComplete())
                    yTemp += footerHeight;
                if(!table.isSplitRows())
                {
                    splittedRow = -1;
                    if(k == rowIdx)
                    {
                        if(k == table.size())
                        {
                            compositeElements.removeFirst();
                        } else
                        {
                            table.getRows().remove(k);
                            return 2;
                        }
                        continue;
                    }
                } else
                if(table.isSplitLate() && rowIdx < k)
                    splittedRow = -1;
                else
                if(k < table.size())
                {
                    yTemp -= fittingRows.completedRowsHeight - fittingRows.height;
                    float h = yTemp - minY;
                    PdfPRow newRow = table.getRow(k).splitRow(table, k, h);
                    if(newRow == null)
                    {
                        LOGGER.info("Didn't split row!");
                        splittedRow = -1;
                        if(rowIdx == k)
                            return 2;
                    } else
                    {
                        if(k != splittedRow)
                        {
                            splittedRow = k + 1;
                            table = new PdfPTable(table);
                            compositeElements.set(0, table);
                            ArrayList rows = table.getRows();
                            for(int i = headerRows; i < rowIdx; i++)
                                rows.set(i, null);

                        }
                        yTemp = minY;
                        table.getRows().add(++k, newRow);
                        LOGGER.info((new StringBuilder()).append("Inserting row at position ").append(k).toString());
                    }
                }
                firstPass = false;
                if(!simulate)
                {
                    switch(table.getHorizontalAlignment())
                    {
                    case 2: // '\002'
                        x1 += rectangularWidth - tableWidth;
                        break;

                    default:
                        x1 += (rectangularWidth - tableWidth) / 2.0F;
                        break;

                    case 0: // '\0'
                        break;
                    }
                    PdfPTable nt = PdfPTable.shallowCopy(table);
                    ArrayList sub = nt.getRows();
                    ArrayList rows;
                    if(!skipHeader && realHeaderRows > 0)
                    {
                        rows = table.getRows(0, realHeaderRows);
                        if(isTagged(canvas))
                            nt.getHeader().rows = rows;
                        sub.addAll(rows);
                    } else
                    {
                        nt.setHeaderRows(footerRows);
                    }
                    rows = table.getRows(rowIdx, k);
                    if(isTagged(canvas))
                        nt.getBody().rows = rows;
                    sub.addAll(rows);
                    boolean showFooter = !table.isSkipLastFooter();
                    boolean newPageFollows = false;
                    if(k < table.size())
                    {
                        nt.setComplete(true);
                        showFooter = true;
                        newPageFollows = true;
                    }
                    if(footerRows > 0 && nt.isComplete() && showFooter)
                    {
                        ArrayList rows = table.getRows(realHeaderRows, realHeaderRows + footerRows);
                        if(isTagged(canvas))
                            nt.getFooter().rows = rows;
                        sub.addAll(rows);
                    } else
                    {
                        footerRows = 0;
                    }
                    float rowHeight = 0.0F;
                    int lastIdx = sub.size() - 1 - footerRows;
                    PdfPRow last = (PdfPRow)sub.get(lastIdx);
                    if(table.isExtendLastRow(newPageFollows))
                    {
                        rowHeight = last.getMaxHeights();
                        last.setMaxHeights((yTemp - minY) + rowHeight);
                        yTemp = minY;
                    }
                    if(newPageFollows)
                    {
                        PdfPTableEvent tableEvent = table.getTableEvent();
                        if(tableEvent instanceof PdfPTableEventSplit)
                            ((PdfPTableEventSplit)tableEvent).splitTable(table);
                    }
                    if(canvases != null)
                    {
                        if(isTagged(canvases[3]))
                            canvases[3].openMCBlock(table);
                        nt.writeSelectedRows(0, -1, 0, -1, x1, yLineWrite, canvases, false);
                        if(isTagged(canvases[3]))
                            canvases[3].closeMCBlock(table);
                    } else
                    {
                        if(isTagged(canvas))
                            canvas.openMCBlock(table);
                        nt.writeSelectedRows(0, -1, 0, -1, x1, yLineWrite, canvas, false);
                        if(isTagged(canvas))
                            canvas.closeMCBlock(table);
                    }
                    if(splittedRow == k && k < table.size())
                    {
                        PdfPRow splitted = (PdfPRow)table.getRows().get(k);
                        splitted.copyRowContent(nt, lastIdx);
                    } else
                    if(k > 0 && k < table.size())
                    {
                        PdfPRow row = table.getRow(k);
                        row.splitRowspans(table, k - 1, nt, lastIdx);
                    }
                    if(table.isExtendLastRow(newPageFollows))
                        last.setMaxHeights(rowHeight);
                    if(newPageFollows)
                    {
                        PdfPTableEvent tableEvent = table.getTableEvent();
                        if(tableEvent instanceof PdfPTableEventAfterSplit)
                        {
                            PdfPRow row = table.getRow(k);
                            ((PdfPTableEventAfterSplit)tableEvent).afterSplitTable(table, row, k);
                        }
                    }
                } else
                if(table.isExtendLastRow() && minY > -1.073742E+009F)
                    yTemp = minY;
                yLine = yTemp;
                descender = 0.0F;
                currentLeading = 0.0F;
                if(!skipHeader && !table.isComplete())
                    yLine += footerHeight;
                for(; k < table.size() && table.getRowHeight(k) <= 0.0F && !table.hasRowspan(k); k++);
                if(k >= table.size())
                {
                    if(yLine - table.spacingAfter() < minY)
                        yLine = minY;
                    else
                        yLine -= table.spacingAfter();
                    compositeElements.removeFirst();
                    splittedRow = -1;
                    rowIdx = 0;
                } else
                {
                    if(splittedRow != -1)
                    {
                        ArrayList rows = table.getRows();
                        for(int i = rowIdx; i < k; i++)
                            rows.set(i, null);

                    }
                    rowIdx = k;
                    return 2;
                }
            } else
            if(element.type() == 55)
            {
                if(!simulate)
                {
                    DrawInterface zh = (DrawInterface)element;
                    zh.draw(canvas, leftX, minY, rightX, maxY, yLine);
                }
                compositeElements.removeFirst();
            } else
            if(element.type() == 37)
            {
                ArrayList floatingElements = new ArrayList();
                do
                {
                    floatingElements.add(element);
                    compositeElements.removeFirst();
                    element = compositeElements.isEmpty() ? null : (Element)compositeElements.getFirst();
                } while(element != null && element.type() == 37);
                FloatLayout fl = new FloatLayout(floatingElements, useAscender);
                fl.setSimpleColumn(leftX, minY, rightX, yLine);
                int status = fl.layout(canvas, simulate);
                yLine = fl.getYLine();
                descender = 0.0F;
                if((status & 1) == 0)
                {
                    compositeElements.addAll(floatingElements);
                    return status;
                }
            } else
            {
                compositeElements.removeFirst();
            }
        } while(true);
    }

    public PdfContentByte getCanvas()
    {
        return canvas;
    }

    public void setCanvas(PdfContentByte canvas)
    {
        this.canvas = canvas;
        canvases = null;
        if(compositeColumn != null)
            compositeColumn.setCanvas(canvas);
    }

    public void setCanvases(PdfContentByte canvases[])
    {
        this.canvases = canvases;
        canvas = canvases[3];
        if(compositeColumn != null)
            compositeColumn.setCanvases(canvases);
    }

    public PdfContentByte[] getCanvases()
    {
        return canvases;
    }

    public boolean zeroHeightElement()
    {
        return composite && !compositeElements.isEmpty() && ((Element)compositeElements.getFirst()).type() == 55;
    }

    public java.util.List getCompositeElements()
    {
        return compositeElements;
    }

    public boolean isUseAscender()
    {
        return useAscender;
    }

    public void setUseAscender(boolean useAscender)
    {
        this.useAscender = useAscender;
    }

    public static boolean hasMoreText(int status)
    {
        return (status & 1) == 0;
    }

    public float getFilledWidth()
    {
        return filledWidth;
    }

    public void setFilledWidth(float filledWidth)
    {
        this.filledWidth = filledWidth;
    }

    public void updateFilledWidth(float w)
    {
        if(w > filledWidth)
            filledWidth = w;
    }

    public boolean isAdjustFirstLine()
    {
        return adjustFirstLine;
    }

    public void setAdjustFirstLine(boolean adjustFirstLine)
    {
        this.adjustFirstLine = adjustFirstLine;
    }

    private static boolean isTagged(PdfContentByte canvas)
    {
        return canvas != null && canvas.pdf != null && canvas.writer != null && canvas.writer.isTagged();
    }

    private final Logger LOGGER = LoggerFactory.getLogger(co/com/pdf/text/pdf/ColumnText);
    public static final int AR_NOVOWEL = 1;
    public static final int AR_COMPOSEDTASHKEEL = 4;
    public static final int AR_LIG = 8;
    public static final int DIGITS_EN2AN = 32;
    public static final int DIGITS_AN2EN = 64;
    public static final int DIGITS_EN2AN_INIT_LR = 96;
    public static final int DIGITS_EN2AN_INIT_AL = 128;
    public static final int DIGIT_TYPE_AN = 0;
    public static final int DIGIT_TYPE_AN_EXTENDED = 256;
    protected int runDirection;
    public static final float GLOBAL_SPACE_CHAR_RATIO = 0F;
    public static final int START_COLUMN = 0;
    public static final int NO_MORE_TEXT = 1;
    public static final int NO_MORE_COLUMN = 2;
    protected static final int LINE_STATUS_OK = 0;
    protected static final int LINE_STATUS_OFFLIMITS = 1;
    protected static final int LINE_STATUS_NOLINE = 2;
    protected float maxY;
    protected float minY;
    protected float leftX;
    protected float rightX;
    protected int alignment;
    protected ArrayList leftWall;
    protected ArrayList rightWall;
    protected BidiLine bidiLine;
    protected float yLine;
    protected float lastX;
    protected float currentLeading;
    protected float fixedLeading;
    protected float multipliedLeading;
    protected PdfContentByte canvas;
    protected PdfContentByte canvases[];
    protected int lineStatus;
    protected float indent;
    protected float followingIndent;
    protected float rightIndent;
    protected float extraParagraphSpace;
    protected float rectangularWidth;
    protected boolean rectangularMode;
    private float spaceCharRatio;
    private boolean lastWasNewline;
    private boolean repeatFirstLineIndent;
    private int linesWritten;
    private float firstLineY;
    private boolean firstLineYDone;
    private int arabicOptions;
    protected float descender;
    protected boolean composite;
    protected ColumnText compositeColumn;
    protected LinkedList compositeElements;
    protected int listIdx;
    protected int rowIdx;
    private int splittedRow;
    protected Phrase waitPhrase;
    private boolean useAscender;
    private float filledWidth;
    private boolean adjustFirstLine;
    private boolean inheritGraphicState;
}
