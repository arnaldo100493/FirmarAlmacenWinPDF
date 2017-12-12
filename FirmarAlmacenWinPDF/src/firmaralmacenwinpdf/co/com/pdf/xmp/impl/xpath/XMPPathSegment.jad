// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPPathSegment.java

package co.com.pdf.xmp.impl.xpath;


public class XMPPathSegment
{

    public XMPPathSegment(String name)
    {
        this.name = name;
    }

    public XMPPathSegment(String name, int kind)
    {
        this.name = name;
        this.kind = kind;
    }

    public int getKind()
    {
        return kind;
    }

    public void setKind(int kind)
    {
        this.kind = kind;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAlias(boolean alias)
    {
        this.alias = alias;
    }

    public boolean isAlias()
    {
        return alias;
    }

    public int getAliasForm()
    {
        return aliasForm;
    }

    public void setAliasForm(int aliasForm)
    {
        this.aliasForm = aliasForm;
    }

    public String toString()
    {
        switch(kind)
        {
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
            return name;

        case 5: // '\005'
        case 6: // '\006'
            return name;
        }
        return name;
    }

    private String name;
    private int kind;
    private boolean alias;
    private int aliasForm;
}
