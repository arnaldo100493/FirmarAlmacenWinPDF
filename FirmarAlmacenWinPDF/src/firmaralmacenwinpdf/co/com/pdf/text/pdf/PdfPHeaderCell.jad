// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPHeaderCell.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfPCell, PdfName

public class PdfPHeaderCell extends PdfPCell
{

    public PdfPHeaderCell()
    {
        scope = 0;
        name = null;
        role = PdfName.TH;
    }

    public PdfPHeaderCell(PdfPHeaderCell headerCell)
    {
        super(headerCell);
        scope = 0;
        name = null;
        role = headerCell.role;
        scope = headerCell.scope;
        name = headerCell.getName();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public PdfName getRole()
    {
        return role;
    }

    public void setRole(PdfName role)
    {
        this.role = role;
    }

    public void setScope(int scope)
    {
        this.scope = scope;
    }

    public int getScope()
    {
        return scope;
    }

    public static final int NONE = 0;
    public static final int ROW = 1;
    public static final int COLUMN = 2;
    public static final int BOTH = 3;
    protected int scope;
    protected String name;
}
