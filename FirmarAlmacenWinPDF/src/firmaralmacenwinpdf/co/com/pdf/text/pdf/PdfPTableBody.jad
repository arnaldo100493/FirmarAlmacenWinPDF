// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPTableBody.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.AccessibleElementId;
import co.com.pdf.text.pdf.interfaces.IAccessibleElement;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfObject, PdfName

public class PdfPTableBody
    implements IAccessibleElement
{

    public PdfPTableBody()
    {
        id = new AccessibleElementId();
        rows = null;
        role = PdfName.TBODY;
        accessibleAttributes = null;
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

    protected AccessibleElementId id;
    protected ArrayList rows;
    protected PdfName role;
    protected HashMap accessibleAttributes;
}
