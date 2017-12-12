// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ZapfDingbatsNumberList.java

package co.com.pdf.text;

import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text:
//            List, ListItem, Chunk, Font, 
//            FontFactory, Element

public class ZapfDingbatsNumberList extends List
{

    public ZapfDingbatsNumberList(int type)
    {
        super(true);
        this.type = type;
        float fontsize = symbol.getFont().getSize();
        symbol.setFont(FontFactory.getFont("ZapfDingbats", fontsize, 0));
        postSymbol = " ";
    }

    public ZapfDingbatsNumberList(int type, int symbolIndent)
    {
        super(true, symbolIndent);
        this.type = type;
        float fontsize = symbol.getFont().getSize();
        symbol.setFont(FontFactory.getFont("ZapfDingbats", fontsize, 0));
        postSymbol = " ";
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getType()
    {
        return type;
    }

    public boolean add(Element o)
    {
        if(o instanceof ListItem)
        {
            ListItem item = (ListItem)o;
            Chunk chunk = new Chunk(preSymbol, symbol.getFont());
            chunk.setAttributes(symbol.getAttributes());
            switch(type)
            {
            case 0: // '\0'
                chunk.append(String.valueOf((char)(first + list.size() + 171)));
                break;

            case 1: // '\001'
                chunk.append(String.valueOf((char)(first + list.size() + 181)));
                break;

            case 2: // '\002'
                chunk.append(String.valueOf((char)(first + list.size() + 191)));
                break;

            default:
                chunk.append(String.valueOf((char)(first + list.size() + 201)));
                break;
            }
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

    protected int type;
}
