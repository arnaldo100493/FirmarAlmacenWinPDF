// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfStructureElement.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.BaseColor;
import co.com.pdf.text.Chunk;
import co.com.pdf.text.Document;
import co.com.pdf.text.DocumentException;
import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.Font;
import co.com.pdf.text.Image;
import co.com.pdf.text.List;
import co.com.pdf.text.ListBody;
import co.com.pdf.text.ListItem;
import co.com.pdf.text.ListLabel;
import co.com.pdf.text.Paragraph;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.interfaces.IAccessibleElement;
import co.com.pdf.text.pdf.interfaces.IPdfStructureElement;
import co.com.pdf.text.pdf.internal.PdfVersionImp;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfStructureTreeRoot, PdfArray, PdfNumber, 
//            PdfNull, PdfPTable, PdfPRow, PdfPHeaderCell, 
//            PdfPCell, PdfPTableHeader, PdfPTableFooter, PdfPTableBody, 
//            PdfDiv, PdfName, PdfRectangle, PdfString, 
//            PdfObject, PdfWriter, PdfIndirectReference

public class PdfStructureElement extends PdfDictionary
    implements IPdfStructureElement
{

    public PdfStructureElement(PdfStructureElement parent, PdfName structureType)
    {
        top = parent.top;
        init(parent, structureType);
        this.parent = parent;
        put(PdfName.P, parent.reference);
        put(PdfName.TYPE, PdfName.STRUCTELEM);
    }

    public PdfStructureElement(PdfStructureTreeRoot parent, PdfName structureType)
    {
        top = parent;
        init(parent, structureType);
        put(PdfName.P, parent.getReference());
        put(PdfName.TYPE, PdfName.STRUCTELEM);
    }

    protected PdfStructureElement(PdfDictionary parent, PdfName structureType)
    {
        if(parent instanceof PdfStructureElement)
        {
            top = ((PdfStructureElement)parent).top;
            init(parent, structureType);
            this.parent = (PdfStructureElement)parent;
            put(PdfName.P, ((PdfStructureElement)parent).reference);
            put(PdfName.TYPE, PdfName.STRUCTELEM);
        } else
        if(parent instanceof PdfStructureTreeRoot)
        {
            top = (PdfStructureTreeRoot)parent;
            init(parent, structureType);
            put(PdfName.P, ((PdfStructureTreeRoot)parent).getReference());
            put(PdfName.TYPE, PdfName.STRUCTELEM);
        }
    }

    public PdfName getStructureType()
    {
        return structureType;
    }

    private void init(PdfDictionary parent, PdfName structureType)
    {
        if(!top.getWriter().getStandardStructElems().contains(structureType))
        {
            PdfDictionary roleMap = top.getAsDict(PdfName.ROLEMAP);
            if(roleMap == null || !roleMap.contains(structureType))
                throw new ExceptionConverter(new DocumentException(MessageLocalization.getComposedMessage("unknown.structure.element.role.1", new Object[] {
                    structureType.toString()
                })));
            this.structureType = roleMap.getAsName(structureType);
        } else
        {
            this.structureType = structureType;
        }
        PdfObject kido = parent.get(PdfName.K);
        PdfArray kids = null;
        if(kido == null)
        {
            kids = new PdfArray();
            parent.put(PdfName.K, kids);
        } else
        if(kido instanceof PdfArray)
        {
            kids = (PdfArray)kido;
        } else
        {
            kids = new PdfArray();
            kids.add(kido);
            parent.put(PdfName.K, kids);
        }
        if(kids.size() > 0)
        {
            if(kids.getAsNumber(0) != null)
                kids.remove(0);
            if(kids.size() > 0)
            {
                PdfDictionary mcr = kids.getAsDict(0);
                if(mcr != null && PdfName.MCR.equals(mcr.getAsName(PdfName.TYPE)))
                    kids.remove(0);
            }
        }
        kids.add(this);
        put(PdfName.S, structureType);
        reference = top.getWriter().getPdfIndirectReference();
    }

    public PdfDictionary getParent()
    {
        return getParent(false);
    }

    public PdfDictionary getParent(boolean includeStructTreeRoot)
    {
        if(parent == null && includeStructTreeRoot)
            return top;
        else
            return parent;
    }

    void setPageMark(int page, int mark)
    {
        if(mark >= 0)
            put(PdfName.K, new PdfNumber(mark));
        top.setPageMark(page, reference);
    }

    public PdfIndirectReference getReference()
    {
        return reference;
    }

    public PdfObject getAttribute(PdfName name)
    {
        PdfDictionary attr = getAsDict(PdfName.A);
        if(attr != null && attr.contains(name))
            return attr.get(name);
        PdfDictionary parent = getParent();
        if(parent instanceof PdfStructureElement)
            return ((PdfStructureElement)parent).getAttribute(name);
        if(parent instanceof PdfStructureTreeRoot)
            return ((PdfStructureTreeRoot)parent).getAttribute(name);
        else
            return new PdfNull();
    }

    public void setAttribute(PdfName name, PdfObject obj)
    {
        PdfDictionary attr = getAsDict(PdfName.A);
        if(attr == null)
        {
            attr = new PdfDictionary();
            put(PdfName.A, attr);
        }
        attr.put(name, obj);
    }

    public void writeAttributes(IAccessibleElement element)
    {
        if(top.getWriter().getPdfVersion().getVersion() < '7')
            return;
        if(element instanceof ListItem)
            writeAttributes((ListItem)element);
        else
        if(element instanceof Paragraph)
            writeAttributes((Paragraph)element);
        else
        if(element instanceof Chunk)
            writeAttributes((Chunk)element);
        else
        if(element instanceof Image)
            writeAttributes((Image)element);
        else
        if(element instanceof List)
            writeAttributes((List)element);
        else
        if(element instanceof ListLabel)
            writeAttributes((ListLabel)element);
        else
        if(element instanceof ListBody)
            writeAttributes((ListBody)element);
        else
        if(element instanceof PdfPTable)
            writeAttributes((PdfPTable)element);
        else
        if(element instanceof PdfPRow)
            writeAttributes((PdfPRow)element);
        else
        if(element instanceof PdfPHeaderCell)
            writeAttributes((PdfPHeaderCell)element);
        else
        if(element instanceof PdfPCell)
            writeAttributes((PdfPCell)element);
        else
        if(element instanceof PdfPTableHeader)
            writeAttributes((PdfPTableHeader)element);
        else
        if(element instanceof PdfPTableFooter)
            writeAttributes((PdfPTableFooter)element);
        else
        if(element instanceof PdfPTableBody)
            writeAttributes((PdfPTableBody)element);
        else
        if(element instanceof PdfDiv)
            writeAttributes((PdfDiv)element);
        else
        if(element instanceof Document)
            writeAttributes((Document)element);
        if(element.getAccessibleAttributes() != null)
        {
            for(Iterator i$ = element.getAccessibleAttributes().keySet().iterator(); i$.hasNext();)
            {
                PdfName key = (PdfName)i$.next();
                if(key.equals(PdfName.LANG) || key.equals(PdfName.ALT) || key.equals(PdfName.ACTUALTEXT) || key.equals(PdfName.E))
                    put(key, element.getAccessibleAttribute(key));
                else
                    setAttribute(key, element.getAccessibleAttribute(key));
            }

        }
    }

    private void writeAttributes(Chunk chunk)
    {
        if(chunk != null)
            if(chunk.getImage() != null)
            {
                writeAttributes(chunk.getImage());
            } else
            {
                HashMap attr = chunk.getAttributes();
                if(attr != null)
                {
                    if(attr.containsKey("UNDERLINE"))
                        setAttribute(PdfName.TEXTDECORATIONTYPE, PdfName.UNDERLINE);
                    if(attr.containsKey("BACKGROUND"))
                    {
                        Object back[] = (Object[])(Object[])attr.get("BACKGROUND");
                        BaseColor color = (BaseColor)back[0];
                        setAttribute(PdfName.BACKGROUNDCOLOR, new PdfArray(new float[] {
                            (float)color.getRed() / 255F, (float)color.getGreen() / 255F, (float)color.getBlue() / 255F
                        }));
                    }
                    IPdfStructureElement parent = (IPdfStructureElement)getParent(true);
                    PdfObject obj = parent.getAttribute(PdfName.COLOR);
                    if(chunk.getFont() != null && chunk.getFont().getColor() != null)
                    {
                        BaseColor c = chunk.getFont().getColor();
                        setColorAttribute(c, obj, PdfName.COLOR);
                    }
                    PdfObject decorThickness = parent.getAttribute(PdfName.TEXTDECORATIONTHICKNESS);
                    PdfObject decorColor = parent.getAttribute(PdfName.TEXTDECORATIONCOLOR);
                    if(attr.containsKey("UNDERLINE"))
                    {
                        Object unders[][] = (Object[][])(Object[][])attr.get("UNDERLINE");
                        Object arr[] = unders[unders.length - 1];
                        BaseColor color = (BaseColor)arr[0];
                        float floats[] = (float[])(float[])arr[1];
                        float thickness = floats[0];
                        if(decorThickness instanceof PdfNumber)
                        {
                            float t = ((PdfNumber)decorThickness).floatValue();
                            if(Float.compare(thickness, t) != 0)
                                setAttribute(PdfName.TEXTDECORATIONTHICKNESS, new PdfNumber(thickness));
                        } else
                        {
                            setAttribute(PdfName.TEXTDECORATIONTHICKNESS, new PdfNumber(thickness));
                        }
                        if(color != null)
                            setColorAttribute(color, decorColor, PdfName.TEXTDECORATIONCOLOR);
                    }
                    if(attr.containsKey("LINEHEIGHT"))
                    {
                        float height = ((Float)attr.get("LINEHEIGHT")).floatValue();
                        PdfObject parentLH = parent.getAttribute(PdfName.LINEHEIGHT);
                        if(parentLH instanceof PdfNumber)
                        {
                            float pLH = ((PdfNumber)parentLH).floatValue();
                            if(Float.compare(pLH, height) != 0)
                                setAttribute(PdfName.LINEHEIGHT, new PdfNumber(height));
                        } else
                        {
                            setAttribute(PdfName.LINEHEIGHT, new PdfNumber(height));
                        }
                    }
                }
            }
    }

    private void writeAttributes(Image image)
    {
        if(image != null)
        {
            if(image.getWidth() > 0.0F)
                setAttribute(PdfName.WIDTH, new PdfNumber(image.getWidth()));
            if(image.getHeight() > 0.0F)
                setAttribute(PdfName.HEIGHT, new PdfNumber(image.getHeight()));
            PdfRectangle rect = new PdfRectangle(image, image.getRotation());
            setAttribute(PdfName.BBOX, rect);
            if(image.getBackgroundColor() != null)
            {
                BaseColor color = image.getBackgroundColor();
                setAttribute(PdfName.BACKGROUNDCOLOR, new PdfArray(new float[] {
                    (float)color.getRed() / 255F, (float)color.getGreen() / 255F, (float)color.getBlue() / 255F
                }));
            }
        }
    }

    private void writeAttributes(Paragraph paragraph)
    {
        if(paragraph != null)
        {
            if(Float.compare(paragraph.getSpacingBefore(), 0.0F) != 0)
                setAttribute(PdfName.SPACEBEFORE, new PdfNumber(paragraph.getSpacingBefore()));
            if(Float.compare(paragraph.getSpacingAfter(), 0.0F) != 0)
                setAttribute(PdfName.SPACEAFTER, new PdfNumber(paragraph.getSpacingAfter()));
            IPdfStructureElement parent = (IPdfStructureElement)getParent(true);
            PdfObject obj = parent.getAttribute(PdfName.COLOR);
            if(paragraph.getFont() != null && paragraph.getFont().getColor() != null)
            {
                BaseColor c = paragraph.getFont().getColor();
                setColorAttribute(c, obj, PdfName.COLOR);
            }
            obj = parent.getAttribute(PdfName.TEXTINDENT);
            if(Float.compare(paragraph.getFirstLineIndent(), 0.0F) != 0)
            {
                boolean writeIndent = true;
                if((obj instanceof PdfNumber) && Float.compare(((PdfNumber)obj).floatValue(), (new Float(paragraph.getFirstLineIndent())).floatValue()) == 0)
                    writeIndent = false;
                if(writeIndent)
                    setAttribute(PdfName.TEXTINDENT, new PdfNumber(paragraph.getFirstLineIndent()));
            }
            obj = parent.getAttribute(PdfName.STARTINDENT);
            if(obj instanceof PdfNumber)
            {
                float startIndent = ((PdfNumber)obj).floatValue();
                if(Float.compare(startIndent, paragraph.getIndentationLeft()) != 0)
                    setAttribute(PdfName.STARTINDENT, new PdfNumber(paragraph.getIndentationLeft()));
            } else
            if(Math.abs(paragraph.getIndentationLeft()) > 1.401298E-045F)
                setAttribute(PdfName.STARTINDENT, new PdfNumber(paragraph.getIndentationLeft()));
            obj = parent.getAttribute(PdfName.ENDINDENT);
            if(obj instanceof PdfNumber)
            {
                float endIndent = ((PdfNumber)obj).floatValue();
                if(Float.compare(endIndent, paragraph.getIndentationRight()) != 0)
                    setAttribute(PdfName.ENDINDENT, new PdfNumber(paragraph.getIndentationRight()));
            } else
            if(Float.compare(paragraph.getIndentationRight(), 0.0F) != 0)
                setAttribute(PdfName.ENDINDENT, new PdfNumber(paragraph.getIndentationRight()));
            setTextAlignAttribute(paragraph.getAlignment());
        }
    }

    private void writeAttributes(List list)
    {
        if(list != null)
        {
            setAttribute(PdfName.O, PdfName.LIST);
            if(list.isAutoindent())
                if(list.isNumbered())
                {
                    if(list.isLettered())
                    {
                        if(list.isLowercase())
                            setAttribute(PdfName.LISTNUMBERING, PdfName.LOWERROMAN);
                        else
                            setAttribute(PdfName.LISTNUMBERING, PdfName.UPPERROMAN);
                    } else
                    {
                        setAttribute(PdfName.LISTNUMBERING, PdfName.DECIMAL);
                    }
                } else
                if(list.isLettered())
                    if(list.isLowercase())
                        setAttribute(PdfName.LISTNUMBERING, PdfName.LOWERALPHA);
                    else
                        setAttribute(PdfName.LISTNUMBERING, PdfName.UPPERALPHA);
            PdfObject obj = parent.getAttribute(PdfName.STARTINDENT);
            if(obj instanceof PdfNumber)
            {
                float startIndent = ((PdfNumber)obj).floatValue();
                if(Float.compare(startIndent, list.getIndentationLeft()) != 0)
                    setAttribute(PdfName.STARTINDENT, new PdfNumber(list.getIndentationLeft()));
            } else
            if(Math.abs(list.getIndentationLeft()) > 1.401298E-045F)
                setAttribute(PdfName.STARTINDENT, new PdfNumber(list.getIndentationLeft()));
            obj = parent.getAttribute(PdfName.ENDINDENT);
            if(obj instanceof PdfNumber)
            {
                float endIndent = ((PdfNumber)obj).floatValue();
                if(Float.compare(endIndent, list.getIndentationRight()) != 0)
                    setAttribute(PdfName.ENDINDENT, new PdfNumber(list.getIndentationRight()));
            } else
            if(Float.compare(list.getIndentationRight(), 0.0F) != 0)
                setAttribute(PdfName.ENDINDENT, new PdfNumber(list.getIndentationRight()));
        }
    }

    private void writeAttributes(ListItem listItem)
    {
        if(listItem != null)
        {
            PdfObject obj = parent.getAttribute(PdfName.STARTINDENT);
            if(obj instanceof PdfNumber)
            {
                float startIndent = ((PdfNumber)obj).floatValue();
                if(Float.compare(startIndent, listItem.getIndentationLeft()) != 0)
                    setAttribute(PdfName.STARTINDENT, new PdfNumber(listItem.getIndentationLeft()));
            } else
            if(Math.abs(listItem.getIndentationLeft()) > 1.401298E-045F)
                setAttribute(PdfName.STARTINDENT, new PdfNumber(listItem.getIndentationLeft()));
            obj = parent.getAttribute(PdfName.ENDINDENT);
            if(obj instanceof PdfNumber)
            {
                float endIndent = ((PdfNumber)obj).floatValue();
                if(Float.compare(endIndent, listItem.getIndentationRight()) != 0)
                    setAttribute(PdfName.ENDINDENT, new PdfNumber(listItem.getIndentationRight()));
            } else
            if(Float.compare(listItem.getIndentationRight(), 0.0F) != 0)
                setAttribute(PdfName.ENDINDENT, new PdfNumber(listItem.getIndentationRight()));
        }
    }

    private void writeAttributes(ListBody listBody)
    {
        if(listBody == null);
    }

    private void writeAttributes(ListLabel listLabel)
    {
        if(listLabel != null)
        {
            PdfObject obj = parent.getAttribute(PdfName.STARTINDENT);
            if(obj instanceof PdfNumber)
            {
                float startIndent = ((PdfNumber)obj).floatValue();
                if(Float.compare(startIndent, listLabel.getIndentation()) != 0)
                    setAttribute(PdfName.STARTINDENT, new PdfNumber(listLabel.getIndentation()));
            } else
            if(Math.abs(listLabel.getIndentation()) > 1.401298E-045F)
                setAttribute(PdfName.STARTINDENT, new PdfNumber(listLabel.getIndentation()));
        }
    }

    private void writeAttributes(PdfPTable table)
    {
        if(table != null)
        {
            if(Float.compare(table.getSpacingBefore(), 0.0F) != 0)
                setAttribute(PdfName.SPACEBEFORE, new PdfNumber(table.getSpacingBefore()));
            if(Float.compare(table.getSpacingAfter(), 0.0F) != 0)
                setAttribute(PdfName.SPACEAFTER, new PdfNumber(table.getSpacingAfter()));
            if(table.getTotalHeight() > 0.0F)
                setAttribute(PdfName.HEIGHT, new PdfNumber(table.getTotalHeight()));
            if(table.getTotalWidth() > 0.0F)
                setAttribute(PdfName.WIDTH, new PdfNumber(table.getTotalWidth()));
        }
    }

    private void writeAttributes(PdfPRow row)
    {
        if(row != null)
            setAttribute(PdfName.O, PdfName.TABLE);
    }

    private void writeAttributes(PdfPCell cell)
    {
        if(cell != null)
        {
            setAttribute(PdfName.O, PdfName.TABLE);
            if(cell.getColspan() != 1)
                setAttribute(PdfName.COLSPAN, new PdfNumber(cell.getColspan()));
            if(cell.getRowspan() != 1)
                setAttribute(PdfName.ROWSPAN, new PdfNumber(cell.getRowspan()));
            if(cell.getHeaders() != null)
            {
                PdfArray headers = new PdfArray();
                ArrayList list = cell.getHeaders();
                Iterator i$ = list.iterator();
                do
                {
                    if(!i$.hasNext())
                        break;
                    PdfPHeaderCell header = (PdfPHeaderCell)i$.next();
                    if(header.getName() != null)
                        headers.add(new PdfString(header.getName()));
                } while(true);
                if(!headers.isEmpty())
                    setAttribute(PdfName.HEADERS, headers);
            }
            if(cell.getFixedHeight() > 0.0F)
                setAttribute(PdfName.HEIGHT, new PdfNumber(cell.getFixedHeight()));
            if(cell.getWidth() > 0.0F)
                setAttribute(PdfName.WIDTH, new PdfNumber(cell.getWidth()));
            if(cell.getBackgroundColor() != null)
            {
                BaseColor color = cell.getBackgroundColor();
                setAttribute(PdfName.BACKGROUNDCOLOR, new PdfArray(new float[] {
                    (float)color.getRed() / 255F, (float)color.getGreen() / 255F, (float)color.getBlue() / 255F
                }));
            }
        }
    }

    private void writeAttributes(PdfPHeaderCell headerCell)
    {
        if(headerCell != null)
        {
            if(headerCell.getScope() != 0)
                switch(headerCell.getScope())
                {
                case 1: // '\001'
                    setAttribute(PdfName.SCOPE, PdfName.ROW);
                    break;

                case 2: // '\002'
                    setAttribute(PdfName.SCOPE, PdfName.COLUMN);
                    break;

                case 3: // '\003'
                    setAttribute(PdfName.SCOPE, PdfName.BOTH);
                    break;
                }
            if(headerCell.getName() != null)
                setAttribute(PdfName.NAME, new PdfName(headerCell.getName()));
            writeAttributes(((PdfPCell) (headerCell)));
        }
    }

    private void writeAttributes(PdfPTableHeader header)
    {
        if(header != null)
            setAttribute(PdfName.O, PdfName.TABLE);
    }

    private void writeAttributes(PdfPTableBody body)
    {
        if(body == null);
    }

    private void writeAttributes(PdfPTableFooter footer)
    {
        if(footer == null);
    }

    private void writeAttributes(PdfDiv div)
    {
        if(div != null)
        {
            if(div.getBackgroundColor() != null)
                setColorAttribute(div.getBackgroundColor(), null, PdfName.BACKGROUNDCOLOR);
            setTextAlignAttribute(div.getTextAlignment());
        }
    }

    private void writeAttributes(Document document)
    {
        if(document == null);
    }

    private boolean colorsEqual(PdfArray parentColor, float color[])
    {
        if(Float.compare(color[0], parentColor.getAsNumber(0).floatValue()) != 0)
            return false;
        if(Float.compare(color[1], parentColor.getAsNumber(1).floatValue()) != 0)
            return false;
        return Float.compare(color[2], parentColor.getAsNumber(2).floatValue()) == 0;
    }

    private void setColorAttribute(BaseColor newColor, PdfObject oldColor, PdfName attributeName)
    {
        float colorArr[] = {
            (float)newColor.getRed() / 255F, (float)newColor.getGreen() / 255F, (float)newColor.getBlue() / 255F
        };
        if(oldColor != null && (oldColor instanceof PdfArray))
        {
            PdfArray oldC = (PdfArray)oldColor;
            if(colorsEqual(oldC, colorArr))
                setAttribute(attributeName, new PdfArray(colorArr));
            else
                setAttribute(attributeName, new PdfArray(colorArr));
        } else
        {
            setAttribute(attributeName, new PdfArray(colorArr));
        }
    }

    private void setTextAlignAttribute(int elementAlign)
    {
        PdfName align = null;
        switch(elementAlign)
        {
        case 0: // '\0'
            align = PdfName.START;
            break;

        case 1: // '\001'
            align = PdfName.CENTER;
            break;

        case 2: // '\002'
            align = PdfName.END;
            break;

        case 3: // '\003'
            align = PdfName.JUSTIFY;
            break;
        }
        PdfObject obj = parent.getAttribute(PdfName.TEXTALIGN);
        if(obj instanceof PdfName)
        {
            PdfName textAlign = (PdfName)obj;
            if(align != null && !textAlign.equals(align))
                setAttribute(PdfName.TEXTALIGN, align);
        } else
        if(align != null && !PdfName.START.equals(align))
            setAttribute(PdfName.TEXTALIGN, align);
    }

    public void toPdf(PdfWriter writer, OutputStream os)
        throws IOException
    {
        PdfWriter.checkPdfIsoConformance(writer, 16, this);
        super.toPdf(writer, os);
    }

    private PdfStructureElement parent;
    private PdfStructureTreeRoot top;
    private PdfIndirectReference reference;
    private PdfName structureType;
}
