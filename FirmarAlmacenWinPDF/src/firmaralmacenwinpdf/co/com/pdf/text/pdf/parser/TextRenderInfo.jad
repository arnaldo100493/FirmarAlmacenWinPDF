// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TextRenderInfo.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.BaseColor;
import co.com.pdf.text.pdf.CMapAwareDocumentFont;
import co.com.pdf.text.pdf.DocumentFont;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            Matrix, MarkedContentInfo, LineSegment, Vector, 
//            GraphicsState

public class TextRenderInfo
{

    TextRenderInfo(String text, GraphicsState gs, Matrix textMatrix, Collection markedContentInfo)
    {
        this.text = text;
        textToUserSpaceTransformMatrix = textMatrix.multiply(gs.ctm);
        this.gs = gs;
        markedContentInfos = new ArrayList(markedContentInfo);
    }

    private TextRenderInfo(TextRenderInfo parent, int charIndex, float horizontalOffset)
    {
        text = parent.text.substring(charIndex, charIndex + 1);
        textToUserSpaceTransformMatrix = (new Matrix(horizontalOffset, 0.0F)).multiply(parent.textToUserSpaceTransformMatrix);
        gs = parent.gs;
        markedContentInfos = parent.markedContentInfos;
    }

    public String getText()
    {
        return text;
    }

    public boolean hasMcid(int mcid)
    {
        return hasMcid(mcid, false);
    }

    public boolean hasMcid(int mcid, boolean checkTheTopmostLevelOnly)
    {
label0:
        {
            MarkedContentInfo info;
            if(checkTheTopmostLevelOnly)
            {
                if(markedContentInfos instanceof ArrayList)
                {
                    ArrayList mci = (ArrayList)markedContentInfos;
                    info = mci.size() <= 0 ? null : (MarkedContentInfo)mci.get(mci.size() - 1);
                    return info == null || !info.hasMcid() ? false : info.getMcid() == mcid;
                }
                break label0;
            }
            Iterator i$ = markedContentInfos.iterator();
            do
            {
                if(!i$.hasNext())
                    break label0;
                info = (MarkedContentInfo)i$.next();
            } while(!info.hasMcid() || info.getMcid() != mcid);
            return true;
        }
        return false;
    }

    float getUnscaledWidth()
    {
        return getStringWidth(text);
    }

    public LineSegment getBaseline()
    {
        return getUnscaledBaselineWithOffset(0.0F + gs.rise).transformBy(textToUserSpaceTransformMatrix);
    }

    public LineSegment getAscentLine()
    {
        float ascent = gs.getFont().getFontDescriptor(1, gs.getFontSize());
        return getUnscaledBaselineWithOffset(ascent + gs.rise).transformBy(textToUserSpaceTransformMatrix);
    }

    public LineSegment getDescentLine()
    {
        float descent = gs.getFont().getFontDescriptor(3, gs.getFontSize());
        return getUnscaledBaselineWithOffset(descent + gs.rise).transformBy(textToUserSpaceTransformMatrix);
    }

    private LineSegment getUnscaledBaselineWithOffset(float yOffset)
    {
        float correctedUnscaledWidth = getUnscaledWidth() - gs.characterSpacing * gs.horizontalScaling;
        return new LineSegment(new Vector(0.0F, yOffset, 1.0F), new Vector(correctedUnscaledWidth, yOffset, 1.0F));
    }

    public DocumentFont getFont()
    {
        return gs.getFont();
    }

    public float getRise()
    {
        if(gs.rise == 0.0F)
            return 0.0F;
        else
            return convertHeightFromTextSpaceToUserSpace(gs.rise);
    }

    private float convertWidthFromTextSpaceToUserSpace(float width)
    {
        LineSegment textSpace = new LineSegment(new Vector(0.0F, 0.0F, 1.0F), new Vector(width, 0.0F, 1.0F));
        LineSegment userSpace = textSpace.transformBy(textToUserSpaceTransformMatrix);
        return userSpace.getLength();
    }

    private float convertHeightFromTextSpaceToUserSpace(float height)
    {
        LineSegment textSpace = new LineSegment(new Vector(0.0F, 0.0F, 1.0F), new Vector(0.0F, height, 1.0F));
        LineSegment userSpace = textSpace.transformBy(textToUserSpaceTransformMatrix);
        return userSpace.getLength();
    }

    public float getSingleSpaceWidth()
    {
        return convertWidthFromTextSpaceToUserSpace(getUnscaledFontSpaceWidth());
    }

    public int getTextRenderMode()
    {
        return gs.renderMode;
    }

    public BaseColor getFillColor()
    {
        return gs.fillColor;
    }

    public BaseColor getStrokeColor()
    {
        return gs.strokeColor;
    }

    private float getUnscaledFontSpaceWidth()
    {
        char charToUse = ' ';
        if(gs.font.getWidth(charToUse) == 0)
            charToUse = '\240';
        return getStringWidth(String.valueOf(charToUse));
    }

    private float getStringWidth(String string)
    {
        DocumentFont font = gs.font;
        char chars[] = string.toCharArray();
        float totalWidth = 0.0F;
        for(int i = 0; i < chars.length; i++)
        {
            float w = (float)font.getWidth(chars[i]) / 1000F;
            float wordSpacing = chars[i] != ' ' ? 0.0F : gs.wordSpacing;
            totalWidth += (w * gs.fontSize + gs.characterSpacing + wordSpacing) * gs.horizontalScaling;
        }

        return totalWidth;
    }

    public List getCharacterRenderInfos()
    {
        List rslt = new ArrayList(text.length());
        DocumentFont font = gs.font;
        char chars[] = text.toCharArray();
        float totalWidth = 0.0F;
        for(int i = 0; i < chars.length; i++)
        {
            float w = (float)font.getWidth(chars[i]) / 1000F;
            float wordSpacing = chars[i] != ' ' ? 0.0F : gs.wordSpacing;
            TextRenderInfo subInfo = new TextRenderInfo(this, i, totalWidth);
            rslt.add(subInfo);
            totalWidth += (w * gs.fontSize + gs.characterSpacing + wordSpacing) * gs.horizontalScaling;
        }

        return rslt;
    }

    private final String text;
    private final Matrix textToUserSpaceTransformMatrix;
    private final GraphicsState gs;
    private final Collection markedContentInfos;
}
