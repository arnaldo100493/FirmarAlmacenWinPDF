// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfTemplate.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.AccessibleElementId;
import co.com.pdf.text.Rectangle;
import co.com.pdf.text.pdf.interfaces.IAccessibleElement;
import java.io.IOException;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfContentByte, PageResources, PdfArray, PdfNumber, 
//            PdfFormXObject, PdfObject, PdfIndirectReference, PdfName, 
//            PdfWriter, ByteBuffer, PdfTransparencyGroup, PdfOCG, 
//            PdfDictionary, PdfStream

public class PdfTemplate extends PdfContentByte
    implements IAccessibleElement
{

    protected PdfTemplate()
    {
        super(null);
        bBox = new Rectangle(0.0F, 0.0F);
        contentTagged = false;
        additional = null;
        role = PdfName.FIGURE;
        accessibleAttributes = null;
        id = null;
        type = 1;
    }

    PdfTemplate(PdfWriter wr)
    {
        super(wr);
        bBox = new Rectangle(0.0F, 0.0F);
        contentTagged = false;
        additional = null;
        role = PdfName.FIGURE;
        accessibleAttributes = null;
        id = null;
        type = 1;
        pageResources = new PageResources();
        pageResources.addDefaultColor(wr.getDefaultColorspace());
        thisReference = writer.getPdfIndirectReference();
    }

    public static PdfTemplate createTemplate(PdfWriter writer, float width, float height)
    {
        return createTemplate(writer, width, height, null);
    }

    static PdfTemplate createTemplate(PdfWriter writer, float width, float height, PdfName forcedName)
    {
        PdfTemplate template = new PdfTemplate(writer);
        template.setWidth(width);
        template.setHeight(height);
        writer.addDirectTemplateSimple(template, forcedName);
        return template;
    }

    public boolean isTagged()
    {
        return super.isTagged() && contentTagged;
    }

    public void setWidth(float width)
    {
        bBox.setLeft(0.0F);
        bBox.setRight(width);
    }

    public void setHeight(float height)
    {
        bBox.setBottom(0.0F);
        bBox.setTop(height);
    }

    public float getWidth()
    {
        return bBox.getWidth();
    }

    public float getHeight()
    {
        return bBox.getHeight();
    }

    public Rectangle getBoundingBox()
    {
        return bBox;
    }

    public void setBoundingBox(Rectangle bBox)
    {
        this.bBox = bBox;
    }

    public void setLayer(PdfOCG layer)
    {
        this.layer = layer;
    }

    public PdfOCG getLayer()
    {
        return layer;
    }

    public void setMatrix(float a, float b, float c, float d, float e, float f)
    {
        matrix = new PdfArray();
        matrix.add(new PdfNumber(a));
        matrix.add(new PdfNumber(b));
        matrix.add(new PdfNumber(c));
        matrix.add(new PdfNumber(d));
        matrix.add(new PdfNumber(e));
        matrix.add(new PdfNumber(f));
    }

    PdfArray getMatrix()
    {
        return matrix;
    }

    public PdfIndirectReference getIndirectReference()
    {
        if(thisReference == null)
            thisReference = writer.getPdfIndirectReference();
        return thisReference;
    }

    public void beginVariableText()
    {
        content.append("/Tx BMC ");
    }

    public void endVariableText()
    {
        content.append("EMC ");
    }

    PdfObject getResources()
    {
        return getPageResources().getResources();
    }

    public PdfStream getFormXObject(int compressionLevel)
        throws IOException
    {
        return new PdfFormXObject(this, compressionLevel);
    }

    public PdfContentByte getDuplicate()
    {
        PdfTemplate tpl = new PdfTemplate();
        tpl.writer = writer;
        tpl.pdf = pdf;
        tpl.thisReference = thisReference;
        tpl.pageResources = pageResources;
        tpl.bBox = new Rectangle(bBox);
        tpl.group = group;
        tpl.layer = layer;
        if(matrix != null)
            tpl.matrix = new PdfArray(matrix);
        tpl.separator = separator;
        tpl.additional = additional;
        return tpl;
    }

    public int getType()
    {
        return type;
    }

    PageResources getPageResources()
    {
        return pageResources;
    }

    public PdfTransparencyGroup getGroup()
    {
        return group;
    }

    public void setGroup(PdfTransparencyGroup group)
    {
        this.group = group;
    }

    public PdfDictionary getAdditional()
    {
        return additional;
    }

    public void setAdditional(PdfDictionary additional)
    {
        this.additional = additional;
    }

    public PdfIndirectReference getCurrentPage()
    {
        return pageReference != null ? pageReference : writer.getCurrentPage();
    }

    public PdfIndirectReference getPageReference()
    {
        return pageReference;
    }

    public void setPageReference(PdfIndirectReference pageReference)
    {
        this.pageReference = pageReference;
    }

    public boolean isContentTagged()
    {
        return contentTagged;
    }

    public void setContentTagged(boolean contentTagged)
    {
        this.contentTagged = contentTagged;
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
        if(id == null)
            id = new AccessibleElementId();
        return id;
    }

    public void setId(AccessibleElementId id)
    {
        this.id = id;
    }

    public static final int TYPE_TEMPLATE = 1;
    public static final int TYPE_IMPORTED = 2;
    public static final int TYPE_PATTERN = 3;
    protected int type;
    protected PdfIndirectReference thisReference;
    protected PageResources pageResources;
    protected Rectangle bBox;
    protected PdfArray matrix;
    protected PdfTransparencyGroup group;
    protected PdfOCG layer;
    protected PdfIndirectReference pageReference;
    protected boolean contentTagged;
    private PdfDictionary additional;
    protected PdfName role;
    protected HashMap accessibleAttributes;
    private AccessibleElementId id;
}
