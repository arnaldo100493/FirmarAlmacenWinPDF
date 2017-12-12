// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfArtifact.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.AccessibleElementId;
import co.com.pdf.text.pdf.interfaces.IAccessibleElement;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfObject, PdfString, PdfArray, PdfName

public class PdfArtifact
    implements IAccessibleElement
{

    public PdfArtifact()
    {
        role = PdfName.ARTIFACT;
        accessibleAttributes = null;
        id = new AccessibleElementId();
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

    public void setRole(PdfName pdfname)
    {
    }

    public AccessibleElementId getId()
    {
        return id;
    }

    public void setId(AccessibleElementId id)
    {
        this.id = id;
    }

    public PdfString getType()
    {
        return accessibleAttributes != null ? (PdfString)accessibleAttributes.get(PdfName.TYPE) : null;
    }

    public void setType(PdfString type)
    {
        setAccessibleAttribute(PdfName.TYPE, type);
    }

    public PdfArray getBBox()
    {
        return accessibleAttributes != null ? (PdfArray)accessibleAttributes.get(PdfName.BBOX) : null;
    }

    public void setBBox(PdfArray bbox)
    {
        setAccessibleAttribute(PdfName.BBOX, bbox);
    }

    public PdfArray getAttached()
    {
        return accessibleAttributes != null ? (PdfArray)accessibleAttributes.get(PdfName.ATTACHED) : null;
    }

    public void setAttached(PdfArray attached)
    {
        setAccessibleAttribute(PdfName.ATTACHED, attached);
    }

    protected PdfName role;
    protected HashMap accessibleAttributes;
    protected AccessibleElementId id;
}
