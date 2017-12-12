// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPCell.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.events.PdfPCellEventForwarder;
import co.com.pdf.text.pdf.interfaces.IAccessibleElement;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            ColumnText, PdfPTable, PdfDiv, PdfObject, 
//            PdfName, PdfPRow, PdfPCellEvent, PdfPHeaderCell

public class PdfPCell extends Rectangle
    implements IAccessibleElement
{

    public PdfPCell()
    {
        super(0.0F, 0.0F, 0.0F, 0.0F);
        column = new ColumnText(null);
        verticalAlignment = 4;
        paddingLeft = 2.0F;
        paddingRight = 2.0F;
        paddingTop = 2.0F;
        paddingBottom = 2.0F;
        fixedHeight = 0.0F;
        noWrap = false;
        colspan = 1;
        rowspan = 1;
        useDescender = false;
        useBorderPadding = false;
        role = PdfName.TD;
        accessibleAttributes = null;
        id = new AccessibleElementId();
        headers = null;
        borderWidth = 0.5F;
        border = 15;
        column.setLeading(0.0F, 1.0F);
    }

    public PdfPCell(Phrase phrase)
    {
        super(0.0F, 0.0F, 0.0F, 0.0F);
        column = new ColumnText(null);
        verticalAlignment = 4;
        paddingLeft = 2.0F;
        paddingRight = 2.0F;
        paddingTop = 2.0F;
        paddingBottom = 2.0F;
        fixedHeight = 0.0F;
        noWrap = false;
        colspan = 1;
        rowspan = 1;
        useDescender = false;
        useBorderPadding = false;
        role = PdfName.TD;
        accessibleAttributes = null;
        id = new AccessibleElementId();
        headers = null;
        borderWidth = 0.5F;
        border = 15;
        column.addText(this.phrase = phrase);
        column.setLeading(0.0F, 1.0F);
    }

    public PdfPCell(Image image)
    {
        this(image, false);
    }

    public PdfPCell(Image image, boolean fit)
    {
        super(0.0F, 0.0F, 0.0F, 0.0F);
        column = new ColumnText(null);
        verticalAlignment = 4;
        paddingLeft = 2.0F;
        paddingRight = 2.0F;
        paddingTop = 2.0F;
        paddingBottom = 2.0F;
        fixedHeight = 0.0F;
        noWrap = false;
        colspan = 1;
        rowspan = 1;
        useDescender = false;
        useBorderPadding = false;
        role = PdfName.TD;
        accessibleAttributes = null;
        id = new AccessibleElementId();
        headers = null;
        borderWidth = 0.5F;
        border = 15;
        column.setLeading(0.0F, 1.0F);
        if(fit)
        {
            this.image = image;
            setPadding(borderWidth / 2.0F);
        } else
        {
            image.setScaleToFitLineWhenOverflow(false);
            column.addText(phrase = new Phrase(new Chunk(image, 0.0F, 0.0F, true)));
            setPadding(0.0F);
        }
    }

    public PdfPCell(PdfPTable table)
    {
        this(table, ((PdfPCell) (null)));
    }

    public PdfPCell(PdfPTable table, PdfPCell style)
    {
        super(0.0F, 0.0F, 0.0F, 0.0F);
        column = new ColumnText(null);
        verticalAlignment = 4;
        paddingLeft = 2.0F;
        paddingRight = 2.0F;
        paddingTop = 2.0F;
        paddingBottom = 2.0F;
        fixedHeight = 0.0F;
        noWrap = false;
        colspan = 1;
        rowspan = 1;
        useDescender = false;
        useBorderPadding = false;
        role = PdfName.TD;
        accessibleAttributes = null;
        id = new AccessibleElementId();
        headers = null;
        borderWidth = 0.5F;
        border = 15;
        column.setLeading(0.0F, 1.0F);
        this.table = table;
        table.setWidthPercentage(100F);
        table.setExtendLastRow(true);
        column.addElement(table);
        if(style != null)
        {
            cloneNonPositionParameters(style);
            verticalAlignment = style.verticalAlignment;
            paddingLeft = style.paddingLeft;
            paddingRight = style.paddingRight;
            paddingTop = style.paddingTop;
            paddingBottom = style.paddingBottom;
            colspan = style.colspan;
            rowspan = style.rowspan;
            cellEvent = style.cellEvent;
            useDescender = style.useDescender;
            useBorderPadding = style.useBorderPadding;
            rotation = style.rotation;
        } else
        {
            setPadding(0.0F);
        }
    }

    public PdfPCell(PdfPCell cell)
    {
        super(cell.llx, cell.lly, cell.urx, cell.ury);
        column = new ColumnText(null);
        verticalAlignment = 4;
        paddingLeft = 2.0F;
        paddingRight = 2.0F;
        paddingTop = 2.0F;
        paddingBottom = 2.0F;
        fixedHeight = 0.0F;
        noWrap = false;
        colspan = 1;
        rowspan = 1;
        useDescender = false;
        useBorderPadding = false;
        role = PdfName.TD;
        accessibleAttributes = null;
        id = new AccessibleElementId();
        headers = null;
        cloneNonPositionParameters(cell);
        verticalAlignment = cell.verticalAlignment;
        paddingLeft = cell.paddingLeft;
        paddingRight = cell.paddingRight;
        paddingTop = cell.paddingTop;
        paddingBottom = cell.paddingBottom;
        phrase = cell.phrase;
        fixedHeight = cell.fixedHeight;
        minimumHeight = cell.minimumHeight;
        noWrap = cell.noWrap;
        colspan = cell.colspan;
        rowspan = cell.rowspan;
        if(cell.table != null)
            table = new PdfPTable(cell.table);
        image = Image.getInstance(cell.image);
        cellEvent = cell.cellEvent;
        useDescender = cell.useDescender;
        column = ColumnText.duplicate(cell.column);
        useBorderPadding = cell.useBorderPadding;
        rotation = cell.rotation;
        id = cell.id;
        role = cell.role;
        if(cell.accessibleAttributes != null)
            accessibleAttributes = new HashMap(cell.accessibleAttributes);
        headers = cell.headers;
    }

    public void addElement(Element element)
    {
        if(table != null)
        {
            table = null;
            column.setText(null);
        }
        if(element instanceof PdfPTable)
            ((PdfPTable)element).setSplitLate(false);
        else
        if(element instanceof PdfDiv)
        {
            Iterator i$ = ((PdfDiv)element).getContent().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                Element divChildElement = (Element)i$.next();
                if(divChildElement instanceof PdfPTable)
                    ((PdfPTable)divChildElement).setSplitLate(false);
            } while(true);
        }
        column.addElement(element);
    }

    public Phrase getPhrase()
    {
        return phrase;
    }

    public void setPhrase(Phrase phrase)
    {
        table = null;
        image = null;
        column.setText(this.phrase = phrase);
    }

    public int getHorizontalAlignment()
    {
        return column.getAlignment();
    }

    public void setHorizontalAlignment(int horizontalAlignment)
    {
        column.setAlignment(horizontalAlignment);
    }

    public int getVerticalAlignment()
    {
        return verticalAlignment;
    }

    public void setVerticalAlignment(int verticalAlignment)
    {
        if(table != null)
            table.setExtendLastRow(verticalAlignment == 4);
        this.verticalAlignment = verticalAlignment;
    }

    public float getEffectivePaddingLeft()
    {
        if(isUseBorderPadding())
        {
            float border = getBorderWidthLeft() / (isUseVariableBorders() ? 1.0F : 2.0F);
            return paddingLeft + border;
        } else
        {
            return paddingLeft;
        }
    }

    public float getPaddingLeft()
    {
        return paddingLeft;
    }

    public void setPaddingLeft(float paddingLeft)
    {
        this.paddingLeft = paddingLeft;
    }

    public float getEffectivePaddingRight()
    {
        if(isUseBorderPadding())
        {
            float border = getBorderWidthRight() / (isUseVariableBorders() ? 1.0F : 2.0F);
            return paddingRight + border;
        } else
        {
            return paddingRight;
        }
    }

    public float getPaddingRight()
    {
        return paddingRight;
    }

    public void setPaddingRight(float paddingRight)
    {
        this.paddingRight = paddingRight;
    }

    public float getEffectivePaddingTop()
    {
        if(isUseBorderPadding())
        {
            float border = getBorderWidthTop() / (isUseVariableBorders() ? 1.0F : 2.0F);
            return paddingTop + border;
        } else
        {
            return paddingTop;
        }
    }

    public float getPaddingTop()
    {
        return paddingTop;
    }

    public void setPaddingTop(float paddingTop)
    {
        this.paddingTop = paddingTop;
    }

    public float getEffectivePaddingBottom()
    {
        if(isUseBorderPadding())
        {
            float border = getBorderWidthBottom() / (isUseVariableBorders() ? 1.0F : 2.0F);
            return paddingBottom + border;
        } else
        {
            return paddingBottom;
        }
    }

    public float getPaddingBottom()
    {
        return paddingBottom;
    }

    public void setPaddingBottom(float paddingBottom)
    {
        this.paddingBottom = paddingBottom;
    }

    public void setPadding(float padding)
    {
        paddingBottom = padding;
        paddingTop = padding;
        paddingLeft = padding;
        paddingRight = padding;
    }

    public boolean isUseBorderPadding()
    {
        return useBorderPadding;
    }

    public void setUseBorderPadding(boolean use)
    {
        useBorderPadding = use;
    }

    public void setLeading(float fixedLeading, float multipliedLeading)
    {
        column.setLeading(fixedLeading, multipliedLeading);
    }

    public float getLeading()
    {
        return column.getLeading();
    }

    public float getMultipliedLeading()
    {
        return column.getMultipliedLeading();
    }

    public void setIndent(float indent)
    {
        column.setIndent(indent);
    }

    public float getIndent()
    {
        return column.getIndent();
    }

    public float getExtraParagraphSpace()
    {
        return column.getExtraParagraphSpace();
    }

    public void setExtraParagraphSpace(float extraParagraphSpace)
    {
        column.setExtraParagraphSpace(extraParagraphSpace);
    }

    public void setFixedHeight(float fixedHeight)
    {
        this.fixedHeight = fixedHeight;
        minimumHeight = 0.0F;
    }

    public float getFixedHeight()
    {
        return fixedHeight;
    }

    public boolean hasFixedHeight()
    {
        return getFixedHeight() > 0.0F;
    }

    public void setMinimumHeight(float minimumHeight)
    {
        this.minimumHeight = minimumHeight;
        fixedHeight = 0.0F;
    }

    public float getMinimumHeight()
    {
        return minimumHeight;
    }

    public boolean hasMinimumHeight()
    {
        return getMinimumHeight() > 0.0F;
    }

    public boolean isNoWrap()
    {
        return noWrap;
    }

    public void setNoWrap(boolean noWrap)
    {
        this.noWrap = noWrap;
    }

    public PdfPTable getTable()
    {
        return table;
    }

    void setTable(PdfPTable table)
    {
        this.table = table;
        column.setText(null);
        image = null;
        if(table != null)
        {
            table.setExtendLastRow(verticalAlignment == 4);
            column.addElement(table);
            table.setWidthPercentage(100F);
        }
    }

    public int getColspan()
    {
        return colspan;
    }

    public void setColspan(int colspan)
    {
        this.colspan = colspan;
    }

    public int getRowspan()
    {
        return rowspan;
    }

    public void setRowspan(int rowspan)
    {
        this.rowspan = rowspan;
    }

    public void setFollowingIndent(float indent)
    {
        column.setFollowingIndent(indent);
    }

    public float getFollowingIndent()
    {
        return column.getFollowingIndent();
    }

    public void setRightIndent(float indent)
    {
        column.setRightIndent(indent);
    }

    public float getRightIndent()
    {
        return column.getRightIndent();
    }

    public float getSpaceCharRatio()
    {
        return column.getSpaceCharRatio();
    }

    public void setSpaceCharRatio(float spaceCharRatio)
    {
        column.setSpaceCharRatio(spaceCharRatio);
    }

    public void setRunDirection(int runDirection)
    {
        column.setRunDirection(runDirection);
    }

    public int getRunDirection()
    {
        return column.getRunDirection();
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        column.setText(null);
        table = null;
        this.image = image;
    }

    public PdfPCellEvent getCellEvent()
    {
        return cellEvent;
    }

    public void setCellEvent(PdfPCellEvent cellEvent)
    {
        if(cellEvent == null)
            this.cellEvent = null;
        else
        if(this.cellEvent == null)
            this.cellEvent = cellEvent;
        else
        if(this.cellEvent instanceof PdfPCellEventForwarder)
        {
            ((PdfPCellEventForwarder)this.cellEvent).addCellEvent(cellEvent);
        } else
        {
            PdfPCellEventForwarder forward = new PdfPCellEventForwarder();
            forward.addCellEvent(this.cellEvent);
            forward.addCellEvent(cellEvent);
            this.cellEvent = forward;
        }
    }

    public int getArabicOptions()
    {
        return column.getArabicOptions();
    }

    public void setArabicOptions(int arabicOptions)
    {
        column.setArabicOptions(arabicOptions);
    }

    public boolean isUseAscender()
    {
        return column.isUseAscender();
    }

    public void setUseAscender(boolean useAscender)
    {
        column.setUseAscender(useAscender);
    }

    public boolean isUseDescender()
    {
        return useDescender;
    }

    public void setUseDescender(boolean useDescender)
    {
        this.useDescender = useDescender;
    }

    public ColumnText getColumn()
    {
        return column;
    }

    public List getCompositeElements()
    {
        return getColumn().compositeElements;
    }

    public void setColumn(ColumnText column)
    {
        this.column = column;
    }

    public int getRotation()
    {
        return rotation;
    }

    public void setRotation(int rotation)
    {
        rotation %= 360;
        if(rotation < 0)
            rotation += 360;
        if(rotation % 90 != 0)
        {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("rotation.must.be.a.multiple.of.90", new Object[0]));
        } else
        {
            this.rotation = rotation;
            return;
        }
    }

    public float getMaxHeight()
    {
        boolean pivoted = getRotation() == 90 || getRotation() == 270;
        Image img = getImage();
        if(img != null)
        {
            img.scalePercent(100F);
            float refWidth = pivoted ? img.getScaledHeight() : img.getScaledWidth();
            float scale = (getRight() - getEffectivePaddingRight() - getEffectivePaddingLeft() - getLeft()) / refWidth;
            img.scalePercent(scale * 100F);
            float refHeight = pivoted ? img.getScaledWidth() : img.getScaledHeight();
            setBottom(getTop() - getEffectivePaddingTop() - getEffectivePaddingBottom() - refHeight);
        } else
        if(pivoted && hasFixedHeight() || getColumn() == null)
        {
            setBottom(getTop() - getFixedHeight());
        } else
        {
            ColumnText ct = ColumnText.duplicate(getColumn());
            float right;
            float top;
            float left;
            float bottom;
            if(pivoted)
            {
                right = 20000F;
                top = getRight() - getEffectivePaddingRight();
                left = 0.0F;
                bottom = getLeft() + getEffectivePaddingLeft();
            } else
            {
                right = isNoWrap() ? 20000F : getRight() - getEffectivePaddingRight();
                top = getTop() - getEffectivePaddingTop();
                left = getLeft() + getEffectivePaddingLeft();
                bottom = hasFixedHeight() ? (getTop() + getEffectivePaddingBottom()) - getFixedHeight() : -1.073742E+009F;
            }
            PdfPRow.setColumn(ct, left, bottom, right, top);
            try
            {
                ct.go(true);
            }
            catch(DocumentException e)
            {
                throw new ExceptionConverter(e);
            }
            if(pivoted)
            {
                setBottom(getTop() - getEffectivePaddingTop() - getEffectivePaddingBottom() - ct.getFilledWidth());
            } else
            {
                float yLine = ct.getYLine();
                if(isUseDescender())
                    yLine += ct.getDescender();
                setBottom(yLine - getEffectivePaddingBottom());
            }
        }
        float height = getHeight();
        if(height == getEffectivePaddingTop() + getEffectivePaddingBottom())
            height = 0.0F;
        if(hasFixedHeight())
            height = getFixedHeight();
        else
        if(hasMinimumHeight() && height < getMinimumHeight())
            height = getMinimumHeight();
        return height;
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

    public void addHeader(PdfPHeaderCell header)
    {
        if(headers == null)
            headers = new ArrayList();
        headers.add(header);
    }

    public ArrayList getHeaders()
    {
        return headers;
    }

    private ColumnText column;
    private int verticalAlignment;
    private float paddingLeft;
    private float paddingRight;
    private float paddingTop;
    private float paddingBottom;
    private float fixedHeight;
    private float minimumHeight;
    private boolean noWrap;
    private PdfPTable table;
    private int colspan;
    private int rowspan;
    private Image image;
    private PdfPCellEvent cellEvent;
    private boolean useDescender;
    private boolean useBorderPadding;
    protected Phrase phrase;
    private int rotation;
    protected PdfName role;
    protected HashMap accessibleAttributes;
    protected AccessibleElementId id;
    protected ArrayList headers;
}
