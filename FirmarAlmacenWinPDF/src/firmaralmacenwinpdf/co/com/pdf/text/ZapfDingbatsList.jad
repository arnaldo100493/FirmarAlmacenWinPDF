// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ZapfDingbatsList.java

package co.com.pdf.text;

import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text:
//            List, ListItem, Chunk, Font, 
//            FontFactory, BaseColor, Element

public class ZapfDingbatsList extends List
{

    public ZapfDingbatsList(int zn)
    {
        super(true);
        this.zn = zn;
        float fontsize = symbol.getFont().getSize();
        symbol.setFont(FontFactory.getFont("ZapfDingbats", fontsize, 0));
        postSymbol = " ";
    }

    public ZapfDingbatsList(int zn, int symbolIndent)
    {
        super(true, symbolIndent);
        this.zn = zn;
        float fontsize = symbol.getFont().getSize();
        symbol.setFont(FontFactory.getFont("ZapfDingbats", fontsize, 0));
        postSymbol = " ";
    }

    public ZapfDingbatsList(int zn, int symbolIndent, BaseColor zapfDingbatColor)
    {
        super(true, symbolIndent);
        this.zn = zn;
        float fontsize = symbol.getFont().getSize();
        symbol.setFont(FontFactory.getFont("ZapfDingbats", fontsize, 0, zapfDingbatColor));
        postSymbol = " ";
    }

    public void setDingbatColor(BaseColor zapfDingbatColor)
    {
        float fontsize = symbol.getFont().getSize();
        symbol.setFont(FontFactory.getFont("ZapfDingbats", fontsize, 0, zapfDingbatColor));
    }

    public void setCharNumber(int zn)
    {
        this.zn = zn;
    }

    public int getCharNumber()
    {
        return zn;
    }

    public boolean add(Element o)
    {
        if(o instanceof ListItem)
        {
            ListItem item = (ListItem)o;
            Chunk chunk = new Chunk(preSymbol, symbol.getFont());
            chunk.setAttributes(symbol.getAttributes());
            chunk.append(String.valueOf((char)zn));
            chunk.append(postSymbol);
            item.setListSymbol(chunk);
            item.setIndentationLeft(symbolIndent, autoindent);
            item.setIndentationRight(0.0F);
            list.add(item);
        } else
        if(o instanceof List)
        {
            List nested = (List)o;
            nested.setIndentationLeft(nested.getIndentationLeft() + symbolIndent);
            first--;
            return list.add(nested);
        }
        return false;
    }

    protected int zn;
}
