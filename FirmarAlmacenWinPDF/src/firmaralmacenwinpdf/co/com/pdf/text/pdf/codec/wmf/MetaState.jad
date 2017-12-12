// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MetaState.java

package co.com.pdf.text.pdf.codec.wmf;

import co.com.pdf.text.BaseColor;
import co.com.pdf.text.pdf.PdfContentByte;
import java.util.ArrayList;
import java.util.Stack;

// Referenced classes of package co.com.pdf.text.pdf.codec.wmf:
//            Point, MetaPen, MetaBrush, MetaFont, 
//            MetaObject

public class MetaState
{

    public MetaState()
    {
        currentBackgroundColor = BaseColor.WHITE;
        currentTextColor = BaseColor.BLACK;
        backgroundMode = 2;
        polyFillMode = 1;
        lineJoin = 1;
        savedStates = new Stack();
        MetaObjects = new ArrayList();
        currentPoint = new Point(0, 0);
        currentPen = new MetaPen();
        currentBrush = new MetaBrush();
        currentFont = new MetaFont();
    }

    public MetaState(MetaState state)
    {
        currentBackgroundColor = BaseColor.WHITE;
        currentTextColor = BaseColor.BLACK;
        backgroundMode = 2;
        polyFillMode = 1;
        lineJoin = 1;
        setMetaState(state);
    }

    public void setMetaState(MetaState state)
    {
        savedStates = state.savedStates;
        MetaObjects = state.MetaObjects;
        currentPoint = state.currentPoint;
        currentPen = state.currentPen;
        currentBrush = state.currentBrush;
        currentFont = state.currentFont;
        currentBackgroundColor = state.currentBackgroundColor;
        currentTextColor = state.currentTextColor;
        backgroundMode = state.backgroundMode;
        polyFillMode = state.polyFillMode;
        textAlign = state.textAlign;
        lineJoin = state.lineJoin;
        offsetWx = state.offsetWx;
        offsetWy = state.offsetWy;
        extentWx = state.extentWx;
        extentWy = state.extentWy;
        scalingX = state.scalingX;
        scalingY = state.scalingY;
    }

    public void addMetaObject(MetaObject object)
    {
        for(int k = 0; k < MetaObjects.size(); k++)
            if(MetaObjects.get(k) == null)
            {
                MetaObjects.set(k, object);
                return;
            }

        MetaObjects.add(object);
    }

    public void selectMetaObject(int index, PdfContentByte cb)
    {
        MetaObject obj = (MetaObject)MetaObjects.get(index);
        if(obj == null)
            return;
        switch(obj.getType())
        {
        default:
            break;

        case 2: // '\002'
        {
            currentBrush = (MetaBrush)obj;
            int style = currentBrush.getStyle();
            if(style == 0)
            {
                BaseColor color = currentBrush.getColor();
                cb.setColorFill(color);
            } else
            if(style == 2)
            {
                BaseColor color = currentBackgroundColor;
                cb.setColorFill(color);
            }
            break;
        }

        case 1: // '\001'
        {
            currentPen = (MetaPen)obj;
            int style = currentPen.getStyle();
            if(style == 5)
                break;
            BaseColor color = currentPen.getColor();
            cb.setColorStroke(color);
            cb.setLineWidth(Math.abs(((float)currentPen.getPenWidth() * scalingX) / (float)extentWx));
            switch(style)
            {
            case 1: // '\001'
                cb.setLineDash(18F, 6F, 0.0F);
                break;

            case 3: // '\003'
                cb.setLiteral("[9 6 3 6]0 d\n");
                break;

            case 4: // '\004'
                cb.setLiteral("[9 3 3 3 3 3]0 d\n");
                break;

            case 2: // '\002'
                cb.setLineDash(3F, 0.0F);
                break;

            default:
                cb.setLineDash(0.0F);
                break;
            }
            break;
        }

        case 3: // '\003'
        {
            currentFont = (MetaFont)obj;
            break;
        }
        }
    }

    public void deleteMetaObject(int index)
    {
        MetaObjects.set(index, null);
    }

    public void saveState(PdfContentByte cb)
    {
        cb.saveState();
        MetaState state = new MetaState(this);
        savedStates.push(state);
    }

    public void restoreState(int index, PdfContentByte cb)
    {
        int pops;
        if(index < 0)
            pops = Math.min(-index, savedStates.size());
        else
            pops = Math.max(savedStates.size() - index, 0);
        if(pops == 0)
            return;
        MetaState state;
        for(state = null; pops-- != 0; state = (MetaState)savedStates.pop())
            cb.restoreState();

        setMetaState(state);
    }

    public void cleanup(PdfContentByte cb)
    {
        for(int k = savedStates.size(); k-- > 0;)
            cb.restoreState();

    }

    public float transformX(int x)
    {
        return (((float)x - (float)offsetWx) * scalingX) / (float)extentWx;
    }

    public float transformY(int y)
    {
        return (1.0F - ((float)y - (float)offsetWy) / (float)extentWy) * scalingY;
    }

    public void setScalingX(float scalingX)
    {
        this.scalingX = scalingX;
    }

    public void setScalingY(float scalingY)
    {
        this.scalingY = scalingY;
    }

    public void setOffsetWx(int offsetWx)
    {
        this.offsetWx = offsetWx;
    }

    public void setOffsetWy(int offsetWy)
    {
        this.offsetWy = offsetWy;
    }

    public void setExtentWx(int extentWx)
    {
        this.extentWx = extentWx;
    }

    public void setExtentWy(int extentWy)
    {
        this.extentWy = extentWy;
    }

    public float transformAngle(float angle)
    {
        float ta = scalingY >= 0.0F ? angle : -angle;
        return (float)(scalingX >= 0.0F ? ta : 3.1415926535897931D - (double)ta);
    }

    public void setCurrentPoint(Point p)
    {
        currentPoint = p;
    }

    public Point getCurrentPoint()
    {
        return currentPoint;
    }

    public MetaBrush getCurrentBrush()
    {
        return currentBrush;
    }

    public MetaPen getCurrentPen()
    {
        return currentPen;
    }

    public MetaFont getCurrentFont()
    {
        return currentFont;
    }

    public BaseColor getCurrentBackgroundColor()
    {
        return currentBackgroundColor;
    }

    public void setCurrentBackgroundColor(BaseColor currentBackgroundColor)
    {
        this.currentBackgroundColor = currentBackgroundColor;
    }

    public BaseColor getCurrentTextColor()
    {
        return currentTextColor;
    }

    public void setCurrentTextColor(BaseColor currentTextColor)
    {
        this.currentTextColor = currentTextColor;
    }

    public int getBackgroundMode()
    {
        return backgroundMode;
    }

    public void setBackgroundMode(int backgroundMode)
    {
        this.backgroundMode = backgroundMode;
    }

    public int getTextAlign()
    {
        return textAlign;
    }

    public void setTextAlign(int textAlign)
    {
        this.textAlign = textAlign;
    }

    public int getPolyFillMode()
    {
        return polyFillMode;
    }

    public void setPolyFillMode(int polyFillMode)
    {
        this.polyFillMode = polyFillMode;
    }

    public void setLineJoinRectangle(PdfContentByte cb)
    {
        if(lineJoin != 0)
        {
            lineJoin = 0;
            cb.setLineJoin(0);
        }
    }

    public void setLineJoinPolygon(PdfContentByte cb)
    {
        if(lineJoin == 0)
        {
            lineJoin = 1;
            cb.setLineJoin(1);
        }
    }

    public boolean getLineNeutral()
    {
        return lineJoin == 0;
    }

    public static final int TA_NOUPDATECP = 0;
    public static final int TA_UPDATECP = 1;
    public static final int TA_LEFT = 0;
    public static final int TA_RIGHT = 2;
    public static final int TA_CENTER = 6;
    public static final int TA_TOP = 0;
    public static final int TA_BOTTOM = 8;
    public static final int TA_BASELINE = 24;
    public static final int TRANSPARENT = 1;
    public static final int OPAQUE = 2;
    public static final int ALTERNATE = 1;
    public static final int WINDING = 2;
    public Stack savedStates;
    public ArrayList MetaObjects;
    public Point currentPoint;
    public MetaPen currentPen;
    public MetaBrush currentBrush;
    public MetaFont currentFont;
    public BaseColor currentBackgroundColor;
    public BaseColor currentTextColor;
    public int backgroundMode;
    public int polyFillMode;
    public int lineJoin;
    public int textAlign;
    public int offsetWx;
    public int offsetWy;
    public int extentWx;
    public int extentWy;
    public float scalingX;
    public float scalingY;
}
