// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfDiv.java

package co.com.pdf.text.pdf;

import co.com.pdf.awt.geom.AffineTransform;
import co.com.pdf.text.*;
import co.com.pdf.text.api.Spaceable;
import co.com.pdf.text.pdf.interfaces.IAccessibleElement;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfPTable, FloatLayout, PdfObject, PdfContentByte, 
//            PdfName

public class PdfDiv
    implements Element, Spaceable, IAccessibleElement
{
    public static final class PositionType extends Enum
    {

        public static PositionType[] values()
        {
            return (PositionType[])$VALUES.clone();
        }

        public static PositionType valueOf(String name)
        {
            return (PositionType)Enum.valueOf(co/com/pdf/text/pdf/PdfDiv$PositionType, name);
        }

        public static final PositionType STATIC;
        public static final PositionType ABSOLUTE;
        public static final PositionType FIXED;
        public static final PositionType RELATIVE;
        private static final PositionType $VALUES[];

        static 
        {
            STATIC = new PositionType("STATIC", 0);
            ABSOLUTE = new PositionType("ABSOLUTE", 1);
            FIXED = new PositionType("FIXED", 2);
            RELATIVE = new PositionType("RELATIVE", 3);
            $VALUES = (new PositionType[] {
                STATIC, ABSOLUTE, FIXED, RELATIVE
            });
        }

        private PositionType(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class FloatType extends Enum
    {

        public static FloatType[] values()
        {
            return (FloatType[])$VALUES.clone();
        }

        public static FloatType valueOf(String name)
        {
            return (FloatType)Enum.valueOf(co/com/pdf/text/pdf/PdfDiv$FloatType, name);
        }

        public static final FloatType NONE;
        public static final FloatType LEFT;
        public static final FloatType RIGHT;
        private static final FloatType $VALUES[];

        static 
        {
            NONE = new FloatType("NONE", 0);
            LEFT = new FloatType("LEFT", 1);
            RIGHT = new FloatType("RIGHT", 2);
            $VALUES = (new FloatType[] {
                NONE, LEFT, RIGHT
            });
        }

        private FloatType(String s, int i)
        {
            super(s, i);
        }
    }


    public float getContentWidth()
    {
        return contentWidth;
    }

    public void setContentWidth(float contentWidth)
    {
        this.contentWidth = contentWidth;
    }

    public float getContentHeight()
    {
        return contentHeight;
    }

    public void setContentHeight(float contentHeight)
    {
        this.contentHeight = contentHeight;
    }

    public float getActualHeight()
    {
        return height == null || height.floatValue() < contentHeight ? contentHeight : height.floatValue();
    }

    public float getActualWidth()
    {
        return width == null || width.floatValue() < contentWidth ? contentWidth : width.floatValue();
    }

    public Float getPercentageHeight()
    {
        return percentageHeight;
    }

    public void setPercentageHeight(Float percentageHeight)
    {
        this.percentageHeight = percentageHeight;
    }

    public Float getPercentageWidth()
    {
        return percentageWidth;
    }

    public void setPercentageWidth(Float percentageWidth)
    {
        this.percentageWidth = percentageWidth;
    }

    public BaseColor getBackgroundColor()
    {
        return backgroundColor;
    }

    public void setBackgroundColor(BaseColor backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public PdfDiv()
    {
        left = null;
        top = null;
        right = null;
        bottom = null;
        width = null;
        height = null;
        percentageHeight = null;
        percentageWidth = null;
        contentWidth = 0.0F;
        contentHeight = 0.0F;
        textAlignment = -1;
        paddingLeft = 0.0F;
        paddingRight = 0.0F;
        paddingTop = 0.0F;
        paddingBottom = 0.0F;
        floatType = FloatType.NONE;
        position = PositionType.STATIC;
        floatLayout = null;
        role = PdfName.DIV;
        accessibleAttributes = null;
        id = new AccessibleElementId();
        backgroundColor = null;
        content = new ArrayList();
    }

    public List getChunks()
    {
        return new ArrayList();
    }

    public int type()
    {
        return 37;
    }

    public boolean isContent()
    {
        return true;
    }

    public boolean isNestable()
    {
        return true;
    }

    public boolean process(ElementListener listener)
    {
        try
        {
            return listener.add(this);
        }
        catch(DocumentException de)
        {
            return false;
        }
    }

    public void setSpacingBefore(float spacing)
    {
        spacingBefore = spacing;
    }

    public void setSpacingAfter(float spacing)
    {
        spacingAfter = spacing;
    }

    public float getSpacingBefore()
    {
        return spacingBefore;
    }

    public float getSpacingAfter()
    {
        return spacingAfter;
    }

    public int getTextAlignment()
    {
        return textAlignment;
    }

    public void setTextAlignment(int textAlignment)
    {
        this.textAlignment = textAlignment;
    }

    public void addElement(Element element)
    {
        if(element instanceof PdfPTable)
            ((PdfPTable)element).setSplitLate(false);
        content.add(element);
    }

    public Float getLeft()
    {
        return left;
    }

    public void setLeft(Float left)
    {
        this.left = left;
    }

    public Float getRight()
    {
        return right;
    }

    public void setRight(Float right)
    {
        this.right = right;
    }

    public Float getTop()
    {
        return top;
    }

    public void setTop(Float top)
    {
        this.top = top;
    }

    public Float getBottom()
    {
        return bottom;
    }

    public void setBottom(Float bottom)
    {
        this.bottom = bottom;
    }

    public Float getWidth()
    {
        return width;
    }

    public void setWidth(Float width)
    {
        this.width = width;
    }

    public Float getHeight()
    {
        return height;
    }

    public void setHeight(Float height)
    {
        this.height = height;
    }

    public float getPaddingLeft()
    {
        return paddingLeft;
    }

    public void setPaddingLeft(float paddingLeft)
    {
        this.paddingLeft = paddingLeft;
    }

    public float getPaddingRight()
    {
        return paddingRight;
    }

    public void setPaddingRight(float paddingRight)
    {
        this.paddingRight = paddingRight;
    }

    public float getPaddingTop()
    {
        return paddingTop;
    }

    public void setPaddingTop(float paddingTop)
    {
        this.paddingTop = paddingTop;
    }

    public float getPaddingBottom()
    {
        return paddingBottom;
    }

    public void setPaddingBottom(float paddingBottom)
    {
        this.paddingBottom = paddingBottom;
    }

    public FloatType getFloatType()
    {
        return floatType;
    }

    public void setFloatType(FloatType floatType)
    {
        this.floatType = floatType;
    }

    public PositionType getPosition()
    {
        return position;
    }

    public void setPosition(PositionType position)
    {
        this.position = position;
    }

    public ArrayList getContent()
    {
        return content;
    }

    public void setContent(ArrayList content)
    {
        this.content = content;
    }

    public int layout(PdfContentByte canvas, boolean useAscender, boolean simulate, float llx, float lly, float urx, float ury)
        throws DocumentException
    {
        float leftX = Math.min(llx, urx);
        float maxY = Math.max(lly, ury);
        float minY = Math.min(lly, ury);
        float rightX = Math.max(llx, urx);
        float yLine = maxY;
        boolean contentCutByFixedHeight = false;
        if(width != null && width.floatValue() > 0.0F)
        {
            if(width.floatValue() < rightX - leftX)
                rightX = leftX + width.floatValue();
            else
            if(width.floatValue() > rightX - leftX)
                return 2;
        } else
        if(percentageWidth != null)
        {
            contentWidth = (rightX - leftX) * percentageWidth.floatValue();
            rightX = leftX + contentWidth;
        }
        if(height != null && height.floatValue() > 0.0F)
        {
            if(height.floatValue() < maxY - minY)
            {
                minY = maxY - height.floatValue();
                contentCutByFixedHeight = true;
            } else
            if(height.floatValue() > maxY - minY)
                return 2;
        } else
        if(percentageHeight != null)
        {
            if((double)percentageHeight.floatValue() < 1.0D)
                contentCutByFixedHeight = true;
            contentHeight = (maxY - minY) * percentageHeight.floatValue();
            minY = maxY - contentHeight;
        }
        if(!simulate && position == PositionType.RELATIVE)
        {
            Float translationX = null;
            if(left != null)
                translationX = left;
            else
            if(right != null)
                translationX = Float.valueOf(-right.floatValue());
            else
                translationX = Float.valueOf(0.0F);
            Float translationY = null;
            if(top != null)
                translationY = Float.valueOf(-top.floatValue());
            else
            if(bottom != null)
                translationY = bottom;
            else
                translationY = Float.valueOf(0.0F);
            canvas.saveState();
            canvas.transform(new AffineTransform(1.0F, 0.0F, 0.0F, 1.0F, translationX.floatValue(), translationY.floatValue()));
        }
        if(!simulate && backgroundColor != null && getActualWidth() > 0.0F && getActualHeight() > 0.0F)
        {
            float backgroundWidth = getActualWidth();
            float backgroundHeight = getActualHeight();
            if(width != null)
                backgroundWidth = width.floatValue() <= 0.0F ? 0.0F : width.floatValue();
            if(height != null)
                backgroundHeight = height.floatValue() <= 0.0F ? 0.0F : height.floatValue();
            if(backgroundWidth > 0.0F && backgroundHeight > 0.0F)
            {
                Rectangle background = new Rectangle(leftX, maxY - backgroundHeight, leftX + backgroundWidth, maxY);
                background.setBackgroundColor(backgroundColor);
                canvas.rectangle(background);
            }
        }
        if(percentageWidth == null)
            contentWidth = 0.0F;
        if(percentageHeight == null)
            contentHeight = 0.0F;
        minY += paddingBottom;
        leftX += paddingLeft;
        rightX -= paddingRight;
        yLine -= paddingTop;
        int status = 1;
        if(!content.isEmpty())
        {
            FloatLayout floatLayout = null;
            if(this.floatLayout == null)
            {
                ArrayList floatingElements = new ArrayList();
                floatingElements.addAll(content);
                if(simulate)
                    floatLayout = new FloatLayout(floatingElements, useAscender);
                else
                    floatLayout = this.floatLayout = new FloatLayout(floatingElements, useAscender);
            } else
            if(simulate)
            {
                ArrayList floatingElements = new ArrayList();
                floatingElements.addAll(this.floatLayout.content);
                floatLayout = new FloatLayout(floatingElements, useAscender);
            } else
            {
                floatLayout = this.floatLayout;
            }
            floatLayout.setSimpleColumn(leftX, minY, rightX, yLine);
            status = floatLayout.layout(canvas, simulate);
            yLine = floatLayout.getYLine();
            if(percentageWidth == null && contentWidth < floatLayout.getFilledWidth())
                contentWidth = floatLayout.getFilledWidth();
        }
        if(!simulate && position == PositionType.RELATIVE)
            canvas.restoreState();
        yLine -= paddingBottom;
        if(percentageHeight == null)
            contentHeight = maxY - yLine;
        if(percentageWidth == null)
            contentWidth += paddingLeft + paddingRight;
        return contentCutByFixedHeight ? 1 : status;
    }

    public PdfObject getAccessibleAttribute(PdfName key)
    {
        if(accessibleAttributes != null)
            return (PdfObject)accessibleAttributes.get(key);
        else
            return null;
    }

    public void setAccessibleAttribute(PdfName key, PdfObject value)
    {
        if(accessibleAttributes == null)
            accessibleAttributes = new HashMap();
        accessibleAttributes.put(key, value);
    }

    public HashMap getAccessibleAttributes()
    {
        return accessibleAttributes;
    }

    public PdfName getRole()
    {
        return role;
    }

    public void setRole(PdfName role)
    {
        this.role = role;
    }

    public AccessibleElementId getId()
    {
        return id;
    }

    public void setId(AccessibleElementId id)
    {
        this.id = id;
    }

    private ArrayList content;
    private Float left;
    private Float top;
    private Float right;
    private Float bottom;
    private Float width;
    private Float height;
    private Float percentageHeight;
    private Float percentageWidth;
    private float contentWidth;
    private float contentHeight;
    private int textAlignment;
    private float paddingLeft;
    private float paddingRight;
    private float paddingTop;
    private float paddingBottom;
    private FloatType floatType;
    private PositionType position;
    private FloatLayout floatLayout;
    protected PdfName role;
    protected HashMap accessibleAttributes;
    protected AccessibleElementId id;
    private BaseColor backgroundColor;
    protected float spacingBefore;
    protected float spacingAfter;
}
